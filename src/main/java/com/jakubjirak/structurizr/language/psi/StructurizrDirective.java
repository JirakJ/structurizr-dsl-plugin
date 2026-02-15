/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrAdrsDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrConstantDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrDocsDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrIdentifiersDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrImpliedRelationshipsDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrIncludeDirective;
import org.jetbrains.annotations.Nullable;

public interface StructurizrDirective
extends PsiElement {
    @Nullable
    public StructurizrAdrsDirective getAdrsDirective();

    @Nullable
    public StructurizrConstantDirective getConstantDirective();

    @Nullable
    public StructurizrDocsDirective getDocsDirective();

    @Nullable
    public StructurizrIdentifiersDirective getIdentifiersDirective();

    @Nullable
    public StructurizrImpliedRelationshipsDirective getImpliedRelationshipsDirective();

    @Nullable
    public StructurizrIncludeDirective getIncludeDirective();
}
