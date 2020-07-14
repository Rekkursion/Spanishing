package rekkursion.model.problem

import rekkursion.model.Vocabulary

class SpellingProblem(stem: Vocabulary): Problem(stem) {
    override fun getStemStr(): String = mStem.toString()

    /* ======================================== */

    override fun copy(): SpellingProblem = SpellingProblem(mStem.copy())
}