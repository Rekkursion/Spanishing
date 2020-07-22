package rekkursion.view.searchbar

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import rekkursion.enumerate.Strings
import rekkursion.manager.PreferenceManager
import rekkursion.manager.PropertiesManager
import rekkursion.util.SearchOptions
import rekkursion.view.SpanishSupportedTextField
import rekkursion.view.styled.Styled
import rekkursion.view.styled.StyledCheckBox
import rekkursion.view.styled.StyledHBox
import rekkursion.view.styled.StyledVBox

open class SearchBar: StyledVBox() {
    // the image for showing the search-icon
    private val mImgvIcon = ImageView()

    // the text-field for users to input
    protected val mTxfInput = SpanishSupportedTextField(200.0)

    // the check-box for determining applying regex or not
    private val mCkbRegex = StyledCheckBox(Strings.UsingRegexOrNot)

    // the check-box for determining being case-sensitive or not
    private val mCkbCaseSensitive = StyledCheckBox(Strings.CaseSensitiveOrNot)

    // the h-box as container for basic search-bar
    private val mHbxContainer = StyledHBox(mImgvIcon, mTxfInput, mCkbRegex, mCkbCaseSensitive)

    // the listener for listening the search-status
    private var mOnSearchStatusChangeListener: OnSearchStatusChangeListener? = null

    // some search-options
    private val mSearchOpts = SearchOptions(
            PreferenceManager.usingRegex,
            PreferenceManager.caseSensitive,
            PreferenceManager.textsSearchOn,
            PreferenceManager.pospsSearchOn
    )
    val searchOptionsCopied = mSearchOpts.copy()

    init {
        // set the icon image of the image-view
        mImgvIcon.image = Image("rekkursion/res/search.png", PropertiesManager.searchIconSize, PropertiesManager.searchIconSize, false, false)

        // set the padding-left of the check-boxes
        mCkbRegex.padding = Insets(0.0, 0.0, 0.0, PropertiesManager.generalPadding)

        // set the selected statuses of check-boxes
        mCkbRegex.isSelected = PreferenceManager.usingRegex
        mCkbCaseSensitive.isSelected = PreferenceManager.caseSensitive

        // set the mouse-clicking event on the icon
        mImgvIcon.isPickOnBounds = true
        mImgvIcon.setOnMouseClicked { requestFocus() }

        // add the h-box-as-container into this v-box
        children.add(mHbxContainer)

        // set the text-listening events
        mTxfInput.textProperty().addListener { _, oldValue, newValue ->
            notifyOptionsChanged(oldValue, newValue)
        }
        mCkbRegex.selectedProperty().addListener { _, _, newValue ->
            mSearchOpts.usingRegex = newValue
            notifyOptionsChanged()
            requestFocus()
            PreferenceManager.write("using-regex", newValue.toString())
        }
        mCkbCaseSensitive.selectedProperty().addListener { _, _, newValue ->
            mSearchOpts.caseSensitive = newValue
            notifyOptionsChanged()
            requestFocus()
            PreferenceManager.write("case-sensitive", newValue.toString())
        }
    }

    /* ======================================== */

    // set the listen of search-status changing
    fun setOnSearchStatusChangeListener(onSearchStatusChangeListener: OnSearchStatusChangeListener) {
        mOnSearchStatusChangeListener = onSearchStatusChangeListener
    }

    // notify that some search-options might be changed
    private fun notifyOptionsChanged(oldText: String = mTxfInput.text, newValue: String = mTxfInput.text) {
        mOnSearchStatusChangeListener?.onSearchStatusChanged(this, oldText, newValue, mSearchOpts)
    }

    // set the text-sizes of all sub-views
    fun setTextSizes(textSize: Int) { Styled.unifyTextSize(textSize, mTxfInput, mCkbRegex, mCkbCaseSensitive) }

    // set the search-options by another search-options
    fun setSearchOptions(searchOptions: SearchOptions) {
        mSearchOpts.setAll(searchOptions)
        notifyOptionsChanged(); requestFocus()
    }

    // add a new node at the first row (the tail of basic search-bar)
    protected fun pushNodesAtFirstRow(vararg nodes: Node) {
        mHbxContainer.children.addAll(*nodes)
    }

    /* ======================================== */

    override fun requestFocus() { mTxfInput.requestFocus() }

    /* ======================================== */

    // interface for listening the text change
    interface OnSearchStatusChangeListener {
        fun onSearchStatusChanged(searchBar: SearchBar, oldValue: String, newValue: String, searchOptions: SearchOptions)
    }
}