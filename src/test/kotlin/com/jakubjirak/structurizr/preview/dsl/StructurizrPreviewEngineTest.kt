package com.jakubjirak.structurizr.preview.dsl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/** docs#13: the preview has to show the view that is selected, from the views the workspace defines. */
@DisplayName("Structurizr preview engine")
class StructurizrPreviewEngineTest {

    private val svf = """
        workspace "Semantic Verification Framework" "Target architecture" {
            configuration {
                scope softwaresystem
            }
            model {
                dev = person "Consumer developer" "Writes cases"
                consumer = softwareSystem "Consumer System"
                storage = softwareSystem "Campaign Storage"
                svf = softwareSystem "Semantic Verification Framework" {
                    lib = container "SVF Library" "Core" "Python" {
                        api = component "Public API" "Entry point" "Python"
                        exec = component "Case Execution"
                    }
                    codex = container "Codex Adapter" "" "Python"
                    claude = container "Claude Code Adapter"
                }
                dev -> consumer "Defines cases"
                consumer -> api "Uses" "Python import"
                exec -> codex "Delegates"
                codex -> storage "Writes results"
            }
            views {
                systemLandscape "landscape" {
                    include *
                    autolayout lr
                }
                systemContext svf "context" "The framework in context" {
                    include *
                }
                container svf "containers" {
                    include *
                }
                component lib "components" {
                    include *
                }
                container svf "explicit" {
                    include codex claude
                }
            }
        }
    """.trimIndent()

    private val ws = StructurizrPreviewEngine.parse(svf)

    private fun diagram(key: String) = ViewDiagramBuilder(ws).build(ws.findView(key)!!)

    private fun names(d: ViewDiagram) = d.elements.map { it.name }.toSet()

    @Nested
    inner class ViewSelector {
        @Test
        fun `lists exactly the views defined in the workspace, in order`() {
            val options = StructurizrPreviewEngine.viewOptions(ws)
            assertEquals(listOf("landscape", "context", "containers", "components", "explicit"), options.map { it.key })
            assertEquals("context — System Context: Semantic Verification Framework", options[1].label)
        }

        @Test
        fun `generates default views only when the workspace defines none`() {
            val generated = StructurizrPreviewEngine.parse(
                """
                workspace {
                    model {
                        u = person "U"
                        s = softwareSystem "S" {
                            c = container "C" {
                                k = component "K"
                            }
                        }
                        u -> c "Uses"
                    }
                }
                """.trimIndent(),
            )
            assertEquals(
                listOf("SystemLandscape", "SystemContext-s", "Container-s", "Component-c"),
                generated.views.map { it.key },
            )
            assertTrue(generated.views.all { it.generated })
            assertTrue(StructurizrPreviewEngine.viewOptions(generated).all { it.label.endsWith("(auto)") })
        }

        @Test
        fun `views without a key get Structurizr style generated keys`() {
            val w = StructurizrPreviewEngine.parse(
                "workspace {\n model {\n s = softwareSystem \"S\"\n }\n views {\n systemContext s {\n include *\n }\n systemContext s {\n include *\n }\n }\n}",
            )
            assertEquals(listOf("SystemContext-001", "SystemContext-002"), w.views.map { it.key })
        }
    }

