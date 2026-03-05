/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Provides code folding support for Structurizr DSL files.
 *
 * Folds brace-delimited blocks and displays context-aware placeholder text
 * (e.g. `workspace {...}`, `model {...}`).
 */
class StructurizrFoldingBuilder : FoldingBuilderEx() {

    /** Builds fold regions for all foldable brace blocks in the PSI tree. */
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        collectFoldRegions(root, descriptors)
        return descriptors.toTypedArray()
    }

    private fun collectFoldRegions(element: PsiElement, descriptors: MutableList<FoldingDescriptor>) {
        var child = element.firstChild
        while (child != null) {
            if (isFoldableBlock(child)) {
                val startOffset = findOpenBrace(child)
                val endOffset = findCloseBrace(child)
                if (startOffset >= 0 && endOffset > startOffset) {
                    descriptors.add(FoldingDescriptor(child.node, TextRange(startOffset, endOffset + 1)))
                }
            }
            collectFoldRegions(child, descriptors)
            child = child.nextSibling
        }
    }

    private fun isFoldableBlock(element: PsiElement): Boolean {
        val text = element.text ?: return false
        return "{" in text && "}" in text
    }

    private fun findOpenBrace(element: PsiElement): Int {
        var child = element.firstChild
        while (child != null) {
            if (child.node.elementType == StructurizrTypes.LBRACE) return child.textOffset
            child = child.nextSibling
        }
        return -1
    }

    private fun findCloseBrace(element: PsiElement): Int {
        var child = element.lastChild
        while (child != null) {
            if (child.node.elementType == StructurizrTypes.RBRACE) return child.textOffset
            child = child.prevSibling
        }
        return -1
    }

    /** Returns a context-aware placeholder for the folded region. */
    override fun getPlaceholderText(node: ASTNode): String {
        val text = node.text ?: return "{...}"
        val firstLine = text.lineSequence().firstOrNull() ?: return "{...}"
        return when {
            "workspace" in firstLine -> "workspace {...}"
            "model" in firstLine -> "model {...}"
            "views" in firstLine -> "views {...}"
            "styles" in firstLine -> "styles {...}"
            "softwareSystem" in firstLine -> "softwareSystem {...}"
            "container" in firstLine -> "container {...}"
            "component" in firstLine -> "component {...}"
            "person" in firstLine -> "person {...}"
            "deploymentEnvironment" in firstLine -> "deploymentEnvironment {...}"
            "deploymentNode" in firstLine -> "deploymentNode {...}"
            "systemContext" in firstLine -> "systemContext {...}"
            "systemLandscape" in firstLine -> "systemLandscape {...}"
            "configuration" in firstLine -> "configuration {...}"
            else -> "{...}"
        }
    }

    /** Fold regions are expanded by default. */
    override fun isCollapsedByDefault(node: ASTNode): Boolean = false
}
