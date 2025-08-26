package com.cairosquad.evolvefit.viewmodel.meal_details

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
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
        tryToCall(
            block = { manageNutritionUseCase.addFavouriteMealById(mealId) },
            onSuccess = { handleSaveMealSuccess() },
            onError = {}
        )
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
            onSuccess = { updateMealSaveStatus(mealId, it) },
            onError = { updateState { it.copy(isLoading = false) } },
            onStart = { updateState { it.copy(isLoading = true) } }
        )
    }
    private fun updateMealSaveStatus(mealId: String, meals: List<SuggestedMeal>) {
        updateState { state ->
            state.copy(
                mealDetails =state.mealDetails.copy(isFavouriteMeal =  meals.map { it.id }.contains(mealId)),
                isLoading = false
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