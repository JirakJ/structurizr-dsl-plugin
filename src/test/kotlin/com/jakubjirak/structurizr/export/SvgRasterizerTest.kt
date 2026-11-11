package com.jakubjirak.structurizr.export

import com.jakubjirak.structurizr.preview.dsl.StructurizrPreviewEngine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.awt.Color
import java.awt.image.BufferedImage

class SvgRasterizerTest {

    private fun rgb(image: BufferedImage, x: Int, y: Int): Int = image.getRGB(x, y) and 0xFFFFFF

    @Test
    fun `size comes from the svg attributes`() {
        assertEquals(300 to 200, SvgRasterizer.size("""<svg xmlns="http://www.w3.org/2000/svg" width="300" height="200"/>"""))
        assertEquals(800 to 600, SvgRasterizer.size("""<svg xmlns="http://www.w3.org/2000/svg"/>"""))
    }

    @Test
    fun `scale changes the pixel size`() {
        val svg = """<svg xmlns="http://www.w3.org/2000/svg" width="100" height="50"/>"""
        val image = SvgRasterizer.rasterize(svg, 2.0)
        assertEquals(200, image.width)
        assertEquals(100, image.height)
    }

    @Test
    fun `paints rect fill, percentage sizes and circles`() {
        val svg = """
            <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100">
              <rect width="100%" height="100%" fill="#ffffff"/>
              <rect x="10" y="10" width="30" height="30" fill="#ff0000"/>
              <circle cx="70" cy="70" r="10" fill="#0000ff"/>
            </svg>
        """.trimIndent()
        val image = SvgRasterizer.rasterize(svg)
        assertEquals(0xFFFFFF, rgb(image, 2, 2))
        assertEquals(0xFF0000, rgb(image, 25, 25))
        assertEquals(0x0000FF, rgb(image, 70, 70))
    }

    @Test
    fun `fill none leaves the background and strokes are drawn`() {
        val svg = """
            <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60">
              <rect width="100%" height="100%" fill="#ffffff"/>
              <rect x="10" y="10" width="40" height="40" fill="none" stroke="#00ff00" stroke-width="4"/>
              <line x1="0" y1="55" x2="60" y2="55" stroke="#000000" stroke-width="3" marker-end="url(#arrow)"/>
            </svg>
        """.trimIndent()
        val image = SvgRasterizer.rasterize(svg)
        assertEquals(0xFFFFFF, rgb(image, 30, 30))
        assertEquals(0x00FF00, rgb(image, 10, 30))
        assertEquals(0x000000, rgb(image, 30, 55))
    }

    @Test
    fun `text and quadratic paths are rendered`() {
        val svg = """
            <svg xmlns="http://www.w3.org/2000/svg" width="200" height="80" font-family="Arial, Helvetica, sans-serif">
              <rect width="100%" height="100%" fill="#ffffff"/>
              <text x="100" y="40" font-size="30" font-weight="bold" text-anchor="middle" fill="#000000">WWWW</text>
              <path d="M 10 70 Q 100 50 190 70" fill="none" stroke="#ff0000" stroke-width="2" marker-end="url(#arrow)"/>
            </svg>
        """.trimIndent()
        val image = SvgRasterizer.rasterize(svg)
        val dark = (0 until 200).flatMap { x -> (10 until 45).map { y -> rgb(image, x, y) } }.count { it == 0 }
        assertTrue(dark > 20, "expected text pixels, got $dark")
        val red = (0 until 200).flatMap { x -> (50 until 80).map { y -> rgb(image, x, y) } }
            .count { (it shr 16) > 0xC0 && (it and 0xFF) < 0x40 }
        assertTrue(red > 20, "expected curve pixels, got $red")
    }

    @Test
    fun `rejects non-svg documents and doctype`() {
        assertThrows<IllegalArgumentException> { SvgRasterizer.rasterize("<html/>") }
        assertThrows<Exception> {
            SvgRasterizer.rasterize("""<?xml version="1.0"?><!DOCTYPE svg [<!ENTITY x SYSTEM "file:///etc/passwd">]><svg>&x;</svg>""")
        }
        assertThrows<IllegalArgumentException> { SvgRasterizer.rasterize("<svg/>", 0.0) }
    }

    @Test
    fun `colors parse hex, names, opacity and none`() {
        assertEquals(Color(0x11, 0x22, 0x33), SvgRasterizer.color("#112233", null))
        assertEquals(Color(0xAA, 0xBB, 0xCC), SvgRasterizer.color("#abc", null))
        assertEquals(Color(255, 255, 255, 127), SvgRasterizer.color("white", 0.5))
        assertNull(SvgRasterizer.color("none", null))
        assertNull(SvgRasterizer.color("", null))
    }

    @Test
    fun `renders the plugin's own diagram svg`() {
        val dsl = """
            workspace {
                model {
                    u = person "User" "A user"
                    s = softwareSystem "System" "Does things"
                    u -> s "Uses"
                }
                views {
                    systemContext s "ctx" {
                        include *
                    }
                }
            }
        """.trimIndent()
        val svg = StructurizrPreviewEngine.render(dsl, "ctx").svg
        val (w, h) = SvgRasterizer.size(svg)
        val image = SvgRasterizer.rasterize(svg)
        assertEquals(w, image.width)
        assertEquals(h, image.height)
        val distinct = (0 until image.width step 4).flatMap { x -> (0 until image.height step 4).map { y -> rgb(image, x, y) } }.toSet()
        assertTrue(distinct.size > 3, "expected a drawn diagram, got ${distinct.size} colours")
        assertNotEquals(0xFFFFFF, distinct.first { it != 0xFFFFFF })
    }
}
