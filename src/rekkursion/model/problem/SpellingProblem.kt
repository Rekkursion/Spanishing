package rekkursion.model.problem

import rekkursion.model.Vocabulary

class SpellingProblem(index: Int, stem: Vocabulary): Problem(index, stem) {
    override fun getStemStr(): String = mStem.copiedMeaning.toString()

    fun getEsp(): String = mStem.esp

    /* ======================================== */

    override fun copy(): SpellingProblem = SpellingProblem(mIndex, mStem.copy())
}