package rekkursion.view.practiceview

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import rekkursion.manager.LayoutManager
import rekkursion.manager.PropertiesManager

class PracticeMenuView: VBox(PropertiesManager.generalSpacing) {
    // the button for the single choice problems
    private val mBtnSingleChoice = Button(PropertiesManager.Strings.singleChoiceProblem)

    // the button for the spelling problems
    private val mBtnSpelling = Button(PropertiesManager.Strings.spellingProblem)

    init {
        // set the alignments of all uis to the center
        mBtnSingleChoice.alignment = Pos.CENTER
        mBtnSpelling.alignment = Pos.CENTER
        alignment = Pos.CENTER

        // set the widths of buttons
        mBtnSingleChoice.prefWidth = PropertiesManager.windowWidth
        mBtnSpelling.prefWidth = PropertiesManager.windowWidth

        // set the font sizes of buttons
        mBtnSingleChoice.style = "-fx-font-size: 18;"
        mBtnSpelling.style = "-fx-font-size: 18;"

        // add all buttons into this v-box
        children.addAll(mBtnSingleChoice, mBtnSpelling)

        // the event of clicking on single-choice-problems button
        mBtnSingleChoice.setOnMouseClicked {
            LayoutManager.switchPracticeContent(Text("good"))
        }
    }
}