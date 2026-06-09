/*
 * Copyright 2026 Jakub Jirák. All rights reserved.
 * Licensed under the proprietary license. See LICENSE for details.
 */
package com.jakubjirak.structurizr.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.PrioritizedLookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import com.jakubjirak.structurizr.language.StructurizrIcons
import com.jakubjirak.structurizr.language.psi.StructurizrRelationshipStatement
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import javax.swing.Icon

/**
 * Provides context-aware code completions for Structurizr DSL files, including
 * keywords, model elements, deployment constructs, views, styles, live templates,
 * identifier references, tags, and contextual style properties.
 */
class StructurizrCompletionProvider : CompletionProvider<CompletionParameters>() {

    /** @see CompletionProvider.addCompletions */
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        // FR-306: when completing a relationship target, defined elements are the only
        // valid input — suggest them with high priority and skip the keyword noise
        if (addRelationshipTargetCompletions(parameters, result)) return

        CORE_KEYWORDS.forEach { keyword ->
            result.addElement(
                LookupElementBuilder.create(keyword)
                    .withIcon(StructurizrIcons.WORKSPACE)
                    .withTypeText("keyword")
                    .withBoldness(true)
            )
        }

        MODEL_ELEMENTS.forEach { (keyword, description) ->
            result.addElement(
                LookupElementBuilder.create(keyword)
                    .withIcon(getIconForElement(keyword))
                    .withTypeText(description)
                    .withBoldness(true)
            )
        }

        DEPLOYMENT_KEYWORDS.forEach { keyword ->
            result.addElement(
                LookupElementBuilder.create(keyword)
                    .withIcon(StructurizrIcons.DEPLOYMENT)
                    .withTypeText("deployment")
            )
        }

        VIEW_KEYWORDS.forEach { keyword ->
            result.addElement(
                LookupElementBuilder.create(keyword)
                    .withIcon(StructurizrIcons.VIEWS)
                    .withTypeText("view")
            )
        }

        STYLE_KEYWORDS.forEach { keyword ->
            result.addElement(
                LookupElementBuilder.create(keyword)
                    .withIcon(StructurizrIcons.STYLES)
                    .withTypeText("style")
            )
        }

        STYLE_PROPERTIES.forEach { property ->
            result.addElement(
                LookupElementBuilder.create(property)
                    .withTypeText("property")
            )
        }

        SHAPES.forEach { shape ->
            result.addElement(
                LookupElementBuilder.create(shape)
                    .withTypeText("shape")
            )
        }

        LAYOUT_DIRECTIONS.forEach { direction ->
            result.addElement(
                LookupElementBuilder.create(direction)
                    .withTypeText("layout direction")
            )
        }

        DIRECTIVES.forEach { directive ->
            result.addElement(
                LookupElementBuilder.create(directive)
                    .withTypeText("directive")
                    .withBoldness(true)
            )
        }

        addLiveTemplates(result)
        addIdentifierCompletions(parameters, result)
        addTagCompletions(parameters, result)
        addContextualStyleCompletions(parameters, result)

