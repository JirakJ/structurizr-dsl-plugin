/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.refactoring

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Provides refactoring support for Structurizr DSL elements.
 *
 * Enables in-place rename for identifier tokens in element assignments.
 */
class StructurizrRefactoringSupportProvider : RefactoringSupportProvider() {

    /** Returns whether in-place rename is available for the given [element]. */
    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
        if (element.elementType == StructurizrTypes.IDENTIFIER) {
            return element.parent is StructurizrElementAssignment
        }
        return false
    }
}
