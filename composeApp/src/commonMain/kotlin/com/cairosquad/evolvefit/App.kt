package com.cairosquad.evolvefit

import androidx.compose.runtime.Composable
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.ui.navigation.NavigationHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        NavigationHost()
    }
}