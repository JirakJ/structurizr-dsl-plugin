workspace "V" "views" {
    !const ORG "Acme"
    model {
        properties {
            "structurizr.groupSeparator" "/"
        }
        u = person "User"
        s = softwareSystem "S" {
            web = container "Web" "" "React" "UI"
            api = container "API"
            web -> api "Calls" "JSON/HTTPS"
        }
        u -> web "Uses"
        prod = deploymentEnvironment "Production" {
            deploymentNode "Cloud" {
                webNode = deploymentNode "Web server" "" "nginx" "" 2 {
                    containerInstance web
                }
            }
        }
    }
    views {
        systemLandscape "landscape" "All systems" {
            include *
            autoLayout lr 300 300
        }
        systemContext s {
            include *
            exclude u
        }
        deployment * prod "prod" {
            include *
        }
        dynamic * "flow" "Request flow" {
            u -> web "Opens"
            web -> api "Fetches"
            autoLayout
        }
        dynamic s {
            u -> web
        }
        filtered "landscape" exclude "External" "landscape-internal"
        custom "c1" "Custom" "A custom view" {
            include u s
        }
        image s "img" {
            image https://example.com/x.png
        }
        styles {
            element "Element" {
                color #ffffff
                metadata false
                icon "x.png"
            }
            relationship "Relationship" {
                routing orthogonal
                dashed false
            }
        }
        themes default https://example.com/theme.json
    }
}
