package com.cairosquad.evolvefit.ui.util

fun UInt.toFormattedString(): String {
    return this.toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}

fun Float.toFormattedString(): String {
    val sign = if (this < 0) "-" else ""
    val absValue = kotlin.math.abs(this)

    val intPart = absValue.toInt().toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()

    val decimalPart = ((absValue * 100) % 100).toInt()
        .toString()
        .padStart(2, '0')
        .removeSuffix("0")

    return "$sign$intPart.$decimalPart"
}

fun Float.toString(decimals: Int): String {
    if (decimals == 0) return this.toInt().toString()
    require(decimals >= 1) { "Decimals must not be negative" }
    val (intPart, fractionPart) = this.toString().split(".")
    return intPart + "." + fractionPart.take(decimals)
}