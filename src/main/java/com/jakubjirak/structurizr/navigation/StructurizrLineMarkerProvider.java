/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
 *  com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
 *  com.intellij.psi.PsiElement
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.navigation;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\n\b\u0000\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u0014\u00a8\u0006\u000b"}, d2={"Lcom/jakubjirak/structurizr/navigation/StructurizrLineMarkerProvider;", "Lcom/intellij/codeInsight/daemon/RelatedItemLineMarkerProvider;", "<init>", "()V", "collectNavigationMarkers", "", "element", "Lcom/intellij/psi/PsiElement;", "result", "", "Lcom/intellij/codeInsight/daemon/RelatedItemLineMarkerInfo;", "structurizr-dsl-plugin"})
public final class StructurizrLineMarkerProvider
extends RelatedItemLineMarkerProvider {
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Intrinsics.checkNotNullParameter(result, (String)"result");
    }
}
