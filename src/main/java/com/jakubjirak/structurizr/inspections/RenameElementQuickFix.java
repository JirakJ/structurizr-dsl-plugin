/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInspection.LocalQuickFix
 *  com.intellij.codeInspection.ProblemDescriptor
 *  com.intellij.openapi.application.ApplicationManager
 *  com.intellij.openapi.editor.Editor
 *  com.intellij.openapi.fileEditor.FileEditorManager
 *  com.intellij.openapi.project.Project
 *  com.intellij.psi.PsiElement
 *  com.intellij.refactoring.RefactoringFactory
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.RefactoringFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016\u00a8\u0006\f"}, d2={"Lcom/jakubjirak/structurizr/inspections/RenameElementQuickFix;", "Lcom/intellij/codeInspection/LocalQuickFix;", "<init>", "()V", "getFamilyName", "", "applyFix", "", "project", "Lcom/intellij/openapi/project/Project;", "descriptor", "Lcom/intellij/codeInspection/ProblemDescriptor;", "structurizr-dsl-plugin"})
public final class RenameElementQuickFix
implements LocalQuickFix {
    @NotNull
    public String getFamilyName() {
        return "Rename element";
    }

    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)descriptor, (String)"descriptor");
        PsiElement psiElement = descriptor.getPsiElement();
        if (psiElement == null) {
            return;
        }
        PsiElement element = psiElement;
        ApplicationManager.getApplication().invokeLater(() -> RenameElementQuickFix.applyFix$lambda$0(project, element));
    }

    private static final void applyFix$lambda$0(Project $project, PsiElement $element) {
        Editor editor = FileEditorManager.getInstance((Project)$project).getSelectedTextEditor();
        if (editor != null) {
            RefactoringFactory.getInstance((Project)$project).createRename($element, null).run();
        }
    }
}
