/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.fileChooser.FileChooser
 *  com.intellij.openapi.fileChooser.FileChooserDescriptor
 *  com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.ui.ComboBox
 *  com.intellij.openapi.ui.DialogWrapper
 *  com.intellij.openapi.ui.TextFieldWithBrowseButton
 *  com.intellij.openapi.vfs.VirtualFile
 *  com.intellij.ui.DocumentAdapter
 *  com.intellij.ui.components.JBCheckBox
 *  com.intellij.ui.components.JBLabel
 *  com.intellij.ui.components.JBTextField
 *  com.intellij.util.ui.FormBuilder
 *  com.intellij.util.ui.JBUI$Borders
 *  com.intellij.util.ui.JBUI$CurrentTheme$ContextHelp
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.export;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.JBUI;
import com.jakubjirak.structurizr.export.DiagramExportFormat;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0019H\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u0019H\u0014J\u0006\u0010\u001f\u001a\u00020\nJ\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020!J\u0006\u0010#\u001a\u00020$J\b\u0010%\u001a\u0004\u0018\u00010\u0014J\u0006\u0010&\u001a\u00020$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006'"}, d2={"Lcom/jakubjirak/structurizr/export/ExportDialog;", "Lcom/intellij/openapi/ui/DialogWrapper;", "project", "Lcom/intellij/openapi/project/Project;", "sourceFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "<init>", "(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;)V", "formatComboBox", "Lcom/intellij/openapi/ui/ComboBox;", "Lcom/jakubjirak/structurizr/export/DiagramExportFormat;", "kotlin.jvm.PlatformType", "outputPathField", "Lcom/intellij/openapi/ui/TextFieldWithBrowseButton;", "fileNameField", "Lcom/intellij/ui/components/JBTextField;", "exportAllViewsCheckbox", "Lcom/intellij/ui/components/JBCheckBox;", "openAfterExportCheckbox", "viewSelector", "", "formatDescriptionLabel", "Lcom/intellij/ui/components/JBLabel;", "previewLabel", "setupUI", "", "updateFormatDescription", "updatePreview", "createCenterPanel", "Ljavax/swing/JComponent;", "doOKAction", "getSelectedFormat", "getOutputPath", "Ljava/nio/file/Path;", "getOutputDirectory", "isExportAllViews", "", "getSelectedViewKey", "isOpenAfterExport", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nExportDialog.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExportDialog.kt\ncom/jakubjirak/structurizr/export/ExportDialog\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,288:1\n1#2:289\n*E\n"})
public final class ExportDialog
extends DialogWrapper {
    @NotNull
    private final Project project;
    @NotNull
    private final VirtualFile sourceFile;
    @NotNull
    private final ComboBox<DiagramExportFormat> formatComboBox;
    @NotNull
    private final TextFieldWithBrowseButton outputPathField;
    @NotNull
    private final JBTextField fileNameField;
    @NotNull
    private final JBCheckBox exportAllViewsCheckbox;
    @NotNull
    private final JBCheckBox openAfterExportCheckbox;
    @NotNull
    private final ComboBox<String> viewSelector;
    @NotNull
    private final JBLabel formatDescriptionLabel;
    @NotNull
    private final JBLabel previewLabel;

    public ExportDialog(@NotNull Project project, @NotNull VirtualFile sourceFile) {
        super(project);
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)sourceFile, (String)"sourceFile");
        this.project = project;
        this.sourceFile = sourceFile;
        this.formatComboBox = new ComboBox((Object[])DiagramExportFormat.values());
        this.outputPathField = new TextFieldWithBrowseButton();
        this.fileNameField = new JBTextField();
        this.exportAllViewsCheckbox = new JBCheckBox("Export all views separately");
        this.openAfterExportCheckbox = new JBCheckBox("Open file after export", true);
        this.viewSelector = new ComboBox();
        this.formatDescriptionLabel = new JBLabel();
        this.previewLabel = new JBLabel();
        this.setTitle("Export Diagram");
        this.init();
        this.setupUI();
        this.updatePreview();
    }

    private final void setupUI() {
        this.formatComboBox.setRenderer((ListCellRenderer)new DefaultListCellRenderer(){

            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DiagramExportFormat) {
                    this.setText(((DiagramExportFormat)((Object)value)).getDisplayName() + " (." + ((DiagramExportFormat)((Object)value)).getExtension() + ")");
                }
                return this;
            }
        });
        this.formatComboBox.addActionListener(arg_0 -> ExportDialog.setupUI$lambda$0(this, arg_0));
        this.outputPathField.addActionListener(arg_0 -> ExportDialog.setupUI$lambda$3(this, arg_0));
        Object object = this.sourceFile.getParent();
        if (object == null || (object = ((VirtualFile)object).getPath()) == null) {
            object = System.getProperty("user.home");
        }
        Object defaultDir = object;
        this.outputPathField.setText((String)defaultDir);
        this.fileNameField.setText(this.sourceFile.getNameWithoutExtension());
        this.fileNameField.getDocument().addDocumentListener((DocumentListener)new DocumentAdapter(){
            final /* synthetic */ ExportDialog this$0 = ExportDialog.this;

            protected void textChanged(DocumentEvent e) {
                Intrinsics.checkNotNullParameter((Object)e, (String)"e");
                ExportDialog.access$updatePreview(this.this$0);
            }
        });
        this.viewSelector.addItem("All views");
        this.viewSelector.addItem("System Landscape");
        this.viewSelector.addItem("System Context");
        this.viewSelector.addItem("Container");
        this.viewSelector.addItem("Component");
        this.viewSelector.addItem("Deployment");
        this.exportAllViewsCheckbox.addActionListener(arg_0 -> ExportDialog.setupUI$lambda$4(this, arg_0));
        this.updateFormatDescription();
    }

    private final void updateFormatDescription() {
        Object object = this.formatComboBox.getSelectedItem();
        DiagramExportFormat format = object instanceof DiagramExportFormat ? (DiagramExportFormat)((Object)object) : null;
        Object object2 = format;
        if (object2 == null || (object2 = ((DiagramExportFormat)object2).getDescription()) == null) {
            object2 = "";
        }
        this.formatDescriptionLabel.setText("<html><i>" + (String)object2 + "</i></html>");
        this.formatDescriptionLabel.setForeground(JBUI.CurrentTheme.ContextHelp.FOREGROUND);
    }

    private final void updatePreview() {
        CharSequence charSequence;
        Object object;
        Object object2 = this.formatComboBox.getSelectedItem();
        DiagramExportFormat diagramExportFormat = object2 instanceof DiagramExportFormat ? (DiagramExportFormat)((Object)object2) : null;
        if (diagramExportFormat == null) {
            return;
        }
        DiagramExportFormat format = diagramExportFormat;
        object2 = this.fileNameField.getText();
        if (StringsKt.isBlank((CharSequence)object2)) {
            boolean bl = false;
            object = this.sourceFile.getNameWithoutExtension();
        } else {
            object = object2;
        }
        String fileName = (String)object;
        CharSequence charSequence2 = this.outputPathField.getText();
        if (StringsKt.isBlank((CharSequence)charSequence2)) {
            boolean bl = false;
            VirtualFile parentFile = this.sourceFile.getParent();
            if (parentFile == null || (charSequence = parentFile.getPath()) == null) {
                charSequence = "";
            }
        } else {
            charSequence = charSequence2;
        }
        Intrinsics.checkNotNullExpressionValue((Object)charSequence, (String)"ifBlank(...)");
        String outputDir = (String)charSequence;
        String fullPath = this.exportAllViewsCheckbox.isSelected() ? outputDir + "/<view-name>." + format.getExtension() : outputDir + "/" + fileName + "." + format.getExtension();
        this.previewLabel.setText("<html><b>Output:</b> " + fullPath + "</html>");
    }

    @NotNull
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formPanel = FormBuilder.createFormBuilder().addLabeledComponent("Source:", (JComponent)new JBLabel(this.sourceFile.getName())).addSeparator().addLabeledComponent("Format:", (JComponent)this.formatComboBox).addComponentToRightColumn((JComponent)this.formatDescriptionLabel).addSeparator().addLabeledComponent("Output Directory:", (JComponent)this.outputPathField).addLabeledComponent("File Name:", (JComponent)this.fileNameField).addSeparator().addLabeledComponent("View:", (JComponent)this.viewSelector).addComponent((JComponent)this.exportAllViewsCheckbox).addComponent((JComponent)this.openAfterExportCheckbox).addSeparator().addComponent((JComponent)this.previewLabel).getPanel();
        formPanel.setBorder(JBUI.Borders.empty((int)10));
        panel.add((Component)formPanel, "Center");
        panel.setPreferredSize(new Dimension(500, 350));
        return panel;
    }

    protected void doOKAction() {
        String string = this.outputPathField.getText();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
        if (StringsKt.isBlank((CharSequence)string)) {
            this.setErrorText("Please select an output directory", (JComponent)this.outputPathField);
            return;
        }
        String string2 = this.fileNameField.getText();
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getText(...)");
        if (StringsKt.isBlank((CharSequence)string2) && !this.exportAllViewsCheckbox.isSelected()) {
            this.setErrorText("Please enter a file name", (JComponent)this.fileNameField);
            return;
        }
        super.doOKAction();
    }

    @NotNull
    public final DiagramExportFormat getSelectedFormat() {
        Object object = this.formatComboBox.getSelectedItem();
        Intrinsics.checkNotNull((Object)object, (String)"null cannot be cast to non-null type com.jakubjirak.structurizr.export.DiagramExportFormat");
        return (DiagramExportFormat)((Object)object);
    }

    @NotNull
    public final Path getOutputPath() {
        CharSequence charSequence;
        DiagramExportFormat format = this.getSelectedFormat();
        CharSequence charSequence2 = this.fileNameField.getText();
        if (StringsKt.isBlank((CharSequence)charSequence2)) {
            boolean bl = false;
            charSequence = this.sourceFile.getNameWithoutExtension();
        } else {
            charSequence = charSequence2;
        }
        String fileName = (String)charSequence;
        String[] stringArray = new String[]{fileName + "." + format.getExtension()};
        Path path = Path.of(this.outputPathField.getText(), stringArray);
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"of(...)");
        return path;
    }

    @NotNull
    public final Path getOutputDirectory() {
        Path path = Path.of(this.outputPathField.getText(), new String[0]);
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"of(...)");
        return path;
    }

    public final boolean isExportAllViews() {
        return this.exportAllViewsCheckbox.isSelected();
    }

    @Nullable
    public final String getSelectedViewKey() {
        String selected;
        if (this.exportAllViewsCheckbox.isSelected()) {
            return null;
        }
        Object object = this.viewSelector.getSelectedItem();
        String string = object instanceof String ? (String)object : null;
        if (string == null) {
            return null;
        }
        return switch (selected = string) {
            case "All views" -> null;
            case "System Landscape" -> "landscape";
            case "System Context" -> "systemContext";
            case "Container" -> "container";
            case "Component" -> "component";
            case "Deployment" -> "deployment";
            default -> null;
        };
    }

    public final boolean isOpenAfterExport() {
        return this.openAfterExportCheckbox.isSelected();
    }

    private static final void setupUI$lambda$0(ExportDialog this$0, ActionEvent it) {
        this$0.updateFormatDescription();
        this$0.updatePreview();
    }

    private static final Unit setupUI$lambda$3$lambda$1(ExportDialog this$0, VirtualFile file) {
        this$0.outputPathField.setText(file.getPath());
        return Unit.INSTANCE;
    }

    private static final void setupUI$lambda$3$lambda$2(Function1 $tmp0, Object p0) {
        $tmp0.invoke(p0);
    }

    private static final void setupUI$lambda$3(ExportDialog this$0, ActionEvent it) {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor().withTitle("Select Output Directory").withDescription("Choose where to save the exported diagram");
        FileChooser.chooseFile((FileChooserDescriptor)descriptor, (Project)this$0.project, null, file -> ExportDialog.setupUI$lambda$3$lambda$1(this$0, (VirtualFile)file));
    }

    private static final void setupUI$lambda$4(ExportDialog this$0, ActionEvent it) {
        this$0.viewSelector.setEnabled(!this$0.exportAllViewsCheckbox.isSelected());
        this$0.updatePreview();
    }

    public static final /* synthetic */ void access$updatePreview(ExportDialog $this) {
        $this.updatePreview();
    }
}
