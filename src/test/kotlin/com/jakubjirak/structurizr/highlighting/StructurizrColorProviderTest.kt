package com.jakubjirak.structurizr.highlighting

import com.intellij.openapi.editor.ElementColorProvider
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.awt.Color

@DisplayName("StructurizrColorProvider")
class StructurizrColorProviderTest {

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `can be instantiated`() {
            assertNotNull(StructurizrColorProvider())
        }

        @Test
        fun `implements ElementColorProvider`() {
            assertInstanceOf(ElementColorProvider::class.java, StructurizrColorProvider())
        }

        @Test
        fun `class is final`() {
            assertTrue(java.lang.reflect.Modifier.isFinal(StructurizrColorProvider::class.java.modifiers))
        }
    }

    @Nested
    @DisplayName("Color property names")
    inner class ColorPropertyNames {

        @Test
        fun `contains background`() {
            assertTrue("background" in StructurizrColorProvider.COLOR_PROPERTY_NAMES)
        }

        @Test
        fun `contains color`() {
            assertTrue("color" in StructurizrColorProvider.COLOR_PROPERTY_NAMES)
        }

        @Test
        fun `contains British colour`() {
            assertTrue("colour" in StructurizrColorProvider.COLOR_PROPERTY_NAMES)
        }

        @Test
        fun `contains stroke`() {
            assertTrue("stroke" in StructurizrColorProvider.COLOR_PROPERTY_NAMES)
        }

        @Test
        fun `has exactly 4 entries`() {
            assertEquals(4, StructurizrColorProvider.COLOR_PROPERTY_NAMES.size)
        }

        @Test
        fun `does not contain non-color properties`() {
            assertFalse("shape" in StructurizrColorProvider.COLOR_PROPERTY_NAMES)
            assertFalse("fontSize" in StructurizrColorProvider.COLOR_PROPERTY_NAMES)
        }
    }

    @Nested
    @DisplayName("parseColor")
    inner class ParseColor {

        @Test
        fun `parses 6-digit hex`() {
            assertEquals(Color(0x11, 0x68, 0xBD), StructurizrColorProvider.parseColor("#1168BD"))
        }

        @Test
        fun `parses 6-digit lowercase hex`() {
            assertEquals(Color(0xFF, 0x00, 0x00), StructurizrColorProvider.parseColor("#ff0000"))
        }

        @Test
        fun `parses 3-digit shorthand by expanding digits`() {
            assertEquals(Color(0xFF, 0x00, 0x00), StructurizrColorProvider.parseColor("#f00"))
            assertEquals(Color(0xAA, 0xBB, 0xCC), StructurizrColorProvider.parseColor("#abc"))
        }

        @Test
        fun `parses 8-digit hex with alpha`() {
            val color = StructurizrColorProvider.parseColor("#1168BD80")
            assertNotNull(color)
            assertEquals(0x11, color!!.red)
            assertEquals(0x68, color.green)
            assertEquals(0xBD, color.blue)
            assertEquals(0x80, color.alpha)
        }

        @Test
        fun `parses quoted hex`() {
            assertEquals(Color(0x11, 0x68, 0xBD), StructurizrColorProvider.parseColor("\"#1168BD\""))
        }

        @Test
        fun `parses hex with surrounding whitespace`() {
            assertEquals(Color(0x11, 0x68, 0xBD), StructurizrColorProvider.parseColor("  #1168BD  "))
        }

        @Test
        fun `rejects null`() {
            assertNull(StructurizrColorProvider.parseColor(null))
        }

        @Test
        fun `rejects empty string`() {
            assertNull(StructurizrColorProvider.parseColor(""))
        }

        @Test
        fun `rejects text without hash`() {
            assertNull(StructurizrColorProvider.parseColor("1168BD"))
        }

        @Test
        fun `rejects named colors`() {
            assertNull(StructurizrColorProvider.parseColor("red"))
        }

        @Test
        fun `rejects invalid hex digits`() {
            assertNull(StructurizrColorProvider.parseColor("#GGGGGG"))
        }

        @Test
        fun `rejects wrong lengths`() {
            assertNull(StructurizrColorProvider.parseColor("#12"))
            assertNull(StructurizrColorProvider.parseColor("#1234"))
            assertNull(StructurizrColorProvider.parseColor("#12345"))
            assertNull(StructurizrColorProvider.parseColor("#1234567"))
            assertNull(StructurizrColorProvider.parseColor("#123456789"))
        }

        @Test
        fun `parses black and white`() {
            assertEquals(Color.BLACK, StructurizrColorProvider.parseColor("#000000"))
            assertEquals(Color.WHITE, StructurizrColorProvider.parseColor("#ffffff"))
        }
    }

    @Nested
    @DisplayName("toHex")
    inner class ToHex {

        @Test
        fun `formats color without alpha`() {
            assertEquals("#1168bd", StructurizrColorProvider.toHex(Color(0x11, 0x68, 0xBD)))
        }

        @Test
        fun `formats color with alpha when requested`() {
            assertEquals("#1168bd80", StructurizrColorProvider.toHex(Color(0x11, 0x68, 0xBD, 0x80), includeAlpha = true))
        }

        @Test
        fun `pads single-digit components with zero`() {
            assertEquals("#010203", StructurizrColorProvider.toHex(Color(1, 2, 3)))
        }

        @Test
        fun `formats black and white`() {
            assertEquals("#000000", StructurizrColorProvider.toHex(Color.BLACK))
            assertEquals("#ffffff", StructurizrColorProvider.toHex(Color.WHITE))
        }

        @Test
        fun `round-trips through parseColor`() {
            val original = Color(0xAB, 0xCD, 0xEF)
            assertEquals(original, StructurizrColorProvider.parseColor(StructurizrColorProvider.toHex(original)))
        }

        @Test
        fun `round-trips with alpha through parseColor`() {
            val original = Color(0xAB, 0xCD, 0xEF, 0x42)
            assertEquals(
                original,
                StructurizrColorProvider.parseColor(StructurizrColorProvider.toHex(original, includeAlpha = true))
            )
        }
    }
}
