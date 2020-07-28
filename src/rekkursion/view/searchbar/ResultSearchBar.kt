package rekkursion.view.searchbar

import rekkursion.enumerate.Strings
import rekkursion.manager.PropertiesManager
import rekkursion.view.styled.Styled
import rekkursion.view.styled.StyledCheckBox

class ResultSearchBar: SearchBar() {
    // the check-box for showing correctly-answered results or not
    private val mCkbShowCorrect = StyledCheckBox(Strings.Correct)

    // the check-box for showing wrongly-answered results or not
    private val mCkbShowWrong = StyledCheckBox(Strings.Wrong)

    // the check-box for showing no-answered results or not
    private val mCkbShowNoAnswered = StyledCheckBox(Strings.NoAnswered)

    init {
        // unify the text-sizes
        Styled.unifyTextSize(PropertiesManager.searchBarTextSize, mCkbShowCorrect, mCkbShowWrong, mCkbShowNoAnswered)

        // set the is-selected statuses
        mCkbShowCorrect.isSelected = true
        mCkbShowWrong.isSelected = true
        mCkbShowNoAnswered.isSelected = true

        // add these check-boxes into the basic search-bar
        pushNodesAtFirstRow(mCkbShowCorrect, mCkbShowWrong, mCkbShowNoAnswered)

        // show only collected vocabularies
        mCkbShowCorrect.selectedProperty().addListener { _, _, newValue ->
            // TODO
        }
    }
}