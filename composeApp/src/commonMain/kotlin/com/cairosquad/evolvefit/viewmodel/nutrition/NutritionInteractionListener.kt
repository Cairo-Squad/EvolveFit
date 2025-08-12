package com.cairosquad.evolvefit.viewmodel.nutrition

interface NutritionInteractionListener {
    fun onAddWaterClicked()
    fun onConfirmAddWaterClicked(waterAmount: Float)
    fun onDismissWaterClicked()
    fun onWaterAmountChange(waterAmount: String)
    fun onAddMealSheetClicked()
    fun onConfirmAddMealClicked(consumedMealUiState: NutritionScreenState.ConsumedMealUiState)
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
}