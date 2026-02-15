/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.extapi.psi.ASTWrapperPsiElement
 *  com.intellij.lang.ASTNode
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.PsiElementVisitor
 *  com.intellij.psi.util.PsiTreeUtil
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.jakubjirak.structurizr.language.psi.StructurizrDescriptionStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrImageKroki;
import com.jakubjirak.structurizr.language.psi.StructurizrImageMermaid;
import com.jakubjirak.structurizr.language.psi.StructurizrImagePlantuml;
import com.jakubjirak.structurizr.language.psi.StructurizrImageView;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrTitleStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrViewKey;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructurizrImageViewImpl
extends ASTWrapperPsiElement
implements StructurizrImageView {
    public StructurizrImageViewImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrImageViewImpl.$$$reportNull$$$0(1);
        }
        visitor.visitImageView(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrImageViewImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @NotNull
    public List<StructurizrDescriptionStatement> getDescriptionStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDescriptionStatement.class);
        if (list == null) {
            StructurizrImageViewImpl.$$$reportNull$$$0(3);
        }
        return list;
    }

    @Override
    @Nullable
    public StructurizrElementReference getElementReference() {
        return (StructurizrElementReference)this.findChildByClass(StructurizrElementReference.class);
    }

    @Override
    @NotNull
    public List<StructurizrImageKroki> getImageKrokiList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrImageKroki.class);
        if (list == null) {
            StructurizrImageViewImpl.$$$reportNull$$$0(4);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrImageMermaid> getImageMermaidList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrImageMermaid.class);
        if (list == null) {
            StructurizrImageViewImpl.$$$reportNull$$$0(5);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrImagePlantuml> getImagePlantumlList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrImagePlantuml.class);
        if (list == null) {
            StructurizrImageViewImpl.$$$reportNull$$$0(6);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrPropertiesBlock.class);
        if (list == null) {
            StructurizrImageViewImpl.$$$reportNull$$$0(7);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrTitleStatement> getTitleStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrTitleStatement.class);
        if (list == null) {
            StructurizrImageViewImpl.$$$reportNull$$$0(8);
        }
        return list;
    }

    @Override
    @Nullable
    public StructurizrViewKey getViewKey() {
        return (StructurizrViewKey)this.findChildByClass(StructurizrViewKey.class);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[switch (n) {
            default -> 3;
            case 3, 4, 5, 6, 7, 8 -> 2;
        }];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "node";
                break;
            }
            case 1: 
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "visitor";
                break;
            }
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrImageViewImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrImageViewImpl";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getDescriptionStatementList";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getImageKrokiList";
                break;
            }
            case 5: {
                objectArray = objectArray2;
                objectArray2[1] = "getImageMermaidList";
                break;
            }
            case 6: {
                objectArray = objectArray2;
                objectArray2[1] = "getImagePlantumlList";
                break;
            }
            case 7: {
                objectArray = objectArray2;
                objectArray2[1] = "getPropertiesBlockList";
                break;
            }
            case 8: {
                objectArray = objectArray2;
                objectArray2[1] = "getTitleStatementList";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "<init>";
                break;
            }
            case 1: 
            case 2: {
                objectArray = objectArray;
                objectArray[2] = "accept";
                break;
            }
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                break;
            }
        }
        String string = String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray);
        throw switch (n) {
            default -> new IllegalArgumentException(string);
            case 3, 4, 5, 6, 7, 8 -> new IllegalStateException(string);
        };
    }
}
