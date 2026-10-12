/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.preview.dsl

/** Kinds of model elements the preview understands. */
enum class DslElementType(val displayName: String, val defaultTag: String) {
    PERSON("Person", "Person"),
    SOFTWARE_SYSTEM("Software System", "Software System"),
    CONTAINER("Container", "Container"),
    COMPONENT("Component", "Component"),
    DEPLOYMENT_NODE("Deployment Node", "Deployment Node"),
    INFRASTRUCTURE_NODE("Infrastructure Node", "Infrastructure Node"),
    SOFTWARE_SYSTEM_INSTANCE("Software System Instance", "Software System Instance"),
    CONTAINER_INSTANCE("Container Instance", "Container Instance"),
}

/** A model element. [parent] is the enclosing element (system for a container, node for an instance, ...). */
class DslElement(
    val uid: Int,
    val identifier: String?,
    val type: DslElementType,
    var name: String,
    var description: String = "",
    var technology: String = "",
    val parent: DslElement? = null,
    /** Deployment environment name, for deployment elements only. */
    val environment: String? = null,
    /** Identifier of the element an instance refers to, resolved lazily via [DslWorkspace.resolve]. */
    val instanceOf: String? = null,
) {
    val tags: MutableSet<String> = linkedSetOf("Element", type.defaultTag)

    /** This element followed by all of its ancestors, innermost first. */
    val selfAndAncestors: Sequence<DslElement> get() = generateSequence(this) { it.parent }

    override fun toString(): String = "${type.displayName}(${identifier ?: uid}: $name)"
}

/** A relationship between two model elements. */
class DslRelationship(
    val source: DslElement,
    val destination: DslElement,
    val description: String = "",
    val technology: String = "",
) {
    val tags: MutableSet<String> = linkedSetOf("Relationship")
}

/** View kinds from the `views { }` block. */
enum class DslViewType(val displayName: String, val keyPrefix: String) {
    SYSTEM_LANDSCAPE("System Landscape", "SystemLandscape"),
    SYSTEM_CONTEXT("System Context", "SystemContext"),
    CONTAINER("Container", "Container"),
    COMPONENT("Component", "Component"),
    DEPLOYMENT("Deployment", "Deployment"),
    DYNAMIC("Dynamic", "Dynamic"),
    FILTERED("Filtered", "Filtered"),
    CUSTOM("Custom", "Custom"),
    IMAGE("Image", "Image"),
}

/** One step of a dynamic view (`a -> b "description"`). */
data class DslDynamicStep(val source: String, val destination: String, val description: String)

/** A view definition. [scope] is the raw identifier (or `*`) the view is scoped to. */
class DslView(
    val type: DslViewType,
    var key: String,
    val scope: String? = null,
    val environment: String? = null,
    var description: String = "",
    /** True when the view was generated because the workspace defines no views of its own. */
    val generated: Boolean = false,
) {
    var title: String = ""
    val includes: MutableList<String> = mutableListOf()
    val excludes: MutableList<String> = mutableListOf()
    val steps: MutableList<DslDynamicStep> = mutableListOf()
    var isDefault: Boolean = false

    /** Filtered views: the base view key, include/exclude mode and the tags filtered on. */
    var baseKey: String? = null
    var filterInclude: Boolean = true
    val filterTags: MutableList<String> = mutableListOf()
}

/** The parsed workspace: model elements, relationships and views. */
class DslWorkspace {
    var name: String = "Workspace"
    var description: String = ""
    val elements: MutableList<DslElement> = mutableListOf()
    val relationships: MutableList<DslRelationship> = mutableListOf()
    val views: MutableList<DslView> = mutableListOf()

    internal val identifiers: MutableMap<String, DslElement> = linkedMapOf()

    /** Deployment environment names by the identifier they were assigned to (`live = deploymentEnvironment "Live"`). */
    internal val environmentIdentifiers: MutableMap<String, String> = linkedMapOf()

    /** The environment name a deployment view refers to, whether given by name or by identifier. */
    fun environmentName(reference: String?): String? =
        reference?.let { environmentIdentifiers[it.lowercase()] ?: it }

    /** Finds an element by its DSL identifier (flat or hierarchical), case-insensitively like Structurizr. */
    fun resolve(identifier: String?): DslElement? {
        if (identifier.isNullOrEmpty()) return null
        return identifiers[identifier.lowercase()]
    }

    fun children(of: DslElement): List<DslElement> = elements.filter { it.parent === of }

    fun findView(key: String?): DslView? = views.firstOrNull { it.key == key }
}
