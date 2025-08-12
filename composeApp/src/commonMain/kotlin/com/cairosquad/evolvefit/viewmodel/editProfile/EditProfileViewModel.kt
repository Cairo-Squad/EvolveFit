package com.cairosquad.evolvefit.viewmodel.editProfile

import com.cairosquad.evolvefit.domain.usecase.equipment.ManageEquipmentUseCase
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class EditProfileViewModel(
    private val manageProfileUseCase : ManageProfileUseCase,
    private val manageEquipmentUseCase: ManageEquipmentUseCase
) :BaseViewModel<EditProfileScreenState, EditProfileEffect>(
    EditProfileScreenState()) , EditProfileInteractionListener
 {
     init {
         loadUserInfo()
     }

     private fun loadUserInfo()
     {
         tryToCall(
             block={manageProfileUseCase.getProfile()},
             onSuccess ={profile->
                 updateState { it.copy(profile=profile.toUiState()) }
             },
             onError = {
                 updateState { it.copy(errorMessage = "Failed to load profile") }
             }

         )
     }
//     private fun editUserWorkoutDays()
//     {
//         tryToCall()
//     }
     override fun onBackClicked() {
        sendEffect(EditProfileEffect.NavigateBack)
     }


     override fun onSaveChangesClicked() {
         sendEffect(EditProfileEffect.NavigateBack)

     }

     override fun onProfileImageClicked() {
         sendEffect(EditProfileEffect.OpenImagePicker)

     }

     override fun onBottomSheetDismissed() {
       sendEffect(EditProfileEffect.DismissBottomSheet)
     }

     override fun <T> onValueSelected(infoType: InfoType<T>) {
         val sheet = when (infoType) {
             InfoType.FULL_NAME-> EditProfileBottomSheet.FullName
             InfoType.DATE_OF_BIRTH -> EditProfileBottomSheet.Birthday
             InfoType.GENDER -> EditProfileBottomSheet.Gender
             InfoType.HEIGHT -> EditProfileBottomSheet.Height
             InfoType.WEIGHT -> EditProfileBottomSheet.Weight
             InfoType.MAIN_GOAL -> EditProfileBottomSheet.MainGoal
             InfoType.MEASUREMENT_STANDARD -> EditProfileBottomSheet.MeasurementStandard
         }
         sendEffect(EditProfileEffect.OpenBottomSheet(sheet))
     }

     override fun <T> onValueChange(infoType: InfoType<T>, value: T) {
         updateState {
             it.copy(profile = infoType.updater(it.profile, value))
         }
     }
 }