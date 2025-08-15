package com.cairosquad.evolvefit.viewmodel.nutrition

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.meal_type_breakfast
import evolvefit.composeapp.generated.resources.meal_type_dinner
import evolvefit.composeapp.generated.resources.meal_type_lunch
import evolvefit.composeapp.generated.resources.meal_type_snacks
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class NutritionScreenState(
    val todayConsumedCalories: Float = 0.0f,
    val dailyCaloriesGoal: Float = 0.0f,

    val todayConsumedWater: Float = 0.0f,
    val dailyWaterGoal: Float = 0.0f,

    val remainingDailyCalories: Float = 0.0f,

    val dailyMealSummaryUiStates: List<DailyMealSummaryUiState> = emptyList(),
    val suggestedMeals: List<SuggestedMealUiState> = emptyList(),
    val todayConsumedMeals: List<ConsumedMealUiState> = emptyList(),

    val isAddWaterSheetVisible: Boolean = false,
    val isAddMealSheetVisible: Boolean = false,
    val isDroppedMenuVisible: Boolean = false,
    val isMealTypeMenuExpanded: Boolean = false,
    val isAddButtonEnabled: Boolean = false,

    val screenStatus: ScreenStatus= ScreenStatus.LOADING,

    val mealNameInput: String = "",
    val consumedCaloriesInput: String = "",
    val consumedWaterInput: String = "",
    val selectedMeal: MealTypeUiState = MealTypeUiState.Breakfast,

    val errorMessage: StringResource? = null,
    val inputErrorMessage: StringResource? = null,
    val isMealAddedSnackBarVisible: Boolean = false,

    ) {
    data class DailyMealSummaryUiState(
        val type: MealTypeUiState = MealTypeUiState.Breakfast,
        val calories: Float = 0f,
        val icon: DrawableResource
    )

    data class SuggestedMealUiState(
        val name: String = "",
        val type: MealTypeUiState = MealTypeUiState.Breakfast,
        val calories: Int = 0,
        val imageUrl: String = ""
    )

    data class ConsumedMealUiState(
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
    enum class ScreenStatus{
        SUCCESS,LOADING,FAIL
    }
}