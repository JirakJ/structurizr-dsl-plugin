/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.ide.structureView.StructureViewModel$ElementInfoProvider
 *  com.intellij.ide.structureView.StructureViewModelBase
 *  com.intellij.ide.structureView.StructureViewTreeElement
 *  com.intellij.openapi.editor.Editor
 *  com.intellij.psi.PsiFile
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.navigation;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.jakubjirak.structurizr.navigation.StructurizrStructureViewElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016\u00a8\u0006\u000e"}, d2={"Lcom/jakubjirak/structurizr/navigation/StructurizrStructureViewModel;", "Lcom/intellij/ide/structureView/StructureViewModelBase;", "Lcom/intellij/ide/structureView/StructureViewModel$ElementInfoProvider;", "psiFile", "Lcom/intellij/psi/PsiFile;", "editor", "Lcom/intellij/openapi/editor/Editor;", "<init>", "(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;)V", "isAlwaysShowsPlus", "", "element", "Lcom/intellij/ide/structureView/StructureViewTreeElement;", "isAlwaysLeaf", "structurizr-dsl-plugin"})
public final class StructurizrStructureViewModel
extends StructureViewModelBase
implements StructureViewModel.ElementInfoProvider {
    public StructurizrStructureViewModel(@NotNull PsiFile psiFile, @Nullable Editor editor) {
        super(psiFile, editor, (StructureViewTreeElement)new StructurizrStructureViewElement((com.intellij.psi.PsiElement)psiFile));
        Intrinsics.checkNotNullParameter((Object)psiFile, (String)"psiFile");
    }

    public boolean isAlwaysShowsPlus(@NotNull StructureViewTreeElement element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return false;
    }

    public boolean isAlwaysLeaf(@NotNull StructureViewTreeElement element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return false;
    }
}
