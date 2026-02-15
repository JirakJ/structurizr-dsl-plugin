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
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentNodeDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrDescriptionStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrDescriptionString;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment;
import com.jakubjirak.structurizr.language.psi.StructurizrInfrastructureNodeDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrInstancesString;
import com.jakubjirak.structurizr.language.psi.StructurizrPerspectivesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemInstanceDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrTagsStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrTagsString;
import com.jakubjirak.structurizr.language.psi.StructurizrTechnologyString;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import com.jakubjirak.structurizr.language.psi.StructurizrUrlStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructurizrDeploymentNodeDefinitionImpl
extends ASTWrapperPsiElement
implements StructurizrDeploymentNodeDefinition {
    public StructurizrDeploymentNodeDefinitionImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(1);
        }
        visitor.visitDeploymentNodeDefinition(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @NotNull
    public List<StructurizrContainerInstanceDefinition> getContainerInstanceDefinitionList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrContainerInstanceDefinition.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(3);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrDeploymentNodeDefinition> getDeploymentNodeDefinitionList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDeploymentNodeDefinition.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(4);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrDescriptionStatement> getDescriptionStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDescriptionStatement.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(5);
        }
        return list;
    }

    @Override
    @Nullable
    public StructurizrDescriptionString getDescriptionString() {
        return (StructurizrDescriptionString)this.findChildByClass(StructurizrDescriptionString.class);
    }

    @Override
    @NotNull
    public List<StructurizrDirective> getDirectiveList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDirective.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(6);
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
    public List<StructurizrInfrastructureNodeDefinition> getInfrastructureNodeDefinitionList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrInfrastructureNodeDefinition.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(7);
        }
        return list;
    }

    @Override
    @Nullable
    public StructurizrInstancesString getInstancesString() {
        return (StructurizrInstancesString)this.findChildByClass(StructurizrInstancesString.class);
    }

    @Override
    @NotNull
    public List<StructurizrPerspectivesBlock> getPerspectivesBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrPerspectivesBlock.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(8);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrPropertiesBlock.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(9);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrRelationshipStatement> getRelationshipStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrRelationshipStatement.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(10);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrSoftwareSystemInstanceDefinition> getSoftwareSystemInstanceDefinitionList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrSoftwareSystemInstanceDefinition.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(11);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrTagsStatement> getTagsStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrTagsStatement.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(12);
        }
        return list;
    }

    @Override
    @Nullable
    public StructurizrTagsString getTagsString() {
        return (StructurizrTagsString)this.findChildByClass(StructurizrTagsString.class);
    }

    @Override
    @Nullable
    public StructurizrTechnologyString getTechnologyString() {
        return (StructurizrTechnologyString)this.findChildByClass(StructurizrTechnologyString.class);
    }

    @Override
    @NotNull
    public List<StructurizrUrlStatement> getUrlStatementList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrUrlStatement.class);
        if (list == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(13);
        }
        return list;
    }

    @Override
    @NotNull
    public PsiElement getString() {
        PsiElement psiElement = this.findNotNullChildByType(StructurizrTypes.STRING);
        if (psiElement == null) {
            StructurizrDeploymentNodeDefinitionImpl.$$$reportNull$$$0(14);
        }
        return psiElement;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[switch (n) {
            default -> 3;
            case 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 -> 2;
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
            case 14: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrDeploymentNodeDefinitionImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrDeploymentNodeDefinitionImpl";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getContainerInstanceDefinitionList";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getDeploymentNodeDefinitionList";
                break;
            }
            case 5: {
                objectArray = objectArray2;
                objectArray2[1] = "getDescriptionStatementList";
                break;
            }
            case 6: {
                objectArray = objectArray2;
                objectArray2[1] = "getDirectiveList";
                break;
            }
            case 7: {
                objectArray = objectArray2;
                objectArray2[1] = "getInfrastructureNodeDefinitionList";
                break;
            }
            case 8: {
                objectArray = objectArray2;
                objectArray2[1] = "getPerspectivesBlockList";
                break;
            }
            case 9: {
                objectArray = objectArray2;
                objectArray2[1] = "getPropertiesBlockList";
                break;
            }
            case 10: {
                objectArray = objectArray2;
                objectArray2[1] = "getRelationshipStatementList";
                break;
            }
            case 11: {
                objectArray = objectArray2;
                objectArray2[1] = "getSoftwareSystemInstanceDefinitionList";
                break;
            }
            case 12: {
                objectArray = objectArray2;
                objectArray2[1] = "getTagsStatementList";
                break;
            }
            case 13: {
                objectArray = objectArray2;
                objectArray2[1] = "getUrlStatementList";
                break;
            }
            case 14: {
                objectArray = objectArray2;
                objectArray2[1] = "getString";
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
            case 14: {
                break;
            }
        }
        String string = String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray);
        throw switch (n) {
            default -> new IllegalArgumentException(string);
            case 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 -> new IllegalStateException(string);
        };
    }
}
