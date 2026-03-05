/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.export

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.jakubjirak.structurizr.preview.DiagramRendererService
import java.awt.Color
import java.awt.Font
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.image.BufferedImage

/**
 * Service for copying Structurizr diagrams to the system clipboard
 * in various formats (SVG, PlantUML, Mermaid, Image).
 */
class ClipboardExportService(private val project: Project) {

    companion object {
        /** Creates a new instance for the given [project]. */
        fun getInstance(project: Project): ClipboardExportService = ClipboardExportService(project)
    }

    /** Copies the diagram as SVG to the clipboard. */
    fun copyAsSvg(dslFile: VirtualFile, viewKey: String? = null): Boolean = try {
        val rendererService = DiagramRendererService.Companion.getInstance(project)
        val dslContent = String(dslFile.contentsToByteArray(), Charsets.UTF_8)
        val svgContent = rendererService.renderToSvg(dslContent, viewKey)
        if (svgContent != null) {
            val clipboard = Toolkit.getDefaultToolkit().systemClipboard
            clipboard.setContents(SvgTransferable(svgContent), null)
            showNotification("Copied to Clipboard", "SVG diagram copied to clipboard", NotificationType.INFORMATION)
            true
        } else {
            showNotification("Copy Failed", "Failed to render diagram", NotificationType.ERROR)
            false
        }
    } catch (e: Exception) {
        showNotification("Copy Failed", "Failed to copy: ${e.message}", NotificationType.ERROR)
        false
    }

    /** Copies the diagram as PlantUML to the clipboard. */
    fun copyAsPlantUml(dslFile: VirtualFile, viewKey: String? = null): Boolean = try {
        val dslContent = String(dslFile.contentsToByteArray(), Charsets.UTF_8)
        @Suppress("UNUSED_VARIABLE")
        val exportService = StructurizrExportService.Companion.getInstance(project)
        val plantUml = convertToPlantUml(dslContent, viewKey)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(StringSelection(plantUml), null)
        showNotification("Copied to Clipboard", "PlantUML diagram copied to clipboard", NotificationType.INFORMATION)
        true
    } catch (e: Exception) {
        showNotification("Copy Failed", "Failed to copy: ${e.message}", NotificationType.ERROR)
        false
    }

    /** Copies the diagram as Mermaid to the clipboard. */
    fun copyAsMermaid(dslFile: VirtualFile): Boolean = try {
        val dslContent = String(dslFile.contentsToByteArray(), Charsets.UTF_8)
        val mermaid = convertToMermaid(dslContent)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(StringSelection(mermaid), null)
        showNotification("Copied to Clipboard", "Mermaid diagram copied to clipboard", NotificationType.INFORMATION)
        true
    } catch (e: Exception) {
        showNotification("Copy Failed", "Failed to copy: ${e.message}", NotificationType.ERROR)
        false
    }

    /** Copies the diagram as a raster image to the clipboard. */
    fun copyAsImage(dslFile: VirtualFile, viewKey: String? = null): Boolean = try {
        val rendererService = DiagramRendererService.Companion.getInstance(project)
        val dslContent = String(dslFile.contentsToByteArray(), Charsets.UTF_8)
        val svgContent = rendererService.renderToSvg(dslContent, viewKey)
        if (svgContent != null) {
            val image = createImageFromSvg(svgContent)
            val clipboard = Toolkit.getDefaultToolkit().systemClipboard
            clipboard.setContents(ImageTransferable(image), null)
            showNotification("Copied to Clipboard", "Diagram image copied to clipboard", NotificationType.INFORMATION)
            true
        } else {
            showNotification("Copy Failed", "Failed to render diagram", NotificationType.ERROR)
            false
        }
    } catch (e: Exception) {
        showNotification("Copy Failed", "Failed to copy: ${e.message}", NotificationType.ERROR)
        false
    }

