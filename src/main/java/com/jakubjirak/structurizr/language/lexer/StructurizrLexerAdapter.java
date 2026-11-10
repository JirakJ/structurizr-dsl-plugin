/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.language.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.psi.StructurizrTokenType;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * Lexer adapter that makes keywords case-insensitive, as they are in Structurizr itself.
 * <p>
 * The generated lexer only recognises the canonical spelling ({@code autoLayout}); Structurizr accepts
 * {@code autolayout}, {@code softwaresystem} and so on. Any other spelling used to lex as an identifier,
 * which broke the parse of the whole rest of the file (and with it the Structure view). A word lexed as an
 * identifier whose lower-case form is a keyword is reported as that keyword only where a keyword starts a
 * statement: first on its line, or right after {@code =}. Anywhere else ({@code include Users},
 * {@code u -> Model}) and when it is itself assigned or the source of a relationship
 * ({@code Users = ...}, {@code Users -> web}) it stays an identifier, so mixed-case identifiers keep working.
 */
public final class StructurizrLexerAdapter extends FlexAdapter {

    private static final Map<String, IElementType> KEYWORDS = buildKeywordMap();

    public StructurizrLexerAdapter() {
        super((FlexLexer) new StructurizrLexer(null));
    }

    @Override
    @Nullable
    public IElementType getTokenType() {
        IElementType type = super.getTokenType();
        if (type != StructurizrTypes.IDENTIFIER) return type;
        CharSequence buffer = getBufferSequence();
        int start = getTokenStart();
        int end = getTokenEnd();
        IElementType keyword = KEYWORDS.get(buffer.subSequence(start, end).toString().toLowerCase(Locale.ROOT));
        if (keyword == null || !startsStatement(buffer, start) || isIdentifierUse(buffer, end)) return type;
        return keyword;
    }

    /** The word is the first on its line, or follows {@code =} / an opening brace. */
    private static boolean startsStatement(CharSequence buffer, int start) {
        int i = start - 1;
        while (i >= 0 && (buffer.charAt(i) == ' ' || buffer.charAt(i) == '\t')) i--;
        if (i < 0) return true;
        char c = buffer.charAt(i);
        return c == '\n' || c == '\r' || c == '=' || c == '{';
    }

    /** {@code word =} or {@code word ->} — the word is an identifier being assigned or used as a source. */
    private static boolean isIdentifierUse(CharSequence buffer, int end) {
        int i = end;
        while (i < buffer.length() && (buffer.charAt(i) == ' ' || buffer.charAt(i) == '\t')) i++;
        if (i >= buffer.length()) return false;
        if (buffer.charAt(i) == '=') return true;
        return i + 1 < buffer.length() && buffer.charAt(i) == '-' && buffer.charAt(i + 1) == '>';
    }

    private static Map<String, IElementType> buildKeywordMap() {
        Map<String, IElementType> map = new HashMap<>();
        for (Field field : StructurizrTypes.class.getFields()) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            Object value;
            try {
                value = field.get(null);
            } catch (IllegalAccessException e) {
                continue;
            }
            if (!(value instanceof StructurizrTokenType)) continue;
            // toString() is "StructurizrTokenType.<keyword>"; getDebugName() is internal API.
            String name = value.toString().substring(value.toString().indexOf('.') + 1);
            if (!name.matches("[A-Za-z][A-Za-z0-9]*") || name.equals("IDENTIFIER") || name.equals(name.toUpperCase(Locale.ROOT))) continue;
            String key = name.toLowerCase(Locale.ROOT);
            IElementType existing = map.get(key);
            // "person" (element keyword) and "Person" (shape) collide; statement keywords are spelled in
            // lower camel case, so prefer those — independent of the unspecified getFields() order.
            if (existing != null && Character.isLowerCase(existing.toString().charAt(existing.toString().indexOf('.') + 1))) continue;
            map.put(key, (IElementType) value);
        }
        return Collections.unmodifiableMap(map);
    }
}
