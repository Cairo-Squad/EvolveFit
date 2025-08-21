package com.cairosquad.evolvefit.viewmodel.meal_history

import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.entity.nutrition.ConsumedMeal
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.utils.getDayHeader
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime


class MealHistoryViewModel(
    private val manageMealHistoryUseCase: ManageNutritionUseCase
) : BaseViewModel<MealHistoryScreenState, MealHistoryEffect>(MealHistoryScreenState()),
    MealHistoryInteractionListener {

    init {
        loadMealsHistory()
    }

    override fun onNavigateBackClicked() {
        sendEffect(MealHistoryEffect.NavigateBack)
    }


    fun loadMealsHistory() {
        tryToCall(
            block = {
                val meals = manageMealHistoryUseCase.getMealHistory()
                return@tryToCall meals
            },
            onStart = { setScreenStatus(MealHistoryScreenState.ScreenStatus.LOADING) },
            onSuccess = ::onLoadMealsHistorySuccess,
            onError = ::onLoadMealsHistoryError
        )
    }

    private fun setScreenStatus(status: MealHistoryScreenState.ScreenStatus) {
        updateState { current -> current.copy(screenStatus = status) }
    }

    private fun onLoadMealsHistorySuccess(mealsHistories: List<ConsumedMeal>) {
        val groupedMeals = groupMealsByDay(mealsHistories.map { it.toMealHistoryUiState() })
        updateState { current ->
            current.copy(
                mealsHistories = groupedMeals,
                screenStatus = MealHistoryScreenState.ScreenStatus.SUCCESS,
                errorMessage = null
            )
        }
    }

    private fun groupMealsByDay(
        meals: List<MealHistoryScreenState.MealHistoryUiState>
    ): List<MealHistoryScreenState.DayGroupedMealsUiState> {
        return meals
            .groupBy { LocalDateTime.parse(it.rawDate).date }
            .map { (date, mealsList) ->
                MealHistoryScreenState.DayGroupedMealsUiState(
                    dayHeader = getDayHeader(date),
                    date = date.toString(),
                    meals = mealsList.sortedBy { it.type } // ascending Breakfast → Dinner
                )
            }
            .sortedByDescending { LocalDate.parse(it.date) }
    }

    private fun onLoadMealsHistoryError(e: Throwable) {
        updateState { current ->
            current.copy(
                errorMessage = e.message,
                screenStatus = MealHistoryScreenState.ScreenStatus.ERROR
            )
        }
    }
}