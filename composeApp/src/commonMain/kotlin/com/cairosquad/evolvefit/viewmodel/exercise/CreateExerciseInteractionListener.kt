package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.FocusArea
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.MeasurementType
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

interface CreateExerciseInteractionListener {
    fun onNameChanged(name: String)
    fun onEquipmentToggled(equipmentId: Int)
    fun onFocusAreaNameSelected(name: String)
    fun onEquipmentNameSelected(toolName: String)
    fun onStartImageClicked()
    fun onEndImageClicked()
    fun onStartImageRetrieved(image: UiImage)
    fun onEndImageRetrieved(image: UiImage)
    fun onStartImagePickerDismiss()
    fun onEndImagePickerDismiss()
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
    fun onExitWithoutSavingClicked()
    fun onCancelClicked()
    fun onFocusAreaDismiss()
    fun onEquipmentDismiss()
    fun canSaveExercise(): Boolean

}