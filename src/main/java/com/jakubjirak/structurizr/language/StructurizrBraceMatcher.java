/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.BracePair
 *  com.intellij.lang.PairedBraceMatcher
 *  com.intellij.psi.PsiFile
 *  com.intellij.psi.tree.IElementType
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016\u00a2\u0006\u0002\u0010\u0007J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u001a\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0016\u00a8\u0006\u0013"}, d2={"Lcom/jakubjirak/structurizr/language/StructurizrBraceMatcher;", "Lcom/intellij/lang/PairedBraceMatcher;", "<init>", "()V", "getPairs", "", "Lcom/intellij/lang/BracePair;", "()[Lcom/intellij/lang/BracePair;", "isPairedBracesAllowedBeforeType", "", "lbraceType", "Lcom/intellij/psi/tree/IElementType;", "contextType", "getCodeConstructStart", "", "file", "Lcom/intellij/psi/PsiFile;", "openingBraceOffset", "Companion", "structurizr-dsl-plugin"})
public final class StructurizrBraceMatcher
implements PairedBraceMatcher {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final BracePair[] PAIRS;

    @NotNull
    public BracePair[] getPairs() {
        return PAIRS;
    }

    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        Intrinsics.checkNotNullParameter((Object)lbraceType, (String)"lbraceType");
        return true;
    }

    public int getCodeConstructStart(@Nullable PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }

    static {
        BracePair[] bracePairArray = new BracePair[]{new BracePair(StructurizrTypes.LBRACE, StructurizrTypes.RBRACE, true)};
        PAIRS = bracePairArray;
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007\u00a8\u0006\b"}, d2={"Lcom/jakubjirak/structurizr/language/StructurizrBraceMatcher$Companion;", "", "<init>", "()V", "PAIRS", "", "Lcom/intellij/lang/BracePair;", "[Lcom/intellij/lang/BracePair;", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
