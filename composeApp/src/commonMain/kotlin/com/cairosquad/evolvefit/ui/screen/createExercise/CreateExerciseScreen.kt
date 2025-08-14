package com.cairosquad.evolvefit.ui.screen.createExercise

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.ImagePicker
import com.cairosquad.evolvefit.ui.component.UiImageDisplayer
import com.cairosquad.evolvefit.ui.screen.createExercise.content.CustomDropdownMenu
import com.cairosquad.evolvefit.ui.screen.createExercise.content.ExiteCreateExerciseBottomSheet
import com.cairosquad.evolvefit.ui.screen.createExercise.content.RowWithIcon
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseEffect
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseInteractionListener
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseViewModel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_duration
import evolvefit.composeapp.generated.resources.add_reps
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.choose_available_tools
import evolvefit.composeapp.generated.resources.create_exercise_description
import evolvefit.composeapp.generated.resources.create_exercise_title
import evolvefit.composeapp.generated.resources.enter_exercise_name
import evolvefit.composeapp.generated.resources.enter_instructions
import evolvefit.composeapp.generated.resources.exercise_image
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.im_upload_exercises_dark
import evolvefit.composeapp.generated.resources.im_upload_exercises_light
import evolvefit.composeapp.generated.resources.save_exercise
import evolvefit.composeapp.generated.resources.select_focus_area
import evolvefit.composeapp.generated.resources.upload_image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun CreateExerciseScreen(
    navigateBack: () -> Unit,
    viewModel: CreateExerciseViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()
    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            CreateExerciseEffect.CloseScreen -> {}
            CreateExerciseEffect.NavigateToAllExercises -> {}
            is CreateExerciseEffect.ShowError -> {}
        }
    }
    CreateExerciseScreenContent(
        state = state,
        listener = viewModel
    )
}

