package com.cairosquad.evolvefit.ui.screen.nutrition

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute
import com.cairosquad.evolvefit.ui.navigation.navBar.Scaffold
import com.cairosquad.evolvefit.ui.screen.nutrition.content.NutritionContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionEffect
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NutritionScreen(
    navigateToSuggestedMeals: () -> Unit,
    navigateToMealDetails: (mealId: String, onNavigateBack: (() -> Unit)?) -> Unit,
    navigateToMealsHistory: () -> Unit,
    onSelectNavBarRoute: (navBarRoute: NavBarRoute) -> Unit,
    nutritionViewModel: NutritionViewModel = koinViewModel()
) {
    val state by nutritionViewModel.screenState.collectAsState()

    ObserveAsEffect(nutritionViewModel.effect) { effect ->
        when (effect) {
            NutritionEffect.NavigateToMealHistory -> navigateToMealsHistory()
            is NutritionEffect.NavigateToSuggestedMealDetails -> navigateToMealDetails(
                effect.mealId,
                nutritionViewModel::loadNutritionData
            )
            NutritionEffect.NavigateToSuggestedMeals -> navigateToSuggestedMeals()
        }
    }

    Scaffold(
        currentRoute = NavBarRoute.Nutrition,
        onSelectNavBarRoute = onSelectNavBarRoute
    ) {
        NutritionContent(
            state = state,
            listener = nutritionViewModel,
        )
    }
}
