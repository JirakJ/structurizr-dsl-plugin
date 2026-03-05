/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorPolicy
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.jakubjirak.structurizr.language.StructurizrFileType

/**
 * Provides a split editor (text + preview) for Structurizr DSL files.
 */
class StructurizrSplitEditorProvider : FileEditorProvider, DumbAware {

    /** Accepts only files whose type matches [StructurizrFileType]. */
    override fun accept(project: Project, file: VirtualFile): Boolean =
        file.fileType == StructurizrFileType

    /** Creates a [StructurizrSplitEditor] wrapping the default text editor. */
    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        val textEditor = TextEditorProvider.getInstance().createEditor(project, file) as TextEditor
        return StructurizrSplitEditor(project, textEditor, file)
    }

    override fun getEditorTypeId(): String = "structurizr-split-editor"

    override fun getPolicy(): FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR
}
