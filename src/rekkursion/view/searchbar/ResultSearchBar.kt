package rekkursion.view.searchbar

import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager
import rekkursion.util.searchopts.ResSearchComp
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
        // set the component which is responsible for searching results
        searchOpts.resComp = ResSearchComp()

        // unify the text-sizes
        Styled.unifyTextSize(PropertiesManager.searchBarTextSize, mCkbShowCorrect, mCkbShowWrong, mCkbShowNoAnswered)

        // set the is-selected statuses
        mCkbShowCorrect.isSelected = true
        mCkbShowWrong.isSelected = true
        mCkbShowNoAnswered.isSelected = true

        // add these check-boxes into the basic search-bar
        pushNodesAtFirstRow(mCkbShowCorrect, mCkbShowWrong, mCkbShowNoAnswered)

        // show only correctly-answered results
        mCkbShowCorrect.selectedProperty().addListener { _, _, newValue ->
            searchOpts.resComp?.showCorrectResults = newValue
            notifyOptionsChanged()
        }

        // show only wrongly-answered results
        mCkbShowWrong.selectedProperty().addListener { _, _, newValue ->
            searchOpts.resComp?.showWrongResults = newValue
            notifyOptionsChanged()
        }

        // show only no-answered results
        mCkbShowNoAnswered.selectedProperty().addListener { _, _, newValue ->
            searchOpts.resComp?.showNoAnsweredResults = newValue
            notifyOptionsChanged()
        }
    }
}