package rekkursion.view.pref

import javafx.collections.FXCollections
import javafx.scene.control.CheckBox
import javafx.scene.control.ComboBox
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.view.styled.StyledVBox

class PreferencesPage: StyledVBox() {
    init {
        // the user-preferred interface language
        addLangPrefField()

        // the alert when skipping a single problem
        addAlertWhenSkippingProblemPrefField()

        // the alert when finishing (skipping) a whole problem-set
        addAlertWhenFinishingProblemSetPrefField()
    }

    /* ======================================== */

    // add the field of interface languages
    private fun addLangPrefField() {
        val comboBox = ComboBox<String>(FXCollections.observableArrayList(
                Strings.get(Strings.InterfaceLang_Chi),
                Strings.get(Strings.InterfaceLang_Eng)
        ))
        val langPrefField = PreferenceField(Strings.InterfaceLang, comboBox)

        // add the created field into this page
        children.add(langPrefField)

        // the initial value
        comboBox.promptText = PreferenceManager.lang

        // the listener for selecting
        comboBox.valueProperty().addListener { _, _, newValue ->
            PreferenceManager.write("lang", newValue)
            //

        }
    }

    // add the field of an alert when skipping a single problem
    private fun addAlertWhenSkippingProblemPrefField() {
        val checkBox = CheckBox()
        val alertWhenSkippingProblemPrefField = PreferenceField(Strings.AlertWhenSkippingProblem, checkBox)

        // add the created field into this page
        children.add(alertWhenSkippingProblemPrefField)

        // the initial value
        checkBox.isSelected = PreferenceManager.alertWhenSkipping

        // the listener for selecting
        checkBox.selectedProperty().addListener { _, _, newValue ->
            PreferenceManager.write("alert-when-skipping", newValue.toString())
        }
    }

    // add the field of an alert when finishing (skipping) a whole problem-set
    private fun addAlertWhenFinishingProblemSetPrefField() {
        val checkBox = CheckBox()
        val alertWhenFinishingProblemPrefField = PreferenceField(Strings.AlertWhenFinishingProblemSet, checkBox)

        // add the created field into this page
        children.add(alertWhenFinishingProblemPrefField)

        // the initial value
        checkBox.isSelected = PreferenceManager.alertWhenFinishing

        // the listener for selecting
        checkBox.selectedProperty().addListener { _, _, newValue ->
            PreferenceManager.write("alert-when-finishing", newValue.toString())
        }
    }
}