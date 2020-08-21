package rekkursion.enumerate

enum class VerbType {
    AR, IR, ER, NONE_VERB;

    /* ======================================== */

    companion object {
        // get the verb-type with a verb
        fun getWith(verb: String): VerbType = when {
            verb.endsWith("ar") -> VerbType.AR
            verb.endsWith("ir") -> VerbType.IR
            verb.endsWith("er") -> VerbType.ER
            else -> VerbType.NONE_VERB
        }
    }
}