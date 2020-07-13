package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.paint.Color
import rekkursion.enumerate.Colors
import rekkursion.enumerate.Strings

class StyledLabel(
        labelName: String = "",
        textColor: Color = Colors.LABEL_DEFAULT.color)
    : Label(labelName) {

    // the secondary constructor
    constructor(strEnum: Strings, textColorEnum: Colors = Colors.LABEL_DEFAULT)
            : this(Strings.get(strEnum), textColorEnum.color) {
        Strings.register(this, strEnum)
    }

    init {
        // set the alignment
        alignment = Pos.CENTER
        // set the font size & text color
        style = "-fx-font-size: 18; -fx-text-fill: rgb(${textColor.red * 255}, ${textColor.green * 255}, ${textColor.blue * 255});"
    }
}