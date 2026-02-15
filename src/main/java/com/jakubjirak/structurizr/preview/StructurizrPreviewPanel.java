/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.Disposable
 *  com.intellij.openapi.application.ApplicationManager
 *  com.intellij.openapi.diagnostic.Logger
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.util.Disposer
 *  com.intellij.ui.JBColor
 *  com.intellij.ui.components.JBLabel
 *  com.intellij.ui.jcef.JBCefApp
 *  com.intellij.ui.jcef.JBCefBrowser
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.Pair
 *  kotlin.Triple
 *  kotlin.TuplesKt
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.MatchResult
 *  kotlin.text.MatchResult$Destructured
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.preview;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.jcef.JBCefApp;
import com.intellij.ui.jcef.JBCefBrowser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.UIManager;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u00012\u00020\u0002:\u0007CDEFGHIB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u0012H\u0002J\u001a\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\r2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\rH\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rH\u0002J:\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u001b2\b\u0010\"\u001a\u0004\u0018\u00010\r2\b\u0010#\u001a\u0004\u0018\u00010\rH\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010 \u001a\u00020\rH\u0002J\u0018\u0010&\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\r2\u0006\u0010'\u001a\u00020%H\u0002J\u001a\u0010(\u001a\u00020\r2\u0006\u0010!\u001a\u00020\u001b2\b\u0010\u0017\u001a\u0004\u0018\u00010\rH\u0002J \u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010!\u001a\u00020\u001b2\b\u0010'\u001a\u0004\u0018\u00010%H\u0002J$\u0010,\u001a\b\u0012\u0004\u0012\u00020-0*2\u0006\u0010!\u001a\u00020\u001b2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002J\"\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u000201002\f\u0010.\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002JB\u00102\u001a\u00020\r2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020+0*2\f\u00103\u001a\b\u0012\u0004\u0012\u00020-0*2\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u000201002\b\u0010'\u001a\u0004\u0018\u00010%H\u0002J>\u00105\u001a\b\u0012\u0004\u0012\u0002060*2\u0006\u00107\u001a\u0002012\u0006\u00108\u001a\u0002012\u0006\u00109\u001a\u0002062\u0006\u0010:\u001a\u0002062\u0006\u0010;\u001a\u0002062\u0006\u0010<\u001a\u000206H\u0002J\"\u0010=\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0>2\u0006\u0010?\u001a\u00020\u001fH\u0002J\u0010\u0010@\u001a\u00020\r2\u0006\u00107\u001a\u00020\rH\u0002J\u0006\u0010A\u001a\u00020\u0012J\b\u0010B\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u00070\b\u00a2\u0006\u0002\b\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006J"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel;", "Ljavax/swing/JPanel;", "Lcom/intellij/openapi/Disposable;", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "log", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "browser", "Lcom/intellij/ui/jcef/JBCefBrowser;", "currentDsl", "", "currentViewKey", "placeholderLabel", "Lcom/intellij/ui/components/JBLabel;", "initBrowser", "", "showFallbackPanel", "showPlaceholder", "updatePreview", "dslContent", "viewKey", "showError", "msg", "parseDsl", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ParsedModel;", "dsl", "parseModelLine", "Lkotlin/Pair;", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ElementType;", "line", "model", "sysId", "contId", "parseViewLine", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$View;", "parseViewContent", "view", "generateHtml", "getElementsForView", "", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$Element;", "getRelationshipsForView", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$Relationship;", "elements", "calculatePositions", "", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$Position;", "buildHtml", "rels", "pos", "calculateConnectionPoints", "", "s", "t", "sCx", "sCy", "tCx", "tCy", "getColors", "Lkotlin/Triple;", "type", "esc", "clear", "dispose", "ParsedModel", "Element", "ElementType", "Relationship", "View", "ViewType", "Position", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrPreviewPanel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrPreviewPanel.kt\ncom/jakubjirak/structurizr/preview/StructurizrPreviewPanel\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,500:1\n1099#2,3:501\n1099#2,3:504\n1#3:507\n288#4,2:508\n766#4:510\n857#4,2:511\n766#4:513\n857#4,2:514\n766#4:516\n857#4,2:517\n766#4:519\n857#4,2:520\n1549#4:522\n1620#4,3:523\n766#4:526\n857#4,2:527\n766#4:529\n857#4,2:530\n766#4:532\n857#4,2:533\n766#4:535\n857#4,2:536\n766#4:538\n857#4,2:539\n1855#4,2:541\n1855#4,2:543\n1864#4,3:545\n1864#4,3:548\n1855#4,2:551\n1855#4,2:553\n*S KotlinDebug\n*F\n+ 1 StructurizrPreviewPanel.kt\ncom/jakubjirak/structurizr/preview/StructurizrPreviewPanel\n*L\n148#1:501,3\n149#1:504,3\n231#1:508,2\n242#1:510\n242#1:511,2\n243#1:513\n243#1:514,2\n244#1:516\n244#1:517,2\n246#1:519\n246#1:520,2\n251#1:522\n251#1:523,3\n252#1:526\n252#1:527,2\n259#1:529\n259#1:530,2\n260#1:532\n260#1:533,2\n261#1:535\n261#1:536,2\n262#1:538\n262#1:539,2\n279#1:541,2\n290#1:543,2\n309#1:545,3\n331#1:548,3\n352#1:551,2\n388#1:553,2\n*E\n"})
public final class StructurizrPreviewPanel
extends JPanel
implements Disposable {
    @NotNull
    private final Project project;
    @NotNull
    private final Logger log;
    @Nullable
    private JBCefBrowser browser;
    @NotNull
    private String currentDsl;
    @Nullable
    private String currentViewKey;
    @NotNull
    private final JBLabel placeholderLabel;

    public StructurizrPreviewPanel(@NotNull Project project) {
        super(new BorderLayout());
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        this.project = project;
        Logger logger = Logger.getInstance(StructurizrPreviewPanel.class);
        Intrinsics.checkNotNullExpressionValue((Object)logger, (String)"getInstance(...)");
        this.log = logger;
        this.currentDsl = "";
        this.placeholderLabel = new JBLabel("<html><center><h2>Structurizr Preview</h2><p>Edit your DSL file to see the diagram</p></center></html>", 0);
        Color color = UIManager.getColor("Editor.background");
        if (color == null) {
            Color color2 = JBColor.background();
            color = color2;
            Intrinsics.checkNotNullExpressionValue((Object)color2, (String)"background(...)");
        }
        this.setBackground(color);
        this.initBrowser();
    }

    private final void initBrowser() {
        try {
            if (JBCefApp.isSupported()) {
                JBCefBrowser jBCefBrowser = this.browser = new JBCefBrowser();
                if (jBCefBrowser != null) {
                    JBCefBrowser b = jBCefBrowser;
                    boolean bl = false;
                    this.add((Component)b.getComponent(), "Center");
                    Disposer.register((Disposable)this, (Disposable)((Disposable)b));
                    this.showPlaceholder();
                }
            } else {
                this.showFallbackPanel();
            }
        }
        catch (Exception e) {
            this.log.warn("Failed to initialize JCEF browser", (Throwable)e);
            this.showFallbackPanel();
        }
    }

    private final void showFallbackPanel() {
        this.removeAll();
        this.add((Component)this.placeholderLabel, "Center");
        this.revalidate();
        this.repaint();
    }

    private final void showPlaceholder() {
        block0: {
            String html = "<!DOCTYPE html>\n<html>\n<head>\n<style>\nbody { font-family: -apple-system, sans-serif; display: flex; justify-content: center; \n       align-items: center; height: 100vh; margin: 0; background: #f5f5f5; }\n.placeholder { text-align: center; padding: 40px; }\nh2 { color: #1168bd; }\n</style>\n</head>\n<body><div class=\"placeholder\"><h2>Structurizr Preview</h2><p>Edit your DSL file</p></div></body>\n</html>";
            JBCefBrowser jBCefBrowser = this.browser;
            if (jBCefBrowser == null) break block0;
            jBCefBrowser.loadHTML(html);
        }
    }

    public final void updatePreview(@NotNull String dslContent, @Nullable String viewKey) {
        Intrinsics.checkNotNullParameter((Object)dslContent, (String)"dslContent");
        if (Intrinsics.areEqual((Object)dslContent, (Object)this.currentDsl) && Intrinsics.areEqual((Object)viewKey, (Object)this.currentViewKey)) {
            return;
        }
        this.currentDsl = dslContent;
        this.currentViewKey = viewKey;
        ApplicationManager.getApplication().executeOnPooledThread(() -> StructurizrPreviewPanel.updatePreview$lambda$2(this, dslContent, viewKey));
    }

    public static /* synthetic */ void updatePreview$default(StructurizrPreviewPanel structurizrPreviewPanel, String string, String string2, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = null;
        }
        structurizrPreviewPanel.updatePreview(string, string2);
    }

    private final void showError(String msg) {
        block0: {
            String html = StringsKt.trimIndent((String)("\n<!DOCTYPE html>\n<html><head><style>\nbody { font-family: sans-serif; display: flex; justify-content: center; align-items: center; \n       height: 100vh; background: #fff5f5; }\n.error { text-align: center; padding: 40px; }\nh3 { color: #c53030; }\npre { background: #fed7d7; padding: 15px; border-radius: 8px; }\n</style></head>\n<body><div class=\"error\"><h3>Parse Error</h3><pre>" + StringsKt.replace((String)msg, (String)"<", (String)"&lt;", (boolean)false) + "</pre></div></body>\n</html>\n        "));
            JBCefBrowser jBCefBrowser = this.browser;
            if (jBCefBrowser == null) break block0;
            jBCefBrowser.loadHTML(html);
        }
    }

    private final ParsedModel parseDsl(String dsl) {
        ParsedModel model = new ParsedModel(null, null, null, 7, null);
        List lines = StringsKt.lines((CharSequence)dsl);
        int i = 0;
        boolean inModel = false;
        boolean inViews = false;
        String currentSystemId = null;
        String currentContainerId = null;
        View currentView = null;
        int braceDepth = 0;
        while (i < lines.size()) {
            int element$iv;
            String line = ((Object)StringsKt.trim((CharSequence)((String)lines.get(i)))).toString();
            ++i;
            if (((CharSequence)line).length() == 0 || StringsKt.startsWith((String)line, (String)"//", (boolean)false) || StringsKt.startsWith((String)line, (String)"#", (boolean)false)) continue;
            CharSequence $this$count$iv = line;
            boolean $i$f$count = false;
            int count$iv = 0;
            for (int j = 0; j < $this$count$iv.length(); ++j) {
                int it = element$iv = $this$count$iv.charAt(j);
                boolean bl = false;
                if (!(it == 123)) continue;
                ++count$iv;
            }
            int openBraces = count$iv;
            CharSequence $this$count$iv2 = line;
            boolean $i$f$count2 = false;
            int count$iv2 = 0;
            for (element$iv = 0; element$iv < $this$count$iv2.length(); ++element$iv) {
                char element$iv2;
                char it = element$iv2 = $this$count$iv2.charAt(element$iv);
                boolean bl = false;
                if (!(it == '}')) continue;
                ++count$iv2;
            }
            int closeBraces = count$iv2;
            if (StringsKt.contains((CharSequence)line, (CharSequence)"model", (boolean)false) && StringsKt.contains((CharSequence)line, (CharSequence)"{", (boolean)false)) {
                inModel = true;
                inViews = false;
                braceDepth = 1;
                continue;
            }
            if (StringsKt.contains((CharSequence)line, (CharSequence)"views", (boolean)false) && StringsKt.contains((CharSequence)line, (CharSequence)"{", (boolean)false)) {
                inModel = false;
                inViews = true;
                braceDepth = 1;
                continue;
            }
            if (Intrinsics.areEqual((Object)line, (Object)"}")) {
                if (--braceDepth <= 0) {
                    inModel = false;
                    inViews = false;
                }
                if (currentView != null && inViews) {
                    model.getViews().add(currentView);
                    currentView = null;
                }
                if (currentContainerId != null) {
                    currentContainerId = null;
                    continue;
                }
                if (currentSystemId == null) continue;
                currentSystemId = null;
                continue;
            }
            if (inModel) {
                braceDepth += openBraces - closeBraces;
                Pair pair = this.parseModelLine(line, model, currentSystemId, currentContainerId);
                if (pair != null) {
                    boolean bl = false;
                    String id = (String)pair.component1();
                    ElementType type = (ElementType)((Object)pair.component2());
                    switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                        case 1: {
                            currentSystemId = id;
                            break;
                        }
                        case 2: {
                            currentContainerId = id;
                        }
                    }
                }
                continue;
            }
            if (!inViews) continue;
            braceDepth += openBraces - closeBraces;
            if (currentView == null) {
                currentView = this.parseViewLine(line);
                continue;
            }
            this.parseViewContent(line, currentView);
        }
        return model;
    }

    private final Pair<String, ElementType> parseModelLine(String line, ParsedModel model, String sysId, String contId) {
        block16: {
            Object object;
            String string;
            List<Relationship> list;
            String string2;
            String string3;
            int it;
            Object object2;
            MatchResult matchResult = new Regex("(\\w+)\\s*=\\s*person\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?").find((CharSequence)line, 0);
            if (matchResult != null) {
                MatchResult it2 = matchResult;
                boolean bl = false;
                MatchResult.Destructured destructured = it2.getDestructured();
                String id = (String)destructured.getMatch().getGroupValues().get(1);
                String name = (String)destructured.getMatch().getGroupValues().get(2);
                String desc = (String)destructured.getMatch().getGroupValues().get(3);
                model.getElements().add(new Element(id, name, desc, ElementType.PERSON, null, null, 48, null));
                return StringsKt.contains((CharSequence)line, (CharSequence)"{", (boolean)false) ? (Pair)TuplesKt.to((Object)id, (Object)((Object)ElementType.PERSON)) : null;
            }
            matchResult = new Regex("(\\w+)\\s*=\\s*softwareSystem\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?").find((CharSequence)line, 0);
            if (matchResult != null) {
                MatchResult it3 = matchResult;
                boolean bl = false;
                MatchResult.Destructured destructured = it3.getDestructured();
                String id = (String)destructured.getMatch().getGroupValues().get(1);
                String name = (String)destructured.getMatch().getGroupValues().get(2);
                String desc = (String)destructured.getMatch().getGroupValues().get(3);
                model.getElements().add(new Element(id, name, desc, ElementType.SOFTWARE_SYSTEM, null, null, 48, null));
                return StringsKt.contains((CharSequence)line, (CharSequence)"{", (boolean)false) ? (Pair)TuplesKt.to((Object)id, (Object)((Object)ElementType.SOFTWARE_SYSTEM)) : null;
            }
            matchResult = new Regex("(\\w+)\\s*=\\s*container\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?").find((CharSequence)line, 0);
            if (matchResult != null) {
                Object object3;
                String string4;
                List<Element> list2;
                String string5;
                String string6;
                Object object4;
                MatchResult it4 = matchResult;
                boolean bl = false;
                List g = it4.getGroupValues();
                List<Element> list3 = model.getElements();
                String string7 = (String)g.get(1);
                String string8 = (String)g.get(2);
                int n = 3;
                List list4 = g;
                if (n <= CollectionsKt.getLastIndex((List)list4)) {
                    object4 = list4.get(n);
                } else {
                    int desc = n;
                    string6 = string8;
                    string5 = string7;
                    list2 = list3;
                    boolean bl2 = false;
                    string4 = "";
                    list3 = list2;
                    string7 = string5;
                    string8 = string6;
                    object4 = string4;
                }
                String string9 = (String)object4;
                ElementType elementType = ElementType.CONTAINER;
                list4 = g;
                n = 4;
                if (n <= CollectionsKt.getLastIndex((List)list4)) {
                    object3 = list4.get(n);
                } else {
                    int it5 = n;
                    ElementType elementType2 = elementType;
                    string4 = string9;
                    string6 = string8;
                    string5 = string7;
                    list2 = list3;
                    boolean bl3 = false;
                    String string10 = "";
                    list3 = list2;
                    string7 = string5;
                    string8 = string6;
                    string9 = string4;
                    elementType = elementType2;
                    object3 = string10;
                }
                String string11 = sysId;
                String string12 = (String)object3;
                ElementType elementType3 = elementType;
                String string13 = string9;
                String string14 = string8;
                String string15 = string7;
                list3.add(new Element(string15, string14, string13, elementType3, string12, string11));
                return StringsKt.contains((CharSequence)line, (CharSequence)"{", (boolean)false) ? (Pair)TuplesKt.to(g.get(1), (Object)((Object)ElementType.CONTAINER)) : null;
            }
            matchResult = new Regex("(\\w+)\\s*=\\s*component\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?").find((CharSequence)line, 0);
            if (matchResult != null) {
                Object object5;
                String string16;
                List<Element> list5;
                String string17;
                String string18;
                int it6;
                Object object6;
                MatchResult it7 = matchResult;
                boolean bl = false;
                List g = it7.getGroupValues();
                List<Element> list6 = model.getElements();
                String string19 = (String)g.get(1);
                String string20 = (String)g.get(2);
                int n = 3;
                List list7 = g;
                if (n <= CollectionsKt.getLastIndex((List)list7)) {
                    object6 = list7.get(n);
                } else {
                    it6 = n;
                    string18 = string20;
                    string17 = string19;
                    list5 = list6;
                    boolean bl4 = false;
                    string16 = "";
                    list6 = list5;
                    string19 = string17;
                    string20 = string18;
                    object6 = string16;
                }
                String string21 = (String)object6;
                ElementType elementType = ElementType.COMPONENT;
                list7 = g;
                n = 4;
                if (n <= CollectionsKt.getLastIndex((List)list7)) {
                    object5 = list7.get(n);
                } else {
                    it6 = n;
                    ElementType elementType4 = elementType;
                    string16 = string21;
                    string18 = string20;
                    string17 = string19;
                    list5 = list6;
                    boolean bl5 = false;
                    String string22 = "";
                    list6 = list5;
                    string19 = string17;
                    string20 = string18;
                    string21 = string16;
                    elementType = elementType4;
                    object5 = string22;
                }
                String string23 = contId;
                String string24 = (String)object5;
                ElementType elementType5 = elementType;
                String string25 = string21;
                String string26 = string20;
                String string27 = string19;
                list6.add(new Element(string27, string26, string25, elementType5, string24, string23));
                return StringsKt.contains((CharSequence)line, (CharSequence)"{", (boolean)false) ? (Pair)TuplesKt.to(g.get(1), (Object)((Object)ElementType.COMPONENT)) : null;
            }
            MatchResult matchResult2 = new Regex("(\\w+)\\s*->\\s*(\\w+)(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?").find((CharSequence)line, 0);
            if (matchResult2 == null) break block16;
            MatchResult it8 = matchResult2;
            boolean bl = false;
            List g = it8.getGroupValues();
            List<Relationship> list8 = model.getRelationships();
            String string28 = (String)g.get(1);
            String string29 = (String)g.get(2);
            int n = 3;
            List list9 = g;
            if (n <= CollectionsKt.getLastIndex((List)list9)) {
                object2 = list9.get(n);
            } else {
                it = n;
                string3 = string29;
                string2 = string28;
                list = list8;
                boolean bl6 = false;
                string = "";
                list8 = list;
                string28 = string2;
                string29 = string3;
                object2 = string;
            }
            String string30 = (String)object2;
            list9 = g;
            n = 4;
            if (n <= CollectionsKt.getLastIndex((List)list9)) {
                object = list9.get(n);
            } else {
                it = n;
                string = string30;
                string3 = string29;
                string2 = string28;
                list = list8;
                boolean bl7 = false;
                String string31 = "";
                list8 = list;
                string28 = string2;
                string29 = string3;
                string30 = string;
                object = string31;
            }
            String string32 = (String)object;
            String string33 = string30;
            String string34 = string29;
            String string35 = string28;
            list8.add(new Relationship(string35, string34, string33, string32));
        }
        return null;
    }

    private final View parseViewLine(String line) {
        MatchResult matchResult = new Regex("systemLandscape\\s*(?:\"([^\"]+)\")?\\s*\\{").find((CharSequence)line, 0);
        if (matchResult != null) {
            Object object;
            MatchResult it = matchResult;
            boolean bl = false;
            String string = "landscape";
            ViewType viewType = ViewType.SYSTEM_LANDSCAPE;
            int n = 1;
            List list = it.getGroupValues();
            if (n <= CollectionsKt.getLastIndex((List)list)) {
                object = list.get(n);
            } else {
                int n2 = n;
                ViewType viewType2 = viewType;
                String string2 = string;
                boolean bl2 = false;
                String string3 = "";
                string = string2;
                viewType = viewType2;
                object = string3;
            }
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n3 = 56;
            String string4 = null;
            List list2 = null;
            String string5 = null;
            String string6 = (String)object;
            ViewType viewType3 = viewType;
            String string7 = string;
            return new View(string7, viewType3, string6, string5, list2, string4, n3, defaultConstructorMarker);
        }
        matchResult = new Regex("systemContext\\s+(\\w+)\\s*(?:\"([^\"]+)\")?\\s*\\{").find((CharSequence)line, 0);
        if (matchResult != null) {
            Object object;
            MatchResult it = matchResult;
            boolean bl = false;
            String string = "context";
            ViewType viewType = ViewType.SYSTEM_CONTEXT;
            int n = 2;
            List list = it.getGroupValues();
            if (n <= CollectionsKt.getLastIndex((List)list)) {
                object = list.get(n);
            } else {
                int it2 = n;
                ViewType viewType4 = viewType;
                String string8 = string;
                boolean bl3 = false;
                String string9 = "";
                string = string8;
                viewType = viewType4;
                object = string9;
            }
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n4 = 48;
            String string10 = null;
            List list3 = null;
            String string11 = (String)it.getGroupValues().get(1);
            String string12 = (String)object;
            ViewType viewType5 = viewType;
            String string13 = string;
            return new View(string13, viewType5, string12, string11, list3, string10, n4, defaultConstructorMarker);
        }
        matchResult = new Regex("container\\s+(\\w+)\\s*(?:\"([^\"]+)\")?\\s*\\{").find((CharSequence)line, 0);
        if (matchResult != null) {
            Object object;
            MatchResult it = matchResult;
            boolean bl = false;
            String string = "containers";
            ViewType viewType = ViewType.CONTAINER;
            int n = 2;
            List list = it.getGroupValues();
            if (n <= CollectionsKt.getLastIndex((List)list)) {
                object = list.get(n);
            } else {
                int it3 = n;
                ViewType viewType6 = viewType;
                String string14 = string;
                boolean bl4 = false;
                String string15 = "";
                string = string14;
                viewType = viewType6;
                object = string15;
            }
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n5 = 48;
            String string16 = null;
            List list4 = null;
            String string17 = (String)it.getGroupValues().get(1);
            String string18 = (String)object;
            ViewType viewType7 = viewType;
            String string19 = string;
            return new View(string19, viewType7, string18, string17, list4, string16, n5, defaultConstructorMarker);
        }
        matchResult = new Regex("component\\s+(\\w+)\\s*(?:\"([^\"]+)\")?\\s*\\{").find((CharSequence)line, 0);
        if (matchResult != null) {
            Object object;
            MatchResult it = matchResult;
            boolean bl = false;
            String string = "components";
            ViewType viewType = ViewType.COMPONENT;
            int n = 2;
            List list = it.getGroupValues();
            if (n <= CollectionsKt.getLastIndex((List)list)) {
                object = list.get(n);
            } else {
                int it4 = n;
                ViewType viewType8 = viewType;
                String string20 = string;
                boolean bl5 = false;
                String string21 = "";
                string = string20;
                viewType = viewType8;
                object = string21;
            }
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n6 = 48;
            String string22 = null;
            List list5 = null;
            String string23 = (String)it.getGroupValues().get(1);
            String string24 = (String)object;
            ViewType viewType9 = viewType;
            String string25 = string;
            return new View(string25, viewType9, string24, string23, list5, string22, n6, defaultConstructorMarker);
        }
        return null;
    }

    private final void parseViewContent(String line, View view) {
        block2: {
            CharSequence charSequence;
            if (StringsKt.startsWith((String)line, (String)"include", (boolean)false)) {
                CharSequence charSequence2 = ((Object)StringsKt.trim((CharSequence)StringsKt.removePrefix((String)line, (CharSequence)"include"))).toString();
                Regex includeRegex = new Regex("\\s+");
                int n = 0;
                view.getIncludes().addAll(includeRegex.split(charSequence2, n));
            }
            if (!StringsKt.startsWith((String)line, (String)"autoLayout", (boolean)false)) break block2;
            charSequence = line;
            Regex regex = new Regex("\\s+");
            int n = 0;
            String string = (String)CollectionsKt.getOrNull((List)regex.split(charSequence, n), (int)1);
            if (string != null) {
                String it = string;
                boolean bl = false;
                String string2 = it.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"this as java.lang.String).toLowerCase(Locale.ROOT)");
                view.setAutoLayout(string2);
            }
        }
    }

    private final String generateHtml(ParsedModel model, String viewKey) {
        View view;
        Object v0;
        block2: {
            Iterable $this$firstOrNull$iv = model.getViews();
            boolean $i$f$firstOrNull = false;
            for (Object element$iv : $this$firstOrNull$iv) {
                View it = (View)element$iv;
                boolean bl = false;
                if (!(viewKey == null || Intrinsics.areEqual((Object)it.getKey(), (Object)viewKey))) continue;
                v0 = element$iv;
                break block2;
            }
            v0 = null;
        }
        if ((view = (View)v0) == null) {
            view = (View)CollectionsKt.firstOrNull(model.getViews());
        }
        View view2 = view;
        List<Element> elements = this.getElementsForView(model, view2);
        List<Relationship> relationships = this.getRelationshipsForView(model, elements);
        Map<String, Position> positions = this.calculatePositions(elements);
        return this.buildHtml(elements, relationships, positions, view2);
    }

    private final List<Element> getElementsForView(ParsedModel model, View view) {
        if (view == null) {
            return model.getElements();
        }
        boolean all = view.getIncludes().contains("*");
        return switch (WhenMappings.$EnumSwitchMapping$1[view.getType().ordinal()]) {
            case 1 -> {
                if (all) {
                    Iterable $this$filter$iv = model.getElements();
                    boolean $i$f$filter = false;
                    Iterable var6_14 = $this$filter$iv;
                    Collection destination$iv$iv = new ArrayList();
                    boolean $i$f$filterTo = false;
                    for (Object element$iv$iv : var6_14) {
                        Element it = (Element)element$iv$iv;
                        boolean $i$a$_filter_StructurizrPreviewPanel$getElementsForView$1 = false;
                        if (!(it.getType() == ElementType.PERSON || it.getType() == ElementType.SOFTWARE_SYSTEM)) continue;
                        destination$iv$iv.add(element$iv$iv);
                    }
                    yield (List)destination$iv$iv;
                }
                Iterable $this$filter$iv = model.getElements();
                boolean $i$f$filter = false;
                Iterable $this$filterTo$iv$iv = $this$filter$iv;
                Collection destination$iv$iv = new ArrayList();
                boolean $i$f$filterTo = false;
                for (Object element$iv$iv : $this$filterTo$iv$iv) {
                    Element it = (Element)element$iv$iv;
                    boolean $i$a$_filter_StructurizrPreviewPanel$getElementsForView$2 = false;
                    if (!view.getIncludes().contains(it.getId())) continue;
                    destination$iv$iv.add(element$iv$iv);
                }
                yield (List)destination$iv$iv;
            }
            case 2 -> {
                Iterable $this$filter$iv = model.getElements();
                boolean $i$f$filter = false;
                Iterable $this$filterTo$iv$iv = $this$filter$iv;
                Collection destination$iv$iv = new ArrayList();
                boolean $i$f$filterTo = false;
                for (Object element$iv$iv : $this$filterTo$iv$iv) {
                    Element it = (Element)element$iv$iv;
                    boolean $i$a$_filter_StructurizrPreviewPanel$getElementsForView$3 = false;
                    if (!(it.getType() == ElementType.PERSON || it.getType() == ElementType.SOFTWARE_SYSTEM)) continue;
                    destination$iv$iv.add(element$iv$iv);
                }
                yield (List)destination$iv$iv;
            }
            case 3 -> {
                Iterable $this$filter$iv = model.getElements();
                boolean $i$f$filter = false;
                Iterable $this$filterTo$iv$iv = $this$filter$iv;
                Collection destination$iv$iv = new ArrayList();
                boolean $i$f$filterTo = false;
                for (Object element$iv$iv : $this$filterTo$iv$iv) {
                    Element it = (Element)element$iv$iv;
                    boolean $i$a$_filter_StructurizrPreviewPanel$getElementsForView$4 = false;
                    if (!(it.getType() == ElementType.PERSON || it.getType() == ElementType.SOFTWARE_SYSTEM || it.getType() == ElementType.CONTAINER && Intrinsics.areEqual((Object)it.getParentId(), (Object)view.getScope()))) continue;
                    destination$iv$iv.add(element$iv$iv);
                }
                yield (List)destination$iv$iv;
            }
            case 4 -> model.getElements();
            case 5 -> {
                Iterable $this$filter$iv = model.getElements();
                boolean $i$f$filter = false;
                Iterable $this$filterTo$iv$iv = $this$filter$iv;
                Collection destination$iv$iv = new ArrayList();
                boolean $i$f$filterTo = false;
                for (Object element$iv$iv : $this$filterTo$iv$iv) {
                    Element it = (Element)element$iv$iv;
                    boolean $i$a$_filter_StructurizrPreviewPanel$getElementsForView$5 = false;
                    if (!(it.getType() == ElementType.DEPLOYMENT_NODE)) continue;
                    destination$iv$iv.add(element$iv$iv);
                }
                yield (List)destination$iv$iv;
            }
            default -> throw new NoWhenBranchMatchedException();
        };
    }


    private final List<Relationship> getRelationshipsForView(ParsedModel model, List<Element> elements) {
        Relationship it;
        Iterable $this$map$iv = elements;
        boolean $i$f$map = false;
        Iterable iterable = $this$map$iv;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map$iv, (int)10));
        boolean $i$f$mapTo = false;
        for (Object item$iv$iv : iterable) {
            Element element = (Element)item$iv$iv;
            Collection collection = destination$iv$iv;
            boolean bl = false;
            collection.add(element.getId());
        }
        Set ids = CollectionsKt.toSet((Iterable)((List)destination$iv$iv));
        Iterable $this$filter$iv = model.getRelationships();
        boolean $i$f$filter = false;
        Iterable $this$filterTo$iv$iv = $this$filter$iv;
        destination$iv$iv = new ArrayList();
        boolean $i$f$filterTo = false;
        for (Object element$iv$iv : $this$filterTo$iv$iv) {
            it = (Relationship)element$iv$iv;
            boolean bl = false;
            if (!(ids.contains(it.getSourceId()) && ids.contains(it.getTargetId()))) continue;
            destination$iv$iv.add(element$iv$iv);
        }
        return (List)destination$iv$iv;
    }


    private final Map<String, Position> calculatePositions(List<Element> elements) {
        Element c;
        int y;
        int x;
        int row;
        int col;
        int i;
        int n;
        Iterable $this$forEachIndexed$iv;
        int startX;
        int rows;
        int cols;
        Element it;
        boolean $i$f$forEach;
        Iterable $this$forEach$iv;
        int x2;
        int totalW;
        Map pos = new LinkedHashMap();
        Iterable $this$filter$iv = elements;
        boolean $i$f$filter = false;
        Iterable iterable = $this$filter$iv;
        ArrayList destination$iv$iv = new ArrayList();
        boolean $i$f$filterTo = false;
        for (Object element$iv$iv : iterable) {
            Element it2 = (Element)element$iv$iv;
            boolean bl = false;
            if (!(it2.getType() == ElementType.PERSON)) continue;
            destination$iv$iv.add(element$iv$iv);
        }
        List people = (List)destination$iv$iv;
        Iterable $this$filter$iv2 = elements;
        boolean $i$f$filter2 = false;
        Iterable destination$iv$iv_src = $this$filter$iv2;
        ArrayList destination$iv$iv2 = new ArrayList();
        boolean $i$f$filterTo2 = false;
        for (Object element$iv$iv : destination$iv$iv_src) {
            Element it3 = (Element)element$iv$iv;
            boolean bl = false;
            if (!(it3.getType() == ElementType.SOFTWARE_SYSTEM)) continue;
            destination$iv$iv2.add(element$iv$iv);
        }
        List systems = (List)destination$iv$iv2;
        Iterable $this$filter$iv3 = elements;
        boolean $i$f$filter3 = false;
        Iterable destination$iv$iv2_src = $this$filter$iv3;
        ArrayList destination$iv$iv3 = new ArrayList();
        boolean $i$f$filterTo3 = false;
        for (Object element$iv$iv : destination$iv$iv2_src) {
            Element it4 = (Element)element$iv$iv;
            boolean bl = false;
            if (!(it4.getType() == ElementType.CONTAINER)) continue;
            destination$iv$iv3.add(element$iv$iv);
        }
        List containers = (List)destination$iv$iv3;
        Iterable $this$filter$iv4 = elements;
        boolean $i$f$filter4 = false;
        Iterable destination$iv$iv3_src = $this$filter$iv4;
        Collection destination$iv$iv4 = new ArrayList();
        boolean $i$f$filterTo4 = false;
        for (Object element$iv$iv : destination$iv$iv3_src) {
            Element it5 = (Element)element$iv$iv;
            boolean bl = false;
            if (!(it5.getType() == ElementType.COMPONENT)) continue;
            destination$iv$iv4.add(element$iv$iv);
        }
        List components = (List)destination$iv$iv4;
        int pw = 180;
        int ph = 150;
        int sw = 240;
        int sh = 170;
        int cw = 210;
        int ch = 150;
        int compw = 190;
        int comph = 130;
        int hGap = 60;
        int vGap = 80;
        int canvasWidth = 1400;
        int startY = 60;
        int currentY = 0;
        currentY = startY;
        if (!((Collection)people).isEmpty()) {
            totalW = people.size() * pw + (people.size() - 1) * hGap;
            x2 = 0;
            x2 = (canvasWidth - totalW) / 2;
            $this$forEach$iv = people;
            $i$f$forEach = false;
            for (Object element$iv : $this$forEach$iv) {
                it = (Element)element$iv;
                boolean bl = false;
                pos.put(it.getId(), new Position(x2, currentY, pw, ph));
                x2 += pw + hGap;
            }
            currentY += ph + vGap;
        }
        if (!((Collection)systems).isEmpty()) {
            totalW = systems.size() * sw + (systems.size() - 1) * hGap;
            x2 = 0;
            x2 = (canvasWidth - totalW) / 2;
            $this$forEach$iv = systems;
            $i$f$forEach = false;
            for (Object element$iv : $this$forEach$iv) {
                it = (Element)element$iv;
                boolean bl = false;
                pos.put(it.getId(), new Position(x2, currentY, sw, sh));
                x2 += sw + hGap;
            }
            currentY += sh + vGap;
        }
        if (!((Collection)containers).isEmpty()) {
            cols = containers.size() <= 2 ? containers.size() : (containers.size() <= 4 ? 2 : (containers.size() <= 6 ? 3 : 4));
            rows = (containers.size() + cols - 1) / cols;
            int gridW = cols * cw + (cols - 1) * hGap;
            startX = (canvasWidth - gridW) / 2;
            $this$forEachIndexed$iv = containers;
            boolean $i$f$forEachIndexed = false;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                if ((n = index$iv++) < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Element element = (Element)item$iv;
                i = n;
                boolean bl = false;
                col = i % cols;
                row = i / cols;
                x = startX + col * (cw + hGap);
                y = currentY + row * (ch + vGap);
                pos.put(element.getId(), new Position(x, y, cw, ch));
            }
            currentY += rows * (ch + vGap);
        }
        if (!((Collection)components).isEmpty()) {
            cols = components.size() <= 2 ? components.size() : (components.size() <= 4 ? 2 : (components.size() <= 6 ? 3 : 4));
            rows = (components.size() + cols - 1) / cols;
            int gridW = cols * compw + (cols - 1) * hGap;
            startX = (canvasWidth - gridW) / 2;
            $this$forEachIndexed$iv = components;
            boolean $i$f$forEachIndexed = false;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                if ((n = index$iv++) < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                c = (Element)item$iv;
                i = n;
                boolean bl = false;
                col = i % cols;
                row = i / cols;
                x = startX + col * (compw + hGap);
                y = currentY + row * (comph + vGap);
                pos.put(c.getId(), new Position(x, y, compw, comph));
            }
        }
        return pos;
    }

    private final String buildHtml(List<Element> elements, List<Relationship> rels, Map<String, Position> pos, View view) {
        Object object;
        StringBuilder svg;
        int svgW;
        int svgH;
        block28: {
            block27: {
                Object object2;
                Object object3;
                Object object4;
                Iterator iterator = ((Iterable)pos.values()).iterator();
                if (!iterator.hasNext()) {
                    object4 = null;
                } else {
                    Comparable maxVal = (Comparable)Integer.valueOf(((Position)iterator.next()).getY() + ((Position)((Object)pos.values().iterator().next())).getH());
                    // Re-iterate properly for maxY
                    int currentMax = Integer.MIN_VALUE;
                    for (Object posObj : (Iterable)pos.values()) {
                        int val = ((Position)posObj).getY() + ((Position)posObj).getH();
                        if (val > currentMax) currentMax = val;
                    }
                    object4 = currentMax;
                }
                Integer n = (Integer)object4;
                int maxY = n != null ? n : 600;
                Iterator iterator2 = ((Iterable)pos.values()).iterator();
                if (!iterator2.hasNext()) {
                    object3 = null;
                } else {
                    int currentMax = Integer.MIN_VALUE;
                    for (Object posObj : (Iterable)pos.values()) {
                        int val = ((Position)posObj).getX() + ((Position)posObj).getW();
                        if (val > currentMax) currentMax = val;
                    }
                    object3 = currentMax;
                }
                Integer n2 = (Integer)object3;
                int maxX = n2 != null ? n2 : 1200;
                svgH = maxY + 150;
                svgW = Math.max(1400, maxX + 200);
                svg = new StringBuilder();
                Iterable $this$forEach$iv = rels;
                boolean $i$f$forEach = false;
                for (Object element$iv : $this$forEach$iv) {
                    Relationship rel = (Relationship)element$iv;
                    boolean bl = false;
                    Position s = pos.get(rel.getSourceId());
                    Position t = pos.get(rel.getTargetId());
                    if (s == null || t == null) continue;
                    int sCx = s.getX() + s.getW() / 2;
                    int sCy = s.getY() + s.getH() / 2;
                    int tCx = t.getX() + t.getW() / 2;
                    int tCy = t.getY() + t.getH() / 2;
                    List<Integer> list = this.calculateConnectionPoints(s, t, sCx, sCy, tCx, tCy);
                    int x1 = ((Number)list.get(0)).intValue();
                    int y1 = ((Number)list.get(1)).intValue();
                    int x2 = ((Number)list.get(2)).intValue();
                    int y2 = ((Number)list.get(3)).intValue();
                    int dx = x2 - x1;
                    int dy = y2 - y1;
                    int cx1 = 0;
                    int cy1 = 0;
                    int cx2 = 0;
                    int cy2 = 0;
                    if (Math.abs(dy) > Math.abs(dx)) {
                        cy1 = y1 + dy / 3;
                        cx1 = x1;
                        cy2 = y2 - dy / 3;
                        cx2 = x2;
                    } else {
                        cx1 = x1 + dx / 3;
                        cy1 = y1;
                        cx2 = x2 - dx / 3;
                        cy2 = y2;
                    }
                    svg.append("<path d=\"M " + x1 + " " + y1 + " C " + cx1 + " " + cy1 + ", " + cx2 + " " + cy2 + ", " + x2 + " " + y2 + "\" fill=\"none\" stroke=\"#707070\" stroke-width=\"2\" marker-end=\"url(#arr)\"/>");
                    if (!(((CharSequence)rel.getDescription()).length() > 0)) continue;
                    int lx = (x1 + x2) / 2;
                    int ly = (y1 + y2) / 2 - 15;
                    int labelWidth = Math.min(rel.getDescription().length() * 7 + 16, 150);
                    svg.append("<rect x=\"" + (lx - labelWidth / 2) + "\" y=\"" + (ly - 12) + "\" width=\"" + labelWidth + "\" height=\"24\" fill=\"white\" stroke=\"#ccc\" rx=\"4\"/>");
                    svg.append("<text x=\"" + lx + "\" y=\"" + ly + "\" text-anchor=\"middle\" dominant-baseline=\"middle\" font-family=\"Arial\" font-size=\"11\" fill=\"#707070\">" + this.esc(StringsKt.take((String)rel.getDescription(), (int)22)) + "</text>");
                }
                $this$forEach$iv = elements;
                $i$f$forEach = false;
                for (Object element$iv : $this$forEach$iv) {
                    Element el = (Element)element$iv;
                    boolean bl = false;
                    if (pos.get(el.getId()) == null) continue;
                    Triple<String, String, String> triple = this.getColors(el.getType());
                    String bg = (String)triple.component1();
                    String border = (String)triple.component2();
                    String txt = (String)triple.component3();
                    switch (WhenMappings.$EnumSwitchMapping$0[el.getType().ordinal()]) {
                        case 3: {
                            Position p = (Position)pos.get(el.getId());
                            int cx = p.getX() + p.getW() / 2;
                            svg.append("<circle cx=\"" + cx + "\" cy=\"" + (p.getY() + 30) + "\" r=\"25\" fill=\"" + bg + "\" stroke=\"" + border + "\" stroke-width=\"2\"/>");
                            svg.append("<rect x=\"" + (p.getX() + 20) + "\" y=\"" + (p.getY() + 60) + "\" width=\"" + (p.getW() - 40) + "\" height=\"" + (p.getH() - 70) + "\" fill=\"" + bg + "\" stroke=\"" + border + "\" stroke-width=\"2\" rx=\"8\"/>");
                            svg.append("<text x=\"" + cx + "\" y=\"" + (p.getY() + 90) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"14\" font-weight=\"bold\" fill=\"" + txt + "\">" + this.esc(el.getName()) + "</text>");
                            StringBuilder stringBuilder = svg.append("<text x=\"" + cx + "\" y=\"" + (p.getY() + 110) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"11\" fill=\"" + txt + "\">[Person]</text>");
                            break;
                        }
                        case 1: {
                            Position p = (Position)pos.get(el.getId());
                            svg.append("<rect x=\"" + p.getX() + "\" y=\"" + p.getY() + "\" width=\"" + p.getW() + "\" height=\"" + p.getH() + "\" fill=\"" + bg + "\" stroke=\"" + border + "\" stroke-width=\"2\" rx=\"8\"/>");
                            svg.append("<text x=\"" + (p.getX() + p.getW() / 2) + "\" y=\"" + (p.getY() + 40) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"15\" font-weight=\"bold\" fill=\"" + txt + "\">" + this.esc(el.getName()) + "</text>");
                            svg.append("<text x=\"" + (p.getX() + p.getW() / 2) + "\" y=\"" + (p.getY() + 60) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"11\" fill=\"" + txt + "\">[Software System]</text>");
                            if (((CharSequence)el.getDescription()).length() > 0) {
                                svg.append("<text x=\"" + (p.getX() + p.getW() / 2) + "\" y=\"" + (p.getY() + 85) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"10\" fill=\"" + txt + "\" opacity=\"0.85\">" + this.esc(StringsKt.take((String)el.getDescription(), (int)35)) + "</text>");
                            }
                            Unit unit = Unit.INSTANCE;
                            break;
                        }
                        case 2: {
                            Position p = (Position)pos.get(el.getId());
                            svg.append("<rect x=\"" + p.getX() + "\" y=\"" + p.getY() + "\" width=\"" + p.getW() + "\" height=\"" + p.getH() + "\" fill=\"" + bg + "\" stroke=\"" + border + "\" stroke-width=\"2\" rx=\"8\"/>");
                            svg.append("<text x=\"" + (p.getX() + p.getW() / 2) + "\" y=\"" + (p.getY() + 30) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"14\" font-weight=\"bold\" fill=\"" + txt + "\">" + this.esc(el.getName()) + "</text>");
                            String tech = ((CharSequence)el.getTechnology()).length() > 0 ? ": " + el.getTechnology() : "";
                            svg.append("<text x=\"" + (p.getX() + p.getW() / 2) + "\" y=\"" + (p.getY() + 50) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"11\" fill=\"" + txt + "\">[Container" + tech + "]</text>");
                            if (((CharSequence)el.getDescription()).length() > 0) {
                                svg.append("<text x=\"" + (p.getX() + p.getW() / 2) + "\" y=\"" + (p.getY() + 75) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"10\" fill=\"" + txt + "\" opacity=\"0.85\">" + this.esc(StringsKt.take((String)el.getDescription(), (int)30)) + "</text>");
                            }
                            Unit unit = Unit.INSTANCE;
                            break;
                        }
                        case 4: {
                            Position p = (Position)pos.get(el.getId());
                            svg.append("<rect x=\"" + p.getX() + "\" y=\"" + p.getY() + "\" width=\"" + p.getW() + "\" height=\"" + p.getH() + "\" fill=\"" + bg + "\" stroke=\"" + border + "\" stroke-width=\"2\" rx=\"8\"/>");
                            svg.append("<text x=\"" + (p.getX() + p.getW() / 2) + "\" y=\"" + (p.getY() + 30) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"13\" font-weight=\"bold\" fill=\"" + txt + "\">" + this.esc(el.getName()) + "</text>");
                            String tech = ((CharSequence)el.getTechnology()).length() > 0 ? ": " + el.getTechnology() : "";
                            StringBuilder stringBuilder = svg.append("<text x=\"" + (p.getX() + p.getW() / 2) + "\" y=\"" + (p.getY() + 48) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"10\" fill=\"" + txt + "\">[Component" + tech + "]</text>");
                            break;
                        }
                        default: {
                            Position p = (Position)pos.get(el.getId());
                            svg.append("<rect x=\"" + p.getX() + "\" y=\"" + p.getY() + "\" width=\"" + p.getW() + "\" height=\"" + p.getH() + "\" fill=\"#ddd\" stroke=\"#999\" stroke-width=\"2\" rx=\"8\"/>");
                            StringBuilder stringBuilder = svg.append("<text x=\"" + (p.getX() + p.getW() / 2) + "\" y=\"" + (p.getY() + p.getH() / 2) + "\" text-anchor=\"middle\" font-family=\"Arial\" font-size=\"14\" fill=\"#333\">" + this.esc(el.getName()) + "</text>");
                        }
                    }
                }
                object = view;
                if (object == null || (object = ((View)object).getTitle()) == null) break block27;
                Object it5 = object2 = object;
                boolean bl = false;
                object = ((CharSequence)it5).length() > 0 ? object2 : null;
                if (object != null) break block28;
            }
            View view2 = view;
            ViewType viewType = view2 != null ? view2.getType() : null;
            switch (viewType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[viewType.ordinal()]) {
                case 1: {
                    object = "System Landscape";
                    break;
                }
                case 2: {
                    object = "System Context";
                    break;
                }
                case 3: {
                    object = "Container Diagram";
                    break;
                }
                case 4: {
                    object = "Component Diagram";
                    break;
                }
                default: {
                    object = "Structurizr Diagram";
                }
            }
        }
        Object title = object;
        return "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><style>\n*{margin:0;padding:0;box-sizing:border-box}\nbody{font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,sans-serif;background:#fff;overflow:auto}\n.container{padding:20px;min-height:100vh}\n.title{font-size:24px;font-weight:600;color:#333;margin-bottom:20px;padding-left:10px;border-left:4px solid #1168bd}\nsvg{display:block;background:#fff;border:1px solid #e0e0e0;border-radius:8px}\n.legend{margin-top:20px;padding:15px;background:#f8f9fa;border-radius:8px;display:flex;gap:20px;flex-wrap:wrap}\n.legend-item{display:flex;align-items:center;gap:8px;font-size:12px;color:#666}\n.legend-box{width:20px;height:20px;border-radius:4px;border:2px solid}\n</style></head><body><div class=\"container\">\n<div class=\"title\">" + this.esc((String)title) + "</div>\n<svg width=\"" + svgW + "\" height=\"" + svgH + "\" viewBox=\"0 0 " + svgW + " " + svgH + "\">\n<defs><marker id=\"arr\" markerWidth=\"10\" markerHeight=\"7\" refX=\"9\" refY=\"3.5\" orient=\"auto\"><polygon points=\"0 0, 10 3.5, 0 7\" fill=\"#707070\"/></marker></defs>\n" + svg + "\n</svg>\n<div class=\"legend\">\n<div class=\"legend-item\"><div class=\"legend-box\" style=\"background:#08427b;border-color:#052e56\"></div><span>Person</span></div>\n<div class=\"legend-item\"><div class=\"legend-box\" style=\"background:#1168bd;border-color:#0b4884\"></div><span>Software System</span></div>\n<div class=\"legend-item\"><div class=\"legend-box\" style=\"background:#438dd5;border-color:#2e6295\"></div><span>Container</span></div>\n<div class=\"legend-item\"><div class=\"legend-box\" style=\"background:#85bbf0;border-color:#5d82a8\"></div><span>Component</span></div>\n</div></div></body></html>";
    }

    private final List<Integer> calculateConnectionPoints(Position s, Position t, int sCx, int sCy, int tCx, int tCy) {
        int dx = tCx - sCx;
        int dy = tCy - sCy;
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                x1 = s.getX() + s.getW();
                y1 = sCy;
                x2 = t.getX();
                y2 = tCy;
            } else {
                x1 = s.getX();
                y1 = sCy;
                x2 = t.getX() + t.getW();
                y2 = tCy;
            }
        } else if (dy > 0) {
            x1 = sCx;
            y1 = s.getY() + s.getH();
            x2 = tCx;
            y2 = t.getY();
        } else {
            x1 = sCx;
            y1 = s.getY();
            x2 = tCx;
            y2 = t.getY() + t.getH();
        }
        Integer[] objectArray = new Integer[]{x1, y1, x2, y2};
        return (List<Integer>)(List)CollectionsKt.listOf((Object[])objectArray);
    }

    private final Triple<String, String, String> getColors(ElementType type) {
        return switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 3 -> new Triple((Object)"#08427b", (Object)"#052e56", (Object)"#ffffff");
            case 1 -> new Triple((Object)"#1168bd", (Object)"#0b4884", (Object)"#ffffff");
            case 2 -> new Triple((Object)"#438dd5", (Object)"#2e6295", (Object)"#ffffff");
            case 4 -> new Triple((Object)"#85bbf0", (Object)"#5d82a8", (Object)"#000000");
            default -> new Triple((Object)"#ffffff", (Object)"#888888", (Object)"#000000");
        };
    }

    private final String esc(String s) {
        return StringsKt.replace(StringsKt.replace(StringsKt.replace(StringsKt.replace(s, "&", "&amp;", false), "<", "&lt;", false), ">", "&gt;", false), "\"", "&quot;", false);
    }

    public final void clear() {
        this.currentDsl = "";
        this.currentViewKey = null;
        this.showPlaceholder();
    }

    public void dispose() {
        this.browser = null;
    }

    private static final void updatePreview$lambda$2$lambda$1(StructurizrPreviewPanel this$0, String $html) {
        block0: {
            JBCefBrowser jBCefBrowser = this$0.browser;
            if (jBCefBrowser == null) break block0;
            jBCefBrowser.loadHTML($html);
        }
    }

    private static final void updatePreview$lambda$2(StructurizrPreviewPanel this$0, String $dslContent, String $viewKey) {
        try {
            ParsedModel model = this$0.parseDsl($dslContent);
            String html = this$0.generateHtml(model, $viewKey);
            ApplicationManager.getApplication().invokeLater(() -> StructurizrPreviewPanel.updatePreview$lambda$2$lambda$1(this$0, html));
        }
        catch (Exception e) {
            this$0.log.warn("Parse error", (Throwable)e);
            String string = e.getMessage();
            if (string == null) {
                string = "Unknown error";
            }
            this$0.showError(string);
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003JG\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r\u00a8\u0006!"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$Element;", "", "id", "", "name", "description", "type", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ElementType;", "technology", "parentId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ElementType;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getDescription", "getType", "()Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ElementType;", "getTechnology", "getParentId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "structurizr-dsl-plugin"})
    public static final class Element {
        @NotNull
        private final String id;
        @NotNull
        private final String name;
        @NotNull
        private final String description;
        @NotNull
        private final ElementType type;
        @NotNull
        private final String technology;
        @Nullable
        private final String parentId;

        public Element(@NotNull String id, @NotNull String name, @NotNull String description, @NotNull ElementType type, @NotNull String technology, @Nullable String parentId) {
            Intrinsics.checkNotNullParameter((Object)id, (String)"id");
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)description, (String)"description");
            Intrinsics.checkNotNullParameter((Object)((Object)type), (String)"type");
            Intrinsics.checkNotNullParameter((Object)technology, (String)"technology");
            this.id = id;
            this.name = name;
            this.description = description;
            this.type = type;
            this.technology = technology;
            this.parentId = parentId;
        }

        public /* synthetic */ Element(String string, String string2, String string3, ElementType elementType, String string4, String string5, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this(string, string2, (n & 4) != 0 ? "" : string3, elementType, (n & 0x10) != 0 ? "" : string4, (n & 0x20) != 0 ? null : string5);
        }

        @NotNull
        public final String getId() {
            return this.id;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final String getDescription() {
            return this.description;
        }

        @NotNull
        public final ElementType getType() {
            return this.type;
        }

        @NotNull
        public final String getTechnology() {
            return this.technology;
        }

        @Nullable
        public final String getParentId() {
            return this.parentId;
        }

        @NotNull
        public final String component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.name;
        }

        @NotNull
        public final String component3() {
            return this.description;
        }

        @NotNull
        public final ElementType component4() {
            return this.type;
        }

        @NotNull
        public final String component5() {
            return this.technology;
        }

        @Nullable
        public final String component6() {
            return this.parentId;
        }

        @NotNull
        public final Element copy(@NotNull String id, @NotNull String name, @NotNull String description, @NotNull ElementType type, @NotNull String technology, @Nullable String parentId) {
            Intrinsics.checkNotNullParameter((Object)id, (String)"id");
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)description, (String)"description");
            Intrinsics.checkNotNullParameter((Object)((Object)type), (String)"type");
            Intrinsics.checkNotNullParameter((Object)technology, (String)"technology");
            return new Element(id, name, description, type, technology, parentId);
        }

        public static /* synthetic */ Element copy$default(Element element, String string, String string2, String string3, ElementType elementType, String string4, String string5, int n, Object object) {
            if ((n & 1) != 0) {
                string = element.id;
            }
            if ((n & 2) != 0) {
                string2 = element.name;
            }
            if ((n & 4) != 0) {
                string3 = element.description;
            }
            if ((n & 8) != 0) {
                elementType = element.type;
            }
            if ((n & 0x10) != 0) {
                string4 = element.technology;
            }
            if ((n & 0x20) != 0) {
                string5 = element.parentId;
            }
            return element.copy(string, string2, string3, elementType, string4, string5);
        }

        @NotNull
        public String toString() {
            return "Element(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", type=" + this.type + ", technology=" + this.technology + ", parentId=" + this.parentId + ")";
        }

        public int hashCode() {
            int result = this.id.hashCode();
            result = result * 31 + this.name.hashCode();
            result = result * 31 + this.description.hashCode();
            result = result * 31 + this.type.hashCode();
            result = result * 31 + this.technology.hashCode();
            result = result * 31 + (this.parentId == null ? 0 : this.parentId.hashCode());
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Element)) {
                return false;
            }
            Element element = (Element)other;
            if (!Intrinsics.areEqual((Object)this.id, (Object)element.id)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.name, (Object)element.name)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.description, (Object)element.description)) {
                return false;
            }
            if (this.type != element.type) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.technology, (Object)element.technology)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.parentId, (Object)element.parentId);
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ElementType;", "", "<init>", "(Ljava/lang/String;I)V", "PERSON", "SOFTWARE_SYSTEM", "CONTAINER", "COMPONENT", "DEPLOYMENT_NODE", "INFRASTRUCTURE_NODE", "structurizr-dsl-plugin"})
    public enum ElementType {
        PERSON,
        SOFTWARE_SYSTEM,
        CONTAINER,
        COMPONENT,
        DEPLOYMENT_NODE,
        INFRASTRUCTURE_NODE;

        @NotNull
        public static EnumEntries<ElementType> getEntries() {
            return EnumEntriesKt.enumEntries((Enum[])values());
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0003J9\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u001a"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ParsedModel;", "", "elements", "", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$Element;", "relationships", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$Relationship;", "views", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$View;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getElements", "()Ljava/util/List;", "getRelationships", "getViews", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "structurizr-dsl-plugin"})
    public static final class ParsedModel {
        @NotNull
        private final List<Element> elements;
        @NotNull
        private final List<Relationship> relationships;
        @NotNull
        private final List<View> views;

        public ParsedModel(@NotNull List<Element> elements, @NotNull List<Relationship> relationships, @NotNull List<View> views) {
            Intrinsics.checkNotNullParameter(elements, (String)"elements");
            Intrinsics.checkNotNullParameter(relationships, (String)"relationships");
            Intrinsics.checkNotNullParameter(views, (String)"views");
            this.elements = elements;
            this.relationships = relationships;
            this.views = views;
        }

        public /* synthetic */ ParsedModel(List list, List list2, List list3, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this((n & 1) != 0 ? new ArrayList() : list, (n & 2) != 0 ? new ArrayList() : list2, (n & 4) != 0 ? new ArrayList() : list3);
        }

        @NotNull
        public final List<Element> getElements() {
            return this.elements;
        }

        @NotNull
        public final List<Relationship> getRelationships() {
            return this.relationships;
        }

        @NotNull
        public final List<View> getViews() {
            return this.views;
        }

        @NotNull
        public final List<Element> component1() {
            return this.elements;
        }

        @NotNull
        public final List<Relationship> component2() {
            return this.relationships;
        }

        @NotNull
        public final List<View> component3() {
            return this.views;
        }

        @NotNull
        public final ParsedModel copy(@NotNull List<Element> elements, @NotNull List<Relationship> relationships, @NotNull List<View> views) {
            Intrinsics.checkNotNullParameter(elements, (String)"elements");
            Intrinsics.checkNotNullParameter(relationships, (String)"relationships");
            Intrinsics.checkNotNullParameter(views, (String)"views");
            return new ParsedModel(elements, relationships, views);
        }

        public static /* synthetic */ ParsedModel copy$default(ParsedModel parsedModel, List list, List list2, List list3, int n, Object object) {
            if ((n & 1) != 0) {
                list = parsedModel.elements;
            }
            if ((n & 2) != 0) {
                list2 = parsedModel.relationships;
            }
            if ((n & 4) != 0) {
                list3 = parsedModel.views;
            }
            return parsedModel.copy(list, list2, list3);
        }

        @NotNull
        public String toString() {
            return "ParsedModel(elements=" + this.elements + ", relationships=" + this.relationships + ", views=" + this.views + ")";
        }

        public int hashCode() {
            int result = ((Object)this.elements).hashCode();
            result = result * 31 + ((Object)this.relationships).hashCode();
            result = result * 31 + ((Object)this.views).hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParsedModel)) {
                return false;
            }
            ParsedModel parsedModel = (ParsedModel)other;
            if (!Intrinsics.areEqual(this.elements, parsedModel.elements)) {
                return false;
            }
            if (!Intrinsics.areEqual(this.relationships, parsedModel.relationships)) {
                return false;
            }
            return Intrinsics.areEqual(this.views, parsedModel.views);
        }

        public ParsedModel() {
            this(null, null, null, 7, null);
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0019"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$Position;", "", "x", "", "y", "w", "h", "<init>", "(IIII)V", "getX", "()I", "getY", "getW", "getH", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "structurizr-dsl-plugin"})
    public static final class Position {
        private final int x;
        private final int y;
        private final int w;
        private final int h;

        public Position(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }

        public final int getW() {
            return this.w;
        }

        public final int getH() {
            return this.h;
        }

        public final int component1() {
            return this.x;
        }

        public final int component2() {
            return this.y;
        }

        public final int component3() {
            return this.w;
        }

        public final int component4() {
            return this.h;
        }

        @NotNull
        public final Position copy(int x, int y, int w, int h) {
            return new Position(x, y, w, h);
        }

        public static /* synthetic */ Position copy$default(Position position, int n, int n2, int n3, int n4, int n5, Object object) {
            if ((n5 & 1) != 0) {
                n = position.x;
            }
            if ((n5 & 2) != 0) {
                n2 = position.y;
            }
            if ((n5 & 4) != 0) {
                n3 = position.w;
            }
            if ((n5 & 8) != 0) {
                n4 = position.h;
            }
            return position.copy(n, n2, n3, n4);
        }

        @NotNull
        public String toString() {
            return "Position(x=" + this.x + ", y=" + this.y + ", w=" + this.w + ", h=" + this.h + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.x);
            result = result * 31 + Integer.hashCode(this.y);
            result = result * 31 + Integer.hashCode(this.w);
            result = result * 31 + Integer.hashCode(this.h);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Position)) {
                return false;
            }
            Position position = (Position)other;
            if (this.x != position.x) {
                return false;
            }
            if (this.y != position.y) {
                return false;
            }
            if (this.w != position.w) {
                return false;
            }
            return this.h == position.h;
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0019"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$Relationship;", "", "sourceId", "", "targetId", "description", "technology", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSourceId", "()Ljava/lang/String;", "getTargetId", "getDescription", "getTechnology", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "structurizr-dsl-plugin"})
    public static final class Relationship {
        @NotNull
        private final String sourceId;
        @NotNull
        private final String targetId;
        @NotNull
        private final String description;
        @NotNull
        private final String technology;

        public Relationship(@NotNull String sourceId, @NotNull String targetId, @NotNull String description, @NotNull String technology) {
            Intrinsics.checkNotNullParameter((Object)sourceId, (String)"sourceId");
            Intrinsics.checkNotNullParameter((Object)targetId, (String)"targetId");
            Intrinsics.checkNotNullParameter((Object)description, (String)"description");
            Intrinsics.checkNotNullParameter((Object)technology, (String)"technology");
            this.sourceId = sourceId;
            this.targetId = targetId;
            this.description = description;
            this.technology = technology;
        }

        public /* synthetic */ Relationship(String string, String string2, String string3, String string4, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this(string, string2, (n & 4) != 0 ? "" : string3, (n & 8) != 0 ? "" : string4);
        }

        @NotNull
        public final String getSourceId() {
            return this.sourceId;
        }

        @NotNull
        public final String getTargetId() {
            return this.targetId;
        }

        @NotNull
        public final String getDescription() {
            return this.description;
        }

        @NotNull
        public final String getTechnology() {
            return this.technology;
        }

        @NotNull
        public final String component1() {
            return this.sourceId;
        }

        @NotNull
        public final String component2() {
            return this.targetId;
        }

        @NotNull
        public final String component3() {
            return this.description;
        }

        @NotNull
        public final String component4() {
            return this.technology;
        }

        @NotNull
        public final Relationship copy(@NotNull String sourceId, @NotNull String targetId, @NotNull String description, @NotNull String technology) {
            Intrinsics.checkNotNullParameter((Object)sourceId, (String)"sourceId");
            Intrinsics.checkNotNullParameter((Object)targetId, (String)"targetId");
            Intrinsics.checkNotNullParameter((Object)description, (String)"description");
            Intrinsics.checkNotNullParameter((Object)technology, (String)"technology");
            return new Relationship(sourceId, targetId, description, technology);
        }

        public static /* synthetic */ Relationship copy$default(Relationship relationship, String string, String string2, String string3, String string4, int n, Object object) {
            if ((n & 1) != 0) {
                string = relationship.sourceId;
            }
            if ((n & 2) != 0) {
                string2 = relationship.targetId;
            }
            if ((n & 4) != 0) {
                string3 = relationship.description;
            }
            if ((n & 8) != 0) {
                string4 = relationship.technology;
            }
            return relationship.copy(string, string2, string3, string4);
        }

        @NotNull
        public String toString() {
            return "Relationship(sourceId=" + this.sourceId + ", targetId=" + this.targetId + ", description=" + this.description + ", technology=" + this.technology + ")";
        }

        public int hashCode() {
            int result = this.sourceId.hashCode();
            result = result * 31 + this.targetId.hashCode();
            result = result * 31 + this.description.hashCode();
            result = result * 31 + this.technology.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Relationship)) {
                return false;
            }
            Relationship relationship = (Relationship)other;
            if (!Intrinsics.areEqual((Object)this.sourceId, (Object)relationship.sourceId)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.targetId, (Object)relationship.targetId)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.description, (Object)relationship.description)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.technology, (Object)relationship.technology);
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003JM\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\n\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0017\u00a8\u0006%"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$View;", "", "key", "", "type", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ViewType;", "title", "scope", "includes", "", "autoLayout", "<init>", "(Ljava/lang/String;Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ViewType;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "getType", "()Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ViewType;", "getTitle", "getScope", "getIncludes", "()Ljava/util/List;", "getAutoLayout", "setAutoLayout", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "structurizr-dsl-plugin"})
    public static final class View {
        @NotNull
        private final String key;
        @NotNull
        private final ViewType type;
        @NotNull
        private final String title;
        @Nullable
        private final String scope;
        @NotNull
        private final List<String> includes;
        @NotNull
        private String autoLayout;

        public View(@NotNull String key, @NotNull ViewType type, @NotNull String title, @Nullable String scope, @NotNull List<String> includes, @NotNull String autoLayout) {
            Intrinsics.checkNotNullParameter((Object)key, (String)"key");
            Intrinsics.checkNotNullParameter((Object)((Object)type), (String)"type");
            Intrinsics.checkNotNullParameter((Object)title, (String)"title");
            Intrinsics.checkNotNullParameter(includes, (String)"includes");
            Intrinsics.checkNotNullParameter((Object)autoLayout, (String)"autoLayout");
            this.key = key;
            this.type = type;
            this.title = title;
            this.scope = scope;
            this.includes = includes;
            this.autoLayout = autoLayout;
        }

        public /* synthetic */ View(String string, ViewType viewType, String string2, String string3, List list, String string4, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this(string, viewType, (n & 4) != 0 ? "" : string2, (n & 8) != 0 ? null : string3, (n & 0x10) != 0 ? new ArrayList() : list, (n & 0x20) != 0 ? "tb" : string4);
        }

        @NotNull
        public final String getKey() {
            return this.key;
        }

        @NotNull
        public final ViewType getType() {
            return this.type;
        }

        @NotNull
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getScope() {
            return this.scope;
        }

        @NotNull
        public final List<String> getIncludes() {
            return this.includes;
        }

        @NotNull
        public final String getAutoLayout() {
            return this.autoLayout;
        }

        public final void setAutoLayout(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.autoLayout = string;
        }

        @NotNull
        public final String component1() {
            return this.key;
        }

        @NotNull
        public final ViewType component2() {
            return this.type;
        }

        @NotNull
        public final String component3() {
            return this.title;
        }

        @Nullable
        public final String component4() {
            return this.scope;
        }

        @NotNull
        public final List<String> component5() {
            return this.includes;
        }

        @NotNull
        public final String component6() {
            return this.autoLayout;
        }

        @NotNull
        public final View copy(@NotNull String key, @NotNull ViewType type, @NotNull String title, @Nullable String scope, @NotNull List<String> includes, @NotNull String autoLayout) {
            Intrinsics.checkNotNullParameter((Object)key, (String)"key");
            Intrinsics.checkNotNullParameter((Object)((Object)type), (String)"type");
            Intrinsics.checkNotNullParameter((Object)title, (String)"title");
            Intrinsics.checkNotNullParameter(includes, (String)"includes");
            Intrinsics.checkNotNullParameter((Object)autoLayout, (String)"autoLayout");
            return new View(key, type, title, scope, includes, autoLayout);
        }

        public static /* synthetic */ View copy$default(View view, String string, ViewType viewType, String string2, String string3, List list, String string4, int n, Object object) {
            if ((n & 1) != 0) {
                string = view.key;
            }
            if ((n & 2) != 0) {
                viewType = view.type;
            }
            if ((n & 4) != 0) {
                string2 = view.title;
            }
            if ((n & 8) != 0) {
                string3 = view.scope;
            }
            if ((n & 0x10) != 0) {
                list = view.includes;
            }
            if ((n & 0x20) != 0) {
                string4 = view.autoLayout;
            }
            return view.copy(string, viewType, string2, string3, list, string4);
        }

        @NotNull
        public String toString() {
            return "View(key=" + this.key + ", type=" + this.type + ", title=" + this.title + ", scope=" + this.scope + ", includes=" + this.includes + ", autoLayout=" + this.autoLayout + ")";
        }

        public int hashCode() {
            int result = this.key.hashCode();
            result = result * 31 + this.type.hashCode();
            result = result * 31 + this.title.hashCode();
            result = result * 31 + (this.scope == null ? 0 : this.scope.hashCode());
            result = result * 31 + ((Object)this.includes).hashCode();
            result = result * 31 + this.autoLayout.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof View)) {
                return false;
            }
            View view = (View)other;
            if (!Intrinsics.areEqual((Object)this.key, (Object)view.key)) {
                return false;
            }
            if (this.type != view.type) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.title, (Object)view.title)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.scope, (Object)view.scope)) {
                return false;
            }
            if (!Intrinsics.areEqual(this.includes, view.includes)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.autoLayout, (Object)view.autoLayout);
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel$ViewType;", "", "<init>", "(Ljava/lang/String;I)V", "SYSTEM_LANDSCAPE", "SYSTEM_CONTEXT", "CONTAINER", "COMPONENT", "DEPLOYMENT", "structurizr-dsl-plugin"})
    public enum ViewType {
        SYSTEM_LANDSCAPE,
        SYSTEM_CONTEXT,
        CONTAINER,
        COMPONENT,
        DEPLOYMENT;

        @NotNull
        public static EnumEntries<ViewType> getEntries() {
            return EnumEntriesKt.enumEntries((Enum[])values());
        }
    }

    @Metadata(mv={2, 0, 0}, k=3, xi=48)
    public final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] nArray = new int[ElementType.values().length];
            try {
                nArray[ElementType.SOFTWARE_SYSTEM.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ElementType.CONTAINER.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ElementType.PERSON.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ElementType.COMPONENT.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
            nArray = new int[ViewType.values().length];
            try {
                nArray[ViewType.SYSTEM_LANDSCAPE.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ViewType.SYSTEM_CONTEXT.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ViewType.CONTAINER.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ViewType.COMPONENT.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ViewType.DEPLOYMENT.ordinal()] = 5;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$1 = nArray;
        }
    }
}
