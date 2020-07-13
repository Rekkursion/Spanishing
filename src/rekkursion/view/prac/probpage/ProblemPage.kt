package rekkursion.view.prac.probpage

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.ButtonType
import rekkursion.enumerate.Colors
import rekkursion.enumerate.PracticeType
import rekkursion.enumerate.Strings
import rekkursion.manager.LayoutManager
import rekkursion.manager.PreferenceManager
import rekkursion.model.Problem
import rekkursion.util.AlertUtils
import rekkursion.view.prac.ResultPage
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox

abstract class ProblemPage(practiceType: PracticeType, numOfProblems: Int): StyledVBox() {
    // the type of the practice
    protected val mPracticeType = practiceType

    // the number of problems
    protected val mNumOfProblems = numOfProblems

    // the label for showing the no. of this problem
    protected val mLblNo = StyledLabel(textColor = Colors.NUMBERED.color)

    // the label for showing a certain problem's stem
    protected val mLblStem = StyledLabel()

    // the list of problems
    protected val mProblemList = arrayListOf<Problem>()

    // the index of the current problem
    protected var mCurrentProblemIdx = -1

    // the h-box for containing skipping & finishing buttons
    private val mHbxSkipAndFinish = StyledHBox()

    // the button for skipping a single problem
    private val mBtnSkip = StyledButton(Strings.SkipProblem)

    // the button for finishing the whole problem-set
    private val mBtnFinish = StyledButton(Strings.FinishProblemSet)

    init {
        // set the padding of the stem label
        mLblStem.padding = Insets(0.0, 0.0, 40.0, 0.0)
        // set the font size of the stem label
        mLblStem.style = "-fx-font-size: 24;"

        // add skipping & finishing buttons into an h-box
        mHbxSkipAndFinish.children.addAll(mBtnSkip, mBtnFinish)
        mHbxSkipAndFinish.padding = Insets(40.0, 0.0, 0.0, 0.0)

        // add all sub-views into this v-box
        children.addAll(mLblNo, mLblStem, mHbxSkipAndFinish)

        // set the event of clicking on skip-button
        mBtnSkip.setOnMouseClicked {
            if (PreferenceManager.alertWhenSkipping) {
                if (AlertUtils.createConfirmAlert(Strings.SkipProblemAlertMsg).showAndWait().get() == ButtonType.OK)
                    showNextProblem()
            }
            else
                showNextProblem()
        }

        // set the event of clicking on finish-button
        mBtnFinish.setOnMouseClicked {
            if (PreferenceManager.alertWhenFinishing) {
                if (AlertUtils.createConfirmAlert(Strings.FinishProblemSetAlertMsg).showAndWait().get() == ButtonType.OK)
                    LayoutManager.switchPracticeContent(ResultPage(mProblemList))
            }
            else
                LayoutManager.switchPracticeContent(ResultPage(mProblemList))
        }
    }

    /* ======================================== */

    // determine the problems according to the type of practice & the number of problems
    abstract fun generateProblemsAndShowTheFirst()

    // show the next problem or the result-page if there're no more problems
    abstract fun showNextProblem()

    /* ======================================== */

    // insert some uis before the skipping button & the finishing button
    protected fun insertNodesBeforeSkippingAndFinishingButtons(vararg nodes: Node) {
        children.remove(mHbxSkipAndFinish)
        children.addAll(*nodes, mHbxSkipAndFinish)
    }
}