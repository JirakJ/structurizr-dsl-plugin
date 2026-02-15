package com.jakubjirak.structurizr.navigation;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReferenceBase;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StructurizrReference extends PsiReferenceBase<PsiElement> {

    private final String identifierName;

    public StructurizrReference(@NotNull PsiElement element, @NotNull TextRange rangeInElement) {
        super(element, rangeInElement);
        this.identifierName = element.getText();
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        PsiFile file = myElement.getContainingFile();
        if (file == null) return null;
        List<PsiElement> assignments = new ArrayList<>();
        collectAssignments(file, assignments);
        for (PsiElement assignment : assignments) {
            ASTNode idNode = assignment.getNode().findChildByType(StructurizrTypes.IDENTIFIER);
            if (idNode != null && identifierName.equals(idNode.getText())) {
                return idNode.getPsi();
            }
        }
        return null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return EMPTY_ARRAY;
    }

    private static void collectAssignments(PsiElement element, List<PsiElement> result) {
        for (PsiElement child : element.getChildren()) {
            if (child.getNode().getElementType() == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                result.add(child);
            }
            collectAssignments(child, result);
        }
    }
}
