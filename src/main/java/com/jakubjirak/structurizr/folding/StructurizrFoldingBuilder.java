/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.ASTNode
 *  com.intellij.lang.folding.FoldingBuilderEx
 *  com.intellij.lang.folding.FoldingDescriptor
 *  com.intellij.openapi.editor.Document
 *  com.intellij.openapi.util.TextRange
 *  com.intellij.psi.PsiElement
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.sequences.Sequence
 *  kotlin.sequences.SequencesKt
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.folding;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\bH\u0002J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\bH\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016\u00a8\u0006\u001c"}, d2={"Lcom/jakubjirak/structurizr/folding/StructurizrFoldingBuilder;", "Lcom/intellij/lang/folding/FoldingBuilderEx;", "<init>", "()V", "buildFoldRegions", "", "Lcom/intellij/lang/folding/FoldingDescriptor;", "root", "Lcom/intellij/psi/PsiElement;", "document", "Lcom/intellij/openapi/editor/Document;", "quick", "", "(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/editor/Document;Z)[Lcom/intellij/lang/folding/FoldingDescriptor;", "collectFoldRegions", "", "element", "descriptors", "", "isFoldableBlock", "findOpenBrace", "", "findCloseBrace", "getPlaceholderText", "", "node", "Lcom/intellij/lang/ASTNode;", "isCollapsedByDefault", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrFoldingBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrFoldingBuilder.kt\ncom/jakubjirak/structurizr/folding/StructurizrFoldingBuilder\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,108:1\n37#2,2:109\n*S KotlinDebug\n*F\n+ 1 StructurizrFoldingBuilder.kt\ncom/jakubjirak/structurizr/folding/StructurizrFoldingBuilder\n*L\n24#1:109,2\n*E\n"})
public final class StructurizrFoldingBuilder
extends FoldingBuilderEx {
    @NotNull
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        Intrinsics.checkNotNullParameter((Object)root, (String)"root");
        Intrinsics.checkNotNullParameter((Object)document, (String)"document");
        List descriptors = new ArrayList();
        this.collectFoldRegions(root, descriptors);
        Collection $this$toTypedArray$iv = descriptors;
        boolean $i$f$toTypedArray = false;
        Collection thisCollection$iv = $this$toTypedArray$iv;
        return (FoldingDescriptor[]) thisCollection$iv.toArray(new FoldingDescriptor[0]);
    }

    private final void collectFoldRegions(PsiElement element, List<FoldingDescriptor> descriptors) {
        for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            ASTNode node = child.getNode();
            if (this.isFoldableBlock(child)) {
                int startOffset = this.findOpenBrace(child);
                int endOffset = this.findCloseBrace(child);
                if (startOffset >= 0 && endOffset > startOffset) {
                    descriptors.add(new FoldingDescriptor(child.getNode(), new TextRange(startOffset, endOffset + 1), null));
                }
            }
            this.collectFoldRegions(child, descriptors);
        }
    }

    private final boolean isFoldableBlock(PsiElement element) {
        String text = element.getText();
        Intrinsics.checkNotNull((Object)text);
        return StringsKt.contains((CharSequence)text, (CharSequence)"{", (boolean)false) && StringsKt.contains((CharSequence)text, (CharSequence)"}", (boolean)false);
    }

    private final int findOpenBrace(PsiElement element) {
        for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (!Intrinsics.areEqual((Object)child.getNode().getElementType(), (Object)StructurizrTypes.LBRACE)) continue;
            return child.getTextOffset();
        }
        return -1;
    }

    private final int findCloseBrace(PsiElement element) {
        for (PsiElement child = element.getLastChild(); child != null; child = child.getPrevSibling()) {
            if (!Intrinsics.areEqual((Object)child.getNode().getElementType(), (Object)StructurizrTypes.RBRACE)) continue;
            return child.getTextOffset();
        }
        return -1;
    }

    @NotNull
    public String getPlaceholderText(@NotNull ASTNode node) {
        Intrinsics.checkNotNullParameter((Object)node, (String)"node");
        String string = node.getText();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
        String text = string;
        String string2 = (String)SequencesKt.firstOrNull((Sequence)StringsKt.lineSequence((CharSequence)text));
        if (string2 == null) {
            return "{...}";
        }
        String firstLine = string2;
        return StringsKt.contains((CharSequence)firstLine, (CharSequence)"workspace", (boolean)false) ? "workspace {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"model", (boolean)false) ? "model {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"views", (boolean)false) ? "views {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"styles", (boolean)false) ? "styles {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"softwareSystem", (boolean)false) ? "softwareSystem {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"container", (boolean)false) ? "container {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"component", (boolean)false) ? "component {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"person", (boolean)false) ? "person {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"deploymentEnvironment", (boolean)false) ? "deploymentEnvironment {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"deploymentNode", (boolean)false) ? "deploymentNode {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"systemContext", (boolean)false) ? "systemContext {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"systemLandscape", (boolean)false) ? "systemLandscape {...}" : (StringsKt.contains((CharSequence)firstLine, (CharSequence)"configuration", (boolean)false) ? "configuration {...}" : "{...}"))))))))))));
    }

    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        Intrinsics.checkNotNullParameter((Object)node, (String)"node");
        return false;
    }
}
