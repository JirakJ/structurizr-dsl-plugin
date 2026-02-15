/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
 *  com.intellij.psi.tree.IElementType
 *  kotlin.Metadata
 */
package com.jakubjirak.structurizr.language;

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import kotlin.Metadata;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2={"Lcom/jakubjirak/structurizr/language/StructurizrQuoteHandler;", "Lcom/intellij/codeInsight/editorActions/SimpleTokenSetQuoteHandler;", "<init>", "()V", "structurizr-dsl-plugin"})
public final class StructurizrQuoteHandler
extends SimpleTokenSetQuoteHandler {
    public StructurizrQuoteHandler() {
        super(new IElementType[]{StructurizrTypes.STRING});
    }
}
