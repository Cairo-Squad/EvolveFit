package com.cairosquad.evolvefit.viewmodel.suggested_meals



import com.cairosquad.evolvefit.entity.nutrition.MealType
import com.cairosquad.evolvefit.entity.nutrition.SuggestedMeal

fun SuggestedMeal.toSuggestedMealUiState() : SuggestedMealsScreenState.SuggestedMealUiState{
    return SuggestedMealsScreenState.SuggestedMealUiState(
        name = name,
        type = type.toMealUiState(),
        calories= calories,
        imageUrl = imageUrl

    )
}

fun MealType.toMealUiState(): SuggestedMealsScreenState.MealTypeUiState {
    return when (this) {
        MealType.BREAKFAST -> SuggestedMealsScreenState.MealTypeUiState.Breakfast
        MealType.LUNCH ->  SuggestedMealsScreenState.MealTypeUiState.Lunch
        MealType.DINNER ->SuggestedMealsScreenState.MealTypeUiState.Dinner
        MealType.SNACK -> SuggestedMealsScreenState.MealTypeUiState.Snacks
    }
}