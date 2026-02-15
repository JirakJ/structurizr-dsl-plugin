/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.components.Service
 *  com.intellij.openapi.components.Service$Level
 *  com.intellij.openapi.diagnostic.Logger
 *  com.intellij.openapi.project.Project
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.Pair
 *  kotlin.TuplesKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.io.FilesKt
 *  kotlin.io.TextStreamsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.sequences.Sequence
 *  kotlin.text.Charsets
 *  kotlin.text.MatchResult
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.preview;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.jakubjirak.structurizr.settings.StructurizrProjectSettings;
import com.jakubjirak.structurizr.settings.StructurizrSettings;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service(value={Service.Level.PROJECT})
@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u0000 92\u00020\u0001:\u0006456789B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0012J$\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0002J\u001a\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0012H\u0002J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001aH\u0002J0\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020!H\u0002J\b\u0010%\u001a\u00020\u0012H\u0002JD\u0010&\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0'2\u0006\u0010(\u001a\u00020!2\u0006\u0010)\u001a\u00020!2\u0006\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020!2\u0006\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020!H\u0002J\u0010\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u0012H\u0002J\u0018\u00100\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u00122\u0006\u00101\u001a\u00020!H\u0002J\u0018\u00102\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u00103\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00070\u0007\u00a2\u0006\u0002\b\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006:"}, d2={"Lcom/jakubjirak/structurizr/preview/DiagramRendererService;", "", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "log", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "settings", "Lcom/jakubjirak/structurizr/settings/StructurizrSettings;", "getSettings", "()Lcom/jakubjirak/structurizr/settings/StructurizrSettings;", "projectSettings", "Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettings;", "getProjectSettings", "()Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettings;", "renderToSvg", "", "dslContent", "viewKey", "renderWithCli", "cliPath", "renderWithBuiltIn", "findStructurizrCli", "parseDslContent", "Lcom/jakubjirak/structurizr/preview/DiagramRendererService$DiagramModel;", "generateSvg", "model", "generateElementSvg", "element", "Lcom/jakubjirak/structurizr/preview/DiagramRendererService$DiagramElement;", "x", "", "y", "width", "height", "generateEmptySvg", "calculateEdgePoint", "Lkotlin/Pair;", "fromX", "fromY", "toX", "toY", "halfWidth", "halfHeight", "escapeXml", "text", "truncate", "maxLength", "renderDiagram", "viewType", "DiagramModel", "DiagramElement", "DiagramRelationship", "Position", "ElementType", "Companion", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nDiagramRendererService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DiagramRendererService.kt\ncom/jakubjirak/structurizr/preview/DiagramRendererService\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,455:1\n1#2:456\n288#3,2:457\n1864#3,3:469\n1855#3,2:472\n1864#3,3:474\n1295#4,2:459\n1295#4,2:461\n1295#4,2:463\n1295#4,2:465\n1295#4,2:467\n*S KotlinDebug\n*F\n+ 1 DiagramRendererService.kt\ncom/jakubjirak/structurizr/preview/DiagramRendererService\n*L\n137#1:457,2\n257#1:469,3\n272#1:472,2\n294#1:474,3\n155#1:459,2\n167#1:461,2\n179#1:463,2\n192#1:465,2\n205#1:467,2\n*E\n"})
public final class DiagramRendererService {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Project project;
    @NotNull
    private final Logger log;

