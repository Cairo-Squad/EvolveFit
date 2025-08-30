package com.cairosquad.evolvefit.viewmodel.meal_details

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.entity.ConsumedMeal
import com.cairosquad.evolvefit.domain.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.utils.getCurrentIsoDateTime
import com.cairosquad.evolvefit.viewmodel.utils.toErrorMessageRes
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.error_unknown
import evolvefit.composeapp.generated.resources.ic_green_check_circle
import evolvefit.composeapp.generated.resources.ic_info
import evolvefit.composeapp.generated.resources.meal_added_snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

class MealDetailsViewModel(
    private val manageNutritionUseCase: ManageNutritionUseCase,
    private val mealId: String
) : BaseViewModel<MealDetailsScreenState, MealDetailsEffect>(MealDetailsScreenState()),
    MealDetailsInteractionListener {
    private var snackBarJob: kotlinx.coroutines.Job? = null

    init {
        getMealDetails()
    }

    override fun onBackClicked() {
        sendEffect(MealDetailsEffect.NavigateBack)
    }

    override fun onSaveMealClicked(mealId: String) {
        if (screenState.value.mealDetails.isFavouriteMeal.not()) {
            tryToCall(
                block = { manageNutritionUseCase.addFavouriteMealById(mealId) },
                onSuccess = { handleSaveMealSuccess() },
                onError = {}
            )
        } else {
            tryToCall(
                block = { manageNutritionUseCase.deleteFavouriteMeal(mealId) },
                onSuccess = { handleDeleteMealSuccess() },
                onError = {}
            )
        }
    }

    private fun handleDeleteMealSuccess() {
        viewModelScope.launch {
            updateState { current ->
                current.copy(
                    mealDetails = current.mealDetails.copy(
                        isFavouriteMeal = false,
                    ),
                    showSaveMealSuccessSnackBar = false
                )
            }
        }
    }

    override fun onRetryClicked() {
        getMealDetails()
    }

    override fun onAddWorkoutClicked() {
        getCaloriesSummary { remainingCalories ->
            addWorkout(remainingCalories)
        }
    }

    private fun getCaloriesSummary(successCallback: (Int) -> Unit) {
        tryToCall(
            block = { manageNutritionUseCase.getDailyCalorieSummary() },
            onSuccess = { successCallback(getRemainingCalories(it)) },
            onError = { e ->
                updateState {
                    it.copy(
                        mealAddingStatus = MealDetailsScreenState.MealAddingStatus.READY,
                    )
                }

                showMessage(Res.string.error_unknown, Res.drawable.ic_info)
            },
            onStart = { updateState { it.copy(mealAddingStatus = MealDetailsScreenState.MealAddingStatus.LOADING) } }
        )
    }

    private fun getRemainingCalories(dailyCalorieSummary: DailyCalorieSummary): Int {
        return dailyCalorieSummary.totalCalories - dailyCalorieSummary.consumedCalories
    }

    private fun addWorkout(remainingCalories: Int) {
        val caloriesInput = screenState.value.mealDetails.calories.toString()
        val meal = ConsumedMeal(
            name = screenState.value.mealDetails.name,
            type = screenState.value.mealDetails.mealType.toMealType(),
            calories = 0,
            dateTime = getCurrentIsoDateTime(),
            id = "0"
        )
        tryToCall(
            block = {
                manageNutritionUseCase.saveConsumedMeal(
                    consumedMealCaloriesInput = caloriesInput,
                    consumedMeal = meal,
                    remainingCalories = remainingCalories
                )
            },
            onSuccess = {
                updateState { it.copy(mealAddingStatus = MealDetailsScreenState.MealAddingStatus.READY) }
                showMessage(
                    Res.string.meal_added_snackbar,
                    Res.drawable.ic_green_check_circle
                )
            },
            onError = { e ->
                updateState {
                    it.copy(
                        mealAddingStatus = MealDetailsScreenState.MealAddingStatus.READY,
                    )
                }

                showMessage(Res.string.error_unknown, Res.drawable.ic_info)
            }
        )
    }

    private fun handleSaveMealSuccess() {
        viewModelScope.launch {
            updateState { current ->
                current.copy(
                    mealDetails = current.mealDetails.copy(
                        isFavouriteMeal = true,
                    ),
                    showSaveMealSuccessSnackBar = true
                )
            }
            delay(3000)
            updateState { it.copy(showSaveMealSuccessSnackBar = false) }
        }
    }

    private fun getMealDetails() {
        tryToCall(
            onStart = { setScreenStatus(MealDetailsScreenState.ScreenStatus.LOADING) },
            block = { manageNutritionUseCase.getMealById(mealId) },
            onSuccess = ::onGetMealDetailsSuccess,
            onError = ::onGetMealDetailsError
        )
    }

    private fun setScreenStatus(status: MealDetailsScreenState.ScreenStatus) {
        updateState { current ->
            current.copy(screenStatus = status)
        }
    }

    private fun onGetMealDetailsSuccess(meal: Meal) {
        updateState { current ->
            current.copy(
                mealDetails = meal.toMealDetailsUiState(),
                screenStatus = MealDetailsScreenState.ScreenStatus.SUCCESS,
                errorMessage = null
            )
        }
        loadMealSaveStatus(meal.id)
    }

    private fun loadMealSaveStatus(mealId: String) {
        tryToCall(
            block = { manageNutritionUseCase.getFavouriteMeals() },
            onSuccess = { updateMealSaveStatus(mealId, it) },
            onError = ::onGetMealDetailsError,
        )
    }

    private fun updateMealSaveStatus(mealId: String, meals: List<SuggestedMeal>) {
        updateState { state ->
            state.copy(
                mealDetails = state.mealDetails.copy(isFavouriteMeal = meals.map { it.id }
                    .contains(mealId)),
            )
        }
    }

    private fun onGetMealDetailsError(e: Throwable) {
        updateState { current ->
            current.copy(
                errorMessage = e.toErrorMessageRes(),
                screenStatus = MealDetailsScreenState.ScreenStatus.ERROR
            )
        }
    }

    private fun showMessage(
        message: StringResource,
        icon: DrawableResource,
        duration: Long = 3000L
    ) {
        snackBarJob?.cancel()
        updateState {
            it.copy(
                isSnackBarVisible = true,
                snackBarMessage = message,
                snackBarIcon = icon
            )
        }
        snackBarJob = viewModelScope.launch(Dispatchers.Main) {
            delay(duration)
            updateState {
                it.copy(
                    isSnackBarVisible = false,
                )
            }
        }
    }
}