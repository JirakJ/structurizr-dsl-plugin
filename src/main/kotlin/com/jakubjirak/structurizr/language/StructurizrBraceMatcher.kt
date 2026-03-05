/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.language

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Provides brace matching support for the Structurizr DSL language.
 *
 * Pairs left braces (`{`) with right braces (`}`) so the IDE can highlight
 * matching braces and support brace-based code folding.
 */
class StructurizrBraceMatcher : PairedBraceMatcher {

    /** Returns the set of brace pairs recognized in Structurizr DSL files. */
    override fun getPairs(): Array<BracePair> = PAIRS

    /** Indicates that paired braces are allowed before any token type. */
    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true

    /** Returns the offset where the code construct containing the opening brace starts. */
    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset

    companion object {
        private val PAIRS = arrayOf(
            BracePair(StructurizrTypes.LBRACE, StructurizrTypes.RBRACE, true)
        )
    }
}
