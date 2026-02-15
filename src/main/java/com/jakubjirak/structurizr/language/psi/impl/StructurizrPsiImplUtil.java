/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language.psi.impl;

import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrComponentDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrContainerDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrPersonDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u000bH\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u000bH\u0007J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u000bH\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u000bH\u0007J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\rH\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\rH\u0007J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\rH\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\rH\u0007J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u000fH\u0002J\f\u0010\u0010\u001a\u00020\u0005*\u00020\u0005H\u0002\u00a8\u0006\u0011"}, d2={"Lcom/jakubjirak/structurizr/language/psi/impl/StructurizrPsiImplUtil;", "", "<init>", "()V", "getName", "", "element", "Lcom/jakubjirak/structurizr/language/psi/StructurizrPersonDefinition;", "getDescription", "getTags", "Lcom/jakubjirak/structurizr/language/psi/StructurizrSoftwareSystemDefinition;", "Lcom/jakubjirak/structurizr/language/psi/StructurizrContainerDefinition;", "getTechnology", "Lcom/jakubjirak/structurizr/language/psi/StructurizrComponentDefinition;", "extractName", "Lcom/intellij/psi/PsiElement;", "unquote", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrPsiImplUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrPsiImplUtil.kt\ncom/jakubjirak/structurizr/language/psi/impl/StructurizrPsiImplUtil\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,115:1\n1282#2,2:116\n*S KotlinDebug\n*F\n+ 1 StructurizrPsiImplUtil.kt\ncom/jakubjirak/structurizr/language/psi/impl/StructurizrPsiImplUtil\n*L\n98#1:116,2\n*E\n"})
public final class StructurizrPsiImplUtil {
    @NotNull
    public static final StructurizrPsiImplUtil INSTANCE = new StructurizrPsiImplUtil();

    private StructurizrPsiImplUtil() {
    }

    @JvmStatic
    @Nullable
    public static final String getName(@NotNull StructurizrPersonDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return INSTANCE.extractName(element);
    }

    @JvmStatic
    @Nullable
    public static final String getDescription(@NotNull StructurizrPersonDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Object object = element.getDescriptionString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTags(@NotNull StructurizrPersonDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Object object = element.getTagsString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getName(@NotNull StructurizrSoftwareSystemDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return INSTANCE.extractName(element);
    }

    @JvmStatic
    @Nullable
    public static final String getDescription(@NotNull StructurizrSoftwareSystemDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Object object = element.getDescriptionString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTags(@NotNull StructurizrSoftwareSystemDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Object object = element.getTagsString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getName(@NotNull StructurizrContainerDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return INSTANCE.extractName(element);
    }

    @JvmStatic
    @Nullable
    public static final String getDescription(@NotNull StructurizrContainerDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Object object = element.getDescriptionString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTechnology(@NotNull StructurizrContainerDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Object object = element.getTechnologyString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTags(@NotNull StructurizrContainerDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Object object = element.getTagsString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getName(@NotNull StructurizrComponentDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return INSTANCE.extractName(element);
    }

    @JvmStatic
    @Nullable
    public static final String getDescription(@NotNull StructurizrComponentDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Object object = element.getDescriptionString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTechnology(@NotNull StructurizrComponentDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Object object = element.getTechnologyString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTags(@NotNull StructurizrComponentDefinition element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Object object = element.getTagsString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    private final String extractName(PsiElement element) {
        PsiElement stringElement;
        Object object;
        Object object2;
        block1: {
            PsiElement[] psiElementArray = element.getChildren();
            Intrinsics.checkNotNullExpressionValue((Object)psiElementArray, (String)"getChildren(...)");
            Object[] $this$firstOrNull$iv = psiElementArray;
            boolean $i$f$firstOrNull = false;
            for (Object element$iv : $this$firstOrNull$iv) {
                PsiElement it = (PsiElement)element$iv;
                boolean bl = false;
                if (!Intrinsics.areEqual((Object)it.getNode().getElementType(), (Object)StructurizrTypes.STRING)) continue;
                object2 = element$iv;
                break block1;
            }
            object2 = null;
        }
        return (object = (stringElement = (PsiElement)object2)) != null && (object = ((PsiElement)object).getText()) != null ? this.unquote((String)object) : null;
    }

    private final String unquote(String $this$unquote) {
        String string;
        if (StringsKt.startsWith((String)$this$unquote, (String)"\"", (boolean)false) && StringsKt.endsWith((String)$this$unquote, (String)"\"", (boolean)false) && $this$unquote.length() >= 2) {
            String string2 = $this$unquote.substring(1, $this$unquote.length() - 1);
            string = string2;
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"this as java.lang.String\u2026ing(startIndex, endIndex)");
        } else {
            string = $this$unquote;
        }
        return string;
    }
}
