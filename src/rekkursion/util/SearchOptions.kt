package rekkursion.util

import rekkursion.model.Copiable

// some options for searching
class SearchOptions(usingRegex: Boolean, caseSensitive: Boolean, searchTextOn: Int): Copiable {
    // using regex or not
    private var mUsingRegex = usingRegex
    var usingRegex
        get() = mUsingRegex
        set(value) { mUsingRegex = value }

    // being case-sensitive or not
    private var mCaseSensitive = caseSensitive
    var caseSensitive
        get() = mCaseSensitive
        set(value) { mCaseSensitive = value }

    // search texts on ESP, ENG, and/or CHI
    // 0(000) = NNN, 1(001) = NNY, 2(010) = NYN, 3(011) = NYY, etc, in the order of ESP|ENG|CHI
    private var mSearchTextOn: Int = searchTextOn
    var isSearchingOnESP
        get() = mSearchTextOn.shr(2).and(1) == 1
        set(value) {
            mSearchTextOn = if (value) mSearchTextOn.or(4)
            else mSearchTextOn.and(4.inv())
        }
    var isSearchingOnENG
        get() = mSearchTextOn.shr(1).and(1) == 1
        set(value) {
            mSearchTextOn = if (value) mSearchTextOn.or(2)
            else mSearchTextOn.and(2.inv())
        }
    var isSearchingOnCHI
        get() = mSearchTextOn.and(1) == 1
        set(value) {
            mSearchTextOn = if (value) mSearchTextOn.or(1)
            else mSearchTextOn.and(1.inv())
        }

    /* ======================================== */

    // set all search-options by another search-options
    fun setAll(searchOptions: SearchOptions) {
        usingRegex = searchOptions.usingRegex
        caseSensitive = searchOptions.mCaseSensitive
        mSearchTextOn = searchOptions.mSearchTextOn
    }

    /* ======================================== */

    override fun copy(): SearchOptions = SearchOptions(usingRegex, caseSensitive, mSearchTextOn)
}