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
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrPerspectivesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrTagsStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrUrlStatement;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructurizrRefStatement
extends PsiElement {
    @NotNull
    public List<StructurizrDescriptionStatement> getDescriptionStatementList();

    @NotNull
    public List<StructurizrDirective> getDirectiveList();

    @Nullable
    public StructurizrElementAssignment getElementAssignment();

    @NotNull
    public StructurizrElementReference getElementReference();

    @NotNull
    public List<StructurizrPerspectivesBlock> getPerspectivesBlockList();

    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList();

    @NotNull
    public List<StructurizrRelationshipStatement> getRelationshipStatementList();

    @NotNull
    public List<StructurizrTagsStatement> getTagsStatementList();

    @NotNull
    public List<StructurizrUrlStatement> getUrlStatementList();
}
