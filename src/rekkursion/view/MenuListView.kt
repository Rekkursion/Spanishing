package rekkursion.view

import javafx.scene.control.ListView
import rekkursion.manager.LayoutManager
import rekkursion.manager.PropertiesManager
class MenuListView: ListView<String>() {
    init {
        // add all items from the menu-list
        items.addAll(PropertiesManager.menuList)

        // add the stylesheet of the menu-list-view
        stylesheets.add("rekkursion/css/menu_list_view.css")

        // set the event of on-mouse-click
        setOnMouseClicked { LayoutManager.setCenterByMenuIdx(selectionModel.selectedIndex) }
    }
}

