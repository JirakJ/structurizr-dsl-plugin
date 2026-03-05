/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.export

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.DocumentAdapter
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import java.nio.file.Path
import javax.swing.*
import javax.swing.event.DocumentEvent

/**
 * Full-featured export dialog for Structurizr diagrams.
 *
 * Allows the user to choose format, output directory, file name,
 * view selection, and additional export options.
 */
class ExportDialog(
    private val project: Project,
    private val sourceFile: VirtualFile
) : DialogWrapper(project) {

    private val formatComboBox = ComboBox(DiagramExportFormat.entries.toTypedArray())
    private val outputPathField = TextFieldWithBrowseButton()
    private val fileNameField = JBTextField()
    private val exportAllViewsCheckbox = JBCheckBox("Export all views separately")
    private val openAfterExportCheckbox = JBCheckBox("Open file after export", true)
    private val viewSelector = ComboBox<String>()
    private val formatDescriptionLabel = JBLabel()
    private val previewLabel = JBLabel()

    init {
        title = "Export Diagram"
        init()
        setupUI()
        updatePreview()
    }

    private fun setupUI() {
        formatComboBox.renderer = object : DefaultListCellRenderer() {
            override fun getListCellRendererComponent(
                list: JList<*>?, value: Any?, index: Int, isSelected: Boolean, cellHasFocus: Boolean
            ): Component {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
                (value as? DiagramExportFormat)?.let {
                    text = "${it.displayName} (.${it.extension})"
                }
                return this
            }
        }

        formatComboBox.addActionListener {
            updateFormatDescription()
            updatePreview()
        }

        outputPathField.addActionListener {
            val descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
                .withTitle("Select Output Directory")
                .withDescription("Choose where to save the exported diagram")
            FileChooser.chooseFile(descriptor, project, null) { file ->
                outputPathField.text = file.path
            }
        }

        val defaultDir = sourceFile.parent?.path ?: System.getProperty("user.home")
        outputPathField.text = defaultDir
        fileNameField.text = sourceFile.nameWithoutExtension

        fileNameField.document.addDocumentListener(object : DocumentAdapter() {
            override fun textChanged(e: DocumentEvent) {
                updatePreview()
            }
        })

        viewSelector.apply {
            addItem("All views")
            addItem("System Landscape")
            addItem("System Context")
            addItem("Container")
            addItem("Component")
            addItem("Deployment")
        }

        exportAllViewsCheckbox.addActionListener {
            viewSelector.isEnabled = !exportAllViewsCheckbox.isSelected
            updatePreview()
        }

        updateFormatDescription()
    }

    private fun updateFormatDescription() {
        val format = formatComboBox.selectedItem as? DiagramExportFormat
        formatDescriptionLabel.text = "<html><i>${format?.description ?: ""}</i></html>"
        formatDescriptionLabel.foreground = JBUI.CurrentTheme.ContextHelp.FOREGROUND
    }

    private fun updatePreview() {
        val format = formatComboBox.selectedItem as? DiagramExportFormat ?: return
        val fileName = fileNameField.text.ifBlank { sourceFile.nameWithoutExtension }
        val outputDir = outputPathField.text.ifBlank { sourceFile.parent?.path ?: "" }
        val fullPath = if (exportAllViewsCheckbox.isSelected) {
            "$outputDir/<view-name>.${format.extension}"
        } else {
            "$outputDir/$fileName.${format.extension}"
        }
        previewLabel.text = "<html><b>Output:</b> $fullPath</html>"
    }

    /** Creates the dialog center panel. */
    override fun createCenterPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        val formPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent("Source:", JBLabel(sourceFile.name))
            .addSeparator()
            .addLabeledComponent("Format:", formatComboBox)
            .addComponentToRightColumn(formatDescriptionLabel)
            .addSeparator()
            .addLabeledComponent("Output Directory:", outputPathField)
            .addLabeledComponent("File Name:", fileNameField)
            .addSeparator()
            .addLabeledComponent("View:", viewSelector)
            .addComponent(exportAllViewsCheckbox)
            .addComponent(openAfterExportCheckbox)
            .addSeparator()
            .addComponent(previewLabel)
            .panel
        formPanel.border = JBUI.Borders.empty(10)
        panel.add(formPanel, BorderLayout.CENTER)
        panel.preferredSize = Dimension(500, 350)
        return panel
    }

    /** Validates input before closing. */
    override fun doOKAction() {
        if (outputPathField.text.isBlank()) {
            setErrorText("Please select an output directory", outputPathField)
            return
        }
        if (fileNameField.text.isBlank() && !exportAllViewsCheckbox.isSelected) {
            setErrorText("Please enter a file name", fileNameField)
            return
        }
        super.doOKAction()
    }

    /** Returns the currently selected export format. */
    fun getSelectedFormat(): DiagramExportFormat =
        formatComboBox.selectedItem as DiagramExportFormat

    /** Returns the full output file path. */
    fun getOutputPath(): Path {
        val format = getSelectedFormat()
        val fileName = fileNameField.text.ifBlank { sourceFile.nameWithoutExtension }
        return Path.of(outputPathField.text, "$fileName.${format.extension}")
    }

    /** Returns the selected output directory. */
    fun getOutputDirectory(): Path = Path.of(outputPathField.text)

    /** Returns whether all views should be exported. */
    fun isExportAllViews(): Boolean = exportAllViewsCheckbox.isSelected

    /** Returns the selected view key, or null for all views. */
    fun getSelectedViewKey(): String? {
        if (exportAllViewsCheckbox.isSelected) return null
        return when (viewSelector.selectedItem as? String) {
            "All views" -> null
            "System Landscape" -> "landscape"
            "System Context" -> "systemContext"
            "Container" -> "container"
            "Component" -> "component"
            "Deployment" -> "deployment"
            else -> null
        }
    }

    /** Returns whether files should be opened after export. */
    fun isOpenAfterExport(): Boolean = openAfterExportCheckbox.isSelected
}
