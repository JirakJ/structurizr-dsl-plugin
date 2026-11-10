/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.navigation

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.util.elementType
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Reference from an identifier usage to its element assignment declaration.
 *
 * Resolves identifiers used in relationship statements, views, and other
 * reference contexts to the corresponding element assignment.
 */
class StructurizrReference(
    element: PsiElement,
    rangeInElement: TextRange,
) : PsiReferenceBase<PsiElement>(element, rangeInElement) {

    private val identifierName: String = element.text

    /** Resolves this reference to the declaring element assignment, or `null`. */
    override fun resolve(): PsiElement? {
        val file = myElement.containingFile ?: return null
        val assignments = mutableListOf<PsiElement>()
        collectAssignments(file, assignments)
        return assignments.firstOrNull { assignment ->
            assignment.node.findChildByType(StructurizrTypes.IDENTIFIER)?.text == identifierName
        }?.node?.findChildByType(StructurizrTypes.IDENTIFIER)?.psi
    }

    /** Returns an empty array — no completion variants are provided. */
    override fun getVariants(): Array<Any> = emptyArray()

    companion object {
        private fun collectAssignments(element: PsiElement, result: MutableList<PsiElement>) {
            for (child in element.children) {
                if (child.elementType == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                    result.add(child)
                }
                collectAssignments(child, result)
            }
        }
    }
}
