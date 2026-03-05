/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import com.jakubjirak.structurizr.language.StructurizrLanguage

/**
 * Registers the Structurizr DSL completion provider for basic completion
 * on elements belonging to the [StructurizrLanguage].
 */
class StructurizrCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().withLanguage(StructurizrLanguage),
            StructurizrCompletionProvider()
        )
    }
}
