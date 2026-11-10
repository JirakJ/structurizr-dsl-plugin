/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.highlighting

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.jakubjirak.structurizr.language.StructurizrIcons
import javax.swing.Icon

/**
 * Colour-settings page for the Structurizr DSL language, shown under
 * *Settings → Editor → Color Scheme → Structurizr DSL*.
 *
 * Allows users to customise the colours used for every syntactic element
 * in the Structurizr DSL editor.
 */
class StructurizrColorSettingsPage : ColorSettingsPage {

    /** Returns the Structurizr DSL file icon. */
    override fun getIcon(): Icon = StructurizrIcons.FILE

    /** Returns a [StructurizrSyntaxHighlighter] for the preview pane. */
    override fun getHighlighter(): SyntaxHighlighter = StructurizrSyntaxHighlighter()

    /** Returns the demo text displayed in the colour-settings preview pane. */
    override fun getDemoText(): String = DEMO_TEXT

    /** No additional highlighting tags are needed. */
    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null

    /** Returns the attribute descriptors for all configurable text attributes. */
    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    /** No custom colour descriptors are needed. */
    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    /** Returns the display name shown in the settings tree. */
    override fun getDisplayName(): String = "Structurizr DSL"

    companion object {
        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Keywords//Core", StructurizrSyntaxHighlighter.KEYWORD),
            AttributesDescriptor("Keywords//Element", StructurizrSyntaxHighlighter.ELEMENT_KEYWORD),
            AttributesDescriptor("Keywords//Deployment", StructurizrSyntaxHighlighter.DEPLOYMENT_KEYWORD),
            AttributesDescriptor("Keywords//View", StructurizrSyntaxHighlighter.VIEW_KEYWORD),
            AttributesDescriptor("Keywords//View content", StructurizrSyntaxHighlighter.VIEW_CONTENT_KEYWORD),
            AttributesDescriptor("Keywords//Style", StructurizrSyntaxHighlighter.STYLE_KEYWORD),
            AttributesDescriptor("Properties", StructurizrSyntaxHighlighter.PROPERTY),
            AttributesDescriptor("Directives", StructurizrSyntaxHighlighter.DIRECTIVE),
            AttributesDescriptor("String", StructurizrSyntaxHighlighter.STRING),
            AttributesDescriptor("Number", StructurizrSyntaxHighlighter.NUMBER),
            AttributesDescriptor("Color literal", StructurizrSyntaxHighlighter.COLOR),
            AttributesDescriptor("Identifier", StructurizrSyntaxHighlighter.IDENTIFIER),
            AttributesDescriptor("Variable (reference)", StructurizrAnnotator.VARIABLE),
            AttributesDescriptor("Comment", StructurizrSyntaxHighlighter.COMMENT),
            AttributesDescriptor("Operator", StructurizrSyntaxHighlighter.OPERATOR),
            AttributesDescriptor("Arrow (->)", StructurizrSyntaxHighlighter.ARROW),
            AttributesDescriptor("Braces", StructurizrSyntaxHighlighter.BRACES),
            AttributesDescriptor("Variable reference (\${...})", StructurizrAnnotator.VARIABLE_REFERENCE),
            AttributesDescriptor("Bad character", StructurizrSyntaxHighlighter.BAD_CHARACTER),
        )

        @Suppress("MaxLineLength")
        private const val DEMO_TEXT = """// Structurizr DSL example
/* This is a block comment */

!const SYSTEM_NAME "My System"
!identifiers hierarchical

workspace "Big Bank plc" "Internet Banking System" {
    
    model {
        enterprise "Big Bank plc" {
            user = person "Personal Banking Customer" "A customer of the bank" "Customer"
            
            softwareSystem = softwareSystem "Internet Banking System" "Allows customers to manage accounts" {
                webApp = container "Web Application" "Delivers static content and SPA" "Java/Spring Boot" "Web Browser"
                apiApp = container "API Application" "Provides banking functionality via REST API" "Java/Spring Boot"
                database = container "Database" "Stores user information" "PostgreSQL" "Database"
                
                webApp -> apiApp "Makes API calls" "HTTPS/JSON"
                apiApp -> database "Reads from and writes to" "JDBC"
            }
        }
        
        user -> softwareSystem "Views account balances"
    }
    
    views {
        systemContext softwareSystem "SystemContext" "System Context diagram" {
            include *
            autoLayout lr 300 200
        }
        
        container softwareSystem "Containers" {
            include *
            autoLayout
        }
        
        styles {
            element "Person" {
                shape Person
                background #08427B
                color #ffffff
                fontSize 22
            }
            
            element "Software System" {
                background #1168BD
                color #ffffff
            }
            
            element "Container" {
                background #438DD5
                color #ffffff
            }
            
            element "Database" {
                shape Cylinder
            }
            
            relationship "Relationship" {
                thickness 2
                dashed false
            }
        }
        
        themes https://static.structurizr.com/themes/default/theme.json
    }
    
    configuration {
        users {
            "user@example.com" read
        }
    }
}"""
    }
}
