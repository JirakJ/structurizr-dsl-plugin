/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.options.Configurable
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.settings;

import com.intellij.openapi.options.Configurable;
import com.jakubjirak.structurizr.settings.StructurizrSettings;
import com.jakubjirak.structurizr.settings.StructurizrSettingsComponent;
import javax.swing.JComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2={"Lcom/jakubjirak/structurizr/settings/StructurizrSettingsConfigurable;", "Lcom/intellij/openapi/options/Configurable;", "<init>", "()V", "settingsComponent", "Lcom/jakubjirak/structurizr/settings/StructurizrSettingsComponent;", "getDisplayName", "", "createComponent", "Ljavax/swing/JComponent;", "isModified", "", "apply", "", "reset", "disposeUIResources", "structurizr-dsl-plugin"})
public final class StructurizrSettingsConfigurable
implements Configurable {
    @Nullable
    private StructurizrSettingsComponent settingsComponent;

    @NotNull
    public String getDisplayName() {
        return "Structurizr DSL";
    }

    @NotNull
    public JComponent createComponent() {
        StructurizrSettingsComponent structurizrSettingsComponent = this.settingsComponent = new StructurizrSettingsComponent();
        Intrinsics.checkNotNull((Object)structurizrSettingsComponent);
        return structurizrSettingsComponent.getPanel();
    }

    public boolean isModified() {
        StructurizrSettings settings = StructurizrSettings.Companion.getInstance();
        StructurizrSettingsComponent structurizrSettingsComponent = this.settingsComponent;
        if (structurizrSettingsComponent == null) {
            return false;
        }
        StructurizrSettingsComponent component = structurizrSettingsComponent;
        return component.isModified(settings);
    }

    public void apply() {
        StructurizrSettings settings = StructurizrSettings.Companion.getInstance();
        StructurizrSettingsComponent structurizrSettingsComponent = this.settingsComponent;
        if (structurizrSettingsComponent == null) {
            return;
        }
        StructurizrSettingsComponent component = structurizrSettingsComponent;
        component.applyTo(settings);
    }

    public void reset() {
        StructurizrSettings settings = StructurizrSettings.Companion.getInstance();
        StructurizrSettingsComponent structurizrSettingsComponent = this.settingsComponent;
        if (structurizrSettingsComponent == null) {
            return;
        }
        StructurizrSettingsComponent component = structurizrSettingsComponent;
        component.resetFrom(settings);
    }

    public void disposeUIResources() {
        this.settingsComponent = null;
    }
}
