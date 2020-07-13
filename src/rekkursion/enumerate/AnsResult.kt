package rekkursion.enumerate

// the result of answering a certain problem
enum class AnsResult(val strEnum: Strings, val colorEnum: Colors) {
    CORRECT(Strings.Correct, Colors.CORRECT_RES),
    WRONG(Strings.Wrong, Colors.WRONG_RES),
    NO_ANSWERED(Strings.NoAnswer, Colors.NO_ANSWER_RES)
}