/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview.dsl

/**
 * Lenient Structurizr DSL reader used by the diagram preview and exports.
 *
 * It is deliberately independent of the PSI parser: the preview has to keep working on files the
 * editor grammar does not fully understand, so this reader tokenizes the way Structurizr itself does
 * (whitespace-separated tokens, quoted strings, one statement per line, `{ }` blocks) and skips any
 * statement it does not recognise instead of failing.
 *
 * @param includeResolver loads the text of an `!include` target relative to the file being read,
 *                        or returns `null` when it cannot be resolved
 */
class DslWorkspaceParser(private val includeResolver: (String) -> String? = { null }) {

    fun parse(text: String): DslWorkspace {
        val workspace = DslWorkspace()
        val state = State(workspace)
        val statements = readStatements(text)
        val root = statements.firstOrNull { it.keyword == "workspace" }
        if (root != null) {
            val strings = root.args.drop(1).filter { it.quoted }
            strings.getOrNull(0)?.let { workspace.name = it.text }
            strings.getOrNull(1)?.let { workspace.description = it.text }
            workspaceBody(root.children, state)
        } else {
            // A fragment pulled in by !include, or a file being written: read whatever is there.
            workspaceBody(statements, state)
        }
        resolveDeferred(state)
        if (workspace.views.isEmpty()) addGeneratedViews(workspace)
        return workspace
    }

    // ── Tokenizing ───────────────────────────────────────────────────────

    internal data class Token(val text: String, val quoted: Boolean = false) {
        val isArrow: Boolean get() = !quoted && text == "->"
        val lower: String get() = text.lowercase()
    }

    internal class Statement(val args: List<Token>, val children: List<Statement>, val hasBlock: Boolean) {
        val keyword: String? get() = args.firstOrNull()?.takeIf { !it.quoted }?.lower
    }

    private sealed interface Lexeme {
        data class Word(val token: Token) : Lexeme
        data object Newline : Lexeme
        data object Open : Lexeme
        data object Close : Lexeme
    }

    private fun lex(text: String): List<Lexeme> {
        val out = mutableListOf<Lexeme>()
        var i = 0
        var lineStart = true
        val n = text.length
        while (i < n) {
            val c = text[i]
            when {
                c == '\n' -> { out += Lexeme.Newline; i++; lineStart = true; continue }
                c == '\\' && text.startsWith("\n", i + 1) -> { i += 2; continue } // line continuation
                c == '\\' && text.startsWith("\r\n", i + 1) -> { i += 3; continue }
                c.isWhitespace() -> { i++; continue }
                c == '/' && text.startsWith("/*", i) -> {
                    val end = text.indexOf("*/", i + 2).let { if (it < 0) n else it + 2 }
                    text.substring(i, end).count { it == '\n' }.let { repeat(it) { out += Lexeme.Newline } }
                    i = end
                    continue
                }
                c == '/' && text.startsWith("//", i) || (c == '#' && lineStart) -> {
                    while (i < n && text[i] != '\n') i++
                    continue
                }
                c == '{' -> { out += Lexeme.Open; i++ }
                c == '}' -> { out += Lexeme.Close; i++ }
                c == '"' && text.startsWith("\"\"\"", i) -> {
                    val end = text.indexOf("\"\"\"", i + 3).let { if (it < 0) n else it }
                    out += Lexeme.Word(Token(text.substring(i + 3, end).trimIndent().trim(), quoted = true))
                    i = minOf(n, end + 3)
                }
                c == '"' -> {
                    val sb = StringBuilder()
                    i++
                    while (i < n && text[i] != '"' && text[i] != '\n') {
                        if (text[i] == '\\' && i + 1 < n && text[i + 1] == '"') { sb.append('"'); i += 2; continue }
                        sb.append(text[i]); i++
                    }
                    if (i < n && text[i] == '"') i++
                    out += Lexeme.Word(Token(sb.toString(), quoted = true))
                }
                else -> {
                    val start = i
                    while (i < n && !text[i].isWhitespace() && text[i] != '{' && text[i] != '}' && text[i] != '"') i++
                    out += Lexeme.Word(Token(text.substring(start, i)))
                }
            }
            lineStart = false
        }
        return out
    }

