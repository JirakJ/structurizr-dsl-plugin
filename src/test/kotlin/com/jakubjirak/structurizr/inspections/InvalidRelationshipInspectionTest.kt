package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.LocalInspectionTool
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("InvalidRelationshipInspection")
class InvalidRelationshipInspectionTest {

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `can be instantiated`() {
            val inspection = InvalidRelationshipInspection()
            assertNotNull(inspection)
        }

        @Test
        fun `extends StructurizrInspectionBase`() {
            val inspection = InvalidRelationshipInspection()
            assertInstanceOf(StructurizrInspectionBase::class.java, inspection)
        }

        @Test
        fun `extends LocalInspectionTool`() {
            val inspection = InvalidRelationshipInspection()
            assertInstanceOf(LocalInspectionTool::class.java, inspection)
        }

        @Test
        fun `class is final`() {
            assertTrue(java.lang.reflect.Modifier.isFinal(InvalidRelationshipInspection::class.java.modifiers))
        }
    }

    @Nested
    @DisplayName("Display properties")
    inner class DisplayProperties {

        @Test
        fun `display name is not null`() {
            val inspection = InvalidRelationshipInspection()
            assertNotNull(inspection.displayName)
        }

        @Test
        fun `display name is not empty`() {
            val inspection = InvalidRelationshipInspection()
            assertTrue(inspection.displayName.isNotEmpty())
        }

        @Test
        fun `display name is correct`() {
            val inspection = InvalidRelationshipInspection()
            assertEquals("Invalid relationship", inspection.displayName)
        }

        @Test
        fun `group display name is Structurizr`() {
            val inspection = InvalidRelationshipInspection()
            assertEquals("Structurizr", inspection.groupDisplayName)
        }

        @Test
        fun `short name is not null`() {
            val inspection = InvalidRelationshipInspection()
            assertNotNull(inspection.shortName)
        }

        @Test
        fun `short name is not empty`() {
            val inspection = InvalidRelationshipInspection()
            assertTrue(inspection.shortName.isNotEmpty())
        }

        @Test
        fun `short name is correct`() {
            val inspection = InvalidRelationshipInspection()
            assertEquals("StructurizrInvalidRelationship", inspection.shortName)
        }

        @Test
        fun `is enabled by default`() {
            val inspection = InvalidRelationshipInspection()
            assertTrue(inspection.isEnabledByDefault)
        }
    }

    @Nested
    @DisplayName("Internal methods via reflection")
    inner class InternalMethods {

        private val companionObj: Any = InvalidRelationshipInspection::class.java
            .getDeclaredField("Companion").get(null)
        private val companionClass: Class<*> = companionObj.javaClass

        @Test
        fun `isInsideElementScope method exists`() {
            val method = companionClass.getDeclaredMethod(
                "isInsideElementScope",
                com.intellij.psi.PsiElement::class.java
            )
            method.isAccessible = true
            assertNotNull(method)
        }

        @Test
        fun `getRootIdentifier method exists`() {
            val method = companionClass.getDeclaredMethod(
                "getRootIdentifier",
                String::class.java
            )
            method.isAccessible = true
            assertNotNull(method)
        }

        @Test
        fun `getRootIdentifier extracts root from dotted path`() {
            val method = companionClass.getDeclaredMethod(
                "getRootIdentifier",
                String::class.java
            )
            method.isAccessible = true
            assertEquals("system", method.invoke(companionObj, "system.container"))
        }

        @Test
        fun `getRootIdentifier returns full string when no dot`() {
            val method = companionClass.getDeclaredMethod(
                "getRootIdentifier",
                String::class.java
            )
            method.isAccessible = true
            assertEquals("mySystem", method.invoke(companionObj, "mySystem"))
        }

        @Test
        fun `getRootIdentifier returns empty for empty string`() {
            val method = companionClass.getDeclaredMethod(
                "getRootIdentifier",
                String::class.java
            )
            method.isAccessible = true
            assertEquals("", method.invoke(companionObj, ""))
        }

        @Test
        fun `isBuiltIn method exists`() {
            val method = companionClass.getDeclaredMethod(
                "isBuiltIn",
                String::class.java
            )
            method.isAccessible = true
            assertNotNull(method)
        }

        @Test
        fun `isBuiltIn returns true for this`() {
            val method = companionClass.getDeclaredMethod(
                "isBuiltIn",
                String::class.java
            )
            method.isAccessible = true
            assertTrue(method.invoke(companionObj, "this") as Boolean)
        }

        @Test
        fun `isBuiltIn returns true for source`() {
            val method = companionClass.getDeclaredMethod(
                "isBuiltIn",
                String::class.java
            )
            method.isAccessible = true
            assertTrue(method.invoke(companionObj, "source") as Boolean)
        }

        @Test
        fun `isBuiltIn returns true for destination`() {
            val method = companionClass.getDeclaredMethod(
                "isBuiltIn",
                String::class.java
            )
            method.isAccessible = true
            assertTrue(method.invoke(companionObj, "destination") as Boolean)
        }

        @Test
        fun `isBuiltIn returns false for regular identifier`() {
            val method = companionClass.getDeclaredMethod(
                "isBuiltIn",
                String::class.java
            )
            method.isAccessible = true
            assertFalse(method.invoke(companionObj, "mySystem") as Boolean)
        }
    }

    @Nested
    @DisplayName("buildVisitor method")
    inner class BuildVisitorTests {

        @Test
        fun `buildVisitor method exists`() {
            val method = InvalidRelationshipInspection::class.java.getMethod(
                "buildVisitor",
                com.intellij.codeInspection.ProblemsHolder::class.java,
                Boolean::class.javaPrimitiveType
            )
            assertNotNull(method)
        }

        @Test
        fun `buildVisitor returns PsiElementVisitor`() {
            val method = InvalidRelationshipInspection::class.java.getMethod(
                "buildVisitor",
                com.intellij.codeInspection.ProblemsHolder::class.java,
                Boolean::class.javaPrimitiveType
            )
            assertEquals(com.intellij.psi.PsiElementVisitor::class.java, method.returnType)
        }
    }
}
