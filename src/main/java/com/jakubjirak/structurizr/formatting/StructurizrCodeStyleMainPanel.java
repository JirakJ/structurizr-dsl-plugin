/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.application.options.TabbedLanguageCodeStylePanel
 *  com.intellij.lang.Language
 *  com.intellij.psi.codeStyle.CodeStyleSettings
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.formatting;

import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.jakubjirak.structurizr.language.StructurizrLanguage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lcom/jakubjirak/structurizr/formatting/StructurizrCodeStyleMainPanel;", "Lcom/intellij/application/options/TabbedLanguageCodeStylePanel;", "currentSettings", "Lcom/intellij/psi/codeStyle/CodeStyleSettings;", "settings", "<init>", "(Lcom/intellij/psi/codeStyle/CodeStyleSettings;Lcom/intellij/psi/codeStyle/CodeStyleSettings;)V", "structurizr-dsl-plugin"})
public final class StructurizrCodeStyleMainPanel
extends TabbedLanguageCodeStylePanel {
    public StructurizrCodeStyleMainPanel(@NotNull CodeStyleSettings currentSettings, @NotNull CodeStyleSettings settings) {
        super((Language)StructurizrLanguage.INSTANCE, currentSettings, settings);
        Intrinsics.checkNotNullParameter((Object)currentSettings, (String)"currentSettings");
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
    }
}
