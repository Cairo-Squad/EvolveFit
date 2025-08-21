package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState.Gender
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState.Goal
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState.MeasurementStandard

interface RegisterInteractionListener {
    fun onNextClicked()
    fun onBackClicked()
    fun onStartNowClicked()
    fun onGenderClicked(gender: Gender)
    fun onMeasurementUnitClicked(unit: MeasurementStandard)
    fun onGoalClicked(goal: Goal)
    fun onHeightChanged(height: Float)
    fun onWeightChanged(weight: Float)
    fun onNotificationToggled(type: RegisterScreenState.NotificationType)
    fun onWorkoutDaySelected(day: RegisterScreenState.WeekDayUiState)
    fun onNoEquipmentSelected()
    fun onEquipmentToggled(equipmentId: Int)
    fun onImagePickerClick()
    fun onImagePickerDismiss()
    fun onImageRetrieved(image: UiImage)
    fun onUserNameChange(userName: String)
    fun onUserEmailChange(userEmail: String)
    fun onUserPasswordChange(userPassword: String)
    fun onPasswordVisibilityClick()
    fun onDateOfBirthChange(dateOfBirth: String)
}