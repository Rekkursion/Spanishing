package rekkursion.enumerate

// part of speeches of spanish vocabularies
@Suppress("unused")
enum class PartOfSpeech(val abbr: String) {
    MASCULINE_NOUN("m"),
    FEMININE_NOUN("f"),
    M_OR_F_NOUN("m/f"),
    PLURAL("pl"),
    PRONOUN("pron"),
    COUNTRY_NOUN("country"),

    M_OR_ADJ("m; adj"),
    M_OR_F_OR_ADJ("m/f; adj"),
    M_OR_F_OR_ADJ_OR_ADV("m/f; adj; adv"),

    DEFINITE_ARTICLE("art.d"),
    INDEFINITE_ARTICLE("art.i"),

    VERB("v"),
    AUXILIARY_VERB("aux"),
    AUX_OR_VERB("aux; v"),

    ADJECTIVE("adj"),
    ADVERB("adv"),
    ADJ_OR_ADV("adj; adv"),

    PREPOSITION("prep"),
    CONJUNCTION("conj"),
    INTERJECTION("int"),

    // in case of some errors
    NONE("none");

    /* ======================================== */

    companion object {
        // get the part of speech from an abbreviation
        fun getPospFromAbbr(abbr: String): PartOfSpeech {
            /*val abbrList = abbr.split(";\\s?".toRegex())
            return values().filter { abbrList.contains(it.abbr) }
                    .joinToString { it.abbr }*/
            return values().filter { it.abbr == abbr }.getOrNull(0) ?: NONE
        }
    }
}