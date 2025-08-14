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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
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
import com.cairosquad.evolvefit.ui.screen.createExercise.content.ExitCreateExerciseBottomSheet
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
import evolvefit.composeapp.generated.resources.cancel
import evolvefit.composeapp.generated.resources.choose_available_tools
import evolvefit.composeapp.generated.resources.create_exercise_description
import evolvefit.composeapp.generated.resources.create_exercise_title
import evolvefit.composeapp.generated.resources.enter_exercise_name
import evolvefit.composeapp.generated.resources.enter_instructions
import evolvefit.composeapp.generated.resources.exercise_image
import evolvefit.composeapp.generated.resources.ic_cancle
import evolvefit.composeapp.generated.resources.im_upload1
import evolvefit.composeapp.generated.resources.im_upload2
import evolvefit.composeapp.generated.resources.im_upload_light1
import evolvefit.composeapp.generated.resources.im_upload_light2
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
    val selectedImage1 = state.image1
    val selectedImage2 = state.image2
    val uploadExercisesImg1 = if (selectedImage1 != null) {
        null
    } else if (isSystemInDarkTheme()) {
        painterResource(Res.drawable.im_upload1)
    } else {
        painterResource(Res.drawable.im_upload_light1)
    }

    val uploadExercisesImg2 = if (selectedImage2 != null) {
        null
    } else if (isSystemInDarkTheme()) {
        painterResource(Res.drawable.im_upload2)
    } else {
        painterResource(Res.drawable.im_upload_light2)
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

    val durationTitleColor = if (state.isDurationChecked)
        Theme.color.surfaces.onSurfaceContainer
    else
        Theme.color.surfaces.onSurfaceVariant

    val repsTitleColor = if (state.isRepsChecked)
        Theme.color.surfaces.onSurfaceContainer
    else
        Theme.color.surfaces.onSurfaceVariant

    val selectedFocusAreaTextColor = if (state.selectedFocusAreas.isNotEmpty())
        Theme.color.surfaces.onSurfaceContainer
    else
        Theme.color.surfaces.onSurfaceVariant

    val selectedEquipmentTextColor = if (state.selectedEquipment.name.isNotBlank())
        Theme.color.surfaces.onSurfaceContainer
    else
        Theme.color.surfaces.onSurfaceVariant


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .windowInsetsPadding(WindowInsets.systemBars),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomAppBar(
                modifier = Modifier.padding(bottom = 16.dp),
                title = "",
                header = {
                    ActionIconButton(
                        icon = painterResource(Res.drawable.ic_cancle),
                        contentDescription = stringResource(Res.string.cancel),
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
                        title = stringResource(Res.string.create_exercise_title),
                        description = stringResource(Res.string.create_exercise_description)
                    )
                }

                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, top = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .rotate(-5f)
                                .offset(x = (-15).dp, y = (-18).dp)
                                .clickable { listener.onImage2Clicked() },
                            contentAlignment = Alignment.Center
                        ) {
                            if (state.image2 != null) {
                                UiImageDisplayer(
                                    modifier = Modifier.fillMaxSize(),
                                    image = state.image2,
                                    contentDescription = stringResource(Res.string.exercise_image)
                                )
                            } else {
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    painter = uploadExercisesImg2!!,
                                    contentDescription = stringResource(Res.string.upload_image)
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clickable { listener.onImage1Clicked() },
                            contentAlignment = Alignment.Center
                        ) {
                            if (state.image1 != null) {
                                UiImageDisplayer(
                                    modifier = Modifier.fillMaxSize(),
                                    image = state.image1,
                                    contentDescription = stringResource(Res.string.exercise_image)
                                )
                            } else {
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    painter = uploadExercisesImg1!!,
                                    contentDescription = stringResource(Res.string.upload_image)
                                )
                            }
                        }

                    }
                }

                item {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                            .clickable(onClick = listener::onImage1Clicked),
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
                        modifier = Modifier.padding(bottom = 12.dp),
                        text = selectedFocusArea,
                        textColor = selectedFocusAreaTextColor,
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
                            titleColor = durationTitleColor,
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
                            titleColor = repsTitleColor,
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
                            value = state.measurementInputValue,
                            onValueChange = listener::onMeasurementValueChanged,
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
                        textColor = selectedEquipmentTextColor,
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
                        isEnabled = listener.isSaveEnabled(),
                        text = stringResource(Res.string.save_exercise),
                        onClick = { listener.onSaveClicked() }
                    )
                }
            }
        }

        if (state.isImage1PickerOpen) {
            ImagePicker(
                onImageRetrieved = listener::onImage1Retrieved,
                onImagePickerDismiss = listener::onImage1PickerDismiss
            )
        }

        if (state.isImage2PickerOpen) {
            ImagePicker(
                onImageRetrieved = listener::onImage2Retrieved,
                onImagePickerDismiss = listener::onImage2PickerDismiss
            )
        }


        if (state.showExitBottomSheet) {
            ExitCreateExerciseBottomSheet(
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
                measurementInputValue = "10",
                selectedFocusAreas = setOf(CreateExerciseState.FocusArea.Abs)
            ),
            listener = object : CreateExerciseInteractionListener {
                override fun onNameChanged(name: String) {}
                override fun onEquipmentToggled(equipmentId: Int) {}
                override fun onFocusAreaNameSelected(name: String) {}
                override fun onEquipmentNameSelected(toolName: String) {}
                override fun onImage1Clicked() {}
                override fun onImage2Clicked() {}
                override fun onImage1Retrieved(image: UiImage) {}
                override fun onImage2Retrieved(image: UiImage) {}
                override fun onImage1PickerDismiss() {}
                override fun onImage2PickerDismiss() {}
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
                override fun isSaveEnabled(): Boolean{return true}
            })
    }
}