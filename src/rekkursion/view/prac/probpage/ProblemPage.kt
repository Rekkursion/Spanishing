package rekkursion.view.prac.probpage

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.ButtonType
import rekkursion.enumerate.Colors
import rekkursion.enumerate.PickingScope
import rekkursion.enumerate.PracticeType
import rekkursion.enumerate.Strings
import rekkursion.manager.LayoutManager
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager
import rekkursion.manager.VocManager
import rekkursion.model.Vocabulary
import rekkursion.model.problem.Problem
import rekkursion.util.AlertUtils
import rekkursion.util.digits
import rekkursion.view.prac.ResultPage
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox

abstract class ProblemPage(
        practiceType: PracticeType,
        numOfProblems: Int,
        pickingScope: PickingScope)
    : StyledVBox() {

    // the type of the practice
    private val mPracticeType = practiceType

    // the number of problems
    private val mNumOfProblems = numOfProblems

    // the picking scope
    private val mPickingScope = pickingScope

    // the label for showing the no. of this problem
    private val mLblNo = StyledLabel(textColor = Colors.NUMBERED.color)

    // the label for showing a certain problem's stem
    private val mLblStem = StyledLabel()

    // the list of picked vocabularies as problems
    protected val mPickedVocList = arrayListOf<Vocabulary>()

    // the list of problems
    protected val mProblemList = arrayListOf<Problem>()

    // the index of the current problem
    protected var mCurrentProblemIdx = -1

    // the h-box for containing skipping & finishing buttons
    private val mHbxSkipAndFinish = StyledHBox()

    // the button for skipping a single problem
    protected val mBtnSkip = StyledButton(Strings.SkipProblem)

    // the button for finishing the whole problem-set
    protected val mBtnFinish = StyledButton(Strings.FinishProblemSet)

    init {
        // set the padding of the stem label container
        mLblStem.padding = Insets(0.0, 0.0, PropertiesManager.generalPadding, 0.0)
        // set the font size of the stem label
        mLblStem.style = "-fx-font-size: 24;"

        // add skipping & finishing buttons into an h-box
        mHbxSkipAndFinish.children.addAll(mBtnSkip, mBtnFinish)
        mHbxSkipAndFinish.padding = Insets(PropertiesManager.generalPadding, 0.0, 0.0, 0.0)

        // add all sub-views into this v-box
        children.addAll(mLblNo, mLblStem, mHbxSkipAndFinish)

        // set the event of clicking on skip-button
        mBtnSkip.setOnAction {
            if (PreferenceManager.alertWhenSkipping) {
                if (AlertUtils.createConfirmAlert(Strings.SkipProblemAlertMsg).showAndWait().get() == ButtonType.OK)
                    showNextProblem()
            }
            else
                showNextProblem()
        }

        // set the event of clicking on finish-button
        mBtnFinish.setOnAction {
            if (PreferenceManager.alertWhenFinishing) {
                if (AlertUtils.createConfirmAlert(Strings.FinishProblemSetAlertMsg).showAndWait().get() == ButtonType.OK)
                    LayoutManager.switchPracticeContent(ResultPage(mProblemList))
            }
            else
                LayoutManager.switchPracticeContent(ResultPage(mProblemList))
        }

        // pick some vocabularies as problems
        pickVocabularies()
    }

    /* ======================================== */

    // determine the problems according to the generated vocabularies
    abstract fun generateProblemsAndShowTheFirst()

    // show the next problem or the result-page if there're no more problems
    protected open fun showNextProblem() {
        val size = mPickedVocList.size
        val digits = size.digits()

        ++mCurrentProblemIdx

        // show the problem normally
        if (mCurrentProblemIdx < size) {
            val prob = mProblemList[mCurrentProblemIdx]

            mLblNo.text = String.format("%0${digits}d/%0${digits}d", mCurrentProblemIdx + 1, size)
            mLblStem.text = prob.getStemStr()
        }
        // show the result
        else
            LayoutManager.switchPracticeContent(ResultPage(mProblemList))
    }

    // fire the skipping button
    fun skip() { mBtnSkip.fire() }

    // fire the finishing button
    fun finishDirectly() { mBtnFinish.fire() }

    /* ======================================== */

    // pick vocabularies in the number of problems
    private fun pickVocabularies() {
        Thread {
            // pick some vocabularies
            VocManager.pickVocabularies(mNumOfProblems, mPickingScope).forEach { mPickedVocList.add(it.copy()) }
            // generate the problems
            generateProblemsAndShowTheFirst()
        }.start()
    }

    // insert some uis before the skipping button & the finishing button
    protected fun insertNodesBeforeSkippingAndFinishingButtons(vararg nodes: Node) {
        children.remove(mHbxSkipAndFinish)
        children.addAll(*nodes, mHbxSkipAndFinish)
    }
}