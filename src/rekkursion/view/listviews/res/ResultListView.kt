package rekkursion.view.listviews.res

import rekkursion.model.problem.Problem
import rekkursion.util.searchopts.SearchOptions
import rekkursion.view.listviews.AdjustableListView

class ResultListView(problemList: ArrayList<Problem>): AdjustableListView<Problem>(problemList) {
    init {
        // add all vocabularies from the list
        items.addAll(problemList)

        // set the cell factory
        setCellFactory { ResultListCell(problemList.size) }
    }

    /* ======================================== */

    // filter the result
    override fun filter(str: String, searchOptions: SearchOptions): Int {
        super.filter(str, searchOptions)
        searchOptions.resComp?.let { resComp ->
            items.removeIf {
                (!resComp.showCorrectResults && it.ansResult.isCorrect()) ||
                        (!resComp.showWrongResults && it.ansResult.isWrong()) ||
                        (!resComp.showNoAnsweredResults && it.ansResult.isNoAnswered())
            }
        }
        return items.size
    }
}