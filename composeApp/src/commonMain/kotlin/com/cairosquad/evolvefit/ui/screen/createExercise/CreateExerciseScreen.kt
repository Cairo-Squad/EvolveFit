package com.cairosquad.evolvefit.ui.screen.createExercise

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.ui.screen.createExercise.content.CreateExerciseScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseEffect
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseInteractionListener
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseViewModel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun CreateExerciseScreen(
    navigateBack: () -> Unit,
    onExerciseCreationSuccess: (() -> Unit)?,
    viewModel: CreateExerciseViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()
    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            CreateExerciseEffect.CloseScreen -> { navigateBack() }
            CreateExerciseEffect.NavigateToAllExercises -> {}
            is CreateExerciseEffect.ShowError -> {}
        }
    }
    CreateExerciseScreenContent(
        state = state,
        listener = viewModel
    )
}

@Preview()
@Composable
private fun CreateExerciseScreenPreview() {
    AppTheme(isDarkTheme = true) {
        CreateExerciseScreenContent(
            state = CreateExerciseState(
                name = "Push Ups",
                description = "A basic upper body exercise.",
                measurementType = CreateExerciseState.MeasurementType.REPS,
                isRepsChecked = true,
                measurementInputValue = "10",
                selectedFocusAreas = setOf(CreateExerciseState.FocusArea.ARMS)
            ),
            listener = object : CreateExerciseInteractionListener {
                override fun onNameChanged(name: String) {}
                override fun onEquipmentToggled(equipmentId: Int) {}
                override fun onFocusAreaNameSelected(name: String) {}
                override fun onEquipmentNameSelected(toolName: String) {}
                override fun onStartImageClicked() {}
                override fun onEndImageClicked() {}
                override fun onStartImageRetrieved(image: UiImage) {}
                override fun onEndImageRetrieved(image: UiImage) {}
                override fun onStartImagePickerDismiss() {}
                override fun onEndImagePickerDismiss() {}
                override fun onMeasurementTypeSelected(type: CreateExerciseState.MeasurementType) {}
                override fun onMeasurementValueChanged(value: String) {}
                override fun onFocusAreaToggled(focusArea: CreateExerciseState.FocusArea) {}
                override fun onDescriptionChanged(description: String) {}
                override fun onAvailableEquipmentsIconClicked() {}
                override fun onFocusAreaIconClicked() {}
                override fun onDismissEquipmentsDropdownMenuRequest() {}
                override fun onDismissFocusAreasDropdownMenuRequest() {}
                override fun onSaveClicked() {}
                override fun onExitClicked() {}
                override fun onExitOptionSelected(saveBeforeExit: Boolean) {}
                override fun onFocusAreaDismiss() {}
                override fun onEquipmentDismiss() {}
                override fun canSaveExercise(): Boolean {
                    return true
                }
            })
    }
}