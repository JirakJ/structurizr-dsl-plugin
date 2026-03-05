/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
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
    private val useEmbeddedCliCheckbox = JBCheckBox("Use embedded CLI if available")
    private val cliTimeoutSpinner = JBIntSpinner(30, 5, 300, 5)

    // --- Preview ---
    private val enablePreviewCheckbox = JBCheckBox("Enable live preview")
    private val autoRefreshCheckbox = JBCheckBox("Auto-refresh preview on save")
    private val previewDelaySpinner = JBIntSpinner(500, 100, 5000, 100)
    private val showSplitEditorCheckbox = JBCheckBox("Show preview in split editor by default")
    private val previewZoomSpinner = JBIntSpinner(100, 25, 400, 25)
    private val previewDarkModeCheckbox = JBCheckBox("Dark mode for preview")
    private val previewUseSystemThemeCheckbox = JBCheckBox("Use system theme")

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
    private val exportMetadataCheckbox = JBCheckBox("Include metadata in exports")
    private val autoOpenExportCheckbox = JBCheckBox("Auto-open exported file")

    // --- Editor ---
    private val showBreadcrumbsCheckbox = JBCheckBox("Show breadcrumbs")
    private val enableFoldingCheckbox = JBCheckBox("Enable code folding")
    private val showLineMarkersCheckbox = JBCheckBox("Show line markers")
    private val enableInspectionsCheckbox = JBCheckBox("Enable inspections")

    // --- Code Completion ---
    private val autoPopupCompletionCheckbox = JBCheckBox("Auto-popup completion")
    private val includeReferencesCheckbox = JBCheckBox("Include element references in completion")
    private val showDocInCompletionCheckbox = JBCheckBox("Show documentation in completion popup")

    /** The root Swing panel rendered in the settings dialog. */
    val panel: JPanel = panel {
        group("Structurizr CLI") {
            row("CLI path:") {
                cell(cliPathField).align(Align.FILL)
                    .comment("Path to structurizr-cli JAR file or executable")
            }
            row("") { cell(useEmbeddedCliCheckbox) }
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
                    .comment("Delay before refreshing preview after typing")
            }
            row("") { cell(showSplitEditorCheckbox) }
            row("Zoom level (%):") { cell(previewZoomSpinner) }
            row("") { cell(previewUseSystemThemeCheckbox) }
            row("") { cell(previewDarkModeCheckbox) }
        }
        group("Export") {
            row("Default format:") { cell(exportFormatCombo) }
            row("Default directory:") {
                cell(exportDirectoryField).align(Align.FILL)
                    .comment("Leave empty to use source file directory")
            }
            row("Image width:") { cell(exportWidthSpinner) }
            row("Image height:") { cell(exportHeightSpinner) }
            row("") { cell(exportMetadataCheckbox) }
            row("") { cell(autoOpenExportCheckbox) }
        }
        group("Editor") {
            row("") { cell(showBreadcrumbsCheckbox) }
            row("") { cell(enableFoldingCheckbox) }
            row("") { cell(showLineMarkersCheckbox) }
            row("") { cell(enableInspectionsCheckbox) }
        }
        group("Code Completion") {
            row("") { cell(autoPopupCompletionCheckbox) }
            row("") { cell(includeReferencesCheckbox) }
            row("") { cell(showDocInCompletionCheckbox) }
        }
    }

    /** Returns `true` when any field differs from the persisted [settings]. */
    fun isModified(settings: StructurizrSettings): Boolean =
        cliPathField.text != settings.structurizrCliPath ||
            useEmbeddedCliCheckbox.isSelected != settings.useEmbeddedCli ||
            cliTimeoutSpinner.number != settings.cliTimeoutSeconds ||
            enablePreviewCheckbox.isSelected != settings.enableLivePreview ||
            autoRefreshCheckbox.isSelected != settings.autoRefreshPreview ||
            previewDelaySpinner.number != settings.previewDelayMs ||
            showSplitEditorCheckbox.isSelected != settings.showPreviewInSplitEditor ||
            previewZoomSpinner.number != settings.previewZoomLevel ||
            previewDarkModeCheckbox.isSelected != settings.previewDarkMode ||
            previewUseSystemThemeCheckbox.isSelected != settings.previewUseSystemTheme ||
            exportFormatCombo.selectedItem != settings.defaultExportFormat ||
            exportDirectoryField.text != settings.defaultExportDirectory ||
            exportWidthSpinner.number != settings.exportImageWidth ||
            exportHeightSpinner.number != settings.exportImageHeight ||
            exportMetadataCheckbox.isSelected != settings.exportIncludeMetadata ||
            autoOpenExportCheckbox.isSelected != settings.autoOpenExportedFile ||
            showBreadcrumbsCheckbox.isSelected != settings.showBreadcrumbs ||
            enableFoldingCheckbox.isSelected != settings.enableCodeFolding ||
            showLineMarkersCheckbox.isSelected != settings.showLineMarkers ||
            enableInspectionsCheckbox.isSelected != settings.enableInspections ||
            autoPopupCompletionCheckbox.isSelected != settings.autoPopupCompletion ||
            includeReferencesCheckbox.isSelected != settings.includeReferencesInCompletion ||
            showDocInCompletionCheckbox.isSelected != settings.showDocumentationInCompletion

    /** Writes UI values into the persisted [settings] object. */
    fun applyTo(settings: StructurizrSettings) {
        settings.structurizrCliPath = cliPathField.text
        settings.useEmbeddedCli = useEmbeddedCliCheckbox.isSelected
        settings.cliTimeoutSeconds = cliTimeoutSpinner.number
        settings.enableLivePreview = enablePreviewCheckbox.isSelected
        settings.autoRefreshPreview = autoRefreshCheckbox.isSelected
        settings.previewDelayMs = previewDelaySpinner.number
        settings.showPreviewInSplitEditor = showSplitEditorCheckbox.isSelected
        settings.previewZoomLevel = previewZoomSpinner.number
        settings.previewDarkMode = previewDarkModeCheckbox.isSelected
        settings.previewUseSystemTheme = previewUseSystemThemeCheckbox.isSelected
        settings.defaultExportFormat = exportFormatCombo.selectedItem as String
        settings.defaultExportDirectory = exportDirectoryField.text
        settings.exportImageWidth = exportWidthSpinner.number
        settings.exportImageHeight = exportHeightSpinner.number
        settings.exportIncludeMetadata = exportMetadataCheckbox.isSelected
        settings.autoOpenExportedFile = autoOpenExportCheckbox.isSelected
        settings.showBreadcrumbs = showBreadcrumbsCheckbox.isSelected
        settings.enableCodeFolding = enableFoldingCheckbox.isSelected
        settings.showLineMarkers = showLineMarkersCheckbox.isSelected
        settings.enableInspections = enableInspectionsCheckbox.isSelected
        settings.autoPopupCompletion = autoPopupCompletionCheckbox.isSelected
        settings.includeReferencesInCompletion = includeReferencesCheckbox.isSelected
        settings.showDocumentationInCompletion = showDocInCompletionCheckbox.isSelected
    }

    /** Populates the UI from the persisted [settings] object. */
    fun resetFrom(settings: StructurizrSettings) {
        cliPathField.text = settings.structurizrCliPath
        useEmbeddedCliCheckbox.isSelected = settings.useEmbeddedCli
        cliTimeoutSpinner.number = settings.cliTimeoutSeconds
        enablePreviewCheckbox.isSelected = settings.enableLivePreview
        autoRefreshCheckbox.isSelected = settings.autoRefreshPreview
        previewDelaySpinner.number = settings.previewDelayMs
        showSplitEditorCheckbox.isSelected = settings.showPreviewInSplitEditor
        previewZoomSpinner.number = settings.previewZoomLevel
        previewDarkModeCheckbox.isSelected = settings.previewDarkMode
        previewUseSystemThemeCheckbox.isSelected = settings.previewUseSystemTheme
        exportFormatCombo.selectedItem = settings.defaultExportFormat
        exportDirectoryField.text = settings.defaultExportDirectory
        exportWidthSpinner.number = settings.exportImageWidth
        exportHeightSpinner.number = settings.exportImageHeight
        exportMetadataCheckbox.isSelected = settings.exportIncludeMetadata
        autoOpenExportCheckbox.isSelected = settings.autoOpenExportedFile
        showBreadcrumbsCheckbox.isSelected = settings.showBreadcrumbs
        enableFoldingCheckbox.isSelected = settings.enableCodeFolding
        showLineMarkersCheckbox.isSelected = settings.showLineMarkers
        enableInspectionsCheckbox.isSelected = settings.enableInspections
        autoPopupCompletionCheckbox.isSelected = settings.autoPopupCompletion
        includeReferencesCheckbox.isSelected = settings.includeReferencesInCompletion
        showDocInCompletionCheckbox.isSelected = settings.showDocumentationInCompletion
    }
}
