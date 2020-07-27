package rekkursion.view.listviews.voc

import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import rekkursion.manager.PropertiesManager
import rekkursion.manager.VocManager
import rekkursion.model.Vocabulary
import rekkursion.view.StarButton

class VocabularyListCell: ListCell<Vocabulary>() {
    private val mAnchorPane = AnchorPane()
    private val mVbxContent = VBox()
    private val mHbxEspAndPosp = HBox()
    private val mLblEsp = Label()
    private val mLblPosp = Label()
    private val mLblChiAndEng = Label()

    private val mStarButton = StarButton(false, 40.0, 40.0)

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

        AnchorPane.setTopAnchor(mVbxContent, PropertiesManager.vocListItemPadding)
        AnchorPane.setLeftAnchor(mVbxContent, PropertiesManager.vocListItemPadding)
        AnchorPane.setTopAnchor(mStarButton, PropertiesManager.vocListItemPadding)
        AnchorPane.setRightAnchor(mStarButton, PropertiesManager.vocListItemPadding)
        mAnchorPane.children.addAll(mVbxContent, mStarButton)
    }

    /* ======================================== */

    // update items
    override fun updateItem(item: Vocabulary?, empty: Boolean) {
        super.updateItem(item, empty)

        // set the contents of the item
        if (item != null && !empty) {
            val copiedMeaning = item.copiedMeaning

            // set the pressing-listener on the star-button for collecting this vocabulary
            mStarButton.setOnPressingListener(object: StarButton.OnPressingListener {
                override fun onPressing(oldValue: Boolean, newValue: Boolean) {
                    VocManager.collectOrUncollect(item, newValue)
                }
            })

            mLblEsp.text = item.esp
            mLblPosp.text = "[${copiedMeaning.posp.abbr}]"
            mLblChiAndEng.text = "${copiedMeaning.chi} (${copiedMeaning.eng})"
            if (item.isCollected) mStarButton.press() else mStarButton.unpress()
            graphic = mAnchorPane
        }
        else
            graphic = null
    }
}