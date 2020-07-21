package rekkursion.view.searchbar

import javafx.geometry.Insets
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager
import rekkursion.util.SearchOptions
import rekkursion.view.styled.StyledCheckBox
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledTextField

open class SearchBar: StyledHBox() {
    // the image for showing the search-icon
    private val mImgvIcon = ImageView()

    // the text-field for users to input
    private val mTxfInput = StyledTextField(200.0)

    // the check-box for determining applying regex or not
    private val mCkbRegex = StyledCheckBox(Strings.UsingRegexOrNot)

    // the check-box for determining being case-sensitive or not
    private val mCkbCaseSensitive = StyledCheckBox(Strings.CaseSensitiveOrNot)

    // the on-text-change-listener
    private var mOnTextChangeListener: OnTextChangeListener? = null

    init {
        // set the icon image of the image-view
        mImgvIcon.image = Image("rekkursion/res/search.png", PropertiesManager.searchIconSize, PropertiesManager.searchIconSize, false, false)

        // the text-size
        mTxfInput.textSize = 14

        // set the padding-left of the check-boxes
        mCkbRegex.padding = Insets(0.0, 0.0, 0.0, PropertiesManager.generalPadding)

        // set the selected statuses of check-boxes
        mCkbRegex.isSelected = PreferenceManager.usingRegex
        mCkbCaseSensitive.isSelected = PreferenceManager.caseSensitive

        // add all components into this h-box
        children.addAll(mImgvIcon, mTxfInput, mCkbRegex, mCkbCaseSensitive)

        // set the mouse-clicking event on the icon
        mImgvIcon.isPickOnBounds = true
        mImgvIcon.setOnMouseClicked { requestFocus() }

        // set the text-listening events
        mTxfInput.textProperty().addListener { _, oldValue, newValue ->
            mOnTextChangeListener?.onTextChanged(
                    this,
                    oldValue,
                    newValue,
                    SearchOptions(mCkbRegex.isSelected, mCkbCaseSensitive.isSelected)
            )
        }
        mCkbRegex.selectedProperty().addListener { _, _, newValue ->
            mOnTextChangeListener?.onTextChanged(
                    this,
                    mTxfInput.text,
                    mTxfInput.text,
                    SearchOptions(newValue, mCkbCaseSensitive.isSelected)
            )
            requestFocus()
            PreferenceManager.write("using-regex", newValue.toString())
        }
        mCkbCaseSensitive.selectedProperty().addListener { _, _, newValue ->
            mOnTextChangeListener?.onTextChanged(
                    this,
                    mTxfInput.text,
                    mTxfInput.text,
                    SearchOptions(mCkbRegex.isSelected, newValue)
            )
            requestFocus()
            PreferenceManager.write("case-sensitive", newValue.toString())
        }
    }

    /* ======================================== */

    // set the on-text-change-listener
    fun setOnTextChangeListener(onTextChangeListener: OnTextChangeListener) {
        mOnTextChangeListener = onTextChangeListener
    }

    /* ======================================== */

    override fun requestFocus() { mTxfInput.requestFocus() }

    /* ======================================== */

    // interface for listening the text change
    interface OnTextChangeListener {
        fun onTextChanged(searchBar: SearchBar, oldValue: String, newValue: String, searchOptions: SearchOptions)
    }
}