    @Nested
    inner class Selection {
        @Test
        fun `system landscape shows people and software systems only`() {
            assertEquals(
                setOf("Consumer developer", "Consumer System", "Campaign Storage", "Semantic Verification Framework"),
                names(diagram("landscape")),
            )
        }

        @Test
        fun `system context shows the system and what it is connected to, with relationships lifted`() {
            val d = diagram("context")
            assertEquals(setOf("Semantic Verification Framework", "Consumer System", "Campaign Storage"), names(d))
            assertEquals(setOf("Semantic Verification Framework"), d.focus.map { it.name }.toSet())
            val edges = d.edges.map { "${it.source.name} -> ${it.destination.name}" }.toSet()
            assertEquals(setOf("Consumer System -> Semantic Verification Framework", "Semantic Verification Framework -> Campaign Storage"), edges)
            assertEquals("The framework in context", d.description)
        }

        @Test
        fun `container view shows the containers of the system inside its boundary`() {
            val d = diagram("containers")
            assertEquals(
                setOf("SVF Library", "Codex Adapter", "Claude Code Adapter", "Consumer System", "Campaign Storage"),
                names(d),
            )
            assertEquals(1, d.boundaries.size)
            assertEquals(setOf("SVF Library", "Codex Adapter", "Claude Code Adapter"), d.boundaries[0].members.map { it.name }.toSet())
            assertTrue(d.edges.any { it.source.name == "SVF Library" && it.destination.name == "Codex Adapter" && it.description == "Delegates" })
        }

        @Test
        fun `component view shows components and their neighbours as containers or systems`() {
            val d = diagram("components")
            assertEquals(setOf("Public API", "Case Execution", "Codex Adapter", "Consumer System"), names(d))
            assertEquals("SVF Library [Container]", d.boundaries.single().label)
        }

        @Test
        fun `explicit includes show only the named elements`() {
            assertEquals(setOf("Codex Adapter", "Claude Code Adapter"), names(diagram("explicit")))
        }

        @Test
        fun `every view renders a different diagram`() {
            val svgs = ws.views.map { StructurizrPreviewEngine.render(svf, it.key).svg }
            assertEquals(svgs.size, svgs.toSet().size)
        }

        @Test
        fun `render honours the requested key and falls back to the first view`() {
            assertEquals("components", StructurizrPreviewEngine.render(svf, "components").selectedKey)
            assertEquals("landscape", StructurizrPreviewEngine.render(svf, "no-such-view").selectedKey)
            assertEquals("landscape", StructurizrPreviewEngine.render(svf, null).selectedKey)
        }

        @Test
        fun `exclude removes elements`() {
            val w = StructurizrPreviewEngine.parse(
                "workspace {\n model {\n u = person \"U\"\n s = softwareSystem \"S\"\n u -> s\n }\n views {\n systemLandscape \"l\" {\n include *\n exclude u\n }\n }\n}",
            )
            assertEquals(setOf("S"), ViewDiagramBuilder(w).build(w.views.single()).elements.map { it.name }.toSet())
        }

        @Test
        fun `a view without include explains that it is empty`() {
            val w = StructurizrPreviewEngine.parse("workspace {\n model {\n s = softwareSystem \"S\"\n }\n views {\n systemContext s \"c\" {\n }\n }\n}")
            val d = ViewDiagramBuilder(w).build(w.views.single())
            assertTrue(d.elements.isEmpty())
            assertTrue(d.message!!.contains("include *"))
        }

        @Test
        fun `a view scoped to an undefined element says so`() {
            val w = StructurizrPreviewEngine.parse("workspace {\n model {\n }\n views {\n container nope \"c\" {\n include *\n }\n }\n}")
            assertTrue(ViewDiagramBuilder(w).build(w.views.single()).message!!.contains("nope"))
        }
    }

    @Nested
    inner class OtherViewTypes {
        private val dsl = """
            workspace {
                !identifiers hierarchical
                model {
                    u = person "User"
                    s = softwareSystem "Shop" {
                        web = container "Web" "" "React"
                        db = container "DB" "" "Postgres" "Database"
                        web -> db "Reads"
                    }
                    u -> s.web "Browses"
                    live = deploymentEnvironment "Live" {
                        deploymentNode "AWS" {
                            deploymentNode "EC2" "" "Ubuntu" {
                                containerInstance s.web
                            }
                            deploymentNode "RDS" {
                                containerInstance s.db
                            }
                        }
                    }
                }
                views {
                    deployment s live "deploy" {
                        include *
                    }
                    dynamic s "flow" "Checkout" {
                        u -> s.web "Opens shop"
                        s.web -> s.db
                    }
                    container s "all" {
                        include *
                    }
                    filtered "all" exclude "Database" "no-db"
                    image * "img" {
                        image x.png
                    }
                }
            }
        """.trimIndent()
        private val w = StructurizrPreviewEngine.parse(dsl)
        private fun build(key: String) = ViewDiagramBuilder(w).build(w.findView(key)!!)

        @Test
        fun `hierarchical identifiers resolve`() {
            assertNotNull(w.resolve("s.web"))
            assertEquals(2, w.relationships.size)
        }

        @Test
        fun `deployment view shows instances grouped by node, with the environment given by identifier`() {
            val d = build("deploy")
            assertEquals(listOf("Web", "DB"), d.elements.map { it.name })
            assertEquals(setOf("AWS › EC2 [Ubuntu]", "AWS › RDS"), d.boundaries.map { it.label }.toSet())
            assertEquals(1, d.edges.size)
            assertNull(d.message)
        }

        @Test
        fun `dynamic view numbers its steps and falls back to the modelled description`() {
            val d = build("flow")
            assertEquals(listOf(1, 2), d.edges.map { it.order })
            assertEquals(listOf("Opens shop", "Reads"), d.edges.map { it.description })
            assertEquals("Checkout", d.description)
        }

        @Test
        fun `filtered view removes elements by tag`() {
            assertEquals(setOf("User", "Web"), build("no-db").elements.map { it.name }.toSet())
        }

        @Test
        fun `image view is reported, not drawn`() {
            assertTrue(build("img").message!!.contains("Image views"))
        }
    }

