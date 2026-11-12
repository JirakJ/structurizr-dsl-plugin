/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.screenshots

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.jakubjirak.structurizr.settings.StructurizrSettings
import com.jakubjirak.structurizr.settings.StructurizrSettingsComponent
import java.awt.Color
import java.awt.Component
import java.awt.Container
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JComponent

/**
 * Renders the real settings panel to `docs/screenshots/04-settings.png` so the
 * Marketplace image always shows the settings that exist. Skipped unless opted in:
 *
 *   ./gradlew test --tests "*SettingsScreenshotTest*" -PscreenshotsDir=$(pwd)/docs/screenshots
 */
class SettingsScreenshotTest : BasePlatformTestCase() {

    fun testRenderSettingsPanel() {
        val dir = System.getProperty("structurizr.screenshots.dir")?.takeIf { it.isNotBlank() } ?: return
        File(dir).mkdirs()

        val component = StructurizrSettingsComponent()
        component.resetFrom(StructurizrSettings())
        val target = File(dir, "04-settings.png")
        render(component.panel, WIDTH, HEIGHT, target)
        assertTrue("Screenshot looks empty", target.length() > MIN_BYTES)
    }

    private fun render(component: JComponent, width: Int, height: Int, target: File) {
        component.setBounds(0, 0, width, height)
        layoutRecursively(component)
        val image = BufferedImage(width * SCALE, height * SCALE, BufferedImage.TYPE_INT_RGB)
        val g = image.createGraphics()
        g.scale(SCALE.toDouble(), SCALE.toDouble())
        g.color = component.background ?: Color.WHITE
        g.fillRect(0, 0, width, height)
        component.print(g)
        g.dispose()
        ImageIO.write(image, "png", target)
    }

    private fun layoutRecursively(component: Component) {
        component.doLayout()
        if (component is Container) {
            component.components.forEach { layoutRecursively(it) }
        }
    }

    private companion object {
        const val WIDTH = 980
        const val HEIGHT = 640
        const val SCALE = 2
        const val MIN_BYTES = 5_000L
    }
}
