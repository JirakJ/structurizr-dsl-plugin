workspace "Semantic Verification Framework" "Project-neutral reusable infrastructure for semantic verification." {

    configuration {
        scope softwaresystem
    }

    model {
        dev = person "Consumer developer / verification workflow" "Writes cases"
        consumer = softwareSystem "Consumer System" "Uses SVF"
        svf = softwareSystem "Semantic Verification Framework" "Target architecture" {
            lib = container "SVF Library" "Core" "Python" {
                api = component "Public API & Definitions" "Entry point" "Python"
                exec = component "Case Execution" "Runs cases"
            }
            codex = container "Codex Adapter" "Adapter" "Python"
        }
        dev -> consumer "Defines cases"
        consumer -> lib "Uses" "Python import"
        api -> exec "Delegates"
        lib -> codex "Delegates"
    }

    views {
        systemContext svf "context" {
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
