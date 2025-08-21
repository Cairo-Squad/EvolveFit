package com.cairosquad.evolvefit.ui.util

import com.cairosquad.evolvefit.MainActivity

actual fun recreateCurrentScreen() {
    MainActivity.instance.recreate()
}