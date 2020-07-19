package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.HBox
import rekkursion.manager.PropertiesManager

open class StyledHBox(vararg children: Node): HBox(*children) {
    init {
        // set the alignment
        alignment = Pos.CENTER
        // set the spacing
        spacing = PropertiesManager.generalSpacing
    }
}