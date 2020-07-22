package rekkursion.view.searchbar

import javafx.geometry.Orientation
import javafx.scene.layout.FlowPane
import rekkursion.enumerate.PartOfSpeech
import rekkursion.enumerate.Strings
import rekkursion.manager.PropertiesManager
import rekkursion.util.SearchOptions
import rekkursion.view.pref.PreferenceField
import rekkursion.view.styled.Styled
import rekkursion.view.styled.StyledCheckBox
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledVBox

class AdvancedOptionsPanel(searchBar: VocSearchBar): StyledVBox() {
    // the voc-search-bar
    private val mSearchBar = searchBar

    init {
        // add the field for determining which texts shall be searched on
        addSearchTextsField()

        // add the field for filtering the searching result by part-of-speeches
        addSearchPospField()
    }

    /* ======================================== */

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
        ckbEsp.isSelected = true; ckbEng.isSelected = true; ckbChi.isSelected = true
        Styled.unifyTextSize(PropertiesManager.searchBarTextSize, ckbEsp, ckbEng, ckbChi)

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
    }

    private fun addSearchPospField() {
        val flowPane = FlowPane(Orientation.HORIZONTAL, PropertiesManager.generalSpacing, PropertiesManager.generalSpacing)
        flowPane.children.addAll(PartOfSpeech
                .values()
                .filter { posp -> !posp.name.contains("OR") && posp != PartOfSpeech.NONE }
                .map { posp ->
                    val ckb = StyledCheckBox(posp.abbr)
                    ckb.isSelected = true
                    ckb.textSize = PropertiesManager.searchBarTextSize
                    ckb
                })

        val searchPospField = PreferenceField(
                Strings.AdvancedVocSearchOptions_searchingPosp,
                flowPane,
                PropertiesManager.searchBarTextSize
        )

        // add the created field into this page
        children.add(searchPospField)
    }
}