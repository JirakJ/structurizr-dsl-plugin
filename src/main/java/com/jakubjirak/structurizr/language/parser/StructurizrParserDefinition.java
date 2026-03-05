/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.ASTNode
 *  com.intellij.lang.Language
 *  com.intellij.lang.ParserDefinition
 *  com.intellij.lang.PsiParser
 *  com.intellij.lexer.Lexer
 *  com.intellij.openapi.project.Project
 *  com.intellij.psi.FileViewProvider
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.PsiFile
 *  com.intellij.psi.tree.IElementType
 *  com.intellij.psi.tree.IFileElementType
 *  com.intellij.psi.tree.TokenSet
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.jakubjirak.structurizr.language.StructurizrLanguage;
import com.jakubjirak.structurizr.language.lexer.StructurizrLexerAdapter;
import com.jakubjirak.structurizr.language.parser.StructurizrParser;
import com.jakubjirak.structurizr.language.psi.StructurizrFile;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class StructurizrParserDefinition
implements ParserDefinition {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final IFileElementType FILE = new IFileElementType((Language)StructurizrLanguage.INSTANCE);
    @NotNull
    private static final TokenSet COMMENTS;
    @NotNull
    private static final TokenSet STRINGS;
    @NotNull
    private static final TokenSet KEYWORDS;
    @NotNull
    private static final TokenSet DIRECTIVES;
    @NotNull
    private static final TokenSet STYLE_PROPERTIES;
    @NotNull
    private static final TokenSet SHAPES;
    @NotNull
    private static final TokenSet OPERATORS;
    @NotNull
    private static final TokenSet BRACES;

    @NotNull
    public Lexer createLexer(@Nullable Project project) {
        return (Lexer)new StructurizrLexerAdapter();
    }

    @NotNull
    public PsiParser createParser(@Nullable Project project) {
        return new StructurizrParser();
    }

    @NotNull
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return STRINGS;
    }

    @NotNull
    public PsiElement createElement(@NotNull ASTNode node) {
        PsiElement psiElement = StructurizrTypes.Factory.createElement(node);
        return psiElement;
    }

    @NotNull
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return (PsiFile)new StructurizrFile(viewProvider);
    }

    static {
        IElementType[] iElementTypeArray = new IElementType[]{StructurizrTypes.LINE_COMMENT, StructurizrTypes.BLOCK_COMMENT};
        TokenSet tokenSet = TokenSet.create((IElementType[])iElementTypeArray);
        COMMENTS = tokenSet;
        iElementTypeArray = new IElementType[]{StructurizrTypes.STRING};
        TokenSet tokenSet2 = TokenSet.create((IElementType[])iElementTypeArray);
        STRINGS = tokenSet2;
        iElementTypeArray = new IElementType[]{StructurizrTypes.WORKSPACE, StructurizrTypes.MODEL, StructurizrTypes.VIEWS, StructurizrTypes.CONFIGURATION, StructurizrTypes.PERSON, StructurizrTypes.SOFTWARE_SYSTEM, StructurizrTypes.CONTAINER, StructurizrTypes.COMPONENT, StructurizrTypes.GROUP, StructurizrTypes.ENTERPRISE, StructurizrTypes.DEPLOYMENT_ENVIRONMENT, StructurizrTypes.DEPLOYMENT_NODE, StructurizrTypes.INFRASTRUCTURE_NODE, StructurizrTypes.CONTAINER_INSTANCE, StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE, StructurizrTypes.SYSTEM_LANDSCAPE, StructurizrTypes.SYSTEM_CONTEXT, StructurizrTypes.DYNAMIC, StructurizrTypes.DEPLOYMENT, StructurizrTypes.FILTERED, StructurizrTypes.CUSTOM, StructurizrTypes.IMAGE, StructurizrTypes.STYLES, StructurizrTypes.THEMES, StructurizrTypes.THEME, StructurizrTypes.ELEMENT, StructurizrTypes.RELATIONSHIP, StructurizrTypes.INCLUDE, StructurizrTypes.EXCLUDE, StructurizrTypes.AUTO_LAYOUT, StructurizrTypes.DEFAULT, StructurizrTypes.ANIMATION, StructurizrTypes.TITLE, StructurizrTypes.DESCRIPTION, StructurizrTypes.TAGS, StructurizrTypes.URL, StructurizrTypes.PROPERTIES, StructurizrTypes.PERSPECTIVES, StructurizrTypes.BRANDING, StructurizrTypes.TERMINOLOGY, StructurizrTypes.USERS, StructurizrTypes.REF, StructurizrTypes.THIS, StructurizrTypes.EXTENDS};
        TokenSet tokenSet3 = TokenSet.create((IElementType[])iElementTypeArray);
        KEYWORDS = tokenSet3;
        iElementTypeArray = new IElementType[]{StructurizrTypes.DIRECTIVE_INCLUDE, StructurizrTypes.DIRECTIVE_DOCS, StructurizrTypes.DIRECTIVE_ADRS, StructurizrTypes.DIRECTIVE_IDENTIFIERS, StructurizrTypes.DIRECTIVE_IMPLIED_RELATIONSHIPS, StructurizrTypes.DIRECTIVE_CONSTANT, StructurizrTypes.DIRECTIVE_CONST, StructurizrTypes.DIRECTIVE_VAR, StructurizrTypes.DIRECTIVE_PLUGIN, StructurizrTypes.DIRECTIVE_SCRIPT};
        TokenSet tokenSet4 = TokenSet.create((IElementType[])iElementTypeArray);
        DIRECTIVES = tokenSet4;
        iElementTypeArray = new IElementType[]{StructurizrTypes.SHAPE, StructurizrTypes.ICON, StructurizrTypes.WIDTH, StructurizrTypes.HEIGHT, StructurizrTypes.BACKGROUND, StructurizrTypes.COLOR, StructurizrTypes.COLOUR, StructurizrTypes.STROKE, StructurizrTypes.STROKE_WIDTH, StructurizrTypes.FONT_SIZE, StructurizrTypes.BORDER, StructurizrTypes.OPACITY, StructurizrTypes.METADATA, StructurizrTypes.THICKNESS, StructurizrTypes.STYLE_PROP, StructurizrTypes.ROUTING, StructurizrTypes.POSITION, StructurizrTypes.DASHED};
        TokenSet tokenSet5 = TokenSet.create((IElementType[])iElementTypeArray);
        STYLE_PROPERTIES = tokenSet5;
        iElementTypeArray = new IElementType[]{StructurizrTypes.SHAPE_BOX, StructurizrTypes.SHAPE_ROUNDED_BOX, StructurizrTypes.SHAPE_CIRCLE, StructurizrTypes.SHAPE_ELLIPSE, StructurizrTypes.SHAPE_HEXAGON, StructurizrTypes.SHAPE_CYLINDER, StructurizrTypes.SHAPE_PIPE, StructurizrTypes.SHAPE_PERSON, StructurizrTypes.SHAPE_ROBOT, StructurizrTypes.SHAPE_FOLDER, StructurizrTypes.SHAPE_WEB_BROWSER, StructurizrTypes.SHAPE_MOBILE_PORTRAIT, StructurizrTypes.SHAPE_MOBILE_LANDSCAPE, StructurizrTypes.SHAPE_COMPONENT};
        TokenSet tokenSet6 = TokenSet.create((IElementType[])iElementTypeArray);
        SHAPES = tokenSet6;
        iElementTypeArray = new IElementType[]{StructurizrTypes.ARROW, StructurizrTypes.ARROW_LEFT, StructurizrTypes.ARROW_BOTH, StructurizrTypes.EQUALS};
        TokenSet tokenSet7 = TokenSet.create((IElementType[])iElementTypeArray);
        OPERATORS = tokenSet7;
        iElementTypeArray = new IElementType[]{StructurizrTypes.LBRACE, StructurizrTypes.RBRACE};
        TokenSet tokenSet8 = TokenSet.create((IElementType[])iElementTypeArray);
        BRACES = tokenSet8;
    }

    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final IFileElementType getFILE() {
            return FILE;
        }

        @NotNull
        public final TokenSet getCOMMENTS() {
            return COMMENTS;
        }

        @NotNull
        public final TokenSet getSTRINGS() {
            return STRINGS;
        }

        @NotNull
        public final TokenSet getKEYWORDS() {
            return KEYWORDS;
        }

        @NotNull
        public final TokenSet getDIRECTIVES() {
            return DIRECTIVES;
        }

        @NotNull
        public final TokenSet getSTYLE_PROPERTIES() {
            return STYLE_PROPERTIES;
        }

        @NotNull
        public final TokenSet getSHAPES() {
            return SHAPES;
        }

        @NotNull
        public final TokenSet getOPERATORS() {
            return OPERATORS;
        }

        @NotNull
        public final TokenSet getBRACES() {
            return BRACES;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
