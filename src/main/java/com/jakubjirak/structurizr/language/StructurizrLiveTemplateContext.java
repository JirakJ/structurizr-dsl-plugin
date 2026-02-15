package com.jakubjirak.structurizr.language;

import com.intellij.codeInsight.template.TemplateActionContext;
import com.intellij.codeInsight.template.TemplateContextType;
import org.jetbrains.annotations.NotNull;

public final class StructurizrLiveTemplateContext extends TemplateContextType {

    public StructurizrLiveTemplateContext() {
        super("Structurizr DSL");
    }

    @Override
    public boolean isInContext(@NotNull TemplateActionContext templateActionContext) {
        return templateActionContext.getFile().getName().endsWith(".dsl");
    }
}
