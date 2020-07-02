package rekkursion.view.practiceview

import rekkursion.manager.LayoutManager
import rekkursion.manager.PropertiesManager
import rekkursion.view.StyledButton
import rekkursion.view.StyledVBox

class PracticeMenuView: StyledVBox() {
    // the button for the single choice problems
    private val mBtnSingleChoice = StyledButton(PropertiesManager.Strings.singleChoiceProblem)

    // the button for the spelling problems
    private val mBtnSpelling = StyledButton(PropertiesManager.Strings.spellingProblem)

    init {
        // add all buttons into this v-box
        children.addAll(mBtnSingleChoice, mBtnSpelling)

        // the event of clicking on single-choice-problems button
        mBtnSingleChoice.setOnMouseClicked {
            LayoutManager.switchPracticeContent(PracticeSettingsView(PracticeType.SINGLE_CHOICE))
        }

        // the event of clicking on spelling-problems button
        mBtnSpelling.setOnMouseClicked {
            LayoutManager.switchPracticeContent(PracticeSettingsView(PracticeType.SPELLING))
        }
    }
}