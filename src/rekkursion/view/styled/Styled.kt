package rekkursion.view.styled

import javafx.scene.paint.Color

interface Styled {
    // the font-size
    var textSize: Int

    // the text-color
    var textColor: Color

    // the background-color
    var bgColor: Color

    /* ======================================== */

    // adjust the css code for styling this styled-node
    fun adjustStyle() /*{
        style = "-fx-font-size: $textSize;" +
                "-fx-text-fill: rgb(${mTextColor.red * 255}, ${mTextColor.green * 255}, ${mTextColor.blue * 255});" +
                "-fx-background-color: rgb(${mBgColor.red * 255}, ${mBgColor.green * 255}, ${mBgColor.blue * 255});"

    }*/

    /* ======================================== */

    // the attribute unifier
    @Suppress("unused")
    companion object Unifier {
        // unify the text-size among the passed styled-nodes
        fun unifyTextSize(textSize: Int, vararg nodes: Styled) {
            nodes.forEach { it.textSize = textSize }
        }

        // unify the text-color among the passed styled-nodes
        fun unifyTextColor(textColor: Color, vararg nodes: Styled) {
            nodes.forEach { it.textColor = textColor }
        }

        // unify the bg-color among the passed styled-nodes
        fun unifyBgColor(bgColor: Color, vararg nodes: Styled) {
            nodes.forEach { it.bgColor = bgColor }
        }
    }
}