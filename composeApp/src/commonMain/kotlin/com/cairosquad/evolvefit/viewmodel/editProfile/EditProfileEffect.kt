package com.cairosquad.evolvefit.viewmodel.editProfile

sealed class EditProfileEffect {
    object NavigateBack:EditProfileEffect()
    object DismissBottomSheet : EditProfileEffect()
    object OpenImagePicker : EditProfileEffect()
    data class OpenBottomSheet(val sheet: EditProfileBottomSheet) : EditProfileEffect()

}