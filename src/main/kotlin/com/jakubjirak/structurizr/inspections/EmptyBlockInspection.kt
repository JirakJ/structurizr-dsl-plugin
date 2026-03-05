/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiWhiteSpace
import com.jakubjirak.structurizr.language.psi.StructurizrModelBlock
import com.jakubjirak.structurizr.language.psi.StructurizrStylesBlock
import com.jakubjirak.structurizr.language.psi.StructurizrViewsBlock

/**
 * Inspection that detects empty model, views, and styles blocks.
 */
class EmptyBlockInspection : StructurizrInspectionBase() {

    override fun getDisplayName(): String = "Empty block"

    override fun getShortName(): String = "StructurizrEmptyBlock"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : PsiElementVisitor() {
            override fun visitElement(element: PsiElement) {
                when (element) {
                    is StructurizrModelBlock -> checkModelBlock(element, holder)
                    is StructurizrViewsBlock -> checkViewsBlock(element, holder)
                    is StructurizrStylesBlock -> checkStylesBlock(element, holder)
                }
            }
        }

    private fun checkModelBlock(block: StructurizrModelBlock, holder: ProblemsHolder) {
        val hasContent = block.personDefinitionList.isNotEmpty() ||
            block.softwareSystemDefinitionList.isNotEmpty() ||
            block.deploymentEnvironmentBlockList.isNotEmpty() ||
            block.groupBlockList.isNotEmpty() ||
            block.relationshipStatementList.isNotEmpty() ||
            block.refStatementList.isNotEmpty()

        if (!hasContent) {
            holder.registerProblem(
                block.firstChild ?: block,
                "Model block is empty",
                ProblemHighlightType.WEAK_WARNING
            )
        }
    }

    private fun checkViewsBlock(block: StructurizrViewsBlock, holder: ProblemsHolder) {
        val ignoredTexts = setOf("{", "}", "views")
        val hasContent = block.children.any { child ->
            child !is PsiWhiteSpace && child !is PsiComment && child.text !in ignoredTexts
        }

        if (!hasContent) {
            holder.registerProblem(
                block.firstChild ?: block,
                "Views block is empty",
                ProblemHighlightType.WEAK_WARNING
            )
        }
    }

    private fun checkStylesBlock(block: StructurizrStylesBlock, holder: ProblemsHolder) {
        val hasContent = block.elementStyleList.isNotEmpty() ||
            block.relationshipStyleList.isNotEmpty()

        if (!hasContent) {
            holder.registerProblem(
                block.firstChild ?: block,
                "Styles block is empty",
                ProblemHighlightType.WEAK_WARNING
            )
        }
    }
}
