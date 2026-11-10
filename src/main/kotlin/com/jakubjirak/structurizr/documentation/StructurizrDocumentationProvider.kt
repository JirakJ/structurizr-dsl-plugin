/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.documentation

import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.psi.PsiElement

/**
 * Provides inline documentation for Structurizr DSL keywords.
 *
 * When a user invokes quick-documentation on a keyword such as `workspace`,
 * `model`, or `views`, the provider returns an HTML snippet describing
 * the keyword's syntax and usage.
 */
class StructurizrDocumentationProvider : AbstractDocumentationProvider() {

    /** Generates HTML documentation for the given DSL keyword element. */
    override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
        val text = element?.text ?: return null
        return when (text) {
            "workspace" -> generateWorkspaceDoc()
            "model" -> generateModelDoc()
            "views" -> generateViewsDoc()
            "person" -> generatePersonDoc()
            "softwareSystem" -> generateSoftwareSystemDoc()
            "container" -> generateContainerDoc()
            "component" -> generateComponentDoc()
            "deploymentEnvironment" -> generateDeploymentEnvironmentDoc()
            "deploymentNode" -> generateDeploymentNodeDoc()
            "systemContext" -> generateSystemContextDoc()
            "systemLandscape" -> generateSystemLandscapeDoc()
            "styles" -> generateStylesDoc()
            "autoLayout" -> generateAutoLayoutDoc()
            else -> null
        }
    }

    private fun generateWorkspaceDoc(): String =
        """
        <div class='definition'><pre>workspace</pre></div>
        <div class='content'>
            <p>Defines a Structurizr workspace containing the software architecture model and views.</p>
            <p><b>Syntax:</b></p>
            <pre>workspace [name] [description] {
    model { ... }
    views { ... }
}</pre>
            <p><b>Example:</b></p>
            <pre>workspace "My System" "Description of my system" {
    model {
        // model elements
    }
    views {
        // views
    }
}</pre>
        </div>
        """.trimIndent()

    private fun generateModelDoc(): String =
        """
        <div class='definition'><pre>model</pre></div>
        <div class='content'>
            <p>Contains the model elements: people, software systems, containers, components, and relationships.</p>
            <p><b>Syntax:</b></p>
            <pre>model {
    person ...
    softwareSystem ...
    deploymentEnvironment ...
}</pre>
        </div>
        """.trimIndent()

    private fun generateViewsDoc(): String =
        """
        <div class='definition'><pre>views</pre></div>
        <div class='content'>
            <p>Contains the views that visualize the model: system context, container, component, deployment diagrams.</p>
            <p><b>Syntax:</b></p>
            <pre>views {
    systemLandscape ...
    systemContext ...
    container ...
    component ...
    deployment ...
    styles { ... }
}</pre>
        </div>
        """.trimIndent()

    private fun generatePersonDoc(): String =
        """
        <div class='definition'><pre>person</pre></div>
        <div class='content'>
            <p>Defines a person (user, actor, or role) that interacts with the software system.</p>
            <p><b>Syntax:</b></p>
            <pre>[identifier =] person name [description] [tags]</pre>
            <p><b>Example:</b></p>
            <pre>user = person "User" "A user of the system" "External"</pre>
        </div>
        """.trimIndent()

    private fun generateSoftwareSystemDoc(): String =
        """
        <div class='definition'><pre>softwareSystem</pre></div>
        <div class='content'>
            <p>Defines a software system - the highest level of abstraction in the C4 model.</p>
            <p><b>Syntax:</b></p>
            <pre>[identifier =] softwareSystem name [description] [tags] {
    container ...
}</pre>
            <p><b>Example:</b></p>
            <pre>system = softwareSystem "Banking System" "Handles banking operations" {
    webapp = container "Web App" "The web frontend" "React"
}</pre>
        </div>
        """.trimIndent()

    private fun generateContainerDoc(): String =
        """
        <div class='definition'><pre>container</pre></div>
        <div class='content'>
            <p>Defines a container - something that hosts code or data (application, database, file system, etc.).</p>
            <p><b>Syntax:</b></p>
            <pre>[identifier =] container name [description] [technology] [tags] {
    component ...
}</pre>
            <p><b>Example:</b></p>
            <pre>api = container "API" "REST API" "Java/Spring Boot" {
    controller = component "Controller" "Handles requests" "Spring MVC"
}</pre>
        </div>
        """.trimIndent()

    private fun generateComponentDoc(): String =
        """
        <div class='definition'><pre>component</pre></div>
        <div class='content'>
            <p>Defines a component - a grouping of related functionality within a container.</p>
            <p><b>Syntax:</b></p>
            <pre>[identifier =] component name [description] [technology] [tags]</pre>
            <p><b>Example:</b></p>
            <pre>service = component "User Service" "Manages users" "Spring Bean"</pre>
        </div>
        """.trimIndent()

    private fun generateDeploymentEnvironmentDoc(): String =
        """
        <div class='definition'><pre>deploymentEnvironment</pre></div>
        <div class='content'>
            <p>Defines a deployment environment (e.g., Development, Staging, Production).</p>
            <p><b>Syntax:</b></p>
            <pre>deploymentEnvironment name {
    deploymentNode ...
}</pre>
            <p><b>Example:</b></p>
            <pre>deploymentEnvironment "Production" {
    deploymentNode "AWS" {
        deploymentNode "EC2" {
            containerInstance api
        }
    }
}</pre>
        </div>
        """.trimIndent()

    private fun generateDeploymentNodeDoc(): String =
        """
        <div class='definition'><pre>deploymentNode</pre></div>
        <div class='content'>
            <p>Defines a deployment node - infrastructure where software runs (server, container, cloud service).</p>
            <p><b>Syntax:</b></p>
            <pre>[identifier =] deploymentNode name [description] [technology] [tags] [instances] {
    deploymentNode ...
    containerInstance ...
}</pre>
        </div>
        """.trimIndent()

    private fun generateSystemContextDoc(): String =
        """
        <div class='definition'><pre>systemContext</pre></div>
        <div class='content'>
            <p>Creates a System Context diagram showing the system and its interactions with users and other systems.</p>
            <p><b>Syntax:</b></p>
            <pre>systemContext softwareSystem [key] [description] {
    include ...
    exclude ...
    autoLayout
}</pre>
            <p><b>Example:</b></p>
            <pre>systemContext bankingSystem "SystemContext" "System Context diagram" {
    include *
    autoLayout
}</pre>
        </div>
        """.trimIndent()

    private fun generateSystemLandscapeDoc(): String =
        """
        <div class='definition'><pre>systemLandscape</pre></div>
        <div class='content'>
            <p>Creates a System Landscape diagram showing all software systems and people.</p>
            <p><b>Syntax:</b></p>
            <pre>systemLandscape [key] [description] {
    include ...
    autoLayout
}</pre>
        </div>
        """.trimIndent()

    private fun generateStylesDoc(): String =
        """
        <div class='definition'><pre>styles</pre></div>
        <div class='content'>
            <p>Defines visual styles for elements and relationships in diagrams.</p>
            <p><b>Syntax:</b></p>
            <pre>styles {
    element "tag" {
        shape ...
        background ...
        color ...
    }
    relationship "tag" {
        color ...
        thickness ...
    }
}</pre>
        </div>
        """.trimIndent()

    private fun generateAutoLayoutDoc(): String =
        """
        <div class='definition'><pre>autoLayout</pre></div>
        <div class='content'>
            <p>Enables automatic layout of elements in the diagram.</p>
            <p><b>Syntax:</b></p>
            <pre>autoLayout [direction] [rankSeparation] [nodeSeparation]</pre>
            <p><b>Direction:</b> tb (top-bottom), bt (bottom-top), lr (left-right), rl (right-left)</p>
            <p><b>Example:</b></p>
            <pre>autoLayout lr 300 200</pre>
        </div>
        """.trimIndent()
}
