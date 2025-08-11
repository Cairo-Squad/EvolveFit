package com.cairosquad.evolvefit.viewmodel.nutrition

sealed class NutritionEffect {
    object NavigateToSuggestedMeals : NutritionEffect()
    object NavigateToMealHistory : NutritionEffect()
    data class NavigateToSuggestedMealDetails(val mealId: String) : NutritionEffect()
}