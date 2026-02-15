/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.export;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011\u00a8\u0006\u0012"}, d2={"Lcom/jakubjirak/structurizr/export/DiagramExportFormat;", "", "displayName", "", "extension", "description", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "getExtension", "getDescription", "SVG", "PNG", "PLANTUML", "MERMAID", "DOT", "JSON", "structurizr-dsl-plugin"})
public enum DiagramExportFormat {
    SVG("SVG", "svg", "Scalable Vector Graphics - best for web and documentation"),
    PNG("PNG", "png", "Portable Network Graphics - best for presentations"),
    PLANTUML("PlantUML", "puml", "PlantUML format - can be rendered by PlantUML tools"),
    MERMAID("Mermaid", "mmd", "Mermaid format - can be rendered in Markdown"),
    DOT("Graphviz DOT", "dot", "DOT format - can be rendered by Graphviz"),
    JSON("JSON", "json", "Structurizr JSON format - for programmatic access");

    @NotNull
    private final String displayName;
    @NotNull
    private final String extension;
    @NotNull
    private final String description;

    DiagramExportFormat(String displayName, String extension, String description) {
        this.displayName = displayName;
        this.extension = extension;
        this.description = description;
    }

    @NotNull
    public final String getDisplayName() {
        return this.displayName;
    }

    @NotNull
    public final String getExtension() {
        return this.extension;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public static EnumEntries<DiagramExportFormat> getEntries() {
        return EnumEntriesKt.enumEntries((Enum[])values());
    }
}
