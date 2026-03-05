package com.jakubjirak.structurizr.highlighting

import com.intellij.openapi.editor.colors.TextAttributesKey
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.lang.reflect.Method

@DisplayName("Structurizr Highlighting")
class StructurizrHighlightingTest {

    @Nested
    @DisplayName("StructurizrSyntaxHighlighter TextAttributesKeys via Companion")
    inner class TextAttributesKeys {

        private val outerClass = StructurizrSyntaxHighlighter::class.java

        private fun getKey(name: String): TextAttributesKey {
            val field = outerClass.getField(name)
            return field.get(null) as TextAttributesKey
        }

        @Test
        fun `KEYWORD attribute key is defined`() {
            assertNotNull(getKey("KEYWORD"))
        }

        @Test
        fun `ELEMENT_KEYWORD attribute key is defined`() {
            assertNotNull(getKey("ELEMENT_KEYWORD"))
        }

        @Test
        fun `DEPLOYMENT_KEYWORD attribute key is defined`() {
            assertNotNull(getKey("DEPLOYMENT_KEYWORD"))
        }

        @Test
        fun `VIEW_KEYWORD attribute key is defined`() {
            assertNotNull(getKey("VIEW_KEYWORD"))
        }

        @Test
        fun `VIEW_CONTENT_KEYWORD attribute key is defined`() {
            assertNotNull(getKey("VIEW_CONTENT_KEYWORD"))
        }

        @Test
        fun `STYLE_KEYWORD attribute key is defined`() {
            assertNotNull(getKey("STYLE_KEYWORD"))
        }

        @Test
        fun `PROPERTY attribute key is defined`() {
            assertNotNull(getKey("PROPERTY"))
        }

        @Test
        fun `DIRECTIVE attribute key is defined`() {
            assertNotNull(getKey("DIRECTIVE"))
        }

        @Test
        fun `CONSTANT attribute key is defined`() {
            assertNotNull(getKey("CONSTANT"))
        }

        @Test
        fun `STRING attribute key is defined`() {
            assertNotNull(getKey("STRING"))
        }

        @Test
        fun `NUMBER attribute key is defined`() {
            assertNotNull(getKey("NUMBER"))
        }

        @Test
        fun `COLOR attribute key is defined`() {
            assertNotNull(getKey("COLOR"))
        }

        @Test
        fun `IDENTIFIER attribute key is defined`() {
            assertNotNull(getKey("IDENTIFIER"))
        }

        @Test
        fun `COMMENT attribute key is defined`() {
            assertNotNull(getKey("COMMENT"))
        }

        @Test
        fun `OPERATOR attribute key is defined`() {
            assertNotNull(getKey("OPERATOR"))
        }

        @Test
        fun `ARROW attribute key is defined`() {
            assertNotNull(getKey("ARROW"))
        }

        @Test
        fun `BRACES attribute key is defined`() {
            assertNotNull(getKey("BRACES"))
        }

        @Test
        fun `STAR attribute key is defined`() {
            assertNotNull(getKey("STAR"))
        }

        @Test
        fun `BAD_CHARACTER attribute key is defined`() {
            assertNotNull(getKey("BAD_CHARACTER"))
        }

        @Test
        fun `all 19 attribute keys are defined`() {
            val keyNames = listOf(
                "KEYWORD", "ELEMENT_KEYWORD", "DEPLOYMENT_KEYWORD",
                "VIEW_KEYWORD", "VIEW_CONTENT_KEYWORD", "STYLE_KEYWORD",
                "PROPERTY", "DIRECTIVE", "CONSTANT",
                "STRING", "NUMBER", "COLOR",
                "IDENTIFIER", "COMMENT", "OPERATOR",
                "ARROW", "BRACES", "STAR", "BAD_CHARACTER"
            )
            assertEquals(19, keyNames.size)
            keyNames.forEach { name ->
                assertNotNull(getKey(name), "$name should not be null")
            }
        }
    }

