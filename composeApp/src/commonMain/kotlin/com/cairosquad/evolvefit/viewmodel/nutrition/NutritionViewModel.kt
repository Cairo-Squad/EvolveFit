package com.cairosquad.evolvefit.viewmodel.nutrition

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.usecase.nutrition.MealUseCase
import com.cairosquad.evolvefit.domain.usecase.nutrition.WaterIntakeUseCase
import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.DailyWaterSummary
import com.cairosquad.evolvefit.entity.MealType
import com.cairosquad.evolvefit.entity.SuggestedMeal
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.utils.getCurrentDate
import com.cairosquad.evolvefit.viewmodel.utils.getTodayDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NutritionViewModel(
    private val mealUseCase: MealUseCase, private val waterIntakeUseCase: WaterIntakeUseCase
) : BaseViewModel<NutritionScreenState, NutritionEffect>(
    NutritionScreenState()
), NutritionInteractionListener {

    init {
        loadNutritionData()
    }

    private fun loadNutritionData() {
        loadConsumedMealsForToday()
        loadDailyCaloriesSummary()
        loadDailyWaterSummary()
        loadSuggestedMeals()
    }


    private fun loadDailyCaloriesSummary() {
        tryToCall(
            block = {
                mealUseCase.getDailyCalorieSummary()
            }, onSuccess = ::handleDailyCaloriesSummary,
            onError = { e ->
                updateState { it.copy(errorMessage = e.message) }
            })
    }

    private fun handleDailyCaloriesSummary(dailyCalorieSummary: DailyCalorieSummary) {
        val remainingCalories =
            (dailyCalorieSummary.totalCalories - dailyCalorieSummary.consumedCalories).toFloat()
        updateState {
            it.copy(
                totalCalories = dailyCalorieSummary.consumedCalories.toFloat(),
                consumedCalories = dailyCalorieSummary.totalCalories.toFloat(),
                remainingCalories = remainingCalories
            )
        }
    }

    private fun loadDailyWaterSummary() {
        tryToCall(
            block = {
                waterIntakeUseCase.getDailyWaterSummary()
            }, onSuccess = ::handleDailyWaterSummary,
            onError = { e ->
                updateState { it.copy(errorMessage = e.message) }
            })
    }

    private fun handleDailyWaterSummary(dailyWaterSummary: DailyWaterSummary) {
        updateState {
            it.copy(
                consumedWater = dailyWaterSummary.consumedWater,
                totalWater = dailyWaterSummary.totalWater
            )
        }
    }


    private fun loadConsumedMealsForToday() {
        tryToCall(
            {
                mealUseCase.getConsumedMealsToday("2025-08-01${START_DAY_TIME}","2025-08-11${END_DAY_TIME}")
            }, onStart = {
                updateState { it.copy(todayMealUiStates = getDefaultTodayMeals()) }
            }, onSuccess = ::handleConsumedMealsForToday,
            onError = {})
    }

    // TODO rename variables here
    private fun handleConsumedMealsForToday(consumedMeals: List<ConsumedMeal>) {
        updateConsumedMealsForToday(consumedMeals)
        val grouped = consumedMeals.groupBy { it.type }
        val todayMeals = defaultTypes.map { type ->
            val mealsOfType = grouped[type].orEmpty()
            NutritionScreenState.TodayMealUiState(
                type = type.toMealUiState(),
                calories = mealsOfType.sumOf { (it.calories) }.toFloat(),
                icon = type.toMealUiState().toMealIcon()
            )
        }
        updateState { it.copy(todayMealUiStates = todayMeals) }
    }

    private fun loadSuggestedMeals() {
        tryToCall(
            block = { mealUseCase.getSuggestedMeals() },
            onStart = { updateState { it.copy(dataRequestState = NutritionScreenState.DataRequestState.LOADING) } },
            onSuccess = ::handleSuggestedMeals,
            onError = { e ->
                updateState {
                    it.copy(
                        errorMessage = e.message,
                        dataRequestState = NutritionScreenState.DataRequestState.FAIL
                    )
                }
            })
    }

    private fun handleSuggestedMeals(suggestedMeals: List<SuggestedMeal>) {
        val suggestedMeals = suggestedMeals.map { it.toSuggestedMealUi() }
        updateState {
            it.copy(
                suggestedMeals = suggestedMeals,
                dataRequestState = NutritionScreenState.DataRequestState.SUCCESS
            )
        }
    }

    private fun updateConsumedMealsForToday(consumedMeals: List<ConsumedMeal>) {
        val consumedMeals =
            consumedMeals.sortedByDescending { it.dateTime }.map { it.toMealHistoryUi() }
        updateState {
            it.copy(consumedMealsForToday = consumedMeals)
        }
    }

    private fun getDefaultTodayMeals(): List<NutritionScreenState.TodayMealUiState> {
        return NutritionScreenState.MealTypeUiState.entries.map { mealType ->
            NutritionScreenState.TodayMealUiState(
                type = mealType, calories = 0.0f, icon = mealType.toMealIcon()
            )
        }
    }


    override fun onAddWaterClicked() {
        updateState { it.copy(isAddWaterSheetVisible = true) }
    }

    override fun onConfirmAddWaterClicked(waterAmount: Float) {
        val consumedWaterInput = screenState.value.consumedWaterInput.toFloat()
        tryToCall(block = {
            waterIntakeUseCase.addConsumedWater(consumedWaterInput)
        }, onSuccess = {
            if (it) {
                updateState {
                    loadDailyWaterSummary()
                    it.copy(
                        isAddWaterSheetVisible = false,
                        isAddButtonEnabled = false,
                        consumedWaterInput = ""
                    )
                }
            }
        }, onError = {})
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
        val remainingCalories = screenState.value.remainingCalories
        if (remainingCalories.toInt() == 0) {
            showMealAddedSnackBar()
        } else {
            updateState { it.copy(isAddMealSheetVisible = true) }
        }
    }

    override fun onConfirmAddMealClicked(mealHistory: NutritionScreenState.MealHistory) {
        viewModelScope.launch {
            val consumedMeal = ConsumedMeal(
                name = screenState.value.mealNameInput,
                type = screenState.value.selectedMeal.toMealType(),
                calories = screenState.value.consumedCaloriesInput.toIntOrNull() ?: 0,
                dateTime = getTodayDate(),
                id = "0"
            )
            val success = mealUseCase.addConsumedMeal(consumedMeal)
            if (success) {
                loadConsumedMealsForToday()
                loadDailyCaloriesSummary()
                showMealAddedSnackBar()
                updateState {
                    it.copy(
                        isAddMealSheetVisible = false,
                        isAddButtonEnabled = false,
                        consumedCaloriesInput = "",
                        mealNameInput = "",
                        selectedMeal = NutritionScreenState.MealTypeUiState.Breakfast
                    )
                }
            }
        }
    }

    private fun showMealAddedSnackBar() {
        viewModelScope.launch(Dispatchers.IO) {
            updateState { it.copy(isAddMealSnackBarVisible = true) }
            delay(2000)
            updateState { it.copy(isAddMealSnackBarVisible = false) }
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

    override fun onSuggestedMealClicked(mealId: Long) {
        sendEffect(NutritionEffect.NavigateToSuggestedMealDetails(mealId = mealId))
    }

    override fun onViewAllMealHistoryClicked() {
        sendEffect(NutritionEffect.NavigateToMealHistory)
    }

    override fun onDroppedMenuClick() {
        updateState { it.copy(isDroppedMenuVisible = true) }
    }

    override fun onSnackBarHided() {
        updateState { it.copy(isAddMealSnackBarVisible = false) }
    }

    private companion object  {
        val defaultTypes = listOf(
            MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER, MealType.SNACK
        )
        const val START_DAY_TIME = "T00:00:00"
        const val END_DAY_TIME = "T23:59:59"
    }
}