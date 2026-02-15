/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.fileChooser.FileChooser
 *  com.intellij.openapi.fileChooser.FileChooserDescriptor
 *  com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.ui.DialogWrapper
 *  com.intellij.openapi.ui.TextFieldWithBrowseButton
 *  com.intellij.openapi.vfs.VirtualFile
 *  com.intellij.ui.components.JBLabel
 *  com.intellij.ui.components.JBTextField
 *  com.intellij.util.ui.FormBuilder
 *  com.intellij.util.ui.JBUI$Borders
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.export;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.JBUI;
import com.jakubjirak.structurizr.export.DiagramExportFormat;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import javax.swing.JComponent;
import javax.swing.JPanel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u000fH\u0014J\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2={"Lcom/jakubjirak/structurizr/export/QuickExportDialog;", "Lcom/intellij/openapi/ui/DialogWrapper;", "project", "Lcom/intellij/openapi/project/Project;", "sourceFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "format", "Lcom/jakubjirak/structurizr/export/DiagramExportFormat;", "<init>", "(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/jakubjirak/structurizr/export/DiagramExportFormat;)V", "outputPathField", "Lcom/intellij/openapi/ui/TextFieldWithBrowseButton;", "fileNameField", "Lcom/intellij/ui/components/JBTextField;", "setupUI", "", "createCenterPanel", "Ljavax/swing/JComponent;", "doOKAction", "getOutputPath", "Ljava/nio/file/Path;", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nExportDialog.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExportDialog.kt\ncom/jakubjirak/structurizr/export/QuickExportDialog\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,288:1\n1#2:289\n*E\n"})
public final class QuickExportDialog
extends DialogWrapper {
    @NotNull
    private final Project project;
    @NotNull
    private final VirtualFile sourceFile;
    @NotNull
    private final DiagramExportFormat format;
    @NotNull
    private final TextFieldWithBrowseButton outputPathField;
    @NotNull
    private final JBTextField fileNameField;

    public QuickExportDialog(@NotNull Project project, @NotNull VirtualFile sourceFile, @NotNull DiagramExportFormat format) {
        super(project);
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)sourceFile, (String)"sourceFile");
        Intrinsics.checkNotNullParameter((Object)((Object)format), (String)"format");
        this.project = project;
        this.sourceFile = sourceFile;
        this.format = format;
        this.outputPathField = new TextFieldWithBrowseButton();
        this.fileNameField = new JBTextField();
        this.setTitle("Export as " + this.format.getDisplayName());
        this.init();
        this.setupUI();
    }

    private final void setupUI() {
        this.outputPathField.addActionListener(arg_0 -> QuickExportDialog.setupUI$lambda$2(this, arg_0));
        Object object = this.sourceFile.getParent();
        if (object == null || (object = ((VirtualFile)object).getPath()) == null) {
            object = System.getProperty("user.home");
        }
        Object defaultDir = object;
        this.outputPathField.setText((String)defaultDir);
        this.fileNameField.setText(this.sourceFile.getNameWithoutExtension());
    }

    @NotNull
    protected JComponent createCenterPanel() {
        JPanel panel = FormBuilder.createFormBuilder().addLabeledComponent("Output Directory:", (JComponent)this.outputPathField).addLabeledComponent("File Name:", (JComponent)this.fileNameField).addComponent((JComponent)new JBLabel("<html><i>Will be saved as: " + this.fileNameField.getText() + "." + this.format.getExtension() + "</i></html>")).getPanel();
        panel.setBorder(JBUI.Borders.empty((int)10));
        panel.setPreferredSize(new Dimension(400, 150));
        Intrinsics.checkNotNull((Object)panel);
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
        if (StringsKt.isBlank((CharSequence)string2)) {
            this.setErrorText("Please enter a file name", (JComponent)this.fileNameField);
            return;
        }
        super.doOKAction();
    }

    @NotNull
    public final Path getOutputPath() {
        CharSequence charSequence;
        CharSequence charSequence2 = this.fileNameField.getText();
        if (StringsKt.isBlank((CharSequence)charSequence2)) {
            boolean bl = false;
            charSequence = this.sourceFile.getNameWithoutExtension();
        } else {
            charSequence = charSequence2;
        }
        String fileName = (String)charSequence;
        String[] stringArray = new String[]{fileName + "." + this.format.getExtension()};
        Path path = Path.of(this.outputPathField.getText(), stringArray);
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"of(...)");
        return path;
    }

    private static final Unit setupUI$lambda$2$lambda$0(QuickExportDialog this$0, VirtualFile file) {
        this$0.outputPathField.setText(file.getPath());
        return Unit.INSTANCE;
    }

    private static final void setupUI$lambda$2$lambda$1(Function1 $tmp0, Object p0) {
        $tmp0.invoke(p0);
    }

    private static final void setupUI$lambda$2(QuickExportDialog this$0, ActionEvent it) {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor().withTitle("Select Output Directory").withDescription("Choose where to save the exported diagram");
        FileChooser.chooseFile((FileChooserDescriptor)descriptor, (Project)this$0.project, null, file -> QuickExportDialog.setupUI$lambda$2$lambda$0(this$0, (VirtualFile)file));
    }
}