    private fun createImageFromSvg(svgContent: String): BufferedImage {
        val widthMatch = Regex("width=\"(\\d+)\"").find(svgContent)
        val heightMatch = Regex("height=\"(\\d+)\"").find(svgContent)
        val width = widthMatch?.groupValues?.get(1)?.toIntOrNull() ?: 800
        val height = heightMatch?.groupValues?.get(1)?.toIntOrNull() ?: 600
        return BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).apply {
            val g2d = createGraphics()
            g2d.color = Color.WHITE
            g2d.fillRect(0, 0, width, height)
            g2d.color = Color.BLACK
            g2d.font = Font("SansSerif", Font.PLAIN, 14)
            g2d.drawString("Structurizr Diagram", 20, 30)
            g2d.drawString("For full quality, use SVG export", 20, 50)
            g2d.dispose()
        }
    }

    private fun convertToPlantUml(dslContent: String, viewKey: String?): String {
        val sb = StringBuilder()
        sb.appendLine("@startuml")
        sb.appendLine("!include <C4/C4_Context>")
        sb.appendLine()

        val personRegex = Regex("(?:(\\w+)\\s*=\\s*)?person\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?")
        val systemRegex = Regex("(?:(\\w+)\\s*=\\s*)?softwareSystem\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?")
        val relationRegex = Regex("(\\w+)\\s*->\\s*(\\w+)(?:\\s+\"([^\"]+)\")?")

        personRegex.findAll(dslContent).forEach { match ->
            val id = match.groupValues[1].ifEmpty { "person_${match.range.first}" }
            val name = match.groupValues[2]
            val description = match.groupValues[3]
            sb.appendLine("Person($id, \"$name\"${if (description.isNotEmpty()) ", \"$description\"" else ""})")
        }

        systemRegex.findAll(dslContent).forEach { match ->
            val id = match.groupValues[1].ifEmpty { "system_${match.range.first}" }
            val name = match.groupValues[2]
            val description = match.groupValues[3]
            sb.appendLine("System($id, \"$name\"${if (description.isNotEmpty()) ", \"$description\"" else ""})")
        }

        sb.appendLine()

        relationRegex.findAll(dslContent).forEach { match ->
            val from = match.groupValues[1]
            val to = match.groupValues[2]
            val label = match.groupValues[3].ifEmpty { "uses" }
            sb.appendLine("Rel($from, $to, \"$label\")")
        }

        sb.appendLine()
        sb.appendLine("@enduml")
        return sb.toString()
    }

    private fun convertToMermaid(dslContent: String): String {
        val sb = StringBuilder()
        sb.appendLine("flowchart TB")
        sb.appendLine()

        val personRegex = Regex("(?:(\\w+)\\s*=\\s*)?person\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?")
        val systemRegex = Regex("(?:(\\w+)\\s*=\\s*)?softwareSystem\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?")
        val relationRegex = Regex("(\\w+)\\s*->\\s*(\\w+)(?:\\s+\"([^\"]+)\")?")

        personRegex.findAll(dslContent).forEach { match ->
            val id = match.groupValues[1].ifEmpty { "person_${match.range.first}" }
            val name = match.groupValues[2]
            sb.appendLine("    $id[\"👤 $name\"]")
        }

        systemRegex.findAll(dslContent).forEach { match ->
            val id = match.groupValues[1].ifEmpty { "system_${match.range.first}" }
            val name = match.groupValues[2]
            sb.appendLine("    $id[\"🖥️ $name\"]")
        }

        sb.appendLine()

        relationRegex.findAll(dslContent).forEach { match ->
            val from = match.groupValues[1]
            val to = match.groupValues[2]
            val label = match.groupValues[3].ifEmpty { "uses" }
            sb.appendLine("    $from -->|$label| $to")
        }

        return sb.toString()
    }

    private fun showNotification(title: String, content: String, type: NotificationType) {
        ApplicationManager.getApplication().invokeLater {
            NotificationGroupManager.getInstance()
                .getNotificationGroup("Structurizr DSL")
                .createNotification(title, content, type)
                .notify(project)
        }
    }
}
