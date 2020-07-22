package rekkursion.util

import rekkursion.enumerate.PartOfSpeech
import rekkursion.model.Copiable

// some options for searching
class SearchOptions(
        usingRegex: Boolean,
        caseSensitive: Boolean,
        searchTextOn: Int,
        searchPosp: Int)
    : Copiable {

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

    // search part-of-speeches
    private var mSearchPosp: Int = searchPosp

    /* ======================================== */

    // set all search-options by another search-options
    fun setAll(searchOptions: SearchOptions) {
        usingRegex = searchOptions.usingRegex
        caseSensitive = searchOptions.mCaseSensitive
        mSearchTextOn = searchOptions.mSearchTextOn
        mSearchPosp = searchOptions.mSearchPosp
    }

    // add part-of-speeches
    fun addPosps(vararg posps: PartOfSpeech) {
        posps.forEach { posp -> mSearchPosp = mSearchPosp.or(2.pow(posp.ordinal)) }
    }

    // drop part-of-speeches
    fun dropPosps(vararg posps: PartOfSpeech) {
        posps.forEach { posp -> mSearchPosp = mSearchPosp.and(2.pow(posp.ordinal).inv()) }
    }

    // check if it should search a certain part-of-speech
    fun isSearchingCertainPosp(posp: PartOfSpeech): Boolean {
        posp.abbr.split("(;\\s*)|\\/".toRegex())
                .map { atomAbbr -> PartOfSpeech.getPospFromAbbr(atomAbbr) }
                .forEach { atom ->
                    if (mSearchPosp.shr(atom.ordinal).and(1) == 1)
                        return true
                }
        return false
    }

    /* ======================================== */

    override fun copy(): SearchOptions = SearchOptions(usingRegex, caseSensitive, mSearchTextOn, mSearchPosp)
}