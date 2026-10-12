/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.psi.StructurizrElementType;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;

/**
 * Error recovery for {@link StructurizrParser}.
 * <p>
 * Structurizr DSL is line oriented: one statement per line, optionally followed by a {@code { }} block.
 * The generated grammar does not know every statement Structurizr accepts ({@code name}/{@code description}
 * on the workspace, {@code x = deploymentEnvironment}, text blocks, newer keywords, ...). Previously the
 * first unknown statement ended the enclosing block, and because the recovery stopped at the next
 * {@code }}, the parse of the entire rest of the file collapsed — the {@code views} block vanished from
 * the Structure view, along with highlighting, navigation and inspections below that point.
 * <p>
 * Every block-content loop now falls back to {@link #unknownStatement}: it skips one statement (the rest
 * of the line, plus a balanced block if the line opens one) and lets the loop carry on with the next
 * statement, so one construct the grammar does not model costs only that construct. The skipped statement
 * becomes an {@link #UNKNOWN_STATEMENT} node, which the annotator marks with a weak warning, so a typo such
 * as {@code softwareSytem "X"} is still visible instead of silently disappearing.
 */
public final class StructurizrParserRecovery {

    /** A statement the editor grammar does not model; skipped, but kept in the tree and flagged. */
    public static final IElementType UNKNOWN_STATEMENT = new StructurizrElementType("UNKNOWN_STATEMENT");

    private StructurizrParserRecovery() {
    }

    /**
     * Consumes one statement the grammar does not recognise. Never consumes the {@code }} that closes the
     * enclosing block, and returns {@code false} at end of file or at that brace so the loop terminates.
     */
    public static boolean unknownStatement(PsiBuilder builder, int level) {
        IElementType first = builder.getTokenType();
        if (first == null || first == StructurizrTypes.RBRACE) return false;
        PsiBuilder.Marker marker = builder.mark();
        int depth = 0;
        while (!builder.eof()) {
            IElementType type = builder.getTokenType();
            if (type == StructurizrTypes.RBRACE) {
                if (depth == 0) break;
                depth--;
                builder.advanceLexer();
                if (depth == 0) break;
                continue;
            }
            if (type == StructurizrTypes.LBRACE) {
                depth++;
                builder.advanceLexer();
                continue;
            }
            String text = builder.getTokenText();
            int tokenEnd = builder.getCurrentOffset() + (text == null ? 0 : text.length());
            builder.advanceLexer();
            if (depth == 0 && !builder.eof() && lineEndsBetween(builder.getOriginalText(), tokenEnd, builder.getCurrentOffset())) {
                break;
            }
        }
        // Not an error element: the statement may well be valid Structurizr the grammar simply does not model.
        marker.done(UNKNOWN_STATEMENT);
        return true;
    }

    /**
     * Consumes the {@code {} that opens a view's body. View headers take more optional arguments in
     * Structurizr than the grammar models (e.g. {@code custom <key> <title> <description>}); any extra
     * tokens before the brace on the same line are skipped rather than derailing the rest of the file.
     * Returns {@code false} without consuming anything when the line has no opening brace.
     */
    public static boolean openBlock(PsiBuilder builder) {
        if (builder.getTokenType() == StructurizrTypes.LBRACE) {
            builder.advanceLexer();
            return true;
        }
        PsiBuilder.Marker probe = builder.mark();
        while (!builder.eof()) {
            IElementType type = builder.getTokenType();
            if (type == StructurizrTypes.LBRACE) {
                probe.drop();
                builder.advanceLexer();
                return true;
            }
            if (type == StructurizrTypes.RBRACE) break;
            String text = builder.getTokenText();
            int tokenEnd = builder.getCurrentOffset() + (text == null ? 0 : text.length());
            builder.advanceLexer();
            if (!builder.eof() && lineEndsBetween(builder.getOriginalText(), tokenEnd, builder.getCurrentOffset())) break;
        }
        probe.rollbackTo();
        return false;
    }

    private static boolean lineEndsBetween(CharSequence text, int from, int to) {
        for (int i = Math.max(0, from); i < Math.min(to, text.length()); i++) {
            if (text.charAt(i) == '\n') return true;
        }
        return false;
    }
}
