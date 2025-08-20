package com.cairosquad.evolvefit.ui.navigation.navBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute

@Composable
fun Scaffold(
    currentRoute: NavBarRoute,
    onSelectNavBarRoute: (navBarRoute: NavBarRoute) -> Unit,
    isNavBarVisible: Boolean = true,
    content: @Composable BoxScope.() -> Unit
){
    Scaffold(
        navBar = {
            EvolveNavigationBar(
                currentRoute = currentRoute,
                onSelectNavBarRoute = onSelectNavBarRoute,
                isVisible = isNavBarVisible
            )
         },
        content = content
    )
}

@Composable
fun Scaffold(
    navBar: @Composable () -> Unit,
    content: @Composable BoxScope.() -> Unit
){
    Column(
        Modifier.fillMaxSize()
    ){
        Box(
            Modifier.weight(1f),
            content = content
        )
        navBar()
    }
}