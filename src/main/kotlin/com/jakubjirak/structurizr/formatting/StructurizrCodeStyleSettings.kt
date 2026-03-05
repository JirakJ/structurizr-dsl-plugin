/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.formatting

import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CustomCodeStyleSettings

/**
 * Custom code-style settings for the Structurizr DSL language.
 *
 * Holds formatting preferences such as blank-line counts, spacing flags,
 * and wrapping/brace options.
 */
class StructurizrCodeStyleSettings(container: CodeStyleSettings) :
    CustomCodeStyleSettings("StructurizrCodeStyleSettings", container) {

    @JvmField var BLANK_LINES_AROUND_BLOCK: Int = 1
    @JvmField var BLANK_LINES_AFTER_MODEL_ELEMENTS: Int = 1
    @JvmField var BLANK_LINES_BEFORE_VIEWS: Int = 1
    @JvmField var SPACE_BEFORE_LBRACE: Boolean = true
    @JvmField var SPACE_AFTER_ARROW: Boolean = true
    @JvmField var SPACE_BEFORE_ARROW: Boolean = true
    @JvmField var WRAP_LONG_LINES: Boolean = true
    @JvmField var ALIGN_MULTILINE_PARAMETERS: Boolean = true
    @JvmField var KEEP_LINE_BREAKS: Boolean = true
    @JvmField var LBRACE_ON_NEXT_LINE: Boolean = false
    @JvmField var RBRACE_ON_OWN_LINE: Boolean = true
    @JvmField var ALIGN_PROPERTY_VALUES: Boolean = false
    @JvmField var PROPERTY_VALUE_ON_SAME_LINE: Boolean = true
}
