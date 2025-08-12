package com.cairosquad.evolvefit.viewmodel.editProfile

interface EditProfileInteractionListener {
    fun onBackClicked()
    fun onSaveChangesClicked()
    fun onProfileImageClicked()
    fun onBottomSheetDismissed()
    fun <T> onValueSelected(infoType: InfoType<T>)
    fun <T> onValueChange(infoType: InfoType<T>, value: T)

}