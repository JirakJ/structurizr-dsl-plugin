# Changelog

All notable changes to the Structurizr DSL Plugin are documented here.

## [2026.6.0] - 2026-06-09

### Fixed
- **Critical: Broken lexer disabled the whole language engine** — A code-generation defect in `zzUnpacktrans` (transition table unpacking decremented values once per repetition instead of once per run) made the lexer throw `ArrayIndexOutOfBoundsException` on any input. The editor tokenized every file as a single `BAD_CHARACTER` (pink background, phantom "comment expected" error at EOF) and the parser never produced a PSI tree — silently disabling inspections, go-to-definition, find usages, structure view, completion of identifiers, and formatting. All these features now actually work. Regression-guarded by a new tokenization test suite that lexes real DSL.
- **`colour` values not validated** — The invalid-color inspection now also checks the British `colour` style property
- **Duplicate identifier inspection performance** — No longer rescans the whole file for every element (O(n²) → O(n))
- **Placeholder vendor email** in plugin metadata replaced with a real contact address

### Added
- **Color Preview & Picker** — Hex colors (`#RGB`, `#RRGGBB`, `#RRGGBBAA`) in `background`, `color`, `colour`, and `stroke` style properties show a swatch in the editor gutter; clicking it opens the IDE color picker and writes the chosen color back
- **Smart Relationship Completion** (FR-306) — Completing after `->` suggests only defined elements (excluding the relationship source), prioritized above keyword suggestions
- **Duplicate View Key Inspection** (FR-507) — Flags views sharing the same key, which Structurizr rejects at export/push time
- **4 New Live Templates** — `grp` (group), `props` (properties), `anim` (animation), `autolay` (autoLayout); 21 templates total
- **Marketplace screenshots** — Real IDE captures generated into `.github/docs/screenshots/`
- 73 new tests (965 total), including the first tests that actually execute the lexer

## [2026.4.5] - 2026-03-27

### Changed
- **Version bump** — Release with freemium licensing (code: `PSTRUCTURIZRDSL`)

## [2026.4.4] - 2026-03-11

### Fixed
- **Binary Incompatibility** — Resolved `TextEditorWithPreview.<init>` constructor mismatch that caused `NoSuchMethodError` on IntelliJ IDEA 2023.2–2023.3
- **Deprecated API Removal** — Replaced all 8 `getService()` calls with Kotlin `service<>()` extension
- **Deprecated `deleteRecursively()`** — Replaced with NIO `Files.walk()` + `Files.delete()` in export and renderer services
- **Inconsistent `SwingUtilities.invokeLater`** — Standardized to `ApplicationManager.getApplication().invokeLater`
- **Fragile `myPreview` field access** — Stored preview editor locally via constructor delegation

### Changed
- **Minimum IDE version** bumped from 2023.2 (build 232) to 2024.3 (build 243) to match compiled API surface
- Plugin verification targets updated to IC-2024.3 and IC-2025.1.3

## [2026.3.5] - 2026-03-06

