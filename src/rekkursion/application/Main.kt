package rekkursion.application

import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import rekkursion.enumerate.Strings
import rekkursion.manager.LayoutManager
import rekkursion.manager.PreferenceManager

class Main: Application() {
    // start the application
    override fun start(primaryStage: Stage?) {
        // set and show the primary stage
        primaryStage?.let { stage ->
            // set the scene of the primary stage
            stage.scene = Scene(
                    initViews(),
                    PreferenceManager.windowWidth,
                    PreferenceManager.windowHeight
            )
            // set the style
            stage.scene.stylesheets.add("rekkursion/css/global.css")
            // set the tile
            stage.title = Strings.get(Strings.Title)
            // show the primary stage
            stage.show()
        }

        // the listener for the window width
        primaryStage?.widthProperty()?.addListener { _, _, newValue ->
            PreferenceManager.write("window-width", newValue.toString())
        }

        // the listener for the window height
        primaryStage?.heightProperty()?.addListener { _, _, newValue ->
            PreferenceManager.write("window-height", newValue.toString())
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