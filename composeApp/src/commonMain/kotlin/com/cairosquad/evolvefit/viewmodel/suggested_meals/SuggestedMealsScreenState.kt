package com.cairosquad.evolvefit.viewmodel.suggested_meals

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.meal_type_breakfast
import evolvefit.composeapp.generated.resources.meal_type_dinner
import evolvefit.composeapp.generated.resources.meal_type_lunch
import evolvefit.composeapp.generated.resources.meal_type_snacks
import org.jetbrains.compose.resources.StringResource

data class SuggestedMealsScreenState(
    val suggestedMeals: List<SuggestedMealUiState> = emptyList(),
    val errorMessage: String? = null,
    val screenStatus: ScreenStatus = ScreenStatus.LOADING,

) {
    data class SuggestedMealUiState(
        val id : String = "",
        val name: String = "",
        val type: MealTypeUiState = MealTypeUiState.Breakfast,
        val calories: Int = 0,
        val imageUrl: String = ""
    )

    enum class MealTypeUiState(val displayName: StringResource) {
        Breakfast(Res.string.meal_type_breakfast),
        Lunch(Res.string.meal_type_lunch),
        Dinner(Res.string.meal_type_dinner),
        Snacks(Res.string.meal_type_snacks)
    }
    enum class ScreenStatus {
        LOADING,
        ERROR,
        SUCCESS
    }
}
