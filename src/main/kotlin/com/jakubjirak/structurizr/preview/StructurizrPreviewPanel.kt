/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBLabel
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.BorderLayout
import java.awt.Component
import java.util.Locale
import javax.swing.JPanel
import javax.swing.UIManager
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Swing panel that renders Structurizr DSL content as an interactive diagram
 * inside a JCEF browser component.
 */
class StructurizrPreviewPanel(private val project: Project) : JPanel(BorderLayout()), Disposable {

    private val log = Logger.getInstance(StructurizrPreviewPanel::class.java)
    private var browser: JBCefBrowser? = null
    private var currentDsl = ""
    private var currentViewKey: String? = null
    private val placeholderLabel = JBLabel(
        "<html><center><h2>Structurizr Preview</h2><p>Edit your DSL file to see the diagram</p></center></html>",
        JBLabel.CENTER,
    )

    init {
        background = UIManager.getColor("Editor.background") ?: JBColor.background()
        initBrowser()
    }

    private fun initBrowser() {
        try {
            if (JBCefApp.isSupported()) {
                browser = JBCefBrowser().also { b ->
                    add(b.component as Component, BorderLayout.CENTER)
                    Disposer.register(this, b)
                    showPlaceholder()
                }
            } else {
                showFallbackPanel()
            }
        } catch (e: Exception) {
            log.warn("Failed to initialize JCEF browser", e)
            showFallbackPanel()
        }
    }

    private fun showFallbackPanel() {
        removeAll()
        add(placeholderLabel as Component, BorderLayout.CENTER)
        revalidate()
        repaint()
    }

    private fun showPlaceholder() {
        browser?.loadHTML(
            """<!DOCTYPE html>
            <html><head><style>
            body { font-family: -apple-system, sans-serif; display: flex; justify-content: center;
                   align-items: center; height: 100vh; margin: 0; background: #f5f5f5; }
            .placeholder { text-align: center; padding: 40px; }
            h2 { color: #1168bd; }
            </style></head>
            <body><div class="placeholder"><h2>Structurizr Preview</h2><p>Edit your DSL file</p></div></body>
            </html>"""
        )
    }

    /**
     * Updates the diagram preview with the given DSL content.
     *
     * @param dslContent the Structurizr DSL source text
     * @param viewKey    optional view key filter
     */
    fun updatePreview(dslContent: String, viewKey: String? = null) {
        if (dslContent == currentDsl && viewKey == currentViewKey) return
        currentDsl = dslContent
        currentViewKey = viewKey
        ApplicationManager.getApplication().executeOnPooledThread {
            try {
                val model = parseDsl(dslContent)
                val html = generateHtml(model, viewKey)
                ApplicationManager.getApplication().invokeLater {
                    browser?.loadHTML(html)
                }
            } catch (e: Exception) {
                log.warn("Parse error", e)
                showError(e.message ?: "Unknown error")
            }
        }
    }

    private fun showError(msg: String) {
        browser?.loadHTML(
            """
            <!DOCTYPE html>
            <html><head><style>
            body { font-family: sans-serif; display: flex; justify-content: center; align-items: center;
                   height: 100vh; background: #fff5f5; }
            .error { text-align: center; padding: 40px; }
            h3 { color: #c53030; }
            pre { background: #fed7d7; padding: 15px; border-radius: 8px; }
            </style></head>
            <body><div class="error"><h3>Parse Error</h3><pre>${msg.replace("<", "&lt;")}</pre></div></body>
            </html>
            """.trimIndent()
        )
    }

    // ── DSL Parsing ──────────────────────────────────────────────────────

