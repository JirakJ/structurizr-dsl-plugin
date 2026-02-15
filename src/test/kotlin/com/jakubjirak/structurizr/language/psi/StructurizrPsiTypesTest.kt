package com.jakubjirak.structurizr.language.psi

import com.intellij.psi.tree.IElementType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrTypes - PSI Element and Token Types")
class StructurizrPsiTypesTest {

    @Nested
    @DisplayName("Element types for model definitions")
    inner class ModelElementTypes {

        @Test
        fun `workspace block element type exists`() {
            assertNotNull(StructurizrTypes.WORKSPACE_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.WORKSPACE_BLOCK)
        }

        @Test
        fun `model block element type exists`() {
            assertNotNull(StructurizrTypes.MODEL_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.MODEL_BLOCK)
        }

        @Test
        fun `views block element type exists`() {
            assertNotNull(StructurizrTypes.VIEWS_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.VIEWS_BLOCK)
        }

        @Test
        fun `person definition element type exists`() {
            assertNotNull(StructurizrTypes.PERSON_DEFINITION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.PERSON_DEFINITION)
        }

        @Test
        fun `software system definition element type exists`() {
            assertNotNull(StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION)
        }

        @Test
        fun `container definition element type exists`() {
            assertNotNull(StructurizrTypes.CONTAINER_DEFINITION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.CONTAINER_DEFINITION)
        }

        @Test
        fun `component definition element type exists`() {
            assertNotNull(StructurizrTypes.COMPONENT_DEFINITION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.COMPONENT_DEFINITION)
        }
    }

    @Nested
    @DisplayName("Element types for views")
    inner class ViewElementTypes {

        @Test
        fun `system context view element type exists`() {
            assertNotNull(StructurizrTypes.SYSTEM_CONTEXT_VIEW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.SYSTEM_CONTEXT_VIEW)
        }

        @Test
        fun `system landscape view element type exists`() {
            assertNotNull(StructurizrTypes.SYSTEM_LANDSCAPE_VIEW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.SYSTEM_LANDSCAPE_VIEW)
        }

        @Test
        fun `container view element type exists`() {
            assertNotNull(StructurizrTypes.CONTAINER_VIEW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.CONTAINER_VIEW)
        }

        @Test
        fun `component view element type exists`() {
            assertNotNull(StructurizrTypes.COMPONENT_VIEW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.COMPONENT_VIEW)
        }

        @Test
        fun `dynamic view element type exists`() {
            assertNotNull(StructurizrTypes.DYNAMIC_VIEW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DYNAMIC_VIEW)
        }

        @Test
        fun `deployment view element type exists`() {
            assertNotNull(StructurizrTypes.DEPLOYMENT_VIEW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DEPLOYMENT_VIEW)
        }

        @Test
        fun `filtered view element type exists`() {
            assertNotNull(StructurizrTypes.FILTERED_VIEW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.FILTERED_VIEW)
        }

        @Test
        fun `custom view element type exists`() {
            assertNotNull(StructurizrTypes.CUSTOM_VIEW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.CUSTOM_VIEW)
        }

        @Test
        fun `image view element type exists`() {
            assertNotNull(StructurizrTypes.IMAGE_VIEW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.IMAGE_VIEW)
        }
    }

    @Nested
    @DisplayName("Element types for deployment")
    inner class DeploymentElementTypes {

        @Test
        fun `deployment environment block exists`() {
            assertNotNull(StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK)
        }

        @Test
        fun `deployment node definition exists`() {
            assertNotNull(StructurizrTypes.DEPLOYMENT_NODE_DEFINITION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DEPLOYMENT_NODE_DEFINITION)
        }

        @Test
        fun `deployment group block exists`() {
            assertNotNull(StructurizrTypes.DEPLOYMENT_GROUP_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DEPLOYMENT_GROUP_BLOCK)
        }

        @Test
        fun `infrastructure node definition exists`() {
            assertNotNull(StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION)
        }

        @Test
        fun `container instance definition exists`() {
            assertNotNull(StructurizrTypes.CONTAINER_INSTANCE_DEFINITION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.CONTAINER_INSTANCE_DEFINITION)
        }

        @Test
        fun `software system instance definition exists`() {
            assertNotNull(StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE_DEFINITION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE_DEFINITION)
        }
    }

