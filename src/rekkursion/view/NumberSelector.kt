package rekkursion.view

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import rekkursion.enumerate.Strings
import rekkursion.manager.PropertiesManager

// the number selector (integer only)
class NumberSelector(min: Int, max: Int, initialValue: Int, amountToStepBy: Int): HBox(PropertiesManager.generalSpacing) {
    // the minimum value
    private val mMin = min

    // the maximum value
    private val mMax = max

    // the initial value
    private val mInitialValue = initialValue

    // the text-field for showing/typing the current number
    private val mTxfNumber = TextField(initialValue.toString())

    // the v-box for containing incrementing & decrementing buttons
    private val mVbxIncAndDec = VBox()

    // the label-as-button for incrementing the value by the amount-to-step
    private val mLblIncrement = Label(Strings.get(Strings.Increment))

    // the label-as-button for decrementing the value by the amount-to-step
    private val mLblDecrement = Label(Strings.get(Strings.Decrement))

    init {
        // set some attributes
        mTxfNumber.alignment = Pos.CENTER
        alignment = Pos.CENTER
        mTxfNumber.style = "-fx-font-size: 18;"
        mLblIncrement.style = "-fx-font-size: 18;"
        mLblDecrement.style = "-fx-font-size: 18;"
        mLblIncrement.padding = Insets(0.0, 4.0, 0.0, 4.0)
        mLblDecrement.padding = Insets(0.0, 4.0, 0.0, 4.0)

        // set the heights of the two buttons
        mLblIncrement.prefHeight = mTxfNumber.height / 2
        mLblDecrement.prefHeight = mTxfNumber.height / 2

        // add those two buttons into a v-box
        mVbxIncAndDec.children.addAll(mLblIncrement, mLblDecrement)

        // add all uis into this h-box
        children.addAll(mTxfNumber, mVbxIncAndDec)

        // set the text-changed listener
        mTxfNumber.textProperty().addListener { _, oldValue, newValue ->
            if (newValue.contains("[^0-9]".toRegex()))
                mTxfNumber.text = oldValue
        }

        // set the key-typed listener
        mTxfNumber.setOnKeyReleased { event ->
            if (event.code == KeyCode.ENTER)
                checkBounds()
        }

        // set the hover-event on incrementing label-as-button
        mLblIncrement.hoverProperty().addListener { _, _, newValue ->
            setHoverEventOnBtnLbl(mLblIncrement, newValue)
        }

        // set the hover-event on decrementing label-as-button
        mLblDecrement.hoverProperty().addListener { _, _, newValue ->
            setHoverEventOnBtnLbl(mLblDecrement, newValue)
        }

        // set the on-mouse-clicked event of the incrementing button
        mLblIncrement.setOnMouseClicked {
            val num: Int = mTxfNumber.text.toIntOrNull() ?: initialValue
            mTxfNumber.text = (num + amountToStepBy).toString()
            checkBounds()
        }

        // set the on-mouse-clicked event of the decrementing button
        mLblDecrement.setOnMouseClicked {
            val num: Int = mTxfNumber.text.toIntOrNull() ?: initialValue
            mTxfNumber.text = (num - amountToStepBy).toString()
            checkBounds()
        }
    }

    /* ======================================== */

    // get the number value
    fun getNumber(): Int = mTxfNumber.text.toIntOrNull() ?: mInitialValue

    // check the bounds of current value
    private fun checkBounds() {
        val num = mTxfNumber.text.toIntOrNull()
        if (num != null) {
            if (num < mMin)
                mTxfNumber.text = mMin.toString()
            else if (num > mMax)
                mTxfNumber.text = mMax.toString()
        }
    }

    // set the hover-event on labels which are used as buttons
    private fun setHoverEventOnBtnLbl(lbl: Label, isEntering: Boolean) {
        if (isEntering)
            lbl.style = "-fx-font-size: 18;" +
                    "-fx-background-color: rgb(180, 180, 180);" +
                    "-fx-text-fill: black"
        else
            lbl.style = "-fx-font-size: 18;"
    }
}