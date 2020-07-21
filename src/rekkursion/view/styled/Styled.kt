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
}