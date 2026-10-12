package com.jakubjirak.structurizr.language.lexer

import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Tests that actually run the lexer over DSL text and assert the produced tokens.
 *
 * Regression guard for the zzUnpacktrans decompilation bug that made the lexer
 * throw ArrayIndexOutOfBoundsException and tokenize every file as one
 * BAD_CHARACTER token, silently disabling the parser, PSI, inspections,
 * navigation, and structure view.
 */
@DisplayName("StructurizrLexer Tokenization")
class StructurizrLexerTokenizationTest {

    private fun lex(text: String): List<Pair<IElementType, String>> {
        val lexer = StructurizrLexerAdapter()
        lexer.start(text, 0, text.length, 0)
        val tokens = mutableListOf<Pair<IElementType, String>>()
        while (lexer.tokenType != null) {
            tokens.add(lexer.tokenType!! to text.substring(lexer.tokenStart, lexer.tokenEnd))
            lexer.advance()
        }
        return tokens
    }

    private fun lexTypes(text: String): List<IElementType> =
        lex(text).map { it.first }.filter { it != TokenType.WHITE_SPACE }

    @Nested
    @DisplayName("Basic structure")
    inner class BasicStructure {

        @Test
        fun `lexes minimal workspace without bad characters`() {
            val tokens = lex("workspace \"W\" {\n    model {\n    }\n}\n")
            assertTrue(tokens.isNotEmpty())
            assertFalse(tokens.any { it.first == TokenType.BAD_CHARACTER },
                "No BAD_CHARACTER expected, got: $tokens")
        }

        @Test
        fun `workspace keyword is recognized`() {
            assertEquals(StructurizrTypes.WORKSPACE, lexTypes("workspace").first())
        }

        @Test
        fun `model keyword is recognized`() {
            assertEquals(StructurizrTypes.MODEL, lexTypes("model").first())
        }

        @Test
        fun `views keyword is recognized`() {
            assertEquals(StructurizrTypes.VIEWS, lexTypes("views").first())
        }

        @Test
        fun `braces are recognized`() {
            val types = lexTypes("{}")
            assertEquals(listOf(StructurizrTypes.LBRACE, StructurizrTypes.RBRACE), types)
        }

        @Test
        fun `tokens cover the whole text without gaps`() {
            val text = "workspace \"Name\" \"Description\" {\n    model {\n        a = person \"A\"\n    }\n}\n"
            val tokens = lex(text)
            assertEquals(text.length, tokens.sumOf { it.second.length })
        }

        @Test
        fun `trailing newline lexes as whitespace`() {
            val tokens = lex("workspace {\n}\n")
            assertEquals(TokenType.WHITE_SPACE, tokens.last().first)
        }
    }

