package com.jakubjirak.structurizr.navigation;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import org.jetbrains.annotations.NotNull;

public class StructurizrIncludeReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(),
            new PsiReferenceProvider() {
                @Override
                public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                                       @NotNull ProcessingContext context) {
                    IElementType type = element.getNode().getElementType();

                    // We handle STRING or IDENTIFIER tokens inside an INCLUDE_DIRECTIVE
                    if (type != StructurizrTypes.STRING && type != StructurizrTypes.IDENTIFIER) {
                        return PsiReference.EMPTY_ARRAY;
                    }

                    PsiElement parent = element.getParent();
                    if (parent == null) {
                        return PsiReference.EMPTY_ARRAY;
                    }

                    IElementType parentType = parent.getNode().getElementType();
                    if (parentType != StructurizrTypes.INCLUDE_DIRECTIVE) {
                        return PsiReference.EMPTY_ARRAY;
                    }

                    // Skip the directive keyword itself
                    String text = element.getText();
                    if (text.equals("!include")) {
                        return PsiReference.EMPTY_ARRAY;
                    }

                    // Build a text range for the file path portion
                    int startOffset = 0;
                    int endOffset = text.length();
                    // Strip surrounding quotes for STRING tokens
                    if (text.startsWith("\"") && text.endsWith("\"") && text.length() > 1) {
                        startOffset = 1;
                        endOffset = text.length() - 1;
                    }
                    TextRange range = new TextRange(startOffset, endOffset);
                    return new PsiReference[]{new StructurizrIncludeReference(element, range)};
                }
            }
        );
    }
}
