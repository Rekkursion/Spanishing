package rekkursion.util

import javafx.scene.control.Alert
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
}