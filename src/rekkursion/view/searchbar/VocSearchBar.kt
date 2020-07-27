package rekkursion.view.searchbar

import javafx.geometry.Insets
import javafx.scene.control.CheckBox
import rekkursion.enumerate.Colors
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager
import rekkursion.util.GenericString
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledCheckBox
import rekkursion.view.styled.StyledLabel

class VocSearchBar: SearchBar() {
    // the check-box for showing only collected vocabularies
    private val mCkbShowOnlyCollected = StyledCheckBox(Strings.ShowOnlyCollectedVocs)

    // the button for adjusting advanced options for searching (filtering) vocabularies
    private val mBtnAdvancedOptions = StyledButton(Strings.AdvancedVocSearchOptions)

    // the label for showing the number of filtered vocabularies
    private val mLblNumOfFilteredVocs = StyledLabel(textColor = Colors.NUMBERED.color)

    // the boolean value for checking if it's currently showing the panel for advanced options
    private var mShowingAdvancedOptionsPanel = false

    init {
        // unify the text-sizes
        setTextSizes(PropertiesManager.searchBarTextSize)
        mBtnAdvancedOptions.textSize = PropertiesManager.searchBarTextSize

        // set the width of advanced-options-button
        mBtnAdvancedOptions.maxWidth = 150.0

        mCkbShowOnlyCollected.padding = Insets(0.0, 0.0, 0.0, PropertiesManager.generalPadding)
        mCkbShowOnlyCollected.textSize = PropertiesManager.searchBarTextSize
        mCkbShowOnlyCollected.isSelected = PreferenceManager.isCollectedOnly

        // add the advanced-options-button into the basic search-bar
        pushNodesAtFirstRow(mCkbShowOnlyCollected, mBtnAdvancedOptions, mLblNumOfFilteredVocs)

        // show only collected vocabularies
        mCkbShowOnlyCollected.selectedProperty().addListener { _, _, newValue ->
            val opts = searchOptionsCopied
            opts.isCollectedOnly = newValue
            setSearchOptions(opts)
            PreferenceManager.write("is-collected-only", newValue.toString())
        }

        // set the mouse-clicking event on advanced-options-button
        mBtnAdvancedOptions.setOnAction {
            toggleAdvancedOptionsPanel()
            requestFocus()
        }

        setSearchOptions(searchOptionsCopied)
    }

    // set the number of filtered
    fun setNumOfFiltered(numOfFiltered: Int) {
        // hide the text of num-label
        if (mTxfInput.text.isEmpty()) {
            Strings.unregister(mLblNumOfFilteredVocs)
            mLblNumOfFilteredVocs.text = ""
        }
        // set and register the strings of num-label
        else
            Strings.register(mLblNumOfFilteredVocs,
                    GenericString(Strings.NumOfFilteredVocs_pre),
                    GenericString(str = numOfFiltered.toString()),
                    GenericString(Strings.NumOfFilteredVocs_suf)
            )
    }

    // toggle (hide/show) the advanced-options-panel
    private fun toggleAdvancedOptionsPanel() {
        if (mShowingAdvancedOptionsPanel)
            children.removeAt(children.size - 1)
        else
            children.add(AdvancedOptionsPanel(this))
        mShowingAdvancedOptionsPanel = !mShowingAdvancedOptionsPanel
    }
}