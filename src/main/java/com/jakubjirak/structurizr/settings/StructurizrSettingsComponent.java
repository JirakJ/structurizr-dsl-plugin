/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.fileChooser.FileChooser
 *  com.intellij.openapi.fileChooser.FileChooserDescriptor
 *  com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
 *  com.intellij.openapi.ui.ComboBox
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
 *  kotlin.jvm.internal.SourceDebugExtension
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.settings;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.ComboBox;
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
import com.jakubjirak.structurizr.settings.StructurizrSettings;
import java.awt.event.ActionEvent;
import java.util.Collection;
import javax.swing.JComponent;
import javax.swing.JPanel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020*2\u0006\u0010'\u001a\u00020(J\u000e\u0010+\u001a\u00020*2\u0006\u0010'\u001a\u00020(R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\u00130\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010!\u001a\u00020\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$\u00a8\u0006,"}, d2={"Lcom/jakubjirak/structurizr/settings/StructurizrSettingsComponent;", "", "<init>", "()V", "cliPathField", "Lcom/intellij/openapi/ui/TextFieldWithBrowseButton;", "useEmbeddedCliCheckbox", "Lcom/intellij/ui/components/JBCheckBox;", "cliTimeoutSpinner", "Lcom/intellij/ui/JBIntSpinner;", "enablePreviewCheckbox", "autoRefreshCheckbox", "previewDelaySpinner", "showSplitEditorCheckbox", "previewZoomSpinner", "previewDarkModeCheckbox", "previewUseSystemThemeCheckbox", "exportFormatCombo", "Lcom/intellij/openapi/ui/ComboBox;", "", "kotlin.jvm.PlatformType", "exportDirectoryField", "exportWidthSpinner", "exportHeightSpinner", "exportMetadataCheckbox", "autoOpenExportCheckbox", "showBreadcrumbsCheckbox", "enableFoldingCheckbox", "showLineMarkersCheckbox", "enableInspectionsCheckbox", "autoPopupCompletionCheckbox", "includeReferencesCheckbox", "showDocInCompletionCheckbox", "panel", "Ljavax/swing/JPanel;", "getPanel", "()Ljavax/swing/JPanel;", "isModified", "", "settings", "Lcom/jakubjirak/structurizr/settings/StructurizrSettings;", "applyTo", "", "resetFrom", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrSettingsConfigurable.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrSettingsConfigurable.kt\ncom/jakubjirak/structurizr/settings/StructurizrSettingsComponent\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,287:1\n37#2,2:288\n*S KotlinDebug\n*F\n+ 1 StructurizrSettingsConfigurable.kt\ncom/jakubjirak/structurizr/settings/StructurizrSettingsComponent\n*L\n85#1:288,2\n*E\n"})
public final class StructurizrSettingsComponent {
    @NotNull
    private TextFieldWithBrowseButton cliPathField;
    @NotNull
    private final JBCheckBox useEmbeddedCliCheckbox;
    @NotNull
    private final JBIntSpinner cliTimeoutSpinner;
    @NotNull
    private final JBCheckBox enablePreviewCheckbox;
    @NotNull
    private final JBCheckBox autoRefreshCheckbox;
    @NotNull
    private final JBIntSpinner previewDelaySpinner;
    @NotNull
    private final JBCheckBox showSplitEditorCheckbox;
    @NotNull
    private final JBIntSpinner previewZoomSpinner;
    @NotNull
    private final JBCheckBox previewDarkModeCheckbox;
    @NotNull
    private final JBCheckBox previewUseSystemThemeCheckbox;
    @NotNull
    private final ComboBox<String> exportFormatCombo;
    @NotNull
    private TextFieldWithBrowseButton exportDirectoryField;
    @NotNull
    private final JBIntSpinner exportWidthSpinner;
    @NotNull
    private final JBIntSpinner exportHeightSpinner;
    @NotNull
    private final JBCheckBox exportMetadataCheckbox;
    @NotNull
    private final JBCheckBox autoOpenExportCheckbox;
    @NotNull
    private final JBCheckBox showBreadcrumbsCheckbox;
    @NotNull
    private final JBCheckBox enableFoldingCheckbox;
    @NotNull
    private final JBCheckBox showLineMarkersCheckbox;
    @NotNull
    private final JBCheckBox enableInspectionsCheckbox;
    @NotNull
    private final JBCheckBox autoPopupCompletionCheckbox;
    @NotNull
    private final JBCheckBox includeReferencesCheckbox;
    @NotNull
    private final JBCheckBox showDocInCompletionCheckbox;
    @NotNull
    private final JPanel panel;

