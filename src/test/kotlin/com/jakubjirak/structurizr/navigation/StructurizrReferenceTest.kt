package com.jakubjirak.structurizr.navigation

import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.PsiReferenceContributor
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Structurizr Reference System")
class StructurizrReferenceTest {

    @Nested
    @DisplayName("StructurizrReferenceContributor")
    inner class ReferenceContributorTests {

        @Test
        fun `can be instantiated`() {
            val contributor = StructurizrReferenceContributor()
            assertNotNull(contributor)
        }

        @Test
        fun `extends PsiReferenceContributor`() {
            val contributor = StructurizrReferenceContributor()
            assertInstanceOf(PsiReferenceContributor::class.java, contributor)
        }

        @Test
        fun `has registerReferenceProviders method`() {
            val method = StructurizrReferenceContributor::class.java.getMethod(
                "registerReferenceProviders",
                com.intellij.psi.PsiReferenceRegistrar::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `class name is correct`() {
            assertEquals("StructurizrReferenceContributor", StructurizrReferenceContributor::class.java.simpleName)
        }
    }

    @Nested
    @DisplayName("StructurizrReference")
    inner class ReferenceTests {

        @Test
        fun `class is accessible`() {
            assertNotNull(StructurizrReference::class.java)
        }

        @Test
        fun `extends PsiReferenceBase`() {
            assertTrue(
                PsiReferenceBase::class.java.isAssignableFrom(StructurizrReference::class.java)
            )
        }

        @Test
        fun `has resolve method`() {
            val method = StructurizrReference::class.java.getMethod("resolve")
            assertNotNull(method)
        }

        @Test
        fun `resolve returns PsiElement`() {
            val method = StructurizrReference::class.java.getMethod("resolve")
            assertEquals(com.intellij.psi.PsiElement::class.java, method.returnType)
        }

        @Test
        fun `has getVariants method`() {
            val method = StructurizrReference::class.java.getMethod("getVariants")
            assertNotNull(method)
        }

        @Test
        fun `getVariants returns Object array`() {
            val method = StructurizrReference::class.java.getMethod("getVariants")
            assertTrue(method.returnType.isArray)
        }

        @Test
        fun `constructor accepts PsiElement and TextRange`() {
            val constructor = StructurizrReference::class.java.getConstructor(
                com.intellij.psi.PsiElement::class.java,
                com.intellij.openapi.util.TextRange::class.java
            )
            assertNotNull(constructor)
        }

        @Test
        fun `has identifierName field`() {
            val field = StructurizrReference::class.java.getDeclaredField("identifierName")
            field.isAccessible = true
            assertNotNull(field)
            assertEquals(String::class.java, field.type)
        }
    }

    @Nested
    @DisplayName("Reference context types")
    inner class ReferenceContextTypes {

        @Test
        fun `ELEMENT_REFERENCE type exists`() {
            assertNotNull(StructurizrTypes.ELEMENT_REFERENCE)
        }

        @Test
        fun `RELATIONSHIP_STATEMENT type exists`() {
            assertNotNull(StructurizrTypes.RELATIONSHIP_STATEMENT)
        }

        @Test
        fun `RELATIONSHIP_IN_DYNAMIC_VIEW type exists`() {
            assertNotNull(StructurizrTypes.RELATIONSHIP_IN_DYNAMIC_VIEW)
        }

        @Test
        fun `SYSTEM_CONTEXT_VIEW type exists`() {
            assertNotNull(StructurizrTypes.SYSTEM_CONTEXT_VIEW)
        }

        @Test
        fun `CONTAINER_VIEW type exists`() {
            assertNotNull(StructurizrTypes.CONTAINER_VIEW)
        }

        @Test
        fun `COMPONENT_VIEW type exists`() {
            assertNotNull(StructurizrTypes.COMPONENT_VIEW)
        }

        @Test
        fun `DEPLOYMENT_VIEW type exists`() {
            assertNotNull(StructurizrTypes.DEPLOYMENT_VIEW)
        }

        @Test
        fun `DYNAMIC_VIEW type exists`() {
            assertNotNull(StructurizrTypes.DYNAMIC_VIEW)
        }

        @Test
        fun `INCLUDE_STATEMENT type exists`() {
            assertNotNull(StructurizrTypes.INCLUDE_STATEMENT)
        }

        @Test
        fun `EXCLUDE_STATEMENT type exists`() {
            assertNotNull(StructurizrTypes.EXCLUDE_STATEMENT)
        }

        @Test
        fun `ELEMENT_SELECTOR type exists`() {
            assertNotNull(StructurizrTypes.ELEMENT_SELECTOR)
        }

        @Test
        fun `ANIMATION_STEP type exists`() {
            assertNotNull(StructurizrTypes.ANIMATION_STEP)
        }

        @Test
        fun `IDENTIFIER type exists`() {
            assertNotNull(StructurizrTypes.IDENTIFIER)
        }

        @Test
        fun `ELEMENT_ASSIGNMENT type exists for resolve target`() {
            assertNotNull(StructurizrTypes.ELEMENT_ASSIGNMENT)
        }

        @Test
        fun `all 12 reference context parent types exist`() {
            val contextTypes = listOf(
                StructurizrTypes.ELEMENT_REFERENCE,
                StructurizrTypes.RELATIONSHIP_STATEMENT,
                StructurizrTypes.RELATIONSHIP_IN_DYNAMIC_VIEW,
                StructurizrTypes.SYSTEM_CONTEXT_VIEW,
                StructurizrTypes.CONTAINER_VIEW,
                StructurizrTypes.COMPONENT_VIEW,
                StructurizrTypes.DEPLOYMENT_VIEW,
                StructurizrTypes.DYNAMIC_VIEW,
                StructurizrTypes.INCLUDE_STATEMENT,
                StructurizrTypes.EXCLUDE_STATEMENT,
                StructurizrTypes.ELEMENT_SELECTOR,
                StructurizrTypes.ANIMATION_STEP
            )
            assertEquals(12, contextTypes.size)
            contextTypes.forEach { assertNotNull(it) }
        }
    }

    @Nested
    @DisplayName("collectAssignments helper")
    inner class CollectAssignmentsTests {

        @Test
        fun `collectAssignments method exists`() {
            val method = StructurizrReference::class.java.getDeclaredMethod(
                "collectAssignments",
                com.intellij.psi.PsiElement::class.java,
                java.util.List::class.java
            )
            method.isAccessible = true
            assertNotNull(method)
        }

        @Test
        fun `collectAssignments is static`() {
            val method = StructurizrReference::class.java.getDeclaredMethod(
                "collectAssignments",
                com.intellij.psi.PsiElement::class.java,
                java.util.List::class.java
            )
            assertTrue(java.lang.reflect.Modifier.isStatic(method.modifiers))
        }
    }
}
