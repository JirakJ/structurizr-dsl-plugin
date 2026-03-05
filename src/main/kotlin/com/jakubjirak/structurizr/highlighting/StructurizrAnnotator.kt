/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.highlighting

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.EffectType
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import java.awt.Color
import java.util.regex.Pattern

/**
 * Annotator that provides additional semantic highlighting for Structurizr DSL
 * elements beyond what the lexer-based [StructurizrSyntaxHighlighter] offers.
 *
 * Handles variable substitutions inside strings, definition-site identifiers,
 * arrow operators, and deprecated directives.
 */
class StructurizrAnnotator : Annotator {

    /**
     * Annotates the given [element] with semantic highlighting information.
     */
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val type = element.node.elementType

        // Highlight ${...} substitutions inside string literals
        if (type == StructurizrTypes.STRING) {
            val text = element.text
            val elementStart = element.textRange.startOffset
            val matcher = SUBSTITUTION_PATTERN.matcher(text)
            while (matcher.find()) {
                val range = TextRange(elementStart + matcher.start(), elementStart + matcher.end())
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(range)
                    .textAttributes(VARIABLE_REFERENCE)
                    .create()
            }
        }

        // Highlight IDENTIFIER at definition site (element assignment)
        if (type == StructurizrTypes.IDENTIFIER) {
            val parent = element.parent
            if (parent != null && parent.node.elementType == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(DEFINITION_IDENTIFIER)
                    .create()
            }
        }

        // Highlight arrow operators
        if (type == StructurizrTypes.ARROW ||
            type == StructurizrTypes.ARROW_LEFT ||
            type == StructurizrTypes.ARROW_BOTH
        ) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.textRange)
                .textAttributes(ARROW_OPERATOR)
                .create()
        }

        // Highlight deprecated !ref directive
        if (type == StructurizrTypes.REF) {
            val parent = element.parent
            if (parent != null && parent.node.elementType == StructurizrTypes.REF_STATEMENT) {
                val strikethrough = TextAttributes().apply {
                    effectType = EffectType.STRIKEOUT
                    effectColor = Color.GRAY
                }
                holder.newAnnotation(HighlightSeverity.WARNING, "!ref is deprecated, use !element instead")
                    .range(element.textRange)
                    .textAttributes(DEPRECATED_DIRECTIVE)
                    .enforcedTextAttributes(strikethrough)
                    .create()
            }
        }

        // Highlight deprecated !constant directive
        if (type == StructurizrTypes.DIRECTIVE_CONSTANT) {
            val parent = element.parent
            if (parent != null && parent.node.elementType == StructurizrTypes.CONSTANT_DIRECTIVE) {
                val strikethrough = TextAttributes().apply {
                    effectType = EffectType.STRIKEOUT
                    effectColor = Color.GRAY
                }
                holder.newAnnotation(HighlightSeverity.WARNING, "!constant is deprecated, use !const instead")
                    .range(element.textRange)
                    .textAttributes(DEPRECATED_DIRECTIVE)
                    .enforcedTextAttributes(strikethrough)
                    .create()
            }
        }
    }

    companion object {
        /** Highlighting key for `${...}` variable references inside strings. */
        @JvmField
        val VARIABLE_REFERENCE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
            "STRUCTURIZR_VARIABLE_REFERENCE", DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR
        )

        /** Highlighting key for identifiers at their definition site. */
        @JvmField
        val DEFINITION_IDENTIFIER: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
            "STRUCTURIZR_DEFINITION_IDENTIFIER", DefaultLanguageHighlighterColors.INSTANCE_FIELD
        )

        /** Highlighting key for arrow operators (`->`, `<-`, `<->`). */
        @JvmField
        val ARROW_OPERATOR: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
            "STRUCTURIZR_ARROW_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN
        )

        /** Highlighting key for deprecated directives. */
        @JvmField
        val DEPRECATED_DIRECTIVE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
            "STRUCTURIZR_DEPRECATED_DIRECTIVE", DefaultLanguageHighlighterColors.METADATA
        )

        private val SUBSTITUTION_PATTERN: Pattern = Pattern.compile("\\$\\{[^}]+\\}")
    }
}
