/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
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
            override fun visitElement(element: PsiElement) {
                if (element !is StructurizrElementAssignment) return

                val identifierElement = element.identifier ?: return
                val identifierText = identifierElement.text
                val file = element.containingFile

                val allAssignments = PsiTreeUtil.findChildrenOfType(file, StructurizrElementAssignment::class.java)
                val duplicates = allAssignments.filter { it.identifier?.text == identifierText }

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
