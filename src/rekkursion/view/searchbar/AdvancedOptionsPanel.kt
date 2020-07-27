package rekkursion.view.searchbar

import javafx.geometry.Orientation
import javafx.scene.layout.FlowPane
import rekkursion.enumerate.PartOfSpeech
import rekkursion.enumerate.Strings
import rekkursion.manager.PropertiesManager
import rekkursion.view.SelectingButtonBar
import rekkursion.view.pref.PreferenceField
import rekkursion.view.styled.Styled
import rekkursion.view.styled.StyledCheckBox
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledVBox

class AdvancedOptionsPanel(searchBar: VocSearchBar): StyledVBox() {
    // the voc-search-bar
    private val mSearchBar = searchBar

    // the global counter for the added fields
    private var mFieldCounter = 0

    init {
        // add the field for determining which texts shall be searched on
        addSearchTextsField()

        // add the field for filtering the searching result by part-of-speeches
        addSearchPospField()
    }

    /* ======================================== */

    @Suppress("NAME_SHADOWING")
    private fun addSearchTextsField() {
        val ckbEsp = StyledCheckBox(Strings.Esp)
        val ckbEng = StyledCheckBox(Strings.Eng)
        val ckbChi = StyledCheckBox(Strings.Chi)
        val hbx = StyledHBox(ckbEsp, ckbEng, ckbChi)
        val searchObjectsField = PreferenceField(
                Strings.AdvancedVocSearchOptions_searchingTexts,
                hbx,
                PropertiesManager.searchBarTextSize
        )

        // add the created field into this page
        children.add(searchObjectsField)

        // the initial values
        val opts = mSearchBar.searchOptionsCopied
        ckbEsp.isSelected = opts.isSearchingOnESP
        ckbEng.isSelected = opts.isSearchingOnENG
        ckbChi.isSelected = opts.isSearchingOnCHI
        Styled.unifyTextSize(PropertiesManager.searchBarTextSize, ckbEsp, ckbEng, ckbChi)

        // add listeners for check-boxes when the selected properties have been changed
        ckbEsp.selectedProperty().addListener { _, _, newValue ->
            val opts = mSearchBar.searchOptionsCopied; opts.isSearchingOnESP = newValue
            mSearchBar.setSearchOptions(opts)
        }
        ckbEng.selectedProperty().addListener { _, _, newValue ->
            val opts = mSearchBar.searchOptionsCopied; opts.isSearchingOnENG = newValue
            mSearchBar.setSearchOptions(opts)
        }
        ckbChi.selectedProperty().addListener { _, _, newValue ->
            val opts = mSearchBar.searchOptionsCopied; opts.isSearchingOnCHI = newValue
            mSearchBar.setSearchOptions(opts)
        }

        setStyle(searchObjectsField)
    }

    private fun addSearchPospField() {
        // create a v-box for containing all check-boxes & selecting-button-bar
        val vbx = StyledVBox()

        val ckbPospList = PartOfSpeech.values()
                .filter { posp -> !posp.name.contains("OR") && posp != PartOfSpeech.NONE }
                .map { posp ->
                    val opts = mSearchBar.searchOptionsCopied
                    val ckb = StyledCheckBox(posp.abbr)
                    ckb.isSelected = opts.isSearchingCertainPosp(posp)
                    ckb.textSize = PropertiesManager.searchBarTextSize
                    ckb.selectedProperty().addListener { _, _, newValue ->
                        if (newValue) opts.addPosps(posp) else opts.dropPosps(posp)
                        mSearchBar.setSearchOptions(opts)
                    }
                    ckb
                }

        // create a flow-pane and add check-boxes for all part-of-speeches
        val flowPane = FlowPane(Orientation.HORIZONTAL, PropertiesManager.generalSpacing, PropertiesManager.generalSpacing)
        flowPane.children.addAll(ckbPospList)

        // create a selecting-button-bar
        val selectingBar = SelectingButtonBar()
        selectingBar.setOnSelectingListener(object: SelectingButtonBar.OnSelectingListener {
            override fun onSelectAll() { ckbPospList.forEach { ckb -> ckb.isSelected = true } }

            override fun onUnselectAll() {
                ckbPospList.forEach { ckb -> ckb.isSelected = false }
            }

            override fun onReverseSelect() {
                ckbPospList.forEach { ckb -> ckb.isSelected = !ckb.isSelected }
            }
        })

        // set the v-box as a part of this field
        vbx.children.addAll(flowPane, selectingBar)
        val searchPospField = PreferenceField(
                Strings.AdvancedVocSearchOptions_searchingPosp,
                vbx,
                PropertiesManager.searchBarTextSize
        )

        // add the created field into this page
        children.add(searchPospField)

        setStyle(searchPospField)
    }

    /* ======================================== */

    // set the style by the order of the field when being added to this panel
    private fun setStyle(field: PreferenceField) {
        if (mFieldCounter.and(1) == 1)
            field.style = "-fx-background-color: rgb(50, 50, 50);"
        ++mFieldCounter
    }
}