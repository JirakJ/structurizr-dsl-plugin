/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference
import com.jakubjirak.structurizr.language.psi.StructurizrElementSelector
import com.jakubjirak.structurizr.language.psi.StructurizrFile

/**
 * Inspection that detects references to undefined Structurizr elements.
 */
class UndefinedReferenceInspection : StructurizrInspectionBase() {

    override fun getDisplayName(): String = "Undefined reference"

    override fun getShortName(): String = "StructurizrUndefinedReference"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : PsiElementVisitor() {
            override fun visitElement(element: com.intellij.psi.PsiElement) {
                if (element.containingFile !is StructurizrFile) return

                when (element) {
                    is StructurizrElementReference -> checkElementReference(element, holder)
                    is StructurizrElementSelector -> checkElementSelector(element, holder)
                }
            }
        }

    private fun checkElementReference(element: StructurizrElementReference, holder: ProblemsHolder) {
        val text = element.text ?: return
        if (text.isEmpty() || isBuiltInKeyword(text)) return

        val file = element.containingFile ?: return
        val definedElements = collectDefinedElements(file)
        val rootIdentifier = text.split(".").first()

        if (rootIdentifier !in definedElements) {
            holder.registerProblem(
                element,
                "Undefined reference: '$rootIdentifier'",
                ProblemHighlightType.ERROR,
                CreateElementQuickFix(rootIdentifier)
            )
        }
    }

    private fun checkElementSelector(element: StructurizrElementSelector, holder: ProblemsHolder) {
        val text = element.text.trim()
        if (text == "*" || text.startsWith("->") || text.contains("->") || text.isEmpty()) return

        val file = element.containingFile ?: return
        val definedElements = collectDefinedElements(file)
        val identifier = text.split(".").first()

        if (identifier !in definedElements && !isBuiltInKeyword(identifier)) {
            holder.registerProblem(
                element,
                "Undefined reference: '$identifier'",
                ProblemHighlightType.WARNING
            )
        }
    }

    private fun isBuiltInKeyword(text: String): Boolean = text in BUILT_IN_KEYWORDS

    companion object {
        private val BUILT_IN_KEYWORDS = setOf(
            "this", "source", "destination", "*", "element", "relationship",
            "->", "person", "softwareSystem", "container", "component",
            "deploymentNode", "infrastructureNode", "include", "exclude", "autoLayout"
        )
    }
}
