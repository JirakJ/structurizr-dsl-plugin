/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInspection.LocalQuickFix
 *  com.intellij.codeInspection.ProblemHighlightType
 *  com.intellij.codeInspection.ProblemsHolder
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.PsiElementVisitor
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
import com.jakubjirak.structurizr.inspections.StructurizrInspectionBase;
import com.jakubjirak.structurizr.language.psi.StructurizrEnterpriseBlock;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016\u00a8\u0006\r"}, d2={"Lcom/jakubjirak/structurizr/inspections/DeprecatedKeywordInspection;", "Lcom/jakubjirak/structurizr/inspections/StructurizrInspectionBase;", "<init>", "()V", "getDisplayName", "", "getShortName", "buildVisitor", "Lcom/intellij/psi/PsiElementVisitor;", "holder", "Lcom/intellij/codeInspection/ProblemsHolder;", "isOnTheFly", "", "structurizr-dsl-plugin"})
public final class DeprecatedKeywordInspection
extends StructurizrInspectionBase {
    @NotNull
    public String getDisplayName() {
        return "Deprecated keyword";
    }

    @NotNull
    public String getShortName() {
        return "StructurizrDeprecatedKeyword";
    }

    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        Intrinsics.checkNotNullParameter((Object)holder, (String)"holder");
        return new PsiElementVisitor(){
            final /* synthetic */ ProblemsHolder $holder = holder;

            public void visitElement(PsiElement element) {
                Intrinsics.checkNotNullParameter((Object)element, (String)"element");
                if (element instanceof StructurizrEnterpriseBlock) {
                    PsiElement psiElement = ((StructurizrEnterpriseBlock)element).getFirstChild();
                    if (psiElement == null) {
                        psiElement = element;
                    }
                    this.$holder.registerProblem(psiElement, "'enterprise' is deprecated. Use 'group' with enterprise boundary instead.", ProblemHighlightType.LIKE_DEPRECATED, new LocalQuickFix[0]);
                }
            }
        };
    }
}
