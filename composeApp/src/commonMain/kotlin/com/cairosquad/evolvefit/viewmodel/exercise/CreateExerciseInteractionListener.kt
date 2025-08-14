package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.FocusArea
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.MeasurementType
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

interface CreateExerciseInteractionListener {
    fun onNameChanged(name: String)
    fun onImagePicked(image: UiImage)
    fun onEquipmentToggled(equipmentId: Int)
    fun onFocusAreaNameSelected(name: String)
    fun onEquipmentNameSelected(toolName: String)
    fun onImagePickerClicked()
    fun onImagePickerDismiss()
    fun onImageRetrieved(image: UiImage)
    fun onMeasurementTypeSelected(type: MeasurementType)
    fun onMeasurementValueChanged(value: String)
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