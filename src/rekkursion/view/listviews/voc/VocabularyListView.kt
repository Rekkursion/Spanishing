package rekkursion.view.listviews.voc

import rekkursion.model.Vocabulary
import rekkursion.util.searchopts.SearchOptions
import rekkursion.view.listviews.AdjustableListView
import java.util.regex.PatternSyntaxException

class VocabularyListView(
        vocList: ArrayList<Vocabulary>,
        onCollectionStatusChangeListener: VocabularyListCell.OnCollectionStatusChangeListener? = null)
    : AdjustableListView<Vocabulary>(vocList) {

    init {
        items.addAll(mList)

        // set the cell factory
        setCellFactory { VocabularyListCell(onCollectionStatusChangeListener) }

        // add the stylesheet of the vocabulary-list-view
        stylesheets.add("rekkursion/css/vocabulary_list_view.css")
    }

    /* ======================================== */

    // filter the vocabularies by a string (w/ some options like using regex or not, case sensitive or not)
    override fun filter(str: String, searchOptions: SearchOptions): Int {
        searchOptions.vocComp?.let { vocComp ->
            val usingRegex = searchOptions.usingRegex
            val caseSensitive = searchOptions.caseSensitive
            val onEsp = vocComp.isSearchingOnESP
            val onEng = vocComp.isSearchingOnENG
            val onChi = vocComp.isSearchingOnCHI
            val collectedOnly = vocComp.isCollectedOnly

            items.clear()
            try {
                // the passed string shall be used as a regex
                if (usingRegex)
                    (if (!caseSensitive) str.toLowerCase().toRegex() else str.toRegex()).let { regex ->
                        items.addAll(mList.filter {
                            (if (!caseSensitive)
                                vocComp.isSearchingCertainPosp(it.copiedMeaning.posp) && (
                                        (onEsp && it.esp.toLowerCase().contains(regex)) ||
                                                (onEng && it.copiedMeaning.eng.toLowerCase().contains(regex)) ||
                                                (onChi && it.copiedMeaning.chi.toLowerCase().contains(regex)))
                            else
                                vocComp.isSearchingCertainPosp(it.copiedMeaning.posp) && (
                                        (onEsp && it.esp.contains(regex)) ||
                                                (onEng && it.copiedMeaning.eng.contains(regex)) ||
                                                (onChi && it.copiedMeaning.chi.contains(regex)))

                                    ) && (!collectedOnly || (collectedOnly && it.isCollected))
                        })
                    }
                // the plain text
                else
                    items.addAll(mList.filter {
                        (vocComp.isSearchingCertainPosp(it.copiedMeaning.posp) && (
                                (onEsp && it.esp.contains(str, !caseSensitive)) ||
                                        (onEng && it.copiedMeaning.eng.contains(str, !caseSensitive)) ||
                                        (onChi && it.copiedMeaning.chi.contains(str, !caseSensitive)))

                                ) && (!collectedOnly || (collectedOnly && it.isCollected))
                    })
            } catch (e: PatternSyntaxException) { return 0 }
        }

        // return the number of filtered vocabularies
        return items.size
    }
}