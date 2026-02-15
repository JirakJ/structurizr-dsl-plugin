/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.documentation.AbstractDocumentationProvider
 *  com.intellij.psi.PsiElement
 *  kotlin.Metadata
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\t\u001a\u00020\u0005H\u0002J\b\u0010\n\u001a\u00020\u0005H\u0002J\b\u0010\u000b\u001a\u00020\u0005H\u0002J\b\u0010\f\u001a\u00020\u0005H\u0002J\b\u0010\r\u001a\u00020\u0005H\u0002J\b\u0010\u000e\u001a\u00020\u0005H\u0002J\b\u0010\u000f\u001a\u00020\u0005H\u0002J\b\u0010\u0010\u001a\u00020\u0005H\u0002J\b\u0010\u0011\u001a\u00020\u0005H\u0002J\b\u0010\u0012\u001a\u00020\u0005H\u0002J\b\u0010\u0013\u001a\u00020\u0005H\u0002J\b\u0010\u0014\u001a\u00020\u0005H\u0002J\b\u0010\u0015\u001a\u00020\u0005H\u0002\u00a8\u0006\u0016"}, d2={"Lcom/jakubjirak/structurizr/documentation/StructurizrDocumentationProvider;", "Lcom/intellij/lang/documentation/AbstractDocumentationProvider;", "<init>", "()V", "generateDoc", "", "element", "Lcom/intellij/psi/PsiElement;", "originalElement", "generateWorkspaceDoc", "generateModelDoc", "generateViewsDoc", "generatePersonDoc", "generateSoftwareSystemDoc", "generateContainerDoc", "generateComponentDoc", "generateDeploymentEnvironmentDoc", "generateDeploymentNodeDoc", "generateSystemContextDoc", "generateSystemLandscapeDoc", "generateStylesDoc", "generateAutoLayoutDoc", "structurizr-dsl-plugin"})
public final class StructurizrDocumentationProvider
extends AbstractDocumentationProvider {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Nullable
    public String generateDoc(@Nullable PsiElement element, @Nullable PsiElement originalElement) {
        if (element == null) {
            return null;
        }
        String text = element.getText();
        if (text == null) return null;
        int n = -1;
        switch (text.hashCode()) {
            case -410956671: {
                if (text.equals("container")) {
                    n = 1;
                }
                break;
            }
            case 1108864149: {
                if (text.equals("workspace")) {
                    n = 2;
                }
                break;
            }
            case -2075385330: {
                if (text.equals("deploymentEnvironment")) {
                    n = 3;
                }
                break;
            }
            case -1314498432: {
                if (text.equals("systemContext")) {
                    n = 4;
                }
                break;
            }
            case 1588261479: {
                if (text.equals("deploymentNode")) {
                    n = 5;
                }
                break;
            }
            case -600406599: {
                if (text.equals("autoLayout")) {
                    n = 6;
                }
                break;
            }
            case -1399907075: {
                if (text.equals("component")) {
                    n = 7;
                }
                break;
            }
            case -991716523: {
                if (text.equals("person")) {
                    n = 8;
                }
                break;
            }
            case 104069929: {
                if (text.equals("model")) {
                    n = 9;
                }
                break;
            }
            case -891774750: {
                if (text.equals("styles")) {
                    n = 10;
                }
                break;
            }
            case 1324852556: {
                if (text.equals("systemLandscape")) {
                    n = 11;
                }
                break;
            }
            case 112204398: {
                if (text.equals("views")) {
                    n = 12;
                }
                break;
            }
            case -611801418: {
                if (text.equals("softwareSystem")) {
                    n = 13;
                }
                break;
            }
        }
        switch (n) {
            case 2: {
                String string = this.generateWorkspaceDoc();
                return string;
            }
            case 9: {
                String string = this.generateModelDoc();
                return string;
            }
            case 12: {
                String string = this.generateViewsDoc();
                return string;
            }
            case 8: {
                String string = this.generatePersonDoc();
                return string;
            }
            case 13: {
                String string = this.generateSoftwareSystemDoc();
                return string;
            }
            case 1: {
                String string = this.generateContainerDoc();
                return string;
            }
            case 7: {
                String string = this.generateComponentDoc();
                return string;
            }
            case 3: {
                String string = this.generateDeploymentEnvironmentDoc();
                return string;
            }
            case 5: {
                String string = this.generateDeploymentNodeDoc();
                return string;
            }
            case 4: {
                String string = this.generateSystemContextDoc();
                return string;
            }
            case 11: {
                String string = this.generateSystemLandscapeDoc();
                return string;
            }
            case 10: {
                String string = this.generateStylesDoc();
                return string;
            }
            case 6: {
                String string = this.generateAutoLayoutDoc();
                return string;
            }
            default: {
                return null;
            }
        }
    }

    private final String generateWorkspaceDoc() {
        return "        <div class='definition'><pre>workspace</pre></div>\n        <div class='content'>\n            <p>Defines a Structurizr workspace containing the software architecture model and views.</p>\n            <p><b>Syntax:</b></p>\n            <pre>workspace [name] [description] {\n    model { ... }\n    views { ... }\n}</pre>\n            <p><b>Example:</b></p>\n            <pre>workspace \"My System\" \"Description of my system\" {\n    model {\n        // model elements\n    }\n    views {\n        // views\n    }\n}</pre>\n        </div>";
    }

    private final String generateModelDoc() {
        return "        <div class='definition'><pre>model</pre></div>\n        <div class='content'>\n            <p>Contains the model elements: people, software systems, containers, components, and relationships.</p>\n            <p><b>Syntax:</b></p>\n            <pre>model {\n    person ...\n    softwareSystem ...\n    deploymentEnvironment ...\n}</pre>\n        </div>";
    }

    private final String generateViewsDoc() {
        return "        <div class='definition'><pre>views</pre></div>\n        <div class='content'>\n            <p>Contains the views that visualize the model: system context, container, component, deployment diagrams.</p>\n            <p><b>Syntax:</b></p>\n            <pre>views {\n    systemLandscape ...\n    systemContext ...\n    container ...\n    component ...\n    deployment ...\n    styles { ... }\n}</pre>\n        </div>";
    }

    private final String generatePersonDoc() {
        return "<div class='definition'><pre>person</pre></div>\n<div class='content'>\n    <p>Defines a person (user, actor, or role) that interacts with the software system.</p>\n    <p><b>Syntax:</b></p>\n    <pre>[identifier =] person name [description] [tags]</pre>\n    <p><b>Example:</b></p>\n    <pre>user = person \"User\" \"A user of the system\" \"External\"</pre>\n</div>";
    }

    private final String generateSoftwareSystemDoc() {
        return "        <div class='definition'><pre>softwareSystem</pre></div>\n        <div class='content'>\n            <p>Defines a software system - the highest level of abstraction in the C4 model.</p>\n            <p><b>Syntax:</b></p>\n            <pre>[identifier =] softwareSystem name [description] [tags] {\n    container ...\n}</pre>\n            <p><b>Example:</b></p>\n            <pre>system = softwareSystem \"Banking System\" \"Handles banking operations\" {\n    webapp = container \"Web App\" \"The web frontend\" \"React\"\n}</pre>\n        </div>";
    }

    private final String generateContainerDoc() {
        return "        <div class='definition'><pre>container</pre></div>\n        <div class='content'>\n            <p>Defines a container - something that hosts code or data (application, database, file system, etc.).</p>\n            <p><b>Syntax:</b></p>\n            <pre>[identifier =] container name [description] [technology] [tags] {\n    component ...\n}</pre>\n            <p><b>Example:</b></p>\n            <pre>api = container \"API\" \"REST API\" \"Java/Spring Boot\" {\n    controller = component \"Controller\" \"Handles requests\" \"Spring MVC\"\n}</pre>\n        </div>";
    }

    private final String generateComponentDoc() {
        return "<div class='definition'><pre>component</pre></div>\n<div class='content'>\n    <p>Defines a component - a grouping of related functionality within a container.</p>\n    <p><b>Syntax:</b></p>\n    <pre>[identifier =] component name [description] [technology] [tags]</pre>\n    <p><b>Example:</b></p>\n    <pre>service = component \"User Service\" \"Manages users\" \"Spring Bean\"</pre>\n</div>";
    }

    private final String generateDeploymentEnvironmentDoc() {
        return "        <div class='definition'><pre>deploymentEnvironment</pre></div>\n        <div class='content'>\n            <p>Defines a deployment environment (e.g., Development, Staging, Production).</p>\n            <p><b>Syntax:</b></p>\n            <pre>deploymentEnvironment name {\n    deploymentNode ...\n}</pre>\n            <p><b>Example:</b></p>\n            <pre>deploymentEnvironment \"Production\" {\n    deploymentNode \"AWS\" {\n        deploymentNode \"EC2\" {\n            containerInstance api\n        }\n    }\n}</pre>\n        </div>";
    }

    private final String generateDeploymentNodeDoc() {
        return "        <div class='definition'><pre>deploymentNode</pre></div>\n        <div class='content'>\n            <p>Defines a deployment node - infrastructure where software runs (server, container, cloud service).</p>\n            <p><b>Syntax:</b></p>\n            <pre>[identifier =] deploymentNode name [description] [technology] [tags] [instances] {\n    deploymentNode ...\n    containerInstance ...\n}</pre>\n        </div>";
    }

    private final String generateSystemContextDoc() {
        return "        <div class='definition'><pre>systemContext</pre></div>\n        <div class='content'>\n            <p>Creates a System Context diagram showing the system and its interactions with users and other systems.</p>\n            <p><b>Syntax:</b></p>\n            <pre>systemContext softwareSystem [key] [description] {\n    include ...\n    exclude ...\n    autoLayout\n}</pre>\n            <p><b>Example:</b></p>\n            <pre>systemContext bankingSystem \"SystemContext\" \"System Context diagram\" {\n    include *\n    autoLayout\n}</pre>\n        </div>";
    }

    private final String generateSystemLandscapeDoc() {
        return "        <div class='definition'><pre>systemLandscape</pre></div>\n        <div class='content'>\n            <p>Creates a System Landscape diagram showing all software systems and people.</p>\n            <p><b>Syntax:</b></p>\n            <pre>systemLandscape [key] [description] {\n    include ...\n    autoLayout\n}</pre>\n        </div>";
    }

    private final String generateStylesDoc() {
        return "        <div class='definition'><pre>styles</pre></div>\n        <div class='content'>\n            <p>Defines visual styles for elements and relationships in diagrams.</p>\n            <p><b>Syntax:</b></p>\n            <pre>styles {\n    element \"tag\" {\n        shape ...\n        background ...\n        color ...\n    }\n    relationship \"tag\" {\n        color ...\n        thickness ...\n    }\n}</pre>\n        </div>";
    }

    private final String generateAutoLayoutDoc() {
        return "<div class='definition'><pre>autoLayout</pre></div>\n<div class='content'>\n    <p>Enables automatic layout of elements in the diagram.</p>\n    <p><b>Syntax:</b></p>\n    <pre>autoLayout [direction] [rankSeparation] [nodeSeparation]</pre>\n    <p><b>Direction:</b> tb (top-bottom), bt (bottom-top), lr (left-right), rl (right-left)</p>\n    <p><b>Example:</b></p>\n    <pre>autoLayout lr 300 200</pre>\n</div>";
    }
}
