package rekkursion.view.listviews.voc

import javafx.collections.FXCollections
import javafx.scene.control.ListView
import rekkursion.model.Vocabulary
import rekkursion.util.SearchOptions
import java.util.regex.PatternSyntaxException

class VocabularyListView(vocList: ArrayList<Vocabulary>): ListView<Vocabulary>() {
    // the observable-array-list of the vocabularies
    private val mObservable = FXCollections.observableArrayList(vocList)

    init {
        items.addAll(mObservable)

        // set the cell factory
        setCellFactory { VocabularyListCell() }

        // add the stylesheet of the vocabulary-list-view
        stylesheets.add("rekkursion/css/vocabulary_list_view.css")
    }

    /* ======================================== */

    // filter the vocabularies by a string (w/ some options like using regex or not, case sensitive or not)
    fun filterByString(str: String, searchOptions: SearchOptions) {
        val usingRegex = searchOptions.usingRegex
        val caseSensitive = searchOptions.caseSensitive

        items.clear()
        try {
            // the passed string shall be used as a regex
            if (usingRegex)
                (if (!caseSensitive) str.toLowerCase().toRegex() else str.toRegex()).let { regex ->
                    items.addAll(mObservable.filter {
                        if (!caseSensitive)
                            it.esp.toLowerCase().contains(regex) ||
                                    it.copiedMeaning.chi.toLowerCase().contains(regex) ||
                                    it.copiedMeaning.eng.toLowerCase().contains(regex)
                        else
                            it.esp.contains(regex) ||
                                    it.copiedMeaning.chi.contains(regex) ||
                                    it.copiedMeaning.eng.contains(regex)
                    })
                }
            // the plain text
            else
                items.addAll(mObservable.filter {
                    it.esp.contains(str, !caseSensitive) ||
                            it.copiedMeaning.chi.contains(str, !caseSensitive) ||
                            it.copiedMeaning.eng.contains(str, !caseSensitive)
                })
        } catch (e: PatternSyntaxException) {}
    }
}