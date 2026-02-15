package com.jakubjirak.structurizr.navigation;

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReferenceBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructurizrIncludeReference extends PsiReferenceBase<PsiElement> {

    private final String filePath;

    public StructurizrIncludeReference(@NotNull PsiElement element, @NotNull TextRange rangeInElement) {
        super(element, rangeInElement);
        String text = element.getText();
        // Strip quotes if present
        String raw = rangeInElement.substring(text);
        this.filePath = raw.replace("\"", "").trim();
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        if (filePath.isEmpty()) return null;

        PsiFile containingFile = myElement.getContainingFile();
        if (containingFile == null) return null;

        VirtualFile currentFile = containingFile.getVirtualFile();
        if (currentFile == null) return null;

        VirtualFile parentDir = currentFile.getParent();
        if (parentDir == null) return null;

        VirtualFile targetFile = parentDir.findFileByRelativePath(filePath);
        if (targetFile == null || targetFile.isDirectory()) return null;

        PsiFile psiFile = PsiManager.getInstance(myElement.getProject()).findFile(targetFile);
        return psiFile;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return EMPTY_ARRAY;
    }
}
