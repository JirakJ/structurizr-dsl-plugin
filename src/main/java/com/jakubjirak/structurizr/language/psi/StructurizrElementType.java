/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.Language
 *  com.intellij.psi.tree.IElementType
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.StructurizrLanguage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, d2={"Lcom/jakubjirak/structurizr/language/psi/StructurizrElementType;", "Lcom/intellij/psi/tree/IElementType;", "debugName", "", "<init>", "(Ljava/lang/String;)V", "structurizr-dsl-plugin"})
public final class StructurizrElementType
extends IElementType {
    public StructurizrElementType(@NotNull String debugName) {
        super(debugName, (Language)StructurizrLanguage.INSTANCE);
        Intrinsics.checkNotNullParameter((Object)debugName, (String)"debugName");
    }
}
