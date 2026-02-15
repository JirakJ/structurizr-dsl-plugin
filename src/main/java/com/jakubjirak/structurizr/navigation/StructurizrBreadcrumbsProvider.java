package com.jakubjirak.structurizr.navigation;

import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider;
import com.jakubjirak.structurizr.language.StructurizrLanguage;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import java.util.Set;
import java.util.HashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class StructurizrBreadcrumbsProvider
implements BreadcrumbsProvider {

    private static final Set<IElementType> ACCEPTED_TYPES = new HashSet<>();
    static {
        ACCEPTED_TYPES.add(StructurizrTypes.WORKSPACE_BLOCK);
        ACCEPTED_TYPES.add(StructurizrTypes.MODEL_BLOCK);
        ACCEPTED_TYPES.add(StructurizrTypes.VIEWS_BLOCK);
        ACCEPTED_TYPES.add(StructurizrTypes.STYLES_BLOCK);
        ACCEPTED_TYPES.add(StructurizrTypes.CONFIGURATION_BLOCK);
        ACCEPTED_TYPES.add(StructurizrTypes.PERSON_DEFINITION);
        ACCEPTED_TYPES.add(StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION);
        ACCEPTED_TYPES.add(StructurizrTypes.CONTAINER_DEFINITION);
        ACCEPTED_TYPES.add(StructurizrTypes.COMPONENT_DEFINITION);
        ACCEPTED_TYPES.add(StructurizrTypes.GROUP_BLOCK);
        ACCEPTED_TYPES.add(StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK);
        ACCEPTED_TYPES.add(StructurizrTypes.DEPLOYMENT_NODE_DEFINITION);
        ACCEPTED_TYPES.add(StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION);
        ACCEPTED_TYPES.add(StructurizrTypes.ENTERPRISE_BLOCK);
        ACCEPTED_TYPES.add(StructurizrTypes.ELEMENT_ASSIGNMENT);
        ACCEPTED_TYPES.add(StructurizrTypes.SYSTEM_LANDSCAPE_VIEW);
        ACCEPTED_TYPES.add(StructurizrTypes.SYSTEM_CONTEXT_VIEW);
        ACCEPTED_TYPES.add(StructurizrTypes.CONTAINER_VIEW);
        ACCEPTED_TYPES.add(StructurizrTypes.COMPONENT_VIEW);
        ACCEPTED_TYPES.add(StructurizrTypes.DEPLOYMENT_VIEW);
        ACCEPTED_TYPES.add(StructurizrTypes.DYNAMIC_VIEW);
        ACCEPTED_TYPES.add(StructurizrTypes.CUSTOM_VIEW);
        ACCEPTED_TYPES.add(StructurizrTypes.FILTERED_VIEW);
        ACCEPTED_TYPES.add(StructurizrTypes.IMAGE_VIEW);
    }

    @NotNull
    public Language[] getLanguages() {
        return new Language[]{StructurizrLanguage.INSTANCE};
    }

    public boolean acceptElement(@NotNull PsiElement element) {
        IElementType type = element.getNode().getElementType();
        return ACCEPTED_TYPES.contains(type);
    }

    @NotNull
    public String getElementInfo(@NotNull PsiElement element) {
        IElementType type = element.getNode().getElementType();
        String typeName = getTypeName(type);
        String name = extractName(element, type);
        if (name != null) {
            return typeName + ": " + name;
        }
        return typeName;
    }

    @Nullable
    public String getElementTooltip(@NotNull PsiElement element) {
        IElementType type = element.getNode().getElementType();
        return "Structurizr " + getTypeName(type);
    }

    private static String getTypeName(IElementType type) {
        if (type == StructurizrTypes.WORKSPACE_BLOCK) return "workspace";
        if (type == StructurizrTypes.MODEL_BLOCK) return "model";
        if (type == StructurizrTypes.VIEWS_BLOCK) return "views";
        if (type == StructurizrTypes.STYLES_BLOCK) return "styles";
        if (type == StructurizrTypes.CONFIGURATION_BLOCK) return "configuration";
        if (type == StructurizrTypes.PERSON_DEFINITION) return "person";
        if (type == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION) return "softwareSystem";
        if (type == StructurizrTypes.CONTAINER_DEFINITION) return "container";
        if (type == StructurizrTypes.COMPONENT_DEFINITION) return "component";
        if (type == StructurizrTypes.GROUP_BLOCK) return "group";
        if (type == StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK) return "deploymentEnvironment";
        if (type == StructurizrTypes.DEPLOYMENT_NODE_DEFINITION) return "deploymentNode";
        if (type == StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION) return "infrastructureNode";
        if (type == StructurizrTypes.ENTERPRISE_BLOCK) return "enterprise";
        if (type == StructurizrTypes.ELEMENT_ASSIGNMENT) return "element";
        if (type == StructurizrTypes.SYSTEM_LANDSCAPE_VIEW) return "systemLandscape";
        if (type == StructurizrTypes.SYSTEM_CONTEXT_VIEW) return "systemContext";
        if (type == StructurizrTypes.CONTAINER_VIEW) return "container view";
        if (type == StructurizrTypes.COMPONENT_VIEW) return "component view";
        if (type == StructurizrTypes.DEPLOYMENT_VIEW) return "deployment view";
        if (type == StructurizrTypes.DYNAMIC_VIEW) return "dynamic view";
        if (type == StructurizrTypes.CUSTOM_VIEW) return "custom view";
        if (type == StructurizrTypes.FILTERED_VIEW) return "filtered view";
        if (type == StructurizrTypes.IMAGE_VIEW) return "image view";
        return type.toString();
    }

    private static String extractName(PsiElement element, IElementType type) {
        if (type == StructurizrTypes.ELEMENT_ASSIGNMENT) {
            com.intellij.lang.ASTNode idNode = element.getNode().findChildByType(StructurizrTypes.IDENTIFIER);
            if (idNode != null) return idNode.getText();
        }
        com.intellij.lang.ASTNode stringNode = element.getNode().findChildByType(StructurizrTypes.STRING);
        if (stringNode != null) {
            String text = stringNode.getText();
            if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
                return text.substring(1, text.length() - 1);
            }
            return text;
        }
        return null;
    }
}
