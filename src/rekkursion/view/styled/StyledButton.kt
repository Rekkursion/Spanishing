package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.Button
import rekkursion.manager.PropertiesManager

class StyledButton(buttonName: String): Button(buttonName) {
    // the secondary constructor
    constructor(): this("")

    init {
        // set the width
        prefWidth = PropertiesManager.windowWidth
        // set the alignment
        alignment = Pos.CENTER
        // set the font size
        style = "-fx-font-size: 18;"
    }
}