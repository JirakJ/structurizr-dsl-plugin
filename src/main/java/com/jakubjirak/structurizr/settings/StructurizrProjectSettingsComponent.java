/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.fileChooser.FileChooser
 *  com.intellij.openapi.fileChooser.FileChooserDescriptor
 *  com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
 *  com.intellij.openapi.project.Project
 *  com.intellij.openapi.ui.TextFieldWithBrowseButton
 *  com.intellij.openapi.vfs.VirtualFile
 *  com.intellij.ui.JBIntSpinner
 *  com.intellij.ui.components.JBCheckBox
 *  com.intellij.ui.dsl.builder.Align
 *  com.intellij.ui.dsl.builder.AlignX$FILL
 *  com.intellij.ui.dsl.builder.BuilderKt
 *  com.intellij.ui.dsl.builder.Cell
 *  com.intellij.ui.dsl.builder.Panel
 *  com.intellij.ui.dsl.builder.Row
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.settings;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBIntSpinner;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.dsl.builder.Align;
import com.intellij.ui.dsl.builder.AlignX;
import com.intellij.ui.dsl.builder.BuilderKt;
import com.intellij.ui.dsl.builder.Cell;
import com.intellij.ui.dsl.builder.HyperlinkEventAction;
import com.intellij.ui.dsl.builder.Panel;
import com.intellij.ui.dsl.builder.Row;
import com.jakubjirak.structurizr.settings.StructurizrProjectSettings;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JPanel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001a"}, d2={"Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettingsComponent;", "", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "useProjectCliPathCheckbox", "Lcom/intellij/ui/components/JBCheckBox;", "projectCliPathField", "Lcom/intellij/openapi/ui/TextFieldWithBrowseButton;", "defaultWorkspaceField", "projectExportDirectoryField", "overridePreviewCheckbox", "projectPreviewDelaySpinner", "Lcom/intellij/ui/JBIntSpinner;", "panel", "Ljavax/swing/JPanel;", "getPanel", "()Ljavax/swing/JPanel;", "isModified", "", "settings", "Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettings;", "applyTo", "", "resetFrom", "structurizr-dsl-plugin"})
public final class StructurizrProjectSettingsComponent {
    @NotNull
    private final Project project;
    @NotNull
    private final JBCheckBox useProjectCliPathCheckbox;
    @NotNull
    private TextFieldWithBrowseButton projectCliPathField;
    @NotNull
    private TextFieldWithBrowseButton defaultWorkspaceField;
    @NotNull
    private TextFieldWithBrowseButton projectExportDirectoryField;
    @NotNull
    private final JBCheckBox overridePreviewCheckbox;
    @NotNull
    private final JBIntSpinner projectPreviewDelaySpinner;
    @NotNull
    private final JPanel panel;

    public StructurizrProjectSettingsComponent(@NotNull Project project) {
        TextFieldWithBrowseButton $this$projectExportDirectoryField_u24lambda_u2411;
        TextFieldWithBrowseButton $this$defaultWorkspaceField_u24lambda_u247;
        TextFieldWithBrowseButton $this$projectCliPathField_u24lambda_u243;
        TextFieldWithBrowseButton textFieldWithBrowseButton;
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        this.project = project;
        this.useProjectCliPathCheckbox = new JBCheckBox("Use project-specific CLI path");
        TextFieldWithBrowseButton textFieldWithBrowseButton2 = textFieldWithBrowseButton = new TextFieldWithBrowseButton();
        $this$projectCliPathField_u24lambda_u243 = textFieldWithBrowseButton;
        StructurizrProjectSettingsComponent structurizrProjectSettingsComponent = this;
        boolean bl = false;
        $this$projectCliPathField_u24lambda_u243.addActionListener(arg_0 -> StructurizrProjectSettingsComponent.projectCliPathField$lambda$3$lambda$2(this, $this$projectCliPathField_u24lambda_u243, arg_0));
        structurizrProjectSettingsComponent.projectCliPathField = textFieldWithBrowseButton;
        $this$defaultWorkspaceField_u24lambda_u247 = textFieldWithBrowseButton = new TextFieldWithBrowseButton();
        structurizrProjectSettingsComponent = this;
        boolean bl2 = false;
        $this$defaultWorkspaceField_u24lambda_u247.addActionListener(arg_0 -> StructurizrProjectSettingsComponent.defaultWorkspaceField$lambda$7$lambda$6(this, $this$defaultWorkspaceField_u24lambda_u247, arg_0));
        structurizrProjectSettingsComponent.defaultWorkspaceField = textFieldWithBrowseButton;
        $this$projectExportDirectoryField_u24lambda_u2411 = textFieldWithBrowseButton = new TextFieldWithBrowseButton();
        structurizrProjectSettingsComponent = this;
        boolean bl3 = false;
        $this$projectExportDirectoryField_u24lambda_u2411.addActionListener(arg_0 -> StructurizrProjectSettingsComponent.projectExportDirectoryField$lambda$11$lambda$10(this, (TextFieldWithBrowseButton)$this$projectExportDirectoryField_u24lambda_u2411, arg_0));
        structurizrProjectSettingsComponent.projectExportDirectoryField = textFieldWithBrowseButton;
        this.overridePreviewCheckbox = new JBCheckBox("Override global preview settings");
        this.projectPreviewDelaySpinner = new JBIntSpinner(500, 100, 5000, 100);
        this.panel = (JPanel)BuilderKt.panel(arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23(this, arg_0));
    }

