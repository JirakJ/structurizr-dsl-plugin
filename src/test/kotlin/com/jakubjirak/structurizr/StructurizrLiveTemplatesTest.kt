package com.jakubjirak.structurizr

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import javax.xml.parsers.DocumentBuilderFactory

@DisplayName("Structurizr Live Templates")
class StructurizrLiveTemplatesTest {

    private val templates: Map<String, org.w3c.dom.Element> by lazy {
        val stream = javaClass.getResourceAsStream("/liveTemplates/Structurizr.xml")
            ?: throw IllegalStateException("Structurizr.xml not found on classpath")
        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream)
        val nodeList = doc.getElementsByTagName("template")
        val result = mutableMapOf<String, org.w3c.dom.Element>()
        for (i in 0 until nodeList.length) {
            val element = nodeList.item(i) as org.w3c.dom.Element
            val name = element.getAttribute("name")
            result[name] = element
        }
        result
    }

    @Nested
    @DisplayName("Template file")
    inner class TemplateFile {

        @Test
        fun `Structurizr xml exists on classpath`() {
            val stream = javaClass.getResourceAsStream("/liveTemplates/Structurizr.xml")
            assertNotNull(stream, "Structurizr.xml should be on classpath")
            stream?.close()
        }

        @Test
        fun `contains exactly 17 templates`() {
            assertEquals(17, templates.size, "Expected 17 templates, found: ${templates.keys}")
        }

        @Test
        fun `template set has group name Structurizr`() {
            val stream = javaClass.getResourceAsStream("/liveTemplates/Structurizr.xml")!!
            val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream)
            val templateSet = doc.getElementsByTagName("templateSet").item(0) as org.w3c.dom.Element
            assertEquals("Structurizr", templateSet.getAttribute("group"))
        }
    }

    @Nested
    @DisplayName("All expected templates exist")
    inner class TemplateExistence {

        private val expectedTemplates = listOf(
            "ws", "per", "ss", "con", "comp", "rel", "dep", "sv", "cv", "sty",
            "depenv", "depnode", "dynview", "filtview", "infnode", "inc", "cst"
        )

        @Test
        fun `all 17 expected template abbreviations exist`() {
            expectedTemplates.forEach { name ->
                assertTrue(templates.containsKey(name), "Template '$name' should exist")
            }
        }

        @Test
        fun `no unexpected templates exist`() {
            templates.keys.forEach { name ->
                assertTrue(expectedTemplates.contains(name), "Unexpected template: '$name'")
            }
        }
    }

    @Nested
    @DisplayName("Template values")
    inner class TemplateValues {

        @Test
        fun `all templates have non-empty value`() {
            templates.forEach { (name, element) ->
                val value = element.getAttribute("value")
                assertTrue(value.isNotEmpty(), "Template '$name' should have a non-empty value")
            }
        }

        @Test
        fun `all templates have non-empty description`() {
            templates.forEach { (name, element) ->
                val description = element.getAttribute("description")
                assertTrue(description.isNotEmpty(), "Template '$name' should have a non-empty description")
            }
        }
    }

    @Nested
    @DisplayName("Template contexts")
    inner class TemplateContexts {

        @Test
        fun `all templates have STRUCTURIZR_DSL context`() {
            templates.forEach { (name, element) ->
                val contextNodes = element.getElementsByTagName("option")
                var hasStructurizrContext = false
                for (i in 0 until contextNodes.length) {
                    val option = contextNodes.item(i) as org.w3c.dom.Element
                    if (option.getAttribute("name") == "STRUCTURIZR_DSL" &&
                        option.getAttribute("value") == "true") {
                        hasStructurizrContext = true
                        break
                    }
                }
                assertTrue(hasStructurizrContext, "Template '$name' should have STRUCTURIZR_DSL context")
            }
        }
    }

    @Nested
    @DisplayName("Specific template descriptions")
    inner class SpecificTemplates {

        @Test
        fun `ws template is Workspace`() {
            assertEquals("Workspace with model and views", templates["ws"]!!.getAttribute("description"))
        }

        @Test
        fun `per template is Person`() {
            assertEquals("Person definition", templates["per"]!!.getAttribute("description"))
        }

        @Test
        fun `ss template is Software System`() {
            assertEquals("Software System definition", templates["ss"]!!.getAttribute("description"))
        }

        @Test
        fun `con template is Container`() {
            assertEquals("Container definition", templates["con"]!!.getAttribute("description"))
        }

        @Test
        fun `comp template is Component`() {
            assertEquals("Component definition", templates["comp"]!!.getAttribute("description"))
        }

        @Test
        fun `rel template is Relationship`() {
            assertEquals("Relationship (->)", templates["rel"]!!.getAttribute("description"))
        }

        @Test
        fun `dep template is Deployment`() {
            assertEquals("Deployment environment with node", templates["dep"]!!.getAttribute("description"))
        }

        @Test
        fun `inc template is Include directive`() {
            assertEquals("Include directive", templates["inc"]!!.getAttribute("description"))
        }

        @Test
        fun `cst template is Constant`() {
            assertEquals("Constant", templates["cst"]!!.getAttribute("description"))
        }

        @Test
        fun `dynview template is Dynamic View`() {
            assertEquals("Dynamic View", templates["dynview"]!!.getAttribute("description"))
        }

        @Test
        fun `filtview template is Filtered View`() {
            assertEquals("Filtered View", templates["filtview"]!!.getAttribute("description"))
        }

        @Test
        fun `infnode template is Infrastructure Node`() {
            assertEquals("Infrastructure Node", templates["infnode"]!!.getAttribute("description"))
        }

        @Test
        fun `ws template value contains workspace keyword`() {
            assertTrue(templates["ws"]!!.getAttribute("value").contains("workspace"))
        }

        @Test
        fun `ws template value contains model block`() {
            assertTrue(templates["ws"]!!.getAttribute("value").contains("model"))
        }

        @Test
        fun `ws template value contains views block`() {
            assertTrue(templates["ws"]!!.getAttribute("value").contains("views"))
        }
    }
}
