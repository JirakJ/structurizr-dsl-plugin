/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import com.jakubjirak.structurizr.export.DiagramExportFormat
import com.jakubjirak.structurizr.export.QuickExportDialog
import com.jakubjirak.structurizr.export.StructurizrExportService
import com.jakubjirak.structurizr.language.StructurizrFileType

/**
 * Action for quick export of a Structurizr diagram to PNG format.
 */
class QuickExportPngAction :
    AnAction("Export as PNG...", "Quick export to PNG format", null), DumbAware {

    /** Shows the quick export dialog and exports to PNG. */
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

        val dialog = QuickExportDialog(project, file, DiagramExportFormat.PNG)
        if (dialog.showAndGet()) {
            val exportService = StructurizrExportService.getInstance(project)
            exportService.export(file, dialog.getOutputPath(), DiagramExportFormat.PNG, null) { _, _ -> }
        }
    }

    /** Enables the action only for Structurizr DSL files. */
    override fun update(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE)
        e.presentation.isEnabled = file?.fileType == StructurizrFileType
    }
}
