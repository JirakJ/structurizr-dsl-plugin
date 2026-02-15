workspace {
    model {
        // Missing name for person
        person

        // Duplicate identifier
        sys = softwareSystem "System A"
        sys = softwareSystem "System B"

        // Undefined reference
        sys -> unknownSystem "Uses"

        // Empty block
        group "Empty" {
        }
    }

    views {
        styles {
            element "Person" {
                // Invalid color
                background #xyz
            }
        }
    }
}
