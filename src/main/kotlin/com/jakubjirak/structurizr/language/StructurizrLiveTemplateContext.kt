/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.language

import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType

/**
 * Defines the live template context for Structurizr DSL files.
 *
 * Live templates assigned to the "Structurizr DSL" context will only be available
 * when editing files with the `.dsl` extension.
 */
class StructurizrLiveTemplateContext : TemplateContextType("Structurizr DSL") {

    /** Returns `true` if the current file is a Structurizr DSL file (ends with `.dsl`). */
    override fun isInContext(templateActionContext: TemplateActionContext): Boolean =
        templateActionContext.file.name.endsWith(".dsl")
}
