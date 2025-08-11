package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.FocusArea
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.MeasurementType
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

interface CreateExerciseInteractionListener {
    fun onNameChanged(name: String)
    fun onImagePicked(image: UiImage)
    fun onEquipmentToggled(equipmentId: Long)
    fun onImagePickerClicked()
    fun onImagePickerDismiss()
    fun onImageRetrieved(image: UiImage)
    fun onMeasurementTypeSelected(type: MeasurementType)
    fun onMeasurementValueChanged(value: Int)
    fun onFocusAreaToggled(focusArea: FocusArea)
    fun onDescriptionChanged(description: String)
    fun onAvailableEquipmentsIconClicked()
    fun onFocusAreaIconClicked()
    fun onDismissEquipmentsDropdownMenuRequest()
    fun onDismissFocusAreasDropdownMenuRequest()
    fun onSaveClicked()
    fun onExitClicked()
    fun onExitOptionSelected(saveBeforeExit: Boolean)
}