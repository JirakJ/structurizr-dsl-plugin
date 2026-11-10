/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.formatting

import com.intellij.lang.Language
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizableOptions
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider
import com.jakubjirak.structurizr.language.StructurizrLanguage

/**
 * Language-level code-style settings provider for Structurizr DSL.
 *
 * Registers indent, spacing, blank-line, and wrapping options with the IDE.
 */
class StructurizrLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {

    /** Returns the Structurizr DSL language instance. */
    override fun getLanguage(): Language = StructurizrLanguage

    /** Populates the code-style UI with options appropriate for the given [settingsType]. */
    override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {
        val options = CodeStyleSettingsCustomizableOptions.getInstance()
        when (settingsType) {
            SettingsType.INDENT_SETTINGS -> {
                consumer.showStandardOptions(
                    "INDENT_SIZE", "TAB_SIZE", "USE_TAB_CHARACTER",
                    "SMART_TABS", "KEEP_INDENTS_ON_EMPTY_LINES"
                )
            }
            SettingsType.SPACING_SETTINGS -> {
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "SPACE_BEFORE_LBRACE",
                    "Space before '{'", options.SPACES_BEFORE_LEFT_BRACE
                )
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "SPACE_BEFORE_ARROW",
                    "Space before '->'", options.SPACES_AROUND_OPERATORS
                )
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "SPACE_AFTER_ARROW",
                    "Space after '->'", options.SPACES_AROUND_OPERATORS
                )
            }
            SettingsType.BLANK_LINES_SETTINGS -> {
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "BLANK_LINES_AROUND_BLOCK",
                    "Around block statements", options.BLANK_LINES
                )
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "BLANK_LINES_AFTER_MODEL_ELEMENTS",
                    "After model elements (person, softwareSystem, etc.)", options.BLANK_LINES
                )
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "BLANK_LINES_BEFORE_VIEWS",
                    "Before views block", options.BLANK_LINES
                )
            }
            SettingsType.WRAPPING_AND_BRACES_SETTINGS -> {
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "WRAP_LONG_LINES",
                    "Wrap long lines", options.WRAPPING_KEEP
                )
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "KEEP_LINE_BREAKS",
                    "Keep line breaks", options.WRAPPING_KEEP
                )
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "LBRACE_ON_NEXT_LINE",
                    "Opening brace on next line", options.WRAPPING_BRACES
                )
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "RBRACE_ON_OWN_LINE",
                    "Closing brace on own line", options.WRAPPING_BRACES
                )
                consumer.showCustomOption(
                    StructurizrCodeStyleSettings::class.java, "ALIGN_MULTILINE_PARAMETERS",
                    "Align multiline parameters", options.WRAPPING_METHOD_PARAMETERS
                )
            }
            else -> {}
        }
    }

    /** Returns a representative code sample for the given [settingsType]. */
    override fun getCodeSample(settingsType: SettingsType): String =
        """
        |workspace "Example" "An example workspace" {
        |    
        |    model {
        |        user = person "User" "A user of the system"
        |        
        |        system = softwareSystem "System" "The software system" {
        |            webapp = container "Web Application" "The web frontend" "React"
        |            api = container "API" "The backend API" "Java/Spring"
        |            db = container "Database" "Data storage" "PostgreSQL"
        |            
        |            webapp -> api "Calls" "JSON/HTTPS"
        |            api -> db "Reads/Writes" "JDBC"
        |        }
        |        
        |        user -> system "Uses"
        |    }
        |    
        |    views {
        |        systemContext system "SystemContext" {
        |            include *
        |            autoLayout
        |        }
        |        
        |        container system "Containers" {
        |            include *
        |            autoLayout lr
        |        }
        |        
        |        styles {
        |            element "Person" {
        |                shape Person
        |                background #08427B
        |                color #ffffff
        |            }
        |            element "Software System" {
        |                background #1168BD
        |                color #ffffff
        |            }
        |            element "Container" {
        |                background #438DD5
        |                color #ffffff
        |            }
        |            relationship "Relationship" {
        |                thickness 2
        |                color #707070
        |                style dashed
        |            }
        |        }
        |        
        |        themes default
        |    }
        |}
        """.trimMargin()
}
