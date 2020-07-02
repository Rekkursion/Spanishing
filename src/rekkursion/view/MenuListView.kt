package rekkursion.view

import javafx.scene.control.ListView
import rekkursion.manager.LayoutManager
import rekkursion.manager.PropertiesManager

class MenuListView: ListView<String>() {
    // the previous selected index of the menu-list
    private var mPreviousSelectedIdx = 0

    init {
        // add all items from the menu-list
        items.addAll(PropertiesManager.Strings.menuList)

        // add the stylesheet of the menu-list-view
        stylesheets.add("rekkursion/css/menu_list_view.css")

        // initially select the first item of the menu-list
        selectionModel.select(0)

        // set the event of on-mouse-click
        setOnMouseClicked {
            // get the selected index
            val selectedIdx = selectionModel.selectedIndex
            // if the selected index is different from the previous one
            if (selectedIdx != mPreviousSelectedIdx) {
                // set the center according to the selected index
                LayoutManager.setCenterByMenuIdx(selectedIdx)
                // re-set the previous selected index
                mPreviousSelectedIdx = selectedIdx
            }
        }
    }
}

