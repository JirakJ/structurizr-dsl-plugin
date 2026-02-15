# Changelog

All notable changes to the Structurizr DSL Plugin are documented here.

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
- Live diagram preview (Pro) with built-in renderer + CLI fallback
- Split editor (DSL + diagram side by side)
- Export to SVG, PNG, PlantUML, Mermaid
- Copy to clipboard as SVG, image, PlantUML, Mermaid
- Structurizr CLI auto-download and integration
- Global and project-level settings
- New File action for .dsl files
- Color settings page with 19 configurable attributes
