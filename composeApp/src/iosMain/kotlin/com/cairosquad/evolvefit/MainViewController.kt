package com.cairosquad.evolvefit

import androidx.compose.ui.window.ComposeUIViewController
import com.cairosquad.evolvefit.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }