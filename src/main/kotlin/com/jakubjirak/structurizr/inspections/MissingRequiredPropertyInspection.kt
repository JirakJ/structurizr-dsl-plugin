/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.jakubjirak.structurizr.language.psi.StructurizrComponentDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrContainerDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrPersonDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition

/**
 * Inspection that checks whether required properties (e.g., name) are present on element definitions.
 */
class MissingRequiredPropertyInspection : StructurizrInspectionBase() {

    override fun getDisplayName(): String = "Missing required property"

    override fun getShortName(): String = "StructurizrMissingRequiredProperty"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : PsiElementVisitor() {
            override fun visitElement(element: PsiElement) {
                when (element) {
                    is StructurizrPersonDefinition -> checkDefinition(element, element.string, "Person requires a name", holder)
                    is StructurizrSoftwareSystemDefinition -> checkDefinition(element, element.string, "Software system requires a name", holder)
                    is StructurizrContainerDefinition -> checkDefinition(element, element.string, "Container requires a name", holder)
                    is StructurizrComponentDefinition -> checkDefinition(element, element.string, "Component requires a name", holder)
                }
            }
        }

    /**
     * Checks that the given [stringElement] provides a non-blank name; registers a problem otherwise.
     */
    private fun checkDefinition(
        element: PsiElement,
        stringElement: PsiElement?,
        message: String,
        holder: ProblemsHolder
    ) {
        val name = stringElement?.text?.trim('"')
        if (name.isNullOrEmpty()) {
            holder.registerProblem(
                element.firstChild ?: element,
                message,
                ProblemHighlightType.ERROR
            )
        }
    }
}
