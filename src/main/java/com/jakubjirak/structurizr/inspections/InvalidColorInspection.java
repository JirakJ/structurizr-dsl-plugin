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
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.SetsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.Regex
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
import com.jakubjirak.structurizr.language.psi.StructurizrStyleProperty;
import com.jakubjirak.structurizr.language.psi.StructurizrStylePropertyName;
import com.jakubjirak.structurizr.language.psi.StructurizrStylePropertyValue;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2={"Lcom/jakubjirak/structurizr/inspections/InvalidColorInspection;", "Lcom/jakubjirak/structurizr/inspections/StructurizrInspectionBase;", "<init>", "()V", "getDisplayName", "", "getShortName", "colorRegex", "Lkotlin/text/Regex;", "buildVisitor", "Lcom/intellij/psi/PsiElementVisitor;", "holder", "Lcom/intellij/codeInspection/ProblemsHolder;", "isOnTheFly", "", "checkColorProperty", "", "element", "Lcom/jakubjirak/structurizr/language/psi/StructurizrStyleProperty;", "structurizr-dsl-plugin"})
public final class InvalidColorInspection
extends StructurizrInspectionBase {
    @NotNull
    private final Regex colorRegex = new Regex("^#([0-9A-Fa-f]{3}|[0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$");

    @NotNull
    public String getDisplayName() {
        return "Invalid color value";
    }

    @NotNull
    public String getShortName() {
        return "StructurizrInvalidColor";
    }

    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        Intrinsics.checkNotNullParameter((Object)holder, (String)"holder");
        return new PsiElementVisitor(){
            final /* synthetic */ InvalidColorInspection this$0 = InvalidColorInspection.this;
            final /* synthetic */ ProblemsHolder $holder = holder;

            public void visitElement(PsiElement element) {
                Intrinsics.checkNotNullParameter((Object)element, (String)"element");
                if (element instanceof StructurizrStyleProperty) {
                    InvalidColorInspection.access$checkColorProperty(this.this$0, (StructurizrStyleProperty)element, this.$holder);
                }
            }
        };
    }

    private final void checkColorProperty(StructurizrStyleProperty element, ProblemsHolder holder) {
        String string;
        StructurizrStylePropertyName structurizrStylePropertyName = element.getStylePropertyName();
        String propertyName = structurizrStylePropertyName != null ? structurizrStylePropertyName.getText() : null;
        Object object = element.getStylePropertyValue();
        if (object != null && (object = ((PsiElement)object).getText()) != null) {
            char[] cArray = new char[]{'\"'};
            string = StringsKt.trim((String)object, (char[])cArray);
        } else {
            string = null;
        }
        String propertyValue = string;
        Object[] objectArray = new String[]{"background", "color", "stroke"};
        if (CollectionsKt.contains((Iterable)SetsKt.setOf((Object[])objectArray), (Object)propertyName) && propertyValue != null && StringsKt.startsWith((String)propertyValue, (String)"#", (boolean)false) && !this.colorRegex.matches((CharSequence)propertyValue)) {
            StructurizrStylePropertyValue structurizrStylePropertyValue = element.getStylePropertyValue();
            holder.registerProblem(structurizrStylePropertyValue != null ? (PsiElement)structurizrStylePropertyValue : (PsiElement)element, "Invalid color format: '" + propertyValue + "'. Expected #RGB, #RRGGBB, or #RRGGBBAA", ProblemHighlightType.ERROR, new LocalQuickFix[0]);
        }
    }

    public static final /* synthetic */ void access$checkColorProperty(InvalidColorInspection $this, StructurizrStyleProperty element, ProblemsHolder holder) {
        $this.checkColorProperty(element, holder);
    }
}
