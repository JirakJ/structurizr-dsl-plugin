/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.annotation.AnnotationHolder
 *  com.intellij.lang.annotation.Annotator
 *  com.intellij.lang.annotation.HighlightSeverity
 *  com.intellij.openapi.editor.DefaultLanguageHighlighterColors
 *  com.intellij.openapi.editor.colors.TextAttributesKey
 *  com.intellij.openapi.util.TextRange
 *  com.intellij.psi.PsiElement
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.highlighting;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016\u00a8\u0006\n"}, d2={"Lcom/jakubjirak/structurizr/highlighting/StructurizrAnnotator;", "Lcom/intellij/lang/annotation/Annotator;", "<init>", "()V", "annotate", "", "element", "Lcom/intellij/psi/PsiElement;", "holder", "Lcom/intellij/lang/annotation/AnnotationHolder;", "structurizr-dsl-plugin"})
public final class StructurizrAnnotator
implements Annotator {
    @NotNull
    private static final TextAttributesKey VARIABLE_REFERENCE = TextAttributesKey.createTextAttributesKey(
            "STRUCTURIZR_VARIABLE_REFERENCE", DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR);

    @NotNull
    private static final TextAttributesKey DEFINITION_IDENTIFIER = TextAttributesKey.createTextAttributesKey(
            "STRUCTURIZR_DEFINITION_IDENTIFIER", DefaultLanguageHighlighterColors.INSTANCE_FIELD);

    @NotNull
    private static final TextAttributesKey ARROW_OPERATOR = TextAttributesKey.createTextAttributesKey(
            "STRUCTURIZR_ARROW_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);

    @NotNull
    private static final TextAttributesKey DEPRECATED_DIRECTIVE = TextAttributesKey.createTextAttributesKey(
            "STRUCTURIZR_DEPRECATED_DIRECTIVE", DefaultLanguageHighlighterColors.METADATA);

    @NotNull
    public static TextAttributesKey getVARIABLE_REFERENCE() {
        return VARIABLE_REFERENCE;
    }

    private static final Pattern SUBSTITUTION_PATTERN = Pattern.compile("\\$\\{[^}]+\\}");

    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Intrinsics.checkNotNullParameter((Object)holder, (String)"holder");
        com.intellij.psi.tree.IElementType type = element.getNode().getElementType();

        if (type == StructurizrTypes.STRING) {
            String text = element.getText();
            int elementStart = element.getTextRange().getStartOffset();
            Matcher matcher = SUBSTITUTION_PATTERN.matcher(text);
            while (matcher.find()) {
                TextRange range = new TextRange(elementStart + matcher.start(), elementStart + matcher.end());
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(range)
                        .textAttributes(VARIABLE_REFERENCE)
                        .create();
            }
        }

        // Highlight IDENTIFIER in ELEMENT_ASSIGNMENT (definition site)
        if (type == StructurizrTypes.IDENTIFIER) {
            PsiElement parent = element.getParent();
            if (parent != null && parent.getNode().getElementType() == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(element.getTextRange())
                        .textAttributes(DEFINITION_IDENTIFIER)
                        .create();
            }
        }

        // Highlight arrow operators
        if (type == StructurizrTypes.ARROW || type == StructurizrTypes.ARROW_LEFT || type == StructurizrTypes.ARROW_BOTH) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.getTextRange())
                    .textAttributes(ARROW_OPERATOR)
                    .create();
        }

        // Highlight deprecated directives !ref, !extend, !constant
        if (type == StructurizrTypes.REF) {
            PsiElement parent = element.getParent();
            if (parent != null && parent.getNode().getElementType() == StructurizrTypes.REF_STATEMENT) {
                TextAttributes strikethrough = new TextAttributes();
                strikethrough.setEffectType(EffectType.STRIKEOUT);
                strikethrough.setEffectColor(Color.GRAY);
                holder.newAnnotation(HighlightSeverity.WARNING, "!ref is deprecated, use !element instead")
                        .range(element.getTextRange())
                        .textAttributes(DEPRECATED_DIRECTIVE)
                        .enforcedTextAttributes(strikethrough)
                        .create();
            }
        }

        if (type == StructurizrTypes.DIRECTIVE_CONSTANT) {
            PsiElement parent = element.getParent();
            if (parent != null && parent.getNode().getElementType() == StructurizrTypes.CONSTANT_DIRECTIVE) {
                TextAttributes strikethrough = new TextAttributes();
                strikethrough.setEffectType(EffectType.STRIKEOUT);
                strikethrough.setEffectColor(Color.GRAY);
                holder.newAnnotation(HighlightSeverity.WARNING, "!constant is deprecated, use !const instead")
                        .range(element.getTextRange())
                        .textAttributes(DEPRECATED_DIRECTIVE)
                        .enforcedTextAttributes(strikethrough)
                        .create();
            }
        }
    }
}
