package rekkursion.view

import rekkursion.enumerate.Strings
import rekkursion.manager.PropertiesManager
import rekkursion.view.styled.Styled
import rekkursion.view.styled.StyledButton
import rekkursion.view.styled.StyledHBox

class SelectingButtonBar: StyledHBox() {
    // the button for selecting all
    private val mBtnSelectAll = StyledButton(Strings.SelectAll)

    // the button for unselecting all
    private val mBtnUnselectAll = StyledButton(Strings.UnselectAll)

    // the button for reverse-selecting
    private val mBtnReverseSelect = StyledButton(Strings.ReverseSelect)

    // the selecting-listener triggered when one of these buttons has been clicked
    private var mOnSelectingListener: OnSelectingListener? = null

    init {
        mBtnSelectAll.maxWidth = 100.0
        mBtnUnselectAll.maxWidth = 100.0
        mBtnReverseSelect.maxWidth = 100.0
        Styled.unifyTextSize(PropertiesManager.searchBarTextSize, mBtnSelectAll, mBtnUnselectAll, mBtnReverseSelect)

        // add all sub-views into this node
        children.addAll(mBtnSelectAll, mBtnUnselectAll, mBtnReverseSelect)

        // set the corresponding events respectively
        mBtnSelectAll.setOnAction { mOnSelectingListener?.onSelectAll() }
        mBtnUnselectAll.setOnAction { mOnSelectingListener?.onUnselectAll() }
        mBtnReverseSelect.setOnAction { mOnSelectingListener?.onReverseSelect() }
    }

    /* ======================================== */

    // set the selecting-listener triggered when one of these buttons has been clicked
    fun setOnSelectingListener(onSelectingListener: OnSelectingListener) {
        mOnSelectingListener = onSelectingListener
    }

    /* ======================================== */

    // the selecting-listener
    interface OnSelectingListener {
        fun onSelectAll() {}
        fun onUnselectAll() {}
        fun onReverseSelect() {}
    }
}