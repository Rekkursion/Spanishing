package rekkursion.model.problem

import rekkursion.enumerate.AnsResult
import rekkursion.model.Adjustable
import rekkursion.model.Copiable
import rekkursion.model.Vocabulary
import rekkursion.util.searchopts.SearchOptions
import java.util.regex.PatternSyntaxException

abstract class Problem(index: Int, stem: Vocabulary): Copiable, Adjustable<Problem> {
    // the index of this problem
    protected val mIndex = index
    val index get() = mIndex

    // the vocabulary of this problem's stem
    protected val mStem = stem.copy()
    var isCollected get() = mStem.isCollected; set(value) { mStem.isCollected = value }

    // the result of answering this single choice problem
    private var mAnsResult = AnsResult.NO_ANSWERED
    var ansResult
        get() = mAnsResult
        set(value) { mAnsResult = value }

    /* ======================================== */

    // convert the problem-object into a vocabulary-object
    fun toVoc(): Vocabulary = mStem.copy()

    // get the string of the stem vocabulary
    abstract fun getStemStr(): String

    override fun filterFrom(str: String, searchOptions: SearchOptions): Boolean {
        if (str.isNotEmpty()) {
            val usingRegex = searchOptions.usingRegex
            val caseSensitive = searchOptions.caseSensitive
            try {
                // the passed string shall be used as a regex
                if (usingRegex) (if (!caseSensitive) str.toLowerCase().toRegex() else str.toRegex()).let { regex ->
                    return mStem.esp.contains(regex) || mStem.copiedMeaning.eng.contains(regex) || mStem.copiedMeaning.chi.contains(regex)
                }
                // the plain text
                else return mStem.esp.contains(str) || mStem.copiedMeaning.eng.contains(str) || mStem.copiedMeaning.chi.contains(str)
            } catch (e: PatternSyntaxException) { return false }
        }
        return true
    }

    /* ======================================== */

    override fun toString(): String = mStem.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Problem) return false

        if (mStem != other.mStem) return false
        if (mAnsResult != other.mAnsResult) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mStem.hashCode()
        result = 31 * result + mAnsResult.hashCode()
        return result
    }

}