/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.language

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import com.jakubjirak.structurizr.language.psi.StructurizrTypes

/**
 * Handles automatic quote insertion and matching for the Structurizr DSL language.
 *
 * Delegates to [SimpleTokenSetQuoteHandler] with the [StructurizrTypes.STRING] token,
 * so the IDE automatically inserts a closing quote when the user types an opening one.
 */
class StructurizrQuoteHandler : SimpleTokenSetQuoteHandler(StructurizrTypes.STRING)
