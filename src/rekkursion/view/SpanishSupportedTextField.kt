package rekkursion.view

import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import rekkursion.enumerate.Colors
import rekkursion.view.styled.StyledTextField

class SpanishSupportedTextField(
        width: Double,
        text: String = "",
        textColor: Color = Colors.DEFAULT.color,
        allowSpaces: Boolean = true)
    : StyledTextField(width, text, textColor) {

    init {
        setOnKeyPressed {
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (it.code) {
                KeyCode.A -> {
                    // alt + 'a' = 'á', alt + shift + 'a' = 'Á'
                    if (it.isAltDown)
                        insertText(caretPosition, if (it.isShiftDown) "Á" else "á")
                }
                KeyCode.E -> {
                    // alt + 'e' = 'é', alt + shift + 'e' = 'É'
                    if (it.isAltDown)
                        insertText(caretPosition, if (it.isShiftDown) "É" else "é")
                }
                KeyCode.I -> {
                    // alt + 'i' = 'í', alt + shift + 'i' = 'Í'
                    if (it.isAltDown)
                        insertText(caretPosition, if (it.isShiftDown) "Í" else "í")
                }
                // the alphabet 'o'
                KeyCode.O -> {
                    // alt + 'o' = 'ó', alt + shift + 'o' = 'Ó'
                    if (it.isAltDown)
                        insertText(caretPosition, if (it.isShiftDown) "Ó" else "ó")
                }
                // the alphabet 'u'
                KeyCode.U -> {
                    if (it.isAltDown) {
                        // alt + ctrl + 'u' = 'ü', alt + ctrl + shift + 'u' = 'Ü'
                        if (it.isControlDown)
                            insertText(caretPosition, if (it.isShiftDown) "Ü" else "ü")
                        // alt + 'u' = 'ú', alt + shift + 'u' = 'Ú'
                        else
                            insertText(caretPosition, if (it.isShiftDown) "Ú" else "ú")
                    }
                }
                // the key of #1 (for typing 'ñ' & 'Ñ')
                KeyCode.DIGIT1, KeyCode.NUMPAD1 -> {
                    // alt + #1 = 'ñ', alt + shift + #1 = 'Ñ'
                    if (it.isAltDown)
                        insertText(caretPosition, if (it.isShiftDown) "Ñ" else "ñ")
                }
            }
        }

        if (!allowSpaces)
            textProperty().addListener { _, _, newValue ->
                this.text = newValue.replace(" ", "")
            }
    }
}