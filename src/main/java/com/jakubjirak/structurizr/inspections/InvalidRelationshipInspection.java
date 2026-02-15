package com.jakubjirak.structurizr.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public final class InvalidRelationshipInspection extends StructurizrInspectionBase {

    @NotNull
    public String getDisplayName() {
        return "Invalid relationship";
    }

    @NotNull
    public String getShortName() {
        return "StructurizrInvalidRelationship";
    }

    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new PsiElementVisitor() {
            public void visitElement(@NotNull PsiElement element) {
                if (!(element instanceof StructurizrRelationshipStatement)) {
                    return;
                }
                StructurizrRelationshipStatement rel = (StructurizrRelationshipStatement) element;
                List<StructurizrElementReference> refs = rel.getElementReferenceList();
                if (refs == null || refs.size() < 2) {
                    return;
                }

                PsiFile file = element.getContainingFile();
                if (file == null) {
                    return;
                }
                Set<String> definedElements = collectDefinedElements(file);

                StructurizrElementReference sourceRef = refs.get(0);
                StructurizrElementReference destRef = refs.get(1);
                String sourceText = sourceRef.getText();
                String destText = destRef.getText();

                if (sourceText == null || destText == null) {
                    return;
                }

                // Check 'this' used outside element scope
                if ("this".equals(sourceText) || "this".equals(destText)) {
                    if (!isInsideElementScope(element)) {
                        PsiElement target = "this".equals(sourceText) ? sourceRef : destRef;
                        holder.registerProblem(target,
                            "'this' can only be used inside an element definition",
                            ProblemHighlightType.ERROR, LocalQuickFix.EMPTY_ARRAY);
                        return;
                    }
                }

                // Check self-referencing relationship
                if (sourceText.equals(destText)) {
                    holder.registerProblem((PsiElement) rel,
                        "Self-referencing relationship: source and destination are the same element '" + sourceText + "'",
                        ProblemHighlightType.WARNING, LocalQuickFix.EMPTY_ARRAY);
                    return;
                }

                // Check that source and destination resolve to defined elements
                String sourceRoot = getRootIdentifier(sourceText);
                String destRoot = getRootIdentifier(destText);

                if (!isBuiltIn(sourceRoot) && !definedElements.contains(sourceRoot)) {
                    holder.registerProblem(sourceRef,
                        "Undefined relationship source: '" + sourceRoot + "'",
                        ProblemHighlightType.ERROR, LocalQuickFix.EMPTY_ARRAY);
                }
                if (!isBuiltIn(destRoot) && !definedElements.contains(destRoot)) {
                    holder.registerProblem(destRef,
                        "Undefined relationship destination: '" + destRoot + "'",
                        ProblemHighlightType.ERROR, LocalQuickFix.EMPTY_ARRAY);
                }
            }
        };
    }

    private static boolean isInsideElementScope(PsiElement element) {
        PsiElement parent = element.getParent();
        while (parent != null) {
            if (parent.getNode() != null) {
                IElementType type = parent.getNode().getElementType();
                if (type == StructurizrTypes.PERSON_DEFINITION ||
                    type == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION ||
                    type == StructurizrTypes.CONTAINER_DEFINITION ||
                    type == StructurizrTypes.COMPONENT_DEFINITION ||
                    type == StructurizrTypes.DEPLOYMENT_NODE_DEFINITION ||
                    type == StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION ||
                    type == StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE_DEFINITION ||
                    type == StructurizrTypes.CONTAINER_INSTANCE_DEFINITION) {
                    return true;
                }
            }
            parent = parent.getParent();
        }
        return false;
    }

    private static String getRootIdentifier(String text) {
        if (text == null) return "";
        int dotIndex = text.indexOf('.');
        return dotIndex >= 0 ? text.substring(0, dotIndex) : text;
    }

    private static boolean isBuiltIn(String text) {
        return "this".equals(text) || "source".equals(text) || "destination".equals(text);
    }
}