    private fun parseDsl(dsl: String): ParsedModel {
        val model = ParsedModel()
        val lines = dsl.lines()
        var i = 0
        var inModel = false
        var inViews = false
        var currentSystemId: String? = null
        var currentContainerId: String? = null
        var currentView: View? = null
        var braceDepth = 0

        while (i < lines.size) {
            val line = lines[i].trim()
            i++
            if (line.isEmpty() || line.startsWith("//") || line.startsWith("#")) continue

            val openBraces = line.count { it == '{' }
            val closeBraces = line.count { it == '}' }

            if ("model" in line && "{" in line) {
                inModel = true; inViews = false; braceDepth = 1; continue
            }
            if ("views" in line && "{" in line) {
                inModel = false; inViews = true; braceDepth = 1; continue
            }
            if (line == "}") {
                if (--braceDepth <= 0) { inModel = false; inViews = false }
                if (currentView != null && inViews) { model.views += currentView; currentView = null }
                if (currentContainerId != null) { currentContainerId = null; continue }
                if (currentSystemId != null) { currentSystemId = null; continue }
                continue
            }

            if (inModel) {
                braceDepth += openBraces - closeBraces
                parseModelLine(line, model, currentSystemId, currentContainerId)?.let { (id, type) ->
                    when (type) {
                        ElementType.SOFTWARE_SYSTEM -> currentSystemId = id
                        ElementType.CONTAINER -> currentContainerId = id
                        else -> {}
                    }
                }
                continue
            }
            if (inViews) {
                braceDepth += openBraces - closeBraces
                if (currentView == null) { currentView = parseViewLine(line); continue }
                parseViewContent(line, currentView)
            }
        }
        return model
    }

    private fun parseModelLine(line: String, model: ParsedModel, sysId: String?, contId: String?): Pair<String, ElementType>? {
        Regex("""(\w+)\s*=\s*person\s+"([^"]+)"(?:\s+"([^"]+)")?""").find(line)?.let { m ->
            val id = m.groupValues[1]; val name = m.groupValues[2]; val desc = m.groupValues[3]
            model.elements += Element(id, name, desc, ElementType.PERSON)
            return if ("{" in line) id to ElementType.PERSON else null
        }
        Regex("""(\w+)\s*=\s*softwareSystem\s+"([^"]+)"(?:\s+"([^"]+)")?""").find(line)?.let { m ->
            val id = m.groupValues[1]; val name = m.groupValues[2]; val desc = m.groupValues[3]
            model.elements += Element(id, name, desc, ElementType.SOFTWARE_SYSTEM)
            return if ("{" in line) id to ElementType.SOFTWARE_SYSTEM else null
        }
        Regex("""(\w+)\s*=\s*container\s+"([^"]+)"(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?""").find(line)?.let { m ->
            val g = m.groupValues
            model.elements += Element(g[1], g[2], g.getOrElse(3) { "" }, ElementType.CONTAINER, g.getOrElse(4) { "" }, sysId)
            return if ("{" in line) g[1] to ElementType.CONTAINER else null
        }
        Regex("""(\w+)\s*=\s*component\s+"([^"]+)"(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?""").find(line)?.let { m ->
            val g = m.groupValues
            model.elements += Element(g[1], g[2], g.getOrElse(3) { "" }, ElementType.COMPONENT, g.getOrElse(4) { "" }, contId)
            return if ("{" in line) g[1] to ElementType.COMPONENT else null
        }
        Regex("""(\w+)\s*->\s*(\w+)(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?""").find(line)?.let { m ->
            val g = m.groupValues
            model.relationships += Relationship(g[1], g[2], g.getOrElse(3) { "" }, g.getOrElse(4) { "" })
        }
        return null
    }

    private fun parseViewLine(line: String): View? {
        Regex("""systemLandscape\s*(?:"([^"]+)")?\s*\{""").find(line)?.let { m ->
            return View("landscape", ViewType.SYSTEM_LANDSCAPE, m.groupValues.getOrElse(1) { "" })
        }
        Regex("""systemContext\s+(\w+)\s*(?:"([^"]+)")?\s*\{""").find(line)?.let { m ->
            return View("context", ViewType.SYSTEM_CONTEXT, m.groupValues.getOrElse(2) { "" }, m.groupValues[1])
        }
        Regex("""container\s+(\w+)\s*(?:"([^"]+)")?\s*\{""").find(line)?.let { m ->
            return View("containers", ViewType.CONTAINER, m.groupValues.getOrElse(2) { "" }, m.groupValues[1])
        }
        Regex("""component\s+(\w+)\s*(?:"([^"]+)")?\s*\{""").find(line)?.let { m ->
            return View("components", ViewType.COMPONENT, m.groupValues.getOrElse(2) { "" }, m.groupValues[1])
        }
        return null
    }

