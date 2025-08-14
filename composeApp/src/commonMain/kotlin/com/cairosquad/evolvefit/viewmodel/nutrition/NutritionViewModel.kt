package com.cairosquad.evolvefit.viewmodel.nutrition

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.exceptions.ExceededCaloriesException
import com.cairosquad.evolvefit.domain.exceptions.ExceededWaterLimitException
import com.cairosquad.evolvefit.domain.exceptions.InvalidNumberFormatException
import com.cairosquad.evolvefit.domain.exceptions.MealNotFoundException
import com.cairosquad.evolvefit.domain.exceptions.UnknownException
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.entity.nutrition.ConsumedMeal
import com.cairosquad.evolvefit.entity.nutrition.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.nutrition.DailyWaterSummary
import com.cairosquad.evolvefit.entity.nutrition.MealType
import com.cairosquad.evolvefit.entity.nutrition.SuggestedMeal
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.utils.getTodayDate
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.error_exceeded_calories
import evolvefit.composeapp.generated.resources.error_exceeded_water
import evolvefit.composeapp.generated.resources.error_invalid_number
import evolvefit.composeapp.generated.resources.error_meal_not_found
import evolvefit.composeapp.generated.resources.error_unknown
import evolvefit.composeapp.generated.resources.meal_added_snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

