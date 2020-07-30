package rekkursion.manager

import rekkursion.enumerate.PickingScope
import rekkursion.model.Vocabulary
import rekkursion.util.VocIO

@Suppress("UNCHECKED_CAST")
object VocManager {
    // read in all vocabularies from a json file
    private val mVocList = VocIO.readAllVocabularies()
    val copiedVocList: ArrayList<Vocabulary> get() = mVocList.clone() as ArrayList<Vocabulary>
    val numOfVocabularies: Int get() = mVocList.size
    val copiedCollectedList: ArrayList<Vocabulary> get() = mVocList.filter { it.isCollected }.toCollection(arrayListOf()).clone() as ArrayList<Vocabulary>

    /* ======================================== */

    // non-uniformly pick some vocabularies from the list
    fun pickVocabularies(num: Int, scope: PickingScope): Array<Vocabulary> {
        // get the true number of to-be-picked vocabularies
        val trueNum = when {
            num > numOfVocabularies -> numOfVocabularies
            num < 0 -> 0
            else -> num
        }

        // the number of currently-generated problems
        var curNum = 0

        // the hash-set of picked vocabularies
        val pickedVocHashSet = HashSet<Vocabulary>()

        // randomly pick some vocabularies as problems
        while (curNum < trueNum) {
            val voc = PickingPriorityManager.pickVoc(scope)
            // if this index has not been picked
            if (!pickedVocHashSet.contains(voc)) {
                ++curNum
                pickedVocHashSet.add(voc)
            }
        }

        // convert the built hash-set into an array and return it
        return pickedVocHashSet.shuffled().toTypedArray()
    }

    // collect a certain vocabulary
    fun collectOrUncollect(voc: Vocabulary, isCollecting: Boolean) {
        mVocList.find { it == voc }?.let {
            if (isCollecting) {
                if (!it.isCollected) {
                    it.isCollected = true
                    VocIO.collectOrUncollectCertainVocabulary(it.esp, isCollecting)
                }
            } else {
                if (it.isCollected) {
                    it.isCollected = false
                    VocIO.collectOrUncollectCertainVocabulary(it.esp, isCollecting)
                }
            }
        }
    }
}