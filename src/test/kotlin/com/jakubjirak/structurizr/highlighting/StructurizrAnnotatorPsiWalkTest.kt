/*
 * Copyright 2026 Jakub Jirák
 * SPDX-License-Identifier: Apache-2.0
 */
package com.jakubjirak.structurizr.highlighting

import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.elementType
import com.intellij.psi.util.parents
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File
import java.lang.reflect.Proxy

/**
 * Regression tests for docs#12: [StructurizrAnnotator] threw an NPE because its ancestor walk
 * ran past the [PsiFile] into the [PsiDirectory], whose `getNode()` is null.
 */
@DisplayName("PSI ancestor walk (docs#12)")
class StructurizrAnnotatorPsiWalkTest {

    private fun astNode(type: IElementType): ASTNode =
        Proxy.newProxyInstance(javaClass.classLoader, arrayOf(ASTNode::class.java)) { _, method, _ ->
            when (method.name) {
                "getElementType" -> type
                "toString" -> "ASTNode($type)"
                else -> null
            }
        } as ASTNode

    /** A PSI proxy of [iface] with the given parent, AST node and text; other calls return null. */
    private fun <T : PsiElement> psi(
        iface: Class<T>,
        parent: PsiElement?,
        node: ASTNode?,
        text: String = "",
    ): T = iface.cast(
        Proxy.newProxyInstance(javaClass.classLoader, arrayOf(iface)) { proxy, method, args ->
            when (method.name) {
                "getParent" -> parent
                "getNode" -> node
                "getText" -> text
                "getTextRange" -> TextRange(0, text.length)
                "isValid" -> true
                "equals" -> proxy === args?.get(0)
                "hashCode" -> System.identityHashCode(proxy)
                "toString" -> "${iface.simpleName}($text)"
                else -> null
            }
        }
    )

    /** identifier → file → directory (node == null): the tree from the bug report. */
    private fun topLevelIdentifier(text: String = "foo"): Triple<PsiElement, PsiFile, PsiDirectory> {
        val directory = psi(PsiDirectory::class.java, parent = null, node = null)
        val file = psi(PsiFile::class.java, parent = directory, node = astNode(StructurizrTypes.LBRACE))
        val identifier = psi(PsiElement::class.java, parent = file, node = astNode(StructurizrTypes.IDENTIFIER), text = text)
        return Triple(identifier, file, directory)
    }

    @Test
    fun `annotator does not throw for an identifier directly under the file`() {
        val (identifier, _, _) = topLevelIdentifier()
        var annotations = 0
        val holder = Proxy.newProxyInstance(javaClass.classLoader, arrayOf(AnnotationHolder::class.java)) { _, _, _ ->
            annotations++
            null
        } as AnnotationHolder

        StructurizrAnnotator().annotate(identifier, holder)

        assertEquals(0, annotations, "plain identifier outside any variable context gets no annotation")
    }

    @Test
    fun `platform parents() stops at the file and never yields its directory`() {
        val (identifier, file, _) = topLevelIdentifier()
        assertEquals(listOf<PsiElement>(file), identifier.parents(withSelf = false).toList())
    }

    @Test
    fun `platform elementType is null for elements without an AST node`() {
        val (_, _, directory) = topLevelIdentifier()
        assertNull(directory.elementType)
    }

    /**
     * Guards against the bug class coming back: an unbounded `x = x.parent` loop walks into
     * PsiDirectory, and `.node.elementType` dereferences a node that may be null there.
     * Use `parents(withSelf = ...)` and the null-safe `elementType` extension instead.
     */
    @Test
    fun `main sources use null-safe PSI walks only`() {
        val root = File("src/main/kotlin")
        assertTrue(root.isDirectory, "run from the project directory")
        val forbidden = listOf(
            Regex("""\.node\??\.elementType\b"""),
            Regex("""\b(\w+) = \1\??\.parent\b"""),
        )
        val offenders = root.walkTopDown().filter { it.extension == "kt" }.flatMap { file ->
            file.readLines().withIndex()
                .filter { (_, line) -> forbidden.any { it.containsMatchIn(line) } }
                .map { (i, line) -> "${file.path}:${i + 1}: ${line.trim()}" }
        }.toList()
        assertTrue(offenders.isEmpty(), "unsafe PSI walk:\n" + offenders.joinToString("\n"))
    }
}
