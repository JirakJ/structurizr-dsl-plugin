/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.Disposable
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.vfs.VirtualFile
import com.jakubjirak.structurizr.language.StructurizrFileType
import javax.swing.JComponent

/**
 * Content of the Structurizr Preview tool window: a [StructurizrPreviewPanel] that follows the
 * `.dsl` file selected in the editor and re-renders as it is edited.
 */
class StructurizrPreviewContent(private val project: Project) : Disposable {

    @Volatile
    private var currentFile: VirtualFile? = null

    private val panel = StructurizrPreviewPanel(project) {
        // Runs inside a read action on a background thread (see StructurizrPreviewPanel.source).
        val file = currentFile?.takeIf { it.isValid } ?: return@StructurizrPreviewPanel null
        val document = FileDocumentManager.getInstance().getDocument(file) ?: return@StructurizrPreviewPanel null
        PreviewSource(document.text, file)
    }

    /** The top-level Swing component for embedding in a tool window. */
    val component: JComponent get() = panel

    init {
        Disposer.register(this, panel)
        project.messageBus.connect(this).subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, object : FileEditorManagerListener {
            override fun selectionChanged(event: FileEditorManagerEvent) {
                event.newFile?.let { track(it) }
            }
        })
        EditorFactory.getInstance().eventMulticaster.addDocumentListener(object : DocumentListener {
            override fun documentChanged(event: DocumentEvent) {
                val file = FileDocumentManager.getInstance().getFile(event.document) ?: return
                if (file == currentFile) panel.scheduleRefresh()
            }
        }, this)
        FileEditorManager.getInstance(project).selectedFiles.firstOrNull { it.fileType == StructurizrFileType }?.let { track(it) }
    }

    private fun track(file: VirtualFile) {
        if (file.fileType != StructurizrFileType) return
        if (file != currentFile) {
            currentFile = file
            panel.resetSelection()
            panel.refreshNow()
        }
    }

    override fun dispose() = Unit
}
