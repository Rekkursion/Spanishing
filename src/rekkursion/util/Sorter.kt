package rekkursion.util

object Sorter {
    fun <T> sortAsc(list: ArrayList<T>, compareBy: Comparator<T>) {
        list.sortWith(compareBy)
    }

    fun <T> sortDesc(list: ArrayList<T>, compareBy: Comparator<T>) {
        sortAsc(list, compareBy)
        list.reverse()
    }
}