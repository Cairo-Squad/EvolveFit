package com.cairosquad.evolvefit.viewmodel.editProfile

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class EditProfileViewModel() :BaseViewModel<EditProfileScreenState, EditProfileEffect>(
    EditProfileScreenState()) , EditProfileInteractionListener
 {
     override fun onBackClicked() {
         TODO("Not yet implemented")
     }

     override fun onEditInfoClicked(infoType: InfoType) {
         TODO("Not yet implemented")
     }

     override fun onSaveChangesClicked() {
         TODO("Not yet implemented")
     }

     override fun onProfileImageClicked() {
         TODO("Not yet implemented")
     }

     override fun onBottomSheetDismissed() {
         TODO("Not yet implemented")
     }

     override fun onInfoValueSelected(
         infoType: InfoType,
         value: String
     ) {
         TODO("Not yet implemented")
     }
 }