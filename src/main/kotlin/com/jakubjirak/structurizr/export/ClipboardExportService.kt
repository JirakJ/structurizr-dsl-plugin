/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.export

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.jakubjirak.structurizr.preview.DiagramRendererService
import com.jakubjirak.structurizr.preview.dsl.StructurizrPreviewEngine
import com.jakubjirak.structurizr.preview.dsl.ViewDiagram
import java.awt.datatransfer.StringSelection
import java.awt.datatransfer.Transferable

/**
 * Copies a Structurizr view (the workspace's default view unless [viewKey] is given)
 * to the system clipboard as SVG, PlantUML, Mermaid or a raster image.
 */
class ClipboardExportService(private val project: Project) {

    companion object {
        /** Creates a new instance for the given [project]. */
        fun getInstance(project: Project): ClipboardExportService = ClipboardExportService(project)

        /** Copied images are rendered at twice the diagram's size so they stay sharp when pasted. */
        private const val IMAGE_SCALE = 2.0
    }

    /** Copies the diagram as SVG to the clipboard. */
    fun copyAsSvg(dslFile: VirtualFile, viewKey: String? = null): Boolean =
        copy("SVG") { renderSvg(dslFile, viewKey)?.let(::SvgTransferable) }

    /** Copies the diagram as C4-PlantUML to the clipboard. */
    fun copyAsPlantUml(dslFile: VirtualFile, viewKey: String? = null): Boolean =
        copy("PlantUML") { StringSelection(DiagramTextExporter.toPlantUml(diagram(dslFile, viewKey))) }

    /** Copies the diagram as Mermaid to the clipboard. */
    fun copyAsMermaid(dslFile: VirtualFile, viewKey: String? = null): Boolean =
        copy("Mermaid") { StringSelection(DiagramTextExporter.toMermaid(diagram(dslFile, viewKey))) }

    /** Copies the diagram as a raster image to the clipboard. */
    fun copyAsImage(dslFile: VirtualFile, viewKey: String? = null): Boolean =
        copy("image") { renderSvg(dslFile, viewKey)?.let { ImageTransferable(SvgRasterizer.rasterize(it, IMAGE_SCALE)) } }

    private fun copy(what: String, content: () -> Transferable?): Boolean {
        val transferable = content()
        if (transferable == null) {
            showNotification("Copy Failed", "The diagram could not be rendered", NotificationType.ERROR)
            return false
        }
        CopyPasteManager.getInstance().setContents(transferable)
        showNotification("Copied to Clipboard", "Diagram copied as $what", NotificationType.INFORMATION)
        return true
    }

    private fun renderSvg(dslFile: VirtualFile, viewKey: String?): String? =
        DiagramRendererService.getInstance(project).renderToSvg(DiagramRendererService.currentText(dslFile), viewKey, dslFile)

    private fun diagram(dslFile: VirtualFile, viewKey: String?): ViewDiagram {
        val ws = DiagramRendererService.workspace(dslFile)
        return StructurizrPreviewEngine.diagram(ws, viewKey).second
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
