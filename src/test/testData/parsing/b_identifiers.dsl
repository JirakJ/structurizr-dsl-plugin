workspace "X" "Y" {
    !identifiers hierarchical
    model {
        u = person "User"
        s = softwareSystem "S" {
            c = container "C"
        }
        u -> s.c "Uses"
    }
    views {
        systemContext s {
            include *
        }
    }
}
