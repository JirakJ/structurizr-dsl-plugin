package com.jakubjirak.structurizr.navigation;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.StructurizrIcons;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import java.util.Collection;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;

public final class StructurizrLineMarkerProvider
extends RelatedItemLineMarkerProvider {
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        IElementType elementType = element.getNode().getElementType();

        // Only mark keyword tokens (leaf elements) that are the first keyword of a definition
        PsiElement parent = element.getParent();
        if (parent == null) return;
        IElementType parentType = parent.getNode().getElementType();

        if (elementType == StructurizrTypes.PERSON && parentType == StructurizrTypes.PERSON_DEFINITION) {
            addMarker(element, StructurizrIcons.PERSON, "Person definition", result);
        } else if (elementType == StructurizrTypes.SOFTWARE_SYSTEM && parentType == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION) {
            addMarker(element, StructurizrIcons.SOFTWARE_SYSTEM, "Software System definition", result);
        } else if (elementType == StructurizrTypes.CONTAINER && parentType == StructurizrTypes.CONTAINER_DEFINITION) {
            addMarker(element, StructurizrIcons.CONTAINER, "Container definition", result);
        } else if (elementType == StructurizrTypes.COMPONENT && parentType == StructurizrTypes.COMPONENT_DEFINITION) {
            addMarker(element, StructurizrIcons.COMPONENT, "Component definition", result);
        } else if (elementType == StructurizrTypes.DEPLOYMENT_NODE && parentType == StructurizrTypes.DEPLOYMENT_NODE_DEFINITION) {
            addMarker(element, StructurizrIcons.DEPLOYMENT, "Deployment Node definition", result);
        } else if (elementType == StructurizrTypes.INFRASTRUCTURE_NODE && parentType == StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION) {
            addMarker(element, StructurizrIcons.DEPLOYMENT, "Infrastructure Node definition", result);
        } else if (elementType == StructurizrTypes.ARROW && parentType == StructurizrTypes.RELATIONSHIP_STATEMENT) {
            addMarker(element, StructurizrIcons.RELATIONSHIP, "Relationship", result);
        }
    }

    private static void addMarker(PsiElement element, Icon icon, String tooltip, Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        NavigationGutterIconBuilder<PsiElement> builder =
            NavigationGutterIconBuilder.create(icon)
                .setTargets(element)
                .setTooltipText(tooltip);
        result.add(builder.createLineMarkerInfo(element));
    }
}
