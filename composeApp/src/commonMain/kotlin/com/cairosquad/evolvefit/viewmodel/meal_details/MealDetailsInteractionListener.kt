package com.cairosquad.evolvefit.viewmodel.meal_details

interface MealDetailsInteractionListener {
    fun onBackClicked()
    fun onSaveMealClicked(mealId: String)
    fun onRetryClicked()
    fun onAddWorkoutClicked()
}