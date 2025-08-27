package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.usecase.equipment.ManageEquipmentUseCase
import com.cairosquad.evolvefit.domain.usecase.exercise.ManageExerciseUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.FocusArea
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.MeasurementType
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import com.cairosquad.evolvefit.viewmodel.utils.asByteArray

class CreateExerciseViewModel(
    private val manageExerciseUseCase: ManageExerciseUseCase,
    private val manageEquipmentUseCase: ManageEquipmentUseCase,
) : BaseViewModel<CreateExerciseState, CreateExerciseEffect>(CreateExerciseState()),
    CreateExerciseInteractionListener {

    init {
        getEquipments()
    }

    override fun onNameChanged(name: String) = updateState { it.copy(name = name) }

    override fun onEquipmentToggled(equipmentId: Int) {
        updateState {
            val updatedEquipment = it.availableEquipments
                .firstOrNull { it.id == equipmentId }
                ?: CreateExerciseState.EquipmentUiState()
            it.copy(selectedEquipment = updatedEquipment)
        }
    }

    override fun onFocusAreaNameSelected(focusArea: FocusArea) {
        onFocusAreaToggled(focusArea)
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

    override fun onEquipmentNameSelected(toolName: String) {
        val id = screenState.value.availableEquipments
            .firstOrNull { it.name == toolName }?.id ?: return

        onEquipmentToggled(id)
        updateState { it.copy(isEquipmentExpanded = false) }
    }

    override fun onStartImageClicked() = updateState { it.copy(isFrontImagePickerOpen = true) }

    override fun onEndImageClicked() = updateState { it.copy(isBackImagePickerOpen = true) }

    override fun onStartImageRetrieved(image: UiImage) {
        updateState { it.copy(frontImage = image, isFrontImagePickerOpen = false) }
    }

    override fun onEndImageRetrieved(image: UiImage) {
        updateState { it.copy(backImage = image, isBackImagePickerOpen = false) }
    }

    override fun onStartImagePickerDismiss() {
        updateState { it.copy(isFrontImagePickerOpen = false) }
    }

    override fun onEndImagePickerDismiss() = updateState { it.copy(isBackImagePickerOpen = false) }

    override fun onMeasurementTypeSelected(type: MeasurementType) {
        updateState {
            it.copy(
                measurementType = type,
                isDurationChecked = type == MeasurementType.DURATION,
                isRepsChecked = type == MeasurementType.REPS
            )
        }
    }

    override fun onMeasurementValueChanged(value: String) {
        updateState { it.copy(measurementInputValue = value) }
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

    override fun onSaveClicked() {
        if (screenState.value.isLoading) return
        setLoadingState(true)
        tryToCall(
            onStart = { updateState { it.copy(isExerciseSaved = true) } },
            block = ::saveExercise,
            onSuccess = { it ->
                pushFrontImage(it)
                pushBackImage(it)
            },
            onError = { setLoadingState(false) },
            onEnd = { updateState { it.copy(isExerciseSaved = false) } }
        )

    }

    override fun onExitClicked() {
        updateState { it.copy(showExitBottomSheet = true) }
    }
    override fun onExitWithoutSavingClicked() {
        updateState { it.copy(showExitBottomSheet = false) }
        sendEffect(CreateExerciseEffect.CancelCreateExercise)
    }

    override fun onCancelClicked() {
        updateState { it.copy(showExitBottomSheet = false) }
    }

    override fun onFocusAreaDismiss() {
        updateState { it.copy(isFocusAreaExpanded = false) }
    }
    override fun onEquipmentDismiss() {
        updateState { it.copy(isEquipmentExpanded = false) }
    }
    override fun canSaveExercise(): Boolean {
        val currentState = screenState.value
        return currentState.name.isNotBlank() &&
                currentState.frontImage != null &&
                currentState.selectedFocusAreas.isNotEmpty() &&
                currentState.selectedEquipment.name.isNotBlank() &&
                (currentState.isDurationChecked || currentState.isRepsChecked) &&
                currentState.description.isNotBlank()
    }

    private fun pushFrontImage(id: String) {
        val image = screenState.value.frontImage
        tryToCall(
            block = { uploadExerciseImage(image, id) },
            onSuccess = { handleFrontImageUploadSuccessOrError() },
            onError = { handleFrontImageUploadSuccessOrError() }
        )
    }

    private fun handleFrontImageUploadSuccessOrError() {
        setLoadingState(false)
        sendEffect(CreateExerciseEffect.NavigateToAllExercises)
    }

    private fun pushBackImage(id: String) {
        val image = screenState.value.backImage
        tryToCall(
            block = { uploadExerciseImage(image, id) },
            onSuccess = {},
            onError = {}

        )
    }

    private suspend fun uploadExerciseImage(image: UiImage?, id: String): String {
        val imageFileData = image!!.asByteArray()
        return manageExerciseUseCase.uploadExerciseImage(
            fileBytes = imageFileData.bytes,
            fileName = imageFileData.fileName,
            exerciseId = id
        )
    }

    private fun setLoadingState(isLoading: Boolean) {
        updateState { it.copy(isLoading = isLoading) }
    }

    private suspend fun saveExercise(): String {
        val response = manageExerciseUseCase.createExercise(screenState.value.toDomainExercise())
        updateState { it.copy(exerciseId = response.id) }
        return response.id
    }

    private fun getEquipments() {
        tryToCall(
            block = { manageEquipmentUseCase.getAllEquipments() },
            onSuccess = ::handleEquipmentsSuccess,
            onError = ::handleEquipmentsError
        )
    }

    private fun handleEquipmentsSuccess(equipments: Set<Equipment>) {
        updateState {
            it.copy(availableEquipments = equipments.map { equipment -> equipment.toUiState() }
                .toSet())
        }
    }

    private fun handleEquipmentsError(throwable: Throwable) {
        updateState {
            it.copy(errorMessage = "")
        }

    }
}
