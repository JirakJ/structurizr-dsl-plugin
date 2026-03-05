/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.openapi.vfs.VirtualFile
import java.beans.PropertyChangeListener
import javax.swing.JComponent

/**
 * A [FileEditor] that wraps a [StructurizrPreviewPanel] for rendering Structurizr diagrams.
 */
class StructurizrPreviewFileEditor(
    private val project: Project,
    private val file: VirtualFile,
) : UserDataHolderBase(), FileEditor {

    private val previewPanel = StructurizrPreviewPanel(project)

    /** Pushes new DSL [content] into the preview panel. */
    fun updatePreview(content: String) {
        previewPanel.updatePreview(content)
    }

    override fun getComponent(): JComponent = previewPanel

    override fun getPreferredFocusedComponent(): JComponent = previewPanel

    override fun getName(): String = "Preview"

    override fun setState(state: FileEditorState) { /* no-op */ }

    override fun isModified(): Boolean = false

    override fun isValid(): Boolean = file.isValid

    override fun addPropertyChangeListener(listener: PropertyChangeListener) { /* no-op */ }

    override fun removePropertyChangeListener(listener: PropertyChangeListener) { /* no-op */ }

    override fun dispose() {
        Disposer.dispose(previewPanel)
    }

    override fun getFile(): VirtualFile = file
}
