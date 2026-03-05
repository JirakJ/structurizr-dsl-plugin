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

public final class StructurizrLexerAdapter
extends FlexAdapter {
    public StructurizrLexerAdapter() {
        super((FlexLexer)new StructurizrLexer(null));
    }
}
