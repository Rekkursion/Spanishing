package rekkursion.model

class Vocabulary(esp: String, meaning: Meaning, isCollected: Boolean = false): Copiable {
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