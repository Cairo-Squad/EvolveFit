package com.cairosquad.evolvefit.viewmodel.exercise

import androidx.compose.ui.graphics.ImageBitmap
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.FocusArea
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.MeasurementType
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

interface CreateExerciseInteractionListener {
    fun onNameChanged(name: String)
    fun onEquipmentToggled(equipmentId: Int)
    fun onFocusAreaNameSelected(name: String)
    fun onEquipmentNameSelected(toolName: String)
    fun onImage1Clicked()
    fun onImage2Clicked()
    fun onImage1Retrieved(image: UiImage)
    fun onImage2Retrieved(image: UiImage)
    fun onImage1PickerDismiss()
    fun onImage2PickerDismiss()
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
    fun onFocusAreaDismiss()
    fun onEquipmentDismiss()
    fun isSaveEnabled(): Boolean

}