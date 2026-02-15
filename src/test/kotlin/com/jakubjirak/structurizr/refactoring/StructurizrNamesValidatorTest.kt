package com.jakubjirak.structurizr.refactoring

import com.intellij.lang.refactoring.NamesValidator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StructurizrNamesValidator")
class StructurizrNamesValidatorTest {

    private val validator = StructurizrNamesValidator()

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `can be instantiated`() {
            assertNotNull(validator)
        }

        @Test
        fun `implements NamesValidator`() {
            assertInstanceOf(NamesValidator::class.java, validator)
        }

        @Test
        fun `class is final`() {
            assertTrue(java.lang.reflect.Modifier.isFinal(StructurizrNamesValidator::class.java.modifiers))
        }
    }

    @Nested
    @DisplayName("isIdentifier - valid identifiers")
    inner class ValidIdentifiers {

        @Test
        fun `mySystem is a valid identifier`() {
            assertTrue(validator.isIdentifier("mySystem", null))
        }

        @Test
        fun `system1 is a valid identifier`() {
            assertTrue(validator.isIdentifier("system1", null))
        }

        @Test
        fun `_private is a valid identifier`() {
            assertTrue(validator.isIdentifier("_private", null))
        }

        @Test
        fun `a is a valid identifier`() {
            assertTrue(validator.isIdentifier("a", null))
        }

        @Test
        fun `camelCase is a valid identifier`() {
            assertTrue(validator.isIdentifier("camelCase", null))
        }

        @Test
        fun `under_score is a valid identifier`() {
            assertTrue(validator.isIdentifier("under_score", null))
        }

        @Test
        fun `abc123 is a valid identifier`() {
            assertTrue(validator.isIdentifier("abc123", null))
        }

        @Test
        fun `_leading underscore is valid`() {
            assertTrue(validator.isIdentifier("_x", null))
        }
    }

    @Nested
    @DisplayName("isIdentifier - invalid identifiers")
    inner class InvalidIdentifiers {

        @Test
        fun `empty string is not a valid identifier`() {
            assertFalse(validator.isIdentifier("", null))
        }

        @Test
        fun `1start is not a valid identifier`() {
            assertFalse(validator.isIdentifier("1start", null))
        }

        @Test
        fun `has space is not a valid identifier`() {
            assertFalse(validator.isIdentifier("has space", null))
        }

        @Test
        fun `has-dash is not a valid identifier`() {
            assertFalse(validator.isIdentifier("has-dash", null))
        }

        @Test
        fun `has dot is not a valid identifier`() {
            assertFalse(validator.isIdentifier("has.dot", null))
        }

        @Test
        fun `123 is not a valid identifier`() {
            assertFalse(validator.isIdentifier("123", null))
        }
    }

    @Nested
    @DisplayName("isIdentifier - keywords are not identifiers")
    inner class KeywordsNotIdentifiers {

        @Test
        fun `workspace is not a valid identifier`() {
            assertFalse(validator.isIdentifier("workspace", null))
        }

        @Test
        fun `model is not a valid identifier`() {
            assertFalse(validator.isIdentifier("model", null))
        }

        @Test
        fun `softwareSystem is not a valid identifier`() {
            assertFalse(validator.isIdentifier("softwareSystem", null))
        }
    }

    @Nested
    @DisplayName("isKeyword - recognized keywords")
    inner class RecognizedKeywords {

        @Test
        fun `workspace is a keyword`() {
            assertTrue(validator.isKeyword("workspace", null))
        }

        @Test
        fun `model is a keyword`() {
            assertTrue(validator.isKeyword("model", null))
        }

        @Test
        fun `views is a keyword`() {
            assertTrue(validator.isKeyword("views", null))
        }

        @Test
        fun `person is a keyword`() {
            assertTrue(validator.isKeyword("person", null))
        }

        @Test
        fun `softwareSystem is a keyword`() {
            assertTrue(validator.isKeyword("softwareSystem", null))
        }

        @Test
        fun `container is a keyword`() {
            assertTrue(validator.isKeyword("container", null))
        }

        @Test
        fun `component is a keyword`() {
            assertTrue(validator.isKeyword("component", null))
        }

        @Test
        fun `deploymentEnvironment is a keyword`() {
            assertTrue(validator.isKeyword("deploymentEnvironment", null))
        }

        @Test
        fun `deploymentNode is a keyword`() {
            assertTrue(validator.isKeyword("deploymentNode", null))
        }

        @Test
        fun `styles is a keyword`() {
            assertTrue(validator.isKeyword("styles", null))
        }

        @Test
        fun `configuration is a keyword`() {
            assertTrue(validator.isKeyword("configuration", null))
        }

        @Test
        fun `this is a keyword`() {
            assertTrue(validator.isKeyword("this", null))
        }

        @Test
        fun `true is a keyword`() {
            assertTrue(validator.isKeyword("true", null))
        }

        @Test
        fun `false is a keyword`() {
            assertTrue(validator.isKeyword("false", null))
        }
    }

    @Nested
    @DisplayName("isKeyword - non-keywords")
    inner class NonKeywords {

        @Test
        fun `mySystem is not a keyword`() {
            assertFalse(validator.isKeyword("mySystem", null))
        }

        @Test
        fun `abc is not a keyword`() {
            assertFalse(validator.isKeyword("abc", null))
        }

        @Test
        fun `foo is not a keyword`() {
            assertFalse(validator.isKeyword("foo", null))
        }

        @Test
        fun `empty string is not a keyword`() {
            assertFalse(validator.isKeyword("", null))
        }

        @Test
        fun `random text is not a keyword`() {
            assertFalse(validator.isKeyword("randomText123", null))
        }
    }

    @Nested
    @DisplayName("KEYWORDS set via reflection")
    inner class KeywordsSetTests {

        @Suppress("UNCHECKED_CAST")
        private fun getKeywordsSet(): Set<String> {
            val field = StructurizrNamesValidator::class.java.getDeclaredField("KEYWORDS")
            field.isAccessible = true
            return field.get(null) as Set<String>
        }

        @Test
        fun `KEYWORDS set exists`() {
            assertNotNull(getKeywordsSet())
        }

        @Test
        fun `KEYWORDS set is not empty`() {
            assertTrue(getKeywordsSet().isNotEmpty())
        }

        @Test
        fun `KEYWORDS contains all core keywords`() {
            val keywords = getKeywordsSet()
            val expected = listOf(
                "workspace", "model", "views", "styles", "configuration",
                "person", "softwareSystem", "container", "component"
            )
            expected.forEach { assertTrue(keywords.contains(it), "Missing keyword: $it") }
        }

        @Test
        fun `KEYWORDS contains deployment keywords`() {
            val keywords = getKeywordsSet()
            listOf("deploymentEnvironment", "deploymentNode", "deploymentGroup",
                "infrastructureNode", "containerInstance", "softwareSystemInstance")
                .forEach { assertTrue(keywords.contains(it), "Missing keyword: $it") }
        }

        @Test
        fun `KEYWORDS contains view keywords`() {
            val keywords = getKeywordsSet()
            listOf("systemLandscape", "systemContext", "dynamic", "deployment", "custom", "filtered", "image")
                .forEach { assertTrue(keywords.contains(it), "Missing keyword: $it") }
        }
    }
}
