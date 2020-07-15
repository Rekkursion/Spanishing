package rekkursion.util

import kotlin.math.floor
import kotlin.math.log10

// get the number of digits of this integer
fun Int.digits(): Int = floor(log10(this.toDouble())).toInt() + 1

// convert a character into an ascii value
fun Char.toAscii(): Int = this.toByte().toInt()

// check if a character is a spanish alphabet or not
fun Char.isEspAlphabet(): Boolean = this.isLetter()
        || this == 'á' || this == 'é' || this == 'í' || this == 'ó' || this == 'ú'
        || this == 'ü' || this == 'ñ'