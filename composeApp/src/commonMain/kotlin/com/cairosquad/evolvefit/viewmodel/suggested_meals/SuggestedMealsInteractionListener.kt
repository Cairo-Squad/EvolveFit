package com.cairosquad.evolvefit.viewmodel.suggested_meals

interface SuggestedMealsInteractionListener {
    fun onBackClicked()
    fun onMealClicked(mealId : Long)
}