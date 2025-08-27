package com.cairosquad.evolvefit.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute
import com.cairosquad.evolvefit.ui.navigation.navBar.Scaffold
import com.cairosquad.evolvefit.ui.screen.home.content.HomeContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenEffect
import com.cairosquad.evolvefit.viewmodel.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navigateToWorkout: (id: String, onNavigateBack: (() -> Unit)?) -> Unit,
    onSelectNavBarRoute: (navBarRoute: NavBarRoute) -> Unit,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val state by homeViewModel.screenState.collectAsState()

    HandleHomeEffects(
        homeViewModel = homeViewModel,
        navigateToWorkout = navigateToWorkout,
    )

    Scaffold(
        currentRoute = NavBarRoute.Home,
        onSelectNavBarRoute = onSelectNavBarRoute
    ) {
        HomeContent(
            state = state,
            listener = homeViewModel
        )
    }
}

@Composable
private fun HandleHomeEffects(
    homeViewModel: HomeViewModel,
    navigateToWorkout: (id: String, onNavigateBack: (() -> Unit)?) -> Unit,
) {
    ObserveAsEffect(homeViewModel.effect) { effect ->
        when (effect) {
            is HomeScreenEffect.NavigateToWorkout -> {
                navigateToWorkout(effect.workoutId, homeViewModel::loadFavoriteWorkouts)
            }

            is HomeScreenEffect.ShowErrorSnackBar -> {
                TODO()
            }
        }
    }
}
