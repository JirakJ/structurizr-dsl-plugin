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
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentEnvironmentBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrEnterpriseBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrGroupBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrModelBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPersonDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrRefStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class StructurizrModelBlockImpl
extends ASTWrapperPsiElement
implements StructurizrModelBlock {
    public StructurizrModelBlockImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrModelBlockImpl.$$$reportNull$$$0(1);
        }
        visitor.visitModelBlock(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrModelBlockImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @NotNull
    public List<StructurizrDeploymentEnvironmentBlock> getDeploymentEnvironmentBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDeploymentEnvironmentBlock.class);
        if (list == null) {
            StructurizrModelBlockImpl.$$$reportNull$$$0(3);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrDirective> getDirectiveList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDirective.class);
        if (list == null) {
            StructurizrModelBlockImpl.$$$reportNull$$$0(4);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrEnterpriseBlock> getEnterpriseBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrEnterpriseBlock.class);
        if (list == null) {
            StructurizrModelBlockImpl.$$$reportNull$$$0(5);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrGroupBlock> getGroupBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrGroupBlock.class);
        if (list == null) {
            StructurizrModelBlockImpl.$$$reportNull$$$0(6);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrPersonDefinition> getPersonDefinitionList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrPersonDefinition.class);
        if (list == null) {
            StructurizrModelBlockImpl.$$$reportNull$$$0(7);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrRefStatement> getRefStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrRefStatement.class);
        if (list == null) {
            StructurizrModelBlockImpl.$$$reportNull$$$0(8);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrRelationshipStatement> getRelationshipStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrRelationshipStatement.class);
        if (list == null) {
            StructurizrModelBlockImpl.$$$reportNull$$$0(9);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrSoftwareSystemDefinition> getSoftwareSystemDefinitionList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrSoftwareSystemDefinition.class);
        if (list == null) {
            StructurizrModelBlockImpl.$$$reportNull$$$0(10);
        }
        return list;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[switch (n) {
            default -> 3;
            case 3, 4, 5, 6, 7, 8, 9, 10 -> 2;
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
            case 10: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrModelBlockImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrModelBlockImpl";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getDeploymentEnvironmentBlockList";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getDirectiveList";
                break;
            }
            case 5: {
                objectArray = objectArray2;
                objectArray2[1] = "getEnterpriseBlockList";
                break;
            }
            case 6: {
                objectArray = objectArray2;
                objectArray2[1] = "getGroupBlockList";
                break;
            }
            case 7: {
                objectArray = objectArray2;
                objectArray2[1] = "getPersonDefinitionList";
                break;
            }
            case 8: {
                objectArray = objectArray2;
                objectArray2[1] = "getRefStatementList";
                break;
            }
            case 9: {
                objectArray = objectArray2;
                objectArray2[1] = "getRelationshipStatementList";
                break;
            }
            case 10: {
                objectArray = objectArray2;
                objectArray2[1] = "getSoftwareSystemDefinitionList";
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
            case 10: {
                break;
            }
        }
        String string = String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray);
        throw switch (n) {
            default -> new IllegalArgumentException(string);
            case 3, 4, 5, 6, 7, 8, 9, 10 -> new IllegalStateException(string);
        };
    }
}
