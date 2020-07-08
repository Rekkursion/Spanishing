package rekkursion.view

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

// the number selector (integer only)
class NumberSelector(min: Int, max: Int, initialValue: Int, amountToStepBy: Int): HBox() {
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

    // the button for incrementing the value by the amount-to-step
    private val mBtnIncrement = Button()

    // the button for decrementing the value by the amount-to-step
    private val mBtnDecrement = Button()

    private val mLblIncrement = Label()

    init {
        // set some attributes
        mTxfNumber.alignment = Pos.CENTER
        alignment = Pos.CENTER
        mTxfNumber.style = "-fx-font-size: 18;"

        // set the texts of buttons
        mBtnIncrement.text = "+"
        mBtnDecrement.text = "-"

        // set the heights of the two buttons
        mBtnIncrement.prefHeight = mTxfNumber.height / 2
        mBtnDecrement.prefHeight = mTxfNumber.height / 2

        // add those two buttons into a v-box
        mVbxIncAndDec.children.addAll(mBtnIncrement, mBtnDecrement)

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

        // set the on-mouse-clicked event of the incrementing button
        mBtnIncrement.setOnMouseClicked {
            val num: Int = mTxfNumber.text.toIntOrNull() ?: initialValue
            mTxfNumber.text = (num + amountToStepBy).toString()
            checkBounds()
        }

        // set the on-mouse-clicked event of the decrementing button
        mBtnDecrement.setOnMouseClicked {
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
}