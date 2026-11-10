/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.export

/**
 * Supported diagram export formats with display metadata.
 */
enum class DiagramExportFormat(
    val displayName: String,
    val extension: String,
    val description: String
) {
    SVG("SVG", "svg", "Scalable Vector Graphics - best for web and documentation"),
    PNG("PNG", "png", "Portable Network Graphics - best for presentations"),
    PLANTUML("PlantUML", "puml", "PlantUML format - can be rendered by PlantUML tools"),
    MERMAID("Mermaid", "mmd", "Mermaid format - can be rendered in Markdown"),
    DOT("Graphviz DOT", "dot", "DOT format - can be rendered by Graphviz"),
    JSON("JSON", "json", "Structurizr JSON format - for programmatic access")
}
