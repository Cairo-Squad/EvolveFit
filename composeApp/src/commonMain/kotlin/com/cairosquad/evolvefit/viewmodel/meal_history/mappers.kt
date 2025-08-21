package com.cairosquad.evolvefit.viewmodel.meal_history

import com.cairosquad.evolvefit.domain.entity.ConsumedMeal
import com.cairosquad.evolvefit.domain.model.MealType

fun ConsumedMeal.toMealHistoryUiState(): MealHistoryScreenState.MealHistoryUiState {
    return MealHistoryScreenState.MealHistoryUiState(
        name = name,
        type = type.toMealHistoryType(),
        calories = calories,
        date = dateTime

    )
}

fun MealType.toMealHistoryType(): MealHistoryScreenState.MealType {
    return when (this) {
        MealType.BREAKFAST -> MealHistoryScreenState.MealType.Breakfast
        MealType.LUNCH -> MealHistoryScreenState.MealType.Lunch
        MealType.SNACKS -> MealHistoryScreenState.MealType.Snacks
        MealType.DINNER -> MealHistoryScreenState.MealType.Dinner
    }
}