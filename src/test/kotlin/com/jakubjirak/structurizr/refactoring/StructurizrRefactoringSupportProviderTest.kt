package com.jakubjirak.structurizr.refactoring

import com.intellij.lang.refactoring.RefactoringSupportProvider
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrRefactoringSupportProvider")
class StructurizrRefactoringSupportProviderTest {

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `can be instantiated`() {
            val provider = StructurizrRefactoringSupportProvider()
            assertNotNull(provider)
        }

        @Test
        fun `extends RefactoringSupportProvider`() {
            val provider = StructurizrRefactoringSupportProvider()
            assertInstanceOf(RefactoringSupportProvider::class.java, provider)
        }

        @Test
        fun `class is final`() {
            assertTrue(java.lang.reflect.Modifier.isFinal(StructurizrRefactoringSupportProvider::class.java.modifiers))
        }
    }

    @Nested
    @DisplayName("Methods")
    inner class Methods {

        @Test
        fun `isMemberInplaceRenameAvailable method exists`() {
            val method = StructurizrRefactoringSupportProvider::class.java.getMethod(
                "isMemberInplaceRenameAvailable",
                com.intellij.psi.PsiElement::class.java,
                com.intellij.psi.PsiElement::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `isMemberInplaceRenameAvailable returns boolean`() {
            val method = StructurizrRefactoringSupportProvider::class.java.getMethod(
                "isMemberInplaceRenameAvailable",
                com.intellij.psi.PsiElement::class.java,
                com.intellij.psi.PsiElement::class.java
            )
            assertEquals(Boolean::class.javaPrimitiveType, method.returnType)
        }

        @Test
        fun `isMemberInplaceRenameAvailable is public`() {
            val method = StructurizrRefactoringSupportProvider::class.java.getMethod(
                "isMemberInplaceRenameAvailable",
                com.intellij.psi.PsiElement::class.java,
                com.intellij.psi.PsiElement::class.java
            )
            assertTrue(java.lang.reflect.Modifier.isPublic(method.modifiers))
        }

        @Test
        fun `can be instantiated multiple times`() {
            val p1 = StructurizrRefactoringSupportProvider()
            val p2 = StructurizrRefactoringSupportProvider()
            assertNotSame(p1, p2)
        }
    }
}
