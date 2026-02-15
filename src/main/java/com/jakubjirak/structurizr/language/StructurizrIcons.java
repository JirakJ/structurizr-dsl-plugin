/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.util.IconLoader
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.language;

import com.intellij.openapi.util.IconLoader;
import javax.swing.Icon;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2={"Lcom/jakubjirak/structurizr/language/StructurizrIcons;", "", "<init>", "()V", "load", "Ljavax/swing/Icon;", "path", "", "FILE", "WORKSPACE", "MODEL", "VIEWS", "PERSON", "SOFTWARE_SYSTEM", "CONTAINER", "COMPONENT", "RELATIONSHIP", "DEPLOYMENT", "STYLES", "structurizr-dsl-plugin"})
public final class StructurizrIcons {
    @NotNull
    public static final StructurizrIcons INSTANCE = new StructurizrIcons();
    @JvmField
    @NotNull
    public static final Icon FILE = INSTANCE.load("/icons/structurizr.svg");
    @JvmField
    @NotNull
    public static final Icon WORKSPACE = INSTANCE.load("/icons/workspace.svg");
    @JvmField
    @NotNull
    public static final Icon MODEL = INSTANCE.load("/icons/model.svg");
    @JvmField
    @NotNull
    public static final Icon VIEWS = INSTANCE.load("/icons/views.svg");
    @JvmField
    @NotNull
    public static final Icon PERSON = INSTANCE.load("/icons/person.svg");
    @JvmField
    @NotNull
    public static final Icon SOFTWARE_SYSTEM = INSTANCE.load("/icons/software-system.svg");
    @JvmField
    @NotNull
    public static final Icon CONTAINER = INSTANCE.load("/icons/container.svg");
    @JvmField
    @NotNull
    public static final Icon COMPONENT = INSTANCE.load("/icons/component.svg");
    @JvmField
    @NotNull
    public static final Icon RELATIONSHIP = INSTANCE.load("/icons/relationship.svg");
    @JvmField
    @NotNull
    public static final Icon DEPLOYMENT = INSTANCE.load("/icons/deployment.svg");
    @JvmField
    @NotNull
    public static final Icon STYLES = INSTANCE.load("/icons/styles.svg");

    private StructurizrIcons() {
    }

    private final Icon load(String path) {
        return IconLoader.getIcon((String)path, StructurizrIcons.class);
    }
}
