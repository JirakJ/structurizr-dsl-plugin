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
import com.intellij.util.ProcessingContext
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Registers reference providers for Structurizr DSL identifier references.
 *
 * Attaches [StructurizrReference] instances to IDENTIFIER tokens appearing
 * in reference contexts such as relationship statements and view definitions.
 */
class StructurizrReferenceContributor : PsiReferenceContributor() {

    companion object {
        private val REFERENCE_PARENT_TYPES = setOf(
            StructurizrTypes.ELEMENT_REFERENCE,
            StructurizrTypes.RELATIONSHIP_STATEMENT,
            StructurizrTypes.RELATIONSHIP_IN_DYNAMIC_VIEW,
            StructurizrTypes.SYSTEM_CONTEXT_VIEW,
            StructurizrTypes.CONTAINER_VIEW,
            StructurizrTypes.COMPONENT_VIEW,
            StructurizrTypes.DEPLOYMENT_VIEW,
            StructurizrTypes.DYNAMIC_VIEW,
            StructurizrTypes.INCLUDE_STATEMENT,
            StructurizrTypes.EXCLUDE_STATEMENT,
            StructurizrTypes.ELEMENT_SELECTOR,
            StructurizrTypes.ANIMATION_STEP,
        )
    }

    /** Registers the identifier reference provider with the [registrar]. */
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext,
                ): Array<PsiReference> {
                    if (element.node.elementType != StructurizrTypes.IDENTIFIER) {
                        return PsiReference.EMPTY_ARRAY
                    }

                    val parent = element.parent ?: return PsiReference.EMPTY_ARRAY
                    if (parent.node.elementType !in REFERENCE_PARENT_TYPES) {
                        return PsiReference.EMPTY_ARRAY
                    }

                    val range = TextRange(0, element.textLength)
                    return arrayOf(StructurizrReference(element, range))
                }
            }
        )
    }
}
