package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.layout.HBox
import rekkursion.manager.PropertiesManager

open class StyledHBox: HBox() {
    init {
        // set the alignment
        alignment = Pos.CENTER
        // set the spacing
        spacing = PropertiesManager.generalSpacing
    }
}