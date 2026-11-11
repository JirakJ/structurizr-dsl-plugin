package com.jakubjirak.structurizr.export

import com.jakubjirak.structurizr.preview.ExportFormat
import com.jakubjirak.structurizr.preview.StructurizrCliService
import com.jakubjirak.structurizr.preview.dsl.StructurizrPreviewEngine
import com.jakubjirak.structurizr.preview.dsl.ViewDiagram
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DiagramTextExporterTest {

    private val dsl = """
        workspace "Shop" {
            model {
                customer = person "Customer" "Buys \"things\""
                shop = softwareSystem "Shop" {
                    web = container "Web App" "Storefront" "React"
                    api = container "API" "" "Kotlin"
                }
                mail = softwareSystem "Mail"
                customer -> web "Browses"
                web -> api "Calls" "HTTPS"
                api -> mail "Sends mail"
            }
            views {
                systemContext shop "context" {
                    include *
                }
                container shop "containers" {
                    include *
                }
            }
        }
    """.trimIndent()

    private fun diagram(key: String): ViewDiagram =
        StructurizrPreviewEngine.diagram(StructurizrPreviewEngine.parse(dsl), key).second

    @Test
    fun `plantuml for a context view uses C4 macros and lifted relationships`() {
        val text = DiagramTextExporter.toPlantUml(diagram("context"))
        assertTrue(text.startsWith("@startuml"))
        assertTrue(text.trimEnd().endsWith("@enduml"))
        assertTrue(text.contains("""Person(customer, "Customer", "Buys 'things'")"""), text)
        assertTrue(text.contains("""System(shop, "Shop""""), text)
        assertTrue(text.contains("""System(mail, "Mail""""), text)
        assertTrue(text.contains("Rel(customer, shop, \"Browses\")"), text)
        assertFalse(text.contains("Container("), "context view must not list containers")
    }

    @Test
    fun `plantuml for a container view puts containers in a boundary`() {
        val text = DiagramTextExporter.toPlantUml(diagram("containers"))
        assertTrue(text.contains("""Boundary(b0, "Shop [Software System]")"""), text)
        assertTrue(text.contains("""  Container(web, "Web App", "React", "Storefront")"""), text)
        assertTrue(text.contains("""Rel(web, api, "Calls", "HTTPS")"""), text)
    }

    @Test
    fun `mermaid output has nodes, subgraph, labelled edges and person class`() {
        val text = DiagramTextExporter.toMermaid(diagram("containers"))
        assertTrue(text.contains("flowchart TB"))
        assertTrue(text.contains("""subgraph b0["Shop [Software System]"]"""), text)
        assertTrue(text.contains("""customer(["Customer<br/>[Person]<br/>Buys #quot;things#quot;"])"""), text)
        assertTrue(text.contains("""web -->|"Calls [HTTPS]"| api"""), text)
        assertTrue(text.contains("class customer person"), text)
    }

    @Test
    fun `empty workspace still produces valid documents`() {
        val empty = StructurizrPreviewEngine.diagram(StructurizrPreviewEngine.parse("workspace { }"), null).second
        assertEquals("@startuml", DiagramTextExporter.toPlantUml(empty).lines().first())
        assertTrue(DiagramTextExporter.toMermaid(empty).contains("flowchart TB"))
    }

    @Test
    fun `text escaping`() {
        assertEquals("a 'b' c", DiagramTextExporter.plain("a \"b\"\nc"))
        assertEquals("#lt;x#gt; #quot;y#quot;", DiagramTextExporter.mermaidText("<x> \"y\""))
    }
}

class ExportHelpersTest {

    @Test
    fun `view keys become safe file names`() {
        assertEquals("SystemContext-001", StructurizrExportService.fileNameFor("SystemContext-001"))
        assertEquals("a_b_c", StructurizrExportService.fileNameFor("a/b\\c"))
        assertEquals("view", StructurizrExportService.fileNameFor("///"))
    }

    @Test
    fun `fit scale keeps the diagram inside the configured box`() {
        assertEquals(2.0, StructurizrExportService.fitScale(960, 540, 1920, 1080))
        assertEquals(0.5, StructurizrExportService.fitScale(3840, 1000, 1920, 1080))
        assertEquals(4.0, StructurizrExportService.fitScale(10, 10, 1920, 1080))
        assertEquals(0.25, StructurizrExportService.fitScale(100_000, 100_000, 1920, 1080))
        assertEquals(1.0, StructurizrExportService.fitScale(0, 100, 1920, 1080))
    }

    @Test
    fun `cli export command uses java -jar for jar files`() {
        assertEquals(
            listOf("java", "-jar", "/x/cli.jar", "export", "-workspace", "w.dsl", "-format", "mermaid", "-output", "/out"),
            StructurizrCliService.buildExportCommand("/x/cli.jar", "w.dsl", "/out", ExportFormat.MERMAID),
        )
        assertEquals(
            listOf("/usr/bin/structurizr", "export", "-workspace", "w.dsl", "-format", "plantuml/c4plantuml", "-output", "/out"),
            StructurizrCliService.buildExportCommand("/usr/bin/structurizr", "w.dsl", "/out", ExportFormat.PLANTUML_C4),
        )
    }
}
