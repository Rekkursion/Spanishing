package rekkursion.manager

import rekkursion.model.Vocabulary
import rekkursion.util.JsonReader

object VocManager {
    // read in all vocabularies from a json file
    private val mVocList = JsonReader.readAllVocabularies(PropertiesManager.vocabulariesJsonFileLocation)
    @Suppress("UNCHECKED_CAST")
    val copiedVocList: ArrayList<Vocabulary> get() = mVocList.clone() as ArrayList<Vocabulary>
    val numOfVocabularies: Int get() = mVocList.size
}