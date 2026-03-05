/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.jakubjirak.structurizr.language.psi.StructurizrEnterpriseBlock

/**
 * Inspection that flags usage of deprecated Structurizr DSL keywords.
 */
class DeprecatedKeywordInspection : StructurizrInspectionBase() {

    override fun getDisplayName(): String = "Deprecated keyword"

    override fun getShortName(): String = "StructurizrDeprecatedKeyword"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : PsiElementVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is StructurizrEnterpriseBlock) {
                    holder.registerProblem(
                        element.firstChild ?: element,
                        "'enterprise' is deprecated. Use 'group' with enterprise boundary instead.",
                        ProblemHighlightType.LIKE_DEPRECATED
                    )
                }
            }
        }
}
