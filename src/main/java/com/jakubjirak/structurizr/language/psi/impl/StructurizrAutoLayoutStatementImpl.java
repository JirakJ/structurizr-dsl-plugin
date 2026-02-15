/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.extapi.psi.ASTWrapperPsiElement
 *  com.intellij.lang.ASTNode
 *  com.intellij.psi.PsiElementVisitor
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.jakubjirak.structurizr.language.psi.StructurizrAutoLayoutStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrLayoutDirection;
import com.jakubjirak.structurizr.language.psi.StructurizrNodeSeparation;
import com.jakubjirak.structurizr.language.psi.StructurizrRankSeparation;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructurizrAutoLayoutStatementImpl
extends ASTWrapperPsiElement
implements StructurizrAutoLayoutStatement {
    public StructurizrAutoLayoutStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrAutoLayoutStatementImpl.$$$reportNull$$$0(1);
        }
        visitor.visitAutoLayoutStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrAutoLayoutStatementImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @Nullable
    public StructurizrLayoutDirection getLayoutDirection() {
        return (StructurizrLayoutDirection)this.findChildByClass(StructurizrLayoutDirection.class);
    }

    @Override
    @Nullable
    public StructurizrNodeSeparation getNodeSeparation() {
        return (StructurizrNodeSeparation)this.findChildByClass(StructurizrNodeSeparation.class);
    }

    @Override
    @Nullable
    public StructurizrRankSeparation getRankSeparation() {
        return (StructurizrRankSeparation)this.findChildByClass(StructurizrRankSeparation.class);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[3];
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
        }
        objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrAutoLayoutStatementImpl";
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[2] = "<init>";
                break;
            }
            case 1: 
            case 2: {
                objectArray = objectArray2;
                objectArray2[2] = "accept";
                break;
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }
}
