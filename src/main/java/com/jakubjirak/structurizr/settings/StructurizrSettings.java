/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.application.ApplicationManager
 *  com.intellij.openapi.components.PersistentStateComponent
 *  com.intellij.openapi.components.State
 *  com.intellij.openapi.components.Storage
 *  com.intellij.util.xmlb.XmlSerializerUtil
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@State(name="StructurizrSettings", storages={@Storage(value="structurizr.xml")})
@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\bB\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 V2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001VB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010R\u001a\u00020\u0000H\u0016J\u0010\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020\u0000H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR\u001a\u0010\u0019\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR\u001a\u0010\u001c\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R\u001a\u0010\u001f\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\r\"\u0004\b!\u0010\u000fR\u001a\u0010\"\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R\u001a\u0010%\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\r\"\u0004\b'\u0010\u000fR\u001a\u0010(\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\r\"\u0004\b*\u0010\u000fR\u001a\u0010+\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0007\"\u0004\b-\u0010\tR\u001a\u0010.\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0007\"\u0004\b0\u0010\tR\u001a\u00101\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0013\"\u0004\b3\u0010\u0015R\u001a\u00104\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0013\"\u0004\b6\u0010\u0015R\u001a\u00107\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\r\"\u0004\b9\u0010\u000fR\u001a\u0010:\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\r\"\u0004\b<\u0010\u000fR\u001a\u0010=\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\r\"\u0004\b?\u0010\u000fR\u001a\u0010@\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\r\"\u0004\bB\u0010\u000fR\u001a\u0010C\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\r\"\u0004\bE\u0010\u000fR\u001a\u0010F\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\r\"\u0004\bH\u0010\u000fR\u001a\u0010I\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\r\"\u0004\bK\u0010\u000fR\u001a\u0010L\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\r\"\u0004\bN\u0010\u000fR\u001a\u0010O\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\r\"\u0004\bQ\u0010\u000f\u00a8\u0006W"}, d2={"Lcom/jakubjirak/structurizr/settings/StructurizrSettings;", "Lcom/intellij/openapi/components/PersistentStateComponent;", "<init>", "()V", "structurizrCliPath", "", "getStructurizrCliPath", "()Ljava/lang/String;", "setStructurizrCliPath", "(Ljava/lang/String;)V", "useEmbeddedCli", "", "getUseEmbeddedCli", "()Z", "setUseEmbeddedCli", "(Z)V", "cliTimeoutSeconds", "", "getCliTimeoutSeconds", "()I", "setCliTimeoutSeconds", "(I)V", "enableLivePreview", "getEnableLivePreview", "setEnableLivePreview", "autoRefreshPreview", "getAutoRefreshPreview", "setAutoRefreshPreview", "previewDelayMs", "getPreviewDelayMs", "setPreviewDelayMs", "showPreviewInSplitEditor", "getShowPreviewInSplitEditor", "setShowPreviewInSplitEditor", "previewZoomLevel", "getPreviewZoomLevel", "setPreviewZoomLevel", "previewDarkMode", "getPreviewDarkMode", "setPreviewDarkMode", "previewUseSystemTheme", "getPreviewUseSystemTheme", "setPreviewUseSystemTheme", "defaultExportFormat", "getDefaultExportFormat", "setDefaultExportFormat", "defaultExportDirectory", "getDefaultExportDirectory", "setDefaultExportDirectory", "exportImageWidth", "getExportImageWidth", "setExportImageWidth", "exportImageHeight", "getExportImageHeight", "setExportImageHeight", "exportIncludeMetadata", "getExportIncludeMetadata", "setExportIncludeMetadata", "autoOpenExportedFile", "getAutoOpenExportedFile", "setAutoOpenExportedFile", "showBreadcrumbs", "getShowBreadcrumbs", "setShowBreadcrumbs", "enableCodeFolding", "getEnableCodeFolding", "setEnableCodeFolding", "showLineMarkers", "getShowLineMarkers", "setShowLineMarkers", "enableInspections", "getEnableInspections", "setEnableInspections", "autoPopupCompletion", "getAutoPopupCompletion", "setAutoPopupCompletion", "includeReferencesInCompletion", "getIncludeReferencesInCompletion", "setIncludeReferencesInCompletion", "showDocumentationInCompletion", "getShowDocumentationInCompletion", "setShowDocumentationInCompletion", "getState", "loadState", "", "state", "Companion", "structurizr-dsl-plugin"})
public final class StructurizrSettings
implements PersistentStateComponent<StructurizrSettings> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private String structurizrCliPath = "";
    private boolean useEmbeddedCli = true;
    private int cliTimeoutSeconds = 30;
    private boolean enableLivePreview = true;
    private boolean autoRefreshPreview = true;
    private int previewDelayMs = 500;
    private boolean showPreviewInSplitEditor = true;
    private int previewZoomLevel = 100;
    private boolean previewDarkMode;
    private boolean previewUseSystemTheme = true;
    @NotNull
    private String defaultExportFormat = "PNG";
    @NotNull
    private String defaultExportDirectory = "";
    private int exportImageWidth = 1920;
    private int exportImageHeight = 1080;
    private boolean exportIncludeMetadata = true;
    private boolean autoOpenExportedFile = true;
    private boolean showBreadcrumbs = true;
    private boolean enableCodeFolding = true;
    private boolean showLineMarkers = true;
    private boolean enableInspections = true;
    private boolean autoPopupCompletion = true;
    private boolean includeReferencesInCompletion = true;
    private boolean showDocumentationInCompletion = true;
    @NotNull
    public static final String FORMAT_PNG = "PNG";
    @NotNull
    public static final String FORMAT_SVG = "SVG";
    @NotNull
    public static final String FORMAT_PLANTUML = "PlantUML";
    @NotNull
    public static final String FORMAT_MERMAID = "Mermaid";
    @NotNull
    public static final String FORMAT_DOT = "DOT";
    @NotNull
    public static final String FORMAT_JSON = "JSON";
    @NotNull
    private static final List<String> EXPORT_FORMATS;

    @NotNull
    public final String getStructurizrCliPath() {
        return this.structurizrCliPath;
    }

    public final void setStructurizrCliPath(@NotNull String string) {
        Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
        this.structurizrCliPath = string;
    }

    public final boolean getUseEmbeddedCli() {
        return this.useEmbeddedCli;
    }

    public final void setUseEmbeddedCli(boolean bl) {
        this.useEmbeddedCli = bl;
    }

    public final int getCliTimeoutSeconds() {
        return this.cliTimeoutSeconds;
    }

    public final void setCliTimeoutSeconds(int n) {
        this.cliTimeoutSeconds = n;
    }

    public final boolean getEnableLivePreview() {
        return this.enableLivePreview;
    }

    public final void setEnableLivePreview(boolean bl) {
        this.enableLivePreview = bl;
    }

    public final boolean getAutoRefreshPreview() {
        return this.autoRefreshPreview;
    }

    public final void setAutoRefreshPreview(boolean bl) {
        this.autoRefreshPreview = bl;
    }

    public final int getPreviewDelayMs() {
        return this.previewDelayMs;
    }

    public final void setPreviewDelayMs(int n) {
        this.previewDelayMs = n;
    }

    public final boolean getShowPreviewInSplitEditor() {
        return this.showPreviewInSplitEditor;
    }

    public final void setShowPreviewInSplitEditor(boolean bl) {
        this.showPreviewInSplitEditor = bl;
    }

    public final int getPreviewZoomLevel() {
        return this.previewZoomLevel;
    }

    public final void setPreviewZoomLevel(int n) {
        this.previewZoomLevel = n;
    }

    public final boolean getPreviewDarkMode() {
        return this.previewDarkMode;
    }

    public final void setPreviewDarkMode(boolean bl) {
        this.previewDarkMode = bl;
    }

    public final boolean getPreviewUseSystemTheme() {
        return this.previewUseSystemTheme;
    }

    public final void setPreviewUseSystemTheme(boolean bl) {
        this.previewUseSystemTheme = bl;
    }

    @NotNull
    public final String getDefaultExportFormat() {
        return this.defaultExportFormat;
    }

    public final void setDefaultExportFormat(@NotNull String string) {
        Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
        this.defaultExportFormat = string;
    }

    @NotNull
    public final String getDefaultExportDirectory() {
        return this.defaultExportDirectory;
    }

    public final void setDefaultExportDirectory(@NotNull String string) {
        Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
        this.defaultExportDirectory = string;
    }

    public final int getExportImageWidth() {
        return this.exportImageWidth;
    }

    public final void setExportImageWidth(int n) {
        this.exportImageWidth = n;
    }

    public final int getExportImageHeight() {
        return this.exportImageHeight;
    }

    public final void setExportImageHeight(int n) {
        this.exportImageHeight = n;
    }

    public final boolean getExportIncludeMetadata() {
        return this.exportIncludeMetadata;
    }

    public final void setExportIncludeMetadata(boolean bl) {
        this.exportIncludeMetadata = bl;
    }

    public final boolean getAutoOpenExportedFile() {
        return this.autoOpenExportedFile;
    }

    public final void setAutoOpenExportedFile(boolean bl) {
        this.autoOpenExportedFile = bl;
    }

    public final boolean getShowBreadcrumbs() {
        return this.showBreadcrumbs;
    }

    public final void setShowBreadcrumbs(boolean bl) {
        this.showBreadcrumbs = bl;
    }

    public final boolean getEnableCodeFolding() {
        return this.enableCodeFolding;
    }

    public final void setEnableCodeFolding(boolean bl) {
        this.enableCodeFolding = bl;
    }

    public final boolean getShowLineMarkers() {
        return this.showLineMarkers;
    }

    public final void setShowLineMarkers(boolean bl) {
        this.showLineMarkers = bl;
    }

    public final boolean getEnableInspections() {
        return this.enableInspections;
    }

    public final void setEnableInspections(boolean bl) {
        this.enableInspections = bl;
    }

    public final boolean getAutoPopupCompletion() {
        return this.autoPopupCompletion;
    }

    public final void setAutoPopupCompletion(boolean bl) {
        this.autoPopupCompletion = bl;
    }

    public final boolean getIncludeReferencesInCompletion() {
        return this.includeReferencesInCompletion;
    }

    public final void setIncludeReferencesInCompletion(boolean bl) {
        this.includeReferencesInCompletion = bl;
    }

    public final boolean getShowDocumentationInCompletion() {
        return this.showDocumentationInCompletion;
    }

    public final void setShowDocumentationInCompletion(boolean bl) {
        this.showDocumentationInCompletion = bl;
    }

    @NotNull
    public StructurizrSettings getState() {
        return this;
    }

    public void loadState(@NotNull StructurizrSettings state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        XmlSerializerUtil.copyBean((Object)state, (Object)this);
    }

    static {
        String[] stringArray = new String[]{FORMAT_PNG, FORMAT_SVG, FORMAT_PLANTUML, FORMAT_MERMAID, FORMAT_DOT, FORMAT_JSON};
        EXPORT_FORMATS = CollectionsKt.listOf(stringArray);
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lcom/jakubjirak/structurizr/settings/StructurizrSettings$Companion;", "", "<init>", "()V", "getInstance", "Lcom/jakubjirak/structurizr/settings/StructurizrSettings;", "FORMAT_PNG", "", "FORMAT_SVG", "FORMAT_PLANTUML", "FORMAT_MERMAID", "FORMAT_DOT", "FORMAT_JSON", "EXPORT_FORMATS", "", "getEXPORT_FORMATS", "()Ljava/util/List;", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final StructurizrSettings getInstance() {
            Object object = ApplicationManager.getApplication().getService(StructurizrSettings.class);
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"getService(...)");
            return (StructurizrSettings)object;
        }

        @NotNull
        public final List<String> getEXPORT_FORMATS() {
            return EXPORT_FORMATS;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
