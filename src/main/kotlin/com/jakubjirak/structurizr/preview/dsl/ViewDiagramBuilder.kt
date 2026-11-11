/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview.dsl

/** A box drawn around a set of elements (the system of a container view, a deployment node, ...). */
data class DiagramBoundary(val label: String, val members: List<DslElement>)

/** A line on the diagram. [order] is set for dynamic views. */
data class DiagramEdge(
    val source: DslElement,
    val destination: DslElement,
    val description: String,
    val technology: String,
    val order: Int? = null,
)

/** Everything the renderer needs to draw one view. */
data class ViewDiagram(
    val title: String,
    val description: String,
    val elements: List<DslElement>,
    val edges: List<DiagramEdge>,
    val boundaries: List<DiagramBoundary> = emptyList(),
    /** Elements drawn in the middle row (the system in scope of a context view, ...). */
    val focus: Set<DslElement> = emptySet(),
    /** Shown instead of a diagram when the view has nothing to draw. */
    val message: String? = null,
)

/**
 * Selects the elements and relationships of one view the way Structurizr does for the common cases:
 * `include *` means "the default content for this view type", explicit identifiers add elements,
 * `exclude` removes them, and relationships between nested elements are lifted to the nearest element
 * that is actually on the diagram (implied relationships).
 */
class ViewDiagramBuilder(private val ws: DslWorkspace) {

    fun build(view: DslView): ViewDiagram = when (view.type) {
        DslViewType.DEPLOYMENT -> deployment(view)
        DslViewType.DYNAMIC -> dynamic(view)
        DslViewType.FILTERED -> filtered(view)
        DslViewType.IMAGE -> empty(view, "Image views are not rendered in the preview.")
        else -> static(view)
    }

    private fun title(view: DslView, scope: DslElement?): String {
        if (view.title.isNotBlank()) return view.title
        val suffix = scope?.let { ": ${it.name}" } ?: ""
        return when (view.type) {
            DslViewType.SYSTEM_LANDSCAPE -> "System Landscape"
            DslViewType.SYSTEM_CONTEXT -> "System Context view$suffix"
            DslViewType.CONTAINER -> "Container view$suffix"
            DslViewType.COMPONENT -> "Component view$suffix"
            DslViewType.DEPLOYMENT -> "Deployment view$suffix" + (ws.environmentName(view.environment)?.let { " – $it" } ?: "")
            DslViewType.DYNAMIC -> "Dynamic view$suffix"
            else -> view.key
        }
    }

    private fun empty(view: DslView, message: String): ViewDiagram =
        ViewDiagram(title(view, ws.resolve(view.scope)), view.description, emptyList(), emptyList(), message = message)

    private fun topLevel(e: DslElement): DslElement = e.selfAndAncestors.last()

    // ── Static views ─────────────────────────────────────────────────────

    private fun static(view: DslView): ViewDiagram {
        val scope = ws.resolve(view.scope)
        if (view.type in SCOPED && scope == null) {
            return empty(view, "The element \"${view.scope ?: ""}\" this view is scoped to is not defined in the model.")
        }
        val wildcard = view.includes.any { it == "*" }
        val selected = linkedSetOf<DslElement>()
        if (wildcard) selected += defaultContent(view.type, scope)
        for (token in view.includes) {
            if (token == "*") continue
            ws.resolve(token)?.let { selected += it }
        }
        for (token in view.excludes) ws.resolve(token)?.let { selected -= it }

        val focus = when (view.type) {
            DslViewType.SYSTEM_CONTEXT -> setOfNotNull(scope)
            else -> emptySet()
        }
        val boundaries = when (view.type) {
            DslViewType.CONTAINER, DslViewType.COMPONENT -> {
                val members = selected.filter { it.parent === scope }
                if (scope != null && members.isNotEmpty()) {
                    listOf(DiagramBoundary("${scope.name} [${scope.type.displayName}]", members))
                } else emptyList()
            }
            else -> emptyList()
        }
        val elements = selected.toList()
        val message = if (elements.isEmpty()) {
            if (view.includes.isEmpty()) "This view includes no elements. Add \"include *\" to show its default content."
            else "None of the included elements exist in the model."
        } else null
        return ViewDiagram(title(view, scope), view.description, elements, liftedEdges(elements), boundaries, focus, message)
    }

