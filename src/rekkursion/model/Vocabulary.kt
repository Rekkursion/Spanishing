package rekkursion.model

import rekkursion.util.searchopts.SearchOptions
import java.util.regex.PatternSyntaxException

class Vocabulary(esp: String, meaning: Meaning, isCollected: Boolean = false): Copiable, Adjustable {
    // the vocabulary in the form of spanish (español)
    private val mEsp = esp
    val esp get() = mEsp

    // the meaning of this vocabulary
    private val mMeaning = meaning
    val copiedMeaning get() = mMeaning.copy()

    // if this vocabulary is collected or not
    private var mIsCollected = isCollected
    var isCollected
        get() = mIsCollected
        set(value) { mIsCollected = value }

    /* ======================================== */

    // copy a new vocabulary
    override fun copy(): Vocabulary = Vocabulary(mEsp, mMeaning.copy())

    // filter the vocabularies by a string (w/ some options like using regex or not, case sensitive or not)
    override fun filterFrom(str: String, searchOptions: SearchOptions): Boolean {
        val usingRegex = searchOptions.usingRegex
        val caseSensitive = searchOptions.caseSensitive
        try {
            // the passed string shall be used as a regex
            if (usingRegex) (if (!caseSensitive) str.toLowerCase().toRegex() else str.toRegex()).let { regex ->
                return mEsp.contains(regex) || mMeaning.eng.contains(regex) || mMeaning.chi.contains(regex)
            }
            // the plain text
            else return mEsp == str || mMeaning.eng == str || mMeaning.chi == str
        } catch (e: PatternSyntaxException) { return false }
    }

    /* ======================================== */

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vocabulary

        if (mEsp != other.mEsp) return false
        if (mMeaning != other.mMeaning) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mEsp.hashCode()
        result = 31 * result + mMeaning.hashCode()
        return result
    }

    override fun toString(): String = "$mEsp $mMeaning"
}