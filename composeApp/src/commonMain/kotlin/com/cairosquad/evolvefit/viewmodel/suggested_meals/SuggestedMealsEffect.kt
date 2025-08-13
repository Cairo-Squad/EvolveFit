package com.cairosquad.evolvefit.viewmodel.suggested_meals

sealed class SuggestedMealsEffect {
    object NavigateBack : SuggestedMealsEffect()
    data class NavigateToMealDetails(val mealId : Long) : SuggestedMealsEffect()
}