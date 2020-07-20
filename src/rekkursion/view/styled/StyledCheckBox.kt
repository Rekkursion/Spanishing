package rekkursion.view.styled

import javafx.scene.control.CheckBox
import rekkursion.enumerate.Strings

class StyledCheckBox(strEnum: Strings): CheckBox() {
    init {
        style = "-fx-font-size: 18;"
        Strings.register(this, strEnum)
    }
}