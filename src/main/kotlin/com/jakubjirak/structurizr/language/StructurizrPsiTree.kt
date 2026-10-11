/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.language

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

/**
 * Ancestors of this element inside its own file, nearest first, ending with the [PsiFile].
 *
 * Walking `parent` without a stop runs past the file into its `PsiDirectory`, whose
 * `getNode()` is null. Any loop that then reads `node.elementType` throws an NPE
 * (docs#12: opening a .dsl file whose identifiers sit outside a `workspace { }` block).
 */
fun PsiElement.ancestorsInFile(): Sequence<PsiElement> =
    generateSequence(parent) { if (it is PsiFile) null else it.parent }

/** Element type of this PSI element, or null for elements without an AST node. */
val PsiElement.elementTypeOrNull: IElementType?
    get() = node?.elementType
