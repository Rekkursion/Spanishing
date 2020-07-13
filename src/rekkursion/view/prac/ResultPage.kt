package rekkursion.view.prac

import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import rekkursion.enumerate.Colors
import rekkursion.enumerate.Strings
import rekkursion.manager.LayoutManager
import rekkursion.manager.PropertiesManager
import rekkursion.model.Problem
import rekkursion.util.digits
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox

class ResultPage(problemList: ArrayList<Problem>): StyledVBox() {
    // the scroll-pane for containing the grid-pane
    private val mScrollPane = ScrollPane()

    // the grid-pane for containing the tested vocabularies
    private val mGridPane = GridPane()

    // the label for showing no.'s of tested vocabularies
    private val mLblTestedNo = StyledLabel(Strings.TestedNo)

    // the label for showing tested vocabularies
    private val mLblTestedVocs = StyledLabel(Strings.TestedVocs)

    // the label for showing tested results (one per vocabulary)
    private val mLblTestedResults = StyledLabel(Strings.TestedResults)

    // the button for going back to the practice-menu-view
    private val mBtnGoBack = StyledButton(Strings.Back)

    init {
        // set the fit-to-height/-width
        mScrollPane.isFitToHeight = true
        mScrollPane.isFitToWidth = true
        // set the content
        mScrollPane.content = mGridPane

        // set the alignment of the grid-pane
        mGridPane.alignment = Pos.CENTER
        // set the gaps
        mGridPane.vgap = PropertiesManager.generalSpacing
        mGridPane.hgap = PropertiesManager.generalSpacing

        // set the column-constraints of the grid-pane
        val c1 = ColumnConstraints(); c1.percentWidth = 10.0; c1.halignment = HPos.CENTER; c1.isFillWidth = true
        val c2 = ColumnConstraints(); c2.percentWidth = 70.0; c2.halignment = HPos.CENTER; c2.isFillWidth = true
        val c3 = ColumnConstraints(); c3.percentWidth = 20.0; c3.halignment = HPos.CENTER; c3.isFillWidth = true
        mGridPane.columnConstraints.addAll(c1, c2, c3)

        // add labels-as-column-titles into the grid-pane
        mGridPane.add(mLblTestedNo, 0, 0)
        mGridPane.add(mLblTestedVocs, 1, 0)
        mGridPane.add(mLblTestedResults, 2, 0)

        // add the problems' vocabularies and results into the grid-pane
        problemList.forEachIndexed { index, problem ->
            // add the label-for-no into the grid-pane
            mGridPane.add(StyledLabel(String.format("%0${problemList.size.digits()}d", index + 1), Colors.NUMBERED.color), 0, index + 1)
            // add the label-for-vocabulary into the grid-pane
            val subScrollPane = ScrollPane(StyledLabel(problem.toVoc().toString(), Colors.DEFAULT.color))
            subScrollPane.vbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
            subScrollPane.hbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
            mGridPane.add(subScrollPane, 1, index + 1)
            // add the label-for-result into the grid-pane
            mGridPane.add(StyledLabel(
                    problem.ansResult.strEnum,
                    problem.ansResult.colorEnum
            ), 2, index + 1)
        }

        // add the scroll-pane and the go-back-button into this v-box
        children.addAll(mScrollPane, mBtnGoBack)

        // set the clicking event on the go-back-button
        mBtnGoBack.setOnMouseClicked { LayoutManager.switchPracticeContent() }
    }
}