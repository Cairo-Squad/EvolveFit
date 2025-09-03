package com.cairosquad.evolvefit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController
import androidx.navigation.NavUri

@Composable
fun DeepLinkListener(navController: NavController) {
    DisposableEffect(Unit) {
        ExternalUriHandler.listener =  { uri ->
            navController.navigate(NavUri(uri))
        }
        onDispose {
            ExternalUriHandler.listener = null
        }
    }
}