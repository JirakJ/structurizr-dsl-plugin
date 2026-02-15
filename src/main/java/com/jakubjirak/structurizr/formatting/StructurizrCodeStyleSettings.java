/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.psi.codeStyle.CodeStyleSettings
 *  com.intellij.psi.codeStyle.CustomCodeStyleSettings
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.formatting;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2={"Lcom/jakubjirak/structurizr/formatting/StructurizrCodeStyleSettings;", "Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;", "container", "Lcom/intellij/psi/codeStyle/CodeStyleSettings;", "<init>", "(Lcom/intellij/psi/codeStyle/CodeStyleSettings;)V", "BLANK_LINES_AROUND_BLOCK", "", "BLANK_LINES_AFTER_MODEL_ELEMENTS", "BLANK_LINES_BEFORE_VIEWS", "SPACE_BEFORE_LBRACE", "", "SPACE_AFTER_ARROW", "SPACE_BEFORE_ARROW", "WRAP_LONG_LINES", "ALIGN_MULTILINE_PARAMETERS", "KEEP_LINE_BREAKS", "LBRACE_ON_NEXT_LINE", "RBRACE_ON_OWN_LINE", "ALIGN_PROPERTY_VALUES", "PROPERTY_VALUE_ON_SAME_LINE", "structurizr-dsl-plugin"})
public final class StructurizrCodeStyleSettings
extends CustomCodeStyleSettings {
    @JvmField
    public int BLANK_LINES_AROUND_BLOCK;
    @JvmField
    public int BLANK_LINES_AFTER_MODEL_ELEMENTS;
    @JvmField
    public int BLANK_LINES_BEFORE_VIEWS;
    @JvmField
    public boolean SPACE_BEFORE_LBRACE;
    @JvmField
    public boolean SPACE_AFTER_ARROW;
    @JvmField
    public boolean SPACE_BEFORE_ARROW;
    @JvmField
    public boolean WRAP_LONG_LINES;
    @JvmField
    public boolean ALIGN_MULTILINE_PARAMETERS;
    @JvmField
    public boolean KEEP_LINE_BREAKS;
    @JvmField
    public boolean LBRACE_ON_NEXT_LINE;
    @JvmField
    public boolean RBRACE_ON_OWN_LINE;
    @JvmField
    public boolean ALIGN_PROPERTY_VALUES;
    @JvmField
    public boolean PROPERTY_VALUE_ON_SAME_LINE;

    public StructurizrCodeStyleSettings(@NotNull CodeStyleSettings container) {
        super("StructurizrCodeStyleSettings", container);
        Intrinsics.checkNotNullParameter((Object)container, (String)"container");
        this.BLANK_LINES_AROUND_BLOCK = 1;
        this.BLANK_LINES_AFTER_MODEL_ELEMENTS = 1;
        this.BLANK_LINES_BEFORE_VIEWS = 1;
        this.SPACE_BEFORE_LBRACE = true;
        this.SPACE_AFTER_ARROW = true;
        this.SPACE_BEFORE_ARROW = true;
        this.WRAP_LONG_LINES = true;
        this.ALIGN_MULTILINE_PARAMETERS = true;
        this.KEEP_LINE_BREAKS = true;
        this.RBRACE_ON_OWN_LINE = true;
        this.PROPERTY_VALUE_ON_SAME_LINE = true;
    }
}
