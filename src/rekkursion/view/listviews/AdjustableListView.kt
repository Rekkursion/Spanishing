package rekkursion.view.listviews

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.ListView
import rekkursion.model.Adjustable
import rekkursion.util.searchopts.SearchOptions

abstract class AdjustableListView<T: Adjustable>(list: ArrayList<T>): ListView<T>() {
    // the observable-array-list of items
    protected val mList: ObservableList<T> = FXCollections.observableArrayList(list)

    /* ======================================== */

    // filter the vocabularies by a string (w/ some options like using regex or not, case sensitive or not)
    open fun filter(str: String, searchOptions: SearchOptions): Int {
        items.clear()
        items.addAll(mList.filter { it.filterFrom(str, searchOptions) })
        return items.size
    }
}