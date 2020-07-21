package rekkursion.util

import javafx.scene.Node
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import rekkursion.enumerate.Strings

object AlertUtils {
    // create and return a confirmation alert
    fun createConfirmAlert(contentStrEnum: Strings): Alert {
        // create an alert w/ confirmation-type
        val alert = Alert(Alert.AlertType.CONFIRMATION)

        // set the title (fixed)
        alert.title = Strings.get(Strings.AlertConfirmationTitle)
        // set the header-text w/ the passed content string
        alert.headerText = Strings.get(contentStrEnum)
        // set the content-text w/ the header message (fixed)
        alert.contentText = Strings.get(Strings.AlertConfirmationHeaderMsg)

        // register to the str-enums
        Strings.register(alert,
                GenericString(Strings.AlertConfirmationTitle),
                GenericString(contentStrEnum),
                GenericString(Strings.AlertConfirmationHeaderMsg)
        )

        // return the created confirmation alert
        return alert
    }

    // create and return a user-defined alert with a confirmation button
    fun createUserDefinedAlert(node: Node): Alert {
        // create an alert w/ confirmation-type
        val alert = Alert(Alert.AlertType.NONE)

        // set the content
        alert.dialogPane.content = node
        // set the applying & canceling buttons
        alert.buttonTypes.addAll(ButtonType.APPLY, ButtonType.CANCEL)

        // return the created alert
        return alert
    }
}