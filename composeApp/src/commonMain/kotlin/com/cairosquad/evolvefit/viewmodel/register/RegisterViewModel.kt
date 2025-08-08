package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.domain.usecase.GetEquipmentsUseCase
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import com.cairosquad.evolvefit.entity.FitnessGoal
import com.cairosquad.evolvefit.entity.Gender
import com.cairosquad.evolvefit.entity.MeasurementUnit
import com.cairosquad.evolvefit.entity.User
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState.Goal

class RegisterViewModel(
    private val authUseCase: AuthUseCase,
    private val getEquipmentsUseCase: GetEquipmentsUseCase
) : BaseViewModel<RegisterScreenState, RegisterEffect>(RegisterScreenState()),
    RegisterInteractionListener {
    override fun onClickNext() {
        updateState { current ->
            val nextStep = current.currentStep + 1
            val newState = current.copy(currentStep = nextStep)
            newState.copy(isNextButtonEnabled = updateNextButtonEnableState(newState))
        }
    }

    override fun onClickBack() {
        if (screenState.value.currentStep == 1) {
            sendEffect(RegisterEffect.NavigateBack)
        } else {
            updateState { it.copy(currentStep = it.currentStep - 1) }
        }
    }

    override fun onClickStartNow() {
        val state = screenState.value

        val user = User(
            name = state.name,
            email = state.email,
            password = state.password,
            gender = state.selectedGender?.toDomain() ?: Gender.MALE,
            dateOfBirth = state.dateOfBirth,
            unit = state.selectedMeasurementUnit?.toDomain() ?: MeasurementUnit.METRIC,
            goal = state.selectedGoal?.toDomain() ?: FitnessGoal.STAY_IN_SHAPE,
            height = state.selectedHeight,
            weight = state.selectedWeight,
            tools = state.selectedEquipments.toToolsDomain(state),
            workoutDays = state.selectedWorkoutDays.map { it.toDomain() }
        )

        tryToCall(
            block = { authUseCase.register(user) },
            onSuccess = {
                sendEffect(RegisterEffect.NavigateToHome)
            },
            onError = { error ->
                updateState { it.copy(errorMessage = error.message ?: "Registration failed") }
            },
            onStart = {
                updateState { it.copy(isLoading = true) }
            },
            onEnd = {
                updateState { it.copy(isLoading = false) }
            }
        )
    }


    private fun getEquipments() {
        tryToCall(
            block = { getEquipmentsUseCase.getEquipments() },
            onSuccess = { tools ->
                val equipments = tools.map { tool ->
                    RegisterScreenState.Equipment(
                        name = tool.name,
                        isSelected = false
                    )
                }
                updateState { it.copy(availableEquipments = equipments) }
            },
            onError = {
                updateState { it.copy(errorMessage = "") }
            }
        )
    }

    override fun onHeightChanged(height: Float) {
        updateState { it.copy(selectedHeight = height) }
    }

    override fun onWeightChanged(weight: Float) {
        updateState { it.copy(selectedWeight = weight) }
    }

    override  fun onNotificationToggled(type: RegisterScreenState.NotificationType) {
        updateState { state ->
            val updatedSettings = when (type) {
                is RegisterScreenState.NotificationType.Workout -> {
                    state.notificationSettings.copy(
                        isWorkoutReminderEnabled = !state.notificationSettings.isWorkoutReminderEnabled
                    )
                }
                is RegisterScreenState.NotificationType.Water -> {
                    state.notificationSettings.copy(
                        isWaterReminderEnabled = !state.notificationSettings.isWaterReminderEnabled
                    )
                }
                is RegisterScreenState.NotificationType.BodyWeight -> {
                    state.notificationSettings.copy(
                        isBodyWeightReminderEnabled = !state.notificationSettings.isBodyWeightReminderEnabled
                    )
                }
                is RegisterScreenState.NotificationType.Challenges -> {
                    state.notificationSettings.copy(
                        isChallengesReminderEnabled = !state.notificationSettings.isChallengesReminderEnabled
                    )
                }
            }
            state.copy(notificationSettings = updatedSettings)
        }
    }

    override fun onWorkoutDaySelected(day: RegisterScreenState.WorkoutDay) {
        updateState {
            val currentDays = it.selectedWorkoutDays
            val updatedDays = if (day in currentDays) {
                currentDays - day
            } else {
                currentDays + day
            }
            val newState = it.copy(selectedWorkoutDays = updatedDays)
            newState.copy(isNextButtonEnabled = updateNextButtonEnableState(newState))
        }
    }

    override fun onNoEquipmentSelected() {
        updateState {
            val isSelected = !it.isNoEquipmentSelected
            it.copy(
                isNoEquipmentSelected = isSelected,
                selectedEquipments = if (isSelected) emptyList() else it.selectedEquipments,
                isNextButtonEnabled = updateNextButtonEnableState(
                    it.copy(
                        isNoEquipmentSelected = isSelected,
                        selectedEquipments = if (isSelected) emptyList() else it.selectedEquipments
                    )
                )
            )
        }
    }

    override fun onEquipmentToggled(equipmentId: String) {
        updateState {
            val updatedSelection = it.selectedEquipments.toMutableList()
            if (equipmentId in updatedSelection) {
                updatedSelection.remove(equipmentId)
            } else {
                updatedSelection.add(equipmentId)
            }

            val newState = it.copy(
                isNoEquipmentSelected = false,
                selectedEquipments = updatedSelection
            )
            newState.copy(isNextButtonEnabled = updateNextButtonEnableState(newState))
        }
    }


    override fun onGenderClicked(gender: RegisterScreenState.Gender) {
        updateState {
            val newState =
                it.copy(selectedGender = if (it.selectedGender == gender) null else gender)
            newState.copy(isNextButtonEnabled = updateNextButtonEnableState(newState))
        }
    }

    override fun onMeasurementUnitClicked(unit: RegisterScreenState.MeasurementUnit) {
        updateState {
            val newState =
                it.copy(selectedMeasurementUnit = if (it.selectedMeasurementUnit == unit) null else unit)
            newState.copy(isNextButtonEnabled = updateNextButtonEnableState(newState))
        }
    }

    override fun onGoalClicked(goal: Goal) {
        updateState {
            val newState = it.copy(selectedGoal = if (it.selectedGoal == goal) null else goal)
            newState.copy(isNextButtonEnabled = updateNextButtonEnableState(newState))
        }
    }

    private fun updateNextButtonEnableState(state: RegisterScreenState): Boolean {
        return when (state.currentStep) {
            1 -> state.selectedGender != null
            2 -> state.selectedMeasurementUnit != null
            3 -> true
            4 -> state.selectedGoal != null
            5 -> state.isNoEquipmentSelected || state.selectedEquipments.isNotEmpty()
            6 -> true
            7 -> state.selectedWorkoutDays.isNotEmpty()
            else -> false
        }
    }
    companion object {
        const val MAX_STEPS = 8
    }
}