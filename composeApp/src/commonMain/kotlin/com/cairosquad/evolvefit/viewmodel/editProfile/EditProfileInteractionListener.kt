package com.cairosquad.evolvefit.viewmodel.editProfile

import kotlinx.datetime.LocalDate

interface EditProfileInteractionListener {
    fun onBackClicked()
    fun onSaveChangesClicked()
    fun onFullNameClicked()
    fun onDateOfBirthClicked()
    fun onGenderClicked()
    fun onHeightClicked()
    fun onWeightClicked()
    fun onMainGoalClicked()
    fun onEquipmentClicked()
    fun onWorkoutDaysClicked()
    fun onEquipmentChanged(equipments : List<EditProfileScreenState.EquipmentUiState>)
    fun onWorkoutDaysChanged(workoutsDays : List<EditProfileScreenState.WeekDayUi>)
    fun onPreferredMeasurementStandardClicked()
    fun onImageUrlClicked()
    fun onFullNameChanged(fullName: String)
    fun onDateOfBirthChanged(dateOfBirth: LocalDate)
    fun onGenderChanged(gender: String)
    fun onHeightChanged(height: Float)
    fun onWeightChanged(weight: Float)
    fun onMainGoalChanged(mainGoal: String)
    fun onPreferredMeasurementStandardChanged(measurementStandard: String)
    fun onImageUrlChanged(imageUrl: String)

}