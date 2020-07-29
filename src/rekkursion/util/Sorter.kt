package rekkursion.util

import javafx.collections.ObservableList

object Sorter {
    fun <T> sortAsc(list: ObservableList<T>, compareBy: Comparator<T>) {
        list.sortWith(compareBy)
    }

    fun <T> sortDesc(list: ObservableList<T>, compareBy: Comparator<T>) {
        sortAsc(list, compareBy)
        list.reverse()
    }
}