package rekkursion.enumerate

@Suppress("unused")
enum class Strings(val chi: String, val eng: String) {
    // the title
    Title("Spanishing", "Spanishing"),

    // the button name of single choice problems
    SingleChoiceProblem("單選題型", "Single Choice Problem"),
    // the button name of spelling problems
    SpellingProblem("拼字題型", "Spelling Practice"),

    // the radio-button name of esp-to-chi-and-eng type of single choice problems
    EspToChiAndEng("給定西文，選擇中文/英文", "Given Español, choose Chinese/English"),
    // the radio-button name of chi-and-eng-to-esp type of single choice problems
    ChiAndEngToEsp("給定中文/英文，選擇西文", "Given Chinese/English, choose Español"),
    // the radio-button name of both types of single choice problems
    BothTypes("以上兩者混合", "Both of the above"),

    // the title for selecting the type of single choice problems
    SelectTypeOfSingleChoiceProblem("請選擇題目的類型：", "Please select the type of problems:"),
    // the title for selecting the number of problems
    SelectNumOfProblems("請選擇題目的數量：", "Please select the number of problems:"),

    // the title for showing tested vocabularies
    TestedVocs("本次練習的單字", "Tested vocabularies in this time"),
    // the title for showing results of tested vocabularies
    TestedResults("結果", "Results"),

    // the string of the increment sign
    Increment("+", "+"),
    // the string of the decrement sign
    Decrement("-", "-"),

    // the string of 'start'
    Start("開始", "Start"),
    // the string of 'back'
    Back("返回", "Back"),


    ;

    /* ======================================== */

    companion object {
        // get the string by this enumeration type
        fun get(strEnum: Strings): String = strEnum.chi
    }
}