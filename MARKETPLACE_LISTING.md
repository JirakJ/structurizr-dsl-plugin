# Structurizr DSL — JetBrains Marketplace Listing

---

## 📝 Plugin Information

| Field | Value |
|-------|-------|
| **Name** | Structurizr DSL |
| **Plugin ID** | `com.jakubjirak.structurizr` |
| **Version** | 2026.2.5 |
| **Category** | Languages |
| **Tags** | `structurizr`, `c4-model`, `architecture`, `dsl`, `diagrams`, `software-architecture`, `c4`, `documentation` |

---

## 🎯 Short Description

> Full language support for Structurizr DSL — syntax highlighting, code completion, go-to-definition, find usages, 7 inspections, live templates, and live C4 diagram preview in your JetBrains IDE.

---

## 📖 Full Description

### 🏗️ Architecture as Code — Finally, First-Class IDE Support for Structurizr DSL

**The Problem:** Writing Structurizr DSL in a plain text editor means no syntax highlighting, no code completion, no navigation, and no error detection. Switching between the editor and Structurizr CLI to preview diagrams kills your flow.

**The Solution:** This plugin brings full language support for Structurizr DSL to every JetBrains IDE. Get syntax highlighting, context-aware completion, go-to-definition, find usages, 7 code inspections with quick fixes, live templates, and a live C4 diagram preview — all without leaving your IDE.

---

### ✨ Key Features

#### 🎨 Syntax Highlighting
Full highlighting for keywords, strings, comments, identifiers, directives, operators, colors, and shapes. Variable substitution (`${}`) highlighting in strings.

#### 💡 Context-Aware Code Completion
Keyword completion plus identifier completion from defined elements. Press **Ctrl+Space** for intelligent suggestions based on your current scope.

#### 🔗 Go to Definition & Find Usages
**Ctrl+Click / Ctrl+B** to navigate to element definitions. **Alt+F7** to find all references to an element across your workspace.

#### 🔍 7 Code Inspections + Quick Fixes
Detects undefined references, unused elements, duplicate IDs, empty blocks, missing properties, deprecated keywords, and invalid colors — with one-click quick fixes to create missing elements, remove unused ones, and rename.

#### 📐 Structure View & Breadcrumbs
Hierarchical tree view showing workspace → model → views → elements. Navigation breadcrumbs showing your current scope in the DSL.

