package com.jakubjirak.structurizr.refactoring;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class StructurizrRefactoringSupportProvider extends RefactoringSupportProvider {

    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, @Nullable PsiElement context) {
        if (element.getNode() != null &&
            element.getNode().getElementType() == StructurizrTypes.IDENTIFIER) {
            PsiElement parent = element.getParent();
            return parent instanceof StructurizrElementAssignment;
        }
        return false;
    }
}
