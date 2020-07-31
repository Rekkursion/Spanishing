package rekkursion.view.prac

import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import rekkursion.enumerate.Colors
import rekkursion.manager.PropertiesManager
import rekkursion.util.isEspAlphabet
import rekkursion.view.prac.probpage.SpellingPage
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledTextField

// the input-area for a spelling problem
class SpellingInputArea(voc: String? = null, spellingPage: SpellingPage? = null): StyledHBox() {
    // the vocabulary of this input-area
    private var mVoc = voc

    // the spelling-page
    private val mPage = spellingPage

    // the list of text-fields
    private val mTextFieldList = arrayListOf<StyledTextField>()

    init {
        update()
    }

    /* ======================================== */

    // change the vocabulary of this spelling-input-area
    fun changeVoc(voc: String) {
        mVoc = voc
        update()
    }

    // append some spelling text at the currently-focused text-field
    fun appendSpellingText(text: String) {
        mTextFieldList.find { it.isFocused }?.let {
            it.insertText(it.caretPosition, text)
        }
    }

    // update the input-area
    private fun update() {
        // clear all sub-views
        children.clear()
        // clear all text-fields
        mTextFieldList.clear()

        // the string-builder to build the spanish component
        val sBuf = StringBuilder()
        // the string-builder to build the non-spanish component
        val nBuf = StringBuilder()

        // iterate the string of the vocabulary
        mVoc?.forEach { ch ->
            // a spanish alphabet
            if (ch.isEspAlphabet()) {
                // build the spanish component
                sBuf.append(ch)
                // if the non-spanish-builder is not empty, create a label
                buildComponentUI(nBuf, false)
            }

            // a NON-spanish alphabet
            else {
                // build the non-spanish component
                nBuf.append(ch)
                // if the spanish-builder is not empty, create a text-field
                buildComponentUI(sBuf, true)
            }
        }
        // if there's a spanish component at the tail
        buildComponentUI(sBuf, true)
        // if there's a non-spanish component at the tail
        buildComponentUI(nBuf, false)

        // request the first text-field to be focused
        mTextFieldList.getOrNull(0)?.requestFocus()
    }

    // build the ui (text-field or label) of the vocabulary
    private fun buildComponentUI(buf: StringBuilder, isSpanishComponent: Boolean) {
        // the builder is not empty, create a corresponding ui (text-field or label) according to that if it is a spanish component or not
        if (buf.isNotEmpty()) {
            // get the built component
            val component = buf.toString()

            // is a spanish component
            if (isSpanishComponent) {
                // if this spanish component is a non-spelled-word, e.g., "de", "en", etc.
                if (PropertiesManager.nonSpelledWords.contains(component))
                    // create a label and add it into this h-box
                    children.add(StyledLabel(component, Colors.SPELLING.color))

                // otherwise, a normal spanish component that shall be spelled by the user
                else {
                    // create a text-field to let the user to spell
                    val textField = StyledTextField(
                            component.length * PropertiesManager.widthOfChar,
                            "",
                            Colors.SPELLING.color
                    )
                    // show the hint of the first character of this component
                    textField.promptText = "${component.getOrNull(0)?.toString() ?: ""}..."
                    // set some events of the created text-field
                    setEventsOfSpellingTextField(textField)
                    // add the created text-field into this h-box
                    children.add(textField)
                    // also add into a list of text-fields for the convenience to manage
                    mTextFieldList.add(textField)
                }
            }
            // is a NON-spanish component
            else
                // create a label and add it into this h-box
                children.add(StyledLabel(component, Colors.SPELLING.color))

            // clear the non-spanish-builder for building the next non-spanish component
            buf.clear()
        }
    }

    // set some events of a text-field for the user to spell the word/component
    private fun setEventsOfSpellingTextField(textField: TextField) {
        textField.setOnKeyPressed {
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (it.code) {
                // the enter
                KeyCode.ENTER -> {
                    // ctrl + enter: skip
                    if (it.isControlDown) mPage?.skip()
                    // shift + enter: finish
                    else if (it.isShiftDown) mPage?.finishDirectly()
                    // single enter: focus on the next text-field or fire the submission button
                    else {
                        val idx = getIndexOfTextField(textField)
                        if (idx != null) {
                            if (idx + 1 == mTextFieldList.size) mPage?.submit()
                            else mTextFieldList.getOrNull(idx + 1)?.requestFocus()
                        }
                    }
                }

                KeyCode.A -> {
                    // alt + 'a' = 'á', alt + shift + 'a' = 'Á'
                    if (it.isAltDown)
                        mPage?.fireSpecialAlphabet(if (it.isShiftDown) 'Á' else 'á')
                }
                KeyCode.E -> {
                    // alt + 'e' = 'é', alt + shift + 'e' = 'É'
                    if (it.isAltDown)
                        mPage?.fireSpecialAlphabet(if (it.isShiftDown) 'É' else 'é')
                }
                KeyCode.I -> {
                    // alt + 'i' = 'í', alt + shift + 'i' = 'Í'
                    if (it.isAltDown)
                        mPage?.fireSpecialAlphabet(if (it.isShiftDown) 'Í' else 'í')
                }
                // the alphabet 'o'
                KeyCode.O -> {
                    // alt + 'o' = 'ó', alt + shift + 'o' = 'Ó'
                    if (it.isAltDown)
                        mPage?.fireSpecialAlphabet(if (it.isShiftDown) 'Ó' else 'ó')
                }
                // the alphabet 'u'
                KeyCode.U -> {
                    if (it.isAltDown) {
                        // alt + ctrl + 'u' = 'ü', alt + ctrl + shift + 'u' = 'Ü'
                        if (it.isControlDown) mPage?.fireSpecialAlphabet(if (it.isShiftDown) 'Ü' else 'ü')
                        // alt + 'u' = 'ú', alt + shift + 'u' = 'Ú'
                        else mPage?.fireSpecialAlphabet(if (it.isShiftDown) 'Ú' else 'ú')
                    }
                }
                // the key of #1 (for typing 'ñ' & 'Ñ')
                KeyCode.DIGIT1, KeyCode.NUMPAD1 -> {
                    // alt + #1 = 'ñ', alt + shift + #1 = 'Ñ'
                    if (it.isAltDown)
                        mPage?.fireSpecialAlphabet(if (it.isShiftDown) 'Ñ' else 'ñ')
                }
            }
        }

        textField.textProperty().addListener { _, _, newValue ->
            textField.text = newValue.replace(" ", "")
        }
    }

    // get the index of the passed text-field
    private fun getIndexOfTextField(textField: TextField): Int? = mTextFieldList.mapIndexed { index, tf ->
        if (tf == textField) index
        else -1
    }.filter { index -> index >= 0 }.getOrNull(0)

    /* ======================================== */

    override fun requestFocus() {
        mTextFieldList.getOrNull(0)?.requestFocus()
    }

    override fun toString(): String = children.joinToString("") {
        when (it) {
            is Label -> it.text
            is TextField -> it.text
            else -> ""
        }
    }
}