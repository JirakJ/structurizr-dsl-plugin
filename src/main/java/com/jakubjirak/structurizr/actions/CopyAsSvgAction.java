/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.actionSystem.AnAction
 *  com.intellij.openapi.actionSystem.AnActionEvent
 *  com.intellij.openapi.actionSystem.CommonDataKeys
 *  com.intellij.openapi.project.DumbAware
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.vfs.VirtualFile
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jakubjirak.structurizr.export.ClipboardExportService;
import com.jakubjirak.structurizr.language.StructurizrFileType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\n"}, d2={"Lcom/jakubjirak/structurizr/actions/CopyAsSvgAction;", "Lcom/intellij/openapi/actionSystem/AnAction;", "Lcom/intellij/openapi/project/DumbAware;", "<init>", "()V", "actionPerformed", "", "e", "Lcom/intellij/openapi/actionSystem/AnActionEvent;", "update", "structurizr-dsl-plugin"})
public final class CopyAsSvgAction
extends AnAction
implements DumbAware {
    public CopyAsSvgAction() {
        super("Copy as SVG", "Copy diagram as SVG to clipboard", null);
    }

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
        ClipboardExportService clipboardService = ClipboardExportService.Companion.getInstance(project2);
        ClipboardExportService.copyAsSvg$default(clipboardService, file, null, 2, null);
    }

    public void update(@NotNull AnActionEvent e) {
        Intrinsics.checkNotNullParameter((Object)e, (String)"e");
        VirtualFile file = (VirtualFile)e.getData(CommonDataKeys.VIRTUAL_FILE);
        VirtualFile virtualFile = file;
        e.getPresentation().setEnabled(Intrinsics.areEqual((Object)(virtualFile != null ? virtualFile.getFileType() : null), (Object)((Object)StructurizrFileType.INSTANCE)));
    }
}
