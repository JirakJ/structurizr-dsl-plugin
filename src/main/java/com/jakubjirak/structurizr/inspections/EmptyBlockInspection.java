/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInspection.LocalQuickFix
 *  com.intellij.codeInspection.ProblemHighlightType
 *  com.intellij.codeInspection.ProblemsHolder
 *  com.intellij.psi.PsiComment
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.PsiElementVisitor
 *  com.intellij.psi.PsiWhiteSpace
 *  kotlin.Metadata
 *  kotlin.collections.SetsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiWhiteSpace;
import com.jakubjirak.structurizr.inspections.StructurizrInspectionBase;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentEnvironmentBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrElementStyle;
import com.jakubjirak.structurizr.language.psi.StructurizrGroupBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrModelBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPersonDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrRefStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStyle;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrStylesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrViewsBlock;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\nH\u0002\u00a8\u0006\u0015"}, d2={"Lcom/jakubjirak/structurizr/inspections/EmptyBlockInspection;", "Lcom/jakubjirak/structurizr/inspections/StructurizrInspectionBase;", "<init>", "()V", "getDisplayName", "", "getShortName", "buildVisitor", "Lcom/intellij/psi/PsiElementVisitor;", "holder", "Lcom/intellij/codeInspection/ProblemsHolder;", "isOnTheFly", "", "checkModelBlock", "", "block", "Lcom/jakubjirak/structurizr/language/psi/StructurizrModelBlock;", "checkViewsBlock", "Lcom/jakubjirak/structurizr/language/psi/StructurizrViewsBlock;", "checkStylesBlock", "Lcom/jakubjirak/structurizr/language/psi/StructurizrStylesBlock;", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrInspections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrInspections.kt\ncom/jakubjirak/structurizr/inspections/EmptyBlockInspection\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,501:1\n12744#2,2:502\n*S KotlinDebug\n*F\n+ 1 StructurizrInspections.kt\ncom/jakubjirak/structurizr/inspections/EmptyBlockInspection\n*L\n254#1:502,2\n*E\n"})
public final class EmptyBlockInspection
extends StructurizrInspectionBase {
    @NotNull
    public String getDisplayName() {
        return "Empty block";
    }

    @NotNull
    public String getShortName() {
        return "StructurizrEmptyBlock";
    }

    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        Intrinsics.checkNotNullParameter((Object)holder, (String)"holder");
        return new PsiElementVisitor(){
            final /* synthetic */ EmptyBlockInspection this$0 = EmptyBlockInspection.this;
            final /* synthetic */ ProblemsHolder $holder = holder;

            public void visitElement(PsiElement element) {
                Intrinsics.checkNotNullParameter((Object)element, (String)"element");
                PsiElement psiElement = element;
                if (psiElement instanceof StructurizrModelBlock) {
                    EmptyBlockInspection.access$checkModelBlock(this.this$0, (StructurizrModelBlock)element, this.$holder);
                } else if (psiElement instanceof StructurizrViewsBlock) {
                    EmptyBlockInspection.access$checkViewsBlock(this.this$0, (StructurizrViewsBlock)element, this.$holder);
                } else if (psiElement instanceof StructurizrStylesBlock) {
                    EmptyBlockInspection.access$checkStylesBlock(this.this$0, (StructurizrStylesBlock)element, this.$holder);
                }
            }
        };
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final void checkModelBlock(StructurizrModelBlock block, ProblemsHolder holder) {
        List<StructurizrPersonDefinition> list = block.getPersonDefinitionList();
        Intrinsics.checkNotNullExpressionValue(list, (String)"getPersonDefinitionList(...)");
        if (!((Collection)list).isEmpty()) {
            return;
        }
        boolean bl = false;
        if (bl) return;
        List<StructurizrSoftwareSystemDefinition> list2 = block.getSoftwareSystemDefinitionList();
        Intrinsics.checkNotNullExpressionValue(list2, (String)"getSoftwareSystemDefinitionList(...)");
        if (!((Collection)list2).isEmpty()) {
            return;
        }
        boolean bl2 = false;
        if (bl2) return;
        List<StructurizrDeploymentEnvironmentBlock> list3 = block.getDeploymentEnvironmentBlockList();
        Intrinsics.checkNotNullExpressionValue(list3, (String)"getDeploymentEnvironmentBlockList(...)");
        if (!((Collection)list3).isEmpty()) {
            return;
        }
        boolean bl3 = false;
        if (bl3) return;
        List<StructurizrGroupBlock> list4 = block.getGroupBlockList();
        Intrinsics.checkNotNullExpressionValue(list4, (String)"getGroupBlockList(...)");
        if (!((Collection)list4).isEmpty()) {
            return;
        }
        boolean bl4 = false;
        if (bl4) return;
        List<StructurizrRelationshipStatement> list5 = block.getRelationshipStatementList();
        Intrinsics.checkNotNullExpressionValue(list5, (String)"getRelationshipStatementList(...)");
        if (!((Collection)list5).isEmpty()) {
            return;
        }
        boolean bl5 = false;
        if (bl5) return;
        List<StructurizrRefStatement> list6 = block.getRefStatementList();
        Intrinsics.checkNotNullExpressionValue(list6, (String)"getRefStatementList(...)");
        if (!((Collection)list6).isEmpty()) {
            return;
        }
        boolean bl6 = false;
        if (bl6) {
            return;
        }
        boolean bl7 = false;
        boolean hasContent = bl7;
        if (hasContent) return;
        PsiElement psiElement = block.getFirstChild();
        if (psiElement == null) {
            psiElement = block;
        }
        holder.registerProblem(psiElement, "Model block is empty", ProblemHighlightType.WEAK_WARNING, new LocalQuickFix[0]);
    }

    private final void checkViewsBlock(StructurizrViewsBlock block, ProblemsHolder holder) {
        boolean hasContent = false;
        block3: {
            boolean bl;
            PsiElement[] psiElementArray = block.getChildren();
            Intrinsics.checkNotNullExpressionValue((Object)psiElementArray, (String)"getChildren(...)");
            Object[] $this$any$iv = psiElementArray;
            boolean $i$f$any = false;
            for (Object element$iv : $this$any$iv) {
                Object[] objectArray;
                PsiElement child = (PsiElement)element$iv;
                boolean bl2 = false;
                if (!(!(child instanceof PsiWhiteSpace) && !(child instanceof PsiComment) && !SetsKt.setOf((Object[])(objectArray = new String[]{"{", "}", "views"})).contains(child.getText()))) continue;
                hasContent = true;
                break block3;
            }
        }
        if (!hasContent) {
            PsiElement psiElement = block.getFirstChild();
            if (psiElement == null) {
                psiElement = block;
            }
            holder.registerProblem(psiElement, "Views block is empty", ProblemHighlightType.WEAK_WARNING, new LocalQuickFix[0]);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final void checkStylesBlock(StructurizrStylesBlock block, ProblemsHolder holder) {
        List<StructurizrElementStyle> list = block.getElementStyleList();
        Intrinsics.checkNotNullExpressionValue(list, (String)"getElementStyleList(...)");
        if (!((Collection)list).isEmpty()) {
            return;
        }
        boolean bl = false;
        if (bl) return;
        List<StructurizrRelationshipStyle> list2 = block.getRelationshipStyleList();
        Intrinsics.checkNotNullExpressionValue(list2, (String)"getRelationshipStyleList(...)");
        if (!((Collection)list2).isEmpty()) {
            return;
        }
        boolean bl2 = false;
        if (bl2) {
            return;
        }
        boolean bl3 = false;
        boolean hasContent = bl3;
        if (hasContent) return;
        PsiElement psiElement = block.getFirstChild();
        if (psiElement == null) {
            psiElement = block;
        }
        holder.registerProblem(psiElement, "Styles block is empty", ProblemHighlightType.WEAK_WARNING, new LocalQuickFix[0]);
    }

    public static final /* synthetic */ void access$checkModelBlock(EmptyBlockInspection $this, StructurizrModelBlock block, ProblemsHolder holder) {
        $this.checkModelBlock(block, holder);
    }

    public static final /* synthetic */ void access$checkViewsBlock(EmptyBlockInspection $this, StructurizrViewsBlock block, ProblemsHolder holder) {
        $this.checkViewsBlock(block, holder);
    }

    public static final /* synthetic */ void access$checkStylesBlock(EmptyBlockInspection $this, StructurizrStylesBlock block, ProblemsHolder holder) {
        $this.checkStylesBlock(block, holder);
    }
}
