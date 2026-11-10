/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.language

import com.intellij.lang.Commenter

/**
 * Provides comment style definitions for the Structurizr DSL language.
 *
 * Supports both single-line (`//`) and block comment styles,
 * enabling the IDE's Toggle Comment actions for `.dsl` files.
 */
class StructurizrCommenter : Commenter {

    /** Returns the single-line comment prefix (`//`). */
    override fun getLineCommentPrefix(): String = "//"

    /** Returns the block comment opening prefix. */
    override fun getBlockCommentPrefix(): String = "/*"

    /** Returns the block comment closing suffix. */
    override fun getBlockCommentSuffix(): String = "*/"

    /** Returns the prefix for nested block comments, or `null` if unsupported. */
    override fun getCommentedBlockCommentPrefix(): String? = null

    /** Returns the suffix for nested block comments, or `null` if unsupported. */
    override fun getCommentedBlockCommentSuffix(): String? = null
}
