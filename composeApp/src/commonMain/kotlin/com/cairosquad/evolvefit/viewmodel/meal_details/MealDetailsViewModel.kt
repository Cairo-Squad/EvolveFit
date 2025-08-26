package com.cairosquad.evolvefit.viewmodel.meal_details

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MealDetailsViewModel(
    private val manageNutritionUseCase: ManageNutritionUseCase
) : BaseViewModel<MealDetailsScreenState, MealDetailsEffect>(MealDetailsScreenState()),
    MealDetailsInteractionListener {

    override fun onBackClicked() {
        sendEffect(MealDetailsEffect.NavigateBack)
    }

    override fun onSaveMealClicked(mealId: String) {
        if (screenState.value.mealDetails.isFavouriteMeal == false) {
            tryToCall(
                block = { manageNutritionUseCase.addFavouriteMealById(mealId) },
                onSuccess = { handleSaveMealSuccess() },
                onError = {}
            )
        } else {
            tryToCall(
                block = { manageNutritionUseCase.deleteFavouriteMeal(mealId) },
                onSuccess = { handleDeleteMealSuccess() },
                onError = {}
            )
        }
    }

    private fun handleDeleteMealSuccess() {
        viewModelScope.launch {
            updateState { current ->
                current.copy(
                    mealDetails = current.mealDetails.copy(
                        isFavouriteMeal = false,
                    ),
                    showSaveMealSuccessSnackBar = false
                )
            }
            delay(3000)
            updateState { it.copy(showSaveMealSuccessSnackBar = false) }
        }
    }

    private fun handleSaveMealSuccess() {
        viewModelScope.launch {
            updateState { current ->
                current.copy(
                    mealDetails = current.mealDetails.copy(
                        isFavouriteMeal = true,
                    ),
                    showSaveMealSuccessSnackBar = true
                )
            }
            delay(3000)
            updateState { it.copy(showSaveMealSuccessSnackBar = false) }
        }
    }

    fun getMealDetails(mealId: String) {
        tryToCall(
            block = { manageNutritionUseCase.getMealById(mealId) },
            onStart = { setScreenStatus(MealDetailsScreenState.ScreenStatus.LOADING) },
            onSuccess = ::onGetMealDetailsSuccess,
            onError = ::onGetMealDetailsError
        )
    }

    private fun setScreenStatus(status: MealDetailsScreenState.ScreenStatus) {
        updateState { current ->
            current.copy(screenStatus = status)
        }
    }

    private fun onGetMealDetailsSuccess(meal: Meal) {
        updateState { current ->
            current.copy(
                mealDetails = meal.toMealDetailsUiState(),
                screenStatus = MealDetailsScreenState.ScreenStatus.SUCCESS,
                errorMessage = null
            )
        }
        loadMealSaveStatus(meal.id)
    }

    private fun loadMealSaveStatus(mealId: String) {
        tryToCall(
            block = { manageNutritionUseCase.getFavouriteMeals() },
            onStart = { setScreenStatus(MealDetailsScreenState.ScreenStatus.LOADING) },
            onSuccess = { updateMealSaveStatus(mealId, it) },
            onError = ::onGetMealDetailsError,
        )
    }

    private fun updateMealSaveStatus(mealId: String, meals: List<SuggestedMeal>) {
        updateState { state ->
            state.copy(
                mealDetails = state.mealDetails.copy(isFavouriteMeal = meals.map { it.id }
                    .contains(mealId)),
            )
        }
    }

    private fun onGetMealDetailsError(e: Throwable) {
        updateState { current ->
            current.copy(
                errorMessage = e.message,
                screenStatus = MealDetailsScreenState.ScreenStatus.ERROR
            )
        }
    }
}