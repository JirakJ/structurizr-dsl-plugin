package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.LocalInspectionTool
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("InvalidContextInspection")
class InvalidContextInspectionTest {

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `can be instantiated`() {
            val inspection = InvalidContextInspection()
            assertNotNull(inspection)
        }

        @Test
        fun `extends StructurizrInspectionBase`() {
            val inspection = InvalidContextInspection()
            assertInstanceOf(StructurizrInspectionBase::class.java, inspection)
        }

        @Test
        fun `extends LocalInspectionTool`() {
            val inspection = InvalidContextInspection()
            assertInstanceOf(LocalInspectionTool::class.java, inspection)
        }

        @Test
        fun `class is final`() {
            assertTrue(java.lang.reflect.Modifier.isFinal(InvalidContextInspection::class.java.modifiers))
        }
    }

    @Nested
    @DisplayName("Display properties")
    inner class DisplayProperties {

        @Test
        fun `display name is not null`() {
            val inspection = InvalidContextInspection()
            assertNotNull(inspection.displayName)
        }

        @Test
        fun `display name is not empty`() {
            val inspection = InvalidContextInspection()
            assertTrue(inspection.displayName.isNotEmpty())
        }

        @Test
        fun `display name is correct`() {
            val inspection = InvalidContextInspection()
            assertEquals("Invalid element context", inspection.displayName)
        }

        @Test
        fun `group display name is Structurizr`() {
            val inspection = InvalidContextInspection()
            assertEquals("Structurizr", inspection.groupDisplayName)
        }

        @Test
        fun `short name is not null`() {
            val inspection = InvalidContextInspection()
            assertNotNull(inspection.shortName)
        }

        @Test
        fun `short name is not empty`() {
            val inspection = InvalidContextInspection()
            assertTrue(inspection.shortName.isNotEmpty())
        }

        @Test
        fun `short name is correct`() {
            val inspection = InvalidContextInspection()
            assertEquals("StructurizrInvalidContext", inspection.shortName)
        }

        @Test
        fun `is enabled by default`() {
            val inspection = InvalidContextInspection()
            assertTrue(inspection.isEnabledByDefault)
        }
    }

    @Nested
    @DisplayName("Handled element types")
    inner class HandledElementTypes {

        @Test
        fun `CONTAINER_DEFINITION type exists in StructurizrTypes`() {
            assertNotNull(StructurizrTypes.CONTAINER_DEFINITION)
        }

        @Test
        fun `COMPONENT_DEFINITION type exists in StructurizrTypes`() {
            assertNotNull(StructurizrTypes.COMPONENT_DEFINITION)
        }

        @Test
        fun `DEPLOYMENT_NODE_DEFINITION type exists in StructurizrTypes`() {
            assertNotNull(StructurizrTypes.DEPLOYMENT_NODE_DEFINITION)
        }

        @Test
        fun `INFRASTRUCTURE_NODE_DEFINITION type exists in StructurizrTypes`() {
            assertNotNull(StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION)
        }

        @Test
        fun `SOFTWARE_SYSTEM_INSTANCE_DEFINITION type exists in StructurizrTypes`() {
            assertNotNull(StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE_DEFINITION)
        }

        @Test
        fun `CONTAINER_INSTANCE_DEFINITION type exists in StructurizrTypes`() {
            assertNotNull(StructurizrTypes.CONTAINER_INSTANCE_DEFINITION)
        }

        @Test
        fun `all 6 checked definition types exist`() {
            val definitionTypes = listOf(
                StructurizrTypes.CONTAINER_DEFINITION,
                StructurizrTypes.COMPONENT_DEFINITION,
                StructurizrTypes.DEPLOYMENT_NODE_DEFINITION,
                StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION,
                StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE_DEFINITION,
                StructurizrTypes.CONTAINER_INSTANCE_DEFINITION
            )
            assertEquals(6, definitionTypes.size)
            definitionTypes.forEach { assertNotNull(it) }
        }
    }

    @Nested
    @DisplayName("Parent context types")
    inner class ParentContextTypes {

        @Test
        fun `SOFTWARE_SYSTEM_DEFINITION exists as valid parent for container`() {
            assertNotNull(StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION)
        }

        @Test
        fun `CONTAINER_DEFINITION exists as valid parent for component`() {
            assertNotNull(StructurizrTypes.CONTAINER_DEFINITION)
        }

        @Test
        fun `DEPLOYMENT_NODE_DEFINITION exists as valid parent for instances`() {
            assertNotNull(StructurizrTypes.DEPLOYMENT_NODE_DEFINITION)
        }

        @Test
        fun `DEPLOYMENT_ENVIRONMENT_BLOCK exists as valid parent for deploymentNode`() {
            assertNotNull(StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK)
        }

        @Test
        fun `GROUP_BLOCK exists as intermediate parent`() {
            assertNotNull(StructurizrTypes.GROUP_BLOCK)
        }

        @Test
        fun `MODEL_BLOCK exists in context hierarchy`() {
            assertNotNull(StructurizrTypes.MODEL_BLOCK)
        }

        @Test
        fun `WORKSPACE_BLOCK exists in context hierarchy`() {
            assertNotNull(StructurizrTypes.WORKSPACE_BLOCK)
        }

        @Test
        fun `ELEMENT_ASSIGNMENT exists as valid parent context`() {
            assertNotNull(StructurizrTypes.ELEMENT_ASSIGNMENT)
        }
    }

    @Nested
    @DisplayName("buildVisitor method")
    inner class BuildVisitorTests {

        @Test
        fun `buildVisitor method exists`() {
            val method = InvalidContextInspection::class.java.getMethod(
                "buildVisitor",
                com.intellij.codeInspection.ProblemsHolder::class.java,
                Boolean::class.javaPrimitiveType
            )
            assertNotNull(method)
        }

        @Test
        fun `buildVisitor returns PsiElementVisitor`() {
            val method = InvalidContextInspection::class.java.getMethod(
                "buildVisitor",
                com.intellij.codeInspection.ProblemsHolder::class.java,
                Boolean::class.javaPrimitiveType
            )
            assertEquals(com.intellij.psi.PsiElementVisitor::class.java, method.returnType)
        }
    }
}
