/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.preview

import com.intellij.icons.AllIcons
import com.intellij.openapi.Disposable
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.Separator
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.application.ReadAction
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.components.JBLabel
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.util.Alarm
import com.intellij.util.concurrency.AppExecutorUtil
import com.intellij.util.messages.Topic
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import com.jakubjirak.structurizr.preview.dsl.PreviewResult
import com.jakubjirak.structurizr.preview.dsl.ViewOption
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import javax.swing.DefaultComboBoxModel
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.JPanel

/** What the preview should render: the DSL text and the file it came from (for `!include`). */
data class PreviewSource(val text: String, val file: VirtualFile?)

/**
 * Diagram preview with a view selector, a refresh button and zoom controls, rendered in JCEF.
 *
 * The panel pulls its input from [source], which is always invoked inside a read action on a background
 * thread — never on the EDT — so callers can read documents and PSI freely. The view selector lists the
 * views defined in the workspace (or Structurizr-style default views when it defines none) and keeps the
 * selection, by key, across edits.
 */
class StructurizrPreviewPanel(
    private val project: Project,
    private val source: () -> PreviewSource?,
) : JPanel(BorderLayout()), Disposable {

    private var browser: JBCefBrowser? = null
    private val viewModel = DefaultComboBoxModel<ViewOption>()
    private val viewComboBox = ComboBox(viewModel).apply {
        toolTipText = "Views defined in this workspace"
        prototypeDisplayValue = ViewOption("", "SystemContext-001 — System")
        minimumSize = JBUI.size(80, minimumSize.height)
    }
    private val alarm = Alarm(Alarm.ThreadToUse.SWING_THREAD, this)

    /** Key of the view the user picked; `null` until they pick one (the workspace default is used). */
    @Volatile
    private var selectedKey: String? = null
    private var updatingSelector = false

    @Volatile
    private var disposed = false

    init {
        val created = if (JcefSupport.isAvailable()) JcefSupport.runGuarded { JBCefBrowser() } else null
        if (created == null) {
            add(JBLabel(JcefSupport.UNAVAILABLE_MESSAGE, JBLabel.CENTER) as Component, BorderLayout.CENTER)
        } else {
            browser = created
            Disposer.register(this, created)
            add(createToolbar() as Component, BorderLayout.NORTH)
            add(created.component as Component, BorderLayout.CENTER)
            showMessage("Structurizr Preview", "Open a <code>.dsl</code> file to see its diagrams.")
            viewComboBox.addActionListener {
                if (updatingSelector) return@addActionListener
                selectedKey = (viewComboBox.selectedItem as? ViewOption)?.key
                refreshNow()
            }
            project.messageBus.connect(this).subscribe(REFRESH_TOPIC, Runnable { refreshNow() })
        }
    }

    private fun createToolbar(): JPanel {
        val actions = DefaultActionGroup(
            toolbarAction("Refresh Preview", AllIcons.Actions.Refresh) { refreshNow() },
            Separator.getInstance(),
            toolbarAction("Zoom In", AllIcons.General.ZoomIn) { zoom(+0.1) },
            toolbarAction("Zoom Out", AllIcons.General.ZoomOut) { zoom(-0.1) },
            toolbarAction("Fit to Window", AllIcons.General.ActualZoom) { browser?.zoomLevel = 0.0 },
        )
        val toolbar = ActionManager.getInstance().createActionToolbar(TOOLBAR_PLACE, actions, true)
        toolbar.targetComponent = this
        return JPanel(BorderLayout(JBUI.scale(6), 0)).apply {
            border = JBUI.Borders.empty(2, 6, 2, 2)
            add(JLabel("View:"), BorderLayout.WEST)
            // The selector takes whatever width is left, so long view names never push the buttons out of sight.
            add(viewComboBox, BorderLayout.CENTER)
            add(toolbar.component, BorderLayout.EAST)
        }
    }

    private fun toolbarAction(text: String, icon: Icon, run: () -> Unit): AnAction = object : DumbAwareAction(text, null, icon) {
        override fun actionPerformed(e: AnActionEvent) = run()
        override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.EDT
    }

    private fun zoom(delta: Double) {
        browser?.let { it.zoomLevel = (it.zoomLevel + delta).coerceIn(-3.0, 3.0) }
    }

    /** Forgets the view the user picked; used when the panel starts showing a different file. */
    fun resetSelection() {
        selectedKey = null
    }

    /** Re-renders after a short delay, coalescing bursts of edits. */
    fun scheduleRefresh() {
        if (browser == null || disposed) return
        alarm.cancelAllRequests()
        alarm.addRequest({ refreshNow() }, DEBOUNCE_MS)
    }

    /** Re-reads the source and re-renders the selected view. Safe to call from any thread. */
    fun refreshNow() {
        if (browser == null || disposed) return
        val key = selectedKey
        ReadAction.nonBlocking<Pair<PreviewResult?, Boolean>> {
            val input = source() ?: return@nonBlocking null to false
            DiagramRendererService.getInstance(project).render(input.text, key, input.file) to true
        }
            .expireWith(this)
            // A newer refresh cancels a running one, so an older render can never overwrite a newer one.
            .coalesceBy(this)
            .finishOnUiThread(ModalityState.any()) { (result, hasSource) ->
                when {
                    !hasSource -> showMessage("Structurizr Preview", "Open a <code>.dsl</code> file to see its diagrams.")
                    result == null -> showMessage("Preview Error", "The diagram could not be rendered. See the IDE log for details.")
                    else -> apply(result)
                }
            }
            .submit(AppExecutorUtil.getAppExecutorService())
    }

    private fun apply(result: PreviewResult) {
        updatingSelector = true
        try {
            val current = (0 until viewModel.size).map { viewModel.getElementAt(it) }
            if (current != result.views) {
                viewModel.removeAllElements()
                result.views.forEach { viewModel.addElement(it) }
            }
            viewModel.selectedItem = result.views.firstOrNull { it.key == result.selectedKey }
            viewComboBox.isEnabled = result.views.isNotEmpty()
        } finally {
            updatingSelector = false
        }
        browser?.loadHTML(page(result.svg))
    }

    private fun page(body: String): String {
        val bg = hex(UIUtil.getPanelBackground())
        return """<!DOCTYPE html><html><head><meta charset="UTF-8"><style>
            html,body{margin:0;padding:0;background:$bg}
            .wrap{padding:12px}
            .card{background:#fff;border-radius:8px;box-shadow:0 2px 10px rgba(0,0,0,.25);overflow:hidden;line-height:0;
                  width:fit-content;max-width:100%;margin:0 auto}
            /* Fit to the window by default; the zoom buttons enlarge it (the page then scrolls). */
            svg{display:block;max-width:100%;height:auto}
            </style></head><body><div class="wrap"><div class="card">$body</div></div></body></html>"""
    }

    private fun showMessage(title: String, html: String) {
        val bg = hex(UIUtil.getPanelBackground())
        val fg = hex(UIUtil.getLabelForeground())
        browser?.loadHTML(
            """<!DOCTYPE html><html><head><meta charset="UTF-8"><style>
            body{font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,sans-serif;display:flex;justify-content:center;
                 align-items:center;height:100vh;margin:0;background:$bg;color:$fg}
            .box{text-align:center;padding:${JBUI.scale(32)}px} h3{margin-bottom:12px}
            code{padding:1px 6px;border-radius:4px;background:rgba(127,127,127,.2)}
            </style></head><body><div class="box"><h3>$title</h3><p>$html</p></div></body></html>"""
        )
    }

    private fun hex(c: Color): String = String.format("#%02x%02x%02x", c.red, c.green, c.blue)

    override fun dispose() {
        disposed = true
        browser = null
    }

    companion object {
        private const val DEBOUNCE_MS = 400
        private const val TOOLBAR_PLACE = "StructurizrPreviewToolbar"

        /** Published by the "Refresh Structurizr Preview" action; every open preview re-renders. */
        @JvmField
        val REFRESH_TOPIC: Topic<Runnable> = Topic.create("Structurizr preview refresh", Runnable::class.java)

        /** Asks every open preview of [project] to re-render. */
        fun refreshAll(project: Project) {
            ApplicationManager.getApplication().invokeLater({
                if (!project.isDisposed) project.messageBus.syncPublisher(REFRESH_TOPIC).run()
            }, ModalityState.any())
        }
    }
}
