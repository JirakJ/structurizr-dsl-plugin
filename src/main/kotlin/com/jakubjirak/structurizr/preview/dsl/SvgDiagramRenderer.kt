/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview.dsl

import kotlin.math.abs
import kotlin.math.max

/**
 * Draws a [ViewDiagram] as a standalone SVG document in the C4 colour scheme.
 *
 * Layout is a simple layered grid: people on top, the elements in focus (inside their boundary when the
 * view has one) in the middle, everything else below. It is meant as a fast, dependency-free preview,
 * not a replacement for Structurizr's own renderers.
 */
object SvgDiagramRenderer {

    private const val BOX_W = 220
    private const val BOX_H = 140
    private const val GAP_X = 130
    private const val GAP_Y = 110
    private const val MARGIN = 40
    private const val MAX_COLUMNS = 3
    private const val BOUNDARY_PAD = 28
    private const val BOUNDARY_LABEL = 26

    private data class Box(val x: Int, val y: Int, val w: Int, val h: Int) {
        val cx get() = x + w / 2
        val cy get() = y + h / 2
    }

    fun render(diagram: ViewDiagram): String {
        if (diagram.message != null && diagram.elements.isEmpty()) return renderMessage(diagram)

        val boxes = linkedMapOf<DslElement, Box>()
        val boundaryBoxes = mutableListOf<Pair<DiagramBoundary, Box>>()
        val inBoundary = diagram.boundaries.flatMap { it.members }.toHashSet()
        val people = diagram.elements.filter { it.type == DslElementType.PERSON && it !in inBoundary }
        val middle = diagram.elements.filter { it in diagram.focus && it !in inBoundary && it !in people }
        val rest = diagram.elements.filter { it !in inBoundary && it !in people && it !in middle }

        val headerHeight = if (diagram.description.isNotBlank()) 70 else 50
        var y = MARGIN + headerHeight

        fun gridSize(count: Int): Pair<Int, Int> {
            val cols = minOf(MAX_COLUMNS, count)
            val rowsCount = (count + cols - 1) / cols
            return (cols * BOX_W + (cols - 1) * GAP_X) to (rowsCount * BOX_H + (rowsCount - 1) * GAP_Y)
        }

        fun placeGrid(items: List<DslElement>, left: Int, top: Int) {
            val cols = minOf(MAX_COLUMNS, items.size)
            items.forEachIndexed { i, e ->
                boxes[e] = Box(left + (i % cols) * (BOX_W + GAP_X), top + (i / cols) * (BOX_H + GAP_Y), BOX_W, BOX_H)
            }
        }

        val layoutRows = mutableListOf<Triple<Int, Int, (Int, Int) -> Unit>>() // width, height, place(left, top)

        if (people.isNotEmpty()) {
            val (w, h) = gridSize(people.size)
            layoutRows += Triple(w, h) { left, top -> placeGrid(people, left, top) }
        }
        if (middle.isNotEmpty()) {
            val (w, h) = gridSize(middle.size)
            layoutRows += Triple(w, h) { left, top -> placeGrid(middle, left, top) }
        }
        if (diagram.boundaries.isNotEmpty()) {
            val sizes = diagram.boundaries.map { b ->
                gridSize(b.members.size).let { (w, h) -> (w + 2 * BOUNDARY_PAD) to (h + 2 * BOUNDARY_PAD + BOUNDARY_LABEL) }
            }
            val width = sizes.sumOf { it.first } + (sizes.size - 1) * GAP_X
            val height = sizes.maxOf { it.second }
            layoutRows += Triple(width, height) { left, top ->
                var x = left
                diagram.boundaries.forEachIndexed { i, b ->
                    val (bw, bh) = sizes[i]
                    boundaryBoxes += b to Box(x, top, bw, bh)
                    placeGrid(b.members, x + BOUNDARY_PAD, top + BOUNDARY_PAD)
                    x += bw + GAP_X
                }
            }
        }
        if (rest.isNotEmpty()) {
            val (w, h) = gridSize(rest.size)
            layoutRows += Triple(w, h) { left, top -> placeGrid(rest, left, top) }
        }
        val canvasWidth = max(640, (layoutRows.maxOfOrNull { it.first } ?: 0) + 2 * MARGIN)
        for ((w, h, place) in layoutRows) {
            place((canvasWidth - w) / 2, y)
            y += h + GAP_Y
        }
        val canvasHeight = y - GAP_Y + MARGIN + 30

        val svg = StringBuilder()
        svg.append("""<svg xmlns="http://www.w3.org/2000/svg" width="$canvasWidth" height="$canvasHeight" viewBox="0 0 $canvasWidth""")
        svg.append(""" $canvasHeight" font-family="Arial, Helvetica, sans-serif">""")
        svg.append("""<defs><marker id="arrow" markerWidth="10" markerHeight="8" refX="9" refY="4" orient="auto">""")
        svg.append("""<polygon points="0 0, 10 4, 0 8" fill="#707070"/></marker></defs>""")
        svg.append("""<rect width="100%" height="100%" fill="#ffffff"/>""")
        svg.append("""<text x="$MARGIN" y="${MARGIN + 6}" font-size="20" font-weight="bold" fill="#333333">${esc(diagram.title)}</text>""")
        if (diagram.description.isNotBlank()) {
            svg.append("""<text x="$MARGIN" y="${MARGIN + 30}" font-size="13" fill="#666666">${esc(diagram.description)}</text>""")
        }

        for ((boundary, box) in boundaryBoxes) {
            svg.append("""<rect x="${box.x}" y="${box.y}" width="${box.w}" height="${box.h}" rx="6" fill="none" stroke="#888888"""")
            svg.append(""" stroke-width="1.5" stroke-dasharray="8,5"/>""")
            svg.append("""<text x="${box.x + 12}" y="${box.y + box.h - 10}" font-size="13" font-weight="bold"""")
            svg.append(""" fill="#555555">${esc(boundary.label)}</text>""")
        }

        val labels = StringBuilder()
        for (edge in diagram.edges) {
            val from = boxes[edge.source] ?: continue
            val to = boxes[edge.destination] ?: continue
            val others = boxes.values.filter { it !== from && it !== to }
            var (x1, y1) = clip(from, to.cx, to.cy)
            var (x2, y2) = clip(to, from.cx, from.cy)
            var mx = (x1 + x2) / 2
            var my = (y1 + y2) / 2
            if (others.any { crosses(it, x1, y1, x2, y2) }) {
                // A straight line would run through another element: bow the edge out to whichever side is clear.
                val (rawX, rawY) = detour(from, to, others)
                // Keep the curve on the canvas (a quadratic curve reaches half-way to its control point).
                val cx = rawX.coerceIn(-MARGIN, canvasWidth + MARGIN)
                val cy = rawY.coerceIn(headerHeight, canvasHeight + MARGIN)
                x1 = clip(from, cx, cy).first; y1 = clip(from, cx, cy).second
                x2 = clip(to, cx, cy).first; y2 = clip(to, cx, cy).second
                svg.append("""<path d="M $x1 $y1 Q $cx $cy $x2 $y2" fill="none" stroke="#707070" stroke-width="1.6"""")
                svg.append(""" stroke-dasharray="6,4" marker-end="url(#arrow)"/>""")
                mx = (x1 + 2 * cx + x2) / 4
                my = (y1 + 2 * cy + y2) / 4
            } else {
                svg.append("""<line x1="$x1" y1="$y1" x2="$x2" y2="$y2" stroke="#707070" stroke-width="1.6" stroke-dasharray="6,4"""")
                svg.append(""" marker-end="url(#arrow)"/>""")
            }
            val text = listOfNotNull(edge.order?.let { "$it." }, edge.description.takeIf { it.isNotBlank() }).joinToString(" ")
            val lines = wrap(text, 30, 2) + listOfNotNull(edge.technology.takeIf { it.isNotBlank() }?.let { "[$it]" })
            if (lines.isNotEmpty()) {
                val w = (lines.maxOf { it.length } * 6.2).toInt() + 12
                val h = lines.size * 14 + 6
                labels.append("""<rect x="${mx - w / 2}" y="${my - h / 2}" width="$w" height="$h" rx="3" fill="#ffffff"""")
                labels.append(""" fill-opacity="0.92"/>""")
                lines.forEachIndexed { i, line ->
                    labels.append("""<text x="$mx" y="${my - h / 2 + 15 + i * 14}" font-size="11" text-anchor="middle"""")
                    labels.append(""" fill="#555555">${esc(line)}</text>""")
                }
            }
        }

        for ((element, box) in boxes) svg.append(elementSvg(element, box))
        svg.append(labels)
        svg.append("</svg>")
        return svg.toString()
    }

    private fun renderMessage(diagram: ViewDiagram): String {
        val lines = wrap(diagram.message ?: "", 70, 4)
        val height = 120 + lines.size * 20
        val sb = StringBuilder()
        sb.append("""<svg xmlns="http://www.w3.org/2000/svg" width="640" height="$height" viewBox="0 0 640 $height" font-family="Arial,""")
        sb.append(""" Helvetica, sans-serif">""")
        sb.append("""<rect width="100%" height="100%" fill="#ffffff"/>""")
        sb.append("""<text x="320" y="50" font-size="18" font-weight="bold" text-anchor="middle"""")
        sb.append(""" fill="#333333">${esc(diagram.title)}</text>""")
        lines.forEachIndexed { i, line ->
            sb.append("""<text x="320" y="${90 + i * 20}" font-size="13" text-anchor="middle" fill="#777777">${esc(line)}</text>""")
        }
        sb.append("</svg>")
        return sb.toString()
    }

    /** True when the segment (x1,y1)-(x2,y2) passes through [box] (with a small margin). */
    private fun crosses(box: Box, x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
        val steps = 24
        for (i in 1 until steps) {
            val x = x1 + (x2 - x1) * i / steps
            val y = y1 + (y2 - y1) * i / steps
            if (x > box.x - 6 && x < box.x + box.w + 6 && y > box.y - 6 && y < box.y + box.h + 6) return true
        }
        return false
    }

    /** Control point for a curved edge from [from] to [to] that avoids [others] as far as possible. */
    private fun detour(from: Box, to: Box, others: List<Box>): Pair<Int, Int> {
        val dx = (to.cx - from.cx).toDouble()
        val dy = (to.cy - from.cy).toDouble()
        val length = kotlin.math.sqrt(dx * dx + dy * dy).coerceAtLeast(1.0)
        val nx = -dy / length
        val ny = dx / length
        val mx = (from.cx + to.cx) / 2.0
        val my = (from.cy + to.cy) / 2.0
        val candidates = listOf(0.45, -0.45, 0.8, -0.8).map { k ->
            val bend = length * k
            (mx + nx * bend).toInt() to (my + ny * bend).toInt()
        }
        fun hits(c: Pair<Int, Int>): Int = others.count { box ->
            (1 until 20).any { i ->
                val t = i / 20.0
                val x = (1 - t) * (1 - t) * from.cx + 2 * (1 - t) * t * c.first + t * t * to.cx
                val y = (1 - t) * (1 - t) * from.cy + 2 * (1 - t) * t * c.second + t * t * to.cy
                x > box.x - 6 && x < box.x + box.w + 6 && y > box.y - 6 && y < box.y + box.h + 6
            }
        }
        return candidates.minBy { hits(it) }
    }

    /** Point where the line from the centre of [box] towards (tx, ty) leaves the box. */
    private fun clip(box: Box, tx: Int, ty: Int): Pair<Int, Int> {
        val dx = (tx - box.cx).toDouble()
        val dy = (ty - box.cy).toDouble()
        if (dx == 0.0 && dy == 0.0) return box.cx to box.cy
        val hw = box.w / 2.0 + 4
        val hh = box.h / 2.0 + 4
        val scale = if (abs(dx) * hh > abs(dy) * hw) hw / abs(dx) else hh / abs(dy)
        return (box.cx + dx * scale).toInt() to (box.cy + dy * scale).toInt()
    }

    private fun colors(type: DslElementType): Triple<String, String, String> = when (type) {
        DslElementType.PERSON -> Triple("#08427b", "#052e56", "#ffffff")
        DslElementType.SOFTWARE_SYSTEM, DslElementType.SOFTWARE_SYSTEM_INSTANCE -> Triple("#1168bd", "#0b4884", "#ffffff")
        DslElementType.CONTAINER, DslElementType.CONTAINER_INSTANCE -> Triple("#438dd5", "#2e6295", "#ffffff")
        DslElementType.COMPONENT -> Triple("#85bbf0", "#5d82a8", "#000000")
        DslElementType.DEPLOYMENT_NODE -> Triple("#ffffff", "#888888", "#000000")
        DslElementType.INFRASTRUCTURE_NODE -> Triple("#eeeeee", "#999999", "#000000")
    }

    private fun typeLabel(element: DslElement): String {
        val base = when (element.type) {
            DslElementType.CONTAINER_INSTANCE -> "Container"
            DslElementType.SOFTWARE_SYSTEM_INSTANCE -> "Software System"
            else -> element.type.displayName
        }
        return if (element.technology.isNotBlank()) "[$base: ${element.technology}]" else "[$base]"
    }

    private fun elementSvg(element: DslElement, b: Box): String {
        val (fill, stroke, text) = colors(element.type)
        val sb = StringBuilder("<g>")
        var top = b.y
        if (element.type == DslElementType.PERSON) {
            sb.append("""<circle cx="${b.cx}" cy="${b.y + 22}" r="20" fill="$fill" stroke="$stroke" stroke-width="2"/>""")
            top = b.y + 36
            sb.append("""<rect x="${b.x}" y="$top" width="${b.w}" height="${b.h - 36}" rx="18" fill="$fill" stroke="$stroke"""")
            sb.append(""" stroke-width="2"/>""")
        } else {
            sb.append("""<rect x="${b.x}" y="${b.y}" width="${b.w}" height="${b.h}" rx="6" fill="$fill" stroke="$stroke"""")
            sb.append(""" stroke-width="2"/>""")
        }
        val nameLines = wrap(element.name, 26, 2)
        val typeLine = typeLabel(element)
        val descLines = wrap(element.description, 34, if (element.type == DslElementType.PERSON) 1 else 3)
        val total = nameLines.size * 17 + 15 + descLines.size * 13 + if (descLines.isEmpty()) 0 else 6
        var ty = top + ((b.y + b.h - top) - total) / 2 + 13
        for (line in nameLines) {
            sb.append("""<text x="${b.cx}" y="$ty" font-size="14" font-weight="bold" text-anchor="middle"""")
            sb.append(""" fill="$text">${esc(line)}</text>""")
            ty += 17
        }
        sb.append("""<text x="${b.cx}" y="$ty" font-size="10" text-anchor="middle" fill="$text"""")
        sb.append(""" fill-opacity="0.85">${esc(typeLine)}</text>""")
        ty += 15 + if (descLines.isEmpty()) 0 else 6
        for (line in descLines) {
            sb.append("""<text x="${b.cx}" y="$ty" font-size="11" text-anchor="middle" fill="$text"""")
            sb.append(""" fill-opacity="0.9">${esc(line)}</text>""")
            ty += 13
        }
        sb.append("<title>${esc(element.name)}${if (element.description.isNotBlank()) " – " + esc(element.description) else ""}</title>")
        sb.append("</g>")
        return sb.toString()
    }

    /** Word-wraps [text] into at most [maxLines] lines of about [width] characters, ellipsising the rest. */
    internal fun wrap(text: String, width: Int, maxLines: Int): List<String> {
        if (text.isBlank()) return emptyList()
        val lines = mutableListOf<String>()
        var current = StringBuilder()
        for (word in text.trim().split(Regex("\\s+"))) {
            if (current.isNotEmpty() && current.length + 1 + word.length > width) {
                lines += current.toString()
                current = StringBuilder()
            }
            if (current.isNotEmpty()) current.append(' ')
            current.append(word)
        }
        if (current.isNotEmpty()) lines += current.toString()
        val clipped = lines.map { if (it.length > width) it.take(width - 1) + "…" else it }
        if (clipped.size <= maxLines) return clipped
        return clipped.take(maxLines - 1) + (clipped[maxLines - 1].take(width - 1) + "…")
    }

    private fun esc(s: String): String =
        s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
}
