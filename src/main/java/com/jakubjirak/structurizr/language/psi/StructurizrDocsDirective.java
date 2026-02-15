/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrFilePath;
import org.jetbrains.annotations.Nullable;

public interface StructurizrDocsDirective
extends PsiElement {
    @Nullable
    public StructurizrFilePath getFilePath();

    @Nullable
    public PsiElement getIdentifier();

    @Nullable
    public PsiElement getString();
}
