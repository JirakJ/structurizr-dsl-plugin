/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.language

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

/**
 * Provides icon constants for the Structurizr DSL plugin.
 *
 * All icons are loaded lazily from the `/icons/` resource directory and exposed as
 * `@JvmField` constants for seamless Java interoperability (e.g. in `plugin.xml`).
 */
object StructurizrIcons {

    /** Loads an SVG icon from the plugin resource path. */
    private fun load(path: String): Icon = IconLoader.getIcon(path, StructurizrIcons::class.java)

    /** Icon for generic Structurizr DSL files. */
    @JvmField val FILE: Icon = load("/icons/structurizr.svg")

    /** Icon for a workspace element. */
    @JvmField val WORKSPACE: Icon = load("/icons/workspace.svg")

    /** Icon for a model element. */
    @JvmField val MODEL: Icon = load("/icons/model.svg")

    /** Icon for a views element. */
    @JvmField val VIEWS: Icon = load("/icons/views.svg")

    /** Icon for a person element. */
    @JvmField val PERSON: Icon = load("/icons/person.svg")

    /** Icon for a software system element. */
    @JvmField val SOFTWARE_SYSTEM: Icon = load("/icons/software-system.svg")

    /** Icon for a container element. */
    @JvmField val CONTAINER: Icon = load("/icons/container.svg")

    /** Icon for a component element. */
    @JvmField val COMPONENT: Icon = load("/icons/component.svg")

    /** Icon for a relationship element. */
    @JvmField val RELATIONSHIP: Icon = load("/icons/relationship.svg")

    /** Icon for a deployment element. */
    @JvmField val DEPLOYMENT: Icon = load("/icons/deployment.svg")

    /** Icon for a styles element. */
    @JvmField val STYLES: Icon = load("/icons/styles.svg")
}
