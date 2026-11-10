/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.preview

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Regression tests for the docs#13 follow-up: the first diagram was overwritten by the "Open a .dsl
 * file" placeholder that JCEF replayed after its native browser had been created, so the preview only
 * showed the diagram after Refresh. [PageLoadGate] keeps every page away from the browser until then.
 */
@DisplayName("Preview page loading before the browser exists (docs#13)")
class PageLoadGateTest {

    private val loaded = mutableListOf<String>()
    private val gate = PageLoadGate { loaded += it }

    @Test
    fun `nothing reaches the browser before it is created`() {
        gate.show("placeholder")
        gate.show("diagram")
        assertEquals(emptyList<String>(), loaded)
    }

    @Test
    fun `only the latest page is loaded once the browser is created`() {
        gate.show("placeholder")
        gate.show("diagram")
        gate.browserCreated()
        assertEquals(listOf("diagram"), loaded)
    }

    @Test
    fun `pages load directly after creation`() {
        gate.browserCreated()
        gate.show("diagram")
        gate.show("edited diagram")
        assertEquals(listOf("diagram", "edited diagram"), loaded)
    }

    @Test
    fun `creation with no page yet loads nothing and later creation events are ignored`() {
        gate.browserCreated()
        gate.browserCreated()
        assertEquals(emptyList<String>(), loaded)
        gate.show("diagram")
        gate.browserCreated()
        assertEquals(listOf("diagram"), loaded)
    }
}
