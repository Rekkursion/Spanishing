package rekkursion.view.pref

import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager
import rekkursion.view.styled.StyledVBox

class PreferencesPage: StyledVBox() {
    init {
        // the user-preferred interface language
        addLangPrefField()
    }

    /* ======================================== */

    // add the field of interface languages
    private fun addLangPrefField() {
        val comboBox = ComboBox<String>(FXCollections.observableArrayList(
                Strings.get(Strings.InterfaceLang_Chi),
                Strings.get(Strings.InterfaceLang_Eng)
        ))
        val langPrefField = PreferenceField(Strings.get(Strings.InterfaceLang), comboBox)
        children.addAll(langPrefField)

        // the initial value
        comboBox.promptText = PreferenceManager.lang

        // the listener for selecting
        comboBox.valueProperty().addListener { _, _, newValue -> PreferenceManager.write("lang", newValue) }
    }
}