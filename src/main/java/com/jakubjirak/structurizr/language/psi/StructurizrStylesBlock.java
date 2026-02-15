/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrElementStyle;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStyle;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface StructurizrStylesBlock
extends PsiElement {
    @NotNull
    public List<StructurizrElementStyle> getElementStyleList();

    @NotNull
    public List<StructurizrRelationshipStyle> getRelationshipStyleList();
}
