package rekkursion.view

import javafx.geometry.Pos
import rekkursion.enumerate.Colors
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.VocManager
import rekkursion.model.Vocabulary
import rekkursion.util.GenericString
import rekkursion.util.searchopts.SearchOptions
import rekkursion.view.listviews.voc.VocabularyListCell
import rekkursion.view.listviews.voc.VocabularyListView
import rekkursion.view.searchbar.SearchBar
import rekkursion.view.searchbar.VocSearchBar
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox

class VocabularyListPage: StyledVBox() {
    // the search-bar for searching vocabularies
    private val mSearchBar = VocSearchBar()

    // the view of the vocabulary list
    private val mVocListView = VocabularyListView(
            VocManager.copiedVocList,
            object: VocabularyListCell.OnCollectionStatusChangeListener {
                override fun onCollectingOrUncollecting(voc: Vocabulary, isCollecting: Boolean) {
                    registerLabelForShowingNumOfVocs()
                }
            }
    )

    // the label for showing the number of vocabularies
    private val mLblNumOfVocs = StyledLabel(textColor = Colors.NUMBERED.color)

    init {
        // set the pref-heights
        mVocListView.prefHeight = PreferenceManager.windowHeight
        prefHeight = PreferenceManager.windowHeight

        // bind the pref-width to the parent's width
        mLblNumOfVocs.prefWidthProperty().bind(widthProperty())
        // set the alignment
        mLblNumOfVocs.alignment = Pos.CENTER_RIGHT

        // register the strings of labels
        registerLabelForShowingNumOfVocs()

        // add all sub-views into this v-box
        children.addAll(mSearchBar, mVocListView, mLblNumOfVocs)

        // set the text-listening event on the search-bar
        mSearchBar.setOnSearchStatusChangeListener(object: SearchBar.OnSearchStatusChangeListener {
            override fun onSearchStatusChanged(searchBar: SearchBar, oldValue: String, newValue: String, searchOptions: SearchOptions) {
                mSearchBar.setNumOfFiltered(mVocListView.filter(newValue, searchOptions))
            }
        })
    }

    /* ======================================== */

    // register/re-register the label for showing the number of vocabularies
    private fun registerLabelForShowingNumOfVocs() {
        Thread {
            // register the strings of labels
            Strings.register(mLblNumOfVocs,
                    GenericString(Strings.NumberOfVocs_pre),
                    GenericString(str = VocManager.numOfVocabularies.toString()),
                    GenericString(Strings.NumberOfVocs_mid),
                    GenericString(str = VocManager.copiedCollectedList.size.toString()),
                    GenericString(Strings.NumberOfVocs_suf)
            )
        }.start()
    }

    override fun requestFocus() {
        super.requestFocus()
        mSearchBar.setNumOfFiltered(mVocListView.filter("", mSearchBar.searchOpts))
        registerLabelForShowingNumOfVocs()
    }
}