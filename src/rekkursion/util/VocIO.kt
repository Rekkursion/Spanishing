package rekkursion.util

import javafx.scene.control.Alert
import org.json.JSONArray
import org.json.JSONObject
import rekkursion.enumerate.ConjugationType
import rekkursion.enumerate.PartOfSpeech
import rekkursion.enumerate.Persona
import rekkursion.enumerate.Tense
import rekkursion.model.Conjugation
import rekkursion.model.Meaning
import rekkursion.model.Vocabulary
import java.io.*
import java.nio.charset.Charset

object VocIO {
    // the filename of all vocabularies
    private const val mVocsFilename = "D:\\rekkursion\\mooc\\spanish\\spanish_vocabularies.json"

    // the filename of written-to vocabularies
    private const val mWrittenToFilename = "src/rekkursion/res/vocs/vocs.json"

    // the filename of collected vocabularies
    private const val mCollectedVocsFilename = "src/rekkursion/res/vocs/collected.json"

    // the filename of conjugations of verbs
    private const val mVerbConjugationsFilename = "D:\\rekkursion\\mooc\\spanish\\spanish_verbs.json"

    // read all vocabularies from a json file
    fun readAllVocabularies(): ArrayList<Vocabulary> {
        // first, read all collected vocabularies from another file
        val collectedHashSet = readCollectedVocabularies()

        // the string of json content
        var jsonString = ""

        // get the json content from a certain file
        try {
            val file = File(mVocsFilename)
            val fis = FileInputStream(file)
            val byteArr = fis.readAllBytes()
            fis.close()
            jsonString = String(byteArr, Charset.forName("UTF-8"))
        } catch (e: FileNotFoundException) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "File not found"
            alert.contentText = "The file \"$mVocsFilename\" does NOT exist."
            alert.showAndWait()
        }

        // the result of read-in vocabularies
        val ret = arrayListOf<Vocabulary>()

