package rekkursion.view.styled

import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.control.ScrollPane

class StyledScrollPane(
        child: Node? = null,
        orientation: Orientation = Orientation.HORIZONTAL)
    : ScrollPane(child) {

    init {
        if (orientation == Orientation.HORIZONTAL) {
            hbarPolicy = ScrollBarPolicy.AS_NEEDED
            vbarPolicy = ScrollBarPolicy.NEVER
        } else {
            hbarPolicy = ScrollBarPolicy.NEVER
            vbarPolicy = ScrollBarPolicy.AS_NEEDED
        }
    }
}