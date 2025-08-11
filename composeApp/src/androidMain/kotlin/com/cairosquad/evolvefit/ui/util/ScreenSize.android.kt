package com.cairosquad.evolvefit.ui.util

import android.content.res.Resources

actual object ScreenSize {
    actual val widthDp: Float
        get() = Resources.getSystem().displayMetrics.run { widthPixels / density }

    actual val heightDp: Float
        get() = Resources.getSystem().displayMetrics.run { heightPixels / density }

    actual val density: Float
        get() = Resources.getSystem().displayMetrics.density
}
