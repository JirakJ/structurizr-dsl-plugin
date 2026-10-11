/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.navigation

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.psi.util.elementType
import com.intellij.util.ProcessingContext
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Registers reference providers for `!include` directives in Structurizr DSL files.
 *
 * Attaches [StructurizrIncludeReference] instances to STRING and IDENTIFIER tokens
 * that appear inside INCLUDE_DIRECTIVE nodes.
 */
class StructurizrIncludeReferenceContributor : PsiReferenceContributor() {

    /** Registers the include-directive reference provider with the [registrar]. */
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext,
                ): Array<PsiReference> {
                    val type = element.elementType
                    if (type != StructurizrTypes.STRING && type != StructurizrTypes.IDENTIFIER) {
                        return PsiReference.EMPTY_ARRAY
                    }

                    val parent = element.parent ?: return PsiReference.EMPTY_ARRAY
                    if (parent.elementType != StructurizrTypes.INCLUDE_DIRECTIVE) {
                        return PsiReference.EMPTY_ARRAY
                    }

                    val text = element.text
                    if (text == "!include") return PsiReference.EMPTY_ARRAY

                    val startOffset: Int
                    val endOffset: Int
                    if (text.startsWith("\"") && text.endsWith("\"") && text.length > 1) {
                        startOffset = 1
                        endOffset = text.length - 1
                    } else {
                        startOffset = 0
                        endOffset = text.length
                    }

                    val range = TextRange(startOffset, endOffset)
                    return arrayOf(StructurizrIncludeReference(element, range))
                }
            }
        )
    }
}
