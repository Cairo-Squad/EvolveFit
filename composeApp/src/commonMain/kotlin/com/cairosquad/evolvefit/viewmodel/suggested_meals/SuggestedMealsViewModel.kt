package com.cairosquad.evolvefit.viewmodel.suggested_meals

import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.usecase.meal.ManageMealUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class SuggestedMealsViewModel(
    private val mealUseCase: ManageMealUseCase
) : BaseViewModel<SuggestedMealsScreenState, SuggestedMealsEffect>(SuggestedMealsScreenState()),
    SuggestedMealsInteractionListener {
    init {
        loadSuggestedMeals()
    }
    override fun onBackClicked() {
        sendEffect(SuggestedMealsEffect.NavigateBack)
    }

    override fun onMealClicked(mealId: Long) {
        sendEffect(SuggestedMealsEffect.NavigateToMealDetails(mealId))
    }
    private fun loadSuggestedMeals() {
        tryToCall(
            block = { mealUseCase.getSuggestedMeals() },
            onStart = { setScreenStatus(SuggestedMealsScreenState.ScreenStatus.LOADING) },
            onSuccess = { handleSuccess(it) },
            onError = { handleError(it) }
        )
    }
    private fun setScreenStatus(status: SuggestedMealsScreenState.ScreenStatus) {
        updateState { current ->
            current.copy(screenStatus = status)
        }
    }
    private fun handleSuccess(meals: List<Meal>) {
        updateState { current ->
            current.copy(
                suggestedMeals = meals.map { it.toSuggestedMealUiState() },
                screenStatus = SuggestedMealsScreenState.ScreenStatus.SUCCESS,
                errorMessage = null
            )
        }
    }
    private fun handleError(e: Throwable) {
        updateState { current ->
            current.copy(
                errorMessage = e.message,
                screenStatus = SuggestedMealsScreenState.ScreenStatus.ERROR
            )
        }
    }
}