package rekkursion.view.prac.probpage

import javafx.application.Platform
import javafx.scene.control.Button
import javafx.scene.input.KeyCode
import rekkursion.enumerate.AnsResult
import rekkursion.enumerate.Colors
import rekkursion.enumerate.PracticeType
import rekkursion.enumerate.SingleChoiceProblemType
import rekkursion.manager.PropertiesManager
import rekkursion.manager.VocManager
import rekkursion.model.Vocabulary
import rekkursion.model.problem.SingleChoiceProblem
import rekkursion.util.HoldingQueue
import rekkursion.view.styled.StyledButton
import kotlin.random.Random

class SingleChoicePage(problemType: SingleChoiceProblemType, numOfProblems: Int): ProblemPage(PracticeType.SINGLE_CHOICE, numOfProblems) {
    // the type of single-choice problems
    private val mProblemType = problemType

    // the button list of options of a certain problem
    private val mBtnOptionList = arrayListOf<Button>()

    /* ======================================== */

    // determine the problems according to the type & the number of problems
    override fun generateProblemsAndShowTheFirst() {
        // the original vocabularies list
        val vocList = VocManager.copiedVocList
        // the number of picked vocabularies (the number of problems)
        val sizeOfPicked = mPickedVocList.size
        // the number of options
        val numOfOpts = PropertiesManager.numOfOptionsInSingleChoiceProblem

        // add buttons of options
        repeat(numOfOpts) { mBtnOptionList.add(StyledButton()) }

        // iteratively generate the options per problem
        repeat(sizeOfPicked) { idxOfProblem ->
            // the picked vocabulary
            val pickedVoc = mPickedVocList[idxOfProblem]

            // the list of options of this problem
            val optList = arrayListOf<Vocabulary>()

            // the position of the correct answer
            val posOfCorrectAns = Random.nextInt(0, numOfOpts)

            // create the list of options
            val q = HoldingQueue<Int>(numOfOpts)
            repeat(numOfOpts) { pos ->

                if (pos == posOfCorrectAns)
                    optList.add(pickedVoc)
                else {
                    while (true) {
                        val optIdx = Random.nextInt(0, vocList.size)
                        val optVoc = vocList[optIdx]
                        if (optVoc != pickedVoc && !q.has(optIdx)) {
                            q.push(optIdx)
                            optList.add(optVoc)
                            break
                        }
                    }
                }
            }

            // determine the type of this single choice problem
            val type = if (mProblemType == SingleChoiceProblemType.BOTH) {
                if (Random.nextInt(0, 2) == 0) SingleChoiceProblemType.ESP_TO_CHI_AND_ENG
                else SingleChoiceProblemType.CHI_AND_ENG_TO_ESP
            } else mProblemType

            // add a new problem into the problem list
            mProblemList.add(SingleChoiceProblem(type, pickedVoc, optList))
        }

        mCurrentProblemIdx = -1
        Platform.runLater {
            // set the events of buttons of options
            mBtnOptionList.forEachIndexed { index, button ->
                // request the focusing on the first option-button
                if (index == 0) {
                    this.requestFocus()
                    this.setOnKeyPressed {
                        if (it.code == KeyCode.ENTER) {
                            if (it.isControlDown) mBtnSkip.fire()
                            else if (it.isShiftDown) mBtnFinish.fire()
                        }
                    }
                }

                button.setOnMouseClicked {
                    if (mCurrentProblemIdx < mProblemList.size) {
                        val prob = mProblemList[mCurrentProblemIdx] as SingleChoiceProblem
                        if (index == prob.correctAnsPos) {
                            if (prob.ansResult == AnsResult.NO_ANSWERED)
                                prob.ansResult = AnsResult.CORRECT
                            showNextProblem()
                        }
                        else {
                            (button as StyledButton).setBgColor(Colors.WRONG_ANSWER_BG.color)
                            prob.ansResult = AnsResult.WRONG
                        }
                    }
                }
            }
            // add all buttons of options
            insertNodesBeforeSkippingAndFinishingButtons(*(mBtnOptionList.toTypedArray()))
            // show the first problem
            showNextProblem()
        }
    }

    // show the next problem
    override fun showNextProblem() {
        super.showNextProblem()

        if (mCurrentProblemIdx < mProblemList.size) {
            repeat(PropertiesManager.numOfOptionsInSingleChoiceProblem) {
                mBtnOptionList[it].text = (mProblemList[mCurrentProblemIdx] as SingleChoiceProblem).getOptionStr(it)
                (mBtnOptionList[it] as StyledButton).unsetBgColor()
            }
        }
    }

}