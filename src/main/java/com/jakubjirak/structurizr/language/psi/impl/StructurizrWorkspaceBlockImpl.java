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
import com.jakubjirak.structurizr.language.psi.StructurizrConfigurationBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrModelBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import com.jakubjirak.structurizr.language.psi.StructurizrViewsBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import com.jakubjirak.structurizr.language.psi.StructurizrWorkspaceBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrWorkspaceDescription;
import com.jakubjirak.structurizr.language.psi.StructurizrWorkspaceName;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructurizrWorkspaceBlockImpl
extends ASTWrapperPsiElement
implements StructurizrWorkspaceBlock {
    public StructurizrWorkspaceBlockImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrWorkspaceBlockImpl.$$$reportNull$$$0(1);
        }
        visitor.visitWorkspaceBlock(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrWorkspaceBlockImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @NotNull
    public List<StructurizrConfigurationBlock> getConfigurationBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrConfigurationBlock.class);
        if (list == null) {
            StructurizrWorkspaceBlockImpl.$$$reportNull$$$0(3);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrDirective> getDirectiveList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrDirective.class);
        if (list == null) {
            StructurizrWorkspaceBlockImpl.$$$reportNull$$$0(4);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrModelBlock> getModelBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrModelBlock.class);
        if (list == null) {
            StructurizrWorkspaceBlockImpl.$$$reportNull$$$0(5);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrPropertiesBlock> getPropertiesBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrPropertiesBlock.class);
        if (list == null) {
            StructurizrWorkspaceBlockImpl.$$$reportNull$$$0(6);
        }
        return list;
    }

    @Override
    @NotNull
    public List<StructurizrViewsBlock> getViewsBlockList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrViewsBlock.class);
        if (list == null) {
            StructurizrWorkspaceBlockImpl.$$$reportNull$$$0(7);
        }
        return list;
    }

    @Override
    @Nullable
    public StructurizrWorkspaceDescription getWorkspaceDescription() {
        return (StructurizrWorkspaceDescription)this.findChildByClass(StructurizrWorkspaceDescription.class);
    }

    @Override
    @Nullable
    public StructurizrWorkspaceName getWorkspaceName() {
        return (StructurizrWorkspaceName)this.findChildByClass(StructurizrWorkspaceName.class);
    }

    @Override
    @Nullable
    public PsiElement getString() {
        return this.findChildByType(StructurizrTypes.STRING);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[switch (n) {
            default -> 3;
            case 3, 4, 5, 6, 7 -> 2;
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
            case 7: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrWorkspaceBlockImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrWorkspaceBlockImpl";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getConfigurationBlockList";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getDirectiveList";
                break;
            }
            case 5: {
                objectArray = objectArray2;
                objectArray2[1] = "getModelBlockList";
                break;
            }
            case 6: {
                objectArray = objectArray2;
                objectArray2[1] = "getPropertiesBlockList";
                break;
            }
            case 7: {
                objectArray = objectArray2;
                objectArray2[1] = "getViewsBlockList";
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
            case 7: {
                break;
            }
        }
        String string = String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray);
        throw switch (n) {
            default -> new IllegalArgumentException(string);
            case 3, 4, 5, 6, 7 -> new IllegalStateException(string);
        };
    }
}
