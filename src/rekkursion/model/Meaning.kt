package rekkursion.model

class Meaning(chi: String, eng: String, posp: PartOfSpeech): Copiable {
    // the chinese meaning
    private val mChi = chi
    val chi get() = mChi

    // the english meaning
    private val mEng = eng
    val eng get() = mEng

    // the part of speech
    private val mPosp = posp
    val posp get() = mPosp

    /* ======================================== */

    // copy a new meaning
    override fun copy(): Meaning = Meaning(mChi, mEng, mPosp)

    /* ======================================== */

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Meaning

        if (mChi != other.mChi) return false
        if (mEng != other.mEng) return false
        if (mPosp != other.mPosp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mChi.hashCode()
        result = 31 * result + mEng.hashCode()
        result = 31 * result + mPosp.hashCode()
        return result
    }

    override fun toString(): String = "$mChi ($mEng) [${mPosp.abbr}]"
}