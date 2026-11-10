/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

/**
 * File type definition for Structurizr DSL (`.dsl`) files.
 *
 * Registered as a singleton so that the IDE can associate `.dsl` files with the
 * Structurizr language and provide appropriate icons and descriptions.
 */
object StructurizrFileType : LanguageFileType(StructurizrLanguage) {

    /** Returns the unique name identifying this file type. */
    override fun getName(): String = "Structurizr DSL"

    /** Returns a human-readable description of this file type. */
    override fun getDescription(): String = "Structurizr DSL file for C4 model architecture diagrams"

    /** Returns the default file extension associated with this file type. */
    override fun getDefaultExtension(): String = "dsl"

    /** Returns the icon displayed for Structurizr DSL files in the IDE. */
    override fun getIcon(): Icon = StructurizrIcons.FILE
}
