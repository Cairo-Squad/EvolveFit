package com.cairosquad.evolvefit.viewmodel.meal_history

import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.entity.nutrition.ConsumedMeal
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.todayIn


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
                    return@tryToCall meals},
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
            .groupBy { LocalDateTime.parse(it.date).date } // <- extract date only
            .map { (date, mealsList) ->
                MealHistoryScreenState.DayGroupedMealsUiState(
                    dayHeader = getDayHeader(date),
                    date = date.toString(),
                    meals = mealsList.sortedBy { it.type } // ascending Breakfast → Dinner
                )
            }
            .sortedByDescending { LocalDate.parse(it.date) }
    }

    private fun getDayHeader(date: LocalDate): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        return when {
            date == today -> "Today"
            date == today.minus(DatePeriod(days = 1)) -> "Yesterday"
            date > today.minus(DatePeriod(days = 7)) ->
                date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
            else -> "${date.dayOfMonth} ${date.month.toAbbreviation()}"
        }
    }

    private fun Month.toAbbreviation(): String = when (this) {
        Month.JANUARY -> "Jan"
        Month.FEBRUARY -> "Feb"
        Month.MARCH -> "Mar"
        Month.APRIL -> "Apr"
        Month.MAY -> "May"
        Month.JUNE -> "Jun"
        Month.JULY -> "Jul"
        Month.AUGUST -> "Aug"
        Month.SEPTEMBER -> "Sep"
        Month.OCTOBER -> "Oct"
        Month.NOVEMBER -> "Nov"
        Month.DECEMBER -> "Dec"
        else -> "Unknown"
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