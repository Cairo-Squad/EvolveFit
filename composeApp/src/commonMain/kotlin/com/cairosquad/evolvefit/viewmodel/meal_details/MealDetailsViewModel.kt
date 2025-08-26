package com.cairosquad.evolvefit.viewmodel.meal_details

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import com.cairosquad.evolvefit.viewmodel.utils.toErrorMessageRes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MealDetailsViewModel(
    private val manageNutritionUseCase: ManageNutritionUseCase,
    private val mealId: String
) : BaseViewModel<MealDetailsScreenState, MealDetailsEffect>(MealDetailsScreenState()),
    MealDetailsInteractionListener {
    init {
        getMealDetails()
    }

    override fun onBackClicked() {
        sendEffect(MealDetailsEffect.NavigateBack)
    }

    override fun onSaveMealClicked(mealId: String) {
        if (screenState.value.mealDetails.isFavouriteMeal.not()) {
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
        }
    }

    override fun onRetryClicked() {
        getMealDetails()
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

    private fun getMealDetails() {
        tryToCall(
            onStart = { setScreenStatus(MealDetailsScreenState.ScreenStatus.LOADING) },
            block = { manageNutritionUseCase.getMealById(mealId) },
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
                errorMessage = e.toErrorMessageRes(),
                screenStatus = MealDetailsScreenState.ScreenStatus.ERROR
            )
        }
    }
}