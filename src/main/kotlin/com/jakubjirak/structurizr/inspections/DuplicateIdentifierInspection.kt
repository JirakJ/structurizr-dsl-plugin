/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment

/**
 * Inspection that detects duplicate element identifier declarations.
 */
class DuplicateIdentifierInspection : StructurizrInspectionBase() {

    override fun getDisplayName(): String = "Duplicate identifier"

    override fun getShortName(): String = "StructurizrDuplicateIdentifier"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : PsiElementVisitor() {

            /** Lazily computed once per inspected file: identifier text -> assignments, in file order. */
            private var assignmentsByName: Map<String, List<StructurizrElementAssignment>>? = null

            override fun visitElement(element: PsiElement) {
                if (element !is StructurizrElementAssignment) return

                val identifierElement = element.identifier ?: return
                val identifierText = identifierElement.text

                val byName = assignmentsByName
                    ?: PsiTreeUtil.findChildrenOfType(element.containingFile, StructurizrElementAssignment::class.java)
                        .filter { it.identifier != null }
                        .groupBy { it.identifier!!.text }
                        .also { assignmentsByName = it }
                val duplicates = byName[identifierText] ?: return

                if (duplicates.size > 1 && element != duplicates.first()) {
                    holder.registerProblem(
                        identifierElement,
                        "Duplicate identifier: '$identifierText'",
                        ProblemHighlightType.ERROR,
                        RenameElementQuickFix()
                    )
                }
            }
        }
}