    private fun defaultContent(type: DslViewType, scope: DslElement?): Set<DslElement> {
        val people = ws.elements.filter { it.type == DslElementType.PERSON }
        val systems = ws.elements.filter { it.type == DslElementType.SOFTWARE_SYSTEM }
        return when (type) {
            DslViewType.SYSTEM_LANDSCAPE, DslViewType.CUSTOM -> (people + systems).toSet()
            DslViewType.SYSTEM_CONTEXT -> {
                scope ?: return emptySet()
                linkedSetOf(scope) + connectedTopLevel(scope)
            }
            DslViewType.CONTAINER -> {
                scope ?: return emptySet()
                val containers = ws.children(scope).filter { it.type == DslElementType.CONTAINER }
                linkedSetOf<DslElement>().apply {
                    addAll(containers)
                    addAll(connectedOutside(scope, strict = true) { topLevel(it) })
                }
            }
            DslViewType.COMPONENT -> {
                scope ?: return emptySet()
                val system = scope.parent
                val components = ws.children(scope).filter { it.type == DslElementType.COMPONENT }
                linkedSetOf<DslElement>().apply {
                    addAll(components)
                    addAll(connectedOutside(scope, strict = true) { other ->
                        // Siblings inside the same system show as containers, everything else as its top level.
                        if (system != null && other.selfAndAncestors.any { it === system }) {
                            other.selfAndAncestors.first { it.parent === system }
                        } else topLevel(other)
                    })
                }
            }
            else -> emptySet()
        }
    }

    /** Top-level people/systems that have a relationship with [scope] or anything nested in it. */
    private fun connectedTopLevel(scope: DslElement): Set<DslElement> =
        connectedOutside(scope) { topLevel(it) }
            .filter { it.type == DslElementType.PERSON || it.type == DslElementType.SOFTWARE_SYSTEM }
            .toSet()

    /**
     * Elements outside [scope] that have a relationship with something inside it, lifted by [lift].
     * With [strict], only relationships of elements nested in the scope count, not of the scope itself —
     * a container view shows what its containers talk to, as Structurizr's `include *` does.
     */
    private fun connectedOutside(scope: DslElement, strict: Boolean = false, lift: (DslElement) -> DslElement): Set<DslElement> {
        val result = linkedSetOf<DslElement>()
        fun inside(e: DslElement) = e.selfAndAncestors.any { it === scope } && !(strict && e === scope)
        for (r in ws.relationships) {
            if (r.source.environment != null || r.destination.environment != null) continue
            val s = inside(r.source)
            val d = inside(r.destination)
            if (s && !d) result += lift(r.destination)
            if (d && !s) result += lift(r.source)
        }
        result.removeAll { it.selfAndAncestors.any { a -> a === scope } }
        return result
    }

    /** Model relationships projected onto the visible elements, de-duplicated per element pair. */
    private fun liftedEdges(visible: List<DslElement>): List<DiagramEdge> {
        val visibleSet = visible.toHashSet()
        fun project(e: DslElement): DslElement? = e.selfAndAncestors.firstOrNull { it in visibleSet }
        val seen = hashSetOf<Pair<Int, Int>>()
        val edges = mutableListOf<DiagramEdge>()
        // Direct relationships first so their descriptions win over implied ones.
        val ordered = ws.relationships.sortedBy { if (it.source in visibleSet && it.destination in visibleSet) 0 else 1 }
        for (r in ordered) {
            val s = project(r.source) ?: continue
            val d = project(r.destination) ?: continue
            if (s === d) continue
            if (!seen.add(s.uid to d.uid)) continue
            edges += DiagramEdge(s, d, r.description, r.technology)
        }
        return edges
    }

    // ── Deployment ───────────────────────────────────────────────────────

