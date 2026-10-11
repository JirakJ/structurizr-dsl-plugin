/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parents
import com.jakubjirak.structurizr.language.psi.StructurizrComponentDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrContainerDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentNodeDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrInfrastructureNodeDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrPersonDefinition
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition

/**
 * Quick fix that removes an unused element definition and its parent assignment.
 *
 * @param elementName the name of the unused element to remove
 */
class RemoveUnusedElementQuickFix(private val elementName: String) : LocalQuickFix {

    override fun getFamilyName(): String = "Remove unused element"

    override fun getName(): String = "Remove unused element '$elementName'"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val element = descriptor.psiElement ?: return
        findParentDefinition(element)?.delete()
    }

    private fun findParentDefinition(element: PsiElement): PsiElement? =
        element.parents(withSelf = true).firstOrNull { current ->
            current is StructurizrPersonDefinition ||
                current is StructurizrSoftwareSystemDefinition ||
                current is StructurizrContainerDefinition ||
                current is StructurizrComponentDefinition ||
                current is StructurizrDeploymentNodeDefinition ||
                current is StructurizrInfrastructureNodeDefinition
        }
}
