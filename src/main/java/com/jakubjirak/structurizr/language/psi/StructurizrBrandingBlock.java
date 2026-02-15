/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrFontStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrLogoStatement;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface StructurizrBrandingBlock
extends PsiElement {
    @NotNull
    public List<StructurizrFontStatement> getFontStatementList();

    @NotNull
    public List<StructurizrLogoStatement> getLogoStatementList();
}
