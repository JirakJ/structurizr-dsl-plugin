/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.export

import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.UnsupportedFlavorException

/**
 * Transferable wrapper for copying SVG content to the system clipboard.
 *
 * Supports both the SVG MIME type and plain string flavors.
 */
class SvgTransferable(private val svgContent: String) : Transferable {

    companion object {
        /** Custom data flavor for SVG image content. */
        @JvmField
        val SVG_FLAVOR = DataFlavor("image/svg+xml;class=java.lang.String", "SVG Image")
    }

    /** Returns the supported data flavors. */
    override fun getTransferDataFlavors(): Array<DataFlavor> = arrayOf(SVG_FLAVOR, DataFlavor.stringFlavor)

    /** Checks whether the given [flavor] is supported. */
    override fun isDataFlavorSupported(flavor: DataFlavor): Boolean =
        flavor == SVG_FLAVOR || flavor == DataFlavor.stringFlavor

    /** Returns the SVG content for the given [flavor]. */
    override fun getTransferData(flavor: DataFlavor): Any = when (flavor) {
        SVG_FLAVOR -> svgContent
        DataFlavor.stringFlavor -> svgContent
        else -> throw UnsupportedFlavorException(flavor)
    }
}