    public StructurizrSettingsComponent() {
        TextFieldWithBrowseButton $this$exportDirectoryField_u24lambda_u247;
        TextFieldWithBrowseButton $this$cliPathField_u24lambda_u243;
        TextFieldWithBrowseButton textFieldWithBrowseButton;
        TextFieldWithBrowseButton textFieldWithBrowseButton2 = textFieldWithBrowseButton = new TextFieldWithBrowseButton();
        $this$cliPathField_u24lambda_u243 = textFieldWithBrowseButton;
        StructurizrSettingsComponent structurizrSettingsComponent = this;
        boolean bl = false;
        $this$cliPathField_u24lambda_u243.addActionListener(arg_0 -> StructurizrSettingsComponent.cliPathField$lambda$3$lambda$2((TextFieldWithBrowseButton)$this$cliPathField_u24lambda_u243, arg_0));
        structurizrSettingsComponent.cliPathField = textFieldWithBrowseButton;
        this.useEmbeddedCliCheckbox = new JBCheckBox("Use embedded CLI if available");
        this.cliTimeoutSpinner = new JBIntSpinner(30, 5, 300, 5);
        this.enablePreviewCheckbox = new JBCheckBox("Enable live preview");
        this.autoRefreshCheckbox = new JBCheckBox("Auto-refresh preview on save");
        this.previewDelaySpinner = new JBIntSpinner(500, 100, 5000, 100);
        this.showSplitEditorCheckbox = new JBCheckBox("Show preview in split editor by default");
        this.previewZoomSpinner = new JBIntSpinner(100, 25, 400, 25);
        this.previewDarkModeCheckbox = new JBCheckBox("Dark mode for preview");
        this.previewUseSystemThemeCheckbox = new JBCheckBox("Use system theme");
        Collection $this$toTypedArray$iv = StructurizrSettings.Companion.getEXPORT_FORMATS();
        boolean $i$f$toTypedArray22 = false;
        Collection thisCollection$iv = $this$toTypedArray$iv;
        this.exportFormatCombo = new ComboBox((Object[])thisCollection$iv.toArray(new String[0]));
        TextFieldWithBrowseButton textFieldWithBrowseButton3 = textFieldWithBrowseButton = new TextFieldWithBrowseButton();
        $this$exportDirectoryField_u24lambda_u247 = textFieldWithBrowseButton;
        structurizrSettingsComponent = this;
        boolean bl2 = false;
        $this$exportDirectoryField_u24lambda_u247.addActionListener(arg_0 -> StructurizrSettingsComponent.exportDirectoryField$lambda$7$lambda$6((TextFieldWithBrowseButton)$this$exportDirectoryField_u24lambda_u247, arg_0));
        structurizrSettingsComponent.exportDirectoryField = textFieldWithBrowseButton;
        this.exportWidthSpinner = new JBIntSpinner(1920, 320, 7680, 160);
        this.exportHeightSpinner = new JBIntSpinner(1080, 240, 4320, 120);
        this.exportMetadataCheckbox = new JBCheckBox("Include metadata in exports");
        this.autoOpenExportCheckbox = new JBCheckBox("Auto-open exported file");
        this.showBreadcrumbsCheckbox = new JBCheckBox("Show breadcrumbs");
        this.enableFoldingCheckbox = new JBCheckBox("Enable code folding");
        this.showLineMarkersCheckbox = new JBCheckBox("Show line markers");
        this.enableInspectionsCheckbox = new JBCheckBox("Enable inspections");
        this.autoPopupCompletionCheckbox = new JBCheckBox("Auto-popup completion");
        this.includeReferencesCheckbox = new JBCheckBox("Include element references in completion");
        this.showDocInCompletionCheckbox = new JBCheckBox("Show documentation in completion popup");
        this.panel = (JPanel)BuilderKt.panel(arg_0 -> StructurizrSettingsComponent.panel$lambda$36(this, arg_0));
    }

    @NotNull
    public final JPanel getPanel() {
        return this.panel;
    }

