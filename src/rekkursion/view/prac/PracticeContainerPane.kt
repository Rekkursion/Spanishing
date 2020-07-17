package rekkursion.view.prac

import javafx.scene.layout.BorderPane

class PracticeContainerPane: BorderPane() {
    override fun requestFocus() {
        children.getOrNull(0)?.requestFocus() ?: super.requestFocus()
    }
}