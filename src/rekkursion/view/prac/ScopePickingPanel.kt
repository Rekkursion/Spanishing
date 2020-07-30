package rekkursion.view.prac

import javafx.geometry.Insets
import javafx.scene.control.ToggleGroup
import rekkursion.enumerate.Colors
import rekkursion.enumerate.PickingScope
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager
import rekkursion.manager.VocManager
import rekkursion.util.GenericString
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledRadioButton
import rekkursion.view.styled.StyledVBox

class ScopePickingPanel: StyledVBox() {
    // the label for determining the scope of problem-picking (all or collected-only)
    private val mLblPickingScopeTitle = StyledLabel(Strings.PickingScope)

    // the label for showing the maximum number of vocabularies according to which radio-button is currently selected
    private val mLblMaxNumOfVocs = StyledLabel()

    // the toggle-group for containing all radio-buttons
    private val mToggleGroup = ToggleGroup()

    // the radio-button for scoping all vocabularies
    private val mRdbAll = StyledRadioButton(Strings.PickingScope_All, Colors.DEFAULT, mToggleGroup)

    // the radio-button for scoping collected-only vocabularies
    private val mRdbCollectedOnly = StyledRadioButton(Strings.PickingScope_CollectedOnly, Colors.DEFAULT, mToggleGroup)

    // the h-box for containing radio-buttons
    private val mHbxForRadioButtons = StyledHBox(mRdbAll, mRdbCollectedOnly)

    init {
        // set the padding
        padding = Insets(0.0, 0.0, PropertiesManager.generalPadding, 0.0)

        // set the initial selecting status
        if (PreferenceManager.preferredPickingScope == 0)
            mRdbAll.isSelected = true
        else
            mRdbCollectedOnly.isSelected = true

        // set the text-color of max-num label
        mLblMaxNumOfVocs.textColor = Colors.NUMBERED.color

        // set the text of max-num label
        registerMaxNumLabel()

        // add all sub-views into this v-box
        children.addAll(mLblPickingScopeTitle, mLblMaxNumOfVocs, mHbxForRadioButtons)

        // set the selection statuses listeners on all radio-buttons
        mRdbAll.selectedProperty().addListener { _, _, _ ->
            registerMaxNumLabel()
            PreferenceManager.write("preferred-picking-scope", 0.toString())
        }
        mRdbCollectedOnly.selectedProperty().addListener { _, _, _ ->
            registerMaxNumLabel()
            PreferenceManager.write("preferred-picking-scope", 1.toString())
        }
    }

    /* ======================================== */

    // get the determined picking scope
    fun getPickingScope(): PickingScope =
            if (mRdbAll.isSelected) PickingScope.ALL_VOCS else PickingScope.COLLECTED_ONLY

    // register/re-register the label for showing maximum number of vocabularies according to which radio-button is currently selected
    private fun registerMaxNumLabel() {
        if (mRdbAll.isSelected)
            Strings.register(mLblMaxNumOfVocs,
                    GenericString(Strings.PickingScope_MaxNumOfVocs_All_pre),
                    GenericString(str = VocManager.numOfVocabularies.toString()),
                    GenericString(Strings.PickingScope_MaxNumOfVocs_All_suf)
            )
        else
            Thread {
                Strings.register(mLblMaxNumOfVocs,
                        GenericString(Strings.PickingScope_MaxNumOfVocs_CollectedOnly_pre),
                        GenericString(str = VocManager.copiedCollectedList.size.toString()),
                        GenericString(Strings.PickingScope_MaxNumOfVocs_CollectedOnly_suf)
                )
            }.start()
    }
}