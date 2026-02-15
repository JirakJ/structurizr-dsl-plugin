/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.cacheBuilder.DefaultWordsScanner
 *  com.intellij.lang.cacheBuilder.WordsScanner
 *  com.intellij.lang.findUsages.FindUsagesProvider
 *  com.intellij.lexer.Lexer
 *  com.intellij.psi.PsiElement
 *  com.intellij.psi.tree.IElementType
 *  com.intellij.psi.tree.TokenSet
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.navigation;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.lexer.Lexer;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.jakubjirak.structurizr.language.lexer.StructurizrLexerAdapter;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tH\u0016J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0007H\u0016\u00a8\u0006\u0011"}, d2={"Lcom/jakubjirak/structurizr/navigation/StructurizrFindUsagesProvider;", "Lcom/intellij/lang/findUsages/FindUsagesProvider;", "<init>", "()V", "getWordsScanner", "Lcom/intellij/lang/cacheBuilder/WordsScanner;", "canFindUsagesFor", "", "psiElement", "Lcom/intellij/psi/PsiElement;", "getHelpId", "", "getType", "element", "getDescriptiveName", "getNodeText", "useFullName", "structurizr-dsl-plugin"})
public final class StructurizrFindUsagesProvider
implements FindUsagesProvider {
    @NotNull
    public WordsScanner getWordsScanner() {
        IElementType[] iElementTypeArray = new IElementType[]{StructurizrTypes.IDENTIFIER};
        TokenSet tokenSet = TokenSet.create((IElementType[])iElementTypeArray);
        iElementTypeArray = new IElementType[]{StructurizrTypes.LINE_COMMENT, StructurizrTypes.BLOCK_COMMENT};
        TokenSet tokenSet2 = TokenSet.create((IElementType[])iElementTypeArray);
        iElementTypeArray = new IElementType[]{StructurizrTypes.STRING};
        return (WordsScanner)new DefaultWordsScanner((Lexer)new StructurizrLexerAdapter(), tokenSet, tokenSet2, TokenSet.create((IElementType[])iElementTypeArray));
    }

    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        Intrinsics.checkNotNullParameter((Object)psiElement, (String)"psiElement");
        if (psiElement.getNode().getElementType() == StructurizrTypes.IDENTIFIER) {
            PsiElement parent = psiElement.getParent();
            if (parent != null && parent.getNode().getElementType() == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public String getHelpId(@NotNull PsiElement psiElement) {
        Intrinsics.checkNotNullParameter((Object)psiElement, (String)"psiElement");
        return null;
    }

    @NotNull
    public String getType(@NotNull PsiElement element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        PsiElement parent = element.getParent();
        if (parent != null) {
            IElementType parentType = parent.getNode().getElementType();
            if (parentType == StructurizrTypes.ELEMENT_ASSIGNMENT) {
                // Look at the definition child of the assignment to determine type
                for (PsiElement child : parent.getChildren()) {
                    IElementType childType = child.getNode().getElementType();
                    if (childType == StructurizrTypes.PERSON_DEFINITION) return "person";
                    if (childType == StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION) return "software system";
                    if (childType == StructurizrTypes.CONTAINER_DEFINITION) return "container";
                    if (childType == StructurizrTypes.COMPONENT_DEFINITION) return "component";
                    if (childType == StructurizrTypes.DEPLOYMENT_NODE_DEFINITION) return "deployment node";
                    if (childType == StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION) return "infrastructure node";
                }
            }
        }
        return "element";
    }

    @NotNull
    public String getDescriptiveName(@NotNull PsiElement element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        if (element.getNode().getElementType() == StructurizrTypes.IDENTIFIER) {
            String text = element.getText();
            return text != null ? text : "";
        }
        String string = element.getText();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
        return string;
    }

    @NotNull
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        String string = element.getText();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getText(...)");
        return string;
    }
}
