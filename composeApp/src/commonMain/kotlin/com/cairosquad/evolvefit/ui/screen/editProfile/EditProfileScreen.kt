package com.cairosquad.evolvefit.ui.screen.editProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
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
import com.cairosquad.evolvefit.domain.entity.Profile.FitnessGoal
import com.cairosquad.evolvefit.domain.entity.Profile.Gender
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.ui.component.DateBottomSheet
import com.cairosquad.evolvefit.ui.component.UserProfileImage
import com.cairosquad.evolvefit.ui.screen.editProfile.content.GenderBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.HeightBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.MainGoalBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.MeasurementBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.ToolsBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.WeightBottomSheet
import com.cairosquad.evolvefit.ui.screen.editProfile.content.WorkoutDaysBottomSheet
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.edit_profile.EditProfileEffect
import com.cairosquad.evolvefit.viewmodel.edit_profile.EditProfileInteractionListener
import com.cairosquad.evolvefit.viewmodel.edit_profile.EditProfileScreenState
import com.cairosquad.evolvefit.viewmodel.edit_profile.EditProfileViewModel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.birth
import evolvefit.composeapp.generated.resources.email
import evolvefit.composeapp.generated.resources.female
import evolvefit.composeapp.generated.resources.friday
import evolvefit.composeapp.generated.resources.full_name
import evolvefit.composeapp.generated.resources.gain_weight
import evolvefit.composeapp.generated.resources.gender
import evolvefit.composeapp.generated.resources.goal
import evolvefit.composeapp.generated.resources.height
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.lose_weight
import evolvefit.composeapp.generated.resources.male
import evolvefit.composeapp.generated.resources.monday
import evolvefit.composeapp.generated.resources.no_tools_title
import evolvefit.composeapp.generated.resources.no_workouts
import evolvefit.composeapp.generated.resources.personal_information
import evolvefit.composeapp.generated.resources.saturday
import evolvefit.composeapp.generated.resources.save_changes
import evolvefit.composeapp.generated.resources.stay_in_shape
import evolvefit.composeapp.generated.resources.sunday
import evolvefit.composeapp.generated.resources.thursday
import evolvefit.composeapp.generated.resources.tuesday
import evolvefit.composeapp.generated.resources.unit_imperial
import evolvefit.composeapp.generated.resources.unit_metric
import evolvefit.composeapp.generated.resources.units
import evolvefit.composeapp.generated.resources.wednesday
import evolvefit.composeapp.generated.resources.weight
import evolvefit.composeapp.generated.resources.workouts_days
import evolvefit.composeapp.generated.resources.your_tools
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.round


@Composable
fun EditProfileScreen(
    navigateBack: () -> Unit,
    viewModel: EditProfileViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()
    ObserveAsEffect(viewModel.effect) {effect->
        when(effect){
            EditProfileEffect.NavigateBack->navigateBack()
        }

    }
    EditProfileScreenContent(state, viewModel, navigateBack)

}

