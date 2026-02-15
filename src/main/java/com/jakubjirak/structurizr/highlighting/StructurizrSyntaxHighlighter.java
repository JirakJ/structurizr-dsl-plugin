/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lexer.Lexer
 *  com.intellij.openapi.editor.DefaultLanguageHighlighterColors
 *  com.intellij.openapi.editor.HighlighterColors
 *  com.intellij.openapi.editor.colors.TextAttributesKey
 *  com.intellij.openapi.fileTypes.SyntaxHighlighterBase
 *  com.intellij.psi.tree.IElementType
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.lexer.StructurizrLexerAdapter;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u001b\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\r"}, d2={"Lcom/jakubjirak/structurizr/highlighting/StructurizrSyntaxHighlighter;", "Lcom/intellij/openapi/fileTypes/SyntaxHighlighterBase;", "<init>", "()V", "getHighlightingLexer", "Lcom/intellij/lexer/Lexer;", "getTokenHighlights", "", "Lcom/intellij/openapi/editor/colors/TextAttributesKey;", "tokenType", "Lcom/intellij/psi/tree/IElementType;", "(Lcom/intellij/psi/tree/IElementType;)[Lcom/intellij/openapi/editor/colors/TextAttributesKey;", "Companion", "structurizr-dsl-plugin"})
public final class StructurizrSyntaxHighlighter
extends SyntaxHighlighterBase {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final TextAttributesKey KEYWORD;
    @NotNull
    private static final TextAttributesKey ELEMENT_KEYWORD;
    @NotNull
    private static final TextAttributesKey DEPLOYMENT_KEYWORD;
    @NotNull
    private static final TextAttributesKey VIEW_KEYWORD;
    @NotNull
    private static final TextAttributesKey VIEW_CONTENT_KEYWORD;
    @NotNull
    private static final TextAttributesKey STYLE_KEYWORD;
    @NotNull
    private static final TextAttributesKey PROPERTY;
    @NotNull
    private static final TextAttributesKey DIRECTIVE;
    @NotNull
    private static final TextAttributesKey CONSTANT;
    @NotNull
    private static final TextAttributesKey STRING;
    @NotNull
    private static final TextAttributesKey NUMBER;
    @NotNull
    private static final TextAttributesKey COLOR;
    @NotNull
    private static final TextAttributesKey IDENTIFIER;
    @NotNull
    private static final TextAttributesKey COMMENT;
    @NotNull
    private static final TextAttributesKey OPERATOR;
    @NotNull
    private static final TextAttributesKey ARROW;
    @NotNull
    private static final TextAttributesKey BRACES;
    @NotNull
    private static final TextAttributesKey STAR;
    @NotNull
    private static final TextAttributesKey BAD_CHARACTER;
    @NotNull
    private static final TextAttributesKey[] KEYWORD_KEYS;
    @NotNull
    private static final TextAttributesKey[] ELEMENT_KEYWORD_KEYS;
    @NotNull
    private static final TextAttributesKey[] DEPLOYMENT_KEYWORD_KEYS;
    @NotNull
    private static final TextAttributesKey[] VIEW_KEYWORD_KEYS;
    @NotNull
    private static final TextAttributesKey[] VIEW_CONTENT_KEYWORD_KEYS;
    @NotNull
    private static final TextAttributesKey[] STYLE_KEYWORD_KEYS;
    @NotNull
    private static final TextAttributesKey[] PROPERTY_KEYS;
    @NotNull
    private static final TextAttributesKey[] DIRECTIVE_KEYS;
    @NotNull
    private static final TextAttributesKey[] CONSTANT_KEYS;
    @NotNull
    private static final TextAttributesKey[] STRING_KEYS;
    @NotNull
    private static final TextAttributesKey[] NUMBER_KEYS;
    @NotNull
    private static final TextAttributesKey[] COLOR_KEYS;
    @NotNull
    private static final TextAttributesKey[] IDENTIFIER_KEYS;
    @NotNull
    private static final TextAttributesKey[] COMMENT_KEYS;
    @NotNull
    private static final TextAttributesKey[] OPERATOR_KEYS;
    @NotNull
    private static final TextAttributesKey[] ARROW_KEYS;
    @NotNull
    private static final TextAttributesKey[] BRACES_KEYS;
    @NotNull
    private static final TextAttributesKey[] STAR_KEYS;
    @NotNull
    private static final TextAttributesKey[] BAD_CHARACTER_KEYS;

    @NotNull
    public Lexer getHighlightingLexer() {
        return (Lexer)new StructurizrLexerAdapter();
    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(@NotNull IElementType tokenType) {
        Intrinsics.checkNotNullParameter((Object)tokenType, (String)"tokenType");
        IElementType iElementType = tokenType;
        return Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.WORKSPACE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.MODEL) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.VIEWS) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.CONFIGURATION) ? KEYWORD_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.PERSON) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SOFTWARE_SYSTEM) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.CONTAINER) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.COMPONENT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.GROUP) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.ENTERPRISE) ? ELEMENT_KEYWORD_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DEPLOYMENT_ENVIRONMENT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DEPLOYMENT_GROUP) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DEPLOYMENT_NODE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.INFRASTRUCTURE_NODE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.CONTAINER_INSTANCE) ? DEPLOYMENT_KEYWORD_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SYSTEM_LANDSCAPE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SYSTEM_CONTEXT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DYNAMIC) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DEPLOYMENT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.FILTERED) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.CUSTOM) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.IMAGE) ? VIEW_KEYWORD_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.INCLUDE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.EXCLUDE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.AUTO_LAYOUT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DEFAULT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.ANIMATION) ? VIEW_CONTENT_KEYWORD_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.STYLES) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.ELEMENT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.RELATIONSHIP) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.THEMES) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.THEME) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.BRANDING) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.TERMINOLOGY) ? STYLE_KEYWORD_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.ICON) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.WIDTH) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.HEIGHT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.BACKGROUND) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.COLOR) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.COLOUR) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.STROKE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.STROKE_WIDTH) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.FONT_SIZE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.BORDER) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.OPACITY) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.METADATA) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.THICKNESS) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.STYLE_PROP) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.ROUTING) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.POSITION) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DASHED) ? PROPERTY_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_BOX) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_ROUNDED_BOX) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_CIRCLE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_ELLIPSE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_HEXAGON) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_CYLINDER) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_PIPE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_PERSON) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_ROBOT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_FOLDER) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_WEB_BROWSER) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_MOBILE_PORTRAIT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_MOBILE_LANDSCAPE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.SHAPE_COMPONENT) ? CONSTANT_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.LAYOUT_TB) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.LAYOUT_BT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.LAYOUT_LR) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.LAYOUT_RL) ? CONSTANT_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.TITLE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DESCRIPTION) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.TAGS) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.URL) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.PROPERTIES) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.PERSPECTIVES) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.USERS) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.EXTENDS) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.REF) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.THIS) ? KEYWORD_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DIRECTIVE_INCLUDE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DIRECTIVE_DOCS) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DIRECTIVE_ADRS) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DIRECTIVE_IDENTIFIERS) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DIRECTIVE_IMPLIED_RELATIONSHIPS) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DIRECTIVE_CONSTANT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DIRECTIVE_CONST) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DIRECTIVE_VAR) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DIRECTIVE_PLUGIN) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DIRECTIVE_SCRIPT) ? DIRECTIVE_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.HIERARCHICAL) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.FLAT) ? CONSTANT_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.TRUE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.FALSE) ? CONSTANT_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.READ) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.WRITE) ? KEYWORD_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.PRIVATE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.PUBLIC) ? KEYWORD_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.ARROW) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.ARROW_LEFT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.ARROW_BOTH) ? ARROW_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.EQUALS) ? OPERATOR_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.LBRACE) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.RBRACE) ? BRACES_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.COMMA) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.DOT) ? OPERATOR_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.STAR) ? STAR_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.BANG) ? OPERATOR_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.STRING) ? STRING_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.NUMBER) ? NUMBER_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.COLOR_LITERAL) ? COLOR_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.IDENTIFIER) ? IDENTIFIER_KEYS : (Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.LINE_COMMENT) || Intrinsics.areEqual((Object)iElementType, (Object)StructurizrTypes.BLOCK_COMMENT) ? COMMENT_KEYS : BAD_CHARACTER_KEYS)))))))))))))))))))))))));
    }

    static {
        TextAttributesKey textAttributesKey = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_KEYWORD", (TextAttributesKey)DefaultLanguageHighlighterColors.KEYWORD);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey, (String)"createTextAttributesKey(...)");
        KEYWORD = textAttributesKey;
        TextAttributesKey textAttributesKey2 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_ELEMENT_KEYWORD", (TextAttributesKey)DefaultLanguageHighlighterColors.KEYWORD);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey2, (String)"createTextAttributesKey(...)");
        ELEMENT_KEYWORD = textAttributesKey2;
        TextAttributesKey textAttributesKey3 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_DEPLOYMENT_KEYWORD", (TextAttributesKey)DefaultLanguageHighlighterColors.KEYWORD);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey3, (String)"createTextAttributesKey(...)");
        DEPLOYMENT_KEYWORD = textAttributesKey3;
        TextAttributesKey textAttributesKey4 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_VIEW_KEYWORD", (TextAttributesKey)DefaultLanguageHighlighterColors.KEYWORD);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey4, (String)"createTextAttributesKey(...)");
        VIEW_KEYWORD = textAttributesKey4;
        TextAttributesKey textAttributesKey5 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_VIEW_CONTENT_KEYWORD", (TextAttributesKey)DefaultLanguageHighlighterColors.KEYWORD);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey5, (String)"createTextAttributesKey(...)");
        VIEW_CONTENT_KEYWORD = textAttributesKey5;
        TextAttributesKey textAttributesKey6 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_STYLE_KEYWORD", (TextAttributesKey)DefaultLanguageHighlighterColors.KEYWORD);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey6, (String)"createTextAttributesKey(...)");
        STYLE_KEYWORD = textAttributesKey6;
        TextAttributesKey textAttributesKey7 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_PROPERTY", (TextAttributesKey)DefaultLanguageHighlighterColors.INSTANCE_FIELD);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey7, (String)"createTextAttributesKey(...)");
        PROPERTY = textAttributesKey7;
        TextAttributesKey textAttributesKey8 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_DIRECTIVE", (TextAttributesKey)DefaultLanguageHighlighterColors.METADATA);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey8, (String)"createTextAttributesKey(...)");
        DIRECTIVE = textAttributesKey8;
        TextAttributesKey textAttributesKey9 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_CONSTANT", (TextAttributesKey)DefaultLanguageHighlighterColors.CONSTANT);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey9, (String)"createTextAttributesKey(...)");
        CONSTANT = textAttributesKey9;
        TextAttributesKey textAttributesKey10 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_STRING", (TextAttributesKey)DefaultLanguageHighlighterColors.STRING);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey10, (String)"createTextAttributesKey(...)");
        STRING = textAttributesKey10;
        TextAttributesKey textAttributesKey11 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_NUMBER", (TextAttributesKey)DefaultLanguageHighlighterColors.NUMBER);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey11, (String)"createTextAttributesKey(...)");
        NUMBER = textAttributesKey11;
        TextAttributesKey textAttributesKey12 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_COLOR", (TextAttributesKey)DefaultLanguageHighlighterColors.NUMBER);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey12, (String)"createTextAttributesKey(...)");
        COLOR = textAttributesKey12;
        TextAttributesKey textAttributesKey13 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_IDENTIFIER", (TextAttributesKey)DefaultLanguageHighlighterColors.IDENTIFIER);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey13, (String)"createTextAttributesKey(...)");
        IDENTIFIER = textAttributesKey13;
        TextAttributesKey textAttributesKey14 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_COMMENT", (TextAttributesKey)DefaultLanguageHighlighterColors.LINE_COMMENT);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey14, (String)"createTextAttributesKey(...)");
        COMMENT = textAttributesKey14;
        TextAttributesKey textAttributesKey15 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_OPERATOR", (TextAttributesKey)DefaultLanguageHighlighterColors.OPERATION_SIGN);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey15, (String)"createTextAttributesKey(...)");
        OPERATOR = textAttributesKey15;
        TextAttributesKey textAttributesKey16 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_ARROW", (TextAttributesKey)DefaultLanguageHighlighterColors.OPERATION_SIGN);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey16, (String)"createTextAttributesKey(...)");
        ARROW = textAttributesKey16;
        TextAttributesKey textAttributesKey17 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_BRACES", (TextAttributesKey)DefaultLanguageHighlighterColors.BRACES);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey17, (String)"createTextAttributesKey(...)");
        BRACES = textAttributesKey17;
        TextAttributesKey textAttributesKey18 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_STAR", (TextAttributesKey)DefaultLanguageHighlighterColors.OPERATION_SIGN);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey18, (String)"createTextAttributesKey(...)");
        STAR = textAttributesKey18;
        TextAttributesKey textAttributesKey19 = TextAttributesKey.createTextAttributesKey((String)"STRUCTURIZR_BAD_CHARACTER", (TextAttributesKey)HighlighterColors.BAD_CHARACTER);
        Intrinsics.checkNotNullExpressionValue((Object)textAttributesKey19, (String)"createTextAttributesKey(...)");
        BAD_CHARACTER = textAttributesKey19;
        TextAttributesKey[] textAttributesKeyArray = new TextAttributesKey[]{KEYWORD};
        KEYWORD_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{ELEMENT_KEYWORD};
        ELEMENT_KEYWORD_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{DEPLOYMENT_KEYWORD};
        DEPLOYMENT_KEYWORD_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{VIEW_KEYWORD};
        VIEW_KEYWORD_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{VIEW_CONTENT_KEYWORD};
        VIEW_CONTENT_KEYWORD_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{STYLE_KEYWORD};
        STYLE_KEYWORD_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{PROPERTY};
        PROPERTY_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{DIRECTIVE};
        DIRECTIVE_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{CONSTANT};
        CONSTANT_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{STRING};
        STRING_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{NUMBER};
        NUMBER_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{COLOR};
        COLOR_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{IDENTIFIER};
        IDENTIFIER_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{COMMENT};
        COMMENT_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{OPERATOR};
        OPERATOR_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{ARROW};
        ARROW_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{BRACES};
        BRACES_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{STAR};
        STAR_KEYS = textAttributesKeyArray;
        textAttributesKeyArray = new TextAttributesKey[]{BAD_CHARACTER};
        BAD_CHARACTER_KEYS = textAttributesKeyArray;
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u0011\n\u0002\b\u0014\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0016\u0010\u000b\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0016\u0010\r\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0016\u0010\u000f\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0016\u0010\u0011\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0016\u0010\u0013\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0016\u0010\u0015\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0016\u0010\u0017\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0016\u0010\u0019\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u0016\u0010\u001b\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\bR\u0016\u0010\u001d\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\bR\u0016\u0010\u001f\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u0016\u0010!\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\bR\u0016\u0010#\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\bR\u0016\u0010%\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\bR\u0016\u0010'\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\bR\u0016\u0010)\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\bR\u0016\u0010+\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\bR\u001b\u0010-\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u00100\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u00101\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u00102\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u00103\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u00104\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u00105\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u00106\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u00107\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u00108\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u00109\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u0010:\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u0010;\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u0010<\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u0010=\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u0010>\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u0010?\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u0010@\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u001b\u0010A\u001a\r\u0012\t\u0012\u00070\u0005\u00a2\u0006\u0002\b\u00060.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/\u00a8\u0006B"}, d2={"Lcom/jakubjirak/structurizr/highlighting/StructurizrSyntaxHighlighter$Companion;", "", "<init>", "()V", "KEYWORD", "Lcom/intellij/openapi/editor/colors/TextAttributesKey;", "Lorg/jetbrains/annotations/NotNull;", "getKEYWORD", "()Lcom/intellij/openapi/editor/colors/TextAttributesKey;", "ELEMENT_KEYWORD", "getELEMENT_KEYWORD", "DEPLOYMENT_KEYWORD", "getDEPLOYMENT_KEYWORD", "VIEW_KEYWORD", "getVIEW_KEYWORD", "VIEW_CONTENT_KEYWORD", "getVIEW_CONTENT_KEYWORD", "STYLE_KEYWORD", "getSTYLE_KEYWORD", "PROPERTY", "getPROPERTY", "DIRECTIVE", "getDIRECTIVE", "CONSTANT", "getCONSTANT", "STRING", "getSTRING", "NUMBER", "getNUMBER", "COLOR", "getCOLOR", "IDENTIFIER", "getIDENTIFIER", "COMMENT", "getCOMMENT", "OPERATOR", "getOPERATOR", "ARROW", "getARROW", "BRACES", "getBRACES", "STAR", "getSTAR", "BAD_CHARACTER", "getBAD_CHARACTER", "KEYWORD_KEYS", "", "[Lcom/intellij/openapi/editor/colors/TextAttributesKey;", "ELEMENT_KEYWORD_KEYS", "DEPLOYMENT_KEYWORD_KEYS", "VIEW_KEYWORD_KEYS", "VIEW_CONTENT_KEYWORD_KEYS", "STYLE_KEYWORD_KEYS", "PROPERTY_KEYS", "DIRECTIVE_KEYS", "CONSTANT_KEYS", "STRING_KEYS", "NUMBER_KEYS", "COLOR_KEYS", "IDENTIFIER_KEYS", "COMMENT_KEYS", "OPERATOR_KEYS", "ARROW_KEYS", "BRACES_KEYS", "STAR_KEYS", "BAD_CHARACTER_KEYS", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final TextAttributesKey getKEYWORD() {
            return KEYWORD;
        }

        @NotNull
        public final TextAttributesKey getELEMENT_KEYWORD() {
            return ELEMENT_KEYWORD;
        }

        @NotNull
        public final TextAttributesKey getDEPLOYMENT_KEYWORD() {
            return DEPLOYMENT_KEYWORD;
        }

        @NotNull
        public final TextAttributesKey getVIEW_KEYWORD() {
            return VIEW_KEYWORD;
        }

        @NotNull
        public final TextAttributesKey getVIEW_CONTENT_KEYWORD() {
            return VIEW_CONTENT_KEYWORD;
        }

        @NotNull
        public final TextAttributesKey getSTYLE_KEYWORD() {
            return STYLE_KEYWORD;
        }

        @NotNull
        public final TextAttributesKey getPROPERTY() {
            return PROPERTY;
        }

        @NotNull
        public final TextAttributesKey getDIRECTIVE() {
            return DIRECTIVE;
        }

        @NotNull
        public final TextAttributesKey getCONSTANT() {
            return CONSTANT;
        }

        @NotNull
        public final TextAttributesKey getSTRING() {
            return STRING;
        }

        @NotNull
        public final TextAttributesKey getNUMBER() {
            return NUMBER;
        }

        @NotNull
        public final TextAttributesKey getCOLOR() {
            return COLOR;
        }

        @NotNull
        public final TextAttributesKey getIDENTIFIER() {
            return IDENTIFIER;
        }

        @NotNull
        public final TextAttributesKey getCOMMENT() {
            return COMMENT;
        }

        @NotNull
        public final TextAttributesKey getOPERATOR() {
            return OPERATOR;
        }

        @NotNull
        public final TextAttributesKey getARROW() {
            return ARROW;
        }

        @NotNull
        public final TextAttributesKey getBRACES() {
            return BRACES;
        }

        @NotNull
        public final TextAttributesKey getSTAR() {
            return STAR;
        }

        @NotNull
        public final TextAttributesKey getBAD_CHARACTER() {
            return BAD_CHARACTER;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
