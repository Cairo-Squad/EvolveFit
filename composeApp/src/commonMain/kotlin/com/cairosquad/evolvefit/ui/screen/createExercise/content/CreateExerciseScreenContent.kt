package com.cairosquad.evolvefit.ui.screen.createExercise.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.component.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.ImagePicker
import com.cairosquad.evolvefit.ui.screen.createExercise.content.component.EquipmentDropdownRow
import com.cairosquad.evolvefit.ui.screen.createExercise.content.component.ExitCreationBottomSheet
import com.cairosquad.evolvefit.ui.screen.createExercise.content.component.FocusAreaDropdownRow
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.ui.util.PlatformBackHandler
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseInteractionListener
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_duration
import evolvefit.composeapp.generated.resources.add_duration_placeholder
import evolvefit.composeapp.generated.resources.add_reps
import evolvefit.composeapp.generated.resources.cancel
import evolvefit.composeapp.generated.resources.choose_available_tools
import evolvefit.composeapp.generated.resources.create_exercise_description
import evolvefit.composeapp.generated.resources.create_exercise_title
import evolvefit.composeapp.generated.resources.enter_exercise_name
import evolvefit.composeapp.generated.resources.enter_instructions
import evolvefit.composeapp.generated.resources.exit_create_exercise_confirmation_message
import evolvefit.composeapp.generated.resources.exit_create_workout_confirmation_message
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

@Composable
fun CreateExerciseScreenContent(
    state: CreateExerciseState,
    listener: CreateExerciseInteractionListener
) {
    val selectedImage1 = state.frontImage
    val selectedImage2 = state.backImage
    val uploadExercisesImg1 = if (selectedImage1 != null) {
        null
    } else if (Theme.isDark) {
        painterResource(Res.drawable.im_upload1)
    } else {
        painterResource(Res.drawable.im_upload_light1)
    }

    val uploadExercisesImg2 = if (selectedImage2 != null) {
        null
    } else if (Theme.isDark) {
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


    val durationTitleColor by animateColorAsState(
        targetValue = if (state.isDurationChecked)
            Theme.color.surfaces.onSurfaceContainer
        else
            Theme.color.surfaces.onSurfaceVariant,
        label = "durationTitleColor"
    )

    val repsTitleColor by animateColorAsState(
        targetValue = if (state.isRepsChecked)
            Theme.color.surfaces.onSurfaceContainer
        else
            Theme.color.surfaces.onSurfaceVariant,
        label = "repsTitleColor"
    )

    val selectedFocusAreaTextColor by animateColorAsState(
        targetValue = if (state.selectedFocusAreas.isNotEmpty())
            Theme.color.surfaces.onSurfaceContainer
        else
            Theme.color.surfaces.onSurfaceVariant,
        label = "selectedFocusAreaTextColor"
    )

    val selectedEquipmentTextColor by animateColorAsState(
        targetValue = if (state.selectedEquipment.name.isNotBlank())
            Theme.color.surfaces.onSurfaceContainer
        else
            Theme.color.surfaces.onSurfaceVariant,
        label = "selectedEquipmentTextColor"
    )

    PlatformBackHandler(onBack = listener::onExitClicked)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            stickyHeader {
                CustomAppBar(
                    modifier = Modifier
                        .background(color = Theme.color.surfaces.surface)
                        .padding(bottom = 16.dp),
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
            }
            item {
                RegisterHeader(
                    title = stringResource(Res.string.create_exercise_title),
                    description = stringResource(Res.string.create_exercise_description)
                )
            }
            item {
                ExerciseImages(
                    startImage = state.frontImage,
                    endImage = state.backImage,
                    uploadExercisesImg1 = uploadExercisesImg1,
                    uploadExercisesImg2 = uploadExercisesImg2,
                    onStartImageClicked = listener::onStartImageClicked,
                    onEndImageClicked = listener::onEndImageClicked
                )
            }
            item {
                Text(
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .clickable(onClick = listener::onStartImageClicked),
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
                    placeholder = stringResource(Res.string.enter_exercise_name),
                    verticalPadding = 15.5.dp
                )
            }
            item {
                FocusAreaDropdownRow(
                    state = state,
                    selectedFocusArea = selectedFocusArea,
                    selectedFocusAreaTextColor = selectedFocusAreaTextColor,
                    listener = listener
                )
            }
            item {
                MeasurementTypeSelector(
                    isDurationChecked = state.isDurationChecked,
                    isRepsChecked = state.isRepsChecked,
                    durationTitleColor = durationTitleColor,
                    repsTitleColor = repsTitleColor,
                    onMeasurementTypeSelected = listener::onMeasurementTypeSelected
                )
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
                                stringResource(Res.string.add_duration_placeholder)

                            CreateExerciseState.MeasurementType.REPS ->
                                stringResource(Res.string.add_reps)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        horizontalPadding = 12.dp,
                        verticalPadding = 15.5.dp
                    )
                }
            }
            item {
                EquipmentDropdownRow(
                    state = state,
                    selectedEquipmentNames = selectedEquipmentNames,
                    selectedEquipmentTextColor = selectedEquipmentTextColor,
                    listener = listener
                )
            }
            item {
                InputField(
                    placeholder = stringResource(Res.string.enter_instructions),
                    minHeight = 124.dp,
                    value = state.description,
                    onValueChange = listener::onDescriptionChanged,
                    isSingleLine = false,
                    maxCharacters = 3000,
                    isCharacterCountVisible = true,
                    verticalPadding = 12.dp,
                    horizontalPadding = 12.dp
                )
            }
        }
        PrimaryButton(
            modifier = Modifier
                .padding(bottom = 24.dp, top = 16.dp)
                .navigationBarsPadding(),
            isEnabled = listener.canSaveExercise(),
            isLoading = state.isLoading,
            text = stringResource(Res.string.save_exercise),
            onClick = { listener.onSaveClicked() }
        )
    }

    if (state.isFrontImagePickerOpen) {
        ImagePicker(
            onImageRetrieved = listener::onStartImageRetrieved,
            onImagePickerDismiss = listener::onStartImagePickerDismiss
        )
    }

    if (state.isBackImagePickerOpen) {
        ImagePicker(
            onImageRetrieved = listener::onEndImageRetrieved,
            onImagePickerDismiss = listener::onEndImagePickerDismiss
        )
    }

    ExitCreationBottomSheet(
        isVisible = state.showExitBottomSheet,
        onDismiss = listener::onCancelClicked,
        onCancelClicked = listener::onCancelClicked,
        onExitWithoutSavingClicked = listener::onExitWithoutSavingClicked,
        message = stringResource(Res.string.exit_create_exercise_confirmation_message)
    )

}

