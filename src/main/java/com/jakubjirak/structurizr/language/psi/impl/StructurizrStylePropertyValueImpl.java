/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.extapi.psi.ASTWrapperPsiElement
 *  com.intellij.lang.ASTNode
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.PsiElementVisitor
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.jakubjirak.structurizr.language.psi.StructurizrShapeValue;
import com.jakubjirak.structurizr.language.psi.StructurizrStylePropertyValue;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructurizrStylePropertyValueImpl
extends ASTWrapperPsiElement
implements StructurizrStylePropertyValue {
    public StructurizrStylePropertyValueImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrStylePropertyValueImpl.$$$reportNull$$$0(1);
        }
        visitor.visitStylePropertyValue(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrStylePropertyValueImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @Nullable
    public StructurizrShapeValue getShapeValue() {
        return (StructurizrShapeValue)this.findChildByClass(StructurizrShapeValue.class);
    }

    @Override
    @Nullable
    public PsiElement getColorLiteral() {
        return this.findChildByType(StructurizrTypes.COLOR_LITERAL);
    }

    @Override
    @Nullable
    public PsiElement getIdentifier() {
        return this.findChildByType(StructurizrTypes.IDENTIFIER);
    }

    @Override
    @Nullable
    public PsiElement getNumber() {
        return this.findChildByType(StructurizrTypes.NUMBER);
    }

    @Override
    @Nullable
    public PsiElement getString() {
        return this.findChildByType(StructurizrTypes.STRING);
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
        objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrStylePropertyValueImpl";
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
