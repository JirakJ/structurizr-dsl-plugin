/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrValueLiteral;
import org.jetbrains.annotations.NotNull;

public interface StructurizrConstantDirective
extends PsiElement {
    @NotNull
    public StructurizrValueLiteral getValueLiteral();

    @NotNull
    public PsiElement getIdentifier();
}
