/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview

/**
 * Holds pages back until the embedded browser has been created, then loads only the latest one.
 *
 * JCEF does not queue loads issued before its native browser exists: it keeps one deferred page and
 * replays it from `onAfterCreated` through `invokeLater`. A render that finished in that window was
 * loaded directly and then overwritten by the replayed "Open a .dsl file" placeholder, so the preview
 * needed Refresh (docs#13). Nothing is handed to the browser before [browserCreated], so there is no
 * deferred page to replay.
 *
 * Not thread-safe: every call must come from the EDT.
 *
 * @param load loads a page into the browser
 */
class PageLoadGate(private val load: (String) -> Unit) {

    private var created = false
    private var latest: String? = null

    /** Shows [html] now if the browser exists, otherwise once it has been created. */
    fun show(html: String) {
        latest = html
        if (created) load(html)
    }

    /** Opens the gate and loads the latest page, if any. Later calls do nothing. */
    fun browserCreated() {
        if (created) return
        created = true
        latest?.let(load)
    }
}
