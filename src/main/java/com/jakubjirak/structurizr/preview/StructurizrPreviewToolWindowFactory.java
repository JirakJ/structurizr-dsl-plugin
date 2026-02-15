/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.project.DumbAware
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.wm.ToolWindow
 *  com.intellij.openapi.wm.ToolWindowFactory
 *  com.intellij.ui.content.Content
 *  com.intellij.ui.content.ContentFactory
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.preview;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.jakubjirak.structurizr.preview.StructurizrPreviewContent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\r"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewToolWindowFactory;", "Lcom/intellij/openapi/wm/ToolWindowFactory;", "Lcom/intellij/openapi/project/DumbAware;", "<init>", "()V", "createToolWindowContent", "", "project", "Lcom/intellij/openapi/project/Project;", "toolWindow", "Lcom/intellij/openapi/wm/ToolWindow;", "shouldBeAvailable", "", "structurizr-dsl-plugin"})
public final class StructurizrPreviewToolWindowFactory
implements ToolWindowFactory,
DumbAware {
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)toolWindow, (String)"toolWindow");
        StructurizrPreviewContent previewContent = new StructurizrPreviewContent(project);
        Content content = ContentFactory.getInstance().createContent(previewContent.getComponent(), "Preview", false);
        Intrinsics.checkNotNullExpressionValue((Object)content, (String)"createContent(...)");
        Content content2 = content;
        toolWindow.getContentManager().addContent(content2);
    }

    public boolean shouldBeAvailable(@NotNull Project project) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        return true;
    }
}
