package com.cairosquad.evolvefit.viewmodel.suggested_meals



import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.model.MealType

fun Meal.toSuggestedMealUiState() : SuggestedMealsScreenState.SuggestedMealUiState{
    return SuggestedMealsScreenState.SuggestedMealUiState(
        name = name,
        type = type.toMealUiState(),
        calories= nutrition.caloriesInKcal,
        imageUrl = imageUrl

    )
}

fun MealType.toMealUiState(): SuggestedMealsScreenState.MealTypeUiState {
    return when (this) {
        MealType.BREAKFAST -> SuggestedMealsScreenState.MealTypeUiState.Breakfast
        MealType.LUNCH ->  SuggestedMealsScreenState.MealTypeUiState.Lunch
        MealType.DINNER ->SuggestedMealsScreenState.MealTypeUiState.Dinner
        MealType.SNACKS -> SuggestedMealsScreenState.MealTypeUiState.Snacks
    }
}