workspace "X" {
    name "Override"
    description "desc"
    properties {
        "structurizr.groupSeparator" "/"
    }
    !docs docs
    !adrs adrs
    model {
        enterprise "Big Co" {
            u = person "User"
        }
        s = softwareSystem "S" "d" "tag"
        u -> s "Uses" "" "t"
        u -> s
        !ref s {
            c2 = container "C2"
        }
    }
    views {
        systemLandscape {
            include *
            exclude u
            animation {
                u
                s
            }
            title "Big picture"
            description "The big picture"
        }
        filtered "landscape" include "Element,Relationship" "filt"
        custom "cust" "Custom" {
            include u
        }
        image * "img" {
            plantuml "x.puml"
        }
        properties {
            "plantuml.url" "x"
        }
        branding {
            logo "x.png"
        }
        terminology {
            person "Actor"
        }
    }
}
