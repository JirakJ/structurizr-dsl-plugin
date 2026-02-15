/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.formatting.Alignment
 *  com.intellij.formatting.Block
 *  com.intellij.formatting.FormattingContext
 *  com.intellij.formatting.FormattingModel
 *  com.intellij.formatting.FormattingModelBuilder
 *  com.intellij.formatting.FormattingModelProvider
 *  com.intellij.formatting.SpacingBuilder
 *  com.intellij.formatting.Wrap
 *  com.intellij.formatting.WrapType
 *  com.intellij.lang.ASTNode
 *  com.intellij.lang.Language
 *  com.intellij.psi.PsiFile
 *  com.intellij.psi.codeStyle.CodeStyleSettings
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.formatting;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.FormattingContext;
import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.FormattingModelProvider;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.jakubjirak.structurizr.formatting.StructurizrBlock;
import com.jakubjirak.structurizr.language.StructurizrLanguage;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u00a8\u0006\f"}, d2={"Lcom/jakubjirak/structurizr/formatting/StructurizrFormattingModelBuilder;", "Lcom/intellij/formatting/FormattingModelBuilder;", "<init>", "()V", "createModel", "Lcom/intellij/formatting/FormattingModel;", "formattingContext", "Lcom/intellij/formatting/FormattingContext;", "createSpaceBuilder", "Lcom/intellij/formatting/SpacingBuilder;", "settings", "Lcom/intellij/psi/codeStyle/CodeStyleSettings;", "structurizr-dsl-plugin"})
public final class StructurizrFormattingModelBuilder
implements FormattingModelBuilder {
    @NotNull
    public FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        Intrinsics.checkNotNullParameter((Object)formattingContext, (String)"formattingContext");
        CodeStyleSettings codeStyleSettings = formattingContext.getCodeStyleSettings();
        Intrinsics.checkNotNullExpressionValue((Object)codeStyleSettings, (String)"getCodeStyleSettings(...)");
        CodeStyleSettings codeStyleSettings2 = codeStyleSettings;
        PsiFile psiFile = formattingContext.getContainingFile();
        ASTNode aSTNode = formattingContext.getNode();
        Intrinsics.checkNotNullExpressionValue((Object)aSTNode, (String)"getNode(...)");
        FormattingModel formattingModel = FormattingModelProvider.createFormattingModelForPsiFile((PsiFile)psiFile, (Block)((Block)new StructurizrBlock(aSTNode, Wrap.createWrap((WrapType)WrapType.NONE, (boolean)false), Alignment.createAlignment(), this.createSpaceBuilder(codeStyleSettings2))), (CodeStyleSettings)codeStyleSettings2);
        Intrinsics.checkNotNullExpressionValue((Object)formattingModel, (String)"createFormattingModelForPsiFile(...)");
        return formattingModel;
    }

    private final SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, (Language)StructurizrLanguage.INSTANCE)
            .before(StructurizrTypes.LBRACE).spaces(1)
            .after(StructurizrTypes.LBRACE).lineBreakInCode()
            .before(StructurizrTypes.RBRACE).lineBreakInCode()
            .around(StructurizrTypes.EQUALS).spaces(1)
            .around(StructurizrTypes.ARROW).spaces(1)
            .around(StructurizrTypes.ARROW_LEFT).spaces(1)
            .around(StructurizrTypes.ARROW_BOTH).spaces(1);
    }
}
