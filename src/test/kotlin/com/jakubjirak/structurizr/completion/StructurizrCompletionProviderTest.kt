package com.jakubjirak.structurizr.completion

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrCompletionProvider Keyword Definitions")
class StructurizrCompletionProviderTest {

    private val companionObj = StructurizrCompletionProvider.Companion
    private val companionClass = companionObj.javaClass

    @Suppress("UNCHECKED_CAST")
    private fun getStringList(name: String): List<String> {
        val method = companionClass.getMethod("get$name")
        return method.invoke(companionObj) as List<String>
    }

    @Suppress("UNCHECKED_CAST")
    private fun getStringMap(name: String): Map<String, String> {
        val method = companionClass.getMethod("get$name")
        return method.invoke(companionObj) as Map<String, String>
    }

    @Nested
    @DisplayName("Core keywords")
    inner class CoreKeywords {

        @Test
        fun `core keywords list is not empty`() {
            assertTrue(getStringList("CORE_KEYWORDS").isNotEmpty())
        }

        @Test
        fun `core keywords contains workspace`() {
            assertTrue(getStringList("CORE_KEYWORDS").contains("workspace"))
        }

        @Test
        fun `core keywords contains model`() {
            assertTrue(getStringList("CORE_KEYWORDS").contains("model"))
        }

        @Test
        fun `core keywords contains views`() {
            assertTrue(getStringList("CORE_KEYWORDS").contains("views"))
        }

        @Test
        fun `core keywords contains configuration`() {
            assertTrue(getStringList("CORE_KEYWORDS").contains("configuration"))
        }

        @Test
        fun `core keywords has exactly 5 entries`() {
            assertEquals(5, getStringList("CORE_KEYWORDS").size)
        }
    }

    @Nested
    @DisplayName("Model elements")
    inner class ModelElements {

        @Test
        fun `model elements map is not empty`() {
            assertTrue(getStringMap("MODEL_ELEMENTS").isNotEmpty())
        }

        @Test
        fun `model elements contains person`() {
            assertTrue(getStringMap("MODEL_ELEMENTS").containsKey("person"))
        }

        @Test
        fun `model elements contains softwareSystem`() {
            assertTrue(getStringMap("MODEL_ELEMENTS").containsKey("softwareSystem"))
        }

        @Test
        fun `model elements contains container`() {
            assertTrue(getStringMap("MODEL_ELEMENTS").containsKey("container"))
        }

        @Test
        fun `model elements contains component`() {
            assertTrue(getStringMap("MODEL_ELEMENTS").containsKey("component"))
        }

        @Test
        fun `model elements contains group`() {
            assertTrue(getStringMap("MODEL_ELEMENTS").containsKey("group"))
        }

        @Test
        fun `model elements contains enterprise`() {
            assertTrue(getStringMap("MODEL_ELEMENTS").containsKey("enterprise"))
        }

        @Test
        fun `model elements has exactly 7 entries`() {
            assertEquals(7, getStringMap("MODEL_ELEMENTS").size)
        }

        @Test
        fun `person is described as model element`() {
            assertEquals("model element", getStringMap("MODEL_ELEMENTS")["person"])
        }

        @Test
        fun `enterprise is described as boundary`() {
            assertEquals("boundary", getStringMap("MODEL_ELEMENTS")["enterprise"])
        }

        @Test
        fun `group is described as grouping`() {
            assertEquals("grouping", getStringMap("MODEL_ELEMENTS")["group"])
        }
    }

    @Nested
    @DisplayName("Deployment keywords")
    inner class DeploymentKeywords {

        @Test
        fun `deployment keywords list is not empty`() {
            assertTrue(getStringList("DEPLOYMENT_KEYWORDS").isNotEmpty())
        }

        @Test
        fun `deployment keywords contains deploymentEnvironment`() {
            assertTrue(getStringList("DEPLOYMENT_KEYWORDS").contains("deploymentEnvironment"))
        }

        @Test
        fun `deployment keywords contains deploymentGroup`() {
            assertTrue(getStringList("DEPLOYMENT_KEYWORDS").contains("deploymentGroup"))
        }

        @Test
        fun `deployment keywords contains deploymentNode`() {
            assertTrue(getStringList("DEPLOYMENT_KEYWORDS").contains("deploymentNode"))
        }

        @Test
        fun `deployment keywords contains infrastructureNode`() {
            assertTrue(getStringList("DEPLOYMENT_KEYWORDS").contains("infrastructureNode"))
        }

        @Test
        fun `deployment keywords contains softwareSystemInstance`() {
            assertTrue(getStringList("DEPLOYMENT_KEYWORDS").contains("softwareSystemInstance"))
        }

        @Test
        fun `deployment keywords contains containerInstance`() {
            assertTrue(getStringList("DEPLOYMENT_KEYWORDS").contains("containerInstance"))
        }

        @Test
        fun `deployment keywords has exactly 8 entries`() {
            assertEquals(8, getStringList("DEPLOYMENT_KEYWORDS").size)
        }
    }

