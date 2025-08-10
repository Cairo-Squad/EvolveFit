package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.domain.usecase.workOut.ExerciseUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.Equipment
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.FocusArea
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.MeasurementType
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

class CreateExerciseViewModel(
    private val exerciseUseCase: ExerciseUseCase
) : BaseViewModel<CreateExerciseState, CreateExerciseEffect>(CreateExerciseState()),
    CreateExerciseInteractionListener {

    private fun getEquipments() {
        tryToCall(
            block = { exerciseUseCase.getEquipments() },
            onSuccess = { tools ->
                val equipments = tools.map { tool ->
                    Equipment(
                        toolId = tool.id,
                        toolName = tool.name
                    )
                }
                updateState { it.copy(availableEquipments = equipments) }
            },
            onError = {
                updateState { it.copy(errorMessage = "Failed to load equipments") }
            }
        )
    }

    override fun onEquipmentToggled(equipmentId: Long) {
        updateState {
            val updatedSelection = it.selectedEquipments.toMutableList()
            if (equipmentId in updatedSelection) {
                updatedSelection.remove(equipmentId)
            } else {
                updatedSelection.add(equipmentId)
            }
            it.copy(selectedEquipments = updatedSelection)
        }
    }

    override fun onImagePickerClick() {
        updateState { it.copy(isImagePickerOpen = true) }
    }

    override fun onImagePickerDismiss() {
        updateState { it.copy(isImagePickerOpen = false) }
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

    override fun onNameChanged(name: String) {
        updateState { it.copy(name = name) }
    }

    override fun onImagePicked(image: UiImage) {
        updateState { it.copy(image = image, isImagePickerOpen = false) }
    }

    override fun onMeasurementTypeSelected(type: MeasurementType) {
        updateState { it.copy(measurementType = type) }
    }

    override fun onMeasurementValueChanged(value: Int) {
        updateState { it.copy(measurementValue = value) }
    }

    override fun onSaveClicked() {
        val state = screenState.value
        tryToCall(
            block = {
                exerciseUseCase.createExercise(
                    state.toDomainExercise()
                )
            },
            onSuccess = {
                sendEffect(CreateExerciseEffect.NavigateToAllExercises)
            },
            onError = { e ->
                sendEffect(CreateExerciseEffect.ShowError(e.message ?: "Unknown error"))
            },
            onStart = {
                updateState { it.copy(isSaving = true) }
            },
            onEnd = {
                updateState { it.copy(isSaving = false) }
            }
        )
    }

    override fun onExitClicked() {
        updateState { it.copy(showExitBottomSheet = true) }
    }

    override fun onExitOptionSelected(saveBeforeExit: Boolean) {
        updateState { it.copy(showExitBottomSheet = false) }

        if (saveBeforeExit) {
            onSaveClicked()
        } else {
            sendEffect(CreateExerciseEffect.CloseScreen)
        }
    }
}
