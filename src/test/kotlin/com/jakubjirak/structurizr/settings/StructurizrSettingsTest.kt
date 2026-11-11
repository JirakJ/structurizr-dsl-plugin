package com.jakubjirak.structurizr.settings

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrSettings")
class StructurizrSettingsTest {

    @Nested
    @DisplayName("Default values")
    inner class DefaultValues {

        @Test
        fun `default CLI path is empty`() {
            val settings = StructurizrSettings()
            assertEquals("", settings.structurizrCliPath)
        }

        @Test
        fun `default CLI timeout is 30`() {
            val settings = StructurizrSettings()
            assertEquals(30, settings.cliTimeoutSeconds)
        }

        @Test
        fun `default enableLivePreview is true`() {
            val settings = StructurizrSettings()
            assertTrue(settings.enableLivePreview)
        }

        @Test
        fun `default autoRefreshPreview is true`() {
            val settings = StructurizrSettings()
            assertTrue(settings.autoRefreshPreview)
        }

        @Test
        fun `default previewDelayMs is 500`() {
            val settings = StructurizrSettings()
            assertEquals(500, settings.previewDelayMs)
        }

        @Test
        fun `default export format is PNG`() {
            val settings = StructurizrSettings()
            assertEquals("PNG", settings.defaultExportFormat)
        }

        @Test
        fun `default export directory is empty`() {
            val settings = StructurizrSettings()
            assertEquals("", settings.defaultExportDirectory)
        }

        @Test
        fun `default exportImageWidth is 1920`() {
            val settings = StructurizrSettings()
            assertEquals(1920, settings.exportImageWidth)
        }

        @Test
        fun `default exportImageHeight is 1080`() {
            val settings = StructurizrSettings()
            assertEquals(1080, settings.exportImageHeight)
        }

        @Test
        fun `default autoOpenExportedFile is true`() {
            val settings = StructurizrSettings()
            assertTrue(settings.autoOpenExportedFile)
        }

    }

    @Nested
    @DisplayName("Settings mutation")
    inner class SettingsMutation {

        @Test
        fun `CLI path can be changed`() {
            val settings = StructurizrSettings()
            settings.structurizrCliPath = "/usr/local/bin/structurizr"
            assertEquals("/usr/local/bin/structurizr", settings.structurizrCliPath)
        }

        @Test
        fun `preview delay can be changed`() {
            val settings = StructurizrSettings()
            settings.previewDelayMs = 1000
            assertEquals(1000, settings.previewDelayMs)
        }

        @Test
        fun `export format can be changed`() {
            val settings = StructurizrSettings()
            settings.defaultExportFormat = "SVG"
            assertEquals("SVG", settings.defaultExportFormat)
        }

        @Test
        fun `boolean settings can be toggled`() {
            val settings = StructurizrSettings()
            settings.enableLivePreview = false
            assertFalse(settings.enableLivePreview)
            settings.enableLivePreview = true
            assertTrue(settings.enableLivePreview)
        }
    }

    @Nested
    @DisplayName("State serialization")
    inner class StateTests {

        @Test
        fun `getState returns self`() {
            val settings = StructurizrSettings()
            assertSame(settings, settings.state)
        }

        @Test
        fun `getState preserves modified values`() {
            val settings = StructurizrSettings()
            settings.structurizrCliPath = "/custom/path"
            settings.previewDelayMs = 2000
            val state = settings.state
            assertEquals("/custom/path", state.structurizrCliPath)
            assertEquals(2000, state.previewDelayMs)
        }
    }

    @Nested
    @DisplayName("Format constants")
    inner class FormatConstants {

        @Test
        fun `FORMAT_PNG is PNG`() {
            assertEquals("PNG", StructurizrSettings.FORMAT_PNG)
        }

        @Test
        fun `FORMAT_SVG is SVG`() {
            assertEquals("SVG", StructurizrSettings.FORMAT_SVG)
        }

        @Test
        fun `FORMAT_PLANTUML is PlantUML`() {
            assertEquals("PlantUML", StructurizrSettings.FORMAT_PLANTUML)
        }

        @Test
        fun `FORMAT_MERMAID is Mermaid`() {
            assertEquals("Mermaid", StructurizrSettings.FORMAT_MERMAID)
        }

        @Test
        fun `FORMAT_DOT is DOT`() {
            assertEquals("DOT", StructurizrSettings.FORMAT_DOT)
        }

        @Test
        fun `FORMAT_JSON is JSON`() {
            assertEquals("JSON", StructurizrSettings.FORMAT_JSON)
        }

        @Test
        fun `EXPORT_FORMATS contains all 6 formats`() {
            val method = StructurizrSettings.Companion.javaClass.getMethod("getEXPORT_FORMATS")
            @Suppress("UNCHECKED_CAST")
            val formats = method.invoke(StructurizrSettings.Companion) as List<String>
            assertEquals(6, formats.size)
            assertTrue(formats.contains("PNG"))
            assertTrue(formats.contains("SVG"))
            assertTrue(formats.contains("PlantUML"))
            assertTrue(formats.contains("Mermaid"))
            assertTrue(formats.contains("DOT"))
            assertTrue(formats.contains("JSON"))
        }
    }
}