    internal fun readStatements(text: String): List<Statement> {
        val lexemes = lex(text)
        var pos = 0

        fun block(): List<Statement> {
            val result = mutableListOf<Statement>()
            var current = mutableListOf<Token>()
            fun flush(children: List<Statement> = emptyList(), hasBlock: Boolean = false) {
                if (current.isNotEmpty() || hasBlock) result += Statement(current, children, hasBlock)
                current = mutableListOf()
            }
            while (pos < lexemes.size) {
                when (val lx = lexemes[pos++]) {
                    is Lexeme.Word -> current += lx.token
                    Lexeme.Newline -> flush()
                    Lexeme.Open -> flush(block(), hasBlock = true)
                    Lexeme.Close -> { flush(); return result }
                }
            }
            flush()
            return result
        }
        return block()
    }

    // ── Interpretation ───────────────────────────────────────────────────

    private class State(val workspace: DslWorkspace) {
        var hierarchical = false
        var includeDepth = 0

        /** Directory of the file being read, relative to the workspace file ("" for the workspace itself). */
        var includeDir = ""
        val pendingRelationships = mutableListOf<PendingRelationship>()
        val viewCounters = mutableMapOf<DslViewType, Int>()
        var nextUid = 0
    }

    private class PendingRelationship(
        val source: Any, // DslElement or identifier String
        val destination: String,
        val description: String,
        val technology: String,
        val tags: List<String>,
    )

    private fun include(statement: Statement, state: State, body: (List<Statement>) -> Unit) {
        val target = statement.args.getOrNull(1)?.text ?: return
        if (state.includeDepth >= MAX_INCLUDE_DEPTH) return
        // Structurizr resolves an !include relative to the file that contains it, not to the workspace.
        val path = if (target.contains("://")) target else normalizePath(state.includeDir + target)
        val text = runCatching { includeResolver(path) }.getOrNull() ?: return
        val outerDir = state.includeDir
        state.includeDir = if (path.endsWith(".dsl", ignoreCase = true)) {
            path.substringBeforeLast('/', "").let { if (it.isEmpty()) "" else "$it/" }
        } else {
            "$path/"
        }
        state.includeDepth++
        try {
            val statements = readStatements(text)
            // An included file may itself be a complete workspace; read its body in that case.
            body(statements.firstOrNull { it.keyword == "workspace" }?.children ?: statements)
        } finally {
            state.includeDepth--
            state.includeDir = outerDir
        }
    }

    private fun normalizePath(path: String): String {
        val parts = ArrayDeque<String>()
        for (part in path.split('/')) {
            when (part) {
                "", "." -> Unit
                ".." -> if (parts.isNotEmpty() && parts.last() != "..") parts.removeLast() else parts.addLast("..")
                else -> parts.addLast(part)
            }
        }
        return parts.joinToString("/")
    }

    private fun workspaceBody(statements: List<Statement>, state: State) {
        for (st in statements) {
            when (st.keyword) {
                "name" -> st.args.getOrNull(1)?.let { state.workspace.name = it.text }
                "description" -> st.args.getOrNull(1)?.let { state.workspace.description = it.text }
                "model" -> modelBody(st.children, state, parent = null, environment = null)
                "views" -> viewsBody(st.children, state)
                "!identifiers" -> state.hierarchical = st.args.getOrNull(1)?.lower == "hierarchical"
                "!include" -> include(st, state) { workspaceBody(it, state) }
                else -> Unit
            }
        }
    }

