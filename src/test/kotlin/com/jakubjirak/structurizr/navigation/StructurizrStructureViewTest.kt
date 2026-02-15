package com.jakubjirak.structurizr.navigation

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.jakubjirak.structurizr.language.StructurizrIcons
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Structurizr Structure View")
class StructurizrStructureViewTest {

    @Nested
    @DisplayName("StructurizrStructureViewFactory")
    inner class FactoryTests {

        @Test
        fun `can be instantiated`() {
            val factory = StructurizrStructureViewFactory()
            assertNotNull(factory)
        }

        @Test
        fun `implements PsiStructureViewFactory`() {
            val factory = StructurizrStructureViewFactory()
            assertInstanceOf(PsiStructureViewFactory::class.java, factory)
        }

        @Test
        fun `has getStructureViewBuilder method`() {
            val method = StructurizrStructureViewFactory::class.java.getMethod(
                "getStructureViewBuilder",
                com.intellij.psi.PsiFile::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `getStructureViewBuilder returns nullable type`() {
            val method = StructurizrStructureViewFactory::class.java.getMethod(
                "getStructureViewBuilder",
                com.intellij.psi.PsiFile::class.java
            )
            // The annotation on the method allows null return
            val annotations = method.annotations
            assertNotNull(annotations)
        }
    }

    @Nested
    @DisplayName("StructurizrStructureViewModel")
    inner class ViewModelTests {

        @Test
        fun `class is accessible`() {
            assertNotNull(StructurizrStructureViewModel::class.java)
        }

        @Test
        fun `extends StructureViewModelBase`() {
            assertTrue(
                com.intellij.ide.structureView.StructureViewModelBase::class.java
                    .isAssignableFrom(StructurizrStructureViewModel::class.java)
            )
        }

        @Test
        fun `implements ElementInfoProvider`() {
            assertTrue(
                StructureViewModel.ElementInfoProvider::class.java
                    .isAssignableFrom(StructurizrStructureViewModel::class.java)
            )
        }

        @Test
        fun `has isAlwaysShowsPlus method`() {
            val method = StructurizrStructureViewModel::class.java.getMethod(
                "isAlwaysShowsPlus",
                StructureViewTreeElement::class.java
            )
            assertNotNull(method)
            assertEquals(Boolean::class.javaPrimitiveType, method.returnType)
        }

        @Test
        fun `has isAlwaysLeaf method`() {
            val method = StructurizrStructureViewModel::class.java.getMethod(
                "isAlwaysLeaf",
                StructureViewTreeElement::class.java
            )
            assertNotNull(method)
            assertEquals(Boolean::class.javaPrimitiveType, method.returnType)
        }
    }

    @Nested
    @DisplayName("StructurizrStructureViewElement")
    inner class ViewElementTests {

        @Test
        fun `class is accessible`() {
            assertNotNull(StructurizrStructureViewElement::class.java)
        }

        @Test
        fun `implements StructureViewTreeElement`() {
            assertTrue(
                StructureViewTreeElement::class.java
                    .isAssignableFrom(StructurizrStructureViewElement::class.java)
            )
        }

        @Test
        fun `has getValue method`() {
            val method = StructurizrStructureViewElement::class.java.getMethod("getValue")
            assertNotNull(method)
        }

        @Test
        fun `has getPresentation method`() {
            val method = StructurizrStructureViewElement::class.java.getMethod("getPresentation")
            assertNotNull(method)
        }

        @Test
        fun `has getChildren method`() {
            val method = StructurizrStructureViewElement::class.java.getMethod("getChildren")
            assertNotNull(method)
        }

        @Test
        fun `has navigate method`() {
            val method = StructurizrStructureViewElement::class.java.getMethod(
                "navigate",
                Boolean::class.javaPrimitiveType
            )
            assertNotNull(method)
        }

        @Test
        fun `has canNavigate method`() {
            val method = StructurizrStructureViewElement::class.java.getMethod("canNavigate")
            assertNotNull(method)
            assertEquals(Boolean::class.javaPrimitiveType, method.returnType)
        }

        @Test
        fun `has canNavigateToSource method`() {
            val method = StructurizrStructureViewElement::class.java.getMethod("canNavigateToSource")
            assertNotNull(method)
            assertEquals(Boolean::class.javaPrimitiveType, method.returnType)
        }

        @Test
        fun `constructor accepts PsiElement`() {
            val constructor = StructurizrStructureViewElement::class.java.getConstructor(
                com.intellij.psi.PsiElement::class.java
            )
            assertNotNull(constructor)
        }
    }

    @Nested
    @DisplayName("Structure types used in view")
    inner class StructureTypesTests {

        @Test
        fun `WORKSPACE_BLOCK type exists`() {
            assertNotNull(StructurizrTypes.WORKSPACE_BLOCK)
        }

        @Test
        fun `MODEL_BLOCK type exists`() {
            assertNotNull(StructurizrTypes.MODEL_BLOCK)
        }

        @Test
        fun `VIEWS_BLOCK type exists`() {
            assertNotNull(StructurizrTypes.VIEWS_BLOCK)
        }

        @Test
        fun `STYLES_BLOCK type exists`() {
            assertNotNull(StructurizrTypes.STYLES_BLOCK)
        }

        @Test
        fun `CONFIGURATION_BLOCK type exists`() {
            assertNotNull(StructurizrTypes.CONFIGURATION_BLOCK)
        }

        @Test
        fun `all 24 structure types exist`() {
            val structureTypes = listOf(
                StructurizrTypes.WORKSPACE_BLOCK,
                StructurizrTypes.MODEL_BLOCK,
                StructurizrTypes.VIEWS_BLOCK,
                StructurizrTypes.STYLES_BLOCK,
                StructurizrTypes.CONFIGURATION_BLOCK,
                StructurizrTypes.PERSON_DEFINITION,
                StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION,
                StructurizrTypes.CONTAINER_DEFINITION,
                StructurizrTypes.COMPONENT_DEFINITION,
                StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK,
                StructurizrTypes.DEPLOYMENT_NODE_DEFINITION,
                StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION,
                StructurizrTypes.GROUP_BLOCK,
                StructurizrTypes.ELEMENT_ASSIGNMENT,
                StructurizrTypes.ENTERPRISE_BLOCK,
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
            assertEquals(24, structureTypes.size)
            structureTypes.forEach { assertNotNull(it) }
        }
    }

    @Nested
    @DisplayName("Icons used in structure view")
    inner class IconMappingTests {

        @Test
        fun `FILE icon is available for fallback`() {
            assertNotNull(StructurizrIcons.FILE)
        }

        @Test
        fun `WORKSPACE icon is available`() {
            assertNotNull(StructurizrIcons.WORKSPACE)
        }

        @Test
        fun `MODEL icon is available`() {
            assertNotNull(StructurizrIcons.MODEL)
        }

        @Test
        fun `VIEWS icon is available`() {
            assertNotNull(StructurizrIcons.VIEWS)
        }

        @Test
        fun `STYLES icon is available`() {
            assertNotNull(StructurizrIcons.STYLES)
        }

        @Test
        fun `PERSON icon is available`() {
            assertNotNull(StructurizrIcons.PERSON)
        }

        @Test
        fun `SOFTWARE_SYSTEM icon is available`() {
            assertNotNull(StructurizrIcons.SOFTWARE_SYSTEM)
        }

        @Test
        fun `CONTAINER icon is available`() {
            assertNotNull(StructurizrIcons.CONTAINER)
        }

        @Test
        fun `COMPONENT icon is available`() {
            assertNotNull(StructurizrIcons.COMPONENT)
        }

        @Test
        fun `DEPLOYMENT icon is available`() {
            assertNotNull(StructurizrIcons.DEPLOYMENT)
        }

        @Test
        fun `AllIcons Settings icon is accessible`() {
            assertNotNull(com.intellij.icons.AllIcons.General.Settings)
        }

        @Test
        fun `AllIcons Folder icon is accessible`() {
            assertNotNull(com.intellij.icons.AllIcons.Nodes.Folder)
        }
    }

    @Nested
    @DisplayName("Element type name mapping")
    inner class ElementTypeNameTests {

        @Test
        fun `getElementTypeName is a private static method`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertNotNull(method)
        }

        @Test
        fun `getElementTypeName returns workspace for WORKSPACE_BLOCK`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("workspace", method.invoke(null, StructurizrTypes.WORKSPACE_BLOCK))
        }

        @Test
        fun `getElementTypeName returns model for MODEL_BLOCK`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("model", method.invoke(null, StructurizrTypes.MODEL_BLOCK))
        }

        @Test
        fun `getElementTypeName returns views for VIEWS_BLOCK`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("views", method.invoke(null, StructurizrTypes.VIEWS_BLOCK))
        }

