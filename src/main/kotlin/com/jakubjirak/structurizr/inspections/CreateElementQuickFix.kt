/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil
import com.jakubjirak.structurizr.language.StructurizrFileType
import com.jakubjirak.structurizr.language.psi.StructurizrModelBlock
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition

/**
 * Quick fix that creates a new software system element definition in the model block.
 *
 * @param elementName the name of the element to create
 */
class CreateElementQuickFix(private val elementName: String) : LocalQuickFix {

    override fun getFamilyName(): String = "Create element"

    override fun getName(): String = "Create element '$elementName'"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val file = descriptor.psiElement.containingFile
        val modelBlock = PsiTreeUtil.findChildOfType(file, StructurizrModelBlock::class.java) ?: return

        val dslText = "    $elementName = softwareSystem \"$elementName\"\n"
        val tempFile = PsiFileFactory.getInstance(project).createFileFromText(
            "temp.dsl",
            StructurizrFileType,
            "workspace {\n    model {\n${dslText}    }\n}"
        )

        val newElement = PsiTreeUtil.findChildOfType(tempFile, StructurizrSoftwareSystemDefinition::class.java)
        if (newElement != null) {
            val closingBrace = modelBlock.children.lastOrNull { it.text == "}" }
            if (closingBrace != null) {
                modelBlock.addBefore(newElement.copy(), closingBrace)
            } else {
                modelBlock.add(newElement.copy())
            }
        }
    }
}
