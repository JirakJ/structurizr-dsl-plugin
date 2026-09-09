/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.diagnostic.Logger
import com.intellij.ui.jcef.JBCefApp

/**
 * Single place that decides whether the embedded JCEF browser can be used, and the only place that
 * touches JCEF classes without a net.
 *
 * JCEF is not guaranteed to be present at runtime:
 *
 * * the IDE may run on a JDK without the JetBrains Runtime browser bits;
 * * the user may have disabled the embedded browser;
 * * since IntelliJ 2026.2 (build 262) JCEF is no longer part of the core platform but lives in the
 *   separate bundled plugin `com.intellij.modules.jcef`, so a plugin that does not depend on it
 *   cannot resolve [JBCefApp] at all.
 *
 * The last case surfaces as [NoClassDefFoundError], which is an [Error] rather than an [Exception].
 * Guards that catch only [Exception] let it escape and take the whole editor down with them, so
 * everything here catches [LinkageError] as well. [VirtualMachineError] and control-flow throwables
 * are deliberately left alone.
 */
object JcefSupport {

    private val log = Logger.getInstance(JcefSupport::class.java)

    /**
     * Message shown in place of the diagram when no embedded browser can be created. Kept in one
     * place so the split editor and the tool window say the same thing.
     */
    const val UNAVAILABLE_MESSAGE: String =
        "<html><center><h2>Structurizr Preview</h2>" +
            "<p>The diagram preview needs the IDE's embedded browser (JCEF), which is not available here.</p>" +
            "<p>Editing, inspections and navigation are unaffected.</p></center></html>"

    /**
     * Returns `true` when an embedded browser can be created.
     *
     * @param probe the availability check, overridable in tests
     */
    fun isAvailable(probe: () -> Boolean = { JBCefApp.isSupported() }): Boolean =
        try {
            probe()
        } catch (e: LinkageError) {
            log.warn("JCEF is unavailable in this IDE; diagram preview will be disabled", e)
            false
        } catch (e: Exception) {
            log.warn("JCEF availability check failed; diagram preview will be disabled", e)
            false
        }

    /**
     * Runs [block] and returns `null` instead of propagating a missing or broken JCEF runtime.
     *
     * @param block the JCEF-touching code to execute
     */
    fun <T : Any> runGuarded(block: () -> T): T? =
        try {
            block()
        } catch (e: LinkageError) {
            log.warn("JCEF is unavailable in this IDE; diagram preview will be disabled", e)
            null
        } catch (e: Exception) {
            log.warn("Failed to initialize the JCEF browser; diagram preview will be disabled", e)
            null
        }
}
