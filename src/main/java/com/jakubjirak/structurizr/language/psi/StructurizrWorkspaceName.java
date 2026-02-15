/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface StructurizrWorkspaceName
extends PsiElement {
    @NotNull
    public PsiElement getString();
}
