package com.cairosquad.evolvefit.ui.screen.mealDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.theme.UpdateStatusBarIconsForTheme
import com.cairosquad.evolvefit.ui.screen.mealDetails.content.MealDetailsScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.ui.util.PlatformBackHandler
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsEffect
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MealDetailsScreen(
    mealId: String,
    navigateBack: () -> Unit,
    viewModel: MealDetailsViewModel = koinViewModel(parameters = { parametersOf(mealId) })
) {
    val state by viewModel.screenState.collectAsState()

    val appInDarkMode = Theme.isDark
    var isStatusBarIconsLight by remember { mutableStateOf(true) }
    val resetStatusBarIcons = { isStatusBarIconsLight = appInDarkMode }
    UpdateStatusBarIconsForTheme(isStatusBarIconsLight)
    PlatformBackHandler {
        resetStatusBarIcons()
        navigateBack()
    }

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is MealDetailsEffect.NavigateBack -> {
                resetStatusBarIcons()
                navigateBack()
            }
        }
    }
    MealDetailsScreenContent(
        listener = viewModel,
        state = state,
        mealId = mealId
    )
}