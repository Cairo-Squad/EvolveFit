package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.domain.usecase.equipment.ManageEquipmentUseCase
import com.cairosquad.evolvefit.domain.usecase.exercise.ManageExerciseUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.FocusArea
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.MeasurementType
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

class CreateExerciseViewModel(
    private val manageExerciseUseCase: ManageExerciseUseCase,
    private val manageEquipmentUseCase: ManageEquipmentUseCase,
) : BaseViewModel<CreateExerciseState, CreateExerciseEffect>(CreateExerciseState()),
    CreateExerciseInteractionListener {

    init {
        getEquipments()
    }

    private fun getEquipments() {
        tryToCall(
            block = { manageEquipmentUseCase.getAllEquipments() },
            onSuccess = { equipments ->
                updateState { it.copy(availableEquipments = equipments.map { it.toUiState() }.toSet()) }
            },
            onError = {updateState { it.copy(errorMessage = "Failed to load equipments") } }
        )
    }

    override fun onEquipmentToggled(equipmentId: Int) {
        updateState {
            val updatedEquipment = it.availableEquipments
                .firstOrNull { it.id == equipmentId }
                ?: CreateExerciseState.EquipmentUiState()
            it.copy(selectedEquipment = updatedEquipment)
        }
    }

    override fun onFocusAreaNameSelected(name: String) {
        val focusArea = FocusArea.valueOf(name)
        onFocusAreaToggled(focusArea)
    }

    override fun onEquipmentNameSelected(toolName: String) {
        val id = screenState.value.availableEquipments
            .firstOrNull { it.name == toolName }?.id ?: return

        onEquipmentToggled(id)
        updateState { it.copy(isEquipmentExpanded = false) }
    }


    override fun onStartImageClicked() {
        updateState { it.copy(isImage1PickerOpen = true) }
    }

    override fun onStartImageRetrieved(image: UiImage) {
        updateState { it.copy(image1 = image, isImage1PickerOpen = false) }
    }

    override fun onStartImagePickerDismiss() {
        updateState { it.copy(isImage1PickerOpen = false) }
    }


    override fun onEndImageClicked() {
        updateState { it.copy(isImage2PickerOpen = true) }
    }


    override fun onEndImageRetrieved(image: UiImage) {
        updateState { it.copy(image2 = image, isImage2PickerOpen = false) }
    }


    override fun onEndImagePickerDismiss() {
        updateState { it.copy(isImage2PickerOpen = false) }
    }


    override fun onFocusAreaToggled(focusArea: FocusArea) {
        updateState {
            val updatedSelection = it.selectedFocusAreas.toMutableSet()
            if (focusArea in updatedSelection) {
                updatedSelection.remove(focusArea)
            } else {
                updatedSelection.add(focusArea)
            }
            it.copy(selectedFocusAreas = updatedSelection)
        }
    }

    override fun onDescriptionChanged(description: String) {
        updateState { it.copy(description = description) }
    }

    override fun onAvailableEquipmentsIconClicked() {
        updateState { it.copy(isEquipmentExpanded = !it.isEquipmentExpanded) }
    }

    override fun onFocusAreaIconClicked() {
        updateState { it.copy(isFocusAreaExpanded = !it.isFocusAreaExpanded) }
    }

    override fun onDismissEquipmentsDropdownMenuRequest() {
        updateState { it.copy(isEquipmentExpanded = false) }
    }

    override fun onDismissFocusAreasDropdownMenuRequest() {
        updateState { it.copy(isFocusAreaExpanded = false) }
    }

    override fun onNameChanged(name: String) {
        updateState { it.copy(name = name) }
    }

    override fun onMeasurementTypeSelected(type: MeasurementType) {
        updateState {
            it.copy(
                measurementType = type,
                isDurationChecked = type == MeasurementType.DURATION,
                isRepsChecked = type == MeasurementType.REPS
            )
        }
    }

    override fun onMeasurementValueChanged(value: String) { updateState { it.copy(measurementInputValue = value) } }

    override fun onSaveClicked() {
        tryToCall(
            block = ::saveExercise,
            onSuccess = { sendEffect(CreateExerciseEffect.NavigateToAllExercises) },
            onError = { e ->
                sendEffect(CreateExerciseEffect.ShowError(e.message ?: "Unknown error"))
            },
            onStart = { updateState { it.copy(isExerciseSaved = true) } },
            onEnd = { updateState { it.copy(isExerciseSaved = false) } }
        )
    }

    private suspend fun saveExercise() {
        manageExerciseUseCase.createExercise(screenState.value.toDomainExercise())
    }

    override fun onExitClicked() { updateState { it.copy(showExitBottomSheet = true) } }

    override fun onExitOptionSelected(saveBeforeExit: Boolean) {
        updateState { it.copy(showExitBottomSheet = false) }

        if (saveBeforeExit) {
            onSaveClicked()
        } else {
            sendEffect(CreateExerciseEffect.CloseScreen)
        }
    }

    override fun onFocusAreaDismiss() { updateState { it.copy(isFocusAreaExpanded = false) } }

    override fun onEquipmentDismiss() { updateState { it.copy(isEquipmentExpanded = false) } }

    override fun canSaveExercise(): Boolean {
        val currentState = screenState.value
        return currentState.name.isNotBlank() &&
                currentState.image1 != null &&
                currentState.selectedFocusAreas.isNotEmpty() &&
                currentState.selectedEquipment.name.isNotBlank() &&
                (currentState.isDurationChecked || currentState.isRepsChecked) &&
                currentState.description.isNotBlank()
    }
}