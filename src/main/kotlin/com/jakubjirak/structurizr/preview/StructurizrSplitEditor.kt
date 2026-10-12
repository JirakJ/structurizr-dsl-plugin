/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.fileEditor.TextEditorWithPreview
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.pom.Navigatable

/**
 * A split editor that pairs a standard [TextEditor] with a live
 * [StructurizrPreviewFileEditor] for Structurizr DSL files.
 */
class StructurizrSplitEditor private constructor(
    private val project: Project,
    override val textEditor: TextEditor,
    private val structurizrPreview: StructurizrPreviewFileEditor,
) : TextEditorWithPreview(
    textEditor,
    structurizrPreview,
    "Structurizr Editor",
), TextEditor {

    constructor(project: Project, textEditor: TextEditor, file: VirtualFile) :
        this(project, textEditor, StructurizrPreviewFileEditor(project, file))

    init {
        textEditor.editor.document.addDocumentListener(object : DocumentListener {
            override fun documentChanged(event: DocumentEvent) {
                structurizrPreview.scheduleRefresh()
            }
        }, this)
        structurizrPreview.refreshNow()
    }

    override fun getEditor(): Editor = textEditor.editor

    override fun canNavigateTo(navigatable: Navigatable): Boolean =
        textEditor.canNavigateTo(navigatable)

    override fun navigateTo(navigatable: Navigatable) {
        textEditor.navigateTo(navigatable)
    }
}
