//package rekkursion.util.searchopts
//
//class ResultSearchOptions(usingRegex: Boolean,
//                          caseSensitive: Boolean,
//                          showCorrectRes: Boolean = true,
//                          showWrongRes: Boolean = true,
//                          showNoAnsweredRes: Boolean = true): SearchOptions(usingRegex, caseSensitive) {
//
//    // show the correctly-answered result or not
//    private var mShowCorrect: Boolean = showCorrectRes
//    var showCorrectResult
//        get() = mShowCorrect
//        set(value) { mShowCorrect = value }
//
//    // show the wrongly-answered result or not
//    private var mShowWrong: Boolean = showWrongRes
//    var showWrongResult
//        get() = mShowWrong
//        set(value) { mShowWrong = value }
//
//    // show the no-answered result or not
//    private var mShowNoAnswered: Boolean = showNoAnsweredRes
//    var showNoAnsweredResult
//        get() = mShowNoAnswered
//        set(value) { mShowNoAnswered = value }
//
//    /* ======================================== */
//
//    override fun setAll(searchOptions: SearchOptions) {
//        super.setAll(searchOptions)
//        if (searchOptions is ResultSearchOptions) {
//            mShowCorrect = searchOptions.mShowCorrect
//            mShowWrong = searchOptions.mShowWrong
//            mShowNoAnswered = searchOptions.mShowNoAnswered
//        }
//    }
//
//    override fun copy(): ResultSearchOptions = ResultSearchOptions(
//            usingRegex,
//            caseSensitive,
//            mShowCorrect,
//            mShowWrong,
//            mShowNoAnswered
//    )
//}