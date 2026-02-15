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
 *  com.intellij.psi.util.PsiTreeUtil
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
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
import com.intellij.psi.util.PsiTreeUtil;
import com.jakubjirak.structurizr.inspections.RenameElementQuickFix;
import com.jakubjirak.structurizr.inspections.StructurizrInspectionBase;
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016\u00a8\u0006\r"}, d2={"Lcom/jakubjirak/structurizr/inspections/DuplicateIdentifierInspection;", "Lcom/jakubjirak/structurizr/inspections/StructurizrInspectionBase;", "<init>", "()V", "getDisplayName", "", "getShortName", "buildVisitor", "Lcom/intellij/psi/PsiElementVisitor;", "holder", "Lcom/intellij/codeInspection/ProblemsHolder;", "isOnTheFly", "", "structurizr-dsl-plugin"})
public final class DuplicateIdentifierInspection
extends StructurizrInspectionBase {
    @NotNull
    public String getDisplayName() {
        return "Duplicate identifier";
    }

    @NotNull
    public String getShortName() {
        return "StructurizrDuplicateIdentifier";
    }

    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        Intrinsics.checkNotNullParameter((Object)holder, (String)"holder");
        return new PsiElementVisitor(){
            final /* synthetic */ ProblemsHolder $holder = holder;

            public void visitElement(PsiElement element) {
                Intrinsics.checkNotNullParameter((Object)element, (String)"element");
                if (!(element instanceof StructurizrElementAssignment)) {
                    return;
                }
                PsiElement psiElement = ((StructurizrElementAssignment)element).getIdentifier();
                if (psiElement == null) {
                    return;
                }
                PsiElement identifierElement = psiElement;
                String identifierText = identifierElement.getText();
                PsiFile file = ((StructurizrElementAssignment)element).getContainingFile();
                Collection collection = PsiTreeUtil.findChildrenOfType((PsiElement)((PsiElement)file), StructurizrElementAssignment.class);
                Intrinsics.checkNotNullExpressionValue((Object)collection, (String)"findChildrenOfType(...)");
                Collection allAssignments = collection;
                Iterable $this$filter$iv = allAssignments;
                boolean $i$f$filter = false;
                Iterable iterable = $this$filter$iv;
                Collection destination$iv$iv = new ArrayList<>();
                boolean $i$f$filterTo = false;
                for (Object element$iv$iv : iterable) {
                    StructurizrElementAssignment it = (StructurizrElementAssignment)element$iv$iv;
                    boolean bl = false;
                    PsiElement psiElement2 = it.getIdentifier();
                    if (!Intrinsics.areEqual((Object)(psiElement2 != null ? psiElement2.getText() : null), (Object)identifierText)) continue;
                    destination$iv$iv.add(element$iv$iv);
                }
                List duplicates = (List)destination$iv$iv;
                if (duplicates.size() > 1 && !Intrinsics.areEqual((Object)element, (Object)CollectionsKt.first((List)duplicates))) {
                    LocalQuickFix[] localQuickFixArray = new LocalQuickFix[]{new RenameElementQuickFix()};
                    this.$holder.registerProblem(identifierElement, "Duplicate identifier: '" + identifierText + "'", ProblemHighlightType.ERROR, localQuickFixArray);
                }
            }
        };
    }
}
