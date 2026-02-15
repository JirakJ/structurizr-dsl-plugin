/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.export;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2={"Lcom/jakubjirak/structurizr/export/ImageTransferable;", "Ljava/awt/datatransfer/Transferable;", "image", "Ljava/awt/Image;", "<init>", "(Ljava/awt/Image;)V", "getTransferDataFlavors", "", "Ljava/awt/datatransfer/DataFlavor;", "()[Ljava/awt/datatransfer/DataFlavor;", "isDataFlavorSupported", "", "flavor", "getTransferData", "", "structurizr-dsl-plugin"})
public final class ImageTransferable
implements Transferable {
    @NotNull
    private final Image image;

    public ImageTransferable(@NotNull Image image) {
        Intrinsics.checkNotNullParameter((Object)image, (String)"image");
        this.image = image;
    }

    @Override
    @NotNull
    public DataFlavor[] getTransferDataFlavors() {
        DataFlavor[] dataFlavorArray = new DataFlavor[]{DataFlavor.imageFlavor};
        return dataFlavorArray;
    }

    @Override
    public boolean isDataFlavorSupported(@NotNull DataFlavor flavor) {
        Intrinsics.checkNotNullParameter((Object)flavor, (String)"flavor");
        return Intrinsics.areEqual((Object)flavor, (Object)DataFlavor.imageFlavor);
    }

    @Override
    @NotNull
    public Object getTransferData(@NotNull DataFlavor flavor) throws UnsupportedFlavorException {
        Intrinsics.checkNotNullParameter((Object)flavor, (String)"flavor");
        if (Intrinsics.areEqual((Object)flavor, (Object)DataFlavor.imageFlavor)) {
            return this.image;
        }
        throw new UnsupportedFlavorException(flavor);
    }
}
