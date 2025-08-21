package com.cairosquad.evolvefit.viewmodel.meal_details

import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class MealDetailsViewModel(
     private val manageNutritionUseCase: ManageNutritionUseCase
) : BaseViewModel<MealDetailsScreenState, MealDetailsEffect>(MealDetailsScreenState()),
MealDetailsInteractionListener
{

    override fun onBackClicked() {
        sendEffect(MealDetailsEffect.NavigateBack)
    }

    override fun onSaveMealClicked(mealId: String) {
        updateState { it.copy(showSaveMealSnackBar = true) }
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

    private fun onGetMealDetailsSuccess(meal : Meal) {
        updateState { current ->
            current.copy(
                mealDetails = meal.toMealDetailsUiState(),
                screenStatus = MealDetailsScreenState.ScreenStatus.SUCCESS,
                errorMessage = null
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