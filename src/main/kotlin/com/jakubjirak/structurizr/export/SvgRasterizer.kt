/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.export

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.awt.AlphaComposite
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.Shape
import java.awt.geom.Ellipse2D
import java.awt.geom.Line2D
import java.awt.geom.Path2D
import java.awt.geom.Point2D
import java.awt.geom.QuadCurve2D
import java.awt.geom.Rectangle2D
import java.awt.geom.RoundRectangle2D
import java.awt.image.BufferedImage
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import org.xml.sax.InputSource

/**
 * Rasterizes the SVG produced by the plugin's own diagram renderer
 * ([com.jakubjirak.structurizr.preview.dsl.SvgDiagramRenderer]) with Java2D.
 *
 * Supports exactly the subset that renderer emits: `rect`, `circle`, `line`, `path` (`M x y Q cx cy x y` / `L`),
 * `text`, `g`, solid colours, opacity, dash arrays and `marker-end` arrow heads. `defs` and `title` are skipped.
 */
object SvgRasterizer {

    private const val DEFAULT_WIDTH = 800
    private const val DEFAULT_HEIGHT = 600
    private const val MAX_DIMENSION = 16_384
    private const val ARROW_LENGTH = 10.0
    private const val ARROW_HALF_WIDTH = 4.0

    /** The SVG's own size in pixels (`width`/`height` attributes; defaults when absent). */
    fun size(svg: String): Pair<Int, Int> {
        val root = parse(svg)
        return (root.number("width") ?: DEFAULT_WIDTH.toDouble()).toInt() to (root.number("height") ?: DEFAULT_HEIGHT.toDouble()).toInt()
    }

