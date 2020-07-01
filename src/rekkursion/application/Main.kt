package rekkursion.application

import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import rekkursion.manager.PropertiesManager
import rekkursion.util.JsonReader
import rekkursion.view.VocabularyListView

class Main: Application() {
    // start the application
    override fun start(primaryStage: Stage?) {
        // set and show the primary stage
        primaryStage?.let { stage ->
            // set the scene of the primary stage
            stage.scene = Scene(
                    initViews(),
                    PropertiesManager.windowWidth,
                    PropertiesManager.windowHeight
            )
            // set the style
            stage.scene.stylesheets.add("rekkursion/css/global.css")
            // set the tile
            stage.title = PropertiesManager.title
            // show the primary stage
            stage.show()
        }
    }

    // initialize all views
    private fun initViews(): Parent? {
        // the border-pane as the parent
        val bdpMain = BorderPane()
        bdpMain.layoutX = 0.0
        bdpMain.layoutY = 0.0
        bdpMain.setPrefSize(
                PropertiesManager.windowWidth,
                PropertiesManager.windowHeight
        )

        // read in all vocabularies from a json file
        val vocList = JsonReader.readAllVocabularies(PropertiesManager.vocabulariesJsonFileLocation)

        val vlv = VocabularyListView(vocList)

        val vlv2 = VocabularyListView(vocList)

        bdpMain.left = vlv2

        bdpMain.center = vlv

        // return the parent border-pane
        return bdpMain
    }

    /* ======================================== */

    // launch the application
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java, *args)
        }
    }
}