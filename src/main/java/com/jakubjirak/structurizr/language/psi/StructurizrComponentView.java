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
import com.jakubjirak.structurizr.language.psi.StructurizrAnimationBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrAutoLayoutStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrDefaultStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrDescriptionStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrExcludeStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrIncludeStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrTitleStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrViewDescription;
import com.jakubjirak.structurizr.language.psi.StructurizrViewKey;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructurizrComponentView
extends PsiElement {
    @NotNull
    public List<StructurizrAnimationBlock> getAnimationBlockList();

    @NotNull
    public List<StructurizrAutoLayoutStatement> getAutoLayoutStatementList();

    @NotNull
    public List<StructurizrDefaultStatement> getDefaultStatementList();

    @NotNull
    public List<StructurizrDescriptionStatement> getDescriptionStatementList();

    @Nullable
    public StructurizrElementReference getElementReference();

    @NotNull
    public List<StructurizrExcludeStatement> getExcludeStatementList();

    @NotNull
    public List<StructurizrIncludeStatement> getIncludeStatementList();

    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList();

    @NotNull
    public List<StructurizrTitleStatement> getTitleStatementList();

    @Nullable
    public StructurizrViewDescription getViewDescription();

    @Nullable
    public StructurizrViewKey getViewKey();
}
