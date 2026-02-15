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
import com.jakubjirak.structurizr.language.psi.StructurizrDescriptionStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrImageKroki;
import com.jakubjirak.structurizr.language.psi.StructurizrImageMermaid;
import com.jakubjirak.structurizr.language.psi.StructurizrImagePlantuml;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrTitleStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrViewKey;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructurizrImageView
extends PsiElement {
    @NotNull
    public List<StructurizrDescriptionStatement> getDescriptionStatementList();

    @Nullable
    public StructurizrElementReference getElementReference();

    @NotNull
    public List<StructurizrImageKroki> getImageKrokiList();

    @NotNull
    public List<StructurizrImageMermaid> getImageMermaidList();

    @NotNull
    public List<StructurizrImagePlantuml> getImagePlantumlList();

    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList();

    @NotNull
    public List<StructurizrTitleStatement> getTitleStatementList();

    @Nullable
    public StructurizrViewKey getViewKey();
}
