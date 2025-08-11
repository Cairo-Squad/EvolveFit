package com.cairosquad.evolvefit.viewmodel.nutrition

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NutritionViewModel() : BaseViewModel<NutritionScreenState, NutritionEffect>(
    NutritionScreenState()
), NutritionInteractionListener {
    override fun onAddWaterClicked() {
        updateState { it.copy(isAddWaterSheetVisible = true) }
    }

    override fun onConfirmAddWaterClicked(waterAmount: Float) {
        //TODO link with use case
        updateState {
            it.copy(
                isAddWaterSheetVisible = false,
                isAddButtonEnabled = false,
                waterAmountInput = ""
            )
        }
    }

    override fun onDismissWaterClicked() {
        updateState { it.copy(isAddWaterSheetVisible = false) }
    }

    override fun onWaterAmountChange(waterAmount: String) {
        updateState {
            it.copy(
                waterAmountInput = waterAmount,
                isAddButtonEnabled = waterAmount.isNotBlank()
            )
        }
    }

    override fun onAddMealSheetClicked() {
        updateState { it.copy(isAddMealSheetVisible = true) }
    }

    override fun onConfirmAddMealClicked(mealHistory: NutritionScreenState.MealHistory) {
        //TODO link with use case
        updateState {
            it.copy(
                isAddMealSheetVisible = false,
                isAddButtonEnabled = false,
                mealCaloriesInput = "",
                mealNameInput = "",
                selectedMeal = NutritionScreenState.MealType.Breakfast
            )
        }
        showMealAddedSnackBar()
    }

   private fun showMealAddedSnackBar() {
        viewModelScope.launch(Dispatchers.IO) {
            updateState { it.copy(isAddMealSnackBarVisible = true) }
            delay(2000)
            updateState { it.copy(isAddMealSnackBarVisible = false) }
        }
    }


override fun onDismissMealClicked() {
    updateState { it.copy(isAddMealSheetVisible = false) }
}

override fun onMealNameChanged(name: String) {
    updateState {
        it.copy(
            mealNameInput = name,
            isAddButtonEnabled = name.isNotBlank() && it.mealCaloriesInput.isNotBlank()
        )
    }
}

override fun onMealCaloriesChanged(calories: String) {
    updateState {
        it.copy(
            mealCaloriesInput = calories,
            isAddButtonEnabled = calories.isNotBlank() && it.mealNameInput.isNotBlank()
        )
    }
}

override fun onMealTypeSelected(mealType: NutritionScreenState.MealType) {
    updateState { it.copy(selectedMeal = mealType) }
}

override fun onToggleMealTypeMenu() {
    updateState { it.copy(isMealTypeMenuExpanded = !it.isMealTypeMenuExpanded) }
}

override fun onViewAllSuggestedMealsClicked() {
    sendEffect(NutritionEffect.NavigateToSuggestedMeals)
}

override fun onSuggestedMealClicked(mealId: String) {
    sendEffect(NutritionEffect.NavigateToSuggestedMealDetails(mealId = mealId))
}

override fun onViewAllMealHistoryClicked() {
    sendEffect(NutritionEffect.NavigateToMealHistory)
}

override fun onDroppedMenuClick() {
    updateState { it.copy(isDroppedMenuVisible = true) }
}

override fun onSnackBarHided() {
    updateState { it.copy(isAddMealSnackBarVisible = false) }
}
}