/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInspection.LocalQuickFix
 *  com.intellij.codeInspection.ProblemDescriptor
 *  com.intellij.openapi.fileTypes.FileType
 *  com.intellij.openapi.project.Project
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.PsiFile
 *  com.intellij.psi.PsiFileFactory
 *  com.intellij.psi.util.PsiTreeUtil
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.jakubjirak.structurizr.language.StructurizrFileType;
import com.jakubjirak.structurizr.language.psi.StructurizrModelBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2={"Lcom/jakubjirak/structurizr/inspections/CreateElementQuickFix;", "Lcom/intellij/codeInspection/LocalQuickFix;", "elementName", "", "<init>", "(Ljava/lang/String;)V", "getFamilyName", "getName", "applyFix", "", "project", "Lcom/intellij/openapi/project/Project;", "descriptor", "Lcom/intellij/codeInspection/ProblemDescriptor;", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrInspections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrInspections.kt\ncom/jakubjirak/structurizr/inspections/CreateElementQuickFix\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,501:1\n2298#2,5:502\n*S KotlinDebug\n*F\n+ 1 StructurizrInspections.kt\ncom/jakubjirak/structurizr/inspections/CreateElementQuickFix\n*L\n435#1:502,5\n*E\n"})
public final class CreateElementQuickFix
implements LocalQuickFix {
    @NotNull
    private final String elementName;

    public CreateElementQuickFix(@NotNull String elementName) {
        Intrinsics.checkNotNullParameter((Object)elementName, (String)"elementName");
        this.elementName = elementName;
    }

    @NotNull
    public String getFamilyName() {
        return "Create element";
    }

    @NotNull
    public String getName() {
        return "Create element '" + this.elementName + "'";
    }

    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)descriptor, (String)"descriptor");
        PsiFile file = descriptor.getPsiElement().getContainingFile();
        StructurizrModelBlock structurizrModelBlock = (StructurizrModelBlock)PsiTreeUtil.findChildOfType((PsiElement)((PsiElement)file), StructurizrModelBlock.class);
        if (structurizrModelBlock == null) {
            return;
        }
        StructurizrModelBlock modelBlock = structurizrModelBlock;
        String dslText = "    " + this.elementName + " = softwareSystem \"" + this.elementName + "\"\n";
        PsiFile psiFile = PsiFileFactory.getInstance((Project)project).createFileFromText("temp.dsl", (FileType)StructurizrFileType.INSTANCE, (CharSequence)("workspace {\n    model {\n" + dslText + "    }\n}"));
        Intrinsics.checkNotNullExpressionValue((Object)psiFile, (String)"createFileFromText(...)");
        PsiFile tempFile = psiFile;
        StructurizrSoftwareSystemDefinition newElement = (StructurizrSoftwareSystemDefinition)PsiTreeUtil.findChildOfType((PsiElement)((PsiElement)tempFile), StructurizrSoftwareSystemDefinition.class);
        if (newElement != null) {
            Object object;
            block4: {
                PsiElement[] psiElementArray = modelBlock.getChildren();
                Intrinsics.checkNotNullExpressionValue((Object)psiElementArray, (String)"getChildren(...)");
                Object[] $this$lastOrNull$iv = psiElementArray;
                boolean $i$f$lastOrNull = false;
                int n = $this$lastOrNull$iv.length + -1;
                if (0 <= n) {
                    do {
                        int index$iv = n--;
                        Object element$iv = $this$lastOrNull$iv[index$iv];
                        PsiElement it = (PsiElement)element$iv;
                        boolean bl = false;
                        if (!Intrinsics.areEqual((Object)it.getText(), (Object)"}")) continue;
                        object = element$iv;
                        break block4;
                    } while (0 <= n);
                }
                object = null;
            }
            PsiElement closingBrace = (PsiElement)object;
            PsiElement psiElement = closingBrace != null ? modelBlock.addBefore(newElement.copy(), closingBrace) : modelBlock.add(newElement.copy());
        }
    }
}
