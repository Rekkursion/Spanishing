package rekkursion.view.prac

import javafx.scene.Cursor
import rekkursion.enumerate.Colors
import rekkursion.enumerate.Strings
import rekkursion.manager.LayoutManager
import rekkursion.manager.PropertiesManager
import rekkursion.manager.StatisticsManager
import rekkursion.model.problem.Problem
import rekkursion.util.GenericString
import rekkursion.view.listviews.res.ResultListView
import rekkursion.view.styled.*

class ResultPage(problemList: ArrayList<Problem>): StyledVBox() {
    // the result-list-view
    private val mResultListView = ResultListView(problemList)

    // the result statistics report
    private val mReport = StatisticsManager.hacer(problemList)

    // the label for showing no.'s of tested vocabularies
    private val mLblTestedNo = StyledLabel(Strings.TestedNo)

    // the label for showing tested vocabularies
    private val mLblTestedVocs = StyledLabel(Strings.TestedVocs)

    // the label for showing tested results (one per vocabulary)
    private val mLblTestedResults = StyledLabel(Strings.TestedResults)

    // the grid-pane for showing the titles of the tested vocabulary list
    private val mGdpListTitles = StyledGridPane.Builder(3)
            .setChildren(mLblTestedNo, mLblTestedVocs, mLblTestedResults)
            .setColumnsPercentWidths(*PropertiesManager.percentWidthsOfColumnsInResultListView)
            .create()

    // the label for showing the total number of all tested vocabularies
    private val mLblNumOfAll = StyledLabel(textColor = Colors.NUMBERED.color)

    // the label for showing the number of correct answers
    private val mLblNumOfCorrect = StyledLabel(textColor = Colors.CORRECT_RES.color)

    // the label for showing the number of wrong answers
    private val mLblNumOfWrong = StyledLabel(textColor = Colors.WRONG_RES.color)

    // the label for showing the number of problems that weren't answered
    private val mLblNumOfNoAnswered = StyledLabel(textColor = Colors.NO_ANSWER_RES.color)

    // the label for showing the correct rate
    private val mLblCorrectRate = StyledLabel()

    // the h-box for containing some statistics related uis
    private val mHbxStatistics = StyledHBox(mLblNumOfAll, mLblNumOfCorrect, mLblNumOfWrong, mLblNumOfNoAnswered)

    // the button for going back to the practice-menu-view
    private val mBtnGoBack = StyledButton(Strings.Back)

    init {
        // register labels if needs
        registerLabels()

        // add the sub-views into this v-box
        children.addAll(mGdpListTitles, mResultListView, mHbxStatistics, mLblCorrectRate, mBtnGoBack)

        // set the clicking event on the go-back-button
        mBtnGoBack.setOnAction { LayoutManager.switchPracticeContent() }

        // add hovering-listeners to all title labels of the result-list-view
        mLblTestedNo.hoverProperty().addListener { _, _, newValue -> hoverOnTitleLabels(mLblTestedNo, newValue) }
        mLblTestedVocs.hoverProperty().addListener { _, _, newValue -> hoverOnTitleLabels(mLblTestedVocs, newValue) }
        mLblTestedResults.hoverProperty().addListener { _, _, newValue -> hoverOnTitleLabels(mLblTestedResults, newValue) }

        // add clicking-listeners to all title labels of the result-list-view
        mLblTestedNo.setOnMouseClicked { clickOnTitleLabels(mLblTestedNo) }
        mLblTestedVocs.setOnMouseClicked { clickOnTitleLabels(mLblTestedVocs) }
        mLblTestedResults.setOnMouseClicked { clickOnTitleLabels(mLblTestedResults) }
    }

    /* ======================================== */

    // register labels if needs
    private fun registerLabels() {
        Strings.register(mLblNumOfAll,
                GenericString(Strings.TestedVocsNum), GenericString(Strings.COLON),
                GenericString(str = mReport.totalNum.toString()))
        Strings.register(mLblNumOfCorrect,
                GenericString(Strings.Correct), GenericString(Strings.COLON),
                GenericString(str = mReport.numOfCorrect.toString()))
        Strings.register(mLblNumOfWrong,
                GenericString(Strings.Wrong), GenericString(Strings.COLON),
                GenericString(str = mReport.numOfWrong.toString()))
        Strings.register(mLblNumOfNoAnswered,
                GenericString(Strings.NoAnswered), GenericString(Strings.COLON),
                GenericString(str = mReport.numOfNoAnswered.toString()))

        Strings.register(mLblCorrectRate,
                GenericString(Strings.CorrectRate), GenericString(Strings.COLON),
                GenericString(str = mReport.mCorrectRateWithPercentage))
    }

    // the mouse is currently hovering on some title labels of the result-list-view
    private fun hoverOnTitleLabels(lbl: StyledLabel, isHovering: Boolean) {
        // set the cursor style
        cursor = if (isHovering) Cursor.HAND else Cursor.DEFAULT
        // set the text-color of the hovered label
        lbl.textColor = if (isHovering) Colors.MOUSE_HOVERING.color else Colors.LABEL_DEFAULT.color
    }

    // the clicking event of title labels of the result-list-view
    private fun clickOnTitleLabels(lbl: StyledLabel) {

    }

    /* ======================================== */

    override fun requestFocus() {
        mBtnGoBack.requestFocus()
    }
}