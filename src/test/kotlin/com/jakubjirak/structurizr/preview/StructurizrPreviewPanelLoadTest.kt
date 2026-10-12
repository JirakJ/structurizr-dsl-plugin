/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.preview

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File

/**
 * Regression guard for the docs#13 follow-up: the first render was overwritten by JCEF replaying the
 * "Open a .dsl file" placeholder it had deferred until the native browser existed, so the preview
 * only showed the diagram after Refresh. Every page must go through `show()`, which remembers it,
 * and the panel must re-apply that page once the browser has been created.
 */
@DisplayName("Preview panel initial load (docs#13)")
class StructurizrPreviewPanelLoadTest {

    private val source = File("src/main/kotlin/com/jakubjirak/structurizr/preview/StructurizrPreviewPanel.kt").readText()

    @Test
    fun `pages are loaded only through show and the post-creation reload`() {
        val loads = Regex("""\.loadHTML\(""").findAll(source).count()
        assertEquals(2, loads, "loadHTML should be called only from show() and the onAfterCreated reload")
        assertTrue(source.contains("latestPage = html"), "show() must remember the page it loads")
    }

    @Test
    fun `latest page is re-applied after the native browser is created`() {
        assertTrue(source.contains("addLifeSpanHandler"), "the panel must listen for browser creation")
        assertTrue(source.contains("override fun onAfterCreated"), "the reload must hook onAfterCreated")
        assertTrue(
            Regex("""SwingUtilities\.invokeLater \{\s*SwingUtilities\.invokeLater""").containsMatchIn(source),
            "the reload must be re-posted so it runs after JCEF's own deferred-load replay",
        )
    }
}
