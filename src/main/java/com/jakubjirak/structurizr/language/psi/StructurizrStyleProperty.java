/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrStylePropertyName;
import com.jakubjirak.structurizr.language.psi.StructurizrStylePropertyValue;
import org.jetbrains.annotations.NotNull;

public interface StructurizrStyleProperty
extends PsiElement {
    @NotNull
    public StructurizrStylePropertyName getStylePropertyName();

    @NotNull
    public StructurizrStylePropertyValue getStylePropertyValue();
}