@Composable
fun EditProfileScreenContent(
    state: EditProfileScreenState,
    listener: EditProfileInteractionListener,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .windowInsetsPadding(WindowInsets.statusBars).padding(top = 15.dp)
            .windowInsetsPadding(WindowInsets.navigationBars),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomAppBar(
            title = stringResource(Res.string.personal_information),
            header = {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = "back icon",
                    tint = Theme.color.surfaces.onSurface,
                    modifier = Modifier
                        .clickable { navigateBack() }
                )
            },
            modifier = Modifier.padding(start = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            UserProfileImage(
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
                image = UiImage.ImageUrl(state.profile.imageUrl),
                isImagePickerOpen = state.isImagePickerOpened,
                onImagePickerDismiss = { listener.onImagePickerDismissed() },
                onImagePickerClick = { listener.onImageUrlClicked() },
                onImageRetrieved = { uiImage ->
                    when (uiImage) {
                        is UiImage.ImageFile -> {
                            listener.onImageRetrieved(uiImage)
                        }

                        is UiImage.ImageUrl -> {
                            listener.onImageUrlChanged(uiImage.url)
                            listener.onImagePickerDismissed()
                        }

                        else -> Unit
                    }
                },
                isEditScreen = true,
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
                        value = mapGenderToString(state.profile.gender),
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
                    value = mapMeasurementStandardToString(state.profile.preferredMeasurementStandard),
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
                        value = (round(state.profile.height * 10) / 10).toString(),
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
                    value = mapMainGoalToString(state.profile.mainGoal),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = Res.drawable.ic_arrow_down,
                    isDividerVisible = true,
                    onClick = { listener.onMainGoalClicked() }
                )

                LabeledInputField(
                    label = stringResource(Res.string.your_tools),
                    value = if (state.profile.equipments.isEmpty()) {
                        stringResource(Res.string.no_tools_title)
                    } else {
                        state.profile.equipments.joinToString(", ") { it.name }
                    },
                    onValueChange = { },
                    readOnly = true,
                    trailingIcon = Res.drawable.ic_arrow_down,
                    isDividerVisible = true,
                    onClick = { listener.onEquipmentClicked() }
                )

                LabeledInputField(
                    label = stringResource(Res.string.workouts_days),
                    value = mapWorkoutDaysToString(state.profile.workoutDays),
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
                onClick = { listener.onSaveChangesClicked(state.profile) }
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
                userEquipments = state.profile.equipments,
                allEquipments = state.allEquipments,
                onEquipmentBottomSheetDismiss = { listener.onBottomSheetDismissed() },
                onEquipmentChange = { tools ->
                    listener.onEquipmentChanged(tools)

                }
            )


        }

        EditProfileScreenState.EditProfileBottomSheetType.WORKOUTS_DAYS -> {
            WorkoutDaysBottomSheet(
                userWorkoutDays = state.profile.workoutDays,
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

@Composable
private fun mapWorkoutDaysToString(workoutDays: Set<EditProfileScreenState.WeekDayUiState>): String {
    return if (workoutDays.isEmpty()) {
        stringResource(Res.string.no_workouts)
    } else {
        workoutDays
            .map { day ->
                when (day) {
                    EditProfileScreenState.WeekDayUiState.SUNDAY -> stringResource(Res.string.sunday)
                    EditProfileScreenState.WeekDayUiState.MONDAY -> stringResource(Res.string.monday)
                    EditProfileScreenState.WeekDayUiState.TUESDAY -> stringResource(Res.string.tuesday)
                    EditProfileScreenState.WeekDayUiState.WEDNESDAY -> stringResource(Res.string.wednesday)
                    EditProfileScreenState.WeekDayUiState.THURSDAY -> stringResource(Res.string.thursday)
                    EditProfileScreenState.WeekDayUiState.FRIDAY -> stringResource(Res.string.friday)
                    EditProfileScreenState.WeekDayUiState.SATURDAY -> stringResource(Res.string.saturday)
                }
            }
            .joinToString(", ")
    }
}

@Composable
private fun mapGenderToString(gender: Gender): String {
    return when (gender) {
        Gender.MALE -> stringResource(Res.string.male)
        Gender.FEMALE -> stringResource(Res.string.female)
    }
}

@Composable
private fun mapMeasurementStandardToString(standard: MeasurementStandard): String {
    return when (standard) {
        MeasurementStandard.METRIC -> stringResource(Res.string.unit_metric)
        MeasurementStandard.IMPERIAL -> stringResource(Res.string.unit_imperial)
    }
}

@Composable
private fun mapMainGoalToString(goal: FitnessGoal): String {
    return when (goal) {
        FitnessGoal.LOSE_WEIGHT -> stringResource(Res.string.lose_weight)
        FitnessGoal.GAIN_WEIGHT -> stringResource(Res.string.gain_weight)
        FitnessGoal.STAY_IN_SHAPE-> stringResource(Res.string.stay_in_shape)

    }
}