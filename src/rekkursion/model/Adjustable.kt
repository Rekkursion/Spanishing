package rekkursion.model

import rekkursion.util.searchopts.SearchOptions

interface Adjustable<T> {
    fun filterFrom(str: String, searchOptions: SearchOptions): Boolean
}