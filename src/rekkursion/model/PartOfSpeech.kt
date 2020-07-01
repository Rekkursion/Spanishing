package rekkursion.model

// part of speeches of spanish vocabularies
enum class PartOfSpeech(val abbr: String) {
    MASCULINE_NOUN("m"),
    FEMININE_NOUN("f"),
    M_OR_F_NOUN("m/f"),
    PRONOUN("pron"),

    DEFINITE_ARTICLE("art.d"),
    INDEFINITE_ARTICLE("art.i"),

    TRANSITIVE_VERB("vt"),
    INTRANSITIVE_VERB("vi"),
    T_OR_I_VERB("vi/vt"),
    AUXILIARY_VERB("aux"),

    ADJECTIVE("adj"),
    ADVERB("adv"),

    PREPOSITION("prep"),
    CONJUNCTION("conj"),
    INTERJECTION("int"),

    // in case of some errors
    NONE("none");

    /* ======================================== */

    companion object {
        // get the part of speech from an abbreviation
        fun getPospFromAbbr(abbr: String): PartOfSpeech = values().filter { it.abbr == abbr }.getOrNull(0) ?: NONE
    }
}