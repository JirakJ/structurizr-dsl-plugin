/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.settings

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.JBIntSpinner
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.panel
import javax.swing.JPanel

/**
 * UI component for project-level Structurizr DSL settings.
 *
 * Renders fields for CLI path override, workspace file selection,
 * export directory, and preview delay using the IntelliJ Kotlin UI DSL.
 */
class StructurizrProjectSettingsComponent(private val project: Project) {

    private val useProjectCliPathCheckbox = JBCheckBox("Use project-specific CLI path")

    private val projectCliPathField = TextFieldWithBrowseButton().apply {
        addActionListener {
            val descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor("jar")
                .withTitle("Select Structurizr CLI")
                .withDescription("Select the Structurizr CLI executable for this project")
            FileChooser.chooseFile(descriptor, project, null) { file ->
                text = file.path
            }
        }
    }

    private val defaultWorkspaceField = TextFieldWithBrowseButton().apply {
        addActionListener {
            val descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor("dsl")
                .withTitle("Select Default Workspace")
                .withDescription("Select the default workspace DSL file")
            FileChooser.chooseFile(descriptor, project, null) { file ->
                text = file.path
            }
        }
    }

    private val projectExportDirectoryField = TextFieldWithBrowseButton().apply {
        addActionListener {
            val descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
                .withTitle("Select Export Directory")
                .withDescription("Select the export directory for this project")
            FileChooser.chooseFile(descriptor, project, null) { file ->
                text = file.path
            }
        }
    }

    private val overridePreviewCheckbox = JBCheckBox("Override global preview settings")
    private val projectPreviewDelaySpinner = JBIntSpinner(500, 100, 5000, 100)

    /** The root Swing panel rendered in the project settings dialog. */
    val panel: JPanel = panel {
        group("CLI Configuration") {
            row("") { cell(useProjectCliPathCheckbox) }
            row("Project CLI path:") { cell(projectCliPathField).align(Align.FILL) }
        }
        group("Project Workspace") {
            row("Default workspace file:") {
                cell(defaultWorkspaceField).align(Align.FILL)
                    .comment("The default .dsl file to use for this project")
            }
        }
        group("Export Configuration") {
            row("Export directory:") {
                cell(projectExportDirectoryField).align(Align.FILL)
                    .comment("Leave empty to use global setting or source file directory")
            }
        }
        group("Preview Configuration") {
            row("") { cell(overridePreviewCheckbox) }
            row("Preview delay (ms):") { cell(projectPreviewDelaySpinner) }
        }
        row("") {
            comment("Project settings override global Structurizr DSL settings.")
        }
    }

    /** Returns `true` when any field differs from the persisted [settings]. */
    fun isModified(settings: StructurizrProjectSettings): Boolean =
        useProjectCliPathCheckbox.isSelected != settings.useProjectCliPath ||
            projectCliPathField.text != settings.projectCliPath ||
            defaultWorkspaceField.text != settings.defaultWorkspaceFile ||
            projectExportDirectoryField.text != settings.projectExportDirectory ||
            overridePreviewCheckbox.isSelected != settings.overridePreviewSettings ||
            projectPreviewDelaySpinner.number != settings.projectPreviewDelayMs

    /** Writes UI values into the persisted [settings] object. */
    fun applyTo(settings: StructurizrProjectSettings) {
        settings.useProjectCliPath = useProjectCliPathCheckbox.isSelected
        settings.projectCliPath = projectCliPathField.text
        settings.defaultWorkspaceFile = defaultWorkspaceField.text
        settings.projectExportDirectory = projectExportDirectoryField.text
        settings.overridePreviewSettings = overridePreviewCheckbox.isSelected
        settings.projectPreviewDelayMs = projectPreviewDelaySpinner.number
    }

    /** Populates the UI from the persisted [settings] object. */
    fun resetFrom(settings: StructurizrProjectSettings) {
        useProjectCliPathCheckbox.isSelected = settings.useProjectCliPath
        projectCliPathField.text = settings.projectCliPath
        defaultWorkspaceField.text = settings.defaultWorkspaceFile
        projectExportDirectoryField.text = settings.projectExportDirectory
        overridePreviewCheckbox.isSelected = settings.overridePreviewSettings
        projectPreviewDelaySpinner.number = settings.projectPreviewDelayMs
    }
}
