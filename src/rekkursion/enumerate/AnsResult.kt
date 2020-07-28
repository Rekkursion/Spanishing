package rekkursion.enumerate

// the result of answering a certain problem
enum class AnsResult(val strEnum: Strings, val colorEnum: Colors) {
    CORRECT(Strings.Correct, Colors.CORRECT_RES),
    WRONG(Strings.Wrong, Colors.WRONG_RES),
    NO_ANSWERED(Strings.NoAnswered, Colors.NO_ANSWER_RES);

    /* ======================================== */

    fun isCorrect(): Boolean = this == CORRECT
    fun isWrong(): Boolean = this == WRONG
    fun isNoAnswered(): Boolean = this == NO_ANSWERED
}