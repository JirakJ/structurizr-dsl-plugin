/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.jakubjirak.structurizr.language.lexer.StructurizrLexerAdapter
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Syntax highlighter for the Structurizr DSL language.
 *
 * Maps lexer token types to [TextAttributesKey] instances so that the editor
 * can colour-code keywords, identifiers, literals, operators, and other
 * syntactic elements of the Structurizr DSL.
 */
class StructurizrSyntaxHighlighter : SyntaxHighlighterBase() {

    /** Returns the lexer used for highlighting. */
    override fun getHighlightingLexer(): Lexer = StructurizrLexerAdapter()

    /**
     * Returns the set of [TextAttributesKey]s that should be applied to the
     * given [tokenType].
     */
    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> =
        when (tokenType) {
            // Core keywords
            StructurizrTypes.WORKSPACE,
            StructurizrTypes.MODEL,
            StructurizrTypes.VIEWS,
            StructurizrTypes.CONFIGURATION -> KEYWORD_KEYS

            // Element keywords
            StructurizrTypes.PERSON,
            StructurizrTypes.SOFTWARE_SYSTEM,
            StructurizrTypes.CONTAINER,
            StructurizrTypes.COMPONENT,
            StructurizrTypes.GROUP,
            StructurizrTypes.ENTERPRISE -> ELEMENT_KEYWORD_KEYS

            // Deployment keywords
            StructurizrTypes.DEPLOYMENT_ENVIRONMENT,
            StructurizrTypes.DEPLOYMENT_GROUP,
            StructurizrTypes.DEPLOYMENT_NODE,
            StructurizrTypes.INFRASTRUCTURE_NODE,
            StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE,
            StructurizrTypes.CONTAINER_INSTANCE -> DEPLOYMENT_KEYWORD_KEYS

            // View keywords
            StructurizrTypes.SYSTEM_LANDSCAPE,
            StructurizrTypes.SYSTEM_CONTEXT,
            StructurizrTypes.DYNAMIC,
            StructurizrTypes.DEPLOYMENT,
            StructurizrTypes.FILTERED,
            StructurizrTypes.CUSTOM,
            StructurizrTypes.IMAGE -> VIEW_KEYWORD_KEYS

            // View content keywords
            StructurizrTypes.INCLUDE,
            StructurizrTypes.EXCLUDE,
            StructurizrTypes.AUTO_LAYOUT,
            StructurizrTypes.DEFAULT,
            StructurizrTypes.ANIMATION -> VIEW_CONTENT_KEYWORD_KEYS

            // Style keywords
            StructurizrTypes.STYLES,
            StructurizrTypes.ELEMENT,
            StructurizrTypes.RELATIONSHIP,
            StructurizrTypes.THEMES,
            StructurizrTypes.THEME,
            StructurizrTypes.BRANDING,
            StructurizrTypes.TERMINOLOGY -> STYLE_KEYWORD_KEYS

            // Property keywords
            StructurizrTypes.SHAPE,
            StructurizrTypes.ICON,
            StructurizrTypes.WIDTH,
            StructurizrTypes.HEIGHT,
            StructurizrTypes.BACKGROUND,
            StructurizrTypes.COLOR,
            StructurizrTypes.COLOUR,
            StructurizrTypes.STROKE,
            StructurizrTypes.STROKE_WIDTH,
            StructurizrTypes.FONT_SIZE,
            StructurizrTypes.BORDER,
            StructurizrTypes.OPACITY,
            StructurizrTypes.METADATA,
            StructurizrTypes.THICKNESS,
            StructurizrTypes.STYLE_PROP,
            StructurizrTypes.ROUTING,
            StructurizrTypes.POSITION,
            StructurizrTypes.DASHED -> PROPERTY_KEYS

            // Shape constants
            StructurizrTypes.SHAPE_BOX,
            StructurizrTypes.SHAPE_ROUNDED_BOX,
            StructurizrTypes.SHAPE_CIRCLE,
            StructurizrTypes.SHAPE_ELLIPSE,
            StructurizrTypes.SHAPE_HEXAGON,
            StructurizrTypes.SHAPE_CYLINDER,
            StructurizrTypes.SHAPE_PIPE,
            StructurizrTypes.SHAPE_PERSON,
            StructurizrTypes.SHAPE_ROBOT,
            StructurizrTypes.SHAPE_FOLDER,
            StructurizrTypes.SHAPE_WEB_BROWSER,
            StructurizrTypes.SHAPE_MOBILE_PORTRAIT,
            StructurizrTypes.SHAPE_MOBILE_LANDSCAPE,
            StructurizrTypes.SHAPE_COMPONENT -> CONSTANT_KEYS

            // Layout direction constants
            StructurizrTypes.LAYOUT_TB,
            StructurizrTypes.LAYOUT_BT,
            StructurizrTypes.LAYOUT_LR,
            StructurizrTypes.LAYOUT_RL -> CONSTANT_KEYS

            // Metadata-like keywords
            StructurizrTypes.TITLE,
            StructurizrTypes.DESCRIPTION,
            StructurizrTypes.TAGS,
            StructurizrTypes.URL,
            StructurizrTypes.PROPERTIES,
            StructurizrTypes.PERSPECTIVES,
            StructurizrTypes.USERS,
            StructurizrTypes.EXTENDS,
            StructurizrTypes.REF,
            StructurizrTypes.THIS -> KEYWORD_KEYS

            // Directives
            StructurizrTypes.DIRECTIVE_INCLUDE,
            StructurizrTypes.DIRECTIVE_DOCS,
            StructurizrTypes.DIRECTIVE_ADRS,
            StructurizrTypes.DIRECTIVE_IDENTIFIERS,
            StructurizrTypes.DIRECTIVE_IMPLIED_RELATIONSHIPS,
            StructurizrTypes.DIRECTIVE_CONSTANT,
            StructurizrTypes.DIRECTIVE_CONST,
            StructurizrTypes.DIRECTIVE_VAR,
            StructurizrTypes.DIRECTIVE_PLUGIN,
            StructurizrTypes.DIRECTIVE_SCRIPT -> DIRECTIVE_KEYS

            // Identifier mode constants
            StructurizrTypes.HIERARCHICAL,
            StructurizrTypes.FLAT -> CONSTANT_KEYS

            // Boolean constants
            StructurizrTypes.TRUE,
            StructurizrTypes.FALSE -> CONSTANT_KEYS

            // Access keywords
            StructurizrTypes.READ,
            StructurizrTypes.WRITE -> KEYWORD_KEYS

            // Visibility keywords
            StructurizrTypes.PRIVATE,
            StructurizrTypes.PUBLIC -> KEYWORD_KEYS

            // Arrows
            StructurizrTypes.ARROW,
            StructurizrTypes.ARROW_LEFT,
            StructurizrTypes.ARROW_BOTH -> ARROW_KEYS

            // Operators
            StructurizrTypes.EQUALS -> OPERATOR_KEYS

            // Braces
            StructurizrTypes.LBRACE,
            StructurizrTypes.RBRACE -> BRACES_KEYS

            // Punctuation operators
            StructurizrTypes.COMMA,
            StructurizrTypes.DOT -> OPERATOR_KEYS

            // Star
            StructurizrTypes.STAR -> STAR_KEYS

            // Bang
            StructurizrTypes.BANG -> OPERATOR_KEYS

            // Literals
            StructurizrTypes.STRING -> STRING_KEYS
            StructurizrTypes.NUMBER -> NUMBER_KEYS
            StructurizrTypes.COLOR_LITERAL -> COLOR_KEYS

            // Identifiers
            StructurizrTypes.IDENTIFIER -> IDENTIFIER_KEYS

            // Comments
            StructurizrTypes.LINE_COMMENT,
            StructurizrTypes.BLOCK_COMMENT -> COMMENT_KEYS

            else -> BAD_CHARACTER_KEYS
        }

