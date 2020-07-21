package rekkursion.view.pref

import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import rekkursion.enumerate.Strings
import rekkursion.manager.PropertiesManager
import rekkursion.view.styled.StyledLabel

class PreferenceField(
        fieldNameEnum: Strings,
        node: Node?,
        textSize: Int = 18,
        widthsRatio: Pair<Double, Double> = Pair(70.0, 30.0))
    : GridPane() {

    // the label for showing the key (title of this preference)
    private val mLblKey = StyledLabel(fieldNameEnum)

    // the view as the value ui for this field
    private var mValueView: Node? = node

    init {
        // set the text-size of the label for showing the key
        mLblKey.textSize = textSize

        // set the alignment
        alignment = Pos.CENTER
        // set the gaps
        vgap = PropertiesManager.generalSpacing
        hgap = PropertiesManager.generalSpacing

        // set the column-constraints
        val c1 = ColumnConstraints(); c1.percentWidth = widthsRatio.first ; c1.halignment = HPos.CENTER; //c1.isFillWidth = true
        val c2 = ColumnConstraints(); c2.percentWidth = widthsRatio.second; c2.halignment = HPos.CENTER; //c2.isFillWidth = true
        columnConstraints.addAll(c1, c2)

        // add label-as-field-title
        add(mLblKey, 0, 0)
        // add the view-as-field-value
        add(mValueView, 1, 0)
    }

    /* ======================================== */

    // set the field name
    fun setFieldName(fieldName: String) { mLblKey.text = fieldName }
}