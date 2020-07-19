package rekkursion.view.listviews.res

import javafx.scene.control.ListView
import rekkursion.model.problem.Problem

class ResultListView(problemList: ArrayList<Problem>): ListView<Problem>() {
    init {
        // add all vocabularies from the list
        items.addAll(problemList)

        // set the cell factory
        setCellFactory { ResultListCell(problemList.size) }
    }
}