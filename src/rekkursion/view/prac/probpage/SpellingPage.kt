package rekkursion.view.prac.probpage

import javafx.application.Platform
import rekkursion.enumerate.AnsResult
import rekkursion.enumerate.Colors
import rekkursion.enumerate.PracticeType
import rekkursion.enumerate.Strings
import rekkursion.model.problem.SpellingProblem
import rekkursion.view.prac.SpellingInputArea
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledLabel

class SpellingPage(numOfProblems: Int): ProblemPage(PracticeType.SPELLING, numOfProblems) {
    // the input-area for the user to spell
    private val mSpellingArea: SpellingInputArea

    // the submit button
    private val mBtnSubmit = StyledButton(Strings.Submit)

    // the label for telling the user that they wrong spelled the vocabulary
    private val mLblShowWrongSpelling = StyledLabel(Strings.Wrong, Colors.WRONG_RES)

    init {
        // initialize the spelling-input-area
        mSpellingArea = SpellingInputArea(submitButton = mBtnSubmit)

        // hide the telling-wrong-spelled label
        mLblShowWrongSpelling.isVisible = false

        // the event for submitting the spelling answer
        mBtnSubmit.setOnAction {
            if (mCurrentProblemIdx < mProblemList.size) {
                val prob = mProblemList[mCurrentProblemIdx] as SpellingProblem
                if (prob.getEsp() == mSpellingArea.toString()) {
                    if (prob.ansResult == AnsResult.NO_ANSWERED)
                        prob.ansResult = AnsResult.CORRECT
                    showNextProblem()
                }
                else {
                    mLblShowWrongSpelling.isVisible = true
                    prob.ansResult = AnsResult.WRONG
                }
            }
        }
    }

    /* ======================================== */

    override fun generateProblemsAndShowTheFirst() {
        // the number of picked vocabularies (the number of problems)
        val sizeOfPicked = mPickedVocList.size

        repeat(sizeOfPicked) { idxOfProblem ->
            mProblemList.add(SpellingProblem(mPickedVocList[idxOfProblem]))
        }

        mCurrentProblemIdx = -1
        Platform.runLater {
            // insert the spelling-input-area
            insertNodesBeforeSkippingAndFinishingButtons(mSpellingArea, mBtnSubmit, mLblShowWrongSpelling)
            // show the first problem
            showNextProblem()
        }
    }

    override fun showNextProblem() {
        super.showNextProblem()

        mLblShowWrongSpelling.isVisible = false
        if (mCurrentProblemIdx >= 0 && mCurrentProblemIdx < mProblemList.size)
            mSpellingArea.changeVoc(mProblemList[mCurrentProblemIdx].toVoc().esp)
    }
}