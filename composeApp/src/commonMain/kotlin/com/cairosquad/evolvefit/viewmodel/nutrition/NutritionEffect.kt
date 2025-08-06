package com.cairosquad.evolvefit.viewmodel.nutrition

import com.cairosquad.evolvefit.viewmodel.register.RegisterEffect

sealed class NutritionEffect {
    object NavigateToSuggestedMeals : NutritionEffect()
    object NavigateToMealHistory : NutritionEffect()
    data class NavigateToSuggestedMealDetails(val mealId: Long) : NutritionEffect()
}