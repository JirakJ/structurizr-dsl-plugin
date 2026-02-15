/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.Disposable
 *  com.intellij.openapi.editor.Document
 *  com.intellij.openapi.editor.Editor
 *  com.intellij.openapi.editor.EditorFactory
 *  com.intellij.openapi.editor.event.DocumentEvent
 *  com.intellij.openapi.editor.event.DocumentListener
 *  com.intellij.openapi.fileEditor.FileDocumentManager
 *  com.intellij.openapi.fileEditor.FileEditorManager
 *  com.intellij.openapi.fileEditor.FileEditorManagerEvent
 *  com.intellij.openapi.fileEditor.FileEditorManagerListener
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.vfs.VirtualFile
 *  com.intellij.ui.jcef.JBCefBrowser
 *  com.intellij.util.messages.MessageBusConnection
 *  com.intellij.util.messages.Topic
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.preview;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.jcef.JBCefBrowser;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.messages.Topic;
import com.jakubjirak.structurizr.language.StructurizrFileType;
import com.jakubjirak.structurizr.preview.DiagramRendererService;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0019\u001a\u00020\u0007H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u001bH\u0002J\b\u0010\u001e\u001a\u00020\u001bH\u0002J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0014H\u0002J\u0010\u0010!\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0014H\u0002J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\fH\u0002J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u000e\u001a\n \r*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u00168F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006&"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrPreviewContent;", "", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "panel", "Ljavax/swing/JPanel;", "browser", "Lcom/intellij/ui/jcef/JBCefBrowser;", "viewComboBox", "Ljavax/swing/JComboBox;", "", "kotlin.jvm.PlatformType", "scheduler", "Ljava/util/concurrent/ScheduledExecutorService;", "Ljava/util/concurrent/ScheduledExecutorService;", "scheduledUpdate", "Ljava/util/concurrent/ScheduledFuture;", "currentFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "component", "Ljavax/swing/JComponent;", "getComponent", "()Ljavax/swing/JComponent;", "createToolbar", "showWelcomeContent", "", "setupFileListener", "setupDocumentListener", "updateFromCurrentEditor", "scheduleUpdate", "file", "updatePreview", "showDiagram", "svgContent", "showError", "message", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrPreviewToolWindowFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrPreviewToolWindowFactory.kt\ncom/jakubjirak/structurizr/preview/StructurizrPreviewContent\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,332:1\n1#2:333\n*E\n"})
public final class StructurizrPreviewContent {
    @NotNull
    private final Project project;
    @NotNull
    private final JPanel panel;
    @NotNull
    private final JBCefBrowser browser;
    @NotNull
    private final JComboBox<String> viewComboBox;
    private final ScheduledExecutorService scheduler;
    @Nullable
    private ScheduledFuture<?> scheduledUpdate;
    @Nullable
    private VirtualFile currentFile;