    @Nested
    inner class Robustness {
        @Test
        fun `keywords are case-insensitive`() {
            val w = StructurizrPreviewEngine.parse("workspace {\n MODEL {\n s = SoftwareSystem \"S\"\n }\n VIEWS {\n SYSTEMCONTEXT s \"c\" {\n INCLUDE *\n }\n }\n}")
            assertEquals(setOf("S"), ViewDiagramBuilder(w).build(w.views.single()).elements.map { it.name }.toSet())
        }

        @Test
        fun `comments, text blocks and line continuations are handled`() {
            val w = StructurizrPreviewEngine.parse(
                "/* header */\nworkspace \"W\" {\n # hash\n model {\n // line\n s = softwareSystem \"S\" \"\"\"\n   multi\n   line\n \"\"\"\n u = person \\\n   \"User\"\n u -> s \"Uses\" // trailing\n }\n}",
            )
            assertEquals("multi\nline", w.resolve("s")!!.description)
            assertEquals("User", w.resolve("u")!!.name)
            assertEquals(1, w.relationships.size)
        }

        @Test
        fun `include pulls in other files relative to the workspace`() {
            val files = mapOf(
                "model/people.dsl" to "u = person \"From include\"",
                "views.dsl" to "systemLandscape \"inc\" {\n include *\n}",
            )
            val dsl = "workspace {\n model {\n !include model/people.dsl\n }\n views {\n !include views.dsl\n }\n}"
            val w = StructurizrPreviewEngine.parse(dsl) { files[it] }
            assertEquals("From include", w.resolve("u")!!.name)
            assertEquals(listOf("inc"), w.views.map { it.key })
        }

        @Test
        fun `nested includes resolve relative to the including file`() {
            val files = mapOf(
                "model/people.dsl" to "u = person \"User\"\n!include team.dsl\n!include ../shared/systems.dsl",
                "model/team.dsl" to "t = person \"Team\"",
                "shared/systems.dsl" to "s = softwareSystem \"Shared\"",
            )
            val w = StructurizrPreviewEngine.parse("workspace {\n model {\n !include model/people.dsl\n }\n}") { files[it] }
            assertEquals(listOf("User", "Team", "Shared"), w.elements.map { it.name })
        }

        @Test
        fun `element extension blocks are read once`() {
            val w = StructurizrPreviewEngine.parse(
                "workspace {\n model {\n s = softwareSystem \"S\"\n db = softwareSystem \"DB\"\n !element s {\n api = container \"API\"\n api -> db \"Reads\"\n }\n }\n}",
            )
            assertEquals(1, w.elements.count { it.name == "API" })
            assertEquals(1, w.relationships.size)
        }

        @Test
        fun `relationship expressions in include and exclude do not remove elements`() {
            val w = StructurizrPreviewEngine.parse(
                "workspace {\n model {\n u = person \"U\"\n s = softwareSystem \"S\"\n u -> s\n }\n views {\n systemLandscape \"l\" {\n include *\n exclude u -> s\n }\n }\n}",
            )
            assertEquals(setOf("U", "S"), ViewDiagramBuilder(w).build(w.views.single()).elements.map { it.name }.toSet())
        }

        @Test
        fun `self-including files do not loop forever`() {
            val w = StructurizrPreviewEngine.parse("workspace {\n model {\n !include self.dsl\n }\n}") {
                "s = softwareSystem \"S\"\n!include self.dsl"
            }
            assertTrue(w.elements.isNotEmpty())
        }

        @Test
        fun `garbage never throws and still yields an svg`() {
            listOf("", "}}}{{{", "workspace {", "views { container }", "\"unterminated", "a -> -> b").forEach { input ->
                val result = StructurizrPreviewEngine.render(input, null)
                assertTrue(result.svg.startsWith("<svg"), input)
            }
        }

        @Test
        fun `text is escaped in the svg`() {
            val svg = StructurizrPreviewEngine.render("workspace {\n model {\n s = softwareSystem \"<b>&\"\n }\n}", null).svg
            assertTrue(svg.contains("&lt;b&gt;&amp;"))
            assertFalse(svg.contains("<b>&"))
        }
    }
}
