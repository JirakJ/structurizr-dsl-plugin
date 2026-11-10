/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil
import com.jakubjirak.structurizr.language.psi.StructurizrViewKey

/**
 * Inspection that detects duplicate view keys (FR-507).
 *
 * Structurizr requires every view in a workspace to have a unique key — the CLI
 * rejects workspaces with duplicates ("A view with the key X already exists").
 * This inspection surfaces the error while editing, before export or push.
 */
class DuplicateViewKeyInspection : StructurizrInspectionBase() {

    override fun getDisplayName(): String = "Duplicate view key"

    override fun getShortName(): String = "StructurizrDuplicateViewKey"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : PsiElementVisitor() {

            /** Lazily computed once per inspected file: normalized key -> view keys using it, in file order. */
            private var keysByValue: Map<String, List<StructurizrViewKey>>? = null

            override fun visitElement(element: PsiElement) {
                if (element !is StructurizrViewKey) return

                val key = normalizeKey(element.text) ?: return
                val occurrences = keysByValue ?: collectViewKeys(element.containingFile).also { keysByValue = it }
                val sameKey = occurrences[key] ?: return

                if (sameKey.size > 1 && element != sameKey.first()) {
                    holder.registerProblem(
                        element,
                        "Duplicate view key: \"$key\". Every view in a workspace must have a unique key",
                        ProblemHighlightType.ERROR
                    )
                }
            }
        }

    private fun collectViewKeys(file: PsiElement): Map<String, List<StructurizrViewKey>> =
        PsiTreeUtil.findChildrenOfType(file, StructurizrViewKey::class.java)
            .mapNotNull { viewKey -> normalizeKey(viewKey.text)?.let { it to viewKey } }
            .groupBy({ it.first }, { it.second })

    companion object {
        /**
         * Normalizes a view key by trimming whitespace and surrounding quotes.
         *
         * @return the normalized key, or null when the text holds no usable key
         */
        fun normalizeKey(text: String?): String? {
            val key = text?.trim()?.removeSurrounding("\"")?.trim()
            return if (key.isNullOrEmpty()) null else key
        }
    }
}
