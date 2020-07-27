package rekkursion.util

import javafx.scene.control.Alert
import org.json.JSONArray
import org.json.JSONObject
import rekkursion.enumerate.PartOfSpeech
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
}