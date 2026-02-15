/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.components.PersistentStateComponent
 *  com.intellij.openapi.components.State
 *  com.intellij.openapi.components.Storage
 *  com.intellij.openapi.project.Project
 *  com.intellij.util.xmlb.XmlSerializerUtil
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.jakubjirak.structurizr.settings.StructurizrSettings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@State(name="StructurizrProjectSettings", storages={@Storage(value="$WORKSPACE_FILE$")})
@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 :2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001:B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010.\u001a\u00020\u0000H\u0016J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0000H\u0016J\u000e\u00102\u001a\u0002002\u0006\u00103\u001a\u00020\u000bJ\u000e\u00104\u001a\u0002002\u0006\u00105\u001a\u00020\u000bJ\u000e\u00106\u001a\u0002002\u0006\u00105\u001a\u00020\u000bJ\u0006\u00107\u001a\u00020\u000bJ\u0006\u00108\u001a\u00020\u001aJ\u0006\u00109\u001a\u00020\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020\u001aX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010\u001eR\u001a\u0010(\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\r\"\u0004\b*\u0010\u000fR \u0010+\u001a\b\u0012\u0004\u0012\u00020\u000b0 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\"\"\u0004\b-\u0010$\u00a8\u0006;"}, d2={"Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettings;", "Lcom/intellij/openapi/components/PersistentStateComponent;", "<init>", "()V", "useProjectCliPath", "", "getUseProjectCliPath", "()Z", "setUseProjectCliPath", "(Z)V", "projectCliPath", "", "getProjectCliPath", "()Ljava/lang/String;", "setProjectCliPath", "(Ljava/lang/String;)V", "defaultWorkspaceFile", "getDefaultWorkspaceFile", "setDefaultWorkspaceFile", "projectExportDirectory", "getProjectExportDirectory", "setProjectExportDirectory", "overridePreviewSettings", "getOverridePreviewSettings", "setOverridePreviewSettings", "projectPreviewDelayMs", "", "getProjectPreviewDelayMs", "()I", "setProjectPreviewDelayMs", "(I)V", "recentWorkspaceFiles", "", "getRecentWorkspaceFiles", "()Ljava/util/List;", "setRecentWorkspaceFiles", "(Ljava/util/List;)V", "maxRecentFiles", "getMaxRecentFiles", "setMaxRecentFiles", "lastSelectedView", "getLastSelectedView", "setLastSelectedView", "favoriteViews", "getFavoriteViews", "setFavoriteViews", "getState", "loadState", "", "state", "addRecentWorkspaceFile", "filePath", "addFavoriteView", "viewKey", "removeFavoriteView", "getEffectiveCliPath", "getEffectivePreviewDelay", "getEffectiveExportDirectory", "Companion", "structurizr-dsl-plugin"})
public final class StructurizrProjectSettings
implements PersistentStateComponent<StructurizrProjectSettings> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private boolean useProjectCliPath;
    @NotNull
    private String projectCliPath = "";
    @NotNull
    private String defaultWorkspaceFile = "";
    @NotNull
    private String projectExportDirectory = "";
    private boolean overridePreviewSettings;
    private int projectPreviewDelayMs = 500;
    @NotNull
    private List<String> recentWorkspaceFiles = new ArrayList();
    private int maxRecentFiles = 10;
    @NotNull
    private String lastSelectedView = "";
    @NotNull
    private List<String> favoriteViews = new ArrayList();

    public final boolean getUseProjectCliPath() {
        return this.useProjectCliPath;
    }

    public final void setUseProjectCliPath(boolean bl) {
        this.useProjectCliPath = bl;
    }

    @NotNull
    public final String getProjectCliPath() {
        return this.projectCliPath;
    }

    public final void setProjectCliPath(@NotNull String string) {
        Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
        this.projectCliPath = string;
    }

    @NotNull
    public final String getDefaultWorkspaceFile() {
        return this.defaultWorkspaceFile;
    }

    public final void setDefaultWorkspaceFile(@NotNull String string) {
        Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
        this.defaultWorkspaceFile = string;
    }

    @NotNull
    public final String getProjectExportDirectory() {
        return this.projectExportDirectory;
    }

    public final void setProjectExportDirectory(@NotNull String string) {
        Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
        this.projectExportDirectory = string;
    }

    public final boolean getOverridePreviewSettings() {
        return this.overridePreviewSettings;
    }

    public final void setOverridePreviewSettings(boolean bl) {
        this.overridePreviewSettings = bl;
    }

    public final int getProjectPreviewDelayMs() {
        return this.projectPreviewDelayMs;
    }

    public final void setProjectPreviewDelayMs(int n) {
        this.projectPreviewDelayMs = n;
    }

    @NotNull
    public final List<String> getRecentWorkspaceFiles() {
        return this.recentWorkspaceFiles;
    }

    public final void setRecentWorkspaceFiles(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, (String)"<set-?>");
        this.recentWorkspaceFiles = list;
    }

    public final int getMaxRecentFiles() {
        return this.maxRecentFiles;
    }

    public final void setMaxRecentFiles(int n) {
        this.maxRecentFiles = n;
    }

    @NotNull
    public final String getLastSelectedView() {
        return this.lastSelectedView;
    }

    public final void setLastSelectedView(@NotNull String string) {
        Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
        this.lastSelectedView = string;
    }

    @NotNull
    public final List<String> getFavoriteViews() {
        return this.favoriteViews;
    }

    public final void setFavoriteViews(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, (String)"<set-?>");
        this.favoriteViews = list;
    }

    @NotNull
    public StructurizrProjectSettings getState() {
        return this;
    }

    public void loadState(@NotNull StructurizrProjectSettings state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        XmlSerializerUtil.copyBean((Object)state, (Object)this);
    }

    public final void addRecentWorkspaceFile(@NotNull String filePath) {
        Intrinsics.checkNotNullParameter((Object)filePath, (String)"filePath");
        this.recentWorkspaceFiles.remove(filePath);
        this.recentWorkspaceFiles.add(0, filePath);
        if (this.recentWorkspaceFiles.size() > this.maxRecentFiles) {
            this.recentWorkspaceFiles = CollectionsKt.toMutableList((Collection)CollectionsKt.take((Iterable)this.recentWorkspaceFiles, (int)this.maxRecentFiles));
        }
    }

    public final void addFavoriteView(@NotNull String viewKey) {
        Intrinsics.checkNotNullParameter((Object)viewKey, (String)"viewKey");
        if (!this.favoriteViews.contains(viewKey)) {
            this.favoriteViews.add(viewKey);
        }
    }

    public final void removeFavoriteView(@NotNull String viewKey) {
        Intrinsics.checkNotNullParameter((Object)viewKey, (String)"viewKey");
        this.favoriteViews.remove(viewKey);
    }

    @NotNull
    public final String getEffectiveCliPath() {
        return this.useProjectCliPath && ((CharSequence)this.projectCliPath).length() > 0 ? this.projectCliPath : StructurizrSettings.Companion.getInstance().getStructurizrCliPath();
    }

    public final int getEffectivePreviewDelay() {
        return this.overridePreviewSettings ? this.projectPreviewDelayMs : StructurizrSettings.Companion.getInstance().getPreviewDelayMs();
    }

    @NotNull
    public final String getEffectiveExportDirectory() {
        CharSequence charSequence;
        CharSequence charSequence2 = this.projectExportDirectory;
        if (charSequence2.length() == 0) {
            boolean bl = false;
            charSequence = StructurizrSettings.Companion.getInstance().getDefaultExportDirectory();
        } else {
            charSequence = charSequence2;
        }
        return (String)charSequence;
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\b"}, d2={"Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettings$Companion;", "", "<init>", "()V", "getInstance", "Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettings;", "project", "Lcom/intellij/openapi/project/Project;", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final StructurizrProjectSettings getInstance(@NotNull Project project) {
            Intrinsics.checkNotNullParameter((Object)project, (String)"project");
            Object object = project.getService(StructurizrProjectSettings.class);
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"getService(...)");
            return (StructurizrProjectSettings)object;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
