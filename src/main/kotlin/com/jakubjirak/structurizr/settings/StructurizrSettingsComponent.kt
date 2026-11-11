/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.settings

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.JBIntSpinner
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.panel
import javax.swing.JPanel

/**
 * UI component for global Structurizr DSL plugin settings.
 *
 * Renders the settings panel using the IntelliJ Kotlin UI DSL and
 * provides methods to read, apply, and reset values against [StructurizrSettings].
 */
class StructurizrSettingsComponent {

    // --- CLI ---
    private val cliPathField = TextFieldWithBrowseButton().apply {
        addActionListener {
            val descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor("jar")
                .withTitle("Select Structurizr CLI")
                .withDescription("Select the Structurizr CLI executable")
            FileChooser.chooseFile(descriptor, null, null) { file ->
                text = file.path
            }
        }
    }
    private val cliTimeoutSpinner = JBIntSpinner(30, 5, 300, 5)

    // --- Preview ---
    private val enablePreviewCheckbox = JBCheckBox("Show diagram preview next to the editor")
    private val autoRefreshCheckbox = JBCheckBox("Refresh preview while typing")
    private val previewDelaySpinner = JBIntSpinner(500, 100, 5000, 100)

    // --- Export ---
    private val exportFormatCombo = ComboBox(StructurizrSettings.EXPORT_FORMATS.toTypedArray())
    private val exportDirectoryField = TextFieldWithBrowseButton().apply {
        addActionListener {
            val descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
                .withTitle("Select Export Directory")
                .withDescription("Select the default export directory")
            FileChooser.chooseFile(descriptor, null, null) { file ->
                text = file.path
            }
        }
    }
    private val exportWidthSpinner = JBIntSpinner(1920, 320, 7680, 160)
    private val exportHeightSpinner = JBIntSpinner(1080, 240, 4320, 120)
    private val autoOpenExportCheckbox = JBCheckBox("Open exported file after export")

    /** The root Swing panel rendered in the settings dialog. */
    val panel: JPanel = panel {
        group("Structurizr CLI") {
            row("CLI path:") {
                cell(cliPathField).align(Align.FILL)
                    .comment("Path to the structurizr-cli JAR or launcher script; leave empty to auto-detect")
            }
            row("Timeout (seconds):") {
                cell(cliTimeoutSpinner)
                    .comment("Maximum time to wait for CLI operations")
            }
        }
        group("Live Preview") {
            row("") { cell(enablePreviewCheckbox) }
            row("") { cell(autoRefreshCheckbox) }
            row("Refresh delay (ms):") {
                cell(previewDelaySpinner)
                    .comment("Delay before refreshing the preview after typing")
            }
        }
        group("Export") {
            row("Default format:") { cell(exportFormatCombo) }
            row("Default directory:") {
                cell(exportDirectoryField).align(Align.FILL)
                    .comment("Leave empty to use source file directory")
            }
            row("Max PNG width:") { cell(exportWidthSpinner) }
            row("Max PNG height:") { cell(exportHeightSpinner) }
            row("") { cell(autoOpenExportCheckbox) }
        }
    }

    /** Returns `true` when any field differs from the persisted [settings]. */
    fun isModified(settings: StructurizrSettings): Boolean =
        cliPathField.text != settings.structurizrCliPath ||
            cliTimeoutSpinner.number != settings.cliTimeoutSeconds ||
            enablePreviewCheckbox.isSelected != settings.enableLivePreview ||
            autoRefreshCheckbox.isSelected != settings.autoRefreshPreview ||
            previewDelaySpinner.number != settings.previewDelayMs ||
            exportFormatCombo.selectedItem != settings.defaultExportFormat ||
            exportDirectoryField.text != settings.defaultExportDirectory ||
            exportWidthSpinner.number != settings.exportImageWidth ||
            exportHeightSpinner.number != settings.exportImageHeight ||
            autoOpenExportCheckbox.isSelected != settings.autoOpenExportedFile

    /** Writes UI values into the persisted [settings] object. */
    fun applyTo(settings: StructurizrSettings) {
        settings.structurizrCliPath = cliPathField.text
        settings.cliTimeoutSeconds = cliTimeoutSpinner.number
        settings.enableLivePreview = enablePreviewCheckbox.isSelected
        settings.autoRefreshPreview = autoRefreshCheckbox.isSelected
        settings.previewDelayMs = previewDelaySpinner.number
        settings.defaultExportFormat = exportFormatCombo.selectedItem as String
        settings.defaultExportDirectory = exportDirectoryField.text
        settings.exportImageWidth = exportWidthSpinner.number
        settings.exportImageHeight = exportHeightSpinner.number
        settings.autoOpenExportedFile = autoOpenExportCheckbox.isSelected
    }

    /** Populates the UI from the persisted [settings] object. */
    fun resetFrom(settings: StructurizrSettings) {
        cliPathField.text = settings.structurizrCliPath
        cliTimeoutSpinner.number = settings.cliTimeoutSeconds
        enablePreviewCheckbox.isSelected = settings.enableLivePreview
        autoRefreshCheckbox.isSelected = settings.autoRefreshPreview
        previewDelaySpinner.number = settings.previewDelayMs
        exportFormatCombo.selectedItem = settings.defaultExportFormat
        exportDirectoryField.text = settings.defaultExportDirectory
        exportWidthSpinner.number = settings.exportImageWidth
        exportHeightSpinner.number = settings.exportImageHeight
        autoOpenExportCheckbox.isSelected = settings.autoOpenExportedFile
    }
}
