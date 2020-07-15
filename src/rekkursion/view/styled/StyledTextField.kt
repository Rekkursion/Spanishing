package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import rekkursion.enumerate.Colors

class StyledTextField(width: Double,
                      text: String = "",
                      textColor: Color = Colors.DEFAULT.color)
    : TextField(text) {

    init {
        // set the alignment
        alignment = Pos.CENTER
        // set the font size & text color
        style = "-fx-font-size: 18;" +
                "-fx-text-fill: rgb(${textColor.red * 255}, ${textColor.green * 255}, ${textColor.blue * 255});" +
                "-fx-background-color: rgb(100, 100, 100);"
        // set the pref-width
        prefWidth = width
    }
}