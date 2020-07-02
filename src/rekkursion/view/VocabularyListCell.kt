package rekkursion.view

import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import rekkursion.manager.PropertiesManager
import rekkursion.model.Vocabulary

class VocabularyListCell: ListCell<Vocabulary>() {
    private val mVbxContent = VBox()
    private val mHbxEspAndPosp = HBox()
    private val mLblEsp = Label()
    private val mLblPosp = Label()
    private val mLblChiAndEng = Label()

    init {
        // set the spacings of v-box & h-box
        mVbxContent.spacing = PropertiesManager.generalSpacing
        mHbxEspAndPosp.spacing = PropertiesManager.generalSpacing

        // set the id of this list-cell
        id = "v_list_cell"

        // set the id of lbl-esp
        mLblEsp.id = "esp"

        // add the label of esp and the label of posp into the h-box
        mHbxEspAndPosp.children.addAll(mLblEsp, mLblPosp)

        // add the h-box and the label of chi & eng into the v-box
        mVbxContent.children.addAll(mHbxEspAndPosp, mLblChiAndEng)
    }

    // update items
    override fun updateItem(item: Vocabulary?, empty: Boolean) {
        super.updateItem(item, empty)

        // set the contents of the item
        if (item != null && !empty) {
            mLblEsp.text = item.esp
            mLblPosp.text = "[${item.meaningList[0].posp.abbr}]"
            mLblChiAndEng.text = "${item.meaningList[0].chi} (${item.meaningList[0].eng})"
            graphic = mVbxContent
        }
        else
            graphic = null
    }
}