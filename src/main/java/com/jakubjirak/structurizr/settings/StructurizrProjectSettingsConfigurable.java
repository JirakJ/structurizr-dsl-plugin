/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.options.Configurable
 *  com.intellij.openapi.project.Project
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.jakubjirak.structurizr.settings.StructurizrProjectSettings;
import com.jakubjirak.structurizr.settings.StructurizrProjectSettingsComponent;
import javax.swing.JComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettingsConfigurable;", "Lcom/intellij/openapi/options/Configurable;", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "settingsComponent", "Lcom/jakubjirak/structurizr/settings/StructurizrProjectSettingsComponent;", "getDisplayName", "", "createComponent", "Ljavax/swing/JComponent;", "isModified", "", "apply", "", "reset", "disposeUIResources", "structurizr-dsl-plugin"})
public final class StructurizrProjectSettingsConfigurable
implements Configurable {
    @NotNull
    private final Project project;
    @Nullable
    private StructurizrProjectSettingsComponent settingsComponent;

    public StructurizrProjectSettingsConfigurable(@NotNull Project project) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        this.project = project;
    }

    @NotNull
    public String getDisplayName() {
        return "Structurizr DSL";
    }

    @NotNull
    public JComponent createComponent() {
        StructurizrProjectSettingsComponent structurizrProjectSettingsComponent = this.settingsComponent = new StructurizrProjectSettingsComponent(this.project);
        Intrinsics.checkNotNull((Object)structurizrProjectSettingsComponent);
        return structurizrProjectSettingsComponent.getPanel();
    }

    public boolean isModified() {
        StructurizrProjectSettings settings = StructurizrProjectSettings.Companion.getInstance(this.project);
        StructurizrProjectSettingsComponent structurizrProjectSettingsComponent = this.settingsComponent;
        if (structurizrProjectSettingsComponent == null) {
            return false;
        }
        StructurizrProjectSettingsComponent component = structurizrProjectSettingsComponent;
        return component.isModified(settings);
    }

    public void apply() {
        StructurizrProjectSettings settings = StructurizrProjectSettings.Companion.getInstance(this.project);
        StructurizrProjectSettingsComponent structurizrProjectSettingsComponent = this.settingsComponent;
        if (structurizrProjectSettingsComponent == null) {
            return;
        }
        StructurizrProjectSettingsComponent component = structurizrProjectSettingsComponent;
        component.applyTo(settings);
    }

    public void reset() {
        StructurizrProjectSettings settings = StructurizrProjectSettings.Companion.getInstance(this.project);
        StructurizrProjectSettingsComponent structurizrProjectSettingsComponent = this.settingsComponent;
        if (structurizrProjectSettingsComponent == null) {
            return;
        }
        StructurizrProjectSettingsComponent component = structurizrProjectSettingsComponent;
        component.resetFrom(settings);
    }

    public void disposeUIResources() {
        this.settingsComponent = null;
    }
}
