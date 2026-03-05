/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference
import com.jakubjirak.structurizr.language.psi.StructurizrElementSelector
import com.jakubjirak.structurizr.language.psi.StructurizrRefStatement
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement

/**
 * Base class for all Structurizr DSL inspections.
 *
 * Provides shared helper methods to collect defined and used elements from a PSI file.
 */
abstract class StructurizrInspectionBase : LocalInspectionTool() {

    override fun getGroupDisplayName(): String = "Structurizr"

    override fun isEnabledByDefault(): Boolean = true

    /**
     * Collects the names of all elements defined via element assignments in the given [file].
     *
     * @param file the PSI file to scan
     * @return a set of defined element identifier names
     */
    protected fun collectDefinedElements(file: PsiFile): Set<String> {
        val elements = mutableSetOf<String>()
        PsiTreeUtil.findChildrenOfType(file, StructurizrElementAssignment::class.java).forEach { assignment ->
            assignment.identifier?.text?.let { elements.add(it) }
        }
        return elements
    }

    /**
     * Collects all defined elements with their corresponding PSI elements in the given [file].
     *
     * @param file the PSI file to scan
     * @return a map of element identifier names to their PSI elements
     */
    protected fun collectDefinedElementsWithPsi(file: PsiFile): Map<String, PsiElement> {
        val elements = mutableMapOf<String, PsiElement>()
        PsiTreeUtil.findChildrenOfType(file, StructurizrElementAssignment::class.java).forEach { assignment ->
            val identifier = assignment.identifier ?: return@forEach
            identifier.text?.let { elements[it] = identifier }
        }
        return elements
    }

    /**
     * Collects all element names that are referenced (used) within the given [file].
     *
     * @param file the PSI file to scan
     * @return a set of used element identifier names
     */
    protected fun collectUsedElements(file: PsiFile): Set<String> {
        val used = mutableSetOf<String>()

        PsiTreeUtil.findChildrenOfType(file, StructurizrRelationshipStatement::class.java).forEach { rel ->
            rel.elementReferenceList.forEach { ref ->
                used.add(ref.text)
            }
        }

        PsiTreeUtil.findChildrenOfType(file, StructurizrElementReference::class.java).forEach { ref ->
            used.add(ref.text)
        }

        PsiTreeUtil.findChildrenOfType(file, StructurizrRefStatement::class.java).forEach { ref ->
            ref.text.removePrefix("!ref").trim().split(".").firstOrNull()?.let { used.add(it) }
        }

        PsiTreeUtil.findChildrenOfType(file, StructurizrElementSelector::class.java).forEach { sel ->
            val text = sel.text.trim()
            if (text != "*" && !text.startsWith("->") && !text.contains("->")) {
                used.add(text)
            }
        }

        return used
    }
}
