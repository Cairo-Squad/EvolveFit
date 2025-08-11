package com.cairosquad.evolvefit.ui.util

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
actual object ScreenSize {
    actual val widthDp: Float
        get() = UIScreen.mainScreen.bounds.useContents { size.width.toFloat() }

    actual val heightDp: Float
        get() = UIScreen.mainScreen.bounds.useContents { size.height.toFloat() }

    actual val density: Float
        get() = UIScreen.mainScreen.scale.toFloat()
}