    private fun register(state: State, element: DslElement, identifier: String?) {
        state.workspace.elements += element
        if (identifier.isNullOrEmpty()) return
        val ids = state.workspace.identifiers
        val key = identifier.lowercase()
        val parentPath = element.parent?.let { qualifiedId(it) }
        if (state.hierarchical && parentPath != null) {
            ids["$parentPath.$key"] = element
            ids.putIfAbsent(key, element)
        } else {
            ids[key] = element
        }
    }

    private fun qualifiedId(element: DslElement): String? {
        val parts = element.selfAndAncestors.map { it.identifier?.lowercase() }.toList()
        return if (parts.any { it == null }) element.identifier?.lowercase() else parts.reversed().joinToString(".")
    }

    private fun strings(args: List<Token>): List<String> = args.map { it.text }

    private fun modelBody(statements: List<Statement>, state: State, parent: DslElement?, environment: String?) {
        for (st in statements) {
            var args = st.args
            var identifier: String? = null
            if (args.size >= 2 && !args[0].quoted && args[1].text == "=" && !args[1].quoted) {
                identifier = args[0].text
                args = args.drop(2)
            }
            val arrow = args.indexOfFirst { it.isArrow }
            if (arrow >= 0) {
                relationship(args, arrow, parent, state)
                continue
            }
            val keyword = args.firstOrNull()?.takeIf { !it.quoted }?.lower ?: continue
            val rest = strings(args.drop(1))
            fun element(type: DslElementType, technologyIndex: Int?, tagsIndex: Int): DslElement {
                val element = DslElement(
                    uid = state.nextUid++,
                    identifier = identifier,
                    type = type,
                    name = rest.getOrElse(0) { type.displayName },
                    description = rest.getOrElse(1) { "" },
                    technology = technologyIndex?.let { rest.getOrNull(it) } ?: "",
                    parent = parent,
                    environment = environment,
                )
                rest.getOrNull(tagsIndex)?.let { addTags(element.tags, it) }
                register(state, element, identifier)
                return element
            }
            when (keyword) {
                "person" -> element(DslElementType.PERSON, null, 2).also { elementBody(st.children, state, it) }
                "softwaresystem" -> element(DslElementType.SOFTWARE_SYSTEM, null, 2).also { elementBody(st.children, state, it) }
                "container" -> element(DslElementType.CONTAINER, 2, 3).also { elementBody(st.children, state, it) }
                "component" -> element(DslElementType.COMPONENT, 2, 3).also { elementBody(st.children, state, it) }
                "group", "enterprise" -> modelBody(st.children, state, parent, environment)
                "deploymentenvironment" -> {
                    val name = rest.getOrElse(0) { "Default" }
                    identifier?.let { state.workspace.environmentIdentifiers[it.lowercase()] = name }
                    modelBody(st.children, state, parent = null, environment = name)
                }
                "deploymentgroup" -> Unit
                "deploymentnode" -> element(DslElementType.DEPLOYMENT_NODE, 2, 4).also { elementBody(st.children, state, it) }
                "infrastructurenode" -> element(DslElementType.INFRASTRUCTURE_NODE, 2, 3).also { elementBody(st.children, state, it) }
                "softwaresysteminstance", "containerinstance" -> {
                    val type = if (keyword == "containerinstance") {
                        DslElementType.CONTAINER_INSTANCE
                    } else {
                        DslElementType.SOFTWARE_SYSTEM_INSTANCE
                    }
                    val instance = DslElement(
                        uid = state.nextUid++,
                        identifier = identifier,
                        type = type,
                        name = rest.getOrElse(0) { type.displayName },
                        parent = parent,
                        environment = environment,
                        instanceOf = rest.getOrNull(0),
                    )
                    register(state, instance, identifier)
                    elementBody(st.children, state, instance)
                }
                "!ref", "!element", "!extend" -> {
                    val target = state.workspace.resolve(rest.getOrNull(0))
                    if (target != null) elementBody(st.children, state, target)
                }
                "!identifiers" -> state.hierarchical = rest.getOrNull(0)?.lowercase() == "hierarchical"
                "!include" -> include(st, state) { modelBody(it, state, parent, environment) }
                else -> if (parent != null) elementProperty(keyword, rest, parent)
            }
        }
    }

