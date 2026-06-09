package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.LocalInspectionTool
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("DuplicateViewKeyInspection")
class DuplicateViewKeyInspectionTest {

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `can be instantiated`() {
            assertNotNull(DuplicateViewKeyInspection())
        }

        @Test
        fun `extends StructurizrInspectionBase`() {
            assertInstanceOf(StructurizrInspectionBase::class.java, DuplicateViewKeyInspection())
        }

        @Test
        fun `extends LocalInspectionTool`() {
            assertInstanceOf(LocalInspectionTool::class.java, DuplicateViewKeyInspection())
        }

        @Test
        fun `class is final`() {
            assertTrue(java.lang.reflect.Modifier.isFinal(DuplicateViewKeyInspection::class.java.modifiers))
        }
    }

    @Nested
    @DisplayName("Display properties")
    inner class DisplayProperties {

        @Test
        fun `display name is correct`() {
            assertEquals("Duplicate view key", DuplicateViewKeyInspection().displayName)
        }

        @Test
        fun `short name is correct`() {
            assertEquals("StructurizrDuplicateViewKey", DuplicateViewKeyInspection().shortName)
        }

        @Test
        fun `short name starts with Structurizr prefix`() {
            assertTrue(DuplicateViewKeyInspection().shortName.startsWith("Structurizr"))
        }

        @Test
        fun `group display name is Structurizr`() {
            assertEquals("Structurizr", DuplicateViewKeyInspection().groupDisplayName)
        }

        @Test
        fun `enabled by default`() {
            assertTrue(DuplicateViewKeyInspection().isEnabledByDefault)
        }
    }

    @Nested
    @DisplayName("Key normalization")
    inner class KeyNormalization {

        @Test
        fun `plain key is returned as-is`() {
            assertEquals("SystemContext", DuplicateViewKeyInspection.normalizeKey("SystemContext"))
        }

        @Test
        fun `quoted key has quotes stripped`() {
            assertEquals("SystemContext", DuplicateViewKeyInspection.normalizeKey("\"SystemContext\""))
        }

        @Test
        fun `surrounding whitespace is trimmed`() {
            assertEquals("Containers", DuplicateViewKeyInspection.normalizeKey("  Containers  "))
        }

        @Test
        fun `whitespace inside quotes is trimmed`() {
            assertEquals("Containers", DuplicateViewKeyInspection.normalizeKey("\" Containers \""))
        }

        @Test
        fun `null returns null`() {
            assertNull(DuplicateViewKeyInspection.normalizeKey(null))
        }

        @Test
        fun `empty string returns null`() {
            assertNull(DuplicateViewKeyInspection.normalizeKey(""))
        }

        @Test
        fun `blank string returns null`() {
            assertNull(DuplicateViewKeyInspection.normalizeKey("   "))
        }

        @Test
        fun `empty quoted string returns null`() {
            assertNull(DuplicateViewKeyInspection.normalizeKey("\"\""))
        }

        @Test
        fun `keys differing only in quoting normalize to the same value`() {
            assertEquals(
                DuplicateViewKeyInspection.normalizeKey("Live"),
                DuplicateViewKeyInspection.normalizeKey("\"Live\"")
            )
        }

        @Test
        fun `keys with different case are distinct`() {
            assertNotEquals(
                DuplicateViewKeyInspection.normalizeKey("live"),
                DuplicateViewKeyInspection.normalizeKey("Live")
            )
        }

        @Test
        fun `key with internal spaces is preserved`() {
            assertEquals("My View", DuplicateViewKeyInspection.normalizeKey("\"My View\""))
        }
    }

    @Nested
    @DisplayName("Visitor")
    inner class Visitor {

        @Test
        fun `buildVisitor method exists with correct signature`() {
            val method = DuplicateViewKeyInspection::class.java.getMethod(
                "buildVisitor",
                com.intellij.codeInspection.ProblemsHolder::class.java,
                Boolean::class.javaPrimitiveType
            )
            assertNotNull(method)
        }
    }
}
