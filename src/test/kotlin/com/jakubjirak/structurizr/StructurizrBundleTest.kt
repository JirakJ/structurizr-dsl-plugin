package com.jakubjirak.structurizr

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.Properties

@DisplayName("StructurizrBundle i18n")
class StructurizrBundleTest {

    private fun loadBundle(): Properties {
        val props = Properties()
        val stream = javaClass.classLoader.getResourceAsStream("messages/StructurizrBundle.properties")
        assertNotNull(stream, "StructurizrBundle.properties not found on classpath")
        props.load(stream)
        return props
    }

    @Nested
    @DisplayName("Bundle file exists and loads")
    inner class BundleLoading {

        @Test
        fun `bundle properties file exists`() {
            val stream = javaClass.classLoader.getResourceAsStream("messages/StructurizrBundle.properties")
            assertNotNull(stream, "StructurizrBundle.properties should exist")
            stream?.close()
        }

        @Test
        fun `bundle properties file is not empty`() {
            val props = loadBundle()
            assertTrue(props.isNotEmpty())
        }
    }

    @Nested
    @DisplayName("Plugin metadata keys")
    inner class PluginMetadata {

        @Test
        fun `plugin name key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("plugin.name"))
            assertEquals("Structurizr DSL", props.getProperty("plugin.name"))
        }

        @Test
        fun `plugin description key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("plugin.description"))
        }
    }

    @Nested
    @DisplayName("File type keys")
    inner class FileTypeKeys {

        @Test
        fun `filetype name key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("filetype.structurizr.name"))
        }

        @Test
        fun `filetype description key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("filetype.structurizr.description"))
        }
    }

    @Nested
    @DisplayName("Action keys")
    inner class ActionKeys {

        @Test
        fun `new file action name key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("action.new.file.name"))
        }

        @Test
        fun `new file action description key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("action.new.file.description"))
        }

        @Test
        fun `refresh preview action key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("action.refresh.preview.name"))
        }

        @Test
        fun `export diagram action key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("action.export.diagram.name"))
        }
    }

    @Nested
    @DisplayName("Settings keys")
    inner class SettingsKeys {

        @Test
        fun `settings title key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("settings.title"))
        }

        @Test
        fun `CLI path setting key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("settings.cli.path"))
        }

        @Test
        fun `preview enabled setting key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("settings.preview.enabled"))
        }

        @Test
        fun `export format setting key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("settings.export.default.format"))
        }
    }

    @Nested
    @DisplayName("Inspection keys")
    inner class InspectionKeys {

        @Test
        fun `undefined reference inspection name key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("inspection.undefined.reference.name"))
        }

        @Test
        fun `undefined reference inspection description key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("inspection.undefined.reference.description"))
        }

        @Test
        fun `unused element inspection name key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("inspection.unused.element.name"))
        }

        @Test
        fun `unused element inspection description key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("inspection.unused.element.description"))
        }
    }

    @Nested
    @DisplayName("Quick fix keys")
    inner class QuickFixKeys {

        @Test
        fun `create element quick fix key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("quickfix.create.element"))
        }

        @Test
        fun `remove unused quick fix key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("quickfix.remove.unused"))
        }
    }

    @Nested
    @DisplayName("Notification keys")
    inner class NotificationKeys {

        @Test
        fun `notification group name key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("notification.group.name"))
        }

        @Test
        fun `CLI not found notification key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("notification.cli.not.found"))
        }

        @Test
        fun `export success notification key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("notification.export.success"))
        }

        @Test
        fun `export failed notification key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("notification.export.failed"))
        }
    }

    @Nested
    @DisplayName("Structure view keys")
    inner class StructureViewKeys {

        @Test
        fun `workspace key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("structure.workspace"))
        }

        @Test
        fun `model key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("structure.model"))
        }

        @Test
        fun `views key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("structure.views"))
        }

        @Test
        fun `styles key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("structure.styles"))
        }

        @Test
        fun `person key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("structure.person"))
        }

        @Test
        fun `container key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("structure.container"))
        }

        @Test
        fun `component key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("structure.component"))
        }
    }

    @Nested
    @DisplayName("Documentation keys")
    inner class DocumentationKeys {

        @Test
        fun `workspace doc key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("doc.workspace"))
        }

        @Test
        fun `model doc key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("doc.model"))
        }

        @Test
        fun `views doc key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("doc.views"))
        }

        @Test
        fun `person doc key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("doc.person"))
        }
    }

    @Nested
    @DisplayName("Error keys")
    inner class ErrorKeys {

        @Test
        fun `parse failed error key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("error.parse.failed"))
        }

        @Test
        fun `render failed error key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("error.render.failed"))
        }

        @Test
        fun `CLI execution failed error key exists`() {
            val props = loadBundle()
            assertTrue(props.containsKey("error.cli.execution.failed"))
        }
    }

    @Nested
    @DisplayName("Total key count")
    inner class TotalKeyCount {

        @Test
        fun `bundle has all expected keys`() {
            val props = loadBundle()
            // Count from the properties file: 66 lines with ~40 actual keys
            assertTrue(props.size >= 30, "Expected at least 30 keys, got ${props.size}")
        }
    }
}
