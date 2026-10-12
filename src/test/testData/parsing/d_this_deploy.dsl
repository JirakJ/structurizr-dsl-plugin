workspace {
    model {
        u = person "User"
        s = softwareSystem "S" {
            c = container "C" {
                this -> u "x"
            }
        }
        u -> s "Uses" "HTTPS" "tag1,tag2"
        live = deploymentEnvironment "Live" {
            deploymentNode "AWS" {
                deploymentNode "EC2" "" "Ubuntu" {
                    containerInstance c
                }
                lb = infrastructureNode "LB"
            }
        }
    }
    views {
        deployment s live "deploy" {
            include *
        }
        dynamic s "dyn" "Flow" {
            u -> c "Requests"
            autoLayout
        }
        theme default
    }
}