    private fun parseViewContent(line: String, view: View) {
        if (line.startsWith("include")) {
            val tokens = line.removePrefix("include").trim()
            view.includes += Regex("""\s+""").split(tokens)
        }
        if (line.startsWith("autoLayout")) {
            Regex("""\s+""").split(line).getOrNull(1)?.let {
                view.autoLayout = it.lowercase(Locale.ROOT)
            }
        }
    }

    // ── HTML Generation ──────────────────────────────────────────────────

    private fun generateHtml(model: ParsedModel, viewKey: String?): String {
        val view = model.views.firstOrNull { viewKey == null || it.key == viewKey }
            ?: model.views.firstOrNull()
        val elements = getElementsForView(model, view)
        val relationships = getRelationshipsForView(model, elements)
        val positions = calculatePositions(elements)
        return buildHtml(elements, relationships, positions, view)
    }

    private fun getElementsForView(model: ParsedModel, view: View?): List<Element> {
        if (view == null) return model.elements
        val all = "*" in view.includes
        return when (view.type) {
            ViewType.SYSTEM_LANDSCAPE ->
                if (all) model.elements.filter { it.type == ElementType.PERSON || it.type == ElementType.SOFTWARE_SYSTEM }
                else model.elements.filter { it.id in view.includes }
            ViewType.SYSTEM_CONTEXT ->
                model.elements.filter { it.type == ElementType.PERSON || it.type == ElementType.SOFTWARE_SYSTEM }
            ViewType.CONTAINER ->
                model.elements.filter { it.type == ElementType.PERSON || it.type == ElementType.SOFTWARE_SYSTEM || (it.type == ElementType.CONTAINER && it.parentId == view.scope) }
            ViewType.COMPONENT -> model.elements
            ViewType.DEPLOYMENT ->
                model.elements.filter { it.type == ElementType.DEPLOYMENT_NODE }
        }
    }

    private fun getRelationshipsForView(model: ParsedModel, elements: List<Element>): List<Relationship> {
        val ids = elements.map { it.id }.toSet()
        return model.relationships.filter { it.sourceId in ids && it.targetId in ids }
    }

    private fun calculatePositions(elements: List<Element>): Map<String, Position> {
        val pos = mutableMapOf<String, Position>()
        val people = elements.filter { it.type == ElementType.PERSON }
        val systems = elements.filter { it.type == ElementType.SOFTWARE_SYSTEM }
        val containers = elements.filter { it.type == ElementType.CONTAINER }
        val components = elements.filter { it.type == ElementType.COMPONENT }

        val pw = 180; val ph = 150
        val sw = 240; val sh = 170
        val cw = 210; val ch = 150
        val compw = 190; val comph = 130
        val hGap = 60; val vGap = 80
        val canvasWidth = 1400
        var currentY = 60

        if (people.isNotEmpty()) {
            val totalW = people.size * pw + (people.size - 1) * hGap
            var x = (canvasWidth - totalW) / 2
            for (p in people) { pos[p.id] = Position(x, currentY, pw, ph); x += pw + hGap }
            currentY += ph + vGap
        }
        if (systems.isNotEmpty()) {
            val totalW = systems.size * sw + (systems.size - 1) * hGap
            var x = (canvasWidth - totalW) / 2
            for (s in systems) { pos[s.id] = Position(x, currentY, sw, sh); x += sw + hGap }
            currentY += sh + vGap
        }
        if (containers.isNotEmpty()) {
            val cols = when { containers.size <= 2 -> containers.size; containers.size <= 4 -> 2; containers.size <= 6 -> 3; else -> 4 }
            val rows = (containers.size + cols - 1) / cols
            val gridW = cols * cw + (cols - 1) * hGap
            val startX = (canvasWidth - gridW) / 2
            containers.forEachIndexed { idx, c ->
                val col = idx % cols; val row = idx / cols
                pos[c.id] = Position(startX + col * (cw + hGap), currentY + row * (ch + vGap), cw, ch)
            }
            currentY += rows * (ch + vGap)
        }
        if (components.isNotEmpty()) {
            val cols = when { components.size <= 2 -> components.size; components.size <= 4 -> 2; components.size <= 6 -> 3; else -> 4 }
            val startX = (canvasWidth - (cols * compw + (cols - 1) * hGap)) / 2
            components.forEachIndexed { idx, c ->
                val col = idx % cols; val row = idx / cols
                pos[c.id] = Position(startX + col * (compw + hGap), currentY + row * (comph + vGap), compw, comph)
            }
        }
        return pos
    }

