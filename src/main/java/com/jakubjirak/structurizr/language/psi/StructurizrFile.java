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
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016\u00a8\u0006\n"}, d2={"Lcom/jakubjirak/structurizr/language/psi/StructurizrFile;", "Lcom/intellij/extapi/psi/PsiFileBase;", "viewProvider", "Lcom/intellij/psi/FileViewProvider;", "<init>", "(Lcom/intellij/psi/FileViewProvider;)V", "getFileType", "Lcom/intellij/openapi/fileTypes/FileType;", "toString", "", "structurizr-dsl-plugin"})
public final class StructurizrFile
extends PsiFileBase {
    public StructurizrFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, (Language)StructurizrLanguage.INSTANCE);
        Intrinsics.checkNotNullParameter((Object)viewProvider, (String)"viewProvider");
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
