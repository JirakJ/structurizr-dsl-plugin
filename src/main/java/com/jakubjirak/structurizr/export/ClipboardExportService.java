/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.notification.NotificationGroupManager
 *  com.intellij.notification.NotificationType
 *  com.intellij.openapi.application.ApplicationManager
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.vfs.VirtualFile
 *  kotlin.Metadata
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
package com.jakubjirak.structurizr.export;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jakubjirak.structurizr.export.ImageTransferable;
import com.jakubjirak.structurizr.export.StructurizrExportService;
import com.jakubjirak.structurizr.export.SvgTransferable;
import com.jakubjirak.structurizr.preview.DiagramRendererService;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u001a\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001a\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bH\u0002J\u001a\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2={"Lcom/jakubjirak/structurizr/export/ClipboardExportService;", "", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "copyAsSvg", "", "dslFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "viewKey", "", "copyAsPlantUml", "copyAsMermaid", "copyAsImage", "createImageFromSvg", "Ljava/awt/image/BufferedImage;", "svgContent", "convertToPlantUml", "dslContent", "convertToMermaid", "showNotification", "", "title", "content", "type", "Lcom/intellij/notification/NotificationType;", "Companion", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nClipboardExportService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClipboardExportService.kt\ncom/jakubjirak/structurizr/export/ClipboardExportService\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,332:1\n1295#2:333\n1296#2:335\n1295#2,2:336\n1295#2,2:338\n1295#2,2:340\n1295#2,2:342\n1295#2,2:344\n1#3:334\n*S KotlinDebug\n*F\n+ 1 ClipboardExportService.kt\ncom/jakubjirak/structurizr/export/ClipboardExportService\n*L\n206#1:333\n206#1:335\n213#1:336,2\n222#1:338,2\n250#1:340,2\n257#1:342,2\n266#1:344,2\n*E\n"})
public final class ClipboardExportService {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Project project;