@Composable
fun CreateExerciseScreenContent(
    state: CreateExerciseState,
    listener: CreateExerciseInteractionListener,
) {
    val selectedImage = state.image
    val uploadExercisesImg = if (selectedImage != null) {
        null
    } else if (isSystemInDarkTheme()) {
        painterResource(Res.drawable.im_upload_exercises_dark)
    } else {
        painterResource(Res.drawable.im_upload_exercises_light)
    }

    val selectedEquipmentNames = if (state.selectedEquipment.name.isBlank()) {
        stringResource(Res.string.choose_available_tools)
    } else {
        state.selectedEquipmentNames
    }

    val selectedFocusArea = if (state.selectedFocusAreas.isEmpty()) {
        stringResource(Res.string.select_focus_area)
    } else {
        state.selectedFocusAreasText
    }
    val rowCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CustomAppBar(
            modifier = Modifier.padding(bottom = 16.dp),
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.back),
                    tint = Theme.color.surfaces.onSurface,
                    onClick = listener::onExitClicked
                )
            }
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                RegisterHeader(
                    modifier = Modifier.padding(bottom = 24.dp),
                    title = stringResource(Res.string.create_exercise_title),
                    description = stringResource(Res.string.create_exercise_description)
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(horizontal = 105.dp)
                        .clickable(onClick = listener::onImagePickerClicked),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.isImagePickerOpen) {
                        ImagePicker(
                            onImageRetrieved = listener::onImageRetrieved,
                            onImagePickerDismiss = listener::onImagePickerDismiss
                        )
                    }

                    if (selectedImage != null) {
                        UiImageDisplayer(
                            image = selectedImage,
                            contentDescription = stringResource(Res.string.exercise_image),
                            defaultImageSize = 64.dp
                        )
                    } else {
                        Image(
                            painter = uploadExercisesImg!!,
                            contentDescription = stringResource(Res.string.upload_image)
                        )
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .clickable(onClick = listener::onImagePickerClicked),
                    text = stringResource(Res.string.upload_image),
                    style = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceVariant
                )
            }

            item {
                InputField(
                    modifier = Modifier.padding(bottom = 12.dp),
                    value = state.name,
                    onValueChange = listener::onNameChanged,
                    placeholder = stringResource(Res.string.enter_exercise_name)
                )
            }
            item {
                RowWithIcon(
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .onGloballyPositioned {
                            rowCoordinates.value = it
                        },
                    text = selectedFocusArea,
                    isIconClicked = state.isFocusAreaExpanded,
                    onIconClicked = listener::onFocusAreaIconClicked
                )
                AnimatedVisibility(visible = state.isFocusAreaExpanded) {
                    Column {
                        CustomDropdownMenu(
                            items = state.focusAreaNames,
                            onItemSelected = listener::onFocusAreaNameSelected,
                            isChecked = state::isFocusAreaSelected
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CheckboxItem(
                        modifier = Modifier.weight(1f),
                        text = stringResource(Res.string.add_duration),
                        titleColor = Theme.color.surfaces.onSurfaceVariant,
                        isChecked = state.isDurationChecked,
                        onCheckedChange = {
                            if (it) listener.onMeasurementTypeSelected(
                                CreateExerciseState.MeasurementType.DURATION
                            )
                        },
                        style = CheckboxStyle.Tick
                    )
                    CheckboxItem(
                        modifier = Modifier.weight(1f),
                        text = stringResource(Res.string.add_reps),
                        titleColor = Theme.color.surfaces.onSurfaceVariant,
                        isChecked = state.isRepsChecked,
                        onCheckedChange = {
                            if (it) listener.onMeasurementTypeSelected(
                                CreateExerciseState.MeasurementType.REPS
                            )
                        },
                        style = CheckboxStyle.Tick
                    )
                }
            }
            item {
                AnimatedVisibility(
                    visible = state.isDurationChecked || state.isRepsChecked,
                ) {
                    InputField(
                        modifier = Modifier.padding(bottom = 12.dp),
                        value = state.measurementInputValue.toString(),
                        onValueChange = {
                            listener.onMeasurementValueChanged(it.toIntOrNull() ?: 0)
                        },
                        placeholder = when (state.measurementType) {
                            CreateExerciseState.MeasurementType.DURATION ->
                                stringResource(Res.string.add_duration)

                            CreateExerciseState.MeasurementType.REPS ->
                                stringResource(Res.string.add_reps)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            item {
                RowWithIcon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    text = selectedEquipmentNames,
                    isIconClicked = state.isEquipmentExpanded,
                    onIconClicked = listener::onAvailableEquipmentsIconClicked
                )
                AnimatedVisibility(visible = state.isEquipmentExpanded) {
                    Column {
                        CustomDropdownMenu(
                            items = state.equipmentNames,
                            onItemSelected = listener::onEquipmentNameSelected,
                            isChecked = state::isEquipmentSelected
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                }
            }
            item {
                InputField(
                    placeholder = stringResource(Res.string.enter_instructions),
                    minHeight = 124.dp,
                    value = state.description,
                    onValueChange = listener::onDescriptionChanged,
                    isSingleLine = false,
                    maxCharacters = 3000,
                    isCharacterCountVisible = true
                )
            }
            item {
                PrimaryButton(
                    modifier = Modifier.padding(vertical = 40.dp),
                    text = stringResource(Res.string.save_exercise),
                    onClick = listener::onSaveClicked
                )
            }
        }
        if (state.showExitBottomSheet) {
            ExiteCreateExerciseBottomSheet(
                isVisible = state.showExitBottomSheet,
                onDismiss = listener::onExitClicked,
                onCancelClicked = { listener.onExitOptionSelected(true) },
                onExitWithoutSavingClicked = { listener.onExitOptionSelected(false) }
            )
        }
    }
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
                measurementInputValue = 10,
                selectedFocusAreas = setOf(CreateExerciseState.FocusArea.Abs)
            ),
            listener = object : CreateExerciseInteractionListener {
                override fun onNameChanged(name: String) {}
                override fun onImagePicked(image: com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage) {}
                override fun onEquipmentToggled(equipmentId: Int) {}
                override fun onFocusAreaNameSelected(name: String) {}
                override fun onEquipmentNameSelected(toolName: String) {}
                override fun onImagePickerClicked() {}
                override fun onImagePickerDismiss() {}
                override fun onImageRetrieved(image: UiImage) {}
                override fun onMeasurementTypeSelected(type: CreateExerciseState.MeasurementType) {}
                override fun onMeasurementValueChanged(value: Int) {}
                override fun onFocusAreaToggled(focusArea: CreateExerciseState.FocusArea) {}
                override fun onDescriptionChanged(description: String) {}
                override fun onAvailableEquipmentsIconClicked() {}
                override fun onFocusAreaIconClicked() {}
                override fun onDismissEquipmentsDropdownMenuRequest() {}
                override fun onDismissFocusAreasDropdownMenuRequest() {}
                override fun onSaveClicked() {}
                override fun onExitClicked() {}
                override fun onExitOptionSelected(saveBeforeExit: Boolean) {}
            })
    }
}