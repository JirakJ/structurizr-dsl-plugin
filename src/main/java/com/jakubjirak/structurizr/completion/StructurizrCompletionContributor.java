/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.codeInsight.completion.CompletionContributor
 *  com.intellij.codeInsight.completion.CompletionType
 *  com.intellij.lang.Language
 *  com.intellij.patterns.ElementPattern
 *  com.intellij.patterns.PlatformPatterns
 *  kotlin.Metadata
 */
package com.jakubjirak.structurizr.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.lang.Language;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PlatformPatterns;
import com.jakubjirak.structurizr.completion.StructurizrCompletionProvider;
import com.jakubjirak.structurizr.language.StructurizrLanguage;
import kotlin.Metadata;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2={"Lcom/jakubjirak/structurizr/completion/StructurizrCompletionContributor;", "Lcom/intellij/codeInsight/completion/CompletionContributor;", "<init>", "()V", "structurizr-dsl-plugin"})
public final class StructurizrCompletionContributor
extends CompletionContributor {
    public StructurizrCompletionContributor() {
        this.extend(CompletionType.BASIC, (ElementPattern)PlatformPatterns.psiElement().withLanguage((Language)StructurizrLanguage.INSTANCE), new StructurizrCompletionProvider());
    }
}
