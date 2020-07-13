package rekkursion.enumerate

import javafx.scene.control.*
import javafx.scene.text.Text
import javafx.stage.Stage
import rekkursion.manager.PreferenceManager
import rekkursion.view.pref.PreferenceField

@Suppress("unused", "EnumEntryName")
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

    // the title for showing numbers per tested vocabularies
    TestedNo("題號", "No."),
    // the title for showing tested vocabularies
    TestedVocs("本次練習的單字", "Tested vocabularies in this time"),
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

    // the string of 'start'
    Start("開始", "Start"),
    // the string of 'back'
    Back("返回", "Back"),

    ;

    /* ======================================== */

    companion object {
        // the hash-map of registered views
        private val mRegisteredHashMap = hashMapOf<Any, Strings>()

        // get the string by this enumeration type
        fun get(strEnum: Strings): String = if (PreferenceManager.lang == "英文/English")
            strEnum.eng
        else strEnum.chi

        // register a node
        fun register(any: Any, strEnum: Strings) { mRegisteredHashMap[any] = strEnum }

        // unregister a node
        fun unregister(any: Any) { mRegisteredHashMap.remove(any) }

        // notify all registered nodes to be updated
        fun notifyAllRegistered() {
            mRegisteredHashMap.forEach { (any, strEnum) ->
                (any as? Stage)?.title = get(strEnum)
                (any as? RadioButton)?.text = get(strEnum)
                (any as? Label)?.text = get(strEnum)
                (any as? Text)?.text = get(strEnum)
                (any as? TextField)?.text = get(strEnum)
                (any as? TextArea)?.text = get(strEnum)
                (any as? Button)?.text = get(strEnum)
                (any as? ComboBox<*>)?.promptText = get(strEnum)
                (any as? Spinner<*>)?.promptText = get(strEnum)
                (any as? PreferenceField)?.setFieldName(get(strEnum))
            }
        }
    }
}