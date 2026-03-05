/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.jakubjirak.structurizr.settings.StructurizrProjectSettings
import com.jakubjirak.structurizr.settings.StructurizrSettings
import java.io.File
import java.nio.file.Files
import kotlin.math.abs
import kotlin.math.min

/**
 * Project-level service that renders Structurizr DSL content to SVG.
 *
 * First attempts to use the external Structurizr CLI; falls back to a
 * lightweight built-in renderer when the CLI is unavailable.
 */
@Service(Service.Level.PROJECT)
class DiagramRendererService(private val project: Project) {

    private val log = Logger.getInstance(DiagramRendererService::class.java)

    private val settings: StructurizrSettings
        get() = StructurizrSettings.getInstance()

    private val projectSettings: StructurizrProjectSettings
        get() = StructurizrProjectSettings.getInstance(project)

    /**
     * Renders the given [dslContent] to SVG.
     *
     * @param dslContent the Structurizr DSL source text
     * @param viewKey    optional view key to select a specific diagram
     * @return the SVG string, or `null` on failure
     */
    fun renderToSvg(dslContent: String, viewKey: String? = null): String? = try {
        val cliPath = findStructurizrCli()
        if (cliPath != null) renderWithCli(dslContent, viewKey, cliPath) else renderWithBuiltIn(dslContent, viewKey)
    } catch (e: Exception) {
        log.warn("Failed to render diagram", e)
        null
    }

    private fun renderWithCli(dslContent: String, viewKey: String?, cliPath: String): String? {
        val tempDir = Files.createTempDirectory("structurizr-preview")
        val dslFile = tempDir.resolve("workspace.dsl").toFile()
        try {
            dslFile.writeText(dslContent, Charsets.UTF_8)
            val command = mutableListOf(cliPath, "export", "-workspace", dslFile.absolutePath, "-format", "plantuml")
            if (!viewKey.isNullOrBlank()) {
                command += listOf("-view", viewKey)
            }
            val process = ProcessBuilder(command).directory(tempDir.toFile()).redirectErrorStream(true).start()
            val output = process.inputStream.bufferedReader().readText()
            val exitCode = process.waitFor()
            if (exitCode == 0) {
                return tempDir.toFile().listFiles()?.firstOrNull { it.extension == "svg" }?.readText(Charsets.UTF_8)
            }
            log.warn("Structurizr CLI failed with exit code $exitCode: $output")
            return null
        } finally {
            tempDir.toFile().deleteRecursively()
        }
    }

    private fun renderWithBuiltIn(dslContent: String, @Suppress("UNUSED_PARAMETER") viewKey: String?): String {
        val model = parseDslContent(dslContent)
        return generateSvg(model)
    }

    private fun findStructurizrCli(): String? {
        val projectCliPath = projectSettings.getEffectiveCliPath()
        if (projectCliPath.isNotEmpty() && File(projectCliPath).exists()) return projectCliPath

        val globalCliPath = settings.structurizrCliPath
        if (globalCliPath.isNotEmpty() && File(globalCliPath).exists()) return globalCliPath

        val possiblePaths = listOfNotNull(
            "/usr/local/bin/structurizr-cli",
            "/opt/structurizr-cli/structurizr-cli",
            "${System.getProperty("user.home")}/.structurizr/structurizr-cli",
            System.getenv("STRUCTURIZR_CLI_HOME")?.let { "$it/structurizr-cli" },
        )
        return possiblePaths.firstOrNull { File(it).exists() && File(it).canExecute() }
    }

    // ── Built-in renderer ────────────────────────────────────────────────

    private fun parseDslContent(dslContent: String): DiagramModel {
        val model = DiagramModel()

        val workspaceMatch = Regex("""workspace\s+"([^"]+)"(?:\s+"([^"]+)")?""").find(dslContent)
        model.workspaceName = workspaceMatch?.groupValues?.get(1) ?: "Workspace"
        model.workspaceDescription = workspaceMatch?.groupValues?.get(2) ?: ""

        Regex("""(?:(\w+)\s*=\s*)?person\s+"([^"]+)"(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?""")
            .findAll(dslContent).forEach { match ->
                val id = match.groupValues[1].ifEmpty { "person_${model.elements.size}" }
                model.elements += DiagramElement(id, match.groupValues[2], match.groupValues[3], ElementType.PERSON, tags = match.groupValues[4])
            }

        Regex("""(?:(\w+)\s*=\s*)?softwareSystem\s+"([^"]+)"(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?""")
            .findAll(dslContent).forEach { match ->
                val id = match.groupValues[1].ifEmpty { "system_${model.elements.size}" }
                model.elements += DiagramElement(id, match.groupValues[2], match.groupValues[3], ElementType.SOFTWARE_SYSTEM, tags = match.groupValues[4])
            }

        Regex("""(?:(\w+)\s*=\s*)?container\s+"([^"]+)"(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?""")
            .findAll(dslContent).forEach { match ->
                model.elements += DiagramElement(
                    match.groupValues[1].ifEmpty { "container_${model.elements.size}" },
                    match.groupValues[2], match.groupValues[3], ElementType.CONTAINER,
                    match.groupValues[4], match.groupValues[5],
                )
            }

        Regex("""(?:(\w+)\s*=\s*)?component\s+"([^"]+)"(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?""")
            .findAll(dslContent).forEach { match ->
                model.elements += DiagramElement(
                    match.groupValues[1].ifEmpty { "component_${model.elements.size}" },
                    match.groupValues[2], match.groupValues[3], ElementType.COMPONENT,
                    match.groupValues[4], match.groupValues[5],
                )
            }

        Regex("""(\w+)\s*->\s*(\w+)(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?(?:\s+"([^"]+)")?""")
            .findAll(dslContent).forEach { match ->
                model.relationships += DiagramRelationship(
                    match.groupValues[1], match.groupValues[2],
                    match.groupValues[3].ifEmpty { "uses" },
                    match.groupValues[4], match.groupValues[5],
                )
            }

        return model
    }

