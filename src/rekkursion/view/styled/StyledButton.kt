package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.paint.Color
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager

@Suppress("LeakingThis")
open class StyledButton(buttonName: String, strEnum: Strings? = null): Button(buttonName), Styled {
    override var textSize: Int = PropertiesManager.Styled.defaultTextSize
        set(value) { field = value; adjustStyle() }

    override var textColor: Color = PropertiesManager.Styled.defaultTextColor
        set(value) { field = value; adjustStyle() }

    override var bgColor: Color = PropertiesManager.Styled.defaultButtonBgColor
        set(value) { field = value; adjustStyle() }

    // the background-color in general cases
    private val mBgColor = bgColor

    // the background-color when the mouse is hovering this button
    private val mHoveringBgColor = PropertiesManager.Styled.defaultButtonHoveringBgColor

    // the secondary constructor
    constructor(): this("")

    // the secondary constructor
    constructor(strEnum: Strings): this(Strings.get(strEnum), strEnum)

    init {
        // adjust ccs code
        adjustStyle()

        // set the width
        prefWidth = PreferenceManager.windowWidth
        // set the alignment
        alignment = Pos.CENTER

        // register to the strings enumeration if needs
        if (strEnum != null)
            Strings.register(this, strEnum)

        // listen the focused-property to change background color
        focusedProperty().addListener { _, _, newValue ->
            bgColor = if (newValue) mHoveringBgColor else mBgColor
        }

        // listen the hover-property
        hoverProperty().addListener { _, _, newValue ->
            bgColor = if (newValue) mHoveringBgColor else mBgColor
        }
    }

    /* ======================================== */

    override fun adjustStyle() {
        style = "-fx-font-size: $textSize;" +
                "-fx-text-fill: rgb(${textColor.red * 255}, ${textColor.green * 255}, ${textColor.blue * 255});" +
                "-fx-background-color: rgba(${bgColor.red * 255}, ${bgColor.green * 255}, ${bgColor.blue * 255}, ${bgColor.opacity * 255});"
    }
}