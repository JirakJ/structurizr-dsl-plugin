/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.ASTNode
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.tree.IElementType
 */
package com.jakubjirak.structurizr.language.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.psi.StructurizrElementType;
import com.jakubjirak.structurizr.language.psi.StructurizrTokenType;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrAdrsDirectiveImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrAnimationBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrAnimationStepImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrAutoLayoutStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrBrandingBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrComponentDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrComponentViewImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrConfigurationBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrConstantDirectiveImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrContainerDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrContainerInstanceDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrContainerViewImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrCustomViewImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDefaultStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDeploymentEnvironmentBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDeploymentGroupBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDeploymentGroupsImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDeploymentNodeDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDeploymentViewImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDescriptionStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDescriptionStringImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDirectiveImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDocsDirectiveImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrDynamicViewImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrElementAssignmentImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrElementReferenceImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrElementSelectorImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrElementStyleImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrEnterpriseBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrExcludeStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrFilePathImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrFilterModeImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrFilterTagsImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrFilteredViewImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrFontStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrGroupBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrIdentifiersDirectiveImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrImageKrokiImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrImageMermaidImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrImagePlantumlImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrImageViewImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrImpliedRelationshipsDirectiveImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrIncludeDirectiveImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrIncludeStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrInfrastructureNodeDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrInstancesStringImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrLayoutDirectionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrLogoStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrModelBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrNodeSeparationImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrPersonDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrPerspectiveDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrPerspectivesBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrPropertiesBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrPropertyDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrRankSeparationImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrRefStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrRelationshipDescriptionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrRelationshipInDynamicViewImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrRelationshipStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrRelationshipStyleImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrRelationshipTagsImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrRelationshipTechnologyImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrScopeStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrShapeValueImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrSoftwareSystemDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrSoftwareSystemInstanceDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrStylePropertyImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrStylePropertyNameImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrStylePropertyValueImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrStylesBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrSystemContextViewImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrSystemLandscapeViewImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrTagsStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrTagsStringImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrTechnologyStringImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrTerminologyBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrTerminologyItemImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrThemesStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrTitleStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrUrlStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrUserDefinitionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrUsersBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrValueLiteralImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrViewDescriptionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrViewKeyImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrViewsBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrVisibilityStatementImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrWorkspaceBlockImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrWorkspaceDescriptionImpl;
import com.jakubjirak.structurizr.language.psi.impl.StructurizrWorkspaceNameImpl;

