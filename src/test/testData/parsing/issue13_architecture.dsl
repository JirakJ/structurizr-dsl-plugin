workspace "Semantic Verification Framework" "Project-neutral reusable infrastructure for semantic verification." {
    name "Semantic Verification Framework"

    configuration {
        scope softwaresystem
    }

    model {
        dev = person "Consumer developer / verification workflow" "Defines verification cases"
        consumer = softwareSystem "Consumer System" "Uses SVF"
        storage = softwareSystem "Campaign Storage" "Stores campaign results"
        svf = softwareSystem "Semantic Verification Framework" "Target architecture" {
            lib = container "SVF Library" "Public API and case model" "Python" {
                api = component "Public API & Definitions" "Entry point" "Python"
                exec = component "Case Execution" "Runs cases"
            }
            workflow = container "Verification Workflow" "Orchestrates campaigns" "Python"
            codex = container "Codex Adapter" "Delegates to Codex" "Python"
            claude = container "Claude Code Adapter" "Delegates to Claude Code" "Python"
        }
        dev -> consumer "Defines cases"
        consumer -> api "Uses" "Python import"
        api -> exec "Delegates"
        exec -> workflow "Starts"
        workflow -> codex "Delegates"
        workflow -> claude "Delegates"
        workflow -> storage "Writes results"
    }

    views {
        systemLandscape "landscape" {
            include *
            autolayout lr
        }
        systemContext svf "context" "SVF in context" {
            include *
            autolayout lr
        }
        container svf "containers" {
            include *
            autolayout lr
        }
        component lib "components" {
            include *
            autoLayout
        }
        styles {
            element "Person" {
                shape person
            }
        }
    }
}
