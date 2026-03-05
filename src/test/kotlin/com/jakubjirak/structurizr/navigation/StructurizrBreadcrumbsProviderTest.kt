package com.jakubjirak.structurizr.navigation

import com.intellij.ui.breadcrumbs.BreadcrumbsProvider
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.lang.reflect.Method

@DisplayName("StructurizrBreadcrumbsProvider")
class StructurizrBreadcrumbsProviderTest {

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `can be instantiated`() {
            val provider = StructurizrBreadcrumbsProvider()
            assertNotNull(provider)
        }

        @Test
        fun `implements BreadcrumbsProvider`() {
            val provider = StructurizrBreadcrumbsProvider()
            assertInstanceOf(BreadcrumbsProvider::class.java, provider)
        }
    }

    @Nested
    @DisplayName("getLanguages")
    inner class LanguageTests {

        @Test
        fun `returns non-null array`() {
            val provider = StructurizrBreadcrumbsProvider()
            assertNotNull(provider.languages)
        }

        @Test
        fun `returns array with exactly one language`() {
            val provider = StructurizrBreadcrumbsProvider()
            assertEquals(1, provider.languages.size)
        }

        @Test
        fun `returns Structurizr language`() {
            val provider = StructurizrBreadcrumbsProvider()
            val lang = provider.languages[0]
            assertEquals("Structurizr", lang.id)
        }

        @Test
        fun `returns same language reference on multiple calls`() {
            val provider = StructurizrBreadcrumbsProvider()
            val langs1 = provider.languages
            val langs2 = provider.languages
            assertEquals(langs1[0].id, langs2[0].id)
        }
    }

    @Nested
    @DisplayName("Accepted types")
    inner class AcceptedTypesTests {

        @Test
        fun `ACCEPTED_TYPES field exists via reflection`() {
            val field = StructurizrBreadcrumbsProvider::class.java.getDeclaredField("ACCEPTED_TYPES")
            field.isAccessible = true
            assertNotNull(field)
        }

        @Test
        fun `ACCEPTED_TYPES contains 24 types`() {
            val field = StructurizrBreadcrumbsProvider::class.java.getDeclaredField("ACCEPTED_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertEquals(24, types.size)
        }

        @Test
        fun `ACCEPTED_TYPES contains WORKSPACE_BLOCK`() {
            val field = StructurizrBreadcrumbsProvider::class.java.getDeclaredField("ACCEPTED_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertTrue(types.contains(StructurizrTypes.WORKSPACE_BLOCK))
        }

        @Test
        fun `ACCEPTED_TYPES contains MODEL_BLOCK`() {
            val field = StructurizrBreadcrumbsProvider::class.java.getDeclaredField("ACCEPTED_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertTrue(types.contains(StructurizrTypes.MODEL_BLOCK))
        }

        @Test
        fun `ACCEPTED_TYPES contains VIEWS_BLOCK`() {
            val field = StructurizrBreadcrumbsProvider::class.java.getDeclaredField("ACCEPTED_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertTrue(types.contains(StructurizrTypes.VIEWS_BLOCK))
        }

        @Test
        fun `ACCEPTED_TYPES contains STYLES_BLOCK`() {
            val field = StructurizrBreadcrumbsProvider::class.java.getDeclaredField("ACCEPTED_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertTrue(types.contains(StructurizrTypes.STYLES_BLOCK))
        }

        @Test
        fun `ACCEPTED_TYPES contains all block types`() {
            val field = StructurizrBreadcrumbsProvider::class.java.getDeclaredField("ACCEPTED_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>

            val expectedTypes = listOf(
                StructurizrTypes.WORKSPACE_BLOCK,
                StructurizrTypes.MODEL_BLOCK,
                StructurizrTypes.VIEWS_BLOCK,
                StructurizrTypes.STYLES_BLOCK,
                StructurizrTypes.CONFIGURATION_BLOCK,
                StructurizrTypes.PERSON_DEFINITION,
                StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION,
                StructurizrTypes.CONTAINER_DEFINITION,
                StructurizrTypes.COMPONENT_DEFINITION,
                StructurizrTypes.GROUP_BLOCK,
                StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK,
                StructurizrTypes.DEPLOYMENT_NODE_DEFINITION,
                StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION,
                StructurizrTypes.ENTERPRISE_BLOCK,
                StructurizrTypes.ELEMENT_ASSIGNMENT,
                StructurizrTypes.SYSTEM_LANDSCAPE_VIEW,
                StructurizrTypes.SYSTEM_CONTEXT_VIEW,
                StructurizrTypes.CONTAINER_VIEW,
                StructurizrTypes.COMPONENT_VIEW,
                StructurizrTypes.DEPLOYMENT_VIEW,
                StructurizrTypes.DYNAMIC_VIEW,
                StructurizrTypes.CUSTOM_VIEW,
                StructurizrTypes.FILTERED_VIEW,
                StructurizrTypes.IMAGE_VIEW
            )

            expectedTypes.forEach { type ->
                assertTrue(types.contains(type), "ACCEPTED_TYPES should contain $type")
            }
        }
    }

    @Nested
    @DisplayName("getTypeName mapping via reflection")
    inner class TypeNameMappingTests {

        private val companionObj: Any = StructurizrBreadcrumbsProvider::class.java
            .getDeclaredField("Companion").get(null)

        private fun getTypeName(type: com.intellij.psi.tree.IElementType): String {
            val method: Method = companionObj.javaClass.getDeclaredMethod(
                "getTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            return method.invoke(companionObj, type) as String
        }

        @Test
        fun `WORKSPACE_BLOCK maps to workspace`() {
            assertEquals("workspace", getTypeName(StructurizrTypes.WORKSPACE_BLOCK))
        }

        @Test
        fun `MODEL_BLOCK maps to model`() {
            assertEquals("model", getTypeName(StructurizrTypes.MODEL_BLOCK))
        }

        @Test
        fun `VIEWS_BLOCK maps to views`() {
            assertEquals("views", getTypeName(StructurizrTypes.VIEWS_BLOCK))
        }

        @Test
        fun `STYLES_BLOCK maps to styles`() {
            assertEquals("styles", getTypeName(StructurizrTypes.STYLES_BLOCK))
        }

        @Test
        fun `CONFIGURATION_BLOCK maps to configuration`() {
            assertEquals("configuration", getTypeName(StructurizrTypes.CONFIGURATION_BLOCK))
        }

        @Test
        fun `PERSON_DEFINITION maps to person`() {
            assertEquals("person", getTypeName(StructurizrTypes.PERSON_DEFINITION))
        }

        @Test
        fun `SOFTWARE_SYSTEM_DEFINITION maps to softwareSystem`() {
            assertEquals("softwareSystem", getTypeName(StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION))
        }

        @Test
        fun `CONTAINER_DEFINITION maps to container`() {
            assertEquals("container", getTypeName(StructurizrTypes.CONTAINER_DEFINITION))
        }

        @Test
        fun `COMPONENT_DEFINITION maps to component`() {
            assertEquals("component", getTypeName(StructurizrTypes.COMPONENT_DEFINITION))
        }

        @Test
        fun `GROUP_BLOCK maps to group`() {
            assertEquals("group", getTypeName(StructurizrTypes.GROUP_BLOCK))
        }

        @Test
        fun `DEPLOYMENT_ENVIRONMENT_BLOCK maps to deploymentEnvironment`() {
            assertEquals("deploymentEnvironment", getTypeName(StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK))
        }

        @Test
        fun `DEPLOYMENT_NODE_DEFINITION maps to deploymentNode`() {
            assertEquals("deploymentNode", getTypeName(StructurizrTypes.DEPLOYMENT_NODE_DEFINITION))
        }

        @Test
        fun `INFRASTRUCTURE_NODE_DEFINITION maps to infrastructureNode`() {
            assertEquals("infrastructureNode", getTypeName(StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION))
        }

        @Test
        fun `ENTERPRISE_BLOCK maps to enterprise`() {
            assertEquals("enterprise", getTypeName(StructurizrTypes.ENTERPRISE_BLOCK))
        }

        @Test
        fun `ELEMENT_ASSIGNMENT maps to element`() {
            assertEquals("element", getTypeName(StructurizrTypes.ELEMENT_ASSIGNMENT))
        }

        @Test
        fun `SYSTEM_LANDSCAPE_VIEW maps to systemLandscape`() {
            assertEquals("systemLandscape", getTypeName(StructurizrTypes.SYSTEM_LANDSCAPE_VIEW))
        }

        @Test
        fun `SYSTEM_CONTEXT_VIEW maps to systemContext`() {
            assertEquals("systemContext", getTypeName(StructurizrTypes.SYSTEM_CONTEXT_VIEW))
        }

        @Test
        fun `CONTAINER_VIEW maps to container view`() {
            assertEquals("container view", getTypeName(StructurizrTypes.CONTAINER_VIEW))
        }

        @Test
        fun `COMPONENT_VIEW maps to component view`() {
            assertEquals("component view", getTypeName(StructurizrTypes.COMPONENT_VIEW))
        }

        @Test
        fun `DEPLOYMENT_VIEW maps to deployment view`() {
            assertEquals("deployment view", getTypeName(StructurizrTypes.DEPLOYMENT_VIEW))
        }

        @Test
        fun `DYNAMIC_VIEW maps to dynamic view`() {
            assertEquals("dynamic view", getTypeName(StructurizrTypes.DYNAMIC_VIEW))
        }

        @Test
        fun `CUSTOM_VIEW maps to custom view`() {
            assertEquals("custom view", getTypeName(StructurizrTypes.CUSTOM_VIEW))
        }

        @Test
        fun `FILTERED_VIEW maps to filtered view`() {
            assertEquals("filtered view", getTypeName(StructurizrTypes.FILTERED_VIEW))
        }

        @Test
        fun `IMAGE_VIEW maps to image view`() {
            assertEquals("image view", getTypeName(StructurizrTypes.IMAGE_VIEW))
        }
    }

    @Nested
    @DisplayName("Method signatures")
    inner class MethodSignatures {

        @Test
        fun `acceptElement method exists`() {
            val method = StructurizrBreadcrumbsProvider::class.java.getMethod(
                "acceptElement",
                com.intellij.psi.PsiElement::class.java
            )
            assertNotNull(method)
            assertEquals(Boolean::class.javaPrimitiveType, method.returnType)
        }

        @Test
        fun `getElementInfo method exists`() {
            val method = StructurizrBreadcrumbsProvider::class.java.getMethod(
                "getElementInfo",
                com.intellij.psi.PsiElement::class.java
            )
            assertNotNull(method)
            assertEquals(String::class.java, method.returnType)
        }

        @Test
        fun `getElementTooltip method exists`() {
            val method = StructurizrBreadcrumbsProvider::class.java.getMethod(
                "getElementTooltip",
                com.intellij.psi.PsiElement::class.java
            )
            assertNotNull(method)
            assertEquals(String::class.java, method.returnType)
        }
    }
}