        // read the json content
        try {
            // create a json object
            val jArr = JSONArray(jsonString)

            // iterate the array of vocabularies
            // and add every json-object into the result list as a vocabulary
            jArr.forEach { vocabulary ->
                val jv = vocabulary as JSONObject
                val esp = jv.getString("esp")

                // add this vocabulary into the result list
                if (esp.isNotEmpty()) {
                    val posp = PartOfSpeech.getPospFromAbbr(jv.getString("posp"))
                    ret.add(Vocabulary(
                            esp,
                            Meaning(jv.getString("chi"), jv.getString("eng"), posp),
                            collectedHashSet.contains(esp)
                    ))
                    if (posp == PartOfSpeech.NONE)
                        println("warning: no posp \'${jv.getString("posp")}\' of the word \"$esp\"")
                }
            }
        } catch (e: Exception) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Error with JSON content"
            alert.contentText = "The JSON content probably has some syntax error."
            alert.showAndWait()
        }

        // write all vocabularies into another file
        writeAllVocabularies(ret)

        // return the result list
        return ret
    }

    // collect or uncollect a certain vocabulary
    fun collectOrUncollectCertainVocabulary(esp: String, isCollecting: Boolean) {
        Thread {
            val collected = readCollectedVocabularies()
            if ((isCollecting && collected.contains(esp)) || (!isCollecting && !collected.contains(esp)))
                return@Thread
            try {
                val file = File(mCollectedVocsFilename)
                file.createNewFile()
                val writer = FileWriter(file)

                val jArr = JSONArray()
                collected.forEach { this_esp ->
                    if (!isCollecting && this_esp == esp); else {
                        val jObj = JSONObject()
                        jObj.put("esp", this_esp)
                        jArr.put(jObj)
                    }
                }
                if (isCollecting) {
                    val jObj = JSONObject()
                    jObj.put("esp", esp)
                    jArr.put(jObj)
                }

                writer.write(jArr.toString(4))
                writer.close()
            } catch (e: IOException) { e.printStackTrace() }
        }.start()
    }

    // write all vocabularies to a json file
    private fun writeAllVocabularies(vocList: ArrayList<Vocabulary>) {
        Thread {
            try {
                val file = File(mWrittenToFilename)
                file.createNewFile()
                val writer = FileWriter(file)

                val jArr = JSONArray()
                vocList.forEach { voc ->
                    val jObj = JSONObject(); val meaning = voc.copiedMeaning
                    jObj.put("esp", voc.esp)
                    jObj.put("chi", meaning.chi)
                    jObj.put("eng", meaning.eng)
                    jObj.put("posp", meaning.posp.abbr)
                    jObj.put("collected", voc.isCollected)
                    jArr.put(jObj)
                }

                writer.write(jArr.toString(4))
                writer.close()
            } catch (e: IOException) { e.printStackTrace() }
        }.start()
    }

    // read all collected vocabularies from another file
    private fun readCollectedVocabularies(): HashSet<String> {
        // the string of json content
        var jsonString = ""

        // get the json content from a certain file
        try {
            val file = File(mCollectedVocsFilename)
            val fis = FileInputStream(file)
            val byteArr = fis.readAllBytes()
            fis.close()
            jsonString = String(byteArr, Charset.forName("UTF-8"))
        } catch (e: FileNotFoundException) { return hashSetOf() }

        // the result of read-in collected vocabularies
        val ret = hashSetOf<String>()

        // read the json content
        try {
            // create a json object
            val jArr = JSONArray(jsonString)

            // iterate the array of vocabularies
            // and add every json-object into the result list as a vocabulary
            jArr.forEach { vocabulary ->
                val jv = vocabulary as JSONObject
                val esp = jv.getString("esp")
                // add this collected vocabulary into the result list
                if (esp.isNotEmpty()) ret.add(esp)
            }
        } catch (e: Exception) { e.printStackTrace() }

        // return the result list
        return ret
    }

    // read conjugations of all verbs
    fun readConjugations(): ArrayList<Conjugation> {
        // the string of json content
        var jsonString = ""

        // get the json content from a certain file
        try {
            val file = File(mVerbConjugationsFilename)
            val fis = FileInputStream(file)
            val byteArr = fis.readAllBytes()
            fis.close()
            jsonString = String(byteArr, Charset.forName("UTF-8"))
        } catch (e: FileNotFoundException) {}

        // the list of conjugations as the return value
        val ret = arrayListOf<Conjugation>()

        // read the json content
        try {
            // iterate through the array of verbs
            JSONArray(jsonString).forEach { verb ->
                try {
                    val v = verb as JSONObject
                    val esp = v.optString("esp", "")
                    if (esp.isNotEmpty()) {
                        // create a builder of the conjugation
                        val builder = Conjugation.Builder(esp)
                        // get the irregular conjugations object if this word has one
                        val irr: JSONObject? = v.optJSONObject("irr")
                        // get the stem-changer if this word is a stem-changing word
                        val stmchg = v.optString("stmchg", "")

                        // participles (present and/or past)
                        buildParticiples(builder, irr?.optJSONObject("participles"))
                        // indicative conjugations
                        buildIndicativeConjugations(builder, irr?.optJSONObject("indicative"), esp, stmchg)

                        // add the created conjugation into the return-list
                        ret.add(builder.create())
                    }
                } catch (e: Exception) {}
            }
        } catch (e: Exception) { e.printStackTrace() }

        // return the built list of conjugations
        return ret
    }

    // the helper function to build participles
    private fun buildParticiples(builder: Conjugation.Builder, par: JSONObject?) {
        builder.setParticiples(par?.optString("present", null), par?.optString("past", null))
    }

    // the helper function to build conjugations of the indicative
    private fun buildIndicativeConjugations(builder: Conjugation.Builder, ind: JSONObject?, esp: String, stmchg: String) {
        val present = ind?.optJSONObject("present")
        val preterite = ind?.optJSONObject("preterite")
        val imperfect = ind?.optJSONObject("imperfect")
        val conditional = ind?.optJSONObject("conditional")
        val future = ind?.optJSONObject("future")

        builder
                .setConjugation(ConjugationType.INDICATIVE, Persona.YO, Tense.PRESENT, present?.optString("y", null) ?: constructStmchgForm(esp, stmchg, Persona.YO))
                .setConjugation(ConjugationType.INDICATIVE, Persona.TÚ, Tense.PRESENT, present?.optString("t", null) ?: constructStmchgForm(esp, stmchg, Persona.TÚ))
                .setConjugation(ConjugationType.INDICATIVE, Persona.USTED, Tense.PRESENT, present?.optString("ud", null) ?: constructStmchgForm(esp, stmchg, Persona.USTED))
                .setConjugation(ConjugationType.INDICATIVE, Persona.NOSOTROS, Tense.PRESENT, present?.optString("n", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.VOSOTROS, Tense.PRESENT, present?.optString("v", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.USTEDES, Tense.PRESENT, present?.optString("uds", null) ?: constructStmchgForm(esp, stmchg, Persona.USTEDES))

                .setConjugation(ConjugationType.INDICATIVE, Persona.YO, Tense.PRETERITE, preterite?.optString("y", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.TÚ, Tense.PRETERITE, preterite?.optString("t", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.USTED, Tense.PRETERITE, preterite?.optString("ud", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.NOSOTROS, Tense.PRETERITE, preterite?.optString("n", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.VOSOTROS, Tense.PRETERITE, preterite?.optString("v", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.USTEDES, Tense.PRETERITE, preterite?.optString("uds", null))

                .setConjugation(ConjugationType.INDICATIVE, Persona.YO, Tense.IMPERFECT, imperfect?.optString("y", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.TÚ, Tense.IMPERFECT, imperfect?.optString("t", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.USTED, Tense.IMPERFECT, imperfect?.optString("ud", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.NOSOTROS, Tense.IMPERFECT, imperfect?.optString("n", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.VOSOTROS, Tense.IMPERFECT, imperfect?.optString("v", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.USTEDES, Tense.IMPERFECT, imperfect?.optString("uds", null))

                .setConjugation(ConjugationType.INDICATIVE, Persona.YO, Tense.CONDITIONAL, conditional?.optString("y", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.TÚ, Tense.CONDITIONAL, conditional?.optString("t", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.USTED, Tense.CONDITIONAL, conditional?.optString("ud", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.NOSOTROS, Tense.CONDITIONAL, conditional?.optString("n", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.VOSOTROS, Tense.CONDITIONAL, conditional?.optString("v", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.USTEDES, Tense.CONDITIONAL, conditional?.optString("uds", null))

                .setConjugation(ConjugationType.INDICATIVE, Persona.YO, Tense.FUTURE, future?.optString("y", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.TÚ, Tense.FUTURE, future?.optString("t", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.USTED, Tense.FUTURE, future?.optString("ud", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.NOSOTROS, Tense.FUTURE, future?.optString("n", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.VOSOTROS, Tense.FUTURE, future?.optString("v", null))
                .setConjugation(ConjugationType.INDICATIVE, Persona.USTEDES, Tense.FUTURE, future?.optString("uds", null))
    }

    // the helper function to construct the stem-changing forms
    private fun constructStmchgForm(esp: String, stmchg: String, persona: Persona): String? {
        try {
            // the index of the '>'
            val idxOfArrow = stmchg.indexOf('>')
            if (idxOfArrow == -1)
                return null

            // get the part in the original word that shall be changed
            val bef = stmchg.substring(0, idxOfArrow)
            // get the stem-changer
            val aft = stmchg.substring(idxOfArrow + 1)

            // find the part in the original word to be changed
            val idxOfBef = esp.substring(0, esp.length - 2).lastIndexOf(bef[0])

            // construct the stem-changed form (have NOT applied the persona-varying)
            val stemChanged = esp.replaceRange(idxOfBef..idxOfBef, aft)
            val changedStem = stemChanged.substring(0, stemChanged.length - 2)
            val tail = stemChanged.substring(stemChanged.length - 2)

            // construct the persona-varying to the stem-changed form
            return when (persona) {
                Persona.YO -> "${changedStem}o"
                Persona.TÚ -> "$changedStem${if (tail == "ar") "as" else "es"}"
                Persona.USTED -> "$changedStem${if (tail == "ar") "a" else "e"}"
                // nosotros- and vosotros- forms do NOT apply the stem-changing rules
                Persona.NOSOTROS -> null
                Persona.VOSOTROS -> null
                Persona.USTEDES -> "$changedStem${if (tail == "ar") "an" else "en"}"
            }
        } catch (e: Exception) { return null }
    }
}