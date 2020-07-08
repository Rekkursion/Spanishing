package rekkursion.view.practiceview

import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import rekkursion.enumerate.AnsResult
import rekkursion.enumerate.Strings
import rekkursion.manager.LayoutManager
import rekkursion.manager.PropertiesManager
import rekkursion.model.Problem
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox

class ResultPage(problemList: ArrayList<Problem>): StyledVBox() {
    // the scroll-pane for containing the grid-pane
    private val mScrollPane = ScrollPane()

    // the grid-pane for containing the tested vocabularies
    private val mGridPane = GridPane()

    // the label for showing tested vocabularies
    private val mLblTestedVocs = StyledLabel(Strings.get(Strings.TestedVocs))

    // the label for showing tested results (one per vocabulary)
    private val mLblTestedResults = StyledLabel(Strings.get(Strings.TestedResults))

    // the button for going back to the practice-menu-view
    private val mBtnGoBack = StyledButton(Strings.get(Strings.Back))

    init {
        // set the orientation
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
        val c1 = ColumnConstraints(); c1.percentWidth = 50.0; c1.halignment = HPos.CENTER; c1.isFillWidth = true
        val c2 = ColumnConstraints(); c2.percentWidth = 50.0; c2.halignment = HPos.CENTER; c2.isFillWidth = true
        mGridPane.columnConstraints.addAll(c1, c2)

        // add labels into the grid-pane
        mGridPane.add(mLblTestedVocs, 0, 0)
        mGridPane.add(mLblTestedResults, 1, 0)

        // add the problems' vocabularies and results into the grid-pane
        problemList.forEachIndexed { index, problem ->
            // add the label-for-vocabulary into the grid-pane
            mGridPane.add(StyledLabel(problem.toVoc().toString()), 0, index + 1)
            // add the label-for-result into the grid-pane
            mGridPane.add(StyledLabel(
                    problem.ansResult.name,
                    if (problem.ansResult == AnsResult.CORRECT) Color.GREENYELLOW else Color.ORANGERED
            ), 1, index + 1)
        }

        // add the scroll-pane and the go-back-button into this v-box
        children.addAll(mScrollPane, mBtnGoBack)

        // set the clicking event on the go-back-button
        mBtnGoBack.setOnMouseClicked { LayoutManager.switchPracticeContent() }
    }
}