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
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.jakubjirak.structurizr.inspections.StructurizrInspectionBase;
import com.jakubjirak.structurizr.language.psi.StructurizrComponentDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrContainerDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrPersonDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002\u00a8\u0006\u0017"}, d2={"Lcom/jakubjirak/structurizr/inspections/MissingRequiredPropertyInspection;", "Lcom/jakubjirak/structurizr/inspections/StructurizrInspectionBase;", "<init>", "()V", "getDisplayName", "", "getShortName", "buildVisitor", "Lcom/intellij/psi/PsiElementVisitor;", "holder", "Lcom/intellij/codeInspection/ProblemsHolder;", "isOnTheFly", "", "checkPersonDefinition", "", "element", "Lcom/jakubjirak/structurizr/language/psi/StructurizrPersonDefinition;", "checkSoftwareSystemDefinition", "Lcom/jakubjirak/structurizr/language/psi/StructurizrSoftwareSystemDefinition;", "checkContainerDefinition", "Lcom/jakubjirak/structurizr/language/psi/StructurizrContainerDefinition;", "checkComponentDefinition", "Lcom/jakubjirak/structurizr/language/psi/StructurizrComponentDefinition;", "structurizr-dsl-plugin"})
public final class MissingRequiredPropertyInspection
extends StructurizrInspectionBase {
    @NotNull
    public String getDisplayName() {
        return "Missing required property";
    }

    @NotNull
    public String getShortName() {
        return "StructurizrMissingRequiredProperty";
    }

    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        Intrinsics.checkNotNullParameter((Object)holder, (String)"holder");
        return new PsiElementVisitor(){
            final /* synthetic */ MissingRequiredPropertyInspection this$0 = MissingRequiredPropertyInspection.this;
            final /* synthetic */ ProblemsHolder $holder = holder;

            public void visitElement(PsiElement element) {
                Intrinsics.checkNotNullParameter((Object)element, (String)"element");
                PsiElement psiElement = element;
                if (psiElement instanceof StructurizrPersonDefinition) {
                    MissingRequiredPropertyInspection.access$checkPersonDefinition(this.this$0, (StructurizrPersonDefinition)element, this.$holder);
                } else if (psiElement instanceof StructurizrSoftwareSystemDefinition) {
                    MissingRequiredPropertyInspection.access$checkSoftwareSystemDefinition(this.this$0, (StructurizrSoftwareSystemDefinition)element, this.$holder);
                } else if (psiElement instanceof StructurizrContainerDefinition) {
                    MissingRequiredPropertyInspection.access$checkContainerDefinition(this.this$0, (StructurizrContainerDefinition)element, this.$holder);
                } else if (psiElement instanceof StructurizrComponentDefinition) {
                    MissingRequiredPropertyInspection.access$checkComponentDefinition(this.this$0, (StructurizrComponentDefinition)element, this.$holder);
                }
            }
        };
    }

    private final void checkPersonDefinition(StructurizrPersonDefinition element, ProblemsHolder holder) {
        String string;
        Object object = element.getString();
        if (object != null && (object = ((PsiElement)object).getText()) != null) {
            char[] cArray = new char[]{'\"'};
            string = StringsKt.trim((String)object, (char[])cArray);
        } else {
            string = null;
        }
        String name = string;
        CharSequence charSequence = name;
        if (charSequence == null || charSequence.length() == 0) {
            PsiElement psiElement = element.getFirstChild();
            if (psiElement == null) {
                psiElement = element;
            }
            holder.registerProblem(psiElement, "Person requires a name", ProblemHighlightType.ERROR, new LocalQuickFix[0]);
        }
    }

    private final void checkSoftwareSystemDefinition(StructurizrSoftwareSystemDefinition element, ProblemsHolder holder) {
        String string;
        Object object = element.getString();
        if (object != null && (object = ((PsiElement)object).getText()) != null) {
            char[] cArray = new char[]{'\"'};
            string = StringsKt.trim((String)object, (char[])cArray);
        } else {
            string = null;
        }
        String name = string;
        CharSequence charSequence = name;
        if (charSequence == null || charSequence.length() == 0) {
            PsiElement psiElement = element.getFirstChild();
            if (psiElement == null) {
                psiElement = element;
            }
            holder.registerProblem(psiElement, "Software system requires a name", ProblemHighlightType.ERROR, new LocalQuickFix[0]);
        }
    }

    private final void checkContainerDefinition(StructurizrContainerDefinition element, ProblemsHolder holder) {
        String string;
        Object object = element.getString();
        if (object != null && (object = ((PsiElement)object).getText()) != null) {
            char[] cArray = new char[]{'\"'};
            string = StringsKt.trim((String)object, (char[])cArray);
        } else {
            string = null;
        }
        String name = string;
        CharSequence charSequence = name;
        if (charSequence == null || charSequence.length() == 0) {
            PsiElement psiElement = element.getFirstChild();
            if (psiElement == null) {
                psiElement = element;
            }
            holder.registerProblem(psiElement, "Container requires a name", ProblemHighlightType.ERROR, new LocalQuickFix[0]);
        }
    }

    private final void checkComponentDefinition(StructurizrComponentDefinition element, ProblemsHolder holder) {
        String string;
        Object object = element.getString();
        if (object != null && (object = ((PsiElement)object).getText()) != null) {
            char[] cArray = new char[]{'\"'};
            string = StringsKt.trim((String)object, (char[])cArray);
        } else {
            string = null;
        }
        String name = string;
        CharSequence charSequence = name;
        if (charSequence == null || charSequence.length() == 0) {
            PsiElement psiElement = element.getFirstChild();
            if (psiElement == null) {
                psiElement = element;
            }
            holder.registerProblem(psiElement, "Component requires a name", ProblemHighlightType.ERROR, new LocalQuickFix[0]);
        }
    }

    public static final /* synthetic */ void access$checkPersonDefinition(MissingRequiredPropertyInspection $this, StructurizrPersonDefinition element, ProblemsHolder holder) {
        $this.checkPersonDefinition(element, holder);
    }

    public static final /* synthetic */ void access$checkSoftwareSystemDefinition(MissingRequiredPropertyInspection $this, StructurizrSoftwareSystemDefinition element, ProblemsHolder holder) {
        $this.checkSoftwareSystemDefinition(element, holder);
    }

    public static final /* synthetic */ void access$checkContainerDefinition(MissingRequiredPropertyInspection $this, StructurizrContainerDefinition element, ProblemsHolder holder) {
        $this.checkContainerDefinition(element, holder);
    }

    public static final /* synthetic */ void access$checkComponentDefinition(MissingRequiredPropertyInspection $this, StructurizrComponentDefinition element, ProblemsHolder holder) {
        $this.checkComponentDefinition(element, holder);
    }
}
