/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.openapi.vfs.VirtualFile
import java.beans.PropertyChangeListener
import javax.swing.JComponent

/**
 * A [FileEditor] that wraps a [StructurizrPreviewPanel] for rendering the diagrams of one file.
 */
class StructurizrPreviewFileEditor(
    private val project: Project,
    private val file: VirtualFile,
) : UserDataHolderBase(), FileEditor {

    private val previewPanel = StructurizrPreviewPanel(project) {
        // Runs inside a read action on a background thread (see StructurizrPreviewPanel.source).
        val document = file.takeIf { it.isValid }?.let { FileDocumentManager.getInstance().getDocument(it) }
        document?.let { PreviewSource(it.text, file) }
    }

    /** Re-renders the preview after a short delay; called on every document change. */
    fun scheduleRefresh() = previewPanel.scheduleRefresh()

    /** Re-renders the preview immediately. */
    fun refreshNow() = previewPanel.refreshNow()

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
