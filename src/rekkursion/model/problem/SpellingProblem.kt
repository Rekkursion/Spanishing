package rekkursion.model.problem

import rekkursion.model.Vocabulary

class SpellingProblem(stem: Vocabulary): Problem(stem) {
    override fun getStemStr(): String = mStem.copiedMeaning.toString()

    fun getEsp(): String = mStem.esp

    /* ======================================== */

    override fun copy(): SpellingProblem = SpellingProblem(mStem.copy())
}