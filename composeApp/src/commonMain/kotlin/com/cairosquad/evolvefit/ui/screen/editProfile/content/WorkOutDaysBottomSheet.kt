package com.cairosquad.evolvefit.ui.screen.editProfile.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.SelectableDayBox
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.save
import evolvefit.composeapp.generated.resources.workout_days_title
import evolvefit.composeapp.generated.resources.workout_days_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun WorkoutDaysBottomSheet(
    userWorkoutDays: Set<EditProfileScreenState.WeekDayUiState>,
    isWorkoutDaysBottomSheetOpen: Boolean = true,
    onWorkoutDaysBottomSheetDismiss: () -> Unit,
    onWorkoutDaysChange: (Set<EditProfileScreenState.WeekDayUiState>) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        isVisible = isWorkoutDaysBottomSheetOpen,
        onDismiss = onWorkoutDaysBottomSheetDismiss,
        modifier = modifier.fillMaxWidth()
    ) {
        WorkoutDaysBottomSheetContent(
            userWorkoutDays = userWorkoutDays,
            onWorkoutDaysChange = onWorkoutDaysChange,
            onWorkoutDaysBottomSheetDismiss = onWorkoutDaysBottomSheetDismiss
        )
    }
}

@Composable
fun WorkoutDaysBottomSheetContent(
    userWorkoutDays: Set<EditProfileScreenState.WeekDayUiState>,
    onWorkoutDaysChange: (Set<EditProfileScreenState.WeekDayUiState>) -> Unit,
    onWorkoutDaysBottomSheetDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    var selectedWorkoutDays by remember(userWorkoutDays) {
        mutableStateOf(userWorkoutDays.toSet())
    }

    val weekdays = remember {
        listOf(
            EditProfileScreenState.WeekDayUiState.SUNDAY,
            EditProfileScreenState.WeekDayUiState.MONDAY,
            EditProfileScreenState.WeekDayUiState.TUESDAY,
            EditProfileScreenState.WeekDayUiState.WEDNESDAY,
            EditProfileScreenState.WeekDayUiState.THURSDAY,
            EditProfileScreenState.WeekDayUiState.FRIDAY,
            EditProfileScreenState.WeekDayUiState.SATURDAY
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        RegisterHeader(
            modifier = Modifier.padding(bottom = 24.dp),
            title = stringResource(Res.string.workout_days_title),
            description = stringResource(Res.string.workout_days_description)
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(weekdays) { day ->
                val isSelected = selectedWorkoutDays.contains(day)
                val dayText = when(day) {
                    EditProfileScreenState.WeekDayUiState.SUNDAY -> "Sunday"
                    EditProfileScreenState.WeekDayUiState.MONDAY -> "Monday"
                    EditProfileScreenState.WeekDayUiState.TUESDAY -> "Tuesday"
                    EditProfileScreenState.WeekDayUiState.WEDNESDAY -> "Wednesday"
                    EditProfileScreenState.WeekDayUiState.THURSDAY -> "Thursday"
                    EditProfileScreenState.WeekDayUiState.FRIDAY -> "Friday"
                    EditProfileScreenState.WeekDayUiState.SATURDAY -> "Saturday"
                }

                SelectableDayBox(
                    textDay = dayText,
                    isSelected = isSelected,
                    onClick = {
                        selectedWorkoutDays = if (isSelected) {
                            selectedWorkoutDays - day
                        } else {
                            selectedWorkoutDays + day
                        }
                        onWorkoutDaysChange(selectedWorkoutDays)
                    }
                )
            }
        }

        PrimaryButton(
            text = "Confirm",
            onClick = onWorkoutDaysBottomSheetDismiss,
            modifier = Modifier.fillMaxWidth()
        )
    }
}