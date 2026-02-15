/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.ide.structureView.StructureViewBuilder
 *  com.intellij.ide.structureView.StructureViewModel
 *  com.intellij.ide.structureView.TreeBasedStructureViewBuilder
 *  com.intellij.lang.PsiStructureViewFactory
 *  com.intellij.openapi.editor.Editor
 *  com.intellij.psi.PsiFile
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.navigation;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.jakubjirak.structurizr.language.psi.StructurizrFile;
import com.jakubjirak.structurizr.navigation.StructurizrStructureViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016\u00a8\u0006\b"}, d2={"Lcom/jakubjirak/structurizr/navigation/StructurizrStructureViewFactory;", "Lcom/intellij/lang/PsiStructureViewFactory;", "<init>", "()V", "getStructureViewBuilder", "Lcom/intellij/ide/structureView/StructureViewBuilder;", "psiFile", "Lcom/intellij/psi/PsiFile;", "structurizr-dsl-plugin"})
public final class StructurizrStructureViewFactory
implements PsiStructureViewFactory {
    @Nullable
    public StructureViewBuilder getStructureViewBuilder(@NotNull PsiFile psiFile) {
        Intrinsics.checkNotNullParameter((Object)psiFile, (String)"psiFile");
        if (!(psiFile instanceof StructurizrFile)) {
            return null;
        }
        return (StructureViewBuilder)new TreeBasedStructureViewBuilder(){
            final /* synthetic */ PsiFile $psiFile = psiFile;

            public StructureViewModel createStructureViewModel(Editor editor) {
                return (StructureViewModel)new StructurizrStructureViewModel(this.$psiFile, editor);
            }
        };
    }
}
