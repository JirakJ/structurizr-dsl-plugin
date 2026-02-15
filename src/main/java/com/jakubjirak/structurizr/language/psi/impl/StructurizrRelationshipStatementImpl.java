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
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipDescription;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipTags;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipTechnology;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructurizrRelationshipStatementImpl
extends ASTWrapperPsiElement
implements StructurizrRelationshipStatement {
    public StructurizrRelationshipStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrRelationshipStatementImpl.$$$reportNull$$$0(1);
        }
        visitor.visitRelationshipStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrRelationshipStatementImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @NotNull
    public List<StructurizrElementReference> getElementReferenceList() {
        List list = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, StructurizrElementReference.class);
        if (list == null) {
            StructurizrRelationshipStatementImpl.$$$reportNull$$$0(3);
        }
        return list;
    }

    @Override
    @Nullable
    public StructurizrRelationshipDescription getRelationshipDescription() {
        return (StructurizrRelationshipDescription)this.findChildByClass(StructurizrRelationshipDescription.class);
    }

    @Override
    @Nullable
    public StructurizrRelationshipTags getRelationshipTags() {
        return (StructurizrRelationshipTags)this.findChildByClass(StructurizrRelationshipTags.class);
    }

    @Override
    @Nullable
    public StructurizrRelationshipTechnology getRelationshipTechnology() {
        return (StructurizrRelationshipTechnology)this.findChildByClass(StructurizrRelationshipTechnology.class);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[switch (n) {
            default -> 3;
            case 3 -> 2;
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
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrRelationshipStatementImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrRelationshipStatementImpl";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getElementReferenceList";
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
            case 3: {
                break;
            }
        }
        String string = String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray);
        throw switch (n) {
            default -> new IllegalArgumentException(string);
            case 3 -> new IllegalStateException(string);
        };
    }
}
