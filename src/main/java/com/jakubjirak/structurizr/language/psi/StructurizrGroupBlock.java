/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrPersonDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface StructurizrGroupBlock
extends PsiElement {
    @NotNull
    public List<StructurizrPersonDefinition> getPersonDefinitionList();

    @NotNull
    public List<StructurizrRelationshipStatement> getRelationshipStatementList();

    @NotNull
    public List<StructurizrSoftwareSystemDefinition> getSoftwareSystemDefinitionList();

    @NotNull
    public PsiElement getString();
}
