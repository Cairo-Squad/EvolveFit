package com.cairosquad.evolvefit.ui.screen.editProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.LabeledInputField
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.DateBottomSheet
import com.cairosquad.evolvefit.ui.component.UserProfileImage
import com.cairosquad.evolvefit.ui.screen.editProfile.content.GenderBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.HeightBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.MainGoalBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.MeasurementBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.ToolsBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.WeightBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.WorkoutDaysBottomSheet
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileInteractionListener
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileScreenState
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileViewModel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.birth
import evolvefit.composeapp.generated.resources.email
import evolvefit.composeapp.generated.resources.full_name
import evolvefit.composeapp.generated.resources.gender
import evolvefit.composeapp.generated.resources.goal
import evolvefit.composeapp.generated.resources.height
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.no_tools_title
import evolvefit.composeapp.generated.resources.no_workouts
import evolvefit.composeapp.generated.resources.personal_information
import evolvefit.composeapp.generated.resources.save_changes
import evolvefit.composeapp.generated.resources.units
import evolvefit.composeapp.generated.resources.weight
import evolvefit.composeapp.generated.resources.workouts_days
import evolvefit.composeapp.generated.resources.your_tools
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.round


@Composable
fun EditProfileScreen(
    navigateBack: () -> Unit,
    viewModel: EditProfileViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.screenState.collectAsState()
    EditProfileScreenContent(state, viewModel)

}

