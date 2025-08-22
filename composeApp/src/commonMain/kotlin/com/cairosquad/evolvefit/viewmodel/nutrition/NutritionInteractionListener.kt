package com.cairosquad.evolvefit.viewmodel.nutrition

interface NutritionInteractionListener {
    fun onAddWaterClicked()
    fun onConfirmAddWaterClicked(waterAmount: String)
    fun onDismissWaterClicked()
    fun onWaterAmountChange(waterAmount: String)
    fun onAddMealSheetClicked()
    fun onConfirmAddMealClicked()
    fun onDismissMealClicked()
    fun onMealNameChanged(name: String)
    fun onMealCaloriesChanged(calories: String)
    fun onMealTypeSelected(mealTypeUiState: NutritionScreenState.MealTypeUiState)
    fun onToggleMealTypeMenu()
    fun onViewAllSuggestedMealsClicked()
    fun onSuggestedMealClicked(mealId: String)
    fun onViewAllMealHistoryClicked()
    fun onDroppedMenuClick()
    fun onSnackBarHided()
    fun onRetryClicked()
    fun onRefresh()
}