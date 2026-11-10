/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
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
 * Action for quick export of a Structurizr diagram to PlantUML format.
 */
class QuickExportPlantUmlAction :
    AnAction("Export as PlantUML...", "Quick export to PlantUML format", null), DumbAware {

    /** Shows the quick export dialog and exports to PlantUML. */
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

        val dialog = QuickExportDialog(project, file, DiagramExportFormat.PLANTUML)
        if (dialog.showAndGet()) {
            val exportService = StructurizrExportService.getInstance(project)
            exportService.export(file, dialog.getOutputPath(), DiagramExportFormat.PLANTUML, null) { _, _ -> }
        }
    }

    /** Enables the action only for Structurizr DSL files. */
    override fun update(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE)
        e.presentation.isEnabled = file?.fileType == StructurizrFileType
    }
}
