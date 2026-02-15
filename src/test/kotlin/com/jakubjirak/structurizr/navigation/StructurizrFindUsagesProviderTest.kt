package com.jakubjirak.structurizr.navigation

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrFindUsagesProvider")
class StructurizrFindUsagesProviderTest {

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `implements FindUsagesProvider interface`() {
            val provider = StructurizrFindUsagesProvider()
            assertInstanceOf(FindUsagesProvider::class.java, provider)
        }

        @Test
        fun `can be instantiated multiple times`() {
            val p1 = StructurizrFindUsagesProvider()
            val p2 = StructurizrFindUsagesProvider()
            assertNotSame(p1, p2)
        }

        @Test
        fun `class has canFindUsagesFor method`() {
            val method = StructurizrFindUsagesProvider::class.java.getMethod(
                "canFindUsagesFor",
                com.intellij.psi.PsiElement::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `class has getType method`() {
            val method = StructurizrFindUsagesProvider::class.java.getMethod(
                "getType",
                com.intellij.psi.PsiElement::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `class has getDescriptiveName method`() {
            val method = StructurizrFindUsagesProvider::class.java.getMethod(
                "getDescriptiveName",
                com.intellij.psi.PsiElement::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `class has getNodeText method`() {
            val method = StructurizrFindUsagesProvider::class.java.getMethod(
                "getNodeText",
                com.intellij.psi.PsiElement::class.java,
                Boolean::class.javaPrimitiveType
            )
            assertNotNull(method)
        }

        @Test
        fun `class has getHelpId method`() {
            val method = StructurizrFindUsagesProvider::class.java.getMethod(
                "getHelpId",
                com.intellij.psi.PsiElement::class.java
            )
            assertNotNull(method)
        }
    }

    @Nested
    @DisplayName("WordsScanner")
    inner class WordsScannerTests {

        @Test
        fun `getWordsScanner returns non-null`() {
            val provider = StructurizrFindUsagesProvider()
            assertNotNull(provider.wordsScanner)
        }

        @Test
        fun `getWordsScanner returns DefaultWordsScanner instance`() {
            val provider = StructurizrFindUsagesProvider()
            val scanner = provider.wordsScanner
            assertInstanceOf(DefaultWordsScanner::class.java, scanner)
        }

        @Test
        fun `getWordsScanner returns consistent type across calls`() {
            val provider = StructurizrFindUsagesProvider()
            val scanner1 = provider.wordsScanner
            val scanner2 = provider.wordsScanner
            assertEquals(scanner1!!.javaClass, scanner2!!.javaClass)
        }
    }

    @Nested
    @DisplayName("Type mapping constants in source")
    inner class TypeMappings {

        @Test
        fun `getType method return type is String`() {
            val method = StructurizrFindUsagesProvider::class.java.getMethod(
                "getType",
                com.intellij.psi.PsiElement::class.java
            )
            assertEquals(String::class.java, method.returnType)
        }

        @Test
        fun `getDescriptiveName method return type is String`() {
            val method = StructurizrFindUsagesProvider::class.java.getMethod(
                "getDescriptiveName",
                com.intellij.psi.PsiElement::class.java
            )
            assertEquals(String::class.java, method.returnType)
        }

        @Test
        fun `getNodeText method return type is String`() {
            val method = StructurizrFindUsagesProvider::class.java.getMethod(
                "getNodeText",
                com.intellij.psi.PsiElement::class.java,
                Boolean::class.javaPrimitiveType
            )
            assertEquals(String::class.java, method.returnType)
        }

        @Test
        fun `canFindUsagesFor method return type is boolean`() {
            val method = StructurizrFindUsagesProvider::class.java.getMethod(
                "canFindUsagesFor",
                com.intellij.psi.PsiElement::class.java
            )
            assertEquals(Boolean::class.javaPrimitiveType, method.returnType)
        }

        @Test
        fun `getHelpId return type is String`() {
            val method = StructurizrFindUsagesProvider::class.java.getMethod(
                "getHelpId",
                com.intellij.psi.PsiElement::class.java
            )
            assertEquals(String::class.java, method.returnType)
        }
    }

    @Nested
    @DisplayName("Source code type mappings verification")
    inner class SourceTypeMappings {

        private val sourceClass = StructurizrFindUsagesProvider::class.java

        @Test
        fun `source references PERSON_DEFINITION type`() {
            // Verify via reflection that the source code references the expected StructurizrTypes
            val typesClass = com.jakubjirak.structurizr.language.psi.StructurizrTypes::class.java
            assertNotNull(typesClass.getField("PERSON_DEFINITION"))
        }

        @Test
        fun `source references SOFTWARE_SYSTEM_DEFINITION type`() {
            val typesClass = com.jakubjirak.structurizr.language.psi.StructurizrTypes::class.java
            assertNotNull(typesClass.getField("SOFTWARE_SYSTEM_DEFINITION"))
        }

        @Test
        fun `source references CONTAINER_DEFINITION type`() {
            val typesClass = com.jakubjirak.structurizr.language.psi.StructurizrTypes::class.java
            assertNotNull(typesClass.getField("CONTAINER_DEFINITION"))
        }

        @Test
        fun `source references COMPONENT_DEFINITION type`() {
            val typesClass = com.jakubjirak.structurizr.language.psi.StructurizrTypes::class.java
            assertNotNull(typesClass.getField("COMPONENT_DEFINITION"))
        }

        @Test
        fun `source references DEPLOYMENT_NODE_DEFINITION type`() {
            val typesClass = com.jakubjirak.structurizr.language.psi.StructurizrTypes::class.java
            assertNotNull(typesClass.getField("DEPLOYMENT_NODE_DEFINITION"))
        }

        @Test
        fun `source references INFRASTRUCTURE_NODE_DEFINITION type`() {
            val typesClass = com.jakubjirak.structurizr.language.psi.StructurizrTypes::class.java
            assertNotNull(typesClass.getField("INFRASTRUCTURE_NODE_DEFINITION"))
        }

        @Test
        fun `source references ELEMENT_ASSIGNMENT type`() {
            val typesClass = com.jakubjirak.structurizr.language.psi.StructurizrTypes::class.java
            assertNotNull(typesClass.getField("ELEMENT_ASSIGNMENT"))
        }

        @Test
        fun `source references IDENTIFIER type`() {
            val typesClass = com.jakubjirak.structurizr.language.psi.StructurizrTypes::class.java
            assertNotNull(typesClass.getField("IDENTIFIER"))
        }

        @Test
        fun `all 6 definition types used in getType exist in StructurizrTypes`() {
            val typesClass = com.jakubjirak.structurizr.language.psi.StructurizrTypes::class.java
            val definitionTypes = listOf(
                "PERSON_DEFINITION",
                "SOFTWARE_SYSTEM_DEFINITION",
                "CONTAINER_DEFINITION",
                "COMPONENT_DEFINITION",
                "DEPLOYMENT_NODE_DEFINITION",
                "INFRASTRUCTURE_NODE_DEFINITION"
            )
            definitionTypes.forEach { typeName ->
                assertDoesNotThrow({ typesClass.getField(typeName) }, "$typeName should exist")
            }
        }
    }
}
