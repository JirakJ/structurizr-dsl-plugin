package com.jakubjirak.structurizr.language.lexer

import com.jakubjirak.structurizr.language.psi.StructurizrTokenType
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import com.intellij.psi.tree.IElementType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrLexer Token Definitions")
class StructurizrLexerTest {

    @Nested
    @DisplayName("Keyword token types exist")
    inner class KeywordTokens {

        @Test
        fun `workspace token type is defined`() {
            assertNotNull(StructurizrTypes.WORKSPACE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.WORKSPACE)
        }

        @Test
        fun `model token type is defined`() {
            assertNotNull(StructurizrTypes.MODEL)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.MODEL)
        }

        @Test
        fun `views token type is defined`() {
            assertNotNull(StructurizrTypes.VIEWS)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.VIEWS)
        }

        @Test
        fun `person token type is defined`() {
            assertNotNull(StructurizrTypes.PERSON)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.PERSON)
        }

        @Test
        fun `softwareSystem token type is defined`() {
            assertNotNull(StructurizrTypes.SOFTWARE_SYSTEM)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.SOFTWARE_SYSTEM)
        }

        @Test
        fun `container token type is defined`() {
            assertNotNull(StructurizrTypes.CONTAINER)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.CONTAINER)
        }

        @Test
        fun `component token type is defined`() {
            assertNotNull(StructurizrTypes.COMPONENT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.COMPONENT)
        }

        @Test
        fun `configuration token type is defined`() {
            assertNotNull(StructurizrTypes.CONFIGURATION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.CONFIGURATION)
        }
    }

    @Nested
    @DisplayName("Comment token types exist")
    inner class CommentTokens {

        @Test
        fun `line comment token is defined`() {
            assertNotNull(StructurizrTypes.LINE_COMMENT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.LINE_COMMENT)
        }

        @Test
        fun `block comment token is defined`() {
            assertNotNull(StructurizrTypes.BLOCK_COMMENT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.BLOCK_COMMENT)
        }
    }

    @Nested
    @DisplayName("String and literal token types exist")
    inner class LiteralTokens {

        @Test
        fun `string token is defined`() {
            assertNotNull(StructurizrTypes.STRING)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.STRING)
        }

        @Test
        fun `number token is defined`() {
            assertNotNull(StructurizrTypes.NUMBER)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.NUMBER)
        }

        @Test
        fun `color literal token is defined`() {
            assertNotNull(StructurizrTypes.COLOR_LITERAL)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.COLOR_LITERAL)
        }

        @Test
        fun `identifier token is defined`() {
            assertNotNull(StructurizrTypes.IDENTIFIER)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.IDENTIFIER)
        }
    }

    @Nested
    @DisplayName("Operator token types exist")
    inner class OperatorTokens {

        @Test
        fun `arrow token is defined`() {
            assertNotNull(StructurizrTypes.ARROW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.ARROW)
        }

        @Test
        fun `left arrow token is defined`() {
            assertNotNull(StructurizrTypes.ARROW_LEFT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.ARROW_LEFT)
        }

        @Test
        fun `both arrow token is defined`() {
            assertNotNull(StructurizrTypes.ARROW_BOTH)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.ARROW_BOTH)
        }

        @Test
        fun `equals token is defined`() {
            assertNotNull(StructurizrTypes.EQUALS)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.EQUALS)
        }

        @Test
        fun `star token is defined`() {
            assertNotNull(StructurizrTypes.STAR)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.STAR)
        }

        @Test
        fun `bang token is defined`() {
            assertNotNull(StructurizrTypes.BANG)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.BANG)
        }
    }

    @Nested
    @DisplayName("Directive token types exist")
    inner class DirectiveTokens {

        @Test
        fun `include directive is defined`() {
            assertNotNull(StructurizrTypes.DIRECTIVE_INCLUDE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DIRECTIVE_INCLUDE)
        }

        @Test
        fun `const directive is defined`() {
            assertNotNull(StructurizrTypes.DIRECTIVE_CONST)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DIRECTIVE_CONST)
        }

        @Test
        fun `constant directive is defined`() {
            assertNotNull(StructurizrTypes.DIRECTIVE_CONSTANT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DIRECTIVE_CONSTANT)
        }

        @Test
        fun `identifiers directive is defined`() {
            assertNotNull(StructurizrTypes.DIRECTIVE_IDENTIFIERS)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DIRECTIVE_IDENTIFIERS)
        }

        @Test
        fun `docs directive is defined`() {
            assertNotNull(StructurizrTypes.DIRECTIVE_DOCS)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DIRECTIVE_DOCS)
        }

        @Test
        fun `adrs directive is defined`() {
            assertNotNull(StructurizrTypes.DIRECTIVE_ADRS)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DIRECTIVE_ADRS)
        }

        @Test
        fun `implied relationships directive is defined`() {
            assertNotNull(StructurizrTypes.DIRECTIVE_IMPLIED_RELATIONSHIPS)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DIRECTIVE_IMPLIED_RELATIONSHIPS)
        }

        @Test
        fun `var directive is defined`() {
            assertNotNull(StructurizrTypes.DIRECTIVE_VAR)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DIRECTIVE_VAR)
        }

        @Test
        fun `plugin directive is defined`() {
            assertNotNull(StructurizrTypes.DIRECTIVE_PLUGIN)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DIRECTIVE_PLUGIN)
        }

        @Test
        fun `script directive is defined`() {
            assertNotNull(StructurizrTypes.DIRECTIVE_SCRIPT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DIRECTIVE_SCRIPT)
        }
    }

    @Nested
    @DisplayName("Brace token types exist")
    inner class BraceTokens {

        @Test
        fun `left brace token is defined`() {
            assertNotNull(StructurizrTypes.LBRACE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.LBRACE)
        }

        @Test
        fun `right brace token is defined`() {
            assertNotNull(StructurizrTypes.RBRACE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.RBRACE)
        }
    }

    @Nested
    @DisplayName("StructurizrTokenType properties")
    inner class TokenTypeProperties {

        @Test
        fun `token type toString contains prefix`() {
            val tokenType = StructurizrTokenType("testToken")
            assertTrue(tokenType.toString().contains("StructurizrTokenType"))
            assertTrue(tokenType.toString().contains("testToken"))
        }

        @Test
        fun `token type toString format is correct`() {
            val tokenType = StructurizrTokenType("myToken")
            assertEquals("StructurizrTokenType.myToken", tokenType.toString())
        }
    }

    @Nested
    @DisplayName("Shape token types exist")
    inner class ShapeTokens {

        @Test
        fun `all shape tokens are defined`() {
            assertNotNull(StructurizrTypes.SHAPE_BOX)
            assertNotNull(StructurizrTypes.SHAPE_ROUNDED_BOX)
            assertNotNull(StructurizrTypes.SHAPE_CIRCLE)
            assertNotNull(StructurizrTypes.SHAPE_ELLIPSE)
            assertNotNull(StructurizrTypes.SHAPE_HEXAGON)
            assertNotNull(StructurizrTypes.SHAPE_CYLINDER)
            assertNotNull(StructurizrTypes.SHAPE_PIPE)
            assertNotNull(StructurizrTypes.SHAPE_PERSON)
            assertNotNull(StructurizrTypes.SHAPE_ROBOT)
            assertNotNull(StructurizrTypes.SHAPE_FOLDER)
            assertNotNull(StructurizrTypes.SHAPE_WEB_BROWSER)
            assertNotNull(StructurizrTypes.SHAPE_MOBILE_PORTRAIT)
            assertNotNull(StructurizrTypes.SHAPE_MOBILE_LANDSCAPE)
            assertNotNull(StructurizrTypes.SHAPE_COMPONENT)
        }
    }

    @Nested
    @DisplayName("Layout direction tokens exist")
    inner class LayoutTokens {

        @Test
        fun `layout direction tokens are defined`() {
            assertNotNull(StructurizrTypes.LAYOUT_TB)
            assertNotNull(StructurizrTypes.LAYOUT_BT)
            assertNotNull(StructurizrTypes.LAYOUT_LR)
            assertNotNull(StructurizrTypes.LAYOUT_RL)
        }
    }

    @Nested
    @DisplayName("Deployment keyword tokens exist")
    inner class DeploymentTokens {

        @Test
        fun `deployment tokens are defined`() {
            assertNotNull(StructurizrTypes.DEPLOYMENT_ENVIRONMENT)
            assertNotNull(StructurizrTypes.DEPLOYMENT_GROUP)
            assertNotNull(StructurizrTypes.DEPLOYMENT_NODE)
            assertNotNull(StructurizrTypes.INFRASTRUCTURE_NODE)
            assertNotNull(StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE)
            assertNotNull(StructurizrTypes.CONTAINER_INSTANCE)
        }
    }

    @Nested
    @DisplayName("View keyword tokens exist")
    inner class ViewTokens {

        @Test
        fun `view tokens are defined`() {
            assertNotNull(StructurizrTypes.SYSTEM_LANDSCAPE)
            assertNotNull(StructurizrTypes.SYSTEM_CONTEXT)
            assertNotNull(StructurizrTypes.DYNAMIC)
            assertNotNull(StructurizrTypes.DEPLOYMENT)
            assertNotNull(StructurizrTypes.FILTERED)
            assertNotNull(StructurizrTypes.CUSTOM)
            assertNotNull(StructurizrTypes.IMAGE)
        }
    }

    @Nested
    @DisplayName("Style property tokens exist")
    inner class StylePropertyTokens {

        @Test
        fun `style property tokens are defined`() {
            assertNotNull(StructurizrTypes.SHAPE)
            assertNotNull(StructurizrTypes.ICON)
            assertNotNull(StructurizrTypes.WIDTH)
            assertNotNull(StructurizrTypes.HEIGHT)
            assertNotNull(StructurizrTypes.BACKGROUND)
            assertNotNull(StructurizrTypes.COLOR)
            assertNotNull(StructurizrTypes.COLOUR)
            assertNotNull(StructurizrTypes.STROKE)
            assertNotNull(StructurizrTypes.STROKE_WIDTH)
            assertNotNull(StructurizrTypes.FONT_SIZE)
            assertNotNull(StructurizrTypes.BORDER)
            assertNotNull(StructurizrTypes.OPACITY)
            assertNotNull(StructurizrTypes.METADATA)
            assertNotNull(StructurizrTypes.THICKNESS)
            assertNotNull(StructurizrTypes.STYLE_PROP)
            assertNotNull(StructurizrTypes.ROUTING)
            assertNotNull(StructurizrTypes.POSITION)
            assertNotNull(StructurizrTypes.DASHED)
        }
    }

    @Nested
    @DisplayName("Miscellaneous tokens exist")
    inner class MiscTokens {

        @Test
        fun `boolean tokens are defined`() {
            assertNotNull(StructurizrTypes.TRUE)
            assertNotNull(StructurizrTypes.FALSE)
        }

        @Test
        fun `access control tokens are defined`() {
            assertNotNull(StructurizrTypes.READ)
            assertNotNull(StructurizrTypes.WRITE)
            assertNotNull(StructurizrTypes.PRIVATE)
            assertNotNull(StructurizrTypes.PUBLIC)
        }

        @Test
        fun `other keyword tokens are defined`() {
            assertNotNull(StructurizrTypes.TITLE)
            assertNotNull(StructurizrTypes.DESCRIPTION)
            assertNotNull(StructurizrTypes.TAGS)
            assertNotNull(StructurizrTypes.URL)
            assertNotNull(StructurizrTypes.PROPERTIES)
            assertNotNull(StructurizrTypes.PERSPECTIVES)
            assertNotNull(StructurizrTypes.USERS)
            assertNotNull(StructurizrTypes.EXTENDS)
            assertNotNull(StructurizrTypes.REF)
            assertNotNull(StructurizrTypes.THIS)
        }

        @Test
        fun `punctuation tokens are defined`() {
            assertNotNull(StructurizrTypes.COMMA)
            assertNotNull(StructurizrTypes.DOT)
            assertNotNull(StructurizrTypes.VAR_START)
        }

        @Test
        fun `identifier mode tokens are defined`() {
            assertNotNull(StructurizrTypes.HIERARCHICAL)
            assertNotNull(StructurizrTypes.FLAT)
        }
    }

    @Nested
    @DisplayName("LexerAdapter can be referenced")
    inner class LexerAdapterTests {

        @Test
        fun `StructurizrLexerAdapter class is accessible`() {
            val clazz = StructurizrLexerAdapter::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrLexerAdapter", clazz.simpleName)
        }

        @Test
        fun `StructurizrLexer class is accessible`() {
            val clazz = StructurizrLexer::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrLexer", clazz.simpleName)
        }
    }
}
