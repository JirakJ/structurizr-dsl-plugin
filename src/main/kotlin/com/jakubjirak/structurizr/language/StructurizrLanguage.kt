/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.language

import com.intellij.lang.Language

/**
 * Defines the Structurizr DSL language for the IntelliJ Platform.
 *
 * This singleton language instance is registered with the IDE and serves as the root
 * identifier for all Structurizr DSL language support features (parsing, highlighting, etc.).
 */
object StructurizrLanguage : Language("Structurizr") {

    /** Ensures deserialization returns the singleton [INSTANCE]. */
    private fun readResolve(): Any = StructurizrLanguage

    /** Returns the human-readable display name shown in the IDE. */
    override fun getDisplayName(): String = "Structurizr DSL"

    /** Indicates that the Structurizr DSL is case-sensitive. */
    override fun isCaseSensitive(): Boolean = true
}
