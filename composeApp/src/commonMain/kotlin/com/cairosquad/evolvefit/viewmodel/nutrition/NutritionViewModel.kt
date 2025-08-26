package com.cairosquad.evolvefit.viewmodel.nutrition

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.exception.ExceededCaloriesException
import com.cairosquad.evolvefit.domain.exception.ExceededWaterLimitException
import com.cairosquad.evolvefit.domain.exception.InternetConnectionException
import com.cairosquad.evolvefit.domain.exception.InvalidNumberFormatException
import com.cairosquad.evolvefit.domain.exception.MealNotFoundException
import com.cairosquad.evolvefit.domain.exception.NetworkException
import com.cairosquad.evolvefit.domain.exception.TimeoutException
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.domain.entity.ConsumedMeal
import com.cairosquad.evolvefit.domain.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.domain.entity.DailyWaterSummary
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.exception.FieldException
import com.cairosquad.evolvefit.domain.exception.LengthTooLargeException
import com.cairosquad.evolvefit.domain.exception.NumberTooLargeException
import com.cairosquad.evolvefit.domain.model.FieldType
import com.cairosquad.evolvefit.domain.model.MealType
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.utils.getTodayDate
import com.cairosquad.evolvefit.viewmodel.utils.toErrorMessageRes
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.error_exceeded_calories
import evolvefit.composeapp.generated.resources.error_exceeded_water
import evolvefit.composeapp.generated.resources.error_invalid_number
import evolvefit.composeapp.generated.resources.error_meal_not_found
import evolvefit.composeapp.generated.resources.error_no_internet
import evolvefit.composeapp.generated.resources.error_number_too_large
import evolvefit.composeapp.generated.resources.error_text_too_long
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
        fetchNutritionData()
    }

    private fun fetchNutritionData() {
        tryToCall(
            block = { fetchAllNutritionSections() },
            onStart = { updateState { it.copy(screenStatus = NutritionScreenState.ScreenStatus.LOADING) } },
            onSuccess = { updateState { it.copy(screenStatus = NutritionScreenState.ScreenStatus.SUCCESS) } },
            onError = ::handleFetchNutritionError
        )
    }

    private fun handleFetchNutritionError(throwable: Throwable) {
        updateState {
            it.copy(
                screenStatus = NutritionScreenState.ScreenStatus.FAIL,
                screenErrorMessage = throwable.toErrorMessageRes()
            )
        }
        handleNutritionErrors(throwable)
    }

    private suspend fun fetchAllNutritionSections() {
        val calories = manageNutritionUseCase.getDailyCalorieSummary()
        onLoadDailyCaloriesSummarySuccess(calories)

        val water = manageNutritionUseCase.getDailyWaterSummary()
        onLoadDailyWaterSummarySuccess(water)
        val todayDate = getTodayDate()
        val meals = manageNutritionUseCase.getConsumedMealsByDate(
            "${todayDate}${START_DAY_TIME}",
            "${todayDate}${END_DAY_TIME}"
        )
        onLoadConsumedMealsForTodaySuccess(meals)

        val suggested = manageNutritionUseCase.getSuggestedMeals()
        handleSuggestedMeals(suggested)
    }

    private fun loadDailyCaloriesSummary() {
        callWithNutritionHandler(
            block = { manageNutritionUseCase.getDailyCalorieSummary() },
            onSuccess = ::onLoadDailyCaloriesSummarySuccess
        )
    }

    private fun onLoadDailyCaloriesSummarySuccess(dailyCalorieSummary: DailyCalorieSummary) {
        updateState {
            it.copy(
                dailyCaloriesGoal = dailyCalorieSummary.totalCalories.toFloat(),
                todayConsumedCalories = dailyCalorieSummary.consumedCalories.toFloat(),
                remainingDailyCalories = (dailyCalorieSummary.totalCalories - dailyCalorieSummary.consumedCalories)
            )
        }
    }

    private fun loadDailyWaterSummary() {
        callWithNutritionHandler(
            block = { manageNutritionUseCase.getDailyWaterSummary() },
            onSuccess = ::onLoadDailyWaterSummarySuccess
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
        val todayDate = getTodayDate()
        callWithNutritionHandler(
            block = {
                manageNutritionUseCase.getConsumedMealsByDate(
                    "${todayDate}${START_DAY_TIME}",
                    "${todayDate}${END_DAY_TIME}"
                )
            },
            onSuccess = ::onLoadConsumedMealsForTodaySuccess
        )

    }

    private fun onLoadConsumedMealsForTodaySuccess(consumedMeals: List<ConsumedMeal>) {
        updateConsumedMealsForToday(consumedMeals)
        val mealsGroupedByType = consumedMeals.groupBy { it.type }
        val todayMeals = defaultTypes.map { type ->
            val dailyMealSummaries = mealsGroupedByType[type].orEmpty()
            NutritionScreenState.DailyMealSummaryUiState(
                type = type.toMealUiState(),
                calories = dailyMealSummaries.sumOf { (it.calories) },
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
        callWithNutritionHandler(
            block = { manageNutritionUseCase.getSuggestedMeals() },
            onSuccess = { handleSuggestedMeals(meals = it) }
        )
    }

    private fun handleSuggestedMeals(meals: List<SuggestedMeal>) {
        val suggestedMeals = meals.map { it.toSuggestedMealUi() }
        updateState {
            it.copy(
                suggestedMeals = suggestedMeals,
                screenStatus = NutritionScreenState.ScreenStatus.SUCCESS
            )
        }
    }

    private fun getDefaultTodayMeals(): List<NutritionScreenState.DailyMealSummaryUiState> {
        return NutritionScreenState.MealTypeUiState.entries.map { mealType ->
            NutritionScreenState.DailyMealSummaryUiState(
                type = mealType, calories = 0, icon = mealType.toMealIcon()
            )
        }
    }

    override fun onAddWaterClicked() {
        val state = screenState.value
        if (state.dailyWaterGoal > 0 && state.todayConsumedWater >= state.dailyWaterGoal) {
            showSnackBar(Res.string.error_exceeded_water)
        } else {
            updateState { it.copy(isAddWaterSheetVisible = true) }
        }
    }

    override fun onConfirmAddWaterClicked(waterAmount: String) {
        val consumedWaterInput = screenState.value.consumedWaterInput.trim()
        val remaining = screenState.value.dailyWaterGoal - screenState.value.todayConsumedWater
        callWithNutritionHandler(
            block = { manageNutritionUseCase.saveConsumedWater(consumedWaterInput, remaining) },
            onSuccess = ::onAddWaterSuccess
        )
    }

    private fun onAddWaterSuccess(isAdded: Boolean) {
        if (isAdded) {
            loadDailyWaterSummary()
            updateState {
                it.copy(
                    isAddWaterSheetVisible = false,
                    isAddButtonEnabled = false,
                    consumedWaterInput = "",
                    waterInputError = null
                )
            }
        } else {
            showSnackBar(Res.string.error_unknown)
        }

    }

    override fun onWaterAmountChange(waterAmount: String) {
        updateState {
            it.copy(
                consumedWaterInput = waterAmount,
                isAddButtonEnabled = waterAmount.isNotBlank(),
                waterInputError = null
            )
        }
    }

    override fun onAddMealSheetClicked() {
        val state = screenState.value
        if (state.dailyCaloriesGoal > 0 && state.todayConsumedCalories >= state.dailyCaloriesGoal) {
            showSnackBar(Res.string.error_exceeded_calories)
        } else {
            updateState { it.copy(isAddMealSheetVisible = true) }
        }
    }

    override fun onConfirmAddMealClicked() {
        val caloriesInput = screenState.value.consumedCaloriesInput.trim()
        val remainingCalories = screenState.value.remainingDailyCalories
        val meal = ConsumedMeal(
            name = screenState.value.mealNameInput,
            type = screenState.value.selectedMeal.toMealType(),
            calories = 0,
            dateTime = getTodayDate(),
            id = "0"
        )
        callWithNutritionHandler(
            block = {
                manageNutritionUseCase.saveConsumedMeal(
                    caloriesInput,
                    meal,
                    remainingCalories
                )
            },
            onSuccess = ::onAddMealSuccess
        )
    }

    private fun onAddMealSuccess(isAdded: Boolean) {
        if (isAdded) {
            loadConsumedMealsForToday()
            loadDailyCaloriesSummary()
            showSnackBar(Res.string.meal_added_snackbar)
            updateState {
                it.copy(
                    isAddMealSheetVisible = false,
                    isInitialLoad = true,
                    isAddButtonEnabled = false,
                    consumedCaloriesInput = "",
                    mealNameInput = "",
                    selectedMeal = NutritionScreenState.MealTypeUiState.Breakfast,
                    mealCaloriesInputError = null,
                    mealNameInputError = null
                )
            }
        } else {
            showSnackBar(Res.string.error_unknown)
        }

    }

    private fun showSnackBar(message: StringResource) {
        snackBarJob?.cancel()
        snackBarJob = viewModelScope.launch(Dispatchers.Main) {
            this@NutritionViewModel.onSnackBarHided()
            updateSnackBarMessage()
            delay(200)
            updateState {
                it.copy(
                    isSnackBarVisible = true,
                    isAddMealSheetVisible = false,
                    isAddWaterSheetVisible = false,
                    errorMessage = message
                )
            }
            delay(2000)
            updateSnackBarMessage()
        }
    }

    private fun updateSnackBarMessage() {
        updateState {
            it.copy(
                errorMessage = null,
                isSnackBarVisible = false,
                isAddWaterSheetVisible = false
            )
        }
    }

    override fun onMealNameChanged(name: String) {
        val isAddButtonEnabled =
            name.isNotBlank() && screenState.value.consumedCaloriesInput.isNotBlank()
        updateState {
            it.copy(
                mealNameInput = name,
                isAddButtonEnabled = isAddButtonEnabled,
                mealNameInputError = null
            )
        }
    }

    override fun onMealCaloriesChanged(calories: String) {
        val isAddButtonEnabled =
            calories.isNotBlank() && screenState.value.mealNameInput.isNotBlank()
        updateState {
            it.copy(
                consumedCaloriesInput = calories,
                isAddButtonEnabled = isAddButtonEnabled,
                mealCaloriesInputError = null
            )
        }
    }

    override fun onMealTypeSelected(mealTypeUiState: NutritionScreenState.MealTypeUiState) {
        updateState { it.copy(selectedMeal = mealTypeUiState) }
    }

    override fun onToggleMealTypeMenu() {
        updateState { it.copy(isMealTypeMenuExpanded = !it.isMealTypeMenuExpanded,isInitialLoad = false) }
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
        updateState { it.copy(isSnackBarVisible = false) }
    }

    override fun onRetryClicked() {
        fetchNutritionData()
    }

    override fun onDismissMealClicked() {
        updateState {
            it.copy(
                isAddMealSheetVisible = false,
                mealNameInputError = null,
                mealCaloriesInputError = null,
                isInitialLoad = true
            )
        }
    }

    override fun onDismissWaterClicked() {
        updateState { it.copy(isAddWaterSheetVisible = false, waterInputError = null) }
    }

    override fun onRefresh() {
        updateState {
            it.copy(
                isRefreshing = true,
                screenStatus = NutritionScreenState.ScreenStatus.LOADING,
            )
        }
        fetchNutritionData()
        viewModelScope.launch {
            delay(500L)
            updateState { it.copy(isRefreshing = false) }
        }
    }

    private fun handleNutritionErrors(throwable: Throwable) {
        when (val error = throwable.toUiError()) {
            is NutritionScreenState.UiError.ScreenError -> handleScreenError(error)
            is NutritionScreenState.UiError.InputError -> handleInputError(error)
            is NutritionScreenState.UiError.SnackBarError -> handleSnackBarError(error)
        }
    }

    private fun handleScreenError(error: NutritionScreenState.UiError.ScreenError) {
        updateState {
            it.copy(
                screenStatus = NutritionScreenState.ScreenStatus.FAIL,
                mealCaloriesInputError = null,
                mealNameInputError = null,
                waterInputError = null,
                screenErrorMessage = error.message
            )
        }
    }

    private fun handleInputError(error: NutritionScreenState.UiError.InputError) {
        updateState {
            when (error.field) {
                FieldType.MEAL_CALORIES -> it.copy(mealCaloriesInputError = error.message)
                FieldType.MEAL_NAME -> it.copy(mealNameInputError = error.message)
                FieldType.WATER_INPUT -> it.copy(waterInputError = error.message)
            }
        }
    }

    private fun handleSnackBarError(error: NutritionScreenState.UiError.SnackBarError) {
        showSnackBar(error.message)
    }

    private fun Throwable.toUiError(): NutritionScreenState.UiError = when (this) {
        is FieldException -> toInputError()
        is InternetConnectionException,
        is TimeoutException,
        is NetworkException -> NutritionScreenState.UiError.ScreenError(Res.string.error_no_internet)
        is ExceededCaloriesException -> NutritionScreenState.UiError.SnackBarError(Res.string.error_exceeded_calories)
        is ExceededWaterLimitException -> NutritionScreenState.UiError.SnackBarError(Res.string.error_exceeded_water)
        is MealNotFoundException -> NutritionScreenState.UiError.SnackBarError(Res.string.error_meal_not_found)
        else -> NutritionScreenState.UiError.ScreenError(Res.string.error_unknown)
    }

    private fun FieldException.toInputError(): NutritionScreenState.UiError.InputError {
        val messageRes = when (this) {
            is InvalidNumberFormatException -> Res.string.error_invalid_number
            is NumberTooLargeException -> Res.string.error_number_too_large
            is LengthTooLargeException -> Res.string.error_text_too_long
            else -> Res.string.error_unknown
        }
        return NutritionScreenState.UiError.InputError(field, messageRes)
    }

    private fun <T> callWithNutritionHandler(
        block: suspend () -> T,
        onSuccess: (T) -> Unit
    ) {
        tryToCall(
            block = block,
            onSuccess = onSuccess,
            onError = ::handleNutritionErrors
        )
    }

    private companion object {
        val defaultTypes = listOf(
            MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER, MealType.SNACK
        )
        const val START_DAY_TIME = "T00:00:00"
        const val END_DAY_TIME = "T23:59:59"
    }
}