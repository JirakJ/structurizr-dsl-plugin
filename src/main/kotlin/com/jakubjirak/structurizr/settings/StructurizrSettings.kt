/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * Application-level persistent settings for the Structurizr DSL plugin.
 *
 * Stores CLI paths, preview preferences, export configuration,
 * editor behaviour flags, and code-completion options.
 */
@State(name = "StructurizrSettings", storages = [Storage("structurizr.xml")])
class StructurizrSettings : PersistentStateComponent<StructurizrSettings> {

    // --- CLI ---
    @JvmField var structurizrCliPath: String = ""
    @JvmField var useEmbeddedCli: Boolean = true
    @JvmField var cliTimeoutSeconds: Int = 30

    // --- Preview ---
    @JvmField var enableLivePreview: Boolean = true
    @JvmField var autoRefreshPreview: Boolean = true
    @JvmField var previewDelayMs: Int = 500
    @JvmField var showPreviewInSplitEditor: Boolean = true
    @JvmField var previewZoomLevel: Int = 100
    @JvmField var previewDarkMode: Boolean = false
    @JvmField var previewUseSystemTheme: Boolean = true

    // --- Export ---
    @JvmField var defaultExportFormat: String = FORMAT_PNG
    @JvmField var defaultExportDirectory: String = ""
    @JvmField var exportImageWidth: Int = 1920
    @JvmField var exportImageHeight: Int = 1080
    @JvmField var exportIncludeMetadata: Boolean = true
    @JvmField var autoOpenExportedFile: Boolean = true

    // --- Editor ---
    @JvmField var showBreadcrumbs: Boolean = true
    @JvmField var enableCodeFolding: Boolean = true
    @JvmField var showLineMarkers: Boolean = true
    @JvmField var enableInspections: Boolean = true

    // --- Code Completion ---
    @JvmField var autoPopupCompletion: Boolean = true
    @JvmField var includeReferencesInCompletion: Boolean = true
    @JvmField var showDocumentationInCompletion: Boolean = true

    /** Returns this instance as the persisted state. */
    override fun getState(): StructurizrSettings = this

    /** Loads persisted state by copying bean properties. */
    override fun loadState(state: StructurizrSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        const val FORMAT_PNG = "PNG"
        const val FORMAT_SVG = "SVG"
        const val FORMAT_PLANTUML = "PlantUML"
        const val FORMAT_MERMAID = "Mermaid"
        const val FORMAT_DOT = "DOT"
        const val FORMAT_JSON = "JSON"

        val EXPORT_FORMATS: List<String> = listOf(
            FORMAT_PNG, FORMAT_SVG, FORMAT_PLANTUML, FORMAT_MERMAID, FORMAT_DOT, FORMAT_JSON
        )

        /** Returns the application-level settings service instance. */
        fun getInstance(): StructurizrSettings =
            ApplicationManager.getApplication().getService(StructurizrSettings::class.java)
    }
}