    public final boolean isModified(@NotNull StructurizrSettings settings) {
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        return !Intrinsics.areEqual((Object)this.cliPathField.getText(), (Object)settings.getStructurizrCliPath()) || this.useEmbeddedCliCheckbox.isSelected() != settings.getUseEmbeddedCli() || this.cliTimeoutSpinner.getNumber() != settings.getCliTimeoutSeconds() || this.enablePreviewCheckbox.isSelected() != settings.getEnableLivePreview() || this.autoRefreshCheckbox.isSelected() != settings.getAutoRefreshPreview() || this.previewDelaySpinner.getNumber() != settings.getPreviewDelayMs() || this.showSplitEditorCheckbox.isSelected() != settings.getShowPreviewInSplitEditor() || this.previewZoomSpinner.getNumber() != settings.getPreviewZoomLevel() || this.previewDarkModeCheckbox.isSelected() != settings.getPreviewDarkMode() || this.previewUseSystemThemeCheckbox.isSelected() != settings.getPreviewUseSystemTheme() || !Intrinsics.areEqual((Object)this.exportFormatCombo.getSelectedItem(), (Object)settings.getDefaultExportFormat()) || !Intrinsics.areEqual((Object)this.exportDirectoryField.getText(), (Object)settings.getDefaultExportDirectory()) || this.exportWidthSpinner.getNumber() != settings.getExportImageWidth() || this.exportHeightSpinner.getNumber() != settings.getExportImageHeight() || this.exportMetadataCheckbox.isSelected() != settings.getExportIncludeMetadata() || this.autoOpenExportCheckbox.isSelected() != settings.getAutoOpenExportedFile() || this.showBreadcrumbsCheckbox.isSelected() != settings.getShowBreadcrumbs() || this.enableFoldingCheckbox.isSelected() != settings.getEnableCodeFolding() || this.showLineMarkersCheckbox.isSelected() != settings.getShowLineMarkers() || this.enableInspectionsCheckbox.isSelected() != settings.getEnableInspections() || this.autoPopupCompletionCheckbox.isSelected() != settings.getAutoPopupCompletion() || this.includeReferencesCheckbox.isSelected() != settings.getIncludeReferencesInCompletion() || this.showDocInCompletionCheckbox.isSelected() != settings.getShowDocumentationInCompletion();
    }

    public final void applyTo(@NotNull StructurizrSettings settings) {
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        settings.setStructurizrCliPath(this.cliPathField.getText());
        settings.setUseEmbeddedCli(this.useEmbeddedCliCheckbox.isSelected());
        settings.setCliTimeoutSeconds(this.cliTimeoutSpinner.getNumber());
        settings.setEnableLivePreview(this.enablePreviewCheckbox.isSelected());
        settings.setAutoRefreshPreview(this.autoRefreshCheckbox.isSelected());
        settings.setPreviewDelayMs(this.previewDelaySpinner.getNumber());
        settings.setShowPreviewInSplitEditor(this.showSplitEditorCheckbox.isSelected());
        settings.setPreviewZoomLevel(this.previewZoomSpinner.getNumber());
        settings.setPreviewDarkMode(this.previewDarkModeCheckbox.isSelected());
        settings.setPreviewUseSystemTheme(this.previewUseSystemThemeCheckbox.isSelected());
        Object object = this.exportFormatCombo.getSelectedItem();
        Intrinsics.checkNotNull((Object)object, (String)"null cannot be cast to non-null type kotlin.String");
        settings.setDefaultExportFormat((String)object);
        settings.setDefaultExportDirectory(this.exportDirectoryField.getText());
        settings.setExportImageWidth(this.exportWidthSpinner.getNumber());
        settings.setExportImageHeight(this.exportHeightSpinner.getNumber());
        settings.setExportIncludeMetadata(this.exportMetadataCheckbox.isSelected());
        settings.setAutoOpenExportedFile(this.autoOpenExportCheckbox.isSelected());
        settings.setShowBreadcrumbs(this.showBreadcrumbsCheckbox.isSelected());
        settings.setEnableCodeFolding(this.enableFoldingCheckbox.isSelected());
        settings.setShowLineMarkers(this.showLineMarkersCheckbox.isSelected());
        settings.setEnableInspections(this.enableInspectionsCheckbox.isSelected());
        settings.setAutoPopupCompletion(this.autoPopupCompletionCheckbox.isSelected());
        settings.setIncludeReferencesInCompletion(this.includeReferencesCheckbox.isSelected());
        settings.setShowDocumentationInCompletion(this.showDocInCompletionCheckbox.isSelected());
    }

