package com.cairosquad.evolvefit.viewmodel.nutrition

interface NutritionInteractionListener {
    fun onAddWaterClicked()
    fun onConfirmAddWaterClicked(waterAmount:Float)
    fun onDismissWaterClicked()
    fun onAddMealSheetClicked()
    fun onConfirmAddMealClicked(mealHistory: NutritionScreenState.MealHistory)
    fun onDroppedMenuClick()
    fun onDismissMealClicked()
    fun onSnackBarHided()
    fun onMealTypeSelected(mealType: NutritionScreenState.MealType)
    fun onViewAllSuggestedMealsClicked()
    fun onSuggestedMealClicked(mealId: Long)
    fun onViewAllMealHistoryClicked()

}