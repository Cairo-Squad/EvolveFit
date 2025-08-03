package com.cairosquad.evolvefit.viewmodel.nutrition

import com.cairosquad.evolvefit.ui.screen.nutrition.Meal

data class NutritionScreenState(
    val caloriesConsumed: Int = 0,
    val caloriesGoal: Int = 2000,
    val waterConsumedLiters: Float = 0f,
    val waterGoalLiters: Float = 2f,
    val remainingCalories: Float=0f,
    val mealsToday: List<Meal> = emptyList(),
    val suggestedMeals: List<SuggestedMeal> = emptyList(),
    val mealHistory: List<MealHistoryItem> = emptyList(),
    val isAddWaterSheetVisible: Boolean = false,
    val isAddMealSheetVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {
    data class SuggestedMeal(
        val name: String="",
        val type: String="",
        val calories: Int=0,
        )

    data class MealHistoryItem(
        val name: String="",
        val type: String="",
        val calories: Int=0,
        val date: String=""
    )
}