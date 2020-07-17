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
import rekkursion.manager.StatisticsManager
import rekkursion.model.problem.Problem
import rekkursion.util.GenericString
import rekkursion.util.digits
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox

class ResultPage(problemList: ArrayList<Problem>): StyledVBox() {
    // the result statistics report
    private val mReport = StatisticsManager.hacer(problemList)

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

    // the label for showing the number of correct answers
    private val mLblNumOfCorrect = StyledLabel(textColor = Colors.CORRECT_RES.color)

    // the label for showing the number of wrong answers
    private val mLblNumOfWrong = StyledLabel(textColor = Colors.WRONG_RES.color)

    // the label for showing the number of problems that weren't answered
    private val mLblNumOfNoAnswered = StyledLabel(textColor = Colors.NO_ANSWER_RES.color)

    // the label for showing the correct rate
    private val mLblCorrectRate = StyledLabel()

    // the h-box for containing some statistics related uis
    private val mHbxStatistics = StyledHBox()

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

        // register labels if needs
        registerLabels()

        // add all statistics-related uis into an h-box
        mHbxStatistics.children.addAll(mLblNumOfCorrect, mLblNumOfWrong, mLblNumOfNoAnswered)

        // add the sub-views into this v-box
        children.addAll(mScrollPane, mHbxStatistics, mLblCorrectRate, mBtnGoBack)

        // set the clicking event on the go-back-button
        mBtnGoBack.setOnAction { LayoutManager.switchPracticeContent() }
    }

    // register labels if needs
    private fun registerLabels() {
        Strings.register(mLblNumOfCorrect,
                GenericString(Strings.Correct),
                GenericString(Strings.COLON),
                GenericString(str = mReport.numOfCorrect.toString())
        )
        Strings.register(mLblNumOfWrong,
                GenericString(Strings.Wrong),
                GenericString(Strings.COLON),
                GenericString(str = mReport.numOfWrong.toString())
        )
        Strings.register(mLblNumOfNoAnswered,
                GenericString(Strings.NoAnswer),
                GenericString(Strings.COLON),
                GenericString(str = mReport.numOfNoAnswered.toString())
        )

        Strings.register(mLblCorrectRate,
                GenericString(Strings.CorrectRate),
                GenericString(Strings.COLON),
                GenericString(str = mReport.mCorrectRateWithPercentage)
        )
    }

    /* ======================================== */

    override fun requestFocus() {
        mBtnGoBack.requestFocus()
    }
}