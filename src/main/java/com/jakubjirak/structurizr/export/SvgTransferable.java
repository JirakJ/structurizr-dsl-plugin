/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.export;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2={"Lcom/jakubjirak/structurizr/export/SvgTransferable;", "Ljava/awt/datatransfer/Transferable;", "svgContent", "", "<init>", "(Ljava/lang/String;)V", "getTransferDataFlavors", "", "Ljava/awt/datatransfer/DataFlavor;", "()[Ljava/awt/datatransfer/DataFlavor;", "isDataFlavorSupported", "", "flavor", "getTransferData", "", "Companion", "structurizr-dsl-plugin"})
public final class SvgTransferable
implements Transferable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String svgContent;
    @NotNull
    private static final DataFlavor SVG_FLAVOR = new DataFlavor("image/svg+xml;class=java.lang.String", "SVG Image");

    public SvgTransferable(@NotNull String svgContent) {
        Intrinsics.checkNotNullParameter((Object)svgContent, (String)"svgContent");
        this.svgContent = svgContent;
    }

    @Override
    @NotNull
    public DataFlavor[] getTransferDataFlavors() {
        DataFlavor[] dataFlavorArray = new DataFlavor[]{SVG_FLAVOR, DataFlavor.stringFlavor};
        return dataFlavorArray;
    }

    @Override
    public boolean isDataFlavorSupported(@NotNull DataFlavor flavor) {
        Intrinsics.checkNotNullParameter((Object)flavor, (String)"flavor");
        return Intrinsics.areEqual((Object)flavor, (Object)SVG_FLAVOR) || Intrinsics.areEqual((Object)flavor, (Object)DataFlavor.stringFlavor);
    }

    @Override
    @NotNull
    public Object getTransferData(@NotNull DataFlavor flavor) throws UnsupportedFlavorException {
        String string;
        Intrinsics.checkNotNullParameter((Object)flavor, (String)"flavor");
        if (Intrinsics.areEqual((Object)flavor, (Object)SVG_FLAVOR)) {
            string = this.svgContent;
        } else if (Intrinsics.areEqual((Object)flavor, (Object)DataFlavor.stringFlavor)) {
            string = this.svgContent;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
        return string;
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lcom/jakubjirak/structurizr/export/SvgTransferable$Companion;", "", "<init>", "()V", "SVG_FLAVOR", "Ljava/awt/datatransfer/DataFlavor;", "getSVG_FLAVOR", "()Ljava/awt/datatransfer/DataFlavor;", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final DataFlavor getSVG_FLAVOR() {
            return SVG_FLAVOR;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
