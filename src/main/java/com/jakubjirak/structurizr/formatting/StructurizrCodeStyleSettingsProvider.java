/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.application.options.CodeStyleAbstractConfigurable
 *  com.intellij.application.options.CodeStyleAbstractPanel
 *  com.intellij.psi.codeStyle.CodeStyleConfigurable
 *  com.intellij.psi.codeStyle.CodeStyleSettings
 *  com.intellij.psi.codeStyle.CodeStyleSettingsProvider
 *  com.intellij.psi.codeStyle.CustomCodeStyleSettings
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.formatting;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import com.jakubjirak.structurizr.formatting.StructurizrCodeStyleMainPanel;
import com.jakubjirak.structurizr.formatting.StructurizrCodeStyleSettings;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0016\u00a8\u0006\r"}, d2={"Lcom/jakubjirak/structurizr/formatting/StructurizrCodeStyleSettingsProvider;", "Lcom/intellij/psi/codeStyle/CodeStyleSettingsProvider;", "<init>", "()V", "createCustomSettings", "Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;", "settings", "Lcom/intellij/psi/codeStyle/CodeStyleSettings;", "getConfigurableDisplayName", "", "createConfigurable", "Lcom/intellij/psi/codeStyle/CodeStyleConfigurable;", "modelSettings", "structurizr-dsl-plugin"})
public final class StructurizrCodeStyleSettingsProvider
extends CodeStyleSettingsProvider {
    @NotNull
    public CustomCodeStyleSettings createCustomSettings(@NotNull CodeStyleSettings settings) {
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        return new StructurizrCodeStyleSettings(settings);
    }

    @NotNull
    public String getConfigurableDisplayName() {
        return "Structurizr DSL";
    }

    @NotNull
    public CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings settings, @NotNull CodeStyleSettings modelSettings) {
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        Intrinsics.checkNotNullParameter((Object)modelSettings, (String)"modelSettings");
        String string = this.getConfigurableDisplayName();
        return (CodeStyleConfigurable)new CodeStyleAbstractConfigurable(settings, modelSettings, string){

            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
                CodeStyleSettings codeStyleSettings = this.getCurrentSettings();
                Intrinsics.checkNotNullExpressionValue((Object)codeStyleSettings, (String)"getCurrentSettings(...)");
                return (CodeStyleAbstractPanel)new StructurizrCodeStyleMainPanel(codeStyleSettings, settings);
            }
        };
    }
}
