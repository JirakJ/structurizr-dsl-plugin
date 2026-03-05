/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.navigation

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

/**
 * View model for the Structurizr DSL Structure View.
 *
 * Defines the root element and element-info behaviour for the
 * Structure tool window tree.
 */
class StructurizrStructureViewModel(
    psiFile: PsiFile,
    editor: Editor?,
) : StructureViewModelBase(psiFile, editor, StructurizrStructureViewElement(psiFile)),
    StructureViewModel.ElementInfoProvider {

    /** Returns `false` — the plus icon is shown only when children exist. */
    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean = false

    /** Returns `false` — elements are never forced to appear as leaves. */
    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean = false
}