    public StructurizrPreviewContent(@NotNull Project project) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        this.project = project;
        this.panel = new JPanel(new BorderLayout());
        String[] stringArray = new String[]{"System Landscape", "System Context", "Container", "Component", "Deployment", "Dynamic"};
        this.viewComboBox = new JComboBox<String>(stringArray);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.browser = new JBCefBrowser();
        JPanel toolbar = this.createToolbar();
        this.panel.add((Component)toolbar, "North");
        this.panel.add((Component)this.browser.getComponent(), "Center");
        this.showWelcomeContent();
        this.setupFileListener();
        this.setupDocumentListener();
        this.viewComboBox.addActionListener(arg_0 -> StructurizrPreviewContent._init_$lambda$1(this, arg_0));
        this.updateFromCurrentEditor();
    }

    @NotNull
    public final JComponent getComponent() {
        return this.panel;
    }

    private final JPanel createToolbar() {
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new BoxLayout(toolbar, 0));
        toolbar.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        toolbar.add(new JLabel("View: "));
        toolbar.add(this.viewComboBox);
        toolbar.add(Box.createHorizontalStrut(8));
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(arg_0 -> StructurizrPreviewContent.createToolbar$lambda$3(this, arg_0));
        toolbar.add(refreshButton);
        toolbar.add(Box.createHorizontalStrut(8));
        JButton zoomInButton = new JButton("+");
        zoomInButton.setToolTipText("Zoom In");
        zoomInButton.addActionListener(arg_0 -> StructurizrPreviewContent.createToolbar$lambda$4(this, arg_0));
        toolbar.add(zoomInButton);
        JButton zoomOutButton = new JButton("-");
        zoomOutButton.setToolTipText("Zoom Out");
        zoomOutButton.addActionListener(arg_0 -> StructurizrPreviewContent.createToolbar$lambda$5(this, arg_0));
        toolbar.add(zoomOutButton);
        JButton zoomResetButton = new JButton("100%");
        zoomResetButton.setToolTipText("Reset Zoom");
        zoomResetButton.addActionListener(arg_0 -> StructurizrPreviewContent.createToolbar$lambda$6(this, arg_0));
        toolbar.add(zoomResetButton);
        toolbar.add(Box.createHorizontalGlue());
        return toolbar;
    }

    private final void showWelcomeContent() {
        String html = "<!DOCTYPE html>\n<html>\n<head>\n    <style>\n        body {\n            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;\n            display: flex;\n            justify-content: center;\n            align-items: center;\n            height: 100vh;\n            margin: 0;\n            background: #2b2b2b;\n            color: #a9b7c6;\n        }\n        .content {\n            text-align: center;\n            padding: 40px;\n        }\n        h2 { color: #6897bb; margin-bottom: 20px; }\n        p { margin: 10px 0; line-height: 1.6; }\n        .hint { color: #808080; font-size: 14px; margin-top: 20px; }\n        code {\n            background: #3c3f41;\n            padding: 2px 8px;\n            border-radius: 4px;\n            font-family: 'JetBrains Mono', monospace;\n        }\n    </style>\n</head>\n<body>\n    <div class=\"content\">\n        <h2>\ud83d\udcd0 Structurizr Preview</h2>\n        <p>Open a <code>.dsl</code> file to see the diagram preview</p>\n        <p class=\"hint\">The preview will update automatically as you edit</p>\n    </div>\n</body>\n</html>";
        this.browser.loadHTML(html);
    }

    private final void setupFileListener() {
        MessageBusConnection messageBusConnection = this.project.getMessageBus().connect();
        Topic topic = FileEditorManagerListener.FILE_EDITOR_MANAGER;
        Intrinsics.checkNotNullExpressionValue((Object)topic, (String)"FILE_EDITOR_MANAGER");
        messageBusConnection.subscribe(topic, (Object)new FileEditorManagerListener(){
            final /* synthetic */ StructurizrPreviewContent this$0 = StructurizrPreviewContent.this;

            public void fileOpened(FileEditorManager source, VirtualFile file) {
                Intrinsics.checkNotNullParameter((Object)source, (String)"source");
                Intrinsics.checkNotNullParameter((Object)file, (String)"file");
                if (Intrinsics.areEqual((Object)file.getFileType(), (Object)((Object)StructurizrFileType.INSTANCE))) {
                    StructurizrPreviewContent.access$setCurrentFile$p(this.this$0, file);
                    StructurizrPreviewContent.access$scheduleUpdate(this.this$0, file);
                }
            }

            public void selectionChanged(FileEditorManagerEvent event) {
                Intrinsics.checkNotNullParameter((Object)event, (String)"event");
                VirtualFile file = event.getNewFile();
                if (file != null && Intrinsics.areEqual((Object)file.getFileType(), (Object)((Object)StructurizrFileType.INSTANCE))) {
                    StructurizrPreviewContent.access$setCurrentFile$p(this.this$0, file);
                    StructurizrPreviewContent.access$scheduleUpdate(this.this$0, file);
                }
            }
        });
    }

    private final void setupDocumentListener() {
        EditorFactory.getInstance().getEventMulticaster().addDocumentListener(new DocumentListener(){
            final /* synthetic */ StructurizrPreviewContent this$0 = StructurizrPreviewContent.this;

            public void documentChanged(DocumentEvent event) {
                Intrinsics.checkNotNullParameter((Object)event, (String)"event");
                VirtualFile file = FileDocumentManager.getInstance().getFile(event.getDocument());
                if (file != null && Intrinsics.areEqual((Object)file.getFileType(), (Object)((Object)StructurizrFileType.INSTANCE))) {
                    StructurizrPreviewContent.access$setCurrentFile$p(this.this$0, file);
                    StructurizrPreviewContent.access$scheduleUpdate(this.this$0, file);
                }
            }
        }, (Disposable)this.project);
    }

    private final void updateFromCurrentEditor() {
        Editor editor;
        Editor editor2 = editor = FileEditorManager.getInstance((Project)this.project).getSelectedTextEditor();
        VirtualFile file = null;
        if (editor2 != null) {
            Editor it = editor2;
            boolean bl = false;
            file = FileDocumentManager.getInstance().getFile(it.getDocument());
        }
        if (file != null && Intrinsics.areEqual((Object)file.getFileType(), (Object)((Object)StructurizrFileType.INSTANCE))) {
            this.currentFile = file;
            this.scheduleUpdate(file);
        }
    }

    private final void scheduleUpdate(VirtualFile file) {
        ScheduledFuture<?> scheduledFuture = this.scheduledUpdate;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.scheduledUpdate = this.scheduler.schedule(() -> StructurizrPreviewContent.scheduleUpdate$lambda$9(this, file), 500L, TimeUnit.MILLISECONDS);
    }

    private final void updatePreview(VirtualFile file) {
        DiagramRendererService rendererService;
        String svgContent;
        String selectedView;
        Document document = FileDocumentManager.getInstance().getDocument(file);
        if (document == null) {
            return;
        }
        Document document2 = document;
        String string = document2.getText();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
        String content = string;
        Object object = this.viewComboBox.getSelectedItem();
        String string2 = object instanceof String ? (String)object : null;
        if (string2 == null) {
            selectedView = "System Context";
        } else {
            selectedView = string2;
        }
        if ((svgContent = (rendererService = DiagramRendererService.Companion.getInstance(this.project)).renderDiagram(content, selectedView)) != null) {
            this.showDiagram(svgContent);
        } else {
            this.showError("Failed to render diagram. Check DSL syntax.");
        }
    }

    private final void showDiagram(String svgContent) {
        String html = StringsKt.trimIndent((String)("\n            <!DOCTYPE html>\n            <html>\n            <head>\n                <style>\n                    body {\n                        margin: 0;\n                        padding: 20px;\n                        background: #2b2b2b;\n                        display: flex;\n                        justify-content: center;\n                        align-items: flex-start;\n                        min-height: 100vh;\n                    }\n                    svg {\n                        max-width: 100%;\n                        height: auto;\n                    }\n                    .diagram-container {\n                        background: white;\n                        padding: 20px;\n                        border-radius: 8px;\n                        box-shadow: 0 4px 12px rgba(0,0,0,0.3);\n                    }\n                </style>\n            </head>\n            <body>\n                <div class=\"diagram-container\">\n                    " + svgContent + "\n                </div>\n            </body>\n            </html>\n        "));
        this.browser.loadHTML(html);
    }

    private final void showError(String message) {
        String html = StringsKt.trimIndent((String)("\n            <!DOCTYPE html>\n            <html>\n            <head>\n                <style>\n                    body {\n                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;\n                        display: flex;\n                        justify-content: center;\n                        align-items: center;\n                        height: 100vh;\n                        margin: 0;\n                        background: #2b2b2b;\n                        color: #a9b7c6;\n                    }\n                    .error {\n                        text-align: center;\n                        padding: 40px;\n                    }\n                    .icon { font-size: 48px; margin-bottom: 20px; }\n                    h3 { color: #ff6b6b; margin-bottom: 10px; }\n                    p { color: #808080; }\n                </style>\n            </head>\n            <body>\n                <div class=\"error\">\n                    <div class=\"icon\">\u26a0\ufe0f</div>\n                    <h3>Preview Error</h3>\n                    <p>" + message + "</p>\n                </div>\n            </body>\n            </html>\n        "));
        this.browser.loadHTML(html);
    }

    private static final void _init_$lambda$1(StructurizrPreviewContent this$0, ActionEvent it) {
        block0: {
            VirtualFile virtualFile = this$0.currentFile;
            if (virtualFile == null) break block0;
            VirtualFile file = virtualFile;
            boolean bl = false;
            this$0.scheduleUpdate(file);
        }
    }

    private static final void createToolbar$lambda$3(StructurizrPreviewContent this$0, ActionEvent it) {
        block0: {
            VirtualFile virtualFile = this$0.currentFile;
            if (virtualFile == null) break block0;
            VirtualFile file = virtualFile;
            boolean bl = false;
            this$0.updatePreview(file);
        }
    }

    private static final void createToolbar$lambda$4(StructurizrPreviewContent this$0, ActionEvent it) {
        this$0.browser.setZoomLevel(RangesKt.coerceAtMost((double)(this$0.browser.getZoomLevel() + 0.1), (double)3.0));
    }

    private static final void createToolbar$lambda$5(StructurizrPreviewContent this$0, ActionEvent it) {
        this$0.browser.setZoomLevel(RangesKt.coerceAtLeast((double)(this$0.browser.getZoomLevel() - 0.1), (double)0.25));
    }

    private static final void createToolbar$lambda$6(StructurizrPreviewContent this$0, ActionEvent it) {
        this$0.browser.setZoomLevel(1.0);
    }

    private static final void scheduleUpdate$lambda$9$lambda$8(StructurizrPreviewContent this$0, VirtualFile $file) {
        this$0.updatePreview($file);
    }

    private static final void scheduleUpdate$lambda$9(StructurizrPreviewContent this$0, VirtualFile $file) {
        SwingUtilities.invokeLater(() -> StructurizrPreviewContent.scheduleUpdate$lambda$9$lambda$8(this$0, $file));
    }

    public static final /* synthetic */ void access$setCurrentFile$p(StructurizrPreviewContent $this, VirtualFile virtualFile) {
        $this.currentFile = virtualFile;
    }

    public static final /* synthetic */ void access$scheduleUpdate(StructurizrPreviewContent $this, VirtualFile file) {
        $this.scheduleUpdate(file);
    }
}
