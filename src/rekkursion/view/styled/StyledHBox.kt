package rekkursion.view.styled

import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import rekkursion.manager.PropertiesManager

open class StyledHBox(vararg children: Node): HBox(*children), Styled {
    override var textSize: Int = PropertiesManager.Styled.defaultTextSize
        set(value) { field = value; adjustStyle() }

    override var textColor: Color = PropertiesManager.Styled.defaultTextColor
        set(value) { field = value; adjustStyle() }

    override var bgColor: Color = Color.TRANSPARENT
        set(value) { field = value; adjustStyle() }

    init {
        // set the alignment
        alignment = Pos.CENTER
        // set the spacing
        spacing = PropertiesManager.generalSpacing
    }

    /* ======================================== */

    override fun adjustStyle() {}
}