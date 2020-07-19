package rekkursion.model.problem

import rekkursion.enumerate.SingleChoiceProblemType
import rekkursion.model.Vocabulary

class SingleChoiceProblem(index: Int, problemType: SingleChoiceProblemType, stem: Vocabulary, options: ArrayList<Vocabulary>)
    : Problem(index, stem) {

    // the type of this single choice problem
    private val mProblemType = problemType

    // the list of vocabularies of this problem's options
    private val mOptionList = arrayListOf(*(options.toTypedArray().clone()))

    // the position of the correct answer of this problem
    private val mCorrectAnsPos: Int = mOptionList.indexOf(mStem)
    val correctAnsPos get() = mCorrectAnsPos

    /* ======================================== */

    @Suppress("UNCHECKED_CAST")
    override fun copy(): SingleChoiceProblem = SingleChoiceProblem(mIndex, mProblemType, mStem.copy(), mOptionList.clone() as ArrayList<Vocabulary>)

    /* ======================================== */

    // get the string of a certain option determined by the passed position
    fun getOptionStr(pos: Int): String = if (mProblemType == SingleChoiceProblemType.CHI_AND_ENG_TO_ESP)
        mOptionList[pos].esp
    else mOptionList[pos].copiedMeaning.toString()

    // get the string of the stem vocabulary
    override fun getStemStr(): String = if (mProblemType == SingleChoiceProblemType.CHI_AND_ENG_TO_ESP)
        "[${mStem.copiedMeaning.posp.abbr}] ${mStem.copiedMeaning.chi} (${mStem.copiedMeaning.eng})"
    else
        mStem.esp

    /* ======================================== */

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SingleChoiceProblem

        if (mProblemType != other.mProblemType) return false
        if (mOptionList.size != other.mOptionList.size) return false
        mOptionList.forEachIndexed { idx, voc ->
            if (voc != other.mOptionList[idx])
                return false
        }
        if (mCorrectAnsPos != other.mCorrectAnsPos) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mProblemType.hashCode()
        result = 31 * result + mOptionList.size.hashCode()
        mOptionList.forEach { result = 31 * result + it.hashCode() }
        result = 31 * result + mCorrectAnsPos
        return result
    }
}