package rekkursion.view.practiceview

import rekkursion.enumerate.PracticeType
import rekkursion.enumerate.Strings
import rekkursion.manager.LayoutManager
import rekkursion.manager.VocManager
import rekkursion.view.NumberSelector
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox
import kotlin.math.min

class PracticeSettingsView(practiceType: PracticeType): StyledVBox() {
    // the panel for the settings of single-choice problems only
    private val mSingleChoiceSettingsPanel = SingleChoiceSettingsPanel()

    // the title for the num-of-problems spinner
    private val mLblNumOfProblemsTitle = StyledLabel(Strings.get(Strings.SelectNumOfProblems))

    // the number-selector for selecting the number of problems the user want to take
    private val mNslNumOfProblems = NumberSelector(1, min(100, VocManager.numOfVocabularies), min(10, VocManager.numOfVocabularies), 1)

    // the button for starting practice
    private val mBtnStartPractice = StyledButton(Strings.get(Strings.Start))

    // the button for going back to the menu page of practice
    private val mBtnGoBackToPracticeMenu = StyledButton(Strings.get(Strings.Back))

    init {
        // if the practice type is the single choice problems
        if (practiceType == PracticeType.SINGLE_CHOICE) {
            children.add(mSingleChoiceSettingsPanel)
        }

        // otherwise, it's the spelling problems
        else if (practiceType == PracticeType.SPELLING) {

        }

        // add buttons into this v-box
        children.addAll(mLblNumOfProblemsTitle, mNslNumOfProblems, mBtnStartPractice, mBtnGoBackToPracticeMenu)

        // the event of clicking on the start button
        mBtnStartPractice.setOnMouseClicked {
            LayoutManager.switchPracticeContent(SingleChoicePage(
                    mSingleChoiceSettingsPanel.getSingleChoiceProblemType(),
                    mNslNumOfProblems.getNumber()
            ))
        }

        // the event of clicking on the go-back button
        mBtnGoBackToPracticeMenu.setOnMouseClicked {
            LayoutManager.switchPracticeContent()
        }
    }
}