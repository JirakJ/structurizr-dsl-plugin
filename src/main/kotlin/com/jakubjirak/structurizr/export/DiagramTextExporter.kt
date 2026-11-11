/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.export

import com.jakubjirak.structurizr.preview.dsl.DiagramBoundary
import com.jakubjirak.structurizr.preview.dsl.DslElement
import com.jakubjirak.structurizr.preview.dsl.DslElementType
import com.jakubjirak.structurizr.preview.dsl.ViewDiagram

/**
 * Converts one view (as built for the preview) into C4-PlantUML or Mermaid text.
 * Used when the Structurizr CLI is not installed; the output contains exactly the elements,
 * boundaries and (implied) relationships the preview shows for that view.
 */
object DiagramTextExporter {

    fun toPlantUml(diagram: ViewDiagram): String = buildString {
        appendLine("@startuml")
        appendLine("!include <C4/C4_Container>")
        appendLine("!include <C4/C4_Deployment>")
        appendLine("title ${plain(diagram.title)}")
        appendLine()
        val ids = ids(diagram)
        val bounded = diagram.boundaries.flatMap { it.members }.toSet()
        diagram.elements.filter { it !in bounded }.forEach { appendLine(plantUmlElement(it, ids.getValue(it))) }
        diagram.boundaries.forEachIndexed { index, boundary ->
            appendLine("""Boundary(b$index, "${plain(boundary.label)}") {""")
            boundary.members.forEach { appendLine("  " + plantUmlElement(it, ids.getValue(it))) }
            appendLine("}")
        }
        appendLine()
        diagram.edges.forEach { edge ->
            val from = ids[edge.source] ?: return@forEach
            val to = ids[edge.destination] ?: return@forEach
            val label = plain(edgeLabel(edge.order, edge.description))
            val tech = if (edge.technology.isNotBlank()) """, "${plain(edge.technology)}"""" else ""
            appendLine("""Rel($from, $to, "$label"$tech)""")
        }
        appendLine("@enduml")
    }

    fun toMermaid(diagram: ViewDiagram): String = buildString {
        appendLine("---")
        appendLine("title: ${plain(diagram.title)}")
        appendLine("---")
        appendLine("flowchart TB")
        val ids = ids(diagram)
        val bounded = diagram.boundaries.flatMap { it.members }.toSet()
        diagram.elements.filter { it !in bounded }.forEach { appendLine("    " + mermaidNode(it, ids.getValue(it))) }
        diagram.boundaries.forEachIndexed { index, boundary -> appendMermaidBoundary(this, index, boundary, ids) }
        diagram.edges.forEach { edge ->
            val from = ids[edge.source] ?: return@forEach
            val to = ids[edge.destination] ?: return@forEach
            val label = listOf(edgeLabel(edge.order, edge.description), edge.technology.takeIf { it.isNotBlank() }?.let { "[$it]" })
                .filterNot { it.isNullOrBlank() }
                .joinToString(" ")
            appendLine(if (label.isEmpty()) "    $from --> $to" else "    $from -->|\"${mermaidText(label)}\"| $to")
        }
        val people = diagram.elements.filter { it.type == DslElementType.PERSON }.map { ids.getValue(it) }
        if (people.isNotEmpty()) appendLine("    classDef person fill:#08427B,color:#fff\n    class ${people.joinToString(",")} person")
    }

    private fun appendMermaidBoundary(sb: StringBuilder, index: Int, boundary: DiagramBoundary, ids: Map<DslElement, String>) {
        sb.appendLine("""    subgraph b$index["${mermaidText(boundary.label)}"]""")
        boundary.members.forEach { sb.appendLine("        " + mermaidNode(it, ids.getValue(it))) }
        sb.appendLine("    end")
    }

    /** Stable, syntax-safe identifiers: the DSL identifier when it is a plain word, otherwise `e<uid>`. */
    private fun ids(diagram: ViewDiagram): Map<DslElement, String> {
        val all = (
            diagram.elements +
                diagram.boundaries.flatMap { it.members } +
                diagram.edges.flatMap { listOf(it.source, it.destination) }
            ).distinct()
        val used = mutableSetOf<String>()
        return all.associateWith { e ->
            val candidate = e.identifier?.takeIf { IDENTIFIER.matches(it) } ?: "e${e.uid}"
            if (used.add(candidate)) candidate else "e${e.uid}".also { used.add(it) }
        }
    }

    private fun plantUmlElement(e: DslElement, id: String): String {
        val name = plain(e.name)
        val desc = plain(e.description)
        val tech = plain(e.technology)
        return when (e.type) {
            DslElementType.PERSON -> """Person($id, "$name", "$desc")"""
            DslElementType.SOFTWARE_SYSTEM, DslElementType.SOFTWARE_SYSTEM_INSTANCE -> """System($id, "$name", "$desc")"""
            DslElementType.CONTAINER, DslElementType.CONTAINER_INSTANCE -> """Container($id, "$name", "$tech", "$desc")"""
            DslElementType.COMPONENT -> """Component($id, "$name", "$tech", "$desc")"""
            DslElementType.DEPLOYMENT_NODE, DslElementType.INFRASTRUCTURE_NODE -> """Deployment_Node($id, "$name", "$tech", "$desc")"""
        }
    }

    private fun mermaidNode(e: DslElement, id: String): String {
        val lines = listOfNotNull(
            e.name,
            "[${e.type.displayName}${if (e.technology.isNotBlank()) ": ${e.technology}" else ""}]",
            e.description.takeIf { it.isNotBlank() },
        ).joinToString("<br/>") { mermaidText(it) }
        return if (e.type == DslElementType.PERSON) """$id(["$lines"])""" else """$id["$lines"]"""
    }

    private fun edgeLabel(order: Int?, description: String): String =
        listOfNotNull(order?.let { "$it." }, description.takeIf { it.isNotBlank() }).joinToString(" ")

    /** Strips characters that would terminate a quoted PlantUML string. */
    internal fun plain(text: String): String = text.replace("\"", "'").replace("\n", " ").trim()

    /** Mermaid labels: quotes and angle brackets as entities. */
    internal fun mermaidText(text: String): String =
        text.replace("\"", "#quot;").replace("<", "#lt;").replace(">", "#gt;").replace("\n", " ").trim()

    private val IDENTIFIER = Regex("^[A-Za-z_][A-Za-z0-9_]*$")
}
