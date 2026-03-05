/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.jakubjirak.structurizr.language.StructurizrLanguage
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Builds a [FormattingModel] for Structurizr DSL files.
 *
 * Configures spacing rules and delegates block construction to [StructurizrBlock].
 */
class StructurizrFormattingModelBuilder : FormattingModelBuilder {

    /** Creates the formatting model from the given [formattingContext]. */
    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val settings = formattingContext.codeStyleSettings
        val file = formattingContext.containingFile
        val node = formattingContext.node
        val block = StructurizrBlock(
            node,
            Wrap.createWrap(WrapType.NONE, false),
            Alignment.createAlignment(),
            createSpaceBuilder(settings)
        )
        return FormattingModelProvider.createFormattingModelForPsiFile(file, block, settings)
    }

    /** Builds the [SpacingBuilder] with Structurizr DSL spacing rules. */
    private fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder =
        SpacingBuilder(settings, StructurizrLanguage)
            .before(StructurizrTypes.LBRACE).spaces(1)
            .after(StructurizrTypes.LBRACE).lineBreakInCode()
            .before(StructurizrTypes.RBRACE).lineBreakInCode()
            .around(StructurizrTypes.EQUALS).spaces(1)
            .around(StructurizrTypes.ARROW).spaces(1)
            .around(StructurizrTypes.ARROW_LEFT).spaces(1)
            .around(StructurizrTypes.ARROW_BOTH).spaces(1)
}
