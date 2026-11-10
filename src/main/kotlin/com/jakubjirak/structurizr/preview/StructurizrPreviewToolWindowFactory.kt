/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

/**
 * Factory that creates the Structurizr preview tool window content.
 */
class StructurizrPreviewToolWindowFactory : ToolWindowFactory, DumbAware {

    /** Creates and registers the preview panel inside the given [toolWindow]. */
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val previewContent = StructurizrPreviewContent(project)
        // Empty display name: a single, unnamed content shows no tab, so the header reads just
        // "Structurizr Preview" instead of offering a "Preview" tab that looks like a button.
        val content = ContentFactory.getInstance().createContent(previewContent.component, "", false)
        content.setDisposer(previewContent)
        toolWindow.contentManager.addContent(content)
    }

    /** Always available regardless of project type. */
    override fun shouldBeAvailable(project: Project): Boolean = true
}
