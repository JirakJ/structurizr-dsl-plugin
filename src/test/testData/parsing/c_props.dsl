workspace "X" "Y" {
    model {
        u = person "User" {
            tags "External"
            description "desc"
            properties {
                "owner" "team"
            }
        }
        s = softwareSystem "S" {
            tags "Internal" "Core"
            url "https://example.com"
            perspectives {
                "Security" "TLS"
            }
            c = container "C" {
                technology "Java"
                -> u "Notifies"
            }
        }
        u -> s "Uses" {
            tags "Async"
        }
        group "Team" {
            g = softwareSystem "G"
        }
    }
    views {
        systemLandscape "landscape" {
            include *
        }
    }
}
