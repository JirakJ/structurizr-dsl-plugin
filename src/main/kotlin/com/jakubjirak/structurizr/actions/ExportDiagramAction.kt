/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.actions

import com.intellij.ide.actions.RevealFileAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import com.jakubjirak.structurizr.export.ExportDialog
import com.jakubjirak.structurizr.export.StructurizrExportService
import com.jakubjirak.structurizr.language.StructurizrFileType

/**
 * Action that opens the full export dialog and exports the diagram.
 */
class ExportDiagramAction : AnAction(), DumbAware {

    /** Opens the export dialog and performs the export. */
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

        val dialog = ExportDialog(project, file)
        if (!dialog.showAndGet()) return

        val exportService = StructurizrExportService.getInstance(project)
        val format = dialog.getSelectedFormat()
        val openAfterExport = dialog.isOpenAfterExport()

        if (dialog.isExportAllViews()) {
            val outputDir = dialog.getOutputDirectory()
            exportService.exportAllViews(file, outputDir, format) { success, _ ->
                if (success && openAfterExport) {
                    RevealFileAction.openDirectory(outputDir.toFile())
                }
            }
        } else {
            val outputPath = dialog.getOutputPath()
            val viewKey = dialog.getSelectedViewKey()
            exportService.export(file, outputPath, format, viewKey) { success, _ ->
                if (success && openAfterExport) {
                    RevealFileAction.openFile(outputPath.toFile())
                }
            }
        }
    }

    /** Enables the action only for Structurizr DSL files. */
    override fun update(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE)
        e.presentation.isEnabled = file?.fileType == StructurizrFileType
    }
}
