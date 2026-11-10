/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile
import com.jakubjirak.structurizr.language.psi.StructurizrFile

/**
 * Inspection that detects elements that are defined but never referenced.
 */
class UnusedElementInspection : StructurizrInspectionBase() {

    override fun getDisplayName(): String = "Unused element"

    override fun getShortName(): String = "StructurizrUnusedElement"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : PsiElementVisitor() {
            override fun visitFile(file: PsiFile) {
                if (file !is StructurizrFile) return

                val definedElements = collectDefinedElementsWithPsi(file)
                val usedElements = collectUsedElements(file)

                definedElements.forEach { (name, element) ->
                    if (name !in usedElements) {
                        holder.registerProblem(
                            element,
                            "Unused element: '$name' is defined but never used",
                            ProblemHighlightType.WEAK_WARNING,
                            RemoveUnusedElementQuickFix(name)
                        )
                    }
                }
            }
        }
}
