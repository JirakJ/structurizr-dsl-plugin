/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.ide.actions.RevealFileAction
 *  com.intellij.openapi.actionSystem.AnAction
 *  com.intellij.openapi.actionSystem.AnActionEvent
 *  com.intellij.openapi.actionSystem.CommonDataKeys
 *  com.intellij.openapi.project.DumbAware
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.vfs.VirtualFile
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.actions;

import com.intellij.ide.actions.RevealFileAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jakubjirak.structurizr.export.DiagramExportFormat;
import com.jakubjirak.structurizr.export.ExportDialog;
import com.jakubjirak.structurizr.export.StructurizrExportService;
import com.jakubjirak.structurizr.language.StructurizrFileType;
import java.io.File;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\n"}, d2={"Lcom/jakubjirak/structurizr/actions/ExportDiagramAction;", "Lcom/intellij/openapi/actionSystem/AnAction;", "Lcom/intellij/openapi/project/DumbAware;", "<init>", "()V", "actionPerformed", "", "e", "Lcom/intellij/openapi/actionSystem/AnActionEvent;", "update", "structurizr-dsl-plugin"})
public final class ExportDiagramAction
extends AnAction
implements DumbAware {
    public void actionPerformed(@NotNull AnActionEvent e) {
        Intrinsics.checkNotNullParameter((Object)e, (String)"e");
        Project project = e.getProject();
        if (project == null) {
            return;
        }
        Project project2 = project;
        VirtualFile virtualFile = (VirtualFile)e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (virtualFile == null) {
            return;
        }
        VirtualFile file = virtualFile;
        ExportDialog dialog = new ExportDialog(project2, file);
        if (!dialog.showAndGet()) {
            return;
        }
        StructurizrExportService exportService = StructurizrExportService.Companion.getInstance(project2);
        DiagramExportFormat format = dialog.getSelectedFormat();
        boolean openAfterExport = dialog.isOpenAfterExport();
        if (dialog.isExportAllViews()) {
            Path outputDir = dialog.getOutputDirectory();
            exportService.exportAllViews(file, outputDir, format, (Function2<? super Boolean, ? super String, Unit>)((Function2)(arg_0, arg_1) -> ExportDiagramAction.actionPerformed$lambda$0(openAfterExport, outputDir, (boolean)(Boolean)arg_0, (String)arg_1)));
        } else {
            Path outputPath = dialog.getOutputPath();
            String viewKey = dialog.getSelectedViewKey();
            exportService.export(file, outputPath, format, viewKey, (Function2<? super Boolean, ? super String, Unit>)((Function2)(arg_0, arg_1) -> ExportDiagramAction.actionPerformed$lambda$1(openAfterExport, outputPath, (boolean)(Boolean)arg_0, (String)arg_1)));
        }
    }

    public void update(@NotNull AnActionEvent e) {
        Intrinsics.checkNotNullParameter((Object)e, (String)"e");
        VirtualFile file = (VirtualFile)e.getData(CommonDataKeys.VIRTUAL_FILE);
        VirtualFile virtualFile = file;
        e.getPresentation().setEnabled(Intrinsics.areEqual((Object)(virtualFile != null ? virtualFile.getFileType() : null), (Object)((Object)StructurizrFileType.INSTANCE)));
    }

    private static final Unit actionPerformed$lambda$0(boolean $openAfterExport, Path $outputDir, boolean success, String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        if (success && $openAfterExport) {
            RevealFileAction.openDirectory((File)$outputDir.toFile());
        }
        return Unit.INSTANCE;
    }

    private static final Unit actionPerformed$lambda$1(boolean $openAfterExport, Path $outputPath, boolean success, String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        if (success && $openAfterExport) {
            RevealFileAction.openFile((File)$outputPath.toFile());
        }
        return Unit.INSTANCE;
    }
}
