/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.export

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.jakubjirak.structurizr.preview.DiagramRendererService
import com.jakubjirak.structurizr.preview.ExportFormat
import com.jakubjirak.structurizr.preview.StructurizrCliService
import com.jakubjirak.structurizr.settings.StructurizrProjectSettings
import com.jakubjirak.structurizr.settings.StructurizrSettings
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.Comparator
import javax.imageio.ImageIO
import kotlin.io.extension

/**
 * Project-level service for exporting Structurizr diagrams to various formats.
 */
@Service(Service.Level.PROJECT)
class StructurizrExportService(private val project: Project) {

    private val log = Logger.getInstance(StructurizrExportService::class.java)

    private val settings: StructurizrSettings
        get() = StructurizrSettings.Companion.getInstance()

    private val projectSettings: StructurizrProjectSettings
        get() = StructurizrProjectSettings.Companion.getInstance(project)

    companion object {
        /** Returns the service instance for the given [project]. */
        fun getInstance(project: Project): StructurizrExportService =
            project.service()
    }

    /** Nested enum representing Structurizr view types. */
    enum class ViewType {
        SYSTEM_LANDSCAPE, SYSTEM_CONTEXT, CONTAINER, COMPONENT, DEPLOYMENT, DYNAMIC, FILTERED
    }

    /** Data class representing a single view extracted from DSL. */
    data class ViewInfo(val key: String, val name: String, val type: ViewType)

    /** Returns the default export format from settings. */
    fun getDefaultFormat(): DiagramExportFormat =
        DiagramExportFormat.entries.firstOrNull { it.name.equals(settings.defaultExportFormat, ignoreCase = true) }
            ?: DiagramExportFormat.PNG

    /** Returns the default export directory from project settings. */
    fun getDefaultExportDirectory(): String = projectSettings.getEffectiveExportDirectory()

    /** Returns the configured export image dimensions. */
    fun getExportDimensions(): Pair<Int, Int> = settings.exportImageWidth to settings.exportImageHeight

