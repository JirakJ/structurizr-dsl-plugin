/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.Language
 *  kotlin.Metadata
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language;

import com.intellij.lang.Language;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016\u00a8\u0006\n"}, d2={"Lcom/jakubjirak/structurizr/language/StructurizrLanguage;", "Lcom/intellij/lang/Language;", "<init>", "()V", "readResolve", "", "getDisplayName", "", "isCaseSensitive", "", "structurizr-dsl-plugin"})
public final class StructurizrLanguage
extends Language {
    @NotNull
    public static final StructurizrLanguage INSTANCE = new StructurizrLanguage();

    private StructurizrLanguage() {
        super("Structurizr");
    }

    private final Object readResolve() {
        return INSTANCE;
    }

    @NotNull
    public String getDisplayName() {
        return "Structurizr DSL";
    }

    public boolean isCaseSensitive() {
        return true;
    }
}
