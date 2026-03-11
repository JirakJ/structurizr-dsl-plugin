/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.jcef.JBCefBrowser
import com.jakubjirak.structurizr.language.StructurizrFileType
import java.awt.BorderLayout
import java.awt.Component
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import javax.swing.*

/**
 * Content panel shown in the Structurizr preview tool window.
 *
 * Hosts a JCEF browser and a toolbar for view selection, refreshing, and zooming.
 */
class StructurizrPreviewContent(private val project: Project) {

    private val panel = JPanel(BorderLayout())
    private val browser = JBCefBrowser()
    private val viewComboBox = JComboBox(
        arrayOf("System Landscape", "System Context", "Container", "Component", "Deployment", "Dynamic")
    )
    private val scheduler = Executors.newSingleThreadScheduledExecutor()
    private var scheduledUpdate: ScheduledFuture<*>? = null
    private var currentFile: VirtualFile? = null

    /** The top-level Swing component for embedding in a tool window. */
    val component: JComponent get() = panel

    init {
        val toolbar = createToolbar()
        panel.add(toolbar as Component, BorderLayout.NORTH)
        panel.add(browser.component as Component, BorderLayout.CENTER)
        showWelcomeContent()
        setupFileListener()
        setupDocumentListener()
        viewComboBox.addActionListener { currentFile?.let { scheduleUpdate(it) } }
        updateFromCurrentEditor()
    }

    private fun createToolbar(): JPanel = JPanel().apply {
        layout = BoxLayout(this, BoxLayout.X_AXIS)
        border = BorderFactory.createEmptyBorder(4, 4, 4, 4)
        add(JLabel("View: "))
        add(viewComboBox)
        add(Box.createHorizontalStrut(8))

        add(JButton("Refresh").also { btn ->
            btn.addActionListener { currentFile?.let { updatePreview(it) } }
        })
        add(Box.createHorizontalStrut(8))

        add(JButton("+").also { btn ->
            btn.toolTipText = "Zoom In"
            btn.addActionListener { browser.zoomLevel = (browser.zoomLevel + 0.1).coerceAtMost(3.0) }
        })
        add(JButton("-").also { btn ->
            btn.toolTipText = "Zoom Out"
            btn.addActionListener { browser.zoomLevel = (browser.zoomLevel - 0.1).coerceAtLeast(0.25) }
        })
        add(JButton("100%").also { btn ->
            btn.toolTipText = "Reset Zoom"
            btn.addActionListener { browser.zoomLevel = 1.0 }
        })
        add(Box.createHorizontalGlue())
    }

    private fun showWelcomeContent() {
        val html = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body {
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                        background: #2b2b2b;
                        color: #a9b7c6;
                    }
                    .content {
                        text-align: center;
                        padding: 40px;
                    }
                    h2 { color: #6897bb; margin-bottom: 20px; }
                    p { margin: 10px 0; line-height: 1.6; }
                    .hint { color: #808080; font-size: 14px; margin-top: 20px; }
                    code {
                        background: #3c3f41;
                        padding: 2px 8px;
                        border-radius: 4px;
                        font-family: 'JetBrains Mono', monospace;
                    }
                </style>
            </head>
            <body>
                <div class="content">
                    <h2>📐 Structurizr Preview</h2>
                    <p>Open a <code>.dsl</code> file to see the diagram preview</p>
                    <p class="hint">The preview will update automatically as you edit</p>
                </div>
            </body>
            </html>
        """.trimIndent()
        browser.loadHTML(html)
    }

    private fun setupFileListener() {
        val connection = project.messageBus.connect()
        connection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, object : FileEditorManagerListener {
            override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
                if (file.fileType == StructurizrFileType) {
                    currentFile = file
                    scheduleUpdate(file)
                }
            }

            override fun selectionChanged(event: FileEditorManagerEvent) {
                val file = event.newFile
                if (file != null && file.fileType == StructurizrFileType) {
                    currentFile = file
                    scheduleUpdate(file)
                }
            }
        })
    }

    private fun setupDocumentListener() {
        EditorFactory.getInstance().eventMulticaster.addDocumentListener(object : DocumentListener {
            override fun documentChanged(event: DocumentEvent) {
                val file = FileDocumentManager.getInstance().getFile(event.document)
                if (file != null && file.fileType == StructurizrFileType) {
                    currentFile = file
                    scheduleUpdate(file)
                }
            }
        }, project)
    }

    private fun updateFromCurrentEditor() {
        val file = FileEditorManager.getInstance(project).selectedTextEditor
            ?.let { FileDocumentManager.getInstance().getFile(it.document) }
        if (file != null && file.fileType == StructurizrFileType) {
            currentFile = file
            scheduleUpdate(file)
        }
    }

    private fun scheduleUpdate(file: VirtualFile) {
        scheduledUpdate?.cancel(false)
        scheduledUpdate = scheduler.schedule({
            ApplicationManager.getApplication().invokeLater { updatePreview(file) }
        }, 500L, TimeUnit.MILLISECONDS)
    }

    private fun updatePreview(file: VirtualFile) {
        val document = FileDocumentManager.getInstance().getDocument(file) ?: return
        val content = document.text
        val selectedView = viewComboBox.selectedItem as? String ?: "System Context"
        val rendererService = DiagramRendererService.getInstance(project)
        val svgContent = rendererService.renderDiagram(content, selectedView)
        if (svgContent != null) {
            showDiagram(svgContent)
        } else {
            showError("Failed to render diagram. Check DSL syntax.")
        }
    }

    private fun showDiagram(svgContent: String) {
        val html = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body {
                        margin: 0;
                        padding: 20px;
                        background: #2b2b2b;
                        display: flex;
                        justify-content: center;
                        align-items: flex-start;
                        min-height: 100vh;
                    }
                    svg {
                        max-width: 100%;
                        height: auto;
                    }
                    .diagram-container {
                        background: white;
                        padding: 20px;
                        border-radius: 8px;
                        box-shadow: 0 4px 12px rgba(0,0,0,0.3);
                    }
                </style>
            </head>
            <body>
                <div class="diagram-container">
                    $svgContent
                </div>
            </body>
            </html>
        """.trimIndent()
        browser.loadHTML(html)
    }

    private fun showError(message: String) {
        val html = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body {
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                        background: #2b2b2b;
                        color: #a9b7c6;
                    }
                    .error {
                        text-align: center;
                        padding: 40px;
                    }
                    .icon { font-size: 48px; margin-bottom: 20px; }
                    h3 { color: #ff6b6b; margin-bottom: 10px; }
                    p { color: #808080; }
                </style>
            </head>
            <body>
                <div class="error">
                    <div class="icon">⚠️</div>
                    <h3>Preview Error</h3>
                    <p>$message</p>
                </div>
            </body>
            </html>
        """.trimIndent()
        browser.loadHTML(html)
    }
}
