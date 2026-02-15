/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.ide.actions.CreateFileFromTemplateAction
 *  com.intellij.ide.actions.CreateFileFromTemplateDialog$Builder
 *  com.intellij.openapi.project.DumbAware
 *  com.intellij.openapi.project.Project
 *  com.intellij.psi.PsiDirectory
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.jakubjirak.structurizr.language.StructurizrIcons;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J$\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0014\u00a8\u0006\u0011"}, d2={"Lcom/jakubjirak/structurizr/actions/NewStructurizrFileAction;", "Lcom/intellij/ide/actions/CreateFileFromTemplateAction;", "Lcom/intellij/openapi/project/DumbAware;", "<init>", "()V", "buildDialog", "", "project", "Lcom/intellij/openapi/project/Project;", "directory", "Lcom/intellij/psi/PsiDirectory;", "builder", "Lcom/intellij/ide/actions/CreateFileFromTemplateDialog$Builder;", "getActionName", "", "newName", "templateName", "structurizr-dsl-plugin"})
public final class NewStructurizrFileAction
extends CreateFileFromTemplateAction
implements DumbAware {
    public NewStructurizrFileAction() {
        super("Structurizr DSL File", "Create a new Structurizr DSL file", StructurizrIcons.FILE);
    }

    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory, @NotNull CreateFileFromTemplateDialog.Builder builder) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)directory, (String)"directory");
        Intrinsics.checkNotNullParameter((Object)builder, (String)"builder");
        builder.setTitle("New Structurizr DSL File").addKind("Workspace", StructurizrIcons.WORKSPACE, "Structurizr Workspace");
    }

    @NotNull
    protected String getActionName(@Nullable PsiDirectory directory, @NotNull String newName, @Nullable String templateName) {
        Intrinsics.checkNotNullParameter((Object)newName, (String)"newName");
        return "Create Structurizr DSL file " + newName;
    }
}
