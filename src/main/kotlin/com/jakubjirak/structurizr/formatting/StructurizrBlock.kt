/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Formatting block implementation for Structurizr DSL files.
 *
 * Provides indentation, spacing, and child-block logic for the formatter.
 */
class StructurizrBlock(
    private val node: ASTNode,
    private val wrap: Wrap?,
    private val alignment: Alignment?,
    private val spacingBuilder: SpacingBuilder
) : ASTBlock {

    companion object {
        private val BLOCK_TYPES = setOf(
            StructurizrTypes.WORKSPACE_BLOCK,
            StructurizrTypes.MODEL_BLOCK,
            StructurizrTypes.VIEWS_BLOCK,
            StructurizrTypes.STYLES_BLOCK,
            StructurizrTypes.CONFIGURATION_BLOCK,
            StructurizrTypes.PERSON_DEFINITION,
            StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION,
            StructurizrTypes.CONTAINER_DEFINITION,
            StructurizrTypes.COMPONENT_DEFINITION,
            StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK,
            StructurizrTypes.DEPLOYMENT_NODE_DEFINITION,
            StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION,
            StructurizrTypes.GROUP_BLOCK,
            StructurizrTypes.ENTERPRISE_BLOCK,
            StructurizrTypes.ELEMENT_ASSIGNMENT,
            StructurizrTypes.SYSTEM_LANDSCAPE_VIEW,
            StructurizrTypes.SYSTEM_CONTEXT_VIEW,
            StructurizrTypes.CONTAINER_VIEW,
            StructurizrTypes.COMPONENT_VIEW,
            StructurizrTypes.DEPLOYMENT_VIEW,
            StructurizrTypes.DYNAMIC_VIEW,
            StructurizrTypes.CUSTOM_VIEW,
            StructurizrTypes.FILTERED_VIEW,
            StructurizrTypes.IMAGE_VIEW,
            StructurizrTypes.ELEMENT_STYLE,
            StructurizrTypes.RELATIONSHIP_STYLE,
            StructurizrTypes.ANIMATION_BLOCK,
            StructurizrTypes.PROPERTIES_BLOCK,
            StructurizrTypes.PERSPECTIVES_BLOCK,
            StructurizrTypes.USERS_BLOCK,
            StructurizrTypes.BRANDING_BLOCK,
            StructurizrTypes.TERMINOLOGY_BLOCK
        )
    }

    /** Returns the AST node backing this block. */
    override fun getNode(): ASTNode = node

    /** Returns the text range of the underlying AST node. */
    override fun getTextRange(): TextRange = node.textRange

    /** Returns the wrap setting for this block. */
    override fun getWrap(): Wrap? = wrap

    /** Returns the indent for this block based on its position in the tree. */
    override fun getIndent(): Indent? {
        val type = node.elementType
        val parentType = node.treeParent?.elementType ?: return Indent.getNoneIndent()

        return when {
            type == StructurizrTypes.RBRACE -> Indent.getNoneIndent()
            parentType in BLOCK_TYPES && type != StructurizrTypes.LBRACE -> Indent.getNormalIndent()
            else -> Indent.getNoneIndent()
        }
    }

    /** Returns the alignment for this block. */
    override fun getAlignment(): Alignment? = alignment

    /** Returns the spacing between two adjacent child blocks. */
    override fun getSpacing(child1: Block?, child2: Block): Spacing? =
        spacingBuilder.getSpacing(this, child1, child2)

    /** Builds and returns the list of child sub-blocks. */
    override fun getSubBlocks(): List<Block> {
        val blocks = mutableListOf<Block>()
        var child = node.firstChildNode
        while (child != null) {
            if (child.textRange.length > 0) {
                blocks += StructurizrBlock(child, Wrap.createWrap(WrapType.NONE, false), null, spacingBuilder)
            }
            child = child.treeNext
        }
        return blocks
    }

    /** Returns whether this block is incomplete. */
    override fun isIncomplete(): Boolean = false

    /** Returns whether this block is a leaf (has no children). */
    override fun isLeaf(): Boolean = node.firstChildNode == null

    /** Returns the child attributes for a new child at the given index. */
    override fun getChildAttributes(newChildIndex: Int): ChildAttributes =
        if (node.elementType in BLOCK_TYPES) ChildAttributes(Indent.getNormalIndent(), null)
        else ChildAttributes(Indent.getNoneIndent(), null)
}
