// Complete Structurizr DSL example
/* Multi-line block comment
   spanning multiple lines */

!const SYSTEM_NAME "My System"
!identifiers hierarchical

workspace "Big Bank plc" "Internet Banking System" {

    model {
        enterprise "Big Bank plc" {
            user = person "Personal Banking Customer" "A customer of the bank" "Customer"

            softwareSystem = softwareSystem "Internet Banking System" "Allows customers to manage accounts" {
                webApp = container "Web Application" "Delivers static content" "Java/Spring Boot" "Web Browser"
                apiApp = container "API Application" "Provides banking functionality" "Java/Spring Boot"
                database = container "Database" "Stores user info" "PostgreSQL" "Database"

                webApp -> apiApp "Makes API calls" "HTTPS/JSON"
                apiApp -> database "Reads from and writes to" "JDBC"
            }
        }

        user -> softwareSystem "Views account balances"
    }

    views {
        systemContext softwareSystem "SystemContext" "System Context diagram" {
            include *
            autoLayout lr 300 200
        }

        container softwareSystem "Containers" {
            include *
            autoLayout
        }

        styles {
            element "Person" {
                shape Person
                background #08427B
                color #ffffff
                fontSize 22
            }

            element "Software System" {
                background #1168BD
                color #ffffff
            }

            relationship "Relationship" {
                thickness 2
                dashed false
            }
        }

        themes https://static.structurizr.com/themes/default/theme.json
    }

    configuration {
        users {
            "user@example.com" read
        }
    }
}