    private fun buildHtml(elements: List<Element>, rels: List<Relationship>, pos: Map<String, Position>, view: View?): String {
        val maxY = pos.values.maxOfOrNull { it.y + it.h } ?: 600
        val maxX = pos.values.maxOfOrNull { it.x + it.w } ?: 1200
        val svgH = maxY + 150
        val svgW = max(1400, maxX + 200)

        val svg = StringBuilder()

        // Relationships
        for (rel in rels) {
            val s = pos[rel.sourceId] ?: continue
            val t = pos[rel.targetId] ?: continue
            val sCx = s.x + s.w / 2; val sCy = s.y + s.h / 2
            val tCx = t.x + t.w / 2; val tCy = t.y + t.h / 2
            val (x1, y1, x2, y2) = calculateConnectionPoints(s, t, sCx, sCy, tCx, tCy)
            val dx = x2 - x1; val dy = y2 - y1
            val (cx1, cy1, cx2, cy2) = if (abs(dy) > abs(dx))
                listOf(x1, y1 + dy / 3, x2, y2 - dy / 3)
            else
                listOf(x1 + dx / 3, y1, x2 - dx / 3, y2)
            svg.append("""<path d="M $x1 $y1 C $cx1 $cy1, $cx2 $cy2, $x2 $y2" fill="none" stroke="#707070" stroke-width="2" marker-end="url(#arr)"/>""")
            if (rel.description.isNotEmpty()) {
                val lx = (x1 + x2) / 2; val ly = (y1 + y2) / 2 - 15
                val labelWidth = min(rel.description.length * 7 + 16, 150)
                svg.append("""<rect x="${lx - labelWidth / 2}" y="${ly - 12}" width="$labelWidth" height="24" fill="white" stroke="#ccc" rx="4"/>""")
                svg.append("""<text x="$lx" y="$ly" text-anchor="middle" dominant-baseline="middle" font-family="Arial" font-size="11" fill="#707070">${esc(rel.description.take(22))}</text>""")
            }
        }

        // Elements
        for (el in elements) {
            val p = pos[el.id] ?: continue
            val (bg, border, txt) = getColors(el.type)
            when (el.type) {
                ElementType.PERSON -> {
                    val cx = p.x + p.w / 2
                    svg.append("""<circle cx="$cx" cy="${p.y + 30}" r="25" fill="$bg" stroke="$border" stroke-width="2"/>""")
                    svg.append("""<rect x="${p.x + 20}" y="${p.y + 60}" width="${p.w - 40}" height="${p.h - 70}" fill="$bg" stroke="$border" stroke-width="2" rx="8"/>""")
                    svg.append("""<text x="$cx" y="${p.y + 90}" text-anchor="middle" font-family="Arial" font-size="14" font-weight="bold" fill="$txt">${esc(el.name)}</text>""")
                    svg.append("""<text x="$cx" y="${p.y + 110}" text-anchor="middle" font-family="Arial" font-size="11" fill="$txt">[Person]</text>""")
                }
                ElementType.SOFTWARE_SYSTEM -> {
                    svg.append("""<rect x="${p.x}" y="${p.y}" width="${p.w}" height="${p.h}" fill="$bg" stroke="$border" stroke-width="2" rx="8"/>""")
                    svg.append("""<text x="${p.x + p.w / 2}" y="${p.y + 40}" text-anchor="middle" font-family="Arial" font-size="15" font-weight="bold" fill="$txt">${esc(el.name)}</text>""")
                    svg.append("""<text x="${p.x + p.w / 2}" y="${p.y + 60}" text-anchor="middle" font-family="Arial" font-size="11" fill="$txt">[Software System]</text>""")
                    if (el.description.isNotEmpty()) {
                        svg.append("""<text x="${p.x + p.w / 2}" y="${p.y + 85}" text-anchor="middle" font-family="Arial" font-size="10" fill="$txt" opacity="0.85">${esc(el.description.take(35))}</text>""")
                    }
                }
                ElementType.CONTAINER -> {
                    svg.append("""<rect x="${p.x}" y="${p.y}" width="${p.w}" height="${p.h}" fill="$bg" stroke="$border" stroke-width="2" rx="8"/>""")
                    svg.append("""<text x="${p.x + p.w / 2}" y="${p.y + 30}" text-anchor="middle" font-family="Arial" font-size="14" font-weight="bold" fill="$txt">${esc(el.name)}</text>""")
                    val tech = if (el.technology.isNotEmpty()) ": ${el.technology}" else ""
                    svg.append("""<text x="${p.x + p.w / 2}" y="${p.y + 50}" text-anchor="middle" font-family="Arial" font-size="11" fill="$txt">[Container$tech]</text>""")
                    if (el.description.isNotEmpty()) {
                        svg.append("""<text x="${p.x + p.w / 2}" y="${p.y + 75}" text-anchor="middle" font-family="Arial" font-size="10" fill="$txt" opacity="0.85">${esc(el.description.take(30))}</text>""")
                    }
                }
                ElementType.COMPONENT -> {
                    svg.append("""<rect x="${p.x}" y="${p.y}" width="${p.w}" height="${p.h}" fill="$bg" stroke="$border" stroke-width="2" rx="8"/>""")
                    svg.append("""<text x="${p.x + p.w / 2}" y="${p.y + 30}" text-anchor="middle" font-family="Arial" font-size="13" font-weight="bold" fill="$txt">${esc(el.name)}</text>""")
                    val tech = if (el.technology.isNotEmpty()) ": ${el.technology}" else ""
                    svg.append("""<text x="${p.x + p.w / 2}" y="${p.y + 48}" text-anchor="middle" font-family="Arial" font-size="10" fill="$txt">[Component$tech]</text>""")
                }
                else -> {
                    svg.append("""<rect x="${p.x}" y="${p.y}" width="${p.w}" height="${p.h}" fill="#ddd" stroke="#999" stroke-width="2" rx="8"/>""")
                    svg.append("""<text x="${p.x + p.w / 2}" y="${p.y + p.h / 2}" text-anchor="middle" font-family="Arial" font-size="14" fill="#333">${esc(el.name)}</text>""")
                }
            }
        }

        val title = view?.title?.takeIf { it.isNotEmpty() } ?: when (view?.type) {
            ViewType.SYSTEM_LANDSCAPE -> "System Landscape"
            ViewType.SYSTEM_CONTEXT -> "System Context"
            ViewType.CONTAINER -> "Container Diagram"
            ViewType.COMPONENT -> "Component Diagram"
            else -> "Structurizr Diagram"
        }

        return """<!DOCTYPE html><html><head><meta charset="UTF-8"><style>
*{margin:0;padding:0;box-sizing:border-box}
body{font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,sans-serif;background:#fff;overflow:auto}
.container{padding:20px;min-height:100vh}
.title{font-size:24px;font-weight:600;color:#333;margin-bottom:20px;padding-left:10px;border-left:4px solid #1168bd}
svg{display:block;background:#fff;border:1px solid #e0e0e0;border-radius:8px}
.legend{margin-top:20px;padding:15px;background:#f8f9fa;border-radius:8px;display:flex;gap:20px;flex-wrap:wrap}
.legend-item{display:flex;align-items:center;gap:8px;font-size:12px;color:#666}
.legend-box{width:20px;height:20px;border-radius:4px;border:2px solid}
</style></head><body><div class="container">
<div class="title">${esc(title)}</div>
<svg width="$svgW" height="$svgH" viewBox="0 0 $svgW $svgH">
<defs><marker id="arr" markerWidth="10" markerHeight="7" refX="9" refY="3.5" orient="auto"><polygon points="0 0, 10 3.5, 0 7" fill="#707070"/></marker></defs>
$svg
</svg>
<div class="legend">
<div class="legend-item"><div class="legend-box" style="background:#08427b;border-color:#052e56"></div><span>Person</span></div>
<div class="legend-item"><div class="legend-box" style="background:#1168bd;border-color:#0b4884"></div><span>Software System</span></div>
<div class="legend-item"><div class="legend-box" style="background:#438dd5;border-color:#2e6295"></div><span>Container</span></div>
<div class="legend-item"><div class="legend-box" style="background:#85bbf0;border-color:#5d82a8"></div><span>Component</span></div>
</div></div></body></html>"""
    }

