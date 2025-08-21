package com.cairosquad.evolvefit.viewmodel.meal_details

import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.model.MealType


fun Meal.toMealDetailsUiState(): MealDetailsScreenState.MealDetailsUiState {
    return MealDetailsScreenState.MealDetailsUiState(
        name = name,
        imgUrl = imageUrl,
        description = description,
        mealType = type.toMealUiState(),
        calories= nutrition.caloriesInKcal,
        protein =nutrition.proteinInGrams,
        carbs = nutrition.carbsInGrams,
        fat  =  nutrition.fatInGrams,
        ingredients = ingredients

    )
}
fun MealType.toMealUiState(): MealDetailsScreenState.MealTypeUiState {
    return when (this) {
        MealType.BREAKFAST -> MealDetailsScreenState.MealTypeUiState.Breakfast
        MealType.LUNCH ->  MealDetailsScreenState.MealTypeUiState.Lunch
        MealType.DINNER -> MealDetailsScreenState.MealTypeUiState.Dinner
        MealType.SNACKS -> MealDetailsScreenState.MealTypeUiState.Snacks
    }
}