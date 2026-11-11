package com.jakubjirak.structurizr.language.parser

import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.elementType
import com.intellij.testFramework.ParsingTestCase
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import com.jakubjirak.structurizr.navigation.StructurizrStructureViewElement
import java.io.File

/**
 * Regression tests for docs#13: one statement the grammar did not model (lower-case `autolayout`, a
 * text block, `name` on the workspace, `x = deploymentEnvironment`, ...) used to end the parse of the
 * whole file, so the `views` block — and the views in it — disappeared from the Structure view.
 */
class StructurizrParserRecoveryTest : ParsingTestCase("parsing", "dsl", StructurizrParserDefinition()) {

    override fun getTestDataPath(): String = "src/test/testData"

    private fun parseFile(name: String): PsiFile = createPsiFile(name, File("$testDataPath/parsing/$name.dsl").readText())

    private fun errors(file: PsiFile): List<String> =
        PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java).map { e ->
            val line = file.text.substring(0, e.textOffset).count { it == '\n' } + 1
            "line $line: ${e.errorDescription}"
        }

    private fun types(file: PsiFile): List<IElementType> =
        PsiTreeUtil.collectElements(file) { true }.mapNotNull { it.elementType }

    private fun assertParses(name: String, vararg expectedViews: IElementType) {
        val file = parseFile(name)
        assertEquals("unexpected parse errors in $name", emptyList<String>(), errors(file))
        val all = types(file)
        assertTrue("$name: views block missing", StructurizrTypes.VIEWS_BLOCK in all)
        val views = all.filter { it in VIEW_TYPES }
        assertEquals("$name: views", expectedViews.toList(), views)
    }

    fun testLowerCaseKeywordsDoNotEndTheParse() = assertParses(
        "a_basic",
        StructurizrTypes.SYSTEM_CONTEXT_VIEW, StructurizrTypes.CONTAINER_VIEW, StructurizrTypes.COMPONENT_VIEW,
    )

    fun testHierarchicalIdentifiers() = assertParses("b_identifiers", StructurizrTypes.SYSTEM_CONTEXT_VIEW)

    fun testElementBodiesWithPropertiesAndGroups() = assertParses("c_props", StructurizrTypes.SYSTEM_LANDSCAPE_VIEW)

    fun testAssignedDeploymentEnvironmentAndIdentifierEnvironmentInView() = assertParses(
        "d_this_deploy", StructurizrTypes.DEPLOYMENT_VIEW, StructurizrTypes.DYNAMIC_VIEW,
    )

    fun testWorkspacePropertiesFilteredWithoutBlockCustomAndImageViews() = assertParses(
        "e_misc",
        StructurizrTypes.SYSTEM_LANDSCAPE_VIEW, StructurizrTypes.FILTERED_VIEW, StructurizrTypes.CUSTOM_VIEW, StructurizrTypes.IMAGE_VIEW,
    )

    fun testTextBlocksAndComments() = assertParses("f_comments", StructurizrTypes.SYSTEM_CONTEXT_VIEW)

    fun testEveryViewKind() = assertParses(
        "g_views",
        StructurizrTypes.SYSTEM_LANDSCAPE_VIEW, StructurizrTypes.SYSTEM_CONTEXT_VIEW, StructurizrTypes.DEPLOYMENT_VIEW,
        StructurizrTypes.DYNAMIC_VIEW, StructurizrTypes.DYNAMIC_VIEW, StructurizrTypes.FILTERED_VIEW,
        StructurizrTypes.CUSTOM_VIEW, StructurizrTypes.IMAGE_VIEW,
    )

    fun testUnknownStatementCostsOnlyItself() {
        val file = createPsiFile(
            "unknown",
            """
            workspace {
                model {
                    u = person "User"
                    frobnicate "this is not DSL" {
                        nested stuff
                    }
                    s = softwareSystem "S"
                    u -> s "Uses"
                }
                views {
                    systemContext s "ctx" {
                        include *
                    }
                }
            }
            """.trimIndent(),
        )
        val all = types(file)
        // The skipped statement stays visible in the tree so the annotator can flag it.
        assertEquals(1, all.count { it == StructurizrParserRecovery.UNKNOWN_STATEMENT })
        assertTrue(StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION in all)
        assertTrue(StructurizrTypes.RELATIONSHIP_STATEMENT in all)
        assertTrue(StructurizrTypes.SYSTEM_CONTEXT_VIEW in all)
    }

    fun testIncludedModelFragmentWithoutWorkspace() {
        val file = createPsiFile("fragment", "u = person \"User\"\ns = softwareSystem \"S\"\nu -> s \"Uses\"\n")
        assertEquals(emptyList<String>(), errors(file))
        assertTrue(StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION in types(file))
    }

    fun testKeywordSpelledLikeIdentifierStaysIdentifierWhenAssigned() {
        // "Container" is assigned here, so it must stay an identifier even though "container" is a keyword.
        val file = createPsiFile(
            "assigned",
            "workspace {\n  model {\n    Container = softwareSystem \"S\"\n    u = person \"U\"\n    u -> Container \"Uses\"\n  }\n}\n",
        )
        assertEquals(emptyList<String>(), errors(file))
        assertTrue(StructurizrTypes.RELATIONSHIP_STATEMENT in types(file))
    }

    fun testStructureViewListsTheViewsOfTheIssueWorkspace() {
        val file = parseFile("issue13_architecture")
        assertEquals(emptyList<String>(), errors(file))
        fun dump(e: TreeElement, depth: Int): List<String> =
            listOf("  ".repeat(depth) + e.presentation.presentableText) + e.children.flatMap { dump(it, depth + 1) }
        val tree = dump(StructurizrStructureViewElement(file), 0)
        val views = tree.dropWhile { it.trim() != "views" }.drop(1).map { it.trim() }
        assertEquals(
            listOf(
                "systemLandscape: landscape",
                "systemContext svf: context",
                "container view svf: containers",
                "component view lib: components",
                "styles",
            ),
            views,
        )
        assertTrue(tree.any { it.trim() == "person: Consumer developer / verification workflow (dev)" })
        assertFalse("identifiers are shown inline, not as child nodes", tree.any { it.trim() == "dev" })
    }

    fun testDeploymentViewLabelShowsEnvironmentNotAsKey() {
        val file = createPsiFile(
            "deploy",
            "workspace {\n model {\n s = softwareSystem \"S\"\n" +
                " live = deploymentEnvironment \"Live\" {\n }\n }\n views {\n deployment * \"Live\" {\n" +
                " include *\n }\n deployment s live \"d1\" {\n include *\n }\n }\n}\n",
        )
        val labels = PsiTreeUtil.collectElements(file) { it.elementType == StructurizrTypes.DEPLOYMENT_VIEW }
            .map { StructurizrStructureViewElement(it).presentation.presentableText }
        assertEquals(listOf("deployment view * Live", "deployment view s live: d1"), labels)
    }

    private companion object {
        val VIEW_TYPES = setOf(
            StructurizrTypes.SYSTEM_LANDSCAPE_VIEW, StructurizrTypes.SYSTEM_CONTEXT_VIEW, StructurizrTypes.CONTAINER_VIEW,
            StructurizrTypes.COMPONENT_VIEW, StructurizrTypes.DEPLOYMENT_VIEW, StructurizrTypes.DYNAMIC_VIEW,
            StructurizrTypes.CUSTOM_VIEW, StructurizrTypes.FILTERED_VIEW, StructurizrTypes.IMAGE_VIEW,
        )
    }
}
