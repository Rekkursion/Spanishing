package rekkursion.view

import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.layout.VBox
import rekkursion.model.Vocabulary

class VocabularyListCell: ListCell<Vocabulary>() {
    private val mVBox = VBox()
    private val mLblEsp = Label()
    private val mLblPosp = Label()
    private val mLblChiAndEng = Label()

    init {
        // set the spacing of the content v-box
        mVBox.spacing = 10.0

        // set the id of this list-cell
        id = "v_list_cell"

        // set the id of lbl-esp
        mLblEsp.id = "esp"

        // add texts into the v-box
        mVBox.children.addAll(mLblEsp, mLblPosp, mLblChiAndEng)
    }

    // update items
    override fun updateItem(item: Vocabulary?, empty: Boolean) {
        super.updateItem(item, empty)

        // set the contents of the item
        if (item != null && !empty) {
            mLblEsp.text = item.esp
            mLblPosp.text = "[${item.meaningList[0].posp.abbr}]"
            mLblChiAndEng.text = "${item.meaningList[0].chi} (${item.meaningList[0].eng})"
            graphic = mVBox
        }
        else
            graphic = null
    }
}