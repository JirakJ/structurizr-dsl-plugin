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
import com.jakubjirak.structurizr.language.psi.StructurizrDescriptionString;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment;
import com.jakubjirak.structurizr.language.psi.StructurizrPerspectivesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrTagsStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrTagsString;
import com.jakubjirak.structurizr.language.psi.StructurizrUrlStatement;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructurizrPersonDefinition
extends PsiElement {
    @NotNull
    public List<StructurizrDescriptionStatement> getDescriptionStatementList();

    @Nullable
    public StructurizrDescriptionString getDescriptionString();

    @NotNull
    public List<StructurizrDirective> getDirectiveList();

    @Nullable
    public StructurizrElementAssignment getElementAssignment();

    @NotNull
    public List<StructurizrPerspectivesBlock> getPerspectivesBlockList();

    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList();

    @NotNull
    public List<StructurizrRelationshipStatement> getRelationshipStatementList();

    @NotNull
    public List<StructurizrTagsStatement> getTagsStatementList();

    @Nullable
    public StructurizrTagsString getTagsString();

    @NotNull
    public List<StructurizrUrlStatement> getUrlStatementList();

    @NotNull
    public PsiElement getString();
}
