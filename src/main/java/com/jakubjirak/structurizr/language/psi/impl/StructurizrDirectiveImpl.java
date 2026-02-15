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
import com.jakubjirak.structurizr.language.psi.StructurizrAdrsDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrConstantDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrDocsDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrIdentifiersDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrImpliedRelationshipsDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrIncludeDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructurizrDirectiveImpl
extends ASTWrapperPsiElement
implements StructurizrDirective {
    public StructurizrDirectiveImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull StructurizrVisitor visitor) {
        if (visitor == null) {
            StructurizrDirectiveImpl.$$$reportNull$$$0(1);
        }
        visitor.visitDirective(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            StructurizrDirectiveImpl.$$$reportNull$$$0(2);
        }
        if (visitor instanceof StructurizrVisitor) {
            this.accept((StructurizrVisitor)visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Override
    @Nullable
    public StructurizrAdrsDirective getAdrsDirective() {
        return (StructurizrAdrsDirective)this.findChildByClass(StructurizrAdrsDirective.class);
    }

    @Override
    @Nullable
    public StructurizrConstantDirective getConstantDirective() {
        return (StructurizrConstantDirective)this.findChildByClass(StructurizrConstantDirective.class);
    }

    @Override
    @Nullable
    public StructurizrDocsDirective getDocsDirective() {
        return (StructurizrDocsDirective)this.findChildByClass(StructurizrDocsDirective.class);
    }

    @Override
    @Nullable
    public StructurizrIdentifiersDirective getIdentifiersDirective() {
        return (StructurizrIdentifiersDirective)this.findChildByClass(StructurizrIdentifiersDirective.class);
    }

    @Override
    @Nullable
    public StructurizrImpliedRelationshipsDirective getImpliedRelationshipsDirective() {
        return (StructurizrImpliedRelationshipsDirective)this.findChildByClass(StructurizrImpliedRelationshipsDirective.class);
    }

    @Override
    @Nullable
    public StructurizrIncludeDirective getIncludeDirective() {
        return (StructurizrIncludeDirective)this.findChildByClass(StructurizrIncludeDirective.class);
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
        objectArray2[1] = "com/jakubjirak/structurizr/language/psi/impl/StructurizrDirectiveImpl";
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
