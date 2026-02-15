package com.jakubjirak.structurizr.navigation;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import org.jetbrains.annotations.NotNull;

public class StructurizrReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(),
            new PsiReferenceProvider() {
                @Override
                public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                                       @NotNull ProcessingContext context) {
                    IElementType type = element.getNode().getElementType();
                    if (type != StructurizrTypes.IDENTIFIER) {
                        return PsiReference.EMPTY_ARRAY;
                    }
                    PsiElement parent = element.getParent();
                    if (parent == null) {
                        return PsiReference.EMPTY_ARRAY;
                    }
                    IElementType parentType = parent.getNode().getElementType();
                    // Only provide references for IDENTIFIER used in reference contexts
                    if (parentType == StructurizrTypes.ELEMENT_REFERENCE ||
                        parentType == StructurizrTypes.RELATIONSHIP_STATEMENT ||
                        parentType == StructurizrTypes.RELATIONSHIP_IN_DYNAMIC_VIEW ||
                        parentType == StructurizrTypes.SYSTEM_CONTEXT_VIEW ||
                        parentType == StructurizrTypes.CONTAINER_VIEW ||
                        parentType == StructurizrTypes.COMPONENT_VIEW ||
                        parentType == StructurizrTypes.DEPLOYMENT_VIEW ||
                        parentType == StructurizrTypes.DYNAMIC_VIEW ||
                        parentType == StructurizrTypes.INCLUDE_STATEMENT ||
                        parentType == StructurizrTypes.EXCLUDE_STATEMENT ||
                        parentType == StructurizrTypes.ELEMENT_SELECTOR ||
                        parentType == StructurizrTypes.ANIMATION_STEP) {
                        TextRange range = new TextRange(0, element.getTextLength());
                        return new PsiReference[]{new StructurizrReference(element, range)};
                    }
                    return PsiReference.EMPTY_ARRAY;
                }
            }
        );
    }
}
