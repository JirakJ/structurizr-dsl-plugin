/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import org.jetbrains.annotations.Nullable;

public interface StructurizrElementSelector
extends PsiElement {
    @Nullable
    public StructurizrElementReference getElementReference();

    @Nullable
    public PsiElement getString();
}
