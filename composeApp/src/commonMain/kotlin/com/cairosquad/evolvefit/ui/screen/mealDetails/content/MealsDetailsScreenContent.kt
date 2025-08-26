package com.cairosquad.evolvefit.ui.screen.mealDetails.content

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsInteractionListener
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsScreenState


@Composable
fun MealDetailsScreenContent(
    listener: MealDetailsInteractionListener,
    state: MealDetailsScreenState,
    mealId: String
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
        ) { status ->
            when (status) {
                MealDetailsScreenState.ScreenStatus.LOADING -> {
                    MealDetailsLoadingScreen()
                }

                MealDetailsScreenState.ScreenStatus.ERROR -> {
                    MealDetailsErrorScreen(
                        onRetry = listener::onRetryClicked
                    )
                }

                MealDetailsScreenState.ScreenStatus.SUCCESS -> {
                    MealDetailsSuccessScreen(
                        listener = listener,
                        state = state,
                        mealId = mealId
                    )
                }
            }
        }
    }
}