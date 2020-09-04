package rekkursion.model

import rekkursion.enumerate.ConjugationType
import rekkursion.enumerate.Persona
import rekkursion.enumerate.Tense
import rekkursion.enumerate.VerbType

class Conjugation private constructor(esp: String): Copiable {
    @Suppress("ReplaceWithEnumMap", "ReplacePutWithAssignment")
    class Builder(esp: String) {
        // the instance of the building-up conjugation
        private val instance = Conjugation(esp)

        /* ======================================== */

        // set a certain conjugation (if there's no conjugation passed, then it will be viewed as a regular conjugation)
        fun setConjugation(conjugationType: ConjugationType, persona: Persona, tense: Tense, conjugation: String? = null): Builder {
            val d = instance.mConjDict
                    .getOrPut(conjugationType) { HashMap() }
                    .getOrPut(persona) { HashMap() }
            if (conjugation != null || !d.containsKey(tense))
                d.put(tense, conjugation ?: instance.getRegularConjugation(conjugationType, persona, tense))
            return this
        }

        // set the participles (present & past)
        fun setParticiples(presentParticiple: String? = null, pastParticiple: String? = null): Builder {
            val reg = instance.getRegularParticiples()
            instance.mPresentParticiple = presentParticiple ?: reg.first
            instance.mPastParticiple = pastParticiple ?: reg.second
            return this
        }

        // create (return) the built-up conjugation
        fun create(): Conjugation = instance
    }

    /* ======================================== */

    // the spanish spelling word
    private val mEsp: String = esp
    val esp get() = mEsp

    // the present participle of this verb
    private var mPresentParticiple = ""
    val presentParticiple get() = mPresentParticiple

    // the past participle of this verb
    private var mPastParticiple = ""
    val pastParticiple get() = mPastParticiple

    // the dictionary of the conjugation of this verb
    private val mConjDict = HashMap<ConjugationType, HashMap<Persona, HashMap<Tense, String>>>()

    /* ======================================== */

    // get the verb-type of this verb
    private fun getVerbType(): VerbType = VerbType.getWith(mEsp)

    // get a certain regular conjugation
    private fun getRegularConjugation(conjugationType: ConjugationType, persona: Persona, tense: Tense): String {
        // the type of the verb (-ar, -ir, or -er)
        val vt = getVerbType()

        // indicative
        return if (conjugationType == ConjugationType.INDICATIVE) {
            when (tense) {
                // indicative-present
                Tense.PRESENT -> when (persona) {
                    Persona.YO -> replace("o")
                    Persona.TÚ -> if (vt == VerbType.AR) replace("as") else replace("es")
                    Persona.USTED -> if (vt == VerbType.AR) replace("a") else replace("e")
                    Persona.NOSOTROS -> if (vt == VerbType.AR) replace("amos") else if (vt == VerbType.ER) replace("emos") else replace("imos")
                    Persona.VOSOTROS -> if (vt == VerbType.AR) replace("áis") else if (vt == VerbType.ER) replace("éis") else replace("ís")
                    Persona.USTEDES -> if (vt == VerbType.AR) replace("an") else replace("en")
                }
                // indicative-preterite
                Tense.PRETERITE -> when (persona) {
                    Persona.YO -> if (vt == VerbType.AR) replace("é") else replace("í")
                    Persona.TÚ -> if (vt == VerbType.AR) replace("aste") else replace("iste")
                    Persona.USTED -> if (vt == VerbType.AR) replace("ó") else replace("ió")
                    Persona.NOSOTROS -> if (vt == VerbType.AR) replace("amos") else replace("imos")
                    Persona.VOSOTROS -> if (vt == VerbType.AR) replace("asteis") else replace("isteis")
                    Persona.USTEDES -> if (vt == VerbType.AR) replace("aron") else replace("ieron")
                }
                // indicative-imperfect
                Tense.IMPERFECT -> when (persona) {
                    Persona.YO -> if (vt == VerbType.AR) replace("aba") else replace("ía")
                    Persona.TÚ -> if (vt == VerbType.AR) replace("abas") else replace("ías")
                    Persona.USTED -> if (vt == VerbType.AR) replace("aba") else replace("ía")
                    Persona.NOSOTROS -> if (vt == VerbType.AR) replace("ábamos") else replace("íamos")
                    Persona.VOSOTROS -> if (vt == VerbType.AR) replace("abais") else replace("íais")
                    Persona.USTEDES -> if (vt == VerbType.AR) replace("aban") else replace("ían")
                }
                // indicative-conditional
                Tense.CONDITIONAL -> when (persona) {
                    Persona.YO -> concate("ía")
                    Persona.TÚ -> concate("ías")
                    Persona.USTED -> concate("ía")
                    Persona.NOSOTROS -> concate("íamos")
                    Persona.VOSOTROS -> concate("íais")
                    Persona.USTEDES -> concate("ían")
                }
                // indicative-future
                Tense.FUTURE -> when (persona) {
                    Persona.YO -> concate("é")
                    Persona.TÚ -> concate("ás")
                    Persona.USTED -> concate("á")
                    Persona.NOSOTROS -> concate("emos")
                    Persona.VOSOTROS -> concate("éis")
                    Persona.USTEDES -> concate("án")
                }
            }
        }
        else ""
    }

    // get a pair of regular participles
    private fun getRegularParticiples(): Pair<String, String> = Pair(
            if (getVerbType() == VerbType.AR) mEsp.substring(0, mEsp.length - 2) + "ando"
                else mEsp.substring(0, mEsp.length - 2) + "iendo",
            if (getVerbType() == VerbType.AR) mEsp.substring(0, mEsp.length - 2) + "ado"
                else mEsp.substring(0, mEsp.length - 2) + "ido"
    )

    // replace the tail of the verb with a certain new tail
    private fun replace(tail: String): String = mEsp.substring(0, mEsp.length - 2) + tail

    // concatenate the verb with a certain tail
    private fun concate(tail: String): String = mEsp + tail

    /* ======================================== */

    override fun copy(): Conjugation {
        val ret = Conjugation(mEsp)
        ret.mPresentParticiple = mPresentParticiple
        ret.mPastParticiple = mPastParticiple
        ret.mConjDict.putAll(mConjDict)
        return ret
    }

    /* ======================================== */

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Conjugation) return false

        if (mEsp != other.mEsp) return false
        if (mPresentParticiple != other.mPresentParticiple) return false
        if (mPastParticiple != other.mPastParticiple) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mEsp.hashCode()
        result = 31 * result + mPresentParticiple.hashCode()
        result = 31 * result + mPastParticiple.hashCode()
        return result
    }

    override fun toString(): String =
            """
            Verb${"\t\t" + mEsp}
            |$mPresentParticiple|$mPastParticiple|
            ${mConjDict[ConjugationType.INDICATIVE]?.get(Persona.YO)?.toList()?.joinToString("|")}
            ${mConjDict[ConjugationType.INDICATIVE]?.get(Persona.TÚ)?.toList()?.joinToString("|")}
            ${mConjDict[ConjugationType.INDICATIVE]?.get(Persona.USTED)?.toList()?.joinToString("|")}
            ${mConjDict[ConjugationType.INDICATIVE]?.get(Persona.NOSOTROS)?.toList()?.joinToString("|")}
            ${mConjDict[ConjugationType.INDICATIVE]?.get(Persona.VOSOTROS)?.toList()?.joinToString("|")}
            ${mConjDict[ConjugationType.INDICATIVE]?.get(Persona.USTEDES)?.toList()?.joinToString("|")}
            
            ========================================
            
            """.trimIndent()
}