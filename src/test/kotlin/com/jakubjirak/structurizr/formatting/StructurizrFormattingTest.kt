package com.jakubjirak.structurizr.formatting

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Structurizr Formatting")
class StructurizrFormattingTest {

    @Nested
    @DisplayName("Formatting classes exist")
    inner class ClassExistence {

        @Test
        fun `StructurizrFormattingModelBuilder class is accessible`() {
            val clazz = StructurizrFormattingModelBuilder::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrFormattingModelBuilder", clazz.simpleName)
        }

        @Test
        fun `StructurizrBlock class is accessible`() {
            val clazz = StructurizrBlock::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrBlock", clazz.simpleName)
        }

        @Test
        fun `StructurizrCodeStyleSettings class is accessible`() {
            val clazz = StructurizrCodeStyleSettings::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrCodeStyleSettings", clazz.simpleName)
        }

        @Test
        fun `StructurizrCodeStyleSettingsProvider class is accessible`() {
            val clazz = StructurizrCodeStyleSettingsProvider::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrCodeStyleSettingsProvider", clazz.simpleName)
        }

        @Test
        fun `StructurizrLanguageCodeStyleSettingsProvider class is accessible`() {
            val clazz = StructurizrLanguageCodeStyleSettingsProvider::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrLanguageCodeStyleSettingsProvider", clazz.simpleName)
        }

        @Test
        fun `StructurizrCodeStyleMainPanel class is accessible`() {
            val clazz = StructurizrCodeStyleMainPanel::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrCodeStyleMainPanel", clazz.simpleName)
        }
    }

    @Nested
    @DisplayName("StructurizrCodeStyleSettings fields")
    inner class CodeStyleSettingsFields {

        @Test
        fun `BLANK_LINES_AROUND_BLOCK field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("BLANK_LINES_AROUND_BLOCK")
            assertNotNull(field)
            assertEquals("int", field.type.name)
        }

        @Test
        fun `BLANK_LINES_AFTER_MODEL_ELEMENTS field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("BLANK_LINES_AFTER_MODEL_ELEMENTS")
            assertNotNull(field)
            assertEquals("int", field.type.name)
        }

        @Test
        fun `BLANK_LINES_BEFORE_VIEWS field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("BLANK_LINES_BEFORE_VIEWS")
            assertNotNull(field)
            assertEquals("int", field.type.name)
        }

        @Test
        fun `SPACE_BEFORE_LBRACE field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("SPACE_BEFORE_LBRACE")
            assertNotNull(field)
            assertEquals("boolean", field.type.name)
        }

        @Test
        fun `SPACE_AFTER_ARROW field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("SPACE_AFTER_ARROW")
            assertNotNull(field)
            assertEquals("boolean", field.type.name)
        }

        @Test
        fun `SPACE_BEFORE_ARROW field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("SPACE_BEFORE_ARROW")
            assertNotNull(field)
            assertEquals("boolean", field.type.name)
        }

        @Test
        fun `WRAP_LONG_LINES field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("WRAP_LONG_LINES")
            assertNotNull(field)
        }

        @Test
        fun `ALIGN_MULTILINE_PARAMETERS field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("ALIGN_MULTILINE_PARAMETERS")
            assertNotNull(field)
        }

        @Test
        fun `KEEP_LINE_BREAKS field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("KEEP_LINE_BREAKS")
            assertNotNull(field)
        }

        @Test
        fun `LBRACE_ON_NEXT_LINE field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("LBRACE_ON_NEXT_LINE")
            assertNotNull(field)
        }

        @Test
        fun `RBRACE_ON_OWN_LINE field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("RBRACE_ON_OWN_LINE")
            assertNotNull(field)
        }

        @Test
        fun `ALIGN_PROPERTY_VALUES field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("ALIGN_PROPERTY_VALUES")
            assertNotNull(field)
        }

        @Test
        fun `PROPERTY_VALUE_ON_SAME_LINE field exists`() {
            val field = StructurizrCodeStyleSettings::class.java.getField("PROPERTY_VALUE_ON_SAME_LINE")
            assertNotNull(field)
        }

        @Test
        fun `all 13 fields are defined`() {
            val fields = listOf(
                "BLANK_LINES_AROUND_BLOCK",
                "BLANK_LINES_AFTER_MODEL_ELEMENTS",
                "BLANK_LINES_BEFORE_VIEWS",
                "SPACE_BEFORE_LBRACE",
                "SPACE_AFTER_ARROW",
                "SPACE_BEFORE_ARROW",
                "WRAP_LONG_LINES",
                "ALIGN_MULTILINE_PARAMETERS",
                "KEEP_LINE_BREAKS",
                "LBRACE_ON_NEXT_LINE",
                "RBRACE_ON_OWN_LINE",
                "ALIGN_PROPERTY_VALUES",
                "PROPERTY_VALUE_ON_SAME_LINE"
            )
            fields.forEach { fieldName ->
                assertDoesNotThrow {
                    StructurizrCodeStyleSettings::class.java.getField(fieldName)
                }
            }
        }
    }
}
