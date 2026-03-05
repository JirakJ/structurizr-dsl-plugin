/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.preview

/**
 * Supported diagram export formats for the Structurizr CLI.
 *
 * Each entry carries the CLI argument string, a human-readable label, and the
 * file extension used when writing output.
 */
enum class ExportFormat(
    val cliFormat: String,
    val displayName: String,
    val extension: String,
) {
    PNG("png", "PNG Image", "png"),
    SVG("svg", "SVG Vector", "svg"),
    PLANTUML("plantuml", "PlantUML", "puml"),
    PLANTUML_C4("plantuml/c4plantuml", "PlantUML (C4)", "puml"),
    MERMAID("mermaid", "Mermaid", "mmd"),
    DOT("dot", "Graphviz DOT", "dot"),
    ILOGRAPH("ilograph", "Ilograph", "yaml"),
    WEBSEQUENCEDIAGRAMS("websequencediagrams", "WebSequenceDiagrams", "wsd"),
    JSON("json", "JSON", "json"),
}