    private fun calculateConnectionPoints(s: Position, t: Position, sCx: Int, sCy: Int, tCx: Int, tCy: Int): List<Int> {
        val dx = tCx - sCx; val dy = tCy - sCy
        return if (abs(dx) > abs(dy)) {
            if (dx > 0) listOf(s.x + s.w, sCy, t.x, tCy)
            else listOf(s.x, sCy, t.x + t.w, tCy)
        } else {
            if (dy > 0) listOf(sCx, s.y + s.h, tCx, t.y)
            else listOf(sCx, s.y, tCx, t.y + t.h)
        }
    }

    private fun getColors(type: ElementType): Triple<String, String, String> = when (type) {
        ElementType.PERSON -> Triple("#08427b", "#052e56", "#ffffff")
        ElementType.SOFTWARE_SYSTEM -> Triple("#1168bd", "#0b4884", "#ffffff")
        ElementType.CONTAINER -> Triple("#438dd5", "#2e6295", "#ffffff")
        ElementType.COMPONENT -> Triple("#85bbf0", "#5d82a8", "#000000")
        else -> Triple("#ffffff", "#888888", "#000000")
    }

    private fun esc(s: String): String =
        s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")

    /** Clears the current DSL content and resets to the placeholder. */
    fun clear() {
        currentDsl = ""
        currentViewKey = null
        showPlaceholder()
    }