    @NotNull
    public final JPanel getPanel() {
        return this.panel;
    }

    public final boolean isModified(@NotNull StructurizrProjectSettings settings) {
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        return this.useProjectCliPathCheckbox.isSelected() != settings.getUseProjectCliPath() || !Intrinsics.areEqual((Object)this.projectCliPathField.getText(), (Object)settings.getProjectCliPath()) || !Intrinsics.areEqual((Object)this.defaultWorkspaceField.getText(), (Object)settings.getDefaultWorkspaceFile()) || !Intrinsics.areEqual((Object)this.projectExportDirectoryField.getText(), (Object)settings.getProjectExportDirectory()) || this.overridePreviewCheckbox.isSelected() != settings.getOverridePreviewSettings() || this.projectPreviewDelaySpinner.getNumber() != settings.getProjectPreviewDelayMs();
    }

    public final void applyTo(@NotNull StructurizrProjectSettings settings) {
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        settings.setUseProjectCliPath(this.useProjectCliPathCheckbox.isSelected());
        settings.setProjectCliPath(this.projectCliPathField.getText());
        settings.setDefaultWorkspaceFile(this.defaultWorkspaceField.getText());
        settings.setProjectExportDirectory(this.projectExportDirectoryField.getText());
        settings.setOverridePreviewSettings(this.overridePreviewCheckbox.isSelected());
        settings.setProjectPreviewDelayMs(this.projectPreviewDelaySpinner.getNumber());
    }

    public final void resetFrom(@NotNull StructurizrProjectSettings settings) {
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        this.useProjectCliPathCheckbox.setSelected(settings.getUseProjectCliPath());
        this.projectCliPathField.setText(settings.getProjectCliPath());
        this.defaultWorkspaceField.setText(settings.getDefaultWorkspaceFile());
        this.projectExportDirectoryField.setText(settings.getProjectExportDirectory());
        this.overridePreviewCheckbox.setSelected(settings.getOverridePreviewSettings());
        this.projectPreviewDelaySpinner.setNumber(settings.getProjectPreviewDelayMs());
    }

    private static final Unit projectCliPathField$lambda$3$lambda$2$lambda$0(TextFieldWithBrowseButton $this_apply, VirtualFile file) {
        $this_apply.setText(file.getPath());
        return Unit.INSTANCE;
    }

    private static final void projectCliPathField$lambda$3$lambda$2$lambda$1(Function1 $tmp0, Object p0) {
        $tmp0.invoke(p0);
    }

    private static final void projectCliPathField$lambda$3$lambda$2(StructurizrProjectSettingsComponent this$0, TextFieldWithBrowseButton $this_apply, ActionEvent it) {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor((String)"jar").withTitle("Select Structurizr CLI").withDescription("Select the Structurizr CLI executable for this project");
        FileChooser.chooseFile((FileChooserDescriptor)descriptor, (Project)this$0.project, null, file -> StructurizrProjectSettingsComponent.projectCliPathField$lambda$3$lambda$2$lambda$1(f -> StructurizrProjectSettingsComponent.projectCliPathField$lambda$3$lambda$2$lambda$0($this_apply, (VirtualFile)f), file));
    }

    private static final Unit defaultWorkspaceField$lambda$7$lambda$6$lambda$4(TextFieldWithBrowseButton $this_apply, VirtualFile file) {
        $this_apply.setText(file.getPath());
        return Unit.INSTANCE;
    }

    private static final void defaultWorkspaceField$lambda$7$lambda$6$lambda$5(Function1 $tmp0, Object p0) {
        $tmp0.invoke(p0);
    }

