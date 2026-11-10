/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.navigation

import com.intellij.navigation.ChooseByNameContributorEx
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.Processor
import com.intellij.util.indexing.FindSymbolParameters
import com.intellij.util.indexing.IdFilter
import com.jakubjirak.structurizr.language.StructurizrFileType
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment
import com.jakubjirak.structurizr.language.psi.StructurizrFile

/**
 * Contributes Structurizr DSL symbols to the Go To Symbol dialog.
 *
 * Collects all element assignment identifiers from Structurizr files
 * within the given search scope.
 */
class StructurizrGotoSymbolContributor : ChooseByNameContributorEx {

    /** Feeds all symbol names from Structurizr files into the [processor]. */
    override fun processNames(
        processor: Processor<in String>,
        scope: GlobalSearchScope,
        filter: IdFilter?,
    ) {
        val project = scope.project ?: return
        for (entry in collectAllEntries(project, scope)) {
            if (!processor.process(entry.name)) return
        }
    }

    /** Feeds navigation items matching [name] into the [processor]. */
    override fun processElementsWithName(
        name: String,
        processor: Processor<in NavigationItem>,
        parameters: FindSymbolParameters,
    ) {
        val project = parameters.project
        val scope = parameters.searchScope
        for (entry in collectAllEntries(project, scope)) {
            if (entry.name == name && entry.element is NavigationItem) {
                if (!processor.process(entry.element as NavigationItem)) return
            }
        }
    }

    private data class NavigationEntry(val name: String, val element: PsiElement)

    companion object {
        private fun collectAllEntries(
            project: com.intellij.openapi.project.Project,
            scope: GlobalSearchScope,
        ): List<NavigationEntry> {
            val result = mutableListOf<NavigationEntry>()
            val psiManager = PsiManager.getInstance(project)
            FileTypeIndex.processFiles(StructurizrFileType, { virtualFile ->
                val psiFile = psiManager.findFile(virtualFile)
                if (psiFile is StructurizrFile) {
                    PsiTreeUtil.findChildrenOfType(psiFile, StructurizrElementAssignment::class.java)
                        .forEach { assignment ->
                            assignment.identifier?.let { identifier ->
                                val text = identifier.text
                                if (!text.isNullOrEmpty()) {
                                    result.add(NavigationEntry(text, identifier))
                                }
                            }
                        }
                }
                true
            }, scope)
            return result
        }
    }
}
