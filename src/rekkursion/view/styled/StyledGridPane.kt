package rekkursion.view.styled

import javafx.geometry.HPos
import javafx.scene.Node
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane

class StyledGridPane private constructor(colNum: Int = 0): GridPane() {
    class Builder(colNum: Int) {
        // the instance
        private val mInstance = StyledGridPane(colNum)

        // set all passed children
        fun setChildren(vararg children: Node): Builder {
            // add all children passed in the row-first order
            children.forEachIndexed { index, child ->
                mInstance.add(child, index % mInstance.mColNum, index / mInstance.mColNum)
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

    // the total number of columns
    private val mColNum = colNum
}