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
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileInteractionListener
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileScreenState
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileViewModel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.email
import evolvefit.composeapp.generated.resources.full_name
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_mail
import evolvefit.composeapp.generated.resources.personal_information
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


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
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
            .background(color = Theme.color.surfaces.surface, shape = RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomAppBar(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            title = stringResource(Res.string.personal_information),
            header = {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = "back icon",
                )

            },
        )
        UserProfileImage(
            modifier = Modifier.padding(top = 24.dp, bottom = 32.dp),
            image = UiImage.ImageUrl(state.profile.imageUrl),
            isImagePickerOpen = state.isImagePickerOpened,
            onImagePickerDismiss = { listener.onImagePickerDismissed() },
            onImagePickerClick = { listener.onImageUrlClicked() },
            onImageRetrieved = { uiImage ->
                if (uiImage is UiImage.ImageUrl) {
                    listener.onImageUrlChanged(uiImage.url)
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
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LabeledInputField(
                label = stringResource(Res.string.full_name),
                value = state.profile.fullName,
                onValueChange = listener::onFullNameChanged,
                trailingIcon = Res.drawable.ic_mail,
                isDividerVisible = true
            )


            LabeledInputField(
                label = stringResource(Res.string.email),
                value = state.profile.email,
                onValueChange = { },
                placeholder = "Email",
                readOnly = true,
                trailingIcon = null,
                isDividerVisible = true
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                LabeledInputField(
                    label = "DOB",
                    value = state.profile.dateOfBirth?.toString() ?: "29/04/2000",
                    onValueChange = {},
                    placeholder = "DOB",
                    readOnly = true,
                    trailingIcon = Res.drawable.ic_arrow_down,
                    onClick = { listener.onDateOfBirthClicked() },
                    isDividerVisible = true,
                    modifier = Modifier.weight(1f)
                )

                LabeledInputField(
                    label = "gender",
                    value = state.profile.gender,
                    onValueChange = {},
                    placeholder = "Gender",
                    readOnly = true,
                    trailingIcon = Res.drawable.ic_arrow_down,
                    onClick = { listener.onGenderClicked() },
                    isDividerVisible = true,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                LabeledInputField(
                    label = "Height",
                    value = (state.profile.height).toString(),
                    onValueChange = {},
                    placeholder = "Height",
                    readOnly = true,
                    trailingIcon = Res.drawable.ic_arrow_down,
                    onClick = { listener.onHeightClicked() },
                    isDividerVisible = true,
                    modifier = Modifier.weight(1f)
                )

                LabeledInputField(
                    label = "weight",
                    value = state.profile.weight.toString(),
                    onValueChange = { },
                    placeholder = "Weight",
                    readOnly = true,
                    trailingIcon = Res.drawable.ic_arrow_down,
                    onClick = { listener.onWeightClicked() },
                    isDividerVisible = true,
                    modifier = Modifier.weight(1f)
                )
            }
            LabeledInputField(
                label = "goal",
                value = state.profile.mainGoal.ifEmpty { "Lose Weight" },
                onValueChange = {},
                placeholder = "Main Goal",
                readOnly = true,
                trailingIcon = Res.drawable.ic_arrow_down,
                isDividerVisible = true,
                onClick = { listener.onMainGoalClicked() }
            )

            LabeledInputField(
                label = "your tools",
                value = if (state.userEquipments.isEmpty()) {
                    "No tools"
                } else {
                    state.userEquipments.joinToString(", ") { it.name }
                },
                onValueChange = { listener::onEquipmentChanged },
                placeholder = "Your Tools",
                readOnly = true,
                trailingIcon = Res.drawable.ic_arrow_down,
                isDividerVisible = true,
                onClick = { listener.onEquipmentClicked() }
            )

            LabeledInputField(
                label = "workouts",
                value = if (state.userWorkoutsDays.isEmpty()) {
                    "No Workouts Days"
                } else {
                    state.userWorkoutsDays.joinToString(",") { it.name }
                },
                onValueChange = { },
                placeholder = "Workout Days",
                readOnly = true,
                trailingIcon = Res.drawable.ic_arrow_down,
                isDividerVisible = true,
                onClick = { listener.onWorkoutDaysClicked() }
            )
        }

        PrimaryButton(
            modifier = Modifier.padding(top = 29.dp),
            text = "save changes",
            onClick = { listener.onSaveChangesClicked() }
        )


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

        }

        EditProfileScreenState.EditProfileBottomSheetType.WORKOUTS_DAYS -> {

        }

        EditProfileScreenState.EditProfileBottomSheetType.FULL_NAME -> {

        }

        EditProfileScreenState.EditProfileBottomSheetType.GENDER -> {

        }

        EditProfileScreenState.EditProfileBottomSheetType.MAIN_GOAL -> {

        }

        EditProfileScreenState.EditProfileBottomSheetType.MEASUREMENT_STANDARD -> {

        }

        EditProfileScreenState.EditProfileBottomSheetType.HEIGHT -> {

        }

        EditProfileScreenState.EditProfileBottomSheetType.WEIGHT -> {

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