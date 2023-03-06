package com.learning.sepiataskapp.utils

class Utils {
    companion object {
        fun getDay(dayString: String) =
            when (dayString) {
                "S" -> 1
                "M" -> 2
                "T" -> 3
                "W" -> 4
                "TH" -> 5
                "F" -> 6
                "SA" -> 7
                else -> 0
            }
    }
}