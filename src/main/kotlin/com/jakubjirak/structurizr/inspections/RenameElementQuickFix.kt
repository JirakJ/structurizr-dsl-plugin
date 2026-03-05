/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.refactoring.RefactoringFactory

/**
 * Quick fix that initiates an in-place rename refactoring for the duplicated element.
 */
class RenameElementQuickFix : LocalQuickFix {

    override fun getFamilyName(): String = "Rename element"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val element = descriptor.psiElement ?: return
        ApplicationManager.getApplication().invokeLater {
            val editor = FileEditorManager.getInstance(project).selectedTextEditor
            if (editor != null) {
                RefactoringFactory.getInstance(project).createRename(element, null).run()
            }
        }
    }
}
