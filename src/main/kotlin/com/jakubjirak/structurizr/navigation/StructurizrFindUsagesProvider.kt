/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.navigation

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.util.elementType
import com.jakubjirak.structurizr.language.lexer.StructurizrLexerAdapter
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Provides Find Usages support for Structurizr DSL identifiers.
 *
 * Enables navigating between element assignments and their references
 * throughout the project.
 */
class StructurizrFindUsagesProvider : FindUsagesProvider {

    /** Returns a words scanner configured for Structurizr DSL tokens. */
    override fun getWordsScanner(): WordsScanner {
        val identifierSet = TokenSet.create(StructurizrTypes.IDENTIFIER)
        val commentSet = TokenSet.create(StructurizrTypes.LINE_COMMENT, StructurizrTypes.BLOCK_COMMENT)
        val stringSet = TokenSet.create(StructurizrTypes.STRING)
        return DefaultWordsScanner(StructurizrLexerAdapter(), identifierSet, commentSet, stringSet)
    }

    /** Returns `true` if Find Usages can be invoked on the given [psiElement]. */
    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        if (psiElement.elementType != StructurizrTypes.IDENTIFIER) return false
        val parent = psiElement.parent ?: return false
        return parent.elementType == StructurizrTypes.ELEMENT_ASSIGNMENT
    }

    /** Returns the help topic ID for the given [psiElement], or `null`. */
    override fun getHelpId(psiElement: PsiElement): String? = null

    /** Returns a human-readable type description for the given [element]. */
    override fun getType(element: PsiElement): String {
        val parent = element.parent ?: return "element"
        if (parent.elementType != StructurizrTypes.ELEMENT_ASSIGNMENT) return "element"

        for (child in parent.children) {
            val childType = child.elementType
            when (childType) {
                StructurizrTypes.PERSON_DEFINITION -> return "person"
                StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION -> return "software system"
                StructurizrTypes.CONTAINER_DEFINITION -> return "container"
                StructurizrTypes.COMPONENT_DEFINITION -> return "component"
                StructurizrTypes.DEPLOYMENT_NODE_DEFINITION -> return "deployment node"
                StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION -> return "infrastructure node"
            }
        }
        return "element"
    }

    /** Returns a descriptive name for the given [element]. */
    override fun getDescriptiveName(element: PsiElement): String {
        if (element.elementType == StructurizrTypes.IDENTIFIER) {
            return element.text ?: ""
        }
        return element.text
    }

    /** Returns the display text for the given [element] in Find Usages results. */
    override fun getNodeText(element: PsiElement, useFullName: Boolean): String = element.text
}
