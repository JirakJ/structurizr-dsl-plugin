package com.jakubjirak.structurizr.navigation;

import com.intellij.icons.AllIcons;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.StructurizrIcons;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import javax.swing.Icon;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import org.jetbrains.annotations.NotNull;

public final class StructurizrStructureViewElement
implements StructureViewTreeElement {
    @NotNull
    private final PsiElement element;

    private static final Set<IElementType> STRUCTURE_TYPES = new HashSet<>();
    static {
        STRUCTURE_TYPES.add(StructurizrTypes.WORKSPACE_BLOCK);
        STRUCTURE_TYPES.add(StructurizrTypes.MODEL_BLOCK);
        STRUCTURE_TYPES.add(StructurizrTypes.VIEWS_BLOCK);
        STRUCTURE_TYPES.add(StructurizrTypes.STYLES_BLOCK);
        STRUCTURE_TYPES.add(StructurizrTypes.CONFIGURATION_BLOCK);
        STRUCTURE_TYPES.add(StructurizrTypes.PERSON_DEFINITION);
        STRUCTURE_TYPES.add(StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION);
        STRUCTURE_TYPES.add(StructurizrTypes.CONTAINER_DEFINITION);
        STRUCTURE_TYPES.add(StructurizrTypes.COMPONENT_DEFINITION);
        STRUCTURE_TYPES.add(StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK);
        STRUCTURE_TYPES.add(StructurizrTypes.DEPLOYMENT_NODE_DEFINITION);
        STRUCTURE_TYPES.add(StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION);
        STRUCTURE_TYPES.add(StructurizrTypes.GROUP_BLOCK);
        STRUCTURE_TYPES.add(StructurizrTypes.ELEMENT_ASSIGNMENT);
        STRUCTURE_TYPES.add(StructurizrTypes.ENTERPRISE_BLOCK);
        STRUCTURE_TYPES.add(StructurizrTypes.SYSTEM_LANDSCAPE_VIEW);
        STRUCTURE_TYPES.add(StructurizrTypes.SYSTEM_CONTEXT_VIEW);
        STRUCTURE_TYPES.add(StructurizrTypes.CONTAINER_VIEW);
        STRUCTURE_TYPES.add(StructurizrTypes.COMPONENT_VIEW);
        STRUCTURE_TYPES.add(StructurizrTypes.DEPLOYMENT_VIEW);
        STRUCTURE_TYPES.add(StructurizrTypes.DYNAMIC_VIEW);
        STRUCTURE_TYPES.add(StructurizrTypes.CUSTOM_VIEW);
        STRUCTURE_TYPES.add(StructurizrTypes.FILTERED_VIEW);
        STRUCTURE_TYPES.add(StructurizrTypes.IMAGE_VIEW);
    }

    public StructurizrStructureViewElement(@NotNull PsiElement element) {
        this.element = element;
    }

    @NotNull
    public Object getValue() {
        return this.element;
    }

    public void navigate(boolean requestFocus) {
        if (this.element instanceof Navigatable) {
            ((Navigatable)this.element).navigate(requestFocus);
        }
    }

    public boolean canNavigate() {
        return this.element instanceof Navigatable && ((Navigatable)this.element).canNavigate();
    }

    public boolean canNavigateToSource() {
        return this.element instanceof Navigatable && ((Navigatable)this.element).canNavigateToSource();
    }

    @NotNull
    public ItemPresentation getPresentation() {
        final StructurizrStructureViewElement self = this;
        return new ItemPresentation(){
            public String getPresentableText() {
                PsiElement el = self.element;
                if (el instanceof PsiFile) {
                    return ((PsiFile)el).getName();
                }
                IElementType type = el.getNode().getElementType();
                // For ELEMENT_ASSIGNMENT, show "identifier = type"
                if (type == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                    String name = extractIdentifierFromAssignment(el);
                    String defType = extractDefinitionType(el);
                    if (name != null) {
                        return name + (defType != null ? " (" + defType + ")" : "");
                    }
                }
                // For definitions, show the name from STRING child
                String nameFromString = extractFirstString(el);
                String typeName = getElementTypeName(type);
                if (nameFromString != null) {
                    return typeName + ": " + nameFromString;
                }
                return typeName;
            }

            public String getLocationString() {
                return null;
            }

            public Icon getIcon(boolean unused) {
                PsiElement el = self.element;
                if (el instanceof PsiFile) {
                    return StructurizrIcons.FILE;
                }
                IElementType type = el.getNode().getElementType();
                if (type == StructurizrTypes.WORKSPACE_BLOCK) return StructurizrIcons.WORKSPACE;
                if (type == StructurizrTypes.MODEL_BLOCK) return StructurizrIcons.MODEL;
                if (type == StructurizrTypes.VIEWS_BLOCK) return StructurizrIcons.VIEWS;
                if (type == StructurizrTypes.STYLES_BLOCK) return StructurizrIcons.STYLES;
                if (type == StructurizrTypes.CONFIGURATION_BLOCK) return AllIcons.General.Settings;
                if (type == StructurizrTypes.PERSON_DEFINITION) return StructurizrIcons.PERSON;
                if (type == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION) return StructurizrIcons.SOFTWARE_SYSTEM;
                if (type == StructurizrTypes.CONTAINER_DEFINITION) return StructurizrIcons.CONTAINER;
                if (type == StructurizrTypes.COMPONENT_DEFINITION) return StructurizrIcons.COMPONENT;
                if (type == StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK) return StructurizrIcons.DEPLOYMENT;
                if (type == StructurizrTypes.DEPLOYMENT_NODE_DEFINITION) return StructurizrIcons.DEPLOYMENT;
                if (type == StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION) return StructurizrIcons.DEPLOYMENT;
                if (type == StructurizrTypes.GROUP_BLOCK) return AllIcons.Nodes.Folder;
                if (type == StructurizrTypes.ENTERPRISE_BLOCK) return AllIcons.Nodes.Folder;
                if (type == StructurizrTypes.ELEMENT_ASSIGNMENT) return getIconForAssignment(el);
                // Views
                if (type == StructurizrTypes.SYSTEM_LANDSCAPE_VIEW ||
                    type == StructurizrTypes.SYSTEM_CONTEXT_VIEW ||
                    type == StructurizrTypes.CONTAINER_VIEW ||
                    type == StructurizrTypes.COMPONENT_VIEW ||
                    type == StructurizrTypes.DEPLOYMENT_VIEW ||
                    type == StructurizrTypes.DYNAMIC_VIEW ||
                    type == StructurizrTypes.CUSTOM_VIEW ||
                    type == StructurizrTypes.FILTERED_VIEW ||
                    type == StructurizrTypes.IMAGE_VIEW) return StructurizrIcons.VIEWS;
                return StructurizrIcons.FILE;
            }
        };
    }

    @NotNull
    public TreeElement[] getChildren() {
        List<TreeElement> children = new ArrayList<>();
        PsiElement[] psiChildren = this.element.getChildren();
        for (PsiElement child : psiChildren) {
            IElementType childType = child.getNode().getElementType();
            if (STRUCTURE_TYPES.contains(childType)) {
                children.add(new StructurizrStructureViewElement(child));
            } else {
                // Recurse into non-structure nodes to find nested structure elements
                collectStructureChildren(child, children);
            }
        }
        return children.toArray(new TreeElement[0]);
    }

    private void collectStructureChildren(PsiElement parent, List<TreeElement> result) {
        for (PsiElement child : parent.getChildren()) {
            IElementType childType = child.getNode().getElementType();
            if (STRUCTURE_TYPES.contains(childType)) {
                result.add(new StructurizrStructureViewElement(child));
            }
        }
    }

    private static String extractIdentifierFromAssignment(PsiElement assignment) {
        for (PsiElement child : assignment.getChildren()) {
            if (child.getNode().getElementType() == StructurizrTypes.IDENTIFIER) {
                return child.getText();
            }
        }
        // Check leaf nodes
        com.intellij.lang.ASTNode node = assignment.getNode().findChildByType(StructurizrTypes.IDENTIFIER);
        if (node != null) return node.getText();
        return null;
    }

    private static String extractDefinitionType(PsiElement assignment) {
        for (PsiElement child : assignment.getChildren()) {
            IElementType t = child.getNode().getElementType();
            if (t == StructurizrTypes.PERSON_DEFINITION) return "person";
            if (t == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION) return "softwareSystem";
            if (t == StructurizrTypes.CONTAINER_DEFINITION) return "container";
            if (t == StructurizrTypes.COMPONENT_DEFINITION) return "component";
            if (t == StructurizrTypes.DEPLOYMENT_NODE_DEFINITION) return "deploymentNode";
            if (t == StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION) return "infrastructureNode";
        }
        return null;
    }

    private static String extractFirstString(PsiElement element) {
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

    private static String getElementTypeName(IElementType type) {
        if (type == StructurizrTypes.WORKSPACE_BLOCK) return "workspace";
        if (type == StructurizrTypes.MODEL_BLOCK) return "model";
        if (type == StructurizrTypes.VIEWS_BLOCK) return "views";
        if (type == StructurizrTypes.STYLES_BLOCK) return "styles";
        if (type == StructurizrTypes.CONFIGURATION_BLOCK) return "configuration";
        if (type == StructurizrTypes.PERSON_DEFINITION) return "person";
        if (type == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION) return "softwareSystem";
        if (type == StructurizrTypes.CONTAINER_DEFINITION) return "container";
        if (type == StructurizrTypes.COMPONENT_DEFINITION) return "component";
        if (type == StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK) return "deploymentEnvironment";
        if (type == StructurizrTypes.DEPLOYMENT_NODE_DEFINITION) return "deploymentNode";
        if (type == StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION) return "infrastructureNode";
        if (type == StructurizrTypes.GROUP_BLOCK) return "group";
        if (type == StructurizrTypes.ENTERPRISE_BLOCK) return "enterprise";
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

    private static Icon getIconForAssignment(PsiElement assignment) {
        for (PsiElement child : assignment.getChildren()) {
            IElementType t = child.getNode().getElementType();
            if (t == StructurizrTypes.PERSON_DEFINITION) return StructurizrIcons.PERSON;
            if (t == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION) return StructurizrIcons.SOFTWARE_SYSTEM;
            if (t == StructurizrTypes.CONTAINER_DEFINITION) return StructurizrIcons.CONTAINER;
            if (t == StructurizrTypes.COMPONENT_DEFINITION) return StructurizrIcons.COMPONENT;
            if (t == StructurizrTypes.DEPLOYMENT_NODE_DEFINITION) return StructurizrIcons.DEPLOYMENT;
            if (t == StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION) return StructurizrIcons.DEPLOYMENT;
        }
        return StructurizrIcons.FILE;
    }
}