    private fun generateSvg(model: DiagramModel): String {
        val elements = model.elements
        val relationships = model.relationships
        if (elements.isEmpty()) return generateEmptySvg()

        val elementWidth = 200
        val elementHeight = 120
        val padding = 50
        val cols = min(3, elements.size)
        val rows = (elements.size + cols - 1) / cols
        val width = cols * (elementWidth + padding) + padding
        val height = rows * (elementHeight + padding) + padding + 60

        val svg = StringBuilder()
        svg.append("""<svg xmlns="http://www.w3.org/2000/svg" width="$width" height="$height" viewBox="0 0 $width $height">""")
        svg.append("""
            <style>
            .title { font: bold 18px sans-serif; fill: #333; }
            .subtitle { font: 12px sans-serif; fill: #666; }
            .element-name { font: bold 13px sans-serif; fill: white; }
            .element-type { font: 10px sans-serif; fill: white; opacity: 0.8; }
            .element-tech { font: 9px sans-serif; fill: white; opacity: 0.7; }
            .relationship-label { font: 10px sans-serif; fill: #666; }
            </style>
        """.trimIndent())

        svg.append("""<text x="${width / 2}" y="30" class="title" text-anchor="middle">${escapeXml(model.workspaceName)}</text>""")
        if (model.workspaceDescription.isNotEmpty()) {
            svg.append("""<text x="${width / 2}" y="48" class="subtitle" text-anchor="middle">${escapeXml(model.workspaceDescription)}</text>""")
        }

        val positions = mutableMapOf<String, Position>()
        elements.forEachIndexed { index, el ->
            val col = index % cols
            val row = index / cols
            val x = padding + col * (elementWidth + padding)
            val y = 70 + row * (elementHeight + padding)
            positions[el.id] = Position(x, y, elementWidth, elementHeight)
        }

        svg.append("""
            <defs>
            <marker id="arrowhead" markerWidth="10" markerHeight="7" refX="10" refY="3.5" orient="auto">
                <polygon points="0 0, 10 3.5, 0 7" fill="#707070"/>
            </marker>
            </defs>
        """.trimIndent())

        for (rel in relationships) {
            val from = positions[rel.sourceId] ?: continue
            val to = positions[rel.targetId] ?: continue
            val fromCx = from.x + from.width / 2
            val fromCy = from.y + from.height / 2
            val toCx = to.x + to.width / 2
            val toCy = to.y + to.height / 2
            val (startX, startY) = calculateEdgePoint(fromCx, fromCy, toCx, toCy, from.width / 2, from.height / 2)
            val (endX, endY) = calculateEdgePoint(toCx, toCy, fromCx, fromCy, to.width / 2, to.height / 2)
            val midX = (startX + endX) / 2
            val midY = (startY + endY) / 2
            svg.append("""<line x1="$startX" y1="$startY" x2="$endX" y2="$endY" stroke="#707070" stroke-width="2" stroke-dasharray="5,5" marker-end="url(#arrowhead)"/>""")
            svg.append("""<text x="$midX" y="${midY - 5}" class="relationship-label" text-anchor="middle">${escapeXml(rel.description)}</text>""")
        }

        elements.forEachIndexed { _, el ->
            val pos = positions[el.id]!!
            svg.append(generateElementSvg(el, pos.x, pos.y, pos.width, pos.height))
        }
        svg.append("</svg>")
        return svg.toString()
    }

