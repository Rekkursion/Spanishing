package rekkursion.util

// some options for searching
class SearchOptions(usingRegex: Boolean, caseSensitive: Boolean) {
    // using regex or not
    private var mUsingRegex = usingRegex
    var usingRegex
        get() = mUsingRegex
        set(value) { mUsingRegex = value }

    // being case-sensitive or not
    private var mCaseSensitive = caseSensitive
    var caseSensitive
        get() = mCaseSensitive
        set(value) { mCaseSensitive = value }
}