package rekkursion.manager

import javafx.scene.paint.Color
import rekkursion.enumerate.Colors

object PropertiesManager {
    // the default width of the window
    const val defaultWindowWidth: Double = 1280.0

    // the default height of the window
    const val defaultWindowHeight: Double = 700.0

    // the default interface language
    const val defaultLang: String = "eng"

    // the default boolean value for determining using regex or not
    const val defaultUsingRegexOrNot: Boolean = false

    // the default boolean value for determining being case sensitive or not
    const val defaultCaseSensitiveOrNot: Boolean = true

    // the default bit values for determining texts searched on (7 = 111 = YYY, in the order of ESP|ENG|CHI)
    const val defaultTextsSearchOn: Int = 7

    // the default bit values for determining posp's searched on
    const val defaultPospsSearchOn: Int = Int.MAX_VALUE

    // the default boolean-value of the alert when skipping a single problem
    const val defaultAlertWhenSkipping: Boolean = true

    // the default boolean-value of the alert when finishing (skipping) a whole problem-set
    const val defaultAlertWhenFinishing: Boolean = true

    // the default single choice problem type
    const val defaultSingleChoiceProblemType: Int = 2

    // the size of a search-icon
    const val searchIconSize: Double = 24.0

    // the text-size using in a search-bar
    const val searchBarTextSize: Int = 14

    // the number of options in a single choice problem
    const val numOfOptionsInSingleChoiceProblem: Int = 4

    // the maximum number of problems per practice
    const val maxNumOfProblemsPerPractice = 150

    // the general spacing among uis
    const val generalSpacing: Double = 10.0

    // the general padding of a ui
    const val generalPadding: Double = 30.0

    // the list of percent widths of columns used in the result-list-view
    val percentWidthsOfColumnsInResultListView = doubleArrayOf(10.0, 70.0, 20.0)

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

    /* ======================================== */

    // the dedicated object for some default values of styled-nodes
    object Styled {
        // the default text-size
        const val defaultTextSize: Int = 18

        // the default text-color
        val defaultTextColor: Color = Colors.DEFAULT.color

        // the default background-color of text-fields
        val defaultTextFieldBgColor: Color = Color.rgb(100, 100, 100)

        // the default background-color of buttons
        val defaultButtonBgColor: Color = Color.rgb(30, 30, 30)

        // the default background-color of buttons when mouse-hovering it
        val defaultButtonHoveringBgColor: Color = Colors.FOCUSED_BTN_BG.color
    }
}