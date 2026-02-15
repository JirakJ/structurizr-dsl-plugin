/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInsight.completion.CompletionParameters
 *  com.intellij.codeInsight.completion.CompletionProvider
 *  com.intellij.codeInsight.completion.CompletionResultSet
 *  com.intellij.codeInsight.completion.InsertionContext
 *  com.intellij.codeInsight.lookup.LookupElement
 *  com.intellij.codeInsight.lookup.LookupElementBuilder
 *  com.intellij.psi.PsiElement
 *  com.intellij.util.ProcessingContext
 *  kotlin.Metadata
 *  kotlin.Pair
 *  kotlin.TuplesKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.MapsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.jakubjirak.structurizr.language.StructurizrIcons;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0011B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002\u00a8\u0006\u0012"}, d2={"Lcom/jakubjirak/structurizr/completion/StructurizrCompletionProvider;", "Lcom/intellij/codeInsight/completion/CompletionProvider;", "Lcom/intellij/codeInsight/completion/CompletionParameters;", "<init>", "()V", "addCompletions", "", "parameters", "context", "Lcom/intellij/util/ProcessingContext;", "result", "Lcom/intellij/codeInsight/completion/CompletionResultSet;", "getIconForElement", "Ljavax/swing/Icon;", "keyword", "", "addLiveTemplates", "Companion", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrCompletionContributor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrCompletionContributor.kt\ncom/jakubjirak/structurizr/completion/StructurizrCompletionProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,320:1\n1855#2,2:321\n1855#2,2:325\n1855#2,2:327\n1855#2,2:329\n1855#2,2:331\n1855#2,2:333\n1855#2,2:335\n1855#2,2:337\n215#3,2:323\n*S KotlinDebug\n*F\n+ 1 StructurizrCompletionContributor.kt\ncom/jakubjirak/structurizr/completion/StructurizrCompletionProvider\n*L\n40#1:321,2\n60#1:325,2\n69#1:327,2\n78#1:329,2\n87#1:331,2\n95#1:333,2\n103#1:335,2\n111#1:337,2\n50#1:323,2\n*E\n"})