### Fixed
- **Identifier Context Coloring** — Identifiers now match the ACE editor's state-machine behavior:
  - Identifiers after keywords (e.g., `ss` in `container ss "Diagram"`) → **gray** (#555555) — matching the ACE "default" state
  - Identifiers in relationships (e.g., `u` and `ss.wa` in `u -> ss.wa "Uses"`) → **red** (#772222) — matching the ACE variable token
  - Identifiers in include/exclude statements → **red** (variable)
  - Identifiers in animation blocks → **red** (variable)
  - Dots in qualified identifiers → context-dependent (red in relationships, gray elsewhere)
  - `*` wildcard → always **red** (variable)
- **Missing Keyword Recognition** — 8 keywords from the ACE editor that were missing from the lexer are now recognized via the annotator and colored **blue**:
  - `name`, `logo`, `scope`, `visibility`, `animationStep`, `instanceOf`, `light`, `dark`

## [2026.3.4] - 2026-03-06

### Fixed
- **Font Styles** — Removed bold/italic font styling that leaked from IntelliJ theme defaults:
  - Keywords now render as **plain** blue text (were bold due to IntelliJ KEYWORD fallback)
  - Properties now render as **plain** blue text (were italic due to INSTANCE_FIELD fallback)
  - Constants/substitutions now render as **plain** green text (were bold due to CONSTANT fallback)
  - Comments now render as **plain** gray text (were italic; the official website uses no italic)
  - All `FONT_TYPE=0` explicitly set in color scheme XMLs to prevent theme-dependent styling
- **Dot in Qualified Identifiers** — The `.` in references like `ss.wa` now renders as red (#772222) matching the official Structurizr website where `ss.wa` is a single red variable token

## [2026.3.3] - 2026-03-05

### Fixed
- **Token Classification** — Aligned syntax token coloring with the official Structurizr ACE editor at structurizr.com:
  - Shape values (Person, Cylinder, Box, etc.) now render as gray text (string) instead of green constants
  - Layout directions (lr, tb, rl, bt) now render as gray text instead of green constants
  - Boolean values (true/false) now render as gray text instead of green constants
  - Identifier mode keywords (hierarchical/flat) now render as blue (keyword) instead of green
  - Wildcard `*` (in include/exclude) now renders as red (variable) matching the website
  - Added handling for `${` (VAR_START) token — previously showed as bad character
  - Fixed `else` branch in highlighter — unknown tokens no longer render as error characters

## [2026.3.2] - 2026-03-05

### Fixed
- **Syntax Highlighting Colors** — Colors now match the official Structurizr website appearance (https://structurizr.com/dsl)
  - Keywords: Structurizr blue (#1168BD) — matching the official editor
  - Identifiers/variables: dark red (#772222 light / #aa2222 dark)
  - Strings: subtle gray (#555555 light / #aaaaaa dark)
  - Comments: medium gray (#999999), italic
  - Constants/substitutions: green (#00aa00)
  - Braces, arrows, operators: Structurizr blue (#1168BD)
- Added `additionalTextAttributes` for both Default (light) and Darcula (dark) schemes
- Plugin editor now provides a consistent, familiar look for Structurizr DSL users

## [2026.3.1] - 2026-03-05

### Changed
- **Full Kotlin Migration** — Migrated all 82 hand-written source files from decompiled Java to idiomatic Kotlin with proper KDoc documentation
- **License Headers** — Added copyright headers to all source files
- **CI/CD Pipeline** — Added GitHub Actions workflow for automated build, test, and verification on every push and PR
- Removed all decompilation artifacts (`@Metadata`, `Intrinsics`, `SourceDebugExtension`)
- Replaced Java collections and patterns with Kotlin idioms (`when`, `?.let`, `apply`, `companion object`, `object`, expression bodies)
- Updated 7 reflection-based test suites to match Kotlin class structure

### Fixed
- `StructurizrPsiImplUtil` compilation — replaced `Intrinsics.areEqual` with `Objects.equals`

## [2026.2.4] - 2026-02-15

### Added
- **Context Validation Inspection** (FR-503) — detects invalid parent-child nesting (container outside softwareSystem, etc.)
- **Relationship Validation Inspection** (FR-504) — warns on self-referencing relationships, unresolved targets, `this` outside element scope
- **Rename Refactoring** (FR-607) — Shift+F6 with identifier validation
- **Go to Symbol** (FR-407) — Ctrl+Alt+Shift+N for project-wide identifier search
- **!include File Navigation** (FR-207/FR-406) — Ctrl+Click on file paths in `!include` directives
- **Tag Completion** (FR-303) — suggests tags used across workspace
- **Style Property Completion** (FR-305) — context-aware completion for shapes, colors, borders, routing
- **Deprecated Keyword Strikethrough** (FR-505) — visual warning for `!ref`, `!extend`, `!constant`
- 7 new live templates: `depenv`, `depnode`, `dynview`, `filtview`, `infnode`, `inc`, `cst`
- 192 new tests (866 total)

## [2026.2.3] - 2026-02-15

### Added
- **Go to Definition** (Ctrl+Click/Ctrl+B) — PSI reference resolution for identifiers
- **Find Usages** (Alt+F7) — find all usages of defined elements
- **Structure View** — full hierarchical tree (workspace → model → views → elements)
- **Breadcrumbs** — navigation path for current scope
- **Line Markers** — gutter icons for person, softwareSystem, container, component, deployment nodes
- **Code Formatting** — proper indentation and spacing rules (Ctrl+Alt+L)
- **Identifier Completion** — suggests defined element names in relationships and views
- **Semantic Highlighting** — definition identifiers, arrow operators, deprecated directives
- **Reference Contributor** — enables cross-file identifier resolution
- 243 new tests (674 total)

## [2026.2.2] - 2026-02-15

### Added
- 431 unit tests covering lexer, parser, PSI, completion, inspections, navigation, formatting, highlighting, settings, export, i18n
- 10 Live Templates: `ws`, `per`, `ss`, `con`, `comp`, `rel`, `dep`, `sv`, `cv`, `sty`
- Missing SRS keywords in completion: archetypes, element, instanceOf, healthCheck, !components, !extend, !ref, !element, !elements, !relationship, !relationships
- `${...}` variable reference highlighting (FR-106)
- StructurizrLiveTemplateContext for DSL-specific templates
- Missing i18n bundle entries

### Fixed
- liveTemplateContext contextId registration
- OTHER_KEYWORDS iteration in CompletionProvider

## [2026.2.1] - 2026-02-15

### Added
- Full plugin restoration from marketplace JAR (original source lost)
- Syntax highlighting for all Structurizr DSL keywords
- Code completion for keywords, directives, shapes, style properties
- Code folding for all block constructs
- Brace matching and quote handling
- Comment/uncomment support (// and /* */)
- 7 inspections: undefined references, unused elements, duplicate IDs, empty blocks, missing properties, deprecated keywords, invalid colors
- Quick fixes: create element, remove unused, rename
- Documentation provider (Ctrl+Q) for 12 keywords
- Live diagram preview with built-in renderer + CLI fallback
- Split editor (DSL + diagram side by side)
- Export to SVG, PNG, PlantUML, Mermaid
- Copy to clipboard as SVG, image, PlantUML, Mermaid
- Structurizr CLI auto-download and integration
- Global and project-level settings
- New File action for .dsl files
- Color settings page with 19 configurable attributes
