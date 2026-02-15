package com.jakubjirak.structurizr.export

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("DiagramExportFormat")
class StructurizrExportTest {

    @Nested
    @DisplayName("Enum values")
    inner class EnumValues {

        @Test
        fun `SVG format exists`() {
            assertNotNull(DiagramExportFormat.SVG)
        }

        @Test
        fun `PNG format exists`() {
            assertNotNull(DiagramExportFormat.PNG)
        }

        @Test
        fun `PLANTUML format exists`() {
            assertNotNull(DiagramExportFormat.PLANTUML)
        }

        @Test
        fun `MERMAID format exists`() {
            assertNotNull(DiagramExportFormat.MERMAID)
        }

        @Test
        fun `DOT format exists`() {
            assertNotNull(DiagramExportFormat.DOT)
        }

        @Test
        fun `JSON format exists`() {
            assertNotNull(DiagramExportFormat.JSON)
        }

        @Test
        fun `exactly 6 formats exist`() {
            assertEquals(6, DiagramExportFormat.values().size)
        }
    }

    @Nested
    @DisplayName("Display names")
    inner class DisplayNames {

        @Test
        fun `SVG display name`() {
            assertEquals("SVG", DiagramExportFormat.SVG.displayName)
        }

        @Test
        fun `PNG display name`() {
            assertEquals("PNG", DiagramExportFormat.PNG.displayName)
        }

        @Test
        fun `PLANTUML display name`() {
            assertEquals("PlantUML", DiagramExportFormat.PLANTUML.displayName)
        }

        @Test
        fun `MERMAID display name`() {
            assertEquals("Mermaid", DiagramExportFormat.MERMAID.displayName)
        }

        @Test
        fun `DOT display name`() {
            assertEquals("Graphviz DOT", DiagramExportFormat.DOT.displayName)
        }

        @Test
        fun `JSON display name`() {
            assertEquals("JSON", DiagramExportFormat.JSON.displayName)
        }
    }

    @Nested
    @DisplayName("File extensions")
    inner class FileExtensions {

        @Test
        fun `SVG extension`() {
            assertEquals("svg", DiagramExportFormat.SVG.extension)
        }

        @Test
        fun `PNG extension`() {
            assertEquals("png", DiagramExportFormat.PNG.extension)
        }

        @Test
        fun `PLANTUML extension`() {
            assertEquals("puml", DiagramExportFormat.PLANTUML.extension)
        }

        @Test
        fun `MERMAID extension`() {
            assertEquals("mmd", DiagramExportFormat.MERMAID.extension)
        }

        @Test
        fun `DOT extension`() {
            assertEquals("dot", DiagramExportFormat.DOT.extension)
        }

        @Test
        fun `JSON extension`() {
            assertEquals("json", DiagramExportFormat.JSON.extension)
        }
    }

    @Nested
    @DisplayName("Descriptions")
    inner class Descriptions {

        @Test
        fun `SVG description is not empty`() {
            assertTrue(DiagramExportFormat.SVG.description.isNotEmpty())
        }

        @Test
        fun `PNG description is not empty`() {
            assertTrue(DiagramExportFormat.PNG.description.isNotEmpty())
        }

        @Test
        fun `PLANTUML description is not empty`() {
            assertTrue(DiagramExportFormat.PLANTUML.description.isNotEmpty())
        }

        @Test
        fun `MERMAID description is not empty`() {
            assertTrue(DiagramExportFormat.MERMAID.description.isNotEmpty())
        }

        @Test
        fun `DOT description is not empty`() {
            assertTrue(DiagramExportFormat.DOT.description.isNotEmpty())
        }

        @Test
        fun `JSON description is not empty`() {
            assertTrue(DiagramExportFormat.JSON.description.isNotEmpty())
        }

        @Test
        fun `all descriptions are unique`() {
            val descriptions = DiagramExportFormat.values().map { it.description }.toSet()
            assertEquals(6, descriptions.size)
        }
    }

    @Nested
    @DisplayName("Enum properties")
    inner class EnumProperties {

        @Test
        fun `valueOf works for all formats`() {
            assertEquals(DiagramExportFormat.SVG, DiagramExportFormat.valueOf("SVG"))
            assertEquals(DiagramExportFormat.PNG, DiagramExportFormat.valueOf("PNG"))
            assertEquals(DiagramExportFormat.PLANTUML, DiagramExportFormat.valueOf("PLANTUML"))
            assertEquals(DiagramExportFormat.MERMAID, DiagramExportFormat.valueOf("MERMAID"))
            assertEquals(DiagramExportFormat.DOT, DiagramExportFormat.valueOf("DOT"))
            assertEquals(DiagramExportFormat.JSON, DiagramExportFormat.valueOf("JSON"))
        }

        @Test
        fun `all extensions are unique`() {
            val extensions = DiagramExportFormat.values().map { it.extension }.toSet()
            assertEquals(6, extensions.size)
        }

        @Test
        fun `all display names are unique`() {
            val names = DiagramExportFormat.values().map { it.displayName }.toSet()
            assertEquals(6, names.size)
        }
    }

    @Nested
    @DisplayName("Export service classes exist")
    inner class ExportServiceClasses {

        @Test
        fun `StructurizrExportService class is accessible`() {
            assertNotNull(StructurizrExportService::class.java)
        }

        @Test
        fun `ClipboardExportService class is accessible`() {
            assertNotNull(ClipboardExportService::class.java)
        }

        @Test
        fun `ExportDialog class is accessible`() {
            assertNotNull(ExportDialog::class.java)
        }
    }
}