    /** Body of an element: nested elements, relationships from `this`, and properties. */
    private fun elementBody(statements: List<Statement>, state: State, element: DslElement) {
        if (statements.isEmpty()) return
        modelBody(statements, state, element, element.environment)
    }

    private fun elementProperty(keyword: String, values: List<String>, element: DslElement) {
        when (keyword) {
            "description" -> values.firstOrNull()?.let { element.description = it }
            "technology" -> values.firstOrNull()?.let { element.technology = it }
            "tag", "tags" -> values.forEach { addTags(element.tags, it) }
            else -> Unit
        }
    }

    private fun relationship(args: List<Token>, arrow: Int, current: DslElement?, state: State) {
        val sourceText = args.take(arrow).lastOrNull()?.text
        val source: Any = when {
            sourceText == null || sourceText.equals("this", ignoreCase = true) -> current ?: return
            else -> sourceText
        }
        val after = strings(args.drop(arrow + 1))
        val destination = after.firstOrNull() ?: return
        state.pendingRelationships += PendingRelationship(
            source = source,
            destination = if (destination.equals("this", ignoreCase = true) && current != null) "\u0000${current.uid}" else destination,
            description = after.getOrElse(1) { "" },
            technology = after.getOrElse(2) { "" },
            tags = after.getOrNull(3)?.let { listOf(it) } ?: emptyList(),
        )
    }

    private fun resolveDeferred(state: State) {
        val ws = state.workspace
        for (instance in ws.elements) {
            val target = ws.resolve(instance.instanceOf) ?: continue
            instance.name = target.name
            instance.description = target.description
            instance.technology = target.technology
        }
        for (p in state.pendingRelationships) {
            val source = (p.source as? DslElement) ?: ws.resolve(p.source as String) ?: continue
            val destination = if (p.destination.startsWith("\u0000")) {
                ws.elements.firstOrNull { it.uid == p.destination.substring(1).toInt() }
            } else {
                ws.resolve(p.destination)
            } ?: continue
            ws.relationships += DslRelationship(source, destination, p.description, p.technology).also { r ->
                p.tags.forEach { addTags(r.tags, it) }
            }
        }
    }

    private fun addTags(target: MutableSet<String>, raw: String) {
        raw.split(',').map { it.trim() }.filter { it.isNotEmpty() }.forEach { target += it }
    }

    // ── Views ────────────────────────────────────────────────────────────

    private fun viewsBody(statements: List<Statement>, state: State) {
        for (st in statements) {
            val keyword = st.keyword ?: continue
            val rest = st.args.drop(1).map { it.text }
            val view: DslView = when (keyword) {
                "systemlandscape" -> newView(state, DslViewType.SYSTEM_LANDSCAPE, null, null, rest.getOrNull(0), rest.getOrNull(1))
                "systemcontext" -> newView(state, DslViewType.SYSTEM_CONTEXT, rest.getOrNull(0), null, rest.getOrNull(1), rest.getOrNull(2))
                "container" -> newView(state, DslViewType.CONTAINER, rest.getOrNull(0), null, rest.getOrNull(1), rest.getOrNull(2))
                "component" -> newView(state, DslViewType.COMPONENT, rest.getOrNull(0), null, rest.getOrNull(1), rest.getOrNull(2))
                "deployment" ->
                    newView(state, DslViewType.DEPLOYMENT, rest.getOrNull(0), rest.getOrNull(1), rest.getOrNull(2), rest.getOrNull(3))
                "dynamic" -> newView(state, DslViewType.DYNAMIC, rest.getOrNull(0), null, rest.getOrNull(1), rest.getOrNull(2))
                "custom" -> newView(state, DslViewType.CUSTOM, null, null, rest.getOrNull(0), rest.getOrNull(2)).also { v ->
                    rest.getOrNull(1)?.let { v.title = it }
                }
                "image" -> newView(state, DslViewType.IMAGE, rest.getOrNull(0), null, rest.getOrNull(1), null)
                "filtered" -> newView(state, DslViewType.FILTERED, null, null, rest.getOrNull(3), rest.getOrNull(4)).also { v ->
                    v.baseKey = rest.getOrNull(0)
                    v.filterInclude = rest.getOrNull(1)?.lowercase() != "exclude"
                    rest.getOrNull(2)?.split(',')?.map { it.trim() }?.filter { it.isNotEmpty() }?.let { v.filterTags += it }
                }
                "!include" -> { include(st, state) { viewsBody(it, state) }; continue }
                else -> continue
            }
            viewBody(st.children, view)
            state.workspace.views += view
        }
    }