    @Nested
    @DisplayName("StructurizrSyntaxHighlighter instance")
    inner class HighlighterInstance {

        @Test
        fun `can be instantiated`() {
            val highlighter = StructurizrSyntaxHighlighter()
            assertNotNull(highlighter)
        }

        @Test
        fun `getHighlightingLexer returns non-null`() {
            val highlighter = StructurizrSyntaxHighlighter()
            assertNotNull(highlighter.highlightingLexer)
        }
    }

    @Nested
    @DisplayName("StructurizrColorSettingsPage")
    inner class ColorSettingsPageTests {

        @Test
        fun `can be instantiated`() {
            val page = StructurizrColorSettingsPage()
            assertNotNull(page)
        }

        @Test
        fun `display name is Structurizr DSL`() {
            val page = StructurizrColorSettingsPage()
            assertEquals("Structurizr DSL", page.displayName)
        }

        @Test
        fun `demo text is not empty`() {
            val page = StructurizrColorSettingsPage()
            val demo = page.demoText
            assertNotNull(demo)
            assertTrue(demo.isNotEmpty())
        }

        @Test
        fun `demo text contains workspace keyword`() {
            val page = StructurizrColorSettingsPage()
            assertTrue(page.demoText.contains("workspace"))
        }

        @Test
        fun `demo text contains model keyword`() {
            val page = StructurizrColorSettingsPage()
            assertTrue(page.demoText.contains("model"))
        }

        @Test
        fun `demo text contains views keyword`() {
            val page = StructurizrColorSettingsPage()
            assertTrue(page.demoText.contains("views"))
        }

        @Test
        fun `demo text contains person keyword`() {
            val page = StructurizrColorSettingsPage()
            assertTrue(page.demoText.contains("person"))
        }

        @Test
        fun `demo text contains softwareSystem keyword`() {
            val page = StructurizrColorSettingsPage()
            assertTrue(page.demoText.contains("softwareSystem"))
        }

        @Test
        fun `demo text contains single-line comment`() {
            val page = StructurizrColorSettingsPage()
            assertTrue(page.demoText.contains("//"))
        }

        @Test
        fun `demo text contains block comment`() {
            val page = StructurizrColorSettingsPage()
            assertTrue(page.demoText.contains("/*"))
            assertTrue(page.demoText.contains("*/"))
        }

        @Test
        fun `demo text contains arrow operator`() {
            val page = StructurizrColorSettingsPage()
            assertTrue(page.demoText.contains("->"))
        }

        @Test
        fun `demo text contains color literal`() {
            val page = StructurizrColorSettingsPage()
            assertTrue(page.demoText.contains("#"))
        }

        @Test
        fun `demo text contains styles block`() {
            val page = StructurizrColorSettingsPage()
            assertTrue(page.demoText.contains("styles"))
        }

        @Test
        fun `icon is not null`() {
            val page = StructurizrColorSettingsPage()
            assertNotNull(page.icon)
        }

        @Test
        fun `highlighter is not null`() {
            val page = StructurizrColorSettingsPage()
            assertNotNull(page.highlighter)
        }

        @Test
        fun `attribute descriptors are not empty`() {
            val page = StructurizrColorSettingsPage()
            val descriptors = page.attributeDescriptors
            assertNotNull(descriptors)
            assertTrue(descriptors.isNotEmpty())
        }

        @Test
        fun `color descriptors are not null`() {
            val page = StructurizrColorSettingsPage()
            assertNotNull(page.colorDescriptors)
        }
    }

    @Nested
    @DisplayName("StructurizrAnnotator")
    inner class AnnotatorTests {

        @Test
        fun `StructurizrAnnotator class is accessible`() {
            val clazz = StructurizrAnnotator::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrAnnotator", clazz.simpleName)
        }
    }

    @Nested
    @DisplayName("StructurizrSyntaxHighlighterFactory")
    inner class HighlighterFactoryTests {

        @Test
        fun `factory class is accessible`() {
            val clazz = StructurizrSyntaxHighlighterFactory::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrSyntaxHighlighterFactory", clazz.simpleName)
        }
    }
}
