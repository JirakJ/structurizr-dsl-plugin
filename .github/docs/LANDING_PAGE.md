# Structurizr DSL — Architecture Diagrams as Code

> **Write, navigate, and preview C4 model diagrams directly in your JetBrains IDE.**

---

## The Problem

Software architecture diagrams live in drawing tools that get outdated the moment they are exported. Teams waste hours keeping diagrams in sync with code, and new developers have no reliable way to understand the system. The C4 model and Structurizr DSL solve this by treating diagrams as code — but without IDE support, writing DSL files is slow and error-prone.

---

## Key Features

### 1. Rich Syntax Highlighting
Full colorization for keywords, strings, comments, identifiers, directives, operators, colors, shapes, and `${}` variable substitutions. Customizable via the color settings page.

### 2. Intelligent Code Completion
Context-aware suggestions for elements, relationships, views, properties, and all DSL keywords. Type and let the IDE fill in the rest.

### 3. Navigation and Find Usages
Go to definition with Ctrl+Click, find all usages with Alt+F7, browse the structure view, follow breadcrumbs, and jump to any symbol with Go to Symbol.

### 4. 9 Code Inspections with Quick Fixes
Catches undefined references, unused elements, duplicate identifiers, empty blocks, missing properties, deprecated keywords, invalid colors, invalid contexts, and invalid relationships — all with suggested fixes.

### 5. 10 Live Templates
Scaffold workspaces, persons, software systems, containers, components, relationships, deployment nodes, and views with short abbreviations and Tab.

### 6. Live Diagram Preview (Pro)
Real-time C4 diagram rendering in a dedicated tool window or split editor view. See your architecture update as you type.

### 7. Multi-Format Export (Pro)
Export diagrams as SVG, PNG, PlantUML, or Mermaid. Copy to clipboard in any format for instant sharing.

### 8. Structurizr CLI Integration (Pro)
Auto-download and manage the Structurizr CLI. Render and validate diagrams without leaving the IDE.

---

## How It Works

1. **Create a DSL file** — Create a `.dsl` file in your project. Use the `ws` live template to scaffold a workspace.
2. **Write your architecture** — Define people, software systems, containers, and components. The IDE provides completion, navigation, and error checking as you type.
3. **Preview your diagrams** — Open the live preview panel to see your C4 diagrams rendered in real time (Pro).
4. **Export and share** — Export as SVG, PNG, PlantUML, or Mermaid for documentation, presentations, or wikis.

---

## Pricing

| Feature | Free | Pro |
|---------|:----:|:---:|
| Syntax highlighting | ✅ | ✅ |
| Code completion | ✅ | ✅ |
| Navigation and find usages | ✅ | ✅ |
| 9 code inspections | ✅ | ✅ |
| 10 live templates | ✅ | ✅ |
| Code folding and formatting | ✅ | ✅ |
| Live diagram preview | — | ✅ |
| Split editor view | — | ✅ |
| Multi-format export | — | ✅ |
| CLI integration | — | ✅ |
| Rename refactoring | — | ✅ |

---

## Testimonials

> *"This plugin transformed how our team documents architecture. We went from stale Confluence diagrams to living, version-controlled C4 models."*
> — **Senior Architect, Enterprise SaaS Company**

> *"The live preview alone is worth the Pro upgrade. I can see my diagrams update as I type — no more context switching."*
> — **Tech Lead, FinTech Startup**

> *"Finally, an IDE-native experience for Structurizr DSL. The inspections catch mistakes I used to find only after rendering."*
> — **Software Engineer, Consulting Firm**

---

## FAQ

**Q: Which JetBrains IDEs are supported?**
A: All JetBrains IDEs based on the IntelliJ Platform (2023.2+), including IntelliJ IDEA, WebStorm, PyCharm, GoLand, CLion, Rider, PhpStorm, RubyMine, and more.

**Q: Do I need to install the Structurizr CLI separately?**
A: No. The Pro tier auto-downloads and manages the CLI for you. The Free tier does not require the CLI at all.

**Q: Can I use this plugin with existing Structurizr workspaces?**
A: Yes. Open any `.dsl` file and the plugin provides full language support immediately.

**Q: What is the C4 model?**
A: The C4 model is a standard approach for visualizing software architecture at four levels: Context, Containers, Components, and Code. Structurizr DSL is the text-based language for defining C4 models.

**Q: Is the Free tier permanently free?**
A: Yes. The Free tier includes syntax highlighting, code completion, navigation, inspections, live templates, and formatting with no time limit.

---

## Get Started

Install from the [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/29351-structurizr-dsl) and start writing architecture diagrams as code today.
