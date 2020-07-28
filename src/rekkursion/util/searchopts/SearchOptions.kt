package rekkursion.util.searchopts

import rekkursion.model.Copiable

// some options for searching
class SearchOptions(
        usingRegex: Boolean,
        caseSensitive: Boolean,
        vocComp: VocSearchComp? = null)
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

    // the component for dedicatedly searching vocabularies
    private var mVocComp: VocSearchComp? = vocComp
    var vocComp
        get() = mVocComp
        set(value) { mVocComp = value }

    /* ======================================== */

    override fun copy(): SearchOptions = SearchOptions(mUsingRegex, mCaseSensitive)
}