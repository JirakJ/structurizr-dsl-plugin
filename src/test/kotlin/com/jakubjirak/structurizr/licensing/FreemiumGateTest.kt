/*
 * Copyright (c) 2026 Jakub Jirák. All rights reserved.
 * Licensed under the terms of the JetBrains Marketplace EULA.
 */
package com.jakubjirak.structurizr.licensing

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("FreemiumGate")
class FreemiumGateTest {

    private val gate = FreemiumGate()

    @Nested
    @DisplayName("Product code")
    inner class ProductCodeTests {

        @Test
        fun `product code is PSTRUCTDSL`() {
            assertEquals("PSTRUCTDSL", FreemiumGate.PRODUCT_CODE)
        }
    }

    @Nested
    @DisplayName("Feature constants")
    inner class FeatureConstantTests {

        @Test
        fun `live preview constant is correct`() {
            assertEquals("live_preview", FreemiumGate.FEATURE_LIVE_PREVIEW)
        }

        @Test
        fun `split editor constant is correct`() {
            assertEquals("split_editor", FreemiumGate.FEATURE_SPLIT_EDITOR)
        }

        @Test
        fun `export SVG constant is correct`() {
            assertEquals("export_svg", FreemiumGate.FEATURE_EXPORT_SVG)
        }

        @Test
        fun `export PNG constant is correct`() {
            assertEquals("export_png", FreemiumGate.FEATURE_EXPORT_PNG)
        }

        @Test
        fun `export PlantUML constant is correct`() {
            assertEquals("export_plantuml", FreemiumGate.FEATURE_EXPORT_PLANTUML)
        }

        @Test
        fun `export Mermaid constant is correct`() {
            assertEquals("export_mermaid", FreemiumGate.FEATURE_EXPORT_MERMAID)
        }

        @Test
        fun `CLI integration constant is correct`() {
            assertEquals("cli_integration", FreemiumGate.FEATURE_CLI_INTEGRATION)
        }

        @Test
        fun `batch export constant is correct`() {
            assertEquals("batch_export", FreemiumGate.FEATURE_BATCH_EXPORT)
        }
    }

    @Nested
    @DisplayName("LicenseStatus enum")
    inner class LicenseStatusTests {

        @Test
        fun `has FREE status`() {
            assertNotNull(FreemiumGate.LicenseStatus.valueOf("FREE"))
        }

        @Test
        fun `has TRIAL status`() {
            assertNotNull(FreemiumGate.LicenseStatus.valueOf("TRIAL"))
        }

        @Test
        fun `has PAID status`() {
            assertNotNull(FreemiumGate.LicenseStatus.valueOf("PAID"))
        }

        @Test
        fun `has exactly three statuses`() {
            assertEquals(3, FreemiumGate.LicenseStatus.entries.size)
        }
    }

    @Nested
    @DisplayName("License status without Application context")
    inner class LicenseStatusWithoutContextTests {

        @Test
        fun `returns FREE when no LicensingFacade is available`() {
            assertEquals(FreemiumGate.LicenseStatus.FREE, gate.getLicenseStatus())
        }

        @Test
        fun `isPremiumUser returns false when FREE`() {
            assertFalse(gate.isPremiumUser())
        }
    }

    @Nested
    @DisplayName("isPaidFeatureAvailable")
    inner class IsPaidFeatureAvailableTests {

        @Test
        fun `non-premium feature is always available`() {
            assertTrue(gate.isPaidFeatureAvailable("some_unknown_feature"))
        }

        @Test
        fun `live preview is not available for free users`() {
            assertFalse(gate.isPaidFeatureAvailable(FreemiumGate.FEATURE_LIVE_PREVIEW))
        }

        @Test
        fun `split editor is not available for free users`() {
            assertFalse(gate.isPaidFeatureAvailable(FreemiumGate.FEATURE_SPLIT_EDITOR))
        }

        @Test
        fun `export SVG is not available for free users`() {
            assertFalse(gate.isPaidFeatureAvailable(FreemiumGate.FEATURE_EXPORT_SVG))
        }

        @Test
        fun `export PNG is not available for free users`() {
            assertFalse(gate.isPaidFeatureAvailable(FreemiumGate.FEATURE_EXPORT_PNG))
        }

        @Test
        fun `export PlantUML is not available for free users`() {
            assertFalse(gate.isPaidFeatureAvailable(FreemiumGate.FEATURE_EXPORT_PLANTUML))
        }

        @Test
        fun `export Mermaid is not available for free users`() {
            assertFalse(gate.isPaidFeatureAvailable(FreemiumGate.FEATURE_EXPORT_MERMAID))
        }

        @Test
        fun `CLI integration is not available for free users`() {
            assertFalse(gate.isPaidFeatureAvailable(FreemiumGate.FEATURE_CLI_INTEGRATION))
        }

        @Test
        fun `batch export is not available for free users`() {
            assertFalse(gate.isPaidFeatureAvailable(FreemiumGate.FEATURE_BATCH_EXPORT))
        }
    }

    @Nested
    @DisplayName("Can be instantiated")
    inner class InstantiationTests {

        @Test
        fun `can be instantiated`() {
            assertNotNull(gate)
        }

        @Test
        fun `is an instance of FreemiumGate`() {
            assertInstanceOf(FreemiumGate::class.java, gate)
        }
    }
}
