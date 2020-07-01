package rekkursion.util

import javafx.scene.control.Alert
import org.json.JSONArray
import org.json.JSONObject
import rekkursion.model.Meaning
import rekkursion.model.PartOfSpeech
import rekkursion.model.Vocabulary
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.nio.charset.Charset

object JsonReader {
    // read all vocabularies from a json file
    fun readAllVocabularies(filename: String): ArrayList<Vocabulary> {
        // the string of json content
        var jsonString = ""

        // get the json content from a certain file
        try {
            val file = File(filename)
            val fis = FileInputStream(file)
            val byteArr = fis.readAllBytes()
            fis.close()
            jsonString = String(byteArr, Charset.forName("UTF-8"))
        } catch (e: FileNotFoundException) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "File not found"
            alert.contentText = "The file \"$filename\" does NOT exist."
            alert.showAndWait()
        }

        // the result of read-in vocabularies
        val ret = arrayListOf<Vocabulary>()

        // read the json content
        try {
            // create a json object
            val jObj = JSONObject(jsonString)

            // get the array of vocabularies
            val jArr: JSONArray = jObj.getJSONArray("vocabularies");

            // iterate the array of vocabularies
            // and add every json-object into the result list as a vocabulary
            jArr.forEach { vocabulary ->
                val jv = vocabulary as JSONObject
                val esp = jv.getString("esp")

                // add this vocabulary into the result list
                if (esp.isNotEmpty())
                    ret.add(Vocabulary(
                            esp,
                            Meaning(
                                    jv.getString("chi"),
                                    jv.getString("eng"),
                                    PartOfSpeech.getPospFromAbbr(jv.getString("posp"))
                            )
                    ))
            }
        } catch (e: Exception) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Error with JSON content"
            alert.contentText = "The JSON content probably has some syntax error."
            alert.showAndWait()
        }

        // return the result list
        return ret
    }
}