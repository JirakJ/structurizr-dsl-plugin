# Changelog

All notable changes to the Structurizr DSL Plugin are documented here.

## [2026.8.0] - 2026-11-10

### Changed
- The plugin is now completely free — all features unlocked, no license needed.
- Source code is now open source under Apache-2.0: https://github.com/JirakJ/structurizr-dsl-plugin

## [2026.6.4] - 2026-10-12

### Fixed
- **The preview first showed "Open a .dsl file" and only drew the diagram after Refresh** ([docs#13](https://github.com/JirakJ/jetbrains-plugins-docs/issues/13#issuecomment-6125944359)) — both in the split editor and in the Structurizr Preview tool window. Until its native browser exists, JCEF keeps one deferred page and replays it from `onAfterCreated` via `invokeLater`; when the first render finished in that window it was loaded directly and then overwritten by the replayed placeholder. With the out-of-process JCEF of IntelliJ 2026.2 the window is wide enough to hit on every open. The panel no longer hands any page to JCEF before its browser has been created: pages wait in a small gate, the latest one is loaded from `onAfterCreated`, and later pages load directly — so there is no deferred placeholder left to replay. If the creation listener cannot be registered, pages load directly as before.

### Added
- 4 regression tests (1031 total) for the gate: nothing loads before creation, only the latest page loads on creation, pages load directly afterwards, repeated creation events are ignored.

## [2026.6.3] - 2026-10-12

### Fixed
- **Preview ignored the selected view** ([docs#13](https://github.com/JirakJ/jetbrains-plugins-docs/issues/13)) — the built-in renderer drew a grid of every element in the file whichever view was picked, and with a Structurizr CLI configured it silently failed (it exported PlantUML but looked for an SVG). The preview now has its own lenient workspace reader and draws the selected view the way Structurizr does: `include *` gives each view type its default content (landscape: people and systems; system context: the system and what it talks to; container/component view: the members of the scope inside a boundary plus their neighbours), explicit `include`/`exclude` identifiers are honoured, relationships of nested elements are lifted to what is on the diagram, deployment views group instances by deployment node, dynamic views number their steps, filtered views filter by tag. `!include` is resolved relative to the file. The same renderer now backs the SVG/PNG export and *Copy as SVG*, so they honour the view key too.
- **The view selector listed six fixed view types instead of the workspace's views** — it now lists exactly the views defined in `views { }` (key, type and scope), keeps the selection across edits, and offers Structurizr-style default views only when the workspace defines none.
- **Refresh threw `Read access is allowed from inside read-action only`** — the preview read the document on the EDT outside a read action. Documents are now read in a non-blocking read action on a background thread and only the result is applied on the EDT; the *Refresh Structurizr Preview* action (Ctrl+Shift+R), which previously did nothing, now refreshes every open preview.
- **Structure view lost the `views` block for many valid workspaces** — the editor parser had no error recovery: the first statement its grammar did not model (lower-case `autolayout`, `name`/`description` on the workspace, `x = deploymentEnvironment`, `"""` text blocks, `deployment * <env>`, an identifier as the deployment environment, `filtered` views without a block, extra arguments on a view header) ended the parse of the whole rest of the file. Unknown statements are now skipped one at a time and marked with a weak warning (so a typo such as `softwareSytem` is still visible), keywords are case-insensitive as in Structurizr wherever a keyword starts a statement (mixed-case identifiers such as `Users` keep working), and `!include` fragments without a `workspace` parse instead of being swallowed. The Structure view shows each view with its scope and key (`systemContext svf: context`) and element identifiers inline.
- **"Preview" button in the tool window header** — it was the name of the window's only tab; the tab is now unnamed, so the header reads just *Structurizr Preview*. The toolbar uses standard icon actions and the view selector takes the remaining width, so long view names no longer push Refresh and zoom out of sight; the diagram fits the window by default.

### Added
- 42 tests (1027 total): PSI recovery tests over real workspaces (every view kind, the reporter's model shape, fragments), Structure view output for the issue's workspace, lexer tests for case-insensitive keywords versus mixed-case identifiers, and preview engine tests for view selection, default views, deployment/dynamic/filtered views, nested `!include`, `!element` blocks and malformed input.
- Export and *Copy as SVG* render the editor's current (possibly unsaved) text, so they match what the preview shows.

## [2026.6.2] - 2026-10-11

### Fixed
- **`NullPointerException` in `StructurizrAnnotator` when opening a `.dsl` file** ([docs#12](https://github.com/JirakJ/jetbrains-plugins-docs/issues/12)) — `isInVariableContext` walked `PsiElement.parent` until it reached a block boundary or `null`. For an identifier with no enclosing `workspace`/`model`/`views` block (an `!include`d fragment, or a file whose parse is momentarily broken while typing) the walk passed the `PsiFile` and reached its `PsiDirectory`, whose `getNode()` is `null`, so `parent.node.elementType` threw. The IDE reported it on every open, and the failed annotator pass left highlighting incomplete.
- **Every upward PSI walk is now bounded at the file** — the same unbounded `x = x.parent` pattern also lived in style and relationship-target completion, `InvalidContextInspection`, `InvalidRelationshipInspection` and `RemoveUnusedElementQuickFix`. All of them now use the platform's `parents(withSelf = …)`, which stops at the `PsiFile`, and every `.node.elementType` read in the plugin uses the null-safe `PsiElement.elementType` extension instead.

### Added
- 4 regression tests (985 total): the reported tree (identifier → file → directory with no AST node) run through the annotator, plus a source guard that fails the build if `.node.elementType` or a hand-rolled `x = x.parent` loop reappears in `src/main`.

## [2026.6.1] - 2026-09-09

### Fixed
- **Critical: `.dsl` files could not be opened on IntelliJ 2026.2** ([docs#11](https://github.com/JirakJ/jetbrains-plugins-docs/issues/11)) — IntelliJ 2026.2 (build 262) extracted JCEF from the core platform into the separate bundled plugin `com.intellij.modules.jcef`. The plugin only declared `com.intellij.modules.platform`, so its classloader could no longer resolve `com.intellij.ui.jcef.JBCefApp`. The preview panel's guard caught `Exception`, but a missing class arrives as `NoClassDefFoundError` — an `Error` — so it escaped, `StructurizrSplitEditorProvider.createEditor` threw, and because that provider hides the default editor the file ended up with no editor at all: the tab blinked and closed instantly. The plugin now declares the JCEF module as an **optional** dependency, which restores the preview on 2026.2+ while keeping the plugin loadable on 2024.3–2026.1, where that module id does not exist and JCEF still comes from the core classloader.
- **Editing no longer depends on the diagram preview** — all JCEF access goes through a single guarded entry point (`JcefSupport`) that treats `LinkageError` as "no browser available". When JCEF is missing or broken, the preview pane shows an explanatory placeholder and the text editor, inspections, completion and navigation are unaffected. `StructurizrSplitEditorProvider` additionally falls back to the plain text editor if the split editor cannot be constructed at all.
- **Preview tool window could crash the same way** — `StructurizrPreviewContent` created a `JBCefBrowser` in a field initializer with no guard whatsoever; it is now null-safe and degrades to the same placeholder.

### Added
- 16 tests (981 total): regression coverage for `Error`-vs-`Exception` handling around JCEF, and descriptor tests asserting that the optional `com.intellij.modules.jcef` dependency and its config file stay in place.

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
- **Marketplace screenshots** — Real IDE captures generated into `docs/screenshots/`
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
