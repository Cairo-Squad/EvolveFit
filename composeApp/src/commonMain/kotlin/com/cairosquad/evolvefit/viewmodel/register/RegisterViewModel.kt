package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import com.cairosquad.evolvefit.entity.FitnessGoal
import com.cairosquad.evolvefit.entity.Gender
import com.cairosquad.evolvefit.entity.MeasurementUnit
import com.cairosquad.evolvefit.entity.User
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState.Goal

class RegisterViewModel(
    private val authUseCase: AuthUseCase,
) : BaseViewModel<RegisterScreenState, RegisterEffect>(RegisterScreenState()),
    RegisterInteractionListener {
    override fun onClickNext() {
        updateState {
            it.copy(currentStep = it.currentStep + 1)
        }
        updateNextButtonEnableState()
    }

    override fun onClickBack() {
        if (screenState.value.currentStep == 1) {
            sendEffect(RegisterEffect.NavigateBack)
        } else {
            updateState { it.copy(currentStep = it.currentStep - 1) }
        }
    }

    override fun onClickStartNow() {
        val user = screenState.value.toDomain()

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
            block = { authUseCase.getEquipments() },
            onSuccess = { tools ->
                val equipments = tools.map { tool ->
                    RegisterScreenState.Equipment(
                        toolId = tool.id,
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
            it.copy(selectedWorkoutDays = updatedDays)
        }
        updateNextButtonEnableState()
    }

    override fun onNoEquipmentSelected() {
        updateState {
            val isSelected = !it.isNoEquipmentSelected
            it.copy(
                isNoEquipmentSelected = isSelected,
                selectedEquipments = if (isSelected) emptyList() else it.selectedEquipments,
            )
        }
        updateNextButtonEnableState()
    }

    override fun onEquipmentToggled(equipmentId: Long) {
        updateState {
            val updatedSelection = it.selectedEquipments.toMutableList()
            if (equipmentId in updatedSelection) {
                updatedSelection.remove(equipmentId)
            } else {
                updatedSelection.add(equipmentId)
            }

            it.copy(
                isNoEquipmentSelected = false,
                selectedEquipments = updatedSelection
            )
        }
        updateNextButtonEnableState()
    }


    override fun onImagePickerClick() {
        updateState { it.copy(isImagePickerOpen = true) }
    }

    override fun onImagePickerDismiss() {
        updateState { it.copy(isImagePickerOpen = false) }
    }

    override fun onImageRetrieved(image: UiImage) {
        updateState { it.copy(image = image, isImagePickerOpen = false) }
        updateNextButtonEnableState()
    }

    override fun onUserNameChange(userName: String) {
        updateState { it.copy(userNameInput = userName) }
        updateNextButtonEnableState()
    }

    override fun onUserEmailChange(userEmail: String) {
        updateState { it.copy(userEmailInput = userEmail) }
        updateNextButtonEnableState()
    }

    override fun onUserPasswordChange(userPassword: String) {
        updateState { it.copy(userPasswordInput = userPassword) }
        updateNextButtonEnableState()
    }

    override fun onPasswordVisibilityClick() {
        updateState { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    override fun onDateOfBirthChange(dateOfBirth: String) {
        updateState { it.copy(dateOfBirthInput = dateOfBirth) }
        updateNextButtonEnableState()
    }


    override fun onGenderClicked(gender: RegisterScreenState.Gender) {
        updateState {
            it.copy(selectedGender = if (it.selectedGender == gender) null else gender)
        }
        updateNextButtonEnableState()
    }

    override fun onMeasurementUnitClicked(unit: RegisterScreenState.MeasurementStandard) {
        updateState {
            it.copy(
                selectedMeasurementStandard =
                    if (it.selectedMeasurementStandard == unit) null
                    else unit
            )
        }
        updateNextButtonEnableState()
    }

    override fun onGoalClicked(goal: Goal) {
        updateState {
            it.copy(selectedGoal = if (it.selectedGoal == goal) null else goal)
        }
        updateNextButtonEnableState()
    }

    private fun updateNextButtonEnableState() {
        val state = screenState.value
        val isNextButtonEnabled = when (state.currentStep) {
            1 -> state.selectedGender != null
            2 -> state.selectedMeasurementStandard != null
            3 -> true
            4 -> state.selectedGoal != null
            5 -> state.isNoEquipmentSelected || state.selectedEquipments.isNotEmpty()
            6 -> true
            7 -> state.selectedWorkoutDays.isNotEmpty()
            else -> isCredentialsValid()
        }
        updateState { it.copy(isNextButtonEnabled = isNextButtonEnabled) }
    }

    private fun isCredentialsValid(): Boolean {
        val state = screenState.value
        return state.userNameInput.isNotEmpty()
                && state.userEmailInput.isNotEmpty()
                && state.userPasswordInput.isNotEmpty()
                && state.dateOfBirthInput.isNotEmpty()
    }
    companion object {
        const val MAX_STEPS = 8
    }
}