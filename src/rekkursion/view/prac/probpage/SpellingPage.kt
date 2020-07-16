package rekkursion.view.prac.probpage

import javafx.application.Platform
import rekkursion.enumerate.AnsResult
import rekkursion.enumerate.Colors
import rekkursion.enumerate.PracticeType
import rekkursion.enumerate.Strings
import rekkursion.model.problem.SpellingProblem
import rekkursion.view.prac.SpellingInputArea
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledLabel

class SpellingPage(numOfProblems: Int): ProblemPage(PracticeType.SPELLING, numOfProblems) {
    // the input-area for the user to spell
    private val mSpellingArea = SpellingInputArea(spellingPage = this)

    // the h-box for containing all labels of special alphabets
    private val mHbxSpAlphabets = StyledHBox()

    // the list of buttons of special alphabets
    private val mSpAlphabetButtonList = arrayOf(
            StyledButton("á"), StyledButton("Á"),
            StyledButton("é"), StyledButton("É"),
            StyledButton("í"), StyledButton("Í"),
            StyledButton("ó"), StyledButton("Ó"),
            StyledButton("ú"), StyledButton("Ú"),
            StyledButton("ü"), StyledButton("Ü"),
            StyledButton("ñ"), StyledButton("Ñ")
    )

    // the submit button
    private val mBtnSubmit = StyledButton(Strings.Submit)

    // the label for telling the user that they wrong spelled the vocabulary
    private val mLblShowWrongSpelling = StyledLabel(Strings.Wrong, Colors.WRONG_RES)

    init {
        // hide the telling-wrong-spelled label
        mLblShowWrongSpelling.isVisible = false

        // add all labels of special alphabets to an h-box
        mHbxSpAlphabets.children.addAll(
                StyledLabel("á = alt + A", Colors.HINT_TYPING_SP_ALPHABET_EVEN.color),
                StyledLabel("é = alt + E", Colors.HINT_TYPING_SP_ALPHABET_ODDS.color),
                StyledLabel("í = alt + I", Colors.HINT_TYPING_SP_ALPHABET_EVEN.color),
                StyledLabel("ó = alt + O", Colors.HINT_TYPING_SP_ALPHABET_ODDS.color),
                StyledLabel("ú = alt + U", Colors.HINT_TYPING_SP_ALPHABET_EVEN.color),
                StyledLabel("ü = alt + ctrl + u", Colors.HINT_TYPING_SP_ALPHABET_ODDS.color),
                StyledLabel("ñ = alt + 1", Colors.HINT_TYPING_SP_ALPHABET_EVEN.color)
        )

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

        // set the clicking event on all buttons of special alphabets
        mSpAlphabetButtonList.forEach { button ->
            button.setOnAction {
                mSpellingArea.appendSpellingText(button.text)
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
            insertNodesBeforeSkippingAndFinishingButtons(mSpellingArea, mHbxSpAlphabets, mBtnSubmit, mLblShowWrongSpelling)
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

    // fire the submission button
    fun submit() { mBtnSubmit.fire() }

    // fire a certain button of the special alphabet
    fun fireSpecialAlphabet(alphabet: Char) {
        mSpAlphabetButtonList.filter { it.text == alphabet.toString() }.getOrNull(0)?.fire()
    }
}