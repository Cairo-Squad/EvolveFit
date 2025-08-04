package com.cairosquad.evolvefit.viewmodel.nutrition

import org.jetbrains.compose.resources.DrawableResource

data class NutritionScreenState(
    val caloriesConsumed: Int = 0,
    val caloriesGoal: Int = 0,
    val waterConsumedLiters: Float = 0f,
    val waterGoalLiters: Float = 0f,
    val remainingCalories: Float = 0f,
    val todayMeals: List<TodayMeal> = emptyList(),
    val suggestedMeals: List<SuggestedMeal> = emptyList(),
    val mealHistory: List<MealHistory> = emptyList(),
    val isAddWaterSheetVisible: Boolean = false,
    val isAddMealSheetVisible: Boolean = false,
    val isAddMealSnackBarVisible: Boolean = false,
    val isDroppedMenuVisible: Boolean = false,
    val mealTypeSelected: MealType = MealType.Breakfast,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {
    data class SuggestedMeal(
        val name: String = "",
        val type: MealType = MealType.Breakfast,
        val calories: Int = 0,
    )

    data class MealHistory(
        val name: String = "",
        val type: MealType = MealType.Breakfast,
        val calories: Int = 0,
        val date: String = ""
    )

    data class TodayMeal(
        val type: MealType = MealType.Breakfast,
        val calories: Int = 0,
        val icon: DrawableResource
    )

    enum class MealType(val displayName: String) {
        Breakfast("Breakfast"), Lunch("Lunch"), Dinner("Dinner"), Snacks("Snacks")
    }
}