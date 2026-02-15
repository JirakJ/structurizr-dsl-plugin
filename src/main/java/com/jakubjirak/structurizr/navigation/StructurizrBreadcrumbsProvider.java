/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.Language
 *  com.intellij.psi.PsiElement
 *  com.intellij.ui.breadcrumbs.BreadcrumbsProvider
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.navigation;

import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider;
import com.jakubjirak.structurizr.language.StructurizrLanguage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016\u00a8\u0006\u000f"}, d2={"Lcom/jakubjirak/structurizr/navigation/StructurizrBreadcrumbsProvider;", "Lcom/intellij/ui/breadcrumbs/BreadcrumbsProvider;", "<init>", "()V", "getLanguages", "", "Lcom/intellij/lang/Language;", "()[Lcom/intellij/lang/Language;", "acceptElement", "", "element", "Lcom/intellij/psi/PsiElement;", "getElementInfo", "", "getElementTooltip", "structurizr-dsl-plugin"})
public final class StructurizrBreadcrumbsProvider
implements BreadcrumbsProvider {
    @NotNull
    public Language[] getLanguages() {
        Language[] languageArray = new Language[]{StructurizrLanguage.INSTANCE};
        return languageArray;
    }

    public boolean acceptElement(@NotNull PsiElement element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return false;
    }

    @NotNull
    public String getElementInfo(@NotNull PsiElement element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        String string = element.getText();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
        return StringsKt.take((String)string, (int)20);
    }

    @Nullable
    public String getElementTooltip(@NotNull PsiElement element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return null;
    }
}
