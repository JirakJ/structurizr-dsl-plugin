/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lexer.FlexAdapter
 *  com.intellij.lexer.FlexLexer
 *  kotlin.Metadata
 */
package com.jakubjirak.structurizr.language.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;
import com.jakubjirak.structurizr.language.lexer.StructurizrLexer;
import kotlin.Metadata;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2={"Lcom/jakubjirak/structurizr/language/lexer/StructurizrLexerAdapter;", "Lcom/intellij/lexer/FlexAdapter;", "<init>", "()V", "structurizr-dsl-plugin"})
public final class StructurizrLexerAdapter
extends FlexAdapter {
    public StructurizrLexerAdapter() {
        super((FlexLexer)new StructurizrLexer(null));
    }
}
