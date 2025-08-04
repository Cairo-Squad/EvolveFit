package com.cairosquad.evolvefit.ui.screen.nutrition

import androidx.compose.runtime.Composable
import com.cairosquad.evolvefit.ui.screen.nutrition.content.NutritionScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionEffect
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NutritionScreen(
    navigateToSuggestedMeals: () -> Unit,
    navigateToMealDetails: (Long) -> Unit,
    navigateToMealsHistory: () -> Unit,
    nutritionViewModel: NutritionViewModel = koinViewModel()) {
    ObserveAsEffect(nutritionViewModel.effect) { effect ->
        when (effect) {
            NutritionEffect.NavigateToMealHistory -> {
                navigateToMealsHistory()
            }

            is NutritionEffect.NavigateToSuggestedMealDetails -> {
                navigateToMealDetails(effect.mealId)
            }

            NutritionEffect.NavigateToSuggestedMeals -> {
                navigateToSuggestedMeals()
            }
        }
    }
    NutritionScreenContent(nutritionViewModel)
}