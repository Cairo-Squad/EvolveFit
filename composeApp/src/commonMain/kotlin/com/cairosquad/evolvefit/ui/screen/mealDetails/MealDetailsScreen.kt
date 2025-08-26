package com.cairosquad.evolvefit.ui.screen.mealDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.screen.mealDetails.content.MealDetailsScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
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
    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is MealDetailsEffect.NavigateBack -> navigateBack()
        }
    }
    MealDetailsScreenContent(
        listener = viewModel,
        state = state ,
        mealId = mealId
    )
}