    public DiagramRendererService(@NotNull Project project) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        this.project = project;
        Logger logger = Logger.getInstance(DiagramRendererService.class);
        Intrinsics.checkNotNullExpressionValue((Object)logger, (String)"getInstance(...)");
        this.log = logger;
    }

    private final StructurizrSettings getSettings() {
        return StructurizrSettings.Companion.getInstance();
    }

    private final StructurizrProjectSettings getProjectSettings() {
        return StructurizrProjectSettings.Companion.getInstance(this.project);
    }

    @Nullable
    public final String renderToSvg(@NotNull String dslContent, @Nullable String viewKey) {
        String string;
        Intrinsics.checkNotNullParameter((Object)dslContent, (String)"dslContent");
        try {
            String cliPath = this.findStructurizrCli();
            string = cliPath != null ? this.renderWithCli(dslContent, viewKey, cliPath) : this.renderWithBuiltIn(dslContent, viewKey);
        }
        catch (Exception e) {
            this.log.warn("Failed to render diagram", (Throwable)e);
            string = null;
        }
        return string;
    }

    public static /* synthetic */ String renderToSvg$default(DiagramRendererService diagramRendererService, String string, String string2, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = null;
        }
        return diagramRendererService.renderToSvg(string, string2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final String renderWithCli(String dslContent, String viewKey, String cliPath) throws Exception {
        Path tempDir = Files.createTempDirectory("structurizr-preview", new FileAttribute[0]);
        File dslFile = tempDir.resolve("workspace.dsl").toFile();
        try {
            Intrinsics.checkNotNull((Object)dslFile);
            FilesKt.writeText((File)dslFile, (String)dslContent, Charsets.UTF_8);
            Object object = new String[]{cliPath, "export", "-workspace", dslFile.getAbsolutePath(), "-format", "plantuml"};
            List command = CollectionsKt.mutableListOf((Object[])object);
            object = viewKey;
            if (!(object == null || StringsKt.isBlank((CharSequence)object))) {
                command.add("-view");
                command.add(viewKey);
            }
            Process process = new ProcessBuilder(command).directory(tempDir.toFile()).redirectErrorStream(true).start();
            InputStream inputStream = process.getInputStream();
            Intrinsics.checkNotNullExpressionValue((Object)inputStream, (String)"getInputStream(...)");
            InputStream inputStream2 = inputStream;
            Charset charset = Charsets.UTF_8;
            Reader reader = new InputStreamReader(inputStream2, charset);
            int n = 8192;
            String output = TextStreamsKt.readText((Reader)(reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, n)));
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                File svgFile;
                File file;
                block9: {
                    File[] fileArray = tempDir.toFile().listFiles();
                    if (fileArray != null) {
                        File[] fileArray2 = fileArray;
                        int n2 = fileArray2.length;
                        for (int i = 0; i < n2; ++i) {
                            File file2;
                            File it = file2 = fileArray2[i];
                            boolean bl = false;
                            Intrinsics.checkNotNull((Object)it);
                            if (!Intrinsics.areEqual((Object)FilesKt.getExtension((File)it), (Object)"svg")) continue;
                            file = file2;
                            break block9;
                        }
                        file = null;
                    } else {
                        file = null;
                    }
                }
                File file3 = svgFile = file;
                String svgResult = file3 != null ? FilesKt.readText(file3, Charsets.UTF_8) : null;
                return svgResult;
            }
            this.log.warn("Structurizr CLI failed with exit code " + exitCode + ": " + output);
            return null;
        }
        finally {
            File file = tempDir.toFile();
            Intrinsics.checkNotNullExpressionValue((Object)file, (String)"toFile(...)");
            FilesKt.deleteRecursively((File)file);
        }
    }

    private final String renderWithBuiltIn(String dslContent, String viewKey) {
        DiagramModel elements = this.parseDslContent(dslContent);
        return this.generateSvg(elements);
    }


    private final String findStructurizrCli() {
        Object v4;
        block5: {
            String string;
            String projectCliPath = this.getProjectSettings().getEffectiveCliPath();
            if (((CharSequence)projectCliPath).length() > 0 && new File(projectCliPath).exists()) {
                return projectCliPath;
            }
            String globalCliPath = this.getSettings().getStructurizrCliPath();
            if (((CharSequence)globalCliPath).length() > 0 && new File(globalCliPath).exists()) {
                return globalCliPath;
            }
            Object[] objectArray = new String[4];
            objectArray[0] = "/usr/local/bin/structurizr-cli";
            objectArray[1] = "/opt/structurizr-cli/structurizr-cli";
            objectArray[2] = System.getProperty("user.home") + "/.structurizr/structurizr-cli";
            Object[] objectArray2 = objectArray;
            int n = 3;
            String string2 = System.getenv("STRUCTURIZR_CLI_HOME");
            if (string2 != null) {
                String string3 = string2;
                int n2 = n;
                Object[] objectArray3 = objectArray2;
                boolean bl = false;
                String string4 = string3 + "/structurizr-cli";
                objectArray2 = objectArray3;
                n = n2;
                string = string4;
            } else {
                string = null;
            }
            objectArray2[n] = string;
            List possiblePaths = CollectionsKt.filterNotNull((Iterable)CollectionsKt.listOf((Object[])objectArray));
            Iterable $this$firstOrNull$iv = possiblePaths;
            boolean $i$f$firstOrNull = false;
            for (Object element$iv : $this$firstOrNull$iv) {
                String path = (String)element$iv;
                boolean bl = false;
                if (!(new File(path).exists() && new File(path).canExecute())) continue;
                v4 = element$iv;
                break block5;
            }
            v4 = null;
        }
        return (String)v4;
    }

    private final DiagramModel parseDslContent(String dslContent) {
        String string;
        String string2;
        Object object;
        Object object2;
        Object object3;
        CharSequence charSequence;
        MatchResult match;
        DiagramModel model = new DiagramModel(null, null, null, null, 15, null);
        MatchResult workspaceMatch = new Regex("workspace\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?").find((CharSequence)dslContent, 0);
        Object object4 = workspaceMatch;
        if (object4 == null || (object4 = ((MatchResult)object4).getGroupValues()) == null || (object4 = (String)((List)object4).get(1)) == null) {
            object4 = "Workspace";
        }
        model.setWorkspaceName((String)object4);
        Object object5 = workspaceMatch;
        if (object5 == null || (object5 = ((MatchResult)object5).getGroupValues()) == null || (object5 = (String)((List)object5).get(2)) == null) {
            object5 = "";
        }
        model.setWorkspaceDescription((String)object5);
        Sequence $this$forEach$iv = new Regex("(?:(\\w+)\\s*=\\s*)?person\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?").findAll((CharSequence)dslContent, 0);
        boolean $i$f$forEach = false;
        for (Object element$iv : SequencesKt.toList($this$forEach$iv)) {
            Object object6;
            match = (MatchResult)element$iv;
            boolean bl = false;
            List<DiagramElement> list = model.getElements();
            charSequence = (CharSequence)match.getGroupValues().get(1);
            if (charSequence.length() == 0) {
                object3 = list;
                boolean bl2 = false;
                object6 = "person_" + model.getElements().size();
                list = (List<DiagramElement>)object3;
            } else {
                object6 = charSequence;
            }
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n = 16;
            String string3 = (String)match.getGroupValues().get(4);
            String string4 = null;
            ElementType elementType = ElementType.PERSON;
            String string5 = (String)match.getGroupValues().get(3);
            String string6 = (String)match.getGroupValues().get(2);
            String string7 = (String)object6;
            list.add(new DiagramElement(string7, string6, string5, elementType, string4, string3, n, defaultConstructorMarker));
        }
        $this$forEach$iv = new Regex("(?:(\\w+)\\s*=\\s*)?softwareSystem\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?").findAll((CharSequence)dslContent, 0);
        $i$f$forEach = false;
        for (Object element$iv : SequencesKt.toList($this$forEach$iv)) {
            Object object7;
            match = (MatchResult)element$iv;
            boolean bl = false;
            List<DiagramElement> list = model.getElements();
            charSequence = (CharSequence)match.getGroupValues().get(1);
            if (charSequence.length() == 0) {
                object3 = list;
                boolean bl3 = false;
                object7 = "system_" + model.getElements().size();
                list = (List<DiagramElement>)object3;
            } else {
                object7 = charSequence;
            }
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n = 16;
            String string8 = (String)match.getGroupValues().get(4);
            String string9 = null;
            ElementType elementType = ElementType.SOFTWARE_SYSTEM;
            String string10 = (String)match.getGroupValues().get(3);
            String string11 = (String)match.getGroupValues().get(2);
            String string12 = (String)object7;
            list.add(new DiagramElement(string12, string11, string10, elementType, string9, string8, n, defaultConstructorMarker));
        }
        $this$forEach$iv = new Regex("(?:(\\w+)\\s*=\\s*)?container\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?").findAll((CharSequence)dslContent, 0);
        $i$f$forEach = false;
        for (Object element$iv : SequencesKt.toList($this$forEach$iv)) {
            Object object8;
            match = (MatchResult)element$iv;
            boolean bl = false;
            List<DiagramElement> list = model.getElements();
            charSequence = (CharSequence)match.getGroupValues().get(1);
            if (charSequence.length() == 0) {
                object2 = list;
                boolean bl4 = false;
                object8 = "container_" + model.getElements().size();
                list = (List<DiagramElement>)object2;
            } else {
                object8 = charSequence;
            }
            object3 = (String)object8;
            charSequence = (String)match.getGroupValues().get(2);
            String bl4 = (String)match.getGroupValues().get(3);
            String string13 = (String)match.getGroupValues().get(4);
            object = ElementType.CONTAINER;
            string2 = (String)match.getGroupValues().get(5);
            list.add(new DiagramElement((String)object3, (String)charSequence, bl4, (ElementType)((Object)object), string13, string2));
        }
        $this$forEach$iv = new Regex("(?:(\\w+)\\s*=\\s*)?component\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?").findAll((CharSequence)dslContent, 0);
        $i$f$forEach = false;
        for (Object element$iv : SequencesKt.toList($this$forEach$iv)) {
            Object object9;
            match = (MatchResult)element$iv;
            boolean bl = false;
            List<DiagramElement> list = model.getElements();
            charSequence = (CharSequence)match.getGroupValues().get(1);
            if (charSequence.length() == 0) {
                object2 = list;
                boolean bl5 = false;
                object9 = "component_" + model.getElements().size();
                list = (List<DiagramElement>)object2;
            } else {
                object9 = charSequence;
            }
            object3 = (String)object9;
            charSequence = (String)match.getGroupValues().get(2);
            string = (String)match.getGroupValues().get(3);
            String string14 = (String)match.getGroupValues().get(4);
            object = ElementType.COMPONENT;
            string2 = (String)match.getGroupValues().get(5);
            list.add(new DiagramElement((String)object3, (String)charSequence, string, (ElementType)((Object)object), string14, string2));
        }
        $this$forEach$iv = new Regex("(\\w+)\\s*->\\s*(\\w+)(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?(?:\\s+\"([^\"]+)\")?").findAll((CharSequence)dslContent, 0);
        $i$f$forEach = false;
        for (Object element$iv : SequencesKt.toList($this$forEach$iv)) {
            CharSequence charSequence2;
            match = (MatchResult)element$iv;
            boolean bl = false;
            List object10 = model.getRelationships();
            String string15 = (String)match.getGroupValues().get(1);
            Object object11 = (String)match.getGroupValues().get(2);
            charSequence = (CharSequence)match.getGroupValues().get(3);
            if (charSequence.length() == 0) {
                object2 = object11;
                string = string15;
                object = object10;
                boolean bl6 = false;
                String string16 = "uses";
                object10 = (List)object;
                string15 = string;
                object11 = object2;
                charSequence2 = string16;
            } else {
                charSequence2 = charSequence;
            }
            String string17 = (String)match.getGroupValues().get(5);
            String string18 = (String)match.getGroupValues().get(4);
            String string19 = (String)charSequence2;
            Object object12 = object11;
            String string20 = string15;
            object10.add((DiagramRelationship)new DiagramRelationship(string20, (String)object12, string19, string18, string17));
        }
        return model;
    }


    private final String generateSvg(DiagramModel model) {
        Object from;
        int n;
        List<DiagramElement> elements = model.getElements();
        List<DiagramRelationship> relationships = model.getRelationships();
        if (elements.isEmpty()) {
            return this.generateEmptySvg();
        }
        int elementWidth = 200;
        int elementHeight = 120;
        int padding = 50;
        int cols = Math.min(3, elements.size());
        int rows = (elements.size() + cols - 1) / cols;
        int width = cols * (elementWidth + padding) + padding;
        int height = rows * (elementHeight + padding) + padding + 60;
        StringBuilder svg = new StringBuilder();
        svg.append("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" + width + "\" height=\"" + height + "\" viewBox=\"0 0 " + width + " " + height + "\">");
        svg.append("<style>\n            .title { font: bold 18px sans-serif; fill: #333; }\n            .subtitle { font: 12px sans-serif; fill: #666; }\n            .element-name { font: bold 13px sans-serif; fill: white; }\n            .element-type { font: 10px sans-serif; fill: white; opacity: 0.8; }\n            .element-tech { font: 9px sans-serif; fill: white; opacity: 0.7; }\n            .relationship-label { font: 10px sans-serif; fill: #666; }\n        </style>");
        svg.append("<text x=\"" + width / 2 + "\" y=\"30\" class=\"title\" text-anchor=\"middle\">" + this.escapeXml(model.getWorkspaceName()) + "</text>");
        if (((CharSequence)model.getWorkspaceDescription()).length() > 0) {
            svg.append("<text x=\"" + width / 2 + "\" y=\"48\" class=\"subtitle\" text-anchor=\"middle\">" + this.escapeXml(model.getWorkspaceDescription()) + "</text>");
        }
        Map positions = new LinkedHashMap();
        Iterable $this$forEachIndexed$iv = elements;
        boolean $i$f$forEachIndexed = false;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            if ((n = index$iv++) < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            DiagramElement diagramElement = (DiagramElement)item$iv;
            int index = n;
            boolean bl = false;
            int col = index % cols;
            int row = index / cols;
            int x = padding + col * (elementWidth + padding);
            int y = 70 + row * (elementHeight + padding);
            positions.put(diagramElement.getId(), new Position(x, y, elementWidth, elementHeight));
        }
        svg.append("<defs>\n            <marker id=\"arrowhead\" markerWidth=\"10\" markerHeight=\"7\" refX=\"10\" refY=\"3.5\" orient=\"auto\">\n                <polygon points=\"0 0, 10 3.5, 0 7\" fill=\"#707070\"/>\n            </marker>\n        </defs>");
        Iterable $this$forEach$iv = relationships;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            DiagramRelationship rel = (DiagramRelationship)element$iv;
            boolean bl = false;
            from = (Position)positions.get(rel.getSourceId());
            Position to = (Position)positions.get(rel.getTargetId());
            if (from == null || to == null) continue;
            int fromCenterX = ((Position)from).getX() + ((Position)from).getWidth() / 2;
            int fromCenterY = ((Position)from).getY() + ((Position)from).getHeight() / 2;
            int toCenterX = to.getX() + to.getWidth() / 2;
            int toCenterY = to.getY() + to.getHeight() / 2;
            Pair<Integer, Integer> pair = this.calculateEdgePoint(fromCenterX, fromCenterY, toCenterX, toCenterY, ((Position)from).getWidth() / 2, ((Position)from).getHeight() / 2);
            int startX = ((Number)pair.component1()).intValue();
            int startY = ((Number)pair.component2()).intValue();
            Pair<Integer, Integer> pair2 = this.calculateEdgePoint(toCenterX, toCenterY, fromCenterX, fromCenterY, to.getWidth() / 2, to.getHeight() / 2);
            int endX = ((Number)pair2.component1()).intValue();
            int endY = ((Number)pair2.component2()).intValue();
            int midX = (startX + endX) / 2;
            int midY = (startY + endY) / 2;
            svg.append("<line x1=\"" + startX + "\" y1=\"" + startY + "\" x2=\"" + endX + "\" y2=\"" + endY + "\" stroke=\"#707070\" stroke-width=\"2\" stroke-dasharray=\"5,5\" marker-end=\"url(#arrowhead)\"/>");
            svg.append("<text x=\"" + midX + "\" y=\"" + (midY - 5) + "\" class=\"relationship-label\" text-anchor=\"middle\">" + this.escapeXml(rel.getDescription()) + "</text>");
        }
        $this$forEachIndexed$iv = elements;
        $i$f$forEachIndexed = false;
        index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            if ((n = index$iv++) < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            from = (DiagramElement)item$iv;
            int index = n;
            boolean bl = false;
            Object v = positions.get(((DiagramElement)from).getId());
            Intrinsics.checkNotNull(v);
            Position pos = (Position)v;
            svg.append(this.generateElementSvg((DiagramElement)from, pos.getX(), pos.getY(), pos.getWidth(), pos.getHeight()));
        }
        svg.append("</svg>");
        String string = svg.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    private final String generateElementSvg(DiagramElement element, int x, int y, int width, int height) {
        Pair pair = switch (WhenMappings.$EnumSwitchMapping$0[element.getType().ordinal()]) {
            case 1 -> TuplesKt.to((Object)"#08427B", (Object)"#063563");
            case 2 -> TuplesKt.to((Object)"#1168BD", (Object)"#0D5299");
            case 3 -> TuplesKt.to((Object)"#438DD5", (Object)"#3578B9");
            case 4 -> TuplesKt.to((Object)"#85BBF0", (Object)"#6FA8E0");
            default -> throw new NoWhenBranchMatchedException();
        };
        String bgColor = (String)pair.component1();
        String borderColor = (String)pair.component2();
        StringBuilder svg = new StringBuilder();
        if (element.getType() == ElementType.PERSON) {
            int headRadius = 18;
            int headY = y + headRadius + 5;
            int bodyY = y + headRadius * 2 + 10;
            int bodyHeight = height - headRadius * 2 - 15;
            svg.append("<g>\n                <circle cx=\"" + (x + width / 2) + "\" cy=\"" + headY + "\" r=\"" + headRadius + "\" fill=\"" + bgColor + "\" stroke=\"" + borderColor + "\" stroke-width=\"2\"/>\n                <rect x=\"" + x + "\" y=\"" + bodyY + "\" width=\"" + width + "\" height=\"" + bodyHeight + "\" rx=\"4\" fill=\"" + bgColor + "\" stroke=\"" + borderColor + "\" stroke-width=\"2\"/>\n                <text x=\"" + (x + width / 2) + "\" y=\"" + (bodyY + 25) + "\" class=\"element-name\" text-anchor=\"middle\">" + this.escapeXml(this.truncate(element.getName(), 20)) + "</text>\n                <text x=\"" + (x + width / 2) + "\" y=\"" + (bodyY + 42) + "\" class=\"element-type\" text-anchor=\"middle\">[Person]</text>\n            </g>");
        } else {
            svg.append("<g>\n                <rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + width + "\" height=\"" + height + "\" rx=\"4\" fill=\"" + bgColor + "\" stroke=\"" + borderColor + "\" stroke-width=\"2\"/>\n                <text x=\"" + (x + width / 2) + "\" y=\"" + (y + 30) + "\" class=\"element-name\" text-anchor=\"middle\">" + this.escapeXml(this.truncate(element.getName(), 22)) + "</text>\n                <text x=\"" + (x + width / 2) + "\" y=\"" + (y + 48) + "\" class=\"element-type\" text-anchor=\"middle\">[" + element.getType().getDisplayName() + "]</text>\n            ");
            if (((CharSequence)element.getTechnology()).length() > 0) {
                svg.append("<text x=\"" + (x + width / 2) + "\" y=\"" + (y + 65) + "\" class=\"element-tech\" text-anchor=\"middle\">" + this.escapeXml(element.getTechnology()) + "</text>");
            }
            if (((CharSequence)element.getDescription()).length() > 0) {
                int descY = ((CharSequence)element.getTechnology()).length() > 0 ? y + 85 : y + 70;
                svg.append("<text x=\"" + (x + width / 2) + "\" y=\"" + descY + "\" class=\"element-tech\" text-anchor=\"middle\">" + this.escapeXml(this.truncate(element.getDescription(), 30)) + "</text>");
            }
            svg.append("</g>");
        }
        String string = svg.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    private final String generateEmptySvg() {
        return "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"400\" height=\"200\" viewBox=\"0 0 400 200\">\n    <text x=\"200\" y=\"90\" text-anchor=\"middle\" fill=\"#666\" font-size=\"16\" font-family=\"sans-serif\">No elements found</text>\n    <text x=\"200\" y=\"115\" text-anchor=\"middle\" fill=\"#999\" font-size=\"12\" font-family=\"sans-serif\">Add persons, systems, or containers to see them here</text>\n</svg>";
    }

    private final Pair<Integer, Integer> calculateEdgePoint(int fromX, int fromY, int toX, int toY, int halfWidth, int halfHeight) {
        Pair pair;
        int absDy;
        int dx = toX - fromX;
        int dy = toY - fromY;
        if (dx == 0 && dy == 0) {
            return new Pair((Object)fromX, (Object)fromY);
        }
        int absDx = Math.abs(dx);
        if (absDx * halfHeight > (absDy = Math.abs(dy)) * halfWidth) {
            int sign = dx > 0 ? 1 : -1;
            pair = new Pair((Object)(fromX + sign * halfWidth), (Object)(fromY + sign * halfWidth * dy / absDx));
        } else {
            int sign = dy > 0 ? 1 : -1;
            pair = new Pair((Object)(fromX + sign * halfHeight * dx / absDy), (Object)(fromY + sign * halfHeight));
        }
        return pair;
    }

    private final String escapeXml(String text) {
        return StringsKt.replace(StringsKt.replace(StringsKt.replace(StringsKt.replace(text, "&", "&amp;", false), "<", "&lt;", false), ">", "&gt;", false), "\"", "&quot;", false);
    }

    private final String truncate(String text, int maxLength) {
        return text.length() > maxLength ? StringsKt.take((String)text, (int)(maxLength - 3)) + "..." : text;
    }

    @Nullable
    public final String renderDiagram(@NotNull String dslContent, @NotNull String viewType) {
        Intrinsics.checkNotNullParameter((Object)dslContent, (String)"dslContent");
        Intrinsics.checkNotNullParameter((Object)viewType, (String)"viewType");
        String viewKey = switch (viewType) {
            case "System Landscape" -> "landscape";
            case "System Context" -> "systemContext";
            case "Container" -> "container";
            case "Component" -> "component";
            case "Deployment" -> "deployment";
            case "Dynamic" -> "dynamic";
            default -> null;
        };
        return this.renderToSvg(dslContent, viewKey);
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\b"}, d2={"Lcom/jakubjirak/structurizr/preview/DiagramRendererService$Companion;", "", "<init>", "()V", "getInstance", "Lcom/jakubjirak/structurizr/preview/DiagramRendererService;", "project", "Lcom/intellij/openapi/project/Project;", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final DiagramRendererService getInstance(@NotNull Project project) {
            Intrinsics.checkNotNullParameter((Object)project, (String)"project");
            Object object = project.getService(DiagramRendererService.class);
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"getService(...)");
            return (DiagramRendererService)object;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r\u00a8\u0006!"}, d2={"Lcom/jakubjirak/structurizr/preview/DiagramRendererService$DiagramElement;", "", "id", "", "name", "description", "type", "Lcom/jakubjirak/structurizr/preview/DiagramRendererService$ElementType;", "technology", "tags", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jakubjirak/structurizr/preview/DiagramRendererService$ElementType;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getDescription", "getType", "()Lcom/jakubjirak/structurizr/preview/DiagramRendererService$ElementType;", "getTechnology", "getTags", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "structurizr-dsl-plugin"})
    public static final class DiagramElement {
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
        @NotNull
        private final String tags;

        public DiagramElement(@NotNull String id, @NotNull String name, @NotNull String description, @NotNull ElementType type, @NotNull String technology, @NotNull String tags) {
            Intrinsics.checkNotNullParameter((Object)id, (String)"id");
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)description, (String)"description");
            Intrinsics.checkNotNullParameter((Object)((Object)type), (String)"type");
            Intrinsics.checkNotNullParameter((Object)technology, (String)"technology");
            Intrinsics.checkNotNullParameter((Object)tags, (String)"tags");
            this.id = id;
            this.name = name;
            this.description = description;
            this.type = type;
            this.technology = technology;
            this.tags = tags;
        }

        public /* synthetic */ DiagramElement(String string, String string2, String string3, ElementType elementType, String string4, String string5, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this(string, string2, (n & 4) != 0 ? "" : string3, elementType, (n & 0x10) != 0 ? "" : string4, (n & 0x20) != 0 ? "" : string5);
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

        @NotNull
        public final String getTags() {
            return this.tags;
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

        @NotNull
        public final String component6() {
            return this.tags;
        }

        @NotNull
        public final DiagramElement copy(@NotNull String id, @NotNull String name, @NotNull String description, @NotNull ElementType type, @NotNull String technology, @NotNull String tags) {
            Intrinsics.checkNotNullParameter((Object)id, (String)"id");
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)description, (String)"description");
            Intrinsics.checkNotNullParameter((Object)((Object)type), (String)"type");
            Intrinsics.checkNotNullParameter((Object)technology, (String)"technology");
            Intrinsics.checkNotNullParameter((Object)tags, (String)"tags");
            return new DiagramElement(id, name, description, type, technology, tags);
        }

        public static /* synthetic */ DiagramElement copy$default(DiagramElement diagramElement, String string, String string2, String string3, ElementType elementType, String string4, String string5, int n, Object object) {
            if ((n & 1) != 0) {
                string = diagramElement.id;
            }
            if ((n & 2) != 0) {
                string2 = diagramElement.name;
            }
            if ((n & 4) != 0) {
                string3 = diagramElement.description;
            }
            if ((n & 8) != 0) {
                elementType = diagramElement.type;
            }
            if ((n & 0x10) != 0) {
                string4 = diagramElement.technology;
            }
            if ((n & 0x20) != 0) {
                string5 = diagramElement.tags;
            }
            return diagramElement.copy(string, string2, string3, elementType, string4, string5);
        }

        @NotNull
        public String toString() {
            return "DiagramElement(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", type=" + this.type + ", technology=" + this.technology + ", tags=" + this.tags + ")";
        }

        public int hashCode() {
            int result = this.id.hashCode();
            result = result * 31 + this.name.hashCode();
            result = result * 31 + this.description.hashCode();
            result = result * 31 + this.type.hashCode();
            result = result * 31 + this.technology.hashCode();
            result = result * 31 + this.tags.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DiagramElement)) {
                return false;
            }
            DiagramElement diagramElement = (DiagramElement)other;
            if (!Intrinsics.areEqual((Object)this.id, (Object)diagramElement.id)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.name, (Object)diagramElement.name)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.description, (Object)diagramElement.description)) {
                return false;
            }
            if (this.type != diagramElement.type) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.technology, (Object)diagramElement.technology)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.tags, (Object)diagramElement.tags);
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H\u00c6\u0003J=\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013\u00a8\u0006 "}, d2={"Lcom/jakubjirak/structurizr/preview/DiagramRendererService$DiagramModel;", "", "workspaceName", "", "workspaceDescription", "elements", "", "Lcom/jakubjirak/structurizr/preview/DiagramRendererService$DiagramElement;", "relationships", "Lcom/jakubjirak/structurizr/preview/DiagramRendererService$DiagramRelationship;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getWorkspaceName", "()Ljava/lang/String;", "setWorkspaceName", "(Ljava/lang/String;)V", "getWorkspaceDescription", "setWorkspaceDescription", "getElements", "()Ljava/util/List;", "getRelationships", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "structurizr-dsl-plugin"})
    public static final class DiagramModel {
        @NotNull
        private String workspaceName;
        @NotNull
        private String workspaceDescription;
        @NotNull
        private final List<DiagramElement> elements;
        @NotNull
        private final List<DiagramRelationship> relationships;

        public DiagramModel(@NotNull String workspaceName, @NotNull String workspaceDescription, @NotNull List<DiagramElement> elements, @NotNull List<DiagramRelationship> relationships) {
            Intrinsics.checkNotNullParameter((Object)workspaceName, (String)"workspaceName");
            Intrinsics.checkNotNullParameter((Object)workspaceDescription, (String)"workspaceDescription");
            Intrinsics.checkNotNullParameter(elements, (String)"elements");
            Intrinsics.checkNotNullParameter(relationships, (String)"relationships");
            this.workspaceName = workspaceName;
            this.workspaceDescription = workspaceDescription;
            this.elements = elements;
            this.relationships = relationships;
        }

        public /* synthetic */ DiagramModel(String string, String string2, List list, List list2, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this((n & 1) != 0 ? "Workspace" : string, (n & 2) != 0 ? "" : string2, (n & 4) != 0 ? new ArrayList() : list, (n & 8) != 0 ? new ArrayList() : list2);
        }

        @NotNull
        public final String getWorkspaceName() {
            return this.workspaceName;
        }

        public final void setWorkspaceName(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.workspaceName = string;
        }

        @NotNull
        public final String getWorkspaceDescription() {
            return this.workspaceDescription;
        }

        public final void setWorkspaceDescription(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.workspaceDescription = string;
        }

        @NotNull
        public final List<DiagramElement> getElements() {
            return this.elements;
        }

        @NotNull
        public final List<DiagramRelationship> getRelationships() {
            return this.relationships;
        }

        @NotNull
        public final String component1() {
            return this.workspaceName;
        }

        @NotNull
        public final String component2() {
            return this.workspaceDescription;
        }

        @NotNull
        public final List<DiagramElement> component3() {
            return this.elements;
        }

        @NotNull
        public final List<DiagramRelationship> component4() {
            return this.relationships;
        }

        @NotNull
        public final DiagramModel copy(@NotNull String workspaceName, @NotNull String workspaceDescription, @NotNull List<DiagramElement> elements, @NotNull List<DiagramRelationship> relationships) {
            Intrinsics.checkNotNullParameter((Object)workspaceName, (String)"workspaceName");
            Intrinsics.checkNotNullParameter((Object)workspaceDescription, (String)"workspaceDescription");
            Intrinsics.checkNotNullParameter(elements, (String)"elements");
            Intrinsics.checkNotNullParameter(relationships, (String)"relationships");
            return new DiagramModel(workspaceName, workspaceDescription, elements, relationships);
        }

        public static /* synthetic */ DiagramModel copy$default(DiagramModel diagramModel, String string, String string2, List list, List list2, int n, Object object) {
            if ((n & 1) != 0) {
                string = diagramModel.workspaceName;
            }
            if ((n & 2) != 0) {
                string2 = diagramModel.workspaceDescription;
            }
            if ((n & 4) != 0) {
                list = diagramModel.elements;
            }
            if ((n & 8) != 0) {
                list2 = diagramModel.relationships;
            }
            return diagramModel.copy(string, string2, list, list2);
        }

        @NotNull
        public String toString() {
            return "DiagramModel(workspaceName=" + this.workspaceName + ", workspaceDescription=" + this.workspaceDescription + ", elements=" + this.elements + ", relationships=" + this.relationships + ")";
        }

        public int hashCode() {
            int result = this.workspaceName.hashCode();
            result = result * 31 + this.workspaceDescription.hashCode();
            result = result * 31 + ((Object)this.elements).hashCode();
            result = result * 31 + ((Object)this.relationships).hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DiagramModel)) {
                return false;
            }
            DiagramModel diagramModel = (DiagramModel)other;
            if (!Intrinsics.areEqual((Object)this.workspaceName, (Object)diagramModel.workspaceName)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.workspaceDescription, (Object)diagramModel.workspaceDescription)) {
                return false;
            }
            if (!Intrinsics.areEqual(this.elements, diagramModel.elements)) {
                return false;
            }
            return Intrinsics.areEqual(this.relationships, diagramModel.relationships);
        }

        public DiagramModel() {
            this(null, null, null, null, 15, null);
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lcom/jakubjirak/structurizr/preview/DiagramRendererService$DiagramRelationship;", "", "sourceId", "", "targetId", "description", "technology", "tags", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSourceId", "()Ljava/lang/String;", "getTargetId", "getDescription", "getTechnology", "getTags", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "structurizr-dsl-plugin"})
    public static final class DiagramRelationship {
        @NotNull
        private final String sourceId;
        @NotNull
        private final String targetId;
        @NotNull
        private final String description;
        @NotNull
        private final String technology;
        @NotNull
        private final String tags;

        public DiagramRelationship(@NotNull String sourceId, @NotNull String targetId, @NotNull String description, @NotNull String technology, @NotNull String tags) {
            Intrinsics.checkNotNullParameter((Object)sourceId, (String)"sourceId");
            Intrinsics.checkNotNullParameter((Object)targetId, (String)"targetId");
            Intrinsics.checkNotNullParameter((Object)description, (String)"description");
            Intrinsics.checkNotNullParameter((Object)technology, (String)"technology");
            Intrinsics.checkNotNullParameter((Object)tags, (String)"tags");
            this.sourceId = sourceId;
            this.targetId = targetId;
            this.description = description;
            this.technology = technology;
            this.tags = tags;
        }

        public /* synthetic */ DiagramRelationship(String string, String string2, String string3, String string4, String string5, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this(string, string2, (n & 4) != 0 ? "" : string3, (n & 8) != 0 ? "" : string4, (n & 0x10) != 0 ? "" : string5);
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
        public final String getTags() {
            return this.tags;
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
        public final String component5() {
            return this.tags;
        }

        @NotNull
        public final DiagramRelationship copy(@NotNull String sourceId, @NotNull String targetId, @NotNull String description, @NotNull String technology, @NotNull String tags) {
            Intrinsics.checkNotNullParameter((Object)sourceId, (String)"sourceId");
            Intrinsics.checkNotNullParameter((Object)targetId, (String)"targetId");
            Intrinsics.checkNotNullParameter((Object)description, (String)"description");
            Intrinsics.checkNotNullParameter((Object)technology, (String)"technology");
            Intrinsics.checkNotNullParameter((Object)tags, (String)"tags");
            return new DiagramRelationship(sourceId, targetId, description, technology, tags);
        }

        public static /* synthetic */ DiagramRelationship copy$default(DiagramRelationship diagramRelationship, String string, String string2, String string3, String string4, String string5, int n, Object object) {
            if ((n & 1) != 0) {
                string = diagramRelationship.sourceId;
            }
            if ((n & 2) != 0) {
                string2 = diagramRelationship.targetId;
            }
            if ((n & 4) != 0) {
                string3 = diagramRelationship.description;
            }
            if ((n & 8) != 0) {
                string4 = diagramRelationship.technology;
            }
            if ((n & 0x10) != 0) {
                string5 = diagramRelationship.tags;
            }
            return diagramRelationship.copy(string, string2, string3, string4, string5);
        }

        @NotNull
        public String toString() {
            return "DiagramRelationship(sourceId=" + this.sourceId + ", targetId=" + this.targetId + ", description=" + this.description + ", technology=" + this.technology + ", tags=" + this.tags + ")";
        }

        public int hashCode() {
            int result = this.sourceId.hashCode();
            result = result * 31 + this.targetId.hashCode();
            result = result * 31 + this.description.hashCode();
            result = result * 31 + this.technology.hashCode();
            result = result * 31 + this.tags.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DiagramRelationship)) {
                return false;
            }
            DiagramRelationship diagramRelationship = (DiagramRelationship)other;
            if (!Intrinsics.areEqual((Object)this.sourceId, (Object)diagramRelationship.sourceId)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.targetId, (Object)diagramRelationship.targetId)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.description, (Object)diagramRelationship.description)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.technology, (Object)diagramRelationship.technology)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.tags, (Object)diagramRelationship.tags);
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\f"}, d2={"Lcom/jakubjirak/structurizr/preview/DiagramRendererService$ElementType;", "", "displayName", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "PERSON", "SOFTWARE_SYSTEM", "CONTAINER", "COMPONENT", "structurizr-dsl-plugin"})
    public enum ElementType {
        PERSON("Person"),
        SOFTWARE_SYSTEM("Software System"),
        CONTAINER("Container"),
        COMPONENT("Component");

        @NotNull
        private final String displayName;

        ElementType(String displayName) {
            this.displayName = displayName;
        }

        @NotNull
        public final String getDisplayName() {
            return this.displayName;
        }

        @NotNull
        public static EnumEntries<ElementType> getEntries() {
            return EnumEntriesKt.enumEntries((Enum[])values());
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0019"}, d2={"Lcom/jakubjirak/structurizr/preview/DiagramRendererService$Position;", "", "x", "", "y", "width", "height", "<init>", "(IIII)V", "getX", "()I", "getY", "getWidth", "getHeight", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "structurizr-dsl-plugin"})
    public static final class Position {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Position(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }

        public final int getWidth() {
            return this.width;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int component1() {
            return this.x;
        }

        public final int component2() {
            return this.y;
        }

        public final int component3() {
            return this.width;
        }

        public final int component4() {
            return this.height;
        }

        @NotNull
        public final Position copy(int x, int y, int width, int height) {
            return new Position(x, y, width, height);
        }

        public static /* synthetic */ Position copy$default(Position position, int n, int n2, int n3, int n4, int n5, Object object) {
            if ((n5 & 1) != 0) {
                n = position.x;
            }
            if ((n5 & 2) != 0) {
                n2 = position.y;
            }
            if ((n5 & 4) != 0) {
                n3 = position.width;
            }
            if ((n5 & 8) != 0) {
                n4 = position.height;
            }
            return position.copy(n, n2, n3, n4);
        }

        @NotNull
        public String toString() {
            return "Position(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.x);
            result = result * 31 + Integer.hashCode(this.y);
            result = result * 31 + Integer.hashCode(this.width);
            result = result * 31 + Integer.hashCode(this.height);
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
            if (this.width != position.width) {
                return false;
            }
            return this.height == position.height;
        }
    }

    @Metadata(mv={2, 0, 0}, k=3, xi=48)
    public final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[ElementType.values().length];
            try {
                nArray[ElementType.PERSON.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ElementType.SOFTWARE_SYSTEM.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ElementType.CONTAINER.ordinal()] = 3;
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
        }
    }
}
