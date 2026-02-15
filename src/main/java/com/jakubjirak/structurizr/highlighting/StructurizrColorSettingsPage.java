/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.editor.colors.TextAttributesKey
 *  com.intellij.openapi.fileTypes.SyntaxHighlighter
 *  com.intellij.openapi.options.colors.AttributesDescriptor
 *  com.intellij.openapi.options.colors.ColorDescriptor
 *  com.intellij.openapi.options.colors.ColorSettingsPage
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.jakubjirak.structurizr.highlighting.StructurizrSyntaxHighlighter;
import com.jakubjirak.structurizr.language.StructurizrIcons;
import java.util.Map;
import javax.swing.Icon;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bH\u0016J\u0013\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000eH\u0016\u00a2\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\tH\u0016\u00a8\u0006\u0016"}, d2={"Lcom/jakubjirak/structurizr/highlighting/StructurizrColorSettingsPage;", "Lcom/intellij/openapi/options/colors/ColorSettingsPage;", "<init>", "()V", "getIcon", "Ljavax/swing/Icon;", "getHighlighter", "Lcom/intellij/openapi/fileTypes/SyntaxHighlighter;", "getDemoText", "", "getAdditionalHighlightingTagToDescriptorMap", "", "Lcom/intellij/openapi/editor/colors/TextAttributesKey;", "getAttributeDescriptors", "", "Lcom/intellij/openapi/options/colors/AttributesDescriptor;", "()[Lcom/intellij/openapi/options/colors/AttributesDescriptor;", "getColorDescriptors", "Lcom/intellij/openapi/options/colors/ColorDescriptor;", "()[Lcom/intellij/openapi/options/colors/ColorDescriptor;", "getDisplayName", "Companion", "structurizr-dsl-plugin"})
public final class StructurizrColorSettingsPage
implements ColorSettingsPage {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final AttributesDescriptor[] DESCRIPTORS;

    @NotNull
    public Icon getIcon() {
        return StructurizrIcons.FILE;
    }

    @NotNull
    public SyntaxHighlighter getHighlighter() {
        return (SyntaxHighlighter)new StructurizrSyntaxHighlighter();
    }

    @NotNull
    public String getDemoText() {
        return "// Structurizr DSL example\n/* This is a block comment */\n\n!const SYSTEM_NAME \"My System\"\n!identifiers hierarchical\n\nworkspace \"Big Bank plc\" \"Internet Banking System\" {\n    \n    model {\n        enterprise \"Big Bank plc\" {\n            user = person \"Personal Banking Customer\" \"A customer of the bank\" \"Customer\"\n            \n            softwareSystem = softwareSystem \"Internet Banking System\" \"Allows customers to manage accounts\" {\n                webApp = container \"Web Application\" \"Delivers static content and SPA\" \"Java/Spring Boot\" \"Web Browser\"\n                apiApp = container \"API Application\" \"Provides banking functionality via REST API\" \"Java/Spring Boot\"\n                database = container \"Database\" \"Stores user information\" \"PostgreSQL\" \"Database\"\n                \n                webApp -> apiApp \"Makes API calls\" \"HTTPS/JSON\"\n                apiApp -> database \"Reads from and writes to\" \"JDBC\"\n            }\n        }\n        \n        user -> softwareSystem \"Views account balances\"\n    }\n    \n    views {\n        systemContext softwareSystem \"SystemContext\" \"System Context diagram\" {\n            include *\n            autoLayout lr 300 200\n        }\n        \n        container softwareSystem \"Containers\" {\n            include *\n            autoLayout\n        }\n        \n        styles {\n            element \"Person\" {\n                shape Person\n                background #08427B\n                color #ffffff\n                fontSize 22\n            }\n            \n            element \"Software System\" {\n                background #1168BD\n                color #ffffff\n            }\n            \n            element \"Container\" {\n                background #438DD5\n                color #ffffff\n            }\n            \n            element \"Database\" {\n                shape Cylinder\n            }\n            \n            relationship \"Relationship\" {\n                thickness 2\n                dashed false\n            }\n        }\n        \n        themes https://static.structurizr.com/themes/default/theme.json\n    }\n    \n    configuration {\n        users {\n            \"user@example.com\" read\n        }\n    }\n}";
    }

    @Nullable
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    public ColorDescriptor[] getColorDescriptors() {
        Intrinsics.checkNotNullExpressionValue((Object)ColorDescriptor.EMPTY_ARRAY, (String)"EMPTY_ARRAY");
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    public String getDisplayName() {
        return "Structurizr DSL";
    }

    static {
        AttributesDescriptor[] attributesDescriptorArray = new AttributesDescriptor[]{new AttributesDescriptor("Keywords//Core", StructurizrSyntaxHighlighter.Companion.getKEYWORD()), new AttributesDescriptor("Keywords//Element", StructurizrSyntaxHighlighter.Companion.getELEMENT_KEYWORD()), new AttributesDescriptor("Keywords//Deployment", StructurizrSyntaxHighlighter.Companion.getDEPLOYMENT_KEYWORD()), new AttributesDescriptor("Keywords//View", StructurizrSyntaxHighlighter.Companion.getVIEW_KEYWORD()), new AttributesDescriptor("Keywords//View content", StructurizrSyntaxHighlighter.Companion.getVIEW_CONTENT_KEYWORD()), new AttributesDescriptor("Keywords//Style", StructurizrSyntaxHighlighter.Companion.getSTYLE_KEYWORD()), new AttributesDescriptor("Properties", StructurizrSyntaxHighlighter.Companion.getPROPERTY()), new AttributesDescriptor("Directives", StructurizrSyntaxHighlighter.Companion.getDIRECTIVE()), new AttributesDescriptor("Constants", StructurizrSyntaxHighlighter.Companion.getCONSTANT()), new AttributesDescriptor("String", StructurizrSyntaxHighlighter.Companion.getSTRING()), new AttributesDescriptor("Number", StructurizrSyntaxHighlighter.Companion.getNUMBER()), new AttributesDescriptor("Color literal", StructurizrSyntaxHighlighter.Companion.getCOLOR()), new AttributesDescriptor("Identifier", StructurizrSyntaxHighlighter.Companion.getIDENTIFIER()), new AttributesDescriptor("Comment", StructurizrSyntaxHighlighter.Companion.getCOMMENT()), new AttributesDescriptor("Operator", StructurizrSyntaxHighlighter.Companion.getOPERATOR()), new AttributesDescriptor("Arrow (->)", StructurizrSyntaxHighlighter.Companion.getARROW()), new AttributesDescriptor("Braces", StructurizrSyntaxHighlighter.Companion.getBRACES()), new AttributesDescriptor("Star (*)", StructurizrSyntaxHighlighter.Companion.getSTAR()), new AttributesDescriptor("Bad character", StructurizrSyntaxHighlighter.Companion.getBAD_CHARACTER())};
        DESCRIPTORS = attributesDescriptorArray;
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007\u00a8\u0006\b"}, d2={"Lcom/jakubjirak/structurizr/highlighting/StructurizrColorSettingsPage$Companion;", "", "<init>", "()V", "DESCRIPTORS", "", "Lcom/intellij/openapi/options/colors/AttributesDescriptor;", "[Lcom/intellij/openapi/options/colors/AttributesDescriptor;", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
