package rekkursion.manager

object PropertiesManager {
    // the default width of the window
    const val defaultWindowWidth: Double = 1180.0

    // the default height of the window
    const val defaultWindowHeight: Double = 660.0

    // the default interface language
    const val defaultLang: String = "eng"

    // the general spacing among uis
    const val generalSpacing: Double = 10.0

    // the location of the json file of vocabularies
    const val vocabulariesJsonFileLocation: String = "D:\\rekkursion\\mooc\\spanish\\spanish_vocabularies.json"

    // the list of menu features
    val menuList: Array<String> = arrayOf(
            "Vocabulary List",
            "Practice",
            "Preferences"
    )
}