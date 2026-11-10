/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.export

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import com.intellij.util.ui.JBUI
import java.awt.Dimension
import java.nio.file.Path
import javax.swing.JComponent

/**
 * Simplified quick-export dialog for a single format.
 *
 * Presents only output directory and file name fields.
 */
class QuickExportDialog(
    private val project: Project,
    private val sourceFile: VirtualFile,
    private val format: DiagramExportFormat
) : DialogWrapper(project) {

    private val outputPathField = TextFieldWithBrowseButton()
    private val fileNameField = JBTextField()

    init {
        title = "Export as ${format.displayName}"
        init()
        setupUI()
    }

    private fun setupUI() {
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
    }

    /** Creates the dialog center panel. */
    override fun createCenterPanel(): JComponent {
        val panel = FormBuilder.createFormBuilder()
            .addLabeledComponent("Output Directory:", outputPathField)
            .addLabeledComponent("File Name:", fileNameField)
            .addComponent(JBLabel("<html><i>Will be saved as: ${fileNameField.text}.${format.extension}</i></html>"))
            .panel
        panel.border = JBUI.Borders.empty(10)
        panel.preferredSize = Dimension(400, 150)
        return panel
    }

    /** Validates input before closing. */
    override fun doOKAction() {
        if (outputPathField.text.isBlank()) {
            setErrorText("Please select an output directory", outputPathField)
            return
        }
        if (fileNameField.text.isBlank()) {
            setErrorText("Please enter a file name", fileNameField)
            return
        }
        super.doOKAction()
    }

    /** Returns the full output file path. */
    fun getOutputPath(): Path {
        val fileName = fileNameField.text.ifBlank { sourceFile.nameWithoutExtension }
        return Path.of(outputPathField.text, "$fileName.${format.extension}")
    }
}
