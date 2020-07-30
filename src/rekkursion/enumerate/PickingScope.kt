package rekkursion.enumerate

import rekkursion.manager.VocManager

enum class PickingScope {
    ALL_VOCS,
    COLLECTED_ONLY;

    // get the number of available vocabularies
    fun getNumOfVocs(): Int = if (this == ALL_VOCS) VocManager.numOfVocabularies else VocManager.copiedCollectedList.size
}