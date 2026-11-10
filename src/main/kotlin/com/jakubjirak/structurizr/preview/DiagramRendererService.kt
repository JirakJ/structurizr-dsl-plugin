/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Computable
import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.openapi.vfs.VirtualFile
import com.jakubjirak.structurizr.language.StructurizrFileType
import com.jakubjirak.structurizr.preview.dsl.PreviewResult
import com.jakubjirak.structurizr.preview.dsl.StructurizrPreviewEngine

/**
 * Project-level service that renders Structurizr DSL content to SVG for the preview and the exports.
 *
 * Rendering is done by the built-in [StructurizrPreviewEngine], which honours the selected view
 * (type, scope, include/exclude) and resolves `!include` relative to the edited file.
 */
@Service(Service.Level.PROJECT)
class DiagramRendererService(@Suppress("unused") private val project: Project) {

    private val log = Logger.getInstance(DiagramRendererService::class.java)

    /**
     * Renders the given [dslContent] to SVG.
     *
     * @param dslContent the Structurizr DSL source text
     * @param viewKey    key of the view to render; `null` renders the workspace's default view
     * @param file       the file the DSL comes from, used to resolve `!include`
     * @return the SVG string, or `null` on failure
     */
    fun renderToSvg(dslContent: String, viewKey: String? = null, file: VirtualFile? = null): String? =
        render(dslContent, viewKey, file)?.svg

    /** Renders [viewKey] and also returns the views the workspace defines, for the view selector. */
    fun render(dslContent: String, viewKey: String?, file: VirtualFile?): PreviewResult? = try {
        StructurizrPreviewEngine.render(dslContent, viewKey, includeResolver(file))
    } catch (e: Exception) {
        log.warn("Failed to render Structurizr diagram", e)
        null
    }

    companion object {
        /**
         * The text to render for [file]: the editor's document when it is open (so unsaved edits are
         * exported exactly as the preview shows them), otherwise the file on disk.
         */
        fun currentText(file: VirtualFile): String =
            ApplicationManager.getApplication().runReadAction(Computable { FileDocumentManager.getInstance().getDocument(file)?.text })
                ?: String(file.contentsToByteArray(), file.charset)

        /** Retrieves the project-level singleton instance. */
        @JvmStatic
        fun getInstance(project: Project): DiagramRendererService =
            project.service()

        /**
         * Resolves `!include` targets relative to [file]'s directory. A directory includes every `.dsl`
         * file in it, as Structurizr does; remote URLs are not fetched by the preview.
         */
        fun includeResolver(file: VirtualFile?): (String) -> String? = resolver@{ path ->
            if (path.startsWith("http://") || path.startsWith("https://")) return@resolver null
            val base = file?.parent ?: return@resolver null
            val target = base.findFileByRelativePath(path) ?: return@resolver null
            if (target.isDirectory) {
                target.children
                    .filter { !it.isDirectory && it.fileType == StructurizrFileType }
                    .sortedBy { it.name }
                    .joinToString("\n") { VfsUtilCore.loadText(it) }
            } else {
                VfsUtilCore.loadText(target)
            }
        }
    }
}
