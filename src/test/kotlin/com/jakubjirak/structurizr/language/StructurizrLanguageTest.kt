package com.jakubjirak.structurizr.language

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrLanguage Registration")
class StructurizrLanguageTest {

    @Nested
    @DisplayName("StructurizrLanguage singleton")
    inner class LanguageTests {

        @Test
        fun `singleton exists`() {
            assertNotNull(StructurizrLanguage)
        }

        @Test
        fun `language ID is Structurizr`() {
            assertEquals("Structurizr", StructurizrLanguage.id)
        }

        @Test
        fun `display name is Structurizr DSL`() {
            assertEquals("Structurizr DSL", StructurizrLanguage.displayName)
        }

        @Test
        fun `language is case sensitive`() {
            assertTrue(StructurizrLanguage.isCaseSensitive)
        }

        @Test
        fun `singleton is always the same reference`() {
            assertSame(StructurizrLanguage, StructurizrLanguage)
        }
    }

    @Nested
    @DisplayName("StructurizrFileType singleton")
    inner class FileTypeTests {

        @Test
        fun `singleton exists`() {
            assertNotNull(StructurizrFileType)
        }

        @Test
        fun `file type name is Structurizr DSL`() {
            assertEquals("Structurizr DSL", StructurizrFileType.name)
        }

        @Test
        fun `file type description matches`() {
            assertEquals(
                "Structurizr DSL file for C4 model architecture diagrams",
                StructurizrFileType.description
            )
        }

        @Test
        fun `default extension is dsl`() {
            assertEquals("dsl", StructurizrFileType.defaultExtension)
        }

        @Test
        fun `icon is not null`() {
            assertNotNull(StructurizrFileType.icon)
        }

        @Test
        fun `singleton is always the same reference`() {
            assertSame(StructurizrFileType, StructurizrFileType)
        }
    }

    @Nested
    @DisplayName("StructurizrIcons")
    inner class IconTests {

        @Test
        fun `FILE icon is defined`() {
            assertNotNull(StructurizrIcons.FILE)
        }

        @Test
        fun `WORKSPACE icon is defined`() {
            assertNotNull(StructurizrIcons.WORKSPACE)
        }

        @Test
        fun `MODEL icon is defined`() {
            assertNotNull(StructurizrIcons.MODEL)
        }

        @Test
        fun `VIEWS icon is defined`() {
            assertNotNull(StructurizrIcons.VIEWS)
        }

        @Test
        fun `PERSON icon is defined`() {
            assertNotNull(StructurizrIcons.PERSON)
        }

        @Test
        fun `SOFTWARE_SYSTEM icon is defined`() {
            assertNotNull(StructurizrIcons.SOFTWARE_SYSTEM)
        }

        @Test
        fun `CONTAINER icon is defined`() {
            assertNotNull(StructurizrIcons.CONTAINER)
        }

        @Test
        fun `COMPONENT icon is defined`() {
            assertNotNull(StructurizrIcons.COMPONENT)
        }

        @Test
        fun `RELATIONSHIP icon is defined`() {
            assertNotNull(StructurizrIcons.RELATIONSHIP)
        }

        @Test
        fun `DEPLOYMENT icon is defined`() {
            assertNotNull(StructurizrIcons.DEPLOYMENT)
        }

        @Test
        fun `STYLES icon is defined`() {
            assertNotNull(StructurizrIcons.STYLES)
        }

        @Test
        fun `all 11 icons are unique`() {
            val icons = listOf(
                StructurizrIcons.FILE,
                StructurizrIcons.WORKSPACE,
                StructurizrIcons.MODEL,
                StructurizrIcons.VIEWS,
                StructurizrIcons.PERSON,
                StructurizrIcons.SOFTWARE_SYSTEM,
                StructurizrIcons.CONTAINER,
                StructurizrIcons.COMPONENT,
                StructurizrIcons.RELATIONSHIP,
                StructurizrIcons.DEPLOYMENT,
                StructurizrIcons.STYLES
            )
            assertEquals(11, icons.size)
        }
    }
}
