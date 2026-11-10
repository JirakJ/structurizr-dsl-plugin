/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import javax.swing.JComponent

/**
 * Configurable entry point for project-level Structurizr DSL settings.
 *
 * Registered in `plugin.xml` and displayed under the project-level
 * *Settings → Tools → Structurizr DSL*.
 */
class StructurizrProjectSettingsConfigurable(private val project: Project) : Configurable {

    private var settingsComponent: StructurizrProjectSettingsComponent? = null

    /** Display name shown in the settings tree. */
    override fun getDisplayName(): String = "Structurizr DSL"

    /** Creates and returns the project settings UI panel. */
    override fun createComponent(): JComponent {
        settingsComponent = StructurizrProjectSettingsComponent(project)
        return settingsComponent!!.panel
    }

    /** Checks whether the user has modified any project setting. */
    override fun isModified(): Boolean {
        val settings = StructurizrProjectSettings.getInstance(project)
        return settingsComponent?.isModified(settings) ?: false
    }

    /** Persists the current UI values to project-level settings. */
    override fun apply() {
        val settings = StructurizrProjectSettings.getInstance(project)
        settingsComponent?.applyTo(settings)
    }

    /** Resets the UI to match the persisted project settings. */
    override fun reset() {
        val settings = StructurizrProjectSettings.getInstance(project)
        settingsComponent?.resetFrom(settings)
    }

    /** Releases the settings component when the dialog is closed. */
    override fun disposeUIResources() {
        settingsComponent = null
    }
}
