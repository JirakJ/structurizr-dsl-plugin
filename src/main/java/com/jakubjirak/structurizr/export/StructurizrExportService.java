/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.notification.NotificationGroupManager
 *  com.intellij.notification.NotificationType
 *  com.intellij.openapi.application.ApplicationManager
 *  com.intellij.openapi.components.Service
 *  com.intellij.openapi.components.Service$Level
 *  com.intellij.openapi.diagnostic.Logger
 *  com.intellij.openapi.progress.ProgressIndicator
 *  com.intellij.openapi.progress.ProgressManager
 *  com.intellij.openapi.progress.Task
 *  com.intellij.openapi.progress.Task$Backgroundable
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.vfs.LocalFileSystem
 *  com.intellij.openapi.vfs.VirtualFile
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.Pair
 *  kotlin.TuplesKt
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.io.FilesKt
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.Ref$BooleanRef
 *  kotlin.jvm.internal.Ref$ObjectRef
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
import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.jakubjirak.structurizr.export.DiagramExportFormat;
import com.jakubjirak.structurizr.export.StructurizrExportService;
import com.jakubjirak.structurizr.preview.DiagramRendererService;
import com.jakubjirak.structurizr.preview.ExportFormat;
import com.jakubjirak.structurizr.preview.StructurizrCliService;
import com.jakubjirak.structurizr.settings.StructurizrProjectSettings;
import com.jakubjirak.structurizr.settings.StructurizrSettings;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
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
@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 =2\u00020\u0001:\u0003=>?B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0014J\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016JD\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00122\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00142\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00190!J*\u0010#\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00142\u0006\u0010$\u001a\u00020%H\u0002J*\u0010&\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00142\u0006\u0010$\u001a\u00020%H\u0002J*\u0010'\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00142\u0006\u0010$\u001a\u00020%H\u0002J*\u0010(\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00142\u0006\u0010$\u001a\u00020%H\u0002J*\u0010)\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00142\u0006\u0010$\u001a\u00020%H\u0002J \u0010*\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020%H\u0002J2\u0010+\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020,2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00142\u0006\u0010$\u001a\u00020%H\u0002J\u0018\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u001a\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u00020\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010\u0014H\u0002J\u0010\u00101\u001a\u00020\u00142\u0006\u00100\u001a\u00020\u0014H\u0002J8\u00102\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u00103\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00122\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00190!J2\u00104\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010\u00142\u0006\u0010$\u001a\u00020%H\u0002J\u0016\u00105\u001a\b\u0012\u0004\u0012\u000207062\u0006\u00100\u001a\u00020\u0014H\u0002J \u00108\u001a\u00020\u00192\u0006\u00109\u001a\u00020\u00142\u0006\u0010:\u001a\u00020\u00142\u0006\u0010;\u001a\u00020<H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00070\u0007\u00a2\u0006\u0002\b\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006@"}, d2={"Lcom/jakubjirak/structurizr/export/StructurizrExportService;", "", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "log", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "settings", "Lcom/jakubjirak/structurizr/settings/StructurizrSettings;", "getSettings", "()Lcom/jakubjirak/structurizr/settings/StructurizrSettings;", "projectSettings", "Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettings;", "getProjectSettings", "()Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettings;", "getDefaultFormat", "Lcom/jakubjirak/structurizr/export/DiagramExportFormat;", "getDefaultExportDirectory", "", "getExportDimensions", "Lkotlin/Pair;", "", "export", "", "dslFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "outputPath", "Ljava/nio/file/Path;", "format", "viewKey", "callback", "Lkotlin/Function2;", "", "exportToSvg", "indicator", "Lcom/intellij/openapi/progress/ProgressIndicator;", "exportToPng", "exportToPlantUml", "exportToMermaid", "exportToDot", "exportToJson", "exportWithCli", "Lcom/jakubjirak/structurizr/preview/ExportFormat;", "convertSvgToPng", "svgContent", "convertToPlantUml", "dslContent", "convertToMermaid", "exportAllViews", "outputDir", "exportSingleView", "extractViews", "", "Lcom/jakubjirak/structurizr/export/StructurizrExportService$ViewInfo;", "showNotification", "title", "content", "type", "Lcom/intellij/notification/NotificationType;", "Companion", "ViewInfo", "ViewType", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrExportService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrExportService.kt\ncom/jakubjirak/structurizr/export/StructurizrExportService\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,634:1\n1#2:635\n1295#3,2:636\n1295#3,2:638\n1295#3,2:640\n1295#3,2:642\n1295#3,2:644\n1295#3,2:646\n1295#3,2:652\n1295#3,2:654\n1295#3,2:656\n1295#3,2:658\n1295#3,2:660\n1855#4,2:648\n1855#4,2:650\n*S KotlinDebug\n*F\n+ 1 StructurizrExportService.kt\ncom/jakubjirak/structurizr/export/StructurizrExportService\n*L\n392#1:636,2\n399#1:638,2\n408#1:640,2\n438#1:642,2\n445#1:644,2\n454#1:646,2\n555#1:652,2\n561#1:654,2\n567#1:656,2\n573#1:658,2\n579#1:660,2\n463#1:648,2\n466#1:650,2\n*E\n"})
public final class StructurizrExportService {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Project project;
    @NotNull
    private final Logger log;

