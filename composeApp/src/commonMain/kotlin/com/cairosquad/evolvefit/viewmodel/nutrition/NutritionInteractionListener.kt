package com.cairosquad.evolvefit.viewmodel.nutrition

interface NutritionInteractionListener {
    fun onAddWaterClicked()
    fun onAddMealClicked()
    fun onViewAllSuggestedMealsClicked()
    fun onSuggestedMealClicked(mealId: Long)
    fun onViewAllMealHistoryClicked()

}