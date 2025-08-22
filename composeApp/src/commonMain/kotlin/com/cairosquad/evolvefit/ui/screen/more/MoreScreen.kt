package com.cairosquad.evolvefit.ui.screen.more

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute
import com.cairosquad.evolvefit.ui.navigation.navBar.Scaffold
import com.cairosquad.evolvefit.ui.screen.more.content.MoreScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.more.MoreEffect
import com.cairosquad.evolvefit.viewmodel.more.MoreViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MoreScreen(
    navigateToFavorites: () -> Unit,
    navigateToNotificationSettings: () -> Unit,
    onLogout: () -> Unit,
    navigateToEditProfile: () -> Unit,
    onSelectNavBarRoute: (navBarRoute: NavBarRoute) -> Unit,
    viewModel: MoreViewModel = koinViewModel(),
    ) {
    val state by viewModel.screenState.collectAsState()

    LaunchedEffect(key1 = true){
        viewModel.onReturnToScreen()
    }
    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is MoreEffect.NavigateToFavorites -> {
                navigateToFavorites()
            }
            is MoreEffect.NavigateToNotificationSettings -> {
                navigateToNotificationSettings()
            }
            is MoreEffect.Logout -> {
                onLogout()
            }

            MoreEffect.NavigateToEditProfile -> {
                navigateToEditProfile()
            }
        }
    }

    Scaffold(
        currentRoute = NavBarRoute.More,
        onSelectNavBarRoute = onSelectNavBarRoute
    ) {
        MoreScreenContent(
            state = state,
            listener = viewModel
        )
    }
}