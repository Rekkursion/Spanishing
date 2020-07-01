package rekkursion.view

import javafx.scene.control.ListView
import rekkursion.model.Vocabulary

class VocabularyListView(vocList: ArrayList<Vocabulary>): ListView<Vocabulary>() {
    init {
        // add all vocabularies from the list
        items.addAll(vocList)

        // set the cell factory
        setCellFactory { VocabularyListCell() }
        stylesheets.add("rekkursion/css/vocabulary_list_view.css")
    }
}