package rekkursion.view.styled

import javafx.geometry.HPos
import javafx.scene.Node
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import rekkursion.manager.PropertiesManager

open class StyledGridPane private constructor(colNum: Int = 0): GridPane(), Styled {
    class Builder(colNum: Int) {
        // the instance
        private val mInstance = StyledGridPane(colNum)

        // set all passed children
        fun setChildren(vararg children: Node): Builder {
            // add all children passed in the row-first order
            children.forEachIndexed { index, child ->
                mInstance.add(child, index % mInstance.mColNum, index / mInstance.mColNum)
                ++mInstance.mIndex
            }
            return this
        }

        // set the column-constraints to make the widths be fixed
        fun setColumnsPercentWidths(vararg percentWidths: Double): Builder {
            // clear all constraints
            mInstance.columnConstraints.clear()
            // add constraints with all passed percent widths
            percentWidths.forEach {
                val c = ColumnConstraints()
                c.percentWidth = it
                c.halignment = HPos.CENTER
                c.isFillWidth = true

                mInstance.columnConstraints.add(c)
            }
            return this
        }

        // create a whole new styled-grid-pane
        fun create(): StyledGridPane = mInstance
    }

    /* ======================================== */

    override var textSize: Int = PropertiesManager.Styled.defaultTextSize
        set(value) { field = value; adjustStyle() }

    override var textColor: Color = PropertiesManager.Styled.defaultTextColor
        set(value) { field = value; adjustStyle() }

    override var bgColor: Color = Color.TRANSPARENT
        set(value) { field = value; adjustStyle() }

    // the total number of columns
    private val mColNum = colNum

    // the current index
    private var mIndex = 0

    /* ======================================== */

    // push an extra child
    fun pushChild(child: Node) {
        add(child, mIndex % mColNum, mIndex / mColNum)
        ++mIndex
    }

    override fun adjustStyle() {}
}