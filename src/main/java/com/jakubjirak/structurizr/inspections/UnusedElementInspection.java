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
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.jakubjirak.structurizr.inspections.RemoveUnusedElementQuickFix;
import com.jakubjirak.structurizr.inspections.StructurizrInspectionBase;
import com.jakubjirak.structurizr.language.psi.StructurizrFile;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016\u00a8\u0006\r"}, d2={"Lcom/jakubjirak/structurizr/inspections/UnusedElementInspection;", "Lcom/jakubjirak/structurizr/inspections/StructurizrInspectionBase;", "<init>", "()V", "getDisplayName", "", "getShortName", "buildVisitor", "Lcom/intellij/psi/PsiElementVisitor;", "holder", "Lcom/intellij/codeInspection/ProblemsHolder;", "isOnTheFly", "", "structurizr-dsl-plugin"})
public final class UnusedElementInspection
extends StructurizrInspectionBase {
    @NotNull
    public String getDisplayName() {
        return "Unused element";
    }

    @NotNull
    public String getShortName() {
        return "StructurizrUnusedElement";
    }

    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        Intrinsics.checkNotNullParameter((Object)holder, (String)"holder");
        return new PsiElementVisitor(){
            final /* synthetic */ UnusedElementInspection this$0 = UnusedElementInspection.this;
            final /* synthetic */ ProblemsHolder $holder = holder;

            public void visitFile(PsiFile file) {
                Intrinsics.checkNotNullParameter((Object)file, (String)"file");
                if (!(file instanceof StructurizrFile)) {
                    return;
                }
                Map<String, PsiElement> definedElements = this.this$0.collectDefinedElementsWithPsi(file);
                Set<String> usedElements = this.this$0.collectUsedElements(file);
                Map<String, PsiElement> map = definedElements;
                ProblemsHolder problemsHolder = this.$holder;
                boolean $i$f$forEach = false;
                Iterator<Map.Entry<String, PsiElement>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry element$iv;
                    Map.Entry entry = element$iv = iterator.next();
                    boolean bl = false;
                    String name = (String)entry.getKey();
                    PsiElement element = (PsiElement)entry.getValue();
                    if (usedElements.contains(name)) continue;
                    LocalQuickFix[] localQuickFixArray = new LocalQuickFix[]{new RemoveUnusedElementQuickFix(name)};
                    problemsHolder.registerProblem(element, "Unused element: '" + name + "' is defined but never used", ProblemHighlightType.WEAK_WARNING, localQuickFixArray);
                }
            }
        };
    }
}
