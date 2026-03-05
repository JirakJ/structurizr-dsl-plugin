/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.jakubjirak.structurizr.language.psi.StructurizrStyleProperty

/**
 * Inspection that validates color values in Structurizr style properties.
 */
class InvalidColorInspection : StructurizrInspectionBase() {

    private val colorRegex = Regex("^#([0-9A-Fa-f]{3}|[0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$")

    override fun getDisplayName(): String = "Invalid color value"

    override fun getShortName(): String = "StructurizrInvalidColor"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : PsiElementVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is StructurizrStyleProperty) {
                    checkColorProperty(element, holder)
                }
            }
        }

    private fun checkColorProperty(element: StructurizrStyleProperty, holder: ProblemsHolder) {
        val propertyName = element.stylePropertyName?.text
        val propertyValue = element.stylePropertyValue?.text?.trim('"')

        if (propertyName in COLOR_PROPERTIES && propertyValue != null &&
            propertyValue.startsWith("#") && !colorRegex.matches(propertyValue)
        ) {
            holder.registerProblem(
                element.stylePropertyValue ?: element,
                "Invalid color format: '$propertyValue'. Expected #RGB, #RRGGBB, or #RRGGBBAA",
                ProblemHighlightType.ERROR
            )
        }
    }

    companion object {
        private val COLOR_PROPERTIES = setOf("background", "color", "stroke")
    }
}
