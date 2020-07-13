package rekkursion.enumerate

import javafx.scene.control.*
import javafx.scene.text.Text
import javafx.stage.Stage
import rekkursion.manager.PreferenceManager
import rekkursion.util.GenericString
import rekkursion.view.pref.PreferenceField

@Suppress("unused", "EnumEntryName")
enum class Strings(val chi: String, val eng: String) {
    // the title
    Title("Spanishing", "Spanishing"),

    // the prefix of showing the number of vocabularies
    NumberOfVocs_pre("目前共有 ", "Total number of vocabularies: "),
    NumberOfVocs_suf(" 個單詞。", ""),

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

    // the title for showing numbers per tested vocabularies
    TestedNo("題號", "No."),
    // the title for showing tested vocabularies
    TestedVocs("本次練習的單字", "Tested vocabularies"),
    // the title for showing results of tested vocabularies
    TestedResults("結果", "Results"),

    // the string of the increment sign
    Increment("+", "+"),
    // the string of the decrement sign
    Decrement("-", "-"),

    // pref: the interface language
    InterfaceLang("介面語言", "Interface Language"),
    // pref: chinese as the interface language
    InterfaceLang_Chi("中文/Chinese", "中文/Chinese"),
    // pref: english as the interface language
    InterfaceLang_Eng("英文/English", "英文/English"),

    // used when the answer was correct
    Correct("正確", "Correct"),
    // used when the answer was wrong
    Wrong("錯誤", "Wrong"),
    // used when the problem has not been answered
    NoAnswer("未作答", "No Answer"),

    // the string of 'start'
    Start("開始", "Start"),
    // the string of 'back'
    Back("返回", "Back"),

    // the empty string
    Empty("", "")
    ;

    /* ======================================== */

    companion object {
        // the hash-map of registered views
        private val mRegisteredHashMap = hashMapOf<Any, GenericString.Array>()

        // get the string by this enumeration type
        fun get(strEnum: Strings): String = if (PreferenceManager.lang == "英文/English")
            strEnum.eng
        else strEnum.chi

        // register a node
        fun register(any: Any, strEnum: Strings) { mRegisteredHashMap[any] = GenericString.Array(GenericString(strEnum)) }

        // register a node w/ a sequence of literal strings or str-enums
        fun register(any: Any, vararg strs: GenericString) { mRegisteredHashMap[any] = GenericString.Array(*strs) }

        // unregister a node
        fun unregister(any: Any) { mRegisteredHashMap.remove(any) }

        // notify all registered nodes to be updated
        fun notifyAllRegistered() {
            mRegisteredHashMap.forEach { (any, strs) ->
                val joined = strs.toString()

                (any as? Stage)?.title = joined
                (any as? RadioButton)?.text = joined
                (any as? Label)?.text = joined
                (any as? Text)?.text = joined
                (any as? TextField)?.text = joined
                (any as? TextArea)?.text = joined
                (any as? Button)?.text = joined
                (any as? ComboBox<*>)?.promptText = joined
                (any as? Spinner<*>)?.promptText = joined
                (any as? PreferenceField)?.setFieldName(joined)
            }
        }
    }
}