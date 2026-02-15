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
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentEnvironmentBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrGroupBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPersonDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrRefStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructurizrEnterpriseBlock
extends PsiElement {
    @NotNull
    public List<StructurizrDeploymentEnvironmentBlock> getDeploymentEnvironmentBlockList();

    @NotNull
    public List<StructurizrDirective> getDirectiveList();

    @NotNull
    public List<StructurizrEnterpriseBlock> getEnterpriseBlockList();

    @NotNull
    public List<StructurizrGroupBlock> getGroupBlockList();

    @NotNull
    public List<StructurizrPersonDefinition> getPersonDefinitionList();

    @NotNull
    public List<StructurizrRefStatement> getRefStatementList();

    @NotNull
    public List<StructurizrRelationshipStatement> getRelationshipStatementList();

    @NotNull
    public List<StructurizrSoftwareSystemDefinition> getSoftwareSystemDefinitionList();

    @Nullable
    public PsiElement getString();
}