    public final void resetFrom(@NotNull StructurizrSettings settings) {
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        this.cliPathField.setText(settings.getStructurizrCliPath());
        this.useEmbeddedCliCheckbox.setSelected(settings.getUseEmbeddedCli());
        this.cliTimeoutSpinner.setNumber(settings.getCliTimeoutSeconds());
        this.enablePreviewCheckbox.setSelected(settings.getEnableLivePreview());
        this.autoRefreshCheckbox.setSelected(settings.getAutoRefreshPreview());
        this.previewDelaySpinner.setNumber(settings.getPreviewDelayMs());
        this.showSplitEditorCheckbox.setSelected(settings.getShowPreviewInSplitEditor());
        this.previewZoomSpinner.setNumber(settings.getPreviewZoomLevel());
        this.previewDarkModeCheckbox.setSelected(settings.getPreviewDarkMode());
        this.previewUseSystemThemeCheckbox.setSelected(settings.getPreviewUseSystemTheme());
        this.exportFormatCombo.setSelectedItem((Object)settings.getDefaultExportFormat());
        this.exportDirectoryField.setText(settings.getDefaultExportDirectory());
        this.exportWidthSpinner.setNumber(settings.getExportImageWidth());
        this.exportHeightSpinner.setNumber(settings.getExportImageHeight());
        this.exportMetadataCheckbox.setSelected(settings.getExportIncludeMetadata());
        this.autoOpenExportCheckbox.setSelected(settings.getAutoOpenExportedFile());
        this.showBreadcrumbsCheckbox.setSelected(settings.getShowBreadcrumbs());
        this.enableFoldingCheckbox.setSelected(settings.getEnableCodeFolding());
        this.showLineMarkersCheckbox.setSelected(settings.getShowLineMarkers());
        this.enableInspectionsCheckbox.setSelected(settings.getEnableInspections());
        this.autoPopupCompletionCheckbox.setSelected(settings.getAutoPopupCompletion());
        this.includeReferencesCheckbox.setSelected(settings.getIncludeReferencesInCompletion());
        this.showDocInCompletionCheckbox.setSelected(settings.getShowDocumentationInCompletion());
    }

    private static final Unit cliPathField$lambda$3$lambda$2$lambda$0(TextFieldWithBrowseButton $this_apply, VirtualFile file) {
        $this_apply.setText(file.getPath());
        return Unit.INSTANCE;
    }

    private static final void cliPathField$lambda$3$lambda$2$lambda$1(Function1 $tmp0, Object p0) {
        $tmp0.invoke(p0);
    }

    private static final void cliPathField$lambda$3$lambda$2(TextFieldWithBrowseButton $this_apply, ActionEvent it) {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor((String)"jar").withTitle("Select Structurizr CLI").withDescription("Select the Structurizr CLI executable");
        FileChooser.chooseFile((FileChooserDescriptor)descriptor, null, null, file -> StructurizrSettingsComponent.cliPathField$lambda$3$lambda$2$lambda$1(f -> StructurizrSettingsComponent.cliPathField$lambda$3$lambda$2$lambda$0($this_apply, (VirtualFile)f), file));
    }

    private static final Unit exportDirectoryField$lambda$7$lambda$6$lambda$4(TextFieldWithBrowseButton $this_apply, VirtualFile file) {
        $this_apply.setText(file.getPath());
        return Unit.INSTANCE;
    }

    private static final void exportDirectoryField$lambda$7$lambda$6$lambda$5(Function1 $tmp0, Object p0) {
        $tmp0.invoke(p0);
    }

    private static final void exportDirectoryField$lambda$7$lambda$6(TextFieldWithBrowseButton $this_apply, ActionEvent it) {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor().withTitle("Select Export Directory").withDescription("Select the default export directory");
        FileChooser.chooseFile((FileChooserDescriptor)descriptor, null, null, file -> StructurizrSettingsComponent.exportDirectoryField$lambda$7$lambda$6$lambda$5(f -> StructurizrSettingsComponent.exportDirectoryField$lambda$7$lambda$6$lambda$4($this_apply, (VirtualFile)f), file));
    }

