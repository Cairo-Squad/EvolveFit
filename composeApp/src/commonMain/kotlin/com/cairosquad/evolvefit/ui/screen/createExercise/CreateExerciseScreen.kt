package com.cairosquad.evolvefit.ui.screen.createExercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.cairosquad.evolvefit.ui.screen.createExercise.content.ExiteCreateExerciseBottomSheet
import com.cairosquad.evolvefit.ui.screen.createExercise.content.RowWithIcon
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseInteractionListener
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.im_upload_exercises_dark
import evolvefit.composeapp.generated.resources.im_upload_exercises_light
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun CreateExerciseScreen(
    navigateBack: () -> Unit,
) {

}

@Composable
fun CreateExerciseScreenContent(
    state: CreateExerciseState,
    listener: CreateExerciseInteractionListener,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val selectedImage = state.image

        val uploadExercisesImg = if (selectedImage != null) {
            null
        } else if (isSystemInDarkTheme()) {
            painterResource(Res.drawable.im_upload_exercises_light)
        } else {
            painterResource(Res.drawable.im_upload_exercises_dark)
        }

        CustomAppBar(
            modifier = Modifier.padding(bottom = 16.dp),
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Theme.color.surfaces.onSurface,
                    onClick = listener::onExitClicked
                )
            }
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                RegisterHeader(
                    modifier = Modifier.padding(bottom = 24.dp),
                    title = "Create Exercise",
                    description = "Add a new exercise to personalize your workout and stay on track."
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
                            contentDescription = "Exercise image",
                            defaultImageSize = 64.dp
                        )
                    } else {
                        Image(
                            painter = uploadExercisesImg!!,
                            contentDescription = "Upload image"
                        )
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .clickable(onClick = listener::onImagePickerClicked),
                    text = "Upload image",
                    style = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceVariant
                )
            }

            item {
                InputField(
                    modifier = Modifier.padding(bottom = 12.dp),
                    value = state.name,
                    onValueChange = listener::onNameChanged,
                    placeholder = "Enter exercise name"
                )
            }
            item {
                RowWithIcon(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = "Select focus area",
                    isIconClicked = false,
                    onIconClicked = { listener::onFocusAreaToggled }
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CheckboxItem(
                        modifier = Modifier.weight(1f),
                        text = "Add Duration",
                        titleColor = Theme.color.surfaces.onSurfaceVariant,
                        isChecked = state.isDurationChecked,
                        onCheckedChange = {
                            if (it) listener.onMeasurementTypeSelected(CreateExerciseState.MeasurementType.DURATION)
                        },
                        style = CheckboxStyle.Tick
                    )
                    CheckboxItem(
                        modifier = Modifier.weight(1f),
                        text = "Add Reps",
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
                RowWithIcon(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = "Choose available tools",
                    isIconClicked = false,
                    onIconClicked = { listener::onEquipmentToggled }
                )
            }
            item {
                InputField(
                    placeholder = "Enter instructions",
                    modifier = Modifier
                        .height(124.dp),
                    value = state.description,
                    onValueChange = listener::onDescriptionChanged,
                    isSingleLine = false,
                    maxCharacters = 3000,
                    isCharacterCountVisible = true
                )
            }
            item {
                PrimaryButton(
                    modifier = Modifier.padding(top = 40.dp),
                    text = "Save Exercise",
                    onClick = listener::onSaveClicked
                )
            }
        }
    }
}

@Preview()
@Composable
private fun CreateExerciseScreenPreview() {
    AppTheme(isDarkTheme = true) {
        CreateExerciseScreenContent(
            state = CreateExerciseState(
                name = "",
                description = "",
                measurementType = CreateExerciseState.MeasurementType.REPS,
                selectedFocusAreas = listOf(CreateExerciseState.FocusArea.Abs)
            ),
            listener = object : CreateExerciseInteractionListener {
                override fun onNameChanged(name: String) {}
                override fun onImagePicked(image: com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage) {}
                override fun onEquipmentToggled(equipmentId: Long) {}
                override fun onImagePickerClicked() {}
                override fun onImagePickerDismiss() {}
                override fun onImageRetrieved(image: UiImage) {}
                override fun onMeasurementTypeSelected(type: CreateExerciseState.MeasurementType) {}
                override fun onMeasurementValueChanged(value: Int) {}
                override fun onFocusAreaToggled(focusArea: CreateExerciseState.FocusArea) {}
                override fun onDescriptionChanged(description: String) {}
                override fun onSaveClicked() {}
                override fun onExitClicked() {}
                override fun onExitOptionSelected(saveBeforeExit: Boolean) {}
            })
    }
}