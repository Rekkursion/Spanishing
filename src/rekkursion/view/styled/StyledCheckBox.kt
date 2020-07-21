package rekkursion.view.styled

import javafx.scene.control.CheckBox
import javafx.scene.paint.Color
import rekkursion.enumerate.Strings
import rekkursion.manager.PropertiesManager

class StyledCheckBox(strEnum: Strings): CheckBox(), Styled {
    override var textSize: Int = PropertiesManager.Styled.defaultTextSize
        set(value) { field = value; adjustStyle() }

    override var textColor: Color = PropertiesManager.Styled.defaultTextColor
        set(value) { field = value; adjustStyle() }

    override var bgColor: Color = Color.TRANSPARENT
        set(value) { field = value; adjustStyle() }

    init {
        // adjust ccs code
        adjustStyle()

        // register the str-enum
        Strings.register(this, strEnum)
    }

    /* ======================================== */

    override fun adjustStyle() {
        style = "-fx-font-size: $textSize;" +
                "-fx-text-fill: rgb(${textColor.red * 255}, ${textColor.green * 255}, ${textColor.blue * 255});" +
                "-fx-background-color: rgba(${bgColor.red * 255}, ${bgColor.green * 255}, ${bgColor.blue * 255}, ${bgColor.opacity * 255});"
    }
}