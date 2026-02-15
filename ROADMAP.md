# Roadmap

## Structurizr DSL Plugin for JetBrains IDEs

### ✅ Completed (v2026.2.3)

- [x] Full lexer with all Structurizr DSL keywords (case-insensitive)
- [x] Generated parser with PSI tree hierarchy
- [x] Syntax highlighting (19 configurable color attributes)
- [x] Code completion (keywords + identifiers)
- [x] Go to Definition (Ctrl+Click)
- [x] Find Usages (Alt+F7)
- [x] Structure View (hierarchical)
- [x] Breadcrumbs navigation
- [x] Line markers (gutter icons)
- [x] Code folding, brace matching, commenter
- [x] Code formatting (indentation + spacing)
- [x] 7 inspections with quick fixes
- [x] Live templates (10 templates)
- [x] Documentation provider (Ctrl+Q)
- [x] ${} variable highlighting
- [x] Live diagram preview (Pro)
- [x] Export to SVG/PNG/PlantUML/Mermaid (Pro)
- [x] CLI integration with auto-download (Pro)
- [x] Global + project settings
- [x] 674 unit tests

---

### 🔜 Next Release (v2026.3.x)

#### Must Have
- [ ] **FR-503: Context Validation Inspection** — explicit inspection for invalid parent-child nesting (e.g., `container` outside `softwareSystem`)
- [ ] **FR-207: `!include` File Resolution** — resolve `!include` paths to actual files, enable Ctrl+Click on paths
- [ ] **FR-405: File Structure Popup** — Ctrl+F12 popup showing file structure
- [ ] **FR-407: Go to Symbol** — Ctrl+Alt+Shift+N for project-wide symbol search
- [ ] **FR-504: Relationship Validation** — validate allowed source/destination combinations per C4 model
- [ ] **Rename Refactoring** (FR-607) — Shift+F6 to rename identifiers across all references

#### Should Have
- [ ] **FR-303: Tag Completion** — collect and suggest tags used across the workspace
- [ ] **FR-304: Snippet Templates** — workspace, view, and deployment boilerplate snippets
- [ ] **FR-305: Style Property Completion** — complete shape, colour, icon values in styles blocks
- [ ] **FR-406: Navigate to !include'd Files** — Ctrl+Click on file paths in `!include` directives
- [ ] **FR-505: Deprecated Keyword Warnings** — strikethrough + suggestion for `!extend`, `!ref`
- [ ] **FR-606: More Live Templates** — deployment environment, dynamic view, filtered view

---

### 📋 Future Releases

#### Pro Features
- [ ] **FR-306: Smart Relationship Completion** — suggest valid targets based on C4 hierarchy
- [ ] **FR-507: Workspace-level Validation** — unique view keys, valid expressions
- [ ] **FR-608: Extract to !include** — refactor element definition to external file
- [ ] **FR-701-707: Enhanced Preview** — zoom, pan, click-to-navigate, dark mode, SVG/PNG export from preview
- [ ] **FR-803: Structurizr Cloud Sync** — push/pull workspaces

#### Enterprise Features  
- [ ] **FR-804: Multi-workspace Support** — multiple workspace files in one project
- [ ] **FR-805: Team Configuration Sharing** — shared styles, themes, settings

#### Quality & Performance
- [ ] **NFR-103: Large File Performance** — benchmark and optimize for 5000+ line files (<500ms parse)
- [ ] **NFR-105: Memory Optimization** — keep heap usage under 50MB
- [ ] **JaCoCo Coverage** — achieve 80% lang, 70% core, 60% editor coverage targets
- [ ] **Plugin Verifier** — CI pipeline with JetBrains Plugin Verifier for all supported IDE versions

---

### 🏗️ Technical Debt

- [ ] Migrate decompiled Java sources to Kotlin (262 files)
- [ ] Add source .bnf grammar file (currently using pre-generated parser)
- [ ] Add source .flex lexer file (currently using pre-generated lexer)
- [ ] Set up GitHub Actions CI/CD pipeline
- [ ] Add integration tests using CodeInsightTestFixture
- [ ] Add UI tests using RemoteRobot

---

*Last updated: 2026-02-15*
