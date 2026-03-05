/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.formatting

import com.intellij.application.options.CodeStyleAbstractConfigurable
import com.intellij.application.options.CodeStyleAbstractPanel
import com.intellij.psi.codeStyle.CodeStyleConfigurable
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider
import com.intellij.psi.codeStyle.CustomCodeStyleSettings

/**
 * Provides custom code-style settings for the Structurizr DSL language.
 *
 * Integrates with the IDE's code-style configuration UI.
 */
class StructurizrCodeStyleSettingsProvider : CodeStyleSettingsProvider() {

    /** Creates the custom settings instance for the given [settings] container. */
    override fun createCustomSettings(settings: CodeStyleSettings): CustomCodeStyleSettings =
        StructurizrCodeStyleSettings(settings)

    /** Returns the display name shown in the code-style configuration dialog. */
    override fun getConfigurableDisplayName(): String = "Structurizr DSL"

    /** Creates the configurable panel for editing code-style settings. */
    override fun createConfigurable(
        settings: CodeStyleSettings,
        modelSettings: CodeStyleSettings
    ): CodeStyleConfigurable {
        val displayName = configurableDisplayName
        return object : CodeStyleAbstractConfigurable(settings, modelSettings, displayName) {
            override fun createPanel(settings: CodeStyleSettings): CodeStyleAbstractPanel =
                StructurizrCodeStyleMainPanel(currentSettings, settings)
        }
    }
}
