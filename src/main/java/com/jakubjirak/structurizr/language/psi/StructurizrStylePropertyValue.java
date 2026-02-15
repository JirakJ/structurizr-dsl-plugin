/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrShapeValue;
import org.jetbrains.annotations.Nullable;

public interface StructurizrStylePropertyValue
extends PsiElement {
    @Nullable
    public StructurizrShapeValue getShapeValue();

    @Nullable
    public PsiElement getColorLiteral();

    @Nullable
    public PsiElement getIdentifier();

    @Nullable
    public PsiElement getNumber();

    @Nullable
    public PsiElement getString();
}
