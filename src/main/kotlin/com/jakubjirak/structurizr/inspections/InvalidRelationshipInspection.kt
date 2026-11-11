/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.elementType
import com.intellij.psi.util.parents
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Inspection that validates relationship statements for correctness, including
 * self-references, undefined sources/destinations, and misuse of 'this'.
 */
class InvalidRelationshipInspection : StructurizrInspectionBase() {

    override fun getDisplayName(): String = "Invalid relationship"

    override fun getShortName(): String = "StructurizrInvalidRelationship"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : PsiElementVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element !is StructurizrRelationshipStatement) return

                val refs = element.elementReferenceList
                if (refs.size < 2) return

                val file = element.containingFile ?: return
                val definedElements = collectDefinedElements(file)

                val sourceRef = refs[0]
                val destRef = refs[1]
                val sourceText = sourceRef.text ?: return
                val destText = destRef.text ?: return

                // Check 'this' used outside element scope
                if (sourceText == "this" || destText == "this") {
                    if (!isInsideElementScope(element)) {
                        val target = if (sourceText == "this") sourceRef else destRef
                        holder.registerProblem(
                            target,
                            "'this' can only be used inside an element definition",
                            ProblemHighlightType.ERROR,
                        )
                        return
                    }
                }

                // Check self-referencing relationship
                if (sourceText == destText) {
                    holder.registerProblem(
                        element,
                        "Self-referencing relationship: source and destination are the same element '$sourceText'",
                        ProblemHighlightType.WARNING,
                    )
                    return
                }

                // Check that source and destination resolve to defined elements
                val sourceRoot = getRootIdentifier(sourceText)
                val destRoot = getRootIdentifier(destText)

                if (!isBuiltIn(sourceRoot) && sourceRoot !in definedElements) {
                    holder.registerProblem(
                        sourceRef,
                        "Undefined relationship source: '$sourceRoot'",
                        ProblemHighlightType.ERROR,
                    )
                }
                if (!isBuiltIn(destRoot) && destRoot !in definedElements) {
                    holder.registerProblem(
                        destRef,
                        "Undefined relationship destination: '$destRoot'",
                        ProblemHighlightType.ERROR,
                    )
                }
            }
        }

    companion object {
        private val ELEMENT_SCOPE_TYPES = setOf(
            StructurizrTypes.PERSON_DEFINITION,
            StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION,
            StructurizrTypes.CONTAINER_DEFINITION,
            StructurizrTypes.COMPONENT_DEFINITION,
            StructurizrTypes.DEPLOYMENT_NODE_DEFINITION,
            StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION,
            StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE_DEFINITION,
            StructurizrTypes.CONTAINER_INSTANCE_DEFINITION
        )

        private val BUILT_IN_IDENTIFIERS = setOf("this", "source", "destination")

        private fun isInsideElementScope(element: PsiElement): Boolean {
            return element.parents(withSelf = false).any { it.elementType in ELEMENT_SCOPE_TYPES }
        }

        private fun getRootIdentifier(text: String): String {
            val dotIndex = text.indexOf('.')
            return if (dotIndex >= 0) text.substring(0, dotIndex) else text
        }

        private fun isBuiltIn(text: String): Boolean = text in BUILT_IN_IDENTIFIERS
    }
}
