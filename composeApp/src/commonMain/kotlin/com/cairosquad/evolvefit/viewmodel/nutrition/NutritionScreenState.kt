package com.cairosquad.evolvefit.viewmodel.nutrition

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.meal_type_breakfast
import evolvefit.composeapp.generated.resources.meal_type_dinner
import evolvefit.composeapp.generated.resources.meal_type_lunch
import evolvefit.composeapp.generated.resources.meal_type_snacks
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class NutritionScreenState(
    val consumedCalories: Float = 0f,
    val totalCalories: Float = 0.0f,

    val consumedWater: Float = 0f,
    val totalWater: Float = 0f,

    val remainingCalories: Float = 0.0f,

    val todayMealUiStates: List<TodayMealUiState> = emptyList(),
    val suggestedMeals: List<SuggestedMeal> = emptyList(),
    val consumedMealsForToday: List<MealHistory> = emptyList(),
    val mealsHistory: List<MealHistory> = emptyList(),
    val isAddWaterSheetVisible: Boolean = false,
    val isAddMealSheetVisible: Boolean = false,
    val isAddMealSnackBarVisible: Boolean = false,
    val isDroppedMenuVisible: Boolean = false,
    val isMealTypeMenuExpanded: Boolean = false,
    val isAddButtonEnabled: Boolean = false,
    val dataRequestState: DataRequestState= DataRequestState.LOADING,
    val mealNameInput: String = "",
    val consumedCaloriesInput: String = "",
    val consumedWaterInput: String = "",
    val selectedMeal: MealTypeUiState = MealTypeUiState.Breakfast,
    val errorMessage: String? = null,
) {
    data class TodayMealUiState(
        val type: MealTypeUiState = MealTypeUiState.Breakfast,
        val calories: Float = 0f,
        val icon: DrawableResource
    )

    data class SuggestedMeal(
        val name: String = "",
        val type: MealTypeUiState = MealTypeUiState.Breakfast,
        val calories: Int = 0,
        val imageUrl: String = ""
    )

    data class MealHistory(
        val name: String = "",
        val type: MealTypeUiState = MealTypeUiState.Breakfast,
        val calories: Int = 0,
        val date: String = ""
    )

    enum class MealTypeUiState(val displayName: StringResource) {
        Breakfast(Res.string.meal_type_breakfast),
        Lunch(Res.string.meal_type_lunch),
        Dinner(Res.string.meal_type_dinner),
        Snacks(Res.string.meal_type_snacks)
    }
    enum class DataRequestState{
        SUCCESS,LOADING,FAIL
    }
}