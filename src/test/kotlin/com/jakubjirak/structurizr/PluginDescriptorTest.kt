/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

@DisplayName("plugin.xml descriptor")
class PluginDescriptorTest {

    private fun parse(resource: String): Element {
        val stream = javaClass.classLoader.getResourceAsStream(resource)
        assertNotNull(stream, "$resource not found on classpath")
        val factory = DocumentBuilderFactory.newInstance().apply {
            setFeature("http://apache.org/xml/features/disallow-doctype-decl", true)
            isXIncludeAware = false
            isExpandEntityReferences = false
        }
        return stream!!.use { factory.newDocumentBuilder().parse(it).documentElement }
    }

    private fun dependsElements(root: Element): List<Element> =
        root.getElementsByTagName("depends").let { nodes ->
            (0 until nodes.length).map { nodes.item(it) as Element }
        }

    @Nested
    @DisplayName("JCEF dependency")
    inner class JcefDependency {

        /**
         * Regression guard: IntelliJ 2026.2 moved JCEF out of the core platform into the bundled
         * `com.intellij.modules.jcef` plugin. Without this dependency the plugin classloader cannot
         * resolve `JBCefApp`, and opening a `.dsl` file fails with `NoClassDefFoundError`.
         */
        @Test
        fun `declares the JCEF module`() {
            val jcef = dependsElements(parse("META-INF/plugin.xml"))
                .singleOrNull { it.textContent.trim() == "com.intellij.modules.jcef" }
            assertNotNull(jcef, "plugin.xml must depend on com.intellij.modules.jcef")
        }

        /**
         * The id does not exist before 2026.2, and the plugin's since-build is 243. A mandatory
         * dependency would therefore make the plugin unloadable on every supported IDE below 2026.2.
         */
        @Test
        fun `declares the JCEF module as optional`() {
            val jcef = dependsElements(parse("META-INF/plugin.xml"))
                .single { it.textContent.trim() == "com.intellij.modules.jcef" }
            assertEquals("true", jcef.getAttribute("optional"), "JCEF dependency must stay optional")
        }

        @Test
        fun `the optional JCEF config file exists`() {
            val jcef = dependsElements(parse("META-INF/plugin.xml"))
                .single { it.textContent.trim() == "com.intellij.modules.jcef" }
            val configFile = jcef.getAttribute("config-file")
            assertTrue(configFile.isNotBlank(), "optional dependency needs a config-file attribute")

            val stream = javaClass.classLoader.getResourceAsStream("META-INF/$configFile")
            assertNotNull(stream, "META-INF/$configFile referenced by plugin.xml is missing")
            stream?.close()
        }

        @Test
        fun `the optional JCEF config file is a valid descriptor`() {
            assertEquals("idea-plugin", parse("META-INF/structurizr-jcef.xml").tagName)
        }
    }

    @Nested
    @DisplayName("Platform dependency")
    inner class PlatformDependency {

        @Test
        fun `still depends on the platform module`() {
            val platform = dependsElements(parse("META-INF/plugin.xml"))
                .singleOrNull { it.textContent.trim() == "com.intellij.modules.platform" }
            assertNotNull(platform, "plugin.xml must depend on com.intellij.modules.platform")
            assertEquals("", platform!!.getAttribute("optional"), "platform dependency is mandatory")
        }
    }
}
