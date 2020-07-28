//package rekkursion.util.searchopts
//
//import rekkursion.manager.PreferenceManager
//
//class VocSearchOptions(usingRegex: Boolean,
//                       caseSensitive: Boolean,
//                       vocSearchComp: VocSearchComp =
//                               VocSearchComp(
//                                       PreferenceManager.textsSearchOn,
//                                       PreferenceManager.pospsSearchOn,
//                                       PreferenceManager.isCollectedOnly
//                               )): SearchOptions(usingRegex, caseSensitive) {
//
//    // the search-options for vocabularies
//    private var mVocSearchComp = vocSearchComp
//    var vocSearchComp
//        get() = mVocSearchComp
//        set(value) { mVocSearchComp = value }
//
//    /* ======================================== */
//
//    override fun setAll(searchOptions: SearchOptions) {
//        super.setAll(searchOptions)
//        if (searchOptions is VocSearchOptions) {
//            if (mVocSearchComp.searchTextOn != searchOptions.mVocSearchComp.searchTextOn)
//                PreferenceManager.write("texts-search-on", searchOptions.mVocSearchComp.searchTextOn.toString())
//            if (mVocSearchComp.searchPospOn != searchOptions.mVocSearchComp.searchPospOn)
//                PreferenceManager.write("posps-search-on", searchOptions.mVocSearchComp.searchPospOn.toString())
//            mVocSearchComp = searchOptions.vocSearchComp.copy()
//        }
//    }
//
//    override fun copy(): VocSearchOptions = VocSearchOptions(mUsingRegex, mCaseSensitive, mVocSearchComp.copy())
//}