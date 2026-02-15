/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInspection.LocalQuickFix
 *  com.intellij.codeInspection.ProblemDescriptor
 *  com.intellij.openapi.project.Project
 *  com.intellij.psi.PsiElement
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrComponentDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrContainerDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentNodeDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrInfrastructureNodeDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrPersonDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2={"Lcom/jakubjirak/structurizr/inspections/RemoveUnusedElementQuickFix;", "Lcom/intellij/codeInspection/LocalQuickFix;", "elementName", "", "<init>", "(Ljava/lang/String;)V", "getFamilyName", "getName", "applyFix", "", "project", "Lcom/intellij/openapi/project/Project;", "descriptor", "Lcom/intellij/codeInspection/ProblemDescriptor;", "findParentDefinition", "Lcom/intellij/psi/PsiElement;", "element", "structurizr-dsl-plugin"})
public final class RemoveUnusedElementQuickFix
implements LocalQuickFix {
    @NotNull
    private final String elementName;

    public RemoveUnusedElementQuickFix(@NotNull String elementName) {
        Intrinsics.checkNotNullParameter((Object)elementName, (String)"elementName");
        this.elementName = elementName;
    }

    @NotNull
    public String getFamilyName() {
        return "Remove unused element";
    }

    @NotNull
    public String getName() {
        return "Remove unused element '" + this.elementName + "'";
    }

    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        block0: {
            PsiElement parent;
            Intrinsics.checkNotNullParameter((Object)project, (String)"project");
            Intrinsics.checkNotNullParameter((Object)descriptor, (String)"descriptor");
            PsiElement element = descriptor.getPsiElement();
            Intrinsics.checkNotNull((Object)element);
            PsiElement psiElement = parent = this.findParentDefinition(element);
            if (psiElement == null) break block0;
            psiElement.delete();
        }
    }

    private final PsiElement findParentDefinition(PsiElement element) {
        for (PsiElement current = element; current != null; current = current.getParent()) {
            if (!(current instanceof StructurizrPersonDefinition) && !(current instanceof StructurizrSoftwareSystemDefinition) && !(current instanceof StructurizrContainerDefinition) && !(current instanceof StructurizrComponentDefinition) && !(current instanceof StructurizrDeploymentNodeDefinition) && !(current instanceof StructurizrInfrastructureNodeDefinition)) continue;
            return current;
        }
        return null;
    }
}
