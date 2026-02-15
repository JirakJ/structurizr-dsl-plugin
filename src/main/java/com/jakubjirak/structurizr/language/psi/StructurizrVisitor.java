/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.PsiElementVisitor
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.jakubjirak.structurizr.language.psi.StructurizrAdrsDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrAnimationBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrAnimationStep;
import com.jakubjirak.structurizr.language.psi.StructurizrAutoLayoutStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrBrandingBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrComponentDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrComponentView;
import com.jakubjirak.structurizr.language.psi.StructurizrConfigurationBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrConstantDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrContainerDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrContainerInstanceDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrContainerView;
import com.jakubjirak.structurizr.language.psi.StructurizrCustomView;
import com.jakubjirak.structurizr.language.psi.StructurizrDefaultStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentEnvironmentBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentGroupBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentGroups;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentNodeDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrDeploymentView;
import com.jakubjirak.structurizr.language.psi.StructurizrDescriptionStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrDescriptionString;
import com.jakubjirak.structurizr.language.psi.StructurizrDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrDocsDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrDynamicView;
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment;
import com.jakubjirak.structurizr.language.psi.StructurizrElementReference;
import com.jakubjirak.structurizr.language.psi.StructurizrElementSelector;
import com.jakubjirak.structurizr.language.psi.StructurizrElementStyle;
import com.jakubjirak.structurizr.language.psi.StructurizrEnterpriseBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrExcludeStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrFilePath;
import com.jakubjirak.structurizr.language.psi.StructurizrFilterMode;
import com.jakubjirak.structurizr.language.psi.StructurizrFilterTags;
import com.jakubjirak.structurizr.language.psi.StructurizrFilteredView;
import com.jakubjirak.structurizr.language.psi.StructurizrFontStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrGroupBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrIdentifiersDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrImageKroki;
import com.jakubjirak.structurizr.language.psi.StructurizrImageMermaid;
import com.jakubjirak.structurizr.language.psi.StructurizrImagePlantuml;
import com.jakubjirak.structurizr.language.psi.StructurizrImageView;
import com.jakubjirak.structurizr.language.psi.StructurizrImpliedRelationshipsDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrIncludeDirective;
import com.jakubjirak.structurizr.language.psi.StructurizrIncludeStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrInfrastructureNodeDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrInstancesString;
import com.jakubjirak.structurizr.language.psi.StructurizrLayoutDirection;
import com.jakubjirak.structurizr.language.psi.StructurizrLogoStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrModelBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrNodeSeparation;
import com.jakubjirak.structurizr.language.psi.StructurizrPersonDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrPerspectiveDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrPerspectivesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertiesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrPropertyDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrRankSeparation;
import com.jakubjirak.structurizr.language.psi.StructurizrRefStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipDescription;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipInDynamicView;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStyle;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipTags;
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipTechnology;
import com.jakubjirak.structurizr.language.psi.StructurizrScopeStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrShapeValue;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrSoftwareSystemInstanceDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrStyleProperty;
import com.jakubjirak.structurizr.language.psi.StructurizrStylePropertyName;
import com.jakubjirak.structurizr.language.psi.StructurizrStylePropertyValue;
import com.jakubjirak.structurizr.language.psi.StructurizrStylesBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrSystemContextView;
import com.jakubjirak.structurizr.language.psi.StructurizrSystemLandscapeView;
import com.jakubjirak.structurizr.language.psi.StructurizrTagsStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrTagsString;
import com.jakubjirak.structurizr.language.psi.StructurizrTechnologyString;
import com.jakubjirak.structurizr.language.psi.StructurizrTerminologyBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrTerminologyItem;
import com.jakubjirak.structurizr.language.psi.StructurizrThemesStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrTitleStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrUrlStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrUserDefinition;
import com.jakubjirak.structurizr.language.psi.StructurizrUsersBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrValueLiteral;
import com.jakubjirak.structurizr.language.psi.StructurizrViewDescription;
import com.jakubjirak.structurizr.language.psi.StructurizrViewKey;
import com.jakubjirak.structurizr.language.psi.StructurizrViewsBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrVisibilityStatement;
import com.jakubjirak.structurizr.language.psi.StructurizrWorkspaceBlock;
import com.jakubjirak.structurizr.language.psi.StructurizrWorkspaceDescription;
import com.jakubjirak.structurizr.language.psi.StructurizrWorkspaceName;
import org.jetbrains.annotations.NotNull;

