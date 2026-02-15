/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.extapi.psi.ASTWrapperPsiElement
 *  com.intellij.lang.ASTNode
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.PsiElementVisitor
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.jakubjirak.structurizr.language.psi.StructurizrConstantDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import com.jakubjirak.structurizr.language.psi.StructurizrValueLiteral;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import org.jetbrains.annotations.NotNull;

public class StructurizrConstantDirectiveImpl
extends ASTWrapperPsiElement
implements StructurizrConstantDirective {
    public StructurizrConstantDirectiveImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrConstantDirectiveImpl.$$$reportNull$$$0(1);
        }
        visitor.visitConstantDirective(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrConstantDirectiveImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @NotNull
    public StructurizrValueLiteral getValueLiteral() {
        StructurizrValueLiteral structurizrValueLiteral = (StructurizrValueLiteral)this.findNotNullChildByClass(StructurizrValueLiteral.class);
        if (structurizrValueLiteral == null) {
            StructurizrConstantDirectiveImpl.$$$reportNull$$$0(3);
        }
        return structurizrValueLiteral;
    }

    @Override
    @NotNull
    public PsiElement getIdentifier() {
        PsiElement psiElement = this.findNotNullChildByType(StructurizrTypes.IDENTIFIER);
        if (psiElement == null) {
            StructurizrConstantDirectiveImpl.$$$reportNull$$$0(4);
        }
        return psiElement;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[switch (n) {
            default -> 3;
            case 3, 4 -> 2;
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
            case 4: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrConstantDirectiveImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrConstantDirectiveImpl";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getValueLiteral";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getIdentifier";
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
            case 4: {
                break;
            }
        }
        String string = String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray);
        throw switch (n) {
            default -> new IllegalArgumentException(string);
            case 3, 4 -> new IllegalStateException(string);
        };
    }
}
