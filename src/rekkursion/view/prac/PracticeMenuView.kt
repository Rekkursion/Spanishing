package rekkursion.view.prac

import rekkursion.enumerate.PracticeType
import rekkursion.enumerate.Strings
import rekkursion.manager.LayoutManager
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledVBox

class PracticeMenuView: StyledVBox() {
    // the button for the single choice problems
    private val mBtnSingleChoice = StyledButton(Strings.SingleChoiceProblem)

    // the button for the spelling problems
    private val mBtnSpelling = StyledButton(Strings.SpellingProblem)

    init {
        // add all buttons into this v-box
        children.addAll(mBtnSingleChoice, mBtnSpelling)

        // the event of clicking on single-choice-problems button
        mBtnSingleChoice.setOnAction {
            LayoutManager.switchPracticeContent(PracticeSettingsView(PracticeType.SINGLE_CHOICE))
        }

        // the event of clicking on spelling-problems button
        mBtnSpelling.setOnAction {
            LayoutManager.switchPracticeContent(PracticeSettingsView(PracticeType.SPELLING))
        }
    }

    /* ======================================== */

    override fun requestFocus() {
        mBtnSingleChoice.requestFocus()
    }
}