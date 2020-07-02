package rekkursion.manager

object PropertiesManager {

    // the width of the window
    const val windowWidth: Double = 960.0

    // the height of the window
    const val windowHeight: Double = 660.0

    // the general spacing among uis
    const val generalSpacing: Double = 10.0

    // the location of the json file of vocabularies
    const val vocabulariesJsonFileLocation: kotlin.String = "D:\\rekkursion\\mooc\\spanish\\spanish_vocabularies.json"

    /* ======================================== */

    // the dedicated object for storing constant strings
    object Strings {
        // the title
        const val title: String = "Spanishing"

        // the list of menu features
        val menuList: Array<String> = arrayOf("Vocabulary List", "Practice")

        // the button name of single choice problems
        const val singleChoiceProblem = "Single Choice Problem"

        // the button name of spelling problems
        const val spellingProblem = "Spelling Practice"
    }
}