    @Nested
    @DisplayName("View keywords")
    inner class ViewKeywords {

        @Test
        fun `view keywords list is not empty`() {
            assertTrue(getStringList("VIEW_KEYWORDS").isNotEmpty())
        }

        @Test
        fun `view keywords contains systemLandscape`() {
            assertTrue(getStringList("VIEW_KEYWORDS").contains("systemLandscape"))
        }

        @Test
        fun `view keywords contains systemContext`() {
            assertTrue(getStringList("VIEW_KEYWORDS").contains("systemContext"))
        }

        @Test
        fun `view keywords contains container`() {
            assertTrue(getStringList("VIEW_KEYWORDS").contains("container"))
        }

        @Test
        fun `view keywords contains component`() {
            assertTrue(getStringList("VIEW_KEYWORDS").contains("component"))
        }

        @Test
        fun `view keywords contains dynamic`() {
            assertTrue(getStringList("VIEW_KEYWORDS").contains("dynamic"))
        }

        @Test
        fun `view keywords contains deployment`() {
            assertTrue(getStringList("VIEW_KEYWORDS").contains("deployment"))
        }

        @Test
        fun `view keywords contains filtered`() {
            assertTrue(getStringList("VIEW_KEYWORDS").contains("filtered"))
        }

        @Test
        fun `view keywords contains custom`() {
            assertTrue(getStringList("VIEW_KEYWORDS").contains("custom"))
        }

        @Test
        fun `view keywords contains image`() {
            assertTrue(getStringList("VIEW_KEYWORDS").contains("image"))
        }

        @Test
        fun `view keywords has exactly 9 entries`() {
            assertEquals(9, getStringList("VIEW_KEYWORDS").size)
        }
    }

    @Nested
    @DisplayName("Style keywords")
    inner class StyleKeywords {

        @Test
        fun `style keywords list is not empty`() {
            assertTrue(getStringList("STYLE_KEYWORDS").isNotEmpty())
        }

        @Test
        fun `style keywords contains styles`() {
            assertTrue(getStringList("STYLE_KEYWORDS").contains("styles"))
        }

        @Test
        fun `style keywords contains element`() {
            assertTrue(getStringList("STYLE_KEYWORDS").contains("element"))
        }

        @Test
        fun `style keywords contains relationship`() {
            assertTrue(getStringList("STYLE_KEYWORDS").contains("relationship"))
        }

        @Test
        fun `style keywords contains themes`() {
            assertTrue(getStringList("STYLE_KEYWORDS").contains("themes"))
        }

        @Test
        fun `style keywords contains theme`() {
            assertTrue(getStringList("STYLE_KEYWORDS").contains("theme"))
        }

        @Test
        fun `style keywords contains branding`() {
            assertTrue(getStringList("STYLE_KEYWORDS").contains("branding"))
        }

        @Test
        fun `style keywords contains terminology`() {
            assertTrue(getStringList("STYLE_KEYWORDS").contains("terminology"))
        }

        @Test
        fun `style keywords has exactly 7 entries`() {
            assertEquals(7, getStringList("STYLE_KEYWORDS").size)
        }
    }

    @Nested
    @DisplayName("Style properties")
    inner class StyleProperties {

        @Test
        fun `style properties list is not empty`() {
            assertTrue(getStringList("STYLE_PROPERTIES").isNotEmpty())
        }

        @Test
        fun `style properties contains shape`() {
            assertTrue(getStringList("STYLE_PROPERTIES").contains("shape"))
        }

        @Test
        fun `style properties contains background`() {
            assertTrue(getStringList("STYLE_PROPERTIES").contains("background"))
        }

        @Test
        fun `style properties contains color`() {
            assertTrue(getStringList("STYLE_PROPERTIES").contains("color"))
        }

        @Test
        fun `style properties contains colour`() {
            assertTrue(getStringList("STYLE_PROPERTIES").contains("colour"))
        }

        @Test
        fun `style properties contains fontSize`() {
            assertTrue(getStringList("STYLE_PROPERTIES").contains("fontSize"))
        }

        @Test
        fun `style properties contains dashed`() {
            assertTrue(getStringList("STYLE_PROPERTIES").contains("dashed"))
        }

        @Test
        fun `style properties has exactly 18 entries`() {
            assertEquals(18, getStringList("STYLE_PROPERTIES").size)
        }
    }

