package com.cairosquad.evolvefit.viewmodel.nutrition

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.usecases.MealUseCase
import com.cairosquad.evolvefit.domain.usecases.WaterIntakeUseCase
import com.cairosquad.evolvefit.entity.DailyWaterIntake
import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.MealType
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.utils.getTodayDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NutritionViewModel(
    private val mealUseCase: MealUseCase,
    private val waterIntakeUseCase: WaterIntakeUseCase
) :
    BaseViewModel<NutritionScreenState, NutritionEffect>(
        NutritionScreenState()
    ), NutritionInteractionListener {

    init {
        updateNutritionScreenData()
    }

    private fun updateNutritionScreenData() {
        loadTodayMeals()
        loadDailyCaloriesSummary()
        loadDailyWaterSummary()
        loadMealsHistoryForDay()
        loadSuggestedMeals()
    }

    fun loadSuggestedMeals() {
        tryToCall(
            block = { mealUseCase.getSuggestedMeals() },
            onStart = { updateState { it.copy(dataRequestState = NutritionScreenState.DataRequestState.LOADING) } },
            onSuccess = { meals ->
                val uiMeals = meals.map { it.toSuggestedMealUi() }
                updateState {
                    it.copy(
                        suggestedMeals = uiMeals,
                        dataRequestState = NutritionScreenState.DataRequestState.SUCCESS
                    )
                }

            },
            onError = { e ->
                updateState {
                    it.copy(
                        errorMessage = e.message,
                        dataRequestState = NutritionScreenState.DataRequestState.FAIL
                    )
                }
            }
        )
    }

    private fun loadTodayMeals() {
        tryToCall(
            { mealUseCase.getConsumedMealsForToday() },
            onStart = {
                updateState { it.copy(todayMealUiStates = getDefaultTodayMeals()) }
            },
            onSuccess =
                { meals ->

                    val filteredMeals = meals.filter { isToday(it.dateTime)}
                    val grouped = filteredMeals.groupBy { it.type }
                    val todayMeals = defaultTypes.map { type ->
                        val mealsOfType = grouped[type].orEmpty()
                        NutritionScreenState.TodayMealUiState(
                            type = type.toMealUiState(),
                            calories = mealsOfType.sumOf { (it.calories) }.toFloat(),
                            icon = type.toMealUiState().toMealIcon()
                        )
                    }

                    updateState { it.copy(todayMealUiStates = todayMeals) }
                }, onError = {}
        )
    }

    private fun loadMealsHistoryForDay() {
        tryToCall(
            { mealUseCase.getConsumedMealsForToday() },
            onSuccess =
                { meals ->
                    val historyMeals = meals
                        .sortedByDescending { it.dateTime }
                        .map { it.toMealHistoryUi() }

                    updateState {
                        it.copy(mealsHistoryForDay = historyMeals)
                    }
                }, onError = {}
        )
    }
     fun loadAllMealsHistory() {
        tryToCall(
            { mealUseCase.getConsumedMealsForToday() },
            onSuccess =
                { meals ->
                    val historyMeals = meals
                        .sortedByDescending { it.dateTime }
                        .map { it.toMealHistoryUi() }
                    updateState {
                        it.copy(mealsHistory = historyMeals)
                    }
                }, onError = {}
        )
    }

    private fun loadDailyCaloriesSummary() {
        tryToCall(
            block = {
                mealUseCase.getDailyCalorieSummary()
            },
            onSuccess = { meals ->
                val remainingCalories = (meals.totalCalories - meals.caloriesConsumed).toFloat()
                updateState {
                    it.copy(
                        caloriesGoal = meals.caloriesConsumed.toFloat(),
                        caloriesConsumed = meals.totalCalories.toFloat(),
                        remainingCalories = remainingCalories
                    )
                }
            },
            onError = { e ->
                updateState { it.copy(errorMessage = e.message) }
            }
        )
    }

    private fun loadDailyWaterSummary() {
        tryToCall(
            block = {
                waterIntakeUseCase.getDailyWaterIntake()
            },
            onSuccess = { water ->
                updateState {
                    it.copy(
                        waterConsumedLiters = water.total
                    )
                }
            },
            onError = { e ->
                updateState { it.copy(errorMessage = e.message) }
            }
        )
    }

    private fun getDefaultTodayMeals(): List<NutritionScreenState.TodayMealUiState> {
        return NutritionScreenState.MealTypeUiState.entries.map { mealType ->
            NutritionScreenState.TodayMealUiState(
                type = mealType,
                calories = 0.0f,
                icon = mealType.toMealIcon()
            )
        }
    }

    override fun onAddWaterClicked() {
        updateState { it.copy(isAddWaterSheetVisible = true) }
    }

    override fun onConfirmAddWaterClicked(waterAmount: Float) {
        val waterIntake = DailyWaterIntake(screenState.value.waterAmountInput.toFloatOrNull() ?: 0f)
        println("waterAmount : ${waterIntake}")
        tryToCall(
            block = {
                waterIntakeUseCase.addWaterIntake(waterAmount)
            },
            onSuccess = {
                if (it) {
                    updateState {
                        it.copy(
                            isAddWaterSheetVisible = false,
                            isAddButtonEnabled = false,
                            waterAmountInput = ""
                        )
                    }
                }
            },
            onEnd = { loadDailyWaterSummary() },
            onError = {}
        )

    }

    override fun onDismissWaterClicked() {
        updateState { it.copy(isAddWaterSheetVisible = false) }
    }

    override fun onWaterAmountChange(waterAmount: String) {
        updateState {
            it.copy(
                waterAmountInput = waterAmount,
                isAddButtonEnabled = waterAmount.isNotBlank()
            )
        }
    }

    override fun onAddMealSheetClicked() {
        updateState { it.copy(isAddMealSheetVisible = true) }
    }

    override fun onConfirmAddMealClicked(mealHistory: NutritionScreenState.MealHistory) {
        viewModelScope.launch {
            val consumedMeal = ConsumedMeal(
                name = screenState.value.mealNameInput,
                type = screenState.value.selectedMeal.toMealType(),
                calories = screenState.value.mealCaloriesInput.toIntOrNull() ?: 0,
                dateTime = getTodayDate(),
                id = "0"
            )
            println("meal added : ${consumedMeal.toString()}")
            val success = mealUseCase.addMeal(consumedMeal)
            if (success) {
                updateNutritionScreenData()
                showMealAddedSnackBar()
                updateState {
                    it.copy(
                        isAddMealSheetVisible = false,
                        isAddButtonEnabled = false,
                        mealCaloriesInput = "",
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
                isAddButtonEnabled = name.isNotBlank() && it.mealCaloriesInput.isNotBlank()
            )
        }
    }

    override fun onMealCaloriesChanged(calories: String) {
        updateState {
            it.copy(
                mealCaloriesInput = calories,
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

    private companion object {
        val defaultTypes = listOf(
            MealType.BREAKFAST,
            MealType.LUNCH,
            MealType.DINNER,
            MealType.SNACK
        )
    }
}