    private fun newView(
        state: State,
        type: DslViewType,
        scope: String?,
        environment: String?,
        key: String?,
        description: String?,
    ): DslView {
        val generatedKey = {
            val n = (state.viewCounters[type] ?: 0) + 1
            state.viewCounters[type] = n
            "${type.keyPrefix}-${n.toString().padStart(3, '0')}"
        }
        return DslView(type, key?.takeIf { it.isNotBlank() } ?: generatedKey(), scope, environment, description ?: "")
    }

    private fun viewBody(statements: List<Statement>, view: DslView) {
        for (st in statements) {
            val arrow = st.args.indexOfFirst { it.isArrow }
            if (view.type == DslViewType.DYNAMIC && arrow > 0) {
                val source = st.args[arrow - 1].text
                val after = st.args.drop(arrow + 1).map { it.text }
                after.firstOrNull()?.let { view.steps += DslDynamicStep(source, it, after.getOrElse(1) { "" }) }
                continue
            }
            if (view.type == DslViewType.DYNAMIC && st.hasBlock && st.args.isEmpty()) {
                viewBody(st.children, view) // parallel sequence block
                continue
            }
            val rest = st.args.drop(1).map { it.text }
            when (st.keyword) {
                // Relationship expressions (`exclude a -> b`) filter relationships, not elements; the preview
                // draws every relationship between visible elements, so they are ignored rather than misread.
                "include" -> if ("->" !in rest) view.includes += rest
                "exclude" -> if ("->" !in rest) view.excludes += rest
                "title" -> rest.firstOrNull()?.let { view.title = it }
                "description" -> rest.firstOrNull()?.let { view.description = it }
                "default" -> view.isDefault = true
                else -> Unit
            }
        }
    }

    /** Mirrors what Structurizr shows for a workspace without views: one view per level that has content. */
    private fun addGeneratedViews(ws: DslWorkspace) {
        val systems = ws.elements.filter { it.type == DslElementType.SOFTWARE_SYSTEM }
        if (ws.elements.none { it.type == DslElementType.PERSON || it.type == DslElementType.SOFTWARE_SYSTEM }) return
        ws.views += DslView(DslViewType.SYSTEM_LANDSCAPE, "SystemLandscape", generated = true).also { it.includes += "*" }
        for (system in systems) {
            val scope = system.identifier ?: continue
            ws.views += DslView(DslViewType.SYSTEM_CONTEXT, "SystemContext-${system.identifier}", scope, generated = true)
                .also { it.includes += "*" }
            if (ws.children(system).any { it.type == DslElementType.CONTAINER }) {
                ws.views += DslView(DslViewType.CONTAINER, "Container-${system.identifier}", scope, generated = true)
                    .also { it.includes += "*" }
            }
        }
        for (container in ws.elements.filter { it.type == DslElementType.CONTAINER }) {
            val scope = container.identifier ?: continue
            if (ws.children(container).any { it.type == DslElementType.COMPONENT }) {
                ws.views += DslView(DslViewType.COMPONENT, "Component-${container.identifier}", scope, generated = true)
                    .also { it.includes += "*" }
            }
        }
    }

    private companion object {
        const val MAX_INCLUDE_DEPTH = 8
    }
}
