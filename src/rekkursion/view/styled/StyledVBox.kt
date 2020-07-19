package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.VBox
import rekkursion.manager.PropertiesManager

open class StyledVBox(vararg children: Node): VBox(*children) {
    init {
        // set the alignment
        alignment = Pos.CENTER
        // set the spacing
        spacing = PropertiesManager.generalSpacing
    }
}