    private fun generateElementSvg(element: DiagramElement, x: Int, y: Int, width: Int, height: Int): String {
        val (bgColor, borderColor) = when (element.type) {
            ElementType.PERSON -> "#08427B" to "#063563"
            ElementType.SOFTWARE_SYSTEM -> "#1168BD" to "#0D5299"
            ElementType.CONTAINER -> "#438DD5" to "#3578B9"
            ElementType.COMPONENT -> "#85BBF0" to "#6FA8E0"
        }

        val svg = StringBuilder()
        if (element.type == ElementType.PERSON) {
            val headRadius = 18
            val headY = y + headRadius + 5
            val bodyY = y + headRadius * 2 + 10
            val bodyHeight = height - headRadius * 2 - 15
            svg.append("""
                <g>
                <circle cx="${x + width / 2}" cy="$headY" r="$headRadius" fill="$bgColor" stroke="$borderColor" stroke-width="2"/>
                <rect x="$x" y="$bodyY" width="$width" height="$bodyHeight" rx="4" fill="$bgColor" stroke="$borderColor" stroke-width="2"/>
                <text x="${x + width / 2}" y="${bodyY + 25}" class="element-name" text-anchor="middle">${escapeXml(truncate(element.name, 20))}</text>
                <text x="${x + width / 2}" y="${bodyY + 42}" class="element-type" text-anchor="middle">[Person]</text>
                </g>
            """.trimIndent())
        } else {
            svg.append("""
                <g>
                <rect x="$x" y="$y" width="$width" height="$height" rx="4" fill="$bgColor" stroke="$borderColor" stroke-width="2"/>
                <text x="${x + width / 2}" y="${y + 30}" class="element-name" text-anchor="middle">${escapeXml(truncate(element.name, 22))}</text>
                <text x="${x + width / 2}" y="${y + 48}" class="element-type" text-anchor="middle">[${element.type.displayName}]</text>
            """.trimIndent())
            if (element.technology.isNotEmpty()) {
                svg.append("""<text x="${x + width / 2}" y="${y + 65}" class="element-tech" text-anchor="middle">${escapeXml(element.technology)}</text>""")
            }
            if (element.description.isNotEmpty()) {
                val descY = if (element.technology.isNotEmpty()) y + 85 else y + 70
                svg.append("""<text x="${x + width / 2}" y="$descY" class="element-tech" text-anchor="middle">${escapeXml(truncate(element.description, 30))}</text>""")
            }
            svg.append("</g>")
        }
        return svg.toString()
    }

    private fun generateEmptySvg(): String = """
        <svg xmlns="http://www.w3.org/2000/svg" width="400" height="200" viewBox="0 0 400 200">
            <text x="200" y="90" text-anchor="middle" fill="#666" font-size="16" font-family="sans-serif">No elements found</text>
            <text x="200" y="115" text-anchor="middle" fill="#999" font-size="12" font-family="sans-serif">Add persons, systems, or containers to see them here</text>
        </svg>
    """.trimIndent()

    private fun calculateEdgePoint(fromX: Int, fromY: Int, toX: Int, toY: Int, halfWidth: Int, halfHeight: Int): Pair<Int, Int> {
        val dx = toX - fromX
        val dy = toY - fromY
        if (dx == 0 && dy == 0) return fromX to fromY

        val absDx = abs(dx)
        val absDy = abs(dy)
        return if (absDx * halfHeight > absDy * halfWidth) {
            val sign = if (dx > 0) 1 else -1
            (fromX + sign * halfWidth) to (fromY + sign * halfWidth * dy / absDx)
        } else {
            val sign = if (dy > 0) 1 else -1
            (fromX + sign * halfHeight * dx / absDy) to (fromY + sign * halfHeight)
        }
    }

    private fun escapeXml(text: String): String =
        text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")

    private fun truncate(text: String, maxLength: Int): String =
        if (text.length > maxLength) text.take(maxLength - 3) + "..." else text

    /**
     * Renders a diagram for the given [dslContent] and [viewType].
     *
     * @param viewType one of "System Landscape", "System Context", "Container",
     *                 "Component", "Deployment", or "Dynamic"
     * @return the SVG string, or `null` on failure
     */
    fun renderDiagram(dslContent: String, viewType: String): String? {
        val viewKey = when (viewType) {
            "System Landscape" -> "landscape"
            "System Context" -> "systemContext"
            "Container" -> "container"
            "Component" -> "component"
            "Deployment" -> "deployment"
            "Dynamic" -> "dynamic"
            else -> null
        }
        return renderToSvg(dslContent, viewKey)
    }

    // ── Inner data classes ───────────────────────────────────────────────

    /** Element types supported by the built-in renderer. */
    enum class ElementType(val displayName: String) {
        PERSON("Person"),
        SOFTWARE_SYSTEM("Software System"),
        CONTAINER("Container"),
        COMPONENT("Component"),
    }

    /** Positional information for an element on the SVG canvas. */
    data class Position(val x: Int, val y: Int, val width: Int, val height: Int)

    /** A single architectural element parsed from DSL. */
    data class DiagramElement(
        val id: String,
        val name: String,
        val description: String = "",
        val type: ElementType,
        val technology: String = "",
        val tags: String = "",
    )

    /** A relationship between two elements. */
    data class DiagramRelationship(
        val sourceId: String,
        val targetId: String,
        val description: String = "",
        val technology: String = "",
        val tags: String = "",
    )

    /** The complete parsed workspace model. */
    data class DiagramModel(
        var workspaceName: String = "Workspace",
        var workspaceDescription: String = "",
        val elements: MutableList<DiagramElement> = mutableListOf(),
        val relationships: MutableList<DiagramRelationship> = mutableListOf(),
    )

    companion object {
        /** Retrieves the project-level singleton instance. */
        @JvmStatic
        fun getInstance(project: Project): DiagramRendererService =
            project.getService(DiagramRendererService::class.java)
    }
}