    private static final Unit panel$lambda$36$lambda$11$lambda$8(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.cliPathField).align(Align.FILL).comment((String)"Path to structurizr-cli JAR file or executable", 70, HyperlinkEventAction.HTML_HYPERLINK_INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$11$lambda$9(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.useEmbeddedCliCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$11$lambda$10(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.cliTimeoutSpinner).comment((String)"Maximum time to wait for CLI operations", 70, HyperlinkEventAction.HTML_HYPERLINK_INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$11(StructurizrSettingsComponent this$0, Panel $this$group) {
        Intrinsics.checkNotNullParameter((Object)$this$group, (String)"$this$group");
        $this$group.row("CLI path:", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$11$lambda$8(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$11$lambda$9(this$0, arg_0));
        $this$group.row("Timeout (seconds):", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$11$lambda$10(this$0, arg_0));
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$19$lambda$12(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.enablePreviewCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$19$lambda$13(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.autoRefreshCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$19$lambda$14(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.previewDelaySpinner).comment((String)"Delay before refreshing preview after typing", 70, HyperlinkEventAction.HTML_HYPERLINK_INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$19$lambda$15(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.showSplitEditorCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$19$lambda$16(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.previewZoomSpinner);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$19$lambda$17(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.previewUseSystemThemeCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$19$lambda$18(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.previewDarkModeCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$19(StructurizrSettingsComponent this$0, Panel $this$group) {
        Intrinsics.checkNotNullParameter((Object)$this$group, (String)"$this$group");
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$19$lambda$12(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$19$lambda$13(this$0, arg_0));
        $this$group.row("Refresh delay (ms):", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$19$lambda$14(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$19$lambda$15(this$0, arg_0));
        $this$group.row("Zoom level (%):", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$19$lambda$16(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$19$lambda$17(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$19$lambda$18(this$0, arg_0));
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$26$lambda$20(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.exportFormatCombo);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$26$lambda$21(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.exportDirectoryField).align(Align.FILL).comment((String)"Leave empty to use source file directory", 70, HyperlinkEventAction.HTML_HYPERLINK_INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$26$lambda$22(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.exportWidthSpinner);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$26$lambda$23(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.exportHeightSpinner);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$26$lambda$24(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.exportMetadataCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$26$lambda$25(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.autoOpenExportCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$26(StructurizrSettingsComponent this$0, Panel $this$group) {
        Intrinsics.checkNotNullParameter((Object)$this$group, (String)"$this$group");
        $this$group.row("Default format:", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$26$lambda$20(this$0, arg_0));
        $this$group.row("Default directory:", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$26$lambda$21(this$0, arg_0));
        $this$group.row("Image width:", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$26$lambda$22(this$0, arg_0));
        $this$group.row("Image height:", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$26$lambda$23(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$26$lambda$24(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$26$lambda$25(this$0, arg_0));
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$31$lambda$27(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.showBreadcrumbsCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$31$lambda$28(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.enableFoldingCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$31$lambda$29(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.showLineMarkersCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$31$lambda$30(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.enableInspectionsCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$31(StructurizrSettingsComponent this$0, Panel $this$group) {
        Intrinsics.checkNotNullParameter((Object)$this$group, (String)"$this$group");
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$31$lambda$27(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$31$lambda$28(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$31$lambda$29(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$31$lambda$30(this$0, arg_0));
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$35$lambda$32(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.autoPopupCompletionCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$35$lambda$33(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.includeReferencesCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$35$lambda$34(StructurizrSettingsComponent this$0, Row $this$row) {
        Intrinsics.checkNotNullParameter((Object)$this$row, (String)"$this$row");
        $this$row.cell((JComponent)this$0.showDocInCompletionCheckbox);
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36$lambda$35(StructurizrSettingsComponent this$0, Panel $this$group) {
        Intrinsics.checkNotNullParameter((Object)$this$group, (String)"$this$group");
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$35$lambda$32(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$35$lambda$33(this$0, arg_0));
        $this$group.row("", arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$35$lambda$34(this$0, arg_0));
        return Unit.INSTANCE;
    }

    private static final Unit panel$lambda$36(StructurizrSettingsComponent this$0, Panel $this$panel) {
        Intrinsics.checkNotNullParameter((Object)$this$panel, (String)"$this$panel");
        $this$panel.group((String)"Structurizr CLI", false, arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$11(this$0, arg_0));
        $this$panel.group((String)"Live Preview", false, arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$19(this$0, arg_0));
        $this$panel.group((String)"Export", false, arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$26(this$0, arg_0));
        $this$panel.group((String)"Editor", false, arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$31(this$0, arg_0));
        $this$panel.group((String)"Code Completion", false, arg_0 -> StructurizrSettingsComponent.panel$lambda$36$lambda$35(this$0, arg_0));
        return Unit.INSTANCE;
    }
}
