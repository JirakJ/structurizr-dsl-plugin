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
 */
package com.jakubjirak.structurizr.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
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
import com.jakubjirak.structurizr.language.psi.StructurizrViewsBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class StructurizrViewsBlockImpl
extends ASTWrapperPsiElement
implements StructurizrViewsBlock {
    public StructurizrViewsBlockImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(1);
        }
        visitor.visitViewsBlock(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @NotNull
    public List<StructurizrBrandingBlock> getBrandingBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrBrandingBlock.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(3);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrComponentView> getComponentViewList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrComponentView.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(4);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrContainerView> getContainerViewList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrContainerView.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(5);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrCustomView> getCustomViewList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrCustomView.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(6);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrDeploymentView> getDeploymentViewList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDeploymentView.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(7);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrDirective> getDirectiveList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDirective.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(8);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrDynamicView> getDynamicViewList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDynamicView.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(9);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrFilteredView> getFilteredViewList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrFilteredView.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(10);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrImageView> getImageViewList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrImageView.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(11);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrPropertiesBlock.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(12);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrStylesBlock> getStylesBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrStylesBlock.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(13);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrSystemContextView> getSystemContextViewList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrSystemContextView.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(14);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrSystemLandscapeView> getSystemLandscapeViewList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrSystemLandscapeView.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(15);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrTerminologyBlock> getTerminologyBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrTerminologyBlock.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(16);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrThemesStatement> getThemesStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrThemesStatement.class);
        if (list == null) {
            StructurizrViewsBlockImpl.$$$reportNull$$$0(17);
        }
        return list;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[switch (n) {
            default -> 3;
            case 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 -> 2;
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
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrViewsBlockImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrViewsBlockImpl";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getBrandingBlockList";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getComponentViewList";
                break;
            }
            case 5: {
                objectArray = objectArray2;
                objectArray2[1] = "getContainerViewList";
                break;
            }
            case 6: {
                objectArray = objectArray2;
                objectArray2[1] = "getCustomViewList";
                break;
            }
            case 7: {
                objectArray = objectArray2;
                objectArray2[1] = "getDeploymentViewList";
                break;
            }
            case 8: {
                objectArray = objectArray2;
                objectArray2[1] = "getDirectiveList";
                break;
            }
            case 9: {
                objectArray = objectArray2;
                objectArray2[1] = "getDynamicViewList";
                break;
            }
            case 10: {
                objectArray = objectArray2;
                objectArray2[1] = "getFilteredViewList";
                break;
            }
            case 11: {
                objectArray = objectArray2;
                objectArray2[1] = "getImageViewList";
                break;
            }
            case 12: {
                objectArray = objectArray2;
                objectArray2[1] = "getPropertiesBlockList";
                break;
            }
            case 13: {
                objectArray = objectArray2;
                objectArray2[1] = "getStylesBlockList";
                break;
            }
            case 14: {
                objectArray = objectArray2;
                objectArray2[1] = "getSystemContextViewList";
                break;
            }
            case 15: {
                objectArray = objectArray2;
                objectArray2[1] = "getSystemLandscapeViewList";
                break;
            }
            case 16: {
                objectArray = objectArray2;
                objectArray2[1] = "getTerminologyBlockList";
                break;
            }
            case 17: {
                objectArray = objectArray2;
                objectArray2[1] = "getThemesStatementList";
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
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: {
                break;
            }
        }
        String string = String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray);
        throw switch (n) {
            default -> new IllegalArgumentException(string);
            case 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 -> new IllegalStateException(string);
        };
    }
}
