/*
 * Copyright (c) 2026 Jakub Jirák. All rights reserved.
 * Licensed under the terms of the JetBrains Marketplace EULA.
 */
package com.jakubjirak.structurizr.licensing

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.ui.LicensingFacade

@Service(Service.Level.APP)
class FreemiumGate {

    enum class LicenseStatus { FREE, TRIAL, PAID }

    companion object {
        const val PRODUCT_CODE = "PSTRUCTDSL"

        const val FEATURE_LIVE_PREVIEW = "live_preview"
        const val FEATURE_SPLIT_EDITOR = "split_editor"
        const val FEATURE_EXPORT_SVG = "export_svg"
        const val FEATURE_EXPORT_PNG = "export_png"
        const val FEATURE_EXPORT_PLANTUML = "export_plantuml"
        const val FEATURE_EXPORT_MERMAID = "export_mermaid"
        const val FEATURE_CLI_INTEGRATION = "cli_integration"
        const val FEATURE_BATCH_EXPORT = "batch_export"

        private val PREMIUM_FEATURES = setOf(
            FEATURE_LIVE_PREVIEW, FEATURE_SPLIT_EDITOR, FEATURE_EXPORT_SVG,
            FEATURE_EXPORT_PNG, FEATURE_EXPORT_PLANTUML, FEATURE_EXPORT_MERMAID,
            FEATURE_CLI_INTEGRATION, FEATURE_BATCH_EXPORT
        )

        fun getInstance(): FreemiumGate =
            ApplicationManager.getApplication().getService(FreemiumGate::class.java)
    }

    fun getLicenseStatus(): LicenseStatus {
        val facade = LicensingFacade.getInstance() ?: return LicenseStatus.FREE
        val stamp = facade.getConfirmationStamp(PRODUCT_CODE) ?: return LicenseStatus.FREE
        return if (stamp.startsWith("eval:")) LicenseStatus.TRIAL else LicenseStatus.PAID
    }

    fun isPaidFeatureAvailable(featureId: String): Boolean {
        if (featureId !in PREMIUM_FEATURES) return true
        return getLicenseStatus() != LicenseStatus.FREE
    }

    fun isPremiumUser(): Boolean = getLicenseStatus() != LicenseStatus.FREE
}
