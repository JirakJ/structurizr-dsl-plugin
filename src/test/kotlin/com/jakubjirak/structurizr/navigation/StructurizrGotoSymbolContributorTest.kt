package com.jakubjirak.structurizr.navigation

import com.intellij.navigation.ChooseByNameContributorEx
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrGotoSymbolContributor")
class StructurizrGotoSymbolContributorTest {

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `can be instantiated`() {
            val contributor = StructurizrGotoSymbolContributor()
            assertNotNull(contributor)
        }

        @Test
        fun `implements ChooseByNameContributorEx`() {
            val contributor = StructurizrGotoSymbolContributor()
            assertInstanceOf(ChooseByNameContributorEx::class.java, contributor)
        }

        @Test
        fun `class is final`() {
            assertTrue(java.lang.reflect.Modifier.isFinal(StructurizrGotoSymbolContributor::class.java.modifiers))
        }

        @Test
        fun `can be instantiated multiple times`() {
            val c1 = StructurizrGotoSymbolContributor()
            val c2 = StructurizrGotoSymbolContributor()
            assertNotSame(c1, c2)
        }
    }

    @Nested
    @DisplayName("Methods")
    inner class Methods {

        @Test
        fun `processNames method exists`() {
            val method = StructurizrGotoSymbolContributor::class.java.getMethod(
                "processNames",
                com.intellij.util.Processor::class.java,
                com.intellij.psi.search.GlobalSearchScope::class.java,
                com.intellij.util.indexing.IdFilter::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `processElementsWithName method exists`() {
            val method = StructurizrGotoSymbolContributor::class.java.getMethod(
                "processElementsWithName",
                String::class.java,
                com.intellij.util.Processor::class.java,
                com.intellij.util.indexing.FindSymbolParameters::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `processNames returns void`() {
            val method = StructurizrGotoSymbolContributor::class.java.getMethod(
                "processNames",
                com.intellij.util.Processor::class.java,
                com.intellij.psi.search.GlobalSearchScope::class.java,
                com.intellij.util.indexing.IdFilter::class.java
            )
            assertEquals(Void.TYPE, method.returnType)
        }

        @Test
        fun `processElementsWithName returns void`() {
            val method = StructurizrGotoSymbolContributor::class.java.getMethod(
                "processElementsWithName",
                String::class.java,
                com.intellij.util.Processor::class.java,
                com.intellij.util.indexing.FindSymbolParameters::class.java
            )
            assertEquals(Void.TYPE, method.returnType)
        }
    }

    @Nested
    @DisplayName("NavigationEntry inner class")
    inner class NavigationEntryTests {

        @Test
        fun `NavigationEntry inner class exists`() {
            val innerClasses = StructurizrGotoSymbolContributor::class.java.declaredClasses
            val navEntry = innerClasses.find { it.simpleName == "NavigationEntry" }
            assertNotNull(navEntry, "NavigationEntry inner class should exist")
        }

        @Test
        fun `NavigationEntry has name field`() {
            val innerClasses = StructurizrGotoSymbolContributor::class.java.declaredClasses
            val navEntry = innerClasses.find { it.simpleName == "NavigationEntry" }!!
            val nameField = navEntry.getDeclaredField("name")
            assertNotNull(nameField)
            assertEquals(String::class.java, nameField.type)
        }

        @Test
        fun `NavigationEntry has element field`() {
            val innerClasses = StructurizrGotoSymbolContributor::class.java.declaredClasses
            val navEntry = innerClasses.find { it.simpleName == "NavigationEntry" }!!
            val elementField = navEntry.getDeclaredField("element")
            assertNotNull(elementField)
            assertEquals(com.intellij.psi.PsiElement::class.java, elementField.type)
        }
    }
}
