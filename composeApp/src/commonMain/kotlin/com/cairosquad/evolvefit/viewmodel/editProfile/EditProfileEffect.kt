package com.cairosquad.evolvefit.viewmodel.editProfile

sealed class EditProfileEffect {
    data class ShowBottomSheet(val infoType: InfoType) : EditProfileEffect()
    object NavigateBack:EditProfileEffect()
    object DismissBottomSheet : EditProfileEffect()

}