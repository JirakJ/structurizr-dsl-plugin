/*
 * block comment
 */
workspace "X" "Y" {
    # hash comment
    model {
        // line comment
        u = person "User" "A \"quoted\" desc"
        s = softwareSystem "S" """
            multi
            line
        """
        u -> s "Uses"   // trailing
    }
    views {
        systemContext s "ctx" "Description here" {
            include *
            autoLayout tb 300 300
        }
    }
}
