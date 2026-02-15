/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInspection.LocalQuickFix
 *  com.intellij.codeInspection.ProblemHighlightType
 *  com.intellij.codeInspection.ProblemsHolder
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.PsiElementVisitor
 *  com.intellij.psi.PsiFile
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.SetsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.jakubjirak.structurizr.inspections.CreateElementQuickFix;
import com.jakubjirak.structurizr.inspections.StructurizrInspectionBase;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrElementSelector;
import com.jakubjirak.structurizr.language.psi.StructurizrFile;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0005H\u0002\u00a8\u0006\u0015"}, d2={"Lcom/jakubjirak/structurizr/inspections/UndefinedReferenceInspection;", "Lcom/jakubjirak/structurizr/inspections/StructurizrInspectionBase;", "<init>", "()V", "getDisplayName", "", "getShortName", "buildVisitor", "Lcom/intellij/psi/PsiElementVisitor;", "holder", "Lcom/intellij/codeInspection/ProblemsHolder;", "isOnTheFly", "", "checkElementReference", "", "element", "Lcom/jakubjirak/structurizr/language/psi/StructurizrElementReference;", "checkElementSelector", "Lcom/jakubjirak/structurizr/language/psi/StructurizrElementSelector;", "isBuiltInKeyword", "text", "structurizr-dsl-plugin"})
public final class UndefinedReferenceInspection
extends StructurizrInspectionBase {
    @NotNull
    public String getDisplayName() {
        return "Undefined reference";
    }

    @NotNull
    public String getShortName() {
        return "StructurizrUndefinedReference";
    }

    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        Intrinsics.checkNotNullParameter((Object)holder, (String)"holder");
        return new PsiElementVisitor(){
            final /* synthetic */ UndefinedReferenceInspection this$0 = UndefinedReferenceInspection.this;
            final /* synthetic */ ProblemsHolder $holder = holder;

            public void visitElement(PsiElement element) {
                Intrinsics.checkNotNullParameter((Object)element, (String)"element");
                if (!(element.getContainingFile() instanceof StructurizrFile)) {
                    return;
                }
                PsiElement psiElement = element;
                if (psiElement instanceof StructurizrElementReference) {
                    UndefinedReferenceInspection.access$checkElementReference(this.this$0, (StructurizrElementReference)element, this.$holder);
                } else if (psiElement instanceof StructurizrElementSelector) {
                    UndefinedReferenceInspection.access$checkElementSelector(this.this$0, (StructurizrElementSelector)element, this.$holder);
                }
            }
        };
    }

    private final void checkElementReference(StructurizrElementReference element, ProblemsHolder holder) {
        String[] stringArray;
        String rootIdentifier;
        String text = element.getText();
        Intrinsics.checkNotNull((Object)text);
        if (((CharSequence)text).length() == 0 || this.isBuiltInKeyword(text)) {
            return;
        }
        PsiFile psiFile = element.getContainingFile();
        if (psiFile == null) {
            return;
        }
        PsiFile file = psiFile;
        Set<String> definedElements = this.collectDefinedElements(file);
        if (!definedElements.contains(rootIdentifier = (String)CollectionsKt.first((List)StringsKt.split((CharSequence)text, (String[])(stringArray = new String[]{"."}), (boolean)false, (int)0)))) {
            LocalQuickFix[] quickFixes = new LocalQuickFix[]{new CreateElementQuickFix(rootIdentifier)};
            holder.registerProblem((PsiElement)element, "Undefined reference: '" + rootIdentifier + "'", ProblemHighlightType.ERROR, quickFixes);
        }
    }

    private final void checkElementSelector(StructurizrElementSelector element, ProblemsHolder holder) {
        String[] stringArray;
        String identifier;
        String string = element.getText();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
        String text = ((Object)StringsKt.trim((CharSequence)string)).toString();
        if (Intrinsics.areEqual((Object)text, (Object)"*") || StringsKt.startsWith((String)text, (String)"->", (boolean)false) || StringsKt.contains((CharSequence)text, (CharSequence)"->", (boolean)false) || ((CharSequence)text).length() == 0) {
            return;
        }
        PsiFile psiFile = element.getContainingFile();
        if (psiFile == null) {
            return;
        }
        PsiFile file = psiFile;
        Set<String> definedElements = this.collectDefinedElements(file);
        if (!definedElements.contains(identifier = (String)CollectionsKt.first((List)StringsKt.split((CharSequence)text, (String[])(stringArray = new String[]{"."}), (boolean)false, (int)0))) && !this.isBuiltInKeyword(identifier)) {
            holder.registerProblem((PsiElement)element, "Undefined reference: '" + identifier + "'", ProblemHighlightType.WARNING, new LocalQuickFix[0]);
        }
    }

    private final boolean isBuiltInKeyword(String text) {
        Object[] objectArray = new String[]{"this", "source", "destination", "*", "element", "relationship", "->", "person", "softwareSystem", "container", "component", "deploymentNode", "infrastructureNode", "include", "exclude", "autoLayout"};
        return SetsKt.setOf((Object[])objectArray).contains(text);
    }

    public static final /* synthetic */ void access$checkElementReference(UndefinedReferenceInspection $this, StructurizrElementReference element, ProblemsHolder holder) {
        $this.checkElementReference(element, holder);
    }

    public static final /* synthetic */ void access$checkElementSelector(UndefinedReferenceInspection $this, StructurizrElementSelector element, ProblemsHolder holder) {
        $this.checkElementSelector(element, holder);
    }
}
