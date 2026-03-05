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
import org.jetbrains.annotations.NotNull;

public final class StructurizrElementType
extends IElementType {
    public StructurizrElementType(@NotNull String debugName) {
        super(debugName, (Language)StructurizrLanguage.INSTANCE);
    }
}
