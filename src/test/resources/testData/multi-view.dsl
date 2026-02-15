workspace "Multi-View Example" {

    model {
        user = person "User"
        system = softwareSystem "System" {
            web = container "Web App"
            api = container "API"
            db = container "Database"

            web -> api "Calls"
            api -> db "Reads/Writes"
        }
        user -> system "Uses"
    }

    views {
        systemLandscape "Landscape" {
            include *
            autoLayout
        }

        systemContext system "Context" {
            include *
            autoLayout
        }

        container system "Containers" {
            include *
            autoLayout
        }

        dynamic system "Dynamic" {
            user -> web "Requests page"
            web -> api "Calls API"
            api -> db "Queries"
            autoLayout
        }

        filtered "Landscape" include "Element" "FilteredView"

        styles {
            element "Person" {
                shape Person
            }
        }
    }
}