    private static final void defaultWorkspaceField$lambda$7$lambda$6(StructurizrProjectSettingsComponent this$0, TextFieldWithBrowseButton $this_apply, ActionEvent it) {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor((String)"dsl").withTitle("Select Default Workspace").withDescription("Select the default workspace DSL file");
        FileChooser.chooseFile((FileChooserDescriptor)descriptor, (Project)this$0.project, null, file -> StructurizrProjectSettingsComponent.defaultWorkspaceField$lambda$7$lambda$6$lambda$5(f -> StructurizrProjectSettingsComponent.defaultWorkspaceField$lambda$7$lambda$6$lambda$4($this_apply, (VirtualFile)f), file));
    }

    private static final Unit projectExportDirectoryField$lambda$11$lambda$10$lambda$8(TextFieldWithBrowseButton $this_apply, VirtualFile file) {
        $this_apply.setText(file.getPath());
        return Unit.INSTANCE;
    }

    private static final void projectExportDirectoryField$lambda$11$lambda$10$lambda$9(Function1 $tmp0, Object p0) {
        $tmp0.invoke(p0);
    }

    private static final void projectExportDirectoryField$lambda$11$lambda$10(StructurizrProjectSettingsComponent this$0, TextFieldWithBrowseButton $this_apply, ActionEvent it) {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor().withTitle("Select Export Directory").withDescription("Select the export directory for this project");
        FileChooser.chooseFile((FileChooserDescriptor)descriptor, (Project)this$0.project, null, file -> StructurizrProjectSettingsComponent.projectExportDirectoryField$lambda$11$lambda$10$lambda$9(f -> StructurizrProjectSettingsComponent.projectExportDirectoryField$lambda$11$lambda$10$lambda$8($this_apply, (VirtualFile)f), file));
    }

    private static final Unit panel$lambda$23$lambda$14$lambda$12(StructurizrProjectSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.useProjectCliPathCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23$lambda$14$lambda$13(StructurizrProjectSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.projectCliPathField).align(Align.FILL);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23$lambda$14(StructurizrProjectSettingsComponent this$0, Panel $this$group) {
        Intrinsics.checkNotNullParameter((Object)$this$group, (String)"$this$group");
        $this$group.row("", arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23$lambda$14$lambda$12(this$0, arg_0));
        $this$group.row("Project CLI path:", arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23$lambda$14$lambda$13(this$0, arg_0));
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23$lambda$16$lambda$15(StructurizrProjectSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.defaultWorkspaceField).align(Align.FILL).comment((String)"The default .dsl file to use for this project", 70, HyperlinkEventAction.HTML_HYPERLINK_INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23$lambda$16(StructurizrProjectSettingsComponent this$0, Panel $this$group) {
        Intrinsics.checkNotNullParameter((Object)$this$group, (String)"$this$group");
        $this$group.row("Default workspace file:", arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23$lambda$16$lambda$15(this$0, arg_0));
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23$lambda$18$lambda$17(StructurizrProjectSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.projectExportDirectoryField).align(Align.FILL).comment((String)"Leave empty to use global setting or source file directory", 70, HyperlinkEventAction.HTML_HYPERLINK_INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23$lambda$18(StructurizrProjectSettingsComponent this$0, Panel $this$group) {
        Intrinsics.checkNotNullParameter((Object)$this$group, (String)"$this$group");
        $this$group.row("Export directory:", arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23$lambda$18$lambda$17(this$0, arg_0));
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23$lambda$21$lambda$19(StructurizrProjectSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.overridePreviewCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23$lambda$21$lambda$20(StructurizrProjectSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.projectPreviewDelaySpinner);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23$lambda$21(StructurizrProjectSettingsComponent this$0, Panel $this$group) {
        Intrinsics.checkNotNullParameter((Object)$this$group, (String)"$this$group");
        $this$group.row("", arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23$lambda$21$lambda$19(this$0, arg_0));
        $this$group.row("Preview delay (ms):", arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23$lambda$21$lambda$20(this$0, arg_0));
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23$lambda$22(Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.comment((String)"Project settings override global Structurizr DSL settings.", 70, HyperlinkEventAction.HTML_HYPERLINK_INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$23(StructurizrProjectSettingsComponent this$0, Panel $this$panel) {
        Intrinsics.checkNotNullParameter((Object)$this$panel, (String)"$this$panel");
        $this$panel.group((String)"CLI Configuration", false, arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23$lambda$14(this$0, arg_0));
        $this$panel.group((String)"Project Workspace", false, arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23$lambda$16(this$0, arg_0));
        $this$panel.group((String)"Export Configuration", false, arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23$lambda$18(this$0, arg_0));
        $this$panel.group((String)"Preview Configuration", false, arg_0 -> StructurizrProjectSettingsComponent.panel$lambda$23$lambda$21(this$0, arg_0));
        $this$panel.row("", StructurizrProjectSettingsComponent::panel$lambda$23$lambda$22);
        return Unit.INSTANCE;
    }
}
