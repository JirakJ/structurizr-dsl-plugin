/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.elementType
import com.intellij.psi.util.parents
import com.jakubjirak.structurizr.language.psi.StructurizrComponentDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrContainerDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrContainerInstanceDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentNodeDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrInfrastructureNodeDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemInstanceDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Inspection that verifies elements are defined within the correct parent context
 * (e.g., containers inside software systems, components inside containers).
 */
class InvalidContextInspection : StructurizrInspectionBase() {

    override fun getDisplayName(): String = "Invalid element context"

    override fun getShortName(): String = "StructurizrInvalidContext"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : PsiElementVisitor() {
            override fun visitElement(element: PsiElement) {
                when (element) {
                    is StructurizrContainerDefinition -> checkContainerContext(element, holder)
                    is StructurizrComponentDefinition -> checkComponentContext(element, holder)
                    is StructurizrSoftwareSystemInstanceDefinition -> checkInstanceContext(element, "softwareSystemInstance", holder)
                    is StructurizrContainerInstanceDefinition -> checkInstanceContext(element, "containerInstance", holder)
                    is StructurizrInfrastructureNodeDefinition -> checkInfrastructureNodeContext(element, holder)
                    is StructurizrDeploymentNodeDefinition -> checkDeploymentNodeContext(element, holder)
                }
            }
        }

    companion object {
        private val MEANINGFUL_PARENT_TYPES = setOf(
            StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION,
            StructurizrTypes.CONTAINER_DEFINITION,
            StructurizrTypes.COMPONENT_DEFINITION,
            StructurizrTypes.DEPLOYMENT_NODE_DEFINITION,
            StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK,
            StructurizrTypes.GROUP_BLOCK,
            StructurizrTypes.MODEL_BLOCK,
            StructurizrTypes.WORKSPACE_BLOCK,
            StructurizrTypes.ELEMENT_ASSIGNMENT
        )

        private fun checkContainerContext(element: PsiElement, holder: ProblemsHolder) {
            val parent = findMeaningfulParent(element) ?: return
            val parentType = parent.elementType

            if (parentType == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION ||
                parentType == StructurizrTypes.ELEMENT_ASSIGNMENT
            ) return

            if (parentType == StructurizrTypes.GROUP_BLOCK) {
                if (isAncestorOfType(parent, StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION)) return
            }

            holder.registerProblem(
                element.firstChild ?: element,
                "container can only be defined inside softwareSystem",
                ProblemHighlightType.ERROR,
                *LocalQuickFix.EMPTY_ARRAY
            )
        }

        private fun checkComponentContext(element: PsiElement, holder: ProblemsHolder) {
            val parent = findMeaningfulParent(element) ?: return
            val parentType = parent.elementType

            if (parentType == StructurizrTypes.CONTAINER_DEFINITION ||
                parentType == StructurizrTypes.ELEMENT_ASSIGNMENT
            ) return

            if (parentType == StructurizrTypes.GROUP_BLOCK) {
                if (isAncestorOfType(parent, StructurizrTypes.CONTAINER_DEFINITION)) return
            }

            holder.registerProblem(
                element.firstChild ?: element,
                "component can only be defined inside container",
                ProblemHighlightType.ERROR,
                *LocalQuickFix.EMPTY_ARRAY
            )
        }

        private fun checkInstanceContext(element: PsiElement, keyword: String, holder: ProblemsHolder) {
            if (!isAncestorOfType(element, StructurizrTypes.DEPLOYMENT_NODE_DEFINITION)) {
                holder.registerProblem(
                    element.firstChild ?: element,
                    "$keyword can only be defined inside deploymentNode",
                    ProblemHighlightType.ERROR,
                    *LocalQuickFix.EMPTY_ARRAY
                )
            }
        }

        private fun checkInfrastructureNodeContext(element: PsiElement, holder: ProblemsHolder) {
            if (!isAncestorOfType(element, StructurizrTypes.DEPLOYMENT_NODE_DEFINITION)) {
                holder.registerProblem(
                    element.firstChild ?: element,
                    "infrastructureNode can only be defined inside deploymentNode",
                    ProblemHighlightType.ERROR,
                    *LocalQuickFix.EMPTY_ARRAY
                )
            }
        }

        private fun checkDeploymentNodeContext(element: PsiElement, holder: ProblemsHolder) {
            if (isAncestorOfType(element, StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK) ||
                isAncestorOfType(element, StructurizrTypes.DEPLOYMENT_NODE_DEFINITION)
            ) return

            holder.registerProblem(
                element.firstChild ?: element,
                "deploymentNode can only be defined inside deploymentEnvironment or another deploymentNode",
                ProblemHighlightType.ERROR,
                *LocalQuickFix.EMPTY_ARRAY
            )
        }

        private fun findMeaningfulParent(element: PsiElement): PsiElement? {
            return element.parents(withSelf = false).firstOrNull { it.elementType in MEANINGFUL_PARENT_TYPES }
        }

        private fun isAncestorOfType(element: PsiElement, type: IElementType): Boolean {
            return element.parents(withSelf = false).any { it.elementType == type }
        }
    }
}