    @Nested
    @DisplayName("Shapes")
    inner class Shapes {

        @Test
        fun `shapes list is not empty`() {
            assertTrue(getStringList("SHAPES").isNotEmpty())
        }

        @Test
        fun `shapes contains Box`() {
            assertTrue(getStringList("SHAPES").contains("Box"))
        }

        @Test
        fun `shapes contains Person`() {
            assertTrue(getStringList("SHAPES").contains("Person"))
        }

        @Test
        fun `shapes contains Cylinder`() {
            assertTrue(getStringList("SHAPES").contains("Cylinder"))
        }

        @Test
        fun `shapes contains WebBrowser`() {
            assertTrue(getStringList("SHAPES").contains("WebBrowser"))
        }

        @Test
        fun `shapes has exactly 14 entries`() {
            assertEquals(14, getStringList("SHAPES").size)
        }

        @Test
        fun `shapes contains all expected values`() {
            val expected = listOf(
                "Box", "RoundedBox", "Circle", "Ellipse", "Hexagon",
                "Cylinder", "Pipe", "Person", "Robot", "Folder",
                "WebBrowser", "MobileDevicePortrait", "MobileDeviceLandscape", "Component"
            )
            assertEquals(expected, getStringList("SHAPES"))
        }
    }

    @Nested
    @DisplayName("Layout directions")
    inner class LayoutDirections {

        @Test
        fun `layout directions has 4 entries`() {
            assertEquals(4, getStringList("LAYOUT_DIRECTIONS").size)
        }

        @Test
        fun `layout directions contains tb, bt, lr, rl`() {
            assertEquals(listOf("tb", "bt", "lr", "rl"), getStringList("LAYOUT_DIRECTIONS"))
        }
    }

    @Nested
    @DisplayName("Directives")
    inner class Directives {

        @Test
        fun `directives list is not empty`() {
            assertTrue(getStringList("DIRECTIVES").isNotEmpty())
        }

        @Test
        fun `directives contains include`() {
            assertTrue(getStringList("DIRECTIVES").contains("!include"))
        }

        @Test
        fun `directives contains docs`() {
            assertTrue(getStringList("DIRECTIVES").contains("!docs"))
        }

        @Test
        fun `directives contains adrs`() {
            assertTrue(getStringList("DIRECTIVES").contains("!adrs"))
        }

        @Test
        fun `directives contains identifiers`() {
            assertTrue(getStringList("DIRECTIVES").contains("!identifiers"))
        }

        @Test
        fun `directives contains impliedRelationships`() {
            assertTrue(getStringList("DIRECTIVES").contains("!impliedRelationships"))
        }

        @Test
        fun `directives contains constant`() {
            assertTrue(getStringList("DIRECTIVES").contains("!constant"))
        }

        @Test
        fun `directives contains const`() {
            assertTrue(getStringList("DIRECTIVES").contains("!const"))
        }

        @Test
        fun `directives contains var`() {
            assertTrue(getStringList("DIRECTIVES").contains("!var"))
        }

        @Test
        fun `directives contains plugin`() {
            assertTrue(getStringList("DIRECTIVES").contains("!plugin"))
        }

        @Test
        fun `directives contains script`() {
            assertTrue(getStringList("DIRECTIVES").contains("!script"))
        }

        @Test
        fun `directives has exactly 17 entries`() {
            assertEquals(17, getStringList("DIRECTIVES").size)
        }
    }

    @Nested
    @DisplayName("Other keywords")
    inner class OtherKeywords {

        @Test
        fun `other keywords list is not empty`() {
            assertTrue(getStringList("OTHER_KEYWORDS").isNotEmpty())
        }

        @Test
        fun `other keywords contains include`() {
            assertTrue(getStringList("OTHER_KEYWORDS").contains("include"))
        }

        @Test
        fun `other keywords contains exclude`() {
            assertTrue(getStringList("OTHER_KEYWORDS").contains("exclude"))
        }

        @Test
        fun `other keywords contains autoLayout`() {
            assertTrue(getStringList("OTHER_KEYWORDS").contains("autoLayout"))
        }

        @Test
        fun `other keywords contains tags`() {
            assertTrue(getStringList("OTHER_KEYWORDS").contains("tags"))
        }

        @Test
        fun `other keywords contains description`() {
            assertTrue(getStringList("OTHER_KEYWORDS").contains("description"))
        }

        @Test
        fun `other keywords has exactly 28 entries`() {
            assertEquals(28, getStringList("OTHER_KEYWORDS").size)
        }
    }

    @Nested
    @DisplayName("Total keyword coverage")
    inner class TotalCoverage {

        @Test
        fun `total keyword count across all categories`() {
            val total = getStringList("CORE_KEYWORDS").size +
                getStringMap("MODEL_ELEMENTS").size +
                getStringList("DEPLOYMENT_KEYWORDS").size +
                getStringList("VIEW_KEYWORDS").size +
                getStringList("STYLE_KEYWORDS").size +
                getStringList("STYLE_PROPERTIES").size +
                getStringList("SHAPES").size +
                getStringList("LAYOUT_DIRECTIONS").size +
                getStringList("DIRECTIVES").size +
                getStringList("OTHER_KEYWORDS").size
            // 5+7+8+9+7+18+14+4+17+28 = 117
            assertEquals(117, total)
        }

        @Test
        fun `completion provider class can be instantiated`() {
            val provider = StructurizrCompletionProvider()
            assertNotNull(provider)
        }
    }
}
