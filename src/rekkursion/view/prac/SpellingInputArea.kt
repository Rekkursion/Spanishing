package rekkursion.view.prac

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import rekkursion.enumerate.Colors
import rekkursion.manager.PropertiesManager
import rekkursion.util.isEspAlphabet
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledTextField

// the input-area for a spelling problem
class SpellingInputArea(voc: String? = null,
                        submitButton: Button? = null,
                        skipButton: Button? = null,
                        finishButton: Button? = null): StyledHBox() {
    // the vocabulary of this input-area
    private var mVoc = voc

    // the list of text-fields
    private val mTextFieldList = arrayListOf<StyledTextField>()

    // the button for submitting
    private val mBtnSubmit: Button? = submitButton

    // the button for skipping
    private val mBtnSkip: Button? = skipButton

    // the button for finishing
    private val mBtnFinish: Button? = finishButton

    init {
        update()
    }

    /* ======================================== */

    // change the vocabulary of this spelling-input-area
    fun changeVoc(voc: String) {
        mVoc = voc
        update()
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
                    if (it.isControlDown) mBtnSkip?.fire()
                    // shift + enter: finish
                    else if (it.isShiftDown) mBtnFinish?.fire()
                    // single enter: focus on the next text-field or fire the submission button
                    else {
                        val idx = getIndexOfTextField(textField)
                        if (idx != null) {
                            if (idx + 1 == mTextFieldList.size)
                                mBtnSubmit?.fire()
                            else
                                mTextFieldList.getOrNull(idx + 1)?.requestFocus()
                        }
                    }
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

    override fun toString(): String = children.joinToString("") {
        when (it) {
            is Label -> it.text
            is TextField -> it.text
            else -> ""
        }
    }
}