    override fun dispose() {
        browser = null
    }

    // ── Inner data types ─────────────────────────────────────────────────

    /** Element types in a Structurizr model. */
    enum class ElementType {
        PERSON, SOFTWARE_SYSTEM, CONTAINER, COMPONENT, DEPLOYMENT_NODE, INFRASTRUCTURE_NODE,
    }

    /** View types supported by the DSL parser. */
    enum class ViewType {
        SYSTEM_LANDSCAPE, SYSTEM_CONTEXT, CONTAINER, COMPONENT, DEPLOYMENT,
    }

    /** A parsed element from the DSL model section. */
    data class Element(
        val id: String,
        val name: String,
        val description: String = "",
        val type: ElementType,
        val technology: String = "",
        val parentId: String? = null,
    )

    /** A parsed relationship between two elements. */
    data class Relationship(
        val sourceId: String,
        val targetId: String,
        val description: String = "",
        val technology: String = "",
    )

    /** A parsed view definition. */
    data class View(
        val key: String,
        val type: ViewType,
        val title: String = "",
        val scope: String? = null,
        val includes: MutableList<String> = mutableListOf(),
        var autoLayout: String = "tb",
    )

    /** The complete parsed model including elements, relationships, and views. */
    data class ParsedModel(
        val elements: MutableList<Element> = mutableListOf(),
        val relationships: MutableList<Relationship> = mutableListOf(),
        val views: MutableList<View> = mutableListOf(),
    )

    /** Position and size of an element on the SVG canvas. */
    data class Position(val x: Int, val y: Int, val w: Int, val h: Int)
}
