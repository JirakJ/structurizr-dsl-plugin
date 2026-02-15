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
import com.jakubjirak.structurizr.language.psi.StructurizrConfigurationBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrModelBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrViewsBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrWorkspaceDescription;
import com.jakubjirak.structurizr.language.psi.StructurizrWorkspaceName;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructurizrWorkspaceBlock
extends PsiElement {
    @NotNull
    public List<StructurizrConfigurationBlock> getConfigurationBlockList();

    @NotNull
    public List<StructurizrDirective> getDirectiveList();

    @NotNull
    public List<StructurizrModelBlock> getModelBlockList();

    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList();

    @NotNull
    public List<StructurizrViewsBlock> getViewsBlockList();

    @Nullable
    public StructurizrWorkspaceDescription getWorkspaceDescription();

    @Nullable
    public StructurizrWorkspaceName getWorkspaceName();

    @Nullable
    public PsiElement getString();
}
