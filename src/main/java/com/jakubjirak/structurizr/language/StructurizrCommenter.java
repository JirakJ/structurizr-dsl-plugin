/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.Commenter
 *  kotlin.Metadata
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.language;

import com.intellij.lang.Commenter;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0016\u00a8\u0006\n"}, d2={"Lcom/jakubjirak/structurizr/language/StructurizrCommenter;", "Lcom/intellij/lang/Commenter;", "<init>", "()V", "getLineCommentPrefix", "", "getBlockCommentPrefix", "getBlockCommentSuffix", "getCommentedBlockCommentPrefix", "getCommentedBlockCommentSuffix", "structurizr-dsl-plugin"})
public final class StructurizrCommenter
implements Commenter {
    @NotNull
    public String getLineCommentPrefix() {
        return "//";
    }

    @NotNull
    public String getBlockCommentPrefix() {
        return "/*";
    }

    @NotNull
    public String getBlockCommentSuffix() {
        return "*/";
    }

    @Nullable
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Nullable
    public String getCommentedBlockCommentSuffix() {
        return null;
    }
}
