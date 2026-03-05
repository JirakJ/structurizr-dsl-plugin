/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import com.jakubjirak.structurizr.export.ClipboardExportService
import com.jakubjirak.structurizr.language.StructurizrFileType

/**
 * Action that copies the current Structurizr diagram as PlantUML to the clipboard.
 */
class CopyAsPlantUmlAction : AnAction("Copy as PlantUML", "Copy diagram as PlantUML to clipboard", null), DumbAware {

    /** Copies the diagram as PlantUML format to the system clipboard. */
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        ClipboardExportService.getInstance(project).copyAsPlantUml(file)
    }

    /** Enables the action only for Structurizr DSL files. */
    override fun update(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE)
        e.presentation.isEnabled = file?.fileType == StructurizrFileType
    }
}
