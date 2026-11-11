/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.export

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.progress.ProcessCanceledException
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.jakubjirak.structurizr.preview.DiagramRendererService
import com.jakubjirak.structurizr.preview.ExportFormat
import com.jakubjirak.structurizr.preview.StructurizrCliService
import com.jakubjirak.structurizr.preview.dsl.StructurizrPreviewEngine
import com.jakubjirak.structurizr.settings.StructurizrProjectSettings
import com.jakubjirak.structurizr.settings.StructurizrSettings
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import javax.imageio.ImageIO

/** Raised when a diagram cannot be exported; the message is shown to the user. */
class DiagramExportException(message: String, cause: Throwable? = null) : Exception(message, cause)

/**
 * Project-level service for exporting Structurizr diagrams to various formats.
 *
 * SVG and PNG are produced by the plugin's own renderer (the same one the preview uses). PlantUML and Mermaid
 * use the Structurizr CLI when it is installed and the built-in converter otherwise; DOT and JSON need the CLI.
 */
@Service(Service.Level.PROJECT)
class StructurizrExportService(private val project: Project) {

    private val log = Logger.getInstance(StructurizrExportService::class.java)

    private val settings: StructurizrSettings
        get() = StructurizrSettings.getInstance()

    companion object {
        /** Returns the service instance for the given [project]. */
        fun getInstance(project: Project): StructurizrExportService =
            project.service()

        /** Turns a view key into a safe file name. */
        fun fileNameFor(viewKey: String): String =
            viewKey.replace(Regex("[^A-Za-z0-9._-]+"), "_").trim('_').ifEmpty { "view" }

        /** Scale that makes a [width]×[height] diagram fit into [maxWidth]×[maxHeight] (between 0.25× and 4×). */
        fun fitScale(width: Int, height: Int, maxWidth: Int, maxHeight: Int): Double {
            if (minOf(width, height, maxWidth, maxHeight) <= 0) return 1.0
            return minOf(maxWidth.toDouble() / width, maxHeight.toDouble() / height).coerceIn(MIN_SCALE, MAX_SCALE)
        }

        private const val MIN_SCALE = 0.25
        private const val MAX_SCALE = 4.0
    }

    /** Returns the default export format from settings. */
    fun getDefaultFormat(): DiagramExportFormat =
        DiagramExportFormat.entries.firstOrNull { it.name.equals(settings.defaultExportFormat, ignoreCase = true) }
            ?: DiagramExportFormat.SVG

    /** Returns the default export directory (project setting, then global setting); empty when none is set. */
    fun getDefaultExportDirectory(): String = StructurizrProjectSettings.getInstance(project).getEffectiveExportDirectory()

