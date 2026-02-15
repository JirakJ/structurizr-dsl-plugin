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
- [x] 866 unit tests

---

### 🔜 Next Release (v2026.3.x)

#### Must Have
- [x] ~~**FR-503: Context Validation Inspection**~~ ✅ v2026.2.4
- [x] ~~**FR-207: `!include` File Resolution**~~ ✅ v2026.2.4
- [ ] **FR-405: File Structure Popup** — Ctrl+F12 popup showing file structure (works via Structure View)
- [x] ~~**FR-407: Go to Symbol**~~ ✅ v2026.2.4
- [x] ~~**FR-504: Relationship Validation**~~ ✅ v2026.2.4
- [x] ~~**Rename Refactoring** (FR-607)~~ ✅ v2026.2.4

#### Should Have
- [x] ~~**FR-303: Tag Completion**~~ ✅ v2026.2.4
- [ ] **FR-304: Snippet Templates** — workspace, view, and deployment boilerplate snippets
- [x] ~~**FR-305: Style Property Completion**~~ ✅ v2026.2.4
- [x] ~~**FR-406: Navigate to !include'd Files**~~ ✅ v2026.2.4
- [x] ~~**FR-505: Deprecated Keyword Warnings**~~ ✅ v2026.2.4
- [x] ~~**FR-606: More Live Templates**~~ ✅ v2026.2.4 (7 new templates)

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
