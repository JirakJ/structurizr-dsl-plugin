/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipDescription;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructurizrRelationshipInDynamicView
extends PsiElement {
    @NotNull
    public List<StructurizrElementReference> getElementReferenceList();

    @Nullable
    public StructurizrRelationshipDescription getRelationshipDescription();
}
