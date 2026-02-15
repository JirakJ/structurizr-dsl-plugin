/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrScopeStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrUsersBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrVisibilityStatement;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface StructurizrConfigurationBlock
extends PsiElement {
    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList();

    @NotNull
    public List<StructurizrScopeStatement> getScopeStatementList();

    @NotNull
    public List<StructurizrUsersBlock> getUsersBlockList();

    @NotNull
    public List<StructurizrVisibilityStatement> getVisibilityStatementList();
}
