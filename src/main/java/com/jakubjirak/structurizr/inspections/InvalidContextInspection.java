package com.jakubjirak.structurizr.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.psi.StructurizrContainerDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrComponentDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemInstanceDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrContainerInstanceDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrInfrastructureNodeDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentNodeDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import org.jetbrains.annotations.NotNull;

public final class InvalidContextInspection extends StructurizrInspectionBase {

    @NotNull
    public String getDisplayName() {
        return "Invalid element context";
    }

    @NotNull
    public String getShortName() {
        return "StructurizrInvalidContext";
    }

    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new PsiElementVisitor() {
            public void visitElement(@NotNull PsiElement element) {
                if (element instanceof StructurizrContainerDefinition) {
                    checkContainerContext(element, holder);
                } else if (element instanceof StructurizrComponentDefinition) {
                    checkComponentContext(element, holder);
                } else if (element instanceof StructurizrSoftwareSystemInstanceDefinition) {
                    checkInstanceContext(element, "softwareSystemInstance", holder);
                } else if (element instanceof StructurizrContainerInstanceDefinition) {
                    checkInstanceContext(element, "containerInstance", holder);
                } else if (element instanceof StructurizrInfrastructureNodeDefinition) {
                    checkInfrastructureNodeContext(element, holder);
                } else if (element instanceof StructurizrDeploymentNodeDefinition) {
                    checkDeploymentNodeContext(element, holder);
                }
            }
        };
    }

    private static void checkContainerContext(PsiElement element, ProblemsHolder holder) {
        PsiElement parent = findMeaningfulParent(element);
        if (parent == null) {
            return;
        }
        IElementType parentType = parent.getNode().getElementType();
        if (parentType == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION ||
            parentType == StructurizrTypes.GROUP_BLOCK ||
            parentType == StructurizrTypes.ELEMENT_ASSIGNMENT) {
            // Check if group is inside softwareSystem
            if (parentType == StructurizrTypes.GROUP_BLOCK) {
                if (!isAncestorOfType(parent, StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION)) {
                    holder.registerProblem(element.getFirstChild() != null ? element.getFirstChild() : element,
                        "container can only be defined inside softwareSystem",
                        ProblemHighlightType.ERROR, LocalQuickFix.EMPTY_ARRAY);
                }
            }
            return;
        }
        holder.registerProblem(element.getFirstChild() != null ? element.getFirstChild() : element,
            "container can only be defined inside softwareSystem",
            ProblemHighlightType.ERROR, LocalQuickFix.EMPTY_ARRAY);
    }

    private static void checkComponentContext(PsiElement element, ProblemsHolder holder) {
        PsiElement parent = findMeaningfulParent(element);
        if (parent == null) {
            return;
        }
        IElementType parentType = parent.getNode().getElementType();
        if (parentType == StructurizrTypes.CONTAINER_DEFINITION ||
            parentType == StructurizrTypes.GROUP_BLOCK ||
            parentType == StructurizrTypes.ELEMENT_ASSIGNMENT) {
            if (parentType == StructurizrTypes.GROUP_BLOCK) {
                if (!isAncestorOfType(parent, StructurizrTypes.CONTAINER_DEFINITION)) {
                    holder.registerProblem(element.getFirstChild() != null ? element.getFirstChild() : element,
                        "component can only be defined inside container",
                        ProblemHighlightType.ERROR, LocalQuickFix.EMPTY_ARRAY);
                }
            }
            return;
        }
        holder.registerProblem(element.getFirstChild() != null ? element.getFirstChild() : element,
            "component can only be defined inside container",
            ProblemHighlightType.ERROR, LocalQuickFix.EMPTY_ARRAY);
    }

    private static void checkInstanceContext(PsiElement element, String keyword, ProblemsHolder holder) {
        if (!isAncestorOfType(element, StructurizrTypes.DEPLOYMENT_NODE_DEFINITION)) {
            holder.registerProblem(element.getFirstChild() != null ? element.getFirstChild() : element,
                keyword + " can only be defined inside deploymentNode",
                ProblemHighlightType.ERROR, LocalQuickFix.EMPTY_ARRAY);
        }
    }

    private static void checkInfrastructureNodeContext(PsiElement element, ProblemsHolder holder) {
        if (!isAncestorOfType(element, StructurizrTypes.DEPLOYMENT_NODE_DEFINITION)) {
            holder.registerProblem(element.getFirstChild() != null ? element.getFirstChild() : element,
                "infrastructureNode can only be defined inside deploymentNode",
                ProblemHighlightType.ERROR, LocalQuickFix.EMPTY_ARRAY);
        }
    }

    private static void checkDeploymentNodeContext(PsiElement element, ProblemsHolder holder) {
        if (isAncestorOfType(element, StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK) ||
            isAncestorOfType(element, StructurizrTypes.DEPLOYMENT_NODE_DEFINITION)) {
            return;
        }
        holder.registerProblem(element.getFirstChild() != null ? element.getFirstChild() : element,
            "deploymentNode can only be defined inside deploymentEnvironment or another deploymentNode",
            ProblemHighlightType.ERROR, LocalQuickFix.EMPTY_ARRAY);
    }

    private static PsiElement findMeaningfulParent(PsiElement element) {
        PsiElement parent = element.getParent();
        while (parent != null) {
            IElementType type = parent.getNode().getElementType();
            if (type == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION ||
                type == StructurizrTypes.CONTAINER_DEFINITION ||
                type == StructurizrTypes.COMPONENT_DEFINITION ||
                type == StructurizrTypes.DEPLOYMENT_NODE_DEFINITION ||
                type == StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK ||
                type == StructurizrTypes.GROUP_BLOCK ||
                type == StructurizrTypes.MODEL_BLOCK ||
                type == StructurizrTypes.WORKSPACE_BLOCK ||
                type == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                return parent;
            }
            parent = parent.getParent();
        }
        return null;
    }

    private static boolean isAncestorOfType(PsiElement element, IElementType type) {
        PsiElement parent = element.getParent();
        while (parent != null) {
            if (parent.getNode() != null && parent.getNode().getElementType() == type) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }
}
