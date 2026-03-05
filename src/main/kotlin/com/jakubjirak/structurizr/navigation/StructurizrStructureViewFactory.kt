/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.navigation

import com.intellij.ide.structureView.StructureViewBuilder
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import com.jakubjirak.structurizr.language.psi.StructurizrFile

/**
 * Factory that creates a Structure View for Structurizr DSL files.
 *
 * Returns a tree-based structure view builder backed by
 * [StructurizrStructureViewModel] for files of type [StructurizrFile].
 */
class StructurizrStructureViewFactory : PsiStructureViewFactory {

    /** Creates a [StructureViewBuilder] for the given [psiFile], or `null` if not a Structurizr file. */
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder? {
        if (psiFile !is StructurizrFile) return null

        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel =
                StructurizrStructureViewModel(psiFile, editor)
        }
    }
}
