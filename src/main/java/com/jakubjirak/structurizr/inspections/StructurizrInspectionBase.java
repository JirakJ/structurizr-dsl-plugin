/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInspection.LocalInspectionTool
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.PsiFile
 *  com.intellij.psi.util.PsiTreeUtil
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.inspections;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrElementSelector;
import com.jakubjirak.structurizr.language.psi.StructurizrRefStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\n\u001a\u00020\u000bH\u0004J\u001c\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\n\u001a\u00020\u000bH\u0004J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\n\u001a\u00020\u000bH\u0004\u00a8\u0006\u0010"}, d2={"Lcom/jakubjirak/structurizr/inspections/StructurizrInspectionBase;", "Lcom/intellij/codeInspection/LocalInspectionTool;", "<init>", "()V", "getGroupDisplayName", "", "isEnabledByDefault", "", "collectDefinedElements", "", "file", "Lcom/intellij/psi/PsiFile;", "collectDefinedElementsWithPsi", "", "Lcom/intellij/psi/PsiElement;", "collectUsedElements", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrInspections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrInspections.kt\ncom/jakubjirak/structurizr/inspections/StructurizrInspectionBase\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,501:1\n1855#2:502\n1856#2:504\n1855#2,2:505\n1855#2:507\n1855#2,2:508\n1856#2:510\n1855#2,2:511\n1855#2,2:513\n1855#2,2:515\n1#3:503\n*S KotlinDebug\n*F\n+ 1 StructurizrInspections.kt\ncom/jakubjirak/structurizr/inspections/StructurizrInspectionBase\n*L\n24#1:502\n24#1:504\n35#1:505,2\n49#1:507\n50#1:508,2\n49#1:510\n56#1:511,2\n61#1:513,2\n66#1:515,2\n*E\n"})
public abstract class StructurizrInspectionBase
extends LocalInspectionTool {
    @NotNull
    public String getGroupDisplayName() {
        return "Structurizr";
    }

    public boolean isEnabledByDefault() {
        return true;
    }

    @NotNull
    protected final Set<String> collectDefinedElements(@NotNull PsiFile file) {
        Intrinsics.checkNotNullParameter((Object)file, (String)"file");
        Set elements = new LinkedHashSet();
        Collection collection = PsiTreeUtil.findChildrenOfType((PsiElement)((PsiElement)file), StructurizrElementAssignment.class);
        Intrinsics.checkNotNullExpressionValue((Object)collection, (String)"findChildrenOfType(...)");
        Iterable $this$forEach$iv = collection;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            StructurizrElementAssignment assignment = (StructurizrElementAssignment)element$iv;
            boolean bl = false;
            Object object = assignment.getIdentifier();
            if (object == null || (object = ((PsiElement)object).getText()) == null) continue;
            Object it = object;
            boolean bl2 = false;
            elements.add(it);
        }
        return elements;
    }

    @NotNull
    protected final Map<String, PsiElement> collectDefinedElementsWithPsi(@NotNull PsiFile file) {
        Intrinsics.checkNotNullParameter((Object)file, (String)"file");
        Map elements = new LinkedHashMap();
        Collection collection = PsiTreeUtil.findChildrenOfType((PsiElement)((PsiElement)file), StructurizrElementAssignment.class);
        Intrinsics.checkNotNullExpressionValue((Object)collection, (String)"findChildrenOfType(...)");
        Iterable $this$forEach$iv = collection;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            StructurizrElementAssignment assignment = (StructurizrElementAssignment)element$iv;
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue((Object)assignment.getIdentifier(), (String)"getIdentifier(...)");
            PsiElement identifier = assignment.getIdentifier();
            Object object = identifier;
            if (object == null || (object = ((PsiElement)object).getText()) == null) continue;
            Object it = object;
            boolean bl2 = false;
            elements.put(it, identifier);
        }
        return elements;
    }

    @NotNull
    protected final Set<String> collectUsedElements(@NotNull PsiFile file) {
        Object ref;
        String[] $this$forEach$iv;
        boolean bl;
        Intrinsics.checkNotNullParameter((Object)file, (String)"file");
        Set used = new LinkedHashSet();
        Collection collection = PsiTreeUtil.findChildrenOfType((PsiElement)((PsiElement)file), StructurizrRelationshipStatement.class);
        Intrinsics.checkNotNullExpressionValue((Object)collection, (String)"findChildrenOfType(...)");
        Iterable $this$forEach$iv2 = collection;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv2) {
            StructurizrRelationshipStatement rel = (StructurizrRelationshipStatement)element$iv;
            bl = false;
            List<StructurizrElementReference> list = rel.getElementReferenceList();
            Intrinsics.checkNotNullExpressionValue(list, (String)"getElementReferenceList(...)");
            Iterable refList = (Iterable)list;
            boolean $i$f$forEach2 = false;
            for (Object t : refList) {
                StructurizrElementReference ref2 = (StructurizrElementReference)t;
                boolean bl2 = false;
                String string = ref2.getText();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
                used.add(string);
            }
        }
        Collection collection2 = PsiTreeUtil.findChildrenOfType((PsiElement)((PsiElement)file), StructurizrElementReference.class);
        Intrinsics.checkNotNullExpressionValue((Object)collection2, (String)"findChildrenOfType(...)");
        Iterable $this$forEach$iv3 = collection2;
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv3) {
            ref = (StructurizrElementReference)element$iv;
            bl = false;
            String string = ((PsiElement)ref).getText();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
            used.add(string);
        }
        Collection collection3 = PsiTreeUtil.findChildrenOfType((PsiElement)((PsiElement)file), StructurizrRefStatement.class);
        Intrinsics.checkNotNullExpressionValue((Object)collection3, (String)"findChildrenOfType(...)");
        $this$forEach$iv3 = collection3;
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv3) {
            String it;
            ref = (StructurizrRefStatement)element$iv;
            bl = false;
            String string2 = ((PsiElement)ref).getText();
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getText(...)");
            String[] splitDelimiters = new String[]{"."};
            it = (String)CollectionsKt.firstOrNull((List)StringsKt.split((CharSequence)((Object)StringsKt.trim((CharSequence)StringsKt.removePrefix((String)string2, (CharSequence)"!ref"))).toString(), (String[])splitDelimiters, (boolean)false, (int)0));
            if (it == null) continue;
            boolean bl3 = false;
            used.add(it);
        }
        Collection collection4 = PsiTreeUtil.findChildrenOfType((PsiElement)((PsiElement)file), StructurizrElementSelector.class);
        Intrinsics.checkNotNullExpressionValue((Object)collection4, (String)"findChildrenOfType(...)");
        $this$forEach$iv3 = collection4;
        $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv3) {
            StructurizrElementSelector sel = (StructurizrElementSelector)element$iv;
            bl = false;
            String string = sel.getText();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
            String text = ((Object)StringsKt.trim((CharSequence)string)).toString();
            if (Intrinsics.areEqual((Object)text, (Object)"*") || StringsKt.startsWith((String)text, (String)"->", (boolean)false) || StringsKt.contains((CharSequence)text, (CharSequence)"->", (boolean)false)) continue;
            used.add(text);
        }
        return used;
    }
}
