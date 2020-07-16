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
    CORRECT_RES(Color.GREENYELLOW),

    // the color for showing NO_ANSWER results at a result-page
    NO_ANSWER_RES(Color.ORANGE),

    // the color for spelling vocabularies in spelling problems
    SPELLING(Color.SKYBLUE),

    // the color for showing hints of typing special alphabets (even)
    HINT_TYPING_SP_ALPHABET_EVEN(Color.BLUEVIOLET),

    // the color for showing hints of typing special alphabets (odds)
    HINT_TYPING_SP_ALPHABET_ODDS(Color.AZURE)
}