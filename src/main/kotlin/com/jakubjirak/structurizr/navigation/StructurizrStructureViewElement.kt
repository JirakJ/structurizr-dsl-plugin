/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.navigation

import com.intellij.icons.AllIcons
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.jakubjirak.structurizr.language.StructurizrIcons
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import javax.swing.Icon

/**
 * A single element in the Structurizr DSL Structure View tree.
 *
 * Wraps a [PsiElement] and provides presentation (icon, text) and
 * child discovery for the Structure tool window.
 */
class StructurizrStructureViewElement(
    private val element: PsiElement,
) : StructureViewTreeElement {

    companion object {
        private val STRUCTURE_TYPES: Set<IElementType> = setOf(
            StructurizrTypes.WORKSPACE_BLOCK,
            StructurizrTypes.MODEL_BLOCK,
            StructurizrTypes.VIEWS_BLOCK,
            StructurizrTypes.STYLES_BLOCK,
            StructurizrTypes.CONFIGURATION_BLOCK,
            StructurizrTypes.PERSON_DEFINITION,
            StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION,
            StructurizrTypes.CONTAINER_DEFINITION,
            StructurizrTypes.COMPONENT_DEFINITION,
            StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK,
            StructurizrTypes.DEPLOYMENT_NODE_DEFINITION,
            StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION,
            StructurizrTypes.GROUP_BLOCK,
            StructurizrTypes.ELEMENT_ASSIGNMENT,
            StructurizrTypes.ENTERPRISE_BLOCK,
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

        private val VIEW_TYPES: Set<IElementType> = setOf(
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

        private fun extractIdentifierFromAssignment(assignment: PsiElement): String? {
            for (child in assignment.children) {
                if (child.node.elementType == StructurizrTypes.IDENTIFIER) {
                    return child.text
                }
            }
            return assignment.node.findChildByType(StructurizrTypes.IDENTIFIER)?.text
        }

        private fun extractDefinitionType(assignment: PsiElement): String? {
            for (child in assignment.children) {
                when (child.node.elementType) {
                    StructurizrTypes.PERSON_DEFINITION -> return "person"
                    StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION -> return "softwareSystem"
                    StructurizrTypes.CONTAINER_DEFINITION -> return "container"
                    StructurizrTypes.COMPONENT_DEFINITION -> return "component"
                    StructurizrTypes.DEPLOYMENT_NODE_DEFINITION -> return "deploymentNode"
                    StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION -> return "infrastructureNode"
                }
            }
            return null
        }

        private fun extractFirstString(element: PsiElement): String? =
            element.node.findChildByType(StructurizrTypes.STRING)?.text?.let { text ->
                if (text.length >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
                    text.substring(1, text.length - 1)
                } else {
                    text
                }
            }

        private fun getElementTypeName(type: IElementType): String = when (type) {
            StructurizrTypes.WORKSPACE_BLOCK -> "workspace"
            StructurizrTypes.MODEL_BLOCK -> "model"
            StructurizrTypes.VIEWS_BLOCK -> "views"
            StructurizrTypes.STYLES_BLOCK -> "styles"
            StructurizrTypes.CONFIGURATION_BLOCK -> "configuration"
            StructurizrTypes.PERSON_DEFINITION -> "person"
            StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION -> "softwareSystem"
            StructurizrTypes.CONTAINER_DEFINITION -> "container"
            StructurizrTypes.COMPONENT_DEFINITION -> "component"
            StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK -> "deploymentEnvironment"
            StructurizrTypes.DEPLOYMENT_NODE_DEFINITION -> "deploymentNode"
            StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION -> "infrastructureNode"
            StructurizrTypes.GROUP_BLOCK -> "group"
            StructurizrTypes.ENTERPRISE_BLOCK -> "enterprise"
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

        private fun getIconForAssignment(assignment: PsiElement): Icon {
            for (child in assignment.children) {
                when (child.node.elementType) {
                    StructurizrTypes.PERSON_DEFINITION -> return StructurizrIcons.PERSON
                    StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION -> return StructurizrIcons.SOFTWARE_SYSTEM
                    StructurizrTypes.CONTAINER_DEFINITION -> return StructurizrIcons.CONTAINER
                    StructurizrTypes.COMPONENT_DEFINITION -> return StructurizrIcons.COMPONENT
                    StructurizrTypes.DEPLOYMENT_NODE_DEFINITION -> return StructurizrIcons.DEPLOYMENT
                    StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION -> return StructurizrIcons.DEPLOYMENT
                }
            }
            return StructurizrIcons.FILE
        }
    }

    /** Returns the underlying PSI element. */
    override fun getValue(): Any = element

    /** Navigates to this element in the editor. */
    override fun navigate(requestFocus: Boolean) {
        (element as? Navigatable)?.navigate(requestFocus)
    }

    /** Returns `true` if navigation to this element is possible. */
    override fun canNavigate(): Boolean =
        (element as? Navigatable)?.canNavigate() == true

    /** Returns `true` if navigation to the source of this element is possible. */
    override fun canNavigateToSource(): Boolean =
        (element as? Navigatable)?.canNavigateToSource() == true

    /** Returns the visual presentation (icon, text) for this element in the tree. */
    override fun getPresentation(): ItemPresentation = object : ItemPresentation {
        override fun getPresentableText(): String {
            if (element is PsiFile) return element.name

            val type = element.node.elementType
            if (type == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                val name = extractIdentifierFromAssignment(element)
                val defType = extractDefinitionType(element)
                if (name != null) {
                    return if (defType != null) "$name ($defType)" else name
                }
            }

            val nameFromString = extractFirstString(element)
            val typeName = getElementTypeName(type)
            return nameFromString?.let { "$typeName: $it" } ?: typeName
        }

        override fun getLocationString(): String? = null

        override fun getIcon(unused: Boolean): Icon {
            if (element is PsiFile) return StructurizrIcons.FILE

            val type = element.node.elementType
            return when (type) {
                StructurizrTypes.WORKSPACE_BLOCK -> StructurizrIcons.WORKSPACE
                StructurizrTypes.MODEL_BLOCK -> StructurizrIcons.MODEL
                StructurizrTypes.VIEWS_BLOCK -> StructurizrIcons.VIEWS
                StructurizrTypes.STYLES_BLOCK -> StructurizrIcons.STYLES
                StructurizrTypes.CONFIGURATION_BLOCK -> AllIcons.General.Settings
                StructurizrTypes.PERSON_DEFINITION -> StructurizrIcons.PERSON
                StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION -> StructurizrIcons.SOFTWARE_SYSTEM
                StructurizrTypes.CONTAINER_DEFINITION -> StructurizrIcons.CONTAINER
                StructurizrTypes.COMPONENT_DEFINITION -> StructurizrIcons.COMPONENT
                StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK -> StructurizrIcons.DEPLOYMENT
                StructurizrTypes.DEPLOYMENT_NODE_DEFINITION -> StructurizrIcons.DEPLOYMENT
                StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION -> StructurizrIcons.DEPLOYMENT
                StructurizrTypes.GROUP_BLOCK -> AllIcons.Nodes.Folder
                StructurizrTypes.ENTERPRISE_BLOCK -> AllIcons.Nodes.Folder
                StructurizrTypes.ELEMENT_ASSIGNMENT -> getIconForAssignment(element)
                in VIEW_TYPES -> StructurizrIcons.VIEWS
                else -> StructurizrIcons.FILE
            }
        }
    }

    /** Returns the child elements to display under this node in the structure tree. */
    override fun getChildren(): Array<TreeElement> {
        val children = mutableListOf<TreeElement>()
        for (child in element.children) {
            val childType = child.node.elementType
            if (childType in STRUCTURE_TYPES) {
                children.add(StructurizrStructureViewElement(child))
            } else {
                collectStructureChildren(child, children)
            }
        }
        return children.toTypedArray()
    }

    private fun collectStructureChildren(parent: PsiElement, result: MutableList<TreeElement>) {
        for (child in parent.children) {
            if (child.node.elementType in STRUCTURE_TYPES) {
                result.add(StructurizrStructureViewElement(child))
            }
        }
    }
}
