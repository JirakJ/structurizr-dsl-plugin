/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.highlighting

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

/**
 * Factory that provides [StructurizrSyntaxHighlighter] instances for the
 * Structurizr DSL file type.
 */
class StructurizrSyntaxHighlighterFactory : SyntaxHighlighterFactory() {

    /**
     * Creates a new [StructurizrSyntaxHighlighter] for the given [project]
     * and [virtualFile].
     */
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
        StructurizrSyntaxHighlighter()
}
