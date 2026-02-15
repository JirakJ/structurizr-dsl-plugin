workspace "Deployment Example" {

    model {
        softwareSystem = softwareSystem "My System" {
            webApp = container "Web App" "Delivers content" "Node.js"
            api = container "API" "REST API" "Java"
            db = container "Database" "Stores data" "PostgreSQL"
        }

        deploymentEnvironment "Production" {
            deploymentNode "AWS" {
                deploymentNode "EC2" {
                    containerInstance webApp
                    containerInstance api
                }
                infrastructureNode "RDS" {
                    softwareSystemInstance softwareSystem
                }
            }
        }
    }

    views {
        deployment softwareSystem "Production" "ProductionDeployment" {
            include *
            autoLayout
        }
    }
}
