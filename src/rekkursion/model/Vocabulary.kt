package rekkursion.model

class Vocabulary(esp: String, vararg meanings: Meaning): Copiable {
    // the vocabulary in the form of spanish (español)
    private val mEsp = esp
    val esp get() = mEsp

    // the list of meanings of this vocabulary
    private val mMeaningList: ArrayList<Meaning> = arrayListOf()
    @Suppress("UNCHECKED_CAST")
    val meaningList: ArrayList<Meaning> get() = mMeaningList.clone() as ArrayList<Meaning>

    // initialize for some members
    init {
        // add all passed (and copied) meanings into the list of meanings
        meanings.forEach { mMeaningList.add(it.copy()) }
    }

    /* ======================================== */

    // copy a new vocabulary
    @Suppress("UNCHECKED_CAST")
    override fun copy(): Vocabulary = Vocabulary(mEsp, *(mMeaningList.clone() as ArrayList<Meaning>).toTypedArray())

    /* ======================================== */

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vocabulary

        if (mEsp != other.mEsp) return false
        if (mMeaningList != other.mMeaningList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mEsp.hashCode()
        result = 31 * result + mMeaningList.hashCode()
        return result
    }

    override fun toString(): String = mEsp
}