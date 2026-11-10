/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.highlighting

import com.intellij.openapi.editor.ElementColorProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.jakubjirak.structurizr.language.psi.StructurizrStyleProperty
import java.awt.Color

/**
 * Shows a color swatch in the editor gutter for hex color values in style
 * properties (`background`, `color`, `colour`, `stroke`) and lets the user
 * change them via the platform color picker.
 */
class StructurizrColorProvider : ElementColorProvider {

    override fun getColorFrom(element: PsiElement): Color? {
        if (element !is LeafPsiElement) return null

        val styleProperty = PsiTreeUtil.getParentOfType(element, StructurizrStyleProperty::class.java) ?: return null
        if (styleProperty.stylePropertyName?.text !in COLOR_PROPERTY_NAMES) return null

        val valueElement = styleProperty.stylePropertyValue ?: return null
        if (!PsiTreeUtil.isAncestor(valueElement, element, false)) return null

        return parseColor(element.text)
    }

    override fun setColorTo(element: PsiElement, color: Color) {
        if (element !is LeafPsiElement) return

        val original = element.text
        val quoted = original.startsWith("\"") && original.endsWith("\"")
        val hasAlpha = (if (quoted) original.removeSurrounding("\"") else original).trim().length == 9

        val hex = toHex(color, hasAlpha || color.alpha != 255)
        element.replaceWithText(if (quoted) "\"$hex\"" else hex)
    }

    companion object {
        /** Style property names whose values are colors. */
        val COLOR_PROPERTY_NAMES: Set<String> = setOf("background", "color", "colour", "stroke")

        private val HEX_COLOR = Regex("^#([0-9A-Fa-f]{3}|[0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$")

        /**
         * Parses a Structurizr hex color literal (optionally quoted) into a [Color].
         *
         * Supports `#RGB`, `#RRGGBB`, and `#RRGGBBAA`.
         *
         * @return the parsed color, or null when the text is not a valid hex color
         */
        fun parseColor(text: String?): Color? {
            val value = text?.trim()?.removeSurrounding("\"")?.trim() ?: return null
            if (!HEX_COLOR.matches(value)) return null

            val hex = value.removePrefix("#")
            return when (hex.length) {
                3 -> Color(
                    hex[0].digitToInt(16) * 17,
                    hex[1].digitToInt(16) * 17,
                    hex[2].digitToInt(16) * 17
                )
                6 -> Color(hex.toInt(16))
                8 -> Color(
                    hex.substring(0, 2).toInt(16),
                    hex.substring(2, 4).toInt(16),
                    hex.substring(4, 6).toInt(16),
                    hex.substring(6, 8).toInt(16)
                )
                else -> null
            }
        }

        /**
         * Formats a [Color] as a Structurizr hex literal — `#rrggbb`, or
         * `#rrggbbaa` when [includeAlpha] is true.
         */
        fun toHex(color: Color, includeAlpha: Boolean = false): String =
            if (includeAlpha) {
                String.format("#%02x%02x%02x%02x", color.red, color.green, color.blue, color.alpha)
            } else {
                String.format("#%02x%02x%02x", color.red, color.green, color.blue)
            }
    }
}
