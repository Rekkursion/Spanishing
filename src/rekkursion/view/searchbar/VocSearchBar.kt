package rekkursion.view.searchbar

import rekkursion.enumerate.Colors
import rekkursion.enumerate.Strings
import rekkursion.manager.PropertiesManager
import rekkursion.util.GenericString
import rekkursion.util.SearchOptions
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox

class VocSearchBar: SearchBar() {
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

        // add the advanced-options-button into the basic search-bar
        pushNodesAtFirstRow(mBtnAdvancedOptions, mLblNumOfFilteredVocs)

        // set the mouse-clicking event on advanced-options-button
        mBtnAdvancedOptions.setOnAction {
            toggleAdvancedOptionsPanel()
            requestFocus()
        }
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