package rekkursion.model

import rekkursion.enumerate.SingleChoiceProblemType

class Problem(problemType: SingleChoiceProblemType, stem: Vocabulary, options: ArrayList<Vocabulary>): Copiable {
    // the type of this single choice problem
    private val mProblemType = problemType

    // the vocabulary of this problem's stem
    private val mStem = stem

    // the list of vocabularies of this problem's options
    private val mOptionList = arrayListOf(*(options.toTypedArray().clone()))

    // the position of the correct answer of this problem
    private val mCorrectAnsPos: Int = mOptionList.indexOf(mStem)
    val correctAnsPos = mCorrectAnsPos

    /* ======================================== */

    // get the string of the stem
    fun getStem(): String = if (mProblemType == SingleChoiceProblemType.CHI_AND_ENG_TO_ESP)
            "${mStem.meaningList[0].chi} (${mStem.meaningList[0].eng})"
        else
            mStem.esp

    // get the string of a certain option determined by the passed position
    fun getOption(pos: Int): String = if (mProblemType == SingleChoiceProblemType.CHI_AND_ENG_TO_ESP)
        mOptionList[pos].esp
    else
        "${mOptionList[pos].meaningList[0].chi} (${mOptionList[pos].meaningList[0].eng})"

    /* ======================================== */

    @Suppress("UNCHECKED_CAST")
    override fun copy(): Problem = Problem(mProblemType, mStem.copy(), mOptionList.clone() as ArrayList<Vocabulary>)

    /* ======================================== */

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Problem

        if (mProblemType != other.mProblemType) return false
        if (mStem != other.mStem) return false
        if (mCorrectAnsPos != other.mCorrectAnsPos) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mProblemType.hashCode()
        result = 31 * result + mStem.hashCode()
        result = 31 * result + mCorrectAnsPos
        return result
    }

    override fun toString(): String = mStem.esp
}