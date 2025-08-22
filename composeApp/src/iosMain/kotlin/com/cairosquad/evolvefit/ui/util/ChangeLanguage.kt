package com.cairosquad.evolvefit.ui.util

import platform.Foundation.NSUserDefaults

actual fun changeLanguage(language: String) {
    NSUserDefaults.standardUserDefaults
        .setObject(arrayListOf(language), "AppleLanguages")
}