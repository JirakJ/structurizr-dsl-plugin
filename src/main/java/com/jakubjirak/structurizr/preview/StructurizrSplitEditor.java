/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.Disposable
 *  com.intellij.openapi.editor.Document
 *  com.intellij.openapi.editor.Editor
 *  com.intellij.openapi.editor.event.DocumentEvent
 *  com.intellij.openapi.editor.event.DocumentListener
 *  com.intellij.openapi.fileEditor.FileEditor
 *  com.intellij.openapi.fileEditor.TextEditor
 *  com.intellij.openapi.fileEditor.TextEditorWithPreview
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.vfs.VirtualFile
 *  com.intellij.pom.Navigatable
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.preview;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.fileEditor.TextEditorWithPreview;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.jakubjirak.structurizr.preview.StructurizrPreviewFileEditor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010 \u001a\u00020\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrSplitEditor;", "Lcom/intellij/openapi/fileEditor/TextEditorWithPreview;", "Lcom/intellij/openapi/fileEditor/TextEditor;", "project", "Lcom/intellij/openapi/project/Project;", "textEditor", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "<init>", "(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/fileEditor/TextEditor;Lcom/intellij/openapi/vfs/VirtualFile;)V", "previewEditor", "Lcom/jakubjirak/structurizr/preview/StructurizrPreviewFileEditor;", "getPreviewEditor", "()Lcom/jakubjirak/structurizr/preview/StructurizrPreviewFileEditor;", "scheduler", "Ljava/util/concurrent/ScheduledExecutorService;", "kotlin.jvm.PlatformType", "Ljava/util/concurrent/ScheduledExecutorService;", "updateTask", "Ljava/util/concurrent/ScheduledFuture;", "updateDelayMs", "", "schedulePreviewUpdate", "", "updatePreview", "getEditor", "Lcom/intellij/openapi/editor/Editor;", "canNavigateTo", "", "navigatable", "Lcom/intellij/pom/Navigatable;", "navigateTo", "dispose", "structurizr-dsl-plugin"})
public final class StructurizrSplitEditor
extends TextEditorWithPreview
implements TextEditor {
    @NotNull
    private final Project project;
    @NotNull
    private final TextEditor textEditor;
    @NotNull
    private final VirtualFile file;
    private final ScheduledExecutorService scheduler;
    @Nullable
    private ScheduledFuture<?> updateTask;
    private final long updateDelayMs;

    public StructurizrSplitEditor(@NotNull Project project, @NotNull TextEditor textEditor, @NotNull VirtualFile file) {
        super(textEditor, (FileEditor)new StructurizrPreviewFileEditor(project, file), "Structurizr Editor");
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)textEditor, (String)"textEditor");
        Intrinsics.checkNotNullParameter((Object)file, (String)"file");
        this.project = project;
        this.textEditor = textEditor;
        this.file = file;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.updateDelayMs = 500L;
        this.textEditor.getEditor().getDocument().addDocumentListener(new DocumentListener(){

            public void documentChanged(DocumentEvent event) {
                Intrinsics.checkNotNullParameter((Object)event, (String)"event");
                StructurizrSplitEditor.this.schedulePreviewUpdate();
            }
        }, (Disposable)this);
        this.updatePreview();
    }

    private final StructurizrPreviewFileEditor getPreviewEditor2() {
        FileEditor fileEditor = this.myPreview;
        Intrinsics.checkNotNull((Object)fileEditor, (String)"null cannot be cast to non-null type com.jakubjirak.structurizr.preview.StructurizrPreviewFileEditor");
        return (StructurizrPreviewFileEditor)fileEditor;
    }

    private final void schedulePreviewUpdate() {
        ScheduledFuture<?> scheduledFuture = this.updateTask;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.updateTask = this.scheduler.schedule(() -> StructurizrSplitEditor.schedulePreviewUpdate$lambda$0(this), this.updateDelayMs, TimeUnit.MILLISECONDS);
    }

    private final void updatePreview() {
        Document document = this.textEditor.getEditor().getDocument();
        Intrinsics.checkNotNullExpressionValue((Object)document, (String)"getDocument(...)");
        Document document2 = document;
        String string = document2.getText();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
        String content = string;
        this.getPreviewEditor2().updatePreview(content);
    }

    @NotNull
    public Editor getEditor() {
        Editor editor = this.textEditor.getEditor();
        Intrinsics.checkNotNullExpressionValue((Object)editor, (String)"getEditor(...)");
        return editor;
    }

    public boolean canNavigateTo(@NotNull Navigatable navigatable) {
        Intrinsics.checkNotNullParameter((Object)navigatable, (String)"navigatable");
        return this.textEditor.canNavigateTo(navigatable);
    }

    public void navigateTo(@NotNull Navigatable navigatable) {
        Intrinsics.checkNotNullParameter((Object)navigatable, (String)"navigatable");
        this.textEditor.navigateTo(navigatable);
    }

    public void dispose() {
        this.scheduler.shutdown();
        super.dispose();
    }

    private static final void schedulePreviewUpdate$lambda$0(StructurizrSplitEditor this$0) {
        this$0.updatePreview();
    }
}
