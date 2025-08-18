package com.cairosquad.evolvefit.viewmodel.editProfile

sealed class EditProfileEffect {
    object NavigateBack:EditProfileEffect()
    object OpenImagePicker : EditProfileEffect()

}