package com.cairosquad.evolvefit.ui.screen.editProfile
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.DateBottomSheet
import com.cairosquad.evolvefit.ui.component.DateBottomSheetContent
import com.cairosquad.evolvefit.ui.component.UserProfileImage
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileScreenState
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileInteractionListener
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileViewModel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import com.cairosquad.evolvefit.viewmodel.register.RegisterViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_question_mark
import evolvefit.composeapp.generated.resources.personal_information
import kotlinx.datetime.LocalDate
import evolvefit.composeapp.generated.resources.female
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_date
import evolvefit.composeapp.generated.resources.ic_mail
import evolvefit.composeapp.generated.resources.ic_profile
import evolvefit.composeapp.generated.resources.male
import evolvefit.composeapp.generated.resources.select_gender_description
import evolvefit.composeapp.generated.resources.select_gender_title
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Clock


@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.screenState.collectAsState()
    EditProfileScreenContent(state,viewModel)

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
            .background(color = Theme.color.surfaces.surface),
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



        InputField(
            value = state.profile.fullName,
            onValueChange = listener::onFullNameChanged,
            placeholder = "Full Name",
            readOnly = false,
            leadingIcon = Res.drawable.ic_profile,
            trailingIcon = Res.drawable.ic_arrow_down,
            onTrailingIconClick = { listener.onFullNameClicked() },
            onClick = { listener.onFullNameClicked() }
        )

        InputField(
            value = state.profile.email,
            onValueChange = { },
            placeholder = "Email",
            readOnly = true,
            leadingIcon = Res.drawable.ic_mail
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InputField(
                value = state.profile.dateOfBirth?.toString() ?: "29/04/2000",
                onValueChange = {},
                placeholder = "DOB",
                readOnly = false,
                leadingIcon = Res.drawable.ic_date,
                trailingIcon = Res.drawable.ic_arrow_down,
                onClick = { listener.onDateOfBirthClicked() },
                modifier = Modifier.weight(1f)
            )

            InputField(
                value = state.profile.gender.ifEmpty { "Female" },
                onValueChange = {listener::onGenderChanged},
                placeholder = "Gender",
                readOnly = true,
                trailingIcon = Res.drawable.ic_arrow_down,
                onClick = { listener.onGenderClicked() },
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InputField(
                value = if (state.profile.height > 0) "${state.profile.height.toInt()} cm" else "166 cm",
                onValueChange = {listener::onHeightChanged},
                placeholder = "Height",
                readOnly = true,
                trailingIcon = Res.drawable.ic_arrow_down,
                onClick = { listener.onHeightClicked() },
                modifier = Modifier.weight(1f)
            )

            InputField(
                value = if (state.profile.weight > 0) "${state.profile.weight} kg" else "64.5 kg",
                onValueChange = {listener::onWeightChanged},
                placeholder = "Weight",
                readOnly = true,
                trailingIcon = Res.drawable.ic_arrow_down,
                onClick = { listener.onWeightClicked() },
                modifier = Modifier.weight(1f)
            )
        }
        InputField(
            value = state.profile.mainGoal.ifEmpty { "Lose Weight" },
            onValueChange = {},
            placeholder = "Main Goal",
            readOnly = true,
            trailingIcon = Res.drawable.ic_arrow_down,
            onClick = { listener.onMainGoalClicked() }
        )

        InputField(
            value = if (state.userEquipments.isEmpty()) {
                "No tools"
            } else {
                state.userEquipments.joinToString(", ") { it.name }
            },
            onValueChange = {listener::onEquipmentChanged},
            placeholder = "Your Tools",
            readOnly = true,
            trailingIcon = Res.drawable.ic_arrow_down,
            onClick = { listener.onEquipmentClicked() }
        )

        InputField(
            value = if(state.userWorkoutsDays.isEmpty()){
                "No Workouts Days"
            }else{
                state.userWorkoutsDays.joinToString(","){it.name}
            },
            onValueChange = {listener::onWorkoutDaysChanged},
            placeholder = "Workout Days",
            readOnly = true,
            trailingIcon = Res.drawable.ic_arrow_down,
            onClick = { listener.onWorkoutDaysClicked() }
        )

        PrimaryButton(
            text="save changes",
            onClick = { listener.onSaveChangesClicked() }
        )

    }
    when(state.bottomSheetType)
    {
        EditProfileScreenState.EditProfileBottomSheetType.BIRTHDAY->{
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
        EditProfileScreenState.EditProfileBottomSheetType.MAIN_GOAL-> {

        }
        EditProfileScreenState.EditProfileBottomSheetType.MEASUREMENT_STANDARD -> {

        }
        EditProfileScreenState.EditProfileBottomSheetType.HEIGHT-> {

        }
        EditProfileScreenState.EditProfileBottomSheetType.WEIGHT-> {

        }
        null->Unit
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
            override fun onFullNameClicked() {}
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