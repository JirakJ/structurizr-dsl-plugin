package com.jakubjirak.structurizr.navigation;

import com.intellij.navigation.ChooseByNameContributorEx;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Processor;
import com.intellij.util.indexing.FindSymbolParameters;
import com.intellij.util.indexing.IdFilter;
import com.jakubjirak.structurizr.language.StructurizrFileType;
import com.jakubjirak.structurizr.language.psi.StructurizrElementAssignment;
import com.jakubjirak.structurizr.language.psi.StructurizrFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class StructurizrGotoSymbolContributor implements ChooseByNameContributorEx {

    @Override
    public void processNames(@NotNull Processor<? super String> processor,
                             @NotNull GlobalSearchScope scope,
                             @Nullable IdFilter filter) {
        Project project = scope.getProject();
        if (project == null) {
            return;
        }
        for (NavigationEntry entry : collectAllEntries(project, scope)) {
            if (!processor.process(entry.name)) {
                return;
            }
        }
    }

    @Override
    public void processElementsWithName(@NotNull String name,
                                         @NotNull Processor<? super NavigationItem> processor,
                                         @NotNull FindSymbolParameters parameters) {
        Project project = parameters.getProject();
        GlobalSearchScope scope = parameters.getSearchScope();
        for (NavigationEntry entry : collectAllEntries(project, scope)) {
            if (name.equals(entry.name)) {
                if (entry.element instanceof NavigationItem) {
                    if (!processor.process((NavigationItem) entry.element)) {
                        return;
                    }
                }
            }
        }
    }

    private static List<NavigationEntry> collectAllEntries(Project project, GlobalSearchScope scope) {
        List<NavigationEntry> result = new ArrayList<>();
        PsiManager psiManager = PsiManager.getInstance(project);
        FileTypeIndex.processFiles(StructurizrFileType.INSTANCE, virtualFile -> {
            PsiFile psiFile = psiManager.findFile(virtualFile);
            if (!(psiFile instanceof StructurizrFile)) {
                return true;
            }
            Collection<StructurizrElementAssignment> assignments =
                PsiTreeUtil.findChildrenOfType(psiFile, StructurizrElementAssignment.class);
            for (StructurizrElementAssignment assignment : assignments) {
                PsiElement identifier = assignment.getIdentifier();
                if (identifier != null) {
                    String text = identifier.getText();
                    if (text != null && !text.isEmpty()) {
                        result.add(new NavigationEntry(text, identifier));
                    }
                }
            }
            return true;
        }, scope);
        return result;
    }

    private static final class NavigationEntry {
        final String name;
        final PsiElement element;

        NavigationEntry(String name, PsiElement element) {
            this.name = name;
            this.element = element;
        }
    }
}
