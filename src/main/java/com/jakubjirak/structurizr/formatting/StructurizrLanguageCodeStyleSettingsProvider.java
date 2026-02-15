/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.Language
 *  com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable
 *  com.intellij.psi.codeStyle.CodeStyleSettingsCustomizableOptions
 *  com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider
 *  com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider$SettingsType
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.formatting;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizableOptions;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.jakubjirak.structurizr.formatting.StructurizrCodeStyleSettings;
import com.jakubjirak.structurizr.language.StructurizrLanguage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016\u00a8\u0006\u000e"}, d2={"Lcom/jakubjirak/structurizr/formatting/StructurizrLanguageCodeStyleSettingsProvider;", "Lcom/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider;", "<init>", "()V", "getLanguage", "Lcom/intellij/lang/Language;", "customizeSettings", "", "consumer", "Lcom/intellij/psi/codeStyle/CodeStyleSettingsCustomizable;", "settingsType", "Lcom/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider$SettingsType;", "getCodeSample", "", "structurizr-dsl-plugin"})
public final class StructurizrLanguageCodeStyleSettingsProvider
extends LanguageCodeStyleSettingsProvider {
    @NotNull
    public Language getLanguage() {
        return StructurizrLanguage.INSTANCE;
    }

    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull LanguageCodeStyleSettingsProvider.SettingsType settingsType) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        Intrinsics.checkNotNullParameter((Object)settingsType, (String)"settingsType");
        switch (WhenMappings.$EnumSwitchMapping$0[settingsType.ordinal()]) {
            case 1: {
                String[] stringArray = new String[]{"INDENT_SIZE", "TAB_SIZE", "USE_TAB_CHARACTER", "SMART_TABS", "KEEP_INDENTS_ON_EMPTY_LINES"};
                consumer.showStandardOptions(stringArray);
                break;
            }
            case 2: {
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "SPACE_BEFORE_LBRACE", "Space before '{'", CodeStyleSettingsCustomizableOptions.getInstance().SPACES_BEFORE_LEFT_BRACE, new Object[0]);
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "SPACE_BEFORE_ARROW", "Space before '->'", CodeStyleSettingsCustomizableOptions.getInstance().SPACES_AROUND_OPERATORS, new Object[0]);
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "SPACE_AFTER_ARROW", "Space after '->'", CodeStyleSettingsCustomizableOptions.getInstance().SPACES_AROUND_OPERATORS, new Object[0]);
                break;
            }
            case 3: {
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "BLANK_LINES_AROUND_BLOCK", "Around block statements", CodeStyleSettingsCustomizableOptions.getInstance().BLANK_LINES, new Object[0]);
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "BLANK_LINES_AFTER_MODEL_ELEMENTS", "After model elements (person, softwareSystem, etc.)", CodeStyleSettingsCustomizableOptions.getInstance().BLANK_LINES, new Object[0]);
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "BLANK_LINES_BEFORE_VIEWS", "Before views block", CodeStyleSettingsCustomizableOptions.getInstance().BLANK_LINES, new Object[0]);
                break;
            }
            case 4: {
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "WRAP_LONG_LINES", "Wrap long lines", CodeStyleSettingsCustomizableOptions.getInstance().WRAPPING_KEEP, new Object[0]);
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "KEEP_LINE_BREAKS", "Keep line breaks", CodeStyleSettingsCustomizableOptions.getInstance().WRAPPING_KEEP, new Object[0]);
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "LBRACE_ON_NEXT_LINE", "Opening brace on next line", CodeStyleSettingsCustomizableOptions.getInstance().WRAPPING_BRACES, new Object[0]);
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "RBRACE_ON_OWN_LINE", "Closing brace on own line", CodeStyleSettingsCustomizableOptions.getInstance().WRAPPING_BRACES, new Object[0]);
                consumer.showCustomOption(StructurizrCodeStyleSettings.class, "ALIGN_MULTILINE_PARAMETERS", "Align multiline parameters", CodeStyleSettingsCustomizableOptions.getInstance().WRAPPING_METHOD_PARAMETERS, new Object[0]);
            }
        }
    }

    @NotNull
    public String getCodeSample(@NotNull LanguageCodeStyleSettingsProvider.SettingsType settingsType) {
        Intrinsics.checkNotNullParameter((Object)settingsType, (String)"settingsType");
        return "workspace \"Example\" \"An example workspace\" {\n    \n    model {\n        user = person \"User\" \"A user of the system\"\n        \n        system = softwareSystem \"System\" \"The software system\" {\n            webapp = container \"Web Application\" \"The web frontend\" \"React\"\n            api = container \"API\" \"The backend API\" \"Java/Spring\"\n            db = container \"Database\" \"Data storage\" \"PostgreSQL\"\n            \n            webapp -> api \"Calls\" \"JSON/HTTPS\"\n            api -> db \"Reads/Writes\" \"JDBC\"\n        }\n        \n        user -> system \"Uses\"\n    }\n    \n    views {\n        systemContext system \"SystemContext\" {\n            include *\n            autoLayout\n        }\n        \n        container system \"Containers\" {\n            include *\n            autoLayout lr\n        }\n        \n        styles {\n            element \"Person\" {\n                shape Person\n                background #08427B\n                color #ffffff\n            }\n            element \"Software System\" {\n                background #1168BD\n                color #ffffff\n            }\n            element \"Container\" {\n                background #438DD5\n                color #ffffff\n            }\n            relationship \"Relationship\" {\n                thickness 2\n                color #707070\n                style dashed\n            }\n        }\n        \n        themes default\n    }\n}";
    }

    @Metadata(mv={2, 0, 0}, k=3, xi=48)
    public final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[LanguageCodeStyleSettingsProvider.SettingsType.values().length];
            try {
                nArray[LanguageCodeStyleSettingsProvider.SettingsType.INDENT_SETTINGS.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[LanguageCodeStyleSettingsProvider.SettingsType.SPACING_SETTINGS.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[LanguageCodeStyleSettingsProvider.SettingsType.BLANK_LINES_SETTINGS.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[LanguageCodeStyleSettingsProvider.SettingsType.WRAPPING_AND_BRACES_SETTINGS.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}
