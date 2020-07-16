package rekkursion.view

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import rekkursion.view.styled.StyledButton

class AlphabetButton(alphabet: String,
                     shortcutKey: KeyCode? = null,
                     useCtrl: Boolean = false,
                     useShift: Boolean = false,
                     useAlt: Boolean = false): StyledButton(alphabet) {

    // the key-code of the shortcut
    private val mShortcutKey = shortcutKey

    // if the shortcut must be used w/ the ctrl-key
    private val mUseCtrl = useCtrl

    // if the shortcut must be used w/ the shift-key
    private val mUseShift = useShift

    // if the shortcut must be used w/ the alt-key
    private val mUseAlt = useAlt

    /* ======================================== */

    // fire this button through the shortcut
    fun fire(event: KeyEvent) {
        if (event.code == mShortcutKey
                && !mUseCtrl.xor(event.isControlDown)
                && !mUseAlt.xor(event.isAltDown)
                && !mUseShift.xor(event.isShiftDown))
            fire()
    }
}