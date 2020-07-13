package rekkursion.view

import javafx.geometry.Pos
import rekkursion.enumerate.Colors
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.VocManager
import rekkursion.util.GenericString
import rekkursion.view.styled.StyledLabel
import rekkursion.view.styled.StyledVBox

class VocabularyListPage: StyledVBox() {
    // the view of the vocabulary list
    private val mVocListView = VocabularyListView(VocManager.copiedVocList)

    // the label for showing the number of vocabularies
    private val mLblNumOfVocs = StyledLabel(textColor = Colors.NUMBERED.color)

    init {
        prefHeight = PreferenceManager.windowHeight

        // bind the pref-width to the parent's width
        mLblNumOfVocs.prefWidthProperty().bind(widthProperty())
        // set the alignment
        mLblNumOfVocs.alignment = Pos.CENTER_RIGHT
        // set the text
        mLblNumOfVocs.text = Strings.get(Strings.NumberOfVocs_pre) + VocManager.numOfVocabularies.toString() + Strings.get(Strings.NumberOfVocs_suf)

        Strings.register(mLblNumOfVocs,
                GenericString(Strings.NumberOfVocs_pre),
                GenericString(str = VocManager.numOfVocabularies.toString()),
                GenericString(Strings.NumberOfVocs_suf)
        )

        children.addAll(mVocListView, mLblNumOfVocs)
    }
}