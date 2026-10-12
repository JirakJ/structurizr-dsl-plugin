/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import com.jakubjirak.structurizr.language.StructurizrFileType
import com.jakubjirak.structurizr.preview.StructurizrPreviewPanel

/**
 * Action that refreshes the Structurizr diagram preview.
 */
class RefreshPreviewAction : AnAction(), DumbAware {

    /** Refreshes the preview for the current editor and file. */
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        StructurizrPreviewPanel.refreshAll(project)
    }

    /** Enables the action only for Structurizr DSL files. */
    override fun update(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE)
        e.presentation.isEnabled = file?.fileType == StructurizrFileType
    }
}
