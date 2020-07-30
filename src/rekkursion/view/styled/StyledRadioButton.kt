package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.scene.paint.Color
import rekkursion.enumerate.Colors
import rekkursion.enumerate.Strings
import rekkursion.manager.PropertiesManager

class StyledRadioButton(
        name: String,
        textColor: Color = Colors.LABEL_DEFAULT.color,
        toggleGrupo: ToggleGroup? = null)
    : RadioButton(name), Styled {

    override var textSize: Int = PropertiesManager.Styled.defaultTextSize
        set(value) { field = value; adjustStyle() }

    override var textColor: Color = textColor
        set(value) { field = value; adjustStyle() }

    override var bgColor: Color = Color.TRANSPARENT
        set(value) { field = value; adjustStyle() }

    // the secondary constructor
    constructor(strEnum: Strings, textColorEnum: Colors = Colors.LABEL_DEFAULT, toggleGrupo: ToggleGroup? = null)
            : this(Strings.get(strEnum), textColorEnum.color, toggleGrupo) {
        Strings.register(this, strEnum)
    }

    init {
        // set the toggle-group
        toggleGrupo?.let { toggleGroup = it }

        // adjust ccs code
        adjustStyle()

        // set the alignment
        alignment = Pos.CENTER
    }

    override fun adjustStyle() {
        style = "-fx-font-size: $textSize;" +
                "-fx-text-fill: rgb(${textColor.red * 255}, ${textColor.green * 255}, ${textColor.blue * 255});" +
                "-fx-background-color: rgba(${bgColor.red * 255}, ${bgColor.green * 255}, ${bgColor.blue * 255}, ${bgColor.opacity * 255});"
    }
}