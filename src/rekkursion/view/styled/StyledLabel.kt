package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.paint.Color

class StyledLabel(labelName: String, textColor: Color): Label(labelName) {
    // secondary constructor w/o a initial label name
    constructor(): this("")

    // secondary constructor w/ designated text color
    constructor(labelName: String): this(labelName, Color.rgb(205, 186, 241))

    init {
        // set the alignment
        alignment = Pos.CENTER
        // set the font size & text color
        style = "-fx-font-size: 18; -fx-text-fill: rgb(${textColor.red * 255}, ${textColor.green * 255}, ${textColor.blue * 255});"
    }
}