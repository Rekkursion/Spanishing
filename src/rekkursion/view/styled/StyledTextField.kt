package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import rekkursion.enumerate.Colors
import rekkursion.manager.PropertiesManager

open class StyledTextField(width: Double,
                      text: String = "",
                      textColor: Color = Colors.DEFAULT.color)
    : TextField(text), Styled {

    override var textSize: Int = PropertiesManager.Styled.defaultTextSize
        set(value) { field = value; adjustStyle() }

    override var textColor: Color = textColor
        set(value) { field = value; adjustStyle() }

    override var bgColor: Color = PropertiesManager.Styled.defaultTextFieldBgColor
        set(value) { field = value; adjustStyle() }

    init {
        // adjust css code
        adjustStyle()

        // set the alignment
        alignment = Pos.CENTER
        // set the pref-width
        prefWidth = width
    }

    /* ======================================== */

    final override fun adjustStyle() {
        style = "-fx-font-size: $textSize;" +
                "-fx-text-fill: rgb(${textColor.red * 255}, ${textColor.green * 255}, ${textColor.blue * 255});" +
                "-fx-prompt-text-fill: rgb(120, 126, 100); }" +
                "-fx-background-color: rgba(${bgColor.red * 255}, ${bgColor.green * 255}, ${bgColor.blue * 255}, ${bgColor.opacity * 255});"
    }
}