public interface StructurizrTypes {
    public static final IElementType ADRS_DIRECTIVE = new StructurizrElementType("ADRS_DIRECTIVE");
    public static final IElementType ANIMATION_BLOCK = new StructurizrElementType("ANIMATION_BLOCK");
    public static final IElementType ANIMATION_STEP = new StructurizrElementType("ANIMATION_STEP");
    public static final IElementType AUTO_LAYOUT_STATEMENT = new StructurizrElementType("AUTO_LAYOUT_STATEMENT");
    public static final IElementType BRANDING_BLOCK = new StructurizrElementType("BRANDING_BLOCK");
    public static final IElementType COMPONENT_DEFINITION = new StructurizrElementType("COMPONENT_DEFINITION");
    public static final IElementType COMPONENT_VIEW = new StructurizrElementType("COMPONENT_VIEW");
    public static final IElementType CONFIGURATION_BLOCK = new StructurizrElementType("CONFIGURATION_BLOCK");
    public static final IElementType CONSTANT_DIRECTIVE = new StructurizrElementType("CONSTANT_DIRECTIVE");
    public static final IElementType CONTAINER_DEFINITION = new StructurizrElementType("CONTAINER_DEFINITION");
    public static final IElementType CONTAINER_INSTANCE_DEFINITION = new StructurizrElementType("CONTAINER_INSTANCE_DEFINITION");
    public static final IElementType CONTAINER_VIEW = new StructurizrElementType("CONTAINER_VIEW");
    public static final IElementType CUSTOM_VIEW = new StructurizrElementType("CUSTOM_VIEW");
    public static final IElementType DEFAULT_STATEMENT = new StructurizrElementType("DEFAULT_STATEMENT");
    public static final IElementType DEPLOYMENT_ENVIRONMENT_BLOCK = new StructurizrElementType("DEPLOYMENT_ENVIRONMENT_BLOCK");
    public static final IElementType DEPLOYMENT_GROUPS = new StructurizrElementType("DEPLOYMENT_GROUPS");
    public static final IElementType DEPLOYMENT_GROUP_BLOCK = new StructurizrElementType("DEPLOYMENT_GROUP_BLOCK");
    public static final IElementType DEPLOYMENT_NODE_DEFINITION = new StructurizrElementType("DEPLOYMENT_NODE_DEFINITION");
    public static final IElementType DEPLOYMENT_VIEW = new StructurizrElementType("DEPLOYMENT_VIEW");
    public static final IElementType DESCRIPTION_STATEMENT = new StructurizrElementType("DESCRIPTION_STATEMENT");
    public static final IElementType DESCRIPTION_STRING = new StructurizrElementType("DESCRIPTION_STRING");
    public static final IElementType DIRECTIVE = new StructurizrElementType("DIRECTIVE");
    public static final IElementType DOCS_DIRECTIVE = new StructurizrElementType("DOCS_DIRECTIVE");
    public static final IElementType DYNAMIC_VIEW = new StructurizrElementType("DYNAMIC_VIEW");
    public static final IElementType ELEMENT_ASSIGNMENT = new StructurizrElementType("ELEMENT_ASSIGNMENT");
    public static final IElementType ELEMENT_REFERENCE = new StructurizrElementType("ELEMENT_REFERENCE");
    public static final IElementType ELEMENT_SELECTOR = new StructurizrElementType("ELEMENT_SELECTOR");
    public static final IElementType ELEMENT_STYLE = new StructurizrElementType("ELEMENT_STYLE");
    public static final IElementType ENTERPRISE_BLOCK = new StructurizrElementType("ENTERPRISE_BLOCK");
    public static final IElementType EXCLUDE_STATEMENT = new StructurizrElementType("EXCLUDE_STATEMENT");
    public static final IElementType FILE_PATH = new StructurizrElementType("FILE_PATH");
    public static final IElementType FILTERED_VIEW = new StructurizrElementType("FILTERED_VIEW");
    public static final IElementType FILTER_MODE = new StructurizrElementType("FILTER_MODE");
    public static final IElementType FILTER_TAGS = new StructurizrElementType("FILTER_TAGS");
    public static final IElementType FONT_STATEMENT = new StructurizrElementType("FONT_STATEMENT");
    public static final IElementType GROUP_BLOCK = new StructurizrElementType("GROUP_BLOCK");
    public static final IElementType IDENTIFIERS_DIRECTIVE = new StructurizrElementType("IDENTIFIERS_DIRECTIVE");
    public static final IElementType IMAGE_KROKI = new StructurizrElementType("IMAGE_KROKI");
    public static final IElementType IMAGE_MERMAID = new StructurizrElementType("IMAGE_MERMAID");
    public static final IElementType IMAGE_PLANTUML = new StructurizrElementType("IMAGE_PLANTUML");
    public static final IElementType IMAGE_VIEW = new StructurizrElementType("IMAGE_VIEW");
    public static final IElementType IMPLIED_RELATIONSHIPS_DIRECTIVE = new StructurizrElementType("IMPLIED_RELATIONSHIPS_DIRECTIVE");
    public static final IElementType INCLUDE_DIRECTIVE = new StructurizrElementType("INCLUDE_DIRECTIVE");
    public static final IElementType INCLUDE_STATEMENT = new StructurizrElementType("INCLUDE_STATEMENT");
    public static final IElementType INFRASTRUCTURE_NODE_DEFINITION = new StructurizrElementType("INFRASTRUCTURE_NODE_DEFINITION");
    public static final IElementType INSTANCES_STRING = new StructurizrElementType("INSTANCES_STRING");
    public static final IElementType LAYOUT_DIRECTION = new StructurizrElementType("LAYOUT_DIRECTION");
    public static final IElementType LOGO_STATEMENT = new StructurizrElementType("LOGO_STATEMENT");
    public static final IElementType MODEL_BLOCK = new StructurizrElementType("MODEL_BLOCK");
    public static final IElementType NODE_SEPARATION = new StructurizrElementType("NODE_SEPARATION");
    public static final IElementType PERSON_DEFINITION = new StructurizrElementType("PERSON_DEFINITION");
    public static final IElementType PERSPECTIVES_BLOCK = new StructurizrElementType("PERSPECTIVES_BLOCK");
    public static final IElementType PERSPECTIVE_DEFINITION = new StructurizrElementType("PERSPECTIVE_DEFINITION");
    public static final IElementType PROPERTIES_BLOCK = new StructurizrElementType("PROPERTIES_BLOCK");
    public static final IElementType PROPERTY_DEFINITION = new StructurizrElementType("PROPERTY_DEFINITION");
    public static final IElementType RANK_SEPARATION = new StructurizrElementType("RANK_SEPARATION");
    public static final IElementType REF_STATEMENT = new StructurizrElementType("REF_STATEMENT");
    public static final IElementType RELATIONSHIP_DESCRIPTION = new StructurizrElementType("RELATIONSHIP_DESCRIPTION");
    public static final IElementType RELATIONSHIP_IN_DYNAMIC_VIEW = new StructurizrElementType("RELATIONSHIP_IN_DYNAMIC_VIEW");
    public static final IElementType RELATIONSHIP_STATEMENT = new StructurizrElementType("RELATIONSHIP_STATEMENT");
    public static final IElementType RELATIONSHIP_STYLE = new StructurizrElementType("RELATIONSHIP_STYLE");
    public static final IElementType RELATIONSHIP_TAGS = new StructurizrElementType("RELATIONSHIP_TAGS");
    public static final IElementType RELATIONSHIP_TECHNOLOGY = new StructurizrElementType("RELATIONSHIP_TECHNOLOGY");
    public static final IElementType SCOPE_STATEMENT = new StructurizrElementType("SCOPE_STATEMENT");
    public static final IElementType SHAPE_VALUE = new StructurizrElementType("SHAPE_VALUE");
    public static final IElementType SOFTWARE_SYSTEM_DEFINITION = new StructurizrElementType("SOFTWARE_SYSTEM_DEFINITION");
    public static final IElementType SOFTWARE_SYSTEM_INSTANCE_DEFINITION = new StructurizrElementType("SOFTWARE_SYSTEM_INSTANCE_DEFINITION");
    public static final IElementType STYLES_BLOCK = new StructurizrElementType("STYLES_BLOCK");
    public static final IElementType STYLE_PROPERTY = new StructurizrElementType("STYLE_PROPERTY");
    public static final IElementType STYLE_PROPERTY_NAME = new StructurizrElementType("STYLE_PROPERTY_NAME");
    public static final IElementType STYLE_PROPERTY_VALUE = new StructurizrElementType("STYLE_PROPERTY_VALUE");
    public static final IElementType SYSTEM_CONTEXT_VIEW = new StructurizrElementType("SYSTEM_CONTEXT_VIEW");
    public static final IElementType SYSTEM_LANDSCAPE_VIEW = new StructurizrElementType("SYSTEM_LANDSCAPE_VIEW");
    public static final IElementType TAGS_STATEMENT = new StructurizrElementType("TAGS_STATEMENT");
    public static final IElementType TAGS_STRING = new StructurizrElementType("TAGS_STRING");
    public static final IElementType TECHNOLOGY_STRING = new StructurizrElementType("TECHNOLOGY_STRING");
    public static final IElementType TERMINOLOGY_BLOCK = new StructurizrElementType("TERMINOLOGY_BLOCK");
    public static final IElementType TERMINOLOGY_ITEM = new StructurizrElementType("TERMINOLOGY_ITEM");
    public static final IElementType THEMES_STATEMENT = new StructurizrElementType("THEMES_STATEMENT");
    public static final IElementType TITLE_STATEMENT = new StructurizrElementType("TITLE_STATEMENT");
    public static final IElementType URL_STATEMENT = new StructurizrElementType("URL_STATEMENT");
    public static final IElementType USERS_BLOCK = new StructurizrElementType("USERS_BLOCK");
    public static final IElementType USER_DEFINITION = new StructurizrElementType("USER_DEFINITION");
    public static final IElementType VALUE_LITERAL = new StructurizrElementType("VALUE_LITERAL");
    public static final IElementType VIEWS_BLOCK = new StructurizrElementType("VIEWS_BLOCK");
    public static final IElementType VIEW_DESCRIPTION = new StructurizrElementType("VIEW_DESCRIPTION");
    public static final IElementType VIEW_KEY = new StructurizrElementType("VIEW_KEY");
    public static final IElementType VISIBILITY_STATEMENT = new StructurizrElementType("VISIBILITY_STATEMENT");
    public static final IElementType WORKSPACE_BLOCK = new StructurizrElementType("WORKSPACE_BLOCK");
    public static final IElementType WORKSPACE_DESCRIPTION = new StructurizrElementType("WORKSPACE_DESCRIPTION");
    public static final IElementType WORKSPACE_NAME = new StructurizrElementType("WORKSPACE_NAME");
    public static final IElementType ANIMATION = new StructurizrTokenType("animation");
    public static final IElementType ARROW = new StructurizrTokenType("->");
    public static final IElementType ARROW_BOTH = new StructurizrTokenType("<->");
    public static final IElementType ARROW_LEFT = new StructurizrTokenType("<-");
    public static final IElementType AUTO_LAYOUT = new StructurizrTokenType("autoLayout");
    public static final IElementType BACKGROUND = new StructurizrTokenType("background");
    public static final IElementType BANG = new StructurizrTokenType("!");
    public static final IElementType BLOCK_COMMENT = new StructurizrTokenType("BLOCK_COMMENT");
    public static final IElementType BORDER = new StructurizrTokenType("border");
    public static final IElementType BRANDING = new StructurizrTokenType("branding");
    public static final IElementType COLOR = new StructurizrTokenType("color");
    public static final IElementType COLOR_LITERAL = new StructurizrTokenType("COLOR_LITERAL");
    public static final IElementType COLOUR = new StructurizrTokenType("colour");
    public static final IElementType COMMA = new StructurizrTokenType(",");
    public static final IElementType COMPONENT = new StructurizrTokenType("component");
    public static final IElementType CONFIGURATION = new StructurizrTokenType("configuration");
    public static final IElementType CONTAINER = new StructurizrTokenType("container");
    public static final IElementType CONTAINER_INSTANCE = new StructurizrTokenType("containerInstance");
    public static final IElementType CUSTOM = new StructurizrTokenType("custom");
    public static final IElementType DASHED = new StructurizrTokenType("dashed");
    public static final IElementType DEFAULT = new StructurizrTokenType("default");
    public static final IElementType DEPLOYMENT = new StructurizrTokenType("deployment");
    public static final IElementType DEPLOYMENT_ENVIRONMENT = new StructurizrTokenType("deploymentEnvironment");
    public static final IElementType DEPLOYMENT_GROUP = new StructurizrTokenType("deploymentGroup");
    public static final IElementType DEPLOYMENT_NODE = new StructurizrTokenType("deploymentNode");
    public static final IElementType DESCRIPTION = new StructurizrTokenType("description");
    public static final IElementType DIRECTIVE_ADRS = new StructurizrTokenType("!adrs");
    public static final IElementType DIRECTIVE_CONST = new StructurizrTokenType("!const");
    public static final IElementType DIRECTIVE_CONSTANT = new StructurizrTokenType("!constant");
    public static final IElementType DIRECTIVE_DOCS = new StructurizrTokenType("!docs");
    public static final IElementType DIRECTIVE_IDENTIFIERS = new StructurizrTokenType("!identifiers");
    public static final IElementType DIRECTIVE_IMPLIED_RELATIONSHIPS = new StructurizrTokenType("!impliedRelationships");
    public static final IElementType DIRECTIVE_INCLUDE = new StructurizrTokenType("!include");
    public static final IElementType DIRECTIVE_PLUGIN = new StructurizrTokenType("!plugin");
    public static final IElementType DIRECTIVE_SCRIPT = new StructurizrTokenType("!script");
    public static final IElementType DIRECTIVE_VAR = new StructurizrTokenType("!var");
    public static final IElementType DOT = new StructurizrTokenType(".");
    public static final IElementType DYNAMIC = new StructurizrTokenType("dynamic");
    public static final IElementType ELEMENT = new StructurizrTokenType("element");
    public static final IElementType ENTERPRISE = new StructurizrTokenType("enterprise");
    public static final IElementType EQUALS = new StructurizrTokenType("=");
    public static final IElementType EXCLUDE = new StructurizrTokenType("exclude");
    public static final IElementType EXTENDS = new StructurizrTokenType("extends");
    public static final IElementType FALSE = new StructurizrTokenType("false");
    public static final IElementType FILTERED = new StructurizrTokenType("filtered");
    public static final IElementType FLAT = new StructurizrTokenType("flat");
    public static final IElementType FONT_SIZE = new StructurizrTokenType("fontSize");
    public static final IElementType GROUP = new StructurizrTokenType("group");
    public static final IElementType HEIGHT = new StructurizrTokenType("height");
    public static final IElementType HIERARCHICAL = new StructurizrTokenType("hierarchical");
    public static final IElementType ICON = new StructurizrTokenType("icon");
    public static final IElementType IDENTIFIER = new StructurizrTokenType("IDENTIFIER");
    public static final IElementType IMAGE = new StructurizrTokenType("image");
    public static final IElementType INCLUDE = new StructurizrTokenType("include");
    public static final IElementType INFRASTRUCTURE_NODE = new StructurizrTokenType("infrastructureNode");
    public static final IElementType LAYOUT_BT = new StructurizrTokenType("bt");
    public static final IElementType LAYOUT_LR = new StructurizrTokenType("lr");
    public static final IElementType LAYOUT_RL = new StructurizrTokenType("rl");
    public static final IElementType LAYOUT_TB = new StructurizrTokenType("tb");
    public static final IElementType LBRACE = new StructurizrTokenType("{");
    public static final IElementType LINE_COMMENT = new StructurizrTokenType("LINE_COMMENT");
    public static final IElementType METADATA = new StructurizrTokenType("metadata");
    public static final IElementType MODEL = new StructurizrTokenType("model");
    public static final IElementType NUMBER = new StructurizrTokenType("NUMBER");
    public static final IElementType OPACITY = new StructurizrTokenType("opacity");
    public static final IElementType PERSON = new StructurizrTokenType("person");
    public static final IElementType PERSPECTIVES = new StructurizrTokenType("perspectives");
    public static final IElementType POSITION = new StructurizrTokenType("position");
    public static final IElementType PRIVATE = new StructurizrTokenType("private");
    public static final IElementType PROPERTIES = new StructurizrTokenType("properties");
    public static final IElementType PUBLIC = new StructurizrTokenType("public");
    public static final IElementType RBRACE = new StructurizrTokenType("}");
    public static final IElementType READ = new StructurizrTokenType("read");
    public static final IElementType REF = new StructurizrTokenType("ref");
    public static final IElementType RELATIONSHIP = new StructurizrTokenType("relationship");
    public static final IElementType ROUTING = new StructurizrTokenType("routing");
    public static final IElementType SHAPE = new StructurizrTokenType("shape");
    public static final IElementType SHAPE_BOX = new StructurizrTokenType("Box");
    public static final IElementType SHAPE_CIRCLE = new StructurizrTokenType("Circle");
    public static final IElementType SHAPE_COMPONENT = new StructurizrTokenType("Component");
    public static final IElementType SHAPE_CYLINDER = new StructurizrTokenType("Cylinder");
    public static final IElementType SHAPE_ELLIPSE = new StructurizrTokenType("Ellipse");
    public static final IElementType SHAPE_FOLDER = new StructurizrTokenType("Folder");
    public static final IElementType SHAPE_HEXAGON = new StructurizrTokenType("Hexagon");
    public static final IElementType SHAPE_MOBILE_LANDSCAPE = new StructurizrTokenType("MobileDeviceLandscape");
    public static final IElementType SHAPE_MOBILE_PORTRAIT = new StructurizrTokenType("MobileDevicePortrait");
    public static final IElementType SHAPE_PERSON = new StructurizrTokenType("Person");
    public static final IElementType SHAPE_PIPE = new StructurizrTokenType("Pipe");
    public static final IElementType SHAPE_ROBOT = new StructurizrTokenType("Robot");
    public static final IElementType SHAPE_ROUNDED_BOX = new StructurizrTokenType("RoundedBox");
    public static final IElementType SHAPE_WEB_BROWSER = new StructurizrTokenType("WebBrowser");
    public static final IElementType SOFTWARE_SYSTEM = new StructurizrTokenType("softwareSystem");
    public static final IElementType SOFTWARE_SYSTEM_INSTANCE = new StructurizrTokenType("softwareSystemInstance");
    public static final IElementType STAR = new StructurizrTokenType("*");
    public static final IElementType STRING = new StructurizrTokenType("STRING");
    public static final IElementType STROKE = new StructurizrTokenType("stroke");
    public static final IElementType STROKE_WIDTH = new StructurizrTokenType("strokeWidth");
    public static final IElementType STYLES = new StructurizrTokenType("styles");
    public static final IElementType STYLE_PROP = new StructurizrTokenType("style");
    public static final IElementType SYSTEM_CONTEXT = new StructurizrTokenType("systemContext");
    public static final IElementType SYSTEM_LANDSCAPE = new StructurizrTokenType("systemLandscape");
    public static final IElementType TAGS = new StructurizrTokenType("tags");
    public static final IElementType TERMINOLOGY = new StructurizrTokenType("terminology");
    public static final IElementType THEME = new StructurizrTokenType("theme");
    public static final IElementType THEMES = new StructurizrTokenType("themes");
    public static final IElementType THICKNESS = new StructurizrTokenType("thickness");
    public static final IElementType THIS = new StructurizrTokenType("this");
    public static final IElementType TITLE = new StructurizrTokenType("title");
    public static final IElementType TRUE = new StructurizrTokenType("true");
    public static final IElementType URL = new StructurizrTokenType("url");
    public static final IElementType USERS = new StructurizrTokenType("users");
    public static final IElementType VAR_START = new StructurizrTokenType("${");
    public static final IElementType VIEWS = new StructurizrTokenType("views");
    public static final IElementType WIDTH = new StructurizrTokenType("width");
    public static final IElementType WORKSPACE = new StructurizrTokenType("workspace");
    public static final IElementType WRITE = new StructurizrTokenType("write");

