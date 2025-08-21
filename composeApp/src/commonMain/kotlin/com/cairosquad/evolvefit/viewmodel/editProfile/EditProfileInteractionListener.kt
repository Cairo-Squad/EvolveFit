package com.cairosquad.evolvefit.viewmodel.editProfile

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import kotlinx.datetime.LocalDate

interface EditProfileInteractionListener {
    fun onBackClicked()
    fun onSaveChangesClicked(profile: EditProfileScreenState.ProfileUiState)
    fun onDateOfBirthClicked()
    fun onGenderClicked()
    fun onHeightClicked()
    fun onWeightClicked()
    fun onMainGoalClicked()
    fun onEquipmentClicked()
    fun onWorkoutDaysClicked()
    fun onEquipmentChanged(equipments : Set<EditProfileScreenState.EquipmentUiState>)
    fun onWorkoutDaysChanged(workoutsDays : Set<EditProfileScreenState.WeekDayUiState>)
    fun onPreferredMeasurementStandardClicked()
    fun onImageUrlClicked()
    fun onFullNameChanged(fullName: String)
    fun onDateOfBirthChanged(dateOfBirth: LocalDate)
    fun onGenderChanged(gender: Profile.Gender)
    fun onHeightChanged(height: Float)
    fun onWeightChanged(weight: Float)
    fun onMainGoalChanged(mainGoal: Profile.FitnessGoal)
    fun onPreferredMeasurementStandardChanged(measurementStandard: MeasurementStandard)
    fun onImageUrlChanged(imageUrl: String)
    fun onImageRetrieved(image: UiImage)
    fun onBottomSheetDismissed()
    fun onImagePickerDismissed()

}