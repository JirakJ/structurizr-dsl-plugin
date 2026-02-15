/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrBrandingBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrComponentView;
import com.jakubjirak.structurizr.language.psi.StructurizrContainerView;
import com.jakubjirak.structurizr.language.psi.StructurizrCustomView;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentView;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrDynamicView;
import com.jakubjirak.structurizr.language.psi.StructurizrFilteredView;
import com.jakubjirak.structurizr.language.psi.StructurizrImageView;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrStylesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrSystemContextView;
import com.jakubjirak.structurizr.language.psi.StructurizrSystemLandscapeView;
import com.jakubjirak.structurizr.language.psi.StructurizrTerminologyBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrThemesStatement;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface StructurizrViewsBlock
extends PsiElement {
    @NotNull
    public List<StructurizrBrandingBlock> getBrandingBlockList();

    @NotNull
    public List<StructurizrComponentView> getComponentViewList();

    @NotNull
    public List<StructurizrContainerView> getContainerViewList();

    @NotNull
    public List<StructurizrCustomView> getCustomViewList();

    @NotNull
    public List<StructurizrDeploymentView> getDeploymentViewList();

    @NotNull
    public List<StructurizrDirective> getDirectiveList();

    @NotNull
    public List<StructurizrDynamicView> getDynamicViewList();

    @NotNull
    public List<StructurizrFilteredView> getFilteredViewList();

    @NotNull
    public List<StructurizrImageView> getImageViewList();

    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList();

    @NotNull
    public List<StructurizrStylesBlock> getStylesBlockList();

    @NotNull
    public List<StructurizrSystemContextView> getSystemContextViewList();

    @NotNull
    public List<StructurizrSystemLandscapeView> getSystemLandscapeViewList();

    @NotNull
    public List<StructurizrTerminologyBlock> getTerminologyBlockList();

    @NotNull
    public List<StructurizrThemesStatement> getThemesStatementList();
}
