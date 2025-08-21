package com.cairosquad.evolvefit.viewmodel.suggested_meals



import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.model.MealType

fun SuggestedMeal.toSuggestedMealUiState() : SuggestedMealsScreenState.SuggestedMealUiState{
    return SuggestedMealsScreenState.SuggestedMealUiState(
        id = id,
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
        MealType.SNACKS -> SuggestedMealsScreenState.MealTypeUiState.Snacks
    }
}