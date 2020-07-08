package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.Label

class StyledLabel(labelName: String): Label(labelName) {
    // secondary constructor w/o a initial label name
    constructor(): this("")

    init {
        // set the alignment
        alignment = Pos.CENTER
        // set the font size & text color
        style = "-fx-font-size: 18; -fx-text-fill: rgb(205, 186, 241);"
    }
}