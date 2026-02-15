/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.annotation.AnnotationHolder
 *  com.intellij.lang.annotation.Annotator
 *  com.intellij.psi.PsiElement
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.highlighting;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016\u00a8\u0006\n"}, d2={"Lcom/jakubjirak/structurizr/highlighting/StructurizrAnnotator;", "Lcom/intellij/lang/annotation/Annotator;", "<init>", "()V", "annotate", "", "element", "Lcom/intellij/psi/PsiElement;", "holder", "Lcom/intellij/lang/annotation/AnnotationHolder;", "structurizr-dsl-plugin"})
public final class StructurizrAnnotator
implements Annotator {
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Intrinsics.checkNotNullParameter((Object)holder, (String)"holder");
    }
}
