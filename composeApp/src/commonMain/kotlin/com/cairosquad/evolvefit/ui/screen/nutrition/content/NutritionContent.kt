package com.cairosquad.evolvefit.ui.screen.nutrition.content

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.SnackBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.screen.nutrition.component.AddWaterIntakeBottomSheet
import com.cairosquad.evolvefit.ui.screen.nutrition.component.MealAddedSnackBar
import com.cairosquad.evolvefit.ui.screen.nutrition.component.MealCantAddSnackBar
import com.cairosquad.evolvefit.ui.screen.nutrition.component.MealTypeDropdownMenu
import com.cairosquad.evolvefit.ui.screen.nutrition.component.NutritionHeader
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionInteractionListener
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import com.cairosquad.evolvefit.ui.screen.nutrition.component.WaterAddedSnackBar
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.meal_added_snackbar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NutritionContent(
    state: NutritionScreenState,
    listener: NutritionInteractionListener,
) {
    RefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = { listener.onRefresh() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.surfaces.surface)
        ) {
            NutritionHeader()
            Crossfade(
                targetState = state.screenStatus,
                animationSpec = tween(
                    durationMillis = 400,
                    easing = FastOutSlowInEasing
                )
            ) {
                when (state.screenStatus) {
                    NutritionScreenState.ScreenStatus.SUCCESS -> {
                        NutritionSuccessScreen(state, listener)
                    }

                    NutritionScreenState.ScreenStatus.LOADING -> {
                        NutritionLoadingScreen()
                    }

                    NutritionScreenState.ScreenStatus.FAIL -> {
                        NutritionErrorScreen { listener.onRetryClicked() }
                    }
                }
            }
        }
        if (state.screenStatus == NutritionScreenState.ScreenStatus.SUCCESS) {
            MealTypeDropdownMenu(
                state = state,
                listener = listener,
            )
            AddWaterIntakeBottomSheet(
                modifier = Modifier.align(Alignment.BottomCenter),
                listener = listener,
                state = state
            )
            SnackBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                text = stringResource(state.snackBarMessage),
                isVisible = state.isSnackBarVisible,
                addNavBarPadding = false,
                icon = painterResource(state.snackBarIcon)
            )
        }
    }
}