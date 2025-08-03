package com.cairosquad.evolvefit.viewmodel.nutrition

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class NutritionViewModel() : BaseViewModel<NutritionScreenState, NutritionEffect>(
    NutritionScreenState()
), NutritionInteractionListener {
    override fun onAddWaterClicked() {
      updateState { it.copy(isAddWaterSheetVisible = true) }
    }

    override fun onDismissWaterClicked() {
        updateState { it.copy(isAddWaterSheetVisible = false) }
    }

    override fun onShowAddMealSheetClicked() {
        updateState { it.copy(isAddMealSheetVisible = true) }
    }

    override fun onConfirmAddMealClicked() {
        updateState { it.copy(isAddMealSnackBarVisible = true, isAddMealSheetVisible = false) }
    }

    override fun onDismissMealClicked() {
        updateState { it.copy(isAddMealSheetVisible = false) }
    }

    override fun onSnackBarHided() {
        updateState { it.copy(isAddMealSnackBarVisible = false) }
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