#### ✂️ Code Folding & Formatting
Fold/unfold all block constructs. Reformat code with **Ctrl+Alt+L**. Toggle comments with **Ctrl+/**.

#### 📝 10 Live Templates
Quickly scaffold DSL structures: `ws` (workspace), `per` (person), `ss` (software system), `con` (container), `comp` (component), `rel` (relationship), `dep` (deployment), `sv` (system view), `cv` (container view), `sty` (styles).

#### 📚 Hover Documentation
Press **Ctrl+Q** for documentation on any Structurizr DSL keyword.

#### 🖼️ Live Diagram Preview
Real-time C4 diagram rendering in a tool window. Split editor mode for side-by-side DSL code and diagram view.

#### 📤 Diagram Export
Export diagrams as SVG, PNG, PlantUML, or Mermaid. Copy diagrams to clipboard in any supported format.

#### 🔄 Rename Refactoring
Rename identifiers across all references in a single operation.

#### ⚡ CLI Integration
Auto-download and manage Structurizr CLI directly from the IDE.

---

### 💰 Pricing

Free & open source (Apache-2.0) — every feature is available to everyone, no license needed.

---

### 🎯 Perfect For

- **Software Architects** documenting systems with the C4 model
- **Development Teams** practicing architecture-as-code
- **Tech Leads** maintaining up-to-date architecture diagrams alongside code
- **DevOps Engineers** documenting deployment architectures
- **Anyone** using Structurizr DSL for software architecture documentation

---

### ⚙️ Requirements

| Requirement | Value |
|-------------|-------|
| **IDE Version** | Any JetBrains IDE 2023.2+ (build 232+) |
| **Java** | 17+ |
| **File Extension** | `.dsl` |

---

### 🚀 Quick Start

1. **Install** — Settings → Plugins → Marketplace → Search "Structurizr DSL"
2. **Create a file** — Create a new file with `.dsl` extension
3. **Scaffold** — Type `ws` + **Tab** to insert a workspace template
4. **Add elements** — Use `per`, `ss`, `con`, `comp` templates for quick scaffolding
5. **Navigate** — Use **Ctrl+Space** for completion, **Ctrl+Click** for navigation
6. **Preview** — Open the diagram preview tool window for live C4 rendering

---

## 📸 Screenshots & Media

| # | Screenshot | Description |
|---|-----------|-------------|
| 1 | `syntax-highlighting.png` | Full syntax highlighting for Structurizr DSL with keyword, string, comment, and identifier colors |
| 2 | `code-completion.png` | Context-aware code completion showing keyword and identifier suggestions |
| 3 | `go-to-definition.png` | Ctrl+Click navigation from a reference to its element definition |
| 4 | `inspections-quick-fixes.png` | Code inspections detecting undefined references and unused elements with quick-fix options |
| 5 | `structure-view.png` | Structure view showing hierarchical workspace → model → views → elements tree |
| 6 | `live-templates.png` | Live template expansion for workspace, person, and software system scaffolding |
| 7 | `live-diagram-preview.png` | Live C4 diagram preview in split editor mode with real-time rendering |
| 8 | `export-diagrams.png` | Export dialog with SVG, PNG, PlantUML, and Mermaid format options |

---

## 🏷️ Marketplace Categories

| Type | Category |
|------|----------|
| **Primary** | Languages |
| **Secondary** | Architecture, Documentation |

---

## 🔍 Keywords (SEO)

`structurizr`, `c4 model`, `software architecture`, `dsl`, `architecture diagram`, `c4 diagram`, `architecture as code`, `system context`, `container diagram`, `component diagram`, `deployment diagram`, `structurizr dsl`, `simon brown`, `plantuml`, `mermaid`, `architecture documentation`, `software design`

---

## 📋 Compatibility

| IDE | Supported |
|-----|-----------|
| IntelliJ IDEA Community | ✅ |
| IntelliJ IDEA Ultimate | ✅ |
| WebStorm | ✅ |
| PhpStorm | ✅ |
| PyCharm | ✅ |
| GoLand | ✅ |
| RubyMine | ✅ |
| CLion | ✅ |
| Rider | ✅ |
| DataGrip | ✅ |
| Android Studio | ✅ |
| Writerside | ✅ |
| Aqua | ✅ |
| MPS | ✅ |
| DataSpell | ✅ |
| RustRover | ✅ |

| Requirement | Value |
|-------------|-------|
| **Minimum IDE Build** | 232 (2023.2) |
| **Java Version** | 17+ |
| **OS Support** | Windows, macOS, Linux |

---

## 📞 Vendor Information

| Field | Value |
|-------|-------|
| **Vendor** | Jakub Jirak |
| **Email** | jirak@jakubjirak.com |
| **Website** | https://jakubjirak.com |
| **Support** | support@jakubjirak.com |
| **Source Code** | https://github.com/JirakJ/structurizr-dsl-plugin |
| **Marketplace** | https://plugins.jetbrains.com/plugin/29351-structurizr-dsl |

---

## ✅ Pre-Launch Checklist

- [ ] Plugin icon (40×40 and 80×80 SVG/PNG) included in repository
- [ ] Plugin description under 255 characters for short description field
- [ ] Full description formatted in HTML for Marketplace submission
- [ ] Screenshots captured at 1280×800 minimum resolution
- [ ] License agreement uploaded
- [ ] Plugin JAR/ZIP tested on IntelliJ IDEA 2023.2 (build 232)
- [ ] Plugin JAR/ZIP tested across multiple JetBrains IDEs (WebStorm, PyCharm, etc.)
- [ ] Plugin JAR/ZIP tested on latest IntelliJ IDEA version
- [ ] All 674 tests passing
- [ ] Change notes prepared for current version
- [ ] Vendor information verified and support email tested
- [ ] Plugin ID (`com.jakubjirak.structurizr`) registered on Marketplace