    public StructurizrExportService(@NotNull Project project) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        this.project = project;
        Logger logger = Logger.getInstance(StructurizrExportService.class);
        Intrinsics.checkNotNullExpressionValue((Object)logger, (String)"getInstance(...)");
        this.log = logger;
    }

    private final StructurizrSettings getSettings() {
        return StructurizrSettings.Companion.getInstance();
    }

    private final StructurizrProjectSettings getProjectSettings() {
        return StructurizrProjectSettings.Companion.getInstance(this.project);
    }

    @NotNull
    public final DiagramExportFormat getDefaultFormat() {
        DiagramExportFormat diagramExportFormat = null;
        DiagramExportFormat diagramExportFormat2 = null;
        block2: {
            DiagramExportFormat[] diagramExportFormatArray = DiagramExportFormat.values();
            int n = diagramExportFormatArray.length;
            for (int i = 0; i < n; ++i) {
                DiagramExportFormat diagramExportFormat3;
                DiagramExportFormat it = diagramExportFormat3 = diagramExportFormatArray[i];
                boolean bl = false;
                if (!StringsKt.equals((String)it.name(), (String)this.getSettings().getDefaultExportFormat(), (boolean)true)) continue;
                diagramExportFormat2 = diagramExportFormat3;
                break block2;
            }
            diagramExportFormat2 = diagramExportFormat = null;
        }
        if (diagramExportFormat2 == null) {
            diagramExportFormat = DiagramExportFormat.PNG;
        }
        return diagramExportFormat;
    }

    @NotNull
    public final String getDefaultExportDirectory() {
        return this.getProjectSettings().getEffectiveExportDirectory();
    }

    @NotNull
    public final Pair<Integer, Integer> getExportDimensions() {
        return TuplesKt.to((Integer)this.getSettings().getExportImageWidth(), (Integer)this.getSettings().getExportImageHeight());
    }

    public final void export(@NotNull VirtualFile dslFile, @NotNull Path outputPath, @NotNull DiagramExportFormat format, @Nullable String viewKey, @NotNull Function2<? super Boolean, ? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter((Object)dslFile, (String)"dslFile");
        Intrinsics.checkNotNullParameter((Object)outputPath, (String)"outputPath");
        Intrinsics.checkNotNullParameter((Object)((Object)format), (String)"format");
        Intrinsics.checkNotNullParameter(callback, (String)"callback");
        Project project = this.project;
        String string = "Exporting Diagram to " + format.getDisplayName();
        ProgressManager.getInstance().run((Task)new Task.Backgroundable(project, string, true){
            final DiagramExportFormat $format = format;
            final StructurizrExportService this$0 = StructurizrExportService.this;
            final VirtualFile $dslFile = dslFile;
            final Path $outputPath = outputPath;
            final String $viewKey = viewKey;
            final Function2<Boolean, String, Unit> $callback = (Function2<Boolean, String, Unit>)(Function2)callback;

            public void run(ProgressIndicator indicator) {
                Intrinsics.checkNotNullParameter((Object)indicator, (String)"indicator");
                try {
                    indicator.setIndeterminate(false);
                    indicator.setFraction(0.0);
                    indicator.setText("Preparing export...");
                    switch (this.$format.ordinal() + 1) {
                        case 1: {
                            StructurizrExportService.access$exportToSvg(this.this$0, this.$dslFile, this.$outputPath, this.$viewKey, indicator);
                            break;
                        }
                        case 2: {
                            StructurizrExportService.access$exportToPng(this.this$0, this.$dslFile, this.$outputPath, this.$viewKey, indicator);
                            break;
                        }
                        case 3: {
                            StructurizrExportService.access$exportToPlantUml(this.this$0, this.$dslFile, this.$outputPath, this.$viewKey, indicator);
                            break;
                        }
                        case 4: {
                            StructurizrExportService.access$exportToMermaid(this.this$0, this.$dslFile, this.$outputPath, this.$viewKey, indicator);
                            break;
                        }
                        case 5: {
                            StructurizrExportService.access$exportToDot(this.this$0, this.$dslFile, this.$outputPath, this.$viewKey, indicator);
                            break;
                        }
                        case 6: {
                            StructurizrExportService.access$exportToJson(this.this$0, this.$dslFile, this.$outputPath, indicator);
                            break;
                        }
                        default: {
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                    indicator.setFraction(1.0);
                    LocalFileSystem.getInstance().refreshAndFindFileByPath(((Object)this.$outputPath).toString());
                    this.$callback.invoke(Boolean.TRUE, "Successfully exported to " + this.$outputPath.getFileName());
                    StructurizrExportService.access$showNotification(this.this$0, "Export Complete", "Diagram exported to " + this.$outputPath.getFileName(), NotificationType.INFORMATION);
                }
                catch (Exception e) {
                    StructurizrExportService.access$getLog$p(this.this$0).error("Export failed", (Throwable)e);
                    this.$callback.invoke(Boolean.FALSE, "Export failed: " + e.getMessage());
                }
            }
        });
    }

    public static /* synthetic */ void export$default(StructurizrExportService structurizrExportService, VirtualFile virtualFile, Path path, DiagramExportFormat diagramExportFormat, String string, Function2 function2, int n, Object object) {
        if ((n & 8) != 0) {
            string = null;
        }
        structurizrExportService.export(virtualFile, path, diagramExportFormat, string, (Function2<? super Boolean, ? super String, Unit>)function2);
    }

    private final void exportToSvg(VirtualFile dslFile, Path outputPath, String viewKey, ProgressIndicator indicator) throws Exception {
        indicator.setText("Generating SVG...");
        indicator.setFraction(0.3);
        StructurizrCliService cliService = StructurizrCliService.Companion.getInstance();
        if (cliService.isCliAvailable()) {
            this.exportWithCli(dslFile, outputPath, ExportFormat.SVG, viewKey, indicator);
        } else {
            DiagramRendererService rendererService = DiagramRendererService.Companion.getInstance(this.project);
            byte[] byArray = dslFile.contentsToByteArray();
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"contentsToByteArray(...)");
            byte[] byArray2 = byArray;
            String dslContent = new String(byArray2, Charsets.UTF_8);
            String svgContent = rendererService.renderToSvg(dslContent, viewKey);
            if (svgContent != null) {
                indicator.setFraction(0.8);
                Files.writeString(outputPath, (CharSequence)svgContent, new OpenOption[0]);
            } else {
                throw new Exception("Failed to render SVG");
            }
        }
    }

    private final void exportToPng(VirtualFile dslFile, Path outputPath, String viewKey, ProgressIndicator indicator) throws Exception {
        indicator.setText("Generating PNG...");
        indicator.setFraction(0.3);
        StructurizrCliService cliService = StructurizrCliService.Companion.getInstance();
        if (cliService.isCliAvailable()) {
            this.exportWithCli(dslFile, outputPath, ExportFormat.PNG, viewKey, indicator);
        } else {
            DiagramRendererService rendererService = DiagramRendererService.Companion.getInstance(this.project);
            byte[] byArray = dslFile.contentsToByteArray();
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"contentsToByteArray(...)");
            byte[] byArray2 = byArray;
            String dslContent = new String(byArray2, Charsets.UTF_8);
            String svgContent = rendererService.renderToSvg(dslContent, viewKey);
            if (svgContent != null) {
                indicator.setFraction(0.6);
                indicator.setText("Converting to PNG...");
                this.convertSvgToPng(svgContent, outputPath);
            } else {
                throw new Exception("Failed to render diagram");
            }
        }
    }

    private final void exportToPlantUml(VirtualFile dslFile, Path outputPath, String viewKey, ProgressIndicator indicator) throws Exception {
        indicator.setText("Generating PlantUML...");
        indicator.setFraction(0.3);
        StructurizrCliService cliService = StructurizrCliService.Companion.getInstance();
        if (cliService.isCliAvailable()) {
            this.exportWithCli(dslFile, outputPath, ExportFormat.PLANTUML_C4, viewKey, indicator);
        } else {
            byte[] byArray = dslFile.contentsToByteArray();
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"contentsToByteArray(...)");
            byte[] byArray2 = byArray;
            String dslContent = new String(byArray2, Charsets.UTF_8);
            String plantUml = this.convertToPlantUml(dslContent, viewKey);
            indicator.setFraction(0.8);
            Files.writeString(outputPath, (CharSequence)plantUml, new OpenOption[0]);
        }
    }

    private final void exportToMermaid(VirtualFile dslFile, Path outputPath, String viewKey, ProgressIndicator indicator) throws Exception {
        indicator.setText("Generating Mermaid...");
        indicator.setFraction(0.3);
        StructurizrCliService cliService = StructurizrCliService.Companion.getInstance();
        if (cliService.isCliAvailable()) {
            this.exportWithCli(dslFile, outputPath, ExportFormat.MERMAID, viewKey, indicator);
        } else {
            byte[] byArray = dslFile.contentsToByteArray();
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"contentsToByteArray(...)");
            byte[] byArray2 = byArray;
            String dslContent = new String(byArray2, Charsets.UTF_8);
            String mermaid = this.convertToMermaid(dslContent);
            indicator.setFraction(0.8);
            Files.writeString(outputPath, (CharSequence)mermaid, new OpenOption[0]);
        }
    }

    private final void exportToDot(VirtualFile dslFile, Path outputPath, String viewKey, ProgressIndicator indicator) throws Exception {
        indicator.setText("Generating DOT...");
        indicator.setFraction(0.3);
        StructurizrCliService cliService = StructurizrCliService.Companion.getInstance();
        if (!cliService.isCliAvailable()) {
            throw new Exception("Structurizr CLI is required for DOT export");
        }
        this.exportWithCli(dslFile, outputPath, ExportFormat.DOT, viewKey, indicator);
    }

    private final void exportToJson(VirtualFile dslFile, Path outputPath, ProgressIndicator indicator) throws Exception {
        indicator.setText("Generating JSON...");
        indicator.setFraction(0.3);
        StructurizrCliService cliService = StructurizrCliService.Companion.getInstance();
        if (!cliService.isCliAvailable()) {
            throw new Exception("Structurizr CLI is required for JSON export");
        }
        this.exportWithCli(dslFile, outputPath, ExportFormat.JSON, null, indicator);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void exportWithCli(VirtualFile dslFile, Path outputPath, ExportFormat format, String viewKey, ProgressIndicator indicator) throws Exception {
        indicator.setFraction(0.5);
        StructurizrCliService cliService = StructurizrCliService.Companion.getInstance();
        Path tempDir = Files.createTempDirectory("structurizr-export", new FileAttribute[0]);
        try {
            File fileArray2 = null;
            File[] fileArray;
            block12: {
                block11: {
                    block9: {
                        File[] exportedFiles;
                        Ref.BooleanRef exportComplete = new Ref.BooleanRef();
                        Ref.ObjectRef exportError = new Ref.ObjectRef();
                        String string = dslFile.getPath();
                        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getPath(...)");
                        cliService.exportToFormat(this.project, string, ((Object)tempDir).toString(), format, (Function2<? super Boolean, ? super String, Unit>)((Function2)(arg_0, arg_1) -> StructurizrExportService.exportWithCli$lambda$1(exportComplete, exportError, (Boolean)arg_0, (String)arg_1)));
                        for (int waited = 0; !exportComplete.element && exportError.element == null && waited < 60000; waited += 100) {
                            Thread.sleep(100L);
                        }
                        if (exportError.element != null) {
                            throw new Exception((String)exportError.element);
                        }
                        indicator.setFraction(0.8);
                        fileArray = exportedFiles = tempDir.toFile().listFiles();
                        if (exportedFiles == null) break block11;
                        File[] fileArray3 = fileArray;
                        int n = fileArray3.length;
                        for (int i = 0; i < n; ++i) {
                            File fileArray4;
                            File it = fileArray4 = fileArray3[i];
                            boolean bl = false;
                            Intrinsics.checkNotNull((Object)it);
                            if (!Intrinsics.areEqual((Object)FilesKt.getExtension((File)it), (Object)format.getExtension())) continue;
                            fileArray2 = fileArray4;
                            break block9;
                        }
                        fileArray = null;
                        fileArray2 = null;
                    }
                    if (fileArray2 != null) break block12;
                }
                throw new Exception("Export file not found");
            }
            File exportedFile = fileArray2;
            CopyOption[] copyOptionArray = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
            Files.copy(exportedFile.toPath(), outputPath, copyOptionArray);
        }
        finally {
            File file = tempDir.toFile();
            Intrinsics.checkNotNullExpressionValue((Object)file, (String)"toFile(...)");
            FilesKt.deleteRecursively((File)file);
        }
    }

    private final void convertSvgToPng(String svgContent, Path outputPath) throws Exception {
        int width = 800;
        int height = 600;
        BufferedImage image = new BufferedImage(width, height, 2);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", 0, 14));
        g2d.drawString("Diagram exported from Structurizr DSL", 20, 30);
        g2d.drawString("For full quality PNG export, install Structurizr CLI", 20, 50);
        g2d.dispose();
        ImageIO.write((RenderedImage)image, "PNG", outputPath.toFile());
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
        Iterable $this$forEach$iv = SequencesKt.toList(personRegex.findAll(dslContent, 0));
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
        $this$forEach$iv = SequencesKt.toList(systemRegex.findAll(dslContent, 0));
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
        $this$forEach$iv = SequencesKt.toList(relationRegex.findAll(dslContent, 0));
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
        String id;
        CharSequence name;
        String id2;
        MatchResult match;
        Object element$iv;
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
        Iterable $this$forEach$iv = SequencesKt.toList(personRegex.findAll(dslContent, 0));
        boolean $i$f$forEach = false;
        Iterator iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            Object object;
            element$iv = iterator.next();
            match = (MatchResult)element$iv;
            boolean bl = false;
            CharSequence charSequence = (CharSequence)match.getGroupValues().get(1);
            if (charSequence.length() == 0) {
                boolean bl2 = false;
                object = "person_" + match.getRange().getFirst();
            } else {
                object = charSequence;
            }
            id2 = (String)object;
            name = (String)match.getGroupValues().get(2);
            persons.add(id2);
            StringBuilder stringBuilder2 = sb.append("    " + id2 + "[\"\ud83d\udc64 " + (String)name + "\"]");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder2, (String)"append(value)");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder2.append('\n'), (String)"append('\\n')");
        }
        $this$forEach$iv = SequencesKt.toList(systemRegex.findAll(dslContent, 0));
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            Object object;
            element$iv = iterator.next();
            match = (MatchResult)element$iv;
            boolean bl = false;
            name = (CharSequence)match.getGroupValues().get(1);
            if (name.length() == 0) {
                boolean bl3 = false;
                object = "system_" + match.getRange().getFirst();
            } else {
                object = name;
            }
            id2 = (String)object;
            name = (String)match.getGroupValues().get(2);
            systems.add(id2);
            StringBuilder stringBuilder3 = sb.append("    " + id2 + "[\"\ud83d\udda5\ufe0f " + (String)name + "\"]");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder3, (String)"append(value)");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder3.append('\n'), (String)"append('\\n')");
        }
        Intrinsics.checkNotNullExpressionValue((Object)sb.append('\n'), (String)"append('\\n')");
        $this$forEach$iv = SequencesKt.toList(relationRegex.findAll(dslContent, 0));
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            CharSequence charSequence;
            element$iv = iterator.next();
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
        Intrinsics.checkNotNullExpressionValue((Object)sb.append('\n'), (String)"append('\\n')");
        StringBuilder stringBuilder5 = sb.append("    %% Styling");
        Intrinsics.checkNotNullExpressionValue((Object)stringBuilder5, (String)"append(value)");
        Intrinsics.checkNotNullExpressionValue((Object)stringBuilder5.append('\n'), (String)"append('\\n')");
        $this$forEach$iv = persons;
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            element$iv = iterator.next();
            id = (String)element$iv;
            boolean bl = false;
            StringBuilder stringBuilder6 = sb.append("    style " + id + " fill:#08427B,color:#fff");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder6, (String)"append(value)");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder6.append('\n'), (String)"append('\\n')");
        }
        $this$forEach$iv = systems;
        $i$f$forEach = false;
        iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            element$iv = iterator.next();
            id = (String)element$iv;
            boolean bl = false;
            StringBuilder stringBuilder7 = sb.append("    style " + id + " fill:#1168BD,color:#fff");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder7, (String)"append(value)");
            Intrinsics.checkNotNullExpressionValue((Object)stringBuilder7.append('\n'), (String)"append('\\n')");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    public final void exportAllViews(@NotNull VirtualFile dslFile, @NotNull Path outputDir, @NotNull DiagramExportFormat format, @NotNull Function2<? super Boolean, ? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter((Object)dslFile, (String)"dslFile");
        Intrinsics.checkNotNullParameter((Object)outputDir, (String)"outputDir");
        Intrinsics.checkNotNullParameter((Object)((Object)format), (String)"format");
        Intrinsics.checkNotNullParameter(callback, (String)"callback");
        Project project = this.project;
        ProgressManager.getInstance().run((Task)new Task.Backgroundable(project, "Exporting All Views", true){
            final VirtualFile $dslFile = dslFile;
            final StructurizrExportService this$0 = StructurizrExportService.this;
            final Path $outputDir = outputDir;
            final DiagramExportFormat $format = format;
            final Function2<Boolean, String, Unit> $callback = (Function2<Boolean, String, Unit>)(Function2)callback;

            /*
             * WARNING - void declaration
             */
            public void run(ProgressIndicator indicator) {
                Intrinsics.checkNotNullParameter((Object)indicator, (String)"indicator");
                try {
                    indicator.setIndeterminate(false);
                    indicator.setText("Analyzing DSL file...");
                    byte[] byArray = this.$dslFile.contentsToByteArray();
                    Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"contentsToByteArray(...)");
                    byte[] byArray2 = byArray;
                    String dslContent = new String(byArray2, Charsets.UTF_8);
                    List views = StructurizrExportService.access$extractViews(this.this$0, dslContent);
                    if (views.isEmpty()) {
                        Path outputPath = this.$outputDir.resolve(this.$dslFile.getNameWithoutExtension() + "." + this.$format.getExtension());
                        Intrinsics.checkNotNull((Object)outputPath);
                        this.this$0.export(this.$dslFile, outputPath, this.$format, null, (Function2<? super Boolean, ? super String, Unit>)((Function2)(success, msg) -> Unit.INSTANCE));
                    } else {
                        Iterable outputPath = views;
                        Path path = this.$outputDir;
                        DiagramExportFormat diagramExportFormat = this.$format;
                        StructurizrExportService structurizrExportService = this.this$0;
                        VirtualFile virtualFile = this.$dslFile;
                        boolean $i$f$forEachIndexed = false;
                        int index$iv = 0;
                        for (Object item$iv : outputPath) {
                            int n;
                            if ((n = index$iv++) < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            ViewInfo viewInfo = (ViewInfo)item$iv;
                            int index = n;
                            boolean bl = false;
                            indicator.setFraction((double)index / (double)views.size());
                            indicator.setText("Exporting " + viewInfo.getName() + "...");
                            Path outputPath2 = path.resolve(viewInfo.getName() + "." + diagramExportFormat.getExtension());
                            Intrinsics.checkNotNull((Object)outputPath2);
                            StructurizrExportService.access$exportSingleView(structurizrExportService, virtualFile, outputPath2, diagramExportFormat, viewInfo.getKey(), indicator);
                        }
                    }
                    this.$callback.invoke(Boolean.TRUE, "Exported " + (views.isEmpty() ? 1 : views.size()) + " diagram(s)");
                    StructurizrExportService.access$showNotification(this.this$0, "Bulk Export Complete", "Exported " + (views.isEmpty() ? 1 : views.size()) + " diagram(s) to " + this.$outputDir, NotificationType.INFORMATION);
                }
                catch (Exception e) {
                    StructurizrExportService.access$getLog$p(this.this$0).error("Bulk export failed", (Throwable)e);
                    this.$callback.invoke(Boolean.FALSE, "Export failed: " + e.getMessage());
                }
            }

            private static final Unit run$lambda$0(boolean bl, String string) {
                Intrinsics.checkNotNullParameter((Object)string, (String)"<unused var>");
                return Unit.INSTANCE;
            }
        });
    }

    private final void exportSingleView(VirtualFile dslFile, Path outputPath, DiagramExportFormat format, String viewKey, ProgressIndicator indicator) throws Exception {
        switch (WhenMappings.$EnumSwitchMapping$0[format.ordinal()]) {
            case 1: {
                this.exportToSvg(dslFile, outputPath, viewKey, indicator);
                break;
            }
            case 2: {
                this.exportToPng(dslFile, outputPath, viewKey, indicator);
                break;
            }
            case 3: {
                this.exportToPlantUml(dslFile, outputPath, viewKey, indicator);
                break;
            }
            case 4: {
                this.exportToMermaid(dslFile, outputPath, viewKey, indicator);
                break;
            }
            case 5: {
                this.exportToDot(dslFile, outputPath, viewKey, indicator);
                break;
            }
            case 6: {
                this.exportToJson(dslFile, outputPath, indicator);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private final List<ViewInfo> extractViews(String dslContent) {
        String key;
        MatchResult match;
        List views = new ArrayList();
        Iterable $this$forEach$iv = SequencesKt.toList(new Regex("systemLandscape\\s+\"?(\\w+)\"?\\s*\\{").findAll(dslContent, 0));
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            match = (MatchResult)element$iv;
            boolean bl = false;
            key = (String)match.getGroupValues().get(1);
            views.add(new ViewInfo(key, "SystemLandscape-" + key, ViewType.SYSTEM_LANDSCAPE));
        }
        $this$forEach$iv = SequencesKt.toList(new Regex("systemContext\\s+(\\w+)\\s+\"?(\\w+)\"?\\s*\\{").findAll(dslContent, 0));
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            match = (MatchResult)element$iv;
            boolean bl = false;
            key = (String)match.getGroupValues().get(2);
            views.add(new ViewInfo(key, "SystemContext-" + key, ViewType.SYSTEM_CONTEXT));
        }
        $this$forEach$iv = SequencesKt.toList(new Regex("container\\s+(\\w+)\\s+\"?(\\w+)\"?\\s*\\{").findAll(dslContent, 0));
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            match = (MatchResult)element$iv;
            boolean bl = false;
            key = (String)match.getGroupValues().get(2);
            views.add(new ViewInfo(key, "Container-" + key, ViewType.CONTAINER));
        }
        $this$forEach$iv = SequencesKt.toList(new Regex("component\\s+(\\w+)\\s+\"?(\\w+)\"?\\s*\\{").findAll(dslContent, 0));
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            match = (MatchResult)element$iv;
            boolean bl = false;
            key = (String)match.getGroupValues().get(2);
            views.add(new ViewInfo(key, "Component-" + key, ViewType.COMPONENT));
        }
        $this$forEach$iv = SequencesKt.toList(new Regex("deployment\\s+\\*\\s+\"?(\\w+)\"?\\s+\"?(\\w+)\"?\\s*\\{").findAll(dslContent, 0));
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            match = (MatchResult)element$iv;
            boolean bl = false;
            key = (String)match.getGroupValues().get(2);
            views.add(new ViewInfo(key, "Deployment-" + key, ViewType.DEPLOYMENT));
        }
        return views;
    }

    private final void showNotification(String title, String content, NotificationType type) {
        ApplicationManager.getApplication().invokeLater(() -> StructurizrExportService.showNotification$lambda$22(title, content, type, this));
    }

    private static final Unit exportWithCli$lambda$1(Ref.BooleanRef $exportComplete, Ref.ObjectRef $exportError, boolean success, String message) {
        if (success) {
            $exportComplete.element = true;
        } else {
            $exportError.element = message;
        }
        return Unit.INSTANCE;
    }

    private static final void showNotification$lambda$22(String $title, String $content, NotificationType $type, StructurizrExportService this$0) {
        NotificationGroupManager.getInstance().getNotificationGroup("Structurizr DSL").createNotification($title, $content, $type).notify(this$0.project);
    }

    public static final void access$exportToSvg(StructurizrExportService $this, VirtualFile dslFile, Path outputPath, String viewKey, ProgressIndicator indicator) throws Exception {
        $this.exportToSvg(dslFile, outputPath, viewKey, indicator);
    }

    public static final void access$exportToPng(StructurizrExportService $this, VirtualFile dslFile, Path outputPath, String viewKey, ProgressIndicator indicator) throws Exception {
        $this.exportToPng(dslFile, outputPath, viewKey, indicator);
    }

    public static final void access$exportToPlantUml(StructurizrExportService $this, VirtualFile dslFile, Path outputPath, String viewKey, ProgressIndicator indicator) throws Exception {
        $this.exportToPlantUml(dslFile, outputPath, viewKey, indicator);
    }

    public static final void access$exportToMermaid(StructurizrExportService $this, VirtualFile dslFile, Path outputPath, String viewKey, ProgressIndicator indicator) throws Exception {
        $this.exportToMermaid(dslFile, outputPath, viewKey, indicator);
    }

    public static final void access$exportToDot(StructurizrExportService $this, VirtualFile dslFile, Path outputPath, String viewKey, ProgressIndicator indicator) throws Exception {
        $this.exportToDot(dslFile, outputPath, viewKey, indicator);
    }

    public static final void access$exportToJson(StructurizrExportService $this, VirtualFile dslFile, Path outputPath, ProgressIndicator indicator) throws Exception {
        $this.exportToJson(dslFile, outputPath, indicator);
    }

    public static final void access$showNotification(StructurizrExportService $this, String title, String content, NotificationType type) throws Exception {
        $this.showNotification(title, content, type);
    }

    public static final /* synthetic */ Logger access$getLog$p(StructurizrExportService $this) {
        return $this.log;
    }

    public static final /* synthetic */ List access$extractViews(StructurizrExportService $this, String dslContent) {
        return $this.extractViews(dslContent);
    }

    public static final void access$exportSingleView(StructurizrExportService $this, VirtualFile dslFile, Path outputPath, DiagramExportFormat format, String viewKey, ProgressIndicator indicator) throws Exception {
        $this.exportSingleView(dslFile, outputPath, format, viewKey, indicator);
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\b"}, d2={"Lcom/jakubjirak/structurizr/export/StructurizrExportService$Companion;", "", "<init>", "()V", "getInstance", "Lcom/jakubjirak/structurizr/export/StructurizrExportService;", "project", "Lcom/intellij/openapi/project/Project;", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final StructurizrExportService getInstance(@NotNull Project project) {
            Intrinsics.checkNotNullParameter((Object)project, (String)"project");
            Object object = project.getService(StructurizrExportService.class);
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"getService(...)");
            return (StructurizrExportService)object;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2={"Lcom/jakubjirak/structurizr/export/StructurizrExportService$ViewInfo;", "", "key", "", "name", "type", "Lcom/jakubjirak/structurizr/export/StructurizrExportService$ViewType;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jakubjirak/structurizr/export/StructurizrExportService$ViewType;)V", "getKey", "()Ljava/lang/String;", "getName", "getType", "()Lcom/jakubjirak/structurizr/export/StructurizrExportService$ViewType;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "structurizr-dsl-plugin"})
    public static final class ViewInfo {
        @NotNull
        private final String key;
        @NotNull
        private final String name;
        @NotNull
        private final ViewType type;

        public ViewInfo(@NotNull String key, @NotNull String name, @NotNull ViewType type) {
            Intrinsics.checkNotNullParameter((Object)key, (String)"key");
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)((Object)type), (String)"type");
            this.key = key;
            this.name = name;
            this.type = type;
        }

        @NotNull
        public final String getKey() {
            return this.key;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final ViewType getType() {
            return this.type;
        }

        @NotNull
        public final String component1() {
            return this.key;
        }

        @NotNull
        public final String component2() {
            return this.name;
        }

        @NotNull
        public final ViewType component3() {
            return this.type;
        }

        @NotNull
        public final ViewInfo copy(@NotNull String key, @NotNull String name, @NotNull ViewType type) {
            Intrinsics.checkNotNullParameter((Object)key, (String)"key");
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)((Object)type), (String)"type");
            return new ViewInfo(key, name, type);
        }

        public static /* synthetic */ ViewInfo copy$default(ViewInfo viewInfo, String string, String string2, ViewType viewType, int n, Object object) {
            if ((n & 1) != 0) {
                string = viewInfo.key;
            }
            if ((n & 2) != 0) {
                string2 = viewInfo.name;
            }
            if ((n & 4) != 0) {
                viewType = viewInfo.type;
            }
            return viewInfo.copy(string, string2, viewType);
        }

        @NotNull
        public String toString() {
            return "ViewInfo(key=" + this.key + ", name=" + this.name + ", type=" + this.type + ")";
        }

        public int hashCode() {
            int result = this.key.hashCode();
            result = result * 31 + this.name.hashCode();
            result = result * 31 + this.type.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ViewInfo)) {
                return false;
            }
            ViewInfo viewInfo = (ViewInfo)other;
            if (!Intrinsics.areEqual((Object)this.key, (Object)viewInfo.key)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.name, (Object)viewInfo.name)) {
                return false;
            }
            return this.type == viewInfo.type;
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2={"Lcom/jakubjirak/structurizr/export/StructurizrExportService$ViewType;", "", "<init>", "(Ljava/lang/String;I)V", "SYSTEM_LANDSCAPE", "SYSTEM_CONTEXT", "CONTAINER", "COMPONENT", "DEPLOYMENT", "DYNAMIC", "FILTERED", "structurizr-dsl-plugin"})
    public static enum ViewType {
        SYSTEM_LANDSCAPE,
        SYSTEM_CONTEXT,
        CONTAINER,
        COMPONENT,
        DEPLOYMENT,
        DYNAMIC,
        FILTERED;

        private static final /* synthetic */ EnumEntries $ENTRIES;

        @NotNull
        public static EnumEntries<ViewType> getEntries() {
            return $ENTRIES;
        }

        static {
            $ENTRIES = EnumEntriesKt.enumEntries((Enum[])values());
        }
    }

    @Metadata(mv={2, 0, 0}, k=3, xi=48)
    public final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[DiagramExportFormat.values().length];
            try {
                nArray[DiagramExportFormat.SVG.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[DiagramExportFormat.PNG.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[DiagramExportFormat.PLANTUML.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[DiagramExportFormat.MERMAID.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[DiagramExportFormat.DOT.ordinal()] = 5;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[DiagramExportFormat.JSON.ordinal()] = 6;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}
