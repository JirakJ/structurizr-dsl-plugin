package com.jakubjirak.structurizr.formatting

import com.intellij.formatting.ASTBlock
import com.intellij.formatting.FormattingModelBuilder
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Structurizr Formatting Enhanced")
class StructurizrFormattingTest2 {

    @Nested
    @DisplayName("StructurizrFormattingModelBuilder")
    inner class FormattingModelBuilderTests {

        @Test
        fun `can be instantiated`() {
            val builder = StructurizrFormattingModelBuilder()
            assertNotNull(builder)
        }

        @Test
        fun `implements FormattingModelBuilder`() {
            val builder = StructurizrFormattingModelBuilder()
            assertInstanceOf(FormattingModelBuilder::class.java, builder)
        }

        @Test
        fun `has createModel method`() {
            val method = StructurizrFormattingModelBuilder::class.java.getMethod(
                "createModel",
                com.intellij.formatting.FormattingContext::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `createModel returns FormattingModel`() {
            val method = StructurizrFormattingModelBuilder::class.java.getMethod(
                "createModel",
                com.intellij.formatting.FormattingContext::class.java
            )
            assertEquals(com.intellij.formatting.FormattingModel::class.java, method.returnType)
        }

        @Test
        fun `has private createSpaceBuilder method`() {
            val method = StructurizrFormattingModelBuilder::class.java.getDeclaredMethod(
                "createSpaceBuilder",
                com.intellij.psi.codeStyle.CodeStyleSettings::class.java
            )
            method.isAccessible = true
            assertNotNull(method)
        }

        @Test
        fun `createSpaceBuilder returns SpacingBuilder`() {
            val method = StructurizrFormattingModelBuilder::class.java.getDeclaredMethod(
                "createSpaceBuilder",
                com.intellij.psi.codeStyle.CodeStyleSettings::class.java
            )
            method.isAccessible = true
            assertEquals(com.intellij.formatting.SpacingBuilder::class.java, method.returnType)
        }
    }

    @Nested
    @DisplayName("StructurizrBlock")
    inner class BlockTests {

        @Test
        fun `implements ASTBlock`() {
            assertTrue(ASTBlock::class.java.isAssignableFrom(StructurizrBlock::class.java))
        }

        @Test
        fun `constructor has 4 parameters`() {
            val constructor = StructurizrBlock::class.java.constructors.first()
            assertEquals(4, constructor.parameterCount)
        }

        @Test
        fun `has getNode method`() {
            val method = StructurizrBlock::class.java.getMethod("getNode")
            assertNotNull(method)
        }

        @Test
        fun `has getTextRange method`() {
            val method = StructurizrBlock::class.java.getMethod("getTextRange")
            assertNotNull(method)
        }

        @Test
        fun `has getWrap method`() {
            val method = StructurizrBlock::class.java.getMethod("getWrap")
            assertNotNull(method)
        }

        @Test
        fun `has getIndent method`() {
            val method = StructurizrBlock::class.java.getMethod("getIndent")
            assertNotNull(method)
        }

        @Test
        fun `has getAlignment method`() {
            val method = StructurizrBlock::class.java.getMethod("getAlignment")
            assertNotNull(method)
        }

        @Test
        fun `has getSpacing method`() {
            val method = StructurizrBlock::class.java.getMethod(
                "getSpacing",
                com.intellij.formatting.Block::class.java,
                com.intellij.formatting.Block::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `has getSubBlocks method`() {
            val method = StructurizrBlock::class.java.getMethod("getSubBlocks")
            assertNotNull(method)
        }

        @Test
        fun `has isIncomplete method`() {
            val method = StructurizrBlock::class.java.getMethod("isIncomplete")
            assertNotNull(method)
            assertEquals(Boolean::class.javaPrimitiveType, method.returnType)
        }

        @Test
        fun `has isLeaf method`() {
            val method = StructurizrBlock::class.java.getMethod("isLeaf")
            assertNotNull(method)
            assertEquals(Boolean::class.javaPrimitiveType, method.returnType)
        }

        @Test
        fun `has getChildAttributes method`() {
            val method = StructurizrBlock::class.java.getMethod(
                "getChildAttributes",
                Int::class.javaPrimitiveType
            )
            assertNotNull(method)
            assertEquals(com.intellij.formatting.ChildAttributes::class.java, method.returnType)
        }
    }

    @Nested
    @DisplayName("BLOCK_TYPES set")
    inner class BlockTypesTests {

        @Test
        fun `BLOCK_TYPES static field exists`() {
            val field = StructurizrBlock::class.java.getDeclaredField("BLOCK_TYPES")
            field.isAccessible = true
            assertNotNull(field)
        }

        @Test
        fun `BLOCK_TYPES contains 32 types`() {
            val field = StructurizrBlock::class.java.getDeclaredField("BLOCK_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertEquals(32, types.size)
        }

        @Test
        fun `BLOCK_TYPES contains workspace and model blocks`() {
            val field = StructurizrBlock::class.java.getDeclaredField("BLOCK_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertTrue(types.contains(StructurizrTypes.WORKSPACE_BLOCK))
            assertTrue(types.contains(StructurizrTypes.MODEL_BLOCK))
        }

        @Test
        fun `BLOCK_TYPES contains style-related types`() {
            val field = StructurizrBlock::class.java.getDeclaredField("BLOCK_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertTrue(types.contains(StructurizrTypes.ELEMENT_STYLE))
            assertTrue(types.contains(StructurizrTypes.RELATIONSHIP_STYLE))
            assertTrue(types.contains(StructurizrTypes.STYLES_BLOCK))
        }

        @Test
        fun `BLOCK_TYPES contains animation and properties blocks`() {
            val field = StructurizrBlock::class.java.getDeclaredField("BLOCK_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertTrue(types.contains(StructurizrTypes.ANIMATION_BLOCK))
            assertTrue(types.contains(StructurizrTypes.PROPERTIES_BLOCK))
            assertTrue(types.contains(StructurizrTypes.PERSPECTIVES_BLOCK))
        }

        @Test
        fun `BLOCK_TYPES contains all view types`() {
            val field = StructurizrBlock::class.java.getDeclaredField("BLOCK_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            val viewTypes = listOf(
                StructurizrTypes.SYSTEM_LANDSCAPE_VIEW,
                StructurizrTypes.SYSTEM_CONTEXT_VIEW,
                StructurizrTypes.CONTAINER_VIEW,
                StructurizrTypes.COMPONENT_VIEW,
                StructurizrTypes.DEPLOYMENT_VIEW,
                StructurizrTypes.DYNAMIC_VIEW,
                StructurizrTypes.CUSTOM_VIEW,
                StructurizrTypes.FILTERED_VIEW,
                StructurizrTypes.IMAGE_VIEW
            )
            viewTypes.forEach { type ->
                assertTrue(types.contains(type), "BLOCK_TYPES should contain $type")
            }
        }

        @Test
        fun `BLOCK_TYPES contains branding and terminology blocks`() {
            val field = StructurizrBlock::class.java.getDeclaredField("BLOCK_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertTrue(types.contains(StructurizrTypes.BRANDING_BLOCK))
            assertTrue(types.contains(StructurizrTypes.TERMINOLOGY_BLOCK))
        }

        @Test
        fun `BLOCK_TYPES contains users block`() {
            val field = StructurizrBlock::class.java.getDeclaredField("BLOCK_TYPES")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val types = field.get(null) as Set<*>
            assertTrue(types.contains(StructurizrTypes.USERS_BLOCK))
        }
    }

    @Nested
    @DisplayName("SpacingBuilder rules")
    inner class SpacingRulesTests {

        @Test
        fun `LBRACE type exists for spacing rule`() {
            assertNotNull(StructurizrTypes.LBRACE)
        }

        @Test
        fun `RBRACE type exists for spacing rule`() {
            assertNotNull(StructurizrTypes.RBRACE)
        }

        @Test
        fun `EQUALS type exists for spacing rule`() {
            assertNotNull(StructurizrTypes.EQUALS)
        }

        @Test
        fun `ARROW type exists for spacing rule`() {
            assertNotNull(StructurizrTypes.ARROW)
        }

        @Test
        fun `ARROW_LEFT type exists for spacing rule`() {
            assertNotNull(StructurizrTypes.ARROW_LEFT)
        }

        @Test
        fun `ARROW_BOTH type exists for spacing rule`() {
            assertNotNull(StructurizrTypes.ARROW_BOTH)
        }
    }
}
