/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.ide.structureView.StructureViewTreeElement
 *  com.intellij.ide.util.treeView.smartTree.TreeElement
 *  com.intellij.navigation.ItemPresentation
 *  com.intellij.pom.Navigatable
 *  com.intellij.psi.PsiFile
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package com.jakubjirak.structurizr.navigation;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.jakubjirak.structurizr.language.StructurizrIcons;
import javax.swing.Icon;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2={"Lcom/jakubjirak/structurizr/navigation/StructurizrStructureViewElement;", "Lcom/intellij/ide/structureView/StructureViewTreeElement;", "element", "Lcom/intellij/psi/PsiFile;", "<init>", "(Lcom/intellij/psi/PsiFile;)V", "getValue", "", "navigate", "", "requestFocus", "", "canNavigate", "canNavigateToSource", "getPresentation", "Lcom/intellij/navigation/ItemPresentation;", "getChildren", "", "Lcom/intellij/ide/util/treeView/smartTree/TreeElement;", "()[Lcom/intellij/ide/util/treeView/smartTree/TreeElement;", "structurizr-dsl-plugin"})
public final class StructurizrStructureViewElement
implements StructureViewTreeElement {
    @NotNull
    private final PsiFile element;

    public StructurizrStructureViewElement(@NotNull PsiFile element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        this.element = element;
    }

    @NotNull
    public Object getValue() {
        return this.element;
    }

    public void navigate(boolean requestFocus) {
        block0: {
            PsiFile psiFile = this.element;
            Navigatable navigatable = psiFile instanceof Navigatable ? (Navigatable)psiFile : null;
            if (navigatable == null) break block0;
            navigatable.navigate(requestFocus);
        }
    }

    public boolean canNavigate() {
        PsiFile psiFile = this.element;
        Navigatable navigatable = psiFile instanceof Navigatable ? (Navigatable)psiFile : null;
        return navigatable != null ? navigatable.canNavigate() : false;
    }

    public boolean canNavigateToSource() {
        PsiFile psiFile = this.element;
        Navigatable navigatable = psiFile instanceof Navigatable ? (Navigatable)psiFile : null;
        return navigatable != null ? navigatable.canNavigateToSource() : false;
    }

    @NotNull
    public ItemPresentation getPresentation() {
        return new ItemPresentation(){
            final /* synthetic */ StructurizrStructureViewElement this$0 = StructurizrStructureViewElement.this;

            public String getPresentableText() {
                String string = StructurizrStructureViewElement.access$getElement$p(this.this$0).getName();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
                return string;
            }

            public String getLocationString() {
                return null;
            }

            public Icon getIcon(boolean unused) {
                return StructurizrIcons.FILE;
            }
        };
    }

    @NotNull
    public TreeElement[] getChildren() {
        return new TreeElement[0];
    }

    public static final /* synthetic */ PsiFile access$getElement$p(StructurizrStructureViewElement $this) {
        return $this.element;
    }
}
