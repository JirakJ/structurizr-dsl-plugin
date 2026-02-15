/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.Disposable
 *  com.intellij.openapi.fileEditor.FileEditor
 *  com.intellij.openapi.fileEditor.FileEditorState
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.util.Disposer
 *  com.intellij.openapi.util.UserDataHolderBase
 *  com.intellij.openapi.vfs.VirtualFile
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.preview;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.jakubjirak.structurizr.preview.StructurizrPreviewPanel;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001d\u001a\u00020\fH\u0016J\b\u0010\u001e\u001a\u00020\u0006H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewFileEditor;", "Lcom/intellij/openapi/util/UserDataHolderBase;", "Lcom/intellij/openapi/fileEditor/FileEditor;", "project", "Lcom/intellij/openapi/project/Project;", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "<init>", "(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;)V", "previewPanel", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewPanel;", "updatePreview", "", "content", "", "getComponent", "Ljavax/swing/JComponent;", "getPreferredFocusedComponent", "getName", "setState", "state", "Lcom/intellij/openapi/fileEditor/FileEditorState;", "isModified", "", "isValid", "addPropertyChangeListener", "listener", "Ljava/beans/PropertyChangeListener;", "removePropertyChangeListener", "dispose", "getFile", "structurizr-dsl-plugin"})
public final class StructurizrPreviewFileEditor
extends UserDataHolderBase
implements FileEditor {
    @NotNull
    private final Project project;
    @NotNull
    private final VirtualFile file;
    @NotNull
    private final StructurizrPreviewPanel previewPanel;

    public StructurizrPreviewFileEditor(@NotNull Project project, @NotNull VirtualFile file) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)file, (String)"file");
        this.project = project;
        this.file = file;
        this.previewPanel = new StructurizrPreviewPanel(this.project);
    }

    public final void updatePreview(@NotNull String content) {
        Intrinsics.checkNotNullParameter((Object)content, (String)"content");
        StructurizrPreviewPanel.updatePreview$default(this.previewPanel, content, null, 2, null);
    }

    @NotNull
    public JComponent getComponent() {
        return this.previewPanel;
    }

    @NotNull
    public JComponent getPreferredFocusedComponent() {
        return this.previewPanel;
    }

    @NotNull
    public String getName() {
        return "Preview";
    }

    public void setState(@NotNull FileEditorState state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
    }

    public boolean isModified() {
        return false;
    }

    public boolean isValid() {
        return this.file.isValid();
    }

    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {
        Intrinsics.checkNotNullParameter((Object)listener, (String)"listener");
    }

    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {
        Intrinsics.checkNotNullParameter((Object)listener, (String)"listener");
    }

    public void dispose() {
        Disposer.dispose((Disposable)this.previewPanel);
    }

    @NotNull
    public VirtualFile getFile() {
        return this.file;
    }
}
