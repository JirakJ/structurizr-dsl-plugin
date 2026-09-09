/*
 * Copyright (c) 2026 Jakub Jirák. All rights reserved.
 * Licensed under the terms of the JetBrains Marketplace EULA.
 */
package com.jakubjirak.structurizr.preview

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("JcefSupport")
class JcefSupportTest {

    @Nested
    @DisplayName("Probe outcome")
    inner class ProbeOutcome {

        @Test
        fun `reports available when the probe says JCEF is supported`() {
            assertTrue(JcefSupport.isAvailable { true })
        }

        @Test
        fun `reports unavailable when the probe says JCEF is unsupported`() {
            assertFalse(JcefSupport.isAvailable { false })
        }
    }

    @Nested
    @DisplayName("Missing JCEF classes (IDE 2026.2+ without the JCEF plugin)")
    inner class MissingClasses {

        /**
         * Regression guard for the 2026.2 defect: JCEF moved out of the core platform into the
         * separate `com.intellij.modules.jcef` plugin, so `JBCefApp` may be absent from the plugin
         * classloader. That surfaces as [NoClassDefFoundError] — an [Error], not an [Exception] —
         * which the old `catch (e: Exception)` guard did not catch, taking the whole `.dsl` editor
         * down with it.
         */
        @Test
        fun `reports unavailable when the JCEF class is missing`() {
            assertFalse(
                JcefSupport.isAvailable { throw NoClassDefFoundError("com/intellij/ui/jcef/JBCefApp") }
            )
        }

        @Test
        fun `reports unavailable when the JCEF native library fails to link`() {
            assertFalse(JcefSupport.isAvailable { throw UnsatisfiedLinkError("libjcef") })
        }

        @Test
        fun `reports unavailable when JCEF class initialization fails`() {
            assertFalse(
                JcefSupport.isAvailable { throw ExceptionInInitializerError(IllegalStateException("boom")) }
            )
        }

        @Test
        fun `reports unavailable when the probe throws a runtime exception`() {
            assertFalse(JcefSupport.isAvailable { throw IllegalStateException("no browser") })
        }
    }

    @Nested
    @DisplayName("Non-recoverable failures")
    inner class NonRecoverableFailures {

        @Test
        fun `virtual machine errors are not swallowed`() {
            assertThrows(OutOfMemoryError::class.java) {
                JcefSupport.isAvailable { throw OutOfMemoryError("heap") }
            }
        }
    }

    @Nested
    @DisplayName("Guarded execution")
    inner class GuardedExecution {

        @Test
        fun `runGuarded returns the block result`() {
            assertEquals("ok", JcefSupport.runGuarded { "ok" })
        }

        @Test
        fun `runGuarded returns null when the block hits a missing JCEF class`() {
            assertNull(JcefSupport.runGuarded { throw NoClassDefFoundError("com/intellij/ui/jcef/JBCefBrowser") })
        }

        @Test
        fun `runGuarded returns null when the block throws a runtime exception`() {
            assertNull(JcefSupport.runGuarded { throw IllegalStateException("boom") })
        }

        @Test
        fun `runGuarded does not swallow virtual machine errors`() {
            assertThrows(StackOverflowError::class.java) {
                JcefSupport.runGuarded { throw StackOverflowError() }
            }
        }
    }
}