    private fun deployment(view: DslView): ViewDiagram {
        val env = ws.environmentName(view.environment)
        val scope = view.scope?.takeIf { it != "*" }?.let { ws.resolve(it) }
        val inEnv = ws.elements.filter { it.environment != null && it.environment.equals(env, ignoreCase = true) }
        if (inEnv.isEmpty()) {
            return empty(view, "Deployment environment \"${env ?: ""}\" is not defined in the model.")
        }
        val instances = inEnv.filter { e ->
            when (e.type) {
                DslElementType.CONTAINER_INSTANCE -> scope == null || ws.resolve(e.instanceOf)?.parent === scope
                DslElementType.SOFTWARE_SYSTEM_INSTANCE -> scope == null || ws.resolve(e.instanceOf) === scope
                DslElementType.INFRASTRUCTURE_NODE -> true
                else -> false
            }
        }
        val boundaries = instances.groupBy { it.parent }.mapNotNull { (node, members) ->
            node ?: return@mapNotNull null
            val path = node.selfAndAncestors.toList().reversed().joinToString(" › ") { it.name }
            val tech = node.technology.takeIf { it.isNotBlank() }?.let { " [$it]" } ?: ""
            DiagramBoundary(path + tech, members)
        }
        val edges = mutableListOf<DiagramEdge>()
        val seen = hashSetOf<Pair<Int, Int>>()
        for (r in ws.relationships) {
            val sources = instances.filter { it === r.source || ws.resolve(it.instanceOf) === r.source }
            val destinations = instances.filter { it === r.destination || ws.resolve(it.instanceOf) === r.destination }
            for (s in sources) for (d in destinations) {
                if (s !== d && seen.add(s.uid to d.uid)) edges += DiagramEdge(s, d, r.description, r.technology)
            }
        }
        val message = if (instances.isEmpty()) "No instances are deployed in this environment." else null
        return ViewDiagram(title(view, scope), view.description, instances, edges, boundaries, message = message)
    }

    // ── Dynamic ──────────────────────────────────────────────────────────

    private fun dynamic(view: DslView): ViewDiagram {
        val scope = view.scope?.takeIf { it != "*" }?.let { ws.resolve(it) }
        val elements = linkedSetOf<DslElement>()
        val edges = mutableListOf<DiagramEdge>()
        view.steps.forEachIndexed { index, step ->
            val s = ws.resolve(step.source) ?: return@forEachIndexed
            val d = ws.resolve(step.destination) ?: return@forEachIndexed
            elements += s
            elements += d
            val modelled = ws.relationships.firstOrNull { it.source === s && it.destination === d }
            edges += DiagramEdge(s, d, step.description.ifBlank { modelled?.description ?: "" }, modelled?.technology ?: "", index + 1)
        }
        val members = elements.filter { scope != null && it.parent === scope }
        val boundaries = if (scope != null && members.isNotEmpty()) {
            listOf(DiagramBoundary("${scope.name} [${scope.type.displayName}]", members))
        } else emptyList()
        val message = if (elements.isEmpty()) "This dynamic view has no steps between defined elements." else null
        return ViewDiagram(title(view, scope), view.description, elements.toList(), edges, boundaries, message = message)
    }

    // ── Filtered ─────────────────────────────────────────────────────────

    private fun filtered(view: DslView): ViewDiagram {
        val base = ws.findView(view.baseKey)?.takeIf { it.type != DslViewType.FILTERED }
            ?: return empty(view, "Filtered view refers to an unknown base view \"${view.baseKey ?: ""}\".")
        val diagram = build(base)
        val tags = view.filterTags.map { it.lowercase() }.toSet()
        fun matches(element: DslElement) = element.tags.any { it.lowercase() in tags }
        val elements = diagram.elements.filter { if (view.filterInclude) matches(it) else !matches(it) }
        val kept = elements.toHashSet()
        return diagram.copy(
            title = view.title.ifBlank { "${diagram.title} (filtered)" },
            description = view.description.ifBlank { diagram.description },
            elements = elements,
            edges = diagram.edges.filter { it.source in kept && it.destination in kept },
            boundaries = diagram.boundaries
                .map { b -> b.copy(members = b.members.filter { it in kept }) }
                .filter { it.members.isNotEmpty() },
            message = if (elements.isEmpty()) "The filter removes every element of the base view." else null,
        )
    }

    private companion object {
        val SCOPED = setOf(DslViewType.SYSTEM_CONTEXT, DslViewType.CONTAINER, DslViewType.COMPONENT)
    }
}
