package com.cairosquad.evolvefit.viewmodel.editProfile

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.usecase.equipment.ManageEquipmentUseCase
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileScreenState.EditProfileBottomSheetType
import kotlinx.datetime.LocalDate

class EditProfileViewModel(
    private val manageProfileUseCase : ManageProfileUseCase,
    private val manageEquipmentUseCase: ManageEquipmentUseCase
) :BaseViewModel<EditProfileScreenState, EditProfileEffect>(
    EditProfileScreenState()) , EditProfileInteractionListener
 {
     init {
         loadUserInfo()
     }

     private fun loadUserInfo()
     {
         tryToCall(
             block={manageProfileUseCase.getProfile()},
             onSuccess ={profile->
                 updateState { it.copy(profile=profile.toUiState()) }
             },
             onError = {
                 updateState { it.copy(errorMessage = "Failed to load profile") }
             }

         )
     }
     private fun editUserWorkoutDays(workoutDays: List<EditProfileScreenState.WeekDayUi>) {
         updateState { it.copy(bottomSheetType = EditProfileBottomSheetType.WORKOUTS_DAYS) }
         tryToCall(
             block = { manageProfileUseCase.editUserWorkoutDays(workoutDays.toDomainList()) },
             onSuccess = {
             },
             onError = {
                 updateState { it.copy(errorMessage = "Failed to update workout days") }
             }
         )
     }

     private fun editUserEquipments(equipments: List<EditProfileScreenState.EquipmentUiState>) {
         tryToCall(
             block = { manageEquipmentUseCase.editUserEquipments(equipments.toDomainList()) },
             onSuccess = {
             },
             onError = {
                 updateState { it.copy(errorMessage = "Failed to update equipments") }
             }
         )
     }


     override fun onBackClicked() {
        sendEffect(EditProfileEffect.NavigateBack)
     }


     override fun onSaveChangesClicked() {
         sendEffect(EditProfileEffect.NavigateBack)

     }

     override fun onFullNameClicked() {
         updateState { it.copy(bottomSheetType = EditProfileBottomSheetType.FULL_NAME) }
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

     override fun onEquipmentChanged(equipments:List<EditProfileScreenState.EquipmentUiState>) {
         editUserEquipments(equipments)
     }

     override fun onWorkoutDaysChanged(workoutDays: List<EditProfileScreenState.WeekDayUi>) {
         editUserWorkoutDays(workoutDays)
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


 }