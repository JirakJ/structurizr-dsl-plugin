/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.fileTypes.SyntaxHighlighter
 *  com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.vfs.VirtualFile
 *  kotlin.Metadata
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.highlighting;

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jakubjirak.structurizr.highlighting.StructurizrSyntaxHighlighter;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016\u00a8\u0006\n"}, d2={"Lcom/jakubjirak/structurizr/highlighting/StructurizrSyntaxHighlighterFactory;", "Lcom/intellij/openapi/fileTypes/SyntaxHighlighterFactory;", "<init>", "()V", "getSyntaxHighlighter", "Lcom/intellij/openapi/fileTypes/SyntaxHighlighter;", "project", "Lcom/intellij/openapi/project/Project;", "virtualFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "structurizr-dsl-plugin"})
public final class StructurizrSyntaxHighlighterFactory
extends SyntaxHighlighterFactory {
    @NotNull
    public SyntaxHighlighter getSyntaxHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile) {
        return (SyntaxHighlighter)new StructurizrSyntaxHighlighter();
    }
}
