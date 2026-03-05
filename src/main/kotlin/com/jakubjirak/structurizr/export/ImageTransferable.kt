/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.export

import java.awt.Image
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.UnsupportedFlavorException

/**
 * Transferable wrapper for copying an [Image] to the system clipboard.
 */
class ImageTransferable(private val image: Image) : Transferable {

    /** Returns supported data flavors. */
    override fun getTransferDataFlavors(): Array<DataFlavor> = arrayOf(DataFlavor.imageFlavor)

    /** Checks whether the given [flavor] is supported. */
    override fun isDataFlavorSupported(flavor: DataFlavor): Boolean = flavor == DataFlavor.imageFlavor

    /** Returns the image data for the given [flavor]. */
    override fun getTransferData(flavor: DataFlavor): Any {
        if (flavor == DataFlavor.imageFlavor) return image
        throw UnsupportedFlavorException(flavor)
    }
}
