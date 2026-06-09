package com.jakubjirak.structurizr.inspections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Structurizr Inspections")
class StructurizrInspectionsTest {

    @Nested
    @DisplayName("UndefinedReferenceInspection")
    inner class UndefinedReferenceTests {

        @Test
        fun `can be instantiated`() {
            val inspection = UndefinedReferenceInspection()
            assertNotNull(inspection)
        }

        @Test
        fun `display name is set`() {
            val inspection = UndefinedReferenceInspection()
            assertEquals("Undefined reference", inspection.displayName)
        }

        @Test
        fun `short name is set`() {
            val inspection = UndefinedReferenceInspection()
            assertEquals("StructurizrUndefinedReference", inspection.shortName)
        }

        @Test
        fun `group display name is Structurizr`() {
            val inspection = UndefinedReferenceInspection()
            assertEquals("Structurizr", inspection.groupDisplayName)
        }

        @Test
        fun `is enabled by default`() {
            val inspection = UndefinedReferenceInspection()
            assertTrue(inspection.isEnabledByDefault)
        }
    }

    @Nested
    @DisplayName("UnusedElementInspection")
    inner class UnusedElementTests {

        @Test
        fun `can be instantiated`() {
            val inspection = UnusedElementInspection()
            assertNotNull(inspection)
        }

        @Test
        fun `display name is set`() {
            val inspection = UnusedElementInspection()
            assertEquals("Unused element", inspection.displayName)
        }

        @Test
        fun `short name is set`() {
            val inspection = UnusedElementInspection()
            assertEquals("StructurizrUnusedElement", inspection.shortName)
        }

        @Test
        fun `group display name is Structurizr`() {
            val inspection = UnusedElementInspection()
            assertEquals("Structurizr", inspection.groupDisplayName)
        }

        @Test
        fun `is enabled by default`() {
            val inspection = UnusedElementInspection()
            assertTrue(inspection.isEnabledByDefault)
        }
    }

    @Nested
    @DisplayName("DuplicateIdentifierInspection")
    inner class DuplicateIdentifierTests {

        @Test
        fun `can be instantiated`() {
            val inspection = DuplicateIdentifierInspection()
            assertNotNull(inspection)
        }

        @Test
        fun `display name is set`() {
            val inspection = DuplicateIdentifierInspection()
            assertEquals("Duplicate identifier", inspection.displayName)
        }

        @Test
        fun `short name is set`() {
            val inspection = DuplicateIdentifierInspection()
            assertEquals("StructurizrDuplicateIdentifier", inspection.shortName)
        }

        @Test
        fun `group display name is Structurizr`() {
            val inspection = DuplicateIdentifierInspection()
            assertEquals("Structurizr", inspection.groupDisplayName)
        }
    }

    @Nested
    @DisplayName("EmptyBlockInspection")
    inner class EmptyBlockTests {

        @Test
        fun `can be instantiated`() {
            val inspection = EmptyBlockInspection()
            assertNotNull(inspection)
        }

        @Test
        fun `display name is set`() {
            val inspection = EmptyBlockInspection()
            assertEquals("Empty block", inspection.displayName)
        }

        @Test
        fun `short name is set`() {
            val inspection = EmptyBlockInspection()
            assertEquals("StructurizrEmptyBlock", inspection.shortName)
        }

        @Test
        fun `group display name is Structurizr`() {
            val inspection = EmptyBlockInspection()
            assertEquals("Structurizr", inspection.groupDisplayName)
        }
    }

    @Nested
    @DisplayName("MissingRequiredPropertyInspection")
    inner class MissingRequiredPropertyTests {

        @Test
        fun `can be instantiated`() {
            val inspection = MissingRequiredPropertyInspection()
            assertNotNull(inspection)
        }

        @Test
        fun `display name is set`() {
            val inspection = MissingRequiredPropertyInspection()
            assertEquals("Missing required property", inspection.displayName)
        }

        @Test
        fun `short name is set`() {
            val inspection = MissingRequiredPropertyInspection()
            assertEquals("StructurizrMissingRequiredProperty", inspection.shortName)
        }

        @Test
        fun `group display name is Structurizr`() {
            val inspection = MissingRequiredPropertyInspection()
            assertEquals("Structurizr", inspection.groupDisplayName)
        }
    }

    @Nested
    @DisplayName("DeprecatedKeywordInspection")
    inner class DeprecatedKeywordTests {

        @Test
        fun `can be instantiated`() {
            val inspection = DeprecatedKeywordInspection()
            assertNotNull(inspection)
        }

        @Test
        fun `display name is set`() {
            val inspection = DeprecatedKeywordInspection()
            assertEquals("Deprecated keyword", inspection.displayName)
        }

        @Test
        fun `short name is set`() {
            val inspection = DeprecatedKeywordInspection()
            assertEquals("StructurizrDeprecatedKeyword", inspection.shortName)
        }

        @Test
        fun `group display name is Structurizr`() {
            val inspection = DeprecatedKeywordInspection()
            assertEquals("Structurizr", inspection.groupDisplayName)
        }
    }

