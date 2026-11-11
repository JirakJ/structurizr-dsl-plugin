/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.actions

import com.intellij.ide.actions.RevealFileAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import com.jakubjirak.structurizr.export.DiagramExportFormat
import com.jakubjirak.structurizr.export.QuickExportDialog
import com.jakubjirak.structurizr.export.StructurizrExportService
import com.jakubjirak.structurizr.settings.StructurizrSettings
import com.jakubjirak.structurizr.language.StructurizrFileType

/**
 * Action for quick export of a Structurizr diagram to SVG format.
 */
class QuickExportSvgAction :
    AnAction("Export as SVG...", "Quick export to SVG format", null), DumbAware {

    /** Shows the quick export dialog and exports to SVG. */
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

        val dialog = QuickExportDialog(project, file, DiagramExportFormat.SVG)
        if (dialog.showAndGet()) {
            val exportService = StructurizrExportService.getInstance(project)
            val outputPath = dialog.getOutputPath()
            exportService.export(file, outputPath, DiagramExportFormat.SVG, null) { success, _ ->
                if (success && StructurizrSettings.getInstance().autoOpenExportedFile) RevealFileAction.openFile(outputPath.toFile())
            }
        }
    }

    /** Enables the action only for Structurizr DSL files. */
    override fun update(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE)
        e.presentation.isEnabled = file?.fileType == StructurizrFileType
    }
}