    companion object {
        @JvmField
        val KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

        @JvmField
        val ELEMENT_KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_ELEMENT_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

        @JvmField
        val DEPLOYMENT_KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_DEPLOYMENT_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

        @JvmField
        val VIEW_KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_VIEW_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

        @JvmField
        val VIEW_CONTENT_KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_VIEW_CONTENT_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

        @JvmField
        val STYLE_KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_STYLE_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

        @JvmField
        val PROPERTY: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_PROPERTY", DefaultLanguageHighlighterColors.INSTANCE_FIELD)

        @JvmField
        val DIRECTIVE: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_DIRECTIVE", DefaultLanguageHighlighterColors.METADATA)

        @JvmField
        val CONSTANT: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT)

        @JvmField
        val STRING: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_STRING", DefaultLanguageHighlighterColors.STRING)

        @JvmField
        val NUMBER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_NUMBER", DefaultLanguageHighlighterColors.NUMBER)

        @JvmField
        val COLOR: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_COLOR", DefaultLanguageHighlighterColors.NUMBER)

        @JvmField
        val IDENTIFIER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)

        @JvmField
        val COMMENT: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)

        @JvmField
        val OPERATOR: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)

        @JvmField
        val ARROW: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_ARROW", DefaultLanguageHighlighterColors.OPERATION_SIGN)

        @JvmField
        val BRACES: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_BRACES", DefaultLanguageHighlighterColors.BRACES)

        @JvmField
        val STAR: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_STAR", DefaultLanguageHighlighterColors.OPERATION_SIGN)

        @JvmField
        val BAD_CHARACTER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("STRUCTURIZR_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val ELEMENT_KEYWORD_KEYS = arrayOf(ELEMENT_KEYWORD)
        private val DEPLOYMENT_KEYWORD_KEYS = arrayOf(DEPLOYMENT_KEYWORD)
        private val VIEW_KEYWORD_KEYS = arrayOf(VIEW_KEYWORD)
        private val VIEW_CONTENT_KEYWORD_KEYS = arrayOf(VIEW_CONTENT_KEYWORD)
        private val STYLE_KEYWORD_KEYS = arrayOf(STYLE_KEYWORD)
        private val PROPERTY_KEYS = arrayOf(PROPERTY)
        private val DIRECTIVE_KEYS = arrayOf(DIRECTIVE)
        private val CONSTANT_KEYS = arrayOf(CONSTANT)
        private val STRING_KEYS = arrayOf(STRING)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val COLOR_KEYS = arrayOf(COLOR)
        private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val OPERATOR_KEYS = arrayOf(OPERATOR)
        private val ARROW_KEYS = arrayOf(ARROW)
        private val BRACES_KEYS = arrayOf(BRACES)
        private val STAR_KEYS = arrayOf(STAR)
        private val BAD_CHARACTER_KEYS = arrayOf(BAD_CHARACTER)
    }
}