public final class StructurizrCompletionProvider
extends CompletionProvider<CompletionParameters> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final List<String> CORE_KEYWORDS;
    @NotNull
    private static final Map<String, String> MODEL_ELEMENTS;
    @NotNull
    private static final List<String> DEPLOYMENT_KEYWORDS;
    @NotNull
    private static final List<String> VIEW_KEYWORDS;
    @NotNull
    private static final List<String> STYLE_KEYWORDS;
    @NotNull
    private static final List<String> STYLE_PROPERTIES;
    @NotNull
    private static final List<String> SHAPES;
    @NotNull
    private static final List<String> LAYOUT_DIRECTIONS;
    @NotNull
    private static final List<String> DIRECTIVES;
    @NotNull
    private static final List<String> OTHER_KEYWORDS;

    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
        boolean bl;
        Object keyword;
        Intrinsics.checkNotNullParameter((Object)parameters, (String)"parameters");
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        Intrinsics.checkNotNullParameter((Object)result, (String)"result");
        PsiElement psiElement = parameters.getPosition();
        Intrinsics.checkNotNullExpressionValue((Object)psiElement, (String)"getPosition(...)");
        PsiElement position = psiElement;
        String string = result.getPrefixMatcher().getPrefix();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getPrefix(...)");
        String prefix = string;
        Iterable $this$forEach$iv = (Iterable)CORE_KEYWORDS;
        boolean $i$f$forEach = false;
        Iterator iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            Object t = iterator.next();
            keyword = (String)t;
            bl = false;
            result.addElement((LookupElement)LookupElementBuilder.create((String)keyword).withIcon(StructurizrIcons.WORKSPACE).withTypeText("keyword").withBoldness(true));
        }
        Map $this$forEach$iv2 = MODEL_ELEMENTS;
        $i$f$forEach = false;
        for (Object entryObj : $this$forEach$iv2.entrySet()) {
            Map.Entry entry = (Map.Entry)entryObj;
            bl = false;
            String keyword2 = (String)entry.getKey();
            String description = (String)entry.getValue();
            result.addElement((LookupElement)LookupElementBuilder.create((String)keyword2).withIcon(this.getIconForElement(keyword2)).withTypeText(description).withBoldness(true));
        }
        $this$forEach$iv = (Iterable)DEPLOYMENT_KEYWORDS;
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            keyword = (String)object;
            bl = false;
            result.addElement((LookupElement)LookupElementBuilder.create((String)keyword).withIcon(StructurizrIcons.DEPLOYMENT).withTypeText("deployment"));
        }
        $this$forEach$iv = (Iterable)VIEW_KEYWORDS;
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            keyword = (String)object;
            bl = false;
            result.addElement((LookupElement)LookupElementBuilder.create((String)keyword).withIcon(StructurizrIcons.VIEWS).withTypeText("view"));
        }
        $this$forEach$iv = (Iterable)STYLE_KEYWORDS;
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            keyword = (String)object;
            bl = false;
            result.addElement((LookupElement)LookupElementBuilder.create((String)keyword).withIcon(StructurizrIcons.STYLES).withTypeText("style"));
        }
        $this$forEach$iv = (Iterable)STYLE_PROPERTIES;
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            String property = (String)object;
            bl = false;
            result.addElement((LookupElement)LookupElementBuilder.create((String)property).withTypeText("property"));
        }
        $this$forEach$iv = (Iterable)SHAPES;
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            String shape = (String)object;
            bl = false;
            result.addElement((LookupElement)LookupElementBuilder.create((String)shape).withTypeText("shape"));
        }
        $this$forEach$iv = (Iterable)LAYOUT_DIRECTIONS;
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            String direction = (String)object;
            bl = false;
            result.addElement((LookupElement)LookupElementBuilder.create((String)direction).withTypeText("layout direction"));
        }
        $this$forEach$iv = (Iterable)DIRECTIVES;
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            String directive = (String)object;
            bl = false;
            result.addElement((LookupElement)LookupElementBuilder.create((String)directive).withTypeText("directive").withBoldness(true));
        }
        this.addLiveTemplates(result);
    }

    private final Icon getIconForElement(String keyword) {
        return switch (keyword) {
            case "person" -> StructurizrIcons.PERSON;
            case "softwareSystem" -> StructurizrIcons.SOFTWARE_SYSTEM;
            case "container" -> StructurizrIcons.CONTAINER;
            case "component" -> StructurizrIcons.COMPONENT;
            case "model" -> StructurizrIcons.MODEL;
            case "views" -> StructurizrIcons.VIEWS;
            default -> StructurizrIcons.WORKSPACE;
        };
    }

    private final void addLiveTemplates(CompletionResultSet result) {
        result.addElement((LookupElement)LookupElementBuilder.create((String)"workspace_template").withPresentableText("workspace { ... }").withInsertHandler(StructurizrCompletionProvider::addLiveTemplates$lambda$9).withTypeText("template"));
        result.addElement((LookupElement)LookupElementBuilder.create((String)"person_template").withPresentableText("person \"Name\" \"Description\"").withInsertHandler(StructurizrCompletionProvider::addLiveTemplates$lambda$10).withTypeText("template"));
        result.addElement((LookupElement)LookupElementBuilder.create((String)"softwareSystem_template").withPresentableText("softwareSystem \"Name\" { ... }").withInsertHandler(StructurizrCompletionProvider::addLiveTemplates$lambda$11).withTypeText("template"));
    }

    private static final void addLiveTemplates$lambda$9(InsertionContext context, LookupElement lookupElement) {
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        Intrinsics.checkNotNullParameter((Object)lookupElement, (String)"<unused var>");
        String template = "workspace \"Name\" \"Description\" {\n    \n    model {\n        $END$\n    }\n    \n    views {\n        \n    }\n}";
        context.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), (CharSequence)StringsKt.replace((String)template, (String)"$END$", (String)"", (boolean)false));
    }

    private static final void addLiveTemplates$lambda$10(InsertionContext context, LookupElement lookupElement) {
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        Intrinsics.checkNotNullParameter((Object)lookupElement, (String)"<unused var>");
        String template = "person \"Name\" \"Description\"";
        context.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), (CharSequence)template);
    }

    private static final void addLiveTemplates$lambda$11(InsertionContext context, LookupElement lookupElement) {
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        Intrinsics.checkNotNullParameter((Object)lookupElement, (String)"<unused var>");
        String template = "softwareSystem \"Name\" \"Description\" {\n    \n}";
        context.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), (CharSequence)template);
    }

    static {
        CORE_KEYWORDS = java.util.Arrays.asList("workspace", "model", "views", "configuration");
        Pair[] pairArray = new Pair[]{TuplesKt.to((Object)"person", (Object)"model element"), TuplesKt.to((Object)"softwareSystem", (Object)"model element"), TuplesKt.to((Object)"container", (Object)"model element"), TuplesKt.to((Object)"component", (Object)"model element"), TuplesKt.to((Object)"group", (Object)"grouping"), TuplesKt.to((Object)"enterprise", (Object)"boundary")};
        MODEL_ELEMENTS = MapsKt.mapOf(pairArray);
        DEPLOYMENT_KEYWORDS = java.util.Arrays.asList("deploymentEnvironment", "deploymentGroup", "deploymentNode", "infrastructureNode", "softwareSystemInstance", "containerInstance");
        VIEW_KEYWORDS = java.util.Arrays.asList("systemLandscape", "systemContext", "container", "component", "dynamic", "deployment", "filtered", "custom", "image");
        STYLE_KEYWORDS = java.util.Arrays.asList("styles", "element", "relationship", "themes", "theme", "branding", "terminology");
        STYLE_PROPERTIES = java.util.Arrays.asList("shape", "icon", "width", "height", "background", "color", "colour", "stroke", "strokeWidth", "fontSize", "border", "opacity", "metadata", "thickness", "style", "routing", "position", "dashed");
        SHAPES = java.util.Arrays.asList("Box", "RoundedBox", "Circle", "Ellipse", "Hexagon", "Cylinder", "Pipe", "Person", "Robot", "Folder", "WebBrowser", "MobileDevicePortrait", "MobileDeviceLandscape", "Component");
        LAYOUT_DIRECTIONS = java.util.Arrays.asList("tb", "bt", "lr", "rl");
        DIRECTIVES = java.util.Arrays.asList("!include", "!docs", "!adrs", "!identifiers", "!impliedRelationships", "!constant", "!const", "!var", "!plugin", "!script");
        OTHER_KEYWORDS = java.util.Arrays.asList("include", "exclude", "autoLayout", "default", "animation", "title", "description", "tags", "url", "properties", "perspectives", "users", "extends", "ref", "this", "true", "false", "read", "write", "private", "public", "hierarchical", "flat");
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0013\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\b\u00a8\u0006\u001d"}, d2={"Lcom/jakubjirak/structurizr/completion/StructurizrCompletionProvider$Companion;", "", "<init>", "()V", "CORE_KEYWORDS", "", "", "getCORE_KEYWORDS", "()Ljava/util/List;", "MODEL_ELEMENTS", "", "getMODEL_ELEMENTS", "()Ljava/util/Map;", "DEPLOYMENT_KEYWORDS", "getDEPLOYMENT_KEYWORDS", "VIEW_KEYWORDS", "getVIEW_KEYWORDS", "STYLE_KEYWORDS", "getSTYLE_KEYWORDS", "STYLE_PROPERTIES", "getSTYLE_PROPERTIES", "SHAPES", "getSHAPES", "LAYOUT_DIRECTIONS", "getLAYOUT_DIRECTIONS", "DIRECTIVES", "getDIRECTIVES", "OTHER_KEYWORDS", "getOTHER_KEYWORDS", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final List<String> getCORE_KEYWORDS() {
            return CORE_KEYWORDS;
        }

        @NotNull
        public final Map<String, String> getMODEL_ELEMENTS() {
            return MODEL_ELEMENTS;
        }

        @NotNull
        public final List<String> getDEPLOYMENT_KEYWORDS() {
            return DEPLOYMENT_KEYWORDS;
        }

        @NotNull
        public final List<String> getVIEW_KEYWORDS() {
            return VIEW_KEYWORDS;
        }

        @NotNull
        public final List<String> getSTYLE_KEYWORDS() {
            return STYLE_KEYWORDS;
        }

        @NotNull
        public final List<String> getSTYLE_PROPERTIES() {
            return STYLE_PROPERTIES;
        }

        @NotNull
        public final List<String> getSHAPES() {
            return SHAPES;
        }

        @NotNull
        public final List<String> getLAYOUT_DIRECTIONS() {
            return LAYOUT_DIRECTIONS;
        }

        @NotNull
        public final List<String> getDIRECTIVES() {
            return DIRECTIVES;
        }

        @NotNull
        public final List<String> getOTHER_KEYWORDS() {
            return OTHER_KEYWORDS;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
