/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrLayoutDirection;
import com.jakubjirak.structurizr.language.psi.StructurizrNodeSeparation;
import com.jakubjirak.structurizr.language.psi.StructurizrRankSeparation;
import org.jetbrains.annotations.Nullable;

public interface StructurizrAutoLayoutStatement
extends PsiElement {
    @Nullable
    public StructurizrLayoutDirection getLayoutDirection();

    @Nullable
    public StructurizrNodeSeparation getNodeSeparation();

    @Nullable
    public StructurizrRankSeparation getRankSeparation();
}
