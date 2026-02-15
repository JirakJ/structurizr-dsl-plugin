package com.jakubjirak.structurizr.refactoring;

import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public final class StructurizrNamesValidator implements NamesValidator {

    private static final Set<String> KEYWORDS = new HashSet<>();
    static {
        KEYWORDS.add("workspace");
        KEYWORDS.add("model");
        KEYWORDS.add("views");
        KEYWORDS.add("styles");
        KEYWORDS.add("configuration");
        KEYWORDS.add("person");
        KEYWORDS.add("softwareSystem");
        KEYWORDS.add("container");
        KEYWORDS.add("component");
        KEYWORDS.add("deploymentEnvironment");
        KEYWORDS.add("deploymentNode");
        KEYWORDS.add("deploymentGroup");
        KEYWORDS.add("infrastructureNode");
        KEYWORDS.add("containerInstance");
        KEYWORDS.add("softwareSystemInstance");
        KEYWORDS.add("group");
        KEYWORDS.add("enterprise");
        KEYWORDS.add("element");
        KEYWORDS.add("relationship");
        KEYWORDS.add("tags");
        KEYWORDS.add("description");
        KEYWORDS.add("technology");
        KEYWORDS.add("url");
        KEYWORDS.add("properties");
        KEYWORDS.add("perspectives");
        KEYWORDS.add("autoLayout");
        KEYWORDS.add("animation");
        KEYWORDS.add("title");
        KEYWORDS.add("include");
        KEYWORDS.add("exclude");
        KEYWORDS.add("default");
        KEYWORDS.add("systemLandscape");
        KEYWORDS.add("systemContext");
        KEYWORDS.add("dynamic");
        KEYWORDS.add("deployment");
        KEYWORDS.add("custom");
        KEYWORDS.add("filtered");
        KEYWORDS.add("image");
        KEYWORDS.add("theme");
        KEYWORDS.add("themes");
        KEYWORDS.add("branding");
        KEYWORDS.add("terminology");
        KEYWORDS.add("users");
        KEYWORDS.add("ref");
        KEYWORDS.add("this");
        KEYWORDS.add("true");
        KEYWORDS.add("false");
    }

    public boolean isKeyword(@NotNull String name, Project project) {
        return KEYWORDS.contains(name);
    }

    public boolean isIdentifier(@NotNull String name, Project project) {
        if (name.isEmpty()) {
            return false;
        }
        char first = name.charAt(0);
        if (!Character.isLetter(first) && first != '_') {
            return false;
        }
        for (int i = 1; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return !isKeyword(name, null);
    }
}
