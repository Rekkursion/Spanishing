package rekkursion.util

import rekkursion.enumerate.Strings

class GenericString(strEnum: Strings? = null, str: String? = null) {
    private val mStr: String? = str
    private val mStrEnum: Strings? = strEnum
    val str get() = mStr ?: Strings.get(mStrEnum ?: Strings.Empty)

    class Array(vararg strs: GenericString) {
        private val mStrs = strs

        fun get(idx: Int): String = mStrs.getOrNull(idx)?.str ?: ""

        override fun toString(): String = mStrs.joinToString("") { it.str }
    }
}