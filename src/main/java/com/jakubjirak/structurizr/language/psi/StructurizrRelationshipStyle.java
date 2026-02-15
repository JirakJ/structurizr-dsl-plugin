/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrStyleProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface StructurizrRelationshipStyle
extends PsiElement {
    @NotNull
    public List<StructurizrStyleProperty> getStylePropertyList();

    @NotNull
    public PsiElement getString();
}
