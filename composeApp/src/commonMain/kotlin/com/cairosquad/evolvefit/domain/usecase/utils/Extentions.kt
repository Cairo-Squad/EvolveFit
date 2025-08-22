package com.cairosquad.evolvefit.domain.usecase.utils

fun Float.keepOneDecimal(): Float {
    return (this * 10).toInt() / 10f
}