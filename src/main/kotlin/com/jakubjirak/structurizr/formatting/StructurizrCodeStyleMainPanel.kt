/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.formatting

import com.intellij.application.options.TabbedLanguageCodeStylePanel
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.jakubjirak.structurizr.language.StructurizrLanguage

/**
 * Main code-style settings panel for the Structurizr DSL language.
 *
 * Provides a tabbed UI for configuring formatting options.
 */
class StructurizrCodeStyleMainPanel(
    currentSettings: CodeStyleSettings,
    settings: CodeStyleSettings
) : TabbedLanguageCodeStylePanel(StructurizrLanguage, currentSettings, settings)
