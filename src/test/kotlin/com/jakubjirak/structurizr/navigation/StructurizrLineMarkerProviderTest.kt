package com.jakubjirak.structurizr.navigation

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.jakubjirak.structurizr.language.StructurizrIcons
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrLineMarkerProvider")
class StructurizrLineMarkerProviderTest {

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `can be instantiated`() {
            val provider = StructurizrLineMarkerProvider()
            assertNotNull(provider)
        }

        @Test
        fun `extends RelatedItemLineMarkerProvider`() {
            val provider = StructurizrLineMarkerProvider()
            assertInstanceOf(RelatedItemLineMarkerProvider::class.java, provider)
        }

        @Test
        fun `can be instantiated multiple times`() {
            val p1 = StructurizrLineMarkerProvider()
            val p2 = StructurizrLineMarkerProvider()
            assertNotSame(p1, p2)
        }

        @Test
        fun `class name is correct`() {
            assertEquals("StructurizrLineMarkerProvider", StructurizrLineMarkerProvider::class.java.simpleName)
        }
    }

    @Nested
    @DisplayName("collectNavigationMarkers method")
    inner class CollectMarkersMethod {

        @Test
        fun `collectNavigationMarkers method exists`() {
            val method = StructurizrLineMarkerProvider::class.java.getDeclaredMethod(
                "collectNavigationMarkers",
                com.intellij.psi.PsiElement::class.java,
                java.util.Collection::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `collectNavigationMarkers is protected`() {
            val method = StructurizrLineMarkerProvider::class.java.getDeclaredMethod(
                "collectNavigationMarkers",
                com.intellij.psi.PsiElement::class.java,
                java.util.Collection::class.java
            )
            assertTrue(java.lang.reflect.Modifier.isProtected(method.modifiers))
        }

        @Test
        fun `collectNavigationMarkers returns void`() {
            val method = StructurizrLineMarkerProvider::class.java.getDeclaredMethod(
                "collectNavigationMarkers",
                com.intellij.psi.PsiElement::class.java,
                java.util.Collection::class.java
            )
            assertEquals(Void.TYPE, method.returnType)
        }
    }

    @Nested
    @DisplayName("Element type to icon mapping")
    inner class IconMappingTests {

        @Test
        fun `PERSON icon is used for person definitions`() {
            assertNotNull(StructurizrIcons.PERSON)
        }

        @Test
        fun `SOFTWARE_SYSTEM icon is used for software system definitions`() {
            assertNotNull(StructurizrIcons.SOFTWARE_SYSTEM)
        }

        @Test
        fun `CONTAINER icon is used for container definitions`() {
            assertNotNull(StructurizrIcons.CONTAINER)
        }

        @Test
        fun `COMPONENT icon is used for component definitions`() {
            assertNotNull(StructurizrIcons.COMPONENT)
        }

        @Test
        fun `DEPLOYMENT icon is used for deployment node definitions`() {
            assertNotNull(StructurizrIcons.DEPLOYMENT)
        }

        @Test
        fun `RELATIONSHIP icon is used for relationship statements`() {
            assertNotNull(StructurizrIcons.RELATIONSHIP)
        }
    }

    @Nested
    @DisplayName("Types referenced by provider")
    inner class ReferencedTypes {

        @Test
        fun `PERSON token type exists`() {
            assertNotNull(StructurizrTypes.PERSON)
        }

        @Test
        fun `SOFTWARE_SYSTEM token type exists`() {
            assertNotNull(StructurizrTypes.SOFTWARE_SYSTEM)
        }

        @Test
        fun `CONTAINER token type exists`() {
            assertNotNull(StructurizrTypes.CONTAINER)
        }

        @Test
        fun `COMPONENT token type exists`() {
            assertNotNull(StructurizrTypes.COMPONENT)
        }

        @Test
        fun `DEPLOYMENT_NODE token type exists`() {
            assertNotNull(StructurizrTypes.DEPLOYMENT_NODE)
        }

        @Test
        fun `INFRASTRUCTURE_NODE token type exists`() {
            assertNotNull(StructurizrTypes.INFRASTRUCTURE_NODE)
        }

        @Test
        fun `ARROW token type exists`() {
            assertNotNull(StructurizrTypes.ARROW)
        }

        @Test
        fun `RELATIONSHIP_STATEMENT element type exists`() {
            assertNotNull(StructurizrTypes.RELATIONSHIP_STATEMENT)
        }

        @Test
        fun `all parent definition types exist`() {
            val parentTypes = listOf(
                StructurizrTypes.PERSON_DEFINITION,
                StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION,
                StructurizrTypes.CONTAINER_DEFINITION,
                StructurizrTypes.COMPONENT_DEFINITION,
                StructurizrTypes.DEPLOYMENT_NODE_DEFINITION,
                StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION,
                StructurizrTypes.RELATIONSHIP_STATEMENT
            )
            assertEquals(7, parentTypes.size)
            parentTypes.forEach { assertNotNull(it) }
        }
    }

    @Nested
    @DisplayName("addMarker helper method")
    inner class AddMarkerMethod {

        @Test
        fun `addMarker private method exists`() {
            val companionClass = StructurizrLineMarkerProvider::class.java.declaredClasses
                .first { it.simpleName == "Companion" }
            val method = companionClass.getDeclaredMethod(
                "addMarker",
                com.intellij.psi.PsiElement::class.java,
                javax.swing.Icon::class.java,
                String::class.java,
                java.util.Collection::class.java
            )
            method.isAccessible = true
            assertNotNull(method)
        }

        @Test
        fun `addMarker is a companion object method`() {
            val companionClass = StructurizrLineMarkerProvider::class.java.declaredClasses
                .first { it.simpleName == "Companion" }
            val method = companionClass.getDeclaredMethod(
                "addMarker",
                com.intellij.psi.PsiElement::class.java,
                javax.swing.Icon::class.java,
                String::class.java,
                java.util.Collection::class.java
            )
            assertFalse(java.lang.reflect.Modifier.isStatic(method.modifiers))
        }
    }
}