    /** Renders [svg] at [scale] (1.0 = the SVG's own pixel size). */
    fun rasterize(svg: String, scale: Double = 1.0): BufferedImage {
        require(scale > 0) { "scale must be positive" }
        val root = parse(svg)
        val width = root.number("width") ?: DEFAULT_WIDTH.toDouble()
        val height = root.number("height") ?: DEFAULT_HEIGHT.toDouble()
        val pixelWidth = (width * scale).toInt().coerceIn(1, MAX_DIMENSION)
        val pixelHeight = (height * scale).toInt().coerceIn(1, MAX_DIMENSION)

        val image = BufferedImage(pixelWidth, pixelHeight, BufferedImage.TYPE_INT_ARGB)
        val g = image.createGraphics()
        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
            g.scale(scale, scale)
            val context = Context(width, height, fontFamily(root.getAttribute("font-family")))
            drawChildren(g, root, context)
        } finally {
            g.dispose()
        }
        return image
    }

    private class Context(val width: Double, val height: Double, val fontFamily: String)

    private fun parse(svg: String): Element {
        val factory = DocumentBuilderFactory.newInstance().apply {
            isNamespaceAware = false
            setFeature("http://apache.org/xml/features/disallow-doctype-decl", true)
            isExpandEntityReferences = false
        }
        val root = factory.newDocumentBuilder().parse(InputSource(StringReader(svg))).documentElement
        require(root.tagName == "svg") { "Not an SVG document" }
        return root
    }

    private fun drawChildren(g: Graphics2D, parent: Element, ctx: Context) {
        val children = parent.childNodes
        for (i in 0 until children.length) {
            val node = children.item(i)
            if (node.nodeType == Node.ELEMENT_NODE) draw(g, node as Element, ctx)
        }
    }

    private fun draw(g: Graphics2D, e: Element, ctx: Context) {
        when (e.tagName) {
            "g" -> drawChildren(g, e, ctx)
            "rect" -> paintShape(g, e, rect(e, ctx))
            "circle" -> {
                val r = e.number("r") ?: 0.0
                paintShape(g, e, Ellipse2D.Double((e.number("cx") ?: 0.0) - r, (e.number("cy") ?: 0.0) - r, 2 * r, 2 * r))
            }
            "line" -> drawLine(g, e)
            "path" -> drawPath(g, e)
            "text" -> drawText(g, e, ctx)
            else -> Unit // defs, title, marker definitions
        }
    }

    private fun rect(e: Element, ctx: Context): Shape {
        val x = e.number("x") ?: 0.0
        val y = e.number("y") ?: 0.0
        val w = e.length("width", ctx.width)
        val h = e.length("height", ctx.height)
        val rx = e.number("rx") ?: 0.0
        return if (rx > 0) RoundRectangle2D.Double(x, y, w, h, 2 * rx, 2 * rx) else Rectangle2D.Double(x, y, w, h)
    }

    private fun paintShape(g: Graphics2D, e: Element, shape: Shape) {
        color(e.getAttribute("fill"), e.number("fill-opacity"))?.let {
            g.color = it
            g.fill(shape)
        }
        stroke(g, e)?.let {
            g.color = it
            g.draw(shape)
        }
    }

    private fun drawLine(g: Graphics2D, e: Element) {
        val x1 = e.number("x1") ?: 0.0
        val y1 = e.number("y1") ?: 0.0
        val x2 = e.number("x2") ?: 0.0
        val y2 = e.number("y2") ?: 0.0
        val c = stroke(g, e) ?: return
        g.color = c
        g.draw(Line2D.Double(x1, y1, x2, y2))
        if (e.hasAttribute("marker-end")) arrowHead(g, c, Point2D.Double(x1, y1), Point2D.Double(x2, y2))
    }

    private fun drawPath(g: Graphics2D, e: Element) {
        val tokens = e.getAttribute("d").trim().split(Regex("[\\s,]+")).filter { it.isNotEmpty() }
        val path = Path2D.Double()
        var lastControl: Pair<Double, Double>? = null
        var current = 0.0 to 0.0
        var i = 0
        fun num() = tokens[i++].toDouble()
        while (i < tokens.size) {
            when (tokens[i++]) {
                "M" -> { current = num() to num(); path.moveTo(current.first, current.second) }
                "L" -> { lastControl = current; current = num() to num(); path.lineTo(current.first, current.second) }
                "Q" -> {
                    val cx = num()
                    val cy = num()
                    lastControl = cx to cy
                    current = num() to num()
                    path.quadTo(cx, cy, current.first, current.second)
                }
                else -> return // unsupported command: draw nothing rather than garbage
            }
        }
        val c = stroke(g, e) ?: return
        g.color = c
        g.draw(path)
        val from = lastControl
        if (e.hasAttribute("marker-end") && from != null) {
            arrowHead(g, c, Point2D.Double(from.first, from.second), Point2D.Double(current.first, current.second))
        }
    }

    /** Filled arrow head at [to], pointing away from [from] (the `marker-end` of the renderer's edges). */
    private fun arrowHead(g: Graphics2D, c: Color, from: Point2D, to: Point2D) {
        val toX = to.x
        val toY = to.y
        val angle = atan2(toY - from.y, toX - from.x)
        val back = toX - ARROW_LENGTH * cos(angle) to toY - ARROW_LENGTH * sin(angle)
        val head = Path2D.Double().apply {
            moveTo(toX, toY)
            lineTo(back.first + ARROW_HALF_WIDTH * sin(angle), back.second - ARROW_HALF_WIDTH * cos(angle))
            lineTo(back.first - ARROW_HALF_WIDTH * sin(angle), back.second + ARROW_HALF_WIDTH * cos(angle))
            closePath()
        }
        val previous = g.stroke
        g.stroke = BasicStroke(1f)
        g.color = c
        g.fill(head)
        g.stroke = previous
    }

    private fun drawText(g: Graphics2D, e: Element, ctx: Context) {
        val text = e.textContent ?: return
        if (text.isBlank()) return
        val size = (e.number("font-size") ?: 12.0).toFloat()
        val style = if (e.getAttribute("font-weight") == "bold") Font.BOLD else Font.PLAIN
        g.font = Font(ctx.fontFamily, style, 1).deriveFont(size)
        val c = color(e.getAttribute("fill").ifEmpty { "#000000" }, e.number("fill-opacity")) ?: return
        val width = g.fontMetrics.stringWidth(text)
        val x = e.number("x") ?: 0.0
        val anchorOffset = when (e.getAttribute("text-anchor")) {
            "middle" -> width / 2.0
            "end" -> width.toDouble()
            else -> 0.0
        }
        g.color = c
        g.drawString(text, (x - anchorOffset).toFloat(), (e.number("y") ?: 0.0).toFloat())
    }

    /** Applies the element's stroke settings and returns its colour, or null when it has no stroke. */
    private fun stroke(g: Graphics2D, e: Element): Color? {
        val c = color(e.getAttribute("stroke"), e.number("stroke-opacity")) ?: return null
        val width = (e.number("stroke-width") ?: 1.0).toFloat()
        val dash = e.getAttribute("stroke-dasharray").split(',', ' ').mapNotNull { it.trim().toFloatOrNull() }
        g.stroke = if (dash.isEmpty() || dash.all { it == 0f }) {
            BasicStroke(width)
        } else {
            BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dash.toFloatArray(), 0f)
        }
        g.composite = AlphaComposite.SrcOver
        return c
    }

    /** Parses `#rgb`, `#rrggbb`, `white`/`black`; `none`/empty means no paint. */
    internal fun color(value: String?, opacity: Double?): Color? {
        val v = value?.trim()?.lowercase().orEmpty()
        val rgb = when {
            v.isEmpty() || v == "none" || v == "transparent" -> return null
            v == "white" -> 0xFFFFFF
            v == "black" -> 0x000000
            v.matches(Regex("#[0-9a-f]{6}")) -> v.substring(1).toInt(16)
            v.matches(Regex("#[0-9a-f]{3}")) -> v.substring(1).map { "$it$it" }.joinToString("").toInt(16)
            else -> 0x000000
        }
        val alpha = ((opacity ?: 1.0).coerceIn(0.0, 1.0) * 255).toInt()
        return Color((rgb shr 16) and 0xFF, (rgb shr 8) and 0xFF, rgb and 0xFF, alpha)
    }
}

private fun fontFamily(value: String): String =
    value.split(',').map { it.trim().trim('"', '\'') }.firstOrNull { it.isNotEmpty() && it != "sans-serif" } ?: Font.SANS_SERIF

private fun Element.number(name: String): Double? =
    getAttribute(name).trim().removeSuffix("px").toDoubleOrNull()

private fun Element.length(name: String, reference: Double): Double {
    val raw = getAttribute(name).trim()
    return if (raw.endsWith("%")) {
        (raw.removeSuffix("%").toDoubleOrNull() ?: 0.0) / 100.0 * reference
    } else {
        raw.removeSuffix("px").toDoubleOrNull() ?: 0.0
    }
}