public class StructurizrVisitor
extends PsiElementVisitor {
    public void visitAdrsDirective(@NotNull StructurizrAdrsDirective o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(0);
        }
        this.visitPsiElement(o);
    }

    public void visitAnimationBlock(@NotNull StructurizrAnimationBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(1);
        }
        this.visitPsiElement(o);
    }

    public void visitAnimationStep(@NotNull StructurizrAnimationStep o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(2);
        }
        this.visitPsiElement(o);
    }

    public void visitAutoLayoutStatement(@NotNull StructurizrAutoLayoutStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(3);
        }
        this.visitPsiElement(o);
    }

    public void visitBrandingBlock(@NotNull StructurizrBrandingBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(4);
        }
        this.visitPsiElement(o);
    }

    public void visitComponentDefinition(@NotNull StructurizrComponentDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(5);
        }
        this.visitPsiElement(o);
    }

    public void visitComponentView(@NotNull StructurizrComponentView o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(6);
        }
        this.visitPsiElement(o);
    }

    public void visitConfigurationBlock(@NotNull StructurizrConfigurationBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(7);
        }
        this.visitPsiElement(o);
    }

    public void visitConstantDirective(@NotNull StructurizrConstantDirective o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(8);
        }
        this.visitPsiElement(o);
    }

    public void visitContainerDefinition(@NotNull StructurizrContainerDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(9);
        }
        this.visitPsiElement(o);
    }

    public void visitContainerInstanceDefinition(@NotNull StructurizrContainerInstanceDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(10);
        }
        this.visitPsiElement(o);
    }

    public void visitContainerView(@NotNull StructurizrContainerView o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(11);
        }
        this.visitPsiElement(o);
    }

    public void visitCustomView(@NotNull StructurizrCustomView o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(12);
        }
        this.visitPsiElement(o);
    }

    public void visitDefaultStatement(@NotNull StructurizrDefaultStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(13);
        }
        this.visitPsiElement(o);
    }

    public void visitDeploymentEnvironmentBlock(@NotNull StructurizrDeploymentEnvironmentBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(14);
        }
        this.visitPsiElement(o);
    }

    public void visitDeploymentGroupBlock(@NotNull StructurizrDeploymentGroupBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(15);
        }
        this.visitPsiElement(o);
    }

    public void visitDeploymentGroups(@NotNull StructurizrDeploymentGroups o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(16);
        }
        this.visitPsiElement(o);
    }

    public void visitDeploymentNodeDefinition(@NotNull StructurizrDeploymentNodeDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(17);
        }
        this.visitPsiElement(o);
    }

    public void visitDeploymentView(@NotNull StructurizrDeploymentView o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(18);
        }
        this.visitPsiElement(o);
    }

    public void visitDescriptionStatement(@NotNull StructurizrDescriptionStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(19);
        }
        this.visitPsiElement(o);
    }

    public void visitDescriptionString(@NotNull StructurizrDescriptionString o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(20);
        }
        this.visitPsiElement(o);
    }

    public void visitDirective(@NotNull StructurizrDirective o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(21);
        }
        this.visitPsiElement(o);
    }

    public void visitDocsDirective(@NotNull StructurizrDocsDirective o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(22);
        }
        this.visitPsiElement(o);
    }

    public void visitDynamicView(@NotNull StructurizrDynamicView o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(23);
        }
        this.visitPsiElement(o);
    }

    public void visitElementAssignment(@NotNull StructurizrElementAssignment o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(24);
        }
        this.visitPsiElement(o);
    }

    public void visitElementReference(@NotNull StructurizrElementReference o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(25);
        }
        this.visitPsiElement(o);
    }

    public void visitElementSelector(@NotNull StructurizrElementSelector o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(26);
        }
        this.visitPsiElement(o);
    }

    public void visitElementStyle(@NotNull StructurizrElementStyle o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(27);
        }
        this.visitPsiElement(o);
    }

    public void visitEnterpriseBlock(@NotNull StructurizrEnterpriseBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(28);
        }
        this.visitPsiElement(o);
    }

    public void visitExcludeStatement(@NotNull StructurizrExcludeStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(29);
        }
        this.visitPsiElement(o);
    }

    public void visitFilePath(@NotNull StructurizrFilePath o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(30);
        }
        this.visitPsiElement(o);
    }

    public void visitFilterMode(@NotNull StructurizrFilterMode o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(31);
        }
        this.visitPsiElement(o);
    }

    public void visitFilterTags(@NotNull StructurizrFilterTags o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(32);
        }
        this.visitPsiElement(o);
    }

    public void visitFilteredView(@NotNull StructurizrFilteredView o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(33);
        }
        this.visitPsiElement(o);
    }

    public void visitFontStatement(@NotNull StructurizrFontStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(34);
        }
        this.visitPsiElement(o);
    }

    public void visitGroupBlock(@NotNull StructurizrGroupBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(35);
        }
        this.visitPsiElement(o);
    }

    public void visitIdentifiersDirective(@NotNull StructurizrIdentifiersDirective o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(36);
        }
        this.visitPsiElement(o);
    }

    public void visitImageKroki(@NotNull StructurizrImageKroki o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(37);
        }
        this.visitPsiElement(o);
    }

    public void visitImageMermaid(@NotNull StructurizrImageMermaid o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(38);
        }
        this.visitPsiElement(o);
    }

    public void visitImagePlantuml(@NotNull StructurizrImagePlantuml o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(39);
        }
        this.visitPsiElement(o);
    }

    public void visitImageView(@NotNull StructurizrImageView o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(40);
        }
        this.visitPsiElement(o);
    }

    public void visitImpliedRelationshipsDirective(@NotNull StructurizrImpliedRelationshipsDirective o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(41);
        }
        this.visitPsiElement(o);
    }

    public void visitIncludeDirective(@NotNull StructurizrIncludeDirective o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(42);
        }
        this.visitPsiElement(o);
    }

    public void visitIncludeStatement(@NotNull StructurizrIncludeStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(43);
        }
        this.visitPsiElement(o);
    }

    public void visitInfrastructureNodeDefinition(@NotNull StructurizrInfrastructureNodeDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(44);
        }
        this.visitPsiElement(o);
    }

    public void visitInstancesString(@NotNull StructurizrInstancesString o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(45);
        }
        this.visitPsiElement(o);
    }

    public void visitLayoutDirection(@NotNull StructurizrLayoutDirection o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(46);
        }
        this.visitPsiElement(o);
    }

    public void visitLogoStatement(@NotNull StructurizrLogoStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(47);
        }
        this.visitPsiElement(o);
    }

    public void visitModelBlock(@NotNull StructurizrModelBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(48);
        }
        this.visitPsiElement(o);
    }

    public void visitNodeSeparation(@NotNull StructurizrNodeSeparation o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(49);
        }
        this.visitPsiElement(o);
    }

    public void visitPersonDefinition(@NotNull StructurizrPersonDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(50);
        }
        this.visitPsiElement(o);
    }

    public void visitPerspectiveDefinition(@NotNull StructurizrPerspectiveDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(51);
        }
        this.visitPsiElement(o);
    }

    public void visitPerspectivesBlock(@NotNull StructurizrPerspectivesBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(52);
        }
        this.visitPsiElement(o);
    }

    public void visitPropertiesBlock(@NotNull StructurizrPropertiesBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(53);
        }
        this.visitPsiElement(o);
    }

    public void visitPropertyDefinition(@NotNull StructurizrPropertyDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(54);
        }
        this.visitPsiElement(o);
    }

    public void visitRankSeparation(@NotNull StructurizrRankSeparation o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(55);
        }
        this.visitPsiElement(o);
    }

    public void visitRefStatement(@NotNull StructurizrRefStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(56);
        }
        this.visitPsiElement(o);
    }

    public void visitRelationshipDescription(@NotNull StructurizrRelationshipDescription o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(57);
        }
        this.visitPsiElement(o);
    }

    public void visitRelationshipInDynamicView(@NotNull StructurizrRelationshipInDynamicView o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(58);
        }
        this.visitPsiElement(o);
    }

    public void visitRelationshipStatement(@NotNull StructurizrRelationshipStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(59);
        }
        this.visitPsiElement(o);
    }

    public void visitRelationshipStyle(@NotNull StructurizrRelationshipStyle o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(60);
        }
        this.visitPsiElement(o);
    }

    public void visitRelationshipTags(@NotNull StructurizrRelationshipTags o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(61);
        }
        this.visitPsiElement(o);
    }

    public void visitRelationshipTechnology(@NotNull StructurizrRelationshipTechnology o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(62);
        }
        this.visitPsiElement(o);
    }

    public void visitScopeStatement(@NotNull StructurizrScopeStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(63);
        }
        this.visitPsiElement(o);
    }

    public void visitShapeValue(@NotNull StructurizrShapeValue o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(64);
        }
        this.visitPsiElement(o);
    }

    public void visitSoftwareSystemDefinition(@NotNull StructurizrSoftwareSystemDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(65);
        }
        this.visitPsiElement(o);
    }

    public void visitSoftwareSystemInstanceDefinition(@NotNull StructurizrSoftwareSystemInstanceDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(66);
        }
        this.visitPsiElement(o);
    }

    public void visitStyleProperty(@NotNull StructurizrStyleProperty o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(67);
        }
        this.visitPsiElement(o);
    }

    public void visitStylePropertyName(@NotNull StructurizrStylePropertyName o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(68);
        }
        this.visitPsiElement(o);
    }

    public void visitStylePropertyValue(@NotNull StructurizrStylePropertyValue o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(69);
        }
        this.visitPsiElement(o);
    }

    public void visitStylesBlock(@NotNull StructurizrStylesBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(70);
        }
        this.visitPsiElement(o);
    }

    public void visitSystemContextView(@NotNull StructurizrSystemContextView o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(71);
        }
        this.visitPsiElement(o);
    }

    public void visitSystemLandscapeView(@NotNull StructurizrSystemLandscapeView o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(72);
        }
        this.visitPsiElement(o);
    }

    public void visitTagsStatement(@NotNull StructurizrTagsStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(73);
        }
        this.visitPsiElement(o);
    }

    public void visitTagsString(@NotNull StructurizrTagsString o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(74);
        }
        this.visitPsiElement(o);
    }

    public void visitTechnologyString(@NotNull StructurizrTechnologyString o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(75);
        }
        this.visitPsiElement(o);
    }

    public void visitTerminologyBlock(@NotNull StructurizrTerminologyBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(76);
        }
        this.visitPsiElement(o);
    }

    public void visitTerminologyItem(@NotNull StructurizrTerminologyItem o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(77);
        }
        this.visitPsiElement(o);
    }

    public void visitThemesStatement(@NotNull StructurizrThemesStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(78);
        }
        this.visitPsiElement(o);
    }

    public void visitTitleStatement(@NotNull StructurizrTitleStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(79);
        }
        this.visitPsiElement(o);
    }

    public void visitUrlStatement(@NotNull StructurizrUrlStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(80);
        }
        this.visitPsiElement(o);
    }

    public void visitUserDefinition(@NotNull StructurizrUserDefinition o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(81);
        }
        this.visitPsiElement(o);
    }

    public void visitUsersBlock(@NotNull StructurizrUsersBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(82);
        }
        this.visitPsiElement(o);
    }

    public void visitValueLiteral(@NotNull StructurizrValueLiteral o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(83);
        }
        this.visitPsiElement(o);
    }

    public void visitViewDescription(@NotNull StructurizrViewDescription o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(84);
        }
        this.visitPsiElement(o);
    }

    public void visitViewKey(@NotNull StructurizrViewKey o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(85);
        }
        this.visitPsiElement(o);
    }

    public void visitViewsBlock(@NotNull StructurizrViewsBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(86);
        }
        this.visitPsiElement(o);
    }

    public void visitVisibilityStatement(@NotNull StructurizrVisibilityStatement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(87);
        }
        this.visitPsiElement(o);
    }

    public void visitWorkspaceBlock(@NotNull StructurizrWorkspaceBlock o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(88);
        }
        this.visitPsiElement(o);
    }

    public void visitWorkspaceDescription(@NotNull StructurizrWorkspaceDescription o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(89);
        }
        this.visitPsiElement(o);
    }

    public void visitWorkspaceName(@NotNull StructurizrWorkspaceName o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(90);
        }
        this.visitPsiElement(o);
    }

    public void visitPsiElement(@NotNull PsiElement o) {
        if (o == null) {
            StructurizrVisitor.$$$reportNull$$$0(91);
        }
        this.visitElement(o);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2 = new Object[3];
        objectArray2[0] = "o";
        objectArray2[1] = "com/jakubjirak/structurizr/language/psi/StructurizrVisitor";
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[2] = "visitAdrsDirective";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[2] = "visitAnimationBlock";
                break;
            }
            case 2: {
                objectArray = objectArray2;
                objectArray2[2] = "visitAnimationStep";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[2] = "visitAutoLayoutStatement";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[2] = "visitBrandingBlock";
                break;
            }
            case 5: {
                objectArray = objectArray2;
                objectArray2[2] = "visitComponentDefinition";
                break;
            }
            case 6: {
                objectArray = objectArray2;
                objectArray2[2] = "visitComponentView";
                break;
            }
            case 7: {
                objectArray = objectArray2;
                objectArray2[2] = "visitConfigurationBlock";
                break;
            }
            case 8: {
                objectArray = objectArray2;
                objectArray2[2] = "visitConstantDirective";
                break;
            }
            case 9: {
                objectArray = objectArray2;
                objectArray2[2] = "visitContainerDefinition";
                break;
            }
            case 10: {
                objectArray = objectArray2;
                objectArray2[2] = "visitContainerInstanceDefinition";
                break;
            }
            case 11: {
                objectArray = objectArray2;
                objectArray2[2] = "visitContainerView";
                break;
            }
            case 12: {
                objectArray = objectArray2;
                objectArray2[2] = "visitCustomView";
                break;
            }
            case 13: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDefaultStatement";
                break;
            }
            case 14: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDeploymentEnvironmentBlock";
                break;
            }
            case 15: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDeploymentGroupBlock";
                break;
            }
            case 16: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDeploymentGroups";
                break;
            }
            case 17: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDeploymentNodeDefinition";
                break;
            }
            case 18: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDeploymentView";
                break;
            }
            case 19: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDescriptionStatement";
                break;
            }
            case 20: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDescriptionString";
                break;
            }
            case 21: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDirective";
                break;
            }
            case 22: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDocsDirective";
                break;
            }
            case 23: {
                objectArray = objectArray2;
                objectArray2[2] = "visitDynamicView";
                break;
            }
            case 24: {
                objectArray = objectArray2;
                objectArray2[2] = "visitElementAssignment";
                break;
            }
            case 25: {
                objectArray = objectArray2;
                objectArray2[2] = "visitElementReference";
                break;
            }
            case 26: {
                objectArray = objectArray2;
                objectArray2[2] = "visitElementSelector";
                break;
            }
            case 27: {
                objectArray = objectArray2;
                objectArray2[2] = "visitElementStyle";
                break;
            }
            case 28: {
                objectArray = objectArray2;
                objectArray2[2] = "visitEnterpriseBlock";
                break;
            }
            case 29: {
                objectArray = objectArray2;
                objectArray2[2] = "visitExcludeStatement";
                break;
            }
            case 30: {
                objectArray = objectArray2;
                objectArray2[2] = "visitFilePath";
                break;
            }
            case 31: {
                objectArray = objectArray2;
                objectArray2[2] = "visitFilterMode";
                break;
            }
            case 32: {
                objectArray = objectArray2;
                objectArray2[2] = "visitFilterTags";
                break;
            }
            case 33: {
                objectArray = objectArray2;
                objectArray2[2] = "visitFilteredView";
                break;
            }
            case 34: {
                objectArray = objectArray2;
                objectArray2[2] = "visitFontStatement";
                break;
            }
            case 35: {
                objectArray = objectArray2;
                objectArray2[2] = "visitGroupBlock";
                break;
            }
            case 36: {
                objectArray = objectArray2;
                objectArray2[2] = "visitIdentifiersDirective";
                break;
            }
            case 37: {
                objectArray = objectArray2;
                objectArray2[2] = "visitImageKroki";
                break;
            }
            case 38: {
                objectArray = objectArray2;
                objectArray2[2] = "visitImageMermaid";
                break;
            }
            case 39: {
                objectArray = objectArray2;
                objectArray2[2] = "visitImagePlantuml";
                break;
            }
            case 40: {
                objectArray = objectArray2;
                objectArray2[2] = "visitImageView";
                break;
            }
            case 41: {
                objectArray = objectArray2;
                objectArray2[2] = "visitImpliedRelationshipsDirective";
                break;
            }
            case 42: {
                objectArray = objectArray2;
                objectArray2[2] = "visitIncludeDirective";
                break;
            }
            case 43: {
                objectArray = objectArray2;
                objectArray2[2] = "visitIncludeStatement";
                break;
            }
            case 44: {
                objectArray = objectArray2;
                objectArray2[2] = "visitInfrastructureNodeDefinition";
                break;
            }
            case 45: {
                objectArray = objectArray2;
                objectArray2[2] = "visitInstancesString";
                break;
            }
            case 46: {
                objectArray = objectArray2;
                objectArray2[2] = "visitLayoutDirection";
                break;
            }
            case 47: {
                objectArray = objectArray2;
                objectArray2[2] = "visitLogoStatement";
                break;
            }
            case 48: {
                objectArray = objectArray2;
                objectArray2[2] = "visitModelBlock";
                break;
            }
            case 49: {
                objectArray = objectArray2;
                objectArray2[2] = "visitNodeSeparation";
                break;
            }
            case 50: {
                objectArray = objectArray2;
                objectArray2[2] = "visitPersonDefinition";
                break;
            }
            case 51: {
                objectArray = objectArray2;
                objectArray2[2] = "visitPerspectiveDefinition";
                break;
            }
            case 52: {
                objectArray = objectArray2;
                objectArray2[2] = "visitPerspectivesBlock";
                break;
            }
            case 53: {
                objectArray = objectArray2;
                objectArray2[2] = "visitPropertiesBlock";
                break;
            }
            case 54: {
                objectArray = objectArray2;
                objectArray2[2] = "visitPropertyDefinition";
                break;
            }
            case 55: {
                objectArray = objectArray2;
                objectArray2[2] = "visitRankSeparation";
                break;
            }
            case 56: {
                objectArray = objectArray2;
                objectArray2[2] = "visitRefStatement";
                break;
            }
            case 57: {
                objectArray = objectArray2;
                objectArray2[2] = "visitRelationshipDescription";
                break;
            }
            case 58: {
                objectArray = objectArray2;
                objectArray2[2] = "visitRelationshipInDynamicView";
                break;
            }
            case 59: {
                objectArray = objectArray2;
                objectArray2[2] = "visitRelationshipStatement";
                break;
            }
            case 60: {
                objectArray = objectArray2;
                objectArray2[2] = "visitRelationshipStyle";
                break;
            }
            case 61: {
                objectArray = objectArray2;
                objectArray2[2] = "visitRelationshipTags";
                break;
            }
            case 62: {
                objectArray = objectArray2;
                objectArray2[2] = "visitRelationshipTechnology";
                break;
            }
            case 63: {
                objectArray = objectArray2;
                objectArray2[2] = "visitScopeStatement";
                break;
            }
            case 64: {
                objectArray = objectArray2;
                objectArray2[2] = "visitShapeValue";
                break;
            }
            case 65: {
                objectArray = objectArray2;
                objectArray2[2] = "visitSoftwareSystemDefinition";
                break;
            }
            case 66: {
                objectArray = objectArray2;
                objectArray2[2] = "visitSoftwareSystemInstanceDefinition";
                break;
            }
            case 67: {
                objectArray = objectArray2;
                objectArray2[2] = "visitStyleProperty";
                break;
            }
            case 68: {
                objectArray = objectArray2;
                objectArray2[2] = "visitStylePropertyName";
                break;
            }
            case 69: {
                objectArray = objectArray2;
                objectArray2[2] = "visitStylePropertyValue";
                break;
            }
            case 70: {
                objectArray = objectArray2;
                objectArray2[2] = "visitStylesBlock";
                break;
            }
            case 71: {
                objectArray = objectArray2;
                objectArray2[2] = "visitSystemContextView";
                break;
            }
            case 72: {
                objectArray = objectArray2;
                objectArray2[2] = "visitSystemLandscapeView";
                break;
            }
            case 73: {
                objectArray = objectArray2;
                objectArray2[2] = "visitTagsStatement";
                break;
            }
            case 74: {
                objectArray = objectArray2;
                objectArray2[2] = "visitTagsString";
                break;
            }
            case 75: {
                objectArray = objectArray2;
                objectArray2[2] = "visitTechnologyString";
                break;
            }
            case 76: {
                objectArray = objectArray2;
                objectArray2[2] = "visitTerminologyBlock";
                break;
            }
            case 77: {
                objectArray = objectArray2;
                objectArray2[2] = "visitTerminologyItem";
                break;
            }
            case 78: {
                objectArray = objectArray2;
                objectArray2[2] = "visitThemesStatement";
                break;
            }
            case 79: {
                objectArray = objectArray2;
                objectArray2[2] = "visitTitleStatement";
                break;
            }
            case 80: {
                objectArray = objectArray2;
                objectArray2[2] = "visitUrlStatement";
                break;
            }
            case 81: {
                objectArray = objectArray2;
                objectArray2[2] = "visitUserDefinition";
                break;
            }
            case 82: {
                objectArray = objectArray2;
                objectArray2[2] = "visitUsersBlock";
                break;
            }
            case 83: {
                objectArray = objectArray2;
                objectArray2[2] = "visitValueLiteral";
                break;
            }
            case 84: {
                objectArray = objectArray2;
                objectArray2[2] = "visitViewDescription";
                break;
            }
            case 85: {
                objectArray = objectArray2;
                objectArray2[2] = "visitViewKey";
                break;
            }
            case 86: {
                objectArray = objectArray2;
                objectArray2[2] = "visitViewsBlock";
                break;
            }
            case 87: {
                objectArray = objectArray2;
                objectArray2[2] = "visitVisibilityStatement";
                break;
            }
            case 88: {
                objectArray = objectArray2;
                objectArray2[2] = "visitWorkspaceBlock";
                break;
            }
            case 89: {
                objectArray = objectArray2;
                objectArray2[2] = "visitWorkspaceDescription";
                break;
            }
            case 90: {
                objectArray = objectArray2;
                objectArray2[2] = "visitWorkspaceName";
                break;
            }
            case 91: {
                objectArray = objectArray2;
                objectArray2[2] = "visitPsiElement";
                break;
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }
}