    public static class Factory {
        public static PsiElement createElement(ASTNode node) {
            IElementType type = node.getElementType();
            if (type == ADRS_DIRECTIVE) {
                return new StructurizrAdrsDirectiveImpl(node);
            }
            if (type == ANIMATION_BLOCK) {
                return new StructurizrAnimationBlockImpl(node);
            }
            if (type == ANIMATION_STEP) {
                return new StructurizrAnimationStepImpl(node);
            }
            if (type == AUTO_LAYOUT_STATEMENT) {
                return new StructurizrAutoLayoutStatementImpl(node);
            }
            if (type == BRANDING_BLOCK) {
                return new StructurizrBrandingBlockImpl(node);
            }
            if (type == COMPONENT_DEFINITION) {
                return new StructurizrComponentDefinitionImpl(node);
            }
            if (type == COMPONENT_VIEW) {
                return new StructurizrComponentViewImpl(node);
            }
            if (type == CONFIGURATION_BLOCK) {
                return new StructurizrConfigurationBlockImpl(node);
            }
            if (type == CONSTANT_DIRECTIVE) {
                return new StructurizrConstantDirectiveImpl(node);
            }
            if (type == CONTAINER_DEFINITION) {
                return new StructurizrContainerDefinitionImpl(node);
            }
            if (type == CONTAINER_INSTANCE_DEFINITION) {
                return new StructurizrContainerInstanceDefinitionImpl(node);
            }
            if (type == CONTAINER_VIEW) {
                return new StructurizrContainerViewImpl(node);
            }
            if (type == CUSTOM_VIEW) {
                return new StructurizrCustomViewImpl(node);
            }
            if (type == DEFAULT_STATEMENT) {
                return new StructurizrDefaultStatementImpl(node);
            }
            if (type == DEPLOYMENT_ENVIRONMENT_BLOCK) {
                return new StructurizrDeploymentEnvironmentBlockImpl(node);
            }
            if (type == DEPLOYMENT_GROUPS) {
                return new StructurizrDeploymentGroupsImpl(node);
            }
            if (type == DEPLOYMENT_GROUP_BLOCK) {
                return new StructurizrDeploymentGroupBlockImpl(node);
            }
            if (type == DEPLOYMENT_NODE_DEFINITION) {
                return new StructurizrDeploymentNodeDefinitionImpl(node);
            }
            if (type == DEPLOYMENT_VIEW) {
                return new StructurizrDeploymentViewImpl(node);
            }
            if (type == DESCRIPTION_STATEMENT) {
                return new StructurizrDescriptionStatementImpl(node);
            }
            if (type == DESCRIPTION_STRING) {
                return new StructurizrDescriptionStringImpl(node);
            }
            if (type == DIRECTIVE) {
                return new StructurizrDirectiveImpl(node);
            }
            if (type == DOCS_DIRECTIVE) {
                return new StructurizrDocsDirectiveImpl(node);
            }
            if (type == DYNAMIC_VIEW) {
                return new StructurizrDynamicViewImpl(node);
            }
            if (type == ELEMENT_ASSIGNMENT) {
                return new StructurizrElementAssignmentImpl(node);
            }
            if (type == ELEMENT_REFERENCE) {
                return new StructurizrElementReferenceImpl(node);
            }
            if (type == ELEMENT_SELECTOR) {
                return new StructurizrElementSelectorImpl(node);
            }
            if (type == ELEMENT_STYLE) {
                return new StructurizrElementStyleImpl(node);
            }
            if (type == ENTERPRISE_BLOCK) {
                return new StructurizrEnterpriseBlockImpl(node);
            }
            if (type == EXCLUDE_STATEMENT) {
                return new StructurizrExcludeStatementImpl(node);
            }
            if (type == FILE_PATH) {
                return new StructurizrFilePathImpl(node);
            }
            if (type == FILTERED_VIEW) {
                return new StructurizrFilteredViewImpl(node);
            }
            if (type == FILTER_MODE) {
                return new StructurizrFilterModeImpl(node);
            }
            if (type == FILTER_TAGS) {
                return new StructurizrFilterTagsImpl(node);
            }
            if (type == FONT_STATEMENT) {
                return new StructurizrFontStatementImpl(node);
            }
            if (type == GROUP_BLOCK) {
                return new StructurizrGroupBlockImpl(node);
            }
            if (type == IDENTIFIERS_DIRECTIVE) {
                return new StructurizrIdentifiersDirectiveImpl(node);
            }
            if (type == IMAGE_KROKI) {
                return new StructurizrImageKrokiImpl(node);
            }
            if (type == IMAGE_MERMAID) {
                return new StructurizrImageMermaidImpl(node);
            }
            if (type == IMAGE_PLANTUML) {
                return new StructurizrImagePlantumlImpl(node);
            }
            if (type == IMAGE_VIEW) {
                return new StructurizrImageViewImpl(node);
            }
            if (type == IMPLIED_RELATIONSHIPS_DIRECTIVE) {
                return new StructurizrImpliedRelationshipsDirectiveImpl(node);
            }
            if (type == INCLUDE_DIRECTIVE) {
                return new StructurizrIncludeDirectiveImpl(node);
            }
            if (type == INCLUDE_STATEMENT) {
                return new StructurizrIncludeStatementImpl(node);
            }
            if (type == INFRASTRUCTURE_NODE_DEFINITION) {
                return new StructurizrInfrastructureNodeDefinitionImpl(node);
            }
            if (type == INSTANCES_STRING) {
                return new StructurizrInstancesStringImpl(node);
            }
            if (type == LAYOUT_DIRECTION) {
                return new StructurizrLayoutDirectionImpl(node);
            }
            if (type == LOGO_STATEMENT) {
                return new StructurizrLogoStatementImpl(node);
            }
            if (type == MODEL_BLOCK) {
                return new StructurizrModelBlockImpl(node);
            }
            if (type == NODE_SEPARATION) {
                return new StructurizrNodeSeparationImpl(node);
            }
            if (type == PERSON_DEFINITION) {
                return new StructurizrPersonDefinitionImpl(node);
            }
            if (type == PERSPECTIVES_BLOCK) {
                return new StructurizrPerspectivesBlockImpl(node);
            }
            if (type == PERSPECTIVE_DEFINITION) {
                return new StructurizrPerspectiveDefinitionImpl(node);
            }
            if (type == PROPERTIES_BLOCK) {
                return new StructurizrPropertiesBlockImpl(node);
            }
            if (type == PROPERTY_DEFINITION) {
                return new StructurizrPropertyDefinitionImpl(node);
            }
            if (type == RANK_SEPARATION) {
                return new StructurizrRankSeparationImpl(node);
            }
            if (type == REF_STATEMENT) {
                return new StructurizrRefStatementImpl(node);
            }
            if (type == RELATIONSHIP_DESCRIPTION) {
                return new StructurizrRelationshipDescriptionImpl(node);
            }
            if (type == RELATIONSHIP_IN_DYNAMIC_VIEW) {
                return new StructurizrRelationshipInDynamicViewImpl(node);
            }
            if (type == RELATIONSHIP_STATEMENT) {
                return new StructurizrRelationshipStatementImpl(node);
            }
            if (type == RELATIONSHIP_STYLE) {
                return new StructurizrRelationshipStyleImpl(node);
            }
            if (type == RELATIONSHIP_TAGS) {
                return new StructurizrRelationshipTagsImpl(node);
            }
            if (type == RELATIONSHIP_TECHNOLOGY) {
                return new StructurizrRelationshipTechnologyImpl(node);
            }
            if (type == SCOPE_STATEMENT) {
                return new StructurizrScopeStatementImpl(node);
            }
            if (type == SHAPE_VALUE) {
                return new StructurizrShapeValueImpl(node);
            }
            if (type == SOFTWARE_SYSTEM_DEFINITION) {
                return new StructurizrSoftwareSystemDefinitionImpl(node);
            }
            if (type == SOFTWARE_SYSTEM_INSTANCE_DEFINITION) {
                return new StructurizrSoftwareSystemInstanceDefinitionImpl(node);
            }
            if (type == STYLES_BLOCK) {
                return new StructurizrStylesBlockImpl(node);
            }
            if (type == STYLE_PROPERTY) {
                return new StructurizrStylePropertyImpl(node);
            }
            if (type == STYLE_PROPERTY_NAME) {
                return new StructurizrStylePropertyNameImpl(node);
            }
            if (type == STYLE_PROPERTY_VALUE) {
                return new StructurizrStylePropertyValueImpl(node);
            }
            if (type == SYSTEM_CONTEXT_VIEW) {
                return new StructurizrSystemContextViewImpl(node);
            }
            if (type == SYSTEM_LANDSCAPE_VIEW) {
                return new StructurizrSystemLandscapeViewImpl(node);
            }
            if (type == TAGS_STATEMENT) {
                return new StructurizrTagsStatementImpl(node);
            }
            if (type == TAGS_STRING) {
                return new StructurizrTagsStringImpl(node);
            }
            if (type == TECHNOLOGY_STRING) {
                return new StructurizrTechnologyStringImpl(node);
            }
            if (type == TERMINOLOGY_BLOCK) {
                return new StructurizrTerminologyBlockImpl(node);
            }
            if (type == TERMINOLOGY_ITEM) {
                return new StructurizrTerminologyItemImpl(node);
            }
            if (type == THEMES_STATEMENT) {
                return new StructurizrThemesStatementImpl(node);
            }
            if (type == TITLE_STATEMENT) {
                return new StructurizrTitleStatementImpl(node);
            }
            if (type == URL_STATEMENT) {
                return new StructurizrUrlStatementImpl(node);
            }
            if (type == USERS_BLOCK) {
                return new StructurizrUsersBlockImpl(node);
            }
            if (type == USER_DEFINITION) {
                return new StructurizrUserDefinitionImpl(node);
            }
            if (type == VALUE_LITERAL) {
                return new StructurizrValueLiteralImpl(node);
            }
            if (type == VIEWS_BLOCK) {
                return new StructurizrViewsBlockImpl(node);
            }
            if (type == VIEW_DESCRIPTION) {
                return new StructurizrViewDescriptionImpl(node);
            }
            if (type == VIEW_KEY) {
                return new StructurizrViewKeyImpl(node);
            }
            if (type == VISIBILITY_STATEMENT) {
                return new StructurizrVisibilityStatementImpl(node);
            }
            if (type == WORKSPACE_BLOCK) {
                return new StructurizrWorkspaceBlockImpl(node);
            }
            if (type == WORKSPACE_DESCRIPTION) {
                return new StructurizrWorkspaceDescriptionImpl(node);
            }
            if (type == WORKSPACE_NAME) {
                return new StructurizrWorkspaceNameImpl(node);
            }
            throw new AssertionError((Object)("Unknown element type: " + String.valueOf(type)));
        }
    }
}
