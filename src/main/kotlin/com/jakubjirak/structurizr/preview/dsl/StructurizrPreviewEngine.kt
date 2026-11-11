/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview.dsl

/** An entry of the preview's view selector. */
data class ViewOption(val key: String, val label: String, val isDefault: Boolean = false) {
    override fun toString(): String = label
}

/** Result of rendering one view of a workspace. */
data class PreviewResult(val views: List<ViewOption>, val selectedKey: String?, val svg: String)

/** Pure (IDE-independent) pipeline: DSL text → workspace → selected view → SVG. */
object StructurizrPreviewEngine {

    fun parse(dsl: String, includeResolver: (String) -> String? = { null }): DslWorkspace =
        DslWorkspaceParser(includeResolver).parse(dsl)

    fun viewOptions(ws: DslWorkspace): List<ViewOption> = ws.views.map { view ->
        val scope = ws.resolve(view.scope)?.name ?: view.scope?.takeIf { it != "*" }
        val subject = listOfNotNull(scope, ws.environmentName(view.environment)).joinToString(" – ")
        val type = view.type.displayName + if (subject.isNotEmpty()) ": $subject" else ""
        val label = when {
            view.generated -> "$type (auto)"
            view.title.isNotBlank() -> "${view.key} — ${view.title}"
            else -> "${view.key} — $type"
        }
        ViewOption(view.key, label, view.isDefault)
    }

    /**
     * Renders [viewKey] (or the workspace's default view when it is `null` or unknown) to SVG.
     * Never throws for malformed DSL: the worst case is an SVG explaining that there is nothing to show.
     */
    fun render(dsl: String, viewKey: String?, includeResolver: (String) -> String? = { null }): PreviewResult {
        val ws = parse(dsl, includeResolver)
        val (view, diagram) = diagram(ws, viewKey)
        return PreviewResult(viewOptions(ws), view?.key, SvgDiagramRenderer.render(diagram))
    }

    /** Builds the diagram of [viewKey] (default view when `null`/unknown) without rendering it. */
    fun diagram(ws: DslWorkspace, viewKey: String?): Pair<DslView?, ViewDiagram> {
        val view = ws.findView(viewKey) ?: ws.views.firstOrNull { it.isDefault } ?: ws.views.firstOrNull()
        val diagram = if (view != null) {
            ViewDiagramBuilder(ws).build(view)
        } else {
            ViewDiagram(ws.name, ws.description, emptyList(), emptyList(), message = NO_CONTENT)
        }
        return view to diagram
    }

    private const val NO_CONTENT = "The workspace defines no elements or views yet."
}
