/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * Application-level persistent settings for the Structurizr DSL plugin.
 *
 * Stores the CLI location, preview behaviour and export defaults.
 */
@State(name = "StructurizrSettings", storages = [Storage("structurizr.xml")])
class StructurizrSettings : PersistentStateComponent<StructurizrSettings> {

    // --- CLI ---
    /** Path to the Structurizr CLI (`.jar` or launcher script); empty = auto-detect. */
    @JvmField var structurizrCliPath: String = ""
    @JvmField var cliTimeoutSeconds: Int = 30

    // --- Preview ---
    /** Show the diagram preview next to the editor for `.dsl` files. */
    @JvmField var enableLivePreview: Boolean = true
    /** Re-render the preview while typing. */
    @JvmField var autoRefreshPreview: Boolean = true
    @JvmField var previewDelayMs: Int = 500

    // --- Export ---
    @JvmField var defaultExportFormat: String = FORMAT_PNG
    @JvmField var defaultExportDirectory: String = ""
    /** PNG exports are scaled to fit into this box. */
    @JvmField var exportImageWidth: Int = 1920
    @JvmField var exportImageHeight: Int = 1080
    @JvmField var autoOpenExportedFile: Boolean = true

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
            service()
    }
}
