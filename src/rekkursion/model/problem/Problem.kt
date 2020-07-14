package rekkursion.model.problem

import rekkursion.enumerate.AnsResult
import rekkursion.model.Copiable
import rekkursion.model.Vocabulary

abstract class Problem(stem: Vocabulary): Copiable {
    // the vocabulary of this problem's stem
    protected val mStem = stem

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

    /* ======================================== */

    override fun toString(): String = mStem.esp

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