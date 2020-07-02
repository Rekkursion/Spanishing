package rekkursion.view.practiceview

import rekkursion.manager.LayoutManager
import rekkursion.manager.PropertiesManager
import rekkursion.view.StyledButton
import rekkursion.view.StyledVBox

// the types of practices
enum class PracticeType {
    // single choice problems
    SINGLE_CHOICE,
    // spelling problems
    SPELLING
}

class PracticeSettingsView(practiceType: PracticeType): StyledVBox() {
    // the button for starting practice
    private val mBtnStartPractice = StyledButton(PropertiesManager.Strings.start)

    // the button for going back to the menu page of practice
    private val mBtnGoBackToPracticeMenu = StyledButton(PropertiesManager.Strings.back)

    init {
        // if the practice type is the single choice problems
        if (practiceType == PracticeType.SINGLE_CHOICE) {

        }

        // otherwise, it's the spelling problems
        else if (practiceType == PracticeType.SPELLING) {

        }

        // add buttons into this v-box
        children.addAll(mBtnStartPractice, mBtnGoBackToPracticeMenu)

        // the event of clicking on the start button
        mBtnStartPractice.setOnMouseClicked {

        }

        // the event of clicking on the go-back button
        mBtnGoBackToPracticeMenu.setOnMouseClicked {
            LayoutManager.switchPracticeContent()
        }
    }
}