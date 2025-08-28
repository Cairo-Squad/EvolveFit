package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.exception.InternetConnectionException
import com.cairosquad.evolvefit.domain.exception.InvalidEmailFormatException
import com.cairosquad.evolvefit.domain.exception.InvalidPasswordException
import com.cairosquad.evolvefit.domain.exception.NetworkException
import com.cairosquad.evolvefit.domain.exception.UnknownException
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthenticationUseCase
import com.cairosquad.evolvefit.domain.usecase.equipment.ManageEquipmentUseCase
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState.Goal
import com.cairosquad.evolvefit.viewmodel.utils.asByteArray
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.error_email_already_used
import evolvefit.composeapp.generated.resources.error_invalid_email
import evolvefit.composeapp.generated.resources.error_invalid_email_format
import evolvefit.composeapp.generated.resources.error_invalid_password
import evolvefit.composeapp.generated.resources.error_no_internet
import evolvefit.composeapp.generated.resources.error_unexpected
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.StringResource

class RegisterViewModel(
    private val authenticationUseCase: AuthenticationUseCase,
    private val manageEquipmentUseCase: ManageEquipmentUseCase,
    private val manageProfileUseCase: ManageProfileUseCase
) : BaseViewModel<RegisterScreenState, RegisterEffect>(RegisterScreenState()),
    RegisterInteractionListener {

    init {
        getEquipments()
    }

    override fun onNextClicked() {
        updateState {
            it.copy(currentStep = it.currentStep + 1)
        }
        updateNextButtonEnableState()
    }

    override fun onBackClicked() {
        if (screenState.value.currentStep == 1) {
            sendEffect(RegisterEffect.NavigateBack)
        } else {
            updateState { it.copy(currentStep = it.currentStep - 1) }
        }
        updateNextButtonEnableState()
    }

    override fun onStartNowClicked() {
        val state = screenState.value
        tryToCall(
            onStart = { updateState { it.copy(isLoading = true) } },
            block = {
                authenticationUseCase.register(
                    profile = Profile(
                        name = state.userNameInput,
                        email = state.userEmailInput,
                        dateOfBirth = dateUiStateToDomain(state.dateOfBirthInput),
                        gender = state.selectedGender.toDomain(),
                        preferredMeasurementStandard = state.selectedMeasurementStandard.toDomain(),
                        height = state.selectedHeight,
                        weight = state.selectedWeight,
                        goal = state.selectedGoal.toDomain(),
                        imageUrl = "no image",
                        equipments = state.availableEquipments
                            .filter { state.selectedEquipments.contains(it.toolId) }
                            .map { it.toDomain() }
                            .toSet(),
                        workoutDays = state.workoutDays
                    ),
                    password = state.userPasswordInput,
                    workoutDays = state.selectedWeekDayUiState.map { it.toDomain() }.toSet(),
                    availableEquipment = state.selectedEquipments,
                )
                delay(500)
            },
            onSuccess = {
                val imageFileData = state.image.asByteArray()
                manageProfileUseCase.uploadProfileImage(
                    imageFileData.bytes,
                    imageFileData.fileName
                )
                sendEffect(RegisterEffect.NavigateToHome)
            },
            onError = { error -> handleRegisterError(error) },
            onEnd = { updateState { it.copy(isLoading = false)
            } }
        )
    }

    private fun getEquipments() {
        tryToCall(
            block = { manageEquipmentUseCase.getAllEquipments() },
            onSuccess = ::handleEquipmentsSuccess,
            onError = ::handleEquipmentsError
        )
    }

    private fun handleEquipmentsSuccess(equipments: Set<Equipment>) {
        updateState {
            it.copy(availableEquipments = equipments.map { equipment -> equipment.toUiState() }
                .toSet())
        }
    }

    private fun handleEquipmentsError(throwable: Throwable) {
        updateState {
            it.copy(errorMessage = "Failed to load equipments")
        }
    }

    override fun onHeightChanged(height: Float) {
        updateState { it.copy(selectedHeight = height) }
    }

    override fun onWeightChanged(weight: Float) {
        updateState { it.copy(selectedWeight = weight) }
    }

    override fun onNotificationToggled(type: RegisterScreenState.NotificationType) {
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

    override fun onWorkoutDaySelected(day: RegisterScreenState.WeekDayUiState) {
        updateState {
            val currentDays = it.selectedWeekDayUiState
            val updatedDays = if (day in currentDays) {
                currentDays - day
            } else {
                currentDays + day
            }
            it.copy(selectedWeekDayUiState = updatedDays)
        }
        updateNextButtonEnableState()
    }

    override fun onNoEquipmentSelected() {
        updateState {
            val isSelected = !it.isNoEquipmentSelected
            it.copy(
                isNoEquipmentSelected = isSelected,
                selectedEquipments = if (isSelected) emptySet() else it.selectedEquipments,
            )
        }
        updateNextButtonEnableState()
    }

    override fun onEquipmentToggled(equipmentId: Int) {
        updateState {
            val updatedSelection = it.selectedEquipments.toMutableSet()
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
            val defaults = if (unit == RegisterScreenState.MeasurementStandard.Metric) {
                170f to 70f
            } else {
                5.6f to 150f
            }

            it.copy(
                selectedMeasurementStandard =
                    if (it.selectedMeasurementStandard == unit) null
                    else unit,
                selectedHeight = defaults.first,
                selectedWeight = defaults.second
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
            7 -> state.selectedWeekDayUiState.isNotEmpty()
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

    private fun handleRegisterError(error: Throwable) {
        updateState { it.copy(isLoading = false) }
        val isUserAlreadyExists =
            error is NetworkException && error.message?.contains(
                "User already exists",
                ignoreCase = true
            ) == true

        when {
            error is NetworkException && error.message?.contains(
                "User already exists",
                ignoreCase = true
            ) == true -> {
                setErrorState(emailError = Res.string.error_email_already_used)
            }

            error is NetworkException -> {
                setErrorState(
                    emailError = Res.string.error_invalid_email
                )
            }

            error is InternetConnectionException -> {
                setErrorState(passwordError = Res.string.error_no_internet)
            }

            error is UnknownException -> {
                setErrorState(
                    passwordError = Res.string.error_unexpected
                )
            }

            error is InvalidEmailFormatException -> {
                setErrorState(emailError = Res.string.error_invalid_email_format)
            }

            error is InvalidPasswordException -> {
                setErrorState(
                    passwordError = Res.string.error_invalid_password
                )
            }

            else -> {
                setErrorState(passwordError = Res.string.error_unexpected)
            }
        }

    }

    private fun setErrorState(
        emailError: StringResource? = null,
        passwordError: StringResource? = null,
    ) {
        updateState {
            val updated = it.copy(
                emailError = emailError,
                passwordError = passwordError,
            )
            updated.copy()
        }
    }

    companion object {
        const val MAX_STEPS = 7
    }
}