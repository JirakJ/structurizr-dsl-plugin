/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.Language
 *  com.intellij.openapi.fileTypes.LanguageFileType
 *  kotlin.Metadata
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.jakubjirak.structurizr.language.StructurizrIcons;
import com.jakubjirak.structurizr.language.StructurizrLanguage;
import javax.swing.Icon;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\tH\u0016\u00a8\u0006\n"}, d2={"Lcom/jakubjirak/structurizr/language/StructurizrFileType;", "Lcom/intellij/openapi/fileTypes/LanguageFileType;", "<init>", "()V", "getName", "", "getDescription", "getDefaultExtension", "getIcon", "Ljavax/swing/Icon;", "structurizr-dsl-plugin"})
public final class StructurizrFileType
extends LanguageFileType {
    @NotNull
    public static final StructurizrFileType INSTANCE = new StructurizrFileType();

    private StructurizrFileType() {
        super((Language)StructurizrLanguage.INSTANCE);
    }

    @NotNull
    public String getName() {
        return "Structurizr DSL";
    }

    @NotNull
    public String getDescription() {
        return "Structurizr DSL file for C4 model architecture diagrams";
    }

    @NotNull
    public String getDefaultExtension() {
        return "dsl";
    }

    @NotNull
    public Icon getIcon() {
        return StructurizrIcons.FILE;
    }
}
