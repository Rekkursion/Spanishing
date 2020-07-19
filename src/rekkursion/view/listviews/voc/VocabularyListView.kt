package rekkursion.view.listviews.voc

import javafx.scene.control.ListView
import rekkursion.model.Vocabulary

class VocabularyListView(vocList: ArrayList<Vocabulary>): ListView<Vocabulary>() {
    init {
        // add all vocabularies from the list
        items.addAll(vocList)

        // set the cell factory
        setCellFactory { VocabularyListCell() }

        // add the stylesheet of the vocabulary-list-view
        stylesheets.add("rekkursion/css/vocabulary_list_view.css")
    }
}