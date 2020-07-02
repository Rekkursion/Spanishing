package rekkursion.application

import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import rekkursion.manager.LayoutManager
import rekkursion.manager.PropertiesManager

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
            stage.title = PropertiesManager.Strings.title
            // show the primary stage
            stage.show()
        }
    }

    // initialize all views
    private fun initViews(): Parent? = LayoutManager.parentView

    /* ======================================== */

    // launch the application
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java, *args)
        }
    }
}