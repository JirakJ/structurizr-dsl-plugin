/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.preview;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014\u00a8\u0006\u0015"}, d2={"Lcom/jakubjirak/structurizr/preview/ExportFormat;", "", "cliFormat", "", "displayName", "extension", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCliFormat", "()Ljava/lang/String;", "getDisplayName", "getExtension", "PNG", "SVG", "PLANTUML", "PLANTUML_C4", "MERMAID", "DOT", "ILOGRAPH", "WEBSEQUENCEDIAGRAMS", "JSON", "structurizr-dsl-plugin"})
public enum ExportFormat {
    PNG("png", "PNG Image", "png"),
    SVG("svg", "SVG Vector", "svg"),
    PLANTUML("plantuml", "PlantUML", "puml"),
    PLANTUML_C4("plantuml/c4plantuml", "PlantUML (C4)", "puml"),
    MERMAID("mermaid", "Mermaid", "mmd"),
    DOT("dot", "Graphviz DOT", "dot"),
    ILOGRAPH("ilograph", "Ilograph", "yaml"),
    WEBSEQUENCEDIAGRAMS("websequencediagrams", "WebSequenceDiagrams", "wsd"),
    JSON("json", "JSON", "json");

    @NotNull
    private final String cliFormat;
    @NotNull
    private final String displayName;
    @NotNull
    private final String extension;

    ExportFormat(String cliFormat, String displayName, String extension) {
        this.cliFormat = cliFormat;
        this.displayName = displayName;
        this.extension = extension;
    }

    @NotNull
    public final String getCliFormat() {
        return this.cliFormat;
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
    public static EnumEntries<ExportFormat> getEntries() {
        return EnumEntriesKt.enumEntries((Enum[])values());
    }
}
