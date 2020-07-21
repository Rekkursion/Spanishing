package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.paint.Color
import rekkursion.enumerate.Colors
import rekkursion.enumerate.Strings
import rekkursion.manager.PropertiesManager

class StyledLabel(
        labelName: String = "",
        textColor: Color = Colors.LABEL_DEFAULT.color)
    : Label(labelName), Styled {

    override var textSize: Int = PropertiesManager.Styled.defaultTextSize
        set(value) { field = value; adjustStyle() }

    override var textColor: Color = textColor
        set(value) { field = value; adjustStyle() }

    override var bgColor: Color = Color.TRANSPARENT
        set(value) { field = value; adjustStyle() }

    // the secondary constructor
    constructor(strEnum: Strings, textColorEnum: Colors = Colors.LABEL_DEFAULT)
            : this(Strings.get(strEnum), textColorEnum.color) {
        Strings.register(this, strEnum)
    }

    init {
        // adjust ccs code
        adjustStyle()

        // set the alignment
        alignment = Pos.CENTER
    }

    /* ======================================== */

    override fun adjustStyle() {
        style = "-fx-font-size: $textSize;" +
                "-fx-text-fill: rgb(${textColor.red * 255}, ${textColor.green * 255}, ${textColor.blue * 255});" +
                "-fx-background-color: rgba(${bgColor.red * 255}, ${bgColor.green * 255}, ${bgColor.blue * 255}, ${bgColor.opacity * 255});"
    }
}