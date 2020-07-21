package rekkursion.view.styled

import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.control.ScrollPane
import javafx.scene.paint.Color
import rekkursion.manager.PropertiesManager

class StyledScrollPane(
        child: Node? = null,
        orientation: Orientation = Orientation.HORIZONTAL)
    : ScrollPane(child), Styled {

    override var textSize: Int = PropertiesManager.Styled.defaultTextSize
        set(value) { field = value; adjustStyle() }

    override var textColor: Color = PropertiesManager.Styled.defaultTextColor
        set(value) { field = value; adjustStyle() }

    override var bgColor: Color = Color.TRANSPARENT
        set(value) { field = value; adjustStyle() }

    init {
        if (orientation == Orientation.HORIZONTAL) {
            hbarPolicy = ScrollBarPolicy.AS_NEEDED
            vbarPolicy = ScrollBarPolicy.NEVER
        } else {
            hbarPolicy = ScrollBarPolicy.NEVER
            vbarPolicy = ScrollBarPolicy.AS_NEEDED
        }
    }

    /* ======================================== */

    override fun adjustStyle() {}
}