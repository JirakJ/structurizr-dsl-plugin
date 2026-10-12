/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.lang.ASTNode
 *  com.intellij.lang.LightPsiParser
 *  com.intellij.lang.PsiBuilder
 *  com.intellij.lang.PsiBuilder$Marker
 *  com.intellij.lang.PsiParser
 *  com.intellij.lang.parser.GeneratedParserUtilBase
 *  com.intellij.lang.parser.GeneratedParserUtilBase$Parser
 *  com.intellij.psi.tree.IElementType
 */
package com.jakubjirak.structurizr.language.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.psi.tree.IElementType;
import com.jakubjirak.structurizr.language.psi.StructurizrTypes;

public class StructurizrParser
implements PsiParser,
LightPsiParser {
    public ASTNode parse(IElementType root_, PsiBuilder builder_) {
        this.parseLight(root_, builder_);
        return builder_.getTreeBuilt();
    }

    public void parseLight(IElementType root_, PsiBuilder builder_) {
        builder_ = GeneratedParserUtilBase.adapt_builder_((IElementType)root_, (PsiBuilder)builder_, (PsiParser)this, null);
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)0, (int)1, null);
        boolean result_ = this.parse_root_(root_, builder_);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)0, (PsiBuilder.Marker)marker_, (IElementType)root_, (boolean)result_, (boolean)true, (GeneratedParserUtilBase.Parser)GeneratedParserUtilBase.TRUE_CONDITION);
    }

    protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
        return StructurizrParser.parse_root_(root_, builder_, 0);
    }

    static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
        return StructurizrParser.structurizrFile(builder_, level_ + 1);
    }

    public static boolean adrs_directive(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"adrs_directive")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_ADRS)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_ADRS);
        result_ = result_ && StructurizrParser.adrs_directive_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.ADRS_DIRECTIVE, (boolean)result_);
        return result_;
    }

    private static boolean adrs_directive_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"adrs_directive_1")) {
            return false;
        }
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        }
        if (!result_) {
            result_ = StructurizrParser.file_path(builder_, level_ + 1);
        }
        return result_;
    }

    public static boolean animation_block(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"animation_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.ANIMATION)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.ANIMATION, StructurizrTypes.LBRACE});
        result_ = result_ && StructurizrParser.animation_block_2(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.ANIMATION_BLOCK, (boolean)result_);
        return result_;
    }

    private static boolean animation_block_2(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"animation_block_2")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.animation_step(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"animation_block_2", (int)pos_));
        return true;
    }

    public static boolean animation_step(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"animation_step")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.ANIMATION_STEP, (String)"<animation step>");
        boolean result_ = StructurizrParser.animation_step_0(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.animation_step_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean animation_step_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"animation_step_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.animation_step_0_1(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean animation_step_0_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"animation_step_0_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.element_reference(builder_, level_ + 1);
        while (result_) {
            int pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
            if (StructurizrParser.element_reference(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"animation_step_0_1", (int)pos_)) continue;
            break;
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean animation_step_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"animation_step_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.element_reference(builder_, level_ + 1);
        while (result_) {
            int pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
            if (StructurizrParser.element_reference(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"animation_step_1", (int)pos_)) continue;
            break;
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    public static boolean auto_layout_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"auto_layout_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.AUTO_LAYOUT)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.AUTO_LAYOUT);
        result_ = result_ && StructurizrParser.auto_layout_statement_1(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.auto_layout_statement_2(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.auto_layout_statement_3(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.AUTO_LAYOUT_STATEMENT, (boolean)result_);
        return result_;
    }

    private static boolean auto_layout_statement_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"auto_layout_statement_1")) {
            return false;
        }
        StructurizrParser.layout_direction(builder_, level_ + 1);
        return true;
    }

    private static boolean auto_layout_statement_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"auto_layout_statement_2")) {
            return false;
        }
        StructurizrParser.rank_separation(builder_, level_ + 1);
        return true;
    }

    private static boolean auto_layout_statement_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"auto_layout_statement_3")) {
            return false;
        }
        StructurizrParser.node_separation(builder_, level_ + 1);
        return true;
    }

    public static boolean branding_block(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"branding_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.BRANDING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.BRANDING, StructurizrTypes.LBRACE});
        result_ = result_ && StructurizrParser.branding_block_2(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.BRANDING_BLOCK, (boolean)result_);
        return result_;
    }

    private static boolean branding_block_2(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"branding_block_2")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.branding_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"branding_block_2", (int)pos_));
        return true;
    }

    static boolean branding_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"branding_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.logo_statement(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.font_statement(builder_, level_ + 1);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean component_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<component definition>", (IElementType[])new IElementType[]{StructurizrTypes.COMPONENT, StructurizrTypes.IDENTIFIER})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.COMPONENT_DEFINITION, (String)"<component definition>");
        boolean result_ = StructurizrParser.component_definition_0(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.component_definition_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean component_definition_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.component_definition_0_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.COMPONENT, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.component_definition_0_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.component_definition_0_4(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.component_definition_0_5(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.component_definition_0_7(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean component_definition_0_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_0_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean component_definition_0_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_0_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean component_definition_0_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_0_4")) {
            return false;
        }
        StructurizrParser.technology_string(builder_, level_ + 1);
        return true;
    }

    private static boolean component_definition_0_5(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_0_5")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    private static boolean component_definition_0_7(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_0_7")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.element_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"component_definition_0_7", (int)pos_));
        return true;
    }

    private static boolean component_definition_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.component_definition_1_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.COMPONENT, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.component_definition_1_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.component_definition_1_4(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.component_definition_1_5(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean component_definition_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_1_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean component_definition_1_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_1_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean component_definition_1_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_1_4")) {
            return false;
        }
        StructurizrParser.technology_string(builder_, level_ + 1);
        return true;
    }

    private static boolean component_definition_1_5(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_definition_1_5")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    public static boolean component_view(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_view")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.COMPONENT)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.COMPONENT_VIEW, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.COMPONENT);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.element_reference(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.component_view_2(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.component_view_3(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParserRecovery.openBlock(builder_)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.component_view_5(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean component_view_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_view_2")) {
            return false;
        }
        StructurizrParser.view_key(builder_, level_ + 1);
        return true;
    }

    private static boolean component_view_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_view_3")) {
            return false;
        }
        StructurizrParser.view_description(builder_, level_ + 1);
        return true;
    }

    private static boolean component_view_5(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"component_view_5")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.view_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"component_view_5", (int)pos_));
        return true;
    }

    public static boolean configuration_block(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"configuration_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.CONFIGURATION)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.CONFIGURATION_BLOCK, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)1, (IElementType[])new IElementType[]{StructurizrTypes.CONFIGURATION, StructurizrTypes.LBRACE});
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.configuration_block_2(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean configuration_block_2(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"configuration_block_2")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.configuration_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"configuration_block_2", (int)pos_));
        return true;
    }

    static boolean configuration_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"configuration_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.users_block(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.scope_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.visibility_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.properties_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean constant_directive(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"constant_directive")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<constant directive>", (IElementType[])new IElementType[]{StructurizrTypes.DIRECTIVE_CONST, StructurizrTypes.DIRECTIVE_CONSTANT})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.CONSTANT_DIRECTIVE, (String)"<constant directive>");
        boolean result_ = StructurizrParser.constant_directive_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        result_ = result_ && StructurizrParser.value_literal(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean constant_directive_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"constant_directive_0")) {
            return false;
        }
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_CONSTANT);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_CONST);
        }
        return result_;
    }

    static boolean container_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.component_definition(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.group_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.relationship_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.element_content(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean container_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<container definition>", (IElementType[])new IElementType[]{StructurizrTypes.CONTAINER, StructurizrTypes.IDENTIFIER})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.CONTAINER_DEFINITION, (String)"<container definition>");
        boolean result_ = StructurizrParser.container_definition_0(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.container_definition_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean container_definition_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.container_definition_0_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.CONTAINER, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.container_definition_0_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.container_definition_0_4(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.container_definition_0_5(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.container_definition_0_7(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean container_definition_0_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_0_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean container_definition_0_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_0_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean container_definition_0_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_0_4")) {
            return false;
        }
        StructurizrParser.technology_string(builder_, level_ + 1);
        return true;
    }

    private static boolean container_definition_0_5(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_0_5")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    private static boolean container_definition_0_7(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_0_7")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.container_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"container_definition_0_7", (int)pos_));
        return true;
    }

    private static boolean container_definition_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.container_definition_1_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.CONTAINER, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.container_definition_1_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.container_definition_1_4(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.container_definition_1_5(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean container_definition_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_1_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean container_definition_1_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_1_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean container_definition_1_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_1_4")) {
            return false;
        }
        StructurizrParser.technology_string(builder_, level_ + 1);
        return true;
    }

    private static boolean container_definition_1_5(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_definition_1_5")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    public static boolean container_instance_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_instance_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<container instance definition>", (IElementType[])new IElementType[]{StructurizrTypes.CONTAINER_INSTANCE, StructurizrTypes.IDENTIFIER})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.CONTAINER_INSTANCE_DEFINITION, (String)"<container instance definition>");
        boolean result_ = StructurizrParser.container_instance_definition_0(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.container_instance_definition_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean container_instance_definition_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_instance_definition_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.container_instance_definition_0_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.CONTAINER_INSTANCE);
        result_ = result_ && StructurizrParser.element_reference(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.container_instance_definition_0_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.container_instance_definition_0_4(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.container_instance_definition_0_6(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean container_instance_definition_0_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_instance_definition_0_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean container_instance_definition_0_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_instance_definition_0_3")) {
            return false;
        }
        StructurizrParser.deployment_groups(builder_, level_ + 1);
        return true;
    }

    private static boolean container_instance_definition_0_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_instance_definition_0_4")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    private static boolean container_instance_definition_0_6(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_instance_definition_0_6")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.element_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"container_instance_definition_0_6", (int)pos_));
        return true;
    }

    private static boolean container_instance_definition_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_instance_definition_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.container_instance_definition_1_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.CONTAINER_INSTANCE);
        result_ = result_ && StructurizrParser.element_reference(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.container_instance_definition_1_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.container_instance_definition_1_4(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean container_instance_definition_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_instance_definition_1_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean container_instance_definition_1_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_instance_definition_1_3")) {
            return false;
        }
        StructurizrParser.deployment_groups(builder_, level_ + 1);
        return true;
    }

    private static boolean container_instance_definition_1_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_instance_definition_1_4")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    public static boolean container_view(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_view")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.CONTAINER)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.CONTAINER_VIEW, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.CONTAINER);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.element_reference(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.container_view_2(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.container_view_3(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParserRecovery.openBlock(builder_)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.container_view_5(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean container_view_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_view_2")) {
            return false;
        }
        StructurizrParser.view_key(builder_, level_ + 1);
        return true;
    }

    private static boolean container_view_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_view_3")) {
            return false;
        }
        StructurizrParser.view_description(builder_, level_ + 1);
        return true;
    }

    private static boolean container_view_5(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"container_view_5")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.view_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"container_view_5", (int)pos_));
        return true;
    }

    public static boolean custom_view(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"custom_view")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.CUSTOM)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.CUSTOM_VIEW, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.CUSTOM);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.custom_view_1(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.custom_view_2(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParserRecovery.openBlock(builder_)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.custom_view_4(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean custom_view_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"custom_view_1")) {
            return false;
        }
        StructurizrParser.view_key(builder_, level_ + 1);
        return true;
    }

    private static boolean custom_view_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"custom_view_2")) {
            return false;
        }
        StructurizrParser.view_description(builder_, level_ + 1);
        return true;
    }

    private static boolean custom_view_4(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"custom_view_4")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.view_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"custom_view_4", (int)pos_));
        return true;
    }

    public static boolean default_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"default_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DEFAULT)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DEFAULT);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.DEFAULT_STATEMENT, (boolean)result_);
        return result_;
    }

    static boolean deployment_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.deployment_group_block(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.deployment_node_definition(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.relationship_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.directive(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean deployment_environment_block(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_environment_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DEPLOYMENT_ENVIRONMENT)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.DEPLOYMENT_ENVIRONMENT_BLOCK, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)1, (IElementType[])new IElementType[]{StructurizrTypes.DEPLOYMENT_ENVIRONMENT, StructurizrTypes.STRING, StructurizrTypes.LBRACE});
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.deployment_environment_block_3(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean deployment_environment_block_3(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_environment_block_3")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.deployment_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"deployment_environment_block_3", (int)pos_));
        return true;
    }

    public static boolean deployment_group_block(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_group_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DEPLOYMENT_GROUP)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.DEPLOYMENT_GROUP, StructurizrTypes.STRING});
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.DEPLOYMENT_GROUP_BLOCK, (boolean)result_);
        return result_;
    }

    public static boolean deployment_groups(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_groups")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        result_ = result_ && StructurizrParser.deployment_groups_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.DEPLOYMENT_GROUPS, (boolean)result_);
        return result_;
    }

    private static boolean deployment_groups_1(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_groups_1")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.deployment_groups_1_0(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"deployment_groups_1", (int)pos_));
        return true;
    }

    private static boolean deployment_groups_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_groups_1_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.COMMA, StructurizrTypes.IDENTIFIER});
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    static boolean deployment_node_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.deployment_node_definition(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.infrastructure_node_definition(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.software_system_instance_definition(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.container_instance_definition(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.relationship_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.element_content(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean deployment_node_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<deployment node definition>", (IElementType[])new IElementType[]{StructurizrTypes.DEPLOYMENT_NODE, StructurizrTypes.IDENTIFIER})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.DEPLOYMENT_NODE_DEFINITION, (String)"<deployment node definition>");
        boolean result_ = StructurizrParser.deployment_node_definition_0(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.deployment_node_definition_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean deployment_node_definition_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.deployment_node_definition_0_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.DEPLOYMENT_NODE, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.deployment_node_definition_0_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.deployment_node_definition_0_4(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.deployment_node_definition_0_5(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.deployment_node_definition_0_6(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.deployment_node_definition_0_8(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean deployment_node_definition_0_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_0_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_node_definition_0_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_0_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_node_definition_0_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_0_4")) {
            return false;
        }
        StructurizrParser.technology_string(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_node_definition_0_5(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_0_5")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_node_definition_0_6(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_0_6")) {
            return false;
        }
        StructurizrParser.instances_string(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_node_definition_0_8(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_0_8")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.deployment_node_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"deployment_node_definition_0_8", (int)pos_));
        return true;
    }

    private static boolean deployment_node_definition_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.deployment_node_definition_1_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.DEPLOYMENT_NODE, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.deployment_node_definition_1_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.deployment_node_definition_1_4(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.deployment_node_definition_1_5(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.deployment_node_definition_1_6(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean deployment_node_definition_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_1_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_node_definition_1_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_1_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_node_definition_1_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_1_4")) {
            return false;
        }
        StructurizrParser.technology_string(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_node_definition_1_5(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_1_5")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_node_definition_1_6(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_node_definition_1_6")) {
            return false;
        }
        StructurizrParser.instances_string(builder_, level_ + 1);
        return true;
    }

    public static boolean deployment_view(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_view")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DEPLOYMENT)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.DEPLOYMENT_VIEW, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DEPLOYMENT);
        // "deployment * <environment>" shows every software system deployed in the environment.
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)(StructurizrParser.element_reference(builder_, level_ + 1) || GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STAR)));
        // The environment may be given by name ("Live") or by the identifier it was assigned to (live).
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)(GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING) || GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER))) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.deployment_view_3(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.deployment_view_4(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParserRecovery.openBlock(builder_)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.deployment_view_6(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean deployment_view_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_view_3")) {
            return false;
        }
        StructurizrParser.view_key(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_view_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_view_4")) {
            return false;
        }
        StructurizrParser.view_description(builder_, level_ + 1);
        return true;
    }

    private static boolean deployment_view_6(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"deployment_view_6")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.view_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"deployment_view_6", (int)pos_));
        return true;
    }

    public static boolean description_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"description_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DESCRIPTION)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.DESCRIPTION, StructurizrTypes.STRING});
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.DESCRIPTION_STATEMENT, (boolean)result_);
        return result_;
    }

    public static boolean description_string(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"description_string")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.DESCRIPTION_STRING, (boolean)result_);
        return result_;
    }

    public static boolean directive(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"directive")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.DIRECTIVE, (String)"<directive>");
        boolean result_ = StructurizrParser.include_directive(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.docs_directive(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.adrs_directive(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.identifiers_directive(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.implied_relationships_directive(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.constant_directive(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean docs_directive(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"docs_directive")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_DOCS)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_DOCS);
        result_ = result_ && StructurizrParser.docs_directive_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.DOCS_DIRECTIVE, (boolean)result_);
        return result_;
    }

    private static boolean docs_directive_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"docs_directive_1")) {
            return false;
        }
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        }
        if (!result_) {
            result_ = StructurizrParser.file_path(builder_, level_ + 1);
        }
        return result_;
    }

    public static boolean dynamic_view(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"dynamic_view")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DYNAMIC)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.DYNAMIC_VIEW, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DYNAMIC);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.dynamic_view_1(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.dynamic_view_2(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.dynamic_view_3(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParserRecovery.openBlock(builder_)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.dynamic_view_5(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean dynamic_view_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"dynamic_view_1")) {
            return false;
        }
        boolean result_ = StructurizrParser.element_reference(builder_, level_ + 1);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STAR);
        }
        return result_;
    }

    private static boolean dynamic_view_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"dynamic_view_2")) {
            return false;
        }
        StructurizrParser.view_key(builder_, level_ + 1);
        return true;
    }

    private static boolean dynamic_view_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"dynamic_view_3")) {
            return false;
        }
        StructurizrParser.view_description(builder_, level_ + 1);
        return true;
    }

    private static boolean dynamic_view_5(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"dynamic_view_5")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.dynamic_view_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"dynamic_view_5", (int)pos_));
        return true;
    }

    static boolean dynamic_view_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"dynamic_view_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.relationship_in_dynamic_view(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.auto_layout_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.title_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.description_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.properties_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean element_assignment(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"element_assignment")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.EQUALS});
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.ELEMENT_ASSIGNMENT, (boolean)result_);
        return result_;
    }

    static boolean element_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"element_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.tags_statement(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.url_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.properties_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.perspectives_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.description_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.relationship_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.directive(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean element_reference(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"element_reference")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<element reference>", (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.THIS})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.ELEMENT_REFERENCE, (String)"<element reference>");
        boolean result_ = StructurizrParser.element_reference_0(builder_, level_ + 1);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.THIS);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean element_reference_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"element_reference_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        result_ = result_ && StructurizrParser.element_reference_0_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean element_reference_0_1(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"element_reference_0_1")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.element_reference_0_1_0(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"element_reference_0_1", (int)pos_));
        return true;
    }

    private static boolean element_reference_0_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"element_reference_0_1_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.DOT, StructurizrTypes.IDENTIFIER});
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    public static boolean element_selector(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"element_selector")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.ELEMENT_SELECTOR, (String)"<element selector>");
        boolean result_ = StructurizrParser.element_reference(builder_, level_ + 1);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean element_style(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"element_style")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.ELEMENT)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.ELEMENT, StructurizrTypes.STRING, StructurizrTypes.LBRACE});
        result_ = result_ && StructurizrParser.element_style_3(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.ELEMENT_STYLE, (boolean)result_);
        return result_;
    }

    private static boolean element_style_3(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"element_style_3")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while ((StructurizrParser.style_property(builder_, level_ + 1) || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1)) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"element_style_3", (int)pos_));
        return true;
    }

    public static boolean enterprise_block(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"enterprise_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.ENTERPRISE)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.ENTERPRISE);
        result_ = result_ && StructurizrParser.enterprise_block_1(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.enterprise_block_3(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.ENTERPRISE_BLOCK, (boolean)result_);
        return result_;
    }

    private static boolean enterprise_block_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"enterprise_block_1")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        return true;
    }

    private static boolean enterprise_block_3(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"enterprise_block_3")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.model_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"enterprise_block_3", (int)pos_));
        return true;
    }

    public static boolean exclude_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"exclude_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.EXCLUDE)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.EXCLUDE);
        result_ = result_ && StructurizrParser.exclude_statement_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.EXCLUDE_STATEMENT, (boolean)result_);
        return result_;
    }

    private static boolean exclude_statement_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"exclude_statement_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.element_selector(builder_, level_ + 1);
        while (result_) {
            int pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
            if (StructurizrParser.element_selector(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"exclude_statement_1", (int)pos_)) continue;
            break;
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    public static boolean file_path(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"file_path")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        result_ = result_ && StructurizrParser.file_path_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.FILE_PATH, (boolean)result_);
        return result_;
    }

    private static boolean file_path_1(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"file_path_1")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.file_path_1_0(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"file_path_1", (int)pos_));
        return true;
    }

    private static boolean file_path_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"file_path_1_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.DOT, StructurizrTypes.IDENTIFIER});
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    public static boolean filter_mode(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"filter_mode")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<filter mode>", (IElementType[])new IElementType[]{StructurizrTypes.EXCLUDE, StructurizrTypes.INCLUDE})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.FILTER_MODE, (String)"<filter mode>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.INCLUDE);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.EXCLUDE);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean filter_tags(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"filter_tags")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.FILTER_TAGS, (boolean)result_);
        return result_;
    }

    public static boolean filtered_view(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"filtered_view")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.FILTERED)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.FILTERED_VIEW, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.FILTERED);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.view_key(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.filtered_view_2(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.filtered_view_3(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.filtered_view_4(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.filtered_view_5(builder_, level_ + 1)) && result_;
        // Structurizr's filtered views take no block; an (older) block form is still accepted.
        if (GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE)) {
            result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE) && result_;
            result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.filtered_view_7(builder_, level_ + 1)) && result_;
            result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean filtered_view_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"filtered_view_2")) {
            return false;
        }
        StructurizrParser.filter_mode(builder_, level_ + 1);
        return true;
    }

    private static boolean filtered_view_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"filtered_view_3")) {
            return false;
        }
        StructurizrParser.filter_tags(builder_, level_ + 1);
        return true;
    }

    private static boolean filtered_view_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"filtered_view_4")) {
            return false;
        }
        StructurizrParser.view_key(builder_, level_ + 1);
        return true;
    }

    private static boolean filtered_view_5(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"filtered_view_5")) {
            return false;
        }
        StructurizrParser.view_description(builder_, level_ + 1);
        return true;
    }

    private static boolean filtered_view_7(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"filtered_view_7")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.view_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"filtered_view_7", (int)pos_));
        return true;
    }

    public static boolean font_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"font_statement")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.FONT_STATEMENT, (String)"<font statement>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (String)"font");
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        result_ = result_ && StructurizrParser.font_statement_2(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean font_statement_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"font_statement_2")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        return true;
    }

    public static boolean group_block(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"group_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.GROUP)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.GROUP, StructurizrTypes.STRING, StructurizrTypes.LBRACE});
        result_ = result_ && StructurizrParser.group_block_3(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.GROUP_BLOCK, (boolean)result_);
        return result_;
    }

    private static boolean group_block_3(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"group_block_3")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.model_group_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"group_block_3", (int)pos_));
        return true;
    }

    public static boolean identifiers_directive(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"identifiers_directive")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_IDENTIFIERS)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_IDENTIFIERS);
        result_ = result_ && StructurizrParser.identifiers_directive_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.IDENTIFIERS_DIRECTIVE, (boolean)result_);
        return result_;
    }

    private static boolean identifiers_directive_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"identifiers_directive_1")) {
            return false;
        }
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.HIERARCHICAL);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.FLAT);
        }
        return result_;
    }

    static boolean image_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"image_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.image_plantuml(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.image_mermaid(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.image_kroki(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.title_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.description_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.properties_block(builder_, level_ + 1);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean image_kroki(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"image_kroki")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.IMAGE_KROKI, (String)"<image kroki>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (String)"kroki");
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.STRING});
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean image_mermaid(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"image_mermaid")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.IMAGE_MERMAID, (String)"<image mermaid>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (String)"mermaid");
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean image_plantuml(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"image_plantuml")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.IMAGE_PLANTUML, (String)"<image plantuml>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (String)"plantuml");
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean image_view(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"image_view")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.IMAGE)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.IMAGE_VIEW, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IMAGE);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.image_view_1(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.image_view_2(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParserRecovery.openBlock(builder_)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.image_view_4(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean image_view_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"image_view_1")) {
            return false;
        }
        boolean result_ = StructurizrParser.element_reference(builder_, level_ + 1);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STAR);
        }
        return result_;
    }

    private static boolean image_view_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"image_view_2")) {
            return false;
        }
        StructurizrParser.view_key(builder_, level_ + 1);
        return true;
    }

    private static boolean image_view_4(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"image_view_4")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.image_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"image_view_4", (int)pos_));
        return true;
    }

    public static boolean implied_relationships_directive(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"implied_relationships_directive")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_IMPLIED_RELATIONSHIPS)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_IMPLIED_RELATIONSHIPS);
        result_ = result_ && StructurizrParser.implied_relationships_directive_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.IMPLIED_RELATIONSHIPS_DIRECTIVE, (boolean)result_);
        return result_;
    }

    private static boolean implied_relationships_directive_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"implied_relationships_directive_1")) {
            return false;
        }
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.TRUE);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.FALSE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        }
        return result_;
    }

    public static boolean include_directive(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"include_directive")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_INCLUDE)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_INCLUDE);
        result_ = result_ && StructurizrParser.include_directive_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.INCLUDE_DIRECTIVE, (boolean)result_);
        return result_;
    }

    private static boolean include_directive_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"include_directive_1")) {
            return false;
        }
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        }
        if (!result_) {
            result_ = StructurizrParser.file_path(builder_, level_ + 1);
        }
        return result_;
    }

    public static boolean include_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"include_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.INCLUDE)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.INCLUDE);
        result_ = result_ && StructurizrParser.include_statement_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.INCLUDE_STATEMENT, (boolean)result_);
        return result_;
    }

    private static boolean include_statement_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"include_statement_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STAR);
        if (!result_) {
            result_ = StructurizrParser.include_statement_1_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean include_statement_1_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"include_statement_1_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.element_selector(builder_, level_ + 1);
        while (result_) {
            int pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
            if (StructurizrParser.element_selector(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"include_statement_1_1", (int)pos_)) continue;
            break;
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    public static boolean infrastructure_node_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<infrastructure node definition>", (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.INFRASTRUCTURE_NODE})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.INFRASTRUCTURE_NODE_DEFINITION, (String)"<infrastructure node definition>");
        boolean result_ = StructurizrParser.infrastructure_node_definition_0(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.infrastructure_node_definition_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean infrastructure_node_definition_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.infrastructure_node_definition_0_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.INFRASTRUCTURE_NODE, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.infrastructure_node_definition_0_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.infrastructure_node_definition_0_4(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.infrastructure_node_definition_0_5(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.infrastructure_node_definition_0_7(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean infrastructure_node_definition_0_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_0_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean infrastructure_node_definition_0_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_0_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean infrastructure_node_definition_0_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_0_4")) {
            return false;
        }
        StructurizrParser.technology_string(builder_, level_ + 1);
        return true;
    }

    private static boolean infrastructure_node_definition_0_5(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_0_5")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    private static boolean infrastructure_node_definition_0_7(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_0_7")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.element_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"infrastructure_node_definition_0_7", (int)pos_));
        return true;
    }

    private static boolean infrastructure_node_definition_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.infrastructure_node_definition_1_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.INFRASTRUCTURE_NODE, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.infrastructure_node_definition_1_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.infrastructure_node_definition_1_4(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.infrastructure_node_definition_1_5(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean infrastructure_node_definition_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_1_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean infrastructure_node_definition_1_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_1_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean infrastructure_node_definition_1_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_1_4")) {
            return false;
        }
        StructurizrParser.technology_string(builder_, level_ + 1);
        return true;
    }

    private static boolean infrastructure_node_definition_1_5(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"infrastructure_node_definition_1_5")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    public static boolean instances_string(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"instances_string")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<instances string>", (IElementType[])new IElementType[]{StructurizrTypes.NUMBER, StructurizrTypes.STRING})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.INSTANCES_STRING, (String)"<instances string>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.NUMBER);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    static boolean item_(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"item_")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.directive(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.workspace_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        if (!result_) {
            // A fragment pulled in with !include has model content at the top level, without a workspace.
            result_ = StructurizrParser.model_content(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    public static boolean layout_direction(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"layout_direction")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.LAYOUT_DIRECTION, (String)"<layout direction>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LAYOUT_TB);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LAYOUT_BT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LAYOUT_LR);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LAYOUT_RL);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean logo_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"logo_statement")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.LOGO_STATEMENT, (String)"<logo statement>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (String)"logo");
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean model_block(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"model_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.MODEL)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.MODEL_BLOCK, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)1, (IElementType[])new IElementType[]{StructurizrTypes.MODEL, StructurizrTypes.LBRACE});
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.model_block_2(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean model_block_2(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"model_block_2")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.model_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"model_block_2", (int)pos_));
        return true;
    }

    static boolean model_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"model_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.person_definition(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.software_system_definition(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.deployment_environment_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.enterprise_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.group_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.relationship_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.ref_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.directive(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    static boolean model_group_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"model_group_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.person_definition(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.software_system_definition(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.relationship_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean node_separation(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"node_separation")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.NUMBER)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.NUMBER);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.NODE_SEPARATION, (boolean)result_);
        return result_;
    }

    public static boolean person_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"person_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<person definition>", (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.PERSON})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.PERSON_DEFINITION, (String)"<person definition>");
        boolean result_ = StructurizrParser.person_definition_0(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.person_definition_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean person_definition_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"person_definition_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.person_definition_0_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.PERSON, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.person_definition_0_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.person_definition_0_4(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.person_definition_0_6(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean person_definition_0_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"person_definition_0_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean person_definition_0_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"person_definition_0_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean person_definition_0_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"person_definition_0_4")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    private static boolean person_definition_0_6(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"person_definition_0_6")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.element_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"person_definition_0_6", (int)pos_));
        return true;
    }

    private static boolean person_definition_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"person_definition_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.person_definition_1_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.PERSON, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.person_definition_1_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.person_definition_1_4(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean person_definition_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"person_definition_1_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean person_definition_1_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"person_definition_1_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean person_definition_1_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"person_definition_1_4")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    public static boolean perspective_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"perspective_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.STRING, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.perspective_definition_2(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.PERSPECTIVE_DEFINITION, (boolean)result_);
        return result_;
    }

    private static boolean perspective_definition_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"perspective_definition_2")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    public static boolean perspectives_block(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"perspectives_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.PERSPECTIVES)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.PERSPECTIVES, StructurizrTypes.LBRACE});
        result_ = result_ && StructurizrParser.perspectives_block_2(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.PERSPECTIVES_BLOCK, (boolean)result_);
        return result_;
    }

    private static boolean perspectives_block_2(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"perspectives_block_2")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.perspective_definition(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"perspectives_block_2", (int)pos_));
        return true;
    }

    public static boolean properties_block(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"properties_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.PROPERTIES)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.PROPERTIES, StructurizrTypes.LBRACE});
        result_ = result_ && StructurizrParser.properties_block_2(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.PROPERTIES_BLOCK, (boolean)result_);
        return result_;
    }

    private static boolean properties_block_2(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"properties_block_2")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.property_definition(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"properties_block_2", (int)pos_));
        return true;
    }

    public static boolean property_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"property_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<property definition>", (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.STRING})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.PROPERTY_DEFINITION, (String)"<property definition>");
        boolean result_ = GeneratedParserUtilBase.parseTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.STRING});
        if (!result_) {
            result_ = GeneratedParserUtilBase.parseTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.STRING, StructurizrTypes.STRING});
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean rank_separation(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"rank_separation")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.NUMBER)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.NUMBER);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.RANK_SEPARATION, (boolean)result_);
        return result_;
    }

    public static boolean ref_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"ref_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<ref statement>", (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.REF})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.REF_STATEMENT, (String)"<ref statement>");
        boolean result_ = StructurizrParser.ref_statement_0(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.ref_statement_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean ref_statement_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"ref_statement_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.ref_statement_0_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.REF);
        result_ = result_ && StructurizrParser.element_reference(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.ref_statement_0_4(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean ref_statement_0_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"ref_statement_0_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean ref_statement_0_4(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"ref_statement_0_4")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.element_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"ref_statement_0_4", (int)pos_));
        return true;
    }

    private static boolean ref_statement_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"ref_statement_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.ref_statement_1_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.REF);
        result_ = result_ && StructurizrParser.element_reference(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean ref_statement_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"ref_statement_1_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    public static boolean relationship_description(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_description")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.RELATIONSHIP_DESCRIPTION, (boolean)result_);
        return result_;
    }

    public static boolean relationship_in_dynamic_view(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_in_dynamic_view")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<relationship in dynamic view>", (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.THIS})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.RELATIONSHIP_IN_DYNAMIC_VIEW, (String)"<relationship in dynamic view>");
        boolean result_ = StructurizrParser.element_reference(builder_, level_ + 1);
        boolean pinned_ = result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.ARROW);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.element_reference(builder_, level_ + 1));
        result_ = pinned_ && StructurizrParser.relationship_in_dynamic_view_3(builder_, level_ + 1) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean relationship_in_dynamic_view_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_in_dynamic_view_3")) {
            return false;
        }
        StructurizrParser.relationship_description(builder_, level_ + 1);
        return true;
    }

    public static boolean relationship_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<relationship statement>", (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.THIS})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.RELATIONSHIP_STATEMENT, (String)"<relationship statement>");
        boolean result_ = StructurizrParser.element_reference(builder_, level_ + 1);
        boolean pinned_ = result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.ARROW);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.element_reference(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.relationship_statement_3(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.relationship_statement_4(builder_, level_ + 1)) && result_;
        result_ = pinned_ && StructurizrParser.relationship_statement_5(builder_, level_ + 1) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean relationship_statement_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_statement_3")) {
            return false;
        }
        StructurizrParser.relationship_description(builder_, level_ + 1);
        return true;
    }

    private static boolean relationship_statement_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_statement_4")) {
            return false;
        }
        StructurizrParser.relationship_technology(builder_, level_ + 1);
        return true;
    }

    private static boolean relationship_statement_5(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_statement_5")) {
            return false;
        }
        StructurizrParser.relationship_tags(builder_, level_ + 1);
        return true;
    }

    public static boolean relationship_style(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_style")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.RELATIONSHIP)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.RELATIONSHIP, StructurizrTypes.STRING, StructurizrTypes.LBRACE});
        result_ = result_ && StructurizrParser.relationship_style_3(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.RELATIONSHIP_STYLE, (boolean)result_);
        return result_;
    }

    private static boolean relationship_style_3(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_style_3")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while ((StructurizrParser.style_property(builder_, level_ + 1) || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1)) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"relationship_style_3", (int)pos_));
        return true;
    }

    public static boolean relationship_tags(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_tags")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.RELATIONSHIP_TAGS, (boolean)result_);
        return result_;
    }

    public static boolean relationship_technology(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"relationship_technology")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.RELATIONSHIP_TECHNOLOGY, (boolean)result_);
        return result_;
    }

    public static boolean scope_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"scope_statement")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.SCOPE_STATEMENT, (String)"<scope statement>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (String)"scope");
        result_ = result_ && StructurizrParser.scope_statement_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean scope_statement_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"scope_statement_1")) {
            return false;
        }
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        }
        return result_;
    }

    public static boolean shape_value(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"shape_value")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.SHAPE_VALUE, (String)"<shape value>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_BOX);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_ROUNDED_BOX);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_CIRCLE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_ELLIPSE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_HEXAGON);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_CYLINDER);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_PIPE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_PERSON);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_ROBOT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_FOLDER);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_WEB_BROWSER);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_MOBILE_PORTRAIT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_MOBILE_LANDSCAPE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE_COMPONENT);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    static boolean software_system_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.container_definition(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.group_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.relationship_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.element_content(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean software_system_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<software system definition>", (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.SOFTWARE_SYSTEM})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.SOFTWARE_SYSTEM_DEFINITION, (String)"<software system definition>");
        boolean result_ = StructurizrParser.software_system_definition_0(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.software_system_definition_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean software_system_definition_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_definition_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.software_system_definition_0_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.SOFTWARE_SYSTEM, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.software_system_definition_0_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.software_system_definition_0_4(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.software_system_definition_0_6(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean software_system_definition_0_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_definition_0_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean software_system_definition_0_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_definition_0_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean software_system_definition_0_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_definition_0_4")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    private static boolean software_system_definition_0_6(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_definition_0_6")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.software_system_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"software_system_definition_0_6", (int)pos_));
        return true;
    }

    private static boolean software_system_definition_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_definition_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.software_system_definition_1_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.SOFTWARE_SYSTEM, StructurizrTypes.STRING});
        result_ = result_ && StructurizrParser.software_system_definition_1_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.software_system_definition_1_4(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean software_system_definition_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_definition_1_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean software_system_definition_1_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_definition_1_3")) {
            return false;
        }
        StructurizrParser.description_string(builder_, level_ + 1);
        return true;
    }

    private static boolean software_system_definition_1_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_definition_1_4")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    public static boolean software_system_instance_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_instance_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<software system instance definition>", (IElementType[])new IElementType[]{StructurizrTypes.IDENTIFIER, StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE_DEFINITION, (String)"<software system instance definition>");
        boolean result_ = StructurizrParser.software_system_instance_definition_0(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.software_system_instance_definition_1(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean software_system_instance_definition_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_instance_definition_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.software_system_instance_definition_0_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE);
        result_ = result_ && StructurizrParser.element_reference(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.software_system_instance_definition_0_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.software_system_instance_definition_0_4(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE);
        result_ = result_ && StructurizrParser.software_system_instance_definition_0_6(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean software_system_instance_definition_0_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_instance_definition_0_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean software_system_instance_definition_0_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_instance_definition_0_3")) {
            return false;
        }
        StructurizrParser.deployment_groups(builder_, level_ + 1);
        return true;
    }

    private static boolean software_system_instance_definition_0_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_instance_definition_0_4")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    private static boolean software_system_instance_definition_0_6(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_instance_definition_0_6")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.element_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"software_system_instance_definition_0_6", (int)pos_));
        return true;
    }

    private static boolean software_system_instance_definition_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_instance_definition_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.software_system_instance_definition_1_0(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SOFTWARE_SYSTEM_INSTANCE);
        result_ = result_ && StructurizrParser.element_reference(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.software_system_instance_definition_1_3(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.software_system_instance_definition_1_4(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean software_system_instance_definition_1_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_instance_definition_1_0")) {
            return false;
        }
        StructurizrParser.element_assignment(builder_, level_ + 1);
        return true;
    }

    private static boolean software_system_instance_definition_1_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_instance_definition_1_3")) {
            return false;
        }
        StructurizrParser.deployment_groups(builder_, level_ + 1);
        return true;
    }

    private static boolean software_system_instance_definition_1_4(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"software_system_instance_definition_1_4")) {
            return false;
        }
        StructurizrParser.tags_string(builder_, level_ + 1);
        return true;
    }

    static boolean structurizrFile(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"structurizrFile")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.item_(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"structurizrFile", (int)pos_));
        return true;
    }

    public static boolean style_property(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"style_property")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.STYLE_PROPERTY, (String)"<style property>");
        boolean result_ = StructurizrParser.style_property_name(builder_, level_ + 1);
        result_ = result_ && StructurizrParser.style_property_value(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean style_property_name(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"style_property_name")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.STYLE_PROPERTY_NAME, (String)"<style property name>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SHAPE);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.ICON);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.WIDTH);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.HEIGHT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BACKGROUND);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.COLOR);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.COLOUR);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STROKE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STROKE_WIDTH);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.FONT_SIZE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BORDER);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.OPACITY);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.METADATA);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.THICKNESS);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STYLE_PROP);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.ROUTING);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.POSITION);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DASHED);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DESCRIPTION);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean style_property_value(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"style_property_value")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.STYLE_PROPERTY_VALUE, (String)"<style property value>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.NUMBER);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.COLOR_LITERAL);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.TRUE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.FALSE);
        }
        if (!result_) {
            result_ = StructurizrParser.shape_value(builder_, level_ + 1);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean styles_block(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"styles_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STYLES)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.STYLES_BLOCK, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)1, (IElementType[])new IElementType[]{StructurizrTypes.STYLES, StructurizrTypes.LBRACE});
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.styles_block_2(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean styles_block_2(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"styles_block_2")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.styles_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"styles_block_2", (int)pos_));
        return true;
    }

    static boolean styles_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"styles_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.element_style(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.relationship_style(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean system_context_view(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"system_context_view")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.SYSTEM_CONTEXT)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.SYSTEM_CONTEXT_VIEW, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SYSTEM_CONTEXT);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.element_reference(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.system_context_view_2(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.system_context_view_3(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParserRecovery.openBlock(builder_)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.system_context_view_5(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean system_context_view_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"system_context_view_2")) {
            return false;
        }
        StructurizrParser.view_key(builder_, level_ + 1);
        return true;
    }

    private static boolean system_context_view_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"system_context_view_3")) {
            return false;
        }
        StructurizrParser.view_description(builder_, level_ + 1);
        return true;
    }

    private static boolean system_context_view_5(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"system_context_view_5")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.view_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"system_context_view_5", (int)pos_));
        return true;
    }

    public static boolean system_landscape_view(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"system_landscape_view")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.SYSTEM_LANDSCAPE)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.SYSTEM_LANDSCAPE_VIEW, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SYSTEM_LANDSCAPE);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.system_landscape_view_1(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.system_landscape_view_2(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParserRecovery.openBlock(builder_)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.system_landscape_view_4(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean system_landscape_view_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"system_landscape_view_1")) {
            return false;
        }
        StructurizrParser.view_key(builder_, level_ + 1);
        return true;
    }

    private static boolean system_landscape_view_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"system_landscape_view_2")) {
            return false;
        }
        StructurizrParser.view_description(builder_, level_ + 1);
        return true;
    }

    private static boolean system_landscape_view_4(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"system_landscape_view_4")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.view_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"system_landscape_view_4", (int)pos_));
        return true;
    }

    public static boolean tags_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"tags_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.TAGS)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.TAGS);
        result_ = result_ && StructurizrParser.tags_statement_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.TAGS_STATEMENT, (boolean)result_);
        return result_;
    }

    private static boolean tags_statement_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"tags_statement_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        while (result_) {
            int pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
            if (GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"tags_statement_1", (int)pos_)) continue;
            break;
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    public static boolean tags_string(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"tags_string")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.TAGS_STRING, (boolean)result_);
        return result_;
    }

    public static boolean technology_string(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"technology_string")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.TECHNOLOGY_STRING, (boolean)result_);
        return result_;
    }

    public static boolean terminology_block(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"terminology_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.TERMINOLOGY)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.TERMINOLOGY, StructurizrTypes.LBRACE});
        result_ = result_ && StructurizrParser.terminology_block_2(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.TERMINOLOGY_BLOCK, (boolean)result_);
        return result_;
    }

    private static boolean terminology_block_2(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"terminology_block_2")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.terminology_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"terminology_block_2", (int)pos_));
        return true;
    }

    static boolean terminology_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"terminology_content")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = StructurizrParser.terminology_item(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean terminology_item(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"terminology_item")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.TERMINOLOGY_ITEM, (String)"<terminology item>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.ENTERPRISE);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.PERSON);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.SOFTWARE_SYSTEM);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.CONTAINER);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.COMPONENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DEPLOYMENT_NODE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.INFRASTRUCTURE_NODE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RELATIONSHIP);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    public static boolean themes_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"themes_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (String)"<themes statement>", (IElementType[])new IElementType[]{StructurizrTypes.THEME, StructurizrTypes.THEMES})) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.THEMES_STATEMENT, (String)"<themes statement>");
        boolean result_ = StructurizrParser.themes_statement_0(builder_, level_ + 1);
        if (!result_) {
            result_ = GeneratedParserUtilBase.parseTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.THEME, StructurizrTypes.STRING});
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean themes_statement_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"themes_statement_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.THEMES);
        result_ = result_ && StructurizrParser.themes_statement_0_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean themes_statement_0_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"themes_statement_0_1")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        while (result_) {
            int pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
            if (GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"themes_statement_0_1", (int)pos_)) continue;
            break;
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    public static boolean title_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"title_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.TITLE)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.TITLE, StructurizrTypes.STRING});
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.TITLE_STATEMENT, (boolean)result_);
        return result_;
    }

    public static boolean url_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"url_statement")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.URL)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.URL, StructurizrTypes.STRING});
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.URL_STATEMENT, (boolean)result_);
        return result_;
    }

    public static boolean user_definition(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"user_definition")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        result_ = result_ && StructurizrParser.user_definition_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.USER_DEFINITION, (boolean)result_);
        return result_;
    }

    private static boolean user_definition_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"user_definition_1")) {
            return false;
        }
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.READ);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.WRITE);
        }
        return result_;
    }

    public static boolean users_block(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"users_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.USERS)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.USERS, StructurizrTypes.LBRACE});
        result_ = result_ && StructurizrParser.users_block_2(builder_, level_ + 1);
        result_ = result_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.USERS_BLOCK, (boolean)result_);
        return result_;
    }

    private static boolean users_block_2(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"users_block_2")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.user_definition(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"users_block_2", (int)pos_));
        return true;
    }

    public static boolean value_literal(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"value_literal")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.VALUE_LITERAL, (String)"<value literal>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.NUMBER);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.IDENTIFIER);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.COLOR_LITERAL);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.TRUE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.FALSE);
        }
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    static boolean view_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"view_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.include_statement(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.exclude_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.auto_layout_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.default_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.animation_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.title_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.description_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.properties_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean view_description(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"view_description")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.VIEW_DESCRIPTION, (boolean)result_);
        return result_;
    }

    public static boolean view_key(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"view_key")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.VIEW_KEY, (boolean)result_);
        return result_;
    }

    public static boolean views_block(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"views_block")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.VIEWS)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.VIEWS_BLOCK, null);
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)1, (IElementType[])new IElementType[]{StructurizrTypes.VIEWS, StructurizrTypes.LBRACE});
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.views_block_2(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, null);
        return result_ || pinned_;
    }

    private static boolean views_block_2(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"views_block_2")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.views_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"views_block_2", (int)pos_));
        return true;
    }

    static boolean views_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"views_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.system_landscape_view(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.system_context_view(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.container_view(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.component_view(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.dynamic_view(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.deployment_view(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.filtered_view(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.custom_view(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.image_view(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.styles_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.themes_statement(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.properties_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.branding_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.terminology_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.directive(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean visibility_statement(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"visibility_statement")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.VISIBILITY_STATEMENT, (String)"<visibility statement>");
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (String)"visibility");
        result_ = result_ && StructurizrParser.visibility_statement_1(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean visibility_statement_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"visibility_statement_1")) {
            return false;
        }
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.PRIVATE);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.PUBLIC);
        }
        return result_;
    }

    public static boolean workspace_block(PsiBuilder builder_, int level_) {
        boolean result_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_block")) {
            return false;
        }
        // Without this guard the recovery below ran for any top-level token and swallowed whole
        // !include fragments (model content without a workspace) as unparsed text.
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.WORKSPACE)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)0, (IElementType)StructurizrTypes.WORKSPACE_BLOCK, (String)"<workspace block>");
        boolean pinned_ = result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.WORKSPACE);
        result_ = result_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.workspace_block_1(builder_, level_ + 1));
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.workspace_block_2(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.workspace_block_3(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LBRACE)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.report_error_((PsiBuilder)builder_, (boolean)StructurizrParser.workspace_block_5(builder_, level_ + 1)) && result_;
        result_ = pinned_ && GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE) && result_;
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)pinned_, StructurizrParser::workspace_recovery);
        return result_ || pinned_;
    }

    private static boolean workspace_block_1(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_block_1")) {
            return false;
        }
        StructurizrParser.workspace_name(builder_, level_ + 1);
        return true;
    }

    private static boolean workspace_block_2(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_block_2")) {
            return false;
        }
        StructurizrParser.workspace_description(builder_, level_ + 1);
        return true;
    }

    private static boolean workspace_block_3(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_block_3")) {
            return false;
        }
        StructurizrParser.workspace_block_3_0(builder_, level_ + 1);
        return true;
    }

    private static boolean workspace_block_3_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_block_3_0")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeTokens((PsiBuilder)builder_, (int)0, (IElementType[])new IElementType[]{StructurizrTypes.EXTENDS, StructurizrTypes.STRING});
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, null, (boolean)result_);
        return result_;
    }

    private static boolean workspace_block_5(PsiBuilder builder_, int level_) {
        int pos_;
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_block_5")) {
            return false;
        }
        do {
            pos_ = GeneratedParserUtilBase.current_position_((PsiBuilder)builder_);
        } while (StructurizrParser.workspace_content(builder_, level_ + 1) && GeneratedParserUtilBase.empty_element_parsed_guard_((PsiBuilder)builder_, (String)"workspace_block_5", (int)pos_));
        return true;
    }

    static boolean workspace_content(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_content")) {
            return false;
        }
        boolean result_ = StructurizrParser.model_block(builder_, level_ + 1);
        if (!result_) {
            result_ = StructurizrParser.views_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.configuration_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.properties_block(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = StructurizrParser.directive(builder_, level_ + 1);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.LINE_COMMENT);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.BLOCK_COMMENT);
        }
        // Unknown statements are skipped instead of ending the block (see StructurizrParserRecovery).
        return result_ || StructurizrParserRecovery.unknownStatement(builder_, level_ + 1);
    }

    public static boolean workspace_description(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_description")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.WORKSPACE_DESCRIPTION, (boolean)result_);
        return result_;
    }

    public static boolean workspace_name(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_name")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING)) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_);
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.STRING);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (PsiBuilder.Marker)marker_, (IElementType)StructurizrTypes.WORKSPACE_NAME, (boolean)result_);
        return result_;
    }

    static boolean workspace_recovery(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_recovery")) {
            return false;
        }
        PsiBuilder.Marker marker_ = GeneratedParserUtilBase.enter_section_((PsiBuilder)builder_, (int)level_, (int)16);
        boolean result_ = !StructurizrParser.workspace_recovery_0(builder_, level_ + 1);
        GeneratedParserUtilBase.exit_section_((PsiBuilder)builder_, (int)level_, (PsiBuilder.Marker)marker_, (boolean)result_, (boolean)false, null);
        return result_;
    }

    private static boolean workspace_recovery_0(PsiBuilder builder_, int level_) {
        if (!GeneratedParserUtilBase.recursion_guard_((PsiBuilder)builder_, (int)level_, (String)"workspace_recovery_0")) {
            return false;
        }
        boolean result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.RBRACE);
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.WORKSPACE);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.MODEL);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.VIEWS);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.CONFIGURATION);
        }
        if (!result_) {
            result_ = GeneratedParserUtilBase.consumeToken((PsiBuilder)builder_, (IElementType)StructurizrTypes.DIRECTIVE_INCLUDE);
        }
        return result_;
    }
}