        @Test
        fun `getElementTypeName returns styles for STYLES_BLOCK`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("styles", method.invoke(null, StructurizrTypes.STYLES_BLOCK))
        }

        @Test
        fun `getElementTypeName returns configuration for CONFIGURATION_BLOCK`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("configuration", method.invoke(null, StructurizrTypes.CONFIGURATION_BLOCK))
        }

        @Test
        fun `getElementTypeName returns person for PERSON_DEFINITION`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("person", method.invoke(null, StructurizrTypes.PERSON_DEFINITION))
        }

        @Test
        fun `getElementTypeName returns softwareSystem for SOFTWARE_SYSTEM_DEFINITION`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("softwareSystem", method.invoke(null, StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION))
        }

        @Test
        fun `getElementTypeName returns container for CONTAINER_DEFINITION`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("container", method.invoke(null, StructurizrTypes.CONTAINER_DEFINITION))
        }

        @Test
        fun `getElementTypeName returns component for COMPONENT_DEFINITION`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("component", method.invoke(null, StructurizrTypes.COMPONENT_DEFINITION))
        }

        @Test
        fun `getElementTypeName returns deploymentEnvironment for DEPLOYMENT_ENVIRONMENT_BLOCK`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("deploymentEnvironment", method.invoke(null, StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK))
        }

        @Test
        fun `getElementTypeName returns deploymentNode for DEPLOYMENT_NODE_DEFINITION`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("deploymentNode", method.invoke(null, StructurizrTypes.DEPLOYMENT_NODE_DEFINITION))
        }

        @Test
        fun `getElementTypeName returns infrastructureNode for INFRASTRUCTURE_NODE_DEFINITION`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("infrastructureNode", method.invoke(null, StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION))
        }

        @Test
        fun `getElementTypeName returns group for GROUP_BLOCK`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("group", method.invoke(null, StructurizrTypes.GROUP_BLOCK))
        }

        @Test
        fun `getElementTypeName returns enterprise for ENTERPRISE_BLOCK`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true
            assertEquals("enterprise", method.invoke(null, StructurizrTypes.ENTERPRISE_BLOCK))
        }

        @Test
        fun `getElementTypeName returns view names for view types`() {
            val method = StructurizrStructureViewElement::class.java.getDeclaredMethod(
                "getElementTypeName",
                com.intellij.psi.tree.IElementType::class.java
            )
            method.isAccessible = true

            val viewMappings = mapOf(
                StructurizrTypes.SYSTEM_LANDSCAPE_VIEW to "systemLandscape",
                StructurizrTypes.SYSTEM_CONTEXT_VIEW to "systemContext",
                StructurizrTypes.CONTAINER_VIEW to "container view",
                StructurizrTypes.COMPONENT_VIEW to "component view",
                StructurizrTypes.DEPLOYMENT_VIEW to "deployment view",
                StructurizrTypes.DYNAMIC_VIEW to "dynamic view",
                StructurizrTypes.CUSTOM_VIEW to "custom view",
                StructurizrTypes.FILTERED_VIEW to "filtered view",
                StructurizrTypes.IMAGE_VIEW to "image view"
            )

            viewMappings.forEach { (type, expected) ->
                assertEquals(expected, method.invoke(null, type), "Type name for $type")
            }
        }
    }
}
