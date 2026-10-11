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
import com.intellij.psi.tree.IElementType
import com.jakubjirak.structurizr.language.ancestorsInFile
import com.jakubjirak.structurizr.language.elementTypeOrNull
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import java.awt.Color
import java.util.regex.Pattern

/**
 * Annotator that provides additional semantic highlighting for Structurizr DSL
 * elements beyond what the lexer-based [StructurizrSyntaxHighlighter] offers.
 *
 * Handles:
 * - Variable substitutions inside strings (`${...}`)
 * - Definition-site identifiers (before `=`)
 * - Identifiers and dots in variable contexts (relationships, include/exclude, animation)
 * - Missing keyword recognition (name, logo, scope, visibility, etc.)
 * - Arrow operators
 * - Deprecated directives
 */
class StructurizrAnnotator : Annotator {

    /**
     * Annotates the given [element] with semantic highlighting information.
     */
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val type = element.node?.elementType ?: return

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

        // Semantic highlighting for identifiers
        if (type == StructurizrTypes.IDENTIFIER) {
            val parent = element.parent

            // 1. Definition-site identifier (before = in element assignment)
            if (parent?.elementTypeOrNull == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(DEFINITION_IDENTIFIER)
                    .create()
                return
            }

            // 2. Identifier in a variable context (relationship, include/exclude, animation)
            if (isInVariableContext(element)) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(VARIABLE)
                    .create()
                return
            }

            // 3. Missing keyword recognition — keywords in the ACE editor that our lexer
            //    doesn't recognize as separate token types
            if (element.text in ACE_KEYWORDS) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(StructurizrSyntaxHighlighter.KEYWORD)
                    .create()
                return
            }
        }

        // Highlight dots in variable contexts (qualified identifiers like ss.wa in relationships)
        if (type == StructurizrTypes.DOT && isInVariableContext(element)) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.textRange)
                .textAttributes(VARIABLE)
                .create()
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
            if (parent?.elementTypeOrNull == StructurizrTypes.REF_STATEMENT) {
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
            if (parent?.elementTypeOrNull == StructurizrTypes.CONSTANT_DIRECTIVE) {
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

    /**
     * Checks whether the given element is inside a "variable" context where
     * identifiers should be colored red (matching the ACE editor's variable token).
     *
     * Variable contexts are: relationship statements, include/exclude,
     * animation blocks, systemContext views (special ACE rule),
     * and instance definitions (special ACE rule).
     */
    private fun isInVariableContext(element: PsiElement): Boolean {
        for (parent in element.ancestorsInFile()) {
            when (parent.elementTypeOrNull) {
                // Contexts where identifiers are red (ACE variable token)
                StructurizrTypes.RELATIONSHIP_STATEMENT,
                StructurizrTypes.RELATIONSHIP_IN_DYNAMIC_VIEW,
                StructurizrTypes.INCLUDE_STATEMENT,
                StructurizrTypes.EXCLUDE_STATEMENT,
                StructurizrTypes.ANIMATION_BLOCK,
                StructurizrTypes.ANIMATION_STEP,
                // Special ACE rules: systemContext has (.+?) group as variable
                StructurizrTypes.SYSTEM_CONTEXT_VIEW,
                // Special ACE rules: softwareSystemInstance/containerInstance have (.*)$ as variable
                StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE_DEFINITION,
                StructurizrTypes.CONTAINER_INSTANCE_DEFINITION -> return true
                // Stop at block-level boundaries to avoid walking the entire tree
                StructurizrTypes.WORKSPACE_BLOCK,
                StructurizrTypes.MODEL_BLOCK,
                StructurizrTypes.VIEWS_BLOCK,
                StructurizrTypes.STYLES_BLOCK,
                StructurizrTypes.CONFIGURATION_BLOCK,
                StructurizrTypes.ELEMENT_STYLE,
                StructurizrTypes.RELATIONSHIP_STYLE -> return false
            }
        }
        return false
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

        /** Highlighting key for identifiers in variable contexts (relationships, includes). */
        @JvmField
        val VARIABLE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
            "STRUCTURIZR_VARIABLE", DefaultLanguageHighlighterColors.IDENTIFIER
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

        /**
         * Keywords recognized by the ACE editor on structurizr.com but missing from
         * our lexer's token types. These are colored as keywords when they appear as
         * standalone identifiers (not inside variable contexts).
         */
        private val ACE_KEYWORDS = setOf(
            "name", "logo", "scope", "visibility",
            "animationStep", "instanceOf", "light", "dark"
        )
    }
}
