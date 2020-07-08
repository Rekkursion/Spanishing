package rekkursion.view.practiceview

import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import rekkursion.enumerate.SingleChoiceProblemType
import rekkursion.enumerate.Strings
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox

class SingleChoiceSettingsPanel: StyledVBox() {
    // the label for showing the title
    private val mLblTitle = StyledLabel(Strings.get(Strings.SelectTypeOfSingleChoiceProblem))

    // the radio-button for the type of esp-to-chi-and-eng
    private val mRdbEspToChiAndEng = RadioButton(Strings.get(Strings.EspToChiAndEng))

    // the radio-button for the type of chi-and-eng-to-esp
    private val mRdbChiAndEngToEsp = RadioButton(Strings.get(Strings.ChiAndEngToEsp))

    // the radio-button for the type of both
    private val mRdbBoth = RadioButton(Strings.get(Strings.BothTypes))

    // the toggle-group for containing all radio-buttons
    private val mToggleGroup =  ToggleGroup()

    init {
        // set the toggle-group of all radio-buttons
        mRdbEspToChiAndEng.toggleGroup = mToggleGroup
        mRdbChiAndEngToEsp.toggleGroup = mToggleGroup
        mRdbBoth.toggleGroup = mToggleGroup

        // set the font sizes
        mRdbEspToChiAndEng.style = "-fx-font-size: 18;"
        mRdbChiAndEngToEsp.style = "-fx-font-size: 18;"
        mRdbBoth.style = "-fx-font-size: 18;"

        // set the default selected radio-button
        mRdbBoth.isSelected = true

        // add all uis into this v-box
        children.addAll(mLblTitle, mRdbEspToChiAndEng, mRdbChiAndEngToEsp, mRdbBoth)
    }

    /* ======================================== */

    // get the user-selected type of single-choice problems
    fun getSingleChoiceProblemType(): SingleChoiceProblemType = when (mToggleGroup.selectedToggle) {
        mRdbEspToChiAndEng -> SingleChoiceProblemType.ESP_TO_CHI_AND_ENG
        mRdbChiAndEngToEsp -> SingleChoiceProblemType.CHI_AND_ENG_TO_ESP
        else -> SingleChoiceProblemType.BOTH
    }
}