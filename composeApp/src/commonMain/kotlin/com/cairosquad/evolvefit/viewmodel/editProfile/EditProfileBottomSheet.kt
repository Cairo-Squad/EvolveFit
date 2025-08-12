package com.cairosquad.evolvefit.viewmodel.editProfile

sealed class EditProfileBottomSheet {
    object Birthday : EditProfileBottomSheet()
    object Gender : EditProfileBottomSheet()
    object Height : EditProfileBottomSheet()
    object Weight : EditProfileBottomSheet()
    object MainGoal : EditProfileBottomSheet()
    object MeasurementStandard : EditProfileBottomSheet()
    object FullName : EditProfileBottomSheet()
}