        OTHER_KEYWORDS.forEach { keyword ->
            result.addElement(
                LookupElementBuilder.create(keyword)
                    .withIcon(StructurizrIcons.WORKSPACE)
                    .withTypeText("keyword")
            )
        }
    }

    private fun getIconForElement(keyword: String): Icon = when (keyword) {
        "person" -> StructurizrIcons.PERSON
        "softwareSystem" -> StructurizrIcons.SOFTWARE_SYSTEM
        "container" -> StructurizrIcons.CONTAINER
        "component" -> StructurizrIcons.COMPONENT
        "model" -> StructurizrIcons.MODEL
        "views" -> StructurizrIcons.VIEWS
        else -> StructurizrIcons.WORKSPACE
    }

    private fun addLiveTemplates(result: CompletionResultSet) {
        result.addElement(
            LookupElementBuilder.create("workspace_template")
                .withPresentableText("workspace { ... }")
                .withInsertHandler { context, _ ->
                    val template = "workspace \"Name\" \"Description\" {\n    \n    model {\n        \n    }\n    \n    views {\n        \n    }\n}"
                    context.document.replaceString(context.startOffset, context.tailOffset, template)
                }
                .withTypeText("template")
        )
        result.addElement(
            LookupElementBuilder.create("person_template")
                .withPresentableText("person \"Name\" \"Description\"")
                .withInsertHandler { context, _ ->
                    val template = "person \"Name\" \"Description\""
                    context.document.replaceString(context.startOffset, context.tailOffset, template)
                }
                .withTypeText("template")
        )
        result.addElement(
            LookupElementBuilder.create("softwareSystem_template")
                .withPresentableText("softwareSystem \"Name\" { ... }")
                .withInsertHandler { context, _ ->
                    val template = "softwareSystem \"Name\" \"Description\" {\n    \n}"
                    context.document.replaceString(context.startOffset, context.tailOffset, template)
                }
                .withTypeText("template")
        )
    }

    /**
     * Adds smart completions for relationship targets (FR-306).
     *
     * When the caret sits after an arrow (`->`, `<-`, `<->`) inside a relationship
     * statement, only defined element identifiers are suggested — excluding the
     * relationship's own source to avoid proposing an invalid self-reference.
     *
     * @return true when target completions were added and other suggestions should be suppressed
     */
    private fun addRelationshipTargetCompletions(
        parameters: CompletionParameters,
        result: CompletionResultSet
    ): Boolean {
        val position = parameters.position
        if (position.node.elementType == StructurizrTypes.STRING) return false

        var statement: PsiElement? = position.parent
        while (statement != null && statement !is StructurizrRelationshipStatement) {
            statement = statement.parent
        }
        if (statement !is StructurizrRelationshipStatement) return false

        // Only the target side (after the arrow) is restricted to identifiers
        val arrowBeforeCaret = generateSequence(statement.firstChild) { it.nextSibling }.any {
            it.node.elementType in ARROW_TOKENS && it.textOffset < position.textOffset
        }
        if (!arrowBeforeCaret) return false

        val sourceName = statement.elementReferenceList.firstOrNull()?.text

        val assignments = mutableListOf<PsiElement>()
        collectAssignments(parameters.originalFile, assignments)

        var added = false
        assignments.forEach { assignment ->
            val name = assignment.node.findChildByType(StructurizrTypes.IDENTIFIER)?.text ?: return@forEach
            if (name.isEmpty() || name == sourceName) return@forEach

            result.addElement(
                PrioritizedLookupElement.withPriority(
                    LookupElementBuilder.create(name)
                        .withIcon(getAssignmentIcon(assignment))
                        .withTypeText(getAssignmentTypeText(assignment)),
                    RELATIONSHIP_TARGET_PRIORITY
                )
            )
            added = true
        }
        return added
    }

    private fun addIdentifierCompletions(parameters: CompletionParameters, result: CompletionResultSet) {
        val file = parameters.originalFile
        val assignments = mutableListOf<PsiElement>()
        collectAssignments(file, assignments)

        assignments.forEach { assignment ->
            val idNode = assignment.node.findChildByType(StructurizrTypes.IDENTIFIER) ?: return@forEach
            val name = idNode.text
            if (name.isNullOrEmpty()) return@forEach

            val typeText = getAssignmentTypeText(assignment)
            val icon = getAssignmentIcon(assignment)
            result.addElement(
                LookupElementBuilder.create(name)
                    .withIcon(icon)
                    .withTypeText(typeText)
                    .withBoldness(false)
            )
        }
    }

    private fun addTagCompletions(parameters: CompletionParameters, result: CompletionResultSet) {
        val position = parameters.position
        var inTagsContext = position.parent?.let { parent ->
            val parentType = parent.node.elementType
            parentType == StructurizrTypes.TAGS_STATEMENT || parentType == StructurizrTypes.TAGS_STRING
        } ?: false

        if (!inTagsContext) {
            var prev = position.prevSibling
            while (prev != null && prev.text.trim().isEmpty()) {
                prev = prev.prevSibling
            }
            if (prev?.node?.elementType == StructurizrTypes.TAGS) {
                inTagsContext = true
            }
        }

        if (!inTagsContext) return

        val file = parameters.originalFile
        val tags = linkedSetOf<String>()
        collectTags(file, tags)

        tags.forEach { tag ->
            result.addElement(
                LookupElementBuilder.create(tag)
                    .withIcon(StructurizrIcons.STYLES)
                    .withTypeText("Tag")
            )
        }
    }

    private fun addContextualStyleCompletions(parameters: CompletionParameters, result: CompletionResultSet) {
        val position = parameters.position
        var inElementStyle = false
        var inRelationshipStyle = false
        var afterShapeKeyword = false
        var afterBorderKeyword = false
        var afterRoutingKeyword = false

        // Walk up to find if we're inside an element style or relationship style block
        var current: PsiElement? = position.parent
        while (current != null) {
            when (current.node.elementType) {
                StructurizrTypes.ELEMENT_STYLE -> {
                    inElementStyle = true
                    break
                }
                StructurizrTypes.RELATIONSHIP_STYLE -> {
                    inRelationshipStyle = true
                    break
                }
            }
            current = current.parent
        }

        // Check if previous non-whitespace sibling is a shape/border/routing keyword
        var prev = position.prevSibling
        while (prev != null && prev.text.trim().isEmpty()) {
            prev = prev.prevSibling
        }
        prev?.let {
            when (it.node.elementType) {
                StructurizrTypes.SHAPE -> afterShapeKeyword = true
                StructurizrTypes.BORDER -> afterBorderKeyword = true
                StructurizrTypes.ROUTING -> afterRoutingKeyword = true
            }
            val prevText = it.text.trim()
            if (prevText == "shape") afterShapeKeyword = true
            if (prevText == "border") afterBorderKeyword = true
            if (prevText == "routing") afterRoutingKeyword = true
        }

        when {
            afterShapeKeyword -> {
                SHAPES.forEach { shape ->
                    result.addElement(
                        LookupElementBuilder.create(shape)
                            .withTypeText("shape value")
                            .withBoldness(true)
                    )
                }
                return
            }
            afterBorderKeyword -> {
                BORDER_VALUES.forEach { border ->
                    result.addElement(
                        LookupElementBuilder.create(border)
                            .withTypeText("border value")
                            .withBoldness(true)
                    )
                }
                return
            }
            afterRoutingKeyword -> {
                ROUTING_VALUES.forEach { routing ->
                    result.addElement(
                        LookupElementBuilder.create(routing)
                            .withTypeText("routing value")
                            .withBoldness(true)
                    )
                }
                return
            }
        }

        if (inElementStyle) {
            ELEMENT_STYLE_PROPERTIES.forEach { prop ->
                result.addElement(
                    LookupElementBuilder.create(prop)
                        .withIcon(StructurizrIcons.STYLES)
                        .withTypeText("element style property")
                )
            }
        }

        if (inRelationshipStyle) {
            RELATIONSHIP_STYLE_PROPERTIES.forEach { prop ->
                result.addElement(
                    LookupElementBuilder.create(prop)
                        .withIcon(StructurizrIcons.STYLES)
                        .withTypeText("relationship style property")
                )
            }
        }
    }

    companion object {
        /** Lookup priority for relationship target suggestions (FR-306). */
        const val RELATIONSHIP_TARGET_PRIORITY: Double = 100.0

        /** Token types that form a relationship arrow. */
        val ARROW_TOKENS = setOf(
            StructurizrTypes.ARROW, StructurizrTypes.ARROW_BOTH, StructurizrTypes.ARROW_LEFT
        )

        /** Core Structurizr DSL keywords. */
        val CORE_KEYWORDS: List<String> = listOf(
            "workspace", "model", "views", "configuration", "archetypes"
        )

        /** Model element keywords mapped to their descriptions. */
        val MODEL_ELEMENTS: Map<String, String> = mapOf(
            "person" to "model element",
            "softwareSystem" to "model element",
            "container" to "model element",
            "component" to "model element",
            "group" to "grouping",
            "enterprise" to "boundary",
            "element" to "model element"
        )

        /** Deployment-related keywords. */
        val DEPLOYMENT_KEYWORDS: List<String> = listOf(
            "deploymentEnvironment", "deploymentGroup", "deploymentNode",
            "infrastructureNode", "softwareSystemInstance", "containerInstance",
            "instanceOf", "healthCheck"
        )

        /** View-related keywords. */
        val VIEW_KEYWORDS: List<String> = listOf(
            "systemLandscape", "systemContext", "container", "component",
            "dynamic", "deployment", "filtered", "custom", "image"
        )

        /** Style-related keywords. */
        val STYLE_KEYWORDS: List<String> = listOf(
            "styles", "element", "relationship", "themes", "theme",
            "branding", "terminology"
        )

        /** Style property names. */
        val STYLE_PROPERTIES: List<String> = listOf(
            "shape", "icon", "width", "height", "background", "color",
            "colour", "stroke", "strokeWidth", "fontSize", "border",
            "opacity", "metadata", "thickness", "style", "routing",
            "position", "dashed"
        )

        /** Available shape values. */
        val SHAPES: List<String> = listOf(
            "Box", "RoundedBox", "Circle", "Ellipse", "Hexagon", "Cylinder",
            "Pipe", "Person", "Robot", "Folder", "WebBrowser",
            "MobileDevicePortrait", "MobileDeviceLandscape", "Component"
        )

        /** Auto-layout direction values. */
        val LAYOUT_DIRECTIONS: List<String> = listOf("tb", "bt", "lr", "rl")

        /** Structurizr DSL directives. */
        val DIRECTIVES: List<String> = listOf(
            "!include", "!docs", "!adrs", "!identifiers",
            "!impliedRelationships", "!constant", "!const", "!var",
            "!plugin", "!script", "!components", "!extend", "!ref",
            "!element", "!elements", "!relationship", "!relationships"
        )

        /** Miscellaneous keywords. */
        val OTHER_KEYWORDS: List<String> = listOf(
            "include", "exclude", "autoLayout", "default", "animation",
            "title", "description", "tag", "tags", "technology", "instances",
            "url", "properties", "perspectives", "users", "extends", "ref",
            "this", "true", "false", "read", "write", "private", "public",
            "hierarchical", "flat", "light", "dark"
        )

        /** Style properties for element styles. */
        val ELEMENT_STYLE_PROPERTIES: List<String> = listOf(
            "shape", "icon", "width", "height", "background", "color",
            "colour", "stroke", "strokeWidth", "fontSize", "border",
            "opacity", "metadata", "description"
        )

        /** Style properties for relationship styles. */
        val RELATIONSHIP_STYLE_PROPERTIES: List<String> = listOf(
            "thickness", "color", "colour", "dashed", "routing",
            "fontSize", "width", "position", "opacity"
        )

        /** Border style values. */
        val BORDER_VALUES: List<String> = listOf("solid", "dashed", "dotted")

        /** Routing strategy values. */
        val ROUTING_VALUES: List<String> = listOf("Direct", "Orthogonal", "Curved")

        private fun collectAssignments(element: PsiElement, result: MutableList<PsiElement>) {
            element.children.forEach { child ->
                if (child.node.elementType == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                    result.add(child)
                }
                collectAssignments(child, result)
            }
        }

        private fun getAssignmentTypeText(assignment: PsiElement): String {
            assignment.children.forEach { child ->
                when (child.node.elementType) {
                    StructurizrTypes.PERSON_DEFINITION -> return "Person"
                    StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION -> return "Software System"
                    StructurizrTypes.CONTAINER_DEFINITION -> return "Container"
                    StructurizrTypes.COMPONENT_DEFINITION -> return "Component"
                    StructurizrTypes.DEPLOYMENT_NODE_DEFINITION -> return "Deployment Node"
                    StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION -> return "Infrastructure Node"
                }
            }
            return "element"
        }

        private fun getAssignmentIcon(assignment: PsiElement): Icon {
            assignment.children.forEach { child ->
                when (child.node.elementType) {
                    StructurizrTypes.PERSON_DEFINITION -> return StructurizrIcons.PERSON
                    StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION -> return StructurizrIcons.SOFTWARE_SYSTEM
                    StructurizrTypes.CONTAINER_DEFINITION -> return StructurizrIcons.CONTAINER
                    StructurizrTypes.COMPONENT_DEFINITION -> return StructurizrIcons.COMPONENT
                    StructurizrTypes.DEPLOYMENT_NODE_DEFINITION -> return StructurizrIcons.DEPLOYMENT
                    StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION -> return StructurizrIcons.DEPLOYMENT
                }
            }
            return StructurizrIcons.WORKSPACE
        }

        private fun collectTags(element: PsiElement, tags: MutableSet<String>) {
            element.children.forEach { child ->
                val type = child.node.elementType
                if (type == StructurizrTypes.TAGS_STATEMENT || type == StructurizrTypes.TAGS_STRING) {
                    var text = child.text
                    if (text.startsWith("tags")) {
                        text = text.substring(4).trim()
                    }
                    text.split(Regex("[,\\s]+")).forEach { part ->
                        val tag = part.replace("\"", "").trim()
                        if (tag.isNotEmpty()) {
                            tags.add(tag)
                        }
                    }
                }
                collectTags(child, tags)
            }
        }
    }
}
