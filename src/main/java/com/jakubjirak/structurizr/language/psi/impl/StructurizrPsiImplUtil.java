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
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension(value={"SMAP\nStructurizrPsiImplUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrPsiImplUtil.kt\ncom/jakubjirak/structurizr/language/psi/impl/StructurizrPsiImplUtil\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,115:1\n1282#2,2:116\n*S KotlinDebug\n*F\n+ 1 StructurizrPsiImplUtil.kt\ncom/jakubjirak/structurizr/language/psi/impl/StructurizrPsiImplUtil\n*L\n98#1:116,2\n*E\n"})
public final class StructurizrPsiImplUtil {
    @NotNull
    public static final StructurizrPsiImplUtil INSTANCE = new StructurizrPsiImplUtil();

    private StructurizrPsiImplUtil() {
    }

    @JvmStatic
    @Nullable
    public static final String getName(@NotNull StructurizrPersonDefinition element) {
        return INSTANCE.extractName(element);
    }

    @JvmStatic
    @Nullable
    public static final String getDescription(@NotNull StructurizrPersonDefinition element) {
        Object object = element.getDescriptionString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTags(@NotNull StructurizrPersonDefinition element) {
        Object object = element.getTagsString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getName(@NotNull StructurizrSoftwareSystemDefinition element) {
        return INSTANCE.extractName(element);
    }

    @JvmStatic
    @Nullable
    public static final String getDescription(@NotNull StructurizrSoftwareSystemDefinition element) {
        Object object = element.getDescriptionString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTags(@NotNull StructurizrSoftwareSystemDefinition element) {
        Object object = element.getTagsString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getName(@NotNull StructurizrContainerDefinition element) {
        return INSTANCE.extractName(element);
    }

    @JvmStatic
    @Nullable
    public static final String getDescription(@NotNull StructurizrContainerDefinition element) {
        Object object = element.getDescriptionString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTechnology(@NotNull StructurizrContainerDefinition element) {
        Object object = element.getTechnologyString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTags(@NotNull StructurizrContainerDefinition element) {
        Object object = element.getTagsString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getName(@NotNull StructurizrComponentDefinition element) {
        return INSTANCE.extractName(element);
    }

    @JvmStatic
    @Nullable
    public static final String getDescription(@NotNull StructurizrComponentDefinition element) {
        Object object = element.getDescriptionString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTechnology(@NotNull StructurizrComponentDefinition element) {
        Object object = element.getTechnologyString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    @JvmStatic
    @Nullable
    public static final String getTags(@NotNull StructurizrComponentDefinition element) {
        Object object = element.getTagsString();
        return object != null && (object = ((PsiElement)object).getText()) != null ? INSTANCE.unquote((String)object) : null;
    }

    private final String extractName(PsiElement element) {
        PsiElement stringElement;
        Object object;
        Object object2;
        block1: {
            PsiElement[] psiElementArray = element.getChildren();
            Object[] $this$firstOrNull$iv = psiElementArray;
            boolean $i$f$firstOrNull = false;
            for (Object element$iv : $this$firstOrNull$iv) {
                PsiElement it = (PsiElement)element$iv;
                boolean bl = false;
                if (!java.util.Objects.equals(it.getNode().getElementType(), StructurizrTypes.STRING)) continue;
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
        } else {
            string = $this$unquote;
        }
        return string;
    }
}
