package rekkursion.model

import rekkursion.util.searchopts.SearchOptions

interface Adjustable {
    fun filterFrom(str: String, searchOptions: SearchOptions): Boolean
}