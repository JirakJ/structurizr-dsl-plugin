package com.jakubjirak.structurizr.navigation

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Structurizr Navigation")
class StructurizrNavigationTest {

    @Nested
    @DisplayName("StructurizrFindUsagesProvider")
    inner class FindUsagesProviderTests {

        @Test
        fun `can be instantiated`() {
            val provider = StructurizrFindUsagesProvider()
            assertNotNull(provider)
        }

        @Test
        fun `getWordsScanner returns non-null`() {
            val provider = StructurizrFindUsagesProvider()
            assertNotNull(provider.wordsScanner)
        }

        @Test
        fun `getWordsScanner returns DefaultWordsScanner`() {
            val provider = StructurizrFindUsagesProvider()
            val scanner = provider.wordsScanner
            assertInstanceOf(com.intellij.lang.cacheBuilder.DefaultWordsScanner::class.java, scanner)
        }
    }

    @Nested
    @DisplayName("Navigation classes exist")
    inner class ClassExistence {

        @Test
        fun `StructurizrFindUsagesProvider class is accessible`() {
            assertNotNull(StructurizrFindUsagesProvider::class.java)
        }

        @Test
        fun `StructurizrBreadcrumbsProvider class is accessible`() {
            assertNotNull(StructurizrBreadcrumbsProvider::class.java)
        }

        @Test
        fun `StructurizrStructureViewFactory class is accessible`() {
            assertNotNull(StructurizrStructureViewFactory::class.java)
        }

        @Test
        fun `StructurizrStructureViewModel class is accessible`() {
            assertNotNull(StructurizrStructureViewModel::class.java)
        }

        @Test
        fun `StructurizrStructureViewElement class is accessible`() {
            assertNotNull(StructurizrStructureViewElement::class.java)
        }

        @Test
        fun `StructurizrLineMarkerProvider class is accessible`() {
            assertNotNull(StructurizrLineMarkerProvider::class.java)
        }
    }
}
