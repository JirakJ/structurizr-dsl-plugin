/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.settings

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

/**
 * Configurable entry point for global Structurizr DSL settings.
 *
 * Registered in `plugin.xml` and displayed under
 * *Settings → Tools → Structurizr DSL*.
 */
class StructurizrSettingsConfigurable : Configurable {

    private var settingsComponent: StructurizrSettingsComponent? = null

    /** Display name shown in the settings tree. */
    override fun getDisplayName(): String = "Structurizr DSL"

    /** Creates and returns the settings UI panel. */
    override fun createComponent(): JComponent {
        settingsComponent = StructurizrSettingsComponent()
        return settingsComponent!!.panel
    }

    /** Checks whether the user has modified any setting. */
    override fun isModified(): Boolean {
        val settings = StructurizrSettings.getInstance()
        return settingsComponent?.isModified(settings) ?: false
    }

    /** Persists the current UI values to application-level settings. */
    override fun apply() {
        val settings = StructurizrSettings.getInstance()
        settingsComponent?.applyTo(settings)
    }

    /** Resets the UI to match the persisted settings. */
    override fun reset() {
        val settings = StructurizrSettings.getInstance()
        settingsComponent?.resetFrom(settings)
    }

    /** Releases the settings component when the dialog is closed. */
    override fun disposeUIResources() {
        settingsComponent = null
    }
}