@Composable
fun EditProfileScreenContent(
    state: EditProfileScreenState,
    listener: EditProfileInteractionListener,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .windowInsetsPadding(WindowInsets.statusBars),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomAppBar(
            title = stringResource(Res.string.personal_information),
            header = {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = "back icon",
                )
            },
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(top = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserProfileImage(
                modifier = Modifier.padding(top = 24.dp, bottom = 32.dp),
                image = UiImage.ImageUrl(state.profile.imageUrl),
                isImagePickerOpen = state.isImagePickerOpened,
                onImagePickerDismiss = { listener.onImagePickerDismissed() },
                onImagePickerClick = { listener.onImageUrlClicked() },
                onImageRetrieved = { uiImage ->
                    if (uiImage is UiImage.ImageUrl) {
                        listener.onImageUrlChanged(uiImage.url)
                        listener.onImagePickerDismissed()
                    }
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Theme.color.surfaces.surfaceContainer,
                        shape = RoundedCornerShape(16.dp),
                    )
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                LabeledInputField(
                    label = stringResource(Res.string.full_name),
                    value = state.profile.fullName,
                    onValueChange = listener::onFullNameChanged,
                    isDividerVisible = true
                )

                LabeledInputField(
                    label = stringResource(Res.string.email),
                    value = state.profile.email,
                    onValueChange = { },
                    readOnly = true,
                    trailingIcon = null,
                    isDividerVisible = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    LabeledInputField(
                        label = stringResource(Res.string.birth),
                        value = state.profile.dateOfBirth?.toString() ?: "29/04/2000",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = Res.drawable.ic_arrow_down,
                        onClick = { listener.onDateOfBirthClicked() },
                        isDividerVisible = true,
                        modifier = Modifier.weight(1f)
                    )

                    LabeledInputField(
                        label = stringResource(Res.string.gender),
                        value = state.profile.gender,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = Res.drawable.ic_arrow_down,
                        onClick = { listener.onGenderClicked() },
                        isDividerVisible = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                LabeledInputField(
                    label = stringResource(Res.string.units),
                    value = state.profile.preferredMeasurementStandard,
                    onValueChange = { },
                    readOnly = false,
                    onClick = { listener.onPreferredMeasurementStandardClicked() },
                    trailingIcon = Res.drawable.ic_arrow_down,
                    isDividerVisible = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    LabeledInputField(
                        label = stringResource(Res.string.height),
                        value =(round(state.profile.height * 10) / 10).toString(),
                        onValueChange = {},
                        readOnly = false,
                        trailingIcon = Res.drawable.ic_arrow_down,
                        onClick = { listener.onHeightClicked() },
                        isDividerVisible = true,
                        modifier = Modifier.weight(1f)
                    )

                    LabeledInputField(
                        label = stringResource(Res.string.weight),
                        value = (round(state.profile.weight * 10) / 10).toString(),
                        onValueChange = { },
                        readOnly = true,
                        trailingIcon = Res.drawable.ic_arrow_down,
                        onClick = { listener.onWeightClicked() },
                        isDividerVisible = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                LabeledInputField(
                    label = stringResource(Res.string.goal),
                    value = state.profile.mainGoal,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = Res.drawable.ic_arrow_down,
                    isDividerVisible = true,
                    onClick = { listener.onMainGoalClicked() }
                )

                LabeledInputField(
                    label = stringResource(Res.string.your_tools),
                    value = if (state.userEquipments.isEmpty()) {
                        stringResource(Res.string.no_tools_title)
                    } else {
                        state.userEquipments.joinToString(", ") { it.name }
                    },
                    onValueChange = { listener::onEquipmentChanged },
                    readOnly = true,
                    trailingIcon = Res.drawable.ic_arrow_down,
                    isDividerVisible = true,
                    onClick = { listener.onEquipmentClicked() }
                )

                LabeledInputField(
                    label = stringResource(Res.string.workouts_days),
                    value = if (state.userWorkoutsDays.isEmpty()) {
                        stringResource(Res.string.no_workouts)
                    } else {
                        state.userWorkoutsDays.joinToString(",") { it.name }
                    },
                    onValueChange = { },
                    readOnly = true,
                    trailingIcon = Res.drawable.ic_arrow_down,
                    isDividerVisible = false,
                    onClick = { listener.onWorkoutDaysClicked() }
                )
            }

            PrimaryButton(
                modifier = Modifier.padding(top = 29.dp, bottom = 32.dp),
                text = stringResource(Res.string.save_changes),
                onClick = { listener.onSaveChangesClicked() }
            )
        }
    }
    when (state.bottomSheetType) {
        EditProfileScreenState.EditProfileBottomSheetType.BIRTHDAY -> {
            DateBottomSheet(
                maxDate = "2025",
                dateOfBirth = state.profile.dateOfBirth?.toString() ?: "",
                isDatePickerBottomSheetOpen = true,
                onDatePickerDismiss = { listener.onBottomSheetDismissed() },
                onDateChange = { date ->
                    listener.onDateOfBirthChanged(LocalDate.parse(date))
                    listener.onBottomSheetDismissed()
                }
            )
        }

        EditProfileScreenState.EditProfileBottomSheetType.EQUIPMENT -> {
            ToolsBottomSheet(
                userEquipments = state.userEquipments,
                allEquipments = state.allEquipments,
                onEquipmentBottomSheetDismiss = { listener.onBottomSheetDismissed() },
                onEquipmentChange = { tools ->
                    listener.onEquipmentChanged(tools)
                    listener.onBottomSheetDismissed()

                }
            )


        }

        EditProfileScreenState.EditProfileBottomSheetType.WORKOUTS_DAYS -> {
            WorkoutDaysBottomSheet(
                userWorkoutDays = state.userWorkoutsDays,
                isWorkoutDaysBottomSheetOpen = true,
                onWorkoutDaysBottomSheetDismiss = { listener.onBottomSheetDismissed() },
                onWorkoutDaysChange = { workoutDays ->
                    listener.onWorkoutDaysChanged(workoutDays)
                }
            )

        }

        EditProfileScreenState.EditProfileBottomSheetType.GENDER -> {
            GenderBottomSheet(
                selectedGender = state.profile.gender,
                onGenderBottomSheetDismiss = { listener.onBottomSheetDismissed() },
                onGenderChange = { gender ->
                    listener.onGenderChanged(gender)
                    listener.onBottomSheetDismissed()
                }
            )

        }

        EditProfileScreenState.EditProfileBottomSheetType.MAIN_GOAL -> {
            MainGoalBottomSheet(
                selectedGoal = state.profile.mainGoal,
                onGoalBottomSheetDismiss = { listener.onBottomSheetDismissed() },
                onGoalChange = { goal ->
                    listener.onMainGoalChanged(goal)
                }

            )

        }

        EditProfileScreenState.EditProfileBottomSheetType.MEASUREMENT_STANDARD -> {
            MeasurementBottomSheet(
                selectedMeasurementStandard = state.profile.preferredMeasurementStandard,
                onDismiss = { listener.onBottomSheetDismissed() },
                onMeasurementChange = { unit ->
                    listener.onPreferredMeasurementStandardChanged(unit)
                }
            )

        }

        EditProfileScreenState.EditProfileBottomSheetType.HEIGHT -> {
            HeightBottomSheet(
                selectedHeight = state.profile.height,
                measurementStandard = state.profile.preferredMeasurementStandard,
                onHeightBottomSheetDismiss = { listener.onBottomSheetDismissed() },
                onHeightChange = { height ->
                    listener.onHeightChanged(height)
                }

            )

        }

        EditProfileScreenState.EditProfileBottomSheetType.WEIGHT -> {
            WeightBottomSheet(
                selectedWeight = state.profile.weight,
                measurementStandard = state.profile.preferredMeasurementStandard,
                onWeightBottomSheetDismiss = { listener.onBottomSheetDismissed() },
                onWeightChange = { weight ->
                    listener.onWeightChanged(weight)
                }

            )

        }

        null -> Unit
    }
}


@Preview()
@Composable
private fun EditProfileScreenPreview() {
    val sampleProfile = EditProfileScreenState.ProfileUiState(
        fullName = "Hawraa Mahmood",
        email = "hawraamahmood@gmail.com",
        dateOfBirth = null,
        gender = "Female",
        height = 166f,
        weight = 64.5f,
        mainGoal = "Los Weight",
        imageUrl = "",
        preferredMeasurementStandard = "Metric"
    )

    val sampleState = EditProfileScreenState(
        profile = sampleProfile
    )

    EditProfileScreenContent(
        state = sampleState,
        listener = object : EditProfileInteractionListener {
            override fun onBackClicked() {}
            override fun onSaveChangesClicked() {}
            override fun onDateOfBirthClicked() {}
            override fun onGenderClicked() {}
            override fun onHeightClicked() {}
            override fun onWeightClicked() {}
            override fun onMainGoalClicked() {}
            override fun onEquipmentClicked() {}
            override fun onWorkoutDaysClicked() {}
            override fun onEquipmentChanged(equipments: Set<EditProfileScreenState.EquipmentUiState>) {}
            override fun onWorkoutDaysChanged(workoutsDays: Set<EditProfileScreenState.WeekDayUiState>) {}
            override fun onPreferredMeasurementStandardClicked() {}
            override fun onImageUrlClicked() {}
            override fun onFullNameChanged(fullName: String) {}
            override fun onDateOfBirthChanged(dateOfBirth: LocalDate) {}
            override fun onGenderChanged(gender: String) {}
            override fun onHeightChanged(height: Float) {}
            override fun onWeightChanged(weight: Float) {}
            override fun onMainGoalChanged(mainGoal: String) {}
            override fun onPreferredMeasurementStandardChanged(measurementStandard: String) {}
            override fun onImageUrlChanged(imageUrl: String) {}
            override fun onImagePickerDismissed() {}
            override fun onBottomSheetDismissed() {
                TODO("Not yet implemented")
            }
        }
    )
}