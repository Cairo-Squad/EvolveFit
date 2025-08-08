package com.cairosquad.evolvefit.viewmodel.nutrition

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.usecases.MealUseCase
import com.cairosquad.evolvefit.entity.Meal
import com.cairosquad.evolvefit.entity.MealType
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.utils.getTodayDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NutritionViewModel(private val mealUseCase: MealUseCase) :
    BaseViewModel<NutritionScreenState, NutritionEffect>(
        NutritionScreenState()
    ), NutritionInteractionListener {

    init {
        updateNutritionScreenData()
    }

    private fun updateNutritionScreenData() {
        loadTodayMeals()
        loadDailySummary()
        loadSuggestedMeals()
    }

    fun loadSuggestedMeals() {
        tryToCall(
            block = { mealUseCase.getSuggestedMeals() },
            onStart = { updateState { it.copy(dataRequestState = NutritionScreenState.DataRequestState.LOADING) } },
            onSuccess = { meals ->
                val uiMeals = meals.map { it.toSuggestedMealUi() }
                updateState { it.copy(suggestedMeals = uiMeals, dataRequestState = NutritionScreenState.DataRequestState.SUCCESS) }
            },
            onError = { e ->
                updateState { it.copy(errorMessage = e.message, dataRequestState = NutritionScreenState.DataRequestState.FAIL) }
            }
        )
    }

    private fun loadTodayMeals() {
        tryToCall(
            { mealUseCase.getMealHistoryForToday() },
            onSuccess = { meals ->
                val grouped = meals.groupBy { it.type }

                val todayMeals = defaultTypes.map { type ->
                    val mealsOfType = grouped[type].orEmpty()
                    NutritionScreenState.TodayMealUiState(
                        type = type.toMealUiState(),
                        calories = mealsOfType.sumOf { (it.calories.toInt()) }.toFloat(),
                        icon = type.toMealUiState().toMealIcon()
                    )
                }

                updateState { it.copy(todayMealUiStates = todayMeals) }
            },
            onError = {
                updateState { it.copy(todayMealUiStates = getDefaultTodayMeals()) }
            }
        )
    }

    fun loadDailySummary() {
        tryToCall(
            block = { mealUseCase.getDailySummary() },
            onSuccess = { meals ->
                val remainingCalories = (meals.totalCalories - meals.calorieGoal).toFloat()
                updateState {
                    it.copy(
                        caloriesGoal = meals.calorieGoal.toFloat(),
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
        //TODO link with use case
        updateState {
            it.copy(
                isAddWaterSheetVisible = false,
                isAddButtonEnabled = false,
                waterAmountInput = ""
            )
        }
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
            val meal = Meal(
                name = screenState.value.mealNameInput,
                type = screenState.value.selectedMeal.toMealType(),
                calories = screenState.value.mealCaloriesInput.toIntOrNull() ?: 0,
                dateTime = getTodayDate(),
                id = 0
            )
            println("meal added : ${meal.toString()}")
            val success = mealUseCase.addMeal(meal)
            if (success) {
                updateNutritionScreenData()
                showMealAddedSnackBar()
            }
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