package rekkursion.view.practiceview

import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import rekkursion.enumerate.AnsResult
import rekkursion.enumerate.Colors
import rekkursion.enumerate.SingleChoiceProblemType
import rekkursion.enumerate.Strings
import rekkursion.manager.LayoutManager
import rekkursion.manager.PreferenceManager
import rekkursion.manager.VocManager
import rekkursion.model.Problem
import rekkursion.model.Vocabulary
import rekkursion.util.AlertUtils
import rekkursion.util.GenericString
import rekkursion.util.HoldingQueue
import rekkursion.util.digits
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox
import kotlin.random.Random

class SingleChoicePage(problemType: SingleChoiceProblemType, numOfProblems: Int): StyledVBox() {
    // the label for showing the no. of this problem
    private val mLblNo = StyledLabel(textColor = Colors.NUMBERED.color)

    // the label for showing a certain problem's stem
    private val mLblStem = StyledLabel()

    // the button list of options of a certain problem
    private val mBtnOptionList = arrayOf<Button>(StyledButton(), StyledButton(), StyledButton(), StyledButton())

    // the list of problems
    private val mProblemList = arrayListOf<Problem>()

    // the index of the current problem
    private var mCurrentProblemIdx = -1

    // the h-box for containing skipping & finishing buttons
    private val mHbxSkipAndFinish = StyledHBox()

    // the button for skipping a single problem
    private val mBtnSkip = StyledButton(Strings.SkipProblem)

    // the button for finishing the whole problem-set
    private val mBtnFinish = StyledButton(Strings.FinishProblemSet)

    init {
        // determine the problems according to the type & the number of problems
        generateProblemsAndShowTheFirst(problemType, numOfProblems)

        // set the padding of the stem label
        mLblStem.padding = Insets(0.0, 0.0, 40.0, 0.0)
        // set the font size of the stem label
        mLblStem.style = "-fx-font-size: 24;"

        // add skipping & finishing buttons into an h-box
        mHbxSkipAndFinish.children.addAll(mBtnSkip, mBtnFinish)
        mHbxSkipAndFinish.padding = Insets(40.0, 0.0, 0.0, 0.0)

        // add all sub-views into this v-box
        children.addAll(mLblNo, mLblStem, *mBtnOptionList, mHbxSkipAndFinish)

        // set the events of buttons of options
        mBtnOptionList.forEachIndexed { index, button ->
            button.setOnMouseClicked {
                if (mCurrentProblemIdx < mProblemList.size) {
                    val prob = mProblemList[mCurrentProblemIdx]
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

    // determine the problems according to the type & the number of problems
    private fun generateProblemsAndShowTheFirst(problemType: SingleChoiceProblemType, numOfProblems: Int) {
        Thread {
            // get the copied vocabulary list
            val vocList = VocManager.copiedVocList

            // shuffle the copied vocabulary list
            var idx = 0
            while (idx < vocList.size / 2) {
                // get a random index
                val randomIndex = Random.nextInt(idx + 1, vocList.size)

                // swap the current vocabulary and the random one
                val v1 = vocList[idx].copy()
                val v2 = vocList[randomIndex].copy()
                vocList[randomIndex] = v1
                vocList[idx] = v2

                // repeat
                ++idx
            }

            // iteratively select the vocabularies counted from the front
            repeat(numOfProblems) {
                // the list of options of this problem
                val optList = arrayListOf<Vocabulary>()

                // the position of the correct answer
                val posOfCorrectAns = Random.nextInt(0, 4)

                // create the list of options
                val q = HoldingQueue<Int>(4)
                repeat(4) { pos ->
                    if (pos == posOfCorrectAns)
                        optList.add(vocList[it])
                    else {
                        var optIdx = 0
                        while (true) {
                            optIdx = if (it == 0)
                                Random.nextInt(it + 1, vocList.size)
                            else if (it == vocList.size)
                                Random.nextInt(0, it)
                            else {
                                val r1 = Random.nextInt(0, it)
                                val r2 = Random.nextInt(it + 1, vocList.size)
                                // choose r1
                                if (Random.nextInt(0, 2) == 0) r1
                                // choose r2
                                else r2
                            }

                            if (!q.has(optIdx)) {
                                q.push(optIdx)
                                break
                            }
                        }
                        optList.add(vocList[optIdx])
                    }
                }

                // determine the type of this single choice problem
                val type = if (problemType == SingleChoiceProblemType.BOTH) {
                    if (Random.nextInt(0, 2) == 0) SingleChoiceProblemType.ESP_TO_CHI_AND_ENG
                    else SingleChoiceProblemType.CHI_AND_ENG_TO_ESP
                } else problemType

                // add a new problem into the problem list
                mProblemList.add(Problem(type, vocList[it], optList))
            }

            // show the first problem
            Platform.runLater { showNextProblem() }
        }.start()
    }

    // show the next problem
    private fun showNextProblem() {
        val size = mProblemList.size
        val digits = size.digits()

        ++mCurrentProblemIdx
        // show the problem normally
        if (mCurrentProblemIdx < size) {
            mLblNo.text = String.format("%0${digits}d/%0${digits}d", mCurrentProblemIdx + 1, size)
            mLblStem.text = mProblemList[mCurrentProblemIdx].getStem()
            repeat(4) {
                mBtnOptionList[it].text = mProblemList[mCurrentProblemIdx].getOption(it)
                (mBtnOptionList[it] as StyledButton).unsetBgColor()
            }
        }
        // show the result
        else
            LayoutManager.switchPracticeContent(ResultPage(mProblemList))
    }
}