    /**
     * Exports a diagram asynchronously.
     *
     * @param viewKey the view to export; `null` exports the workspace's default view
     * @param callback invoked with (success, message)
     */
    fun export(
        dslFile: VirtualFile,
        outputPath: Path,
        format: DiagramExportFormat,
        viewKey: String? = null,
        callback: (Boolean, String) -> Unit,
    ) {
        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Exporting Diagram to ${format.displayName}", true) {
            override fun run(indicator: ProgressIndicator) {
                runExport(indicator, callback) {
                    indicator.isIndeterminate = false
                    exportSingleView(dslFile, outputPath, format, viewKey, indicator)
                    LocalFileSystem.getInstance().refreshAndFindFileByPath(outputPath.toString())
                    "Diagram exported to ${outputPath.fileName}"
                }
            }
        })
    }

    /** Exports every view the workspace defines to [outputDir], one file per view named after the view key. */
    fun exportAllViews(
        dslFile: VirtualFile,
        outputDir: Path,
        format: DiagramExportFormat,
        callback: (Boolean, String) -> Unit,
    ) {
        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Exporting All Views", true) {
            override fun run(indicator: ProgressIndicator) {
                runExport(indicator, callback) {
                    indicator.isIndeterminate = false
                    indicator.text = "Analyzing DSL file..."
                    val keys = viewKeys(dslFile)
                    Files.createDirectories(outputDir)
                    keys.forEachIndexed { index, key ->
                        indicator.checkCanceled()
                        indicator.fraction = index.toDouble() / keys.size
                        indicator.text = "Exporting $key..."
                        exportSingleView(dslFile, outputDir.resolve("${fileNameFor(key)}.${format.extension}"), format, key, indicator)
                    }
                    LocalFileSystem.getInstance().refreshAndFindFileByPath(outputDir.toString())
                    "Exported ${keys.size} diagram(s) to $outputDir"
                }
            }
        })
    }

    /** Keys of the views defined in [dslFile] (including generated default views). */
    fun viewKeys(dslFile: VirtualFile): List<String> {
        val ws = DiagramRendererService.workspace(dslFile)
        return StructurizrPreviewEngine.viewOptions(ws).map { it.key }
    }

    private fun runExport(indicator: ProgressIndicator, callback: (Boolean, String) -> Unit, action: () -> String) {
        try {
            val message = action()
            indicator.fraction = 1.0
            callback(true, message)
            showNotification("Export Complete", message, NotificationType.INFORMATION)
        } catch (e: ProcessCanceledException) {
            throw e
        } catch (e: DiagramExportException) {
            fail(callback, e.message ?: "Export failed")
        } catch (e: IOException) {
            log.warn("Export failed", e)
            fail(callback, "Export failed: ${e.message}")
        }
    }

    private fun fail(callback: (Boolean, String) -> Unit, message: String) {
        callback(false, message)
        showNotification("Export Failed", message, NotificationType.ERROR)
    }

    private fun exportSingleView(
        dslFile: VirtualFile,
        outputPath: Path,
        format: DiagramExportFormat,
        viewKey: String?,
        indicator: ProgressIndicator,
    ) {
        indicator.fraction = 0.3
        when (format) {
            DiagramExportFormat.SVG -> Files.writeString(outputPath, renderSvg(dslFile, viewKey))
            DiagramExportFormat.PNG -> writePng(renderSvg(dslFile, viewKey), outputPath)
            DiagramExportFormat.PLANTUML, DiagramExportFormat.MERMAID -> textOrCli(dslFile, outputPath, format, viewKey, indicator)
            DiagramExportFormat.DOT -> requireCli("DOT").also { exportWithCli(dslFile, outputPath, ExportFormat.DOT, viewKey, indicator) }
            DiagramExportFormat.JSON -> requireCli("JSON").also { exportWithCli(dslFile, outputPath, ExportFormat.JSON, null, indicator) }
        }
    }

    private fun renderSvg(dslFile: VirtualFile, viewKey: String?): String =
        DiagramRendererService.getInstance(project).renderToSvg(DiagramRendererService.currentText(dslFile), viewKey, dslFile)
            ?: throw DiagramExportException("The diagram could not be rendered")

    private fun writePng(svg: String, outputPath: Path) {
        val (width, height) = SvgRasterizer.size(svg)
        val scale = fitScale(width, height, settings.exportImageWidth, settings.exportImageHeight)
        if (!ImageIO.write(SvgRasterizer.rasterize(svg, scale), "png", outputPath.toFile())) {
            throw DiagramExportException("No PNG writer available")
        }
    }

    /** PlantUML/Mermaid: the Structurizr CLI when installed, otherwise the built-in converter. */
    private fun textOrCli(
        dslFile: VirtualFile,
        outputPath: Path,
        format: DiagramExportFormat,
        viewKey: String?,
        indicator: ProgressIndicator,
    ) {
        val cliFormat = if (format == DiagramExportFormat.MERMAID) ExportFormat.MERMAID else ExportFormat.PLANTUML_C4
        if (StructurizrCliService.getInstance().isCliAvailable(project)) {
            exportWithCli(dslFile, outputPath, cliFormat, viewKey, indicator)
        } else {
            val diagram = StructurizrPreviewEngine.diagram(DiagramRendererService.workspace(dslFile), viewKey).second
            val text = if (format == DiagramExportFormat.MERMAID) {
                DiagramTextExporter.toMermaid(diagram)
            } else {
                DiagramTextExporter.toPlantUml(diagram)
            }
            Files.writeString(outputPath, text)
        }
    }

    private fun requireCli(formatName: String) {
        if (!StructurizrCliService.getInstance().isCliAvailable(project)) {
            throw DiagramExportException("$formatName export requires the Structurizr CLI (Settings | Tools | Structurizr DSL)")
        }
    }

    /**
     * Runs the CLI into a temporary directory and copies the file for [viewKey] (the CLI writes one file per view,
     * named `structurizr-<key>.<ext>`), or the only/first produced file when no view is requested.
     */
    private fun exportWithCli(
        dslFile: VirtualFile,
        outputPath: Path,
        format: ExportFormat,
        viewKey: String?,
        indicator: ProgressIndicator,
    ) {
        indicator.fraction = 0.5
        val tempDir = Files.createTempDirectory("structurizr-export")
        try {
            val result = StructurizrCliService.getInstance().exportBlocking(project, dslFile.path, tempDir.toString(), format, indicator)
            if (!result.first) throw DiagramExportException(result.second ?: "Structurizr CLI export failed")

            indicator.fraction = 0.8
            val produced = tempDir.toFile().listFiles()?.filter { it.extension == format.extension }.orEmpty().sortedBy { it.name }
            val chosen = viewKey?.let { key -> produced.firstOrNull { it.nameWithoutExtension.endsWith("-$key") } }
                ?: produced.firstOrNull()
            chosen ?: throw DiagramExportException("The Structurizr CLI produced no .${format.extension} file")
            Files.copy(chosen.toPath(), outputPath, StandardCopyOption.REPLACE_EXISTING)
        } finally {
            tempDir.toFile().deleteRecursively()
        }
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
