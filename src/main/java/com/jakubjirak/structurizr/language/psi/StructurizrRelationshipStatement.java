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
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipTags;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipTechnology;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructurizrRelationshipStatement
extends PsiElement {
    @NotNull
    public List<StructurizrElementReference> getElementReferenceList();

    @Nullable
    public StructurizrRelationshipDescription getRelationshipDescription();

    @Nullable
    public StructurizrRelationshipTags getRelationshipTags();

    @Nullable
    public StructurizrRelationshipTechnology getRelationshipTechnology();
}
