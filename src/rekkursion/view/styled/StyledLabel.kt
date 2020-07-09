package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.paint.Color
import rekkursion.enumerate.Colors

class StyledLabel(
        labelName: String = "",
        textColor: Color = Colors.LABEL_DEFAULT.color)
    : Label(labelName) {

    init {
        // set the alignment
        alignment = Pos.CENTER
        // set the font size & text color
        style = "-fx-font-size: 18; -fx-text-fill: rgb(${textColor.red * 255}, ${textColor.green * 255}, ${textColor.blue * 255});"
    }
}