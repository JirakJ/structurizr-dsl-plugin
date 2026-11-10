/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.actions

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.jakubjirak.structurizr.language.StructurizrIcons

/**
 * Action to create a new Structurizr DSL file from a template.
 */
class NewStructurizrFileAction :
    CreateFileFromTemplateAction("Structurizr DSL File", "Create a new Structurizr DSL file", StructurizrIcons.FILE),
    DumbAware {

    /** Builds the file creation dialog with available templates. */
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle("New Structurizr DSL File")
            .addKind("Workspace", StructurizrIcons.WORKSPACE, "Structurizr Workspace")
    }

    /** Returns the action name shown in the undo stack. */
    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String =
        "Create Structurizr DSL file $newName"
}
