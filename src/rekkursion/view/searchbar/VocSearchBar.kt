package rekkursion.view.searchbar

import rekkursion.enumerate.Strings
import rekkursion.view.styled.StyledButton

class VocSearchBar: SearchBar() {
    // the button for adjusting advanced options for searching (filtering) vocabularies
    private val mBtnAdvancedOptions = StyledButton(Strings.AdvancedVocSearchOptions)

    init {
        mBtnAdvancedOptions.maxWidth = 100.0
        children.add(mBtnAdvancedOptions)
    }
}