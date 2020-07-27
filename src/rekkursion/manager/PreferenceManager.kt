package rekkursion.manager

import javafx.application.Platform
import org.json.JSONObject
import rekkursion.enumerate.Strings
import rekkursion.util.toAscii
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.nio.charset.Charset
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

object PreferenceManager {
    // the window width
    var windowWidth: Double = PropertiesManager.defaultWindowWidth

    // the window height
    var windowHeight: Double = PropertiesManager.defaultWindowHeight

    // the language the user would like to use
    var lang: String = PropertiesManager.defaultLang

    // using regex or not in a search-bar
    var usingRegex: Boolean = PropertiesManager.defaultUsingRegexOrNot

    // being case-sensitive or not in a search-bar
    var caseSensitive: Boolean = PropertiesManager.defaultCaseSensitiveOrNot

    // the types of texts (ESP, ENG, and/or CHI) searching on
    var textsSearchOn: Int = PropertiesManager.defaultTextsSearchOn

    // the part-of-speeches searching with
    var pospsSearchOn: Int = PropertiesManager.defaultPospsSearchOn

    // the alert when skipping a single problem
    var alertWhenSkipping: Boolean = PropertiesManager.defaultAlertWhenSkipping

    // the alert when finishing (skipping) a whole problem-set
    var alertWhenFinishing: Boolean = PropertiesManager.defaultAlertWhenFinishing

    // the user-preferred problem-type
    var preferredSingleChoiceProblemType: Int = PropertiesManager.defaultSingleChoiceProblemType

    // the user-preferred number of problems when doing practice
    var preferredProblemNum: Int = 10

    init {
        // the string of json content
        var jsonString = ""

        // get the json content from a certain file
        try {
            val file = File("src/rekkursion/application/pref.json")
            val fis = FileInputStream(file)
            val byteArr = fis.readAllBytes()
            fis.close()
            jsonString = String(byteArr, Charset.forName("UTF-8"))
        } catch (e: FileNotFoundException) { e.printStackTrace() }

        // read the json content
        try {
            // create a json object
            val jObj = JSONObject(jsonString)

            windowWidth = jObj.getDouble("window-width")
            windowHeight = jObj.getDouble("window-height")
            lang = jObj.getString("lang")
            usingRegex = jObj.getBoolean("using-regex")
            caseSensitive = jObj.getBoolean("case-sensitive")
            textsSearchOn = jObj.getInt("texts-search-on")
            pospsSearchOn = jObj.getInt("posps-search-on")
            alertWhenSkipping = jObj.getBoolean("alert-when-skipping")
            alertWhenFinishing = jObj.getBoolean("alert-when-finishing")
            preferredSingleChoiceProblemType = jObj.getInt("preferred-single-choice-problem-type")
            preferredProblemNum = jObj.getInt("preferred-problem-num")
        } catch (e: Exception) { e.printStackTrace() }
    }

    /* ======================================== */

    fun write(key: String, value: String) {
        Thread {
            try {
                val varName = dashed2Camel(key)
                val property = PreferenceManager::class.memberProperties.find { it.name == varName }
                if (property is KMutableProperty<*>) {
                    val data = when (property.returnType.javaType) {
                        Int::class.java,
                        Int::class.javaObjectType,
                        Int::class.javaPrimitiveType -> value.toInt()
                        Double::class.javaObjectType,
                        Double::class.javaPrimitiveType -> value.toDouble()
                        String::class.java -> value
                        Boolean::class.java,
                        Boolean::class.javaObjectType,
                        Boolean::class.javaPrimitiveType -> value.toBoolean()
                        else -> null
                    }
                    if (data != null) {
                        // set the designated property
                        property.setter.call(PreferenceManager::class.objectInstance, data)
                        // update all interface strings
                        if (key == "lang")
                            Platform.runLater { Strings.notifyAllRegistered() }
                        // write to 'pref.json' file
                        writeToPrefJson()
                    }
                }
            } catch (e: Exception) { e.printStackTrace() }
        }.start()
    }

    // convert the key-styled string into the variable-styled string
    // e.g. "window-width" -> "windowWidth"
    private fun dashed2Camel(key: String): String = key.mapIndexed { index, c ->
        if (c == '-') ""
        else if (index > 0 && key[index - 1] == '-') (c.toAscii() - 32).toChar().toString()
        else c.toString()
    }.joinToString("")

    // convert the variable-styled string into the key-styled string
    // e.g. "windowWidth" -> "window-width"
    private fun camel2Dashed(key: String): String = key.map { c ->
        val ascii = c.toAscii()
        if (ascii in 65..90) "-" + (ascii + 32).toChar().toString()
        else c.toString()
    }.joinToString("")

    // write the updated prefs into 'pref.json' file
    private fun writeToPrefJson() {
        val jObj = JSONObject()
        PreferenceManager::class.memberProperties.forEach {
            jObj.put(camel2Dashed(it.name), it.getter.call(PreferenceManager::class.objectInstance))
        }
        val jsonString = jObj.toString(4)

        val file = File("src/rekkursion/application/pref.json")
        val fos = FileOutputStream(file)
        fos.write(jsonString.toByteArray(Charset.forName("UTF-8")))
        fos.flush()
        fos.close()
    }
}