    @Nested
    @DisplayName("Element types for styles")
    inner class StyleElementTypes {

        @Test
        fun `styles block exists`() {
            assertNotNull(StructurizrTypes.STYLES_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.STYLES_BLOCK)
        }

        @Test
        fun `element style exists`() {
            assertNotNull(StructurizrTypes.ELEMENT_STYLE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.ELEMENT_STYLE)
        }

        @Test
        fun `relationship style exists`() {
            assertNotNull(StructurizrTypes.RELATIONSHIP_STYLE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.RELATIONSHIP_STYLE)
        }

        @Test
        fun `style property exists`() {
            assertNotNull(StructurizrTypes.STYLE_PROPERTY)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.STYLE_PROPERTY)
        }

        @Test
        fun `style property name exists`() {
            assertNotNull(StructurizrTypes.STYLE_PROPERTY_NAME)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.STYLE_PROPERTY_NAME)
        }

        @Test
        fun `style property value exists`() {
            assertNotNull(StructurizrTypes.STYLE_PROPERTY_VALUE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.STYLE_PROPERTY_VALUE)
        }

        @Test
        fun `shape value exists`() {
            assertNotNull(StructurizrTypes.SHAPE_VALUE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.SHAPE_VALUE)
        }
    }

    @Nested
    @DisplayName("Element types for relationships")
    inner class RelationshipElementTypes {

        @Test
        fun `relationship statement exists`() {
            assertNotNull(StructurizrTypes.RELATIONSHIP_STATEMENT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.RELATIONSHIP_STATEMENT)
        }

        @Test
        fun `relationship description exists`() {
            assertNotNull(StructurizrTypes.RELATIONSHIP_DESCRIPTION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.RELATIONSHIP_DESCRIPTION)
        }

        @Test
        fun `relationship technology exists`() {
            assertNotNull(StructurizrTypes.RELATIONSHIP_TECHNOLOGY)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.RELATIONSHIP_TECHNOLOGY)
        }

        @Test
        fun `relationship tags exists`() {
            assertNotNull(StructurizrTypes.RELATIONSHIP_TAGS)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.RELATIONSHIP_TAGS)
        }

        @Test
        fun `relationship in dynamic view exists`() {
            assertNotNull(StructurizrTypes.RELATIONSHIP_IN_DYNAMIC_VIEW)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.RELATIONSHIP_IN_DYNAMIC_VIEW)
        }
    }

    @Nested
    @DisplayName("Element types for directives")
    inner class DirectiveElementTypes {

        @Test
        fun `include directive element exists`() {
            assertNotNull(StructurizrTypes.INCLUDE_DIRECTIVE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.INCLUDE_DIRECTIVE)
        }

        @Test
        fun `constant directive element exists`() {
            assertNotNull(StructurizrTypes.CONSTANT_DIRECTIVE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.CONSTANT_DIRECTIVE)
        }

        @Test
        fun `identifiers directive element exists`() {
            assertNotNull(StructurizrTypes.IDENTIFIERS_DIRECTIVE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.IDENTIFIERS_DIRECTIVE)
        }

        @Test
        fun `docs directive element exists`() {
            assertNotNull(StructurizrTypes.DOCS_DIRECTIVE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DOCS_DIRECTIVE)
        }

        @Test
        fun `adrs directive element exists`() {
            assertNotNull(StructurizrTypes.ADRS_DIRECTIVE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.ADRS_DIRECTIVE)
        }

        @Test
        fun `implied relationships directive element exists`() {
            assertNotNull(StructurizrTypes.IMPLIED_RELATIONSHIPS_DIRECTIVE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.IMPLIED_RELATIONSHIPS_DIRECTIVE)
        }
    }

    @Nested
    @DisplayName("Other element types")
    inner class OtherElementTypes {

        @Test
        fun `element assignment exists`() {
            assertNotNull(StructurizrTypes.ELEMENT_ASSIGNMENT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.ELEMENT_ASSIGNMENT)
        }

        @Test
        fun `element reference exists`() {
            assertNotNull(StructurizrTypes.ELEMENT_REFERENCE)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.ELEMENT_REFERENCE)
        }

        @Test
        fun `element selector exists`() {
            assertNotNull(StructurizrTypes.ELEMENT_SELECTOR)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.ELEMENT_SELECTOR)
        }

        @Test
        fun `group block exists`() {
            assertNotNull(StructurizrTypes.GROUP_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.GROUP_BLOCK)
        }

        @Test
        fun `enterprise block exists`() {
            assertNotNull(StructurizrTypes.ENTERPRISE_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.ENTERPRISE_BLOCK)
        }

        @Test
        fun `configuration block exists`() {
            assertNotNull(StructurizrTypes.CONFIGURATION_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.CONFIGURATION_BLOCK)
        }

        @Test
        fun `auto layout statement exists`() {
            assertNotNull(StructurizrTypes.AUTO_LAYOUT_STATEMENT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.AUTO_LAYOUT_STATEMENT)
        }

        @Test
        fun `include statement exists`() {
            assertNotNull(StructurizrTypes.INCLUDE_STATEMENT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.INCLUDE_STATEMENT)
        }

        @Test
        fun `exclude statement exists`() {
            assertNotNull(StructurizrTypes.EXCLUDE_STATEMENT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.EXCLUDE_STATEMENT)
        }

        @Test
        fun `workspace name exists`() {
            assertNotNull(StructurizrTypes.WORKSPACE_NAME)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.WORKSPACE_NAME)
        }

        @Test
        fun `workspace description exists`() {
            assertNotNull(StructurizrTypes.WORKSPACE_DESCRIPTION)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.WORKSPACE_DESCRIPTION)
        }

        @Test
        fun `description string exists`() {
            assertNotNull(StructurizrTypes.DESCRIPTION_STRING)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.DESCRIPTION_STRING)
        }

        @Test
        fun `technology string exists`() {
            assertNotNull(StructurizrTypes.TECHNOLOGY_STRING)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.TECHNOLOGY_STRING)
        }

        @Test
        fun `tags statement exists`() {
            assertNotNull(StructurizrTypes.TAGS_STATEMENT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.TAGS_STATEMENT)
        }

        @Test
        fun `themes statement exists`() {
            assertNotNull(StructurizrTypes.THEMES_STATEMENT)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.THEMES_STATEMENT)
        }

        @Test
        fun `properties block exists`() {
            assertNotNull(StructurizrTypes.PROPERTIES_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.PROPERTIES_BLOCK)
        }

        @Test
        fun `animation block exists`() {
            assertNotNull(StructurizrTypes.ANIMATION_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.ANIMATION_BLOCK)
        }

        @Test
        fun `branding block exists`() {
            assertNotNull(StructurizrTypes.BRANDING_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.BRANDING_BLOCK)
        }

        @Test
        fun `terminology block exists`() {
            assertNotNull(StructurizrTypes.TERMINOLOGY_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.TERMINOLOGY_BLOCK)
        }

        @Test
        fun `users block exists`() {
            assertNotNull(StructurizrTypes.USERS_BLOCK)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.USERS_BLOCK)
        }

        @Test
        fun `value literal exists`() {
            assertNotNull(StructurizrTypes.VALUE_LITERAL)
            assertInstanceOf(IElementType::class.java, StructurizrTypes.VALUE_LITERAL)
        }
    }

    @Nested
    @DisplayName("StructurizrTypes Factory class exists")
    inner class FactoryTests {

        @Test
        fun `Factory class is accessible`() {
            val clazz = StructurizrTypes.Factory::class.java
            assertNotNull(clazz)
            assertEquals("Factory", clazz.simpleName)
        }
    }

    @Nested
    @DisplayName("StructurizrElementType class exists")
    inner class ElementTypeTests {

        @Test
        fun `StructurizrElementType class is accessible`() {
            val clazz = StructurizrElementType::class.java
            assertNotNull(clazz)
            assertEquals("StructurizrElementType", clazz.simpleName)
        }
    }
}
