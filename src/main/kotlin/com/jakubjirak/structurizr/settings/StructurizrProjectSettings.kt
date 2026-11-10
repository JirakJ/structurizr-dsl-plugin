/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * Project-level persistent settings for the Structurizr DSL plugin.
 *
 * Allows per-project overrides of CLI path, workspace file,
 * export directory, and preview delay.
 */
@State(name = "StructurizrProjectSettings", storages = [Storage("\$WORKSPACE_FILE\$")])
class StructurizrProjectSettings : PersistentStateComponent<StructurizrProjectSettings> {

    @JvmField var useProjectCliPath: Boolean = false
    @JvmField var projectCliPath: String = ""
    @JvmField var defaultWorkspaceFile: String = ""
    @JvmField var projectExportDirectory: String = ""
    @JvmField var overridePreviewSettings: Boolean = false
    @JvmField var projectPreviewDelayMs: Int = 500
    @JvmField var recentWorkspaceFiles: MutableList<String> = mutableListOf()
    @JvmField var maxRecentFiles: Int = 10
    @JvmField var lastSelectedView: String = ""
    @JvmField var favoriteViews: MutableList<String> = mutableListOf()

    /** Returns this instance as the persisted state. */
    override fun getState(): StructurizrProjectSettings = this

    /** Loads persisted state by copying bean properties. */
    override fun loadState(state: StructurizrProjectSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    /** Adds a workspace file path to the recent list, trimming to [maxRecentFiles]. */
    fun addRecentWorkspaceFile(filePath: String) {
        recentWorkspaceFiles.remove(filePath)
        recentWorkspaceFiles.add(0, filePath)
        if (recentWorkspaceFiles.size > maxRecentFiles) {
            recentWorkspaceFiles = recentWorkspaceFiles.take(maxRecentFiles).toMutableList()
        }
    }

    /** Adds a view key to favorites if not already present. */
    fun addFavoriteView(viewKey: String) {
        if (viewKey !in favoriteViews) {
            favoriteViews.add(viewKey)
        }
    }

    /** Removes a view key from favorites. */
    fun removeFavoriteView(viewKey: String) {
        favoriteViews.remove(viewKey)
    }

    /** Returns the project CLI path if configured, otherwise the global CLI path. */
    fun getEffectiveCliPath(): String =
        if (useProjectCliPath && projectCliPath.isNotEmpty()) projectCliPath
        else StructurizrSettings.getInstance().structurizrCliPath

    /** Returns the project preview delay if overridden, otherwise the global delay. */
    fun getEffectivePreviewDelay(): Int =
        if (overridePreviewSettings) projectPreviewDelayMs
        else StructurizrSettings.getInstance().previewDelayMs

    /** Returns the project export directory if set, otherwise the global directory. */
    fun getEffectiveExportDirectory(): String =
        projectExportDirectory.ifEmpty { StructurizrSettings.getInstance().defaultExportDirectory }

    companion object {
        /** Returns the project-level settings service instance. */
        fun getInstance(project: Project): StructurizrProjectSettings =
            project.service()
    }
}