class NutritionViewModel(
    private val manageNutritionUseCase: ManageNutritionUseCase
) : BaseViewModel<NutritionScreenState, NutritionEffect>(
    NutritionScreenState()
), NutritionInteractionListener {
    private var snackBarJob: kotlinx.coroutines.Job? = null

    init {
        startLoad()
    }
    private fun loadNutritionData() {
        loadDailyCaloriesSummary()
        loadDailyWaterSummary()
        loadConsumedMealsForToday()
        loadSuggestedMeals()
    }
    private fun startLoad() {
        tryToCall(
            block = {
                loadNutritionData()
            }, onStart = {
                updateState {
                    it.copy(dataRequestState = NutritionScreenState.DataRequestState.LOADING)
                }
            }, onSuccess = {
                updateState {
                    it.copy(dataRequestState = NutritionScreenState.DataRequestState.SUCCESS)
                }
            }, onError = {
                updateState {
                    it.copy(dataRequestState = NutritionScreenState.DataRequestState.FAIL)
                }
            }
        )
    }
    private fun loadDailyCaloriesSummary() {
        tryToCall(
            block = { manageNutritionUseCase.getDailyCalorieSummary() },
            onSuccess = ::onLoadDailyCaloriesSummarySuccess,
            onError = {
                val handled = handleHomeException(it)
                updateState {
                    it.copy(
                        errorMessage = handled.toErrorMessageRes()
                    )
                }
            }
        )
    }
    private fun onLoadDailyCaloriesSummarySuccess(dailyCalorieSummary: DailyCalorieSummary) {
        updateState {
            it.copy(
                dailyCaloriesGoal = dailyCalorieSummary.consumedCalories.toFloat(),
                todayConsumedCalories = dailyCalorieSummary.totalCalories.toFloat(),
                remainingDailyCalories =  (dailyCalorieSummary.totalCalories - dailyCalorieSummary.consumedCalories).toFloat()
            )
        }
    }
    private fun loadDailyWaterSummary() {
        tryToCall(
            block = { manageNutritionUseCase.getDailyWaterSummary() },
            onSuccess = ::onLoadDailyWaterSummarySuccess,
            onError = {
                val handled = handleHomeException(it)
                updateState {
                    it.copy(
                        errorMessage = handled.toErrorMessageRes()
                    )
                }
            }
        )
    }
    private fun onLoadDailyWaterSummarySuccess(dailyWaterSummary: DailyWaterSummary) {
        updateState {
            it.copy(
                todayConsumedWater = dailyWaterSummary.consumedWater,
                dailyWaterGoal = dailyWaterSummary.totalWater
            )
        }
    }
    private fun loadConsumedMealsForToday() {
        tryToCall(
            {
                manageNutritionUseCase.getConsumedMealsByDate(
                    "2025-08-01${START_DAY_TIME}",
                    "2025-08-14${END_DAY_TIME}"
                )
            }, onStart = {
                updateState { it.copy(dailyMealSummaryUiStates = getDefaultTodayMeals()) }
            }, onSuccess = ::onLoadConsumedMealsForTodaySuccess,
            onError = {
                val handled = handleHomeException(it)
                updateState {
                    it.copy(
                        errorMessage = handled.toErrorMessageRes()
                    )
                }
            })
    }
    private fun onLoadConsumedMealsForTodaySuccess(consumedMeals: List<ConsumedMeal>) {
        updateConsumedMealsForToday(consumedMeals)
        val mealsGroupedByType  = consumedMeals.groupBy { it.type }
        val todayMeals = defaultTypes.map { type ->
            val dailyMealSummaries  = mealsGroupedByType [type].orEmpty()
            NutritionScreenState.DailyMealSummaryUiState(
                type = type.toMealUiState(),
                calories = dailyMealSummaries.sumOf { (it.calories) }.toFloat(),
                icon = type.toMealUiState().toMealIcon()
            )
        }
        updateState { it.copy(dailyMealSummaryUiStates = todayMeals) }
    }
    private fun updateConsumedMealsForToday(consumedMeals: List<ConsumedMeal>) {
        val consumedMeals =
            consumedMeals.sortedByDescending { it.dateTime }.map { it.toMealHistoryUi() }
        updateState {
            it.copy(todayConsumedMeals = consumedMeals)
        }
    }
    private fun loadSuggestedMeals() {
        tryToCall(
            block = { manageNutritionUseCase.getSuggestedMeals() },
            onStart = {
                updateState { it.copy(suggestedMealsRequestState = NutritionScreenState.RequestState.LOADING) }
            },
            onSuccess = {
                handleSuggestedMeals(meals = it)
                updateState { it.copy(suggestedMealsRequestState = NutritionScreenState.RequestState.SUCCESS) }
            },
            onError = { e ->
                updateState {
                    it.copy(
                        errorMessage = e.toErrorMessageRes(),
                        suggestedMealsRequestState = NutritionScreenState.RequestState.FAIL
                    )
                }
            })
    }
    private fun handleSuggestedMeals(meals: List<SuggestedMeal>) {
        val suggestedMeals = meals.map { it.toSuggestedMealUi() }
        updateState {
            it.copy(
                suggestedMeals = suggestedMeals,
                dataRequestState = NutritionScreenState.DataRequestState.SUCCESS
            )
        }
    }
    private fun getDefaultTodayMeals(): List<NutritionScreenState.DailyMealSummaryUiState> {
        return NutritionScreenState.MealTypeUiState.entries.map { mealType ->
            NutritionScreenState.DailyMealSummaryUiState(
                type = mealType, calories = 0.0f, icon = mealType.toMealIcon()
            )
        }
    }




    // Actions
    override fun onAddWaterClicked() {
        val state = screenState.value
        if (state.todayConsumedWater >= state.dailyWaterGoal) {
            showSnackBar(Res.string.error_exceeded_water)
        } else {
            updateState { it.copy(isAddWaterSheetVisible = true) }
        }
    }

    override fun onConfirmAddWaterClicked(waterAmount: Float) {
        val input = screenState.value.consumedWaterInput
        tryToCall(
            block = {
                manageNutritionUseCase.saveConsumedWater(
                    input,
                    screenState.value.dailyWaterGoal - screenState.value.todayConsumedWater
                )
            },
            onSuccess = {
                loadDailyWaterSummary()
                updateState {
                    it.copy(
                        isAddWaterSheetVisible = false,
                        isAddButtonEnabled = false,
                        consumedWaterInput = "",
                        inputErrorMessage = null
                    )
                }
            },
            onError = { throwable ->
                val handled = handleHomeException(throwable)
                updateState {
                    it.copy(inputErrorMessage = handled.toErrorMessageRes())
                }
            }
        )
    }

    override fun onDismissWaterClicked() {
        updateState { it.copy(isAddWaterSheetVisible = false) }
    }

    override fun onWaterAmountChange(waterAmount: String) {
        updateState {
            it.copy(
                consumedWaterInput = waterAmount, isAddButtonEnabled = waterAmount.isNotBlank()
            )
        }
    }

    override fun onAddMealSheetClicked() {
        val state = screenState.value
        if (state.todayConsumedCalories >= state.dailyCaloriesGoal) {
            showSnackBar(Res.string.error_exceeded_calories)
        } else {
            updateState { it.copy(isAddMealSheetVisible = true) }
        }
    }

    override fun onConfirmAddMealClicked() {
        val caloriesInput = screenState.value.consumedCaloriesInput
        val remainingCalories = screenState.value.remainingDailyCalories

        val meal = ConsumedMeal(
            name = screenState.value.mealNameInput,
            type = screenState.value.selectedMeal.toMealType(),
            calories = 0,
            dateTime = getTodayDate(),
            id = "0"
        )

        tryToCall(
            block = {
                manageNutritionUseCase.saveConsumedMeal(
                    caloriesInput,
                    meal,
                    remainingCalories
                )
            },
            onSuccess = {
                loadConsumedMealsForToday()
                loadDailyCaloriesSummary()
                showSnackBar(Res.string.meal_added_snackbar)
                updateState {
                    it.copy(
                        isAddMealSheetVisible = false,
                        isAddButtonEnabled = false,
                        consumedCaloriesInput = "",
                        mealNameInput = "",
                        selectedMeal = NutritionScreenState.MealTypeUiState.Breakfast,
                        inputErrorMessage = null
                    )
                }
            },
            onError = { throwable ->
                val handled = handleHomeException(throwable)
                updateState { it.copy(inputErrorMessage = handled.toErrorMessageRes()) }
            }
        )
    }

    private fun showSnackBar(message: StringResource) {
        snackBarJob?.cancel()
        snackBarJob = viewModelScope.launch(Dispatchers.Main) {
            updateState {
                it.copy(
                    isMealAddedSnackBarVisible = true,
                    isAddMealSheetVisible = false,
                    errorMessage = message
                )
            }
            delay(2000)
            updateState { it.copy(isMealAddedSnackBarVisible = false) }
        }
    }


    override fun onDismissMealClicked() {
        updateState { it.copy(isAddMealSheetVisible = false) }
    }

    override fun onMealNameChanged(name: String) {
        updateState {
            it.copy(
                mealNameInput = name,
                isAddButtonEnabled = name.isNotBlank() && it.consumedCaloriesInput.isNotBlank()
            )
        }
    }

    override fun onMealCaloriesChanged(calories: String) {
        updateState {
            it.copy(
                consumedCaloriesInput = calories,
                isAddButtonEnabled = calories.isNotBlank() && it.mealNameInput.isNotBlank()
            )
        }
    }

    override fun onMealTypeSelected(mealTypeUiState: NutritionScreenState.MealTypeUiState) {
        updateState { it.copy(selectedMeal = mealTypeUiState) }
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
        updateState { it.copy(isMealAddedSnackBarVisible = false) }
    }

    private fun handleHomeException(e: Throwable): Throwable {
        return when (e) {
            is InvalidNumberFormatException,
            is ExceededCaloriesException,
            is ExceededWaterLimitException,
            is MealNotFoundException -> e

            else -> UnknownException()
        }
    }

    fun Throwable.toErrorMessageRes(): StringResource {
        return when (this) {
            is InvalidNumberFormatException -> Res.string.error_invalid_number
            is ExceededCaloriesException -> Res.string.error_exceeded_calories
            is ExceededWaterLimitException -> Res.string.error_exceeded_water
            is MealNotFoundException -> Res.string.error_meal_not_found
            else -> Res.string.error_unknown
        }
    }

    private companion object {
        val defaultTypes = listOf(
            MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER, MealType.SNACK
        )
        const val START_DAY_TIME = "T00:00:00"
        const val END_DAY_TIME = "T23:59:59"
    }
}