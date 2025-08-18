package com.cairosquad.evolvefit.viewmodel.editProfile

import com.cairosquad.evolvefit.domain.usecase.equipment.ManageEquipmentUseCase
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileScreenState.EditProfileBottomSheetType
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.failed_to_load_equipments
import evolvefit.composeapp.generated.resources.failed_to_load_user_equipments
import evolvefit.composeapp.generated.resources.failed_to_load_user_profile
import evolvefit.composeapp.generated.resources.failed_to_load_user_workout_days
import evolvefit.composeapp.generated.resources.failed_to_update_equipments
import evolvefit.composeapp.generated.resources.failed_to_update_workout_days
import kotlinx.datetime.LocalDate

class EditProfileViewModel(
    private val manageProfileUseCase : ManageProfileUseCase,
    private val manageEquipmentUseCase: ManageEquipmentUseCase
) :BaseViewModel<EditProfileScreenState, EditProfileEffect>(
    EditProfileScreenState()) , EditProfileInteractionListener
{
    init {
        getProfile()
        getAllEquipment()
    }
    private fun editProfile(profile : EditProfileScreenState.ProfileUiState)
    {
        tryToCall(
            block={manageProfileUseCase.editProfile(profile.toDomain() )},
            onSuccess = { profile->
                updateState { it.copy(profile=profile.toUiState()) }
            },
            onError = {
                updateState { it.copy(errorMessage = Res.string.failed_to_load_user_profile) }
            }

        )
    }

    private fun getProfile()
    {
        tryToCall(
            block={manageProfileUseCase.getProfile()},
            onSuccess ={profile->
                updateState { it.copy(profile=profile.toUiState()) }
            },
            onError = {
                updateState { it.copy(errorMessage = Res.string.failed_to_load_user_profile) }
            }

        )
    }
    private fun getAllEquipment()
    {
        tryToCall(
            block={manageEquipmentUseCase.getAllEquipments()},
            onSuccess ={equipment->
                updateState { it.copy(allEquipments = equipment.toEquipmentUiStateSet()) }
            },
            onError = {
                updateState { it.copy(errorMessage = Res.string.failed_to_load_equipments) }
            }

        )

    }

    override fun onBackClicked() {
        sendEffect(EditProfileEffect.NavigateBack)
    }
    override fun onSaveChangesClicked(profile: EditProfileScreenState.ProfileUiState) {
        editProfile(profile)
        sendEffect(EditProfileEffect.NavigateBack)

    }


    override fun onDateOfBirthClicked() {
        updateState { it.copy(bottomSheetType = EditProfileBottomSheetType.BIRTHDAY) }
    }

    override fun onEquipmentClicked()  {
        updateState { it.copy(bottomSheetType = EditProfileBottomSheetType.EQUIPMENT) }

    }
    override fun onWorkoutDaysClicked() {
        updateState { it.copy(bottomSheetType = EditProfileBottomSheetType.WORKOUTS_DAYS) }
    }

    override fun onEquipmentChanged(equipments:Set<EditProfileScreenState.EquipmentUiState>) {
        updateState {
                state->
            state.copy(profile = state.profile.copy(equipments=equipments))
        }
    }

    override fun onWorkoutDaysChanged(workoutDays: Set<EditProfileScreenState.WeekDayUiState>) {
        updateState {
                state->
            state.copy(profile = state.profile.copy(workoutDays = workoutDays))
        }
    }

    override fun onGenderClicked() {
        updateState { it.copy(bottomSheetType = EditProfileBottomSheetType.GENDER) }

    }

    override fun onHeightClicked() {
        updateState { it.copy(bottomSheetType = EditProfileBottomSheetType.HEIGHT) }
    }

    override fun onWeightClicked() {
        updateState { it.copy(bottomSheetType = EditProfileBottomSheetType.WEIGHT) }
    }

    override fun onMainGoalClicked() {
        updateState { it.copy(bottomSheetType = EditProfileBottomSheetType.MAIN_GOAL) }

    }

    override fun onPreferredMeasurementStandardClicked() {
        updateState { it.copy(bottomSheetType = EditProfileBottomSheetType.MEASUREMENT_STANDARD) }
    }

    override fun onImageUrlClicked() {
        updateState { it.copy(isImagePickerOpened = true) }
        sendEffect(EditProfileEffect.OpenImagePicker)
    }

    override fun onFullNameChanged(fullName: String) {
        updateState { state ->
            state.copy(profile = state.profile.copy(fullName = fullName))
        }
    }

    override fun onDateOfBirthChanged(dateOfBirth: LocalDate) {
        updateState { state ->
            state.copy(profile = state.profile.copy(dateOfBirth = dateOfBirth))
        }
    }

    override fun onGenderChanged(gender: String) {
        updateState { state ->
            state.copy(profile = state.profile.copy(gender = gender))
        }
    }

    override fun onHeightChanged(height: Float) {
        updateState { state ->
            state.copy(profile = state.profile.copy(height = height))
        }

    }

    override fun onWeightChanged(weight: Float) {
        updateState { state ->
            state.copy(profile = state.profile.copy(weight = weight))
        }
    }

    override fun onMainGoalChanged(mainGoal: String) {
        updateState { state ->
            state.copy(profile = state.profile.copy(mainGoal = mainGoal))
        }
    }

    override fun onPreferredMeasurementStandardChanged(measurementStandard: String) {
        updateState { state ->
            state.copy(profile = state.profile.copy(preferredMeasurementStandard = measurementStandard))
        }
    }

    override fun onImageUrlChanged(imageUrl: String) {
        updateState { state ->
            state.copy(profile = state.profile.copy(imageUrl = imageUrl))
        }
    }

    override fun onImagePickerDismissed() {
        updateState { it.copy(isImagePickerOpened = false) }
    }
    override fun onBottomSheetDismissed() {
        updateState { it.copy(bottomSheetType = null) }
    }


}