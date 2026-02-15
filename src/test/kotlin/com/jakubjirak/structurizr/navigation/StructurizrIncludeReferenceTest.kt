package com.jakubjirak.structurizr.navigation

import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.PsiReferenceContributor
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrIncludeReference")
class StructurizrIncludeReferenceTest {

    @Nested
    @DisplayName("StructurizrIncludeReferenceContributor")
    inner class ContributorTests {

        @Test
        fun `can be instantiated`() {
            val contributor = StructurizrIncludeReferenceContributor()
            assertNotNull(contributor)
        }

        @Test
        fun `extends PsiReferenceContributor`() {
            val contributor = StructurizrIncludeReferenceContributor()
            assertInstanceOf(PsiReferenceContributor::class.java, contributor)
        }

        @Test
        fun `has registerReferenceProviders method`() {
            val method = StructurizrIncludeReferenceContributor::class.java.getMethod(
                "registerReferenceProviders",
                com.intellij.psi.PsiReferenceRegistrar::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `registerReferenceProviders returns void`() {
            val method = StructurizrIncludeReferenceContributor::class.java.getMethod(
                "registerReferenceProviders",
                com.intellij.psi.PsiReferenceRegistrar::class.java
            )
            assertEquals(Void.TYPE, method.returnType)
        }
    }

    @Nested
    @DisplayName("StructurizrIncludeReference class")
    inner class ReferenceClassTests {

        @Test
        fun `class exists`() {
            assertNotNull(StructurizrIncludeReference::class.java)
        }

        @Test
        fun `extends PsiReferenceBase`() {
            assertTrue(PsiReferenceBase::class.java.isAssignableFrom(StructurizrIncludeReference::class.java))
        }

        @Test
        fun `has resolve method`() {
            val method = StructurizrIncludeReference::class.java.getMethod("resolve")
            assertNotNull(method)
        }

        @Test
        fun `resolve returns PsiElement`() {
            val method = StructurizrIncludeReference::class.java.getMethod("resolve")
            assertEquals(com.intellij.psi.PsiElement::class.java, method.returnType)
        }

        @Test
        fun `has getVariants method`() {
            val method = StructurizrIncludeReference::class.java.getMethod("getVariants")
            assertNotNull(method)
        }

        @Test
        fun `getVariants returns Object array`() {
            val method = StructurizrIncludeReference::class.java.getMethod("getVariants")
            assertTrue(method.returnType.isArray)
        }

        @Test
        fun `has filePath field`() {
            val field = StructurizrIncludeReference::class.java.getDeclaredField("filePath")
            field.isAccessible = true
            assertNotNull(field)
            assertEquals(String::class.java, field.type)
        }

        @Test
        fun `constructor requires PsiElement and TextRange`() {
            val constructors = StructurizrIncludeReference::class.java.constructors
            val constructor = constructors.find { it.parameterCount == 2 }
            assertNotNull(constructor, "Should have a 2-parameter constructor")
            val paramTypes = constructor!!.parameterTypes
            assertEquals(com.intellij.psi.PsiElement::class.java, paramTypes[0])
            assertEquals(com.intellij.openapi.util.TextRange::class.java, paramTypes[1])
        }
    }
}
