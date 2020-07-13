package rekkursion.enumerate

import javafx.scene.paint.Color

enum class Colors(val color: Color) {
    // the default color
    DEFAULT(Color.WHITE),

    // the default color for styled-labels
    LABEL_DEFAULT(Color.rgb(205, 186, 241)),

    // the color for numbered texts
    NUMBERED(Color.PEACHPUFF),

    // the color for the option-button in a single choice problem when the user chose it but it's a wrong answer
    WRONG_ANSWER_BG(Color.DARKRED),

    // the color for showing WRONG results at a result-page
    WRONG_RES(Color.RED),

    // the color for showing CORRECT results at a result-page
    CORRECT_RES(Color.GREENYELLOW)
}