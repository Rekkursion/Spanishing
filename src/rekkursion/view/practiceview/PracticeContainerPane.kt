package rekkursion.view.practiceview

import javafx.scene.layout.BorderPane

class PracticeContainerPane: BorderPane() {
    // the practice-menu-view
    private val mPracticeMenuView = PracticeMenuView()

    init {
        initialize()
    }

    /* ======================================== */

    // initially, the view is the practice-menu
    fun initialize() {
        center = mPracticeMenuView
    }
}