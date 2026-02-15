package com.jakubjirak.structurizr.highlighting

import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.jakubjirak.structurizr.language.psi.StructurizrTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.lang.reflect.Modifier

@DisplayName("StructurizrAnnotator Enhanced")
class StructurizrAnnotatorTest {

    @Nested
    @DisplayName("Class structure")
    inner class ClassStructure {

        @Test
        fun `can be instantiated`() {
            val annotator = StructurizrAnnotator()
            assertNotNull(annotator)
        }

        @Test
        fun `implements Annotator interface`() {
            val annotator = StructurizrAnnotator()
            assertInstanceOf(Annotator::class.java, annotator)
        }

        @Test
        fun `class name is correct`() {
            assertEquals("StructurizrAnnotator", StructurizrAnnotator::class.java.simpleName)
        }

        @Test
        fun `has annotate method`() {
            val method = StructurizrAnnotator::class.java.getMethod(
                "annotate",
                com.intellij.psi.PsiElement::class.java,
                com.intellij.lang.annotation.AnnotationHolder::class.java
            )
            assertNotNull(method)
        }

        @Test
        fun `annotate method returns void`() {
            val method = StructurizrAnnotator::class.java.getMethod(
                "annotate",
                com.intellij.psi.PsiElement::class.java,
                com.intellij.lang.annotation.AnnotationHolder::class.java
            )
            assertEquals(Void.TYPE, method.returnType)
        }
    }

    @Nested
    @DisplayName("VARIABLE_REFERENCE TextAttributesKey")
    inner class VariableReferenceKeyTests {

        @Test
        fun `VARIABLE_REFERENCE field exists`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("VARIABLE_REFERENCE")
            field.isAccessible = true
            assertNotNull(field)
        }

