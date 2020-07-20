package rekkursion.manager

import rekkursion.enumerate.SingleChoiceProblemType

object PropertiesManager {
    // the default width of the window
    const val defaultWindowWidth: Double = 1280.0

    // the default height of the window
    const val defaultWindowHeight: Double = 700.0

    // the default interface language
    const val defaultLang: String = "eng"

    // the default boolean-value of the alert when skipping a single problem
    const val defaultAlertWhenSkipping: Boolean = true

    // the default boolean-value of the alert when finishing (skipping) a whole problem-set
    const val defaultAlertWhenFinishing: Boolean = true

    // the default single choice problem type
    const val defaultSingleChoiceProblemType: Int = 2

    // the number of options in a single choice problem
    const val numOfOptionsInSingleChoiceProblem: Int = 4

    // the general spacing among uis
    const val generalSpacing: Double = 10.0

    // the general padding of a ui
    const val generalPadding: Double = 30.0

    // the list of percent widths of columns used in the result-list-view
    val percentWidthsOfColumnsInResultListView = doubleArrayOf(10.0, 70.0, 20.0)

    // the location of the json file of vocabularies
    const val vocabulariesJsonFileLocation: String = "D:\\rekkursion\\mooc\\spanish\\spanish_vocabularies.json"

    // words that is no need to be spelled in a spelling problem
    val nonSpelledWords = hashSetOf("el", "la", "los", "las", "de", "a", "y", "en")

    // the width of a character in the spelling-text-field
    const val widthOfChar: Double = 22.0

    // the list of menu features
    val menuList: Array<String> = arrayOf(
            "Vocabulary List",
            "Practice",
            "Preferences"
    )
}