/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
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

    /** Ensures deserialization returns the singleton (called reflectively by Java serialization). */
    @Suppress("UnusedPrivateMember")
    private fun readResolve(): Any = StructurizrLanguage

    /** Returns the human-readable display name shown in the IDE. */
    override fun getDisplayName(): String = "Structurizr DSL"

    /** Indicates that the Structurizr DSL is case-sensitive. */
    override fun isCaseSensitive(): Boolean = true
}
