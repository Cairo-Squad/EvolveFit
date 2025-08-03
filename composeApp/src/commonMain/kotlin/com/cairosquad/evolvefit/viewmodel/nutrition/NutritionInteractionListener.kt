package com.cairosquad.evolvefit.viewmodel.nutrition

interface NutritionInteractionListener {
    fun onAddWaterClicked()
    fun onDismissWaterClicked()
    fun onShowAddMealSheetClicked()
    fun onConfirmAddMealClicked()
    fun onDismissMealClicked()
    fun onSnackBarHided()
    fun onViewAllSuggestedMealsClicked()
    fun onSuggestedMealClicked(mealId: Long)
    fun onViewAllMealHistoryClicked()

}