        @Test
        fun `VARIABLE_REFERENCE is TextAttributesKey`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("VARIABLE_REFERENCE")
            field.isAccessible = true
            assertEquals(TextAttributesKey::class.java, field.type)
        }

        @Test
        fun `VARIABLE_REFERENCE is static`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("VARIABLE_REFERENCE")
            assertTrue(Modifier.isStatic(field.modifiers))
        }

        @Test
        fun `VARIABLE_REFERENCE is private`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("VARIABLE_REFERENCE")
            assertTrue(Modifier.isPrivate(field.modifiers))
        }

        @Test
        fun `VARIABLE_REFERENCE value is not null`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("VARIABLE_REFERENCE")
            field.isAccessible = true
            assertNotNull(field.get(null))
        }

        @Test
        fun `VARIABLE_REFERENCE has correct external name`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("VARIABLE_REFERENCE")
            field.isAccessible = true
            val key = field.get(null) as TextAttributesKey
            assertEquals("STRUCTURIZR_VARIABLE_REFERENCE", key.externalName)
        }

        @Test
        fun `getVARIABLE_REFERENCE static accessor exists`() {
            val method = StructurizrAnnotator::class.java.getMethod("getVARIABLE_REFERENCE")
            assertNotNull(method)
            assertTrue(Modifier.isStatic(method.modifiers))
        }

        @Test
        fun `getVARIABLE_REFERENCE returns correct key`() {
            val method = StructurizrAnnotator::class.java.getMethod("getVARIABLE_REFERENCE")
            val key = method.invoke(null) as TextAttributesKey
            assertNotNull(key)
            assertEquals("STRUCTURIZR_VARIABLE_REFERENCE", key.externalName)
        }
    }

    @Nested
    @DisplayName("DEFINITION_IDENTIFIER TextAttributesKey")
    inner class DefinitionIdentifierKeyTests {

        @Test
        fun `DEFINITION_IDENTIFIER field exists`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("DEFINITION_IDENTIFIER")
            field.isAccessible = true
            assertNotNull(field)
        }

        @Test
        fun `DEFINITION_IDENTIFIER is TextAttributesKey`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("DEFINITION_IDENTIFIER")
            field.isAccessible = true
            assertEquals(TextAttributesKey::class.java, field.type)
        }

        @Test
        fun `DEFINITION_IDENTIFIER value is not null`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("DEFINITION_IDENTIFIER")
            field.isAccessible = true
            assertNotNull(field.get(null))
        }

        @Test
        fun `DEFINITION_IDENTIFIER has correct external name`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("DEFINITION_IDENTIFIER")
            field.isAccessible = true
            val key = field.get(null) as TextAttributesKey
            assertEquals("STRUCTURIZR_DEFINITION_IDENTIFIER", key.externalName)
        }
    }

    @Nested
    @DisplayName("ARROW_OPERATOR TextAttributesKey")
    inner class ArrowOperatorKeyTests {

        @Test
        fun `ARROW_OPERATOR field exists`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("ARROW_OPERATOR")
            field.isAccessible = true
            assertNotNull(field)
        }

        @Test
        fun `ARROW_OPERATOR is TextAttributesKey`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("ARROW_OPERATOR")
            field.isAccessible = true
            assertEquals(TextAttributesKey::class.java, field.type)
        }

        @Test
        fun `ARROW_OPERATOR value is not null`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("ARROW_OPERATOR")
            field.isAccessible = true
            assertNotNull(field.get(null))
        }

        @Test
        fun `ARROW_OPERATOR has correct external name`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("ARROW_OPERATOR")
            field.isAccessible = true
            val key = field.get(null) as TextAttributesKey
            assertEquals("STRUCTURIZR_ARROW_OPERATOR", key.externalName)
        }
    }

    @Nested
    @DisplayName("DEPRECATED_DIRECTIVE TextAttributesKey")
    inner class DeprecatedDirectiveKeyTests {

        @Test
        fun `DEPRECATED_DIRECTIVE field exists`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("DEPRECATED_DIRECTIVE")
            field.isAccessible = true
            assertNotNull(field)
        }

        @Test
        fun `DEPRECATED_DIRECTIVE value is not null`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("DEPRECATED_DIRECTIVE")
            field.isAccessible = true
            assertNotNull(field.get(null))
        }

        @Test
        fun `DEPRECATED_DIRECTIVE has correct external name`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("DEPRECATED_DIRECTIVE")
            field.isAccessible = true
            val key = field.get(null) as TextAttributesKey
            assertEquals("STRUCTURIZR_DEPRECATED_DIRECTIVE", key.externalName)
        }
    }

    @Nested
    @DisplayName("All 4 TextAttributesKeys")
    inner class AllKeysTests {

        @Test
        fun `all 4 annotation keys are defined`() {
            val keyNames = listOf(
                "VARIABLE_REFERENCE",
                "DEFINITION_IDENTIFIER",
                "ARROW_OPERATOR",
                "DEPRECATED_DIRECTIVE"
            )
            keyNames.forEach { name ->
                val field = StructurizrAnnotator::class.java.getDeclaredField(name)
                field.isAccessible = true
                assertNotNull(field.get(null), "$name should not be null")
            }
        }

        @Test
        fun `all keys are unique`() {
            val keyNames = listOf(
                "VARIABLE_REFERENCE",
                "DEFINITION_IDENTIFIER",
                "ARROW_OPERATOR",
                "DEPRECATED_DIRECTIVE"
            )
            val externalNames = keyNames.map { name ->
                val field = StructurizrAnnotator::class.java.getDeclaredField(name)
                field.isAccessible = true
                (field.get(null) as TextAttributesKey).externalName
            }.toSet()
            assertEquals(4, externalNames.size, "All 4 keys should have unique external names")
        }

        @Test
        fun `all keys have STRUCTURIZR prefix`() {
            val keyNames = listOf(
                "VARIABLE_REFERENCE",
                "DEFINITION_IDENTIFIER",
                "ARROW_OPERATOR",
                "DEPRECATED_DIRECTIVE"
            )
            keyNames.forEach { name ->
                val field = StructurizrAnnotator::class.java.getDeclaredField(name)
                field.isAccessible = true
                val key = field.get(null) as TextAttributesKey
                assertTrue(key.externalName.startsWith("STRUCTURIZR_"), "$name should start with STRUCTURIZR_")
            }
        }
    }

    @Nested
    @DisplayName("SUBSTITUTION_PATTERN")
    inner class SubstitutionPatternTests {

        @Test
        fun `SUBSTITUTION_PATTERN field exists`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("SUBSTITUTION_PATTERN")
            field.isAccessible = true
            assertNotNull(field)
        }

        @Test
        fun `SUBSTITUTION_PATTERN is a Pattern`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("SUBSTITUTION_PATTERN")
            field.isAccessible = true
            assertEquals(java.util.regex.Pattern::class.java, field.type)
        }

        @Test
        fun `SUBSTITUTION_PATTERN matches dollar-brace expressions`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("SUBSTITUTION_PATTERN")
            field.isAccessible = true
            val pattern = field.get(null) as java.util.regex.Pattern
            assertTrue(pattern.matcher("\${variable}").find())
        }

        @Test
        fun `SUBSTITUTION_PATTERN does not match plain text`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("SUBSTITUTION_PATTERN")
            field.isAccessible = true
            val pattern = field.get(null) as java.util.regex.Pattern
            assertFalse(pattern.matcher("plain text").find())
        }

        @Test
        fun `SUBSTITUTION_PATTERN matches nested in string`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("SUBSTITUTION_PATTERN")
            field.isAccessible = true
            val pattern = field.get(null) as java.util.regex.Pattern
            val matcher = pattern.matcher("hello \${name} world")
            assertTrue(matcher.find())
            assertEquals("\${name}", matcher.group())
        }

        @Test
        fun `SUBSTITUTION_PATTERN matches multiple substitutions`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("SUBSTITUTION_PATTERN")
            field.isAccessible = true
            val pattern = field.get(null) as java.util.regex.Pattern
            val matcher = pattern.matcher("\${a} and \${b}")
            var count = 0
            while (matcher.find()) count++
            assertEquals(2, count)
        }

        @Test
        fun `SUBSTITUTION_PATTERN does not match empty braces`() {
            val field = StructurizrAnnotator::class.java.getDeclaredField("SUBSTITUTION_PATTERN")
            field.isAccessible = true
            val pattern = field.get(null) as java.util.regex.Pattern
            assertFalse(pattern.matcher("\${}").find())
        }
    }

    @Nested
    @DisplayName("Types used in annotation logic")
    inner class AnnotationTypeTests {

        @Test
        fun `STRING type exists for substitution highlighting`() {
            assertNotNull(StructurizrTypes.STRING)
        }

        @Test
        fun `IDENTIFIER type exists for definition highlighting`() {
            assertNotNull(StructurizrTypes.IDENTIFIER)
        }

        @Test
        fun `ELEMENT_ASSIGNMENT type exists for identifier context`() {
            assertNotNull(StructurizrTypes.ELEMENT_ASSIGNMENT)
        }

        @Test
        fun `ARROW type exists for arrow highlighting`() {
            assertNotNull(StructurizrTypes.ARROW)
        }

        @Test
        fun `ARROW_LEFT type exists for arrow highlighting`() {
            assertNotNull(StructurizrTypes.ARROW_LEFT)
        }

        @Test
        fun `ARROW_BOTH type exists for arrow highlighting`() {
            assertNotNull(StructurizrTypes.ARROW_BOTH)
        }

        @Test
        fun `REF type exists for deprecated directive highlighting`() {
            assertNotNull(StructurizrTypes.REF)
        }

        @Test
        fun `REF_STATEMENT type exists for deprecated directive context`() {
            assertNotNull(StructurizrTypes.REF_STATEMENT)
        }
    }
}
