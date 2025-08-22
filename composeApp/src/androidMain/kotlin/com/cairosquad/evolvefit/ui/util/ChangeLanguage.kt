package com.cairosquad.evolvefit.ui.util

import java.util.Locale

actual fun changeLanguage(language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
}