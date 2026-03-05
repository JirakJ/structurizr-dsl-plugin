/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.refactoring

import com.intellij.lang.refactoring.NamesValidator
import com.intellij.openapi.project.Project

/**
 * Validates identifiers and keywords for the Structurizr DSL language.
 */
class StructurizrNamesValidator : NamesValidator {

    companion object {
        private val KEYWORDS = setOf(
            "workspace", "model", "views", "styles", "configuration",
            "person", "softwareSystem", "container", "component",
            "deploymentEnvironment", "deploymentNode", "deploymentGroup",
            "infrastructureNode", "containerInstance", "softwareSystemInstance",
            "group", "enterprise", "element", "relationship",
            "tags", "description", "technology", "url",
            "properties", "perspectives", "autoLayout", "animation",
            "title", "include", "exclude", "default",
            "systemLandscape", "systemContext", "dynamic", "deployment",
            "custom", "filtered", "image", "theme", "themes",
            "branding", "terminology", "users", "ref",
            "this", "true", "false"
        )
    }

    /** Returns whether the given [name] is a Structurizr keyword. */
    override fun isKeyword(name: String, project: Project?): Boolean = name in KEYWORDS

    /** Returns whether the given [name] is a valid Structurizr identifier. */
    override fun isIdentifier(name: String, project: Project?): Boolean {
        if (name.isEmpty()) return false
        val first = name[0]
        if (!first.isLetter() && first != '_') return false
        for (i in 1 until name.length) {
            val c = name[i]
            if (!c.isLetterOrDigit() && c != '_') return false
        }
        return !isKeyword(name, null)
    }
}
