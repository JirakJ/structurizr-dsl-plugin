/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.fileEditor.FileEditor
 *  com.intellij.openapi.fileEditor.FileEditorPolicy
 *  com.intellij.openapi.fileEditor.FileEditorProvider
 *  com.intellij.openapi.fileEditor.TextEditor
 *  com.intellij.openapi.fileEditor.impl.text.TextEditorProvider
 *  com.intellij.openapi.project.DumbAware
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.vfs.VirtualFile
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.preview;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jakubjirak.structurizr.language.StructurizrFileType;
import com.jakubjirak.structurizr.preview.StructurizrSplitEditor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016\u00a8\u0006\u0011"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrSplitEditorProvider;", "Lcom/intellij/openapi/fileEditor/FileEditorProvider;", "Lcom/intellij/openapi/project/DumbAware;", "<init>", "()V", "accept", "", "project", "Lcom/intellij/openapi/project/Project;", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "createEditor", "Lcom/intellij/openapi/fileEditor/FileEditor;", "getEditorTypeId", "", "getPolicy", "Lcom/intellij/openapi/fileEditor/FileEditorPolicy;", "structurizr-dsl-plugin"})
public final class StructurizrSplitEditorProvider
implements FileEditorProvider,
DumbAware {
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)file, (String)"file");
        return Intrinsics.areEqual((Object)file.getFileType(), (Object)((Object)StructurizrFileType.INSTANCE));
    }

    @NotNull
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)file, (String)"file");
        FileEditor fileEditor = TextEditorProvider.Companion.getInstance().createEditor(project, file);
        Intrinsics.checkNotNull((Object)fileEditor, (String)"null cannot be cast to non-null type com.intellij.openapi.fileEditor.TextEditor");
        TextEditor textEditor = (TextEditor)fileEditor;
        return (FileEditor)new StructurizrSplitEditor(project, textEditor, file);
    }

    @NotNull
    public String getEditorTypeId() {
        return "structurizr-split-editor";
    }

    @NotNull
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }
}
