package com.cairosquad.evolvefit.ui.screen.mealsHistory

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.mealsHistory.content.components.MealsHistoryErrorScreen
import com.cairosquad.evolvefit.ui.screen.mealsHistory.content.components.MealsHistoryLoadingScreen
import com.cairosquad.evolvefit.ui.screen.mealsHistory.content.components.MealsHistorySuccessScreen
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.meal_history.MealHistoryEffect
import com.cairosquad.evolvefit.viewmodel.meal_history.MealHistoryScreenState
import com.cairosquad.evolvefit.viewmodel.meal_history.MealHistoryViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MealsHistoryScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MealHistoryViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()
    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is MealHistoryEffect.NavigateBack -> navigateBack()
        }
    }
    MealsHistoryContent(viewModel, state)
}

@Composable
private fun MealsHistoryContent(
    viewModel: MealHistoryViewModel,
    state: MealHistoryScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
    ) {
        Crossfade(
            targetState = state.screenStatus,
            animationSpec = tween(
                durationMillis = 400,
                easing = FastOutSlowInEasing
            )
        ) { screenStatus ->
            when (screenStatus) {
                MealHistoryScreenState.ScreenStatus.LOADING -> {
                    MealsHistoryLoadingScreen()
                }

                MealHistoryScreenState.ScreenStatus.ERROR -> {
                    MealsHistoryErrorScreen {
                        viewModel.loadMealsHistory()
                    }
                }

                MealHistoryScreenState.ScreenStatus.SUCCESS -> {
                    MealsHistorySuccessScreen(viewModel, state)
                }
            }
        }
    }
}