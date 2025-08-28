package com.cairosquad.evolvefit.viewmodel.meal_history

import com.cairosquad.evolvefit.domain.entity.ConsumedMeal
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.utils.getDayHeader
import com.cairosquad.evolvefit.viewmodel.utils.getTodayDate
import com.cairosquad.evolvefit.viewmodel.utils.toErrorMessageRes
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

    override fun onRetryClicked() {
        loadMealsHistory()
    }


    fun loadMealsHistory() {
        tryToCall(
            block = {
                val meals = manageMealHistoryUseCase.getMealHistory(
                    START_DAY_TIME,
                    "${getTodayDate()}${END_DAY_TIME}"
                )
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
        val status = if (groupedMeals.isEmpty()) {
            MealHistoryScreenState.ScreenStatus.EMPTY
        } else {
            MealHistoryScreenState.ScreenStatus.SUCCESS
        }
        updateState { current ->
            current.copy(
                mealsHistories = groupedMeals,
                screenStatus = status,
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
                errorMessage = e.toErrorMessageRes(),
                screenStatus = MealHistoryScreenState.ScreenStatus.ERROR
            )
        }
    }

    private companion object {
        const val START_DAY_TIME = "2025-08-01T00:00:00"
        const val END_DAY_TIME = "T23:59:59"
    }
}