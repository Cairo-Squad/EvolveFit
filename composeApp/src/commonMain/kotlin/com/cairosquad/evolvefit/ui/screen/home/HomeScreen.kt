package com.cairosquad.evolvefit.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.base.ErrorState
import com.cairosquad.evolvefit.viewmodel.home.HomeInteractionListener
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenEffect
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenState
import com.cairosquad.evolvefit.viewmodel.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navigateToWorkout: (Long) -> Unit,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val state by homeViewModel.screenState.collectAsState()

    ObserveAsEffect(homeViewModel.effect) { effect ->
        when (effect) {
            is HomeScreenEffect.NavigateToWorkout -> {
                navigateToWorkout(effect.workoutId)
            }

            is HomeScreenEffect.ShowErrorSnackBar -> {
                TODO()
            }
        }
    }

    if (state.isLoading) {
        HomeLoadingContent()
    } else if (state.error != null) {
        HomeErrorContent(
            error = state.error!!,
            onRetry = homeViewModel::onRetryClicked
        )
    } else {
        HomeContent(
            state = state,
            interactionListener = homeViewModel
        )
    }
}

@Composable
private fun HomeContent(
    state: HomeScreenState,
    interactionListener: HomeInteractionListener
) {
    // TODO
}

@Composable
private fun HomeErrorContent(
    error: ErrorState,
    onRetry: () -> Unit
) {
    // TODO
}

@Composable
private fun HomeLoadingContent() {
    // TODO
}