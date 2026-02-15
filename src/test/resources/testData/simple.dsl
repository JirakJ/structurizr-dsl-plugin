workspace "Simple" "A simple workspace" {

    model {
        user = person "User" "A user"
    }

    views {
        systemLandscape "landscape" {
            include *
            autoLayout
        }
    }
}
