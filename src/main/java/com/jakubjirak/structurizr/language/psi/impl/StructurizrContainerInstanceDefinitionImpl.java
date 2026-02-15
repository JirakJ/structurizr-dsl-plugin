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
import com.jakubjirak.structurizr.language.psi.StructurizrContainerInstanceDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentGroups;
import com.jakubjirak.structurizr.language.psi.StructurizrDescriptionStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrPerspectivesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrTagsStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrTagsString;
import com.jakubjirak.structurizr.language.psi.StructurizrUrlStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructurizrContainerInstanceDefinitionImpl
extends ASTWrapperPsiElement
implements StructurizrContainerInstanceDefinition {
    public StructurizrContainerInstanceDefinitionImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrContainerInstanceDefinitionImpl.$$$reportNull$$$0(1);
        }
        visitor.visitContainerInstanceDefinition(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrContainerInstanceDefinitionImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @Nullable
    public StructurizrDeploymentGroups getDeploymentGroups() {
        return (StructurizrDeploymentGroups)this.findChildByClass(StructurizrDeploymentGroups.class);
    }

    @Override
    @NotNull
    public List<StructurizrDescriptionStatement> getDescriptionStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDescriptionStatement.class);
        if (list == null) {
            StructurizrContainerInstanceDefinitionImpl.$$$reportNull$$$0(3);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrDirective> getDirectiveList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDirective.class);
        if (list == null) {
            StructurizrContainerInstanceDefinitionImpl.$$$reportNull$$$0(4);
        }
        return list;
    }

    @Override
    @Nullable
    public StructurizrElementAssignment getElementAssignment() {
        return (StructurizrElementAssignment)this.findChildByClass(StructurizrElementAssignment.class);
    }

    @Override
    @NotNull
    public StructurizrElementReference getElementReference() {
        StructurizrElementReference structurizrElementReference = (StructurizrElementReference)this.findNotNullChildByClass(StructurizrElementReference.class);
        if (structurizrElementReference == null) {
            StructurizrContainerInstanceDefinitionImpl.$$$reportNull$$$0(5);
        }
        return structurizrElementReference;
    }

    @Override
    @NotNull
    public List<StructurizrPerspectivesBlock> getPerspectivesBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrPerspectivesBlock.class);
        if (list == null) {
            StructurizrContainerInstanceDefinitionImpl.$$$reportNull$$$0(6);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrPropertiesBlock.class);
        if (list == null) {
            StructurizrContainerInstanceDefinitionImpl.$$$reportNull$$$0(7);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrRelationshipStatement> getRelationshipStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrRelationshipStatement.class);
        if (list == null) {
            StructurizrContainerInstanceDefinitionImpl.$$$reportNull$$$0(8);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrTagsStatement> getTagsStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrTagsStatement.class);
        if (list == null) {
            StructurizrContainerInstanceDefinitionImpl.$$$reportNull$$$0(9);
        }
        return list;
    }

    @Override
    @Nullable
    public StructurizrTagsString getTagsString() {
        return (StructurizrTagsString)this.findChildByClass(StructurizrTagsString.class);
    }

    @Override
    @NotNull
    public List<StructurizrUrlStatement> getUrlStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrUrlStatement.class);
        if (list == null) {
            StructurizrContainerInstanceDefinitionImpl.$$$reportNull$$$0(10);
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
                objectArray3[0] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrContainerInstanceDefinitionImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrContainerInstanceDefinitionImpl";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getDescriptionStatementList";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getDirectiveList";
                break;
            }
            case 5: {
                objectArray = objectArray2;
                objectArray2[1] = "getElementReference";
                break;
            }
            case 6: {
                objectArray = objectArray2;
                objectArray2[1] = "getPerspectivesBlockList";
                break;
            }
            case 7: {
                objectArray = objectArray2;
                objectArray2[1] = "getPropertiesBlockList";
                break;
            }
            case 8: {
                objectArray = objectArray2;
                objectArray2[1] = "getRelationshipStatementList";
                break;
            }
            case 9: {
                objectArray = objectArray2;
                objectArray2[1] = "getTagsStatementList";
                break;
            }
            case 10: {
                objectArray = objectArray2;
                objectArray2[1] = "getUrlStatementList";
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