    /**
     * Exports a diagram asynchronously.
     *
     * @param dslFile the source DSL file
     * @param outputPath the target output path
     * @param format the export format
     * @param viewKey optional view key to export
     * @param callback invoked with (success, message)
     */
    fun export(
        dslFile: VirtualFile,
        outputPath: Path,
        format: DiagramExportFormat,
        viewKey: String? = null,
        callback: (Boolean, String) -> Unit
    ) {
        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Exporting Diagram to ${format.displayName}", true) {
            override fun run(indicator: ProgressIndicator) {
                try {
                    indicator.isIndeterminate = false
                    indicator.fraction = 0.0
                    indicator.text = "Preparing export..."

                    when (format) {
                        DiagramExportFormat.SVG -> exportToSvg(dslFile, outputPath, viewKey, indicator)
                        DiagramExportFormat.PNG -> exportToPng(dslFile, outputPath, viewKey, indicator)
                        DiagramExportFormat.PLANTUML -> exportToPlantUml(dslFile, outputPath, viewKey, indicator)
                        DiagramExportFormat.MERMAID -> exportToMermaid(dslFile, outputPath, viewKey, indicator)
                        DiagramExportFormat.DOT -> exportToDot(dslFile, outputPath, viewKey, indicator)
                        DiagramExportFormat.JSON -> exportToJson(dslFile, outputPath, indicator)
                    }

                    indicator.fraction = 1.0
                    LocalFileSystem.getInstance().refreshAndFindFileByPath(outputPath.toString())
                    callback(true, "Successfully exported to ${outputPath.fileName}")
                    showNotification("Export Complete", "Diagram exported to ${outputPath.fileName}", NotificationType.INFORMATION)
                } catch (e: Exception) {
                    log.error("Export failed", e)
                    callback(false, "Export failed: ${e.message}")
                }
            }
        })
    }

    /**
     * Exports all views from a DSL file to the given output directory.
     *
     * @param dslFile the source DSL file
     * @param outputDir the output directory
     * @param format the export format
     * @param callback invoked with (success, message)
     */
    fun exportAllViews(
        dslFile: VirtualFile,
        outputDir: Path,
        format: DiagramExportFormat,
        callback: (Boolean, String) -> Unit
    ) {
        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Exporting All Views", true) {
            override fun run(indicator: ProgressIndicator) {
                try {
                    indicator.isIndeterminate = false
                    indicator.text = "Analyzing DSL file..."

                    val dslContent = DiagramRendererService.currentText(dslFile)
                    val views = extractViews(dslContent)

                    if (views.isEmpty()) {
                        val outputPath = outputDir.resolve("${dslFile.nameWithoutExtension}.${format.extension}")
                        export(dslFile, outputPath, format, null) { _, _ -> }
                    } else {
                        views.forEachIndexed { index, viewInfo ->
                            indicator.fraction = index.toDouble() / views.size
                            indicator.text = "Exporting ${viewInfo.name}..."
                            val outputPath = outputDir.resolve("${viewInfo.name}.${format.extension}")
                            exportSingleView(dslFile, outputPath, format, viewInfo.key, indicator)
                        }
                    }

                    val count = if (views.isEmpty()) 1 else views.size
                    callback(true, "Exported $count diagram(s)")
                    showNotification("Bulk Export Complete", "Exported $count diagram(s) to $outputDir", NotificationType.INFORMATION)
                } catch (e: Exception) {
                    log.error("Bulk export failed", e)
                    callback(false, "Export failed: ${e.message}")
                }
            }
        })
    }

    private fun exportToSvg(dslFile: VirtualFile, outputPath: Path, viewKey: String?, indicator: ProgressIndicator) {
        indicator.text = "Generating SVG..."
        indicator.fraction = 0.3
        val cliService = StructurizrCliService.Companion.getInstance()
        if (cliService.isCliAvailable()) {
            exportWithCli(dslFile, outputPath, ExportFormat.SVG, viewKey, indicator)
        } else {
            val rendererService = DiagramRendererService.Companion.getInstance(project)
            val dslContent = DiagramRendererService.currentText(dslFile)
            val svgContent = rendererService.renderToSvg(dslContent, viewKey, dslFile)
                ?: throw Exception("Failed to render SVG")
            indicator.fraction = 0.8
            Files.writeString(outputPath, svgContent)
        }
    }

    private fun exportToPng(dslFile: VirtualFile, outputPath: Path, viewKey: String?, indicator: ProgressIndicator) {
        indicator.text = "Generating PNG..."
        indicator.fraction = 0.3
        val cliService = StructurizrCliService.Companion.getInstance()
        if (cliService.isCliAvailable()) {
            exportWithCli(dslFile, outputPath, ExportFormat.PNG, viewKey, indicator)
        } else {
            val rendererService = DiagramRendererService.Companion.getInstance(project)
            val dslContent = DiagramRendererService.currentText(dslFile)
            val svgContent = rendererService.renderToSvg(dslContent, viewKey, dslFile)
                ?: throw Exception("Failed to render diagram")
            indicator.fraction = 0.6
            indicator.text = "Converting to PNG..."
            convertSvgToPng(svgContent, outputPath)
        }
    }

    private fun exportToPlantUml(dslFile: VirtualFile, outputPath: Path, viewKey: String?, indicator: ProgressIndicator) {
        indicator.text = "Generating PlantUML..."
        indicator.fraction = 0.3
        val cliService = StructurizrCliService.Companion.getInstance()
        if (cliService.isCliAvailable()) {
            exportWithCli(dslFile, outputPath, ExportFormat.PLANTUML_C4, viewKey, indicator)
        } else {
            val dslContent = DiagramRendererService.currentText(dslFile)
            val plantUml = convertToPlantUml(dslContent, viewKey)
            indicator.fraction = 0.8
            Files.writeString(outputPath, plantUml)
        }
    }

    private fun exportToMermaid(dslFile: VirtualFile, outputPath: Path, viewKey: String?, indicator: ProgressIndicator) {
        indicator.text = "Generating Mermaid..."
        indicator.fraction = 0.3
        val cliService = StructurizrCliService.Companion.getInstance()
        if (cliService.isCliAvailable()) {
            exportWithCli(dslFile, outputPath, ExportFormat.MERMAID, viewKey, indicator)
        } else {
            val dslContent = DiagramRendererService.currentText(dslFile)
            val mermaid = convertToMermaid(dslContent)
            indicator.fraction = 0.8
            Files.writeString(outputPath, mermaid)
        }
    }

    private fun exportToDot(dslFile: VirtualFile, outputPath: Path, viewKey: String?, indicator: ProgressIndicator) {
        indicator.text = "Generating DOT..."
        indicator.fraction = 0.3
        val cliService = StructurizrCliService.Companion.getInstance()
        if (!cliService.isCliAvailable()) throw Exception("Structurizr CLI is required for DOT export")
        exportWithCli(dslFile, outputPath, ExportFormat.DOT, viewKey, indicator)
    }

    private fun exportToJson(dslFile: VirtualFile, outputPath: Path, indicator: ProgressIndicator) {
        indicator.text = "Generating JSON..."
        indicator.fraction = 0.3
        val cliService = StructurizrCliService.Companion.getInstance()
        if (!cliService.isCliAvailable()) throw Exception("Structurizr CLI is required for JSON export")
        exportWithCli(dslFile, outputPath, ExportFormat.JSON, null, indicator)
    }

    private fun exportWithCli(dslFile: VirtualFile, outputPath: Path, format: ExportFormat, viewKey: String?, indicator: ProgressIndicator) {
        indicator.fraction = 0.5
        val cliService = StructurizrCliService.Companion.getInstance()
        val tempDir = Files.createTempDirectory("structurizr-export")
        try {
            var exportComplete = false
            var exportError: String? = null

            cliService.exportToFormat(project, dslFile.path, tempDir.toString(), format) { success, message ->
                if (success) exportComplete = true else exportError = message
            }

            var waited = 0
            while (!exportComplete && exportError == null && waited < 60000) {
                Thread.sleep(100)
                waited += 100
            }

            exportError?.let { throw Exception(it) }

            indicator.fraction = 0.8
            val exportedFile = tempDir.toFile().listFiles()
                ?.firstOrNull { it.extension == format.extension }
                ?: throw Exception("Export file not found")

            Files.copy(exportedFile.toPath(), outputPath, StandardCopyOption.REPLACE_EXISTING)
        } finally {
            Files.walk(tempDir).sorted(Comparator.reverseOrder()).forEach(Files::delete)
        }
    }

    private fun exportSingleView(
        dslFile: VirtualFile, outputPath: Path, format: DiagramExportFormat,
        viewKey: String?, indicator: ProgressIndicator
    ) {
        when (format) {
            DiagramExportFormat.SVG -> exportToSvg(dslFile, outputPath, viewKey, indicator)
            DiagramExportFormat.PNG -> exportToPng(dslFile, outputPath, viewKey, indicator)
            DiagramExportFormat.PLANTUML -> exportToPlantUml(dslFile, outputPath, viewKey, indicator)
            DiagramExportFormat.MERMAID -> exportToMermaid(dslFile, outputPath, viewKey, indicator)
            DiagramExportFormat.DOT -> exportToDot(dslFile, outputPath, viewKey, indicator)
            DiagramExportFormat.JSON -> exportToJson(dslFile, outputPath, indicator)
        }
    }

    private fun convertSvgToPng(svgContent: String, outputPath: Path) {
        val width = 800
        val height = 600
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).apply {
            val g2d = createGraphics()
            g2d.color = Color.WHITE
            g2d.fillRect(0, 0, width, height)
            g2d.color = Color.BLACK
            g2d.font = Font("SansSerif", Font.PLAIN, 14)
            g2d.drawString("Diagram exported from Structurizr DSL", 20, 30)
            g2d.drawString("For full quality PNG export, install Structurizr CLI", 20, 50)
            g2d.dispose()
        }
        ImageIO.write(image, "PNG", outputPath.toFile())
    }

    private fun convertToPlantUml(dslContent: String, viewKey: String?): String = buildString {
        appendLine("@startuml")
        appendLine("!include <C4/C4_Context>")
        appendLine()

        val personRegex = Regex("(?:(\\w+)\\s*=\\s*)?person\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?")
        val systemRegex = Regex("(?:(\\w+)\\s*=\\s*)?softwareSystem\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?")
        val relationRegex = Regex("(\\w+)\\s*->\\s*(\\w+)(?:\\s+\"([^\"]+)\")?")

        personRegex.findAll(dslContent).forEach { match ->
            val id = match.groupValues[1].ifEmpty { "person_${match.range.first}" }
            val name = match.groupValues[2]
            val description = match.groupValues[3]
            appendLine("Person($id, \"$name\"${if (description.isNotEmpty()) ", \"$description\"" else ""})")
        }

        systemRegex.findAll(dslContent).forEach { match ->
            val id = match.groupValues[1].ifEmpty { "system_${match.range.first}" }
            val name = match.groupValues[2]
            val description = match.groupValues[3]
            appendLine("System($id, \"$name\"${if (description.isNotEmpty()) ", \"$description\"" else ""})")
        }

        appendLine()

        relationRegex.findAll(dslContent).forEach { match ->
            val from = match.groupValues[1]
            val to = match.groupValues[2]
            val label = match.groupValues[3].ifEmpty { "uses" }
            appendLine("Rel($from, $to, \"$label\")")
        }

        appendLine()
        appendLine("@enduml")
    }

    private fun convertToMermaid(dslContent: String): String = buildString {
        appendLine("flowchart TB")
        appendLine()

        val personRegex = Regex("(?:(\\w+)\\s*=\\s*)?person\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?")
        val systemRegex = Regex("(?:(\\w+)\\s*=\\s*)?softwareSystem\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?")
        val relationRegex = Regex("(\\w+)\\s*->\\s*(\\w+)(?:\\s+\"([^\"]+)\")?")

        val persons = mutableListOf<String>()
        val systems = mutableListOf<String>()

        personRegex.findAll(dslContent).forEach { match ->
            val id = match.groupValues[1].ifEmpty { "person_${match.range.first}" }
            val name = match.groupValues[2]
            persons.add(id)
            appendLine("    $id[\"👤 $name\"]")
        }

        systemRegex.findAll(dslContent).forEach { match ->
            val id = match.groupValues[1].ifEmpty { "system_${match.range.first}" }
            val name = match.groupValues[2]
            systems.add(id)
            appendLine("    $id[\"🖥️ $name\"]")
        }

        appendLine()

        relationRegex.findAll(dslContent).forEach { match ->
            val from = match.groupValues[1]
            val to = match.groupValues[2]
            val label = match.groupValues[3].ifEmpty { "uses" }
            appendLine("    $from -->|$label| $to")
        }

        appendLine()
        appendLine("    %% Styling")
        persons.forEach { id -> appendLine("    style $id fill:#08427B,color:#fff") }
        systems.forEach { id -> appendLine("    style $id fill:#1168BD,color:#fff") }
    }

    private fun extractViews(dslContent: String): List<ViewInfo> {
        val views = mutableListOf<ViewInfo>()

        Regex("systemLandscape\\s+\"?(\\w+)\"?\\s*\\{").findAll(dslContent).forEach { match ->
            val key = match.groupValues[1]
            views.add(ViewInfo(key, "SystemLandscape-$key", ViewType.SYSTEM_LANDSCAPE))
        }
        Regex("systemContext\\s+(\\w+)\\s+\"?(\\w+)\"?\\s*\\{").findAll(dslContent).forEach { match ->
            val key = match.groupValues[2]
            views.add(ViewInfo(key, "SystemContext-$key", ViewType.SYSTEM_CONTEXT))
        }
        Regex("container\\s+(\\w+)\\s+\"?(\\w+)\"?\\s*\\{").findAll(dslContent).forEach { match ->
            val key = match.groupValues[2]
            views.add(ViewInfo(key, "Container-$key", ViewType.CONTAINER))
        }
        Regex("component\\s+(\\w+)\\s+\"?(\\w+)\"?\\s*\\{").findAll(dslContent).forEach { match ->
            val key = match.groupValues[2]
            views.add(ViewInfo(key, "Component-$key", ViewType.COMPONENT))
        }
        Regex("deployment\\s+\\*\\s+\"?(\\w+)\"?\\s+\"?(\\w+)\"?\\s*\\{").findAll(dslContent).forEach { match ->
            val key = match.groupValues[2]
            views.add(ViewInfo(key, "Deployment-$key", ViewType.DEPLOYMENT))
        }

        return views
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
