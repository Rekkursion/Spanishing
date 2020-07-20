package rekkursion.view.prac

import javafx.geometry.Insets
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import rekkursion.enumerate.Colors
import rekkursion.enumerate.PracticeType
import rekkursion.enumerate.Strings
import rekkursion.manager.LayoutManager
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager
import rekkursion.manager.VocManager
import rekkursion.view.NumberSelector
import rekkursion.view.prac.probpage.SingleChoicePage
import rekkursion.view.prac.probpage.SpellingPage
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox
import kotlin.math.min

class PracticeSettingsView(practiceType: PracticeType): StyledVBox() {
    // the panel for the settings of single-choice problems only
    private val mSingleChoiceSettingsPanel = SingleChoiceSettingsPanel()

    // the title for showing the practice type
    private val mLblPracticeTypeTitle = StyledLabel(
            if (practiceType == PracticeType.SPELLING) Strings.SpellingProblem else Strings.SingleChoiceProblem,
            Colors.DEFAULT
    )

    // the title for the num-of-problems spinner
    private val mLblNumOfProblemsTitle = StyledLabel(Strings.SelectNumOfProblems)

    // the number-selector for selecting the number of problems the user want to take
    private val mNslNumOfProblems = NumberSelector(
            1,
            min(300, VocManager.numOfVocabularies),
            min(min(300, VocManager.numOfVocabularies), PreferenceManager.preferredProblemNum),
            1
    )

    // the button for starting practice
    private val mBtnStartPractice = StyledButton(Strings.Start)

    // the button for going back to the menu page of practice
    private val mBtnGoBackToPracticeMenu = StyledButton(Strings.Back)

    init {
        // set the bottom-padding of the title label and and it into this v-box
        mLblPracticeTypeTitle.padding = Insets(0.0, 0.0, PropertiesManager.generalPadding, 0.0)
        children.add(mLblPracticeTypeTitle)

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
        mBtnStartPractice.setOnAction {
            LayoutManager.switchPracticeContent(
                    if (practiceType == PracticeType.SINGLE_CHOICE) SingleChoicePage(
                            mSingleChoiceSettingsPanel.getSingleChoiceProblemType(),
                            mNslNumOfProblems.getNumber()
                    ) else SpellingPage(mNslNumOfProblems.getNumber())
            )
        }

        // the event of clicking on the go-back button
        mBtnGoBackToPracticeMenu.setOnAction {
            LayoutManager.switchPracticeContent()
        }

        // the event of releasing a key on the number-selector
        mNslNumOfProblems.setOnKeyReleased(object: NumberSelector.OnKeyReleased {
            override fun onKeyReleased(keyEvent: KeyEvent) {
                when {
                    keyEvent.code == KeyCode.ENTER -> mBtnStartPractice.fire()
                    keyEvent.code == KeyCode.DOWN -> mBtnStartPractice.requestFocus()
                }
            }
        })
    }

    /* ======================================== */

    override fun requestFocus() {
        mNslNumOfProblems.requestFocus()
    }
}