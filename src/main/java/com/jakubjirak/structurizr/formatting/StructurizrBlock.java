/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.formatting.ASTBlock
 *  com.intellij.formatting.Alignment
 *  com.intellij.formatting.Block
 *  com.intellij.formatting.ChildAttributes
 *  com.intellij.formatting.Indent
 *  com.intellij.formatting.Spacing
 *  com.intellij.formatting.SpacingBuilder
 *  com.intellij.formatting.Wrap
 *  com.intellij.formatting.WrapType
 *  com.intellij.lang.ASTNode
 *  com.intellij.openapi.util.TextRange
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.formatting;

import com.intellij.formatting.ASTBlock;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2={"Lcom/jakubjirak/structurizr/formatting/StructurizrBlock;", "Lcom/intellij/formatting/ASTBlock;", "node", "Lcom/intellij/lang/ASTNode;", "wrap", "Lcom/intellij/formatting/Wrap;", "alignment", "Lcom/intellij/formatting/Alignment;", "spacingBuilder", "Lcom/intellij/formatting/SpacingBuilder;", "<init>", "(Lcom/intellij/lang/ASTNode;Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;Lcom/intellij/formatting/SpacingBuilder;)V", "getNode", "getTextRange", "Lcom/intellij/openapi/util/TextRange;", "getWrap", "getIndent", "Lcom/intellij/formatting/Indent;", "getAlignment", "getSpacing", "Lcom/intellij/formatting/Spacing;", "child1", "Lcom/intellij/formatting/Block;", "child2", "getSubBlocks", "", "isIncomplete", "", "isLeaf", "getChildAttributes", "Lcom/intellij/formatting/ChildAttributes;", "newChildIndex", "", "structurizr-dsl-plugin"})
public final class StructurizrBlock
implements ASTBlock {
    @NotNull
    private final ASTNode node;
    @Nullable
    private final Wrap wrap;
    @Nullable
    private final Alignment alignment;
    @NotNull
    private final SpacingBuilder spacingBuilder;

    public StructurizrBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, @NotNull SpacingBuilder spacingBuilder) {
        Intrinsics.checkNotNullParameter((Object)node, (String)"node");
        Intrinsics.checkNotNullParameter((Object)spacingBuilder, (String)"spacingBuilder");
        this.node = node;
        this.wrap = wrap;
        this.alignment = alignment;
        this.spacingBuilder = spacingBuilder;
    }

    @NotNull
    public ASTNode getNode() {
        return this.node;
    }

    @NotNull
    public TextRange getTextRange() {
        TextRange textRange = this.node.getTextRange();
        Intrinsics.checkNotNullExpressionValue((Object)textRange, (String)"getTextRange(...)");
        return textRange;
    }

    @Nullable
    public Wrap getWrap() {
        return this.wrap;
    }

    @Nullable
    public Indent getIndent() {
        return Indent.getNoneIndent();
    }

    @Nullable
    public Alignment getAlignment() {
        return this.alignment;
    }

    @Nullable
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        Intrinsics.checkNotNullParameter((Object)child2, (String)"child2");
        return this.spacingBuilder.getSpacing((Block)this, child1, child2);
    }

    @NotNull
    public List<Block> getSubBlocks() {
        List blocks = new ArrayList();
        for (ASTNode child = this.node.getFirstChildNode(); child != null; child = child.getTreeNext()) {
            if (child.getTextRange().getLength() <= 0) continue;
            blocks.add(new StructurizrBlock(child, Wrap.createWrap((WrapType)WrapType.NONE, (boolean)false), null, this.spacingBuilder));
        }
        return blocks;
    }

    public boolean isIncomplete() {
        return false;
    }

    public boolean isLeaf() {
        return this.node.getFirstChildNode() == null;
    }

    @NotNull
    public ChildAttributes getChildAttributes(int newChildIndex) {
        return new ChildAttributes(Indent.getNoneIndent(), null);
    }
}
