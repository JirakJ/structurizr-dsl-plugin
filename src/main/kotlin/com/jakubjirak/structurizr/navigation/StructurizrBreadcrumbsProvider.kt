/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.navigation

import com.intellij.lang.Language
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.elementType
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider
import com.jakubjirak.structurizr.language.StructurizrLanguage
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Provides breadcrumb navigation for Structurizr DSL files.
 *
 * Displays hierarchical context (workspace > model > person, etc.)
 * in the editor breadcrumb bar.
 */
class StructurizrBreadcrumbsProvider : BreadcrumbsProvider {

    companion object {
        private val ACCEPTED_TYPES: Set<IElementType> = setOf(
            StructurizrTypes.WORKSPACE_BLOCK,
            StructurizrTypes.MODEL_BLOCK,
            StructurizrTypes.VIEWS_BLOCK,
            StructurizrTypes.STYLES_BLOCK,
            StructurizrTypes.CONFIGURATION_BLOCK,
            StructurizrTypes.PERSON_DEFINITION,
            StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION,
            StructurizrTypes.CONTAINER_DEFINITION,
            StructurizrTypes.COMPONENT_DEFINITION,
            StructurizrTypes.GROUP_BLOCK,
            StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK,
            StructurizrTypes.DEPLOYMENT_NODE_DEFINITION,
            StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION,
            StructurizrTypes.ENTERPRISE_BLOCK,
            StructurizrTypes.ELEMENT_ASSIGNMENT,
            StructurizrTypes.SYSTEM_LANDSCAPE_VIEW,
            StructurizrTypes.SYSTEM_CONTEXT_VIEW,
            StructurizrTypes.CONTAINER_VIEW,
            StructurizrTypes.COMPONENT_VIEW,
            StructurizrTypes.DEPLOYMENT_VIEW,
            StructurizrTypes.DYNAMIC_VIEW,
            StructurizrTypes.CUSTOM_VIEW,
            StructurizrTypes.FILTERED_VIEW,
            StructurizrTypes.IMAGE_VIEW,
        )

        private fun getTypeName(type: IElementType?): String = when (type) {
            StructurizrTypes.WORKSPACE_BLOCK -> "workspace"
            StructurizrTypes.MODEL_BLOCK -> "model"
            StructurizrTypes.VIEWS_BLOCK -> "views"
            StructurizrTypes.STYLES_BLOCK -> "styles"
            StructurizrTypes.CONFIGURATION_BLOCK -> "configuration"
            StructurizrTypes.PERSON_DEFINITION -> "person"
            StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION -> "softwareSystem"
            StructurizrTypes.CONTAINER_DEFINITION -> "container"
            StructurizrTypes.COMPONENT_DEFINITION -> "component"
            StructurizrTypes.GROUP_BLOCK -> "group"
            StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK -> "deploymentEnvironment"
            StructurizrTypes.DEPLOYMENT_NODE_DEFINITION -> "deploymentNode"
            StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION -> "infrastructureNode"
            StructurizrTypes.ENTERPRISE_BLOCK -> "enterprise"
            StructurizrTypes.ELEMENT_ASSIGNMENT -> "element"
            StructurizrTypes.SYSTEM_LANDSCAPE_VIEW -> "systemLandscape"
            StructurizrTypes.SYSTEM_CONTEXT_VIEW -> "systemContext"
            StructurizrTypes.CONTAINER_VIEW -> "container view"
            StructurizrTypes.COMPONENT_VIEW -> "component view"
            StructurizrTypes.DEPLOYMENT_VIEW -> "deployment view"
            StructurizrTypes.DYNAMIC_VIEW -> "dynamic view"
            StructurizrTypes.CUSTOM_VIEW -> "custom view"
            StructurizrTypes.FILTERED_VIEW -> "filtered view"
            StructurizrTypes.IMAGE_VIEW -> "image view"
            else -> type.toString()
        }

        private fun extractName(element: PsiElement, type: IElementType?): String? {
            if (type == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                element.node.findChildByType(StructurizrTypes.IDENTIFIER)?.let { return it.text }
            }
            return element.node.findChildByType(StructurizrTypes.STRING)?.text?.let { text ->
                if (text.length >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
                    text.substring(1, text.length - 1)
                } else {
                    text
                }
            }
        }
    }

    /** Returns the languages supported by this breadcrumbs provider. */
    override fun getLanguages(): Array<Language> = arrayOf(StructurizrLanguage)

    /** Determines whether the given [element] should appear in the breadcrumb bar. */
    override fun acceptElement(element: PsiElement): Boolean =
        element.elementType in ACCEPTED_TYPES

    /** Returns the display text for the given [element] in the breadcrumb bar. */
    override fun getElementInfo(element: PsiElement): String {
        val type = element.elementType
        val typeName = getTypeName(type)
        val name = extractName(element, type)
        return name?.let { "$typeName: $it" } ?: typeName
    }

    /** Returns the tooltip text for the given [element] in the breadcrumb bar. */
    override fun getElementTooltip(element: PsiElement): String {
        val type = element.elementType
        return "Structurizr ${getTypeName(type)}"
    }
}
