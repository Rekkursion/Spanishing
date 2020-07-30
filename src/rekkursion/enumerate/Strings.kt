package rekkursion.enumerate

import javafx.application.Platform
import javafx.scene.control.*
import javafx.scene.text.Text
import javafx.stage.Stage
import rekkursion.manager.PreferenceManager
import rekkursion.util.GenericString
import rekkursion.view.pref.PreferenceField

@Suppress("EnumEntryName")
enum class Strings(val chi: String, val eng: String) {
    // the title
    Title("Spanishing", "Spanishing"),

    // the text of the check-box for determining using regex or not in a search-bar
    UsingRegexOrNot("正則匹配", "Regex"),
    // the text of the check-box for determining being case-sensitive or not in a search-bar
    CaseSensitiveOrNot("大小寫敏感", "Case sensitive"),
    // the text of the check-box for showing only collected vocabularies
    ShowOnlyCollectedVocs("只顯示收藏", "Collected only"),
    // the text of the advanced options for searching (filtering) vocabularies
    AdvancedVocSearchOptions("進階選項", "Advanced options"),
    // the prefix of text for showing the number of filtered vocabularies
    NumOfFilteredVocs_pre("找到 ", "Num of filtered: "),
    // the suffix of text for showing the number of filtered vocabularies
    NumOfFilteredVocs_suf(" 個單字", ""),
    // the text of spanish
    Esp("西文", "ESP"),
    // the text of english
    Eng("英文", "ENG"),
    // the text of chinese
    Chi("中文", "CHI"),
    // the text of the field of searching texts
    AdvancedVocSearchOptions_searchingTexts("查找文字的對象", "Search on ESP, ENG, and/or CHI?"),
    // the text of the field of searching part-of-speeches
    AdvancedVocSearchOptions_searchingPosp("詞性過濾", "Part of speeches"),
    // the button text for selecting all items
    SelectAll("全選", "ALL"),
    // the button text for unselecting all items
    UnselectAll("全不選", "NONE"),
    // the button text for reversely selecting items
    ReverseSelect("反選", "REV"),

    // the prefix & suffix for showing the number of vocabularies
    NumberOfVocs_pre("目前共有 ", "Total number of vocabularies: "),
    NumberOfVocs_mid(" 個單詞。已收藏的有 ", ", including "),
    NumberOfVocs_suf(" 個。", " collected."),

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
    // the title for determining the scope for picking problems
    PickingScope("請選擇出題範圍：", "All vocabularies or collected only?"),
    // the name of the all-vocabularies radio-button when determining the picking scope
    PickingScope_All("全部單字", "All"),
    // the name of the collected-only radio-button when determining the picking scope
    PickingScope_CollectedOnly("只含已收藏單字", "Collected only"),
    // the prefix of text of all-scoped maximum vocabularies
    PickingScope_MaxNumOfVocs_All_pre("全部共有 ", "There\'re "),
    // the suffix of text of all-scoped maximum vocabularies
    PickingScope_MaxNumOfVocs_All_suf(" 個單字。", " vocabularies."),
    // the prefix of text of collected-scoped maximum vocabularies
    PickingScope_MaxNumOfVocs_CollectedOnly_pre("共有 ", "There\'re "),
    // the suffix of text of collected-scoped maximum vocabularies
    PickingScope_MaxNumOfVocs_CollectedOnly_suf(" 個已收藏單字。", " collected vocabularies."),

    // the button name of skipping a single problem
    SkipProblem("跳過這題 (Ctrl + Enter)", "Skip (Ctrl + Enter)"),
    // the button name of finishing a whole problem-set
    FinishProblemSet("結束作答 (Shift + Enter)", "Finish (Shift + Enter)"),
    // the alert title of the confirmation-type
    AlertConfirmationTitle("再次確認", "Confirmation"),
    // the alert header text of the confirmation-type
    AlertConfirmationHeaderMsg("若不想見到此確認框，請至設定頁進行設定（即使在作答中，也可以隨時切換至設定頁）。", "You can set preferences if you don\'t want to see this confirmation again."),
    // the alert message of skipping a single problem
    SkipProblemAlertMsg("確定要跳過這題嗎？", "Skip this problem?"),
    // the alert message of finishing a whole problem-set
    FinishProblemSetAlertMsg("確定要結束作答嗎？", "Finish the practice?"),

    // the title for showing numbers per tested vocabularies
    TestedNo("題號", "No."),
    // the title for showing tested vocabularies
    TestedVocs("本次練習的單字", "Tested vocabularies"),
    // the title for showing results of tested vocabularies
    TestedResults("結果", "Results"),
    // the title for showing all tested vocabularies
    TestedVocsNum("總數量", "Total tested"),
    // the title for showing the correct rate
    CorrectRate("正確率", "Correct rate"),

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
    // pref: alert when skipping a single problem
    AlertWhenSkippingProblem("欲跳過題目時，彈出確認的訊息框", "Prompt an alert when skipping a problem"),
    // pref: alert when finishing (skipping) a whole problem-set
    AlertWhenFinishingProblemSet("欲結束作答時，彈出確認的訊息框", "Prompt an alert when skipping a whole problem-set"),

    // used when the answer was correct
    Correct("正確", "Correct"),
    // used when the answer was wrong
    Wrong("錯誤", "Wrong"),
    // used when the problem has not been answered
    NoAnswered("未作答", "No Answered"),

    // the string of 'start'
    Start("開始", "Start"),
    // the string of 'back'
    Back("返回", "Back"),
    // the string of 'yes'
    Yes("要", "Yes"),
    // the string of 'no'
    No("不要", "No"),
    // the string of 'submit'
    Submit("送出", "Submit"),
    // the string of a colon
    COLON("：", ": "),

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
        fun register(any: Any, strEnum: Strings) {
            mRegisteredHashMap[any] = GenericString.Array(GenericString(strEnum))
            notifyRegistered(any)
        }

        // register a node w/ a sequence of literal strings or str-enums
        fun register(any: Any, vararg strs: GenericString) {
            mRegisteredHashMap[any] = GenericString.Array(*strs)
            notifyRegistered(any)
        }

        // unregister a node
        fun unregister(any: Any) { mRegisteredHashMap.remove(any) }

        // notify all registered nodes to be updated
        fun notifyAllRegistered() {
            mRegisteredHashMap.forEach { (any, _) -> updateRegistered(any) }
        }

        // notify a certain registered node
        @Suppress("MemberVisibilityCanBePrivate")
        fun notifyRegistered(any: Any) { updateRegistered(any) }

        // update a certain registered node when notifying it
        private fun updateRegistered(any: Any) {
            Platform.runLater {
                mRegisteredHashMap[any]?.let { strs ->
                    val joined = strs.toString()

                    (any as? Stage)?.title = joined
                    (any as? RadioButton)?.text = joined
                    (any as? Label)?.text = joined
                    (any as? Text)?.text = joined
                    (any as? TextField)?.text = joined
                    (any as? TextArea)?.text = joined
                    (any as? CheckBox)?.text = joined
                    (any as? Button)?.text = joined
                    (any as? ToggleButton)?.text = joined
                    (any as? ComboBox<*>)?.promptText = joined
                    (any as? Spinner<*>)?.promptText = joined
                    (any as? PreferenceField)?.setFieldName(joined)
                    (any as? Alert)?.let {
                        it.title = strs.get(0)
                        it.headerText = strs.get(1)
                        it.contentText = strs.get(2)
                    }
                }
            }
        }
    }
}