package com.cairosquad.evolvefit.viewmodel.editProfile

interface EditProfileInteractionListener {
    fun onBackClicked()
    fun onEditInfoClicked(infoType : InfoType)
    fun onSaveChangesClicked()
    fun onProfileImageClicked()
    fun onBottomSheetDismissed()
    fun onInfoValueSelected(infoType: InfoType, value: String)
}