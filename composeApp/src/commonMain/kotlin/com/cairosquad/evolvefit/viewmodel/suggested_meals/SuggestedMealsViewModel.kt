package com.cairosquad.evolvefit.viewmodel.suggested_meals

import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class SuggestedMealsViewModel(
    private val mealUseCase: ManageNutritionUseCase
) : BaseViewModel<SuggestedMealsScreenState, SuggestedMealsEffect>(SuggestedMealsScreenState()),
    SuggestedMealsInteractionListener {
    init {
        loadSuggestedMeals()
    }

    override fun onBackClicked() {
        sendEffect(SuggestedMealsEffect.NavigateBack)
    }

    override fun onMealClicked(mealId: String) {
        sendEffect(SuggestedMealsEffect.NavigateToMealDetails(mealId))
    }


    private fun loadSuggestedMeals() {
        tryToCall(
            block = { mealUseCase.getSuggestedMeals() },
            onStart = { setScreenStatus(SuggestedMealsScreenState.ScreenStatus.LOADING) },
            onSuccess = ::onLoadSuggestedMealsSuccess,
            onError = ::onLoadSuggestedMealsError
        )
    }

    private fun setScreenStatus(status: SuggestedMealsScreenState.ScreenStatus) {
        updateState { current ->
            current.copy(screenStatus = status)
        }
    }

    private fun onLoadSuggestedMealsSuccess(meals: List<SuggestedMeal>) {
        updateState { current ->
            current.copy(
                suggestedMeals = meals.map { it.toSuggestedMealUiState() },
                screenStatus = SuggestedMealsScreenState.ScreenStatus.SUCCESS,
                errorMessage = null
            )
        }
    }

    private fun onLoadSuggestedMealsError(e: Throwable) {
        updateState { current ->
            current.copy(
                errorMessage = e.message,
                screenStatus = SuggestedMealsScreenState.ScreenStatus.ERROR
            )
        }
    }
}