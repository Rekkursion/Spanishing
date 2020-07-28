package rekkursion.util.searchopts

import rekkursion.model.Copiable

class ResSearchComp(
        showCorrectResults: Boolean = true,
        showWrongResults: Boolean = true,
        showNoAnsweredResults: Boolean = true): Copiable {

    // show the correctly-answered results or not
    private var mShowCorrectResults = showCorrectResults
    var showCorrectResults
        get() = mShowCorrectResults
        set(value) { mShowCorrectResults = value }

    // show the wrongly-answered results or not
    private var mShowWrongResults = showWrongResults
    var showWrongResults
        get() = mShowWrongResults
        set(value) { mShowWrongResults = value }

    // show the no-answered results or not
    private var mShowNoAnsweredResults = showNoAnsweredResults
    var showNoAnsweredResults
        get() = mShowNoAnsweredResults
        set(value) { mShowNoAnsweredResults = value }

    /* ======================================== */

    override fun copy(): ResSearchComp = ResSearchComp(mShowCorrectResults, mShowWrongResults, mShowNoAnsweredResults)
}