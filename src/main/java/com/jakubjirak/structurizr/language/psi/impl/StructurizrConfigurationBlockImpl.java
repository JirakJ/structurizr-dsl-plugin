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
import com.jakubjirak.structurizr.language.psi.StructurizrConfigurationBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrScopeStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrUsersBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrVisibilityStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class StructurizrConfigurationBlockImpl
extends ASTWrapperPsiElement
implements StructurizrConfigurationBlock {
    public StructurizrConfigurationBlockImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrConfigurationBlockImpl.$$$reportNull$$$0(1);
        }
        visitor.visitConfigurationBlock(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrConfigurationBlockImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrPropertiesBlock.class);
        if (list == null) {
            StructurizrConfigurationBlockImpl.$$$reportNull$$$0(3);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrScopeStatement> getScopeStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrScopeStatement.class);
        if (list == null) {
            StructurizrConfigurationBlockImpl.$$$reportNull$$$0(4);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrUsersBlock> getUsersBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrUsersBlock.class);
        if (list == null) {
            StructurizrConfigurationBlockImpl.$$$reportNull$$$0(5);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrVisibilityStatement> getVisibilityStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrVisibilityStatement.class);
        if (list == null) {
            StructurizrConfigurationBlockImpl.$$$reportNull$$$0(6);
        }
        return list;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[switch (n) {
            default -> 3;
            case 3, 4, 5, 6 -> 2;
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
            case 6: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrConfigurationBlockImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrConfigurationBlockImpl";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getPropertiesBlockList";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getScopeStatementList";
                break;
            }
            case 5: {
                objectArray = objectArray2;
                objectArray2[1] = "getUsersBlockList";
                break;
            }
            case 6: {
                objectArray = objectArray2;
                objectArray2[1] = "getVisibilityStatementList";
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
            case 6: {
                break;
            }
        }
        String string = String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray);
        throw switch (n) {
            default -> new IllegalArgumentException(string);
            case 3, 4, 5, 6 -> new IllegalStateException(string);
        };
    }
}
