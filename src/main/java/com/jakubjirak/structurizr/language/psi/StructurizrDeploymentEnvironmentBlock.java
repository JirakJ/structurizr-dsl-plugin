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
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentGroupBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentNodeDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructurizrDeploymentEnvironmentBlock
extends PsiElement {
    @NotNull
    public List<StructurizrDeploymentGroupBlock> getDeploymentGroupBlockList();

    @NotNull
    public List<StructurizrDeploymentNodeDefinition> getDeploymentNodeDefinitionList();

    @NotNull
    public List<StructurizrDirective> getDirectiveList();

    @NotNull
    public List<StructurizrRelationshipStatement> getRelationshipStatementList();

    @Nullable
    public PsiElement getString();
}
