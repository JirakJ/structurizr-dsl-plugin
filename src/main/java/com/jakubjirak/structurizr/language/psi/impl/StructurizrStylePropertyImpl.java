/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.extapi.psi.ASTWrapperPsiElement
 *  com.intellij.lang.ASTNode
 *  com.intellij.psi.PsiElementVisitor
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.jakubjirak.structurizr.language.psi.StructurizrStyleProperty;
import com.jakubjirak.structurizr.language.psi.StructurizrStylePropertyName;
import com.jakubjirak.structurizr.language.psi.StructurizrStylePropertyValue;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import org.jetbrains.annotations.NotNull;

public class StructurizrStylePropertyImpl
extends ASTWrapperPsiElement
implements StructurizrStyleProperty {
    public StructurizrStylePropertyImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrStylePropertyImpl.$$$reportNull$$$0(1);
        }
        visitor.visitStyleProperty(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrStylePropertyImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @NotNull
    public StructurizrStylePropertyName getStylePropertyName() {
        StructurizrStylePropertyName structurizrStylePropertyName = (StructurizrStylePropertyName)this.findNotNullChildByClass(StructurizrStylePropertyName.class);
        if (structurizrStylePropertyName == null) {
            StructurizrStylePropertyImpl.$$$reportNull$$$0(3);
        }
        return structurizrStylePropertyName;
    }

    @Override
    @NotNull
    public StructurizrStylePropertyValue getStylePropertyValue() {
        StructurizrStylePropertyValue structurizrStylePropertyValue = (StructurizrStylePropertyValue)this.findNotNullChildByClass(StructurizrStylePropertyValue.class);
        if (structurizrStylePropertyValue == null) {
            StructurizrStylePropertyImpl.$$$reportNull$$$0(4);
        }
        return structurizrStylePropertyValue;
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
                objectArray3[0] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrStylePropertyImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrStylePropertyImpl";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getStylePropertyName";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getStylePropertyValue";
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
