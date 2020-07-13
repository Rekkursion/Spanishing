package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.paint.Color
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager

class StyledButton(buttonName: String, strEnum: Strings? = null): Button(buttonName) {
    // the secondary constructor
    constructor(): this("")

    // the secondary constructor
    constructor(strEnum: Strings): this(Strings.get(strEnum), strEnum)

    init {
        // set the width
        prefWidth = PreferenceManager.windowWidth
        // set the alignment
        alignment = Pos.CENTER
        // set the font size
        style = "-fx-font-size: 18;"

        // register to the strings enumeration if needs
        if (strEnum != null)
            Strings.register(this, strEnum)
    }

    /* ======================================== */

    // set the background color
    fun setBgColor(color: Color) {
        style = "-fx-font-size: 18; -fx-background-color: rgb(" +
                "${color.red * 255}, " +
                "${color.green * 255}, " +
                "${color.blue * 255}" +
                ");"
    }

    // unset the background color
    fun unsetBgColor() {
        style = "-fx-font-size: 18;"
    }
}