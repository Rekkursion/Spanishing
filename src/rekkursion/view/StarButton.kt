package rekkursion.view

import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color

@Suppress("MemberVisibilityCanBePrivate")
class StarButton(isPressed: Boolean,
                 width: Double = mStandardWidth,
                 height: Double = mStandardHeight)
    : Canvas(width, height) {

    // is pressed or not
    private var mIsPressed = isPressed

    // the listener when pressing this star-button
    private var mOnPressingListener: OnPressingListener? = null

    init {
        // render the star
        render()

        // set the event of clicking on this star-button
        setOnMouseClicked {
            if (mIsPressed) unpress() else press()
        }
    }

    /* ======================================== */

    // set the listener when pressing this star-button
    fun setOnPressingListener(onPressingListener: OnPressingListener) { mOnPressingListener = onPressingListener }

    // press this star-button
    fun press() {
        if (!mIsPressed) {
            mOnPressingListener?.onPressing(mIsPressed, true)
            mIsPressed = true
            render()
        }
    }

    // unpress this star-button
    fun unpress() {
        if (mIsPressed) {
            mOnPressingListener?.onPressing(mIsPressed, false)
            mIsPressed = false
            render()
        }
    }

    // render the star according to the pressing status
    private fun render() {
        graphicsContext2D.fill = if (mIsPressed) Color.GOLD else Color.GRAY
        graphicsContext2D.fillPolygon(
                xPts.map { pt -> pt * width / mStandardWidth }.toDoubleArray(),
                yPts.map { pt -> pt * height / mStandardHeight }.toDoubleArray(),
                xPts.size
        )
    }

    /* ======================================== */

    // the interface of the event when pressing this star-button
    interface OnPressingListener {
        fun onPressing(oldValue: Boolean, newValue: Boolean)
    }

    /* ======================================== */

    companion object {
        // the standard width
        private const val mStandardWidth = 300.0
        // the standard height
        private const val mStandardHeight = 300.0
        // the x-points under the standard width
        private val xPts = doubleArrayOf(10.0, 85.0, 110.0, 135.0, 210.0, 160.0, 170.0, 110.0, 50.0,   60.0)
        // the y-points under the standard height
        private val yPts = doubleArrayOf(85.0, 75.0, 10.0,  75.0,  85.0,  125.0, 190.0, 150.0, 190.0, 125.0)
    }
}