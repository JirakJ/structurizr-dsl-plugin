/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.extapi.psi.PsiFileBase
 *  com.intellij.lang.Language
 *  com.intellij.openapi.fileTypes.FileType
 *  com.intellij.psi.FileViewProvider
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.jakubjirak.structurizr.language.StructurizrFileType;
import com.jakubjirak.structurizr.language.StructurizrLanguage;
import org.jetbrains.annotations.NotNull;

public final class StructurizrFile
extends PsiFileBase {
    public StructurizrFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, (Language)StructurizrLanguage.INSTANCE);
    }

    @NotNull
    public FileType getFileType() {
        return (FileType)StructurizrFileType.INSTANCE;
    }

    @NotNull
    public String toString() {
        return "Structurizr DSL File";
    }
}