    public ClipboardExportService(@NotNull Project project) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        this.project = project;
    }

    public final boolean copyAsSvg(@NotNull VirtualFile dslFile, @Nullable String viewKey) {
        boolean bl;
        Intrinsics.checkNotNullParameter((Object)dslFile, (String)"dslFile");
        try {
            boolean bl2;
            DiagramRendererService rendererService = DiagramRendererService.Companion.getInstance(this.project);
            byte[] byArray = dslFile.contentsToByteArray();
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"contentsToByteArray(...)");
            byte[] byArray2 = byArray;
            String dslContent = new String(byArray2, Charsets.UTF_8);
            String svgContent = rendererService.renderToSvg(dslContent, viewKey);
            if (svgContent != null) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                SvgTransferable selection = new SvgTransferable(svgContent);
                clipboard.setContents(selection, null);
                this.showNotification("Copied to Clipboard", "SVG diagram copied to clipboard", NotificationType.INFORMATION);
                bl2 = true;
            } else {
                this.showNotification("Copy Failed", "Failed to render diagram", NotificationType.ERROR);
                bl2 = false;
            }
            bl = bl2;
        }
        catch (Exception e) {
            this.showNotification("Copy Failed", "Failed to copy: " + e.getMessage(), NotificationType.ERROR);
            bl = false;
        }
        return bl;
    }

    public static /* synthetic */ boolean copyAsSvg$default(ClipboardExportService clipboardExportService, VirtualFile virtualFile, String string, int n, Object object) {
        if ((n & 2) != 0) {
            string = null;
        }
        return clipboardExportService.copyAsSvg(virtualFile, string);
    }

    public final boolean copyAsPlantUml(@NotNull VirtualFile dslFile, @Nullable String viewKey) {
        boolean bl;
        Intrinsics.checkNotNullParameter((Object)dslFile, (String)"dslFile");
        try {
            byte[] byArray = dslFile.contentsToByteArray();
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"contentsToByteArray(...)");
            byte[] byArray2 = byArray;
            String dslContent = new String(byArray2, Charsets.UTF_8);
            StructurizrExportService exportService = StructurizrExportService.Companion.getInstance(this.project);
            String plantUml = this.convertToPlantUml(dslContent, viewKey);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(plantUml);
            clipboard.setContents(selection, null);
            this.showNotification("Copied to Clipboard", "PlantUML diagram copied to clipboard", NotificationType.INFORMATION);
            bl = true;
        }
        catch (Exception e) {
            this.showNotification("Copy Failed", "Failed to copy: " + e.getMessage(), NotificationType.ERROR);
            bl = false;
        }
        return bl;
    }

    public static /* synthetic */ boolean copyAsPlantUml$default(ClipboardExportService clipboardExportService, VirtualFile virtualFile, String string, int n, Object object) {
        if ((n & 2) != 0) {
            string = null;
        }
        return clipboardExportService.copyAsPlantUml(virtualFile, string);
    }

    public final boolean copyAsMermaid(@NotNull VirtualFile dslFile) {
        boolean bl;
        Intrinsics.checkNotNullParameter((Object)dslFile, (String)"dslFile");
        try {
            byte[] byArray = dslFile.contentsToByteArray();
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"contentsToByteArray(...)");
            byte[] byArray2 = byArray;
            String dslContent = new String(byArray2, Charsets.UTF_8);
            String mermaid = this.convertToMermaid(dslContent);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(mermaid);
            clipboard.setContents(selection, null);
            this.showNotification("Copied to Clipboard", "Mermaid diagram copied to clipboard", NotificationType.INFORMATION);
            bl = true;
        }
        catch (Exception e) {
            this.showNotification("Copy Failed", "Failed to copy: " + e.getMessage(), NotificationType.ERROR);
            bl = false;
        }
        return bl;
    }

    public final boolean copyAsImage(@NotNull VirtualFile dslFile, @Nullable String viewKey) {
        boolean bl;
        Intrinsics.checkNotNullParameter((Object)dslFile, (String)"dslFile");
        try {
            boolean bl2;
            DiagramRendererService rendererService = DiagramRendererService.Companion.getInstance(this.project);
            byte[] byArray = dslFile.contentsToByteArray();
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"contentsToByteArray(...)");
            byte[] byArray2 = byArray;
            String dslContent = new String(byArray2, Charsets.UTF_8);
            String svgContent = rendererService.renderToSvg(dslContent, viewKey);
            if (svgContent != null) {
                BufferedImage image = this.createImageFromSvg(svgContent);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                ImageTransferable selection = new ImageTransferable(image);
                clipboard.setContents(selection, null);
                this.showNotification("Copied to Clipboard", "Diagram image copied to clipboard", NotificationType.INFORMATION);
                bl2 = true;
            } else {
                this.showNotification("Copy Failed", "Failed to render diagram", NotificationType.ERROR);
                bl2 = false;
            }
            bl = bl2;
        }
        catch (Exception e) {
            this.showNotification("Copy Failed", "Failed to copy: " + e.getMessage(), NotificationType.ERROR);
            bl = false;
        }
        return bl;
    }

    public static /* synthetic */ boolean copyAsImage$default(ClipboardExportService clipboardExportService, VirtualFile virtualFile, String string, int n, Object object) {
        if ((n & 2) != 0) {
            string = null;
        }
        return clipboardExportService.copyAsImage(virtualFile, string);
    }

    private final BufferedImage createImageFromSvg(String svgContent) {
        MatchResult widthMatch = new Regex("width=\"(\\d+)\"").find((CharSequence)svgContent, 0);
        MatchResult heightMatch = new Regex("height=\"(\\d+)\"").find((CharSequence)svgContent, 0);
        int width = 800;
        if (widthMatch != null) {
            Integer parsed = StringsKt.toIntOrNull(widthMatch.getGroupValues().get(1));
            if (parsed != null) width = parsed;
        }
        int height = 600;
        if (heightMatch != null) {
            Integer parsed = StringsKt.toIntOrNull(heightMatch.getGroupValues().get(1));
            if (parsed != null) height = parsed;
        }
        BufferedImage image = new BufferedImage(width, height, 2);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", 0, 14));
        g2d.drawString("Structurizr Diagram", 20, 30);
        g2d.drawString("For full quality, use SVG export", 20, 50);
        g2d.dispose();
        return image;
    }

    private final String convertToPlantUml(String dslContent, String viewKey) {
        String description;
        CharSequence name;
        String id;
        MatchResult match;
        StringBuilder sb = new StringBuilder();
        StringBuilder stringBuilder = sb.append("@startuml");
        Intrinsics.checkNotNullExpressionValue((Object)stringBuilder, (String)"append(value)");
        Intrinsics.checkNotNullExpressionValue((Object)stringBuilder.append('\n'), (String)"append('\\n')");
        StringBuilder stringBuilder2 = sb.append("!include <C4/C4_Context>");
        Intrinsics.checkNotNullExpressionValue((Object)stringBuilder2, (String)"append(value)");
        Intrinsics.checkNotNullExpressionValue((Object)stringBuilder2.append('\n'), (String)"append('\\n')");
        Intrinsics.checkNotNullExpressionValue((Object)sb.append('\n'), (String)"append('\\n')");
        Regex personRegex = new Regex("(?:(\\w+)\\s*=\\s*)?person\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?");
        Regex systemRegex = new Regex("(?:(\\w+)\\s*=\\s*)?softwareSystem\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?");
        Regex relationRegex = new Regex("(\\w+)\\s*->\\s*(\\w+)(?:\\s+\"([^\"]+)\")?");
        Iterable $this$forEach$iv = kotlin.sequences.SequencesKt.asIterable(personRegex.findAll((CharSequence)dslContent, 0));
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            Object object;
            match = (MatchResult)element$iv;
            boolean bl = false;
            CharSequence charSequence = (CharSequence)match.getGroupValues().get(1);
            if (charSequence.length() == 0) {
                boolean bl2 = false;
                object = "person_" + match.getRange().getFirst();
            } else {
                object = charSequence;
            }
            id = (String)object;
            name = (String)match.getGroupValues().get(2);
            description = (String)match.getGroupValues().get(3);
            StringBuilder stringBuilder3 = sb.append("Person(" + id + ", \"" + (String)name + "\"" + (String)(((CharSequence)description).length() > 0 ? ", \"" + description + "\"" : "") + ")");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder3, (String)"append(value)");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder3.append('\n'), (String)"append('\\n')");
        }
        $this$forEach$iv = kotlin.sequences.SequencesKt.asIterable(systemRegex.findAll((CharSequence)dslContent, 0));
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            Object object;
            match = (MatchResult)element$iv;
            boolean bl = false;
            name = (CharSequence)match.getGroupValues().get(1);
            if (name.length() == 0) {
                boolean bl3 = false;
                object = "system_" + match.getRange().getFirst();
            } else {
                object = name;
            }
            id = (String)object;
            name = (String)match.getGroupValues().get(2);
            description = (String)match.getGroupValues().get(3);
            StringBuilder stringBuilder4 = sb.append("System(" + id + ", \"" + (String)name + "\"" + (String)(((CharSequence)description).length() > 0 ? ", \"" + description + "\"" : "") + ")");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder4, (String)"append(value)");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder4.append('\n'), (String)"append('\\n')");
        }
        Intrinsics.checkNotNullExpressionValue((Object)sb.append('\n'), (String)"append('\\n')");
        $this$forEach$iv = kotlin.sequences.SequencesKt.asIterable(relationRegex.findAll((CharSequence)dslContent, 0));
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            CharSequence charSequence;
            match = (MatchResult)element$iv;
            boolean bl = false;
            String from = (String)match.getGroupValues().get(1);
            String to = (String)match.getGroupValues().get(2);
            CharSequence charSequence2 = (CharSequence)match.getGroupValues().get(3);
            if (charSequence2.length() == 0) {
                boolean bl4 = false;
                charSequence = "uses";
            } else {
                charSequence = charSequence2;
            }
            String label = (String)charSequence;
            StringBuilder stringBuilder5 = sb.append("Rel(" + from + ", " + to + ", \"" + label + "\")");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder5, (String)"append(value)");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder5.append('\n'), (String)"append('\\n')");
        }
        Intrinsics.checkNotNullExpressionValue((Object)sb.append('\n'), (String)"append('\\n')");
        StringBuilder stringBuilder6 = sb.append("@enduml");
        Intrinsics.checkNotNullExpressionValue((Object)stringBuilder6, (String)"append(value)");
        Intrinsics.checkNotNullExpressionValue((Object)stringBuilder6.append('\n'), (String)"append('\\n')");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    private final String convertToMermaid(String dslContent) {
        CharSequence name;
        String id;
        MatchResult match;
        StringBuilder sb = new StringBuilder();
        StringBuilder stringBuilder = sb.append("flowchart TB");
        Intrinsics.checkNotNullExpressionValue((Object)stringBuilder, (String)"append(value)");
        Intrinsics.checkNotNullExpressionValue((Object)stringBuilder.append('\n'), (String)"append('\\n')");
        Intrinsics.checkNotNullExpressionValue((Object)sb.append('\n'), (String)"append('\\n')");
        Regex personRegex = new Regex("(?:(\\w+)\\s*=\\s*)?person\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?");
        Regex systemRegex = new Regex("(?:(\\w+)\\s*=\\s*)?softwareSystem\\s+\"([^\"]+)\"(?:\\s+\"([^\"]+)\")?");
        Regex relationRegex = new Regex("(\\w+)\\s*->\\s*(\\w+)(?:\\s+\"([^\"]+)\")?");
        List persons = new ArrayList();
        List systems = new ArrayList();
        Iterable $this$forEach$iv = kotlin.sequences.SequencesKt.asIterable(personRegex.findAll((CharSequence)dslContent, 0));
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            Object object;
            match = (MatchResult)element$iv;
            boolean bl = false;
            CharSequence charSequence = (CharSequence)match.getGroupValues().get(1);
            if (charSequence.length() == 0) {
                boolean bl2 = false;
                object = "person_" + match.getRange().getFirst();
            } else {
                object = charSequence;
            }
            id = (String)object;
            name = (String)match.getGroupValues().get(2);
            persons.add(id);
            StringBuilder stringBuilder2 = sb.append("    " + id + "[\"\ud83d\udc64 " + (String)name + "\"]");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder2, (String)"append(value)");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder2.append('\n'), (String)"append('\\n')");
        }
        $this$forEach$iv = kotlin.sequences.SequencesKt.asIterable(systemRegex.findAll((CharSequence)dslContent, 0));
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            Object object;
            match = (MatchResult)element$iv;
            boolean bl = false;
            name = (CharSequence)match.getGroupValues().get(1);
            if (name.length() == 0) {
                boolean bl3 = false;
                object = "system_" + match.getRange().getFirst();
            } else {
                object = name;
            }
            id = (String)object;
            name = (String)match.getGroupValues().get(2);
            systems.add(id);
            StringBuilder stringBuilder3 = sb.append("    " + id + "[\"\ud83d\udda5\ufe0f " + (String)name + "\"]");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder3, (String)"append(value)");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder3.append('\n'), (String)"append('\\n')");
        }
        Intrinsics.checkNotNullExpressionValue((Object)sb.append('\n'), (String)"append('\\n')");
        $this$forEach$iv = kotlin.sequences.SequencesKt.asIterable(relationRegex.findAll((CharSequence)dslContent, 0));
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            CharSequence charSequence;
            match = (MatchResult)element$iv;
            boolean bl = false;
            String from = (String)match.getGroupValues().get(1);
            String to = (String)match.getGroupValues().get(2);
            CharSequence charSequence2 = (CharSequence)match.getGroupValues().get(3);
            if (charSequence2.length() == 0) {
                boolean bl4 = false;
                charSequence = "uses";
            } else {
                charSequence = charSequence2;
            }
            String label = (String)charSequence;
            StringBuilder stringBuilder4 = sb.append("    " + from + " -->|" + label + "| " + to);
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder4, (String)"append(value)");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder4.append('\n'), (String)"append('\\n')");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    private final void showNotification(String title, String content, NotificationType type) {
        ApplicationManager.getApplication().invokeLater(() -> ClipboardExportService.showNotification$lambda$12(title, content, type, this));
    }

    private static final void showNotification$lambda$12(String $title, String $content, NotificationType $type, ClipboardExportService this$0) {
        NotificationGroupManager.getInstance().getNotificationGroup("Structurizr DSL").createNotification($title, $content, $type).notify(this$0.project);
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\b"}, d2={"Lcom/jakubjirak/structurizr/export/ClipboardExportService$Companion;", "", "<init>", "()V", "getInstance", "Lcom/jakubjirak/structurizr/export/ClipboardExportService;", "project", "Lcom/intellij/openapi/project/Project;", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ClipboardExportService getInstance(@NotNull Project project) {
            Intrinsics.checkNotNullParameter((Object)project, (String)"project");
            return new ClipboardExportService(project);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
