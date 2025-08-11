package com.cairosquad.evolvefit.ui.util

object Formatter {

    fun oneDecimalPlaceWithThousandSeparators(number: Float): String {
        return addThousandSeparators(keepOneDecimalPlace(number))
    }

    fun keepOneDecimalPlace(number: Float): String {
        val rounded = (number * 10).toInt() / 10f
        val isWholeNumber = rounded % 1 == 0f

        val numberStr = if (isWholeNumber) {
            rounded.toInt().toString()
        } else {
            rounded.toString()
        }
        return numberStr
    }


    fun addThousandSeparators(number: String): String {
        val parts = number.split(".")
        val intPart = parts[0]
        val decimalPart = if (parts.size > 1) parts[1] else null

        val withCommas = intPart.reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()
        return if (decimalPart != null) "$withCommas.$decimalPart" else withCommas
    }
}