    @Nested
    @DisplayName("Element definitions")
    inner class ElementDefinitions {

        @Test
        fun `person assignment produces identifier equals person string`() {
            val types = lexTypes("user = person \"User\"")
            assertEquals(
                listOf(StructurizrTypes.IDENTIFIER, StructurizrTypes.EQUALS,
                    StructurizrTypes.PERSON, StructurizrTypes.STRING),
                types
            )
        }

        @Test
        fun `softwareSystem keyword is recognized`() {
            assertEquals(StructurizrTypes.SOFTWARE_SYSTEM, lexTypes("softwareSystem").first())
        }

        @Test
        fun `keywords are case-insensitive like in Structurizr`() {
            assertEquals(StructurizrTypes.SOFTWARE_SYSTEM, lexTypes("SOFTWARESYSTEM").first())
            assertEquals(StructurizrTypes.WORKSPACE, lexTypes("Workspace").first())
            assertEquals(StructurizrTypes.AUTO_LAYOUT, lexTypes("autolayout").first())
        }

        @Test
        fun `a keyword-like word stays an identifier when assigned or used after an arrow`() {
            assertEquals(StructurizrTypes.IDENTIFIER, lexTypes("Container = softwareSystem").first())
            assertEquals(StructurizrTypes.IDENTIFIER, lexTypes("u -> Container").last())
        }

        @Test
        fun `mixed-case identifiers are not keywords outside statement position`() {
            assertEquals(StructurizrTypes.IDENTIFIER, lexTypes("Users -> web").first())
            assertEquals(StructurizrTypes.IDENTIFIER, lexTypes("include Users").last())
            assertEquals(StructurizrTypes.IDENTIFIER, lexTypes("!element Model").last())
        }

        @Test
        fun `upper-case element keywords map to the element keyword, not the shape`() {
            repeat(3) {
                assertEquals(StructurizrTypes.PERSON, lexTypes("PERSON").first())
                assertEquals(StructurizrTypes.COMPONENT, lexTypes("x = COMPONENT").last())
            }
        }

        @Test
        fun `container and component keywords are recognized`() {
            assertEquals(StructurizrTypes.CONTAINER, lexTypes("container").first())
            assertEquals(StructurizrTypes.COMPONENT, lexTypes("component").first())
        }

        @Test
        fun `strings keep their quotes`() {
            val tokens = lex("\"hello world\"")
            assertEquals(StructurizrTypes.STRING, tokens.first().first)
            assertEquals("\"hello world\"", tokens.first().second)
        }
    }

    @Nested
    @DisplayName("Relationships")
    inner class Relationships {

        @Test
        fun `relationship arrow is recognized`() {
            val types = lexTypes("a -> b \"Uses\"")
            assertEquals(
                listOf(StructurizrTypes.IDENTIFIER, StructurizrTypes.ARROW,
                    StructurizrTypes.IDENTIFIER, StructurizrTypes.STRING),
                types
            )
        }

        @Test
        fun `qualified identifier lexes with dot`() {
            val types = lexTypes("a.b")
            assertEquals(
                listOf(StructurizrTypes.IDENTIFIER, StructurizrTypes.DOT, StructurizrTypes.IDENTIFIER),
                types
            )
        }
    }

    @Nested
    @DisplayName("Comments")
    inner class Comments {

        @Test
        fun `line comment is recognized`() {
            assertEquals(StructurizrTypes.LINE_COMMENT, lexTypes("// a comment").first())
        }

        @Test
        fun `block comment is recognized`() {
            assertEquals(StructurizrTypes.BLOCK_COMMENT, lexTypes("/* block */").first())
        }
    }

    @Nested
    @DisplayName("Views and styles")
    inner class ViewsAndStyles {

        @Test
        fun `systemContext view header lexes correctly`() {
            val types = lexTypes("systemContext banking \"SystemContext\"")
            assertEquals(
                listOf(StructurizrTypes.SYSTEM_CONTEXT, StructurizrTypes.IDENTIFIER, StructurizrTypes.STRING),
                types
            )
        }

        @Test
        fun `include star lexes as include and star`() {
            val types = lexTypes("include *")
            assertEquals(listOf(StructurizrTypes.INCLUDE, StructurizrTypes.STAR), types)
        }

        @Test
        fun `autoLayout keyword is recognized`() {
            assertEquals(StructurizrTypes.AUTO_LAYOUT, lexTypes("autoLayout").first())
        }

        @Test
        fun `full workspace lexes without bad characters`() {
            val dsl = """
                workspace "Bank" "Desc" {
                    model {
                        user = person "Customer"
                        bank = softwareSystem "Bank" {
                            web = container "Web App" "UI" "React"
                        }
                        user -> web "Uses" "HTTPS"
                    }
                    views {
                        systemContext bank "Context" {
                            include *
                            autoLayout lr
                        }
                        styles {
                            element "Person" {
                                shape Person
                                background #08427b
                                color #ffffff
                            }
                        }
                    }
                }
            """.trimIndent() + "\n"
            val bad = lex(dsl).filter { it.first == TokenType.BAD_CHARACTER }
            assertTrue(bad.isEmpty(), "BAD_CHARACTER tokens found: $bad")
        }
    }
}