    @Nested
    @DisplayName("InvalidColorInspection")
    inner class InvalidColorTests {

        @Test
        fun `can be instantiated`() {
            val inspection = InvalidColorInspection()
            assertNotNull(inspection)
        }

        @Test
        fun `display name is set`() {
            val inspection = InvalidColorInspection()
            assertEquals("Invalid color value", inspection.displayName)
        }

        @Test
        fun `short name is set`() {
            val inspection = InvalidColorInspection()
            assertEquals("StructurizrInvalidColor", inspection.shortName)
        }

        @Test
        fun `group display name is Structurizr`() {
            val inspection = InvalidColorInspection()
            assertEquals("Structurizr", inspection.groupDisplayName)
        }
    }

    @Nested
    @DisplayName("All 10 inspections exist")
    inner class AllInspectionsExist {

        @Test
        fun `all inspection classes are accessible`() {
            val inspectionClasses = listOf(
                UndefinedReferenceInspection::class.java,
                UnusedElementInspection::class.java,
                DuplicateIdentifierInspection::class.java,
                EmptyBlockInspection::class.java,
                MissingRequiredPropertyInspection::class.java,
                DeprecatedKeywordInspection::class.java,
                InvalidColorInspection::class.java,
                InvalidContextInspection::class.java,
                InvalidRelationshipInspection::class.java,
                DuplicateViewKeyInspection::class.java
            )
            assertEquals(10, inspectionClasses.size)
            inspectionClasses.forEach { assertNotNull(it) }
        }

        @Test
        fun `all inspections extend StructurizrInspectionBase`() {
            val inspections = listOf(
                UndefinedReferenceInspection(),
                UnusedElementInspection(),
                DuplicateIdentifierInspection(),
                EmptyBlockInspection(),
                MissingRequiredPropertyInspection(),
                DeprecatedKeywordInspection(),
                InvalidColorInspection(),
                InvalidContextInspection(),
                InvalidRelationshipInspection(),
                DuplicateViewKeyInspection()
            )
            inspections.forEach {
                assertInstanceOf(StructurizrInspectionBase::class.java, it)
            }
        }

        @Test
        fun `all inspections have unique display names`() {
            val inspections = listOf(
                UndefinedReferenceInspection(),
                UnusedElementInspection(),
                DuplicateIdentifierInspection(),
                EmptyBlockInspection(),
                MissingRequiredPropertyInspection(),
                DeprecatedKeywordInspection(),
                InvalidColorInspection(),
                InvalidContextInspection(),
                InvalidRelationshipInspection(),
                DuplicateViewKeyInspection()
            )
            val names = inspections.map { it.displayName }.toSet()
            assertEquals(10, names.size)
        }

        @Test
        fun `all inspections have unique short names`() {
            val inspections = listOf(
                UndefinedReferenceInspection(),
                UnusedElementInspection(),
                DuplicateIdentifierInspection(),
                EmptyBlockInspection(),
                MissingRequiredPropertyInspection(),
                DeprecatedKeywordInspection(),
                InvalidColorInspection(),
                InvalidContextInspection(),
                InvalidRelationshipInspection(),
                DuplicateViewKeyInspection()
            )
            val names = inspections.map { it.shortName }.toSet()
            assertEquals(10, names.size)
        }

        @Test
        fun `all short names start with Structurizr prefix`() {
            val inspections = listOf(
                UndefinedReferenceInspection(),
                UnusedElementInspection(),
                DuplicateIdentifierInspection(),
                EmptyBlockInspection(),
                MissingRequiredPropertyInspection(),
                DeprecatedKeywordInspection(),
                InvalidColorInspection(),
                InvalidContextInspection(),
                InvalidRelationshipInspection(),
                DuplicateViewKeyInspection()
            )
            inspections.forEach {
                assertTrue(it.shortName.startsWith("Structurizr"), "${it.shortName} does not start with 'Structurizr'")
            }
        }
    }

    @Nested
    @DisplayName("Quick fix classes exist")
    inner class QuickFixTests {

        @Test
        fun `CreateElementQuickFix class is accessible`() {
            assertNotNull(CreateElementQuickFix::class.java)
        }

        @Test
        fun `RemoveUnusedElementQuickFix class is accessible`() {
            assertNotNull(RemoveUnusedElementQuickFix::class.java)
        }

        @Test
        fun `RenameElementQuickFix class is accessible`() {
            assertNotNull(RenameElementQuickFix::class.java)
        }
    }
}
