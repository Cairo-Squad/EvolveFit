package com.cairosquad.evolvefit.viewmodel.nutrition

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class NutritionViewModel() : BaseViewModel<NutritionScreenState, NutritionEffect>(
    NutritionScreenState()
), NutritionInteractionListener {
    override fun onAddWaterClicked() {
        TODO("Not yet implemented")
    }

    override fun onAddMealClicked() {
        TODO("Not yet implemented")
    }

    override fun onViewAllSuggestedMealsClicked() {
        sendEffect(NutritionEffect.NavigateToSuggestedMeals)
    }

    override fun onSuggestedMealClicked(mealId: Long) {
        sendEffect(NutritionEffect.NavigateToSuggestedMealDetails(mealId = mealId))
    }

    override fun onViewAllMealHistoryClicked() {
        sendEffect(NutritionEffect.NavigateToMealHistory)
    }
}