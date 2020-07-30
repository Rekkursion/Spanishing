package rekkursion.view.prac

import javafx.geometry.Insets
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import rekkursion.enumerate.SingleChoiceProblemType
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox

class SingleChoiceSettingsPanel: StyledVBox() {
    // the label for showing the title
    private val mLblTitle = StyledLabel(Strings.SelectTypeOfSingleChoiceProblem)

    // the list of radio-buttons for choosing the type of single-choice problems
    private val mRdbList = arrayOf(
            RadioButton(Strings.get(Strings.EspToChiAndEng)),
            RadioButton(Strings.get(Strings.ChiAndEngToEsp)),
            RadioButton(Strings.get(Strings.BothTypes))
    )

    // the toggle-group for containing all radio-buttons
    private val mToggleGroup = ToggleGroup()

    init {
        // set the padding
        padding = Insets(0.0, 0.0, PropertiesManager.generalPadding, 0.0)

        // set all radio-buttons
        mRdbList.forEachIndexed { index, rdb ->
            // set the toggle-group of all radio-buttons
            rdb.toggleGroup = mToggleGroup
            // set the font sizes
            rdb.style = "-fx-font-size: 18;"
            // add listener when selecting among the radio-buttons
            rdb.selectedProperty().addListener { _, _, newValue ->
                if (newValue)
                    PreferenceManager.write("preferred-single-choice-problem-type", index.toString())
            }
        }

        // register to the strings
        Strings.register(mRdbList[0], Strings.EspToChiAndEng)
        Strings.register(mRdbList[1], Strings.ChiAndEngToEsp)
        Strings.register(mRdbList[2], Strings.BothTypes)

        // set the default selected radio-button
        (mRdbList.getOrNull(PreferenceManager.preferredSingleChoiceProblemType) ?: mRdbList[2]).isSelected = true

        // add all uis into this v-box
        children.addAll(mLblTitle, *mRdbList)
    }

    /* ======================================== */

    // get the user-selected type of single-choice problems
    fun getSingleChoiceProblemType(): SingleChoiceProblemType = when (mToggleGroup.selectedToggle) {
        mRdbList[0] -> SingleChoiceProblemType.ESP_TO_CHI_AND_ENG
        mRdbList[1] -> SingleChoiceProblemType.CHI_AND_ENG_TO_ESP
        else -> SingleChoiceProblemType.BOTH
    }
}