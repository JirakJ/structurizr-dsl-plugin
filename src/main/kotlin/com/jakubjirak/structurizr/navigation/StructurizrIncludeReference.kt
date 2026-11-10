/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.navigation

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiReferenceBase

/**
 * Reference from an `!include` directive to the targeted file.
 *
 * Resolves relative file paths found inside include directives
 * to the corresponding [PsiElement].
 */
class StructurizrIncludeReference(
    element: PsiElement,
    rangeInElement: TextRange,
) : PsiReferenceBase<PsiElement>(element, rangeInElement) {

    private val filePath: String = rangeInElement.substring(element.text)
        .replace("\"", "")
        .trim()

    /** Resolves the include path to the target [PsiElement], or `null` if not found. */
    override fun resolve(): PsiElement? {
        if (filePath.isEmpty()) return null

        val containingFile = myElement.containingFile ?: return null
        val currentFile = containingFile.virtualFile ?: return null
        val parentDir = currentFile.parent ?: return null
        val targetFile = parentDir.findFileByRelativePath(filePath) ?: return null

        if (targetFile.isDirectory) return null
        return PsiManager.getInstance(myElement.project).findFile(targetFile)
    }

    /** Returns an empty array — no completion variants are provided. */
    override fun getVariants(): Array<Any> = emptyArray()
}
