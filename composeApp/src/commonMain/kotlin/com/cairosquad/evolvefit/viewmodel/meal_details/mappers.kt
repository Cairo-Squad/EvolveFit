package com.cairosquad.evolvefit.viewmodel.meal_details

import com.cairosquad.evolvefit.entity.nutrition.Meal
import com.cairosquad.evolvefit.entity.nutrition.MealType


fun Meal.toMealDetailsUiState(): MealDetailsScreenState.MealDetailsUiState {
    return MealDetailsScreenState.MealDetailsUiState(
        name = name,
        imgUrl = imageUrl,
        description = description,
        mealType = type.toMealUiState(),
        calories= nutrition.calories,
        protein =nutrition.protein,
        carbs = nutrition.carbs,
        fat  =  nutrition.fat,
        ingredients = ingredients

    )
}
fun MealType.toMealUiState(): MealDetailsScreenState.MealTypeUiState {
    return when (this) {
        MealType.BREAKFAST -> MealDetailsScreenState.MealTypeUiState.Breakfast
        MealType.LUNCH ->  MealDetailsScreenState.MealTypeUiState.Lunch
        MealType.DINNER -> MealDetailsScreenState.MealTypeUiState.Dinner
        MealType.SNACK -> MealDetailsScreenState.MealTypeUiState.Snacks
    }
}