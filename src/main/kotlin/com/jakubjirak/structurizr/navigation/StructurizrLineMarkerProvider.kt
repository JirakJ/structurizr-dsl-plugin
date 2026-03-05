/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.navigation

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import com.jakubjirak.structurizr.language.StructurizrIcons
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import javax.swing.Icon

/**
 * Provides gutter icons (line markers) for Structurizr DSL definitions.
 *
 * Marks persons, software systems, containers, components, deployment nodes,
 * infrastructure nodes, and relationships with their respective icons.
 */
class StructurizrLineMarkerProvider : RelatedItemLineMarkerProvider() {

    /** Collects navigation markers for the given [element] into [result]. */
    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>>,
    ) {
        val elementType = element.node.elementType
        val parent = element.parent ?: return
        val parentType = parent.node.elementType

        when {
            elementType == StructurizrTypes.PERSON && parentType == StructurizrTypes.PERSON_DEFINITION ->
                addMarker(element, StructurizrIcons.PERSON, "Person definition", result)
            elementType == StructurizrTypes.SOFTWARE_SYSTEM && parentType == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION ->
                addMarker(element, StructurizrIcons.SOFTWARE_SYSTEM, "Software System definition", result)
            elementType == StructurizrTypes.CONTAINER && parentType == StructurizrTypes.CONTAINER_DEFINITION ->
                addMarker(element, StructurizrIcons.CONTAINER, "Container definition", result)
            elementType == StructurizrTypes.COMPONENT && parentType == StructurizrTypes.COMPONENT_DEFINITION ->
                addMarker(element, StructurizrIcons.COMPONENT, "Component definition", result)
            elementType == StructurizrTypes.DEPLOYMENT_NODE && parentType == StructurizrTypes.DEPLOYMENT_NODE_DEFINITION ->
                addMarker(element, StructurizrIcons.DEPLOYMENT, "Deployment Node definition", result)
            elementType == StructurizrTypes.INFRASTRUCTURE_NODE && parentType == StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION ->
                addMarker(element, StructurizrIcons.DEPLOYMENT, "Infrastructure Node definition", result)
            elementType == StructurizrTypes.ARROW && parentType == StructurizrTypes.RELATIONSHIP_STATEMENT ->
                addMarker(element, StructurizrIcons.RELATIONSHIP, "Relationship", result)
        }
    }

    companion object {
        private fun addMarker(
            element: PsiElement,
            icon: Icon,
            tooltip: String,
            result: MutableCollection<in RelatedItemLineMarkerInfo<*>>,
        ) {
            val builder = NavigationGutterIconBuilder.create(icon)
                .setTargets(element)
                .setTooltipText(tooltip)
            result.add(builder.createLineMarkerInfo(element))
        }
    }
}
