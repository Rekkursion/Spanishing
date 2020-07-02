package rekkursion.view

import javafx.geometry.Pos
import javafx.scene.layout.VBox
import rekkursion.manager.PropertiesManager

open class StyledVBox: VBox() {
    init {
        // set the alignment
        alignment = Pos.CENTER
        // set the spacing
        spacing = PropertiesManager.generalSpacing
    }
}