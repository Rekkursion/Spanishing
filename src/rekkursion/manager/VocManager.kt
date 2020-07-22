package rekkursion.manager

import rekkursion.model.Vocabulary
import rekkursion.util.JsonReader
import kotlin.random.Random

object VocManager {
    // read in all vocabularies from a json file
    private val mVocList = JsonReader.readAllVocabularies(PropertiesManager.vocabulariesJsonFileLocation)
    @Suppress("UNCHECKED_CAST")
    val copiedVocList: ArrayList<Vocabulary> get() = mVocList.clone() as ArrayList<Vocabulary>
    val numOfVocabularies: Int get() = mVocList.size

    /* ======================================== */

    // non-uniformly pick some vocabularies from the list
    fun pickVocabularies(num: Int): Array<Vocabulary> {
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

        // the random generator i think?
        //val r = Random(System.currentTimeMillis())

        // randomly pick some vocabularies as problems
        while (curNum < trueNum) {
            val voc = PickingPriorityManager.pickVoc()
            // if this index has not been picked
            if (!pickedVocHashSet.contains(voc)) {
                ++curNum
                pickedVocHashSet.add(voc)
            }
        }

        // convert the built hash-set into an array and return it
        return pickedVocHashSet.toTypedArray()
    }
}