package rekkursion.view.listviews.res

import javafx.scene.control.ListCell
import javafx.scene.control.ScrollPane
import rekkursion.enumerate.Colors
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager
import rekkursion.manager.VocManager
import rekkursion.model.problem.Problem
import rekkursion.util.digits
import rekkursion.view.StarButton
import rekkursion.view.styled.StyledGridPane
import rekkursion.view.styled.StyledLabel

class ResultListCell(numOfProblems: Int): ListCell<Problem>() {
    // the total number of tested problems
    private val mNumOfProblems = numOfProblems

    // the label for showing the number of the tested vocabulary
    private val mLblProblemNo = StyledLabel(textColor = Colors.NUMBERED.color)

    // the label for showing the tested vocabulary
    private val mLblVoc = StyledLabel()

    // the scroll-pane for containing the label of the tested vocabulary
    private val mScpVoc = ScrollPane(mLblVoc)

    // the label for showing the result of the tested vocabulary
    private val mLblResult = StyledLabel()

    private val mStarButton = StarButton(false, PropertiesManager.sizeOfStarButton, PropertiesManager.sizeOfStarButton)

    // the container of the cell (a row)
    private val mGdpRow = StyledGridPane.Builder(4)
            .setChildren(mLblProblemNo, mScpVoc, mLblResult, mStarButton)
            .setColumnsPercentWidths(*PropertiesManager.percentWidthsOfColumnsInResultListView)
            .create()

    init {
        // set some attributes of the scroll-bar for showing the tested vocabulary
        mScpVoc.prefWidth = PreferenceManager.windowWidth * 0.545
        mScpVoc.vbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        mScpVoc.hbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
    }

    /* ======================================== */

    override fun updateItem(item: Problem?, empty: Boolean) {
        super.updateItem(item, empty)

        // set the contents of the item
        if (item != null && !empty) {
            // set the pressing-listener on the star-button for collecting this vocabulary
            mStarButton.setOnPressingListener(object: StarButton.OnPressingListener {
                override fun onPressing(oldValue: Boolean, newValue: Boolean) {
                    VocManager.collectOrUncollect(item.toVoc(), newValue)
                    item.isCollected = newValue
                }
            })

            mLblProblemNo.text = String.format("%0${mNumOfProblems.digits()}d", item.index + 1)
            mLblVoc.text = item.toString()
            Strings.register(mLblResult, item.ansResult.strEnum)
            mLblResult.textColor = item.ansResult.colorEnum.color

            if (item.isCollected) mStarButton.press() else mStarButton.unpress()

            if (graphic != mGdpRow) graphic = mGdpRow
        }
        else
            graphic = null
    }
}