package rekkursion.manager

import javafx.scene.Node
import javafx.scene.layout.BorderPane
import rekkursion.view.MenuListView
import rekkursion.view.pref.PreferencesPage
import rekkursion.view.VocabularyListView
import rekkursion.view.practiceview.PracticeContainerPane
import rekkursion.view.practiceview.PracticeMenuView

object LayoutManager {
    // the border-pane as the parent
    private val mBdpMain: BorderPane = BorderPane()
    val parentView get() = mBdpMain

    // the vocabulary-list-view
    private val mVocListView: VocabularyListView

    // the menu-list-view
    private val mMenuListView: MenuListView

    // the practice-pane as a container of practice stuffs
    private val mPracticePane: PracticeContainerPane

    // the practice-menu-view
    private val mPracticeMenuView = PracticeMenuView()

    init {
        // set the location of bdp-main
        mBdpMain.layoutX = 0.0
        mBdpMain.layoutY = 0.0
        // set the size (width & height) of bpd-main
        mBdpMain.setPrefSize(
                PreferenceManager.windowWidth,
                PreferenceManager.windowHeight
        )

        // create a vocabulary-list-view which is to be shown at the center
        mVocListView = VocabularyListView(VocManager.copiedVocList)
        // set the created vocabulary-list-view at the center
        mBdpMain.center = mVocListView

        // create a menu-list-view which is to be shown at the left
        mMenuListView = MenuListView()
        // set the created menu-list-view at the left
        mBdpMain.left = mMenuListView

        // create a practice-pane
        mPracticePane = PracticeContainerPane()
    }

    /* ======================================== */

    // set the view at the center by the user-selected menu index
    fun setCenterByMenuIdx(idx: Int) {
        // set the center according to the passed-in index
        mBdpMain.center = when (idx) {
            0 -> mVocListView
            1 -> {
                if (mPracticePane.center == null)
                    switchPracticeContent()
                mPracticePane
            }
            2 -> PreferencesPage()
            else -> null
        }
    }

    // switch the content-view of the practice-pane
    fun switchPracticeContent(node: Node) {
        mPracticePane.center = node
    }

    // switch the content-view to the menu page
    fun switchPracticeContent() {
        mPracticePane.center = mPracticeMenuView
    }
}